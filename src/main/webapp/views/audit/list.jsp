<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<display:table name="audits" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="audit.text" var="text" />
	<display:column property="text" title="${text}" />
	
	<spring:message code="audit.moment" var="moment" />
	<display:column property="moment" title="${moment}" />
	
	<spring:message code="audit.score" var="score" />
	<display:column property="score" title="${score}" />
	
	<security:authorize access="hasRole('AUDITOR')">
		<display:column titleKey="audit.testEntitys">
			<input type="submit" name="testEntitys" value="<spring:message code="audit.testEntitys" />"
				onclick="javascript: relativeRedir('testEntity/listAudit.do?auditId=${row.id}');" />
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('AUDITOR')">
		<display:column titleKey="audit.show">
			<input type="submit" name="show" value="<spring:message code="audit.show" />"
				onclick="javascript: relativeRedir('audit/auditor/show.do?auditId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('AUDITOR')">
		<display:column titleKey="audit.edit">
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="audit.edit" />"
				onclick="javascript: relativeRedir('audit/auditor/edit.do?auditId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="audit.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('AUDITOR')">
		<display:column titleKey="audit.delete">
		<jstl:if test="${row.draftMode eq true}">
			<input type="submit" name="delete" value="<spring:message code="audit.delete" />"
				onclick="javascript: relativeRedir('audit/auditor/delete.do?auditId=${row.id}');" />
				</jstl:if>
					<jstl:if test="${row.draftMode eq false}">
			<spring:message code="audit.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>
	
	
</display:table>

<br/>

<security:authorize access="hasRole('AUDITOR')">
	<input type="submit" name="create"
		value="<spring:message code="audit.create" />"
		onclick="javascript: relativeRedir('audit/auditor/create.do');" />
</security:authorize>