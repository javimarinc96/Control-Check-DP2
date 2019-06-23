<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="personalDatas" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">



	 <display:column>
              <a href="personalData/edit.do?personalDataId=${row.id}">
                <spring:message code="personalData.edit"/>
              </a>
        </display:column>
        
        <display:column>
              <a href="personalData/show.do?personalDataId=${row.id}">
                <spring:message code="personalData.display"/>
              </a>
        </display:column>

	<spring:message code="personalData.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />
	
	<spring:message code="personalData.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />
	
	<spring:message code="personalData.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}" />
	
	<spring:message code="personalData.endDate" var="endDateHeader" />
	<display:column property="endDate" title="${endDateHeader}" />
	
	</br>

		<a href="personalData/create.do"> <spring:message code="personalData.create" />
		</a>

</display:table>