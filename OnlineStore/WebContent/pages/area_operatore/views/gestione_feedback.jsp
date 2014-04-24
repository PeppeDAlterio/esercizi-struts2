<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- BEAN: Leggo lista feedback stato:-1 (da approvare) (10 per pagina) --%>
<s:bean name="it.store.bean.feedback.ListaFeedbackBean" var="feedbackBean">
	<s:if test="%{#parameters.page!=null}">
		<s:param name="page" value="#parameters.page" />
	</s:if>
	<s:param name="stato" value="-1" />
</s:bean>

<H3>
	<s:text name="gestioneFbView.titolo" />
</H3>

<table class="commonTable" style="width: 90%;">

	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="gestioneFbView.lista_da_approvare" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td style="text-align:center;">
			<s:text name="gestioneFbView.utente" />
		</td>
		<td style="text-align:center;">
			<s:text name="gestioneFbView.id_ordine" />
		</td>
		<td style="text-align:center;">
			<s:text name="gestioneFbView.messaggio" />
		</td>
		<td style="text-align:center;">
			<s:text name="gestioneFbView.punteggio" />
		</td>
		<td style="text-align:center;">
			<s:text name="gestioneFbView.accept" />
		</td>
		<td style="text-align:center;">
			<s:text name="gestioneFbView.refuse" />
		</td>
	</tr>	
	<s:iterator value="#feedbackBean.listaFeedback" var="feedback">
		<tr class="risultati">
			<td>
				<s:a action="visualizzaProfilo" namespace="/areaoperatore" target="_blank">
					<s:param name="utente" value="#feedback.Utente_email" />
					
					<s:property value="#feedback.Utente_email" />
				</s:a>
			</td>
			<td>
				<s:a action="visualizzaOrdine" namespace="/areacliente">
					<s:param name="id_ordine" value="#feedback.id_ordine" />
					
					<s:property value="#feedback.id_ordine" />
				</s:a>
			</td>
			<td>
				<s:property value="#feedback.messaggio" default="N/A" />
			</td>
			<td>
				<s:property value="#feedback.punteggio" default="N/A" />
			</td>
			<td>
				<s:a action="approvaFeedback" namespace="/areaoperatore">
					<s:param name="id_ordine" value="#feedback.id_ordine" />
					
					<s:text name="gestioneFbView.accept" />
				</s:a>
			</td>
			<td>
				<s:a action="rifiutaFeedback" namespace="/areaoperatore">
					<s:param name="id_ordine" value="#feedback.id_ordine" />
					
					<s:text name="gestioneFbView.refuse" />
				</s:a>
			</td>
		</tr>
	</s:iterator>
	
</table>