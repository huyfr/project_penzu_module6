package pendzu.sduteam.formatter;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    //    @Override
//    public LocalDateTime parse (String text, Locale locale) throws ParseException {
//        if (!text.equals("")) {
//            return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
//        } else {
//            return LocalDateTime.now();
//        }
//    }
//    @Override
//    public String print (LocalDateTime localDateTime, Locale locale) {
//        return localDateTime.toString();
//    }
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        if (text != null) {
            return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
        } else {
            return LocalDateTime.now();
        }
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        if (localDateTime == null) {
            return "";
        }
        return localDateTime.toString();
    }
}
