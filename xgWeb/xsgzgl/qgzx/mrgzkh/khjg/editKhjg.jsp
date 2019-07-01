<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/mrgzkh/khjg/khjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/mrgzkhKhjg" method="post" styleId="GzkhKhjgForm">
			<html:hidden property="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="yrdw" styleId="yrdw"/>
			<html:hidden property="gwdm" styleId="gwdm"/>
			<html:hidden property="gzrq" styleId="gzrq"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:450px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>填写信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>用人单位</th>
							<td>
							
								<html:select property="yrdw" styleId="yrdw" onclick="checkXh();" onchange="getGwxx();" style="width:200px"  value="${gzkhKhjgXx.yrdw}" disabled = "true">
									<html:options collection="yrdwList" property="bmdm" labelProperty="bmmc"/>
								</html:select>
							</td>
							<th><font color="red">*</font>工时</th>
							<td>
								<html:text property="gs" styleId="gs" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"></html:text>（小时）
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>岗位</th>
							<td>
								<html:select  property="gwdm" styleId="gwdm" style="width:200px"  value="${gzkhKhjgXx.gwdm}" disabled = "true">
									<html:options collection="gwList" property="gwdm" labelProperty="gwmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>工作地点</th>
							<td>
								<html:text property="gzdd" styleId="gzdd" maxlength="50" ></html:text>
								
							</td>
						</tr>
						<tr>
						<th><font color="red">*</font>工作日期及时间段</th>
							<td colspan="3">
								<html:text property="gzrq" styleId="gzrq" onfocus="showCalendar('gzrq','y-mm-dd');" disabled = "true" />
								<html:select  property="gzkssj" styleId="gzkssj" >
									<html:options collection="gzsjList" property="dm" labelProperty="mc" />
								</html:select>
								到
								<html:select  property="gzjssj" styleId="gzjssj" >
									<html:options collection="gzsjList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
						
							<th><span class="red">*</span>工作内容
								</br><font color="red">(限1000字)</font></th>
							<td colspan="3">
								<html:textarea property="gznr" styleId="gznr" 
											   onkeypress="checkLen(this,100);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveKhjg('update');">
										保    存
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