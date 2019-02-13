
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.rithms.riot.api.RiotApiException;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField tfBusqueda;
	private JButton btnBuscar;
	private JPanel panel;
	private JLabel lbl1;
	private String nombre;
	private static int opt = 1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Esta línea de código hace que los componentes de la interfaz
					// se vean nativos dependiendo del SO.
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 609);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 43, 48));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbl1 = new JLabel("Introduce un usuario y empieza a buscar");
		lbl1.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lbl1.setBounds(250, 220, 469, 36);
		lbl1.setForeground(Color.WHITE);
		contentPane.add(lbl1);

		// Un JTextFiled más bonito haciendo uso de de la clase RoundedCornerBorder
		// junto a los métodos paintComponent y updateUI
		tfBusqueda = new JTextField(20) {
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}

			@Override
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundedCornerBorder());
			}
		};
		tfBusqueda.setBackground(new Color(114, 137, 218));
		tfBusqueda.setForeground(Color.WHITE);
		tfBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		tfBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
		tfBusqueda.setBounds(250, 267, 461, 41);
		tfBusqueda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					switch (opt) {
					case 1:
						buscarConAnimacion();
						break;
					case 2:
						buscar();
						break;
					}
				}
			}
		});
		contentPane.add(tfBusqueda);

		btnBuscar = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("res/img/search.png")).getImage()
				.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnBuscar.setBounds(721, 267, 50, 50);
		btnBuscar.setBorder(BorderFactory.createEmptyBorder());
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (opt) {
				case 1:
					buscarConAnimacion();
					break;
				case 2:
					buscar();
					break;
				}
			}
		});
		contentPane.add(btnBuscar);

		panel = new JPanel();
		panel.setBounds(254, 90, 730, 480);
		panel.setBackground(new Color(40, 43, 48));
		contentPane.add(panel);

	}

	public void buscarConAnimacion() {
		nombre = tfBusqueda.getText();

		// Animación para desplazar la barra de búsqueda y el botón en tiempo real:
		// para que esto sea posible "desbloqueamos" al hilo principal del for
		// asignándole la tarea a un hilo nuevo. Como están a la misma altura podemos 
		// usar el mismo hilo para mover los 2 componentes a la vez.
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = btnBuscar.getY(); i > 25; i--) {
					tfBusqueda.setLocation(250, i);
					btnBuscar.setLocation(721, i);
					try {
						Thread.currentThread().sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		// Creamos otro hilo para que la animación del texto suba al mismo tiempo
		// que los otros dos componentes. Inmediatamente al acabar el for,
		// se mostrará el jpanel que contiene el historial.
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 220; i > -40; i--) {
					lbl1.setLocation(250, i);
					try {
						Thread.currentThread().sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				try {
					PanelHistorial ph = new PanelHistorial(nombre);
					panel.add(ph);
				} catch (RiotApiException e) {
					e.printStackTrace();
				}
				panel.validate();
				panel.repaint();
			}
		}).start();
		// Solo queremos que las animaciones se ejecuten la primera vez, por lo tanto
		// cambiamos el valor de la variable estática "opt" a 2.
		opt = 2;
	}

	public void buscar() {
		// Con opt == 2, se ejecutará este método de búsqueda sin animación de
		// componentes.
		nombre = tfBusqueda.getText();
		new Thread(new Runnable() {
			@Override
			public void run() {
				panel.removeAll();

				try {
					PanelHistorial ph = new PanelHistorial(nombre);
					panel.add(ph);
				} catch (RiotApiException e) {
					e.printStackTrace();
				}
				panel.validate();
				panel.repaint();
			}
		}).start();
	}

}
