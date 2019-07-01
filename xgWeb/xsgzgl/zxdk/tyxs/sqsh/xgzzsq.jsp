<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
			<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		
		<script type="text/javascript" defer="defer">

		

		

		function lock(){
			jQuery(".btn").find("button").each(function(){
				jQuery(this).attr("disabled","disabled");
				jQuery(this).css({color:"#cccccc"});
			});
		}
			function saveZzsq(url){	
				var xh= jQuery("#xh").val();
				var xn = jQuery("#xn").val();
				var xxdm = jQuery("#xxdm").val();	
				// 检查学号是否存在
				if (xh=="" ){
					showAlertDivLayer("[学号]不许为空!");
					return false;
				}
				if (xn=="" ){
					showAlertDivLayer("[学年]不许为空!");
					return false;
				}	
				if(xxdm == '10511'){
					if(tyCheckNullhsd()==false){
						return false;
					}
					if(checkNdJe() == false){
						return false;
					}

					if(checksqlylength() == false){
						return false;
					}
					
						ajaxSubFormWithFun("tyxsZzsqForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
							}});
						});
				    
				}else{
					if(tyCheckNull()!=false &&　checksqlylength() != false){
                        var fj = jQuery("#filepath_f").val();
                        if(fj == "" || typeof fj == 'undefined'){
                            showAlertDivLayer("请上传退役证明、录取通知书复印件等信息！");
                            return false;
                        }
						ajaxSubFormWithFun("tyxsZzsqForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
							}});
						});
						}
				}
				
						}
			jQuery(function(){
				//华中师范大学
				if('${xxdm}' == '10511'){
					dklxchange();
					fxjdnxchange();
					
				}
			})
			
						
		</script>
		<style type="text/css">
			.cn tr th{text-align:center}
			.cn tr td{text-align:center}
		</style>
	</head>
	<body>
		<html:form action="/tyxs_zzsq" method="post" styleId="tyxsZzsqForm">
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="xn" styleId="xn"/>		
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />	
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>资助申请</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>服役情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th><span class="red">*</span>入伍时间</th>
						<td>
							<html:text property="rwsj" styleId="rwsj"
									   onfocus="showCalendar('rwsj','y-mm-dd');"></html:text>
						</td>
						<th><span class="red">*</span>退役时间</th>
						<td>
							<html:text property="tysj" styleId="tysj"
									   onfocus="showCalendar('tysj','y-mm-dd');"></html:text>
						</td>

					</tr>
					<tr>
						<th><span class="red">*</span>就读学历层次</th>
						<td>
							<html:select  property="fxjdxlcc" styleId="fxjdxlcc" style="width:150px">
								<html:options collection="xlccList" labelProperty="xlccmc" property="xlccmc"/>
							</html:select>
						</td>
					</tr>
						</tbody>
						<thead>
						<tr>
							<th colspan="4">
								<span>申请学费减免情况</span>
							</th>
						</tr>
					</thead>
						<tbody>
						<tr>
							<th><span class="red">*</span>申请学费减免总计(元)</th>
							<td colspan="3">
								<html:text property="sqxfzj" maxlength="10" styleId="sqxfzj" onblur="checkMoney(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th height="49px"><span class="red" style="height: 100px">*</span>申请理由</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" style="width:93%;" rows="4"></html:textarea>							
							</td>
						
						</tr>
						<tr>
							<th align="right" width="16%">
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',
											label: "需上传退役证明、录取通知书复印件等信息",
											eid : 'filepath_f'
										});
									});
								</script>
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
									<button type="button" onclick="saveZzsq('tyxs_zzsq.do?method=update');">
										保存草稿
									</button>
									<button type="button" onclick="saveZzsq('tyxs_zzsq.do?method=saveAndSubmit');">
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
		</html:form>
	</body>
	
		
</html>