<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="configurations/administrator/edit.do" modelAttribute="config">

	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />

    <%-- systemName--%>
    <acme:textbox code="configurations.systemName" path="systemName" />
    <br>

    <%-- banner--%>
    <acme:textbox code="configurations.banner" path="banner" />
    <br>

	<%-- welcomeMessageEn--%>
	<acme:textbox code="configurations.welcomeMessageEn" path="welcomeMessageEn" />
	<br>

	<%-- welcomeMessageEs--%>
	<acme:textbox code="configurations.welcomeMessageEs" path="welcomeMessageEs" />
	<br>

	<%-- countryCode--%>
	<acme:textbox code="configurations.countryCode" path="countryCode" />
	<br>

	<%-- brandNames--%>
	<acme:textbox code="configurations.brandNames" path="brandNames" />
	<br>

	<%-- cacheTime--%>
	<acme:textbox code="configurations.cacheTime" path="cacheTime" />
	<br>

	<%-- maxResults--%>
	<acme:textbox code="configurations.finderMaxResult" path="finderMaxResult" />
	<br>

	<%-- spamWords--%>
	<acme:textbox code="configurations.spamWords" path="spamWords" />
	<br>

	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="configurations.save"/>"/>
	
	<acme:cancel code="configurations.cancel" url="/" />
</form:form>