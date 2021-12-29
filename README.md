# HttpSession

HTTPSessions, Clients, Web Servers, Endpoints &amp; Logic

# Details
a working understanding of the statelessness of HTTP using  session objects with JSP, Servlet technologies & Web Server(Containerized Application) Refresh sessions logic

# Code
doGet HTTP Method Code Snippet for exper endpoint /api/Hello<br>
<a href="https://github.com/SamBri/HttpSession/blob/master/build/classes/com/codefilms/sessionhacks/Hello.class">Full Code</a><br>
```Java
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
```

# Session States & Responses
created -> 101<br>
<img src="https://github.com/SamBri/HttpSession/blob/master/session_state_created_100.png"></img><br>

active -> 102<br>
<img src="https://github.com/SamBri/HttpSession/blob/master/session_state_active_102.png"></img><br>

expired -> 502<br>
<img src="https://github.com/SamBri/HttpSession/blob/master/session_state_expired_502.png"></img><br>







