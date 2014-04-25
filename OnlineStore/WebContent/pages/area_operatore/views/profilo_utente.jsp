<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" jquerytheme="ui-lightness" />


<%-- BEAN: Leggo dati utente --%>
<s:bean name="it.store.bean.account.DettAccountBean" var="accountBean">
	<s:param name="email" value="#parameters.email" />
	<s:param name="userId" value="#parameters.userid" />
</s:bean>

<script type="text/javascript">		
	$(function(){
		
		$("#profiloForm :input").attr('disabled', true);
		$("#profiloForm :submit").attr('disabled', true);
		
	});
	
	function consentiModifica() {
		$("#profiloForm :input").attr('disabled', false);
		$("#profiloForm :submit").attr('disabled', false);
	}
</script>

<s:push value="#accountBean.userData">
	<table class="commonTable" style="width: 90%;">
		
		<tr class="commonTr">
			<th class="commonTh" colspan="2">
				<s:text name="profiloView.titolo" />
			</th>
		</tr>
		
		<tr class="commonTr">
			<td>
				<s:form id="profiloForm" action="modificaAccount" namespace="/areaoperatore" cssStyle="margin: auto;">
					<s:hidden key="old_email" value="%{email}" />
					<s:textfield label="%{getText('profiloView.email')}" key="email" readonly="true" />
					<s:textfield label="%{getText('profiloView.userId')}" key="userId" />
					<s:textfield label="%{getText('profiloView.nome')}" key="nome" />
					<s:textfield label="%{getText('profiloView.cognome')}" key="cognome" />
					<s:textfield label="%{getText('profiloView.codice_fiscale')}" key="codice_fiscale" />
					<s:textfield label="%{getText('profiloView.telefono_fisso')}" key="telefono_fisso" />
					<s:textfield label="%{getText('profiloView.telefono_mobile')}" key="telefono_mobile" />
					<s:textfield label="%{getText('profiloView.email_secondaria')}" key="email_secondaria" />
					<s:hidden key="old_tipo" value="%{tipo}" />
					<s:select label="%{getText('profiloView.tipo')}" key="tipo" list="#{1:1, 2:2, 3:3 }" />
					<s:submit value="%{getText('profiloView.submit')}" />
				</s:form>
			</td>
		</tr>
	</table>
	
	<%-- La modifica è permessa solo per account di livello uguale o inferiore a quello in uso --%>
	<s:if test="%{tipo <= #session.userData.tipo}">
		<input type="button" value="<s:text name='profiloView.modifica' />" onclick="consentiModifica();" />
	</s:if>
		
</s:push>