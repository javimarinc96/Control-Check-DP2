<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="position/company/edit.do" modelAttribute="position">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="company" />
	<form:hidden path="ticker" />


    <%-- title--%>
    <acme:textbox code="position.title" path="title" />
    <br>

    <%-- description--%>
    <acme:textbox code="position.description" path="description" />
    <br>

	<%-- deadline --%>
	<form:label path="deadline"><spring:message code="position.deadline" /></form:label>
	<form:input path="deadline" placeholder="dd/mm/yyyy hh:mm" format="{0,date,dd/MM/yyyy HH:mm}" />
	<form:errors class="error" path="deadline" />
	<br><br>

	<%-- profileRequired --%>
	<acme:textbox code="position.profileRequired" path="profileRequired" />
	<br>

	<%-- skillsRequired --%>
	<acme:textbox code="position.skillsRequired" path="skillsRequired" />
	<br>

	<%-- technologyRequired --%>
	<acme:textbox code="position.technologyRequired" path="technologyRequired" />
	<br>

	<%-- salaryOffered --%>
	<acme:textbox code="position.salaryOffered" path="salaryOffered" />
	<br>



	<%-- draftMode --%>
	<form:label path="draftMode">
		<spring:message code="position.draftMode" />
	</form:label>
	<form:select id="modeDropdown" path="draftMode">
		<form:option value="0"><spring:message code="position.false" /></form:option>	
		<form:option value="1"><spring:message code="position.true" /></form:option>
	</form:select>
	<form:errors class="error" path="draftMode" />
	<br>
	<br>


	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="position.save"/>"/>
	
	<acme:cancel code="position.cancel" url="position/list.do" />
</form:form>