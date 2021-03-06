<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="educationData" id="row" requestURI="educationData/display.do" class="displaytag">

	<spring:message code="educationData.institution" var="institutionHeader" />
	<display:column property="institution" title="${institutionHeader}" />
	
	<spring:message code="educationData.degree" var="degreeHeader" />
	<display:column property="degree" title="${degreeHeader}" />
	
	<spring:message code="educationData.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}" />
	
	<spring:message code="educationData.endDate" var="endDateHeader" />
	<display:column property="endDate" title="${endDateHeader}" />
	
	<spring:message code="educationData.mark" var="markHeader" />
	<display:column property="mark" title="${markHeader}" />
	

</display:table>


	<acme:cancel code="educationData.cancel" url="curricula/list.do" />
