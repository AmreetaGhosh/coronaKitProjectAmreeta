package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.model.UserMaster;
import com.mysql.cj.Session;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;

	HttpSession session;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcDriver = config.getServletContext().getInitParameter("jdbcDriver");
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.kitDAO = new KitDao(jdbcDriver, jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcDriver, jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;
			case "valuemissing":
				viewName = fieldValueMissing(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {

			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);

	}

	private String fieldValueMissing(HttpServletRequest request, HttpServletResponse response) {
		return "valuemissing.jsp";
	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException {
		HttpSession session = request.getSession();
		  CoronaKit VisitorBillingDetails = new
		  CoronaKit(request.getParameter("visitorName"),request.getParameter(
		  "visitorEmailid"),request.getParameter("visitorContactNumber"),(double) session.getAttribute("billingAmount"),request.getParameter(
		  "visitorDeliveryAddress"),(String)session.getAttribute("billingDate")); 
		  
		  boolean OrderStoredtoDb=this.kitDAO.saveVisitorDetails(VisitorBillingDetails,(List<KitDetail>)request.getSession().getAttribute("ShoppingCart") );
			
			if(!OrderStoredtoDb)
				throw new ServletException("Order Details are not saved");
			
		 OrderSummary ordersummary = new OrderSummary(VisitorBillingDetails, (List<KitDetail>)request.getSession().getAttribute("ShoppingCart"));
		 request.setAttribute("OrderSummary", ordersummary);
		return "ordersummary.jsp";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {

		/*
		 * String deliveryAddress = request.getParameter("deliverAddress"); session =
		 * request.getSession(); session.setAttribute("DeliveryAddress",
		 * deliveryAddress);
		 */
		return "saveorder.jsp";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		
		 
		return "placeorder.jsp";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		int id = Integer.parseInt(request.getParameter("ProductId"));

		ProductMaster product = this.productMasterDao.getProductRecordsById(id);
		request.setAttribute("product", product);
		return "showkit.jsp";
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {

		int productId = Integer.parseInt(request.getParameter("ProductId"));
		List<KitDetail> existingKitItems = (List<KitDetail>) session.getAttribute("ShoppingCart");
		int itemNum = isAlreadyExistsinCart(productId, existingKitItems);
		existingKitItems.remove(itemNum);
		session.setAttribute("ShoppingCart", existingKitItems);
		if (existingKitItems.size() == 0) {
			session.removeAttribute("ShoppingCart");
			return "user?action=showproducts";
		}
		return "user?action=showkit";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		session = request.getSession();
		KitDetail kit;
		int productId = Integer.parseInt(request.getParameter("ProductId"));

		ProductMaster product = this.productMasterDao.getProductRecordsById(productId);
		if (session.getAttribute("ShoppingCart") == null) {
			Random r = new Random();
			int visitorkitId = (int) (1 + (r.nextDouble() * (Integer.MAX_VALUE - 1)));
			List<KitDetail> cartItems = new ArrayList<KitDetail>();
			kit = new KitDetail(1, visitorkitId, product.getId(), product.getProductName(), 1, product.getCost());
			cartItems.add(kit);

			session.setAttribute("ShoppingCart", cartItems);
			session.setAttribute("coronaKitID", kit.getCoronaKitId());

		} else {
			List<KitDetail> existingKitItems = (List<KitDetail>) session.getAttribute("ShoppingCart");
			int itemPresent = isAlreadyExistsinCart(product.getId(), existingKitItems);
			if (itemPresent == -1) {
				kit = new KitDetail(existingKitItems.get(existingKitItems.size() - 1).getId() + 1,
						(int) session.getAttribute("coronaKitID"), product.getId(), product.getProductName(), 1,
						product.getCost());
				existingKitItems.add(kit);

			} else {
				int quantity = existingKitItems.get(itemPresent).getQuantity() + 1;
				existingKitItems.get(itemPresent).setQuantity(quantity);
				existingKitItems.get(itemPresent).setAmount(quantity * product.getCost());

			}
			session.setAttribute("ShoppingCart", existingKitItems);
		}
		return "user?action=showkit";
	}

	private int isAlreadyExistsinCart(int productId, List<KitDetail> existingCart) {
		for (int i = 0; i < existingCart.size(); i++) {
			if (existingCart.get(i).getId() == productId) {
				return i;
			}
		}
		return -1;
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		List<ProductMaster> products = this.productMasterDao.getProductRecords();
		request.setAttribute("Products", products);
		/*
		 * String vName = request.getParameter("visitorName");
		 * request.getSession().setAttribute("visitorName", vName);
		 */
		return "showproductstoadd.jsp";
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		String vName = request.getParameter("visitorName");
		String vEmail = request.getParameter("visitorEmailid");
		String vContactNum = request.getParameter("visitorContactNumber");
		String vAddress = request.getParameter("visitorDeliveryAddress");
		if(vName.isEmpty() || vName == null || vEmail.isEmpty() || vEmail == null || vContactNum.isEmpty() || vContactNum == null ) {
			return "user?action=valuemissing";
		}
		UserMaster Visitor = new UserMaster(request.getParameter("visitorName"), request.getParameter("visitorEmailid"),
				request.getParameter("visitorContactNumber"), request.getParameter("visitorDeliveryAddress"));
		session = request.getSession();
		session.setAttribute("visitorDetails", Visitor);
		return "user?action=showproducts";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		return "newuser.jsp";
	}
}