package com.zazilweb.controller;

import com.zazilweb.model.Categoria;
import com.zazilweb.model.DAO.CategoriaDao;
import com.zazilweb.model.DAO.DistribuidorDao;
import com.zazilweb.model.DAO.SubCategoriaDao;
import com.zazilweb.model.Distribuidor;
import com.zazilweb.model.Producto;
import com.zazilweb.model.DAO.ProductoDao;
import com.zazilweb.model.SubCategoria;
import com.zazilweb.utils.DatabaseConnectionManager;
import net.sf.jasperreports.engine.JasperRunManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(urlPatterns = {"/Productos"})
@MultipartConfig
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
            CategoriaDao catDao = new CategoriaDao();
            List<Categoria> catLista = catDao.findAll();
            req.getSession().setAttribute("categorias",catLista);

            SubCategoriaDao subCatDao = new SubCategoriaDao();
            List<SubCategoria> subcatLista = subCatDao.findAll();
            req.getSession().setAttribute("subcategorias",subcatLista);

        } else if (req.getParameter("operacion").equals("reporte")) {

            //Seleccinar una imagen de los assets (logo)
            FileInputStream archivo =
                    new FileInputStream(req.getSession().
                            getServletContext().
                            getResource("/assets/img/logo.png").
                            getFile());
            //Obtener ubicación y bytes del reporte
            String report = "/WEB-INF/Reporte3B.jasper";
            File file = new File(getServletContext().getRealPath(report));
            InputStream input = new FileInputStream(file);

            //Colocar los parametros del reporte
            Map mapa = new HashMap();
            mapa.put("Logo", archivo);

            //obtener una coneccion a los datos
            Connection con = null;
            try {
                con = DatabaseConnectionManager.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //Establecer el tipo de respuesta
            resp.setContentType("application/pdf");
            //esto es para forzar la descarga del archivo
            resp.setHeader("Content-Disposition",
                    "Attachment; filename=reporte.pdf");

            //Generar el reporte
            try {
                byte[] bytes = JasperRunManager.
                        runReportToPdf(input, mapa, con);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Mensaje en JSP:
        req.getSession().removeAttribute("mensaje");
        String mensaje = "Hubo un error en la inserción del nuevo producto, contacte al administrador.";

        //Registrar el producto en la base de datos

        String UPLOAD_DIRECTORY = req.getServletContext().getRealPath("/") + "assets/img"; // Cambiar dependiendo del directorio donde quieras guardar archivos de imagen

        //Obtener todos los datos del formulario
        String nombre_producto = req.getParameter("nombre_producto");
        String filePath = "";
        String  lote_producto = req.getParameter("lote_producto");
        // Esta linea convierte la string del input en una fecha de SQL:
        Date caducidad_producto = java.sql.Date.valueOf(req.getParameter("caducidad_producto"));
        double precio_costo_producto = Double.parseDouble(req.getParameter("precio_costo_producto"));
        double precio_venta_producto = Double.parseDouble(req.getParameter("precio_venta_producto"));
        int distribuidor_producto = Integer.parseInt(req.getParameter("distribuidor_producto"));
        String marca_producto = req.getParameter("marca_producto");
        String modelo_producto = req.getParameter("modelo_producto");
        String contenido_producto = req.getParameter("contenido_producto");
        String  tipo_contenido_producto = req.getParameter("tipo_contenido_producto");
        int categoria_producto = Integer.parseInt(req.getParameter("categoria_producto"));
        int subcategoria_producto = Integer.parseInt(req.getParameter("subcategoria_producto"));

        //Logica para copiar la imagen que subio el usuario a los assets y obtener el path "filePath para guardar en la BD"
        try {
            Part filePart = req.getPart("imagen_producto");
            String fileName = getSubmittedFileName(filePart);

            // Generar nombre unico con UUID
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

            filePath = UPLOAD_DIRECTORY + File.separator + uniqueFileName;
            InputStream fileContent = filePart.getInputStream();

            //una ves termine este proceso el archivo ya esta en assets
            Files.copy(fileContent, Paths.get(filePath));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Crear el objecto de Producto para pasarlo al DAO
        Producto p = new Producto();
        p.setNombre(nombre_producto);
        p.setImagen(filePath);
        p.setLote(lote_producto);
        p.setCaducidad(caducidad_producto);
        p.setPrecio_costo(precio_costo_producto);
        p.setPrecio_venta(precio_venta_producto);
        p.setDistribuidor(new Distribuidor(distribuidor_producto));
        p.setMarca(marca_producto);
        p.setModelo(modelo_producto);
        p.setContenido(contenido_producto);
        p.setTipo_contenido(tipo_contenido_producto);
        p.setCategoria(new Categoria(categoria_producto));
        p.setSubCategoria(new SubCategoria(subcategoria_producto));

        //Registrar estos datos en la BD
        ProductoDao dao = new ProductoDao();
        if(dao.insert(p)){
            mensaje = "El producto fue registrado exitosamente en la base de datos";
        }

        req.getSession().setAttribute("mensaje",mensaje);
        // Recargar la pagina de la tabla de productos
        resp.sendRedirect("Productos?operacion=lista");
    }

    private String getSubmittedFileName(Part part) {
        String header = part.getHeader("content-disposition");
        String[] elements = header.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

}
