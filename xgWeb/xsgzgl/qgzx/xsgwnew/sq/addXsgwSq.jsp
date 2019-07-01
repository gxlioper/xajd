<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgwnew/sq/js/xsgwsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click(iFClose);
				jQuery("#but_save").click(saveForm);
				//jQuery("#tbody_gwxx").load("xsgzgl/qgzx/xsgw/gwxx.jsp");
				var qgxmSize = jQuery("#qgxmSize").val();
				if("0"==qgxmSize) { 
					initQgmx();
				 } 
			});
			function initQgmx(){
				
				jQuery("#qgmxTbody").empty();
				
				for (var i = 0 ; i < "7"; i++){
					var nextDate = getNextDate(i);
					var rInput = jQuery("<input type='hidden' name='mxrq' value='"+nextDate+"'/>");
					var td = jQuery("<td align='center'>"+nextDate+"</td>");
					var tr = jQuery("<tr></tr>");
					td.append(rInput);
					tr.append(td);
					
					var qjxmCount = jQuery("#qgmxTr th").size()-1;
					
					for (var j = 0 ; j < qjxmCount ; j++){
						var cbox = jQuery("<input name='mxxm"+i+"' type='checkbox' value='"+jQuery("#qgmxTr th").eq(j+1).attr("xmdm")+"'/>");
						var td = jQuery("<td align='center'></td>");
						td.append(cbox);
						tr.append(td);
					}
					
					jQuery("#qgmxTbody").append(tr);
				}
			};
			
			function getNextDate(curDate){
				switch(curDate) { 
					case 0: 
						return "星期一"; 
					break; 
					case 1: 
						return "星期二";  
					break; 
					case 2: 
						return "星期三";  
					break;
					case 3: 
						return "星期四"; 
					break; 
					case 4: 
						return "星期五";  
					break;
					case 5: 
						return "星期六";  
					break; 
					case 6: 
						return "星期日";  
					break; 
					default: 
					break;
				}
			}
			
		</script>
	</head>
	<body style="width:99%">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<html:form action="xsgwsqnew_sq" method="post" styleId="demoForm">
			<input type="hidden" name="qgxmSize" id="qgxmSize" value="${qgxmSize }"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist" >
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span> 
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<td colspan="4">${cssz.gwxx } <br />
							2、具体岗位待相关老师审核确认分配</td>
						</tr>
					</tbody>
					<logic:equal name="cssz" property="kcxs" value="yes">
					<thead>
						<tr>
							<th colspan="4">
								<span>请勾选你空余时间</span>
								<label>
									<input type="checkbox" onclick="checkAll(this);" checked="checked"/>全选/取消全选
								</label>
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
					
<%--					华东师范个性化 第三方协议--%>
					<% if("10511".equals(xxdm) || "10352".equals(xxdm)){ %>
						<thead>
							<tr>
								<th colspan="4">
									<span><bean:message key="lable.qgzx_xys" /></span>
								</th>
							</tr>
						</thead>
							<tr >
								<th >
									<span>附件信息</span>
								</th>
								<td colspan="3">
									<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
									<html:hidden name="cssz" property="dsfxy" styleId="dsfxy"/>
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
					<% } %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<logic:equal value="10344" name="xxdm">
						<tr>
							<th >
								<span>是否服从安排</span>
							</th>
							<td >
								<html:radio property="sffcap" value="是">是</html:radio>&nbsp;
								<html:radio property="sffcap" value="否">否</html:radio>
							</td>
							<th >
								<span>是否自强社成员</span>
							</th>
							<td >
								<html:radio property="sfzqscy" value="是">是</html:radio>&nbsp;
								<html:radio property="sfzqscy" value="否">否</html:radio>
							</td>
						</tr>
					</logic:equal>
						<tr>
							<th width="16%">
								<font color="red">*</font>申请理由
								<br />
								<font color="red">限500字</font>
							</th>
							<td  colspan="4">
								<textarea rows="5" style="width: 100%" id="sqly" name="sqly"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="tssub"  onclick="tj('xsgwsqnew_sq.do?method=addXsgwSq&type=submit');return false;" id="buttonSave">
										提交申请
									</button>
									<button type="button" type="button" id= "but_close">
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

