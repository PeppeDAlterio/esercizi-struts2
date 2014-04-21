<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" jquerytheme="ui-lightness" />

<h3>
	<s:text name="gestioneOrdiniView.titolo" />
</h3>

<table class="commonTable" style="width: 90%;">
	
	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="gestioneOrdiniView.ordine_by_id" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="ricercaOrdineById" namespace="/areaoperatore" method="GET" cssStyle="margin: auto;">
				<s:textfield label="%{getText('gestioneOrdiniView.id_ordine')}" key="id_ordine" required="true" />
				<s:submit value="%{getText('gestioneOrdiniView.submit')}" />
			</s:form>
		</td>
	</tr>
	
	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="gestioneOrdiniView.ordine_by_filtro" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="ricercaOrdine" namespace="/areaoperatore" method="GET" cssStyle="margin: auto;">
				<s:textfield label="%{getText('gestioneOrdiniView.utente_email')}" key="utente_email" maxlength="50" />
				<sj:datepicker label="%{getText('gestioneOrdiniView.data_creazione')}" key="data" displayFormat="yy-mm-dd" readonly="true" />
				<s:textfield id="stato" label="%{getText('gestioneOrdiniView.stato')}" key="stato" maxlength="20" />
				<s:submit value="%{getText('gestioneOrdiniView.submit')}" />
			</s:form>
		</td>
	</tr>
</table>

<table class="commonTable" style="border-collapse: collapse; width: 90%;">
	<tr class="commonTr">
		<th class="commonTh" colspan="5">
			<s:text name="gestioneOrdiniView.risultati_ricerca" />
		</th>
	</tr>
	
	<tr>
		<td style="background: #EECA9C;">
			<s:text name="gestioneOrdiniView.id_ordine" />
		</td>
		<td style="background: #EECA9C;">
			<s:text name="gestioneOrdiniView.utente_email" />
		</td>
		<td style="background: #EECA9C;">
			<s:text name="gestioneOrdiniView.data_creazione" />
		</td>
		<td style="background: #EECA9C;">
			<s:text name="gestioneOrdiniView.stato" />
		</td>
		<td style="background: #EECA9C;">
			<s:text name="gestioneOrdiniView.gestisci" />
		</td>
	</tr>
	
	<s:if test="%{#request.risultati!=null}">
		<s:iterator value="#request.risultati" var="ordine">
			<tr>
				<td>
				</td>
			</tr>
		</s:iterator>
	</s:if>
	<s:else>
		<tr>
			<td colspan="5">
				<s:text name="ricerca.nessun_risultato" />
			</td>
		</tr>
	</s:else>
	
</table>