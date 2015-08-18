package asg.mojtaba.persiandate;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mojtaba Asgari
 * [asg.mojtaba@gmail.com]
 */
public class NumToPersianStringTest {

    @Test
    public void testConvert() throws Exception {
        String convert = NumToPersianString.convert(21);
        assertEquals(convert,"???? ? ??");
    }
}