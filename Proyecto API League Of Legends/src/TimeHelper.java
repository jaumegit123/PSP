
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

// Clase de ayuda en cuanto a cálculos y conversiones de tiempo
public class TimeHelper {

	public static String convertDate(long creation) {
		Date date = new Date(creation);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
		String formattedDate = sdf.format(date);
		return formattedDate;
	}

	public static String convertDuration(long duration) {

		double total = duration / 60f;
		String formattedDuration;

		if (total < 60) {
			double min = total;
			int minInt = (int) min;
			String finalmin = String.valueOf(minInt);
			if (minInt < 10) {
				finalmin = "0" + minInt;
			}
			double seg = min - Math.floor(min);
			seg = Math.round(seg * 60f);
			int segInt = (int) seg;
			String finalseg = String.valueOf(segInt);
			if (segInt < 10) {
				finalseg = "0" + segInt;
			}
			formattedDuration = finalmin + ":" + finalseg;
		} else {
			double hora = total / 60f;
			int horaInt = (int) hora;
			double min = total - (horaInt * 60f);
			int minInt = (int) min;
			String finalmin = String.valueOf(minInt);
			if (minInt < 10) {
				finalmin = "0" + minInt;
			}
			double seg = min - Math.floor(min);
			seg = Math.round(seg * 60f);
			int segInt = (int) seg;
			String finalseg = String.valueOf(segInt);
			if (segInt < 10) {
				finalseg = "0" + segInt;
			}
			formattedDuration = String.valueOf(horaInt) + ":" + finalmin + ":" + finalseg;
		}
		return formattedDuration;
	}
	
}