package com.homeAutomation.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@XmlRootElement
@Entity
@Table(name = "user")
public class User extends AbstractPersistable<Long> {
	@NotNull
	@Length(max=50)
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotNull
	@Length(max=50)
	@Column(name = "LAST_NAME")
	private String lastName;

    @NotNull
    @Email
	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@NotNull
	@Valid
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval=true)
	private Set<Home> homes = new HashSet<Home>();

	@Column(name = "DATE_CREATED", columnDefinition = "DATETIME", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated = new Date();

	@Column(name = "DATE_UPDATED", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated = new Date();

	public User() {
	}

	public User(String firstName, String lastName, String emailAddress, Set<Home> homes) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.homes = homes;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<Home> getHomes() {
		return homes;
	}

	public void setHomes(Set<Home> homes) {
		for (Home hm : homes) {
			addHomes(hm);
		}
	}

	public boolean addHomes(Home home) {
		home.setUser(this);
		return this.getHomes().add(home);
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}
