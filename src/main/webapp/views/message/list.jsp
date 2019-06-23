<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<fieldset>
    <legend>
        <spring:message code="message.searchTag" />
    </legend>
		<form action="message/list.do" method="GET">
	       	<input type="text" name="searcher" /> <input type="submit" value="<spring:message code = "message.searcher" />" />
	   	</form>
</fieldset>

<h2> <spring:message code="message.inboxMessages"/> </h2>

<display:table name="inbox" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

    <display:column>
        <a href="message/delete.do?messageId=${row.id}">
            <spring:message code="message.delete"/>
        </a>
    </display:column>

    <display:column>
        <a href="message/show.do?messageId=${row.id}">
            <spring:message code="message.show"/>
        </a>
    </display:column>

    <!-- Sender -->
    <spring:message code="message.sender" var="senderHeader" />
    <display:column property="sender.name" title="${senderHeader}" />
	
	<!-- Subject -->
	<spring:message code="message.subject" var="subjectHeader" />
	<display:column property="subject" title="${subjectHeader}" />

    <!-- Body -->
    <spring:message code="message.body" var="bodyHeader" />
    <display:column property="body" title="${bodyHeader}" />

    <!-- Moment -->
    <spring:message code="message.moment" var="momentHeader" />
    <display:column property="moment" title="${momentHeader}" />
    
    <!-- Tags -->
    <spring:message code="message.tags" var="tagsHeader" />
    <display:column property="tags" title="${tagsHeader}" />

</display:table>

<h2> <spring:message code="message.outboxMessages"/> </h2>

<display:table name="outbox" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

    <display:column>
        <a href="message/delete.do?messageId=${row.id}">
            <spring:message code="message.delete"/>
        </a>
    </display:column>

    <display:column>
        <a href="message/show.do?messageId=${row.id}">
            <spring:message code="message.show"/>
        </a>
    </display:column>

    <!-- Sender -->
    <spring:message code="message.sender" var="senderHeader" />
    <display:column property="sender.name" title="${senderHeader}" />

    <!-- Subject -->
    <spring:message code="message.subject" var="subjectHeader" />
    <display:column property="subject" title="${subjectHeader}" />

    <!-- Body -->
    <spring:message code="message.body" var="bodyHeader" />
    <display:column property="body" title="${bodyHeader}" />

    <!-- Moment -->
    <spring:message code="message.moment" var="momentHeader" />
    <display:column property="moment" title="${momentHeader}" />
    
    <!-- Tags -->
    <spring:message code="message.tags" var="tagsHeader" />
    <display:column property="tags" title="${tagsHeader}" />

</display:table>

<a href=message/create.do><spring:message code="message.create" /></a>
