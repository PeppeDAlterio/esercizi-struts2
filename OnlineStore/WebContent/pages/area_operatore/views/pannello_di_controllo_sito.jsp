<%@ taglib prefix="s" uri="/struts-tags" %>

<h3><s:property value="getText('pcView.titolo')" />&nbsp;<s:property value="getText(#session.userData.tipo_stringa)" default="TIPO_ACCOUNT" /></h3>

<table class="commonTable" style="width: 80%;">
		
	<!-- INIZIO gestione catalogo -->
	<tr class="commonTr">
		<th class="commonTh" style="text-align: left;">
			<s:property value="getText('pcView.ges_catalogo')" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTh" style="text-align: left;">
			<ul>
			<s:if test="%{#session.userData.tipo>2}">
				<!-- ADMIN+ -->
				<li>
					<s:a action="gestioneCategorie" namespace="/admin">
						<s:property value="getText('pcView.ges_categorie')" />
					</s:a>
				</li>
			</s:if>
			<li>
				<s:a action="gestioneArticoli" namespace="/areaoperatore">
					<s:property value="getText('pcView.ges_articoli')" />
				</s:a>
			</li>
			</ul>
		</td>
	</tr>
	<!-- FINE gestione catalogo -->
	
	<!-- INIZIO gestione ordini -->
	<tr class="commonTr">
		<th class="commonTh" style="text-align: left;">
			<s:property value="getText('pcView.ges_ordini')" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTh" style="text-align: left;">
			<ul>
			<li>
				<s:a action="gestioneOrdini" namespace="/areaoperatore">
					<s:property value="getText('pcView.ges_ordini')" />
				</s:a>
			</li>
			<li>
				<s:a action="gestioneFeedback" namespace="/areaoperatore">
					<s:property value="getText('pcView.ges_fb')" />
				</s:a>
			</li>
			</ul>
		</td>
	</tr>
	<!-- FINE gestione ordini -->
	
	<!-- INIZIO gestione accounts -->
	<tr class="commonTr">
		<th class="commonTh" style="text-align: left;">
			<s:property value="getText('pcView.ges_accounts')" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTh" style="text-align: left;">
			<ul>
			<li><s:property value="getText('pcView.ges_account')" /></li>
			<s:if test="%{#session.userData.tipo>2}">
				<!-- ADMIN+ -->
				<li>
					<s:a action="creaAccountForm" namespace="/admin">
						<s:property value="getText('pcView.crea_account')" />
					</s:a>
				</li>
			</s:if>
			</ul>
		</td>
	</tr>
	<!-- FINE gestione accounts -->
	
</table>