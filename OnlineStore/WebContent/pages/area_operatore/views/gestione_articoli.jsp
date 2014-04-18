<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
	function toggleSelect() {
		document.getElementById("cat_select").disabled = !document.getElementById("cat_select").disabled
	}
</script>

<%-- BEAN: Leggo la lista delle categorie per permettere la scelta del parent --%>
<s:bean name="it.store.bean.catalogo.categorie.ListaCategorieBean" var="listaCategorie" />

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
	<s:text name="cercaArtForm.titolo" />
</h3>

<table class="commonTable" style="width: 90%;">	
	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="cercaArtForm.ricerca_by_id" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
		<s:form action="ricercaArticoloById" namespace="/areaoperatore" cssStyle="margin: auto;" method="GET">
			<s:textfield label="%{getText('cercaArtForm.id_articolo')}" key="idArticolo" value="" />
			<s:submit value="%{getText('cercaArtForm.submit')}" />
		</s:form>
		</td>
	</tr>
	
	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="cercaArtForm.ricerca" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="ricercaArticoli" namespace="/areaoperatore" cssStyle="width: 100%; margin: auto;" method="GET">
			<s:select id="cat_select" label="%{getText('cercaArtForm.categoria')}" list="#listaCategorie.listaCategorie" key="categoria" />
			<s:checkbox id="cat_checkbox" label="%{getText('cercaArtForm.tutte_categorie')}" fieldValue="%" value="false" key="categoria" onclick="toggleSelect();" />
			<s:textfield label="%{getText('cercaArtForm.marca')}" key="marca" />
			<s:textfield label="%{getText('cercaArtForm.nome')}" key="nome" />
			<s:textfield label="%{getText('cercaArtForm.codice_modello')}" key="codice_modello" />
			<s:textfield label="%{getText('cercaArtForm.prezzo_da')}" key="prezzo_min" />
			<s:textfield label="%{getText('cercaArtForm.prezzo_a')}" key="prezzo_max" />
			<s:checkbox label="%{getText('cercaArtForm.solo_scontati')}" key="scontato" />
			<s:hidden key="p" value="0" />
			<s:submit value="%{getText('cercaArtForm.submit')}" />
			</s:form>
		</td>
	</tr>
</table>

<table class="commonTable" style="width: 90%; border-collapse: collapse;">
	<tr class="commonTr">
		<th class="commonTh" colspan="10">
			<s:property value="getText('cercaArtForm.risultati')" />
		</th>
	</tr>
	
	<tr class="risultati">
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.id_articolo" />
		</td>
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.categoria" />
		</td>
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.marca" />
		</td>
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.nome" />
		</td>
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.modello" />
		</td>
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.sconto_perc" />
		</td>
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.prezzo_finale" />
		</td>
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.pezzi_disponibili" />
		</td>
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.pezzi_venduti" />
		</td>
		<td class="commonTd" style="background: #EECA9C;">
			<s:text name="gestioneArtView.ricerca.scheda_articolo" />
		</td>
	</tr>
	
	<s:if test="%{#request.risultatiRicerca != null}">
		<s:iterator value="#request.risultatiRicerca" var="articolo">
			<tr class="risultati">
				<td>
					<s:property value="#articolo.idArticolo" />
				</td>
				<td>
					<s:property value="#articolo.categoria" />
				</td>
				<td>
					<s:property value="#articolo.marca" />
				</td>
				<td>
					<s:property value="#articolo.nome" />
				</td>
				<td>
					<s:property value="#articolo.codice_modello" default="N/A" />
				</td>
				<td>
					<s:property value="#articolo.sconto_perc" />
				</td>
				<td>
					<s:property value="#articolo.prezzo_finale" />
				</td>
				<td>
					<s:property value="#articolo.quantita" />
				</td>
				<td>
					<s:property value="#articolo.pezzi_venduti" />
				</td>
				<td>
					<s:a action="visualizzaArticolo" namespace="/catalogo" target="_blank">
						<s:param name="idArticolo" value="#articolo.idArticolo" />
						
						<s:text name="gestioneArtView.visualizza" />
					</s:a>
				</td>
			</tr>
		</s:iterator>
	</s:if>
	<s:else>
	
		<tr class="commonTr">
			<td class="commonTd" style="text-align: center;"  colspan="10">
				<s:text name="ricerca.nessun_risultato" />
			</td>
		</tr>
	</s:else>
</table>

<%
	String queryString = request.getQueryString();
	if(queryString!=null && request.getAttribute("p")!=null) {
		String pagina = "&p="+request.getAttribute("p").toString();
		
		queryString = queryString.replace(pagina, "");
		
		request.setAttribute("queryString", queryString);
	}
%>

<s:if test="%{#request.queryString != null}">
	<s:text name="global.pagina" />:&nbsp;
	<s:iterator begin="1" end="%{#request.totale_pagine}" status="status" >
		<s:if test="%{#status.index!=#request.p}">
			<s:a action="ricercaArticoli?%{#request.queryString}" namespace="/areaoperatore">
				<s:param name="p" value="#status.index" />
				
				<s:property value="#status.index+1" />
			</s:a>
		</s:if>
		<s:else>
			<s:property value="#status.index+1" />
		</s:else>
&nbsp;-&nbsp;
	</s:iterator>
</s:if>

<br>
<br>

<table class="commonTable">
	<tr class="commonTr">
		<th class="commonTh">
			<s:a action="nuovoArticoloForm" namespace="/areaoperatore"><input type="button" value="<s:property value="getText('gestioneArtView.nuovo_art')" />" /></s:a>
		</th>
	</tr>
</table>