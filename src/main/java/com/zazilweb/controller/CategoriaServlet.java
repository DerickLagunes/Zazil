package com.zazilweb.controller;

import com.google.gson.Gson;
import com.zazilweb.model.Categoria;
import com.zazilweb.model.DAO.CategoriaDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriaServlet", value = "/Categoria")
@MultipartConfig
public class CategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //enviar Categorias y Subcategorias
        CategoriaDao catDao = new CategoriaDao();
        List<Categoria> catLista = catDao.findAll();

        Gson gson = new Gson();
        String respuesta = gson.toJson(catLista);

        resp.setContentType("text/json");
        resp.getWriter().write(respuesta);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Categoria c = new Categoria();

        c.setNombre(req.getParameter("nombre_categoria"));

        CategoriaDao dao = new CategoriaDao();
        dao.insert(c);

        resp.setContentType("text/plain");
        resp.getWriter().write("");
    }
}
