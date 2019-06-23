<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="miscellaneousData" id="row" requestURI="miscellaneousData/display.do" class="displaytag">

	<spring:message code="miscellaneousData.freeText" var="freeTextHeader" />
	<display:column property="freeText" title="${freeTextHeader}" />
	
	<spring:message code="miscellaneousData.attachments" var="attachmentsHeader" />
	<display:column property="attachments" title="${attachmentsHeader}" autolink = "true"  />
	

</display:table>


	<acme:cancel code="miscellaneousData.cancel" url="curricula/list.do" />
