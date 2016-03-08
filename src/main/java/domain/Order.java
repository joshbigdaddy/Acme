package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
@Table(name="OrderTable")
public class Order extends DomainEntity {

	public Order(){
		super();
		comments = new HashSet<String>();
		orderItems = new HashSet<OrderItem>();
	}
	public Order(String ticker){
		super();
		comments = new HashSet<String>();
		orderItems = new HashSet<OrderItem>();
		this.ticker=ticker;
	}
	private String ticker;
	private Date placementMoment;
	private String name;
	private String address;
	private Date deliverMoment;
	private Date cancelMoment;
	private Collection<String> comments;
	private Double sumPrice;
	@Pattern(regexp= "\\d{6}-\\w{4}")
	@Column(unique=true)
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPlacementMoment() {
		return placementMoment;
	}
	public void setPlacementMoment(Date placementMoment) {
		this.placementMoment = placementMoment;
	}
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
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeliverMoment() {
		return deliverMoment;
	}
	public void setDeliverMoment(Date deliverMoment) {
		this.deliverMoment = deliverMoment;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCancelMoment() {
		return cancelMoment;
	}
	public void setCancelMoment(Date cancelMoment) {
		this.cancelMoment = cancelMoment;
	}
	@ElementCollection
	public Collection<String> getComments() {
		return comments;
	}
	public void setComments(Collection<String> comments) {
		this.comments = comments;
	}
	public Double getSumPrice() {
		Double aux=0d;
		if(getOrderItems()==null){
		if(getOrderItems().isEmpty()){
		for(OrderItem o: getOrderItems()){
			aux+=o.getPrice();
		}
		}}
		this.sumPrice=aux;
		return sumPrice;
	}
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	//RELATIONSHIPS
private CreditCard creditCard;
private Collection<OrderItem> orderItems;
private Clerk clerk;
private Consumer consumer;

@NotNull
@Valid
@ManyToOne(optional=true)
public Consumer getConsumer() {
	return consumer;
}
public void setConsumer(Consumer consumer) {
	this.consumer = consumer;
}
@OneToMany
public Collection<OrderItem> getOrderItems() {
	return orderItems;
}
public void setOrderItems(Collection<OrderItem> orderItems) {
	this.orderItems = orderItems;
}

@Valid
@ManyToOne(optional=true)
public Clerk getClerk() {
	return clerk;
}
public void setClerk(Clerk clerk) {
	this.clerk = clerk;
}

@Valid
@ManyToOne(optional=true)
public CreditCard getCreditCard() {
	return creditCard;
}
public void setCreditCard(CreditCard creditCard) {
	this.creditCard = creditCard;
}

	
}

