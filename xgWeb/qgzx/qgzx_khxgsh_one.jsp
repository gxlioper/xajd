<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkWinType();">
		<html:form action="/qgzx_kh_modicheck" method="post">
			<input type="hidden" name="xh" id="xh" value="<bean:write name="xh"/>"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>
					<logic:equal value="10827" name="xxdm">
						当前所在位置学生义工 - 审核 - 卡号更改审核 - 单个审核
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						当前所在位置勤工助学 - 审核 - 卡号更改审核 - 单个审核
					</logic:notEqual>	
					</a>
				</p>
			</div>

			<div class="tab">	
			<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>单个学生申请审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr style="height:22px">
						<th>
							学号
						</th>
						<td align="left">
							<bean:write name='rs' property="xh" />
						</td>
						<th>
							修改类型
						</th>
						<td align="left">
							<bean:write name='rs' property="xglx" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							姓名
						</th>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<th>
							修改前卡号
						</th>
						<td align="left">
							<bean:write name="rs" property="xgqkh" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							性别
						</th>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<th>
							修改后卡号
						</th>
						<td align="left">
							<input type="text" id="xghkh" name="xghkh" readonly="readonly" value="<bean:write name='rs' property="xghkh" />"/>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							年级
						</th>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
						<th>
							联系电话
						</th>
						<td align="left">
							<bean:write name='rs' property="lxdh" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<th>
							审核
						</th>
						<td align="left">
							<html:select name="rs" property="sfsh" style="width:130px" styleId="sfsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							专业
						</th>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<th>
						</th>
						<td align="left">
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							班级
						</th>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<th>
						</th>
						<td align="left">
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<th>
							申请原因
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='sqyy' styleId="sqyy"
								style="width:99%" rows='5' readonly="true"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<th>
							备注
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' readonly="true"/>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
							  <button type="button" onclick="refreshForm('/xgxt/SaveStuKhxgChkOne.do');" id="buttonSave">
								保存
							  </button>
							  <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
				          </div></td>
				      </tr>
				    </tfoot>
				</table>
				</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("操作成功！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
