<%--
 * edit.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="plaba/administrator/edit.do" modelAttribute="plaba">

	
	<jstl:if test="${plaba.id != 0}">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	</jstl:if>
	
	<form:label path="code">
		<spring:message code="plaba.code" />:
	</form:label>
	<form:input path="code" />
	<form:errors cssClass="error" path="code" />
	<br />

	
	

	<form:label path="title">
		<spring:message code="plaba.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
		<form:label path="description">
		<spring:message code="plaba.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	<form:label path="ammount">
		<spring:message code="plaba.ammount" />:
	</form:label>
	<form:input path="ammount" />
	<form:errors cssClass="error" path="ammount" />
	<br />
	<form:label path="ammount">
		<spring:message code="plaba.ammount" />:
	</form:label>
	<form:input path="ammount" />
	<form:errors cssClass="error" path="ammount" />
	<br />
	
	<form:label path="validDate">
		<spring:message code="plaba.validDate" />:
	</form:label>
	<form:input path="validDate" />
	<form:errors cssClass="error" path="validDate" />
	<br />

	<form:label path="Consumer">
		<spring:message code="plaba.consumer" />:
	</form:label>
	<form:select id="consumer" path="consumer">
		<form:option value="0" label="----" />		
		<form:options items="${consumers}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="consumer" />
	<br />
	
	<jstl:if test="${plaba.id== 0}">
		<input type="submit" name="save"
			value="<spring:message code="plaba.create" />"
			onclick="return confirm('<spring:message code="plaba.confirm.create" />')" />&nbsp;
	
	</jstl:if>
	<jstl:if test="${plaba.id != 0}">
	<input type="submit" name="save"
		value="<spring:message code="plaba.save" />" />&nbsp; 
		<input type="submit" name="delete"
			value="<spring:message code="plaba.delete" />"
			onclick="return confirm('<spring:message code="plaba.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<a href="plaba/administrator/list.do">
	<input type="button" name="cancel"
		value="<spring:message code="plaba.cancel" />"
		onclick="javascript: relativeRedir('plaba/administrator/list.do');" /></a>
	<br />

	

</form:form>
