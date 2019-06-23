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

<display:table name="curriculas" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">



	<spring:message code="curricula.id" var="idHeader" />
	<display:column property="id" title="${idHeader}" />
	

	     <display:column titleKey="curricula.display">
                <input type="submit" name="show" value="<spring:message code="curricula.display" />"
				onclick="javascript: relativeRedir('curricula/show.do?curriculaId=${row.id}');" />
        </display:column>
        
	    <display:column titleKey="curricula.delete">
        <input type="submit" name="delete" value="<spring:message code="curricula.delete" />"
				onclick="javascript: relativeRedir('curricula/delete.do?curriculaId=${row.id}');" />
        </display:column>
        



</display:table>


<input type="submit" name="save" value="<spring:message code="curricula.create" />"
				onclick="javascript: relativeRedir('curricula/create.do');" />

