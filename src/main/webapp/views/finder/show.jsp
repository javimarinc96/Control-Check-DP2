<%--
 * show.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<!-- Attributes -->
	
	<b><spring:message code="finder.moment"/></b>: <fmt:formatDate value="${finder.moment}" />
	<br/>
	<b><spring:message code="finder.keyWord"/></b>: <jstl:out value="${finder.keyWord}" />
	<br/>
	<b><spring:message code="finder.deadline"/></b>: <fmt:formatDate value="${finder.deadline}" />
    <br/>
    <b><spring:message code="finder.maximumDeadline"/></b>: <fmt:formatDate value="${finder.maximumDeadline}"/>
    <br/>
	<b><spring:message code="finder.minimumSalary"/></b>: <jstl:out value="${finder.minimumSalary}"/>
	<br/>
	<br>

	<!--New search-->
	<input type="button" name="create" onclick="javascript: window.location.replace('finder/edit.do')"
		value="<spring:message code="finder.newSearch" />" />
		<br>
		<br>
		
	<!--Position results-->
	<display:table name="positions" id="row" requestURI="${requestURI}" pagesize="10" class="displaytag">

	<!-- La lista con el botón de editar en cada fila (rookie)-->

		<display:column>
			<a href="position/show.do?positionId=${row.id}">
				<spring:message code="position.show"/>
			</a>
		</display:column>
		<display:column property="ticker" titleKey="position.ticker" sortable="true"/>
		<display:column property="title" titleKey="position.title" sortable="true"/>
		<display:column property="description" titleKey="position.description" sortable="true"/>
		<display:column property="deadline" titleKey="position.deadline" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
		<display:column property="profileRequired" titleKey="position.profileRequired" sortable="true"/>
		<display:column property="skillsRequired" titleKey="position.skillsRequired" sortable="true"/>
		<display:column property="technologyRequired" titleKey="position.technologyRequired" sortable="true"/>
		<display:column property="salaryOffered" titleKey="position.salaryOffered" sortable="true"/>
		<display:column property="draftMode" titleKey="position.draftMode" sortable="true"/>
	
	</display:table>
	
</html>