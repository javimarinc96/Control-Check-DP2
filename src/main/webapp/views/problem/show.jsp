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


	<fieldset>

	<b><spring:message code="problem.title"></spring:message>:</b><jstl:out value="${problem.title}"></jstl:out>
	<br />
	
	<b><spring:message code="problem.statement"></spring:message>:</b><jstl:out value="${problem.statement}"></jstl:out>
	<br />
	
	<b><spring:message code="problem.hint"></spring:message>:</b><jstl:out value="${problem.hint}"></jstl:out>
	<br />
	
	<b><spring:message code="problem.attachments"></spring:message>:</b><a href="${problem.attachments}">${problem.attachments}</a>
	<br />
	
	<b><spring:message code="problem.draftMode"></spring:message>:</b><jstl:out value="${problem.draftMode}"></jstl:out>
	<br />
	
	<b><spring:message code="problem.position"></spring:message>:</b><jstl:out value="${problem.position.title}"></jstl:out>
	<br />
	
	<b><spring:message code="problem.company"></spring:message>:</b><jstl:out value="${problem.company.commercialName}"></jstl:out>
	<br />
	

		
	</fieldset>

	<br />
	
<!-- Cancel -->

<security:authorize access="hasRole('COMPANY')">	
				<button type="button" onclick="javascript: relativeRedir('problem/company/list.do')">
				<spring:message code="problem.cancel" />
				</button>
		</security:authorize>