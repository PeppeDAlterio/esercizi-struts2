<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<sj:head jqueryui="true" jquerytheme="ui-lightness" />

<%-- BEAN: Leggo la lista delle categorie per permetterne la scelta --%>
<s:bean name="it.store.bean.catalogo.categorie.ListaCategorieBean" var="listaCategorie" />

<script type="text/javascript">
	function toggleSelect() {
		$("#selectCat").attr("disabled", !$("#selectCat").prop("disabled"));
	}
</script>


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


<table style="width: 100%;">
	
	<tr style="width: 100%;">
		<td style="width: 20%;">
			<!-- BOX SINISTRO: Filtro di ricerca -->
			<table class="commonTable" style="margin-left: 1px;">
				<tr class="commonTr">
					<th class="commonTh">
						<s:text name="ricercaArticoli.filtro" />
					</th>
				</tr>
				
				<tr class="commonTr">
					<td style="margin-left: 0px; text-align: left;">
						<s:form action="cerca" namespace="/catalogo" method="GET">
							<s:textfield label="%{getText('ricercaArticoli.marca')}" key="marca" />
							<s:textfield label="%{getText('ricercaArticoli.nome')}" key="nome" />
							<s:select id="selectCat" label="%{getText('ricercaArticoli.categoria')}" key="categoria" list="#listaCategorie.listaCategorie" />
							<s:checkbox label="%{getText('ricercaArticoli.qualsiasi_cat')}" key="categoria" fieldValue="%" onclick="toggleSelect();" />
							<sj:spinner label="%{getText('ricercaArticoli.prezzo_min')}" key="prezzo_min" min="0" step="+1" mouseWheel="true" />
							<sj:spinner label="%{getText('ricercaArticoli.prezzo_max')}" key="prezzo_max" min="0" step="+1" mouseWheel="true" />
							<s:hidden key="page" value="0" />
							<s:submit value="%{getText('ricercaArticoli.submit')}" />
						</s:form>
					</td>
				</tr>
				
			</table>
		</td>
		
		<td colspan="3" style="text-align: center; width: 80%;">
			<h3>
				<s:text name="ricercaArticoli.risultati" />
			</h3>
			<s:if test="%{#request.listaArticoli!=null}">
				<table class="carrello" style="width: 100%;">
					<tr>
						<th style="text-align: left;">
							<s:text name="ricercaArticoli.marca" /> - <s:text name="ricercaArticoli.nome" />
						</th>
						<th style="text-align: left;">
							<s:text name="ricercaArticoli.codice_modello" />
						</th>
						<th style="text-align: right;">
							<s:text name="ricercaArticoli.prezzo" />
						</th>
					</tr>
					<s:iterator value="#request.listaArticoli" var="articolo">
						<tr>
							<td style="text-align: left;">
								<s:a action="visualizzaArticolo" namespace="/catalogo" target="_blank">
									<s:param name="idArticolo" value="#articolo.idArticolo" />
									
									<s:property value="#articolo.marca" />
									<br>
									<s:property value="#articolo.nome" />
								</s:a>
							</td>
							<td style="text-aling: left;">
								<s:property value="#articolo.codice_modello" default="N/A" />
							</td>
							<td style="text-align: left;">
								EUR <s:property value="#articolo.prezzo_finale" />
							</td>
						</tr>
					</s:iterator>
				</table>
			</s:if>
			<s:else>
				<div style="text-align: center;">
					<s:text name="ricerca.nessun_risultato" />
				</div>
			</s:else>
		</td>
	</tr>
	
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
			<s:a action="cerca?%{#request.queryString}" namespace="/catalogo">
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