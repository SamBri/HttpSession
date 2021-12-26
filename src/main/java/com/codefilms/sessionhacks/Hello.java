package com.codefilms.sessionhacks;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/api/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// create a session
	//private  HttpSession currentSession;
	
	private HttpSession runningSession;
	
	private  String currentSessionId;

	private String runningSessionId;
	
			
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		System.out.println("api started.");
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); // getWriter to for HTML View
		runningSession = request.getSession(); // obtain a new session 
		runningSession.setMaxInactiveInterval(10); // set the maximum session interval
		runningSessionId = runningSession.getId();//user obtains a running session id on GET Request
		
		String tempSessionId = ""; // save the running session generated on REQUEST <-> Route.
		
		//display the running session id first 
		System.out.println("Inside doGet(): init  ==> Running Session Id:" + runningSessionId);
		System.out.println("Inside doGet(): init ==> Current Session Id:" + currentSessionId);
		
		// on same state and null state
	 if(currentSessionId == null || currentSessionId.equals(runningSessionId)) {
			out.println("Running Session Id:" + runningSessionId);
		}else if(!currentSessionId.equals(runningSessionId))
		{
		out.println("Expired Session");
		// generate new session
		 currentSessionId = null; // reset currentSessionId
		 System.out.println("Before leaving doGet(): track ==> Running Session Id:"+runningSessionId);
		 System.out.println("Before leaving doGet(): track ==> Current session Id:"+ currentSessionId);
		 return;
		} 

		
		//set the currentSessionId to the runningSessionId
		tempSessionId = runningSessionId;
		currentSessionId = tempSessionId; // done.
		
		System.out.println("Inside doGet(): track  ==> Current Session Id:" + currentSessionId);
		System.out.println("Inside doGet(): track  ==> Running Session Id:" + runningSessionId);
		} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

	/*@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service method called..");
		doGet(req, resp);
		
		
	}*/

	/*@Override
	public void destroy() {
		//before destroy reset session timeout to 10 seconds
		mySession.setMaxInactiveInterval(10);
		System.out.println("session reset to:"+mySession.getMaxInactiveInterval()+"seconds");
		System.out.println("api destroyed..");
	} */

	
	
	
	
	
	
	
}
