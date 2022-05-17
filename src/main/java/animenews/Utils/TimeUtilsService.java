package animenews.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtilsService {
    public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDateString(String date) throws ParseException {
        return DATE_FORMAT.parse(date);
    }

    public static String toDateString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static Date[] getDateStartEnd(String dateStart) throws ParseException {
        Date[] d = new Date[2];
        Calendar calendar = Calendar.getInstance();

        if (dateStart.isEmpty() | dateStart.length() < 10 | dateStart == null) {
            calendar.set(Calendar.DATE, 1);
            d[0] = calendar.getTime();

            int lastDateOfMonth = calendar.getActualMaximum(Calendar.DATE);
            calendar.set(Calendar.DATE, lastDateOfMonth);
            d[1] = calendar.getTime();
        } else {
            calendar.setTime(parseDateString(dateStart));
            int lastDateOfMonth = calendar.getActualMaximum(Calendar.DATE);
            calendar.set(Calendar.DATE, lastDateOfMonth);

            d[0] = calendar.getTime();
            d[1] = calendar.getTime();
        }
        return d;
    }
}
