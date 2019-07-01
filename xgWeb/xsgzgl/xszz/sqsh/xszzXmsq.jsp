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
					});
					jQuery.ajaxSetup({async:false});
					showDialog("选择资助项目",500,350,"xszz_sqsh.do?method=getXmsqInfo&xh="+xh,{self:true});
				}

				if ("10351" == jQuery("#xxdm").val()){
					if ("" == jQuery("textarea[name=sqly]").val()) {
						jQuery("textarea[name=sqly]").val("    本人学习态度端正，勤奋刻苦，多次获得一等奖学金，并以优异的成绩进入了“溯初班”；注重德智体美劳全面发展，积极参与各类竞赛，并多次获奖；作为一名学生干部，严于律己，热情服务同学，出色地完成了各项工作任务，深受好评；注重理论与实践相结合，敢于创新，将所学知识运用到科研课题之中，主持校级课题《*******》、《******》2项，省级课题《*********》1项；并积极参与社会实践，热心公益慈善活动，曾被评为*****。");			
					}			
				}
				
			});
			
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_sqsh?method=xszzXmsq" method="post" styleId="xmsqForm" onsubmit="return false;">
			<html:hidden property="guid"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
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
									资助项目申请信息&nbsp;&nbsp;
									<a onclick="showXmxz();" 
									   href="javascript:void(0);">
									   <font color="blue">选择资助项目</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow" style="width:100%;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>项目名称 </td>
												<td>项目金额 </td>
												<td>项目评定周期</td>
											</tr>
										</thead>
										<tbody id="xmInfo" name="se">
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>申请学年</th>
							<td>${currXn}</td>
							<th>申请学期</th>
							<td>${currXq}</td>
						</tr>
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
									<button type="button" type="button" onclick="saveXmsq('save');">
										保存草稿
									</button>
									<button type="button" id="save_button" type="button" onclick="saveXmsq('submit');">
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

