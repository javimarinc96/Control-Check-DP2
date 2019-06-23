<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="message/create.do" modelAttribute="mess">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="folder" />
	<form:hidden path="sender" />
	<form:hidden path="moment" />


    <%-- receiver--%>
	<div>
		<form:label path="receiver" >
			<spring:message code="message.receiver" />
		</form:label>
		<form:select path="receiver" >
			<form:option label="----" value="0" />
			<jstl:forEach items="${actors}" var="actor">
				<form:option value="${actor}" label="${actor.name.concat(' ').concat(actor.surname)}" />
			</jstl:forEach>
		</form:select>
	</div>
	<br>
	
	
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
		value="<spring:message code="message.save"/>"/>
	
	<acme:cancel code="message.cancel" url="message/list.do" />
</form:form>