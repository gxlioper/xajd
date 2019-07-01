<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">	
		//验证加分
		function checkJf(xmdm,jfdm){
		
			var shfs_id = "shfs_"+xmdm+"_"+jfdm;
			var xxf_id = "xxf_"+xmdm+"_"+jfdm;
			var sxf_id = "sxf_"+xmdm+"_"+jfdm;
			
			var shfs = $(shfs_id).value;
			var xxf = $(xxf_id).value;
			var sxf = $(sxf_id).value;
			
			if(shfs != ""){
				if(parseInt(shfs)<parseInt(xxf)){
					$(shfs_id).value = xxf;
				}else if(parseInt(shfs)>parseInt(sxf)){
					$(shfs_id).value = sxf;
				}
			}
		}
		
		//保存加分审核
		function saveJfsh(){
			if (confirm(checkInputValue())) {
				saveUpdate("/xgxt/zhcpJfsq.do?method=zcjfshDetail&doType=save","");
			}
		}
		
		//检测录入值
		function checkInputValue(){
			var num =  document.getElementsByName("shfs").length;
			var flag = true;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("shfs")[i];
				
				if(obj.value==""){
					flag = false;
					break;
				}
			}
			
			if(flag){
				return "确定您所审核的加分？";
			}else{
				return "有审核分数为空，最终将作为0分计算，请确认？";
			}
			
		}
		
		//同意申请分
		function argeeSqf(){
		
			if (confirm("执行本操作后，审核分数将以学生申请分数为准，请确认")) {
				var num =  document.getElementsByName("sqfs").length;
				var flag = true;
				
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("sqfs")[i];
					var sqfs = obj.value;
					var sqfs_id = obj.id;
					var shfs_id = sqfs_id.replace("sqfs","shfs");
					$(shfs_id).value=sqfs;
				}
			}
		}
		
		//提交加分审核
		function submitJfsh(){
			
			var flag=false;
			
			flag=checkShfs();
			
			if(flag){
				if (confirm("提交后不可再对分数进行修改，请确认")) {
					saveUpdate("/xgxt/zhcpJfsq.do?method=zcjfshDetail&doType=submit","");
				}
			}
		}
		
		function setDivHeight(){
			if($("div_jfxm")){
				if($("tb_xsxx").style.display == "none"){
					$("div_jfxm").style.height="300px";
				}else{
					$("div_jfxm").style.height="230px";
				}
			}			
		}
		
		 function checkShfs(){
     		
			var flag=false;
			
			//主键
			var pkValue=new Array();

			jQuery.ajaxSetup({async:false});
			
			// 得到JSON对象
	        var parameter ={};
			
			parameter["xh"]=jQuery("#xh").val();
			
			var url = "zhcpJfsq.do?method=checkShfs";
          	
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery.post(url,parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					
					if(result!="true"){
					
						if(confirm(result)){
							
							flag=true;
							
						}else {
						
							flag=false;
						
						}
						
						
					}else{
				
						flag= checkShfIsModi();
					}
					
					
				}
			);
			
			jQuery.ajaxSetup({async:true});
			
			return flag;
			
		}
		
		
		 function checkShfIsModi(){
		 	// 得到JSON对象
	        var parameter ={};
		  	
		  	var flag=false;
		  
		 	parameter["xh"]=jQuery("#xh").val();
		 	
		 	jQuery("[name=shfs]").each(function(){
		 		
		 		parameter["str_"+jQuery(this).attr("id")]=jQuery(this).val();
		 	});
			
			var url = "zhcpJfsq.do?method=checkShfIsModi";
          	
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery.ajaxSetup({async:false});
			
			jQuery.post(url,parameter,
				
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					if(result!="true"){
					
						if(confirm(result)){
							
							flag=true;
							
						}else {
						
							flag=false;
						
						}
						
					}else{
					
						flag= true;
					}
					
					
				}
			);
			
			jQuery.ajaxSetup({async:true});
			
			return flag;
			
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt">
			<h3>
				<span>综测周期：</span>
			</h3>
			<p>
				评奖学年(${pjxn })&nbsp;&nbsp;
				<logic:equal name="zczq" value="xq">
				评奖学期(${pjxqmc })&nbsp;&nbsp;
				</logic:equal>		
				
				综测周期
				<logic:equal name="zczq" value="xn">
					(学年)
				</logic:equal>
				<logic:equal name="zczq" value="xq">
					(学期)
				</logic:equal>		
			</p>
		</div>
		<!-- 提示信息 end-->	
		
		<html:form action="/zhcpJfsq">
			<!-- 隐藏域 -->
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="学生"/>
			<input type="hidden" id="url" name="url" value="/xgxt/zhcpJfsq.do?method=zcjfsq"/>
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<input type="hidden" id="zcjflx" value="${zcjflx }"/>
			<div class="tab">		
				<table class="formlist" border="0" align="center">
				
					<!-- 评奖基本信息 -->
