
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="taxes" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="tax/administrator/edit.do?taxId=${row.id}">
				<spring:message	code="tax.edit" />
			</a>
		</display:column>
		</security:authorize>

<!-- Attributes -->

	<spring:message code="tax.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="tax.percent" var="percentHeader" />
	<display:column property="percent" title="${percentHeader}" sortable="true" />
	
	</display:table>


<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="tax/administrator/create.do"> <spring:message
				code="tax.create" />
		</a>
	</div>
</security:authorize>