<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="positionData" id="row" requestURI="positionData/display.do" class="displaytag">

	<spring:message code="positionData.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="positionData.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />
	
	<spring:message code="positionData.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}" />
	
	<spring:message code="positionData.endDate" var="endDateHeader" />
	<display:column property="endDate" title="${endDateHeader}" />
	
	

</display:table>


	<acme:cancel code="positionData.cancel" url="curricula/list.do" />
