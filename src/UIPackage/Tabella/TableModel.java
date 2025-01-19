package UIPackage.Tabella;


import LogicaPackage.COSTANTI;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.stream.Stream;

public class TableModel extends AbstractTableModel {
    private final String[] columnNames = Stream.concat(Stream.of("Orario"), Arrays.stream(COSTANTI.aule))
            .toArray(String[]::new);;


    private final Object[][] data;

    public TableModel(){
        data = new String[11][columnNames.length];

        for (int i = 0; i < 11; i++) {
            data[i][0] = COSTANTI.orariAmmessi[i]; // Prima colonna con gli orari
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
    public boolean isCellEditable(int row, int col){
        return col > 0;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
