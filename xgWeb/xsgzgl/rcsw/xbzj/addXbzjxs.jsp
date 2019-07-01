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
		<script type="text/javascript" src="xsgzgl/rcsw/xbzj/js/xbzj.js"></script>
	</head>
	<body>
		<html:form action="/rcsw_xbzj.do" styleId="xbzjForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
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
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>支教信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学年
							</th>
							<td width="34%">
								<html:select property="xn" name="map" styleId="xn" onchange="" style="width:70%">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>学期
							</th>
							
							<td width="34%">
								<html:select property="xq" name="map" styleId="xq" onchange="" style="width:70%">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>起始时间
							</th>
							<td width="34%">
								<html:text property="zjsj" styleId="zjsj" readonly="true" onclick="return showCalendar('zjsj','y-mm-dd');" style="width:70%"></html:text>
							</td>
							<th width="16%"></th>
							<td width="34%"></td>
						</tr>
						<tr>
							<th width="16%" rowspan="4">
								备注
								<br/>
								<font color="red">限制字数（200）</font>
							</th>
							<td width="34%" colspan="3" rowspan="4">
								<html:textarea property="bz" onblur="chLengs(this,200);" styleId="bz" rows="4" style="width:90%" ></html:textarea>
							</td>
						</tr>
					</tbody>
					
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="save();">
										保存
									</button>
									<button type="button" onclick="refreshParent2();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>	
		</html:form>
	</body>
</html>