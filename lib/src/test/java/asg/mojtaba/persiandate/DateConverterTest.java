package asg.mojtaba.persiandate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals(simpleDate.year, 1394);
        assertEquals(simpleDate.month, 5);
        assertEquals(simpleDate.day, 26);


        assertEquals(gSimpleDate.year, 2015);
        assertEquals(gSimpleDate.month, 8);
        assertEquals(gSimpleDate.day, 17);

    }
}