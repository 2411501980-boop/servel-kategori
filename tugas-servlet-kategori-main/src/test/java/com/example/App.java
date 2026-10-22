package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class App {

    public static void main(String[] args) throws Exception {

        System.out.println(
                "Menjalankan Embedded Tomcat Server..."
        );

        // Membuat Embedded Tomcat
        Tomcat tomcat = new Tomcat();

        // Menentukan port
        tomcat.setPort(8080);

        // Mengaktifkan connector
        tomcat.getConnector();

        // Membuat Context
        Context ctx = tomcat.addContext(
                "",
                new File(".").getAbsolutePath()
        );

        // Mendaftarkan Servlet
        Tomcat.addServlet(
                ctx,
                "ProductServlet",
                new ProductServlet()
        );

        // Mapping URL Servlet
        ctx.addServletMappingDecoded(
                "/products",
                "ProductServlet"
        );

        System.out.println(
                "Server berhasil berjalan di:"
        );

        System.out.println(
                "http://localhost:8080/products"
        );

        // Menjalankan Tomcat
        tomcat.start();

        // Menunggu server
        tomcat.getServer().await();
    }
}