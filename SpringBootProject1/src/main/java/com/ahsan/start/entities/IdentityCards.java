package com.ahsan.start.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="identificationcards")
public class IdentityCards {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generate ID
	    @Column(name = "IdentityCardID") // Column name in the database
	    private long id;

	    @Column(name = "IdentityCardType", nullable = false) // Mark as not nullable if required
	    private String identityType;
	    
	    @ManyToOne
	    @JoinColumn(name = "CitizenID", nullable = false)
	    private CitizenClass citizenClass;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getIdentityType() {
			return identityType;
		}

		public void setIdentityType(String identityType) {
			this.identityType = identityType;
		}
		
		

		public CitizenClass getCitizenClass() {
			return citizenClass;
		}

		public void setCitizenClass(CitizenClass citizenClass) {
			this.citizenClass = citizenClass;
		}

		@Override
		public String toString() {
			return "IdentityCards [id=" + id + ", identityType=" + identityType + "]";
		}
		
	    
	    

}
