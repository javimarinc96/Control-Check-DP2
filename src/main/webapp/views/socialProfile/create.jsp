<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="socialProfile/create.do" modelAttribute="socialProfile">

	

	<%-- Nick --%>
	<acme:textbox code="socialProfile.nick" path="nick" />
	<br>
	
	<%-- SocialNetworkName --%>
	<acme:textbox code="socialProfile.socialNetworkName" path="socialNetworkName" />
	<br>
	
	<%-- Link --%>
	<acme:textbox code="socialProfile.link" path="link" />
	<br>

	
	

	

	<input type="submit" name="save"
		value="<spring:message code="socialProfile.save"/>"/>
	
	<acme:cancel code="socialProfile.cancel" url="socialProfile/list.do" />
	
</form:form>

