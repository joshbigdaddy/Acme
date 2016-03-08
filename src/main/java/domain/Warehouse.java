package domain;

import java.util.Collection;
import java.util.HashSet;


import javax.persistence.Access;
import javax.persistence.AccessType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
public class Warehouse extends DomainEntity{

	public Warehouse(){
		super();
		items = new HashSet<Item>();
		stores = new HashSet<Stores>();
	}
private String name;
private String address;
@NotBlank
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@NotBlank
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

//RELATIONS
private Collection<Item> items;
private Collection<Stores> stores;

@NotNull
@OneToMany
public Collection<Item> getItems() {
	return items;
}
public void setItems(Collection<Item> items) {
	this.items = items;
}
@Valid
@OneToMany(mappedBy="warehouse")
public Collection<Stores> getStores() {
	return stores;
}
public void setStores(Collection<Stores> stores) {
	this.stores = stores;
}

}
