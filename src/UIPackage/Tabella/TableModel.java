package UIPackage.Tabella;


import LogicaPackage.COSTANTI;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.stream.Stream;

public class TableModel extends AbstractTableModel {
    //Creo i nomi delle colonne aggiungendo alle aule la colonna "Orario"
    private final String[] columnNames = COSTANTI.aule;
    private final Object[][] data;

    public TableModel(){
        data = new String[11][columnNames.length];
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
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
