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
<%--

 --%>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="items" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
		<!-- Attributes -->
	
	
		<spring:message code="item.sku" var="skuHeader" />
	<display:column property="sku" title="${skuHeader}" sortable="true" />


	<spring:message code="item.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="item.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="item.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" />
	
	<spring:message code="item.quantity" var="quantityHeader" />
	<display:column property="quantity" title="${quantityHeader}" sortable="true" />

</display:table>
