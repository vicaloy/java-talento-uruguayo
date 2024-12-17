package GUI.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import valueObjects.VOParticipanteListado;
import valueObjects.VOPerformance;

public class ModeloDatosTablaParticipante extends AbstractTableModel {

    private final Class[] tipoColumnas;
    private final String[] titleColumnas;
    private List<VOParticipanteListado> participantes;

    public List<VOParticipanteListado> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<VOParticipanteListado> participantes) {
        this.participantes = participantes;
    }

    public ModeloDatosTablaParticipante() {
    	participantes = new ArrayList();
        this.titleColumnas = new String[]{"Nom artístico", "Edad", "Especialidad"};
        this.tipoColumnas = new Class[]{String.class, Integer.class, String.class};
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
        return participantes.size();
    }

    @Override
    public int getColumnCount() {
        return titleColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return participantes.get(rowIndex).getNomArtistico();
            case 1:
                return participantes.get(rowIndex).getEdad();
            case 2:
                return participantes.get(rowIndex).getEspecialidadArtistica();
            default:
                return null;
        }
    }

   

}