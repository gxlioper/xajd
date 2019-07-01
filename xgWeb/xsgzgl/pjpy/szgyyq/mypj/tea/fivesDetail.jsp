<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//刷新页面
		function reflashFivesDetail(){
			
			var xh = $("xh").value;
			var url = "szgyyq_mypj_tea.do?method=fivesDetail";
				url+="&xh="+xh;
				
			refreshForm(url)
		}
		
		//增加5S分
		function addFives(){

			var div_id = "div_fives";
			
			if($(div_id)){
				
				var add_num = parseInt($("add_num").value)+1;
				
				var divHtml = $(div_id).innerHTML;
					divHtml+="<table class=\"dateline\" style=\"width: 100%\"\">";
					divHtml+="<tbody>";
					divHtml+="<tr>";
					divHtml+="<td align=\"left\" style=\"width:5%\"><input type='checkbox' name=\"checkVal\" value=\"no\"/></td>";
					divHtml+="<td align=\"left\" style=\"width:25%\"><select name=\"fzxm\">"+$("sel_fzxm").innerHTML+"</select></td>";
					divHtml+="<td align=\"left\" style=\"width:10%\"><select name=\"jjf\">"+$("sel_jjf").innerHTML+"</select></td>";
					divHtml+="<td align=\"left\" style=\"width:10%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+add_num+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
					divHtml+="<td align=\"left\" style=\"width:10%\"><input type=\"text\" name=\"jfrq\" id=\"jfrq_"+add_num+"\" style=\"width:60px\" onclick=\"return showCalendar('jfrq_"+add_num+"','ymmdd');\" readOnly=\"true\" /></td>";
					divHtml+="<td align=\"left\" style=\"width:20%\"><select name=\"yy\" onchange='otheryy(this);'>"+$("sel_yy").innerHTML+"</select><input type='hidden' name='jfyy' value=''/></td>";
					divHtml+="<td align=\"left\" style=\"width:10%\">0</td>";
					divHtml+="<td align=\"left\" style=\"width:10%\">0</td>";
					divHtml+="</tr>";
					divHtml+="</tbody>";
					divHtml+="</table>";
					
				$(div_id).innerHTML = "";
				$(div_id).innerHTML = divHtml;
				
				$("add_num").value = add_num;
			}
		}
		
		//保存5S分
		function saveFives(){
			var flag = true;
		
			var sqf_num = jQuery("input[name=sqf]").length;
			var jfrq_num = jQuery("input[name=jfrq]").length;
			
			if(flag){
				for(var i=0;i<sqf_num;i++){
					var obj = jQuery("input[name=sqf]")[i];
					if(obj.value == ""){
						alertError("分值不能为空，请确认");
						flag = false;
					}
				}
			}
			
			if(flag){
				for(var i=0;i<jfrq_num;i++){
					var obj = jQuery("input[name=jfrq]")[i];
					if(obj.value == ""){
						alertError("日期不能为空，请确认");
						flag = false;
					}
				}
			}
			
			if(flag){
			
				var url="szgyyq_mypj_tea.do?method=saveFives";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//分值项目
				var i = 0; 
				var fzxm = new Array();   	
				
				var fzxm_num = document.getElementsByName("fzxm").length;
				for(i=0;i<fzxm_num;i++){
					fzxm[i]=document.getElementsByName("fzxm")[i].value;
				}

				//ID
				i = 0;
				var id = new Array();   				  
				jQuery("input[name=checkVal]").each(function(){id[i] = jQuery(this).val();i++;});
				
				//加减分
				i = 0;
				var jjf = new Array();   				  
				jQuery("select[name=jjf]").each(function(){jjf[i] = escape(jQuery(this).val());i++;});
				
				//申请分
				i = 0;
				var sqf = new Array();   				  
				jQuery("input[name=sqf]").each(function(){sqf[i] = jQuery(this).val();i++;});
				
				//日期
				i = 0;
				var jfrq = new Array();   				  
				jQuery("input[name=jfrq]").each(function(){jfrq[i] = jQuery(this).val();i++;});
				
				//原因
				i = 0;
				var yy = new Array();   				  
				jQuery("select[name=yy]").each(function(){
					if(id[i] == "no"){
						yy[i] = jQuery(this).val();
					}else{
						yy[i] = " ";
					}
					i++;
				});

				//加分原因
				i = 0;
				var jfyy = new Array();   				  
				jQuery("input[name=jfyy]").each(function(){
					if(jQuery(this).val() != ""){
						jfyy[i] = escape(jQuery(this).val());
					}else{
						jfyy[i] = " ";
					}
					i++;
				});

				//学号
				var xh = $("xh").value;

				//参数
			 	var parameter = {
					"xh":xh,
					"id":id.join("!!@@!!"),
					"fzxm":fzxm.join("!!@@!!"),
					"jjf":jjf.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!"),
					"jfrq":jfrq.join("!!@@!!"),
					"yy":yy.join("!!@@!!"),
					"jfyy":jfyy.join("!!@@!!")
				};

				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
		}
		
		//删除5S分
		function delFives(){
		
			var flag = true;
		
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 0){
				alertError("请勾选希望删除的记录");
				return false;
			}
			
			if(flag){
			
				var url="szgyyq_mypj_tea.do?method=delFives";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//ID
				var i = 0;
				var id = new Array();   				  
				jQuery("input[name=checkVal]:checked").each(function(){id[i] = jQuery(this).val();i++;});
				
				//参数
			 	var parameter = {
					"id":id.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
		}
		
		//执行成功
		function dcSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			alertInfo(result,function(tag){if(tag == "ok"){reflashFivesDetail();}});
		}
		
		//显示修改分值
		function showEditFz(obj){
			
			var id = obj.value;
			var sqf_id = "sqf_"+id;
			var p_id = "p_"+id;
			
			if(obj.checked){
				$(sqf_id).style.display = "";
				$(p_id).style.display = "none";
			}else{
				$(sqf_id).style.display = "none";
				$(sqf_id).value = $(p_id).innerHTML;
				$(p_id).style.display = "";
			}
		}
		//其他原因时需要填写具体原因，非必填
		var curr_yy_obj;
		function otheryy(obj){
			if(obj.value=="other"){
				curr_yy_obj=obj;
				tipsWindown("其他原因","id:tempDiv","350","200","true","","true","id");
			}else{
				curr_yy_obj=null;
				obj.nextSibling.value="";
			}
		}
		//将其他原因保存在页面上
		function temp_save_other_yy(){
			curr_yy_obj.nextSibling.value=$("other_yy").value;
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>综合素质养成课 - 我的工作 - 5S分维护</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc }</font>学期 展开的。</br>
				2.可以通过<font color="blue">增加</font>和<font color="blue">删除</font>来维护5S分。</br>
				3.如果原因列表中没有您想要的原因，请选择<font color="blue">其他</font>。</br>
				4.如果您的原因为其他的话，点击后可查看<font color="blue">详细</font>。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/szgyyq_mypj_tea" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 分值项目 -->
			<select id="sel_fzxm" style="display: none">
				<option value="grszf">个人素质分</option>
				<option value="jsssf">教室与宿舍5S分</option>
				<option value="ktf">课堂5S分</option>
				<option value="cxf">诚信分</option>
				<option value="qtf">其他分</option>
			</select>
			<!-- 加减分 -->
			<select id="sel_jjf" style="display: none">
				<option value="加分">加分</option>
				<option value="减分">减分</option>
			</select>
			<!-- 分值项目 -->
			<select id="sel_yy" style="display: none;">
				<logic:notEmpty name="yyList">
					<logic:iterate name="yyList" id="yyMap">
						<option value="${yyMap.dm }">${yyMap.mc }</option>
					</logic:iterate>
						<option value="other">其他</option>
				</logic:notEmpty>
			</select>
			<!-- 增加数量  -->
			<input type="hidden" id="add_num" value="0" />
			
			<input type="hidden" id="xh" name="xh" value="${xh }" />
			<!-- 刷新  -->
			<button type="button" id="btn_sx" onclick="reflashFivesDetail()" style="display:none">
				刷新
			</button>
			<!-- 隐藏域 end-->
			
			<!-- 学生基本信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>学生基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">学号</th>
						<td width="30%">
							<input type="hidden" name="xh" value="${stuInfo.xh }" />
							${stuInfo.xh }
						</td>
						<th width="20%">姓名</th>
						<td width="30%">
							${stuInfo.xm }
						</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							${stuInfo.xb }
						</td>
						<th>班级</th>
						<td>
							${stuInfo.bjmc }
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div>
								<button type="button" onclick="addFives()" id="btn_add">增加</button>
								<button type="button" onclick="delFives()">删除</button>
							</div>
							<br/>
							<div style="height:300px;overflow-x:auto;overflow-y:auto;">
								<table class="dateline" width="100%">
									<!-- 标题 -->
							    	<thead>
										<tr>
											<td style="width: 5%">
												<input type="checkbox" id="selall"name="selall" onclick="selAll()" />
											</td>
											<td style="width: 25%">
												分值项目
											</td>
											<td style="width: 10%">
												加减分
											</td>
											<td style="width: 10%">
												分值
											</td>
											<td style="width: 10%">
												日期
											</td>
											<td style="width: 20%">
												原因
											</td>
											<td style="width: 10%">
												<bean:message key="lable.xb" />审核
											</td>
											<td style="width: 10%">
												学校审核
											</td>
							      		</tr>
									</thead>
									<!-- 标题 end-->
								
									<logic:iterate name="infoList" id="info">
										<tr>
											<!-- 可修改 -->
											<logic:equal name="info" property="shzt" value="yes">
												<td style="width: 5%">
													<input type="checkbox" name="checkVal" value="${info.id }" onclick="showEditFz(this);"/>
												</td>
												<td style="width: 25%">
													${info.fzxm }
													<input type="hidden" name="fzxm" value="123"/>
<%--													<select name="fzxm" style="display: none">--%>
<%--														<option value="${info.fzxm }">${info.fzxm }</option>--%>
<%--													</select>--%>
												</td>
												<td style="width: 10%">
													${info.jjf }
													<select name="jjf" style="display: none">
														<option value="${info.jjf }">${info.jjf }</option>
													</select>
												</td>
												<td style="width: 10%">
													<p id="p_${info.id }">${info.sqf }</p>
													<input type="text" name="sqf" 
														id="sqf_${info.id }" 
														value="${info.sqf }" 
														onkeydown="return onlyNum(this,3)" 
														onmousedown="return onlyNum(this,3)" 
														maxlength="3" 
														style="width : 30px;ime-mode:disabled;display: none"/>
												</td>
												<td style="width: 10%">
													${info.jfrq }
													<input type="text" name="jfrq" value="${info.jfrq }" style="display: none"/>
												</td>
												<td style="width: 20%">
													<logic:notEmpty name="info" property="yy">
														${info.yy }
													</logic:notEmpty>
													<logic:empty name="info" property="yy">
														<a href="#" onclick="tipsWindown('其他原因','id:tempDiv','350','200','true','','true','id');$('other_yy').value='${info.jfyy }';$('btn_bc').style.display='none';return false;"><font color="blue">其他</font></a>
													</logic:empty>
													<input type='hidden' name='jfyy' id="jfyy_${info.id }" value='${info.jfyy }'/>
													<select name="yy" style="display:none">
														<option value="${info.yy }">${info.yy }</option>
													</select>
												</td>
												<td style="width: 10%">
													${info.xyshf }
												</td>
												<td style="width: 10%">
													${info.xxshf }
												</td>
											</logic:equal>
											<!-- 不可修改 -->
											<logic:equal name="info" property="shzt" value="no">
												<td style="width: 5%">
													<input type="checkbox" disabled="disabled"/>
												</td>
												<td style="width: 25%">
													${info.fzxm }
												</td>
												<td style="width: 10%">
													${info.jjf }
												</td>
												<td style="width: 10%">
													${info.sqf }
												</td>
												<td style="width: 10%">
													${info.jfrq }
												</td>
												<td style="width: 20%">
													${info.yy }
												</td>
												<td style="width: 10%">
													${info.xyshf }
												</td>
												<td style="width: 10%">
													${info.xxshf }
												</td>
											</logic:equal>
										</tr>
									</logic:iterate>
								</table>
								<div id="div_fives">

								</div>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- 保存 -->
								<button type="button" onclick="saveFives();">
									<bean:message key="lable.btn_bc_space" />
								</button>
								<!-- 关闭-->
								<button type="button" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 学生基本信息 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">请填写原因</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_bz">
								<th style="width:30%">
									原因<br />
									<font color="red"><限250字></font>
								</th>
								<td>
									<textarea id="other_yy" rows="5"  cols="30"
									style="word-break:break-all;width:100%" onblur="chLeng(this,250)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="保存" id="btn_bc"  onclick="temp_save_other_yy();closeWindown()">
											确 定 
										</button>
										<button type="button" name="取消"  onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			
		</html:form>
	</body>
</html>