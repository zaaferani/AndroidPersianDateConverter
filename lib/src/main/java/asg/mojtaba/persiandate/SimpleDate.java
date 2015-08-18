package asg.mojtaba.persiandate;

import java.util.Calendar;
import java.util.Date;

public class SimpleDate{
        public int year,month, day;

        public static SimpleDate getInstance(Date date) {
            return null;
        }
        
        public SimpleDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public Date asDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            return calendar.getTime();
        }
    }