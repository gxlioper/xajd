<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsq/js/qjsq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.qjsqid}&tt="+new Date().getTime());
		});
		</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="form" action="/qjsq">
		 <html:hidden property="qjsqid"/>
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>
		 <html:hidden property="qjzt"/>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>请假信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							学年
						</th>
						<td align="left">
							${data.xn}
						</td>
						<th align="right">
							学期
						</th>
						<td align="left">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							请假天数
						</th>
						<td align="left">
							${data.qjts}&nbsp;&nbsp;天&nbsp;&nbsp;
						</td>
						<th align="right">
							请假类型
						</th>
						<td align="left">
							${qjlxmc}
						</td>
					</tr>
					<logic:equal value="10351" name="xxdm">
					<tr>
						<th align="right" width="10%">
							请假节数
						</th>
						<td align="left" >
							${data.qjjs}&nbsp;&nbsp;节&nbsp;&nbsp;
						</td>
						<th align="right">
							是否离校
						</th>
						<td align="left">
							${data.sflxmc}
						</td>
					</tr>
					</logic:equal>
					<tr>
						<th align="right" width="10%">
							请假开始时间
						</th>
						<td align="left">
							${data.kssj }
						</td>
						<th align="right">
							请假结束时间
						</th>
						<td align="left">
							${data.jssj }
						</td>
					</tr>
					<logic:equal name="xxdm" value="12727">
						<tr>
							<th align="right" width="10%">
								请假节次
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12688">
						<tr>
							<th align="right" width="10%">
								请假节次
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="70002">
						<tr>
							<th align="right">
								校内校外
							</th>
							 <td align="left">
							 	${data.xnxw }
							 </td>
							 <th></th>
							 <td></td>
							</tr>
						</logic:equal>
					<logic:equal name="xxdm" value="12866">
						<tr>
							<th align="right" width="10%">
								家长电话
								
							</th>
							<td colspan="3">
								${data.jzdh }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								备注&nbsp;
							</th>
							<td colspan="3">
								${data.bz}
							</td>
						</tr>
						</logic:equal>
					<logic:equal value="10511" name="xxdm">
						<tr>
							<th>请假课程</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td>课程名称</td>
											<td>任课老师姓名</td>
											<td>任课老师联系方式</td>
										</tr>
									</thead>
									<tbody id="qjkc">
									<logic:present name="qjkcList">
										<logic:iterate id="qjkc" name="qjkcList" indexId="i">
											<tr>
												<td>
												${qjkc.kcmc }
												</td>
												<td>
												${qjkc.rklsxm }
												</td>
												<td>
												${qjkc.rklslxfs }
												</td>
											</tr>
										</logic:iterate>
										<logic:empty name="qjkcList">
											<tr>
												<td colspan="3" align="center">未找到任何记录！</td>
											</tr>
										</logic:empty>
									</logic:present>
									</tbody>
								</table>
							</td>
						</tr>
						</logic:equal>
						
						
						<logic:equal value="true" name="qjmxEnable" >
							</tbody>
							<thead>
								<tr>
									<th colspan="4">
										<span>请假明细情况</span>
									</th>
								</tr>
							</thead>
							<tbody>
							<tr>
								<td colspan="4">
									<table style="width:100%;">
										<thead >
											<tr id="qjmxTr">
												<th style="text-align: center;">日期</th>
												<logic:iterate id="q" name="qjxmList">
													<th style="text-align: center;" xmdm="${q.dm }">${q.mc }</th>
												</logic:iterate>
											</tr>
										</thead>
										<tbody id="qjmxTbody">
											<%
												List<HashMap<String,String>> qjmxList = (List<HashMap<String,String>>)request.getAttribute("qjmxList");
												List<HashMap<String,String>> qjxmList = (List<HashMap<String,String>>)request.getAttribute("qjxmList");
											
												if (qjmxList != null){
													
													for (int i = 0 ; i < qjmxList.size() ; i++){
														
														String[] qjmxArr = qjmxList.get(i).get("qjmx").split(",");
														
													%>
														<tr>
															<td align="center">
																<%=qjmxList.get(i).get("qjrq") %>
																<input type="hidden" value="<%=qjmxList.get(i).get("qjrq") %>" name="mxrq"/>
															</td>
															<%
																for (int j = 0 ; j < qjxmList.size() ; j++){
																	%>		
																	<td align="center">
																		<input type="checkbox" value="<%=qjxmList.get(j).get("dm") %>" name="mxxm<%=i %>"
																			disabled="disabled"
																			<%
																			
																				if (StringUtils.stringExistArray(qjxmList.get(j).get("dm"),qjmxArr)){
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
						</logic:equal>
					<logic:equal name="xxdm" value="10695">
							<tr>
								<th align="right">
									监护人姓名
								</th>
								<td>
									${data.jhrxm}
								</td>
								<th align="right">
									监护人联系方式
								</th>
								<td>
									${data.jhrlxfs}
								</td>
							</tr>
							<tr>
								<th align="right">
									交通工具
								</th>
								<td>
									${data.jtgjmc}
								</td>
								<th align="right">
									目的地
								</th>
								<td>
									${data.mdd}
								</td>
							</tr>
					</logic:equal>	
						
					<tr>
						<th align="right" width="10%">
							请假事由
						</th>
						<td colspan="3">
							${data.qjsy}
						</td>
					</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="fjid"/>
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
				</tbody>
			</table>
			<logic:notEqual value="无需审核" name="shztmc">	
			<table width="100%" border="0" class="formlist">
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
			</logic:notEqual>	
			<logic:equal value="1" name="xjxx" property="xjzt">	
			<table style="width: 100%;" border="0" class="formlist">
							<thead>
								<tr>
									<th colspan="4">
										<span>销假信息</span>
									</th>
								</tr>
							</thead>				
							<tbody>
								<tr>
									<th align="right" width="16%">
										实际请假天数
									</th>
									<td align="left" width="34%">
										${xjxx.sjqjts } &nbsp;&nbsp;天
									</td>
									<th align="right"  width="16%">
										实际请假时间
									</th>
									<td align="left"  width="34%">
										${xjxx.sjkssj }
										~
										${xjxx.sjjssj }
									</td>
								</tr>
								<tr>
									<th align="right">
										销假备注
									</th>
									<td colspan="3">
										${xjxx.xjbz }
									</td>
								</tr>
							
							</tbody>
						
					</table>
				</logic:equal>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
