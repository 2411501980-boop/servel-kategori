
package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {

    public static void main(String[] args) throws Exception {

        System.out.println("Menjalankan Embedded Tomcat Server...");

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);

        tomcat.getConnector();

        Context ctx = tomcat.addContext(
            "",
            new File(".").getAbsolutePath()
        );

        // Servlet Product
        Tomcat.addServlet(
            ctx,
            "ProductServlet",
            new ProductServlet()
        );

        ctx.addServletMappingDecoded(
            "/products",
            "ProductServlet"
        );

        // Servlet Category
        Tomcat.addServlet(
            ctx,
            "CategoryServlet",
            new CategoryServlet()
        );

        ctx.addServletMappingDecoded(
            "/categories",
            "CategoryServlet"
        );

        System.out.println(
            "Server berhasil berjalan di: http://localhost:8080/products"
        );

        System.out.println(
            "Daftar kategori tersedia di: http://localhost:8080/categories"
        );

        tomcat.start();

        tomcat.getServer().await();
    }
}