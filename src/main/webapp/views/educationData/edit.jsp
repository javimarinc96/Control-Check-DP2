<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="educationData/edit.do" modelAttribute="educationData">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<%-- Insitution --%>
	<acme:textbox code="educationData.institution" path="institution" />
	<br>
	
	<%-- Degree --%>
	<acme:textbox code="educationData.degree" path="degree" />
	<br>
	
	<%-- startDate --%>
	<acme:textbox code="educationData.startDate" path="startDate" />
	<br>
	
	<%-- endDate --%>
	<acme:textbox code="educationData.endDate" path="endDate" />
	<br>
	
	<%-- mark --%>
	<acme:textbox code="educationData.mark" path="mark" />
	<br>

	
	

	
	<input type="submit" name="save"
		value="<spring:message code="educationData.save"/>"/>
		
		
	
	<acme:cancel code="educationData.cancel" url="curricula/list.do" />
	
</form:form>

