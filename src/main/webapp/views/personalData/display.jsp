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


<display:table name="personalData" id="row" requestURI="personalData/display.do" class="displaytag">



	<spring:message code="personalData.fullName" var="fullNameHeader" />
	<display:column property="fullName" title="${fullNameHeader}" />
	
	<spring:message code="personalData.statement" var="statementHeader" />
	<display:column property="statement" title="${statementHeader}" />
	
	<spring:message code="personalData.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader}" />
	
	<spring:message code="personalData.gitHubProfile" var="gitHubProfileHeader" />
	<display:column property="gitHubProfile" title="${gitHubProfileHeader}" autolink = "true" />
	
	<spring:message code="personalData.linkedInProfile" var="linkedInProfileHeader" />
	<display:column property="linkedInProfile" title="${linkedInProfileHeader}" autolink = "true"/>
	

</display:table>


	<acme:cancel code="personalData.cancel" url="/personalData/list.do" />
