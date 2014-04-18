<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- BEAN: Leggo i dati dell'indirizzo da modificare --%>
<s:bean name="it.store.bean.rubrica.IndirizzoBean" var="indirizzo">
	<s:param name="utente"><s:property value="#session.userData.email" /></s:param>
	<s:param name="id_indirizzo"><s:property value="#parameters.id_indirizzo" /></s:param>
</s:bean>

<s:if test="hasActionErrors()">
   <div class="actionError">
      <s:iterator value="actionErrors">
        <div align="center">
			<span><s:property escape="false" /></span>
		</div>
	  </s:iterator>
   </div>
   <br>
</s:if>
<s:if test="hasActionMessages()">
   <div class="actionMessage">
      <s:iterator value="actionMessages">
        <div align="center">
			<span><s:property escape="false" /></span>
		</div>
	  </s:iterator>
   </div>
   <br>
</s:if>

<table class="commonTable" style="width: 80%;">

	<tr class="commonTr">
		<th class="commonTh">
			<s:property value="getText('rubricaForm.titolo')" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:push value="#indirizzo">
				<s:form action="aggiungiIndirizzo" namespace="/account" cssStyle="width: 100%;">
					<s:hidden key="email" value="%{#session.userData.email}" />
					<s:if test="%{#indirizzo.id_indirizzo!=-1}">
						<s:hidden key="id_indirizzo" value="%{#request.id_indirizzo}" />
					</s:if>
					<s:textfield label="%{getText('rubricaForm.nome_cognome')} *" key="nome_cognome" maxlength="46" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.indirizzo_1')} *" key="indirizzo_1" maxlength="50" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.indirizzo_2')}" key="indirizzo_2" maxlength="50" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.citta')} *" key="citta" maxlength="40" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.provincia')} *" key="provincia" maxlength="40" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.cap')} *" key="cap" maxlength="5" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.paese')} *" key="paese" maxlength="40" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.telefono')}" key="telefono" maxlength="11" cssStyle="width: 70%;" />
					<s:if test="%{#indirizzo.id_indirizzo==-1}">
						<s:submit value="%{getText('rubricaForm.submit.aggiungi')}" />
					</s:if>
					<s:else>
						<s:submit value="%{getText('rubricaForm.submit.modifica')}" />
					</s:else>
				</s:form>
			</s:push>
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" colspan="2">
			* : <s:property value="getText('form.campo_obbligo')" />
		</td>
	</tr>

</table>