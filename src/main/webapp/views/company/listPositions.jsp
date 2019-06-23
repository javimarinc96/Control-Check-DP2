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

<display:table name="positions" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="company.position.ticker" var="tickerHeader" />
    <display:column property="ticker" title="${tickerHeader}" />
	
	<!-- Title -->
	<spring:message code="company.position.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

    <!-- Description -->
    <spring:message code="company.position.description" var="descriptionHeader" />
    <display:column property="description" title="${descriptionHeader}" />

    <!-- Deadline -->
    <spring:message code="company.position.deadline" var="deadlineHeader" />
    <display:column property="deadline" title="${deadlineHeader}" />



</display:table>
