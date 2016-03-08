<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="consumers" requestURI="${requestURI}" id="row">
	
<!-- Attributes -->
	
	<spring:message code="consumer.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="consumer.surname" var="descriptionHeader" />
	<display:column property="surname" title="${descriptionHeader}" sortable="true"  />
	
	<spring:message code="consumer.email" var="descriptionHeader" />
	<display:column property="email" title="${descriptionHeader}" sortable="true"  />
	
	

</display:table>

