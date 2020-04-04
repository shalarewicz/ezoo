package shala.ezoo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.dao.DAOUtilities;

/**
 * Servlet implementation class DeleteAnimalServlet
 */
@WebServlet(name = "deleteAnimal", urlPatterns = { "/deleteAnimal" })
public class DeleteAnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("deleteAnimal.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long animalId = Long.parseLong(request.getParameter("id"));
		
		AnimalDAO dao = DAOUtilities.getAnimalDao();
		try {
			dao.removeAnimal(animalId);
			request.getSession().setAttribute("message", "Animal successfully removed");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");
		} catch (Exception e) {
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem deleting the animal at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			response.sendRedirect("animalCare");
		}
	}

}
