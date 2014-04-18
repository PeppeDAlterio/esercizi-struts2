<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="%{#parameters.page!=null}">
	<s:set var="p" scope="request" value="%{#parameters.page}" />
</s:if>

<s:bean name="it.store.bean.ordine.ListaOrdiniBean" var="ordiniBean">
	<s:param name="page" value="#request.p" />
	
	<s:param name="utente" value="#session.userData.email" />
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

<h3>
	<s:text name="listaOrdiniView.titolo" />
</h3>

<table class="commonTable" style="width: 90%;">
		
	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="listaOrdiniView.ricerca_id" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="visualizzaOrdine" namespace="/areacliente" target="_blank" method="GET" cssStyle="margin: auto;">
				<s:textfield label="%{getText('listaOrdiniView.id_ordine')}" key="id_ordine" required="true" />
				<s:submit value="%{getText('listaOrdiniView.ricerca_id.submit')}" />
			</s:form>
		</td>
	</tr>
	
	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="listaOrdiniView.lista_ordini" />
		</th>
	</tr>
	
<s:if test="%{#ordiniBean.listaOrdini!=null}">
	<s:iterator value="%{#ordiniBean.listaOrdini}" var="ordine">
		<tr class="risultati">
			<td style="text-align: center;">
				<s:a action="visualizzaOrdine" namespace="/areacliente" target="_blank">
					<s:param name="id_ordine" value="#ordine.id_ordine" />
					<s:text name="listaOrdiniView.id_ordine" />:&nbsp;<b><s:property value="#ordine.id_ordine" /></b>
				</s:a>
				<br>
				<s:text name="listaOrdiniView.data_creazione" />:&nbsp;<i><s:property value="#ordine.data" /></i>
				<br>
				<s:text name="listaOrdiniView.totale" />:&nbsp;<s:property value="#ordine.totale" />&#8364;
				<br>
				<s:text name="listaOrdiniView.stato" />:&nbsp;<i><s:property value="#ordine.stato" /></i>
			</td>
		</tr>
	</s:iterator>	
</s:if>
<s:else>
	<tr class="commonTr">
		<td>
			<s:text name="ricerca.nessun_risultato" />
		</td>
	</tr>
</s:else>
</table>

<s:text name="global.pagina" />:&nbsp;
<s:iterator begin="1" end="%{#ordiniBean.pagine_totali}" status="status" >
	<s:if test="%{#status.index != #ordiniBean.page}">
		<s:a action="gestioneOrdini" namespace="/areacliente">
			<s:param name="page" value="#status.index" />
			
			<s:property value="#status.index+1" />
		</s:a>
	</s:if>
	<s:else>
		<s:property value="#status.index+1" />
	</s:else>
&nbsp;-&nbsp;
</s:iterator>