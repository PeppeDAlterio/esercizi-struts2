<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" jquerytheme="ui-lightness" />

<s:if test="%{#parameters.id_ordine!=null && #request.id_ordine==null}">
	<s:set var="id_ordine" value="#parameters.id_ordine" scope="request" />
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

<table class="commonTable" style="width: 90%;">

	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="feedbackForm.titolo" />
		</th>
	</tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<div style="text-align: center;">
				<s:text name="feedbackForm.rivedi_ordine" />:&nbsp;
				<s:a action="visualizzaOrdine" namespace="/areacliente" target="_blank">
					<s:param name="id_ordine" value="#request.id_ordine" />
				
					<s:text name="feedbackForm.id_ordine" />&nbsp;<s:property value="#request.id_ordine" />
				</s:a>
			</div>
			<s:form action="rilasciaFeedback" namespace="/areacliente" cssStyle="margin: auto; ">
				<s:textfield label="%{getText('feedbackForm.id_ordine')}" key="id_ordine" value="%{#request.id_ordine}" readonly="true" />
				<sj:spinner label="%{getText('feedbackForm.punteggio')}" key="punteggio" min="1" max="5" step="+1" value="1" mouseWheel="true" required="true" />
				<s:textarea label="%{getText('feedbackForm.messaggio')}" key="messaggio" maxlength="255" />
				<s:submit value="%{getText('feedbackForm.submit')}" />
			</s:form>
		</td>
	</tr>

</table>