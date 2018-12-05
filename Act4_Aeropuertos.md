# Actividad 4. Aeropuertos.
Busca cualquier aeropuerto del mundo, a tu gusto y con interfaz
## Clase Aeropuerto
```java
public class Aeropuerto {

	private String id, nomAeroport, nomCiutat, nomPais, sigles, coordX, coordY;

	public Aeropuerto(String id, String nomAeroport, String nomCiutat, String nomPais, String sigles, String coordX, String coordY) {
		this.id = id;
		this.nomAeroport = nomAeroport;
		this.nomCiutat = nomCiutat;
		this.nomPais = nomPais;
		this.sigles = sigles;
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	public Aeropuerto(Aeropuerto a) {
		this.id = a.id;
		this.nomAeroport = a.nomAeroport;
		this.nomCiutat = a.nomCiutat;
		this.nomPais = a.nomPais;
		this.sigles = a.sigles;
		this.coordX = a.coordX;
		this.coordY = a.coordY;
	}

	public String getId() {
		return id;
	}

	public String getNomAeroport() {
		return nomAeroport;
	}

	public String getNomCiutat() {
		return nomCiutat;
	}

	public String getNomPais() {
		return nomPais;
	}

	public String getSigles() {
		return sigles;
	}

	public String getCoordX() {
		return coordX;
	}

	public String getCoordY() {
		return coordY;
	}

	@Override
	public String toString() {
		return "ID: " + id + " - " + nomAeroport + " - Ciutat: " + nomCiutat + " - País: " + nomPais + " - Sigles: "
				+ sigles;
	}

}
```

## Clase MainAct
```java
import java.awt.EventQueue;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.net.URL;

import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import java.util.ArrayList;

public class MainAct {

	static String filepath = "C://Users//" + System.getProperty("user.name") + "//Desktop//airports.dat";
	static ArrayList<Aeropuerto> listaAeropuertos = new ArrayList<Aeropuerto>();

	public static void main(String[] args) throws IOException {

		descargarBD_Aeroports();
		crearListaAeropuertos();

		// Se invoca la ventana
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana(listaAeropuertos);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void descargarBD_Aeroports() {
		try {
			URL website = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat");
			ReadableByteChannel channel = Channels.newChannel(website.openStream());
			FileOutputStream stream = new FileOutputStream(filepath);

			System.out.println("Descargando base de datos de los aeropuertos...");
			stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
			stream.close();
			System.out.println("Descarga completada.");
		} catch (Exception e) {
			System.out.println("La descarga ha fallado.");
			System.exit(1);
		}
	}

	public static void crearListaAeropuertos() throws IOException {
		// Metemos los datos que nos interesan del fichero previamente descargado en un
		// ArrayList de Aeropuertos.
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		while (br.readLine() != null) {
			String[] arrLinea = br.readLine().split(",");
			listaAeropuertos.add(new Aeropuerto(arrLinea[0], arrLinea[1], arrLinea[2], arrLinea[3], arrLinea[4],
					arrLinea[6], arrLinea[7]));
		}
		br.close();
		System.out.println("Lista de aeropuertos parseada y creada correctamente.");
	}
	
}
```

# Clase Ventana
```java
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

	// Creamos la ventana.
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
```
