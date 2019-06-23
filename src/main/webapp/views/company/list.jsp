<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="companys" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="company.commercialName"
		var="commercialNameHeader" />
	<display:column property="commercialName"
		title="${commercialNameHeader}" />

	<spring:message code="company.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" />

	<spring:message code="company.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" />

	<spring:message code="company.photo" var="photoHeader" />
	<display:column property="photo" title="${photoHeader}" />

	<spring:message code="company.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader}" />
	
	<display:column titleKey ="company.score" >
	<jstl:choose>
				<jstl:when test="${row.score == null}">
					<spring:message code="score.null" />
				</jstl:when>
				<jstl:otherwise>
					<jstl:out value="${row.score}"></jstl:out>
				</jstl:otherwise>
	</jstl:choose>
	</display:column>  
	

	<security:authorize access="hasRole('ADMIN')">
	
	<display:column titleKey="company.compute">
			<input type="submit" name="compute" value="<spring:message code="company.compute" />"
				onclick="javascript: relativeRedir('company/score.do?companyId=${row.id}');" />
	</display:column>
	
	</security:authorize>
	
	<display:column titleKey="company.position">
				<input type="submit" name="positions" value="<spring:message code="company.position" />"
				onclick="javascript: relativeRedir('company/positions.do?companyId=${row.id}');" />
	</display:column>

</display:table>
