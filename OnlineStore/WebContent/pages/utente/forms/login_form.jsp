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

<table class="commonTable">

	<tr class="commonTr">
		<th class="commonTh" colspan="2"><s:property value="getText('loginForm.accesso_utenti')" /></th>
	</tr>
	
	<tr class="commonTr">
		<th class="commonTh" rowspan="2"><s:property value="getText('loginForm.login')" /></th>
		<td class="commonTd">
			<s:form name="loginForm" action="login" namespace="/utente" cssStyle="width: 100%;">
				<s:textfield label="%{getText('loginForm.email')}" key="email" required="true" />
				<s:password label="%{getText('loginForm.password')}" key="password" required="true" />
				<s:submit value="%{getText('loginForm.submit')}" />
			</s:form>
		</td>
	</tr>
	
	<tr>
		<td>
			<ul style="text-align: left;">
				<li><s:a action="passwordDimenticata" namespace="/utente"><s:property value="getText('loginForm.psw_dimenticata')" /></s:a></li>
				<li><s:a action="signupClienteForm" namespace="/utente"><s:property value="getText('loginForm.signup')" /></s:a></li>
			</ul>
		</td>
	</tr>

</table>

