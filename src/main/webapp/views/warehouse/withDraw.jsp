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

<form:form action="stores/clerk/withDraw.do" modelAttribute="stores">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="item" />
    <form:hidden path="warehouse" value="${warehouse}"/>
	<form:label path="item"/>
	

	<br/>	
	<spring:message code="warehouse.stores.withDraw" />
	<form:input path="units" />
	<form:errors cssClass="error" path="units" />
	<br/>


<input type="submit" name="save"
		value="<spring:message code="warehouse.save" />" />&nbsp; 
		<a href="warehouse/list.do">
	<input type="button" name="cancel"
		value="<spring:message code="warehouse.cancel" />"
		onclick="javascript: relativeRedir('warehouse/list.do');" /></a>
	<br />


</form:form>
	
	