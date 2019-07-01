<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				//加载下拉选项
				loadMkxxSelectOptions();
				//加载radio选项
				loadMkxxRadioOptions();
			});
			//验证学生申请金额是否超过金额上限
			function checkJesxSh(obj){
				var ylzd1= jQuery(obj).val();
				var jesx = jQuery("#jesx").val();
				if(parseFloat(ylzd1)>parseFloat(jesx)){
					showAlertDivLayer("申请金额超过项目金额上限！");
					jQuery(obj).val('');
				}
			}
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_sqsh" method="post" styleId="xmsqForm" onsubmit="return false;">
			<html:hidden property="guid"/>
			<html:hidden property="xmdm"/>
			<html:hidden property="xn"/>
			<html:hidden property="xq"/>
			<input type="hidden" name="jesx" id="jesx" value="${mkxxForm.je}"/>
		
			<div style='tab;width:100%;height:345px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="4">
								<span>
									资助项目申请信息
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th>学年</th>
							<td>${mkxxForm.xn }</td>
							<th>学期</th>
							<td>${mkxxForm.xqmc }</td>
						</tr>
						<tr>
							<th>项目名称</th>
							<td>${mkxxForm.xmmc }</td>
							<th>金额</th>
							<td>${mkxxForm.je }</td>
						</tr>
						<logic:equal value="1" name="xmwhForm" property="jesfxssq">
						<tr>
							<th>申请金额</th>
							<td colspan="3">
								<html:text onkeyup="checkInputData(this);checkJesxSh(this)" maxlength="7" property="ylzd1" styleId="ylzd1" style="width:100px"></html:text>
								<font id="message"  color="blue">上限金额：${mkxxForm.je }</font>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<logic:equal value="10335" name="xxdm">
									<span style="color: blue; line-height:20px;display:block;">
										根据学院(园)通知要求，如明确必须上传附件则按要求上传；如无附件要求则无需上传。
									</span>
									&nbsp;
								</logic:equal>
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<logic:notEqual value="11799" name="xxdm">
								<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
													//最大文件大小 单位M
													maxsize: 10,
													//存放附件的隐藏域的id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf|mp4',
													//最大文件大小 单位M
													maxsize: 300,
													//存放附件的隐藏域的id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:equal>
							</td>
						</tr>
					</tbody>
					
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp" %>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveSqxg();">
										保 存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

