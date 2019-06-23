<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="audit/auditor/save.do" modelAttribute="audit" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="auditor" />
	
	<acme:textbox code="audit.text" path="text" />
	<br/>
	
	<acme:textbox code="audit.score" path="score" />
	<br/>

	<jstl:if test='${audit.draftMode == true}'>
	<acme:boolean code="audit.draftMode" trueCode="audit.true" falseCode="audit.false" path="draftMode"/>
	<br/>
	<br/>			
	</jstl:if>
	
	
	<jstl:if test='${audit.id == 0}'>
	
	<form:label path="position">
	<spring:message code="audit.position" />:
	</form:label>
	
	<form:select path="position" >
	<form:options items="${positions}" itemLabel="title" />
	</form:select>	
	
	</jstl:if>

	<br />	
	<br />	
	
	<input type="submit" name="save"
		value="<spring:message code="audit.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="audit.cancel" />"
		onclick="javascript: relativeRedir('audit/auditor/list.do');" />


</form:form>