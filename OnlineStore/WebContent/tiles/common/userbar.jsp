<%@ taglib prefix="s" uri="/struts-tags" %>

<table style="width: 100%;">
	<tr>
		<td>
			<s:a action="listaCategorie" namespace="/catalogo">
				<s:text name="userbar.categorie" />
			</s:a>
		</td>
	<td>
		<s:form action="cerca" namespace="/" theme="simple" method="GET">
			<s:text name="userbar.ricerca" />&nbsp;:&nbsp;<s:textfield key="q" cssStyle="width: 70%;" required="true" />&nbsp;<s:submit value="%{getText('userbar.cerca_button')}" />
		</s:form>
	</td>
	<td>
		<s:a action="carrello" namespace="/ordine">
			<s:text name="userbar.carrello" />&nbsp;( <s:property value="#session.carrello.numero_articoli" default="0" /> )
		</s:a>
	</td>
	<td>
		<s:if test="%{#session.userData==null}">
			<s:a action="loginForm" namespace="/utente"><s:text name="userbar.accedi" /></s:a>/<s:a action="signupClienteForm" namespace="/utente"><s:text name="userbar.registrati" /></s:a>
		</s:if>
		<s:else>
			<s:text name="userbar.benvenuto" />&nbsp;<s:property value="#session.userData.userId"/>&nbsp;-&nbsp;<s:a action="logout" namespace="/account"><s:text name="userbar.esci" /></s:a>
		</s:else>
	</td>
	<td>
		<s:a action="pannelloControlloAccount" namespace="/account"><s:text name="userbar.area_personale" /></s:a>
	</td>
	</tr>
</table>