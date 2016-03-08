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

<form:form action="master-page/register.do" modelAttribute="consumer">


	<form:label path="name">
		<spring:message code="consumer.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="consumer.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="email">
		<spring:message code="consumer.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="phone">
		<spring:message code="consumer.phone" />:
	</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />


	<input type="submit" name="save"
		value="<spring:message code="register.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="register.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />

</form:form>
