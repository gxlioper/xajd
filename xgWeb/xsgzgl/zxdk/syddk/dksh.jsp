<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dksq.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//加载下拉选项
				loadViewMkxxSelectOptions();
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zxdkSydksqshForm.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zxdkSydksqshForm.splcid}&shid=${zxdkSydksqshForm.id}");
			});
			function saveAudit(){
				
				if (!checkNull("zd1-zd2-zd3")){
					return false;
				}
				
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
				var url = "zxdk_sydk.do?method=submitAudit&tt="+new Date();
				ajaxSubFormWithFun("zxdkSydksqshForm",url,function(data){
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
		<html:form action="/zxdk_sydk" method="post" styleId="zxdkSydksqshForm">
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
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>贷款学年</th>
							<td>
								${zxdkSydksqshForm.xn }
							</td>
							<th>贷款总金额（元）</th>
							<td>
								${zxdkSydksqshForm.dkje }
							</td>
						</tr>
						<tr>
							<th>住宿费应收数（元）</th>
							<td>
								${zxdkSydksqshForm.zsysf }
							</td>
							<th>学费应收数（元）</th>
							<td>
								${zxdkSydksqshForm.xfysf }
							</td>
						</tr>
						<tr>
							<th>贷款银行</th>
							<td>
								${zxdkSydksqshForm.yhmc }
							</td>
							<th>贷款期限（月）</th>
							<td>
								${zxdkSydksqshForm.dkqx }
							</td>
						</tr>
						<tr>
							<th>合同编号</th>
							<td>
								${zxdkSydksqshForm.htbh}
							</td>
							<th>贷款开始时间</th>
							<td >
								${zxdkSydksqshForm.dkkssj }
							</td>
						</tr>
						<tr>
							<th>回执检验码</th>
							<td colspan="3">
								${zxdkSydksqshForm.hzjym}
							</td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								${zxdkSydksqshForm.sqly }
							</td>
						</tr>
						<!-- 
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align:center;" >
									<thead>
										<tr>
											<td align="center">学年</td>
											<td align="center">学费（元）</td>
											<td align="center">住宿费（元）</td>
											<td align="center">生活费（元）</td>
										</tr>
										<logic:present name="dkxxList">
											<logic:iterate id="d" name="dkxxList">
											 <tbody>
												<tr>
													<td>
														${d.xn }
													</td>
													<td>
														${d.xf }
													</td>
													<td>
														${d.zsf }
													</td>
													<td>
														${d.shf }
													</td>
												</tr>
											  </tbody>
											</logic:iterate>
										</logic:present>
									</thead>
									<tbody id="dkxxTable"></tbody>
								</table>
							</td>
						</tr>
					 -->
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
							<th><font color="red">*</font>合同编号</th>
							<td>
							    <input type="hidden" name="zd1" value="合同编号"/>
								<html:text property="zd3" styleId="zd3" maxlength="20" ></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>贷款开始时间</th>
							<td>
							    <input type="hidden" name="zd2" value="贷款开始时间"/>
								<html:text property="zd5" styleId="zd5" 
										   onfocus="showCalendar('zd5','yyyy-mm-dd',false);"
										   maxlength="20" readonly="true"></html:text>
							</td>
							<th><font color="red">*</font>回执检验码</th>
							<td colspan="3">
							     <input type="hidden" name="zd4" value="回执检验码"/>
								<html:text property="zd6" styleId="zd6" maxlength="20"></html:text>
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