<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xshjgl/js/hjgl.js"></script>
	</head>
	<body>
	<html:form action="/rcsw_xshjgl.do" styleId="xshjglForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tr>
							<th style="width:15%"><font color="red">*</font>学号</th>
							<td style="width:35%"><html:text property="xh" readonly="true" styleId="xh" style="width:60%" />
							<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个学生',680,480,'xsxx_xsgl.do?method=showStudents&goto=${path}');return false;">选择</button>
							</td>
							<th style="width:15%">姓名</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
					</tr>
						
						<tr>
							<th>性别</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xb"/>
								</logic:present>
							</td>
							<th>联系电话</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="lxdh"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>年级</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="nj"/>
								</logic:present>
							</td>
							<th><bean:message key="lable.xb" /></th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xymc"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>专业</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="zymc"/>
								</logic:present>
							</td>
							<th>班级</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>身份证号</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="sfzh"/>
								</logic:present>
							</td>
							<th>籍贯</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="jgmc"/>
								</logic:present>
							</td>
						</tr>
						<thead>
							<tr>
								<th colspan="4">
									<span>户籍信息</span>
								</th>
							</tr>
						</thead>
					<tbody>
						 <tr>
							<th><span class="red">*</span>迁移状态 </th>
							<td>
								<html:select  property="qyzt" styleId="qyzt" style="width:130px">
								<option value='0'>迁入</option>
								<option value='1'>迁出</option>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>迁入/迁出时间 
							</th>
							<td width="34%">
								<html:text property="qysj" styleId="qysj" readonly="true" onclick="return showCalendar('qysj','y-mm-dd');" style="width:70%"></html:text>
							</td>
						</tr>
						
						<tr>
							<th width="16%" rowspan="4">
								备注
								<br><font color="red">限制字数（500）</font><br/>
							</th>
							<td width="34%" colspan="3" rowspan="4">
								<html:textarea property="bz" onblur="chLengs(this,500);" styleId="bz" rows="4" style="width:90%" ></html:textarea>
							</td>
						</tr>
				</tbody>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="save();">
										保 存
									</button>
									<button type="button" onclick="refreshParent2();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</table>	
		</html:form>
	</body>
</html>