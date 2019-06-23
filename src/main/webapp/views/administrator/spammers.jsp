<%--
 * suspicious.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!--Tabla-->

<display:table name="spammers" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

<!-- La lista con el bot�n de banear en cada fila-->
	
	<display:column property="name" titleKey="actor.name" sortable="true"/>
	<display:column property="surname" titleKey="actor.surname" sortable="true"/>
	<display:column property="userAccount.username" titleKey="actor.username" sortable="true"/>
	
	<jstl:if test="${row.isBanned == false}">
	<display:column titleKey="administrator.ban">
                <input type="submit" name="ban" value="<spring:message code="administrator.ban" />"
				onclick="javascript: relativeRedir('administrator/ban.do?actorId=${row.id}');" />
     </display:column>
     </jstl:if>
           
	
      <jstl:if test="${row.isBanned == true}">
            <display:column titleKey="administrator.unban">
               <input type="submit" name="unban" value="<spring:message code="administrator.unban" />"
				onclick="javascript: relativeRedir('administrator/unban.do?actorId=${row.id}');" />
                </display:column>
       </jstl:if>
	
</display:table>

	<input type="submit" name="compspammers" value="<spring:message code="administrator.spammers.compute" />"
	onclick="javascript: relativeRedir('administrator/compspammers.do');" />