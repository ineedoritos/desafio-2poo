package com.crudjsp.servlets;

import jakarta.servlet.annotation.WebServlet;
import model.Producto;
import model.ProductoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/CrearProducto")
public class CrearProducto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        String fechaExpiracionStr = request.getParameter("fecha_expiracion");
        System.out.println("AAAAAAAAA");
        System.out.println(fechaExpiracionStr);
        java.sql.Date fechaExpiracion = null;

        // Validar y convertir la fecha
        if (fechaExpiracionStr != null && !fechaExpiracionStr.isEmpty()) {
            try {
                // Usamos SimpleDateFormat para convertir la fecha
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // ✅ Este es el correcto
                Date fecha = sdf.parse(fechaExpiracionStr);

                // Convertir a java.sql.Date
                fechaExpiracion = new java.sql.Date(fecha.getTime());
            } catch (Exception e) {
                // Manejo de error: fecha no válida
                request.setAttribute("error", "Fecha de expiración inválida. El formato debe ser MM-DD-YYYY.");
                request.getRequestDispatcher("formularioCrear.jsp").forward(request, response);
                return;
            }
        }

        Producto producto = new Producto(nombre, precio, cantidad, fechaExpiracion);
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.agregar(producto);

        response.sendRedirect("home.jsp");
    }
}
