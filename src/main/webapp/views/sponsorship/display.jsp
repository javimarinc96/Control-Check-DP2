<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="sponsorship" id="row" requestURI="sponsorship/display.do" class="displaytag">

	<spring:message code="sponsorship.banner" var="bannerHeader" />
	<display:column property="banner" title="${bannerHeader}" />
	
	<spring:message code="sponsorship.targetPage" var="targetPageHeader" />
	<display:column property="targetPage" title="${targetPageHeader}" />
	
	<spring:message code="sponsorship.position" var="positionHeader" />
	<display:column property="position" title="${positionHeader}" />

</display:table>


	<acme:cancel code="sponsorship.cancel" url="/sponsorship/provider/list.do" />
