<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				//加载下拉选项
				loadMkxxSelectOptions();
				//加载radio选项
				loadMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}
			});
			//验证学生申请金额是否超过金额上限
			function checkJesxSh(obj){
				var ylzd1= jQuery(obj).val();
				var jesx = jQuery("#jesx").val();
				if(parseFloat(ylzd1)>parseFloat(jesx)){
					showAlertDivLayer("申请金额超过项目金额上限！");
					jQuery(obj).val('');
				}
			}
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_sqsh" method="post" styleId="xmsqForm" onsubmit="return false;">
			<html:hidden property="guid"/>
			<html:hidden property="xmdm"/>
			<html:hidden property="xn"/>
			<html:hidden property="xq"/>
			<html:hidden property="shzt" />
			<input type="hidden" name="isopen" id="isopen" value="${isopen }" />
			<input type="hidden" name="jesx" id="jesx" value="${mkxxForm.je}"/>
		
			<div class="tab" style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况
									<logic:notEqual value="" property="xh" name="xszzSqshForm">
										<a onclick="showJtqk(this);" class="up" 
										   href="javascript:void(0);">
										   <font color="blue">点击展开/收起</font>	
										</a>
<%--										|--%>
<%--										<a onclick="editJtqk();" class="btn_xg"--%>
<%--										   href="javascript:void(0);">--%>
<%--										   <font color="blue">编辑家庭情况</font>	--%>
<%--										</a>--%>
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
								<span>困难生认定信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>学年</td>
												<td>学期</td>
												<td>认定档次</td>
											</tr>
										</thead>
										<tbody>
											<logic:present name="knsInfoList">
												<logic:notEmpty name="knsInfoList">
													<logic:iterate id="k" name="knsInfoList">
														<tr>
															<td>${k.xn }</td>
															<td>${k.xqmc }</td>
															<td>${k.dcmc }</td>
														</tr>
													</logic:iterate>
												</logic:notEmpty>
												<logic:empty name="knsInfoList">
													<tr>
														<td colspan="3" style="text-align:center;">未找到任何记录！</td>
													</tr>
												</logic:empty>
											</logic:present>
											<logic:notPresent name="knsInfoList">
												<tr>
													<td colspan="3" style="text-align:center;">未找到任何记录！</td>
												</tr>
											</logic:notPresent>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>
									资助项目申请信息
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th>申请周期</th>
							<td>${mkxxForm.xn }&nbsp; ${mkxxForm.xqmc }</td>
							<th>项目评定周期</th>
							<td>${xmwhForm.pdxn }&nbsp; ${xmwhForm.pdxqmc }</td>
						</tr>
						<tr>
							<th>项目名称</th>
							<td>${mkxxForm.xmmc }</td>
							<th>金额</th>
							<td>${mkxxForm.ylzd1 }</td>
						</tr>
						<logic:equal value="1" name="xmwhForm" property="jesfxssq">
						<tr>
							<th>申请金额</th>
							<td>
								<html:text onkeyup="checkInputData(this);checkJesxSh(this)" maxlength="7" property="ylzd1" styleId="ylzd1" style="width:100px"></html:text>
								<font id="message"  color="blue">上限金额：${mkxxForm.je }</font>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<logic:equal value="10335" name="xxdm">
									<span style="color: blue; line-height:20px;display:block;">
										根据学院(园)通知要求，如明确必须上传附件则按要求上传；如无附件要求则无需上传。
									</span>
									&nbsp;
								</logic:equal>
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<logic:notEqual value="11799" name="xxdm">
								<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
													//最大文件大小 单位M
													maxsize: 10,
													//存放附件的隐藏域的id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf|mp4',
													//最大文件大小 单位M
													maxsize: 300,
													//存放附件的隐藏域的id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:equal>
							</td>
						</tr>
					</tbody>
					
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp" %>
				</table>
			</div>

			<div style="height: 50px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveSqxg('save');">
										保存草稿
									</button>
									<button type="button" id="save_button" type="button" onclick="saveSqxg('submit');">
										提交申请
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

