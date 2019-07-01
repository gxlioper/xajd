<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/zzdgl/sq/js/zzdsq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xgygl_zdsq" method="post" styleId="zzdsqForm" onsubmit="return false;">
		<html:hidden property="xn" styleId="xn"/>
		<html:hidden property="xq" styleId="xq"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/gygl/zzdgl/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>走读申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年学期
							</th>
							<td width="30%">
								${xnsr}&nbsp;${xqsr}
							</td>
							</td>
							<th>申请时间</th>
							<td>
								${sqsj}
								<input type="hidden" id="sqsj" name="sqsj" value="${sqsj}" />
							</td>
						</tr>
						<tr>
							<th width="20%">
								走读起始时间
							</th>
							<td width="30%">
								<html:text property="zdqssj" styleId="zdqssj" onfocus="showCalendar('zdqssj','y-mm-dd');"></html:text>
							</td>
							</td>
							<th>走读终止时间</th>
							<td>
								<html:text property="zdzzsj" styleId="zdzzsj" onfocus="showCalendar('zdzzsj','y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th><span><font color="red">*</font></span>
								走读原因
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='sdyy' style="width:98%" styleId="sdyy" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
			      		<tr>
						<th>
							附件
						</th>
						<td colspan="3">
							<html:hidden property="filepath" styleId="fjid"/>
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
								<button type="button" onclick="saveZzdsq('save');">
										保存草稿
									</button>
									<button type="button" onclick="saveZzdsq('submit');">
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

