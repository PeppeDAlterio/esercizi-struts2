<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- BEAN: Leggo i dati dell'ordine --%>
<s:bean name="it.store.bean.ordine.DatiOrdineBean" var="ordineBean">
	<s:param name="utente" value="#session.userData.email" />
	<s:param name="tipoAccount" value="#session.userData.tipo" />
	
	<s:param name="id_ordine" value="#parameters.id_ordine" />
</s:bean>

<H3>
	<s:text name="ordineView.titolo" />
</H3>

<%-- Operatore+ --%>
<s:if test="%{#session.userData.tipo>1}">
	<s:a action="modificaOrdineForm" namespace="/areaoperatore">
		<s:param name="id_ordine" value="#parameters.id_ordine" />
		
		<s:text name="dettOrdineView.modifica" />
	</s:a>
</s:if>
<br>
<br>

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
		<th class="commonTh" colspan="2">
			<s:text name="ordineView.lista_articoli" />
		</th>
	</tr>
	
	<s:iterator value="%{#ordineBean.ordine.carrello.articoli}" var="articolo" status="status">
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
			<s:text name="carrelloView.totale" />: <s:property value="%{#ordineBean.ordine.carrello.prezzo_totale}" />&#8364;
		</td>
	</tr>
	
	<tr class="commonTr">
		<th class="commonTh" colspan="2">
			<s:text name="ordineView.indirizzo_spedizione" />
			<s:if test="%{#ordineBean.ordine.stato!='spedito'}">
				&nbsp;-&nbsp;
				[<s:a action="modificaIndirizzoOrdineForm" namespace="/areacliente">
					<s:param name="id_ordine" value="#ordineBean.ordine.id_ordine" />
					
					<s:text name="global.modifica" />
				</s:a>]
			</s:if>
		</th>
	</tr>
			
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.nome_cognome')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#ordineBean.ordine.indirizzo.nome_cognome}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.indirizzo_1')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#ordineBean.ordine.indirizzo.indirizzo_1}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.indirizzo_2')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#ordineBean.ordine.indirizzo.indirizzo_2}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.citta')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#ordineBean.ordine.indirizzo.citta}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.provincia')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#ordineBean.ordine.indirizzo.provincia}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.cap')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#ordineBean.ordine.indirizzo.cap}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.paese')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#ordineBean.ordine.indirizzo.paese}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: left;">
			<s:property value="getText('rubricaForm.telefono')" />:
		</td>
		<td class="commonTd" style="text-align: left;">
			<s:property value="%{#ordineBean.ordine.indirizzo.telefono}" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<th class="commonTh" colspan="2">
			<s:text name="dettOrdineView.info" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" colspan="2" style="text-align: center;">
			<s:text name="dettOrdineView.cliente" />:&nbsp;<i><s:property value="#ordineBean.ordine.Utente_email" /></i>
			<br>
			<s:text name="dettOrdineView.data" />:&nbsp;<i><s:property value="#ordineBean.ordine.data" /></i>
			<br>
			<s:text name="dettOrdineView.stato" />:&nbsp;<i><s:text name="stato.%{#ordineBean.ordine.stato}" /></i>
			<br>
			<s:text name="dettOrdineView.data_spedizione" />:&nbsp;<i><s:property value="#ordineBean.ordine.data_spedizione" default="N/A" /></i>
			<br>
			<s:text name="dettOrdineView.ultima_modifica" />:&nbsp;<i><s:property value="#ordineBean.ordine.data_modifica" default="N/A" /></i>
			<br>
			<%-- Operatore+ --%>
			<s:if test="%{#session.userData.tipo>1}">
				<s:text name="dettOrdineView.email_modifica" />:&nbsp;<i><s:property value="#ordineBean.ordine.email_modifica" default="N/A" /></i>
			</s:if>
		</td>
	</tr>
		
</table>

<br>
<s:a action="gestioneOrdini" namespace="/areacliente">
	<input type="button" value="<s:text name='global.indietro' />" />
</s:a>