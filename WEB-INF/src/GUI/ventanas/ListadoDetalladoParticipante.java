package GUI.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import valueObjects.VOCapaLogica;
import valueObjects.VOExtranjero;
import valueObjects.VONacional;
import valueObjects.VOParticipante;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.chrono.MinguoChronology;
import java.awt.event.ActionEvent;
import GUI.controladores.*;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import java.awt.Color;

public class ListadoDetalladoParticipante extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ControladorListadoDetalladoPart miControlador;
	private ImageIcon ii = null;

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
					ListadoDetalladoParticipante frame = new ListadoDetalladoParticipante();
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
	public ListadoDetalladoParticipante() {

		this.miControlador = new ControladorListadoDetalladoPart(this);

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ListadoDetalladoParticipante.class.getResource("/recursos/talento_icon.png")));
		setResizable(false);
		setTitle("Listado detallado de participante");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 489, 317);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreArtistico = new JLabel("Nombre Artistico:");
		lblNombreArtistico.setBounds(57, 11, 112, 14);
		contentPane.add(lblNombreArtistico);

		textField = new JTextField();
		textField.setBounds(199, 8, 128, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(199, 57, 112, 14);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(201, 82, 112, 14);
		contentPane.add(lblApellido);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEdad.setBounds(199, 107, 112, 14);
		contentPane.add(lblEdad);

		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEspecialidad.setBounds(199, 132, 112, 14);
		contentPane.add(lblEspecialidad);

		JLabel lblLocalidad = new JLabel();
		lblLocalidad.setText("Localidad:");
		lblLocalidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocalidad.setBounds(199, 183, 112, 14);
		contentPane.add(lblLocalidad);

		JLabel lblLocaos = new JLabel();
		lblLocaos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocaos.setBounds(174, 183, 112, 14);
		contentPane.add(lblLocaos);

		JLabel lblTotalDePuntos = new JLabel("Total de Puntos:");
		lblTotalDePuntos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalDePuntos.setBounds(199, 208, 112, 14);
		contentPane.add(lblTotalDePuntos);

		JLabel lblCantidadDeVotos = new JLabel("Cantidad de Votos:");
		lblCantidadDeVotos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantidadDeVotos.setBounds(199, 233, 128, 14);
		contentPane.add(lblCantidadDeVotos);

		JLabel nombre = new JLabel("");
		nombre.setBounds(313, 57, 122, 14);
		contentPane.add(nombre);

		JLabel apellido = new JLabel("");
		apellido.setBounds(313, 82, 122, 14);
		contentPane.add(apellido);

		JLabel edad = new JLabel("");
		edad.setBounds(313, 107, 122, 14);
		contentPane.add(edad);

		JLabel especialidad = new JLabel("");
		especialidad.setBounds(313, 133, 122, 14);
		contentPane.add(especialidad);

		JLabel depto = new JLabel("");
		depto.setBounds(313, 158, 122, 14);
		contentPane.add(depto);

		JLabel localidad = new JLabel("");
		localidad.setBounds(313, 183, 122, 14);
		contentPane.add(localidad);

		JLabel totalPuntos = new JLabel("");
		totalPuntos.setBounds(313, 208, 122, 14);
		contentPane.add(totalPuntos);

		JLabel votos = new JLabel("");
		votos.setBounds(313, 233, 122, 14);
		contentPane.add(votos);

		JLabel lblDepto = new JLabel("Depto:");
		lblDepto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepto.setBounds(199, 157, 112, 14);
		contentPane.add(lblDepto);

		JLabel foto = new JLabel("");
		foto.setIcon(new ImageIcon(ListadoDetalladoParticipante.class.getResource("/recursos/sin_foto.png")));
		foto.setBounds(20, 36, 169, 186);
		contentPane.add(foto);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VOParticipante part = miControlador.buscarParticipante(textField.getText());
				if (part != null) {
					apellido.setText(part.getApellidoVerdadero());
					nombre.setText(part.getNomVerdadero());
					edad.setText(String.valueOf(part.getEdad()));
					especialidad.setText(part.getEspecialidadArtistica());
					votos.setText(String.valueOf(part.getCantVotos()));
					totalPuntos.setText(String.valueOf(part.getVotosJueces()));

					if (part instanceof VOExtranjero) {
						lblDepto.setText("Pais");
						lblLocalidad.setText("Años Uy");

						depto.setText(((VOExtranjero) part).getPaisOrigen());
						localidad.setText(String.valueOf(((VOExtranjero) part).getAniosUru()));
					} else {
						lblDepto.setText("Depto");
						lblLocalidad.setText("Localidad");

						depto.setText(((VONacional) part).getDepartamento());
						localidad.setText(((VONacional) part).getLocalidad());
					}

					if (part.getFoto() != null) {
						InputStream in = new ByteArrayInputStream(part.getFoto());
						try {
							BufferedImage bufferImage = ImageIO.read(in);
							SwingWorker sw = new SwingWorker() {
								@Override
								protected Object doInBackground() throws Exception {
									ii = new ImageIcon(escalarImagen(120, 120, bufferImage));
									return null;
								}

								@Override
								protected void done() {
									super.done();
									foto.setText("");
									foto.setIcon(ii);
								}
							};
							sw.execute();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}
		});
		btnBuscar.setBounds(367, 7, 89, 23);
		contentPane.add(btnBuscar);

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

	public void mensajeError(String e) {
		JOptionPane.showOptionDialog(null, e, "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, null,
				null);

	}
}
