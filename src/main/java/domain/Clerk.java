package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Clerk extends Actor {

	public Clerk(){
		super();
		orders = new HashSet<Order>();
	}
	private Integer deliveredOrders;
	
	public Integer getDeliveredOrders() {
		Integer aux=0;
		for(Order o:getOrders()){
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
	//RELATIONSHIPS
	private Collection<Order> orders;
	@NotNull
	@OneToMany(mappedBy="clerk")
	public Collection<Order> getOrders() {
		return orders;
	}
	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
	
}
