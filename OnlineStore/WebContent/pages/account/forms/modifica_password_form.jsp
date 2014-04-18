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
		<th class="commonTh">
			<s:property value="%{getText('pswForm.titolo')}" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="modificaPassword" namespace="/account">
				<s:hidden key="email" value="%{#session.userData.email}" />
				<s:password label="%{getText('pswForm.vecchia_pass')}" key="vecchia_password" required="true" />
				<s:password label="%{getText('pswForm.nuova_pass')}" key="nuova_password" required="true" />
				<s:password label="%{getText('pswForm.rip_pass')}" key="nuova_password" required="true" />
				<s:submit value="%{getText('pswForm.submit')}" />
			</s:form>
		</td>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd" colspan="2" style="text-align: center;">
			<s:property value="getText('pswForm.mod_prof')" />&nbsp;<s:a action="modificaProfiloForm" namespace="/account"><s:property value="getText('global.click')" /></s:a>.
		</td>
	</tr>
	
</table>