package com.chargify.core.transformers;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateFormatTransformerTest {

    private DateFormatTransformer transformer;

    @Before
    public void setup() {
        transformer = new DateFormatTransformer();
    }

    // 2014-08-08T23:52:20-04:00

    @Test
    public void canConsumeRailsDateFormats() throws Exception {
        transformer.read("2014-08-08T23:52:20-04:00");
    }
}
