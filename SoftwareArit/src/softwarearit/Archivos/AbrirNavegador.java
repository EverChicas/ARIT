/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Archivos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class AbrirNavegador {

    public static void openGrafica(String url) {
        StringBuilder html = new StringBuilder();
        html.append(headerHtml());

        /* AGREGAR TITULO LINEA 37 */
        html.append("GRAFICA");
        html.append("</h2>\n<p>");
        /* DESCRIPCION DE RERPOTE LINEA 41*/
        html.append("GRAFICA GENERADA.");
        html.append("</p>\n");

        html.append("<img src=\"../");
        html.append(url);
        html.append("\">");

        html.append(footerHtml());

        try {
            FileWriter write = new FileWriter("html/grafica.html");
            write.write(html.toString());
            write.close();
            File errores = new File("html/grafica.html");
            Desktop.getDesktop().open(errores);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void mostrarErrores() {
        StringBuilder html = new StringBuilder();
        html.append(headerHtml());

        /* AGREGAR TITULO LINEA 37 */
        html.append("REPORTE DE ERRORES");
        html.append("</h2>\n<p>");
        /* DESCRIPCION DE RERPOTE LINEA 41*/
        html.append("Errores lexicos, sintacticos, semanticos.");
        html.append("</p>\n<table class=\"table\">\n");
        html.append("<thead>\n<tr>\n");

        /* ESPARCIO PARA ENCABEZADOS
         * AGREGAR CON TH
         */
        html.append("<th scope=\"col\">#</th>\n");
        html.append("<th scope=\"col\">");
        html.append("TIPO");
        html.append("</th>\n");
        html.append("<th scope=\"col\">");
        html.append("DESCRIPCION");
        html.append("</th>\n");
        html.append("<th scope=\"col\">");
        html.append("LINEA");
        html.append("</th>\n");
        html.append("<th scope=\"col\">");
        html.append("COLUMNA");
        html.append("</th>\n");

        html.append("</thead>\n<tbody>\n");

        int contador = 1;
        for (NodoError item : Interfaz.LISTA_ERRORES) {
            html.append("<tr>");
            html.append("<th scope=\"row\">");
            html.append(contador);
            html.append("</th>\n");
            /* ESPACIO PARA AGREGAR CONTENIDO A TABLA */
            html.append("<td>");
            html.append(item.Tipo.TipoError);
            html.append("</td>\n");
            html.append("<td>");
            html.append(item.Descripcion);
            html.append("</td>\n");
            html.append("<td>");
            html.append(item.Linea);
            html.append("</td>\n");
            html.append("<td>");
            html.append(item.Columna);
            html.append("</td>\n");
            html.append("</tr>");
            contador++;
        }

        html.append(footerTableHtml());

        try {
            FileWriter write = new FileWriter("html/errores.html");
            write.write(html.toString());
            write.close();
            File errores = new File("html/errores.html");
            Desktop.getDesktop().open(errores);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private static StringBuilder headerHtml() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE HTML>\n<html>\n<head>\n<title>ARIT - COMPI 2</title>\n<meta charset=\"utf-8\" />\n");
        html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />\n");
        html.append("<meta name=\"description\" content=\"\" />\n<meta name=\"keywords\" content=\"\" />\n");
        html.append("<link rel=\"stylesheet\" href=\"assets/css/main.css\" />\n</head>\n<body class=\"is-preload\">\n");
        html.append("<!-- Header -->\n<header id=\"header\">\n<a class=\"logo\" href=\"index.html\">ARIT - COMPI2</a>\n<nav>\n");
        html.append("<a href=\"#menu\">Menu</a>\n</nav>\n</header>\n<nav id=\"menu\">\n<ul class=\"links\">\n");
        html.append("<li><a href=\"index.html\">Reporte de errores</a></li>\n</ul>\n</nav>\n<section id=\"banner\">\n<div class=\"inner\">\n");
        html.append("<h1>COMPI 2</h1>\n<p>Interprete ARIT</p>\n</div>\n<video autoplay loop muted playsinline src=\"images/banner.mp4\"></video>\n");
        html.append("</section>\n<section class=\"wrapper\">\n<div class=\"inner\">\n<header class=\"special\">\n<h2>");
        return html;
    }

    private static StringBuilder footerTableHtml() {
        StringBuilder html = new StringBuilder();
        html.append("</tbody>\n</table>\n</header>\n</div>\n</section>\n<footer id=\"footer\">\n<div class=\"inner\">\n<div class=\"container\">\n");
        html.append("<div class=\"row\">\n<div class=\"col-md-6 col-sm-6\"></div>\n<div class=\"col-md-6 col-sm-6 \">\n<h3>Desarrollador</h3>\n");
        html.append("<p>Ever Eduardo Chicas Prado 201403532</p>\n</div>\n<div class=\"col-md-4\"></div>\n</div>\n</div>\n<div class=\"copyright\">\n");
        html.append("&copy; Untitled. Photos <a href=\"https://unsplash.co\">Unsplash</a>, Video <a\nhref=\"https://coverr.co\">Coverr</a>.\n</div>\n");
        html.append("</div>\n</footer>\n<script src=\"assets/js/jquery.min.js\"></script>\n<script src=\"assets/js/browser.min.js\"></script>\n");
        html.append("<script src=\"assets/js/breakpoints.min.js\"></script>\n<script src=\"assets/js/util.js\"></script>\n");
        html.append("<script src=\"assets/js/main.js\"></script>\n</body>\n</html>\n");
        return html;
    }

    private static StringBuilder footerHtml() {
        StringBuilder html = new StringBuilder();
        html.append("</header>\n</div>\n</section>\n<footer id=\"footer\">\n<div class=\"inner\">\n<div class=\"container\">\n");
        html.append("<div class=\"row\">\n<div class=\"col-md-6 col-sm-6\"></div>\n<div class=\"col-md-6 col-sm-6 \">\n<h3>Desarrollador</h3>\n");
        html.append("<p>Ever Eduardo Chicas Prado 201403532</p>\n</div>\n<div class=\"col-md-4\"></div>\n</div>\n</div>\n<div class=\"copyright\">\n");
        html.append("&copy; Untitled. Photos <a href=\"https://unsplash.co\">Unsplash</a>, Video <a\nhref=\"https://coverr.co\">Coverr</a>.\n</div>\n");
        html.append("</div>\n</footer>\n<script src=\"assets/js/jquery.min.js\"></script>\n<script src=\"assets/js/browser.min.js\"></script>\n");
        html.append("<script src=\"assets/js/breakpoints.min.js\"></script>\n<script src=\"assets/js/util.js\"></script>\n");
        html.append("<script src=\"assets/js/main.js\"></script>\n</body>\n</html>\n");
        return html;
    }
}
