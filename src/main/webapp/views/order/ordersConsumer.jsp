<%--
 * action-2.jsp
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
	name="orders" requestURI="${requestURI}" id="row">
	
    <!-- Attributes -->
	<spring:message code="order.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}" sortable="true" />
	
	<spring:message code="order.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="order.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" sortable="true" />
	
	<spring:message code="order.placementMoment" var="placementMomentHeader" />
	<display:column property="placementMoment" title="${placementMomentHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	<spring:message code="order.deliverMoment" var="deliverMomentHeader" />
	<display:column property="deliverMoment" title="${deliverMomentHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />

	<spring:message code="order.cancelMoment" var="cancelMomentHeader" />
	<display:column property="cancelMoment" title="${cancelMomentHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />


<security:authorize access="hasRole('CONSUMER')">
		<display:column> <jstl:if test="${row.clerk == null}">
			<a href="order/consumer/cancel.do?orderId=${row.id}">
				<spring:message	code="consumer.cancel" />
			</a>
			</jstl:if>
			<jstl:if test="${row.clerk != null}">
			${row.clerk.name}
			</jstl:if>
			
		</display:column>
		</security:authorize>
<security:authorize access="hasRole('CONSUMER')">
		<display:column> 
			<a href="order/consumer/plaba.do?orderId=${row.id}">
				<spring:message	code="clerk.assign" />
			</a>
			</display:column>
		</security:authorize>
</display:table>
