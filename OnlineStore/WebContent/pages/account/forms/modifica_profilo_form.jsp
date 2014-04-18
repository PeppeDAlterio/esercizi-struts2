<%@ taglib prefix="s" uri="/struts-tags" %>

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

<table class="commonTable" style="width: 80%;">
	<tr class="commonTr">
		<th class="commonTh" colspan="2"><s:property value="getText('profiloForm.titolo')" /></th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="modificaProfilo" namespace="/account" cssStyle="width: 100%;">
				<s:hidden key="password" value="x" />
				<s:textfield label="%{getText('profiloForm.email')}" key="email" value="%{#session.userData.email}"  readonly="true" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('profiloForm.userId')}" key="userId" value="%{#session.userData.userId}" maxlength="20" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('profiloForm.nome')}" key="nome" value="%{#session.userData.nome}" maxlength="25" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('profiloForm.cognome')}" key="cognome" value="%{#session.userData.cognome}" maxlength="20" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('profiloForm.cod_fis')}" key="codice_fiscale" value="%{#session.userData.codice_fiscale}" readonly="true" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('profiloForm.tel_fisso')}" key="telefono_fisso" value="%{#session.userData.telefono_fisso}" maxlength="11" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('profiloForm.tel_mobile')}" key="telefono_mobile" value="%{#session.userData.telefono_mobile}" maxlength="50" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('profiloForm.email_secondaria')}" key="email_secondaria" value="%{#session.userData.email_secondaria}" maxlength="50" cssStyle="width: 80%;" />
				<s:submit value="%{getText('profiloForm.submit')}" />
			</s:form>
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" colspan="2" style="text-align: center;">
			<s:property value="getText('profiloForm.mod_pass')" />&nbsp;<s:a action="modificaPasswordForm" namespace="/account"><s:property value="getText('global.click')" /></s:a>
		</td>
	</tr>
</table>