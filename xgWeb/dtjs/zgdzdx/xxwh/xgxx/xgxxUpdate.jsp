<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	</head>
	<body onload="checkWinType();">
		<html:form action="/zgdzdxXxwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学工信息维护</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="xh" scope="request"/>" />
				<fieldset>
					
					<div class="tab">
					<table width="100%" class="formlist">
					<thead>
						<tr><th colspan="4"><span>地大学工信息</span></th></tr>
					</thead>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				         <button type="button"
							onclick="dtjsCommonSave('zgdzdxXxwh.do?method=xgxxSave','nr','80','lbdm-bt');"
							id="buttonSave">
							保 存
						</button>
						<button type="button" onclick="Close();return false;"
							id="buttonClose">
							关 闭
						</button>
				          </div></td>
				      </tr>
				    </tfoot>
						<tbody>
						<tr>
							<th>
								提交人用户名
							</th>
							<td align="left">
								<html:text name='rs' property="tjyhm" readonly="true"/>
							</td>
							<th>
								提交人姓名
							</th>
							<td align="left">
								<bean:write name ="rs" property="xm" />
							</td>
						</tr>
						<tr>
							<th>
								提交人部门
							</th>
							<td align="left" >
								<bean:write name = "rs" name ="bmmc"/>
							</td>
							<th>
								单位
							</th>
							<td align="left" >
								<html:text name='rs' property="dw" maxlength="100"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>类别名称
							</th>
							<td align="left" >
								<html:select name = "rs" property="lbdm" style="width:80px"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="xgxxlbList" property="lbdm"
											labelProperty="lbmc" />
								</html:select>
							</td>
							<th>
								期届
							</th>
							<td align="left" >
								<html:text name='rs' property="qj" maxlength="10"/>
							</td>
						</tr>
						
						<tr align="left">
							<th>
								<font color="red">*</font>标题
							</th>
							<td colspan="3">
								<html:text name='rs' property='bt' style="width:99%" maxlength="100"/>
							</td>
						</tr>
						<tr align="left">
							<th >
								主题
							</th>
							<td colspan="3">
								<html:text name='rs' property='zt' style="width:99%" maxlength="100"/>
							</td>
						</tr>
						<tr align="left" id="nr">
							<th>
								内容
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='nr' style="width:99%"
									rows='20' />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
				</fieldset>
				<div class="buttontool">
					
				</div>
			</logic:notEmpty>
			<logic:present name="update">
				<logic:equal name="update" value="yes">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
