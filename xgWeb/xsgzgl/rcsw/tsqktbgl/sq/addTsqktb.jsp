<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/tsqktbgl/sq/js/tsqktb.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/tsqktbgl_tb" method="post" styleId="tsqktbForm" onsubmit="return false;">
		<html:hidden property="xn" styleId="xn"/>
		<html:hidden property="xq" styleId="xq"/>
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
		<html:hidden property="txr" styleId="txr" value="${txr}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/tsqktbgl/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>通报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${xnsr}
							</td>
							</td>
							<th>学期</th>
							<td>
								${xqsr}
							</td>
						</tr>
						<tr>
					    	<th><span class="red">*</span>学情分类一</th>
					    	<td>
								<html:select property="xqdm1" styleId="xqdm1" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xqflList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>							
							<th>学情分类二</th>
							<td>
								<html:select  property="xqdm2" styleId="xqdm2" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xqflList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>通报时间</th>
					    	<td>
								<html:text property="tbsj" styleId="tbsj" onfocus="showCalendar('tbsj','y-mm-dd');"></html:text>
						   </td>
					<logic:equal name="xxdm" value="10026">
							<th><span class="red">*</span>问题紧急程度</th>
					    	<td>
								<html:select  property="wtjjcd" styleId="wtjjcd" >
									<html:option value=""></html:option>
									<html:option value="一般紧急">一般紧急</html:option>
									<html:option value="比较紧急">比较紧急</html:option>
									<html:option value="非常紧急">非常紧急</html:option>
								</html:select>
						   </td>
					</logic:equal>	
						</tr>
						
								<tr>
									<th><span><font color="red">*</font></span>
										<logic:notEqual name="xxdm" value="10026">
											特殊学情
										</logic:notEqual>
										<logic:equal name="xxdm" value="10026">
											该生状况简述
										</logic:equal>
										<br /><font color="red">&lt;限500字&gt;</font>
									</th>
									<td colspan="3">
										<html:textarea property='tsxq' style="width:98%" styleId="tsxq" rows='8' onblur="checkLen(this,500);"/>
									</td>
								</tr>
						<tr>
							<th><span><font color="red">*</font></span>
							<logic:notEqual name="xxdm" value="10026">
								特殊学情干预情况
							</logic:notEqual>
							<logic:equal name="xxdm" value="10026">
								已采取措施及后续跟进措施
							</logic:equal>
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='tsxqgyqk' style="width:98%" styleId="tsxqgyqk" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:30px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveTsqktb('save');">
										保存草稿
									</button>
									<button type="button" onclick="saveTsqktb('submit');">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

