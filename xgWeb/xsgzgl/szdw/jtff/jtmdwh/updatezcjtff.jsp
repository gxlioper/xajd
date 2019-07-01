<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/jtff/jtmdwh/js/jtmdwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jtff_jtmdwh" method="post" onsubmit="return false" styleId="JtmdwhForm">
			<input type="hidden" name = "jtlb" value = "zc"/>
			<html:hidden property="id" styleId="id"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/szdw/jtff/comm/viewTeacher.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>身份定位信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>岗位</th>
							<td colspan="3">
								
								<html:select styleId="gw" property="gw">
								  <html:option value="部门兼职辅导员">部门兼职辅导员</html:option>
								  <html:option value="学院专职辅导员">学院专职辅导员</html:option>
								  <html:option value="借调辅导员">借调辅导员</html:option>
								  <html:option value="学院副书记">学院副书记</html:option>
								  <html:option value="班主任">班主任</html:option>
								  <html:option value="国际学院、台港澳班主任">国际学院、台港澳班主任</html:option>
								  <html:option value="一临二临辅导员">一临二临辅导员</html:option>
								</html:select>
								
							</td>
						</tr>
						<tr>
							<th>备注
								</br><font color="red">(限50字)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
								   onkeyup="checkzs();" 
								   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
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
									<button type="button" onclick="savezcjt('update');">
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