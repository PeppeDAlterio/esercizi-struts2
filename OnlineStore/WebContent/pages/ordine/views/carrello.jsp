<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" jquerytheme="ui-lightness"/>

<H3>
	<s:text name="carrelloView.titolo" />
</H3>

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

<table class="carrello" style="width: 90%; margin: auto;">

	<tr>
		<th style="text-align: left;" colspan="2">
			<font size="3">
				<s:text name="carrelloView.articoli" />
			</font>
		</th>
		<th style="text-align: center;">
			<s:text name="carrelloView.prezzo" />
		</th>
		<th style="text-align: right;">
			<s:text name="carrelloView.quantita" />
		</th>
	</tr>

	<s:if test="%{#session.carrello != null && #session.carrello.articoli.size>0}">
		<s:iterator value="%{#session.carrello.articoli}" var="articolo" status="status">
		<tr>
			<td style="width: 1%;">
				<s:a action="rimuoviArticoloCarrello" namespace="/ordine">
					<s:param name="index" value="#status.index" />
				
					X
				</s:a>
			</td>
			<td>
				<s:a action="visualizzaArticolo" namespace="/catalogo" target="_blank">
					<s:param name="idArticolo" value="#articolo.idArticolo" />
				
					<s:property value="%{#articolo.nome}" />
				</s:a>
				<br>
				<i><s:property value="%{#articolo.marca}" /></i>
			</td>
			<td style="text-align: center;">
				<s:property value="%{#articolo.prezzo_finale}" />&#8364;
			</td>
			<td style="text-align: right;">
				<s:form action="aggiornaCarrello" namespace="/ordine" theme="simple" method="GET">
				<s:hidden key="index" value="%{#status.index}" />
				<sj:spinner min="1" step="+1" max="%{#articolo.quantita}" key="quantita_ordinata" value="%{#articolo.quantita_ordinata}" mouseWheel="true" />
				<br>
				<s:submit value="%{getText('carrelloForm.aggiorna_articolo')}" />
				</s:form>
			</td>
			
		</tr>
		</s:iterator>
		
		<tr>
			<td class="noHoverEffect" style="text-align: right;" colspan="4">
				<s:text name="carrelloView.totale" />: <s:property value="%{#session.carrello.prezzo_totale}" />&#8364;
			</td>
		</tr>
		
		<tr>
			<td class="noHoverEffect" style="text-align: center;" colspan="4">
				<s:a action="indirizzoOrdineForm" namespace="/ordine">
					<input type="button" value="<s:text name='carrelloView.cassa' />" />
				</s:a>
			</td>
		</tr>
	</s:if>
	<s:else>
		<tr>
			<td  class="noHoverEffect" style="text-align: center;" colspan="4">
				<i><s:text name="carrelloView.vuoto" /></i>
			</td>
		</tr>

	</s:else>
</table>