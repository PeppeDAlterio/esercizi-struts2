<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK href="<%=request.getContextPath() %>/css/layout.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
<title><tiles:insertAttribute name="title" ignore="true" /> - <s:text name="global.nome_sito" /> </title>
</head>
<body>

<table class="layoutTable" style="width: 90%; margin: auto;">

<!-- Logo -->
<tr class="layoutTr">
<th class="layoutTh" colspan="2"><tiles:insertAttribute name="logo" /></th>
</tr>

<!-- Userbar -->
<tr class="layoutTr">
<td class="layoutTd" colspan="2" style="border: 1px #C97B32 solid;"><tiles:insertAttribute name="userbar" ignore="true" /></td>
</tr>

<!-- Body / right box  -->
<tr class="layoutTr">
<!-- Body -->
<td class="layoutTd" rowspan="2" style="border-left: 1px #C97B32 solid; border-right: 1px #C97B32 solid; text-align: center; padding: 10px; padding-bottom: 20px;"><tiles:insertAttribute name="body" /></td>

<!-- Right box up -->
<td class="layoutTd" style="width: 20%; border-right: 1px #C97B32 solid;"><tiles:insertAttribute name="r_box_up" /></td>
</tr>

<tr class="layoutTr">
<!-- Right box down -->
<td class="layoutTd" style="width: 20%; border-right: 1px #C97B32 solid;"><tiles:insertAttribute name="r_box_dw" /></td>
</tr>

<tr class="layoutTr">
<!-- Footer -->
<td class="layoutTd" colspan="2" style="border: 1px #C97B32 solid;"><tiles:insertAttribute name="footer" /></td>
</tr>

</table>

</body>
</html>