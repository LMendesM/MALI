package util;

import controller.StationJpaController;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Station;
import persistence.PersistenceSingleton;

public class StationsUpdater implements Runnable {

    private JTable jTable;

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public static void clear(DefaultTableModel dtm) {
        int rowCount = dtm.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
    }

    @Override
    public void run() {
        EntityManagerFactory emf = PersistenceSingleton.getInstance().getEntityManagerFactory();
        StationJpaController stationJpaController = new StationJpaController(emf);
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();

        clear(dtm);

        List<Station> stationsList = stationJpaController.findStationEntities();

        stationsList.stream().forEach((station) -> {
            Long id = station.getId();
            String name = station.getName();
            String mac = station.getMac();
            String ipv4 = station.getIpv4();
            String ipv6 = station.getIpv6();
            int cpuCores = station.getCpuCores();
            Long totalRAM = station.getTotalRAM();
            Long freeRAM = station.getFreeRAM();
            Timestamp lastLogin = station.getLastLogin();
            String operationalSystem = station.getOperationalSystem();

            dtm.addRow(new Object[]{id, false, name, mac, ipv4, ipv6, cpuCores, totalRAM, freeRAM, lastLogin, operationalSystem});
        });
    }

}
