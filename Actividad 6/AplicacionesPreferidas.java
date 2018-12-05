package activitat6;

import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AplicacionesPreferidas extends JFrame {

	private JPanel contentPane;

	public AplicacionesPreferidas() {
		this.setTitle(" Mis aplicaciones preferidas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creamos el bot�n asign�ndole un ImageIcon, el cu�l contiene otro, en el cu�l especificamos la ruta
		// del recurso. En este caso es una imagen a la que le hacemos un resize al tama�o deseado.
		// No se puede construir directamente desde el segundo ImageIcon por limitaciones del constructor
		// y la superclase abstracta Image.
		JButton btnChrome = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/activitat6/img/chrome.png"))
				.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		btnChrome.setBounds(31, 11, 60, 60);
		contentPane.add(btnChrome);

		JButton btnSpotify = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/activitat6/img/spotify.png"))
				.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		btnSpotify.setBounds(131, 11, 60, 60);
		contentPane.add(btnSpotify);

		JButton btnNotepad = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/activitat6/img/notepad.png"))
				.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		btnNotepad.setBounds(231, 11, 60, 60);
		contentPane.add(btnNotepad);

		JButton btnWorkbench = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/activitat6/img/mysqlWorkbench.png"))
				.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		btnWorkbench.setBounds(331, 11, 60, 60);
		contentPane.add(btnWorkbench);

		// Para que queden m�s elegantes, con el siguiente c�digo quitamos los bordes de los botones y su �rea de rellleno.
		btnChrome.setBorder(BorderFactory.createEmptyBorder());
		btnChrome.setContentAreaFilled(false);
		btnSpotify.setBorder(BorderFactory.createEmptyBorder());
		btnSpotify.setContentAreaFilled(false);
		btnNotepad.setBorder(BorderFactory.createEmptyBorder());
		btnNotepad.setContentAreaFilled(false);
		btnWorkbench.setBorder(BorderFactory.createEmptyBorder());
		btnWorkbench.setContentAreaFilled(false);

		// Aqu� vamos a definir los eventos al pulsar cada bot�n/imagen.
		// Cada uno tiene su proceso que abrir� la aplicaci�n correspondiente.
		btnChrome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Process pb = new ProcessBuilder("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", "").start();
				} catch (Exception e) {
					System.out.println("Excepci�n: " + e);
				}
			}
		});

		btnSpotify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Process pb = new ProcessBuilder("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\Spotify\\Spotify.exe", "").start();
				} catch (Exception e2) {
					System.out.println("Excepci�n: " + e2);
				}
			}
		});

		btnNotepad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Process pb = new ProcessBuilder("C:\\WINDOWS\\system32\\notepad.exe", "").start();
				} catch (Exception e3) {
					System.out.println("Excepci�n: " + e3);
				}
			}
		});

		btnWorkbench.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Process pb = new ProcessBuilder("C:\\Program Files\\MySQL\\MySQL Workbench 6.3 CE\\MySQLWorkbench", "").start();
				} catch (Exception e4) {
					System.out.println("Excepci�n: " + e4);
				}
			}
		});

		// Etiquetas de texto que pondremos bajo los iconos.
		JLabel lblChrome = new JLabel("Google Chrome");
		lblChrome.setFont(new Font("Arial", Font.BOLD, 11));
		lblChrome.setBounds(18, 77, 93, 14);
		contentPane.add(lblChrome);

		JLabel lblSpotify = new JLabel("Spotify");
		lblSpotify.setFont(new Font("Arial", Font.BOLD, 11));
		lblSpotify.setBounds(141, 77, 46, 14);
		contentPane.add(lblSpotify);

		JLabel lblNotepad = new JLabel("Notepad");
		lblNotepad.setFont(new Font("Arial", Font.BOLD, 11));
		lblNotepad.setBounds(235, 77, 50, 14);
		contentPane.add(lblNotepad);

		JLabel lblMysqlWorkbench = new JLabel("MySQL Workbench");
		lblMysqlWorkbench.setFont(new Font("Arial", Font.BOLD, 11));
		lblMysqlWorkbench.setBounds(308, 77, 112, 14);
		contentPane.add(lblMysqlWorkbench);
	}

	// Ejecuci�n del programa.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacionesPreferidas frame = new AplicacionesPreferidas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
