
public class VerificarDNI {

	public static boolean ValidarDNI(String dni) {
		boolean verificarDNI = false;
		int i = 0, charASCII, numeroDNI, residu;
		char lletra = ' ';
		char[] arrLletresDNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' }; // lletres possibles del DNI

		if (dni.length() == 9 && Character.isLetter(dni.charAt(8))) {
			do {
				charASCII = dni.codePointAt(i); // aquesta funci� ens torna el codi ASCII de cada lletra
				verificarDNI = (charASCII > 47 && charASCII < 58); // verifica si el car�cter est� compr�s entre 0 i 9
																	// en ASCII
				i++;
			} while (i < dni.length() - 1 && verificarDNI); // si verificarDNI dona fals alguna vegada, el DNI
															// ser� incorrecte
		}
		if (verificarDNI) { // en canvi si ix del do while perqu� s'ha acabat de recorrer el dni.length(),
							// entrar� dins d'aquest if
			lletra = Character.toUpperCase(dni.charAt(i));
			numeroDNI = Integer.parseInt(dni.substring(0, 8));
			residu = numeroDNI % 23;
			verificarDNI = (lletra == arrLletresDNI[residu]); // si el valor de la operaci� es el mateix que el ASCII de
																// la lletra, retornar� true
		}
		return verificarDNI;
	}

}
