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
	name="plaba" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="plaba/administrator/edit.do?plabaId=${row.id}">
				<spring:message	code="plaba.edit" />
			</a>
		</display:column>		
	</security:authorize>


	
	<!-- Attributes -->
	
	<spring:message code="plaba.code" var="nameHeader" />
	<display:column property="code" title="${nameHeader}" sortable="true" />

	<spring:message code="plaba.title" var="numberHeader" />
	<display:column property="title" title="${numberHeader}" sortable="false"  />
	
	<spring:message code="plaba.ammount" var="numberHeader" />
	<display:column property="ammount" title="${numberHeader}" sortable="false"  />

</display:table>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="plaba/administrator/create.do"> <spring:message
				code="plaba.create" />
		</a>
	</div>
</security:authorize>
