package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.controladores.ControladorDefinirFinalistasRespaldar;
import GUI.ventanas.IngresarPerformance;
import GUI.ventanas.ListadoDetalladoParticipante;
import GUI.ventanas.ListadoParticipante;
import GUI.ventanas.ListadoPerformancePart;
import GUI.ventanas.RegistrarParticipante;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Canvas;
import java.awt.Color;

public class Menu extends JFrame {

	private JPanel contentPane;
	private Lienzo logoMenu;
	private ControladorDefinirFinalistasRespaldar miControlador;

	/**
	 * Launch the application.
	 */
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
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setVisible(boolean b) {
		super.setVisible(b);
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		miControlador = new ControladorDefinirFinalistasRespaldar(this);
		setResizable(false);
		setTitle("Inicio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/recursos/talento_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 237);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setVisible(true);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		mnMenu.setVisible(true);

		logoMenu = new Lienzo();
		logoMenu.setBounds(10, 11, 412, 125);

		// MENU REGISTRAR
		JMenu mnRegistrar = new JMenu("Registrar..");
		mnMenu.add(mnRegistrar);

		JMenuItem mntmRegistrarParticipante = new JMenuItem("Registrar Participante");
		mntmRegistrarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarParticipante regPart = new RegistrarParticipante();
				/*
				 * mejor no hacerla visible aca, sino que sea el controlador de la propia
				 * ventana quien la haga visible
				 */
				// regPart.setVisible(true);
			}
		});
		mnRegistrar.add(mntmRegistrarParticipante);

		JMenuItem mntmIngresarPerformance = new JMenuItem("Ingresar Performance");
		mntmIngresarPerformance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarPerformance ingPer = new IngresarPerformance();
			}
		});
		mnRegistrar.add(mntmIngresarPerformance);

		// MENU LISTAR
		JMenu mnListar = new JMenu("Listar..");
		mnMenu.add(mnListar);

		JMenuItem mntmTodosLosParticipantes = new JMenuItem("Todos los Participantes");
		mntmTodosLosParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoParticipante lisPart = new ListadoParticipante();
			}
		});
		mnListar.add(mntmTodosLosParticipantes);

		JMenuItem mntmParticipante = new JMenuItem("Participante");
		mntmParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoDetalladoParticipante lisDet = new ListadoDetalladoParticipante();
			}
		});
		mnListar.add(mntmParticipante);

		JMenuItem mntmPerformancesDeParticipante = new JMenuItem("Performances de Participante");
		mntmPerformancesDeParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoPerformancePart lisPerf = new ListadoPerformancePart();
			}
		});
		mnListar.add(mntmPerformancesDeParticipante);

		JSeparator separator = new JSeparator();
		mnMenu.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnMenu.add(mntmSalir);

		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		mnOpciones.setVisible(true);

		JMenuItem mntmDeterminarFinalistas = new JMenuItem("Determinar Finalistas");
		mntmDeterminarFinalistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miControlador.definirFinalistas();
			}
		});
		mnOpciones.add(mntmDeterminarFinalistas);
		
		JMenuItem mntmRespaldar = new JMenuItem("Respaldar");
		mntmRespaldar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.respaldar();
			}
		});
		mnOpciones.add(mntmRespaldar);
		
		JMenuItem mntmFinalizarVotacionY = new JMenuItem("Finalizar Votacion y Elegir Ganador");
		mntmFinalizarVotacionY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.FinalizarConcurso();
			}
		});
		mnOpciones.add(mntmFinalizarVotacionY);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		Image logo1 = new ImageIcon(this.getClass().getResource("/talento_logo.png")).getImage();
		Image logo = logo1.getScaledInstance(412, 127, Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(logo));
		label.setBounds(10, 11, 412, 127);
		label.setVisible(true);
		contentPane.add(label);
	}

	public void mensajeError(String e, boolean ok) {

		if (ok == false) {
			JOptionPane.showOptionDialog(null, e, "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null,
					null, null);
		} else {
			JOptionPane.showOptionDialog(null, e, "Correcto", JOptionPane.PLAIN_MESSAGE,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
		}

	}
}
