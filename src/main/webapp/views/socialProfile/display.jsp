<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<a href="${photo}"><img src="${photo}" height = "150px" width = "150px"/></a>


<display:table name="socialProfile" id="row" requestURI="socialProfile/display.do" class="displaytag">

	<spring:message code="socialProfile.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" />
	
	<spring:message code="socialProfile.socialNetworkName" var="socialNetworkNameHeader" />
	<display:column property="socialNetworkName" title="${socialNetworkNameHeader}" />
	
	<spring:message code="socialProfile.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}" autolink = "true" />
	
	

</display:table>


	<acme:cancel code="socialProfile.cancel" url="/socialProfile/list.do" />
