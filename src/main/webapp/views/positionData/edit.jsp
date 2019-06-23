<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="positionData/edit.do" modelAttribute="positionData">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	

	<%-- Insitution --%>
	<acme:textbox code="positionData.title" path="title" />
	<br>
	
	<%-- Degree --%>
	<acme:textbox code="positionData.description" path="description" />
	<br>
	
	<%-- startDate --%>
	<acme:textbox code="positionData.startDate" path="startDate" />
	<br>
	
	<%-- endDate --%>
	<acme:textbox code="positionData.endDate" path="endDate" />
	<br>
	
	

	
	

	
	<input type="submit" name="save"
		value="<spring:message code="positionData.save"/>"/>
		
		
	<acme:cancel code="positionData.cancel" url="curricula/list.do" />
	
</form:form>

