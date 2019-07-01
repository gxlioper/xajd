<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${dkbcSqshForm.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${dkbcSqshForm.splcid}&shid="+jQuery("#shid").val());
			});
			function saveAudit(){
				var shzt=jQuery("#shjg").val();
				jQuery("#shzt").val(shzt);
				if (jQuery("#shyj").val() == ""){
					showAlertDivLayer("请填写审核意见！");
					return false;
				}
				if (jQuery("#shyj").val().length>200){
					showAlertDivLayer("审核意见不能超过200字");
					return false;
				}
				if (!checkNull("zd2-zd6")) {
					 return false;
				 }
				var dcje = jQuery("#zd6").val();
				if(dcje>32000){
					showAlertDivLayer("代偿金额上限为32000");
					return false;
				}
				var dclbmc = jQuery("#zd2").find("option:selected").text();
				jQuery("#zd3").val(dclbmc);
				var text=jQuery("#shjg").find("option:selected").text();
				//提交审核
				zx(shzt,text);
			}
			
			function zx(shzt,text){
				var url = "dkbc_sqsh.do?method=submitAudit&tt="+new Date();
				ajaxSubFormWithFun("form",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/dkbc_sqsh" method="post" styleId="form">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			<input type="hidden" id="shid" value="${shid}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
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
						 <logic:equal value="10511" name="xxdm">
					    	<tr>
					    		<th>贷款类型</th>
					    		<td>
					    			<span>${dkbcSqshForm.dklx}</span>
					    		</td>
					    		<th></th>
					    		<td></td>
					    	</tr>
					    </logic:equal>
						<tr>
							<th>申请学年</th>
							<td>
								${dkbcSqshForm.xn}
							</td>
							<th>毕业时间</th>
							<td>
								${dkbcSqshForm.bysj }
							</td>
						</tr>
						<tr>
							<th>应缴纳学费</th>
							<td>
								${dkbcSqshForm.yjxf }
							</td>
							<th>实际缴纳学费</th>
							<td>
								${dkbcSqshForm.xfje }
							</td>
						</tr>
						<tr>
							<th>贷款金额</th>
							<td>
								${dkbcSqshForm.dkbj }
							</td>
							<th>经办银行</th>
							<td>
								${dkbcSqshForm.yhmc }
							</td>
						</tr>
						<tr>
							<th>贷款合同号</th>
							<td>
								${dkbcSqshForm.dkhth }
							</td>
							<th>贷款起止时间</th>
							<td>
							   <logic:notEmpty name="dkbcSqshForm" property="dkkssj">
							        ${dkbcSqshForm.dkkssj }至${dkbcSqshForm.dkjssj }
							   </logic:notEmpty>						
							</td>
						</tr>
						<tr>
							<th>就业单位名称</th>
							<td>
								${dkbcSqshForm.dwmc }
							</td>
							<th>就业单位电话</th>
							<td>
								${dkbcSqshForm.dwdh }
							</td>
						</tr>
						<tr>
							<th>就业单位邮编</th>
							<td>
								${dkbcSqshForm.dwyb }
							</td>
							<th>就业单位地址</th>
							<td>
								${dkbcSqshForm.dwdz}
							</td>
						</tr>
						<tr>
						   <th>签订年限</th>
							<td>
								${dkbcSqshForm.fwnx}
							</td>
							<th>申请代偿金额(元)</th>
							<td>
							  ${dkbcSqshForm.dcje}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="filepath"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#filepath').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>审核结果
							</th>
							<td id="shjgSpan" colspan="3">
								
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>代偿类别</th>
							<td>
							   <input type="hidden" name="zd1" value="代偿类别"/>
							   <input type="hidden" name="zd3" id="zd3"/>
							   <html:select property="zd2" styleId="zd2">
									<html:option value="">请选择</html:option>
									<html:options collection="dclblist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th><span class="red">*</span>代偿金额</th>
							<td>
								<input type="hidden" name="zd4" value="代偿金额" />
								<input type="text" name="zd6" id="zd6" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> 审核意见&nbsp;
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zxdk_dkbc&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveAudit();">
										保 存
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