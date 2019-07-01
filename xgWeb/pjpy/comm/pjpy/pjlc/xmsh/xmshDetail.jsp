<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		//点击岗位
		function clickGw(shjb){
			var tb_id = "tb_"+shjb;
			if($(tb_id)){
				if($(tb_id).style.display == "none"){
					$(tb_id).style.display = "";
				}else{
					$(tb_id).style.display = "none";
				}
			}
		}
		
		//保存审核状态
		function saveShzt(shzt){
			var xxdm = jQuery('#xxdm').val();
			var shyj = jQuery('textarea[name=shyj]').val();
			
			//贵州大学审核意见必填
			if (shyj == '' && xxdm=='10657'){
				alert('请填写审核意见!');
				return false;
			}
			
			var shjb = $("shjb").value;
			$("shzt").value = shzt;
			
			var msg = "确认修改选中记录的审核状态？\n";
				msg+= "注：\n";
				msg+= "通  过：下一级别可查看到该申请记录\n";
				msg+= "不通过：下一级别无法查看到该申请记录\n";
				if(shjb != "1"){
					msg+= "退  回：需要上一级别重新审核通过后，本级别才查看到该申请记录";
				}
				
			if (confirm(msg)) {
				url = "/xgxt/pjpyXmsh.do?method=xmshDetail&doType=sh";
				saveUpdate(url,'');
			}
		}
		
		//保存顺延项目
		function saveSyxm(){
			url = "/xgxt/pjpyXmsh.do?method=xmshDetail&doType=xmsy";
			saveUpdate(url,'');
		}
		
		</script>
	</head>
	<body style="overflow-x:hidden">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/pjpyXmsh">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 审核项目 -->
			<input type="hidden" name="shxm" id="shxm" value="${shxm }"/>
			<!-- 审核级别 -->
			<input type="hidden" name="shjb" id="shjb" value="${shjb }" />
			<!-- 审核状态 -->
			<input type="hidden" name="shzt" id="shzt" value="" />
			<!-- 审核状态 -->
			<input type="hidden" name="xh" id="xh" value="${rs.xh }" />
			<!-- 详细信息 -->
			<div class="tab">

				<table class="formlist" width="">
					<%@include file="../xmsq/xmxx.jsp" %>
					<%@include file="../xmsq/xsxx.jsp" %>
					<%@include file="../xmsq/zcxx.jsp" %>
					<%@include file="../xmsq/cjxx.jsp" %>
					<%@include file="../xmsq/sqxx.jsp" %>
					<%@include file="../xmsq/qtxx.jsp" %>
				<!-- 审核信息 -->
				<logic:notEmpty name="rsList">
					<logic:iterate id="shInfo" name="rsList">
						<logic:lessEqual name="shInfo" property="shjb" value="${shjb }">
							<!-- 非本级 -->
							<logic:notEqual name="shInfo" property="shjb" value="${shjb }">
								<thead onclick="hiddenMk('tb_${shInfo.shjb }')">
									<tr style="cursor:hand">
										<th colspan="4">
											<span>审核岗位：${shInfo.mc}
											</span>
										</th>
									</tr>
								</thead>
								<tbody id="tb_${shInfo.shjb }" style="display: none">
									<tr style="">
										<th align="right" width="20%">
											审核结果
										</th>
										<td align="left" width="30%">
											<logic:equal name="shInfo" property="shzt" value="未审核">
												<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="通过">
												<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="不通过">
												<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="退回">
												<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="需重审">
												<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
											</logic:equal>
										</td>
										<th align="right" width="20%">
											审核时间
										</th>
										<td align="left" width="">
											${shInfo.shsj }
										</td>
									</tr>
									<tr style="">
										<th align="right" width="20%">
											审核意见
										</th>
										<td align="left" colspan="3">
											<textarea rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,500)"
												id="shyj_${shjb }" readonly="readonly" type="_moz"><logic:notEqual name="shInfo" property="shyj" value="null">${shInfo.shyj }</logic:notEqual></textarea>
										</td>
									</tr>
								</tbody>
							</logic:notEqual>
							<!-- 本级 -->
							<logic:equal name="shInfo" property="shjb" value="${shjb }">
								<thead>
									<tr>
										<th colspan="4">
											<span>审核岗位：${shInfo.mc }</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr style="">
										<th align="right" width="20%">
											审核结果
										</th>
										<td align="left" width="30%">
											<logic:equal name="shInfo" property="shzt" value="未审核">
												<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="通过">
												<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="不通过">
												<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="退回">
												<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="需重审">
												<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
											</logic:equal>
										</td>
										<th align="right" width="20%">
											审核时间
										</th>
										<td align="left" width="">
											<logic:notEqual name="shInfo" property="shzt" value="未审核">
												${shInfo.shsj }
											</logic:notEqual>	
										</td>
									</tr>
									<tr style="">
										<th align="right" width="20%">
											审核意见
											</br>
											<font color="red">(限制录入500字)</font>
										</th>
										<td align="left" colspan="3">
											<textarea rows="5" name="shyj" style="width: 95%;word-break:break-all;" onblur="chLeng(this,500)"
												id="shyj_${shjb }" type="_moz"><logic:notEqual name="shInfo" property="shyj" value="null">${shInfo.shyj }</logic:notEqual></textarea>
										</td>
									</tr>
								</tbody>
							</logic:equal>
						</logic:lessEqual>
					</logic:iterate>
				</logic:notEmpty>
				<!-- 审核信息 end-->
	
					<!-- 操作 -->
					<tfoot>
						<tr>
							<td colspan='4'>
								<div class="bz">

								</div>
								<div class="btn">	
									<!-- 非查看 -->
									<logic:notEqual name="doType" value="view">						
										<button type="button" onclick="saveShzt('通过')">
											通 过
										</button>
										<button type="button" onclick="saveShzt('不通过')">
											不通过
										</button>
										<!-- 第一级不可退回 -->
										<logic:notEqual name="shjb" value="1">
											<button type="button" onclick="saveShzt('退回')">
												退 回
											</button>
										</logic:notEqual>
									</logic:notEqual>		
									<button type="button" onclick="Close();return false;" id="btn_gb">
										<bean:message key="lable.btn_gb_space" />
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<!-- 操作 end-->
			</div>
			<!-- 详细信息 end-->

			<logic:notEmpty name="xmsy">
				<!-- 审核状态选择Div-->
				<div id="syxmDiv" style="display: none;">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th colspan="2">
										<span>审核失败，请确认需要顺延的项目</span>
									</th>
								</tr>
							</thead>
							<tbody>						
								<tr>
									<th width="20%">
										项目选择
									</th>
									<td>
										<logic:iterate name="syxmList" id="syxm" indexId="num">
											<logic:equal name="num" value="0">
												<input type="radio" name="rad_shzt" 
													id="syxm_${syxm.xmdm }" value="${syxm.xmdm }" 
													onclick="$('syxm').value = this.value" 
													checked="checked"/>
													${syxm.xmmc }
													</br>
													<input type="hidden" name="syxm"id="syxm" value="${syxm.xmdm }"/>
											</logic:equal>
											<logic:notEqual name="num" value="0">
												<input type="radio" name="rad_shzt" 
													id="syxm_${syxm.xmdm }" value="${syxm.xmdm }" 
													onclick="$('syxm').value = this.value"/>
													${syxm.xmmc }
												</br>
											</logic:notEqual>
										</logic:iterate>	
									</td>
								</tr>	
								<tr>
									<th width="20%">
										审核失败原因
									</th>
									<td>
										${message }
									</td>
								</tr>							
							</tbody>
							<tfoot>
							<tr>
								<td colspan='2'>
									<div class="btn">
										<!-- 确定 -->
										<button type="button" onclick="saveSyxm();hiddenMessage(true,true);">
											<bean:message key="lable.btn_qd_space" />
										</button>
										<!-- 关闭 -->
										<button type="button" onclick="hiddenMessage(true,true);return false;">
											<bean:message key="lable.btn_gb_space" />
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<!-- 审核状态Div end-->
				<script language="javascript" defer="defer">
					setTimeout("viewTempDiv('顺延项目选择选择','syxmDiv','400','250')",100)
				</script>
			</logic:notEmpty>
			<logic:empty name="xmsy">
				<!-- 提示信息 -->
				<%@ include file="/comm/other/tsxx.jsp"%>
			</logic:empty>
			<!-- 审核状态Div end-->
			<script language="javascript" defer="defer">
				$("sqly").readOnly = "true";
			</script>
		</html:form>
	</body>
</html>
