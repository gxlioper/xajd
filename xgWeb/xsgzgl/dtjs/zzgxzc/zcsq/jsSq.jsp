<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/zzgxzc/zcsq/js/zcsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#no").attr("checked","checked");
				jQuery("#xh").attr("readonly",true).attr("class","text_nor");
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/dzzgxsq" method="post" styleId="ZcsqForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>登记信息</span>&nbsp;
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>所在党支部</th>
							<td>
								<html:select property="szdzb" styleId="szdzb" style="width:90%">
									<html:options collection="dzbList" property="dzbdm" labelProperty="dzbmc"/>
								</html:select>							
							</td>
							<th><font color="red">*</font>是否省内</th>
							<td>
								 <html:select property="sfsn" styleId="sfsn" style="width:90%">
									<html:option value="省内">省内</html:option>
									<html:option value="省外">省外</html:option>
								 </html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>接收本人组织关系的党组织</th>
							<td colspan="3">
								<html:text property="jsdzz" styleId="jsdzz" style="width:90%" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>本人组织关系所去单位</th>
							<td colspan="3">
								<html:text property="sqdw" styleId="sqdw"  maxlength="50" style="width:90%" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>党费交至日期</th>
							<td >
								<html:text property="dfjzrq" styleId="dfjzrq" onclick="return showCalendar('dfjzrq','y-mm-dd',true);"  style="width:90%"/>
							</td>
							<th><font color="red">*</font>是否需要开具婚姻证明</th>
							<td >
								<html:radio property="sfkjhyzm" value="是" styleId="yes"/><label for="yes">是</label>
								<html:radio property="sfkjhyzm" value="否" styleId="no"/><label for="no">否</label>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveSq('save');">
										保存草稿
									</button>
									<button type="button" onclick="saveSq('savesubmit');">
										提交申请
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