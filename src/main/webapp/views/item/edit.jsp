<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="item/edit.do" modelAttribute="item">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="provider" />

    <%-- Name --%>
    <acme:textbox code="item.name" path="name" />
    <br>

    <%-- Description --%>
    <acme:textbox code="item.description" path="description" />
    <br>
    
    <%-- Link --%>
    <acme:textbox code="item.link" path="link" />
    <br>
    
    <%-- Pictures --%>
    <acme:textbox code="item.pictures" path="pictures" />
    <br>

	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="item.save"/>"/>
	
	<acme:cancel code="item.cancel" url="item/list.do" />
</form:form>