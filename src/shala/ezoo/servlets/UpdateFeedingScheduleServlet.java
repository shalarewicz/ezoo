package shala.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shala.ezoo.dao.DAOUtilities;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class UpdateFeedingScheduleServlet
 */
@WebServlet(name = "updateSchedule", urlPatterns = { "/updateSchedule" })
public class UpdateFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		String time = request.getParameter("feedingTime"); //TODO Date and time
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
	
		FeedingSchedule schedule = new FeedingSchedule(id, time, recurrence, food, notes);
		request.getSession().setAttribute("schedule", schedule);
		request.getSession().setAttribute("recurrences", FeedingSchedule.RECURRENCES);
		request.getRequestDispatcher("updateSchedule.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Parameters
		
		long id = Long.parseLong(request.getParameter("id"));
		String time = request.getParameter("feedingTime");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
	
		FeedingSchedule schedule = new FeedingSchedule(id, time, recurrence, food, notes);
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		
		try	{
			dao.updateSchedule(schedule);
			request.getSession().setAttribute("message", "Schedule successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			// update message
			request.getSession().setAttribute("message", "There was a problem updating the schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("updateSchedule.jsp").forward(request, response);
		}
	}

}
