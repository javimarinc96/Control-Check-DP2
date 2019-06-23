<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<fieldset>
    <legend>
        <spring:message code="position.searchText" />
    </legend>
    <security:authorize access="isAuthenticated()">
		<form action="position/list.do" method="GET">
        	<input type="text" name="searcher" /> <input type="submit" value="<spring:message code = "position.searcher" />" />
    	</form>
	</security:authorize>
    <security:authorize access="isAnonymous()">
		<form action="position/anonymousList.do" method="GET">
	       	<input type="text" name="searcher" /> <input type="submit" value="<spring:message code = "position.searcher" />" />
	   	</form>
	</security:authorize>
</fieldset>

<display:table name="positions" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

   <security:authorize access="hasRole('COMPANY')">
        <display:column>
            <jstl:if test="${row.draftMode}">
              <a href="position/company/edit.do?positionId=${row.id}">
                <spring:message code="position.edit"/>
              </a>
            </jstl:if>
        </display:column>
   </security:authorize>

    <display:column>
        <a href="position/show.do?positionId=${row.id}">
            <spring:message code="position.show"/>
        </a>
    </display:column>

    <display:column>
        <a href="company/show.do?companyId=${row.company.id}">
            <spring:message code="position.company"/>
        </a>
    </display:column>

    <!-- Ticker -->
    <spring:message code="position.ticker" var="tickerHeader" />
    <display:column property="ticker" title="${tickerHeader}" />
	
	<!-- Title -->
	<spring:message code="position.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

    <!-- Description -->
    <spring:message code="position.description" var="descriptionHeader" />
    <display:column property="description" title="${descriptionHeader}" />

    <!-- Deadline -->
    <spring:message code="position.deadline" var="deadlineHeader" />
    <display:column property="deadline" title="${deadlineHeader}" />


</display:table>

<security:authorize access="hasRole('COMPANY')">
	<a href=position/company/create.do><spring:message code="position.create" /></a>
</security:authorize>