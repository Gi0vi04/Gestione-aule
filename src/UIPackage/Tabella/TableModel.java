package UIPackage.Tabella;


import LogicaPackage.Aula;
import LogicaPackage.Utils.Costanti;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    //Creo i nomi delle colonne aggiungendo alle aule la colonna "Orario"
    private final String[] columnNames;
    private final Object[][] data;

    public TableModel(){
        columnNames = new String[Costanti.AULE.length + 1];
        columnNames[0] = "Orario";
        for(int i = 0; i < Costanti.AULE.length; i++){
            columnNames[i + 1] = Costanti.AULE[i].getNomeAula();
        }

        data = new String[11][columnNames.length];
        for(int i = 0; i < 11; i++){
            data[i][0] = Costanti.ORARI_AMMESSI[i];
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
