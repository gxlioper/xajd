<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		
		<script type="text/javascript">
			jQuery(function(){
				//加载下拉选项
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				var xn= jQuery("#xn").val();
				if (jQuery.trim(xh) != ""){
				
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${tyxsZzsqForm.id}&tt="+new Date().getTime());
				jQuery("#shjg").load("comm_spl.do?method=shth&lcid=${tyxsZzsqForm.splcid}&shid=${tyxsZzsqForm.id}");
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
				var text=jQuery("#shjg").find("option:selected").text();
				//提交审核
				zx(shzt,text);
			}
			
			function zx(shzt,text){
				var url = "tyxs_zzsq.do?method=submitAuditSH&tt="+new Date();				
				ajaxSubFormWithFun("tyxsZzsqForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}
		</script>
		<style type="text/css">
			.cn tr th{text-align:center}
			.cn tr td{text-align:center}
		</style>
	</head>
	<body>
		<html:form action="/tyxs_zzsq" method="post" styleId="tyxsZzsqForm">
			<html:hidden property="id" value="${tyxsZzsqForm.id }" styleId="id"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="splcid" value="${tyxsZzsqForm.splcid }"/>
			
			
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<logic:notEqual value="12688" name="xxdm" >
									<span>资助审核</span>
								</logic:notEqual>
								<logic:equal value="12688" name="xxdm" >
									<span>补偿审核</span>
								</logic:equal>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					
					
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					
						<tr>
							<th>隶属关系</th>
							<td>
								${tyxsZzsqForm.lsgx }
							</td>
							<th>复学就读学历层次</th>
							<td>
								${tyxsZzsqForm.fxjdxlcc}
							</td>
						</tr>
					
						<tr>
							<th>入伍前入学时间</th>
							<td>
								${tyxsZzsqForm.rwqrxsj }
							</td>
							<th>入伍时间</th>
							<td>
								${tyxsZzsqForm.rwsj }
							</td>
						</tr>
						<tr>
							<th>退役时间</th>
							<td>
								${tyxsZzsqForm.tysj }
							</td>
							<th>复学时间</th>
							<td>
								${tyxsZzsqForm.fxsj }
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="10511">
						<logic:notEqual name="xxdm" value="10704">
						<tr>
							<th>复学后第一年（元）</th>
							<td>
								${tyxsZzsqForm.dyzzxf }
							</td>
							<th>复学后第二年（元）</th>
							<td>
								${tyxsZzsqForm.dezzxf }
							</td>
						</tr>
						<tr>
							<th>复学后第三年（元）</th>
							<td>
								${tyxsZzsqForm.dszzxf }
							</td>
							
							<th>复学后第四年（元）</th>
							<td>
								${tyxsZzsqForm.dsizzxf }
							</td>
						</tr>
						<tr>						
							<th>复学就读年限</th>
							<td>
								${tyxsZzsqForm.fxjdnx }
							</td>
							<th>申请学费减免总计（元）</th>
							<td>
								${tyxsZzsqForm.sqxfzj }
							</td>
						</tr>
						</logic:notEqual>
						</logic:notEqual>
						<logic:equal name="xxdm" value="10704">
						<tr>
							<th>复学后第一年应缴（元）</th>
							<td>
								${tyxsZzsqForm.fistyj }
							</td>
							<th>复学后第二年应缴（元）</th>
							<td>
								${tyxsZzsqForm.secondyj }
							</td>
						</tr>
						<tr>
							<th>复学后第三年应缴（元）</th>
							<td>
								${tyxsZzsqForm.thirdyj }
							</td>
							
							<th>复学后第四年应缴（元）</th>
							<td>
								${tyxsZzsqForm.fourthyj }
							</td>
						</tr>
						<tr>
							<th>复学后学费应缴总计（元）</th>
							<td colspan="3" >
								${tyxsZzsqForm.xfyjzj }
							</td>
						</tr>
						<tr>
							<th>复学后第一年减免（元）</th>
							<td>
								${tyxsZzsqForm.dyzzxf }
							</td>
							<th>复学后第二年减免（元）</th>
							<td>
								${tyxsZzsqForm.dezzxf }
							</td>
						</tr>
						<tr>
							<th>复学后第三年减免（元）</th>
							<td>
								${tyxsZzsqForm.dszzxf }
							</td>
							<th>复学后第四年减免（元）</th>
							<td>
								${tyxsZzsqForm.dsizzxf }
							</td>
						</tr>
						<tr>
							<th>申请学费减免总计（元）</th>
							<td colspan="3" >
								${tyxsZzsqForm.sqxfzj }
							</td>
						</tr>
						<tr>
							<th>是否补报</th>
							<td>
								${tyxsZzsqForm.sfbb }
							</td>
							<th>复学就读年限</th>
							<td>
								${tyxsZzsqForm.fxjdnx }
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10511">
						<th>贷款类型</th>
							<td>
								${tyxsZzsqForm.dklx }
							</td>
						<th>复学就读年限</th>
							<td>
								${tyxsZzsqForm.fxjdnx }
							</td>
						</logic:equal>
						
						
						<logic:equal name="xxdm" value="10511">
						 <tr>
							<th>贷款金额(元)</th>
							<td>
								${tyxsZzsqForm.dkbj}
							</td>
							<th>经办银行</th>
							<td>
							  ${yhmc}
							</td>
						</tr>
						<tr>
							<th>贷款合同号</th>
							<td>
								${tyxsZzsqForm.dkhth}
							</td>
							<th>贷款起止时间</th>
							<td>
								${tyxsZzsqForm.dkkssj}
								至
								${tyxsZzsqForm.dkjssj}
							</td>
						</tr>
						<tr>
							<td colspan="4" width="100%">
								<table width="50%" class="cn">
									<tr width="100%" >
										<th width="40%" >年度</th>
										<th width="60%" >年度金额</th>
									</tr>
									<logic:iterate id="i" name="ndjelist">
										<tr width="100%" >
											<td width="40%" >${i.nd}</td>
											<td width="60%" >${i.je}</td>
									    </tr>
									</logic:iterate>
									<tr>
										<td width='40%' >申请学费减免总计</td>
									    <td width='60%' >
									    	${tyxsZzsqForm.sqxfzj}
									    </td>
									</tr>
								</table>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>申请理由</th>
							<td colspan="3" style="width: 100%" >
								${tyxsZzsqForm.sqly }
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="10511">
							<tr>
								<th align="right" width="10%">
									附件信息
								</th>
								<td colspan="3">
									<div id="commonfileupload-list-0" style="padding: 5px;"></div>
									<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											var gid = "${tyxsZzsqForm.filepath}";
											jQuery.MultiUploader_q({
												gid : gid,
												targetEl : 'commonfileupload-list-0'
											});
										});
									</script>
								</td>
							</tr>					
						</logic:notEqual>
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
							<th width="20%">
								<font color="red">*</font>审核结果
							</th>
							<td id="shjg"  colspan="3">
								
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> 审核意见&nbsp;
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gjzxdk&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${tyxsZzsqForm.shyj}</textarea>
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
		<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzsq.js"></script>
</html>