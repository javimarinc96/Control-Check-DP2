<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="providers" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

    <display:column>
        <a href="provider/show.do?providerId=${row.id}">
            <spring:message code="provider.show"/>
        </a>
    </display:column>
    
    <display:column>
        <a href="item/listByProvider.do?providerId=${row.id}">
            <spring:message code="provider.items"/>
        </a>
    </display:column>

    <!-- Name -->
    <spring:message code="provider.name" var="nameHeader" />
    <display:column property="name" title="${nameHeader}" />
	
	<!-- Surname -->
	<spring:message code="provider.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" />

    <!-- Username -->
    <spring:message code="provider.username" var="usernameHeader" />
    <display:column property="userAccount.username" title="${usernameHeader}" />
    
    <!-- Make -->
    <spring:message code="provider.make" var="makeHeader" />
    <display:column property="make" title="${makeHeader}" />

</display:table>