package com.homeAutomation.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@XmlRootElement(namespace = "com.homeAutomation.model.User")
@Entity
@Table(name = "home")
public class Home extends AbstractPersistable<Long> {
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	User user;

	@NotNull
	@Valid
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "home", fetch = FetchType.EAGER)
	private Set<Room> rooms = new HashSet<Room>();

	public Home() {
	}

	public Home(Long id) {
		this.setId(id);
	}

	public Home(Set<Room> rooms) {
		super();
		this.rooms = rooms;
	}

	public void setHomeId(Long homeId) {
		this.setId(homeId);
	}

	@XmlTransient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		for (Room rm : rooms) {
			addRooms(rm);
		}
	}

	public boolean addRooms(Room room) {
		room.setHome(this);
		return this.getRooms().add(room);
	}

}
