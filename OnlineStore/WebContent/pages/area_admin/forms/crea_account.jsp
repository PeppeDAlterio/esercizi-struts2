<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="%{#request.tipiAccount!=null}">
	<s:set var="tipiAccount" value="#request.tipiAccount" scope="session" />
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

<table class="commonTable" style="width: 80%;">
	<tr class="commonTr">
		<th class="commonTh" colspan="2"><s:property value="getText('signupForm.titolo')" /></th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="creaAccount" namespace="/admin" cssStyle="width: 100%;">
				<s:textfield label="%{getText('signupForm.email')}*" key="email" maxlength="50" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('signupForm.userId')}*" key="userId" maxlength="20" required="true" cssStyle="width: 80%;" />
				<s:password label="%{getText('signupForm.psw')}*" key="psw" required="true" cssStyle="width: 80%;" />
				<s:password label="%{getText('signupForm.ripeti_psw')}*" key="psw" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('signupForm.nome')}*" key="nome" maxlength="25" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('signupForm.cognome')}*" key="cognome" maxlength="20" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('signupForm.cod_fis')}*" key="codice_fiscale" maxlength="16" required="true" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('signupForm.tel_fisso')}" key="telefono_fisso" maxlength="11" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('signupForm.tel_mobile')}" key="telefono_mobile" maxlength="11" cssStyle="width: 80%;" />
				<s:textfield label="%{getText('signupForm.email_secondaria')}" key="email_secondaria" maxlength="50" cssStyle="width: 80%;" />
				<s:select label="%{getText('creaAccountForm.tipo')}*" list="#session.tipiAccount" key="tipo" required="true" />
				<s:submit value="%{getText('signupForm.submit')}" />
			</s:form>
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" colspan="2">
			<i>*: <s:property value="getText('form.campo_obbligo')" /></i>
		</td>
	</tr>

</table>