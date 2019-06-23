<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="application/edit.do" modelAttribute="application">

	<%-- Hidden properties from Application--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="status" />
	<form:hidden path="momentSubmit" />
	<form:hidden path="rookie" />
	<form:hidden path="problem" />
	<form:hidden path="position" />
	
	<%-- Answer Description --%>
	<form:label path="answerDescription">
			<spring:message code="application.answerDescription"/>
	</form:label>
	<form:input path="answerDescription" placeHolder="Can't be left empty."/>
	    	<form:errors cssClass="error" path="answerDescription" />
	<br/>	  
	<br/>  	

	<%-- Link Code --%>
	<form:label path="linkCode">
			<spring:message code="application.linkCode"/>
	</form:label>
	<form:input path="linkCode" placeHolder="Can't be left empty."/>
	    	<form:errors cssClass="error" path="linkCode" />
	<br/>
	<br/>
	
	<%-- Buttons --%>
	<input type="submit" name="save" value="<spring:message code="application.save"/>"/>
	<acme:cancel code="application.cancel" url="/" />
	
</form:form>