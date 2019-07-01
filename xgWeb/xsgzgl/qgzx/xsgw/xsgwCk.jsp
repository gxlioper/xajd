<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/xsgwsh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click();
				jQuery("#but_save").click();
				jQuery("#tbody_gwxx").load("qgzx_wdgwsq.do?method=gwxx&gwdm=${model.gwdm}&t="+Math.random(),function(){
					jQuery("#tbody_gwxx tr:first").hide();
				});
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqbh}");
			});
		</script>
	</head>
	<body style="width:100%">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<html:form action="/qgzx_xsgwsh.do?method=xsgwSh&type=save" method="post" styleId="demoForm">
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden name="model" property="gwdm" styleId="gwdm"/>
			<html:hidden name="model" property="sqbh" styleId="sqbh"/>
			<html:hidden name="model" property="splc" styleId="splc"/>
			<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
								
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<logic:equal value="10344" name="xxdm">
					<thead>
						<tr>
							<th colspan="4">
								<span>困难生认定信息</span>
							</th>
						</tr>
					</thead>
					<logic:notEmpty name="knsInfoList">
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
												
											</logic:notPresent>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					</logic:notEmpty>
					<logic:empty name="knsInfoList">
					<tbody>
						<tr>
							<td colspan="5" style="text-align:center;">未找到任何记录！</td>
						</tr>
					</tbody>
					</logic:empty>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span> 
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<td colspan="4"><button class="btn_01" onclick="wdgwxzCx();return false;" type="button">选择岗位</button></td>
						</tr>
						
					</tbody>
					<logic:equal name="cssz" property="kcxs" value="yes">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生空余时间</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td colspan="4">
							<table style="width:100%;">
								<thead >
									<tr id="qgmxTr">
										<th style="text-align: center;">日期</th>
										<logic:iterate id="q" name="qgxmList">
											<th style="text-align: center;" xmdm="${q.dm }">${q.mc }</th>
										</logic:iterate>
									</tr>
								</thead>
								<tbody id="qgmxTbody">
									<%
										List<HashMap<String,String>> qgmxList = (List<HashMap<String,String>>)request.getAttribute("qgmxList");
										List<HashMap<String,String>> qgxmList = (List<HashMap<String,String>>)request.getAttribute("qgxmList");
									
										if (qgmxList != null){
											
											for (int i = 0 ; i < qgmxList.size() ; i++){
												
												String[] qgmxArr = null ;
												if(StringUtils.isNotNull(qgmxList.get(i).get("qgmx"))){
													qgmxArr = qgmxList.get(i).get("qgmx").split(",");
												}
												
											%>
												<tr>
													<td align="center">
														<%=qgmxList.get(i).get("qgrq") %>
														<input type="hidden" value="<%=qgmxList.get(i).get("qgrq") %>" name="mxrq"/>
													</td>
													<%
														for (int j = 0 ; j < qgxmList.size() ; j++){
															%>		
															<td align="center">
																<input type="checkbox" value="<%=qgxmList.get(j).get("dm") %>" name="mxxm<%=i %>"
																	disabled="disabled"
																	<%
																	
																		if (StringUtils.stringExistArray(qgxmList.get(j).get("dm"),qgmxArr)){
																			%>
																				checked="checked"
																			<%		
																		}
																	%>
																
																/>
															</td>
															<%
														}
													%>
												</tr>
											<%	
											}
										}
									%>
								</tbody>
							</table>
						</td>
					</tr>
					</tbody>
					</logic:equal>
					<%--华东师范个性化 第三方协议--%>
					<input type="hidden" name="xxdm" id="xxdm" value=${xxdm } />
					<% if("10511".equals(xxdm) || "10352".equals(xxdm)){ %>
						<thead>
							<tr>
								<th colspan="4">
									<span><bean:message key="lable.qgzx_xys" /></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr >
								<th >
									<span>附件信息</span>
								</th>
								<td colspan="3">
									<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
									<html:hidden name="cssz" property="dsfxy" styleId="dsfxy" />
									<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											var gid = jQuery('#dsfxy').val();
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
									</script>
								</td>
							</tr>
						</tbody>
					<% } %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:equal value="10344" name="xxdm">
						<tr>
							<th >
								<span>是否服从安排</span>
							</th>
							<td >
								${model.sffcap}
							</td>
							<th >
								<span>是否自强社成员</span>
							</th>
							<td >
								${model.sfzqscy}
							</td>
						</tr>
							<tr>
							<th>有何特长
								
							</th>
							<td colspan="3">
							${model.yhtc}
							</td>
						</tr>
						<tr>
							<th>简述家庭情况
								
							</th>
							<td colspan="3">
							${model.jtqk}
							</td>
						</tr>
						<tr>
							<th>对勤工助学的认识
								
							</th>
							<td colspan="3">
								${model.qgzxrs}
							</td>
						</tr>
					</logic:equal>
								<logic:equal value="11488" name="xxdm">
							    <th >
									<span>学习成绩</span>
								</th>
								<td >
									${model.xxcj}
								</td>
								<th >
									<span>身体状况</span>
								</th>
								<td >
									${model.stzk}
								</td>
						</logic:equal>
						<tr>
							<th width="16%">
								<font color="red"></font>申请时间
							</th>
							<td  colspan="3">
								${model.sqsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								申请理由
							</th>
							<td  colspan="3">
								${model.sqly}
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
						
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4" >
								<div class="btn">
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

