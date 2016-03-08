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
public class SystemFolder extends DomainEntity{
	
	public SystemFolder(){
		super();
		message = new HashSet<Message>();
	}

	private String name;

	@NotBlank	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//RELATIONSHIP
	
	private Collection<Message> message;

	@Valid
	@NotNull
	@OneToMany(mappedBy="systemFolder")
	public Collection<Message> getMessage() {
		return message;
	}

	public void setMessage(Collection<Message> message) {
		this.message = message;
	}


}
