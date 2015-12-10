package view.client;

import controller.StationJpaController;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import javax.persistence.EntityManagerFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;
import model.Station;
import persistence.PersistenceSingleton;
import util.Register;
import util.StationData;

public class NotificationAreaMenu {

    EntityManagerFactory emf = PersistenceSingleton.getInstance().getEntityManagerFactory();
    StationJpaController stationJpaController = new StationJpaController(emf);

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

    public static void main(String[] args) throws InterruptedException {
        if (!SystemTray.isSupported()) {
            String message = "Área de notificação não suportada pelo sistema,\n"
                    + "o aplicativo não será iniciado";
            System.err.println(message);
            JOptionPane.showMessageDialog(null, message, "Erro", ERROR_MESSAGE);
            System.exit(0);
        } else {
            final PopupMenu popupMenu = new PopupMenu();
            final SystemTray systemTray = SystemTray.getSystemTray();

            URL url = NotificationAreaMenu.class.getResource("images/icon.png");
            final TrayIcon trayIcon = new TrayIcon((new ImageIcon(url, "TDFW")).getImage());

            MenuItem menuItem1 = new MenuItem("1");
            MenuItem menuItem2 = new MenuItem("Sair");

            popupMenu.add(menuItem1);
            popupMenu.addSeparator();
            popupMenu.add(menuItem2);

            trayIcon.setPopupMenu(popupMenu);

            try {
                systemTray.add(trayIcon);
            } catch (Exception e) {
                String message = "Não foi possível inserir ícone\n" + e;
                System.err.println(message);
                JOptionPane.showMessageDialog(null, message, "Erro", ERROR_MESSAGE);
                System.exit(0);
            }

            menuItem1.addActionListener((ActionEvent actionEvent) -> {
                trayIcon.displayMessage("Oi", "Item 1", TrayIcon.MessageType.INFO);
            });

            menuItem2.addActionListener((ActionEvent actionEvent) -> {
                int quit = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair?", YES_NO_OPTION, QUESTION_MESSAGE);
                if (quit == YES_OPTION) {
                    System.exit(0);
                }
            });

            Register report = new Register();
            Thread thread = new Thread(report);
            thread.start();
        }
    }
}
