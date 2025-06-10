/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BrandDAO;
import dal.ProductDAO;
import dal.ProductImageDAO;
import dal.TypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Product;
import model.Type;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

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
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        String brandId_Raw = request.getParameter("brandId");
        String typeId_Raw = request.getParameter("typeId");

        try {
            List<Product> productList = new ArrayList<>();
            
            ProductDAO pd = new ProductDAO();
            ProductImageDAO pid = new ProductImageDAO();
            BrandDAO bd = new BrandDAO();
            TypeDAO td = new TypeDAO();
            
            if (brandId_Raw != null) {
                int brandId = Integer.parseInt(brandId_Raw);
                productList = pd.getProductbyBrandId(brandId);
            } else if (typeId_Raw != null) {
                int typeId = Integer.parseInt(typeId_Raw);
                productList = pd.getProductbyTypeId(typeId);
            } else {
                productList = pd.getAll();
            }

           
            List<List<String>> imageLists = new ArrayList<>();
            for (Product p : productList) {
                List<String> imgs = pid.getImagesByProductId(p.getProductId());
                imageLists.add(imgs);
            }

            List<Brand> brandList = bd.getAll();
            List<Type> typeList = td.getAll();

            request.setAttribute("productList", productList);
            request.setAttribute("numP", productList.size());
            request.setAttribute("imageLists", imageLists);
            request.setAttribute("brandList", brandList);
            request.setAttribute("typeList", typeList);

            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            System.out.println(e);
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
