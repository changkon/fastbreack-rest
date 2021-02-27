package com.changkon.fastbreackrest;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FastbreackRestApplication {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        System.setProperty("jdk.httpclient.HttpClient.log", "all");
    }

	public static void main(String[] args) {
	    SpringApplication.run(FastbreackRestApplication.class, args);
	}

}
