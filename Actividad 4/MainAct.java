package activitat4;

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
