<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xmsb/js/xmsb.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/jskpXmsb" method="post" styleId="jskpXmsbForm">
			<html:hidden property="xmid" styleId="xmid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">项目名称</th>
							<td style="width:35%">
								${xmxx.xmmc}
							</td>
							<th style="width:15%">指导单位</th>
							<td style="width:35%">
								${xmxx.zddwmc}
							</td>
						</tr>	
						<tr>
							<th>项目大类</th>
							<td>
								${xmxx.xmdlmc}
							</td>
							<th>项目类别</th>
							<td>
								${xmxx.xmlbmc}
							</td>
						</tr>
						<tr>
							<th>负责人</th>
							<td>
								${xmxx.fzrxm}
							</td>
							<th>负责人联系方式</th>
							<td>
								${xmxx.fzrlxfs}
							</td>
						</tr>
						<tr>
							<th>立项时间</th>
							<td colspan="3">
								${xmxx.lxsj}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>申报项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">申报人</th>
							<td width="30%">
								${userNameReal }
							</td>
							<th>学号</th>
							<td>
								${userName }
							</td>
						</tr>
						<tr>
						<th><span class="red">*</span>获奖时间</th>
							<td colspan="3">
								<html:text  property="hjsj" styleId="hjsj" onfocus="showCalendar('hjsj','y-mm-dd');" />
								<span style="color:red;margin-left:10px;">提示：请填写证书上的获奖时间</span>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>申报理由</th>
							<td colspan="3">
								<html:textarea property="sbly" styleId="sbly" 
											   onkeypress="checkLen(this,500);" onblur="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>附件信息
							</th>
							<td colspan="3">
							<html:hidden property="fjid" styleId="fjid" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'fjid'
												});
										});
									</script>
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
									<button type="button" onclick="saveXmsb();">
										申报
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