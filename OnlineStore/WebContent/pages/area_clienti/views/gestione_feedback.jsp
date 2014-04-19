<%@ taglib prefix="s" uri="/struts-tags" %>

<h3>
	<s:text name="listaFbView.titolo" />
</h3>

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
		<th class="commonTh" colspan="4">
			<s:text name="listaFbView.fb_da_rilasciare" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td style="text-align: center;">
			<s:text name="listaOrdiniView.id_ordine" />
		</td>
		<td style="text-align: center;" colspan="3">
			<s:text name="listaFbView.rilascia_fb" />
		</td>
	</tr>
	<s:if test="%{#request.listaFeedback!=null}">
		<s:iterator value="#request.listaFeedback" var="feedback">
			<s:if test="%{#feedback.stato==-2}"> <%-- SOLO FEEDBACK NON RILASCATI (STATO=-2) --%>
				<tr class="risultati">
					<td style="text-align: center;">
						<s:a action="visualizzaOrdine" namespace="/areacliente" target="_blank">
							<s:param name="id_ordine" value="#feedback.id_ordine" />
							
							<s:property value="#feedback.id_ordine" />
						</s:a>
					</td>
					<td style="text-align: center;" colspan="3">
						<s:a action="rilasciaFeedbackForm" namespace="/areacliente">
						
							<s:param name="id_ordine" value="#feedback.id_ordine" />
						
							<s:text name="listaFbView.rilascia_fb" />
						</s:a>
					</td>
				</tr>
			</s:if>
		</s:iterator>
	</s:if>
	<s:else>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="ricerca.nessun_risultato" />
			</td>
		</tr>
	</s:else>
	
	<tr class="commonTr">
		<th class="commonTh" colspan="4">
			<s:text name="listaFbView.fb_rilasciati" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td style="text-align: center;">
			<s:text name="listaOrdiniView.id_ordine" />
		</td>
		<td style="text-align: center;">
			<s:text name="listaFbView.punteggio" />
		</td>
		<td style="text-align: center;">
			<s:text name="listaFbView.messaggio" />
		</td>
		<td style="text-align: center;">
			<s:text name="listaFbView.stato" />
		</td>
	</tr>
	
	<s:if test="%{#request.listaFeedback!=null}">
		<s:iterator value="#request.listaFeedback" var="feedback">
			<s:if test="%{#feedback.stato>-2}"> <%-- SOLO FEEDBACK RILASCATI (STATO>-2) --%>
				<tr class="risultati">
					<td style="text-align: center;">
						<s:a action="visualizzaOrdine" namespace="/areacliente" target="_blank">
							<s:param name="id_ordine" value="#feedback.id_ordine" />
							
							<s:property value="#feedback.id_ordine" />
						</s:a>
					</td>
					<td style="text-align: center;">
						<s:property value="#feedback.punteggio" />
					</td>
					<td style="text-align: center;">
						<s:property value="#feedback.messaggio" />
					</td>
					<td style="text-align: center;">
						<s:text name="feedback.%{#feedback.stato}" />
					</td>
				</tr>
			</s:if>
		</s:iterator>
	</s:if>
	<s:else>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="ricerca.nessun_risultato" />
			</td>
		</tr>
	</s:else>
	
</table>