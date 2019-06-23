<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="miscellaneousData/create.do" modelAttribute="miscellaneousData">

	

	<%-- Insitution --%>
	<acme:textbox code="miscellaneousData.freeText" path="freeText" />
	<br>
	
	<%-- Degree --%>
	<acme:textbox code="miscellaneousData.attachments" path="attachments" />
	<br>
	
	
	
	<input type="submit" name="save"
		value="<spring:message code="miscellaneousData.save"/>"/>
	
	<acme:cancel code="miscellaneousData.cancel" url="curricula/list.do" />
</form:form>

