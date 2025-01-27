package UIPackage.Tabella;

import LogicaPackage.Aula;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {
    private final String[] columnNames;
    private final Object[][] data;

    /**
     *
     * @param aule aule da stampare sulla riga di intestazione
     */
    public TableModel(ArrayList<Aula> aule){
        // Aggiungo una colonna per l'orario
        columnNames = new String[aule.size() + 1];
        data = new Object[10][columnNames.length];

        columnNames[0] = "Orario";
        for(int i = 0; i < aule.size(); i++){
            columnNames[i + 1] = aule.get(i).getNomeAula();
        }
        for(int i = 0; i < 10; i++){
            data[i][0] =  (i + 8) + " - " + (i+9);
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
