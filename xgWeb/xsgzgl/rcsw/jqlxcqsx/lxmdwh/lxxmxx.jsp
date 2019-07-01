<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<tbody>
	<logic:empty name="xmmcMap">
		<tr>
			<th width="15%">留校项目</th>
			<td width="35%">
				<html:select property="xmid" style="width:50%" styleId="xmid" onchange="changeXmmc(this)" >
					<html:option value=""></html:option>
					<html:options collection="xmmcList" property="xmid" labelProperty="xmmc"/>
				</html:select>
			</td>
			<th width="15%">留校起止时间</th>
			<td width="35%">
				
			</td>
		</tr>
		<tr>
			<th width="15%">留校项目说明</th>
			<td width="85%" colspan="3">
				
			</td>
		</tr>
	</logic:empty>
	<logic:notEmpty name="xmmcMap">
		<tr>
			<th width="15%">留校项目</th>
			<td width="35%">
				<html:select style="width:50%" property="xmid" value="${xmid}" styleId="xmid" onchange="changeXmmc(this)" >
					<html:option value=""></html:option>
					<html:options collection="xmmcList" property="xmid" labelProperty="xmmc"/>
				</html:select>
			</td>
			<th width="15%">留校起止时间</th>
			<td width="35%">
				${xmmcMap.lxkssj}&nbsp;至&nbsp;${xmmcMap.lxjssj}
			</td>
		</tr>
		<tr>
			<th width="15%">留校项目说明</th>
			<td colspan="3" width="85%">
				${xmmcMap.lxxmsm}
			</td>
		</tr>
	</logic:notEmpty>
		
						
</tbody>