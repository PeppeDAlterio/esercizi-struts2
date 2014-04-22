<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" jquerytheme="ui-lightness" />

<%-- BEAN: Leggo i dati dell'ordine --%>
<s:bean name="it.store.bean.ordine.DatiOrdineBean" var="ordineBean">
	<s:param name="utente" value="#session.userData.email" />
	<s:param name="tipoAccount" value="#session.userData.tipo" />
	
	<s:param name="id_ordine" value="#parameters.id_ordine" />
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


<table class="commonTable">

	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="modOrdineForm.titolo" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="modificaOrdine" namespace="/areaoperatore" cssStyle="margin: auto;">
				<s:textfield label="%{getText('listaOrdiniView.id_ordine')}" key="id_ordine" value="%{#parameters.id_ordine}" readonly="true" />
				<s:select label="%{getText('dettOrdineView.stato')}" list="#{'aperto':'aperto', 'lavorazione':'lavorazione', 'spedito':'spedito', 'ricevuto':'ricevuto', 'chiuso':'chiuso' }" key="stato" maxlength="20" required="true" />
				<sj:datepicker label="%{getText('dettOrdineView.data_spedizione')}" key="data_spedizione" displayFormat="yy-mm-dd" maxDate="0" readonly="true" />
				<s:submit value="%{getText('dettOrdineView.modifica')}" />
				<s:reset value="%{getText('modOrdineForm.reset')}" />
			</s:form>
		</td>
	</tr>
	
</table>