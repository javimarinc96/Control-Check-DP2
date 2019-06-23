<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="item" id="row" requestURI="item/show.do" class="displaytag">

	<!-- Provider -->
	<spring:message code="item.provider" var="providerHeader" />
	<display:column property="provider.name" title="${providerHeader}" />

	<!-- Name -->
	<spring:message code="item.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<!-- Description -->
	<spring:message code="item.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<!-- Link -->
	<spring:message code="item.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}" />
	
	<!-- Pictures -->
	<spring:message code="item.pictures" var="picturesHeader" />
	<display:column property="pictures" title="${picturesHeader}" />

	
</display:table>

<security:authorize access="hasRole('PROVIDER')">
		<input type="submit" name="delete" value="<spring:message code="item.delete" />"
			onclick="javascript: relativeRedir('item/delete.do?itemId=${itemid}');" />
</security:authorize>

<security:authorize access="isAnonymous()">
	<acme:cancel code="item.cancel" url="/item/anonymousList.do" />
</security:authorize>

<security:authorize access="isAuthenticated()">
	<acme:cancel code="item.cancel" url="/item/list.do" />
</security:authorize>
