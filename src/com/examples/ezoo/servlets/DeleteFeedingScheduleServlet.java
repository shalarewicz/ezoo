package com.examples.ezoo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;

/**
 * Servlet implementation class DeleteFeedingScheduleServlet
 */
@WebServlet("/deleteSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Option to remove another schedule? Route response appropriately
		request.getRequestDispatcher("deleteSchedule.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long scheduleId = Long.parseLong(request.getParameter("id"));
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		
		dao.removeSchedule(scheduleId);
		request.getSession().setAttribute("message", "Feeding Schedule successfully removed");
		request.getSession().setAttribute("messageClass", "alert-success");
		response.sendRedirect("feedingSchedules");
		
	}

}
