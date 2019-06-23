<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="provider/create.do" modelAttribute="providerForm">
	
	<%-- username--%>
	<acme:textbox code="provider.username" path="userAccount.username" />
	<br>

	<%-- password--%>
	<acme:password code="provider.password" path="userAccount.password" />
	<br>
	
	<%-- Confirm password--%>
	<acme:password code="provider.ConfirmPassword" path="confirmPassword" />
	<br>

	<%-- Name --%>
	<acme:textbox code="provider.name" path="name" />
	<br>

	<%-- Surname --%>
	<acme:textbox code="provider.surname" path="surname" />
	<br>
	
	<%-- Make --%>
	<acme:textbox code="provider.make" path="make" />
	<br>

	<%-- Photo --%>
	<acme:textbox code="provider.photo" path="photo" />
	<br>

	<%-- Phone --%>
	<acme:textbox code="provider.phoneNumber" path="phoneNumber" />
	<br>
	
	<%-- VAT --%>
	<acme:textbox code="provider.vat" path="vat" />
	<br>

	<%-- email --%>
	<acme:textbox code="provider.email" path="email" />
	<br>

	<%-- Address --%>
	<acme:textbox code="provider.address" path="address" />
	<br>
	
	<%-- holderName --%>
	<acme:textbox code="provider.creditCard.holderName" path="creditCard.holderName" />
	<br>
	
	<%-- holderName --%>
	<acme:textbox code="provider.creditCard.brandName" path="creditCard.brandName" />
	<br>
	
	<%-- Number --%>
	<acme:textbox code="provider.creditCard.number" path="creditCard.number" />
	<br>
	
	<%-- expiryMonth --%>
	<acme:textbox code="provider.creditCard.expiryMonth" path="creditCard.expiryMonth" />
	<br>
	
	<%-- expiryYear --%>
	<acme:textbox code="provider.creditCard.expiryYear" path="creditCard.expiryYear" />
	<br>
	
	<%-- cvv --%>
	<acme:textbox code="provider.creditCard.cvv" path="creditCard.cvv" />
	<br>
	
	<p><input id="field_terms" onchange="this.setCustomValidity(validity.valueMissing ? '<spring:message code="provider.check.terms"/>' : '');" type="checkbox" required name="terms"><spring:message code="provider.terms"/></p>

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
				return confirm('<spring:message code="rookie.confirm"/>');
		}
		
	</script>

	<%-- Buttons --%>
	<input type="submit" name="save" value="<spring:message code="provider.save"/>" onClick="javascript: return phoneNumberValidator()"/>
	<acme:cancel code="provider.cancel" url="/"/>
	
</form:form>

<script>

  document.getElementById("field_terms").setCustomValidity("<spring:message code="provider.check.terms"/>");

</script>