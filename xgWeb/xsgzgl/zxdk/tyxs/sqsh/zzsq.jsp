<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		
		<script type="text/javascript">
		jQuery(function(){
			 /*var videotype = document.getElementsByName("lsgx");
				var xxdm = jQuery("#xxdm").val();
		      var len = videotype.length;
		      var i=0;
		      for(;i<len;i++){
		       if("中央" == videotype[i].value){
		        videotype[i].checked=true;
		        break;
		       }
		      }  
		  	//华中师范大学
				if(xxdm == '10511'){
					dklxchange();
					fxjdnxchange();
					
				}*/
		});

			function saveForm(url){	
			var xxdm = jQuery("#xxdm").val();	
			
				var xh= jQuery("#xh").val();
				var xn = jQuery("#xn").val();

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
						jQuery.post("tyxs_zzsq.do?method=getCountByXhAndXn",{xh:xh,xn:xn,id:jQuery("#id").val()},function(data){
							
							if (Number(data) == 0){					
								
								showConfirmDivLayer("您确定要执行此操作吗？",{
									"okFun" : function(){
									ajaxSubFormWithFun("tyxsZzsqForm",url,function(data){
										showAlertDivLayer(data["message"],{},{"clkFun":function(){
											refershParent();
										}});
										});

										}
									});
									
									
							} else {
								showAlertDivLayer("该学年已经申请过学费资助，请确认！");
							}
						},"json");
						
				}else{
					if(tyCheckNull()==false){
						return false;
					}
					if(checksqlylength() == false){
						return false;
					}
					var fj = jQuery("#filepath_f").val();
                    if(fj == "" || typeof fj == 'undefined'){
                        showAlertDivLayer("请上传退役证明、录取通知书复印件等信息！");
                        return false;
                    }
						jQuery.post("tyxs_zzsq.do?method=getCountByXhAndXn",{xh:xh,xn:xn,id:jQuery("#id").val()},function(data){
							
							if (Number(data) == 0){					
								
								showConfirmDivLayer("您确定要执行此操作吗？",{
									"okFun" : function(){
									ajaxSubFormWithFun("tyxsZzsqForm",url,function(data){
										showAlertDivLayer(data["message"],{},{"clkFun":function(){
											refershParent();
										}});
										});

										}
									});
									
									
							} else {
								showAlertDivLayer("该学年已经申请过学费资助，请确认！");
							}
						},"json");
						
					
					
				}
				
				
			}
			
		</script>
		<style type="text/css">
			.cn tr th{text-align:center}
			.cn tr td{text-align:center}
		</style>
	</head>
	<body>
		<html:form action="/tyxs_zzsq" method="post" styleId="tyxsZzsqForm">
	<%@ include file="/comm/hiddenValue.jsp"%>
			
			<html:hidden property="splcid" value="${cssz.xfzzshlc }" styleId="splcid"/>			
			<html:hidden property="xn" value="${xn}" styleId="xn"/>	
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<html:hidden property="dqxn" value="${mkxxForm.xn }" styleId="dqxn"/>			
			<html:hidden property="id" styleId="id"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist" id="tyform">
					<thead>
						<tr>
							<th colspan="4">
								<span>资助申请</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
				
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
								<html:text property="rwsj" styleId="rwsj" readonly="true"
										   onfocus="showCalendar('rwsj','y-mm-dd');"></html:text>
							</td>
							<th><span class="red">*</span>退役时间</th>
							<td>
								<html:text property="tysj" styleId="tysj" readonly="true"
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
							<th height="49px"><span class="red" >*</span>申请理由</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" style="width:93%;" rows="4"></html:textarea>							
							</td>
						</tr>
						<tr>
							<th align="right">
								<span class="red">*</span>附件信息
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
				<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				</div>
				<div style="height:25px;"></div>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm('tyxs_zzsq.do?method=save');">
										保存草稿
									</button>
									<button type="button" onclick="saveForm('tyxs_zzsq.do?method=saveAndSubmit');">
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

