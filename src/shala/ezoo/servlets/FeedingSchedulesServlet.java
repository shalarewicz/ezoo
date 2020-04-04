package shala.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shala.ezoo.dao.DAOUtilities;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class FeedingSchedules
 */
@WebServlet(urlPatterns = { "/feedingSchedules" })
public class FeedingSchedulesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get a set of schedules from the database
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		List<FeedingSchedule> schedules = dao.getAllSchedules();
		
		// Populate the set into a variable that will be stored in the session
		request.getSession().setAttribute("schedules", schedules);
		
		request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("deleteSchedule").forward(request, response);
		
	}
	
	

}
