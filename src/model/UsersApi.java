package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map; 
import java.util.Scanner;

@WebServlet("/UsersApi")
public class UsersApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

    User userObj = new User();
	
	
    public UsersApi() {
    	super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = userObj.insertUser(
				   request.getParameter("uName"), 
				   request.getParameter("uAddress"), 
				   request.getParameter("uEmail"), 
				   request.getParameter("uNIC"),
				   request.getParameter("uPhoneNo")); 
		response.getWriter().write(output);
		
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 String output = userObj.updateUser(paras.get("hidUserIDSave").toString(), 
				 							paras.get("uName").toString(), 
				 							paras.get("uAddress").toString(), 
				 							paras.get("uEmail").toString(), 
				 							paras.get("uNIC").toString(),
				 							paras.get("uPhoneNo").toString()); 
		response.getWriter().write(output); 
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = userObj.deleteUser(paras.get("uID").toString()); 
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}

}
