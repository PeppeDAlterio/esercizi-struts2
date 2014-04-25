<%@ taglib prefix="s" uri="/struts-tags" %>

<table class="commonTable" style="width: 90%;">

	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="ricercaAccountForm.titolo" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="visualizzaProfilo" namespace="/areaoperatore" method="GET" cssStyle="margin: auto;">
				<s:textfield label="%{getText('ricercaAccountForm.email')}" key="email" maxlength="50" />
				<s:textfield label="%{getText('ricercaAccountForm.userid')}" key="userid" maxlength="20" />
				<s:submit value="%{getText('ricercaAccountForm.submit')}" />
			</s:form>
		</td>
	</tr>
</table>