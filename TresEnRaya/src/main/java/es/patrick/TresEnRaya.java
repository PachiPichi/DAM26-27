package es.patrick;


import config.ThymeleafConstants;
import config.UrlConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/tres")
public class TresEnRaya extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        // Parámetros
        String marca = req.getParameter("marca");
        String coordenada = req.getParameter("coordenada");


        //Sesión
        HttpSession session = req.getSession();
        String[] Tablero = (String[]) req.getSession().getAttribute("Tablero");


        if (Tablero==null) {
            Tablero = new String[]{
                    "-", "-", "-",
                    "-", "-", "-",
                    "-", "-", "-"
            };
        }




        if (marca != null && coordenada != null) {

            int posicion;

            try {
                posicion = Integer.parseInt(coordenada);

                if (posicion < 1 || posicion > Tablero.length ||
                        (!marca.equals("X") && !marca.equals("O"))) {
                    resp.sendError(
                            HttpServletResponse.SC_BAD_REQUEST,
                            "Coordenada o marca incorrecta"
                    );
                    return;
                }
            } catch (NumberFormatException e) {
                resp.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "La coordenada debe ser un número"
                );
                return;
            }

        Tablero[posicion-1]=marca;

        }

        // Guardar tablero
        session.setAttribute("Tablero", Tablero);


        // Array convertido en 3 filas
        String fila1 = Tablero[0]+ " " + Tablero[1]+ " " + Tablero[2];
        String fila2 = Tablero[3]+ " " + Tablero[4]+ " " + Tablero[5];
        String fila3 = Tablero[6]+ " " + Tablero[7] + " " + Tablero[8];

        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext ctx = new WebContext(webExchange);
        ctx.setVariable("fila1", fila1);
        ctx.setVariable("fila2", fila2);
        ctx.setVariable("fila3", fila3);

        resp.setContentType(ThymeleafConstants.CONTENT_TYPE);
        ((TemplateEngine)getServletContext().getAttribute(ThymeleafConstants.TEMPLATE_ENGINE_ATTR))
                .process(UrlConstants.TEMPLATE_TRES_EN_RAYA, ctx, resp.getWriter());


    }

}