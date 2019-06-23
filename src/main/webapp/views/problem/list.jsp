<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<display:table name="problems" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="problem.title" var="title" />
	<display:column property="title" title="${title}" />
	
	<spring:message code="problem.statement" var="statement" />
	<display:column property="statement" title="${statement}" />

	<security:authorize access="hasRole('COMPANY')">
		<display:column titleKey="problem.show">
			<input type="submit" name="show" value="<spring:message code="problem.show" />"
				onclick="javascript: relativeRedir('problem/company/show.do?problemId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('COMPANY')">
		<display:column titleKey="problem.edit">
		
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="problem.edit" />"
				onclick="javascript: relativeRedir('problem/company/edit.do?problemId=${row.id}');" />
				</jstl:if>
				
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="problem.no.draft" />
		 </jstl:if>
		 
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('COMPANY')">
		<display:column titleKey="problem.delete">
		<jstl:if test="${row.draftMode eq true}">
			<input type="submit" name="delete" value="<spring:message code="problem.delete" />"
				onclick="javascript: relativeRedir('problem/company/delete.do?problemId=${row.id}');" />
				</jstl:if>
					<jstl:if test="${row.draftMode eq false}">
			<spring:message code="problem.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>
	
	
</display:table>

<br/>

<security:authorize access="hasRole('COMPANY')">
	<input type="submit" name="create"
		value="<spring:message code="problem.create" />"
		onclick="javascript: relativeRedir('problem/company/create.do');" />
</security:authorize>