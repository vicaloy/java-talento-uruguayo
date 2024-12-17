package GUI.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import valueObjects.VOPerformance;

public class ModeloDatosTablaPerformance extends AbstractTableModel {

    private final Class[] tipoColumnas;
    private final String[] titleColumnas;
    private List<VOPerformance> performances;

    public List<VOPerformance> getPerformances() {
        return performances;
    }

    public void setPerformances(ArrayList<VOPerformance> performances) {
        this.performances = performances;
    }

    public ModeloDatosTablaPerformance() {
    	performances = new ArrayList();
        this.titleColumnas = new String[]{"Número", "Descripción", "Ptos uno", "Ptos dos", "Ptos tres"};
        this.tipoColumnas = new Class[]{Integer.class, String.class, Integer.class, Integer.class, Integer.class};
    }

    @Override
    public String getColumnName(int column) {
        return titleColumnas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tipoColumnas[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return performances.size();
    }

    @Override
    public int getColumnCount() {
        return titleColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return performances.get(rowIndex).getNumeroPerformance();
            case 1:
                return performances.get(rowIndex).getDescripcion();
            case 2:
                return performances.get(rowIndex).getPuntajeUno();
            case 3: 
                return performances.get(rowIndex).getPuntajeDos();
            case 4:
            	return performances.get(rowIndex).getPuntajeTres();
            default:
                return null;
        }
    }

    /*@Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
            	performances.get(rowIndex).setNumeroPerformance((int)value);
                break;
            case 1:
            	performances.get(rowIndex).setDescripcion(value.toString());
                break;
            case 2:
            	performances.get(rowIndex).setPuntajeUno((int) value);
                break;
            case 3:
            	performances.get(rowIndex).setPuntajeDos((int) value);
            case 4:
            	performances.get(rowIndex).setPuntajeTres((int) value);
            default: ;
        }
        this.fireTableCellUpdated(rowIndex, columnIndex);
        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }*/

}