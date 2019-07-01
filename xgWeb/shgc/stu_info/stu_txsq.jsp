<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript">
		function printTx(url){
			var xh = document.getElementById("xh").value;
			var sjhm = document.getElementById("sjhm").value;
			var qtdh = document.getElementById("qtdh").value;
			var sqly = document.getElementById("sqly").value;
			var sqrq = document.getElementById("sqsj").value;
			url += "&xh="+xh;
			url += "&sjhm="+sjhm;
			url += "&qtdh="+qtdh;
			url += "&sqly="+sqly;
			url += "&sqrq="+sqrq;
			
			window.open(url);
		}
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 退学申请 - 填写申请表</a>
			</p>
		</div>


		<html:form action="/data_search" method="post">
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<logic:equal name="dataSaved" value="ok" scope="request">
					<script>
    					alert("保存成功！");
    				</script>
				</logic:equal>
			</logic:notEmpty>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/stu_cgcj.do?act=stu_tx_sq" />


			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>填写申请表</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<logic:equal value="yes" name="writeAble">
									<div class="btn">
										<button
											onclick="commSave('/xgxt/stu_cgcj.do?act=stu_tx_sq&doType=save','xh-sqly-qtdh')">
											提 交 申 请
										</button>
										<!---上海工程技术大学 shgc/stu_info/xstxsqb.jsp'-->
										<logic:equal value="10856" name="xxdm">
											<button
												onclick="printTx('business.do?method=printTxreporter')">
												打 印 报 表
											</button>
										</logic:equal>
									</div>
								</logic:equal>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th width="16%">
									<font color="red">*</font>学号
								</th>
								<td width="30%">
									<logic:equal value="view" name="doType">
									<html:text name='rs' property="xh" styleId="xh" readonly="true"/>		
									<input type="hidden" id="sqsj" name="sqsj" value="${rs.sqrq}"/>							
								</logic:equal>
								<logic:notEqual value="view" name="doType">
									<input type="hidden" id="sqsj" name="sqsj" value="${rs.sqrq}"/>
									<html:text name='rs' property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) return false;" />
									<button onClick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notEqual>	
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th width="16%">
									<font color="red">*</font>学号：
								</th>
								<td width="30%">
									<input type="text"
										value="<bean:write name='rs' property="xh" />"
										readonly="readonly" />
									<input type="hidden" id="xh" name="xh"
										value="<bean:write name='rs' property="xh" />"
										readonly="readonly" />
								</td>
							</logic:equal>
							<th width="16%">
								专业
							</th>
							<td width="30%">
								<bean:write name="rs" property="zymc" />
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<bean:write name='rs' property="xb" />
							</td>
							<th>
								班级
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr>
							<th>
								手机号
							</th>
							<td align="left">
								<html:text name="rs" property="sjhm" />
							</td>
							<th>
								<font color="red">*</font>家庭电话
							</th>
							<td align="left">
								<html:text name="rs" property="qtdh" styleId="qtdh" />
							</td>
						</tr>
						<tr>
							<th>
								身份证号
							</th>
							<td align="left">
								<bean:write name="rs" property="sfzh" />
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>申请理由
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="sqly"
									style="height:100px;width:95%" styleId="sqly" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("申请成功！");
					Close();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("申请失败！");
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
