<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
	function conferma(str) {
		if(confirm(str)) {
			return true;
		} else {
			return false;
		}
	}
</script>

<%-- BEAN: Leggo la rubrica indirizzi dell'utente in session --%>
<s:bean name="it.store.bean.rubrica.RubricaBean" var="rubrica">
	<s:param name="utente">
		<s:property value="#session.userData.email" />
	</s:param>
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
		<th class="commonTh" colspan="2">
			<s:property value="getText('rubricaView.titolo')" />
		</th>
	</tr>
<s:if test="%{#rubrica.indirizzi!=null}">
<s:iterator value="#rubrica.indirizzi" var="indirizzo" status="status">
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<table class="commonTable" style="margin: auto; width: 90%;">
				<tr class="commonTr">
					<th class="commonTh" colspan="2" style="text-align: left;">
						<s:property value="getText('rubricaView.ind_num')" /><s:property value="%{#status.index + 1}" />
					</th>
				</tr>
				
				<tr class="commonTr">
					<td class="commonTd" style="text-align: left;">
						<s:property value="getText('rubricaForm.nome_cognome')" />:
					</td>
					<td class="commonTd" style="text-align: left;">
						<s:property value="%{#indirizzo.nome_cognome}" />
					</td>
				</tr>
				
				<tr class="commonTr">
					<td class="commonTd" style="text-align: left;">
						<s:property value="getText('rubricaForm.indirizzo_1')" />:
					</td>
					<td class="commonTd" style="text-align: left;">
						<s:property value="%{#indirizzo.indirizzo_1}" />
					</td>
				</tr>
				
				<tr class="commonTr">
					<td class="commonTd" style="text-align: left;">
						<s:property value="getText('rubricaForm.indirizzo_2')" />:
					</td>
					<td class="commonTd" style="text-align: left;">
						<s:property value="%{#indirizzo.indirizzo_2}" />
					</td>
				</tr>
				
				<tr class="commonTr">
					<td class="commonTd" style="text-align: left;">
						<s:property value="getText('rubricaForm.citta')" />:
					</td>
					<td class="commonTd" style="text-align: left;">
						<s:property value="%{#indirizzo.citta}" />
					</td>
				</tr>
				
				<tr class="commonTr">
					<td class="commonTd" style="text-align: left;">
						<s:property value="getText('rubricaForm.provincia')" />:
					</td>
					<td class="commonTd" style="text-align: left;">
						<s:property value="%{#indirizzo.provincia}" />
					</td>
				</tr>
				
				<tr class="commonTr">
					<td class="commonTd" style="text-align: left;">
						<s:property value="getText('rubricaForm.cap')" />:
					</td>
					<td class="commonTd" style="text-align: left;">
						<s:property value="%{#indirizzo.cap}" />
					</td>
				</tr>
				
				<tr class="commonTr">
					<td class="commonTd" style="text-align: left;">
						<s:property value="getText('rubricaForm.paese')" />:
					</td>
					<td class="commonTd" style="text-align: left;">
						<s:property value="%{#indirizzo.paese}" />
					</td>
				</tr>
				
				<tr class="commonTr">
					<td class="commonTd" style="text-align: left;">
						<s:property value="getText('rubricaForm.telefono')" />:
					</td>
					<td class="commonTd" style="text-align: left;">
						<s:property value="%{#indirizzo.telefono}" />
					</td>
				</tr>
				
				<tr class="commonTr">
					<td class="commonTd" colspan="2" style="text-align: center;">
						<s:a action="modificaIndirizzoForm" namespace="/account">
							<s:param name="id_indirizzo" value="%{#indirizzo.id_indirizzo}" />
							<s:property value="getText('rubricaView.modifica')" />
						</s:a>
						&nbsp;-&nbsp;
						<s:a action="rimuoviIndirizzo" onclick="return conferma('%{getText('global.conferma')}');" namespace="/account">
							<s:param name="id_indirizzo" value="%{#indirizzo.id_indirizzo}" />
							<s:property value="getText('rubricaView.canc')" />
						</s:a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</s:iterator>
</s:if>
<s:else>
	<tr class="commonTr">
		<td class="commonTd" style="text-align: center;">
			<s:property value="getText('rubricaView.vuota')" />
		</td>
	</tr>
</s:else>

	<tr class="commonTr">
		<td class="commonTd" colspan="2" style="text-align: center;">
			<s:a action="aggiungiIndirizzoForm" namespace="/account"><s:property value="getText('rubricaView.nuovo')" /></s:a>
		</td>
	</tr>
	
</table>