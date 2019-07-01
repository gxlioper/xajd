<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<style>

#shlccx_table th{text-align: center;}
#shlccx_table tr{text-align: center;}
</style>
<div class="con_overlfow">
<table id="shlccx_table" width="100%" class="formlist" >
	<tr id="sh_th">
		<th>投递岗位</th>
		<th>单位名称</th>
		<th>投递日期</th>
		<th>录用日期</th>
		<th>状态</th>
	</tr>
	<logic:empty name="qglsjlList">
		<tr>
			<td colspan="5">暂时没有数据</td>
		</tr>
	</logic:empty>
	<logic:notEmpty name="qglsjlList">
		<logic:iterate id="i" name="qglsjlList" >
			<tr>
				<td>${i.gwmc}</td>
				<td>${i.yrdwmc}</td>
				<td>${i.sqsj}</td>
				<td>${i.sgsj}</td>
				<td>${i.zgzt}</td>
			</tr>
		</logic:iterate>
	</logic:notEmpty>
</table>
</div>
