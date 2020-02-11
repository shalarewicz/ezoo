/**
 * 
 */
package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * @author shala
 *
 */
@WebServlet("/addSchedule")
public class CreateFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("addSchedule.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get Parameters
		
		long id = Long.parseLong(req.getParameter("id"));
		String time = req.getParameter("time");
		String recurrence = req.getParameter("recurrence");
		String food = req.getParameter("food");
		String notes = req.getParameter("notes");
	
		FeedingSchedule schedule = new FeedingSchedule(id, time, recurrence, food, notes);
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		
		try	{
			dao.addSchedule(schedule);
			req.getSession().setAttribute("message", "Schedule successfully added");
			req.getSession().setAttribute("messageClass", "alert-success");
			resp.sendRedirect("feedingSchedules");
			
		} catch (SQLIntegrityConstraintViolationException sqe){ 
			sqe.printStackTrace();
			
			// update message
			req.getSession().setAttribute("message", "Id of " + schedule.getScheduleId() + " is already in use");
			req.getSession().setAttribute("messageClass", "alert-danger");
			
			req.getRequestDispatcher("addAnimal.jsp").forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			// update message
			req.getSession().setAttribute("message", "There was a problem creating the schedule at this time");
			req.getSession().setAttribute("messageClass", "alert-danger");
			
			req.getRequestDispatcher("addSchedule.jsp").forward(req, resp);
		}
		
	}


}
