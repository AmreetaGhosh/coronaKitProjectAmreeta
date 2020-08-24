package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	
	
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcDriver =  config.getServletContext().getInitParameter("jdbcDriver");
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao(jdbcDriver, jdbcURL, jdbcUsername, jdbcPassword);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		String viewName = "";
		try {
			switch (action) {
			case "login" : 
				viewName = adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			case "InvalidCreds":
				viewName = invalidCredtional(request, response);
				break;	
			case "prodNotAdded":
				viewName = prodNotadded(request, response);
				break;	
			case "prodNotDeleted":
				viewName = prodNotDeleted(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String prodNotDeleted(HttpServletRequest request, HttpServletResponse response) {
		return "productNotDeleted.jsp";
	}

	private String prodNotadded(HttpServletRequest request, HttpServletResponse response) {
		return "productNotAddedError.jsp";
	}

	private String invalidCredtional(HttpServletRequest request, HttpServletResponse response) {
		
		return "adminNotFound.jsp";
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		return "index.jsp";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
				List<ProductMaster> products = this.productMasterDao.getProductRecords();
				request.setAttribute("Products", products);
				return "listproducts.jsp";
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		
			int id = Integer.parseInt(request.getParameter("id"));
		  String pname = request.getParameter("pname");
		  float pcost=Float.parseFloat(request.getParameter("pcost"));
		  String pdesc =request.getParameter("pdesc"); 
		 
		ProductMaster product = new ProductMaster(id, pname, pcost, pdesc);
		Boolean update = this.productMasterDao.updateProduct(product);
		if(update)
		return "admin?action=list";
		else
			return "errorPage.jsp";
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		String id = request.getParameter("id");
		ProductMaster existingProduct = this.productMasterDao.getProductRecordsById(Integer.parseInt(id));
		request.setAttribute("existingProduct", existingProduct);
		return "editproduct.jsp";
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		String id = request.getParameter("id");
		Boolean deleted = this.productMasterDao.deleteProduct(id);
		
		if(deleted)
			
			return "admin?action=list";
		else
			return "productDeleted.jsp";
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		String pname = request.getParameter("pname");
		String pcost= request.getParameter("pcost");
		String pdesc = request.getParameter("pdesc");
		Boolean added = this.productMasterDao.addNewProduct(pname, pcost, pdesc);
		
		if(added) {
			return "admin?action=list";
		}
		else {
			return "errorPage.jsp";
		}
			
		
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		
		return "newproduct.jsp";
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		String loginid = request.getParameter("loginid");
		String password = request.getParameter("password");
		if(loginid.equalsIgnoreCase("Admin") && password.equalsIgnoreCase("Admin")) {
			return "admin?action=list";
		}
		else
			return "admin?action=InvalidCreds";
	}
	
	
	
}