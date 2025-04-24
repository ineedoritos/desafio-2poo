package com.crudjsp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Producto;
import model.ProductoDAO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/EditarProducto")
public class EditarProducto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        String fechaExpiracionStr = request.getParameter("fecha_expiracion");
        java.sql.Date fechaExpiracion = null;

        if (fechaExpiracionStr != null && !fechaExpiracionStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = sdf.parse(fechaExpiracionStr);
                fechaExpiracion = new java.sql.Date(fecha.getTime());
            } catch (Exception e) {
                request.setAttribute("error", "Fecha inválida.");
                request.getRequestDispatcher("formularioEditar.jsp").forward(request, response);
                return;
            }
        }

        Producto producto = new Producto(id, nombre, precio, cantidad, fechaExpiracion);
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.actualizar(producto);

        response.sendRedirect("home.jsp");
    }
}
