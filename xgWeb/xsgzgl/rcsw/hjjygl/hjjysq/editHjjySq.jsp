<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/hjjygl/hjjysq/js/hjjy.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
	
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hjjyJysq" method="post" styleId="HjjySqForm" onsubmit="return false;">
			<html:hidden property="sqid" styleId="sqid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>借用信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${model.xn}
							</td>
							</td>
							<th>学期</th>
							<td>
								${model.xqmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>借用原因
							</th>
							<td width="30%" colspan="3">
								<html:select property="jyyydm" styleId="jyyydm" style="width:130px">
									<html:options collection="jyyyList" property="jyyydm"
										labelProperty="jyyymc" />
								</html:select>
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>借用开始时间
							</th>
							<td width="30%">
								<html:text property="jykssj" styleId="jykssj"
									onfocus="showCalendar('jykssj','y-mm-dd',true,'jyjzsj');" />
							</td>
							<th width="20%">
								<font color="red">*</font>借用截止时间
							</th>
							<td width="30%">
								<html:text property="jyjzsj" styleId="jyjzsj"
									onfocus="showCalendar('jyjzsj','y-mm-dd',false,'jykssj');" />
							</td>
						</tr>
						<tr><th width="20%">备注
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
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
								<button type="button" onclick="editHjjySq('save');">
										保存草稿
									</button>
									<button type="button" onclick="editHjjySq('submit');">
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

