package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoComponent {
public DemoComponent(String bar){
    log.info("constructing for component using string {}", bar);
}

}
