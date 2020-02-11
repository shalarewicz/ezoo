package com.examples.ezoo.model;

import java.util.Arrays;
import java.util.List;

public class Animal{
	
	private long animalID = 0L;
	private String name = "";
	
	private String taxKingdom = "";
	private String taxPhylum = "";
	private String taxClass = "";
	private String taxOrder = "";
	private String taxFamily = "";
	private String taxGenus = "";
	private String taxSpecies = "";
	
	private double height = 0D;
	private double weight = 0D;
	
	private String type = "";
	private String healthStatus = "";
	
	private Long feedingSchedule = null;
	
	public static List<String> HEALTH_STATUSES = Arrays.asList("Healthy", "Sick", "Injured", "Dead");
	public static List<String> TYPES = Arrays.asList(
			"Mammal (Terrestrial)", "Mammal (Aquatic)", "Mammal (Aviary)", "Fish", "Amphibian", "Reptile", "Bird");
	
	public Animal(){}

	public Animal(long animalID, String name, String taxKingdom, String taxPhylum, String taxClass, String taxOrder,
			String taxFamily, String taxGenus, String taxSpecies, double height, double weight, String type,
			String healthStatus) {
		super();
		this.animalID = animalID;
		this.name = name;
		this.taxKingdom = taxKingdom;
		this.taxPhylum = taxPhylum;
		this.taxClass = taxClass;
		this.taxOrder = taxOrder;
		this.taxFamily = taxFamily;
		this.taxGenus = taxGenus;
		this.taxSpecies = taxSpecies;
		this.height = height;
		this.weight = weight;
		this.type = type;
		this.healthStatus = healthStatus;
	}

	public long getAnimalID() {
		return animalID;
	}

	public void setAnimalID(long animalID) {
		this.animalID = animalID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaxKingdom() {
		return taxKingdom;
	}

	public void setTaxKingdom(String taxKingdom) {
		this.taxKingdom = taxKingdom;
	}

	public String getTaxPhylum() {
		return taxPhylum;
	}

	public void setTaxPhylum(String taxPhylum) {
		this.taxPhylum = taxPhylum;
	}

	public String getTaxClass() {
		return taxClass;
	}

	public void setTaxClass(String taxClass) {
		this.taxClass = taxClass;
	}

	public String getTaxOrder() {
		return taxOrder;
	}

	public void setTaxOrder(String taxOrder) {
		this.taxOrder = taxOrder;
	}

	public String getTaxFamily() {
		return taxFamily;
	}

	public void setTaxFamily(String taxFamily) {
		this.taxFamily = taxFamily;
	}

	public String getTaxGenus() {
		return taxGenus;
	}

	public void setTaxGenus(String taxGenus) {
		this.taxGenus = taxGenus;
	}

	public String getTaxSpecies() {
		return taxSpecies;
	}

	public void setTaxSpecies(String taxSpecies) {
		this.taxSpecies = taxSpecies;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	
	public Long getFeedingSchedule() {
		return this.feedingSchedule;
	}
	
	public void setFeedingSchedule(long schedule) {
		this.feedingSchedule = schedule;
	}

	@Override
	public String toString() {
		return "Animal [animalID=" + animalID + ", name=" + name + ", taxKingdom=" + taxKingdom + ", taxPhylum="
				+ taxPhylum + ", taxClass=" + taxClass + ", taxOrder=" + taxOrder + ", taxFamily=" + taxFamily
				+ ", taxGenus=" + taxGenus + ", taxSpecies=" + taxSpecies + ", height=" + height + ", weight=" + weight
				+ ", type=" + type + ", healthStatus=" + healthStatus + ", feedingSchedule=" + feedingSchedule + "]";
	}
	
	
	
}
