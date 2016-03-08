<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
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


<p><spring:message code="welcome.greeting.prefix" /> ${name}<spring:message code="welcome.greeting.suffix" /></p>

<br/>
<security:authorize access="hasRole('CONSUMER')">
<spring:message code="bestSellingItem"/>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="bestSellingItems" requestURI="${requestURI}" id="row">
	<display:column >
			<img src="${row.picture}" class="itemPicture"/>
	</display:column>

	<spring:message code="item.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="item.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="item.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" />
	
			<security:authorize access="hasRole('CONSUMER')">
		<display:column>
			<a href="shoppingcart/consumer/addItem.do?itemId=${row.id}">
				<spring:message	code="item.add" />
			</a>
		</display:column>		
	</security:authorize>
	</display:table>
</security:authorize>
<br/>
<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 


