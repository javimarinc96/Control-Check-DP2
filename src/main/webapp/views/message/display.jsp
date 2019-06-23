<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="mess" id="row" requestURI="message/display.do" class="displaytag">

	<!-- Folder -->
	<spring:message code="message.folder" var="folderHeader" />
	<display:column property="folder.name" title="${folderHeader}" />

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



<acme:cancel code="message.cancel" url="/message/list.do" />

