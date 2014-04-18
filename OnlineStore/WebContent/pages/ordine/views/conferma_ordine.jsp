<%@ taglib prefix="s" uri="/struts-tags" %>

<H3>
	<s:text name="ordineView.titolo" />
</H3>

<table class="commonTable" style="width: 90%;">
	<tr class="commonTr">
		<th class="commonTh" colspan="2">
			<s:text name="ordineView.lista_articoli" />
		</th>
	</tr>
	
	<s:iterator value="%{#session.ordine.carrello.articoli}" var="articolo" status="status">
		<tr>
		
			<td style="text-align: left;">
				<s:property value="%{#articolo.nome}" />
				<br>
				<i><s:property value="%{#articolo.marca}" /></i>
			</td>
			<td style="text-align: right;">
				<s:property value="%{#articolo.quantita_ordinata}" /> x <s:property value="%{#articolo.prezzo_finale}" />&#8364;
			</td>
			
		</tr>
	</s:iterator>
	<tr>
		<td colspan="2">
			<s:text name="carrelloView.totale" />: <s:property value="%{#session.ordine.carrello.prezzo_totale}" />&#8364;
		</td>
	</tr>
	
	<tr class="commonTr">
		<th class="commonTh" colspan="2">
			<s:text name="ordineView.indirizzo_spedizione" />
		</th>
	</tr>
			
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.nome_cognome')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#session.ordine.indirizzo.nome_cognome}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.indirizzo_1')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#session.ordine.indirizzo.indirizzo_1}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.indirizzo_2')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#session.ordine.indirizzo.indirizzo_2}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.citta')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#session.ordine.indirizzo.citta}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.provincia')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#session.ordine.indirizzo.provincia}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.cap')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#session.ordine.indirizzo.cap}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.paese')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#session.ordine.indirizzo.paese}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.telefono')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#session.ordine.indirizzo.telefono}" />
		</td>
	</tr>
		
</table>

<s:a action="annullaOrdine" namespace="/ordine">
	<input type="button" value="<s:text name='ordineView.annulla' />" />
</s:a>
<s:a action="confermaOrdine" namespace="/ordine">
	<input type="button" value="<s:text name='ordineView.conferma' />" />
</s:a>