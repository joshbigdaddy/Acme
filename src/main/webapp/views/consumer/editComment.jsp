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

<form:form action="shoppingcartcomment/consumer/editComment.do" modelAttribute="shoppingCartComment">

	<jstl:if test="${shoppingCartComment.id != 0}">
	<form:hidden path="id" />
	<form:hidden path="version" />
	</jstl:if>

	<form:label path="comment">
		<spring:message code="sc.comment" />:
	</form:label>
	<form:input path="comment" />
	<form:errors cssClass="error" path="comment" />
	<br />
	<input type="submit" name="save"
		value="<spring:message code="item.save" />" />&nbsp; 
		<jstl:if test="${shoppingCartComment.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="item.delete" />"
			onclick="return confirm('<spring:message code="comment.confirm.delete" />')" />&nbsp;
		</jstl:if>
	<a href="shoppingcart/consumer/shoppingcart.do">
	<input type="button" name="cancel"
		value="<spring:message code="item.cancel" />"
		onclick="javascript: relativeRedir('shoppingcart/consumer/shoppingcart.do');" /></a>
	<br />
</form:form>
