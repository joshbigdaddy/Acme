<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
<a href="/Acme-Supermarket/welcome/index.do">
	<img src="images/logo.png" alt="Acme-Supermarket, Inc." class="logo"/></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="clerk/administrator/create.do"><spring:message code="master.page.clerk" /></a></li>
					<li><a href="tax/administrator/list.do"><spring:message code="master.page.administrator.tax" /></a></li>
					<li><a href="consumer/administrator/list.do"><spring:message code="master.page.administrator.consumer" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="category/administrator/list.do"><spring:message code="master.page.administrator.category" /></a></li>					
					<li><a href="plaba/administrator/list.do"><spring:message code="master.page.administrator.clase1ControlCheck" /></a></li>					
					
					<li><a href="order/administrator/list.do"><spring:message code="master.page.administrator.order" /></a></li>					
					<li><a href="warehouse/list.do"><spring:message code="master.page.administrator.warehouse" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CONSUMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="shoppingcart/consumer/shoppingcart.do"><spring:message code="master.page.customer.shoppingcart" /></a></li>
				<li><a href="order/consumer/list.do"><spring:message code="master.page.customer.order" /></a></li>
				<li><a href="plaba/consumer/list.do"><spring:message code="master.page.administrator.clase1ControlCheck" /></a></li>
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="hasRole('CLERK')">
			<li><a class="fNiv"><spring:message	code="master.page.clerk" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="order/clerk/list.do"><spring:message code="master.page.clerk.order" /></a></li>
					<li><a href="warehouse/list.do"><spring:message code="master.page.administrator.warehouse" /></a></li>
				
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="item/list.do"><spring:message code="master.page.catalogue" /></a></li>
		
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="userAccount/actor/create.do"><spring:message code="master.page.register" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
					<li><a class="fNiv" href="item/list.do"><spring:message code="master.page.catalogue" /></a></li>
					<li><a class="fNiv" href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

