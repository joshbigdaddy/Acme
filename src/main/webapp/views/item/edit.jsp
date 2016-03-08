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

<form:form action="item/administrator/edit.do" modelAttribute="item">
<jstl:if test="${item.id != 0}">
	<form:hidden path="id" />
	<form:hidden path="version" />
</jstl:if>
	<form:hidden path="comment" />
	<form:label path="sku">
		<spring:message code="item.sku" />:
	</form:label>
	<form:input path="sku" />
	<form:errors cssClass="error" path="sku" />
	<br />

	<form:label path="name">
		<spring:message code="item.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="description">
		<spring:message code="item.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	<form:label path="picture">
	<spring:message code="item.picture" />:
	</form:label>
	<form:input path="picture" />
	<form:errors cssClass="error" path="picture" />
	<br />
	<form:label path="price">
		<spring:message code="item.price" />:
	</form:label>
	<form:input path="price" />
	<form:errors cssClass="error" path="price" />
	<br />

	<form:label path="tag">
	<spring:message code="item.tag" />:
	</form:label>
	<form:input path="tag" />
	<form:errors cssClass="error" path="tag" />
	<br />
	
	<form:label path="unitsSold">
		<spring:message code="item.unitsSold" />:
	</form:label>
	<form:input path="unitsSold" />
	<form:errors cssClass="error" path="unitsSold" />
	<br />
	
	<form:label path="tax">
		<spring:message code="item.tax" />:
	</form:label>
	<form:select id="taxes" path="tax">
		<form:option value="0" label="----" />		
		<form:options items="${taxes}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="tax" />
	<br />
	
	<form:label path="category">
		<spring:message code="item.category" />:
	</form:label>
	<form:select id="categories" path="category">
		<form:option value="0" label="----" />		
		<form:options items="${categories}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="category" />
	<br />
	
	
	
	
	<form:label path="comment">
		<spring:message code="item.comments" />:
	</form:label>
	
<jstl:if test="${item.id == 0}">
		<input type="submit" name="save"
			value="<spring:message code="item.create" />"
			onclick="return confirm('<spring:message code="item.confirm.create" />')" />&nbsp;
	</jstl:if>
<jstl:if test="${item.id != 0}">
	<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="" requestURI="${requestURI}" id="comments">
	<spring:message code="item.comment.userName" var="nameHeader" />
	<display:column property="userName" title="${nameHeader}" sortable="true" />
	<spring:message code="item.comment.title" var="nameHeader" />
	<display:column property="title" title="${nameHeader}" sortable="true" />
	<spring:message code="item.comment.text" var="nameHeader" />
	<display:column property="text" title="${nameHeader}" sortable="true" />
	<spring:message code="item.comment.rating" var="nameHeader" />
	<display:column property="rating" title="${nameHeader}" sortable="true" />
	</display:table>
<input type="submit" name="save"
		value="<spring:message code="item.save" />" />&nbsp; 
		<input type="submit" name="delete"
			value="<spring:message code="item.delete" />"
			onclick="return confirm('<spring:message code="item.confirm.delete" />')" />&nbsp;
</jstl:if>
<a href="item/administrator/list.do">
	<input type="button" name="cancel"
		value="<spring:message code="item.cancel" />"
		onclick="javascript: relativeRedir('item/administrator/list.do');" /></a>
		
	<br />


</form:form>
	
	