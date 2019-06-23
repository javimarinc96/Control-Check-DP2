<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<display:table name="testEntitys" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="testEntity.moment.pattern" var="momentPattern" />
	
	<spring:message code="testEntity.ticker" var="ticker" />
	<display:column property="ticker" title="${ticker}" />
	
	<spring:message code="testEntity.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="${momentPattern}"/>
	
	<spring:message code="testEntity.body" var="body" />
	<display:column property="body" title="${body}" />
	

	<security:authorize access="hasRole('RELATIONENTITY1')">
		<display:column titleKey="testEntity.show">
			<input type="submit" name="show" value="<spring:message code="testEntity.show" />"
				onclick="javascript: relativeRedir('testEntity/show.do?testEntityId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('RELATIONENTITY1')">
		<display:column titleKey="testEntity.edit">
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="testEntity.edit" />"
				onclick="javascript: relativeRedir('testEntity/edit.do?testEntityId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="testEntity.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('RELATIONENTITY1')">
		<display:column titleKey="testEntity.delete">
		<jstl:if test="${row.draftMode eq true}">
			<input type="submit" name="delete" value="<spring:message code="testEntity.delete" />"
				onclick="javascript: relativeRedir('testEntity/delete.do?testEntityId=${row.id}');" />
				</jstl:if>
					<jstl:if test="${row.draftMode eq false}">
			<spring:message code="testEntity.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>
	
	
</display:table>

<br/>

<security:authorize access="hasRole('RELATIONENTITY1')">
	<input type="submit" name="create"
		value="<spring:message code="testEntity.create" />"
		onclick="javascript: relativeRedir('testEntity/create.do');" />
</security:authorize>