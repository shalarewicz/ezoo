package shala.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import shala.ezoo.config.Config;
import shala.ezoo.dao.FeedingScheduleDAO;

/**
 * Servlet implementation class AssignFeedingScheduleServlet
 */
@WebServlet(description = "Assigns Feeding Schedule to an animal", urlPatterns = { "/assignSchedule" })
public class AssignFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("assignSchedule.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        FeedingScheduleDAO repo = context.getBean(FeedingScheduleDAO.class);
        
		long scheduleId = Long.parseLong(request.getParameter("scheduleId"));
		long animalId = Long.parseLong(request.getParameter("animalId"));
		try {
			 // TODO By using objects risk adding blank animals/schedules to database. Use IDs? instead? 
			repo.setFeedingSchedule(scheduleId, animalId); 
			request.getSession().setAttribute("message", "Schedule successfully assigned");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("assignSchedule");
					
		} catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem assigning the schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("assignSchedule.jsp").forward(request, response);

		} finally {
		    ((AnnotationConfigApplicationContext) context).close();
		}
	}

}
