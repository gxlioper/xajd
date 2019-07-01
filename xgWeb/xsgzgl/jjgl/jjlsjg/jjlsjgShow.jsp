<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
</head>
<body style="width: 100%">
<html:form action="/jjgl_jjlsjggl" method="post" styleId="jjlsjgForm" onsubmit="return false;">
	<html:hidden property="xh" styleId="xh"/>
	<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>学生基本信息</span>
				</th>
			</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
			<thead>
			<tr>
				<th colspan="4">
					<span>家教信息</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%">
					<span class="red">*</span>擅长科目
				</th>
				<td width="80%" colspan="3">
					${jjlsjgForm.sckmmcs}
				</td>
			</tr>
			<tr>
				<th width="20%">
					家教年级
				</th>
				<td width="30%">
					${jjlsjgForm.jjnjmc}
				</td>
				<th width="20%">
					联系电话
				</th>
				<td width="30%">
					${jjlsjgForm.lxdh}
				</td>
			</tr>

			<tr>
				<th width="20%">
					教学宣言
				</th>
				<td colspan="3" width="80%">
					${jjlsjgForm.jxxy}
				</td>

			</tr>
			<tr>
				<th width="20%">
					优秀教员
				</th>
				<td width="30%">
					<input type="checkbox" name="yxjy" value="1" ${jjlsjgForm.yxjy=="1" ? "checked" : ""} onclick="return false" />
				</td>
				<th>置顶显示</th>
				<td>
					<input type="checkbox" name="zdxs" value="1" ${jjlsjgForm.zdxs=="1" ? "checked" : ""} onclick="return false" />
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div style="height:35px"></div>
	<div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
			<tr>
				<td colspan="4">
					<div class="btn">
						<button type="button" onclick="iFClose();">
							关闭
						</button>
					</div>
				</td>
			</tr>
			</tfoot>
		</table>
	</div>
</html:form>
</body>
</html>

