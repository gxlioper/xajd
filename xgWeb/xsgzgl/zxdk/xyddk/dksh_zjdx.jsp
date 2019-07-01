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
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dksq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//加载下拉选项
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				
				if (jQuery.trim(xh) != ""){
				
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zxdkXyddkForm.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zxdkXyddkForm.splcid}&shid=${zxdkXyddkForm.id}");
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
				var url = "zxdkXyddk.do?method=submitAudit&tt="+new Date();
				ajaxSubFormWithFun("xyddkForm",url,function(data){
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
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			<html:hidden property="xh" styleId="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款申请</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况
									<logic:notEqual value="" property="xh" name="zxdkXyddkForm">
										<a onclick="showJtqk(this);" class="up" 
										   href="javascript:void(0);">
										   <font color="blue">点击展开/收起</font>	
										</a>
									</logic:notEqual>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								
								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>贷款学年</th>
							<td colspan="3">${zxdkXyddkForm.xn }
							</td>
						</tr>
						<tr>
							<th>每年学杂费</th>
							<td>${zxdkXyddkForm.xfdks }</td>
							<th>每年住宿费</th>
							<td>${zxdkXyddkForm.zsfdks }
							</td>
						</tr>
						<tr>
							<th>贷款年限</th>
							<td>${zxdkXyddkForm.xzf }</td>
							<th>贷款期限</th>
							<td>${zxdkXyddkForm.dkqx }</td>
						</tr>
						<tr>
							<th>每年贷款总金额</th>
							<td>${zxdkXyddkForm.mnje }</td>
							<th>贷款总金额</th>
							<td>${zxdkXyddkForm.dkje }</td></tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">${zxdkXyddkForm.sqly }</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${zxdkXyddkForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
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
							<td id="shjgSpan">
								 
							</td>
							<th>贷款合同编号</th>
							<td>
								<input type="hidden" name="zd1" value="贷款合同编号"/>
								<input type="text" name="zd3" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th>贷款银行</th>
							<td>
								<input type="hidden" name="zd1" value="贷款银行"/>
								<input type="text" name="zd5" maxlength="25"/>
							</td>
							<th>银行电话</th>
							<td>
								<input type="hidden" name="zd1" value="银行电话"/>
								<input type="text" name="zd6" maxlength="15"/>
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