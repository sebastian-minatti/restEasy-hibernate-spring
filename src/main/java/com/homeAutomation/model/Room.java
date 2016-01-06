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
@XmlRootElement
@Entity
@Table(name = "room")
public class Room extends AbstractPersistable<Long>{	
	@ManyToOne
	@JoinColumn(name = "HOME_ID")
	Home home;
	
	@NotNull
	@Valid
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "room", fetch = FetchType.EAGER)
	private Set<Component> components = new HashSet<Component>();

	public Room() {
		super();
	}

	public Room(Set<Component> components) {
		super();
		this.components = components;
	}

	@XmlTransient
	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	public Set<Component> getComponents() {
		return components;
	}

	public void setComponents(Set<Component> components) {
		for (Component cp : components) {
			addComponents(cp);
		}
	}
	
	public boolean addComponents(Component component) {
		component.setRoom(this);
		return this.getComponents().add(component);
	}
}
