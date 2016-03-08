package domain;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class ShoppingCartComment extends DomainEntity{
	private String comment;
	public ShoppingCartComment(){
		super();	
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
