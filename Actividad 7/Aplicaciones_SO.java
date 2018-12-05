import java.io.IOException;
import java.util.Scanner;

public class Aplicaciones_SO {

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner lector = new Scanner(System.in);
		String menu = "1.- Abrir PDF\n2.- Abrir hoja Excel\n3.- Ver la versión de java en cmd\n4.- Salir";
		for(;;) {
			System.out.println(menu);
			switch(Integer.parseInt(lector.next())) {
			case 1:
				abrirPdf();
				break;
			case 2:
				abrirExcel();
				break;
			case 3:
				javaVersionEnCmd();
				break;
			case 4:
				System.out.println("Hasta luego!");
				System.exit(0);
				break;
			default:
				System.out.println("Opción no encontrada -_-");
			}
		}
	}

	private static void abrirPdf() throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler c:/pdf/mypdf.pdf");
		p.waitFor();
		System.out.println("Done.");
	}

	private static void abrirExcel() throws IOException {
		Runtime.getRuntime().exec("cmd /c start excel.exe");
	}

	private static void javaVersionEnCmd() {
		try {
			Process p = Runtime.getRuntime().exec("cmd.exe /c start cmd /k java -version");
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	
}
