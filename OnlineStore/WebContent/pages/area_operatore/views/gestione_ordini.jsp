<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" jquerytheme="ui-lightness" />

<h3>
	<s:text name="gestioneOrdiniView.titolo" />
</h3>

<table class="commonTable">
	
	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="gestioneOrdiniView.ordine_by_id" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="ricercaOrdineById" namespace="/areaoperatore" method="GET">
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
			<s:form action="ricercaOrdine" namespace="/areaoperatore" method="GET">
				<s:textfield label="%{getText('gestioneOrdiniView.utente_email')}" key="utente_email" maxlength="50" />
				<sj:datepicker label="%{getText('gestioneOrdiniView.data_creazione')}" key="data" displayFormat="yy-mm-dd" readonly="true" />
				<s:textfield id="stato" label="%{getText('gestioneOrdiniView.stato')}" key="stato" maxlength="20" />
				<s:submit value="%{getText('gestioneOrdiniView.submit')}" />
			</s:form>
		</td>
	</tr>
	
</table>