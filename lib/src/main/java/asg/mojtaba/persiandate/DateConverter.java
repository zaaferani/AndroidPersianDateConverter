package asg.mojtaba.persiandate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mojtaba Asgari
 * [asg.mojtaba@gmail.com]
 */
public class DateConverter {
    private final int[] BREAKS = new int[]{-61, 9, 38, 199, 426, 686, 756, 818, 1111,
            1181, 1210, 1635, 2060, 2097, 2192, 2262, 2324, 2394,
            2456, 3178};

    public SimpleDate persianToGregorian(SimpleDate date) {
        int jDay = persianDateToJulianDay(date);
        return julianDayToGregorianDate(jDay);
    }

    public SimpleDate gregorianToPersian(SimpleDate date) {
        int jDay = gregorianDateToJulianDay(date);
        return julianDayToPersianDate(jDay);
    }

    private int persianDateToJulianDay(SimpleDate date) {
        PersianYearInfo info = persianYearInfo(date.year);
        return gregorianDateToJulianDay(new SimpleDate(info.gYear, 3, info.marchDay)) +
                (date.month - 1) * 31 - date.month / 7 *
                (date.month - 7) + date.day - 1;
    }

    private SimpleDate julianDayToPersianDate(int day) {
        SimpleDate gDate = julianDayToGregorianDate(day);
        int pYear = gDate.year - 621;
        PersianYearInfo info = persianYearInfo(pYear);
        int jDay1Farvardin = gregorianDateToJulianDay(new SimpleDate(info.gYear, 3, info.marchDay));
        int daysPassed = day - jDay1Farvardin;
        if (daysPassed >= 0)
            if (daysPassed <= 185)
                return new SimpleDate(pYear, (1 + daysPassed / 31), (daysPassed % 31) + 1);
            else {
                int daysPassed1 = daysPassed - 186;
                return new SimpleDate(pYear, (7 + daysPassed1 / 30), (daysPassed1 % 30) + 1);
            }
        else {
            int daysPassed1 = daysPassed + 179 + ((info.leap == 1) ? 1 : 0);
            return new SimpleDate(pYear - 1, (7 + daysPassed1 / 30), (daysPassed1 % 30) + 1);
        }
    }

    private int gregorianDateToJulianDay(SimpleDate date) {
        int x = (date.year + (date.month - 8) / 6 + 100100) *
                1461 / 4 + (153 * ((date.month + 9) % 12) + 2) / 5 +
                date.day - 34840408;
        return x - (date.year + 100100 + (date.month - 8) / 6) /
                100 * 3 / 4 + 752;
    }

    private SimpleDate julianDayToGregorianDate(int day) {
        int j = 4 * day + 139361631 +
                (4 * day + 183187720) / 146097 * 3 / 4 * 4 - 3908;
        int i = (j % 1461) / 4 * 5 + 308;
        int d = (i % 153) / 5 + 1;
        int m = (i / 153 % 12) + 1;
        int y = j / 1461 - 100100 + (8 - m) / 6;
        return new SimpleDate(y, m, d);
    }

    private PersianYearInfo persianYearInfo(int pYear) {
        List<Integer> breaksTemp = new ArrayList<Integer>();
        for (int BREAK : BREAKS) {
            if (BREAK <= pYear) {
                breaksTemp.add(BREAK);
            }
        }
        int[] breaks = new int[breaksTemp.size()];
        for (int i = 0; i < breaks.length; i++) {
            breaks[i] = breaksTemp.get(i);
        }

        int gYear = pYear + 621;
        int gLeaps = gYear / 4 - (gYear / 100 + 1) * 3 / 4 - 150;
        int pLeaps = computePersianLeaps(-14, breaks, 0, pYear);
        int n = computeN(breaks, pYear);
        int marchDay = 20 + pLeaps - gLeaps;
        int leap1 = (((n + 1) % 33) - 1) % 4;
        int leap = (leap1 == -1) ? 4 : leap1;
        return new PersianYearInfo(leap, gYear, marchDay);
    }

    private int computeN(int[] breaks, int pYear) {
        int y2 = Arrays.copyOfRange(breaks, breaks.length - 2, breaks.length)[0];
        int y1 = Arrays.copyOfRange(breaks, breaks.length - 2, breaks.length)[1];
        int n = pYear - y2;
        int delta = y2 - y1;
        if ((delta - n) < 6)
            return n - delta + (delta + 4) / 33 * 33;
        else
            return n;
    }

    private int computePersianLeaps(int accResult, int[] breaks, int previousDelta, int pYear) {
        if (breaks.length < 2) {
            int n = pYear - breaks[0];
            int accResult1 = accResult + n / 33 * 8 + ((n % 33) + 3) / 4;
            if ((previousDelta % 33) == 4 && (previousDelta - n == 4))
                return accResult1 + 1;
            else
                return accResult1;
        } else {
            int delta = breaks[1] - breaks[0];
            int accResult1 = accResult + delta / 33 * 8 + (delta % 33) / 4;
            return computePersianLeaps(accResult1, Arrays.copyOfRange(breaks, 1, breaks.length), delta, pYear);
        }
    }
}
