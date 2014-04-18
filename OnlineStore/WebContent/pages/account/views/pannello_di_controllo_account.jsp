<%@ taglib prefix="s" uri="/struts-tags" %>

<h3><s:property value="getText('pcView.titolo')" />&nbsp;<s:property value="#session.userData.userId" /></h3>

<table class="commonTable" style="width: 90%;">

	<!-- Ordini -->
	<tr class="commonTr">
		<th class="commonTh" style="text-align: left;">
			<s:property value="getText('pcView.ordini')" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
		
		<ul>
			<li>
				<s:a action="gestioneOrdini" namespace="/areacliente">
					<s:property value="getText('pcView.storico_ordini')" />
				</s:a>
			</li>
			<li>
				<s:text name="pcView.storico_rilascio_feedback" />
			</li>
		</ul>
		
		</td>
	</tr>
	
	<!-- Dati utente -->
	<tr class="commonTr">
		<th class="commonTh" style="text-align: left;">
			<s:property value="getText('pcView.dati_utente')" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
		
			<ul>
				<li><s:a action="modificaProfiloForm" namespace="/account"><s:property value="getText('pcView.mod_profilo')" /></s:a></li>
				<li><s:a action="modificaPasswordForm" namespace="/account"><s:property value="getText('pcView.mod_password')" /></s:a></li>
				<li><s:a action="rubricaUtente" namespace="/account"><s:property value="getText('pcView.rubrica')" /></s:a></li>
			</ul>
		
		</td>
	</tr>
	
	<!-- Area operatore/admin -->
	<s:if test="%{#session.userData.tipo>1}">
		<tr class="commonTr">
			<th class="commonTh" style="text-align: left;">
				<s:property value="getText('pcView.gestione_sito')" />
			</th>
		</tr>
		
		<tr class="commonTr">
			<td class="commonTd" style="text-align: left;">
			<ul>
				<li>
					<s:a action="pannelloControlloSito" namespace="/areaoperatore"><s:property value="getText('pcView.pannello_controllo')" />&nbsp;<s:property value="%{#session.userData.tipo_stringa}" default="TIPO_ACCOUNT" /></s:a>
				</li>
			</ul>
			</td>
		</tr>
	</s:if>

</table>