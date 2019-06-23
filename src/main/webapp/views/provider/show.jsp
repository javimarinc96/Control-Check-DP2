<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="provider" id="row" requestURI="provider/show.do" class="displaytag">

	<!-- Name -->
	<spring:message code="provider.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<!-- Surname -->
	<spring:message code="provider.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" />

	<!-- VAT -->
	<spring:message code="provider.vat" var="vatHeader" />
	<display:column property="vat" title="${vatHeader}" />

	<!-- Photo -->
	<spring:message code="provider.photo" var="photoHeader" />
	<display:column property="photo" title="${photoHeader}" />
	
	<!-- Email -->
	<spring:message code="provider.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" />
	
	<!-- Phone Number -->
	<spring:message code="provider.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader}" />
	
	<!-- Address -->
	<spring:message code="provider.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" />
	
</display:table>

<acme:cancel code="provider.cancel" url="/provider/list.do" />

