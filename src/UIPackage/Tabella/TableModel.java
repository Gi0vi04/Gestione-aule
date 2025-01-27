package UIPackage.Tabella;


import LogicaPackage.Aula;
import LogicaPackage.Utils.InputOutput;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {
    //Creo i nomi delle colonne aggiungendo alle aule la colonna "Orario"
    private final String[] columnNames;
    private final Object[][] data;

    public TableModel(ArrayList<Aula> aule){
        columnNames = new String[aule.size() + 1];
        columnNames[0] = "Orario";
        for(int i = 0; i < aule.size(); i++){
            columnNames[i + 1] = aule.get(i).getNomeAula();
        }

        data = new Object[10][columnNames.length];
        for(int i = 0; i < 10; i++){
            data[i][0] = InputOutput.ORARI_AMMESSI[i] + " - " + InputOutput.ORARI_AMMESSI[i + 1];
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
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
