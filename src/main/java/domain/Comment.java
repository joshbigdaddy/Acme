package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	public Comment(){
		super();
	}
private String userName;
private String title;
private String text;
private Integer rating;

@NotBlank
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
@NotBlank
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
@NotBlank
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
@Range(min=0, max=5)
public Integer getRating() {
	return rating;
}
public void setRating(Integer rating) {
	this.rating = rating;
}


	//RELATIONSHIPS

private Item item;
private Consumer consumer;
@Valid
@NotNull
@ManyToOne(optional=true)
public Item getItem() {
	return item;
}
public void setItem(Item item) {
	this.item = item;
}
@Valid
@NotNull
@ManyToOne(optional=true)
public Consumer getConsumer() {
	return consumer;
}
public void setConsumer(Consumer consumer) {
	this.consumer = consumer;
}


}
