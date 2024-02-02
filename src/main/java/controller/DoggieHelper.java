/**
 * @author Mike Cordon - mrcordon@dmacc.edu
 * CIS 175 - Spring 2024
 * Feb 1, 2024
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Doggies;
/**
 * Class to be logical controller for dog database project
 */
public class DoggieHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Doggies");
	
	/**
	 * method to add a row in the dog database table
	 * @param d
	 */
	public void insertItem(Doggies d) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		em.close();
	}
	
	
	/**
	 * method to show all items in the database table
	 * @return
	 */
	public List<Doggies> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<Doggies> allItems = em.createQuery("SELECT i FROM Doggies i").getResultList();
		return allItems;
	}
	
	
	/**
	 * method to delete a row from the database table
	 * @param toDelete
	 */
	public void deleteItem(Doggies toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Doggies> typedQuery = em.createQuery("select d from Doggies d where d.name = :selectedName and d.breed = :selectedBreed and d.color = :selectedColor", Doggies.class);
		
		// Substitute parameter with actual data from toDelete item
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedBreed", toDelete.getBreed());
		typedQuery.setParameter("selectedColor", toDelete.getColor());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		// get the result and save it into a new list item
		Doggies result = typedQuery.getSingleResult();
		
		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	
	/**
	 * method to search for a dog by ID number
	 * @param idToEdit
	 * @return doggies object
	 */
	public Doggies searchForDogById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Doggies found = em.find(Doggies.class, idToEdit);
		em.close();
		return found;
	}
	
	
	/**
	 * method to update a row in the dog database table
	 * @param toEdit
	 */
	public void updateItem(Doggies toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	
	/**
	 * method to search for a dog by name
	 * @param name
	 * @return
	 */
	public List<Doggies> searchForDogByName(String name) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Doggies> typedQuery = em.createQuery("select d from Doggies d where d.name = :selectedName", Doggies.class);
		typedQuery.setParameter("selectedName", name);
		List<Doggies> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	
	/**
	 * method to search for a dog by breed
	 * @param breed
	 * @return
	 */
	public List<Doggies> searchForDogByBreed(String breed) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Doggies> typedQuery = em.createQuery("select d from Doggies d where d.breed = :selectedBreed", Doggies.class);
		typedQuery.setParameter("selectedBreed", breed);
		List<Doggies> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	
	/**
	 * method to search for a dog by color
	 * @param color
	 * @return
	 */
	public List<Doggies> searchForDogByColor(String color) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Doggies> typedQuery = em.createQuery("select d from Doggies d where d.color = :selectedColor", Doggies.class);
		typedQuery.setParameter("selectedColor", color);
		List<Doggies> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	/**
	 * clean up method to close EntityManager factory
	 */
	public void cleanUp() {
		emfactory.close();
	}

	
}
