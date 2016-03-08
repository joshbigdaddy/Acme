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

<form:form action="warehouse/administrator/edit.do" modelAttribute="warehouse">
<jstl:if test="${warehouse.id != 0}">
	<form:hidden path="id" />
	<form:hidden path="version" />
</jstl:if>
	<form:label path="name">
		<spring:message code="warehouse.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br/>

	<form:label path="address">
		<spring:message code="warehouse.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br/>


<jstl:if test="${warehouse.id == 0}">
		<input type="submit" name="save"
			value="<spring:message code="warehouse.create" />"
			onclick="return confirm('<spring:message code="warehouse.confirm.create" />')" />&nbsp;
	</jstl:if>
<jstl:if test="${warehouse.id != 0}">
<input type="submit" name="save"
		value="<spring:message code="warehouse.save" />" />&nbsp; 
		<input type="submit" name="delete"
			value="<spring:message code="warehouse.delete" />"
			onclick="return confirm('<spring:message code="warehouse.confirm.delete" />')" />&nbsp;
</jstl:if><a href="warehouse/list.do">
	<input type="button" name="cancel"
		value="<spring:message code="warehouse.cancel" />"
		onclick="javascript: relativeRedir('warehouse/list.do');" /></a>
	<br />


</form:form>
	
	