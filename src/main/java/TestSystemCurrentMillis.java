import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TestSystemCurrentMillis {
	public static void main(String[]args){
		
		
		long nowMillis = System.currentTimeMillis();
		
		Date date = new Date(nowMillis);
		System.err.println(nowMillis);
		SimpleDateFormat df = new SimpleDateFormat("YY-MM-dd HH:mm:ss", Locale.CHINESE);
		System.err.println(df.format(date));
		SimpleDateFormat df1 = new SimpleDateFormat("YY-MM-dd HH:mm:ss", Locale.ENGLISH);
		System.err.println(df1.format(date));
		
		
		
	}
}
