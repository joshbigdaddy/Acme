package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity{
	public CreditCard(){
		super();
	}
private String brandName;
private String holderName;
private String number;
private Integer expeditionMonth;
private Integer expeditionYear;
private Integer cvvCode;

@NotBlank
public String getBrandName() {
	return brandName;
}
public void setBrandName(String brandName) {
	this.brandName = brandName;
}
@NotBlank
public String getHolderName() {
	return holderName;
}
public void setHolderName(String holderName) {
	this.holderName = holderName;
}
@CreditCardNumber
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
@Range(min=1,max=12)
@NotNull
public Integer getExpeditionMonth() {
	return expeditionMonth;
}
public void setExpeditionMonth(Integer expeditionMonth) {
	this.expeditionMonth = expeditionMonth;
}
@NotNull
public Integer getExpeditionYear() {
	return expeditionYear;
}
public void setExpeditionYear(Integer expeditionYear) {
	this.expeditionYear = expeditionYear;
}
@Range(min=100,max=999)
@NotNull
public Integer getCvvCode() {
	return cvvCode;
}
public void setCvvCode(Integer cvvCode) {
	this.cvvCode = cvvCode;
}
}
