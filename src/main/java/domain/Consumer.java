package domain;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.AccessType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public class Consumer extends Actor {

	public Consumer(){
		super();
		order = new HashSet<Order>();
		comment = new HashSet<Comment>();
	}
	
	
	//RELATIONSHIPS
	
	private ShoppingCart shoppingCart;
	private Collection<Order> order;
	private Collection<Comment> comment;
	private List<Plaba> plaba;
	private Customization customization;
	private Double sumPrice;
	private Integer cancelledOrders;
	private Integer deliveredOrders;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="consumer")
	public List<Plaba> getPlaba() {
		return plaba;
	}


	public void setPlaba(List<Plaba> plaba) {
		this.plaba = plaba;
	}
	
	
	
	@Valid
	@OneToOne(optional=true)
	public Customization getCustomization() {
		return customization;
	}


	public void setCustomization(Customization customization) {
		this.customization = customization;
	}


	
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="consumer")
	public Collection<Comment> getComment() {
		return comment;
	}


	public void setComment(Collection<Comment> comment) {
		this.comment = comment;
	}


	@Valid
	@NotNull
	@OneToOne(optional=false)
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}


	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="consumer")
	public Collection<Order> getOrder() {
		return order;
	}


	public void setOrder(Collection<Order> order) {
		this.order = order;
	}

	public Double getSumPrice() {
		Double aux=0d;
		for(Order o : getOrder()){
			aux+=o.getSumPrice();
		}
		
		sumPrice=aux;
		return sumPrice;
	}


	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}


	public Integer getCancelledOrders() {
		Integer aux=0;
		for(Order o : getOrder()){
			if(o.getCancelMoment()!=null){
				aux++;
			}
		}
		cancelledOrders=aux;
		return cancelledOrders;
	}


	public void setCancelledOrders(Integer cancelledOrders) {
		this.cancelledOrders = cancelledOrders;
	}


	public Integer getDeliveredOrders() {
		Integer aux=0;
		for(Order o : getOrder()){
			if(o.getDeliverMoment()!=null){
				aux++;
			}
		}
		deliveredOrders=aux;
		return deliveredOrders;
	}


	public void setDeliveredOrders(Integer deliveredOrders) {
		this.deliveredOrders = deliveredOrders;
	}
	
}
