package com.zazilweb.controller;

import com.google.gson.Gson;
import com.zazilweb.model.DAO.EstadoDao;
import com.zazilweb.model.DAO.MunicipioDao;
import com.zazilweb.model.Estado;
import com.zazilweb.model.Municipio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MunicipioServlet", value = "/Municipio")
public class MunicipioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Municipio> lista = new ArrayList<>();
        try{
            int estado = Integer.parseInt(req.getParameter("estado"));
            MunicipioDao dao = new MunicipioDao();
            lista = dao.findAll(estado);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(lista);

        resp.setContentType("text/json");
        resp.getWriter().write(json);

    }
}
