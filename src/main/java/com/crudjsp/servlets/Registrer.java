package com.crudjsp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mocks.UsuarioDB;
import model.Usuario;


import java.io.IOException;
import java.util.Objects;

import static model.Usuario.hashear;

@WebServlet("/register")
public class Registrer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, IllegalArgumentException {

        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            if (Objects.isNull(email) || Objects.isNull(password) || email.trim().isEmpty() || password.trim().isEmpty())
                throw new IllegalArgumentException("estos son campos vacios");

            //comprobar si existe email antes de registrarlo

            Usuario usuarioExistente = UsuarioDB.buscarUsuario(email);
            // es como el !user pero aca no puedo convertir mis tipos como peras como en js o ts xd
            // entonces evaluamos si no existe para registrarlo y sino que tire un buen errorazo
            if (usuarioExistente == null) {
                //hashear la contraseña y meter a la persona a la base de datos fake
                String hashed = hashear(password);
                Usuario newUser = new Usuario(email, hashed);
                UsuarioDB.agregarUsuario(newUser);


                System.out.println(email);
                System.out.println(hashed);
                UsuarioDB.mostrarUsuarios();
                res.sendRedirect("Login.html");

            } else{
                throw new IllegalArgumentException("Email ya en uso");
            }



        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            res.setStatus(400);
            req.setAttribute("error", "Error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }

    }
}