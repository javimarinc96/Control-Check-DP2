<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<fieldset>

    <legend>
        <spring:message code="item.searchText" />
    </legend>
    
    <security:authorize access="isAuthenticated()">
		<form action="item/list.do" method="GET">
        	<input type="text" name="searcher" /> <input type="submit" value="<spring:message code = "item.searcher" />" />
    	</form>
	</security:authorize>
    <security:authorize access="isAnonymous()">
		<form action="item/anonymousList.do" method="GET">
	       	<input type="text" name="searcher" /> <input type="submit" value="<spring:message code = "item.searcher" />" />
	   	</form>
	</security:authorize>
</fieldset>

<display:table name="items" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

   <security:authorize access="hasRole('PROVIDER')">
        <display:column>
        	<a href="item/edit.do?itemId=${row.id}">
                <spring:message code="item.edit"/>
            </a>
        </display:column>
   </security:authorize>

    <display:column>
        <a href="item/show.do?itemId=${row.id}">
            <spring:message code="item.show"/>
        </a>
    </display:column>

    <display:column>
        <a href="provider/show.do?providerId=${row.provider.id}">
            <spring:message code="item.provider"/>
        </a>
    </display:column>

    <!-- Name -->
    <spring:message code="item.name" var="nameHeader" />
    <display:column property="name" title="${nameHeader}" />
	
	<!-- Description -->
	<spring:message code="item.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

    <!-- Link -->
    <spring:message code="item.link" var="linkHeader" />
    <display:column property="link" title="${linkHeader}" />

</display:table>

<security:authorize access="hasRole('PROVIDER')">
	<a href=item/create.do><spring:message code="item.create" /></a>
</security:authorize>