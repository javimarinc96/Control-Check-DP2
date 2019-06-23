<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:message code="administrator.dashboard.avg" var="avgHeader" />
<spring:message code="administrator.dashboard.min" var="minHeader" />
<spring:message code="administrator.dashboard.max" var="maxHeader" />
<spring:message code="administrator.dashboard.std" var="stdHeader" />

<spring:message code="administrator.name" var="nameHeader" />
<spring:message code="administrator.surname" var="surnameHeader" />

<spring:message code="administrator.dashboard.position.score"
	var="positionAuditScoreHeader" />
<spring:message code="administrator.dashboard.companyScore"
	var="companyAuditScoreHeader" />
<spring:message code="administrator.dashboard.companyHeader"
	var="companyHeader" />
<spring:message code="administrator.dashboard.averageSalaryHeader"
	var="averageSalaryHeader" />
<spring:message code="administrator.dashboard.itemsHeader"
	var="itemsHeader" />
<spring:message code="administrator.dashboard.topHeader"
	var="topHeader" />
<spring:message code="administrator.dashboard.companies.data"
	var="companiesHeader" />
<spring:message code="administrator.dashboard.rookies.data"
	var="rookiesHeader" />
<spring:message code="administrator.dashboard.salaries.data"
	var="salariesHeader" />
<spring:message code="administrator.dashboard.sponsorshipsProvider"
	var="spProvidersHeader" />
<spring:message code="administrator.dashboard.sponsorshipsPosition"
	var="spPositionsHeader" />
<spring:message code="administrator.dashboard.sponsorshipsAbove"
	var="providersAboveHeader" />

<!--  Custom table style -->
<head>
<link rel="stylesheet" href="styles/tablas.css" type="text/css">
<link rel="stylesheet" href="styles/charts.css" type="text/css">
</head>



<!-- C level -->

<table>
	<caption>
		<jstl:out value="${positionAuditScoreHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgSalary}"></jstl:out></td>
		<td><jstl:out value="${minSalary}"></jstl:out></td>
		<td><jstl:out value="${maxSalary}"></jstl:out></td>
		<td><jstl:out value="${stddevSalary}"></jstl:out></td>
	</tr>
</table>
<br />

<table>
	<caption>
		<jstl:out value="${companyAuditScoreHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgCurriculas}"></jstl:out></td>
		<td><jstl:out value="${maxCurriculas}"></jstl:out></td>
		<td><jstl:out value="${minCurriculas}"></jstl:out></td>
		<td><jstl:out value="${stddevCurriculas}"></jstl:out></td>
	</tr>
</table>
<br />


<table>
	<caption>
		<jstl:out value="${companyHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="First"></jstl:out></th>
		<th><jstl:out value="Second"></jstl:out></th>

	</tr>

	<tr>
		<td><jstl:out value="Company1"></jstl:out></td>
		<td><jstl:out value="Company2"></jstl:out></td>

	</tr>
</table>
<br />


<table>
	<caption>
		<jstl:out value="${averageSalaryHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="Average"></jstl:out></th>
	</tr>
	<tr>
		<td><jstl:out value="759.12"></jstl:out></td>
	</tr>
</table>
<br />

<!-- B level -->

<table>
	<caption>
		<jstl:out value="${itemsHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgResults}"></jstl:out></td>
		<td><jstl:out value="${maxResults}"></jstl:out></td>
		<td><jstl:out value="${minResults}"></jstl:out></td>
		<td><jstl:out value="${stddevResults}"></jstl:out></td>
	</tr>
</table>
<br />

<table>
	<caption>
		<jstl:out value="${topHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="1st"></jstl:out></th>
		<th><jstl:out value="2nd"></jstl:out></th>
		<th><jstl:out value="3rd"></jstl:out></th>
		<th><jstl:out value="4th"></jstl:out></th>
		<th><jstl:out value="5th"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="Company 1"></jstl:out></td>
		<td><jstl:out value="Company 2"></jstl:out></td>
		<td><jstl:out value="Company 3"></jstl:out></td>
		<td><jstl:out value="Company 4"></jstl:out></td>
		<td><jstl:out value="Company 5"></jstl:out></td>
	</tr>
</table>
<br />

<!-- A level -->

<table>
	<caption>
		<jstl:out value="${spProvidersHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgSponsorshipsProvider}"></jstl:out></td>
		<td><jstl:out value="${maxSponsorshipsProvider}"></jstl:out></td>
		<td><jstl:out value="${minSponsorshipsProvider}"></jstl:out></td>
		<td><jstl:out value="${stddevSponsorshipsProvider}"></jstl:out></td>
	</tr>
</table>
<br />

<table>
	<caption>
		<jstl:out value="${spPositionsHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgSponsorshipsPosition}"></jstl:out></td>
		<td><jstl:out value="${maxSponsorshipsPosition}"></jstl:out></td>
		<td><jstl:out value="${minSponsorshipsPosition}"></jstl:out></td>
		<td><jstl:out value="${stddevSponsorshipsPosition}"></jstl:out></td>
	</tr>
</table>
<br />

<table>
	<caption>
		<jstl:out value="${providersAboveHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${nameHeader}"></jstl:out></th>
		<th><jstl:out value="${surnameHeader}"></jstl:out></th>
	</tr>
	<jstl:forEach items="${aboveAvgSponsorships}" var="row">
      <tr>
        	<td>${row.name}</td>
        	<td>${row.surname}</td>
      </tr>
   </jstl:forEach>
</table>
<br />


<%-- 
<table>
	<caption>
		<jstl:out value="${ratioHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${ratioHeader}"></jstl:out></th>
	</tr>
	<tr>
		<td>${ratioFinders}</td>
	</tr>
</table>
<br />

<table>
	<caption>
		<jstl:out value="${companiesHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="First"></jstl:out></th>
		<th><jstl:out value="Second"></jstl:out></th>

	</tr>

	<tr>
		<td><jstl:out value="Company1"></jstl:out></td>
		<td><jstl:out value="Company2"></jstl:out></td>

	</tr>
</table>
<br />


<table>
	<caption>
		<jstl:out value="${rookiesHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="First"></jstl:out></th>
		<th><jstl:out value="Second"></jstl:out></th>

	</tr>

	<tr>
		<td><jstl:out value="Rookie1"></jstl:out></td>
		<td><jstl:out value="Rookie2"></jstl:out></td>

	</tr>
</table>
<br />

 --%>