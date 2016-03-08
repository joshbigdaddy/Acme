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

<form:form action="tax/administrator/edit.do" modelAttribute="tax">
<jstl:if test="${tax.id != 0}">
	<form:hidden path="id" />
	<form:hidden path="version" />
</jstl:if>
	
	<form:label path="name">
		<spring:message code="tax.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="percent">
		<spring:message code="tax.percent" />:
	</form:label>
	<form:input path="percent" />
	<form:errors cssClass="error" path="percent" />
	<br />
	
	
	<jstl:if test="${tax.id == 0}">
		<input type="submit" name="save"
			value="<spring:message code="tax.create" />"
			onclick="return confirm('<spring:message code="tax.confirm.create" />')" />&nbsp;
	</jstl:if>
<jstl:if test="${tax.id != 0}">
<input type="submit" name="save"
		value="<spring:message code="tax.save" />" />&nbsp; 
		<input type="submit" name="delete"
			value="<spring:message code="tax.delete" />"
			onclick="return confirm('<spring:message code="tax.confirm.delete" />')" />&nbsp;
</jstl:if>
<a href="tax/administrator/list.do">
	<input type="button" name="cancel"
		value="<spring:message code="tax.cancel" />"
		onclick="javascript: relativeRedir('tax/list.do');" /></a>
	<br />


</form:form>
	
	