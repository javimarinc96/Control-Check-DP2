<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="sponsorship/provider/create.do" modelAttribute="sponsorship">

		<form:hidden path="provider" />
	
	

	<%-- Banner --%>
	<acme:textbox code="sponsorship.banner" path="banner" />
	<br>
	
	<%-- SocialNetworkName --%>
	<acme:textbox code="sponsorship.targetPage" path="targetPage" />
	<br>
	
	<!--Position-->
	<form:label path="position">
		<spring:message code="sponsorship.position" />:
	</form:label>
	<form:select id="positions" path="position">
		<form:option value="0" label="----" />
		<jstl:forEach items="${positions}" var="p">
			<form:option value="${p.id}" label="${p.ticker}" />
		</jstl:forEach>
	</form:select>
	<form:errors cssClass="error" path="position" />
	


	

	<input type="submit" name="save"
		value="<spring:message code="sponsorship.save"/>"/>
	
	<acme:cancel code="sponsorship.cancel" url="sponsorship/provider/list.do" />
	
</form:form>

