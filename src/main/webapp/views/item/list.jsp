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
    <form action="item/search.do" method="post">
    <spring:message	code="item.search" />:<input type="text" name="searchText" /><br/>
      <input type="reset"/>
      <input type="submit" name="search"/>
    </form>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="items" requestURI="${requestURI}" id="row">
	<display:column >
			<img src="${row.picture}" class="itemPicture"/>
	</display:column>
	<!-- Action links -->
		<!-- Attributes -->
	<security:authorize access="hasRole('ADMIN')">
	
		<spring:message code="item.sku" var="skuHeader" />
	<display:column property="sku" title="${skuHeader}" sortable="true" />
		</security:authorize>

	<spring:message code="item.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="item.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="item.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" />
		<security:authorize access="hasRole('ADMIN')">
		<display:column >
			<a href="item/administrator/edit.do?itemId=${row.id}">
				<spring:message	code="item.edit" />
			</a>
		</display:column>
		</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="item.status" var="statusHeader" />
	<display:column title="${statusHeader}">
	<jstl:if test="${row.deleted == true}">
	<spring:message code="item.status.deleted" />
	</jstl:if>
	<jstl:if test="${row.deleted == false}">
	<spring:message code="item.status.notDeleted" />
	</jstl:if>
	</display:column>
	</security:authorize>
			<security:authorize access="hasRole('CONSUMER')">
		<display:column>
			<a href="shoppingcart/consumer/addItem.do?itemId=${row.id}">
				<spring:message	code="item.add" />
			</a>
		</display:column>		
	</security:authorize>
	

</display:table>

<security:authorize access="hasRole('ADMIN')">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="deletedItems" requestURI="${requestURI}" id="rowD">
	
	<!-- Action links -->
		<!-- Attributes -->
	<security:authorize access="hasRole('ADMIN')">
	
		<spring:message code="item.sku" var="skuHeader" />
	<display:column property="sku" title="${skuHeader}" sortable="true" />
		</security:authorize>

	<spring:message code="item.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="item.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="item.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" />
	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="item.status" var="statusHeader" />
	<display:column title="${statusHeader}">
	<jstl:if test="${rowD.deleted == true}">
	<spring:message code="item.status.deleted" />
	</jstl:if>
	<jstl:if test="${rowD.deleted == false}">
	<spring:message code="item.status.notDeleted" />
	</jstl:if>
	</display:column>
	</security:authorize>
</display:table>
</security:authorize>





<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="item/administrator/create.do"> <spring:message
				code="item.create" />
		</a>
	</div>
</security:authorize>
