package com.ogyct.db;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SeesionFactoryBuilderTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        assertTrue(!SeesionFactoryBuilder.getFactory().isClosed());
    }

}
