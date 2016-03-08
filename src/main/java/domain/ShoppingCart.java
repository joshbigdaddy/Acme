package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public class ShoppingCart extends DomainEntity{
	
	public ShoppingCart(){
		super();
		comment = new HashSet<ShoppingCartComment>();
		item = new HashSet<ShoppingCartItem>();
}



//RELATIONSHIPS



private Collection<ShoppingCartItem> item;
private Collection<ShoppingCartComment> comment;

@Valid
@NotNull
@OneToMany
public Collection<ShoppingCartComment> getComment() {
	return comment;
}

public void setComment(Collection<ShoppingCartComment> comment) {
	this.comment = comment;
}
@Valid
@NotNull
@OneToMany
public Collection<ShoppingCartItem> getItem() {
	return item;
}

public void setItem(Collection<ShoppingCartItem> item) {
	this.item = item;
}

}
