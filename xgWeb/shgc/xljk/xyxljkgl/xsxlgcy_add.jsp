<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	
	<body>
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - <bean:message key="lable.xsgzyxpzxy" />心理健康管理 - 
					<logic:equal value="10827" name="xxdm">
						心理委员
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						学生心理观察员
					</logic:notEqual>
					- 新增  
					</a>
				</p>
		</div>
		
		
		<html:form action="/xljk_xsxlgcy" method="post">
			<input type="hidden" id="add_flag" name="add_flag" value="no" />
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button  onclick="gcy_save('gcy_add');"
									id="saveButton">
										保 存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr>
					<th colspan="2">
						<font color="red">*</font>学号
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" readonly="true" />
						<button
							onclick="showTopWin('/xgxt/xljk_xsxlgcy.do?act=xsxlgcy&doType=stu_info',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>
					<th>
						姓名
					</th>
					<td align="left">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr >
					<th colspan="2">
						性别
					</th>
					<td align="left">
						<html:text property="xb" styleId="xb" readonly="true" />
					</td>
					<th>
						出生年月
					</th>
					<td align="left">
						<html:text styleId="csrq" property="csny"
							onclick="return showCalendar('csrq','y-mm-dd');" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
					<th>
						班级
					</th>
					<td align="left">
						<html:text property="bjmc" styleId="bjmc" readonly="true" />
					</td>
				</tr>
				<tr>
					<logic:equal value="10856" name="xxdm">
						<th colspan="2">
							<font color="red">*</font>年级
						</th>
						<td align="left">
										<html:select property="nj" style="width:70px" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
						</td>
					</logic:equal>
					<logic:notEqual value="10856" name="xxdm">
						<th colspan="2">
							<font color="red">*</font>手机号码
						</th>
						<td align="left">
							<html:text property="sjhm" styleId="zy" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
					</logic:notEqual>
					<th>
						寝室电话
					</th>
					<td align="left">
						<div class="pos" style="z-index:2">
							<html:text property="qsdh" onblur="checkPhoneV4(this)"/>
							<div id="phoneErrow" class="hide">
								<p>
									电话格式不正确
								</p>
							</div>
						</div>
					</td>
				</tr>
				<logic:equal value="10856" name="xxdm">
					<tr>
						<th colspan="2">
						心理考试成绩
					</th>
					<td align="left">
						<html:text property="xlkscj" styleId="xlkscj"/>
					</td>
						<logic:equal value="10856" name="xxdm">
						<th>
							<font color="red">*</font>手机号码
						</th>
						<td align="left">
							<html:text property="sjhm" styleId="zy" />
						</td>
					</logic:equal>
					</tr>
				</logic:equal>
				<logic:notEqual value="10856" name="xxdm">
					<tr>
						<th colspan="2">
							
							<logic:equal value="10827" name="xxdm">
							心理委员编号
						</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
							观察员编号
						</logic:notEqual>
						</th>
						<td align="left">
							<html:text property="gcybh" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						<th></th>
						<td></td>
					
				</logic:notEqual>
				<tr>
					<th colspan="2">
							备注
							<br/>
							<br/>
							<font color="red"><限400字符></font>
						</div>
					</th>
					<td colspan="4" align="left">
						<html:textarea rows="5" style="width:98%" property="bz"
							styleId="a"  onblur="checkLen(this,400)"/>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
	</body>
	<logic:notEmpty name="message">
		<logic:equal name="message" value="gcybh_exits">
			<script>
	alert("编号已经存在!增加失败")
</script>
		</logic:equal>
		<logic:equal name="message" value="xh_exits">
			<script>
	alert("该学生已经存在!增加失败")
</script>
		</logic:equal>
		<logic:equal name="message" value="insert_success">
			<script>
	alert("增加成功!");
	window.dialogArguments.document.getElementById("search_go1").click();
	Close();
</script>
		</logic:equal>
		<logic:equal name="message" value="insert_fail">
			<script>
	alert("增加失败!")
</script>
		</logic:equal>
	</logic:notEmpty>
</html>
