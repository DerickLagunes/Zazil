package com.zazilweb.controller;

import com.google.gson.Gson;
import com.zazilweb.model.Colonia;
import com.zazilweb.model.DAO.ColoniaDao;
import com.zazilweb.model.DAO.MunicipioDao;
import com.zazilweb.model.Municipio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ColoniaServlet", value = "/Colonia")
public class ColoniaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Colonia> lista = new ArrayList<>();
        try{
            int municipio = Integer.parseInt(req.getParameter("municipio"));
            ColoniaDao dao = new ColoniaDao();
            lista = dao.findAll(municipio);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(lista);

        resp.setContentType("text/json");
        resp.getWriter().write(json);

    }
}
