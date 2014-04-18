<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
	function conferma(str) {
		if(confirm(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	function setParent(str) {
		document.getElementById("new_parent").value = str;
	}
</script>

<%-- BEAN: LEGGO LA LISTA DELLE CATEGORIE --%>
<s:bean name="it.store.bean.catalogo.categorie.ListaCategorieBean" var="categorie" />

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
			<s:property value="getText('categorieView.titolo')" />
		</th>
	</tr>
		
	<tr class="commonTr">
		<td class="commonTd" style="text-align: center;">
			<s:property value="getText('categorieView.descrizione')" /><br>
			<s:property value="getText('categorieView.note')" />
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
		<ul>
			<s:iterator value="#categorie.listaStampabile" var="cat">
			
				<s:if test="#cat=='BEGIN_CHILDREN'">
					<div style="padding-left: 15px;">
				</s:if>
				
				<s:elseif test="#cat=='END_CHILDREN'">
					</div>
				</s:elseif>
				
				<s:else>
					<li>
					[
					<s:a action="rimuoviCategoria" namespace="/admin" onclick="return conferma('%{getText('global.conferma')}');">
						<s:param name="rimuovi" value="#cat" />
						X
					</s:a>
					]
					<s:a action="modificaCategoriaForm" namespace="/admin">
						<s:param name="categoria" value="#cat" />
						<s:property />
					</s:a>
					[ <s:a onclick="setParent(\"%{#cat}\"); return false;">+</s:a> ]
					</li>
				</s:else>
				
			</s:iterator>
		</ul>
 
		</td>
	</tr>
	
	<tr class="commonTr">
		<th class="commonTh">
			<s:property value="getText('categorieView.aggiungi')" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: center">
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
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" style="text-align: center;">
		<s:form action="aggiungiCategoria" namespace="/admin" cssStyle="margin: auto;">
			<s:textfield label="%{getText('categorieView.nome')}" key="categoria" value="" maxlength="20" required="true" />
			<s:textfield id="new_parent" label="%{getText('categorieView.parent')}" key="parent" value="" readonly="true" />
			<s:submit value="%{getText('categorieView.submit')}" /><s:reset value="%{getText('categorieView.reset')}" />
		</s:form>
		</td>
	</tr>
	
		<tr class="commonTr">
		<td class="commonTd" style="text-align: center;">
			<i><s:property value="getText('categorieView.sel_parent')" /></i>
		</td>
	</tr>
	
</table>