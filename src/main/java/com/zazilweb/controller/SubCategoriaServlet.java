package com.zazilweb.controller;

import com.google.gson.Gson;
import com.zazilweb.model.Categoria;
import com.zazilweb.model.DAO.CategoriaDao;
import com.zazilweb.model.DAO.SubCategoriaDao;
import com.zazilweb.model.SubCategoria;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SubCategoriaServlet", value = "/SubCategoria")
@MultipartConfig
public class SubCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //enviar Categorias y Subcategorias
        SubCategoriaDao subCatDao = new SubCategoriaDao();
        List<SubCategoria> subcatLista = subCatDao.findAll(Integer.parseInt(req.getParameter("catid")));

        Gson gson = new Gson();
        String respuesta = gson.toJson(subcatLista);

        resp.setContentType("text/json");
        resp.getWriter().write(respuesta);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubCategoria s = new SubCategoria();

        s.setNombre(req.getParameter("nombre_subcategoria"));
        s.setCategoria(new Categoria(Integer.parseInt(req.getParameter("subcat_categoria"))));

        SubCategoriaDao dao = new SubCategoriaDao();
        dao.insert(s);

        resp.setContentType("text/plain");
        resp.getWriter().write("");
    }
}
