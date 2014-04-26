<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" jquerytheme="ui-lightness" />


<%-- BEAN: Leggo dati utente --%>
<s:bean name="it.store.bean.account.DettAccountBean" var="accountBean">
	<s:param name="email" value="#parameters.email" />
	<s:param name="userId" value="#parameters.userid" />
</s:bean>

<script type="text/javascript">	

function getUrlParam(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return decodeURIComponent(sParameterName[1]);
        }
    }
}
	$(function(){
			
		if(getUrlParam("edit") == null) {
			$("#profiloForm :input").attr('disabled', true);
			$("#profiloForm :submit").attr('disabled', true);
		}
		
	});
	
	function consentiModifica() {
		$("#profiloForm :input").attr('disabled', false);
		$("#profiloForm :submit").attr('disabled', false);
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
					<div style="text-style: italic;">
						1: <s:text name="cliente" />
						<br>
						2: <s:text name="operatore" />
						<br>
						3: <s:text name="amministratore" />
					</div>
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