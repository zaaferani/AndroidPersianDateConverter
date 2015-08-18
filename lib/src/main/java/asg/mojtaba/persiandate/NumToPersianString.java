package asg.mojtaba.persiandate;

/**
 * Created by Mojtaba Asgari
 * [asg.mojtaba@gmail.com]
 */
public class NumToPersianString {

    private static final String PERSIAN_NEGATIVE = "منفی ";
    private static final String PERSIAN_ZERO = "صفر";
    private static final String PERSIAN_AND = " و ";
    private static final String[] hundredWords = new String[]{"", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه",
            "ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"};
    private static final String[] tensWords = new String[]{"", "ده", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"};
    private static final String[] hundredsWords = new String[]{"", "صد", "دویست", "سیصد", "چهار‌صد", "پانصد", "شش‌صد", "هفت‌صد", "هشت‌صد", "نه‌صد"};
    private static final String[] thousandPowerWords = new String[]{"", "هزار", "میلیون", "میلیارد", "هزار میلیارد"};

    public static String convert(long number) {
        if (number < 0)
            return PERSIAN_NEGATIVE + convert(Math.abs(number));
        else if (number == 0)
            return PERSIAN_ZERO;
        else
            return numToPersianString(number, 0);
    }

    private static String numToPersianString(long n, int powerOfThousand) {
        if (n == 0)
            return "";
        else
            return joinWords(null, numToPersianString(n / 1000, powerOfThousand + 1), hundredsToWords((int) (n % 1000), powerOfThousand));
    }

    private static String joinThousandWords(String prefix, String powerWord) {
        if (prefix.equals(""))
            return "";
        else
            return joinWords(" ", prefix, powerWord);
    }

    private static String hundredsToWords(int n, int powerOfThousand) {
        int hundred = n / 100;
        int tens = n % 100;

        return joinThousandWords(joinWords(null, hundredsWords[hundred], tensToWords(tens)), thousandPowerWords[powerOfThousand]);
    }

    private static String tensToWords(int n) {
        if (n < 20) {
            return hundredWords[n];
        } else {
            return joinWords(null, tensToWords(n / 10), tensToWords(n % 10));
        }
    }

    private static String joinWords(String separator, String... words) {
        if (separator == null)
            separator = PERSIAN_AND;
        StringBuilder stringBuilder = new StringBuilder();
        if (words == null) {
            return "";
        } else {
            for (int i = 0; i < words.length; i++) {
                if (words[i] != null && !words[i].isEmpty()) {
                    stringBuilder.append(words[i]);
                    if (i != words.length - 1)
                        stringBuilder.append(separator);
                }

            }
            return stringBuilder.toString();
        }
    }

}
