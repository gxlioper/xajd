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
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}
				//非第一级审核有退回按钮
//				var firstRddc = jQuery("input[name=tempXmdm]").first().val();
//				if (firstRddc != ""){
//					jQuery("#btn_th").css("display","");
//				}

				//var shxm = jQuery("input[name=tempXmdm][value!=]").last().val();

				var xmdms = jQuery("input[name=tempXmdm]");
				var shxm = "";
				jQuery.each(xmdms,function(i,e){
					var textVal = jQuery(e).val();
					if (textVal != null && textVal != ''){
						shxm = textVal;
					}
				});

				if (shxm != ""){
					jQuery("#shxmdm").val(shxm);
				}
				jQuery.ajaxSetup({async:false});
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#guid").val()+"&tt="+new Date().getTime());
				getXmje();
				jQuery.ajaxSetup({async:true});
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#shlc").val()+"&shid="+jQuery("#shid").val());
				jQuery.ajaxSetup({async:true});

				//判断金额是否可以审核修改
				var jesfxssq=jQuery("#jesfxssq").val();
				if(jesfxssq=='1'){
					jQuery('.je').css("display","");
				}else{
					jQuery('.je').css("display","none");
				}
			});
			function jgcxView(){
				var url = "xszz_xszzbjpy_jgcxgl.do?method=jgcxView&xn=${mkxxForm.xn}&xq=${mkxxForm.xq}&sqr=${mkxxForm.xh}&shztbjpy=99&xmdm=${mkxxForm.dqxmdm}";
				var title = "查看评议详情";
				showDialog(title,800,500,url);
			}
		</script>
	</head>
	<body>
		<html:form action="/xszz_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="guid" styleId="guid"/>
			<html:hidden property="xtgwid" styleId="xtgwid"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="shlc" styleId="shlc"/>
			<html:hidden property="shid" styleId="shid"/>
			<input type="hidden" name="jesfxssq" id="jesfxssq" value="${xmwhForm.jesfxssq }"/>
			<input type="hidden" name="jesx" id="jesx" value="${xmwhForm.je}"/>
			<input type="hidden" name="oldshxmdm" id="oldshxmdm" value="${xmwhForm.xmdm}"/>
			<input type="hidden" name="sqje" id="sqje" value="${mkxxForm.je}"/>
		
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
									<a onclick="showJtqk(this);" class="up" 
									   href="javascript:void(0);">
									   <font color="blue">点击展开/收起</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<!-- 重庆工商开始 -->
					<logic:equal value="11799" name="xxdm" >
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
					</logic:equal>
					<!-- 重庆工商结束 -->
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
								<span>项目申请信息</span>
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
							<td>${mkxxForm.je }</td>
						</tr>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="ylzd5" styleId="fjid"/>
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
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>
					<logic:equal name="SFBJPY_Y" value="true">
					<thead>
						<tr>
							<th colspan="4">
								<span>班级评议结果<a href='javascript:void(0);' class='name' onclick='jgcxView();return false;'>查看评议详情</a></span>
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
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						
						<logic:present name="tzxmList">
							<tr>
								<th>获得项目</th>
								<td >
									<html:select property="shxmdm" styleId="shxmdm" onchange="jeSfkt(this)">
										<html:option value="${xmwhForm.xmdm }">${xmwhForm.xmmc }</html:option>
										<logic:equal value="false" name="isSame">
										<html:option value="${mkxxForm.xmdm }">${mkxxForm.xmmc }</html:option>
										</logic:equal>
										
										<html:options collection="tzxmList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
								<th>
									<font class="je" style="display: none" color="red">*</font>
									<font class="je" style="display: none">项目金额</font>
								</th>
								<td>
									<html:text onkeyup="checkInputData(this);checkJesxSh(this)" maxlength="7" styleClass="je" value="${mkxxForm.je }" property="shxmje" styleId="shxmje" style="display: none;width:100px"></html:text>
									<font class="je" id="message" style="display: none" color="blue">上限金额：${xmwhForm.je }</font>
								</td>
							</tr>
						</logic:present>
						
						<logic:notPresent name="tzxmList">
							<tr style="display:none;">
								<th>获得项目</th>
								<td colspan="3">
									<input name="shxmdm" value="${mkxxForm.xmdm }"/>
								</td>
							</tr>
						</logic:notPresent>
						<tr>
							<th>
								<font color="red">*</font>审核结果
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xszz&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px;" rows="5"
											   onblur="checkLen(this,200);" styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>已获资助项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table class="formList" width="100%">
									<thead align="center">
										<tr align="center" style="text-align: center;">
											<td>
												<b>学年</b>
											</td>
											<td>
												<b>学期</b>
											</td>
											<td>
												<b>项目名称</b>
											</td>
											<td>
												<b>金额</b>
											</td>
											<td>
												<b>申请时间</b>
											</td>
										</tr>
									</thead>
									<tbody align="center">
										<logic:notEmpty name="zzxmjgInfoList">
											<logic:iterate name="zzxmjgInfoList" id="zzxmjgInfo">
												<tr style="cursor: hand">
													<td>
														${zzxmjgInfo.xn}
													</td>
													<td>
														${zzxmjgInfo.xqmc}
													</td>
													<td>
														${zzxmjgInfo.xmmc}
													</td>
													<td>
														${zzxmjgInfo.je}
													</td>
													<td>
														${zzxmjgInfo.sqsj}
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</tbody>
								</table>
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
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveZzsh();">
										保 存
									</button>
									<%--<button type="button" onclick="saveZzsh('1','通过');">
										通过
									</button>
									<button type="button" onclick="saveZzsh('2','不通过');">
										不通过
									</button>
									<button type="button" onclick="saveZzsh('3','退回');" id="btn_th" style="display:none;">
										退回
									</button>
									--%><button type="button" name="关 闭" onclick="iFClose();">
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

