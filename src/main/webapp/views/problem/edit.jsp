<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="problem/company/save.do" modelAttribute="problem" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="company" />

	
	<acme:textbox code="problem.title" path="title" />
	<br/>
	<acme:textarea code="problem.statement" path="statement" /> 
	<br/>
	<acme:textbox code="problem.hint" path="hint" />
	<br/>
	<acme:textbox code="problem.attachments" path="attachments" />
	<br/>
	
	<jstl:if test='${problem.draftMode == true}'>
	<acme:boolean code="problem.draftMode" trueCode="problem.true" falseCode="problem.false" path="draftMode"/>		
	<br/>
	</jstl:if>
	
	<jstl:if test='${problem.id == 0}'>
	
	<form:label path="position">
	<spring:message code="problem.position" />:
	</form:label>
	
	<form:select path="position" >
			<form:options
				items="${positions}" 
				itemLabel="title"
				/>
	</form:select>	
	<br />	
	<br />	
	
	</jstl:if>

	<input type="submit" name="save"
		value="<spring:message code="problem.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="problem.cancel" />"
		onclick="javascript: relativeRedir('problem/company/list.do');" />


</form:form>