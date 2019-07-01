<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/ysjxj/ysjxj/js/ysjxj.js"></script>
	</head>
	<body>	
		<html:form action="/ysjxj_ysjxjwh" method="post" styleId="ysjxjForm">
			<html:hidden property="xn" value="${ysjxjForm.xn}"/>
			<html:hidden property="xq" value="${ysjxjForm.xq}"/>
			<html:hidden property="xh" value="${jbxx.xh}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<html:hidden property="juid" styleId="juid"/>
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
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${ysjxjForm.xn}
							</td>
							<th>学期</th>
							<td>
								${xqmc}
							</td>
					    </tr>
					    <tr>
					    	<th><font color="red">*</font>项目名称
							</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" onblur="checkLen(this,16);"/>
							</td>
							
							<th><font color="red">*</font>金额</th>
							<td>
								<html:text property="je" styleId="je" style="width:120px;" maxlength="5"  styleClass="text_nor" onkeyup="javascript:this.value.substring(0,1)=='0'?this.value='0':this.value=this.value.replace(/\D/g, '')"/>
							</td>
					    </tr>
					    <tr>
					    	<th width="20%"><span class="red">*</span>项目类型</th>
<!--							<td>-->
<!--								<input type="radio" name="xmlx" value="奖学金" />奖学金-->
<!--								<input type="radio" name="xmlx" value="助学金" />助学金-->
<!--							</td>-->
							<td>
								<logic:equal value="奖学金" name="ysjxjForm" property="xmlx">
									<input type="radio" name="xmlx" value="奖学金" checked="checked" />奖学金
									<input type="radio" name="xmlx" value="助学金" />助学金
								</logic:equal>
								<logic:notEqual value="奖学金" name="ysjxjForm" property="xmlx">
									<input type="radio" name="xmlx" value="奖学金" />奖学金
									<input type="radio" name="xmlx" value="助学金" checked="checked" />助学金
								</logic:notEqual>
							</td>
							<th><font color="red">*</font>资金来源</th>
							<td>
								<html:select property="zjly" styleId="zjly" style="width:170px">
									<html:options collection="zjlyList" property="zjlydm" labelProperty="zjlymc" />
								</html:select>
							</td>
				    	</tr>
				    	<tr>
				    		<th><span class="red">*</span>发放时间</th>
							<td colspan="3">
								<html:text property="ffsj" styleId="ffsj" onfocus="showCalendar('ffsj','y-mm-dd');"></html:text>
							</td>
				    	</tr>
					    <tr>
							<th>
								备注
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height:10px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveYsjxj('update');">
										保存
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