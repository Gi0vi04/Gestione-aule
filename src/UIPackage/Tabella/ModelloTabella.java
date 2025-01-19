package UIPackage.Tabella;

import javax.swing.table.AbstractTableModel;

public class ModelloTabella extends AbstractTableModel {
    private final String[] columnNames = {"Orario", "Didattica 1", "Didattica 2", "Laboratorio 1"};

    private final Object[][] data;

    public ModelloTabella(){
        data = new String[11][columnNames.length];
        String[] orari = {
                "08:00", "09:00", "10:00", "11:00", "12:00",
                "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"
        };

        for (int i = 0; i < 11; i++) {
            data[i][0] = orari[i]; // Prima colonna con gli orari
            for (int j = 1; j < columnNames.length; j++) {
                data[i][j] = ""; // Celle vuote iniziali
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data[0][columnIndex].getClass();
    }
}
