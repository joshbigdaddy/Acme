package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.AccessType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity{

	
	public Actor(){
		super();
		systemFolder = new HashSet<SystemFolder>();
	}
	
	
	private String name;
	private String surname;
	private String email;
	private String phone;
	
	
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
    
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	//RELATIONSHIP
	
	private Collection<SystemFolder> systemFolder;
	private UserAccount userAccount;
	
	@NotNull
	@Valid
	@OneToMany
	public Collection<SystemFolder> getSystemFolder() {
		return systemFolder;
	}

	public void setSystemFolder(Collection<SystemFolder> systemFolder) {
		this.systemFolder = systemFolder;
	}
	
	@NotNull
	@Valid
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	
	
	
	
}
