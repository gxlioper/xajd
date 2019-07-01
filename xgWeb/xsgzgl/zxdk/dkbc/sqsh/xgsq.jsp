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

                if (!checkNull("dclb-dcje")) {
                    return false;
                }
                if(jQuery("#dclb").val() == "02"){
                    if(!checkNull("yhdm-dkhth")){
                        return false;
                    }
                }

				ajaxSubFormWithFun("dkbcForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
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
            });
		</script>
	</head>
	<body>
		<html:form action="/dkbc_sqsh" method="post" styleId="dkbcForm">
			<html:hidden property="id" />
			<div style='tab;width:100%;height:440px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
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
							    <html:hidden property="xn" styleId="xn"/>
								<span>${dkbcSqshForm.xn}</span>
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
								<html:text property="dkhth"  styleId="dkhth"maxlength="20" ></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>代偿金额(元)</th>
							<td colspan="3">
								<html:text property="dcje" styleId="dcje" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>就业单位名称</th>
							<td>
								<html:text property="dwmc" styleId="dwmc" maxlength="25" ></html:text>
							</td>
							<th>人力资源部电话</th>
							<td>
								<html:text property="dwdh"  styleId="dwdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" ></html:text>
							</td>
						</tr>
						<tr>
							<th>就业单位地址</th>
							<td>
								<html:text property="dwdz" styleId="dwdz" maxlength="50" ></html:text>
							</td>
							<th>服务年限</th>
							<td>
								<html:text property="fwnx" styleId="fwnx" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>行业性质(类型)</th>
							<td>
								<html:text property="hyxz" styleId="hyxz" maxlength="50" ></html:text>
							</td>
							<th>职务名称</th>
							<td>
								<html:text property="zwmc" styleId="zwmc" maxlength="50" ></html:text>
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
                                            label: "三方协议或合同书、二次分配证明、委托书",
                                            //存放附件的隐藏域的id
                                            elementid : 'filepath'
                                        });
                                    });
								</script>
							</td>
					   </tr>
					</tbody>
				</table>
			</div>
			<div style="height: 5px;"></div>
			<table  width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<button type="button" onclick="saveForm('dkbc_sqsh.do?method=update');">
									保存草稿
								</button>
								<button type="button" onclick="saveForm('dkbc_sqsh.do?method=saveAndSubmit');">
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
		</html:form>
	</body>
</html>

