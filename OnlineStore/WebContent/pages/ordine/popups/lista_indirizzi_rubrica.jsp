<%@ taglib prefix="s" uri="/struts-tags" %>


<%-- BEAN: Leggo la rubrica indirizzi dell'utente in session --%>
<s:bean name="it.store.bean.rubrica.RubricaBean" var="rubrica">
	<s:param name="utente">
		<s:property value="#session.userData.email" />
	</s:param>
</s:bean>

<script type="text/javascript">



function carica_indirizzo() {
	var opener = window.opener;
	
	var indirizzi = document.getElementById("indirizzi");
	
	var indirizzo = indirizzi.options[indirizzi.selectedIndex].value;
			
	opener.location.href = opener.location.pathname+"?id_indirizzo="+indirizzo;
	
	window.close();
}

</script>

<h3>
	<s:text name="cassaForm.lista_rubrica" />
</h3>

<select id="indirizzi">
<s:iterator value="#rubrica.indirizzi" var="indirizzo">
	<option value="<s:property value='#indirizzo.id_indirizzo' />">
		<s:property value="#indirizzo.nome_cognome" /> - <s:property value="#indirizzo.indirizzo_1" />
	</option>
</s:iterator>
</select>

<input type="button" onclick="carica_indirizzo();" value="SUBMIT" />
<br>

