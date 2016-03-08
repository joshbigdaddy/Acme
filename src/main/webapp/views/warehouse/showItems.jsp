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
	
<a href="stores/administrator/create.do?warehouseId=${warehouse}"> <spring:message
				code="warehouse.create" />
		</a>
		</security:authorize>
<br/>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="stores" requestURI="${requestURI}" id="row">
		
		<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="stores/administrator/edit.do?storesId=${row.id}">
				<spring:message	code="warehouse.edit" />
			</a>
		</display:column>
		</security:authorize>
		<security:authorize access="hasRole('CLERK')">
<display:column>
<a href="stores/clerk/withDraw.do?storesId=${row.id}">
<spring:message code="warehouse.withDraw" />
</a>
</display:column>
</security:authorize>
	<!-- Attributes -->

	<spring:message code="warehouse.stores.name" var="itemNameHeader" />
	<display:column property="item.name" title="${itemNameHeader}" sortable="true" />
	
	<spring:message code="warehouse.stores.units" var="unitsHeader" />
	<display:column property="units" title="${unitsHeader}" sortable="true" />
	
	
</display:table>

	