package GUI.ventanas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UIManager;

import GUI.controladores.ControladorListadoParticipante;
import GUI.controladores.ControladorListadoPerformance;
import GUI.modelos.ModeloDatosTablaParticipante;
import GUI.modelos.ModeloDatosTablaPerformance;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import valueObjects.VOParticipanteListado;
import valueObjects.VOPerformance;
import java.awt.Color;

public class ListadoParticipante extends javax.swing.JFrame {

	private ModeloDatosTablaParticipante modeloTabla;
	private ControladorListadoParticipante miControlador;

	public ListadoParticipante() {
		getContentPane().setBackground(new Color(248, 248, 255));
		this.miControlador = new ControladorListadoParticipante(this);
		initComponents();
	}

	private void setModeloTabla(ArrayList<VOParticipanteListado> array) {
		modeloTabla = new ModeloDatosTablaParticipante();
		modeloTabla.setParticipantes(array);
		this.tablePart.setModel(modeloTabla);
		this.tablePart.getColumnModel().getColumn(2);
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ListadoParticipante.class.getResource("/recursos/talento_icon.png")));
		setResizable(false);
		setTitle("Listado de Participantes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		jScrollPane1 = new javax.swing.JScrollPane();
		tablePart = new javax.swing.JTable();

		tablePart
				.setModel(new javax.swing.table.DefaultTableModel(
						null,
						new String[] { "Nom artístico", "Edad", "Especialidad" }));
		jScrollPane1.setViewportView(tablePart);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				layout.createSequentialGroup().addGap(29)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(45, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
		setLocationRelativeTo(null);

		ArrayList<VOParticipanteListado> listado = miControlador.listarParticipantes();
		if (listado != null && listado.size() > 0)
			setModeloTabla(listado);
	}

	public void mensajeError(String e) {

		JOptionPane.showOptionDialog(null, e, "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, null,
				null);

	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ListadoParticipante().setVisible(true);
			}
		});
	}

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tablePart;
}
