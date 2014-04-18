<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" jquerytheme="ui-lightness" />

<script type="text/javascript">		
	$(function(){
				
		$('#prezzo').on("spinstop", function(){
			document.getElementById('prezzo_finale').value = eval( $('#prezzo').spinner('value') - $('#prezzo').spinner('value')*$('#sconto_perc').spinner('value')/100 ) ;
	   });
		
		$('#sconto_perc').on("spinstop", function(){
			document.getElementById('prezzo_finale').value = eval( $('#prezzo').spinner('value') - ($('#prezzo').spinner('value')*$('#sconto_perc').spinner('value')/100) ) ;
	   });
		
	});
</script>

<%-- BEAN: Leggo la lista delle categorie per permetterne la scelta --%>
<s:bean name="it.store.bean.catalogo.categorie.ListaCategorieBean" var="listaCategorie" />

<%-- Se si sta modificando un articolo utilizzo una bean per leggerne le informazioni --%>
<s:if test="%{#parameters.modifica!=null && #parameters.idArticolo!=null}">
	<s:bean name="it.store.bean.catalogo.articoli.DatiArticoloBean" var="datiArticolo">
		<s:param name="idArticolo" value="#parameters.idArticolo" />
	</s:bean>
	
	<s:set var="articolo" scope="request" value="%{#datiArticolo.articolo}" />
</s:if>

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
		<th class="commonTh">
		<%-- Modifica --%>
		<s:if test="%{#parameters.modifica!=null && #parameters.idArticolo!=null}">
			<s:property value="getText('nuovoArtForm.titolo_mod')" />
		</s:if>
		<%-- Aggiunta --%>
		<s:else>
			<s:property value="getText('nuovoArtForm.titolo')" />
		</s:else>
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form name="artForm" action="nuovoArticolo" cssStyle="width: 70%; margin: auto;">
				<%-- Campi comuni: aggiunta e modifica --%>
				<s:select label="%{getText('nuovoArtForm.categoria')}" list="#listaCategorie.listaCategorie" value="%{#request.articolo.categoria}" key="categoria" maxlength="20" />
				<s:textarea label="%{getText('nuovoArtForm.descrizione')}" key="descrizione" value="%{#request.articolo.descrizione}" maxlength="100" />
				<%-- Se si sta modificando un articolo --%>
				<s:if test="%{#parameters.modifica!=null && #parameters.idArticolo!=null}">
					<s:textfield label="%{getText('nuovoArtForm.marca')}" key="marca" value="%{#request.articolo.marca}" maxlength="25" readonly="true" />
					<s:textfield label="%{getText('nuovoArtForm.nome_art')}" key="nome" value="%{#request.articolo.nome}" maxlength="50" readonly="true" />
					<s:textfield label="%{getText('nuovoArtForm.codice_modello')}" key="codice_modello" value="%{#request.articolo.codice_modello}" readonly="true" />
					<sj:spinner label="%{getText('nuovoArtForm.quantita')}" key="quantita" min="0" value="%{#request.articolo.quantita}" required="true" mouseWheel="true" />
					<sj:spinner id="prezzo" label="%{getText('nuovoArtForm.prezzo')}" key="prezzo" min="0.00" step="0.50" value="%{#request.articolo.prezzo}" required="true" mouseWheel="true" />
					<sj:spinner id="sconto_perc" label="%{getText('nuovoArtForm.sconto_perc')}" key="sconto_perc" min="0.0" max="100.0" step="0.5" value="%{#request.articolo.sconto_perc}" mouseWheel="true" />
					<s:textfield label="%{getText('nuovoArtForm.pezzi_venduti')}" value="%{#request.articolo.pezzi_venduti}" disabled="true" />
					<s:hidden key="idArticolo" value="%{#request.articolo.idArticolo}" />
				</s:if>
				<%-- Aggiunta articolo --%>
				<s:else>					
					<s:textfield label="%{getText('nuovoArtForm.marca')}" key="marca" value="%{#request.articolo.marca}" maxlength="25" required="true" />
					<s:textfield label="%{getText('nuovoArtForm.nome_art')}" key="nome" value="%{#request.articolo.nome}" maxlength="50" required="true" />
					<s:textfield label="%{getText('nuovoArtForm.codice_modello')}" key="codice_modello" value="%{#request.articolo.codice_modello}" maxlength="20" />
					<sj:spinner label="%{getText('nuovoArtForm.quantita')}" key="quantita" min="0" value="1" required="true" mouseWheel="true" />
					<sj:spinner id="prezzo" label="%{getText('nuovoArtForm.prezzo')}" key="prezzo" min="0.00" step="0.50" value="0" required="true" mouseWheel="true" />
					<sj:spinner id="sconto_perc" label="%{getText('nuovoArtForm.sconto_perc')}" key="sconto_perc" min="0.0" max="100.0" step="0.5" value="0" mouseWheel="true" />
				</s:else>
				<s:submit value="%{getText('nuovoArtForm.submit')}" />
				<s:reset />
			</s:form>
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: center;">
			<s:property value="getText('nuovoArtForm.prezzo_finale')" /> : <s:textfield id="prezzo_finale" readonly="true" value="%{#request.articolo.prezzo_finale}" theme="simple" />
		</td>
	</tr>
</table>