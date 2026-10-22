
package com.example;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        List<Kategori> daftarKategori = new ArrayList<>();
        daftarKategori.add(new Kategori(1, "Elektronik"));
        daftarKategori.add(new Kategori(2, "Pakaian"));
        daftarKategori.add(new Kategori(3, "Peralatan Rumah Tangga"));

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Daftar Kategori Produk</title>");

        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; background: #f4f6f9; }");
        out.println("h1 { text-align: center; color: #243b55; }");
        out.println("table { width: 80%; margin: 25px auto; border-collapse: collapse; background: white; }");
        out.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
        out.println("th { background: #243b55; color: white; }");
        out.println("tr:nth-child(even) { background: #f2f2f2; }");
        out.println("tr:hover { background: #e2eaf3; }");
        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        out.println("<h1>DAFTAR KATEGORI PRODUK (WEB)</h1>");

        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID Kategori</th>");
        out.println("<th>Nama Kategori</th>");
        out.println("</tr>");

        for (Kategori kategori : daftarKategori) {
            out.println("<tr>");
            out.println("<td>KTG-0" + kategori.getId() + "</td>");
            out.println("<td>" + kategori.getNama() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}