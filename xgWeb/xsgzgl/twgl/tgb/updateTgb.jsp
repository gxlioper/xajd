<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/twgl/js/tgb.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/tgbgl" method="post" styleId="tgbForm" onsubmit="return false;">
		<html:hidden property="xh" styleId="xh"/>
		<html:hidden property="jgid" styleId="jgid" />
		<input type="hidden" id="path" value="${path}" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>团干部信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${tgbForm.xn}
							</td>
							<th><span><font color="red">*</font></span>任职组织</th>
							<td>
								<input type="text" id="rzzzmc" style="width:150px" value="${rzzzmc}"/>
								<html:hidden property="rzzz" styleId="rzzz"/>
								<button class="btn_01" type="button"  onclick="selectTzz('update');">选择</button>
<%--								<html:text property="rzzz" styleId="rzzz"></html:text>--%>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>职务
							</th>
							<td width="30%">
								<html:select property="zwdm" styleId="zwdm" style="width:120px;">
									<html:options collection="dmList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th><span><font color="red">*</font></span>任职时间</th>
							<td>
								<html:text property="rzsj" styleId="rzsj" onfocus="showCalendar('rzsj','y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								备注
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
									<button type="button" onclick="saveTgb('update');">
										保存
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

