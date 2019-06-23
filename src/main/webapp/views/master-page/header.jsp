<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="${banner}" alt="${title}" width="1000" height="300" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/create.do"><spring:message code="master.page.administrator.createAdmin" /></a></li>
					<li><a href="auditor/create.do"><spring:message code="master.page.auditor.createAuditor" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.dashboard" /></a></li>
					<li><a href="configurations/administrator/edit.do"><spring:message code="master.page.administrator.configurations" /></a></li>
					<li><a href="company/list.do"><spring:message code="master.page.company.compute" /></a></li>
					<li><a href="administrator/spammers.do"><spring:message code="master.page.administrator.spammers" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUDITOR')">
			<li><a class="fNiv"><spring:message	code="master.page.audits" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="audit/auditor/list.do"><spring:message code="master.page.audits.list" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('COMPANY')">
			<li><a class="fNiv"><spring:message	code="master.page.problems" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="problem/company/list.do"><spring:message code="master.page.problems.list" /></a></li>				
				</ul>
			</li>
						<li><a class="fNiv"><spring:message	code="master.page.testEntity" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="testEntity/list.do"><spring:message code="master.page.testEntity.list" /></a></li>	
				</ul>
			</li>
				<li><a class="fNiv"><spring:message	code="master.page.profile" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="company/edit.do"><spring:message code="master.page.company.edit" /></a></li>	
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
								
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.application" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="application/list.do"><spring:message code="master.page.application.list" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
	<security:authorize access="hasRole('PROVIDER')">
			
				<li><a class="fNiv"><spring:message	code="master.page.profile" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="provider/edit.do"><spring:message code="master.page.provider.edit" /></a></li>	
						<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>		
					</ul>
				</li>
				
				<li><a class="fNiv"><spring:message	code="master.page.item" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="item/list.do"><spring:message code="master.page.item.list" /></a></li>
					</ul>
				</li>
				
				<li><a class="fNiv"><spring:message	code="master.page.sponsorship" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="sponsorship/provider/list.do"><spring:message code="master.page.sponsorship.list" /></a></li>
					</ul>
				</li>
				
		</security:authorize> 
		
		<security:authorize access="hasRole('ROOKIE')">
			
				<li><a class="fNiv"><spring:message	code="master.page.profile" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="rookie/edit.do"><spring:message code="master.page.rookie.edit" /></a></li>	
						<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
									
					</ul>
				</li>
				
				<li><a class="fNiv"><spring:message	code="master.page.application" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="application/list.do"><spring:message code="master.page.application.list" /></a></li>
					</ul>
				</li>
				
				<li><a class="fNiv"><spring:message	code="master.page.curriculum" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="curricula/list.do"><spring:message code="master.page.curriculum" /></a></li>
					</ul>
				</li>
				
				<li><a class="fNiv"><spring:message	code="master.page.finder" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="finder/show.do"><spring:message code="master.page.finder" /></a></li>
					</ul>
				</li>
				
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.position" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="position/anonymousList.do"><spring:message code="master.page.position.list" /></a></li>
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.company" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="company/list.do"><spring:message code="master.page.company.list" /></a></li>
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.provider" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="provider/list.do"><spring:message code="master.page.provider.list" /></a></li>
					</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.item" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="item/anonymousList.do"><spring:message code="master.page.item.list" /></a></li>
					</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="rookie/create.do"><spring:message code="master.page.rookie.register" /></a></li>	
					<li><a href="company/create.do"><spring:message code="master.page.company.register" /></a></li>
					<li><a href="provider/create.do"><spring:message code="master.page.provider.register" /></a></li>				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"><spring:message	code="master.page.position" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="position/list.do"><spring:message code="master.page.position.list" /></a></li>
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.socialProfile" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="socialProfile/list.do"><spring:message code="master.page.socialProfile.list" /></a></li>
					<li><a href="socialProfile/forgetMe.do"><spring:message code="master.page.socialProfile.forget" /></a></li>
					<li><a href="socialProfile/exportData.do"><spring:message code="master.page.socialProfile.exportData" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.message" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/list.do"><spring:message code="master.page.message.list" /></a></li>
					<li><a href="message/create.do"><spring:message code="master.page.message.create" /></a></li>
				</ul>
			</li>
				
			
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

