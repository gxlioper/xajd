<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
		<html:form action="/qgzx_kh_modicheck" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>
						<logic:equal value="10827" name="xxdm">
							当前位置学生义工 - 卡号更改申请 - 填写申请表
						</logic:equal>
						<logic:notEqual value="10827" name="xxdm">
							当前位置勤工助学 - 卡号更改申请 - 填写申请表
						</logic:notEqual>
					</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    				alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/qgzx_kh_modiapply.do" />
				<input type="hidden" id="gwsbsj" name="gwsbsj" />
				
				<div class="tab">
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>填写申请表</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th>
								<font color="red">*</font>学号
							</th>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th>
								<font color="red">*</font>学号
							</th>
							<td align="left">
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<th>
							<font color="red">*</font>修改类型
						</th>
						<td align="left">
							<html:select name="rs" property="xglx" styleId="xglx" style="width:150px">
								<html:option value=""></html:option>
								<html:option value="报失">报失</html:option>
								<html:option value="修改">修改</html:option>
							</html:select>
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
							<input type="text" name="xgqkh" id="xgqkh" value="<bean:write name="rs" property="xgqkh" />" readonly="readonly"/>
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
							<html:text name="rs" property="xghkh"></html:text>
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
							<html:text name='rs' property="lxdh" value="${rs.lxdh}"/>
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
							专业
						</th>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							班级
						</th>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<logic:present name="sh">
						<th>
							审核
						</th>
						<td align="left">
						</td>
						</logic:present>
						<logic:notPresent name="sh">
						<th>
						</th>
						<td align="left">
						</td>
						</logic:notPresent>
					</tr>
					
					<tr align="left" style="height:22px">
						<th>
							申请原因
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='sqyy' styleId="sqyy"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<th>
							备注
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' />
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
							<button type="button" name="提交" onclick="refreshForm('/xgxt/Savekhapply.do')">提交</button>
							<button type="button" onclick="expAppTab('rsT','勤工助学岗位申请表','')">打印</button>
				          </div></td>
				      </tr>
				    </tfoot>
				</table>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("申请失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
