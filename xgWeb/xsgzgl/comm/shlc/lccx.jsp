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
		<th width="8%">���</th>
		<th width="10%">��˸�λ</th>
		<th width="10%">�����</th>
		<th width="10%">��˽��</th>
		<th width="15%">���ʱ��</th>
		
		<logic:empty name="ywzdFlg2">
			<th width="35%">������</th>
		</logic:empty>
		<logic:notEmpty name="ywzdFlg2">
		<th width="25%">������</th>
		</logic:notEmpty>
		
		<logic:notEmpty name="ywzdFlg">
			<th>${ywzdFlg}</th>
		</logic:notEmpty>
		<logic:notEmpty name="ywzdFlg2">
			<th width="10%">${ywzdFlg2}</th>
		</logic:notEmpty>
	</tr>
	<logic:empty name="infoList">
		<tr>
			<td colspan="6">��ʱû������</td>
		</tr>
	</logic:empty>
	<logic:notEmpty name="infoList">
		<logic:iterate id="sh" name="infoList" >
			<tr>
				<td>${sh.rownum}</td>
				<td>${sh.mc}</td>
				<td>${sh.shr}</td>
				<td>
					<logic:equal value="0" name="sh" property="shzt">�����
					<html:hidden property="gwid" name="sh" styleId="gwid"/>
					</logic:equal>
					<logic:equal value="1" name="sh" property="shzt">���ͨ��</logic:equal>
					<logic:equal value="2" name="sh" property="shzt">��˲�ͨ��</logic:equal>
					<logic:equal value="3" name="sh" property="shzt">���˻�</logic:equal>
					<logic:equal value="4" name="sh" property="shzt">������
					<html:hidden property="gwid" name="sh" styleId="gwid"/>
					</logic:equal>
					<logic:equal value="5" name="sh" property="shzt">�����</logic:equal>
					<logic:equal value="9" name="sh" property="shzt">ȡ�����</logic:equal>
				</td>
				<td>${sh.shsj}</td>
				<td style="word-break:break-all;text-align:left;">${sh.shyj}</td>
				<logic:notEmpty name="ywzdFlg">
					<td>
						<logic:equal value="1" name="sh" property="shzt">
							${sh.zd3}
						</logic:equal>
						<logic:equal value="0" name="sh" property="shzt">
							${sh.zd3}
						</logic:equal>
						<logic:equal value="10026" name="xxdm">
							<logic:equal value="3" name="sh" property="shzt">
								${sh.zd3}
							</logic:equal>
						</logic:equal>						
					</td>
				</logic:notEmpty>
				<logic:notEmpty name="ywzdFlg2">
					<td>
						<logic:equal value="1" name="sh" property="shzt">${sh.zd6}</logic:equal>
						<logic:equal value="0" name="sh" property="shzt">${sh.zd6}</logic:equal>
					</td>
				</logic:notEmpty>
			</tr>
		</logic:iterate>
		
	</logic:notEmpty>
	
</table>
</div>
