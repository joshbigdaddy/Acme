<%--
 * action-1.jsp
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

<form:form action="clerk/administrator/create.do" modelAttribute="clerk">
    <form:hidden path="userAccount.authorities"/>
	<form:label path="userAccount.username">
		<spring:message code="userAccount.username" />:
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors cssClass="error" path="userAccount.username" />
	<br />

	
	<form:label path="userAccount.password">
		<spring:message code="userAccount.password" />:
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors cssClass="error" path="userAccount.password" />
	<br />

	<form:label path="name">
		<spring:message code="userAccount.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	
	
<form:label path="surname">
		<spring:message code="userAccount.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="email">
		<spring:message code="userAccount.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="phone">
		<spring:message code="userAccount.phone" />:
	</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />
	
		<input type="submit" name="save"
			value="<spring:message code="userAccount.create" />"
			onclick="return confirm('<spring:message code="userAccount.confirm.create" />')" />&nbsp;
	
	<a href="index.do">
	<input type="button" name="cancel"
		value="<spring:message code="category.cancel" />"
		onclick="javascript: relativeRedir('Acme-Supermarket');" /></a>
	
	

</form:form>
	
	