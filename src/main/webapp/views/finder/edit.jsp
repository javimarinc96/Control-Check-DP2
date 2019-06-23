<%--
 * edit.jsp
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

	<form:form action="finder/edit.do" modelAttribute="finder">
	
		<!--Ocultos-->
	    <form:hidden path="id" />
	    <form:hidden path="version" />
	    <form:hidden path="moment" />
	    <form:hidden path="positions" />
	    
	    <!--Key Word-->
		<form:label path="keyWord">
			<spring:message code="finder.keyWord"/>
	    </form:label>
		<form:input path="keyWord"/>
	    	<form:errors cssClass="error" path="keyWord" />
	    	
	    <!--Deadline-->
	    <form:label path="deadline">
			<spring:message code="finder.deadline"/>
	    </form:label>
		<form:input path="deadline" placeHolder="dd/MM/yyyy HH:mm"/>
	    	<form:errors cssClass="error" path="deadline" />
	    	
	    <!--Maximum deadline-->
	    <form:label path="maximumDeadline">
			<spring:message code="finder.maximumDeadline"/>
	    	</form:label>
		<form:input path="maximumDeadline" placeHolder="dd/MM/yyyy HH:mm"/>
	    	<form:errors cssClass="error" path="maximumDeadline" />
	    	
	    <!--Minimum Salary-->
		<form:label path="minimumSalary">
			<spring:message code="finder.minimumSalary"/>
	    	</form:label>
		<form:input path="minimumSalary"/>
	    	<form:errors cssClass="error" path="minimumSalary" />
	    	
	    <br/>
	    <br/>
	    
		<input type="submit" name="save" value="<spring:message code="finder.save"/>"/>
		<input type="button" name="cancel"
		value="<spring:message code="finder.cancel" />"
		onclick="javascript: relativeRedir('finder/show.do');" />
		
		<br/>
	
	</form:form>