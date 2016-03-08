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

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="categories" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="category/administrator/edit.do?categoryId=${row.id}">
				<spring:message	code="category.edit" />
			</a>
		</display:column>		
	</security:authorize>


	
	<!-- Attributes -->
	
	<spring:message code="category.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="category.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false"  />

</display:table>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="category/administrator/create.do"> <spring:message
				code="category.create" />
		</a>
	</div>
</security:authorize>
