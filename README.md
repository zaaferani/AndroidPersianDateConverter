# AndroidPersianDateConverter
A Java/Android Library For Bi-Directional Convert Persian Date To Gregorian Date (Java port of [persianutils](https://github.com/bahmanm/persianutils) scala library for android compatibility)

# Usage
    DateConverter dateConverter = new DateConverter()
    SimpleDate gregorianDate = new SimpleDate(2010,10,20);
    SimpleDate persianDate = dateConverter.gregorianToPersian(gregorianDate)
    SimpleDate againConvertToGregorianDate = dateConverter.persianToGregorian(persianDate)
