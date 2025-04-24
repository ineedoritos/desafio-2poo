package com.crudjsp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import model.ProductoDAO;

import java.io.IOException;

@WebServlet("/MostrarFormularioEditar")
public class formularioEditar extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        ProductoDAO dao = new ProductoDAO();
        Producto producto = dao.obtenerPorId(id);  // Asegúrate de tener este método en tu DAO
        request.setAttribute("producto", producto);
        request.getRequestDispatcher("formularioEditar.jsp").forward(request, response);
    }
}
