package com.homeAutomation.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@XmlRootElement
@Entity
@Table(name = "component")
public class Component extends AbstractPersistable<Long>{
	@ManyToOne
	@JoinColumn(name = "room_id")
	Room room;
	
	@NotNull
    @Enumerated(EnumType.STRING)
    private ComponentType componentType;

	@NotNull
    @Enumerated(EnumType.STRING)
    private State state;
    
    public Component() {
		super();
	}
    
	public Component(ComponentType componentType, State state) {
		super();
		this.componentType = componentType;
		this.state = state;
	}
	
	@XmlTransient
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
