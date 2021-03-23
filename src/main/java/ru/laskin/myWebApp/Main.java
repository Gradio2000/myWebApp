package ru.laskin.myWebApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCont.getBeanDefinitionNames()));
    }
}