<%--					<thead>--%>
<%--						<tr onclick="showHiddenNr('tb_zcxx')" style="cursor: hand">--%>
<%--							<th colspan="4">--%>
<%--								<span>综测基本信息</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody id="tb_zcxx" style="display:none">		--%>
<%--						<tr>--%>
<%--							<th width="16%">--%>
<%--								评奖学年--%>
<%--							</th>--%>
<%--							<td width="34%">							--%>
<%--								${pjxn }--%>
<%--							</td>--%>
<%--							<th width="16%">--%>
<%--								评奖学期--%>
<%--							</th>--%>
<%--							<td width="34%">							--%>
<%--								${pjxqmc }--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--								评奖年度--%>
<%--							</th>--%>
<%--							<td>							--%>
<%--								${pjnd }--%>
<%--							</td>--%>
<%--							<th>--%>
<%--								综测周期--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<logic:equal name="zczq" value="xn">--%>
<%--									学年--%>
<%--								</logic:equal>--%>
<%--								<logic:equal name="zczq" value="xq">--%>
<%--									学期--%>
<%--								</logic:equal>		--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
					<!-- 评奖基本信息 end-->
					
					<!-- 学生基本信息 -->
					<thead>
						<tr onclick="showHiddenNr('tb_xsxx');setDivHeight()" style="cursor: hand">
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_xsxx">		
						<tr>
							<th width="16%">
								学号	
							</th>
							<td width="34%">		
								${rs.xh }
								<input type="hidden" name="xh" id="xh"value="${rs.xh }" />
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">							
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>							
								${rs.xm }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>							
								${rs.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
					</tbody>
					<!-- 学生基本信息 end-->
					
					<!-- 综测加分信息 -->
					<logic:notEmpty name="rs" property="zcxmList">
						<thead>
							<tr onclick="showHiddenNr('tb_zcjf')" style="cursor: hand">
								<th colspan="4">
									<span>申请加分项目</span>
								</th>
							</tr>
						</thead>
						<tbody id="tb_zcjf">		
							<tr>
								<td colspan="4">
									<!-- 提示信息 end-->
									<logic:notEqual name="doType" value="jg">
										<div class="prompt">
												提示：1、希望审核分与申请分相同，请执行“同申请分”操作。2、大于（小于）上限分（下限分）的话，以上限分（下限分）为准。
											<a class="close" title="隐藏"
											   onclick="this.parentNode.style.display='none';"></a>
										</div>
									</logic:notEqual>
									<!-- 提示信息 end-->									
									<div style="width:100%;height:230px;overflow-x:hidden;overflow-y:auto;" id="div_jfxm">		
										<logic:iterate name="rs" property="zcxmList" id="zcxm">
											<logic:notEqual name="zcxm" property="count" value="0">
												<table width="100%">
													<thead>
														<tr onclick="showHiddenNr('tb_jfxm_${zcxm.xmdm }')" style="cursor: hand">
															<td colspan="2">
																${zcxm.xmmc }
															</td>
														</tr>
													</thead>
													<tbody id="tb_jfxm_${zcxm.xmdm }">
														<tr>
															<td width="16%">
																申请分数
															</td>
															<td>
																<logic:iterate name="zcxm" property="xmList" id="jfxm">
																	<logic:notEmpty name="jfxm" property="sqfs">
																		<span title="${jfxm.jfmc }">${jfxm.jfmc }(${jfxm.sqfs }分)</span>
																	</logic:notEmpty>
																</logic:iterate>
															</td>
														</tr>
														<tr>
															<td>
																申请理由
															</td>
															<td>
																<textarea id="sqly_${zcxm.xmdm }" rows="5" readonly="readonly"
																	style="word-break:break-all;width:90%" type="_moz">${zcxm.sqly }</textarea>
															</td>
														</tr>
														<logic:notEqual name="mklx" value="jg">
															<tr>
																<td>
																	审核分数
																</td>
																<td>
																	<table>
																	<%int n=0; %>
																	<tr>
																	<logic:iterate name="zcxm" property="xmList" id="jfxm">
																		<logic:notEmpty name="jfxm" property="sqfs">
																			<%n++; %>
																			<td>
																				<span title="${jfxm.jfmc }">${jfxm.showmc }</span>
																				<input type="hidden" name="xmdm" value="${zcxm.xmdm }"/>
																				<input type="hidden" name="jfdm" value="${jfxm.jfdm }"/>
																				<input type="hidden" name="sqfs" id="sqfs_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqfs }"/>
																				<input type="hidden" name="sqly" id="sqly_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqly }"/>
																				<input type="hidden" name="hid_count" id="hid_count_${rs.xmdm }" value="${rs.jfxmNum }"/>
																			</td>
																			<td>
																				<input type="text" style="width: 50px"
																					value="${jfxm.shfs }" name="shfs" 
																					onblur="checkJf('${zcxm.xmdm }','${jfxm.jfdm }');"
																					id="shfs_${zcxm.xmdm }_${jfxm.jfdm }"
																					onkeydown="return onlyNum(this,2)"
																					onmousedown="return onlyNum(this,2)"
																					maxlength="2" 
																					style="width : 80px;ime-mode:disabled"/>
																			</td>
																			<td>
																				(分数范围：${jfxm.xxf }-${jfxm.sxf }分)
																				<input type="hidden" id="xxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.xxf }"/>
																				<input type="hidden" id="sxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sxf }"/>
																			</td>
																		</logic:notEmpty>
																		<%if(n%2==0){%>
																		</tr>
																		<%}%>
																	</logic:iterate>
																</table>
																</td>
															</tr>
														</logic:notEqual>
													</tbody>
												</table>
											</logic:notEqual>
										</logic:iterate>
									</div>
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<!-- 综测加分信息 end-->
					
					<tfoot>
						<tr>
							<td colspan="4">
							
								<!-- 已提交 -->
								<logic:equal name="rs" property="sftj1" value="是">
									<logic:notEqual name="doType" value="jg">
										<div class="bz"><span class="red">注：该学生申请的加分已被审核提交</span></div>
									</logic:notEqual>
								</logic:equal>
								<!-- 已提交 end-->
									
								<div class="btn">
									<!-- 未提交 -->
									<logic:notEqual name="rs" property="sftj1" value="是">
										<logic:notEqual name="doType" value="jg">
											<button type="button" onclick="argeeSqf()" style="width: 80px">
												同申请分
											</button>

											<button type="button" onclick="saveJfsh()" id="buttonSave" style="width: 80px">
												保 存
											</button>
											
											<button type="button" onclick="submitJfsh()" style="width: 80px">
												提 交
											</button>

										</logic:notEqual>
									</logic:notEqual>
									<!-- 未提交 end-->
									
									<button type="button" onclick="Close();return false;" id="buttonClose" style="width: 80px">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>	
					
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>