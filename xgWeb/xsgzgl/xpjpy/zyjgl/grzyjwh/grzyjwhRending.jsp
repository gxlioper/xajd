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
			var id = jQuery("#id").val();
			var url = "pjpy_zyjwhwh.do?method=updateGrzyj&type=update&id="+id;
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
			<input type="hidden" id="type" value="${type}" />
			<html:hidden property="id"  styleId="id"/> 
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/rcsw/hcyhkgl/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>专业信息维护 </span>
							</th>
						</tr>
					</thead>					
						<tr>
							<th>比赛名称</th>
							<td colspan="5">
								${rs.bsmc}
							</td>
						</tr>
						<tr>
							<th>主办单位</th>
							<td>
								${rs.zbdw}
							</td>
							<th>获奖时间</th>
							<td>
								${rs.hjsj}							
							</td>
						</tr>						
					<tr>
						<th>
							证书名称
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="ylzd3" styleId="fjid"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>						
						</td>
					</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>等级认定</span>
							</th>
						</tr>
					</thead>					
						<tr>
							<th><span class="red">*</span>认定等级</th>
							<td colspan="3">
								<html:select  property="rddjdm" styleId="rddjdm" style="width:130px">
									<html:option value="">未认定</html:option>
									<html:options collection="rddjList" labelProperty="rddjmc" property="rddjdm"/>
								</html:select>
							</td>
						</tr>
					<tr>
						<th>备注<br /><font color="red">&lt;限500字&gt;</font></th>
						<td colspan="5">
							<html:textarea property="bz" style="width:98%" styleId="bz" rows='4' onblur="checkLen(this,500)"></html:textarea>
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

