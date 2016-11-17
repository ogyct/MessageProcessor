package com.ogyct.Utils;

import java.io.IOException;
import java.nio.charset.Charset;
import org.junit.Before;
import org.junit.Test;

import com.ogyct.DebugLog;

public class UtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testReadFile() throws IOException {
        DebugLog.debug((Utils.readFile(Utils.getResource("xml/actor.xml").getAbsolutePath(), Charset.defaultCharset())));
    }

}
