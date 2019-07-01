<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/xskqgl/js/xskqgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		
	</head>
		
	<body>
		
		<html:form action="/rcsw_kqgl_xskqgl" method="post" styleId="KqdjForm" onsubmit="return false;">
			<html:hidden property="kqdjid" styleId="kqdjid"/>
			<html:hidden property="xh" styleId="xh"/>
			
			<div style='tab;width:100%;height:410px;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>学生基本信息</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>考勤信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>学年学期</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								<html:select property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>	
							</td>
							<th><span class="red">*</span>周次</th>
							<td>
								第
								<html:text property="zc" styleId="zc" style="width:20px;" maxlength="2" styleClass="text_nor" onblur="if(value != '') {value=parseInt(value,10)}" onkeyup="checkInputData(this);return false;"></html:text>
								周
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>考勤课程</th>
							<td>
								<html:text property="kqkc" styleId="kqkc"  maxlength="25"></html:text>
							</td>
							<th>上课地点</th>
							<td>
								<html:text property="dd" styleId="dd"  maxlength="50"></html:text>
							</td>
						</tr>
					    <tr>
							<th><span class="red">*</span>考勤时间</th>
							<td>
								<html:text styleId="kqsj" property="kqsj" onclick="return showCalendar('kqsj','yyyy-MM-dd HH:mm',true,'kqsj');"  readonly="true"></html:text>
							</td>
							<th><span class="red">*</span>考勤类型</th>
							<td>
								<html:select property="kqlxdm" styleId="kqlxdm">
									<html:options collection="kqlxList" property="kqlxdm" labelProperty="kqlxmc" />
								</html:select>	
							</td>
						</tr>
					    <tr>
							<th>
								考勤情况
								<br /><font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property='kqqk' style="width:98%" styleId="kqqk" rows='4' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('updateKqdj','update');">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

