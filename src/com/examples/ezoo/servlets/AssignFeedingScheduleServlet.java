package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;

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
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		
		long scheduleId = Long.parseLong(request.getParameter("scheduleId"));
		long animalId = Long.parseLong(request.getParameter("animalId"));
		try {
			 // TODO By using objects risk adding blank animals/schedules to database. Use IDs? instead? 
			dao.setFeedingSchedule(scheduleId, animalId); 
			request.getSession().setAttribute("message", "Schedule successfully assigned");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("assignSchedule");

			
		} catch (SQLException e) {
			e.printStackTrace();
			// Have the user add the animal to the database. 
			request.getSession().setAttribute("message", "Pleaase add the animal and feeding schedule prior to assigning it a schedule.");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("addAnimal.jsp").forward(request, response);
			
		} catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem assigning the schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("assignSchedule.jsp").forward(request, response);

		}
	}

}
