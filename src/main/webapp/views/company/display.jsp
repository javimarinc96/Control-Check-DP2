<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

	<fieldset>

	<b><spring:message code="company.commercialName"></spring:message>:</b><jstl:out value="${company.commercialName}"></jstl:out>
	<br />
	
	<b><spring:message code="company.address"></spring:message>:</b><jstl:out value="${company.address}"></jstl:out>
	<br />
	
	<b><spring:message code="company.email"></spring:message>:</b><jstl:out value="${company.email}"></jstl:out>
	<br />

	<b><spring:message code="company.photo"></spring:message>:</b><jstl:out value="${company.photo}"></jstl:out>
	<br />
	
	<b><spring:message code="company.phoneNumber"></spring:message>:</b><jstl:out value="${company.phoneNumber}"></jstl:out>
	<br />
	
	<b><spring:message code="company.score"></spring:message>:</b><jstl:out value="${company.score}"></jstl:out>
	<br />

	</fieldset>
	
	<br />

<security:authorize access="isAnonymous()">
	<acme:cancel code="position.cancel" url="/position/anonymousList.do" />
</security:authorize>

<security:authorize access="isAuthenticated()">
	<acme:cancel code="position.cancel" url="/position/list.do" />
</security:authorize>