<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" jquerytheme="ui-lightness"/>

<%-- BEAN: Leggo i dati dell'articolo tramite ID --%>
<s:bean name="it.store.bean.catalogo.articoli.DatiArticoloBean" var="datiArticolo">
	<s:param name="idArticolo" value="#parameters.idArticolo" />
</s:bean>

<h3>
	<s:text name="schedaArtView.titolo" />
</h3>

<s:if test="%{#datiArticolo.articolo!=null}">
	<s:push value="%{#datiArticolo.articolo}">
	
	<i>
	<s:text name="schedaArtView.categoria" />:&nbsp;<s:property value="categoria" default="N/A" />
	</i>
	
	<br><br>
	
	<i><s:property value="marca" /></i>&nbsp;:&nbsp;<b><s:property value="nome" /></b>
	
	<br>
	
	<i><s:text name="schedaArtView.codice_modello" />:&nbsp;<s:property value="codice_modello" default="N/A" /></i>
	
	<br><br>
	
	<s:text name="schedaArtView.prezzo" />:
	<s:if test="%{sconto_perc>0}">
	<span style="text-decoration: line-through;"><s:property value="prezzo"/>&#8364;</span>
	
	<br>
	
	<font color="#00CC00"><s:text name="schedaArtView.sconto" />:&nbsp;<s:property value="sconto_perc" />%</font>
	
	<br>
	
	</s:if>
	<font color="#0000CC" size="4"><s:property value="prezzo_finale" />&#8364;</font>
	<br><br>
	<s:text name="schedaArtView.disponibilita" />:
	<s:if test="%{quantita<1}">
		<font color="#FF0000">
		<s:text name="schedaArtView.non_disponibile" />
		</font>
		
		<br>
		
		<input type="button" value="<s:text name='schedaArtForm.submit' />" disabled />
	</s:if>
	<s:else>
		<font color="#00AA00">
		<s:property value="quantita" />
		</font>
		
		<br><br>
		
		<s:form action="aggiungiCarrello" namespace="/ordine" method="GET" cssStyle="margin: auto;">
			<s:hidden key="idArticolo" value="%{idArticolo}" />
			<sj:spinner label="%{getText('schedaArtForm.quantita')}" key="quantita_ordinata" value="1" min="1" max="%{quantita}" readonly="true" mouseWheel="true" />
			
			<s:submit value="%{getText('schedaArtForm.submit')}" />
		</s:form>
	</s:else>
	
	<br>
	<br>
	
	<table class="commonTable" style="width: 80%;">
		<tr class="commonTr">
			<th class="commonTh">
				<s:text name="schedaArtView.descrizione" />
			</th>
		</tr>
		
		<tr class="commonTr">
			<td class="commonTd" style="text-align: center;">
				<s:property value="descrizione" default="N/A" />
			</td>
		</tr>
	</table>
	
	<br>
	
	<s:if test="%{#session.userData.tipo>1}">
		<s:a action="modificaArticolo" namespace="/areaoperatore">
			<s:param name="modifica" value="yes" />
			<s:param name="idArticolo" value="%{idArticolo}" />
			
			<input type="button" value="<s:text name='schedaArtView.modifica' />" />
		</s:a>
	</s:if>
	
	</s:push>
</s:if>
<s:else>
	<H3>
		<s:text name="sql.articoli.not_found" />
	</H3>
</s:else>