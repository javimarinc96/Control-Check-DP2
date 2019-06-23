<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<spring:message code="testEntity.moment.pattern" var="momentPattern" />
	
	<fieldset>

	<b><spring:message code="testEntity.ticker"></spring:message>:</b><jstl:out value="${testEntity.ticker}"></jstl:out>
	<br />
	
	<b><spring:message code="testEntity.moment"></spring:message>:</b><jstl:out value="${testEntity.moment}"></jstl:out>
	<br />
	
	<b><spring:message code="testEntity.body"></spring:message>:</b><jstl:out value="${testEntity.body}"></jstl:out>
	<br />
	
	<b><spring:message code="testEntity.photo"></spring:message>:</b><jstl:out value="${testEntity.photo}"></jstl:out>
	<br />
	
	<b><spring:message code="testEntity.draftMode"></spring:message>:</b><jstl:out value="${testEntity.draftMode}"></jstl:out>
	<br />
	
	<b><spring:message code="testEntity.relation2"></spring:message>:</b><jstl:out value="${testEntity.relation2.title}"></jstl:out>
	<br />
	
	<b><spring:message code="testEntity.relation1"></spring:message>:</b><jstl:out value="${testEntity.relation1.name}"></jstl:out>
	<br />
	
	</fieldset>

	<br />
	
<!-- Cancel -->

<security:authorize access="hasRole('RELATIONENTITY1')">	
				<button type="button" onclick="javascript: relativeRedir('testEntity/list.do')">
				<spring:message code="testEntity.cancel" />
				</button>
		</security:authorize>