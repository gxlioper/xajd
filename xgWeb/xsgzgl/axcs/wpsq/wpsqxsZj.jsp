<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type="text/javascript" src="xsgzgl/axcs/wpsq/js/wpsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/axcswpsqxs" method="post" styleId="WpsqForm" onsubmit="return false;">
			<html:hidden property="xh" styleId="xh" value="${jbxx.xh}"/>
			<html:hidden property="xmdm" styleId="xmdm" value="${wpModel.xmdm}" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/axcs/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									申请爱心超市物品信息
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<th width="20%">
								<span class="red">*</span>
								<bean:message key="lable.axcswpmc" />
							</th>
							<td>
								${wpModel.xmmc}
							</td>
							<th width="20%" rowspan="4">
								物品图片
							</th>
							<td rowspan="4">
							<div id="wpImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
					         <img style="width:180px;height:128px" id="axwptp"
						src="axcsWpsz.do?method=showPhoto&xmdm=${wpModel.xmdm}&type=view"
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.axcswplb" />
							</th>
							<td>
								${wpModel.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.axcswpxxjs" />
							</th>
							<td>
								${wpModel.xmxxjs}
							</td>
						</tr>
						<tr>
							<th width="20%">
								申请条件
							</th>
							<td>
								${wpModel.sqtj}
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>申请理由
								</br><font color="red">(限500字)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<div id="txsmDiv" style="color:red"></div>
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveWpsq('save');return false;">
										保存草稿
									</button>
									<button type="button" onclick="saveWpsq('submit');return false;">
										提交申请
									</button>
									<button type="button"  onclick="iFClose();" id="buttonClose">
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

