package GUI.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;

import GUI.controladores.*;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import java.awt.Color;

public class RegistrarParticipante {

	private JFrame frmHola;
	private JTextField textNomArt;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textEspArtistica;
	private JTextField textFieldPaisDepto;
	private JTextField textFieldLocalidadAnios;
	private ControladorRegistrarParticipante miControlador;
	private ImageIcon ii = null;
	private byte[] foto = null;

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
					RegistrarParticipante window = new RegistrarParticipante();
					window.frmHola.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setVisible(boolean b) {
		frmHola.setVisible(b);
	}

	public RegistrarParticipante() {
		this.initialize();
		this.miControlador = new ControladorRegistrarParticipante(this);
		/* mejor hacerla visible desde el controlador */
		// this.setVisible(true);
	}

	private void initialize() {
		frmHola = new JFrame();
		frmHola.getContentPane().setBackground(new Color(248, 248, 255));
		frmHola.setResizable(false);
		frmHola.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(RegistrarParticipante.class.getResource("/recursos/talento_icon.png")));
		frmHola.setTitle("Registrar Participante");
		frmHola.setBounds(100, 100, 460, 550);
		frmHola.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHola.getContentPane().setLayout(null);

		JLabel lblNombreArtistico = new JLabel("Nombre Artistico:");
		lblNombreArtistico.setBounds(23, 21, 164, 14);
		frmHola.getContentPane().add(lblNombreArtistico);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 46, 164, 14);
		frmHola.getContentPane().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 71, 164, 14);
		frmHola.getContentPane().add(lblApellido);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(23, 96, 164, 14);
		frmHola.getContentPane().add(lblEdad);

		JLabel lblEspecialidadArtistica = new JLabel("Especialidad Artistica:");
		lblEspecialidadArtistica.setBounds(23, 121, 164, 14);
		frmHola.getContentPane().add(lblEspecialidadArtistica);

		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setBounds(23, 156, 87, 14);
		frmHola.getContentPane().add(lblOrigen);

		textNomArt = new JTextField();
		textNomArt.setBounds(246, 18, 164, 20);
		frmHola.getContentPane().add(textNomArt);
		textNomArt.setColumns(10);

		textNombre = new JTextField();
		textNombre.setBounds(246, 43, 164, 20);
		frmHola.getContentPane().add(textNombre);
		textNombre.setColumns(10);

		textApellido = new JTextField();
		textApellido.setBounds(246, 68, 164, 20);
		frmHola.getContentPane().add(textApellido);
		textApellido.setColumns(10);

		textEspArtistica = new JTextField();
		textEspArtistica.setBounds(246, 118, 164, 20);
		frmHola.getContentPane().add(textEspArtistica);
		textEspArtistica.setColumns(10);

		JLabel lblPaisDepto = new JLabel();
		lblPaisDepto.setBounds(23, 199, 87, 14);
		frmHola.getContentPane().add(lblPaisDepto);

		textFieldPaisDepto = new JTextField();
		textFieldPaisDepto.setBounds(246, 193, 164, 20);
		frmHola.getContentPane().add(textFieldPaisDepto);
		textFieldPaisDepto.setColumns(10);
		textFieldPaisDepto.setVisible(false);

		JLabel lblLocalidadAnios = new JLabel();
		lblLocalidadAnios.setBounds(23, 224, 164, 14);
		frmHola.getContentPane().add(lblLocalidadAnios);

		textFieldLocalidadAnios = new JTextField();
		textFieldLocalidadAnios.setBounds(246, 224, 164, 20);
		frmHola.getContentPane().add(textFieldLocalidadAnios);
		textFieldLocalidadAnios.setColumns(10);
		textFieldLocalidadAnios.setVisible(false);

		JSpinner spinnerAñosUruguay = new JSpinner();
		spinnerAñosUruguay.setBounds(246, 224, 52, 20);
		spinnerAñosUruguay.setModel(new SpinnerNumberModel(new Short((short) 0), new Short((short) 0), new Short((short) 150), new Short((short) 1)));
		frmHola.getContentPane().add(spinnerAñosUruguay);
		JFormattedTextField formatted = ((JSpinner.NumberEditor) spinnerAñosUruguay.getEditor()).getTextField();
		((NumberFormatter) formatted.getFormatter()).setAllowsInvalid(false);
		spinnerAñosUruguay.setVisible(false);

		// por defecto el comboBox inicia en nacional, indice 0

		spinnerAñosUruguay.setVisible(false);
		lblLocalidadAnios.setVisible(false);
		lblPaisDepto.setVisible(false);
		lblPaisDepto.setText("Departamento:");
		lblLocalidadAnios.setText("Localidad:");
		lblPaisDepto.setVisible(true);
		lblLocalidadAnios.setVisible(true);
		textFieldPaisDepto.setVisible(true);
		textFieldLocalidadAnios.setVisible(true);

		JComboBox comboBoxOrigen = new JComboBox();
		comboBoxOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxOrigen.getSelectedIndex() == 0) {
					spinnerAñosUruguay.setVisible(false);
					lblLocalidadAnios.setVisible(false);
					lblPaisDepto.setVisible(false);
					lblPaisDepto.setText("Departamento:");
					lblLocalidadAnios.setText("Localidad:");
					lblPaisDepto.setVisible(true);
					lblLocalidadAnios.setVisible(true);
					textFieldPaisDepto.setVisible(true);
					textFieldLocalidadAnios.setVisible(true);
				} else {
					textFieldLocalidadAnios.setVisible(false);
					lblLocalidadAnios.setVisible(false);
					lblPaisDepto.setVisible(false);
					lblPaisDepto.setText("Pais de Origen:");
					lblLocalidadAnios.setText("Años en Uruguay:");
					lblPaisDepto.setVisible(true);
					lblLocalidadAnios.setVisible(true);
					textFieldPaisDepto.setVisible(true);
					spinnerAñosUruguay.setVisible(true);
				}

			}
		});
		comboBoxOrigen.setModel(new DefaultComboBoxModel(new String[] { "Nacional", "Extranjero" }));
		comboBoxOrigen.setToolTipText("Si es nacional o extranjero");
		comboBoxOrigen.setBounds(246, 153, 164, 20);
		frmHola.getContentPane().add(comboBoxOrigen);

		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(RegistrarParticipante.class.getResource("/recursos/sin_foto.png")));
		lblFoto.setBounds(246, 268, 164, 139);
		
		frmHola.getContentPane().add(lblFoto);

		JButton btnCargarFoto = new JButton("Cargar Foto");
		

		btnCargarFoto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(filter);
				chooser.showOpenDialog(null);
				final File f = chooser.getSelectedFile();
			
				if (f != null) {
					String filename = f.getAbsolutePath();

					try {
						foto = Files.readAllBytes(f.toPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					SwingWorker sw = new SwingWorker() {
						@Override
						protected Object doInBackground() throws Exception {
							ii = new ImageIcon(escalarImagen(120, 120, ImageIO.read(new File(f.getAbsolutePath()))));
							return null;
						}

						@Override
						protected void done() {
							super.done();
							lblFoto.setText("");
							lblFoto.setIcon(ii);
						}
					};
					sw.execute();

				}
			}
		});
		btnCargarFoto.setBounds(23, 326, 111, 23);
		frmHola.getContentPane().add(btnCargarFoto);

		JSpinner spinnerEdad = new JSpinner();
		spinnerEdad.setModel(new SpinnerNumberModel(new Short((short) 0), new Short((short) 0), new Short((short) 150), new Short((short) 1)));
		spinnerEdad.setBounds(246, 93, 52, 20);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spinnerEdad.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		frmHola.getContentPane().add(spinnerEdad);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("BOTON apretado");
				String nomArt = textNomArt.getText();
				String nom = textNombre.getText();
				String ape = textApellido.getText();
				int edad = (Short) spinnerEdad.getValue();
				String esp = textEspArtistica.getText();
				if (comboBoxOrigen.getSelectedIndex() == 0) {
					String dep = textFieldPaisDepto.getText();
					String loc = textFieldLocalidadAnios.getText();
					miControlador.registrarParticipanteNacional(nomArt, nom, ape, edad, esp, dep, loc, foto);
				} else {
					String pais = textFieldPaisDepto.getText();
					int anios = (Short) spinnerAñosUruguay.getValue();
					miControlador.registrarParticipanteExtranjero(nomArt, nom, ape, edad, esp, pais, anios, foto);
				}
			}
		});

		btnRegistrar.setBounds(77, 442, 288, 23);
		frmHola.getContentPane().add(btnRegistrar);

	}

	public BufferedImage escalarImagen(int w, int h, BufferedImage img) throws Exception {
		BufferedImage bi;
		bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(img, 0, 0, w, h, null);
		g2d.dispose();
		return bi;
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
			frmHola.dispose();
		}

	}
}
