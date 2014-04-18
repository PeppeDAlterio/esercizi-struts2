<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="{#parameters.id_indirizzo!=null}">
	<%-- BEAN: Leggo i dati dell'indirizzo da modificare --%>
	<s:bean name="it.store.bean.rubrica.IndirizzoBean" var="indirizzo">
		<s:param name="utente"><s:property value="#session.userData.email" /></s:param>
		<s:param name="id_indirizzo"><s:property value="#parameters.id_indirizzo" /></s:param>
	</s:bean>
</s:if>
<s:else>
	<s:set value="" var="indirizzo" scope="page" />
</s:else>

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

<table class="commonTable" style="width: 90%;">
		
	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="cassaForm.indirizzo" />
		</th>
	</tr>
	
	<tr>
		<td style="text-aling: center;">
			<s:a action="listaIndirizziRubrica" namespace="/ordine" target="popup" onclick="window.open('','popup','width=300,height=200')">
				<s:text name="cassaForm.indirizzo_da_rubrica" />
			</s:a>
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="indirizzoOrdine" namespace="/ordine" cssStyle="width: 90%;">
				<s:push value="indirizzo">
						<s:textfield label="%{getText('rubricaForm.nome_cognome')} *" key="nome_cognome" maxlength="46" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.indirizzo_1')} *" key="indirizzo_1" maxlength="50" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.indirizzo_2')}" key="indirizzo_2" maxlength="50" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.citta')} *" key="citta" maxlength="40" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.provincia')} *" key="provincia" maxlength="40" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.cap')} *" key="cap" maxlength="5" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.paese')} *" key="paese" maxlength="40" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.telefono')}" key="telefono" maxlength="11" cssStyle="width: 70%;" />
						<s:submit value="%{getText('cassaForm.indirizzo_submit')}" />
				</s:push>
			</s:form>
		</td>
	</tr>
	
	
</table>