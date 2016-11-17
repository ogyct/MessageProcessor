package com.ogyct.db;

import org.junit.Before;
import org.junit.Test;

public class ManageActorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testManageActor() {
        ManageActor ma = new ManageActor();
        ma.listActors();
    }

}
