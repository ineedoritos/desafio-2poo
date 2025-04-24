package com.crudjsp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mocks.UsuarioDB;
import model.Usuario;

import java.io.IOException;
import java.util.Objects;

import static model.Usuario.verificar;
//los controladores servlets son bastante eficientes y su enrutamiento es tan bonito
@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            if (Objects.isNull(email) || Objects.isNull(password) || email.trim().isEmpty() || password.trim().isEmpty())
                throw new IllegalArgumentException("Campos vacíos");

            Usuario usuarioExistente = UsuarioDB.buscarUsuario(email);
            if (usuarioExistente == null)
                throw new IllegalArgumentException("El usuario no existe");

            String hashed = usuarioExistente.getPassword();

            if (verificar(password, hashed)) {
                HttpSession session = req.getSession();
                session.setAttribute("usuario", usuarioExistente);
                res.sendRedirect("home.jsp");
            } else {
                throw new IllegalArgumentException("Contraseña incorrecta");
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            res.setStatus(400);
            req.setAttribute("error", "Error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
