<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var xxdm = document.getElementById('xxdm').value;
			var lxfs_br = document.getElementById('lxfs_br').value;
			var lxfs_jz = document.getElementById('lxfs_jz').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(xxdm == "10338"){
				//浙江理工大学
				if(lxfs_br == null || lxfs_br == ""){
					alert("本人联系方式不能为空!");
					return false;
				}
				if(lxfs_jz == null || lxfs_jz == ""){
					alert("家长联系方式不能为空!");
					return false;
				}
			}
			
			if(checkSjTj("cgrq","出国日期","fhrq","返回日期")){
				showTips('处理数据中，请等待......');
				document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=cgjsqSave";
				document.forms[0].submit();
			}
		}
		
		function back() {
			document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=cgjDate";
			document.forms[0].submit();
		}
	</script>
	</head>

	<body>
		<html:form action="zzlgdx_rcsw.do?method=cgjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zzlgdx_rcsw.do?method=cgjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />"/>
			<input type="hidden" id="guid" name="guid"
				value="<bean:write name="rs" property="guid" />"/>
			<input type="hidden" id="type" name="type"
				value="<bean:write name="type" />"/>
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" />"/>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript" defer>
	         	alert("保存成功！");
	         	if (window.dialogArguments) {
	         		close();
	         		window.dialogArguments.document.getElementById('search_go').click();
	         	}
	         	</script>
				</logic:match>
			<logic:notPresent name="isPASS">	
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("已有重复记录,保存失败！");
	         	</script>
				</logic:match>
			</logic:notPresent>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过学校审核，不能提交！");
	         		</script>
				</logic:match>
			</logic:present>


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>出国(境)申请</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" name="保存" onclick="yz();">
										保存
									</button>
									<logic:equal name="userOnLine" value="teacher" scope="session">
										<button type="button" onclick="Close();return false;">
											关闭
										</button>
									</logic:equal>
									<logic:notEqual name="userOnLine" value="teacher"
										scope="session">
										<button type="button" onclick="back();">
											返回
										</button>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th width="16%">
									<font color="red">*</font>学号
								</th>
								<td width="34%">
									<html:text name='rs' property="xh" styleId="xh" 
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:notEqual name="type" value="modi">
										<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:notEqual>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th width="16%">
									<font color="red">*</font>学号
								</th>
								<td width="34%">
									<input type="text" id="xh" name="xh"
										value="<bean:write name='rs' property="xh" />" readonly="true" />
								</td>
							</logic:equal>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								<input type="text" readonly="readonly" id="xm" name="xm"
									value="<bean:write name="rs" property="xm"/>"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<input type="text" id="xb" readonly="readonly" name="xb"
									value="<bean:write name="rs" property="xb"/>" />
							</td>
							<th>
								身份证号
							</th>
							<td>
								<input type="text" id="sfzh" name="sfzh" readonly="readonly"
									value="<bean:write name="rs" property="sfzh"/>" />
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<input type="text" id="nj" readonly="readonly" name="nj"
									value="<bean:write name="rs" property="nj"/>" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />名称
							</th>
							<td>
								<input type="text" id="xymc" name="xymc" readonly="readonly"
									value="<bean:write name="rs" property="xymc"/>" />
							</td>
						</tr>
						<tr>
							<th>
								专业名称
							</th>
							<td>
								<input type="text" id="zymc" readonly="readonly" name="zymc"
									value="<bean:write name="rs" property="zymc"/>" />
							</td>
							<th>
								班级名称
							</th>
							<td>
								<input type="text" id="bjmc" name="bjmc" readonly="readonly"
									value="<bean:write name="rs" property="bjmc"/>" />
							</td>
						</tr>
						<logic:equal value="10338" name="xxdm">
							<tr>
								<th>
									<font color="red">*</font>本人联系方式
								</th>
								<td>
									<input type="text" id="lxfs_br" name="lxfs_br" maxlength="20"
										value="<bean:write name="rs" property="lxfs_br"/>"
										onkeyup="value=value.replace(/[^\d]/g,'')"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
								<th>
									<font color="red">*</font>家长联系方式
								</th>
								<td>
									<input type="text" id="lxfs_jz" name="lxfs_jz" maxlength="20"
										value="<bean:write name="rs" property="lxfs_jz"/>"
										onkeyup="value=value.replace(/[^\d]/g,'')"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm">
							<tr>
								<th>
									本人联系方式
								</th>
								<td>
									<input type="text" id="lxfs_br" name="lxfs_br" maxlength="20"
										value="<bean:write name="rs" property="lxfs_br"/>"
										onkeyup="value=value.replace(/[^\d]/g,'')"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
								<th>
									家长联系方式
								</th>
								<td>
									<input type="text" id="lxfs_jz" name="lxfs_jz" maxlength="20"
										value="<bean:write name="rs" property="lxfs_jz"/>"
										onkeyup="value=value.replace(/[^\d]/g,'')"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th>
								申请时间
							</th>
							<td>
								<input type="text" id="sqsj" readonly="readonly" name="sqsj"
									value="<bean:write name="rs" property="sqsj"/>" />
							</td>
							<th>
								申请去向
							</th>
							<td>
								<input type="text" id="sqqx" name="sqqx" maxlength="100"
									value="<bean:write name="rs" property="sqqx"/>" />
							</td>
						</tr>
						<tr>
							<th>
								出国日期
							</th>
							<td>
								<input type="text" readonly 
									onclick="return showCalendar('cgrq','y-mm-dd');"
									value="<bean:write name='rs' property="cgrq" />" name="cgrq"
									id="cgrq" />
							</td>
							<th>
								返回日期
							</th>
							<td>
								<input type="text" readonly
									onclick="return showCalendar('fhrq','y-mm-dd');"
									value="<bean:write name='rs' property="fhrq" />" name="fhrq"
									id="fhrq" />
							</td>
						</tr>
						<tr>
							<th>
								申请理由
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="sqly" rows='10'
									style="width:99%;word-break:break-all;" onblur="chLeng(this,1000)" />
							</td>
						</tr>
				</table>
			</div>
		</html:form>
	</body>
	</html>