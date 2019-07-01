<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
		<table width="100%" border="0" class="formlist">
			<tr>
				<th align="left">年级</th>
				<th><bean:message key="lable.xb" /></th>
				<th>专业</th>
				<th>班级</th>
				<th>班级人数</th>
				<th> 操作</th>
			</tr>
			<logic:iterate id="bj" name="bjList">
			<tr>
				<td>${bj.nj}</td>
				<td>${bj.xymc}</td>
				<td>${bj.zymc}</td>
				<td>${bj.bjmc}</td>
				<td>${bj.bjrs}</td>
				<td><a class='name'  onclick="fdyrz_qxsq('${bj.bjdm}');">取消</a></td>
			</tr>
			</logic:iterate>
		</table>

