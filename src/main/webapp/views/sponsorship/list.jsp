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

<display:table name="sponsorships" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">



	 <display:column>
              <a href="sponsorship/provider/edit.do?sponsorshipId=${row.id}">
                <spring:message code="sponsorship.edit"/>
              </a>
        </display:column>
        
        <display:column>
              <a href="sponsorship/provider/show.do?sponsorshipId=${row.id}">
                <spring:message code="sponsorship.display"/>
              </a>
        </display:column>
        
         <display:column titleKey="sponsorship.delete">
        <input type="submit" name="delete" value="<spring:message code="sponsorship.delete" />"
				onclick="javascript: relativeRedir('sponsorship/provider/delete.do?sponsorshipId=${row.id}');" />
        </display:column>

	<spring:message code="sponsorship.banner" var="bannerHeader" />
	<display:column property="banner" title="${bannerHeader}" />
	
	<spring:message code="sponsorship.targetPage" var="targetPageHeader" />
	<display:column property="targetPage" title="${targetPageHeader}" />
	
	<spring:message code="sponsorship.position" var="positionHeader" />
	<display:column property="position" title="${positionHeader}" />
	
	
	
	</br>

	

</display:table>

<a href="sponsorship/provider/create.do"> <spring:message code="sponsorship.create" />
	</a>
