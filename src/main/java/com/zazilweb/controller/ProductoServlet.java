package com.zazilweb.controller;

import com.zazilweb.model.DAO.DistribuidorDao;
import com.zazilweb.model.Distribuidor;
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

        String respuesta = "productos.jsp";

        if(req.getParameter("operacion").equals("nuevo")){
            respuesta = "nuevoProducto.jsp";

            DistribuidorDao ddao = new DistribuidorDao();
            List<Distribuidor> lista = ddao.findAll();
            req.getSession().setAttribute("distribuidores",lista);

            //enviar Categorias y Subcategorias

        } else if (req.getParameter("operacion").equals("reporte")) {

            //Seleccinar una imagen de los assets (logo)
            FileInputStream archivo = new FileInputStream(req.getSession().getServletContext().getResource("/assets/img/logo.png").getFile());
            //Obtener ubicación y bytes del reporte
            String report = "/WEB-INF/ReporteProductos.jasper";
            File file = new File(getServletContext().getRealPath(report));
            InputStream input = new FileInputStream(file);

            //Colocar los parametros del reporte
            Map mapa = new HashMap();
            mapa.put("logo", archivo);

            //obtener una coneccion a los datos
            Connection con = new MysqlConector().connect();

            //Establecer el tipo de respuesta
            resp.setContentType("application/pdf");
            //esto es para forzar la descarga del archivo
            resp.setHeader("Content-Disposition", "Attachment; filename=reporte.pdf");

            //Generar el reporte
            try {
                byte[] bytes = JasperRunManager.runReportToPdf(input, mapa, con);
                OutputStream os = resp.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else{
            ProductoDao dao = new ProductoDao();
            List<Producto> lista = dao.findAll();
            req.getSession().setAttribute("productos", lista);
        }

        resp.sendRedirect(respuesta);
    }

}
