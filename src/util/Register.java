package util;

import controller.ProcessJpaController;
import controller.StationJpaController;
import controller.exceptions.NonexistentEntityException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Station;
import model.Process;
import persistence.PersistenceSingleton;

public class Register implements Runnable {

    EntityManagerFactory emf = PersistenceSingleton.getInstance().getEntityManagerFactory();
    StationJpaController stationJpaController = new StationJpaController(emf);
    ProcessJpaController pjc = new ProcessJpaController(emf);

    Station station;

    public void setStationAttributes() {
        try {
            station.setCpuCores(StationData.getCPUCores());
            station.setFreeRAM(StationData.getFreeRAM());
            station.setIpv4(StationData.getIPv4Address());
            station.setIpv6(StationData.getIPv6Address());
            station.setLastLogin(new Timestamp(System.currentTimeMillis()));
            station.setMac(StationData.getMACAddress());
            station.setName(StationData.getHostName());
            station.setOperationalSystem(StationData.getOperationalSystem());
            station.setTotalRAM(StationData.getTotalRAM());
        } catch (UnknownHostException | SocketException e) {
            String message = "Não foi possível capturar os dados dessa estação\n" + e;
            System.err.println(message);
            JOptionPane.showMessageDialog(null, message, "Erro", ERROR_MESSAGE);
        }
    }

    public void processList() {
        Process process = new Process();
        try {
            List<Process> listaProcessos = pjc.findProcessEntities();

            for (Process p : listaProcessos) {
                pjc.destroy(p.getId());
            }

            InputStreamReader isr = new InputStreamReader(Runtime.getRuntime().exec("ps -U alunos").getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            boolean primeiroInt;

            while ((line = br.readLine()) != null) {
                String pid;
                String cmd;
                primeiroInt = false;
                pid = "";

                if (!line.contains("PID")) {
                    cmd = line.substring(24, line.length());

                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) != ' ') {
                            primeiroInt = true;
                            pid += line.charAt(i);
                        }
                        if (primeiroInt) {
                            if (line.charAt(i) == ' ') {
                                break;
                            }
                        }
                    }

                    process.setPid(Integer.parseInt(pid));
                    process.setCmd(cmd);
                    process.setStation(station);
                    process.setActive(true);

                    pjc.create(process);
                }
            }
        } catch (NonexistentEntityException | IOException | NumberFormatException exception) {
            System.err.println(exception);
            JOptionPane.showMessageDialog(null, exception, "Erro", ERROR_MESSAGE);
        }
    }

    @Override
    public void run() {
        try {
            this.station = stationJpaController.findStationByMac(StationData.getMACAddress());
        } catch (SocketException e) {
            String message = "Não foi possível capturar o endereço MAC dessa estação\n" + e;
            System.err.println(message);
            JOptionPane.showMessageDialog(null, message, "Erro", ERROR_MESSAGE);

        }
        if (station != null) {
            setStationAttributes();
            try {
                stationJpaController.edit(station);
            } catch (Exception e) {
                String message = "Não foi possível atualizar os dados dessa estação\n" + e;
                System.err.println(message);
                JOptionPane.showMessageDialog(null, message, "Erro", ERROR_MESSAGE);
            }
        } else {
            station = new Station();
            setStationAttributes();
            stationJpaController.create(station);
        }
        while(true){
            processList();
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
