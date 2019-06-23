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

<!-------------------------------------- POSITION DATA ---------------------------------------->

<h2><spring:message code="curricula.positionData" /></h2>


<display:table name="positionsData" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">


	<spring:message code="curricula.positionData.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="curricula.positionData.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />
	
		
        <display:column titleKey="curricula.positionData.display">
               <input type="submit" name="show" value="<spring:message code="curricula.positionData.display" />"
				onclick="javascript: relativeRedir('positionData/show.do?positionDataId=${row.id}');" />
        </display:column>
         

	<display:column titleKey="curricula.positionData.edit">
                <input type="submit" name="edit" value="<spring:message code="curricula.positionData.edit" />"
				onclick="javascript: relativeRedir('positionData/edit.do?positionDataId=${row.id}');" />
        </display:column>
	
	    <display:column titleKey="curricula.delete">
        <input type="submit" name="delete" value="<spring:message code="curricula.delete" />"
				onclick="javascript: relativeRedir('positionData/delete.do?positionDataId=${row.id}');" />
                </display:column>
        

</display:table>
              
           <input type="submit" name="create" value="<spring:message code="curricula.positionData.create" />"
				onclick="javascript: relativeRedir('positionData/create.do?curriculaId=${curriculaId}');" />


<!-------------------------------------- EDUCATION DATA ---------------------------------------->

<h2><spring:message code="curricula.educationData" /></h2>


<display:table name="educationsData" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">


	
	<spring:message code="curricula.educationData.institution" var="institutionHeader" />
	<display:column property="institution" title="${institutionHeader}" />
	
	<spring:message code="curricula.educationData.mark" var="markHeader" />
	<display:column property="mark" title="${markHeader}" />

       <display:column titleKey="curricula.educationData.display">
               <input type="submit" name="edit" value="<spring:message code="curricula.educationData.display" />"
				onclick="javascript: relativeRedir('educationData/show.do?educationDataId=${row.id}');" />
        </display:column>
        
	<display:column titleKey="curricula.educationData.edit">
                <input type="submit" name="edit" value="<spring:message code="curricula.educationData.edit" />"
				onclick="javascript: relativeRedir('educationData/edit.do?educationDataId=${row.id}');" />
        </display:column>
        
        <display:column titleKey="curricula.delete">
         <input type="submit" name="delete" value="<spring:message code="curricula.delete" />"
				onclick="javascript: relativeRedir('educationData/delete.do?educationDataId=${row.id}');" />
          </display:column>

</display:table>

      <input type="submit" name="delete" value="<spring:message code="curricula.educationData.create" />"
	onclick="javascript: relativeRedir('educationData/create.do?curriculaId=${curriculaId}');" />
	
<!-------------------------------------- MISCELLANEOUS DATA ---------------------------------------->

<h2><spring:message code="curricula.miscellaneousData" /></h2>


<display:table name="miscellaneousData" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="curricula.miscellaneousData.freeText" var="freeTextHeader" />
	<display:column property="freeText" title="${freeTextHeader}" />

	 <display:column titleKey="curricula.miscellaneousData.display">
                  <input type="submit" name="show" value="<spring:message code="curricula.miscellaneousData.display" />"
				onclick="javascript: relativeRedir('miscellaneousData/show.do?miscellaneousDataId=${row.id}');" />
        </display:column>
        
	<display:column titleKey="curricula.miscellaneousData.edit">
               <input type="submit" name="edit" value="<spring:message code="curricula.miscellaneousData.edit" />"
				onclick="javascript: relativeRedir('miscellaneousData/edit.do?miscellaneousDataId=${row.id}');" />
        </display:column>
       

</display:table>

<!-------------------------------------- PERSONAL DATA ---------------------------------------->

<h2><spring:message code="curricula.personalData" /></h2>


<display:table name="personalData" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

		<spring:message code="curricula.personalData.fullName" var="fullNameHeader" />
		<display:column property="fullName" title="${fullNameHeader}" />
	
	  <display:column titleKey="curricula.personalData.display">
               <input type="submit" name="show" value="<spring:message code="curricula.personalData.display" />"
				onclick="javascript: relativeRedir('personalData/show.do?personalDataId=${row.id}');" />
        </display:column>

	<display:column titleKey="curricula.personalData.edit">
                 <input type="submit" name="edit" value="<spring:message code="curricula.personalData.edit" />"
				onclick="javascript: relativeRedir('personalData/edit.do?personalDataId=${row.id}');" />
        </display:column>
        

	

</display:table>