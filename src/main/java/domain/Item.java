package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.AccessType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Item extends DomainEntity{

	
	public Item(){
		super();
		deleted=false;
	}
	
	
	
	private String sku;
	private String name;
	private String description;
	private Double price;
	private String picture;
	private String tag;

	private Integer unitsSold;
	private boolean deleted;
	
	@Pattern(regexp = "\\w{2}-\\w{4}")
	@Column(unique=true)
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Min(0)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@URL
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@NotNull
	@Valid
	public Integer getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(Integer unitsSold) {
		this.unitsSold = unitsSold;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	//RELATIONSHIPS

	private Category category;
	private Tax tax;
	//Recuerda borrar de aquí esta clase por si las flies
	
	private Collection<Comment> comment;
	//AQUI EMPIEZA
	
	//AQUI TERMINA
	@Valid
	@ElementCollection
	@OneToMany(mappedBy="item") 
	public Collection<Comment> getComment() {
		return comment;
	}


	public void setComment(Collection<Comment> comment) {
		this.comment = comment;
	}


	@Valid
	@NotNull
	@ManyToOne(optional=true)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional=true)
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
}
