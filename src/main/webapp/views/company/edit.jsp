<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="company/edit.do" modelAttribute="company">

	<%-- Hidden properties from company--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount" />
	<form:hidden path="socialProfiles" />




	<%-- username--%>


	<%-- Name --%>
	<acme:textbox code="company.name" path="name" />
	<br>


	<%-- Surname --%>
	<acme:textbox code="company.surname" path="surname" />
	<br>

	<%-- Photo --%>
	<acme:textbox code="company.photo" path="photo" />
	<br>

	<%-- Phone --%>
	<acme:textbox code="company.phoneNumber" path="phoneNumber" />
	<br>

	<%-- VAT --%>
	<acme:textbox code="company.vat" path="vat" />
	<br>

	<%-- email --%>
	<acme:textbox code="company.email" path="email" />
	<br>

	<%-- Address --%>
	<acme:textbox code="company.address" path="address" />
	<br>

	<%-- Commercial Name --%>
	<acme:textbox code="company.commercialName" path="commercialName" />
	<br>


	<%-- holderName --%>
	<acme:textbox code="company.creditCard.holderName"
		path="creditCard.holderName" />
	<br>


	<%-- holderName --%>
	<acme:textbox code="company.creditCard.brandName"
		path="creditCard.brandName" />
	<br>


	<%-- Number --%>
	<acme:textbox code="company.creditCard.number" path="creditCard.number" />
	<br>

	<%-- expiryMonth --%>
	<acme:textbox code="company.creditCard.expiryMonth"
		path="creditCard.expiryMonth" />
	<br>

	<%-- expiryYear --%>
	<acme:textbox code="company.creditCard.expiryYear"
		path="creditCard.expiryYear" />
	<br>

	<%-- cvv --%>
	<acme:textbox code="company.creditCard.cvv" path="creditCard.cvv" />
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
				return confirm('<spring:message code="company.confirm"/>');
		}
	</script>

	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="company.save"/>"
		onClick="javascript: return phoneNumberValidator()" />

	<acme:cancel code="company.cancel" url="/" />
</form:form>