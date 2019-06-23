<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="auditor/create.do" modelAttribute="auditorForm">

	<%-- username--%>
	<acme:textbox code="auditor.username" path="userAccount.username" />
	<br>

	<%-- password--%>
	<acme:password code="auditor.password" path="userAccount.password" />
	<br>
	
	<%-- Confirm password--%>
	<acme:password code="auditor.ConfirmPassword" path="confirmPassword" />
	<br>

	<%-- Name --%>
	<acme:textbox code="auditor.name" path="name" />
	<br>

	<%-- Surname --%>
	<acme:textbox code="auditor.surname" path="surname" />
	<br>

	<%-- Photo --%>
	<acme:textbox code="auditor.photo" path="photo" />
	<br>

	<%-- Phone --%>
	<acme:textbox code="auditor.phoneNumber" path="phoneNumber" />
	<br>
	
	<%-- VAT --%>
	<acme:textbox code="auditor.vat" path="vat" />
	<br>

	<%-- email --%>
	<acme:textbox code="auditor.email" path="email" />
	<br>

	<%-- Address --%>
	<acme:textbox code="auditor.address" path="address" />
	<br>
	
<%-- holderName --%>
	<acme:textbox code="auditor.creditCard.holderName" path="creditCard.holderName" />
	<br>
	
	<%-- holderName --%>
	<acme:textbox code="auditor.creditCard.brandName" path="creditCard.brandName" />
	<br>
	
	<%-- Number --%>
	<acme:textbox code="auditor.creditCard.number" path="creditCard.number" />
	<br>
	
	<%-- expiryMonth --%>
	<acme:textbox code="auditor.creditCard.expiryMonth" path="creditCard.expiryMonth" />
	<br>
	
	<%-- expiryYear --%>
	<acme:textbox code="auditor.creditCard.expiryYear" path="creditCard.expiryYear" />
	<br>
	
	<%-- cvv --%>
	<acme:textbox code="auditor.creditCard.cvv" path="creditCard.cvv" />
	<br>
	
	
	<p><input id="field_terms" onchange="this.setCustomValidity(validity.valueMissing ? '<spring:message code="auditor.check.terms"/>' : '');" type="checkbox" required name="terms">
	<spring:message code="auditor.terms"/></p>

	<script type="text/javascript">

		function phoneNumberValidator() {

			var phoneNumber = document.getElementById("phone").value;

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
				return confirm('<spring:message code="auditor.confirm"/>');
		}
		
	</script>
	

	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="auditor.save"/>"
		onClick="javascript: return phoneNumberValidator()"/>
	<acme:cancel code="auditor.cancel" url="/"/>
	
</form:form>

<script>

  document.getElementById("field_terms").setCustomValidity("<spring:message code="auditor.check.terms"/>");

</script>