package GUI.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import GUI.controladores.ControladorIngresarPerformance;
import GUI.controladores.ControladorRegistrarParticipante;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.Color;

public class IngresarPerformance extends JFrame 
{
	private JPanel contentPane;
	private JTextField textNomArt;
	private ControladorIngresarPerformance miControlador;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresarPerformance frame = new IngresarPerformance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setVisible(boolean b)
	{
		super.setVisible(b);
	}

	/**
	 * Create the frame.
	 */
	public IngresarPerformance() {
		
		this.miControlador = new ControladorIngresarPerformance(this);
		
		setEnabled(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(IngresarPerformance.class.getResource("/recursos/talento_icon.png")));
		setResizable(false);
		setTitle("Ingresar performance");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 337, 308);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreArtistico = new JLabel("Nombre Artistico:");
		lblNombreArtistico.setBounds(20, 11, 105, 14);
		contentPane.add(lblNombreArtistico);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setBounds(20, 48, 105, 14);
		contentPane.add(lblDescripcion);
		
		JLabel lblPuntajeUno = new JLabel("Puntaje uno:");
		lblPuntajeUno.setBounds(20, 144, 105, 14);
		contentPane.add(lblPuntajeUno);
		
		JLabel lblPuntajeDos = new JLabel("Puntaje dos:");
		lblPuntajeDos.setBounds(20, 169, 105, 14);
		contentPane.add(lblPuntajeDos);
		
		JLabel lblPuntajeTres = new JLabel("Puntaje tres:");
		lblPuntajeTres.setBounds(20, 194, 105, 14);
		contentPane.add(lblPuntajeTres);
		
		JSpinner spinnerPtosUno = new JSpinner();
		spinnerPtosUno.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerPtosUno.setBounds(149, 141, 48, 20);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spinnerPtosUno.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		contentPane.add(spinnerPtosUno);
		
		JSpinner spinnerPtosDos = new JSpinner();
		spinnerPtosDos.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerPtosDos.setBounds(149, 166, 48, 20);
		JFormattedTextField txtDos = ((JSpinner.NumberEditor) spinnerPtosDos.getEditor()).getTextField();
		((NumberFormatter) txtDos.getFormatter()).setAllowsInvalid(false);
		contentPane.add(spinnerPtosDos);
		
		JSpinner spinnerPtosTres = new JSpinner();
		spinnerPtosTres.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerPtosTres.setBounds(149, 191, 48, 20);
		JFormattedTextField txtTres = ((JSpinner.NumberEditor) spinnerPtosTres.getEditor()).getTextField();
		((NumberFormatter) txtTres.getFormatter()).setAllowsInvalid(false);
		contentPane.add(spinnerPtosTres);
		
		textNomArt = new JTextField();
		textNomArt.setBounds(149, 8, 147, 20);
		contentPane.add(textNomArt);
		textNomArt.setColumns(10);
		
		JTextArea textDesc = new JTextArea();
		textDesc.setTabSize(1);
		textDesc.setLineWrap(true);
		textDesc.setBounds(149, 43, 147, 86);
		contentPane.add(textDesc);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miControlador.ingresarPerformance(textNomArt.getText(), textDesc.getText(), 
						(Integer)spinnerPtosUno.getValue(), (Integer)spinnerPtosDos.getValue(), 
						(Integer)spinnerPtosTres.getValue());
				
			}
		});
		btnIngresar.setBounds(95, 235, 147, 23);
		contentPane.add(btnIngresar);
		
		
		
	}
	
	public void mensajeError(String e, boolean exit) {
		int input = 0;
		if (exit == false) {
			input = JOptionPane.showOptionDialog(null, e, "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE,
					null, null, null);
		} else {
			input = JOptionPane.showOptionDialog(null, e, "Correcto", JOptionPane.PLAIN_MESSAGE,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
		}

		if (input == JOptionPane.OK_OPTION && exit) {
			this.dispose();
		}

	}
}
