package models;

import java.time.LocalDate;
import java.util.Objects;

public class Agency {
    private final int id;
    private String agency;
    private String type;
    private String district;
    private double debt;
    private long phoneNumber;
    private LocalDate AcceptanceDate;
    private String Address;
    private boolean status;
    private String email;

    public Agency(int id, String agency, String type, String district,long phoneNumber, LocalDate ad, String addrress, String email) {
        this.id = id;
        this.agency = agency;
        this.type = type;
        this.district = district;
        this.debt = 0.0;
        this.phoneNumber = phoneNumber;
        this.AcceptanceDate = ad;
        this.Address = addrress;
        this.email = email;
        status = true;
    }

    

    public int getId() {
        return id;
    }

    public String getAgency() {
        return agency;
    }

    public String getType() {
        return type;
    }

    public String getDistrict() {
        return district;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }
    
    public long getPhoneNumber() {
		return phoneNumber;
	}
    public LocalDate getAcceptanceDate() {
		return AcceptanceDate;
	}
    public String getAddress() {
		return Address;
	}
    public String getEmail() {
        return email;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agent = (Agency) o;
        return getId() == agent.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

