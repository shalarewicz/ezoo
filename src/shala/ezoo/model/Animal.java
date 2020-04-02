package shala.ezoo.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ANIMALS")
public class Animal{
	
	@Id
	private long animalID = 0L;
	@Column
	private String name = "";
	
	@Column
	private String taxKingdom = "";
	@Column
	private String taxPhylum = "";
	@Column
	private String taxClass = "";
	@Column
	private String taxOrder = "";
	@Column
	private String taxFamily = "";
	@Column
	private String taxGenus = "";
	@Column
	private String taxSpecies = "";
	
	@Column
	private double height = 0D;
	@Column
	private double weight = 0D;
	
	@Column
	private String type = "";
	@Column
	private String healthStatus = "";
	
	@ManyToOne(targetEntity=FeedingSchedule.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="feedingschedule")
	private FeedingSchedule feedingSchedule = null;
	
	@Transient
	private Long feedingScheduleId = null;
	
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
	
	public FeedingSchedule getFeedingSchedule() {
		return this.feedingSchedule;
	}
	
	public void setFeedingSchedule(FeedingSchedule schedule) {
		this.feedingSchedule = schedule;
	}

	
    public Long getFeedingScheduleId() {
        return feedingScheduleId;
    }

    
    public void setFeedingScheduleId(Long feedingScheduleId) {
        this.feedingScheduleId = feedingScheduleId;
    }

    @Override
	public String toString() {
		return "Animal [animalID=" + animalID + ", name=" + name + ", taxKingdom=" + taxKingdom + ", taxPhylum="
				+ taxPhylum + ", taxClass=" + taxClass + ", taxOrder=" + taxOrder + ", taxFamily=" + taxFamily
				+ ", taxGenus=" + taxGenus + ", taxSpecies=" + taxSpecies + ", height=" + height + ", weight=" + weight
				+ ", type=" + type + ", healthStatus=" + healthStatus + ", feedingSchedule=" + feedingSchedule + "]";
	}

    @Override 
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (animalID ^ (animalID >>> 32));
        result = prime * result + ((feedingSchedule == null) ? 0 : feedingSchedule.hashCode());
        result = prime * result + ((healthStatus == null) ? 0 : healthStatus.hashCode());
        long temp;
        temp = Double.doubleToLongBits(height);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((taxClass == null) ? 0 : taxClass.hashCode());
        result = prime * result + ((taxFamily == null) ? 0 : taxFamily.hashCode());
        result = prime * result + ((taxGenus == null) ? 0 : taxGenus.hashCode());
        result = prime * result + ((taxKingdom == null) ? 0 : taxKingdom.hashCode());
        result = prime * result + ((taxOrder == null) ? 0 : taxOrder.hashCode());
        result = prime * result + ((taxPhylum == null) ? 0 : taxPhylum.hashCode());
        result = prime * result + ((taxSpecies == null) ? 0 : taxSpecies.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override 
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Animal other = (Animal) obj;
        if (animalID != other.animalID)
            return false;
        if (feedingSchedule == null) {
            if (other.feedingSchedule != null)
                return false;
        } else if (!feedingSchedule.equals(other.feedingSchedule)) {
            System.out.println("ITS THE FEEDING SCHEDULES");
            return false;
        }
        if (healthStatus == null) {
            if (other.healthStatus != null)
                return false;
        } else if (!healthStatus.equals(other.healthStatus))
            return false;
        if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height)) {
            System.out.println("its the height");
            return false;
        }
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (taxClass == null) {
            if (other.taxClass != null)
                return false;
        } else if (!taxClass.equals(other.taxClass))
            return false;
        if (taxFamily == null) {
            if (other.taxFamily != null)
                return false;
        } else if (!taxFamily.equals(other.taxFamily))
            return false;
        if (taxGenus == null) {
            if (other.taxGenus != null)
                return false;
        } else if (!taxGenus.equals(other.taxGenus))
            return false;
        if (taxKingdom == null) {
            if (other.taxKingdom != null)
                return false;
        } else if (!taxKingdom.equals(other.taxKingdom))
            return false;
        if (taxOrder == null) {
            if (other.taxOrder != null)
                return false;
        } else if (!taxOrder.equals(other.taxOrder))
            return false;
        if (taxPhylum == null) {
            if (other.taxPhylum != null)
                return false;
        } else if (!taxPhylum.equals(other.taxPhylum))
            return false;
        if (taxSpecies == null) {
            if (other.taxSpecies != null)
                return false;
        } else if (!taxSpecies.equals(other.taxSpecies))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
            return false;
        return true;
    }
	
	
	
}
