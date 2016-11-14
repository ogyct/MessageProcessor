package com.ogyct.Utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

public class UtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testReadFile() throws IOException {
        System.out.println(Utils.readFile("resources/xml/actor.xml", Charset.defaultCharset()));
    }

}
