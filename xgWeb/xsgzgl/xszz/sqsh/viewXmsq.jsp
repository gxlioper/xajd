<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				//加载下拉选项
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfoForStu",{xh:xh},function(){
					})
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#guid").val()+"&tt="+new Date().getTime());
				if(("${usertype}" != 'stu' || jQuery.trim("${knsrd.sjly}") == '2' ) && ("${xxdm}" == '10718')　){
					jQuery("#t_head_jtqk").show();
				}else{
					jQuery("#t_head_jtqk").hide();
				}
			});
		</script>
	</head>
	<body>
		<html:form action="/xszz_sqsh" method="post" styleId="xmsqForm">
			<html:hidden property="guid" styleId="guid"/>
			<html:hidden property="xh" styleId="xh"/>
		
			<div class="tab" style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					<thead id = "t_head_jtqk">
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
					<tbody id="knsrd">
						<tr>
							<td colspan="4">
								<div class="con_overlfow">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>学年</td>
												<td>学期</td>
												<td>认定档次</td>
												<td ><b>认定时间</b></td>
												<logic:equal value="10335" name="xxdm" scope="request">
													<td ><b>认定状态</b></td>
												</logic:equal>	
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
															<td >
																${k.sqsj}
															</td>
															<logic:equal value="10335" name="xxdm" scope="request">
																<td >
																	${k.sfqxrd}
																</td>	
															</logic:equal>	
														</tr>
													</logic:iterate>
												</logic:notEmpty>
												<logic:empty name="knsInfoList">
													<tr>
														<logic:equal value="10335" name="xxdm" scope="request">
															<td colspan="5" style="text-align:center;">未找到任何记录！</td>
														</logic:equal>
														<logic:notEqual value="10335" name="xxdm" scope="request">
															<td colspan="4" style="text-align:center;">未找到任何记录！</td>
														</logic:notEqual>
													</tr>
												</logic:empty>
											</logic:present>
											<logic:notPresent name="knsInfoList">
												<tr>
													<logic:equal value="10335" name="xxdm" scope="request">
														<td colspan="5" style="text-align:center;">未找到任何记录！</td>
													</logic:equal>
													<logic:notEqual value="10335" name="xxdm" scope="request">
														<td colspan="4" style="text-align:center;">未找到任何记录！</td>
													</logic:notEqual>
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
							<td>${mkxxForm.xn }&nbsp;${mkxxForm.xqmc }</td>
							<th>项目评定周期</th>
							<td>${mkxxForm.pdxn }&nbsp;${mkxxForm.pdxqmc }</td>
						</tr>
						<tr>
							<th>项目名称</th>
							<td>${mkxxForm.xmmc }</td>
							<th>金额</th>
							<td>${mkxxForm.ylzd1 }</td>
						</tr>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="ylzd5" styleId="fjid"/>
								<div id="fjidDiv"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#fjidDiv').multiUploader_q({
											gid : jQuery('#fjid').val(),
											uid : '2'
											});
									});
								</script>
							</td>
						</tr>
					</tbody>
					
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>
					<logic:equal name="SFBJPY_Y" value="true">
					<thead>
						<tr>
							<th colspan="4">
								<span>班级评议结果</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								评议小组成员
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzcyxms }
							</td>
						</tr>
						<tr>
							<th>
								评议小组代表
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzdbxms }
							</td>
						</tr>
						<tr>
							<th>
								评议结果
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgshztmc }
							</td>
						</tr>
						<tr>
							<th>
								评议会时间
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhsj }
							</td>
						</tr>
						<tr>
							<th>
								评议会地点
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhdd }
							</td>
						</tr>
						<tr>
							<th>
								评议意见
							</th>
							<td colspan="3" style="word-break: break-all;">
								${mkxxForm.bjpyjgpyyj }
							</td>
						</tr>
					</tbody>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow">
									<table class="dateline" width="100%">
										<tbody id="shxx">
											<tr>
												<td colspan="4" id="shlccx">
												
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 50px"></div>
			<div class='tab'>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="关 闭" onclick="iFClose();">
											关 闭
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

