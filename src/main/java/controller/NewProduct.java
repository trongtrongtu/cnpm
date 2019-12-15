/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAOImple;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;

/**
 *
 * @author Admin
 */
@WebServlet(urlPatterns = {"/ProductServlet"})
public class NewProduct extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ProductDAOImple productDAO = new ProductDAOImple();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/product.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String product_name = request.getParameter("product_name");
        int category = Integer.parseInt(request.getParameter("category"));
        float price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");
        String image1 = request.getParameter("image1");
        String image2 = request.getParameter("image2");
        String image3 = request.getParameter("image3");
        String image4 = request.getParameter("image4");
        String image5 = request.getParameter("image5");
        java.sql.Date date = null;
        try {
            date = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"))).getTime());
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        String error = "";
        String url = "/product.jsp";

        if (product_name.equals("") || category == 0 || price == 0 || description.equals("") || image1.equals("") || image2.equals("") || image3.equals("") || image4.equals("") || image5.equals("")) {
            error += "Add info, please!";
        } 

        if (error.length() > 0) {
            request.setAttribute("error", error);
        }

        try {
            if (error.length() == 0) {
                productDAO.addProduct(new Product(product_name, price, description, image1, image2, image3, image4, image5, date, category));
                response.sendRedirect("product.jsp");
            } else {
                url = "/product.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/product.jsp");
        }

    }
}
