<%@ page language="java"
	import="java.util.*,xgxt.utils.String.StringUtils"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/wdgwsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click(iFClose);
				//jQuery("#tbody_gwxx").load("xsgzgl/qgzx/xsgw/gwxx.jsp");
					if("10704"==jQuery("#xxdm").val()&&'${model.gwxzmc }'=="固定岗"){
						jQuery("#gdgcjbzTr").show();
						jQuery("#gwcjsxTr").hide();
					}
				var isopen = jQuery("#xssqkg").val();
				var shzt = jQuery("#shzt1").val();
				if('3' != shzt && (isopen==null||isopen==''||"false" == isopen)){
					jQuery("#tssub").hide();
				}
			});
		</script>
	</head>
	<body style="width: 99%">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<html:form action="qgzx_wdgwsq.do?method=wdgwxg" method="post"
			styleId="demoForm">
			<input type="hidden" name="xh" value="${xh}" id="xh" />
			<input type="hidden" name="sqbh" value="${model.sqbh}" id="sqbh" />
			<input type="hidden" name="shzt" value="${shzt}" id="shzt" />
			<input type="hidden" name="shzt1" value="${model.shzt }" id="shzt1" />
			<input type="hidden" name="xssqkg" value="${xssqkg }" id="xssqkg" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>

							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp"%>
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
														<td>
															学年
														</td>
														<td>
															学期
														</td>
														<td>
															认定档次
														</td>
														<td>
															<b>认定时间</b>
														</td>
														<logic:equal value="10335" name="xxdm" scope="request">
															<td>
																<b>认定状态</b>
															</td>
														</logic:equal>
													</tr>
												</thead>
												<tbody>
													<logic:present name="knsInfoList">
														<logic:notEmpty name="knsInfoList">
															<logic:iterate id="k" name="knsInfoList">
																<tr>
																	<td>
																		${k.xn }
																	</td>
																	<td>
																		${k.xqmc }
																	</td>
																	<td>
																		${k.dcmc }
																	</td>
																	<td>
																		${k.sqsj}
																	</td>
																	<logic:equal value="10335" name="xxdm" scope="request">
																		<td>
																			${k.sfqxrd}
																		</td>
																	</logic:equal>
																</tr>
															</logic:iterate>
														</logic:notEmpty>
														<logic:empty name="knsInfoList">
															<tr>
																<td colspan="5" style="text-align: center;">
																	未找到任何记录！
																</td>
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
									<td colspan="5" style="text-align: center;">
										未找到任何记录！
									</td>
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
						<logic:notEqual value="stu" name="userType">
							<tr>
								<td colspan="4">
								<logic:equal value="10344" name="xxdm">
										<div>
										   1.每人只能申请一个岗位。如有一人参加多个岗位，按备案岗位发放勤工助学报酬。 <br />
										   2.凡未经批准或备案的勤工助学活动，学校一律不支付报酬。<br/>
										   3.学生勤工助学报酬由学生处审核每月发放一次。<br />
										</div>
							    </logic:equal>
							    <br />
									<button class="btn_01" onclick="wdgwxzCx();return false;"
										type="button">
										选择岗位
									</button>
									<font color="red">${message}</font>
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th width="16%">
			       					  学年
								<input type="hidden" id="gwdm" name="gwdm"
									value="${model.gwdm }">
							</th>
							<td width="34%">
										${model.xn }
							</td>
							<th width="16%">
								用人单位
							</th>
							<td width="34%">
								${model.yrdwmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								岗位名称
							</th>
							<td width="34%">
									${model.gwmc }
							</td>
							<th width="16%">
								工作性质
							</th>
							<td width="34%">
								<logic:equal name="model" property="gwxzdm" value="0"> 临时</logic:equal>
								<logic:equal name="model" property="gwxzdm" value="1">正式</logic:equal>
							</td>
						</tr>
						<tr>
							<th width="16%">
								招聘人数
							</th>
							<td width="34%">
									${model.xqrs}人
							</td>

							<th width="16%">
								岗位类型
							</th>
							<td>
								<logic:equal name="model" property="gwlx" value="0">临时</logic:equal>
								<logic:equal name="model" property="gwlx" value="1">长期</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								岗位类别
							</th>
							<td >
									${model.gwxzmc}
							</td>
							<th>
								岗位薪酬上限
							</th>
							<td>
									${model.gwcjsx}元
							</td>
						</tr>
						<tr>
							<th>
								工时上限
							</th>
							<td colspan="3">
									${model.gssx}小时
								<span id="label"></span>
							</td>
						</tr>
						<tr>
							<th>
								招聘开始时间
							</th>
							<td>
									${model.zpkssj}
							</td>
							<th>
								招聘结束时间
							</th>
							<td>
								<logic:equal name="model" property="cq" value="1">长期</logic:equal>
								<logic:notEqual name="model" property="cq" value="1">${model.zpjssj}</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								招聘要求
							</th>
							<td colspan="3">
									${model.gwryyq}
							</td>
						</tr>
						<logic:equal name="cssz" property="kcxs" value="yes">
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>请勾选你空余时间</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table style="width: 100%;">
									<thead>
										<tr id="qgmxTr">
											<th style="text-align: center;">
												日期
											</th>
											<logic:iterate id="q" name="qgxmList">
												<th style="text-align: center;" xmdm="${q.dm }">
													${q.mc }
												</th>
											</logic:iterate>
										</tr>
									</thead>
									<tbody id="qgmxTbody">
										<%
											List<HashMap<String,String>> qgmxList = (List<HashMap<String,String>>)request.getAttribute("qgmxList");
											List<HashMap<String,String>> qgxmList = (List<HashMap<String,String>>)request.getAttribute("qgxmList");
										
											if (qgmxList != null){
												
												for (int i = 0 ; i < qgmxList.size() ; i++){
													
													String[] qgmxArr = null;
													if(StringUtils.isNotNull(qgmxList.get(i).get("qgmx"))){
														qgmxArr = qgmxList.get(i).get("qgmx").split(",");
													}
													
												%>
										<tr>
											<td align="center">
												<%=qgmxList.get(i).get("qgrq") %>
												<input type="hidden"
													value="<%=qgmxList.get(i).get("qgrq") %>" name="mxrq" />
											</td>
											<%
															for (int j = 0 ; j < qgxmList.size() ; j++){
																%>
											<td align="center">
												<input type="checkbox"
													value="<%=qgxmList.get(j).get("dm") %>" name="mxxm<%=i %>"
													<%
																		
																			if (StringUtils.stringExistArray(qgxmList.get(j).get("dm"),qgmxArr)){
																				%>
													checked="checked"
													<%		
																			}
																		%> />
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
					<% if("10511".equals(xxdm) || "10352".equals(xxdm)){ %>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.qgzx_xys" />
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
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
						<tr>
							<th width="16%">
								<font color="red">*</font>申请理由
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="4">
								<textarea rows="5" onblur="checkLen(this,500)"
									style="width: 100%" id="sqly" name="sqly">${model.sqly}</textarea>
								<%--
								<html:textarea rows="5" style="width: 90%" property="sqly" styleId="sqly" value=""></html:textarea>
							--%>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px">
			</div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"
								<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" type="button" id="but_save"
									onclick="updateForm('update');return false;">
									保存草稿
								</button>
								<button type="button" id="tssub"
									onclick="updateForm('submit');return false;" id="buttonSave">
									提交申请
								</button>
								<button type="button" type="button" id="but_close">
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

