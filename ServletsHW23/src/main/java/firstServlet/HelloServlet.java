package firstServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;


public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String COOKIE_NAME = "lastVisit";   
	
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDateTime ldt = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH:mm");
		String currentTime = ldt.format(formatter);
		Cookie[] cookies = request.getCookies();
		String lastVisit = "never";
		if(cookies != null) {
			for(Cookie cookie: cookies) {
			if(	cookie.getName().equals(COOKIE_NAME)) {
				lastVisit = cookie.getValue();
			}
			}
		}
		
		response.addCookie(new Cookie(COOKIE_NAME, currentTime));
		PrintWriter writer =  response.getWriter();
		writer.println("<html>");
		writer.println("<body>");
		
		writer.println("<h2> Your last visit at: " + lastVisit + "</h2>");
		writer.println("<h2>Current time: " + currentTime + "</h2>");
		
		writer.println("</html>");
		writer.println("</body>");
	}

	

}
