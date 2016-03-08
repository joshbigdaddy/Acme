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

<security:authorize access="hasRole('CONSUMER')">
	
	<jstl:choose>
				<jstl:when test="${consumer.shoppingCart.isNull()}">
				<spring:message code="customer.shoppingCartEmpty"/>
				
				</jstl:when>
				<jstl:otherwise>
				
<display:table name="items" id="items" requestURI="${requestURI}" pagesize="5" class="displaytag" >
<spring:message code="item.edit" var="edit"/> 
<display:column title="${edit}" sortable="false">
			<a href="shoppingcartitem/consumer/editItem.do?itemId=${items.id}">
				<spring:message	code="item.edit" />
			</a>
</display:column>	

<spring:message code="consumer.quantity" var="quantity"/> 
<display:column property="quantity" title="${quantity}" sortable="true"></display:column>

<spring:message code="consumer.name" var="name"/> 
<display:column property="name" title="${name}" sortable="true"></display:column>

<spring:message code="consumer.price" var="price"/> 
<display:column property="price" title="${price}" sortable="true"></display:column>

<spring:message code="consumer.tag" var="tag"/> 
<display:column property="tag" title="${tag}" sortable="true"></display:column>

</display:table>
<display:table name="comments" id="comments" requestURI="${requestURI}" pagesize="5" class="displaytag" >
<spring:message code="item.edit" var="edit"/> 
<display:column title="${edit}" sortable="false">
			<a href="shoppingcartcomment/consumer/editComment.do?commentId=${comments.id}">
				<spring:message	code="item.edit" />
			</a>
</display:column>
<spring:message code="consumer.comments" var="comment"/> 
<display:column property="comment" title="${comment}" sortable="true"></display:column>
</display:table>
<div>
	<a href="shoppingcartcomment/consumer/create.do"> <spring:message
				code="comment.create" />
		</a>
	<br/>
	<a href="order/consumer/create.do?shoppingCartId=${shoppingcart.id}"> <spring:message
				code="order.sc.create" />
		</a>
	<br/>
</div>

</jstl:otherwise>
			</jstl:choose>
			
	</security:authorize>
