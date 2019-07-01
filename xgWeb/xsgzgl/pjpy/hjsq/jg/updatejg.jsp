<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			//保存表单
			function saveForm(type){
				if(!checkNotNull("hjmc-hjsj-fjdw")){
					return showAlert("请将带<font class='red'>*</font>的必填项填写完整！");
				}
				var url = "jxjg.do?method=save&type=" + type;
				ajaxSubFormWithFun("HjjgForm", url, function(data) {
					 if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jxjg" method="post" styleId="HjjgForm">
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
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>综测信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
							<th>学年</th>
							<td>${xn}</td>
							<th>学期</th>
							<td>${xqmc}</td>
						</tr>
						<tr>
							<th><font class="red">*</font>奖项名称</th>
							<td>
								<html:text property="hjmc" styleId="hjmc" maxlength="50"/>
							</td>
							<th><font class="red">*</font>获奖日期</th>
							<td>
								<html:text property="hjsj" styleId="hjsj" onclick="return showCalendar('hjsj','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<th><font class="red">*</font>颁奖单位</th>
							<td colspan="3">
								<html:text property="fjdw" styleId="fjdw" style="width:70%" maxlength="50"/>
							</td>
						</tr>
					
						<tr>
							<th>
								附件
							</th>
							<td colspan="3">
								<html:hidden property="fj" styleId="fj"/>
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
											elementid : 'fj'
											});
									});
								</script>						
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
									<button type="button" onclick="saveForm('save');">
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
		</html:form>
	</body>
	
</html>