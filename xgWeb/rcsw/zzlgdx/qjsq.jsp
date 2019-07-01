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
			var qjyy = document.getElementById('qjyy').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(qjyy != null){
	         	if(qjyy.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("请假原因不能大于1000个字");
	          		 return false;
	       		 }
	       	}
	       	if(checkSjTj("qjksrq","请假开始日期","qjjzrq","请假截止日期")){
				showTips('处理数据中，请等待......');
				document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=qjsqSave";
				document.forms[0].submit();
			}
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function back() {
			document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=qjDate";
			document.forms[0].submit();
		}
	</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>日常事务 - 申请 - 请假申请</a>
			</p>
		</div>

		<html:form action="zzlgdx_rcsw.do?method=qjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zzlgdx_rcsw.do?method=qjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="guid" name="guid"
				value="<bean:write name="rs" property="guid" />">
			<input type="hidden" id="type" name="type"
				value="<bean:write name="type" />">

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
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
	         			alert("已通过审核，不能提交！");
	         		</script>
				</logic:match>
			</logic:present>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>请假申请</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" name="提交" onclick="yz();">
										提 交
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
								<bean:message key="lable.xsgzyxpzxy" />
								名称
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
						<tr>
							<th>
								申请时间
							</th>
							<td>
								<input type="text" id="sqsj" readonly="readonly" name="sqsj"
									value="<bean:write name="rs" property="sqsj"/>" />
							</td>
							<th>
								请假类型
							</th>
							<td>
								<html:select name="rs" property="qjlx">
									<html:option value=""></html:option>
									<html:option value="病假">病假</html:option>
									<html:option value="事假">事假</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								请假开始日期
							</th>
							<td>
								<input type="text" readonly 
									onclick="return showCalendar('qjksrq','y-mm-dd');"
									value="<bean:write name='rs' property="qjksrq" />"
									name="qjksrq" id="qjksrq" />
							</td>
							<th>
								请假截止日期
							</th>
							<td>
								<input type="text" readonly 
									onclick="return showCalendar('qjjzrq','y-mm-dd');"
									value="<bean:write name='rs' property="qjjzrq" />"
									name="qjjzrq" id="qjjzrq" />
							</td>
						</tr>
						<tr>
							<th>
								请假课程
							</th>
							<td>
								<input type="text" id="qjkc1" name="qjkc1" maxlength="50"
									value="<bean:write name="rs" property="qjkc1"/>" />
							</td>
							<th>
								课程数
							</th>
							<td>
								<input type="text" id="qjkcs1" name="qjkcs1" maxlength="3"
									value="<bean:write name="rs" property="qjkcs1"/>"
									onkeyup="value=value.replace(/[^\d]/g,'')"
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
							</td>
						</tr>
						<tr>
							<th>
								请假课程
							</th>
							<td>
								<input type="text" id="qjkc2" name="qjkc2" maxlength="50"
									value="<bean:write name="rs" property="qjkc2"/>" />
							</td>
							<th>
								课程数
							</th>
							<td>
								<input type="text" id="qjkcs2" name="qjkcs2" maxlength="3"
									value="<bean:write name="rs" property="qjkcs2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'')"
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
							</td>
						</tr>
						<tr>
							<th>
								请假课程
							</th>
							<td>
								<input type="text" id="qjkc3" name="qjkc3" maxlength="50"
									value="<bean:write name="rs" property="qjkc3"/>" />
							</td>
							<th>
								课程数
							</th>
							<td>
								<input type="text" id="qjkcs3" name="qjkcs3" maxlength="3"
									value="<bean:write name="rs" property="qjkcs3"/>"
									onkeyup="value=value.replace(/[^\d]/g,'')"
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
							</td>
						</tr>

						<tr>
							<th>
								请假原因
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="qjyy" rows='10'
									style="width:100%;word-break:break-all;" onblur="yzdx(this,'qjyy')" />
								<input type="hidden" id="qjyy" name="qjyy" value="" />
							</td>
						</tr>
				</table>
		</html:form>
	</body>
	</html>