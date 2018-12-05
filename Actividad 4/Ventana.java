package activitat4;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Ventana extends JFrame {

	// Create the frame.
	public Ventana(ArrayList<Aeropuerto> listaAeropuertos) {
		this.setTitle("Busca tu aeropuerto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 345);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSinResultados = new JLabel("No se han encontrado resultados.");
		lblSinResultados.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblSinResultados.setBounds(254, 99, 233, 14);
		contentPane.add(lblSinResultados);
		lblSinResultados.setVisible(false);

		JLabel label1 = new JLabel();
		label1.setText("Busca tu aeropuerto por ciudad, siglas de ciudad, o país:");
		label1.setFont(new Font("Calibri", Font.PLAIN, 20));
		label1.setBounds(32, 42, 489, 20);
		contentPane.add(label1);

		JTextField textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField.setBounds(32, 96, 195, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JComboBox<Aeropuerto> comboBox = new JComboBox<Aeropuerto>();
		comboBox.setBounds(254, 96, 449, 20);
		contentPane.add(comboBox);

		JButton btnBuscar = new JButton();
		btnBuscar.setText("BUSCAR");
		btnBuscar.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnBuscar.setBounds(67, 144, 123, 39);
		contentPane.add(btnBuscar);

		JButton btnBuscarMaps = new JButton();
		btnBuscarMaps.setText("Abrir en Google Maps");
		btnBuscarMaps.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnBuscarMaps.setBounds(32, 216, 195, 39);
		contentPane.add(btnBuscarMaps);
		btnBuscarMaps.setVisible(false);

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.removeAllItems();

				boolean validar = false;
				for (Aeropuerto a : listaAeropuertos) {
					if (a.getNomCiutat().toLowerCase().contains(textField.getText().toLowerCase())
							|| a.getNomPais().toLowerCase().contains(textField.getText().toLowerCase())
							|| a.getSigles().toLowerCase().contains(textField.getText().toLowerCase())) {
						comboBox.addItem(a);
						validar = true;
					}
				}
				if (validar == false) {
					comboBox.setVisible(false);
					lblSinResultados.setVisible(true);
				} else {
					lblSinResultados.setVisible(false);
					comboBox.setVisible(true);
				}
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() > -1)
					btnBuscarMaps.setVisible(true);
				else
					btnBuscarMaps.setVisible(false);
			}
		});

		btnBuscarMaps.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Aeropuerto airportSelected = new Aeropuerto((Aeropuerto) comboBox.getSelectedItem());
						Desktop.getDesktop().browse(new URI("https://www.google.com/maps/search/?api=1&query="
								+ airportSelected.getCoordX() + "," + airportSelected.getCoordY()));
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

	}
}
