package asg.mojtaba.persiandate;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mojtaba Asgari
 * [asg.mojtaba@gmail.com]
 */
public class DateConverterTest {

    @Test
    public void testConvert() throws Exception {
        DateConverter dateConverter = new DateConverter();
        SimpleDate simpleDate = dateConverter.gregorianToPersian(new SimpleDate(2015, 8, 17));
        SimpleDate gSimpleDate = dateConverter.persianToGregorian(new SimpleDate(1394, 5, 26));
    }
}