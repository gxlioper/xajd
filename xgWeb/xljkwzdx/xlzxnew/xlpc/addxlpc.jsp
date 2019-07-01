<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/xlpc/js/xlpc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xlzxnew_xsxlpc" method="post" styleId="XsxlpcForm">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
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
								<span>家庭情况</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xljkwzdx/xlzxnew/xlpc/jtqkdcload.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>普测结果</th>
							<td colspan="3">
								<html:select property="pcjg" styleId="pcjg" style="width:20%">
									<html:option value=""></html:option>
									<html:option value="没有问题">没有问题</html:option>
									<html:option value="一般心理问题">一般心理问题</html:option>
									<html:option value="严重心理问题">严重心理问题</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
									附件
							</th>
							<td colspan="3">
								<html:hidden property="fjpath" styleId="fjpath"/>
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
											elementid : 'fjpath'
											});
									});
								</script>						
							</td>
						</tr>
							<tr>
							<th>备注
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
								   onblur="checkLen(this,500);" 
								   style="width:99%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:40px;"></div>
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
									<button type="button" onclick="saveForm();">
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