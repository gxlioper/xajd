<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils"  contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/wdgwsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click(iFClose);
//				jQuery("#but_save").click(saveForm);
				var url = "qgzx_wdgwsq.do?method=gwxx&xh="+jQuery('#xh').val() + "&gwdm=" + jQuery('#gwdm').val() + "&type=stu";
				jQuery("#tbody_gwxx").load(url);
				var qgxmSize = jQuery("#qgxmSize").val();
				if("0"==qgxmSize) { 
					initQgmx();
				 } 

				/*var isopen = jQuery("#xssqkg").val();
				var shzt = jQuery("#shzt1").val();
				if('3' != shzt && (isopen==null||isopen==''||"false" == isopen)){
					jQuery("#tssub").hide();
				}*/
			});
			
			function initQgmx(){
				
				jQuery("#qgmxTbody").empty();
				
				for (var i = 0 ; i <7; i++){
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
		<html:form action="qgzx_wdgwsq.do?method=wdgwSqStu" method="post" styleId="demoForm">
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="gwdm" styleId="gwdm"/>
			<input type="hidden" value="${model.shzt }" id="shzt1" /> 
			<input type="hidden" value="${xssqkg }" id="xssqkg" />
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
									<font color="red">*</font><span>是否服从安排</span>
								</th>
								<td >
									<html:radio property="sffcap" value="是">是</html:radio>&nbsp;
									<html:radio property="sffcap" value="否">否</html:radio>
								</td>
								<th >
									<font color="red">*</font><span>是否自强社成员</span>
								</th>
								<td >
									<html:radio property="sfzqscy" value="是">是</html:radio>&nbsp;
									<html:radio property="sfzqscy" value="否">否</html:radio>
								</td>
							</tr>
							<tr>
							<th><font color="red">*</font>有何特长
								<br />
								<font color="red">限500字</font>
							</th>
							<td colspan="3">
								<html:textarea property="yhtc" onblur="checkLen(this,500)" style="width: 100%" styleId="yhtc" rows="5">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>简述家庭情况
								<br />
								<font color="red">限500字</font>
							</th>
							<td colspan="3">
								<html:textarea property="jtqk" onblur="checkLen(this,500)" style="width: 100%" styleId="jtqk" rows="5">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>对勤工助学的认识
								<br />
								<font color="red">限500字</font>
							</th>
							<td colspan="3">
								<html:textarea property="qgzxrs" onblur="checkLen(this,500)" style="width: 100%" styleId="qgzxrs" rows="5">
								</html:textarea>
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="11488" name="xxdm">
							    <th >
									<font color="red">*</font><span>学习成绩</span>
								</th>
								<td >
									<html:select property="xxcj" styleId="xxcj" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="优">优</html:option>
										<html:option value="良">良</html:option>
										<html:option value="一般">一般</html:option>
										<html:option value="差">差</html:option>
									</html:select>
								</td>
								<th >
									<font color="red">*</font><span>身体状况</span>
								</th>
								<td >
									<html:select property="stzk" styleId="stzk" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="健康">健康</html:option>
										<html:option value="良好">良好</html:option>
										<html:option value="一般">一般</html:option>
										<html:option value="差">差</html:option>
									</html:select>
								</td>
						</logic:equal>
						<tr>
							<th width="16%">
								<font color="red">*</font>申请理由
								<br />
								<font color="red">(限1500字)</font>
							</th>
							<td  colspan="4">
								<textarea rows="5" style="width: 100%" onblur="checkLen(this,1500)" id="sqly" name="sqly"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 90px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
				<%--<tr>
					<td colspan="4">
						<div class="bz">
							<span style="font-size: larger; color: purple;">温情提示：</br>1.校内单位的岗位名称默认为</span>
							<span style="font-size: larger; color: red;">劳务</span>
							<span style="font-size: larger; color: purple;">或者</span>
							<span style="font-size: larger; color: red;">助管</span>
							<span style="font-size: larger; color: purple;">，请规范填写</br>2.若职位已过期仍没有招满，请自行延长截止日期</span>
						</div>
					</td>
				</tr>--%>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<%--<button type="button" type="button" id = "but_save">
										保存草稿
									</button>--%>
									<button type="button" id="tssub"  onclick="tj('qgzx_wdgwsq.do?method=wdgwSq&type=submit');return false;" id="buttonSave">
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

