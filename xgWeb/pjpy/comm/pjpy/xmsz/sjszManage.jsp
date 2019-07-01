<%@ page language="java" import="java.util.*"  pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript">
	
		function setEdit(obj){
			var td = jQuery(obj);
	        //建立一个文本框，设置文本框的值为保存的值     
	        var input=jQuery("input[type=text]",td); 
	        //
			input.attr('style','display:""');
			//设置文本框宽度等于td的宽度  
			input.width(100); 
			
			td.html(input);
		}
		
		function setSelect(obj){
			var td = jQuery(obj);
	        //建立一个文本框，设置文本框的值为保存的值     
	        var span=jQuery("span",td); 
	        //
			span.attr('style','display:""');
			
			td.html(span);
		}
		
		function saveSjsz(){
			var sqkssj = jQuery('input[name=sqkssj]').length;
			var sqjssj = jQuery('input[name=sqjssj]').length;

			for(var i=0;i<sqkssj;i++){
				var kssj = jQuery('input[name=sqkssj]')[i].value;
				var jssj = jQuery('input[name=sqjssj]')[i].value;
				
				if(kssj != "" && jssj != "" && kssj > jssj){
					alertError("第"+(i+1)+"行申请开始时间不能晚于申请结束时间，请确认");
					return false;
				}
			}
			
			var shkssj = jQuery('input[name=shkssj]').length;
			var shjssj = jQuery('input[name=shjssj]').length;
			
			for(var i=0;i<shkssj;i++){
				var kssj = jQuery('input[name=shkssj]')[i].value;
				var jssj = jQuery('input[name=shjssj]')[i].value;
				
				if(kssj != "" && jssj != "" && kssj > jssj){
					alertError("第"+(i+1)+"行审核开始时间不能晚于审核结束时间，请确认");
					return false;
				}
			}
			
			confirmInfo('您确定要保存吗?',function(type){
				if ('ok' == type){
					refreshForm('pjpy_ty_sjsz.do?method=sjszSave');
				}
			})
		}
		
		function showPlszWindow(){
			var checkbox = jQuery('input[name=primarykey_cbv]:checked');
			
			if (checkbox.length == 0){
				alertInfo('请勾选您要设置的记录');
			} else {
				tipsWindown("系统提示","id:plszDiv","350","300","true","","true","id");
			}
		}
		
		
		function plsz(){
			confirmInfo('您确定要批量设置选中的记录吗?',function(type){
				if ('ok' == type){
					var temp_sqkssj = jQuery('#temp_sqkssj').val();
					var temp_sqjssj = jQuery('#temp_sqjssj').val();
					var temp_sqkzkg = jQuery('input[type=radio][name=temp_sqkzkg]:checked').val();
					
					if(temp_sqkssj != "" && temp_sqjssj != "" && temp_sqkssj > temp_sqjssj){
						alertError("申请开始时间不能晚于申请结束时间，请确认");
						return false;
					}
					
					var temp_shkssj = jQuery('#temp_shkssj').val();
					var temp_shjssj = jQuery('#temp_shjssj').val();
					var temp_shkzkg = jQuery('input[type=radio][name=temp_shkzkg]:checked').val();
					
					if(temp_shkssj != "" && temp_shjssj != "" && temp_shkssj > temp_shjssj){
						alertError("审核开始时间不能晚于审核结束时间，请确认");
						return false;
					}
					
					var checkbox = jQuery('input[name=primarykey_cbv]:checked');
					
					for (var i = 0 ; i < checkbox.length ; i++){
						var tr = jQuery(checkbox[i]).parents('tr');
					
						jQuery('input[name=sqkssj]',tr).val(temp_sqkssj);
						jQuery('input[name=sqjssj]',tr).val(temp_sqjssj);
						jQuery('input[type=radio][value='+temp_sqkzkg+']',tr).eq(0).attr('checked',true);
						
						jQuery('input[name=shkssj]',tr).val(temp_shkssj);
						jQuery('input[name=shjssj]',tr).val(temp_shjssj);
						jQuery('input[type=radio][value='+temp_shkzkg+']',tr).eq(1).attr('checked',true);
					}
					closeWindown();
					alertInfo('设置成功,保存后将应用在申请审核流程中。');
				}
			})
		}
		//显示帮助层信息
		function showHelpDiv(){

			if($("div_help")){
				if($("div_help").style.display == "none"){
					$("div_help").style.display = "";
				}else{
					$("div_help").style.display = "none";
				}
			}
			
		}
	</script>
  </head>
  
  <body>
 	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置：</em><a>评奖评优-评奖综合设置-时间设置</a>
		</p>
		
		<!-- 在线帮助 -->
		<p class="help">
			<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
		</p>
		<!-- 在线帮助 end -->
		
		<!-- 相关功能 -->
		<p class="other" style="position:relative;">
			<a href="#" onclick="return false;" 
				onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
				style="display:block;height:30px;">相关功能</a>
		</p>
		<!-- 相关功能 end-->
			
	</div>
	
	<!-- 提示信息-->
	<div class="prompt">
		<h3>
			<span>评奖周期：</span>
		</h3>
		<p>
			评奖学年(${pjxtszModel.pjxn })&nbsp;&nbsp;
			评奖学期(${pjxtszModel.pjxqmc })&nbsp;&nbsp;
			评奖年度(${pjxtszModel.pjnd })&nbsp;&nbsp;
		</p>
	</div>
	<!-- 提示信息 end-->	
	
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.本模块用于设置评奖项目的申请和审核开关，如:当前时间在评奖"项目A"的申请开始时间与申请结束时间之间并且申请控制开关状态为"开"，则该项目可申请、否则不可申请。审核控制开关同理。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_rssz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function25.png" />
							<p>评奖人数设置</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_tjsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function56.png" />
							<p>评奖条件设置</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_jdsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function71.png" />
							<p>项目兼得设置</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_tzfwsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function24.png" />
							<p>项目调整范围设置</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->
		
		
	
	<html:form action="/pjpy_ty_sjsz" method="post">

			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_ccg"
									onclick="saveSjsz();return false;"
									id="btn_dr">保存设置</a>
							</li>
						
							<li>
								<a href="#" class="btn_sz"
									onclick='showPlszWindow();return false;'
									id="btn_sz">批量设置</a>
							</li>
						</ul>
					</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th width="80px">项目名称</th>
								<td colspan="4">
									<html:text property="xmmc"></html:text>
								</td>
								<td width="16%">
				                <div class="btn">
				                    <button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('pjpy_ty_sjsz.do?method=sjszManage&doType=query')">
										查 询
									</button>
				                  </div>
				                </td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>


			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue"></font>
						</logic:notEmpty> 
					</span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%" >
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<td>项目名称</td>
								<td>申请开始时间</td>
								<td>申请结束时间</td>
								<td>申请控制开关</td>
								<td>审核开始时间</td>
								<td>审核结束时间</td>
								<td>审核控制开关</td>
							</tr>
						</thead>
						<tbody id="dataTab">
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="r" indexId="i">
									<tr>
										<logic:iterate id="v" name="r" offset="0" length="1">
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv" value="${v }"/>
												<input type="hidden" id="cbv" name="pkValue" value="${v }"/>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="r" offset="1" length="1">
											<td>
												${v }
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="2" length="1">
											<td>
												<input type="text" name="sqkssj" id="sqkssj${i }" value="${v }" 
													   style="width:90px"
													   readonly="true" onclick="return showCalendar(this.id,'y-mm-dd')"
												/>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="3" length="1">
											<td>
												<input type="text" name="sqjssj" id="sqjssj${i }" value="${v }" 
														 style="width:90px"
													   readonly="true" onclick="return showCalendar(this.id,'y-mm-dd')"/>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="4" length="1">
											<td>
												<logic:equal value="1" name="v">
													<input type="radio"  name="sqkzkg${i }"  value="0"/>开
													<input type="radio"  name="sqkzkg${i }"  value="1" checked="true"/>关
												</logic:equal>
												<logic:notEqual value="1" name="v">
													<input type="radio"  name="sqkzkg${i }"  value="0" checked="true"/>开
													<input type="radio"  name="sqkzkg${i }"  value="1" />关
												</logic:notEqual>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="5" length="1">
											<td>
												<input type="text" name="shkssj" id="shkssj${i }" value="${v }" 
														 style="width:90px"
													   readonly="true" onclick="return showCalendar(this.id,'y-mm-dd')"
												/>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="6" length="1">
											<td>
												<input type="text" name="shjssj" id="shjssj${i }" value="${v }" 
													   style="width:90px"
													   readonly="true" onclick="return showCalendar(this.id,'y-mm-dd')"
												/>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="7" length="1">
											<td>
												<logic:equal value="1" name="v">
													<input type="radio"  name="shkzkg${i }"  value="0" />开
													<input type="radio"  name="shkzkg${i }"  value="1" checked="true"/>关
												</logic:equal>
												<logic:notEqual value="1" name="v">
													<input type="radio"  name="shkzkg${i }"  value="0" checked="true"/>开
													<input type="radio"  name="shkzkg${i }"  value="1" />关
												</logic:notEqual>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum")- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=pjpySjszForm"></jsp:include>
				<!--分页显示-->
			</div>
		</html:form>
		
		<div id="plszDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>批量设置</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									申请开始时间
								</th>
								<td>
									<input type="text" id="temp_sqkssj"
										   style="width:100px" readonly="true" 
										   onclick="return showCalendar(this.id,'y-mm-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									申请结束时间
								</th>
								<td>
									<input type="text" id="temp_sqjssj"
										   style="width:100px" readonly="true" 
										   onclick="return showCalendar(this.id,'y-mm-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									申请控制开关
								</th>
								<td>
									<input type="radio"  name="temp_sqkzkg"  value="0" checked="true"/>开
									<input type="radio"  name="temp_sqkzkg"  value="1" />关
								</td>
							</tr>
							<tr>
								<th>
									审核开始时间
								</th>
								<td>
									<input type="text" id="temp_shkssj"
										   style="width:100px" readonly="true" 
										   onclick="return showCalendar(this.id,'y-mm-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									审核结束时间
								</th>
								<td>
									<input type="text" id="temp_shjssj"
										   style="width:100px" readonly="true" 
										   onclick="return showCalendar(this.id,'y-mm-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									审核控制开关
								</th>
								<td>
									<input type="radio"  name="temp_shkzkg"  value="0" checked="true"/>开
									<input type="radio"  name="temp_shkzkg"  value="1" />关
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保 存" onclick="plsz()">
											确 认
										</button>
										<button type="button" name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		<logic:present name="message">
			<script>
				alertInfo("${message}");
			</script>
		</logic:present>
  </body>
</html>
