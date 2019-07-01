<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrd/js/knsrd.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				//加载下拉选项
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					});
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#guid").val()+"&tt="+new Date().getTime());
			});
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_knsrd" method="post" styleId="knsrdForm">
			<html:hidden property="guid" styleId="guid"/>
			<html:hidden property="xh" styleId="xh"/>
		
		<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况
									<logic:notEqual value="" property="xh" name="knsrdForm">
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
								<span>困难生认定信息</span>
								<a onclick="showLsjl(this);" class="up" href="javascript:void(0);">
								<font color="blue">点击展开/收起</font>	
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="t_rdlsjl" style="display: none">
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
														<td colspan="5" style="text-align:center;">未找到任何记录！</td>
													</tr>
												</logic:empty>
											</logic:present>
											<logic:notPresent name="knsInfoList">
												<tr>
													<td colspan="5" style="text-align:center;">未找到任何记录！</td>
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
								<span>困难生认定申请</span>
							</th>
						</tr>
					</thead>
					<logic:equal value="10346" name="xxdm">
						<tr>
							<th>
								<span>
									家庭困难类型
								</span>
							</th>
							<td>
								${mkxxForm.jtknlxmc}
							</td>
							<th>
								<span>
									高档消费品类型
								</span>
							</th>
							<td>
								${mkxxForm.gdxfplxmc}
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="11998">
						<th>
							调查结果分数
						</th>
						<td colspan="3">
							${zf}
						</td>
					</logic:equal>
					<logic:equal value="1" name="sqsftxdc">
						<tbody>
							<tr>
								<th align="right">申请档次</th>
								<td colspan="3">
									${mkxxForm.sqdcmc }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<%-- 中国美术学院个性化  --%>
					<logic:equal name="xxdm" value="10355">
						<th>
							家庭人均年收入
						</th>
						<td colspan="3">
							${mkxxForm.jtrjnsr}
						</td>
					</logic:equal>
					<tr>
						<th>
							附件信息
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="ylzd2" styleId="fjid"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
					</tr>

				<logic:equal value="12389" name="xxdm">
					<tbody>
					<tr>
						<th align="right">申请理由</th>
						<td colspan="3">
							<logic:notEmpty name="sqlyList">
								<logic:iterate name="sqlyList" id="s"  indexId="i" >
									${s}
									<br/>
								</logic:iterate>
								${mkxxForm.ylzd10}
							</logic:notEmpty>
						</td>
					</tr>
					</tbody>
				</logic:equal>
					<logic:equal value="10742" name="xxdm">
						<tbody>
							<tr>
								<th align="right">申请理由</th>
								<td colspan="3">
									${mkxxForm.sqlydm }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<logic:equal value="10277" name="xxdm">
					<tbody>
							<tr>
								<th align="right">困难原因</th>
								<td colspan="3">
									${mkxxForm.yymc }
								</td>
							</tr>
						</tbody>
					</logic:equal>
				<tbody>
				<tr>
					<th align="right">申请理由</th>
					<td colspan="3">
						${mkxxForm.sqlyyy}
					</td>
				</tr>
				<tr>
					<th name="th_sqly">
						具体理由
					</th>
					<td colspan="3" style="word-break:break-all;">
							${mkxxForm.sqly}
					</td>
				</tr>
				</tbody>
					<%--<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>--%>
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
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
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

