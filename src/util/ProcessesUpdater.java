package util;

import controller.ProcessJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Process;
import model.Station;
import persistence.PersistenceSingleton;

public class ProcessesUpdater implements Runnable {

    private JTable jTable;
    private Station station;

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public static void clear(DefaultTableModel dtm) {
        int rowCount = dtm.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            dtm.removeRow(i);
            System.out.println("clear");
        }
    }

    @Override
    public void run() {
        EntityManagerFactory emf = PersistenceSingleton.getInstance().getEntityManagerFactory();
        ProcessJpaController processJpaController = new ProcessJpaController(emf);
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();

        clear(dtm);

        List<Process> processesList = processJpaController.findProcessesByStation(this.station);

        System.out.println("updating");
        for (Process process : processesList) {
            long id = process.getId();
            int pid = process.getPid();
            String cmd = process.getCmd();
            dtm.addRow(new Object[]{id, false, pid, cmd});
        }
    }
}
