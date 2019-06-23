<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<a title="Sponsorship" href="${sponsorship.targetPage}"><img src="${sponsorship.banner}" alt="Sponsorship" height = "150px" width = "500px"/></a>

<display:table name="position" id="row" requestURI="position/display.do" class="displaytag">

	<!-- Company -->
	<spring:message code="position.company" var="companyHeader" />
	<display:column property="company.commercialName" title="${companyHeader}" />

	<!-- Ticker -->
	<spring:message code="position.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}" />

	<!-- Title -->
	<spring:message code="position.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<!-- Description -->
	<spring:message code="position.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<!-- Deadline -->
	<spring:message code="position.deadline" var="deadlineHeader" />
	<display:column property="deadline" title="${deadlineHeader}" />

	<!-- ProfileRequired -->
	<spring:message code="position.profileRequired" var="profileRequiredHeader" />
	<display:column property="profileRequired" title="${profileRequiredHeader}" />

	<!-- SkillsRequired -->
	<spring:message code="position.skillsRequired" var="skillsRequiredHeader" />
	<display:column property="skillsRequired" title="${skillsRequiredHeader}" />

	<!-- TechnologyRequired -->
	<spring:message code="position.technologyRequired" var="technologyRequiredHeader" />
	<display:column property="skillsRequired" title="${technologyRequiredHeader}" />

	<!-- SalaryOffered -->
	<spring:message code="position.salaryOffered" var="salaryOfferedHeader" />
	<display:column property="salaryOffered" title="${salaryOfferedHeader}" />

	<!-- DraftMode -->
	<spring:message code="position.draftMode" var="draftModeHeader" />
	<display:column property="draftMode" title="${draftModeHeader}" />
	
</display:table>

<jstl:if test="${deleteable eq true}">
	<security:authorize access="hasRole('COMPANY')">
			<input type="submit" name="delete" value="<spring:message code="position.delete" />"
				onclick="javascript: relativeRedir('position/company/delete.do?positionId=${positionid}');" />
	</security:authorize>
</jstl:if>

<h2> Audits </h2>

<display:table name="audits" id="row" pagesize="5" class="displaytag">
	
	<!-- Text -->
	<spring:message code="position.audit.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

    <!-- Moment -->
    <spring:message code="position.audit.moment" var="momentHeader" />
    <display:column property="moment" title="${momentHeader}" />


</display:table>

<security:authorize access="isAnonymous()">
	<acme:cancel code="position.cancel" url="/position/anonymousList.do" />
</security:authorize>

<security:authorize access="isAuthenticated()">
	<acme:cancel code="position.cancel" url="/position/list.do" />
</security:authorize>
