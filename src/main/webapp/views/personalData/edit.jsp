<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="personalData/edit.do" modelAttribute="personalData">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<%-- Insitution --%>
	<acme:textbox code="personalData.fullName" path="fullName" />
	<br>
	
	<%-- Degree --%>
	<acme:textbox code="personalData.statement" path="statement" />
	<br>
	
	<%-- startDate --%>
	<acme:textbox code="personalData.phoneNumber" path="phoneNumber" />
	<br>
	
	<%-- endDate --%>
	<acme:textbox code="personalData.gitHubProfile" path="gitHubProfile" />
	<br>
	
	<%-- mark --%>
	<acme:textbox code="personalData.linkedInProfile" path="linkedInProfile" />
	<br>


	
	<input type="submit" name="save"
		value="<spring:message code="personalData.save"/>"/>
		
		
	<acme:cancel code="personalData.cancel" url="curricula/list.do" />
	
</form:form>

