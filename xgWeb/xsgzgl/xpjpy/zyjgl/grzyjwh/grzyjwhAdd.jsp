<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/xmsb/js/xmsb.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/comm/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
		function saveForm(){
			var xh = jQuery("#xh").val();
			var bsmc = jQuery("#bsmc").val();
			var zbdw = jQuery("#zbdw").val();
			var hjsj = jQuery("#hjsj").val();
			if (jQuery.trim(xh) == ""){
				showAlert("请输入学号！");
				return false;
			}
			if (jQuery.trim(bsmc) == ""){
				showAlert("请输入比赛名称！");
				return false;
			}	
			if (jQuery.trim(zbdw) == ""){
				showAlert("请输入主办单位！");
				return false;
			}
			if (jQuery.trim(hjsj) == ""){
				showAlert("请输入获奖时间！");
				return false;
			}
			var url = "pjpy_zyjwhwh.do?method=addGrzyj&type=save";
	      	ajaxSubFormWithFun("GrzyjwhForm",url,function(data){
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
	</head>
	<body style="width: 100%">
		<html:form action="/pjpy_zyjwhwh" method="post" styleId="GrzyjwhForm" onsubmit="return false;">
			<input type="hidden" id="type" value="${type }" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<span>专业信息维护 </span>
							</th>
						</tr>
					</thead>					
						<tr>
							<th><span class="red">*</span>比赛名称</th>
							<td colspan="6">
								<html:text property="bsmc" styleId="bsmc" maxlength="100" style="width:600px"/>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>主办单位</th>
							<td>
								<html:text property="zbdw" styleId="zbdw" style="width:200px" maxlength="40"/>
							</td>
							<th><span class="red">*</span>获奖时间</th>
							<td>
								<html:text property="hjsj" styleId="hjsj" style="width:200px;" onfocus="showCalendar('hjsj','y-mm-dd');" />
							
							</td>
						</tr>						
					<tr>
						<th>
							证书上传
						</th>
						<td colspan="3">
							<html:hidden property="ylzd3" styleId="fjid"/>
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 3,
										//后缀
										accept : 'png|gif|jpg|jpeg|doc|docx',
										//最大文件大小 单位M
										maxsize: 10,
										//存放附件的隐藏域的id
										elementid : 'fjid'
										});
								});
							</script>						
						</td>
					</tr>
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
									<button type="button" id="save_button" type="button"
										onclick="saveForm();">
										保存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
										关 闭
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

