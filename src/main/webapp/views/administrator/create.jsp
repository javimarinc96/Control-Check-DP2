<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="administrator/create.do" modelAttribute="administratorForm">




	<%-- UserAccount--%>

	<%-- username--%>
	<acme:textbox code="administrator.username" path="userAccount.username" />
	<br>

	<%-- password--%>


	<acme:textbox code="administrator.password" path="userAccount.password" />
	<br>


	<%-- confirmPassword--%>
	<acme:password code="administrator.ConfirmPassword" path="confirmPassword" />
	<br>


	<%-- Name --%>

	<acme:textbox code="administrator.name" path="name" />
	<br>


	


	<%-- Surname --%>


	<acme:textbox code="administrator.surname" path="surname" />
	<br>

	<%-- Photo --%>


	<acme:textbox code="administrator.photo" path="photo" />
	<br>

	<%-- Phone --%>



	<acme:textbox code="administrator.phone" path="phoneNumber" />
	<br>
	
	<%-- VAT --%>
	<acme:textbox code="administrator.vat" path="vat" />
	<br>

	<%-- email --%>


	<acme:textbox code="administrator.email" path="email" />
	<br>

	<%-- Address --%>


	<acme:textbox code="administrator.address" path="address" />
	<br>




	<%-- Credit Card Data --%>




	<acme:textbox code="administrator.creditCard.holderName"
		path="creditCard.holderName" />
	<br>



	<acme:textbox code="administrator.creditCard.brandName"
		path="creditCard.brandName" />
	<br>




	<acme:textbox code="administrator.creditCard.number" path="creditCard.number" />
	<br>



	<acme:textbox code="administrator.creditCard.expiryMonth"
		path="creditCard.expiryMonth" />
	<br>



	<acme:textbox code="administrator.creditCard.expiryYear"
		path="creditCard.expiryYear" />
	<br>



	<acme:textbox code="administrator.creditCard.cvv" path="creditCard.cvv" />
	<br>


	<script type="text/javascript">
		function phoneNumberValidator() {

			var phoneNumber = document.getElementById("phoneNumber").value;

			var patternCCACPN = /^(\+[1-9][0-9]{0,2}) (\([1-9][0-9]{0,2}\)) (\d{3}\d+)/
			$;
			var patternCCPN = /^(\+[1-9][0-9]{0,2}) (\d{3}\d+)/
			$;
			var patternPN = /^(\d{3}\d+)/
			$;

			if (patternCCACPN.test(phoneNumber))
				return true;
			else if (patternCCPN.test(phoneNumber))
				return true;
			else if (patternPN.test(phoneNumber))
				return true;
			else
				return confirm('<spring:message code="administrator.confirm"/>');
		}
	</script>
	<%-- Buttons --%>
	<security:authorize access="hasRole('ADMIN')">
		<input type="submit" name="save"
			value="<spring:message code="administrator.save" />"
			onClick="javascript: return phoneNumberValidator()" />

		<!-- <input type="submit" name="delete" value="<spring:message code="administrator.delete"/>" -->

		<input type="button" name="cancel"
			value="<spring:message code="administrator.cancel" />"
			onClick="javascript: window.location.replace('administrator/list.do')" />
	</security:authorize>
	<br>
	<br>
</form:form>
