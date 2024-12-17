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
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;

import GUI.controladores.ControladorListadoPerformance;
import GUI.modelos.ModeloDatosTablaPerformance;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import valueObjects.VOPerformance;
import java.awt.Color;

public class ListadoPerformancePart extends javax.swing.JFrame {

	private ModeloDatosTablaPerformance modeloTabla;
	private ControladorListadoPerformance miControlador;

	public ListadoPerformancePart() {
		getContentPane().setBackground(new Color(248, 248, 255));
		this.miControlador = new ControladorListadoPerformance(this);
		initComponents();
	}

	private void setModeloTabla(ArrayList<VOPerformance> array) {
		modeloTabla = new ModeloDatosTablaPerformance();
		modeloTabla.setPerformances(array);
		this.tablePerf.setModel(modeloTabla);
		this.tablePerf.getColumnModel().getColumn(4);
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ListadoPerformancePart.class.getResource("/recursos/talento_icon.png")));
		setResizable(false);
		setTitle("Listado de Performances");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		jScrollPane1 = new javax.swing.JScrollPane();
		tablePerf = new javax.swing.JTable();

		tablePerf.setModel(new javax.swing.table.DefaultTableModel(
				null,
				new String[] { "Número", "Descripción", "Ptos uno", "Ptos dos", "Ptos tres" }));
		jScrollPane1.setViewportView(tablePerf);

		JLabel label = new JLabel("Nombre artistico:");

		textNomArt = new JTextField();
		textNomArt.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VOPerformance[] listado = miControlador.listarPerformancesParticipante(textNomArt.getText());
				if (listado != null && listado.length > 0) {
					setModeloTabla(new ArrayList<>(Arrays.asList(listado)));
				}else {
					setModeloTabla(new ArrayList<VOPerformance>());
				}
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textNomArt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnBuscar)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(31, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label)
								.addComponent(textNomArt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscar))
						.addGap(18)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		getContentPane().setLayout(layout);

		pack();
		setLocationRelativeTo(null);
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
				new ListadoPerformancePart().setVisible(true);
			}
		});
	}

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tablePerf;
	private JTextField textNomArt;
}
