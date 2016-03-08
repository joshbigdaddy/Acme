<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<form:form action="order/consumer/create.do" modelAttribute="order">

	<jstl:if test="${order.id != 0}">
	<form:hidden path="id" />
	<form:hidden path="version" />
	</jstl:if>
	<form:hidden path="ticker" />
	<form:hidden path="placementMoment" />
	<form:hidden path="deliverMoment" />
	<form:hidden path="cancelMoment" />
	<form:hidden path="comments" />
	<form:hidden path="orderItems" />
	<form:hidden path="clerk" />
	<form:hidden path="consumer" />
	
	

	<form:label path="name">
		<spring:message code="order.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	<form:label path="address">
		<spring:message code="order.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />

	<form:label path="creditCard.brandName">
		<spring:message code="order.creditCard.bn" />:
	</form:label>
	<form:input path="creditCard.brandName" />
	<form:errors cssClass="error" path="creditCard.brandName" />
	<br />
	<form:label path="creditCard.number">
		<spring:message code="order.creditCard.number" />:
	</form:label>
	<form:input path="creditCard.number" />
	<form:errors cssClass="error" path="creditCard.number" />
	<br />
	<form:label path="creditCard.holderName">
		<spring:message code="order.creditCard.hn" />:
	</form:label>
	<form:input path="creditCard.holderName" />
	<form:errors cssClass="error" path="creditCard.holderName" />
	<br />
	<form:label path="creditCard.expeditionMonth">
		<spring:message code="order.creditCard.em" />:
	</form:label>
	<form:input path="creditCard.expeditionMonth" />
	<form:errors cssClass="error" path="creditCard.expeditionMonth" />
	<br />
	<form:label path="creditCard.expeditionYear">
		<spring:message code="order.creditCard.ey" />:
	</form:label>
	<form:input path="creditCard.expeditionYear" />
	<form:errors cssClass="error" path="creditCard.expeditionYear" />
	<br />
	<form:label path="creditCard.cvvCode">
		<spring:message code="order.creditCard.cvv" />:
	</form:label>
	<form:input path="creditCard.cvvCode" />
	<form:errors cssClass="error" path="creditCard.cvvCode" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="order.save" />" />&nbsp; 
	<a href="shoppingcart/consumer/shoppingcart.do">
	<input type="button" name="cancel"
		value="<spring:message code="order.cancel" />"
		onclick="javascript: relativeRedir('shoppingcart/consumer/shoppingcart.do');" /></a>
	<br />
</form:form>
<!-- Action links -->



			


