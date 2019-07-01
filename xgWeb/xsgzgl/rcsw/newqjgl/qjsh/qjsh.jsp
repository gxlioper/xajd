<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsh/js/operation.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.qjsqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${data.splcid}&shid=${data.shid}");

			var tr=jQuery("tr[name='qjxx']").first().next();
			tr.show();
			tr.next().show();
			//展开/隐藏所有
			var haveshow=false;
			var havehide=false;
			jQuery("#all").click(function(){
				jQuery("tr[name='qjxx']").each(function(){
						var tr=jQuery(this).next();
						if(tr.is(":hidden")){
							haveshow=true;
							tr.show();
							tr.next().show();
						}else{
							havehide=true;
							tr.hide();
							tr.next().hide();
						}
				});
				//如果有隐藏的又有显示的，统一设置为隐藏
				if(haveshow&&havehide){
					jQuery("tr[name='qjxx']").each(function(){
						var tr=jQuery(this).next();
							tr.hide();
							tr.next().hide();
					});
					haveshow=false;
					havehide=false;
				}
			});
			//展开 隐藏
			jQuery("[name=show]").each(function(){
				jQuery(this).click(function(){
					var tr=jQuery(this).parents("tr[name='qjxx']").next();
					if(tr.is(":hidden")){
						tr.show();
						tr.next().show();
					}else{
						tr.hide();
						tr.next().hide();
					}
				});
			});
			
		});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjsh">
		 <html:hidden property="qjsqid"/>
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>
		 <html:hidden property="qjzt"/>
		 <html:hidden property="xh"/>
		 <html:hidden property="splcid" styleId="splc"/>
		 <html:hidden property="shid" styleId="shid"/>
		 <html:hidden property="shzt" styleId="shzt"/>
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
						
						<logic:equal value="10695" name="xxdm">
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
				
				<!-- 历史请假记录START -->
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span style="float:left">请假历史信息</span><a href="javascript:;" id="all" style="float:right;margin-right:30px;color: blue">[展开/隐藏所有]</a>
							</th>
						</tr>
					</thead>
					<logic:notEmpty name="history">
					<logic:iterate id="model" name="history" scope="request">
						<tbody id="allmes">
							<tr name="qjxx" bgcolor="#FFFCCC">
								<td align="right"  width="16%">
									请假时间:
								</td>
								<td width="34%">
								${model.kssj }~${model.jssj }
								</td>
								<td align="right" width="16%">
									请假时长:
								</td>
								<td>
									${model.qjts}&nbsp;&nbsp;天&nbsp;&nbsp;
								</td>
								<td align="center">
									<a href="javascript:;" name="show">[展开/隐藏]</a>
								</td>
							</tr>
							<tr style="display:none;">
								<th>
									请假事由:
								</th>
								<td colspan="4">
									${model.qjsy}
								</td>
							</tr>
							<tr style="display:none;">
								<th>
									销假信息:
								</th>
								<td colspan="4">
									${model.xjbz}
								</td>
							</tr>
						</tbody>
					</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="history">
						<tr>
							<td colspan="5" align="center">
								无相关请假历史记录
							</td>
						</tr>
					</logic:empty>
				</table>
				<!-- 历史请假记录END -->	
				
				<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>审核信息</span>
						</th>
					</tr>
				</thead>
				<tbody><%--
					<tr>
						<th >
							<font color="red">*&nbsp;</font>审核状态
						</th>
						<td align="left" colspan="3">
							<html:select property="shzt" style="width:150px" styleId="shzt">
								<html:option value="0">未审核</html:option>
								
								<html:option value="1">通过</html:option>
								<html:option value="2">不通过</html:option>
								<html:option value="3">退回</html:option>
							</html:select>
						</td>
						</tr>
					<tr>
						--%>
					<tr>
						<th>
							<font color="red">*</font>审核结果
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
					<tr>
					<th width="20%">
							<font color="red">*&nbsp;</font> 审核意见&nbsp;
							<br />
							<font color="red">(限200字)</font>
						</th>
						<td colspan="3">
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=qqgl&id=shyj" />
							<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
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
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" id="btqd" onclick="save_sh();">
									保 存
								</button>
							<%--
								<button type="button" onclick="save_sh('1','通过');">
									通过
								</button>
								<button type="button" onclick="save_sh('2','不通过');">
									不通过
								</button>
								<button type="button" onclick="save_sh('3','退回');">
									退回
								</button>
								--%><%--<button type="button" name="保存" id="buttonClose" onclick="save('qjsh.do?method=qjsh&type=save','shzt-shyj');return false;">
									保存
								</button>
								--%>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
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
			<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
</html>
