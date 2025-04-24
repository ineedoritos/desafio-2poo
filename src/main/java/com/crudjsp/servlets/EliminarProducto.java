// Servlet EliminarProducto
package com.crudjsp.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.ProductoDAO;

import java.io.IOException;

@WebServlet("/EliminarProducto")
public class EliminarProducto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del producto desde el formulario
        long id = Long.parseLong(request.getParameter("id"));

        // Crear una instancia de ProductoDAO y eliminar el producto
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.eliminar(id);  // Método eliminar que debe estar en el DAO

        // Redirigir a la página principal después de eliminar el producto
        response.sendRedirect("home.jsp");
    }
}
