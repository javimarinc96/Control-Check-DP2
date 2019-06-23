<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="application/create.do" modelAttribute="application">

	<%-- Hidden properties from Application--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="status" />
	<form:hidden path="answerDescription" />
	<form:hidden path="linkCode" />
	<form:hidden path="momentSubmit" />
	<form:hidden path="rookie" />
	<form:hidden path="problem" />
	
	<!--Position-->
	<form:label path="position">
		<spring:message code="application.position" />:
	</form:label>
	<form:select id="positions" path="position">
		<form:option value="0" label="----" />
		<jstl:forEach items="${positions}" var="p">
			<form:option value="${p.id}" label="${p.ticker}" />
		</jstl:forEach>
	</form:select>
	<form:errors cssClass="error" path="position" />
	<br />
	<br />
	
	<%-- Buttons --%>
	<input type="submit" name="save" value="<spring:message code="application.save"/>"/>
	<acme:cancel code="application.cancel" url="/application/list.do" />
	
</form:form>