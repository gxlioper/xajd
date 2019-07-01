<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:form action="/jtpjsq" styleId="innerForm">
	<table width="" border="0" class="formlist">
		<html:hidden property="jtpjdw" value="qs"/>
		<html:hidden property="pjjtmc" styleId="pjjtmc" />
		<html:hidden property="pjjtid"  styleId="pjjtid"/>
		<html:hidden property="rs"  styleId="rowConut"/><!-- 人数传值 -->
		
		<tbody>
			<tr>
				<th width="20%" style="border-top: 0px;">
					<span class="red">*</span>楼栋
				</th>
				<td width="30%" style="border-top: 0px;">
					<html:select property="lddm" styleId="lddm"   onchange="loadLdxx(); " style="width:200px;">
						<html:option value=""></html:option>
						<html:options collection="ldlist" property="lddm" labelProperty="ldmc" />
					</html:select>
				</td>
				<th style="border-top: 0px;">
					楼栋性别
				</th>
				<td style="border-top: 0px;" id="ldxb">
					
				</td>
			</tr>
				<tr>
					<th>
						层号
					</th>
					<td>
						<html:select property="ch" styleId="ch" onchange="loadChxx();"  style="width:100px;"></html:select>
					</td>
					<th>
						<font color="red">* </font>寝室号
					</th>
					<td>
						<html:select property="qsh" styleId="qsh"  onchange="loadQsxx();" style="width:100px;"></html:select>
					</td>
			</tr>
			<tr>
				<th width="20%">
					寝室学生信息
				</th>
				<td colspan="3">
					<div id="qsxsxx">
						
					</div>
				</td>
			</tr>
	</table>
</html:form>