<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			function saveForm(url){		
				var xh = jQuery("#xh").val();
				if (!checkNull("xh-dclb-dcje")) {
					 return false;
				}
				 if(jQuery("#dclb").val() == "02"){
					if(!checkNull("yhdm-dkhth")){
					    return false;
					}
				 }
				/*var dcje = jQuery("#dcje").val();
				if(dcje>8000){
					showAlertDivLayer("申请金额上限为8000");
					return false;
				}*/

				jQuery.post("rwfby_sqsh.do?method=isExists",{xh:xh},function(data){
					if (data==false||data=="false"){
						ajaxSubFormWithFun("rwfbysqshForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
							}});
						});
					} else {
						showAlertDivLayer("您已申请过相关补偿，不能再次申请！");
					}
				},"json");
			}
			function dclxChange(target){
			    if(target.value == "01"){
			        jQuery("#yhxx").hide();
				} else {
                    jQuery("#yhxx").show();
				}
			}
			jQuery(function(){
                dclxChange(document.getElementById("dclb"));
			})
		</script>
	</head>
	<body>
		<html:form action="/rwfby_sqsh" method="post" styleId="rwfbysqshForm">
			<input type="hidden" name="splcid" value="${cssz.splc }"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<div style='tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款补偿</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>申请学年</th>
							<td>
								<input type="hidden" name="xn" value="${xn}"/>
								<span>${xn}</span>
							</td>
							<th><span class="red">*</span>资助类型</th>
							<td>
								<html:select property="dclb" styleId="dclb" onchange="dclxChange(this);">
									<html:options collection="dclblist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr id="yhxx">
							<th><span class="red">*</span>经办银行</th>
							<td>
								<html:select property="yhdm" styleId="yhdm">
									<html:option value="">请选择</html:option>
									<html:options collection="yhlist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th><span class="red">*</span>贷款合同号</th>
							<td>
								<html:text property="dkhth"  styleId="dkhth" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>申请补偿金额(元)</th>
							<td>
								 <html:text property="dcje" styleId="dcje" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								证明材料
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        jQuery("#filepath_f").multiUploader({
                                            maxcount : 3,
                                            //后缀
                                            accept : 'png|gif|jpg|zip|rar|doc|docx',
                                            //最大文件大小 单位M
                                            maxsize: 10,
                                            label: "需上传退役证明、录取通知书复印件等信息",
                                            //存放附件的隐藏域的id
                                            elementid : 'filepath'
                                        });
                                    });
								</script>
							</td>
					   </tr>
					</tbody>
					</table>
					<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				</div>
				<div style="height: 20px">
				</div>
			</div>
			   <div>
			       <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm('rwfby_sqsh.do?method=save');">
										保存草稿
									</button>
									<button type="button" onclick="saveForm('rwfby_sqsh.do?method=saveAndSubmit');">
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

