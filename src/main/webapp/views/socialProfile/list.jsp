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

<display:table name="socialProfiles" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">



	 <display:column>
              <a href="socialProfile/edit.do?socialProfileId=${row.id}">
                <spring:message code="socialProfile.edit"/>
              </a>
        </display:column>
        
        <display:column>
              <a href="socialProfile/show.do?socialProfileId=${row.id}">
                <spring:message code="socialProfile.display"/>
              </a>
        </display:column>

	<spring:message code="socialProfile.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" />
	
	<spring:message code="socialProfile.socialNetworkName" var="socialNetworkNameHeader" />
	<display:column property="socialNetworkName" title="${socialNetworkNameHeader}" />
	
	<spring:message code="socialProfile.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}" autolink = "true" />
	
	</br>

	

</display:table>

<a href="socialProfile/create.do"> <spring:message code="socialProfile.create" />
	</a>
