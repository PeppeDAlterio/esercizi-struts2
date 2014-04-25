<%@ taglib prefix="s" uri="/struts-tags" %>

<s:bean name="it.store.bean.account.DettAccountBean" var="accountBean">
	<s:param name="email" value="#parameters.email" />
	<s:param name="userId" value="#parameters.userid" />
</s:bean>

<s:push value="#accountBean.userData">
	<table class="commonTable">
		
		<tr class="commonTr">
			<th class="commonTh" colspan="2">
				<s:text name="profiloView.titolo" />
			</th>
		</tr>
		
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="profiloView.email" />
			</td>
			<td class="commonTd">
				<s:property value="email" default="N/A" />
			</td>
		</tr>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="profiloView.userId" />
			</td>
			<td class="commonTd">
				<s:property value="userId" default="N/A" />
			</td>
		</tr>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="profiloView.nome" />
			</td>
			<td class="commonTd">
				<s:property value="nome" default="N/A" />
			</td>
		</tr>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="profiloView.cognome" />
			</td>
			<td class="commonTd">
				<s:property value="cognome" default="N/A" />
			</td>
		</tr>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="profiloView.codice_fiscale" />
			</td>
			<td class="commonTd">
				<s:property value="codice_fiscale" default="N/A" />
			</td>
		</tr>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="profiloView.telefono_fisso" />
			</td>
			<td class="commonTd">
				<s:property value="telefono_fisso" default="N/A" />
			</td>
		</tr>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="profiloView.telefono_mobile" />
			</td>
			<td class="commonTd">
				<s:property value="telefono_mobile" default="N/A" />
			</td>
		</tr>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="profiloView.email_secondaria" />
			</td>
			<td class="commonTd">
				<s:property value="email_secondaria" default="N/A" />
			</td>
		</tr>
		<tr class="commonTr">
			<td class="commonTd">
				<s:text name="profiloView.tipo" />
			</td>
			<td class="commonTd">
				<s:property value="tipo_stringa" default="N/A" />
			</td>
		</tr>
		
	</table>
	
	<%-- La modifica è permessa solo per account di livello uguale o inferiore a quello in uso --%>
	<s:if test="%{tipo <= #session.userData.tipo}">
		<s:a action="modificaAccount" namespace="/areaoperatore">
			<s:param name="email" value="%{email}" />
			
			<s:text name="profiloView.modifica" />
		</s:a>
	</s:if>
		
</s:push>