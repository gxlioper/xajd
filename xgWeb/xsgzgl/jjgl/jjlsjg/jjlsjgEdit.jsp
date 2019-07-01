<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="xsgzgl/jjgl/jjlsjg/js/jjlsjg.js"></script>
	<script type='text/javascript' src="js/check.js"></script>
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
					<logic:iterate id="jjxk" name="jjxkList">
						<input type="checkbox" name="sckm" value="${jjxk.jjxkmc}" ${jjxk.checked == "1" ? "checked" : ""}/> ${jjxk.jjxkmc}
					</logic:iterate>
				</td>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*</font>家教年级
				</th>
				<td width="30%">
					<html:select  property="jjnj" styleId="jjnj" style="width:100px" >
						<html:options collection="jjnjList" labelProperty="jjnjmc" property="jjnjdm"/>
					</html:select>
				</td>
				<th width="20%">
					<font color="red">*</font>联系电话
				</th>
				<td width="30%">
					<html:text property="lxdh" styleId="lxdh" ></html:text>
				</td>
			</tr>

			<tr>
				<th width="20%">
					教学宣言<br/>
					<font color="red">（限制在150字内）</font>
				</th>
				<td colspan="3" width="80%">
					<html:textarea property="jxxy" styleId="jxxy"
								   onkeypress="checkLen(this,150);"
								   style="width:99%;" rows="4"></html:textarea>
				</td>

			</tr>
			<tr>
				<th width="20%">
					优秀教员
				</th>
				<td width="30%">
					<input type="checkbox" name="yxjy" value="1" ${jjlsjgForm.yxjy=="1" ? "checked" : ""}/>
				</td>
				<th>置顶显示</th>
				<td>
					<input type="checkbox" name="zdxs" value="1" ${jjlsjgForm.zdxs=="1" ? "checked" : ""}/>
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
						<button type="button" onclick="jjlsjgSaveForEdit();">
							保存
						</button>
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

