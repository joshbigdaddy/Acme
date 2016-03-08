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

<security:authorize access="hasRole('ADMIN')">
	<a href="warehouse/administrator/create.do"> <spring:message
				code="warehouse.create" />
		</a>
		</security:authorize>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="warehouse" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="warehouse/administrator/edit.do?warehouseId=${row.id}">
				<spring:message	code="warehouse.edit" />
			</a>
		</display:column>
		</security:authorize>

	
	<!-- Attributes -->

	<spring:message code="warehouse.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="warehouse.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" sortable="false" />
	
	
	<display:column><a href="warehouse/showItems.do?warehouseId=${row.id}">
	<spring:message code="warehouse.warehouseItem"/></a></display:column>

</display:table>

