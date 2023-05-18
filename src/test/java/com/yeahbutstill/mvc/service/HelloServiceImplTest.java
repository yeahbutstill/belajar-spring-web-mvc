package com.yeahbutstill.mvc.service;

import com.yeahbutstill.mvc.services.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloServiceImplTest {

    @Qualifier("helloServiceMayaImpl")
    @Autowired
    private HelloService helloServiceMaya;

    @Qualifier("helloServiceImpl")
    @Autowired
    private HelloService helloServiceDani;

    @Test
    void helloIsNullMaya() {
        Assertions.assertEquals("Hello Maya", helloServiceMaya.hello(null));
        Assertions.assertEquals("Hello Yasmin", helloServiceMaya.hello("Yasmin"));
    }

    @Test
    void  helloDani() {
        Assertions.assertEquals("Hello Guest", helloServiceDani.hello(null));
        Assertions.assertEquals("Hello Dani", helloServiceDani.hello("Dani"));
    }

}
