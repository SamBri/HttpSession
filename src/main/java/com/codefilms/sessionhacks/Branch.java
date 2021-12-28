package com.codefilms.sessionhacks;

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;

import com.codefilms.sessionhacks.schema.RequestSchema;
import com.fasterxml.jackson.databind.ObjectMapper;

//import org.apache.tomcat.util.json.*;


//import org.apache.tomcat.util.json.JSONParser;

/**
 * Servlet implementation class Branch
 */
@WebServlet(urlPatterns={"/api/Branch","/api/Branch/Service"})
public class Branch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Branch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Retrieve the amount deposited from the session bag.
		Double theDeposit = (Double)request.getAttribute("deposit");
		
		PrintWriter out = response.getWriter(); 
	
		// set response headers to return as JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		// create a json string
		ObjectMapper mapper = new ObjectMapper();
		String theDepositToJson = mapper.writeValueAsString(theDeposit);		
		
		
		// console log the session details
		System.out.println("session time out in seconds:"+request.getSession().getMaxInactiveInterval());
		System.out.println("in doGet() ==> session id:"+request.getSession().getId());

		
		//Return to REST Client
		out.println(theDepositToJson);
		
		
		
		
		
		
	
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 
		PrintWriter out = response.getWriter();
		request.getSession().setMaxInactiveInterval(10); // set the session bag to expire after 10 seconds
		
		// JSON to POJO Conversion using Jackson. 
		ObjectMapper mapper = new ObjectMapper();
		RequestSchema json = mapper.readValue(request.getInputStream(), RequestSchema.class);
		
		// set response headers.
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		//String toJson = mapper.writeValueAsString(json);
		
		
		//give user a session 
		
		// set amount in a session bag
		request.setAttribute("deposit", json.getAmount());
		
	
		// console log request submitted.
		System.out.println("in doPost(): track ==>"+json);
		System.out.println("in doPost() ==> session id:"+request.getSession().getId());

		
		doGet(request, response); // goTo doGet Method to return to REST Client
	}

}
