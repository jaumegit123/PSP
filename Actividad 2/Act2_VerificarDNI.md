# Actividad 2. Verificar DNI.

Esta es la función realizada en java:
```java
public static boolean ValidarNIF(String dni) {
	boolean verificarDNI = false;
	int i = 0, charASCII, numeroDNI, residu;
	char lletra = ' ';
	char[] arrLletresDNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' }; // lletres possibles del DNI

	if (dni.length() == 9 && Character.isLetter(dni.charAt(8))) {
		do {
			charASCII = dni.codePointAt(i); // aquesta funció ens torna el codi ASCII de cada lletra
			verificarDNI = (charASCII > 47 && charASCII < 58); // verifica si el caràcter està comprés entre 0 i 9 en ASCII
			i++;
		} while (i < dni.length() - 1 && verificarDNI); // si verificarDNI dona fals alguna vegada, el DNI serà incorrecte
	}
	if (verificarDNI) { // en canvi si ix del do while perquè s'ha acabat de recorrer el dni.length(), entrarà dins d'aquest if
		lletra = Character.toUpperCase(dni.charAt(i));
		numeroDNI = Integer.parseInt(dni.substring(0, 8));
		residu = numeroDNI % 23;
		verificarDNI = (lletra == arrLletresDNI[residu]); // si el valor de la operació es el mateix que el ASCII de la lletra, retornarà true
	}
	return verificarDNI;
}
```
