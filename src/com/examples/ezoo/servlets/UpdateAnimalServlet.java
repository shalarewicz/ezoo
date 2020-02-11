package com.examples.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.model.Animal;

/**
 * Servlet implementation class UpdateAnimalServlet
 */
@WebServlet(name = "updateAnimal", urlPatterns = { "/updateAnimal" })
public class UpdateAnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String kingdom = request.getParameter("taxKingdom");
		String phylum = request.getParameter("taxPhylum");
		String clazz = request.getParameter("taxClass");
		String order = request.getParameter("taxOrder");
		String family = request.getParameter("taxFamily");
		String genus = request.getParameter("taxGenus");
		String species = request.getParameter("taxSpecies");
		double height = Double.parseDouble(request.getParameter("height"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		String type = request.getParameter("type");
		String healthStatus = request.getParameter("healthStatus");
		Long feedingSchedule = Long.parseLong(request.getParameter("feedingSchedule"));
		
		Animal animal = new Animal(
				id, 
				name, 
				kingdom,
				phylum,
				clazz,
				order,
				family,
				genus,
				species,
				height,
				weight,
				type,
				healthStatus);
		
		animal.setFeedingSchedule(feedingSchedule);
		
		request.getSession().setAttribute("updatedAnimal", animal);
		request.getSession().setAttribute("healthStatuses", Animal.HEALTH_STATUSES);
		request.getSession().setAttribute("types", Animal.TYPES);
		request.getRequestDispatcher("updateAnimal.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		//We MUST convert to a Long since parameters are always Strings
		long id = Long.parseLong(request.getParameter("id"));
		
		String name = request.getParameter("name");

		String kingdom = request.getParameter("kingdom");
		String phylum = request.getParameter("phylum");
		String clazz = request.getParameter("class");
		String order = request.getParameter("order");
		String family = request.getParameter("family");
		String genus = request.getParameter("genus");
		String species = request.getParameter("species");
		String type = request.getParameter("type");
		String healthStatus = request.getParameter("healthStatus");
	
		
		//Since request parameters are ALWAYS Strings we will convert them to Double
		double height = Double.parseDouble(request.getParameter("height"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		
		//Create an Animal object from the parameters
		Animal animalToSave = new Animal(
				id, 
				name, 
				kingdom,
				phylum,
				clazz,
				order,
				family,
				genus,
				species,
				height,
				weight,
				type,
				healthStatus);
		
		Long feedingSchedule;
		try {
			feedingSchedule = Long.parseLong(request.getParameter("feedingSchedule"));
			animalToSave.setFeedingSchedule(feedingSchedule);
		} catch (NumberFormatException nfe) {
			feedingSchedule = null;
		}
		
		//Call DAO method
		AnimalDAO dao = DAOUtilities.getAnimalDao();
		try {
			dao.updateAnimal(id, animalToSave);
			request.getSession().setAttribute("message", "Animal successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");


		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem updating the animal at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			response.sendRedirect("animalCare");
		}
	}

}
