<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="order/clerk/assign.do" modelAttribute="order">
<jstl:if test="${order.id != 0}">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker"/>
	<form:hidden path="placementMoment"/>
	<form:hidden path="name"/>
	<form:hidden path="address"/>
	<form:hidden path="deliverMoment"/>
	<form:hidden path="cancelMoment"/>
	<form:hidden path="comments"/>
	<form:hidden path="creditCard"/>
	<form:hidden path="orderItems"/>
	<form:hidden path="clerk"/>
	<form:hidden path="consumer"/>
	
	<spring:message code="order.confirm.assign"  /> 
	<br/>
</jstl:if>
	
<jstl:if test="${order.id != 0}">
<input type="submit" name="save"
		value="<spring:message code="order.assign" />" />
		
</jstl:if>
<a href="order/clerk/list.do">
	<input type="button" name="cancel"
		value="<spring:message code="order.cancel" />"/></a>
		
	<br />


</form:form>