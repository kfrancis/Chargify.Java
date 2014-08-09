package com.chargify.core.transformers;

import org.simpleframework.xml.transform.RegistryMatcher;
import org.simpleframework.xml.transform.Transform;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatTransformer implements Transform<Date>
{
    // 2014-08-08T23:52:20-04:00
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);

    @Override
    public Date read(String value) throws Exception
    {
        return dateFormat.parse(value);
    }

    @Override
    public String write(Date value) throws Exception
    {
        return dateFormat.format(value);
    }

    public static RegistryMatcher matcher() {
        RegistryMatcher matcher = new RegistryMatcher();
        matcher.bind(Date.class, new DateFormatTransformer());
        return matcher;
    }

}
