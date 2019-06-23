<%--
 * show.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
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

<b><spring:message code="application.status"></spring:message>: </b><jstl:out value="${application.status}"></jstl:out>
<br />
	
<b><spring:message code="application.answerDescription"></spring:message>: </b><jstl:out value="${application.answerDescription}"></jstl:out>
<br />

<b><spring:message code="application.linkCode"></spring:message>:</b><a href="${application.linkCode}">${application.linkCode}</a>
<br />
	
<b><spring:message code="application.momentSubmit"></spring:message>: </b><jstl:out value="${application.momentSubmit}"></jstl:out>
<br />
	
<b><spring:message code="application.problem"></spring:message>: </b><jstl:out value="${application.problem.title}"></jstl:out>
<br />
	
<b><spring:message code="application.position"></spring:message>: </b><jstl:out value="${application.position.ticker}"></jstl:out>
<br />
	
<b><spring:message code="application.rookie"></spring:message>: </b><jstl:out value="${application.rookie.name}"></jstl:out>
<br />
<br />
	
<!-- Cancel -->

	<security:authorize access="hasRole('ROOKIE')">	
		<button type="button" onclick="javascript: relativeRedir('application/list.do')">
			<spring:message code="application.cancel" />
		</button>
	</security:authorize>

	<security:authorize access="hasRole('COMPANY')">	
		<button type="button" onclick="javascript: relativeRedir('application/list.do')">
			<spring:message code="application.cancel" />
		</button>
	</security:authorize>