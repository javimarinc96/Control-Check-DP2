<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="sponsorship/provider/edit.do" modelAttribute="sponsorship">
	<form:hidden path="id" />
	<form:hidden path="version" />
 	<form:hidden path="provider" />
	<form:hidden path="position" />



	<%-- Nick --%>
	<acme:textbox code="sponsorship.banner" path="banner" />
	<br>
	
	<%-- SocialNetworkName --%>
	<acme:textbox code="sponsorship.targetPage" path="targetPage" />
	<br>
	


	
	<input type="submit" name="save"
		value="<spring:message code="sponsorship.save"/>"/>
		
		
	
	<acme:cancel code="sponsorship.cancel" url="sponsorship/provider/list.do" />
	
</form:form>

