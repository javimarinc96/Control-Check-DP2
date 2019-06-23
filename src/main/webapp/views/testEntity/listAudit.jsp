<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<spring:message code="testEntity.moment.pattern" var="momentPattern" />

<h2> <spring:message code="testEntity.1Month"/> </h2>
	<display:table name="oneMonthOlds" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
		
		<spring:message code="testEntity.ticker" var="ticker" />
		<display:column style="background-color: Indigo" property="ticker" title="${ticker}" />
		
		<spring:message code="testEntity.moment" var="moment" />
		<display:column style="background-color: Indigo" property="moment" title="${moment}" format="${momentPattern}"/>
		
		<spring:message code="testEntity.body" var="body" />
		<display:column style="background-color: Indigo" property="body" title="${body}" />
		
		
	</display:table>

<h2> <spring:message code="testEntity.2Months"/> </h2>
	<display:table name="twoMonthOlds" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
		
		<spring:message code="testEntity.ticker" var="ticker" />
		<display:column style="background-color: DarkSlateGrey" property="ticker" title="${ticker}" />
		
		<spring:message code="testEntity.moment" var="moment" />
		<display:column style="background-color: DarkSlateGrey" property="moment" title="${moment}"  format="${momentPattern}"/>
		
		<spring:message code="testEntity.body" var="body" />
		<display:column style="background-color: DarkSlateGrey" property="body" title="${body}" />
			
	</display:table>
	
	<br/>
	
<h2> <spring:message code="testEntity.3months"/> </h2>
	<display:table name="threeMonthOlds" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
		
		<spring:message code="testEntity.ticker" var="ticker" />
		<display:column style="background-color: PapayaWhip" property="ticker" title="${ticker}" />
		
		<spring:message code="testEntity.moment" var="moment" />
		<display:column style="background-color: PapayaWhip" property="moment" title="${moment}" format="${momentPattern}"/>
		
		<spring:message code="testEntity.body" var="body" />
		<display:column style="background-color: PapayaWhip" property="body" title="${body}" />
		
	</display:table>

	<security:authorize access="hasRole('AUDITOR')">	
				<button type="button" onclick="javascript: relativeRedir('audit/auditor/list.do')">
				<spring:message code="testEntity.cancel" />
				</button>
	</security:authorize>