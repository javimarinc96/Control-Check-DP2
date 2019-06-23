<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="testEntity/save.do" modelAttribute="testEntity" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="ticker" />
	<form:hidden path="relation1" />
	
	<acme:textbox code="testEntity.body" path="body" />
	<br/>
	
	<acme:textbox code="testEntity.photo" path="photo" />
	<br/>

	<jstl:if test='${testEntity.draftMode == true}'>
	<acme:boolean code="testEntity.draftMode" trueCode="testEntity.true" falseCode="testEntity.false" path="draftMode"/>
	<br/>
	<br/>			
	</jstl:if>
	
	
	<jstl:if test='${testEntity.id == 0}'>
	
	<form:label path="relation2">
	<spring:message code="testEntity.relation2" />:
	</form:label>
	
	<form:select path="relation2" >
	<form:options items="${relation2s}" itemLabel="title" />
	</form:select>	
	
	</jstl:if>

	<br />	
	<br />	
	
	<input type="submit" name="save"
		value="<spring:message code="testEntity.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="testEntity.cancel" />"
		onclick="javascript: relativeRedir('testEntity/list.do');" />


</form:form>