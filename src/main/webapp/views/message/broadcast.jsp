<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="message/broadcast.do" modelAttribute="mess">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="folder" />
	<form:hidden path="sender" />
	<form:hidden path="moment" />
	<form:hidden path="recipient" />

	<%-- subject--%>
	<acme:textbox code="message.subject" path="subject" />
	<br>

	<%-- body--%>
	<acme:textarea code="message.body" path="body" />
	<br>

	<%-- tags--%>
	<acme:textbox code="message.tags" path="tags" />
	<br>



	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="position.save"/>"/>
	
	<acme:cancel code="position.cancel" url="position/list.do" />
</form:form>