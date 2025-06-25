/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import dal.ProductImageDAO;
import dal.ProductOptionDAO;
import dal.SpecificationsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;import java.util.List;
import model.Product;
import model.ProductOption;
import model.Specifications;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DetailServlet", urlPatterns = {"/detail"})
public class DetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRaw = request.getParameter("pid");
        String optionRaw = request.getParameter("optionId");

        try {
            int id = Integer.parseInt(idRaw);
            ProductDAO dao = new ProductDAO();
            ProductImageDAO pid = new ProductImageDAO();
            ProductOptionDAO pod = new ProductOptionDAO();
            SpecificationsDAO specDAO = new SpecificationsDAO();

            Product product = dao.getProductById(id);
            List<String> images = pid.getImagesByProductId(id);
            List<ProductOption> options = pod.getByProductID(id);

            request.setAttribute("product", product);
            request.setAttribute("images", images);
            request.setAttribute("options", options);

            // Neu nguoi dung da chon 1 option
            if (optionRaw != null) {
                int optionId = Integer.parseInt(optionRaw);
                ProductOption selectedOption = pod.getByOptionId(optionId);
                List<Specifications> specs = specDAO.getByOptionId(optionId);

                request.setAttribute("selectedOption", selectedOption);
                request.setAttribute("specifications", specs);
            }

            request.getRequestDispatcher("detail.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("DetailServlet Error: " + e.getMessage());
            // response.sendRedirect("error.jsp");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
