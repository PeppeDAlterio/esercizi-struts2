<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- BEAN: Leggo i dati dell'ordine --%>
<s:bean name="it.store.bean.ordine.DatiOrdineBean" var="ordineBean">
	<s:param name="utente" value="#session.userData.email" />
	
	<s:param name="id_ordine" value="#parameters.id_ordine" />
</s:bean>

<table class="commonTable">
	
</table>