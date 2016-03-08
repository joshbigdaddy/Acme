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




<display:table pagesize="5" class="displaytag" keepStatus="true" name="bestSellingItem" requestURI="${requestURI}" id="bestSellingItem">
<spring:message code="administrator.bestSellingItem" var="name" />

<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="worstSellingItem" requestURI="${requestURI}" id="bestSellingItem">
<spring:message code="administrator.worstSellingItem" var="name" />
<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="moreOrders" requestURI="${requestURI}" id="moreOrders">
<spring:message code="administrator.moreOrders" var="name" />
<display:column property="name" title="${name}" sortable="true" />
</display:table>


<display:table pagesize="5" class="displaytag" keepStatus="true" name="morePrice" requestURI="${requestURI}" id="morePrice">
<spring:message code="administrator.morePrice" var="name" />
<display:column property="name" title="${name}" sortable="true" />
</display:table>


<display:table pagesize="5" class="displaytag" keepStatus="true" name="clerkMoreOrders" requestURI="${requestURI}" id="clerkMoreOrders">
<spring:message code="administrator.clerkMoreOrders" var="name" />
<display:column property="name" title="${name}" sortable="true" />
</display:table>


<display:table pagesize="5" class="displaytag" keepStatus="true" name="clerkLessOrders" requestURI="${requestURI}" id="clerkLessOrders">
<spring:message code="administrator.clerkLessOrders" var="name" />
<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="consumerMoreCancelled" requestURI="${requestURI}" id="consumerMoreCancelled">
<spring:message code="administrator.consumerMoreCancelled" var="name" />
<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="consumerLessCancelled" requestURI="${requestURI}" id="consumerLessCancelled">
<spring:message code="administrator.consumerLessCancelled" var="name" />
<display:column property="name" title="${name}" sortable="true" />
</display:table>


<p><spring:message code="administrator.ordersCancelled"/> ${ordersCancelled}</p><br>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="itemsMoreComments" requestURI="${requestURI}" id="itemsMoreComments">
<spring:message code="administrator.itemsMoreComments" var="name" />
<display:column property="name" title="${name}" sortable="true" />
</display:table>

