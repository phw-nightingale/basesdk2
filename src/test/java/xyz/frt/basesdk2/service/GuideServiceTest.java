package xyz.frt.basesdk2.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GuideServiceTest {

    private GuideService guideService;

    @Before
    public void before() {
        guideService =  new GuideServiceImpl();
    }

    @Test
    public void testGetVertexes() {
        guideService.getPositions();
    }


}
