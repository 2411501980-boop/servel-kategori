package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() {

        productRepository = new ProductRepository();

        productRepository.save(
            new Product(
                "PRD-01",
                "Keyboard Mechanical",
                450000.0
            )
        );

        productRepository.save(
            new Product(
                "PRD-02",
                "Mouse Wireless Silent",
                175000.0
            )
        );

        productRepository.save(
            new Product(
                "PRD-03",
                "Monitor Gaming 24 Inch",
                2100000.0
            )
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        // Mengambil query parameter id
        String id = request.getParameter("id");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Katalog Produk Web</title>");
        out.println("</head>");
        out.println("<body>");

        // Jika parameter id diberikan
        if (id != null && !id.isEmpty()) {

            try {

                Product product = productRepository.findById(id);

                out.println("<h2>=== DETAIL PRODUK ===</h2>");

                out.println("<p><b>ID Produk:</b> "
                        + product.getId() + "</p>");

                out.println("<p><b>Nama Produk:</b> "
                        + product.getName() + "</p>");

                out.println("<p><b>Harga:</b> Rp"
                        + product.getPrice() + "</p>");

            } catch (ProductNotFoundException e) {

                // Jika produk tidak ditemukan
                response.setStatus(
                    HttpServletResponse.SC_NOT_FOUND
                );

                out.println(
                    "<h2 style='color:red;'>Error</h2>"
                );

                out.println(
                    "<p style='color:red;'>"
                    + e.getMessage()
                    + "</p>"
                );
            }

        } else {

            // Jika tidak ada parameter id,
            // tampilkan seluruh produk

            List<Product> products = productRepository.findAll();

            out.println(
                "<h2>=== DAFTAR KATALOG PRODUK (WEB) ===</h2>"
            );

            out.println(
                "<table border='1' cellpadding='8'>"
            );

            out.println("<tr>");
            out.println("<th>ID Produk</th>");
            out.println("<th>Nama Produk</th>");
            out.println("<th>Harga Satuan</th>");
            out.println("</tr>");

            for (Product p : products) {

                out.println("<tr>");

                out.println("<td>");
                out.println(p.getId());
                out.println("</td>");

                out.println("<td>");
                out.println(p.getName());
                out.println("</td>");

                out.println("<td>");
                out.println("Rp" + p.getPrice());
                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}