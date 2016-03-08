package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


@Entity
@Access(AccessType.PROPERTY)
public class Plaba extends DomainEntity{
	
	public Plaba(){
		super();
		
	}
	//Clase modelo para el control check
//en este caso va a ser una relación onetoMany- ManyToOne con Item
	
	//los atributos serán dos: Name y Number
private String code;
private String title;
private String description;
private Double ammount;
private Date validDate;
private boolean used;

public String getcode(){
	return code;
}
@Pattern(regexp= "[A-Z][A-Z]-\\d{5}")
@Column(unique=true)
public void setCode(String code){
	this.code=code;
}

@Size(min = 1, max = 20)
public String getTitle(){
	return title;
}
public void setTitle(String title){
	this.title=title;
}

@Size(min = 1, max = 100)
public String getDescription(){
	return description;
}
public void setDescription(String description){
	this.description=description;
}
@Range(min = 1,max = 100)
public Double getAmmount(){
	return ammount;
}
public void setAmmount(Double ammount){
	this.ammount=ammount;
}
@Temporal(TemporalType.TIMESTAMP)
public Date getValidDate(){
	return validDate;
}
public void setValidDate(Date d){
	this.validDate=d;
}
@NotNull
public boolean getUsed(){
	return used;
}
public void setUsed(boolean b){
	this.used=b;
}

//RelationShips
	private Consumer consumer;
	
	@Valid
	@ManyToOne(optional=true)//si el optional=false significa que no puede ser 0
    public Consumer getConsumer() {
		return consumer;
	}
	
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
		}
}
