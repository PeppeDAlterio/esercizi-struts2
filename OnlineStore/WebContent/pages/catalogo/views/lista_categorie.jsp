<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- BEAN: LEGGO LA LISTA DELLE CATEGORIE --%>
<s:bean name="it.store.bean.catalogo.categorie.ListaCategorieBean" var="categorie" />

<h3>
	<s:text name="listaCategorie.titolo" />
</h3>

<i>
	<s:text name="listaCategorie.sottotitolo" />
</i>

<br>
<br>

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
				<s:a action="cerca" namespace="/">
					<s:param name="categoria" value="%{cat}" />
					<s:property />
				</s:a>
			</li>
		</s:else>
		
	</s:iterator>
</ul>