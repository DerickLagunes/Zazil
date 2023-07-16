package com.zazilweb.controller;

import com.zazilweb.model.Producto;
import com.zazilweb.model.DAO.ProductoDao;
import com.zazilweb.utils.MysqlConector;
import net.sf.jasperreports.engine.JasperRunManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductoServlet", value = "/Productos")
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoDao dao = new ProductoDao();
        List<Producto> lista = dao.findAll();
        req.getSession().setAttribute("productos", lista);

        /*FileInputStream archivo = new FileInputStream(req.getSession().getServletContext().getResource("/assets/img/logo.png").getFile());
        String report = "/WEB-INF/ReporteInventario.jasper";
        File file = new File(getServletContext().getRealPath(report));
        InputStream input = new FileInputStream(file);

        Map mapa = new HashMap();
        mapa.put("logo", archivo);
        Connection con = new MysqlConector().connect();
        resp.setContentType("application/pdf");
        //resp.setHeader("Content-Disposition", "Attachment; filename=reporte.pdf");
        try {
            byte[] bytes = JasperRunManager.runReportToPdf(input, mapa, con);
            OutputStream os = resp.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

    }
}
