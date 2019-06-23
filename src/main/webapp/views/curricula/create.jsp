<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ROOKIE')">

	<form:form action="curricula/save.do" modelAttribute="form" >
	
			
		
		<fieldset>
		  <legend> <spring:message code="curricula.personalData" /> </legend>
		  
		  	<br />
		  
			<acme:textbox code="curricula.personalData.fullName" path="personalData.fullName"/>
			<br />
			
			<acme:textbox code="curricula.personalData.statement" path="personalData.statement"/>
			<br />
			
			<acme:textbox code="curricula.personalData.phoneNumber" path="personalData.phoneNumber"/>	
			<br />
			
			<acme:textbox code="curricula.personalData.linkedInProfile" path="personalData.linkedInProfile"/>	
			<br />
			
			<acme:textbox code="curricula.personalData.gitHubProfile" path="personalData.gitHubProfile"/>	
			
		</fieldset>
		
		
		<fieldset>
		  <legend> <spring:message code="curricula.miscellaneousData" /> </legend>
		  
		  	<br />
		  
			<acme:textbox code="curricula.miscellaneousData.freeText" path="miscellaneousData.freeText"/>
			<br />
			
			<acme:textbox code="curricula.miscellaneousData.attachments" path="miscellaneousData.attachments"/>
			<br />
			
			
			
		</fieldset>
	
		<br />
		
	    <input type="submit" name="save" value="<spring:message code="curricula.save"/>" /> 
	
		<acme:cancel url="/curricula/list.do" code="curricula.cancel" /> 
		
	</form:form> 

</security:authorize>