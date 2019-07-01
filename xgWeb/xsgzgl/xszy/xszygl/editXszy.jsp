<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/xszygl/js/xszygl.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xszygl" method="post" styleId="XszyglForm" onsubmit="return false;">
		    <html:hidden property="id" styleId="id"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>新生之友信息修改</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>职工号
							</th>
							<td width="30%">
								${xszyForm.zgh }
							</td>
							<th width="20%">
								<font color="red">*</font>姓名
							</th>
							<td width="30%">
								<html:text property="xm" styleId="xm" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>性别
							</th>
							<td width="30%">
								<html:select property="xb" styleId="xb"
									style="width:150px;">
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>职务职称
							</th>
							<td width="30%">
								<html:text property="zwzc" styleId="zwzc" maxlength="64"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>单位
							</th>
							<td width="30%">
								<html:select property="dwdm" styleId="dwdm" style="width:200px">
									<html:options collection="dwList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>联系电话
							</th>
							<td width="30%">
								<html:text property="lxdh" styleId="lxdh" maxlength="11" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>政治面貌
							</th>
							<td width="30%">
								<html:select property="zzmmdm" styleId="zzmmdm">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>邮箱
							</th>
							<td width="30%">
								<html:text property="dzyx" styleId="dzyx"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>大类要求
							</th>
							<td width="30%" colspan="3">
								<html:textarea property="dlyq" styleId="dlyq" cols="10" rows="4"
									style="width:100%"></html:textarea>
							</td>
						</tr>
						<tr>
						    <th>
								备注</br><font color="red"></font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" cols="50" rows="4"
									style="width:100%" onblur="chLeng(this,500)"></html:textarea>
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
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveXszy('update')">
									保 存
								</button>
								<button type="button" onclick="Close();return false;">
									关 闭
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

