<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- BEAN: Leggo il parent della categoria --%>
<s:bean name="it.store.bean.catalogo.categorie.CategoriaBean" var="categoria">
	<s:param name="categoria"><s:property value="#parameters.categoria" /></s:param>
</s:bean>

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

<table class="commonTable">
	<tr class="commonTr">
		<th class="commonTh">
			<s:property value="getText('categoriaForm.titolo')" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="modificaCategoria" namespace="/admin">
				<s:textfield label="%{getText('categoriaForm.nome_categoria')}" key="categoria" value="%{#parameters.categoria}" readonly="true" />
				<s:textfield label="%{getText('categoriaForm.parent')}" key="parent" value="%{#categoria.parent}" readonly="true" />
				<s:textfield label="%{getText('categoriaForm.nuovo_nome')}" key="nuovo_nome" value="%{#parameters.categoria}" maxlength="20" />
				<s:select label="%{getText('categoriaForm.nuovo_parent')}" list="#listaCategorie.listaCategorie" key="nuovo_parent"  />
				<s:submit value="%{getText('categoriaForm.submit')}" />
			</s:form>
		</td>
	</tr>
	
</table>