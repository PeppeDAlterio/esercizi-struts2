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
				<s:textfield label="%{getText('gestioneOrdiniView.utente_email')}" key="utente_email" value="" maxlength="50" />
				<sj:datepicker label="%{getText('gestioneOrdiniView.data_creazione')}" key="data" value="" displayFormat="yy-mm-dd" readonly="true" />
				<s:textfield id="stato" label="%{getText('gestioneOrdiniView.stato')}" key="stato" value="" maxlength="20" />
				<s:hidden key="page" value="0" />
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
			<tr class="risultati">
				<td>
					<s:property value="#ordine.id_ordine" />
				</td>
				<td>
					<s:property value="#ordine.Utente_email" />
				</td>
				<td>
					<s:property value="#ordine.data" />
				</td>
				<td>
					<s:property value="#ordine.stato" />
				</td>
				<td>
					<s:a action="modificaOrdineForm" namespace="/areaoperatore" target="_blank">
						<s:param name="id_ordine" value="%{#ordine.id_ordine}" />
					
						<s:text name="gestioneOrdiniView.gestisci" />
					</s:a>
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

<%
	String queryString = request.getQueryString();
	if(queryString!=null && request.getAttribute("page")!=null) {
		String pagina = "&page="+request.getAttribute("page").toString();
		
		queryString = queryString.replace(pagina, "");
		
		request.setAttribute("queryString", queryString);
	}
%>

<s:if test="%{#request.queryString != null}">
	<s:text name="global.pagina" />:&nbsp;
	<s:iterator begin="1" end="%{#request.totale_pagine}" status="status" >
		<s:if test="%{#status.index!=#request.page}">
			<s:a action="ricercaOrdine?%{#request.queryString}" namespace="/areaoperatore">
				<s:param name="page" value="#status.index" />
				
				<s:property value="#status.index+1" />
			</s:a>
		</s:if>
		<s:else>
			<s:property value="#status.index+1" />
		</s:else>
&nbsp;-&nbsp;
	</s:iterator>
		
</s:if>	