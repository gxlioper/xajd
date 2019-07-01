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
			function saveForm(){
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
				if(dcje>32000){
					showAlertDivLayer("代偿金额上限为32000");
					return false;
				}*/

				var url = "rwfby_dcjg.do?method=save";
				var xh = jQuery("#xh").val();
				jQuery.post("rwfby_dcjg.do?method=isExists",{xh:xh},function(data){
					if (data==false||data=="false"){
						ajaxSubFormWithFun("rwfbydcjgForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
							}});
						});
					} else {
						showAlertDivLayer("该学生已经在结果库，不能重复增加！");
					}
				},"json");
			}
			jQuery(function(){
                dclxChange(document.getElementById("dclb"));
			})

            function dclxChange(target){
                if(target.value == "01"){
                    jQuery("#yhxx").hide();
                } else {
                    jQuery("#yhxx").show();
                }
            }
		</script>
	</head>
	<body>
		<html:form action="/rwfby_dcjg" method="post" styleId="rwfbydcjgForm">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
							<th>经办银行</th>
							<td>
							   <html:select property="yhdm" styleId="yhdm" style="width:155px">
									<html:option value="">请选择</html:option>
									<html:options collection="yhlist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th>贷款合同号</th>
							<td>
								 <html:text property="dkhth"  styleId="dkhth" maxlength="20" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
						   <th><span class="red">*</span>申请补偿金额(元)</th>
							<td colspan="3">
								<html:text property="dcje" styleId="dcje" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								证明材料
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<%--<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>--%>
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
				<div style="height:30px"></div>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

