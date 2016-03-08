package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


@Entity
@Access(AccessType.PROPERTY)
public class Tax extends DomainEntity{
	
	public Tax(){
		super();
	}
	
	private String name;
	private Integer percent;
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Range(min = 0,max = 100)
	public Integer getPercent() {
		return percent;
	}
	public void setPercent(Integer percent) {
		this.percent = percent;
	}

}
