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

	<b><spring:message code="audit.text"></spring:message>:</b><jstl:out value="${audit.text}"></jstl:out>
	<br />
	
	<b><spring:message code="audit.moment"></spring:message>:</b><jstl:out value="${audit.moment}"></jstl:out>
	<br />
	
	<b><spring:message code="audit.score"></spring:message>:</b><jstl:out value="${audit.score}"></jstl:out>
	<br />
	
	<b><spring:message code="audit.draftMode"></spring:message>:</b><jstl:out value="${audit.draftMode}"></jstl:out>
	<br />
	
	<b><spring:message code="audit.position"></spring:message>:</b><jstl:out value="${audit.position.title}"></jstl:out>
	<br />
	
	<b><spring:message code="audit.auditor"></spring:message>:</b><jstl:out value="${audit.auditor.name}"></jstl:out>
	<br />
	
	</fieldset>

	<br />
	
<!-- Cancel -->

<security:authorize access="hasRole('AUDITOR')">	
				<button type="button" onclick="javascript: relativeRedir('audit/auditor/list.do')">
				<spring:message code="audit.cancel" />
				</button>
		</security:authorize>