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

import com.codefilms.sessionhacks.schema.SessionState;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

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
		Integer theTimeToLive = runningSession.getMaxInactiveInterval();
		String tempSessionId = ""; // save the running session generated on REQUEST <-> Route.
		
		//display the running session id first 
		System.out.println("Inside doGet(): init  ==> Running Session Id:" + runningSessionId);
		System.out.println("Inside doGet(): init ==> Current Session Id:" + currentSessionId);
		
		
		//set headers content-type to return json
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		
		//String jsonString = "Running Session Id:" + runningSessionId; 
		
		SessionState theSessionState = new SessionState();
		// on same state and null state
	 if(currentSessionId == null || currentSessionId.equals(runningSessionId)) {
		 
		 //for null sessionId the session was on start, session was created
		 if(currentSessionId == null)
		 {
			 theSessionState.setStatusCode("100"); // session is created.
		 	 theSessionState.setMessage("created"); 
		 	 theSessionState.setTimeToLive(theTimeToLive);
		 	 theSessionState.setRunningSessionId(runningSessionId);  // mark session state running
			 theSessionState.setCurrentSessionId(currentSessionId); // mark session state current
			
		 } else {
		 theSessionState.setStatusCode("101"); //session is active
		 theSessionState.setRunningSessionId(runningSessionId);  // mark session state running
		 theSessionState.setCurrentSessionId(currentSessionId); // mark session state current
		 theSessionState.setTimeToLive(theTimeToLive);
		 theSessionState.setMessage("active"); // session is active
		 }
		 ObjectMapper mapper = new ObjectMapper();
		 String sessionStateJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(theSessionState);
		 //String sessionStateString = new Gson().toJson(theSessionState);
		 
		 System.out.println("Inside doGet(): track  ==> Running Session Id:" + runningSessionId);
		 System.out.println("Inside doGet(): track  ==> Current Session Id:" + currentSessionId);
			

		 out.println(sessionStateJson); // print as json 
		// out.println(theSessionState); // print as json but not marshalled.
		 
		}else if(!currentSessionId.equals(runningSessionId))
		{
		theSessionState.setStatusCode("502"); //expired session
		theSessionState.setRunningSessionId(runningSessionId);
		theSessionState.setCurrentSessionId(currentSessionId);
	 	theSessionState.setTimeToLive(0);
		theSessionState.setMessage("expired"); // session expired.
		
		ObjectMapper mapper = new ObjectMapper();
		String sessionStateJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(theSessionState);
		
		
		out.println(sessionStateJson); // print as json
		
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
		
		// save payload or request schema.
		
		
		
		
		
		
		
		
		
		
		
		
		
		//doGet(request, response);
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
