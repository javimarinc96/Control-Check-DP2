<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<security:authorize access="hasRole('ROOKIE')">
<h2> <spring:message code="application.pending"/> </h2>
<display:table name="pendingApps" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="application.status" var="status" />
	<display:column property="status" title="${status}" />
	
	<spring:message code="application.momentSubmit" var="momentSubmit" />
	<display:column property="momentSubmit" title="${momentSubmit}" />
	
	<spring:message code="application.answerDescription" var="answerDescription" />
	<display:column property="answerDescription" title="${answerDescription}" />
	
	<spring:message code="application.linkCode" var="linkCode" />
	<display:column property="linkCode" title="${linkCode}" autolink = "true" />

		<display:column titleKey="application.show">
			<input type="submit" name="show" value="<spring:message code="application.show" />"
				onclick="javascript: relativeRedir('application/show.do?applicationId=${row.id}');" />
		</display:column>

	
	<security:authorize access="hasRole('ROOKIE')">
		<display:column titleKey="application.submit">
			<jstl:if test="${row.status eq 'PENDING'}">
			<input type="submit" name="submit" value="<spring:message code="application.submit" />"
				onclick="javascript: relativeRedir('application/edit.do?applicationId=${row.id}');" />
			</jstl:if>
		</display:column>
	</security:authorize>
	
	</display:table>

	<input type="submit" name="create" value="<spring:message code="application.create" />"
	onclick="javascript: relativeRedir('application/create.do');" />
	
</security:authorize>
<br/>


<h2> <spring:message code="application.submitted"/> </h2>
<display:table name="submittedApps" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="application.status" var="status" />
	<display:column property="status" title="${status}" />
	
	<spring:message code="application.momentSubmit" var="momentSubmit" />
	<display:column property="momentSubmit" title="${momentSubmit}" />
	
	<spring:message code="application.answerDescription" var="answerDescription" />
	<display:column property="answerDescription" title="${answerDescription}" />
	
	<spring:message code="application.linkCode" var="linkCode" />
	<display:column property="linkCode" title="${linkCode}" />

		<display:column titleKey="application.show">
			<input type="submit" name="show" value="<spring:message code="application.show" />"
				onclick="javascript: relativeRedir('application/show.do?applicationId=${row.id}');" />
		</display:column>
	
		<security:authorize access="hasRole('COMPANY')">
		<display:column titleKey="application.accept">
			<jstl:if test="${row.status eq 'SUBMITTED'}">
			<input type="submit" name="accept" value="<spring:message code="application.accept" />"
				onclick="javascript: relativeRedir('application/accept.do?applicationId=${row.id}');" />
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('COMPANY')">
		<display:column titleKey="application.reject">
			<jstl:if test="${row.status eq 'SUBMITTED'}">
			<input type="submit" name="reject" value="<spring:message code="application.reject" />"
				onclick="javascript: relativeRedir('application/reject.do?applicationId=${row.id}');" />
			</jstl:if>
		</display:column>
	</security:authorize>
	
	</display:table>
	
	<br/>
	
	
<h2> <spring:message code="application.accepted"/> </h2>
<display:table name="acceptedApps" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="application.status" var="status" />
	<display:column property="status" title="${status}" />
	
	<spring:message code="application.momentSubmit" var="momentSubmit" />
	<display:column property="momentSubmit" title="${momentSubmit}" />
	
	<spring:message code="application.answerDescription" var="answerDescription" />
	<display:column property="answerDescription" title="${answerDescription}" />
	
	<spring:message code="application.linkCode" var="linkCode" />
	<display:column property="linkCode" title="${linkCode}" />

		<display:column titleKey="application.show">
			<input type="submit" name="show" value="<spring:message code="application.show" />"
				onclick="javascript: relativeRedir('application/show.do?applicationId=${row.id}');" />
		</display:column>
	
	</display:table>
	
	<br/>
	
	
<h2> <spring:message code="application.rejected"/> </h2>
<display:table name="rejectedApps" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="application.status" var="status" />
	<display:column property="status" title="${status}" />
	
	<spring:message code="application.momentSubmit" var="momentSubmit" />
	<display:column property="momentSubmit" title="${momentSubmit}" />
	
	<spring:message code="application.answerDescription" var="answerDescription" />
	<display:column property="answerDescription" title="${answerDescription}" />
	
	<spring:message code="application.linkCode" var="linkCode" />
	<display:column property="linkCode" title="${linkCode}" />

		<display:column titleKey="application.show">
			<input type="submit" name="show" value="<spring:message code="application.show" />"
				onclick="javascript: relativeRedir('application/show.do?applicationId=${row.id}');" />
		</display:column>	
	
	</display:table>
	
	<br/>