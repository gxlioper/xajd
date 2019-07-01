<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/pjpyCommService.js'></script>
		<script type="text/javascript" src="js/pjpy/comm/pjpy/pjxmList.js"></script>
		<script type="text/javascript">
			function getPjlc(obj){
				var xmdm = obj.value;
				DWRUtil.removeAllRows('tbody');
				if ('' != xmdm){
					///从后台传过来的lcmcValue字符串处理成数组形式
					var lcmcValue = $('lcmcValue').value.replace('[','').replace(']','').split(',');
					$('shzt').disabled=true;
					pjpyCommService.getLcmcByXmdm(xmdm,lcmcValue,function(data){
						
						var i=0,n=0,cells=[];
						while (i < data.length){
							cells[n++]="&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].key;
							cells[n++]="<select name='"+data[i].key+"' style='width:160px' value='"+data[i].value+"'>"+$('shzt').innerHTML+"</select>";
							i++;
						}
						DWRUtil.addRows('tbody',[''],cells,{escapeHtml:false});
						
						for (var i = 0 ; i < data.length ; i++){
							jQuery("select[name="+data[i].key+"]").attr("value",data[i].value);
						}
					});
				} else {
					$('shzt').disabled=false;
				}
			}
			//打印报表
			function printPj(url){
				var num = document.getElementsByName("primarykey_cbv").length;
				var flag = false;
				var n = 0;
				var jxjName = "";
				var xh = "";
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("primarykey_cbv")[i];
					if(obj.checked == true){
						flag = true;
						var children = obj.parentNode.childNodes;
						jxjName = children[2].value;
						xh = children[4].value;
						n++;
					}
				}
				
				if(flag){
					if(n==1){
						 document.forms[0].action = url+"&jxjName="+jxjName+"&xh="+xh;
						 document.forms[0].target = "_blank";
						 document.forms[0].submit();
						 document.forms[0].target = "_self";
					}else{
						alert("请勾选一条记录！");
					}
				}else{
					alert("请勾选一条需要打印记录！");
				}	
		    }
		    
		    //前往项目上报
			function goXmsb(){
				var url = "pjpyXmsb.do?method=xmsbManage";
					url+= "&xmdm="+$("xmdm").value;
					url+= "&bjdm="+$("bjdm").value;
				
				showWaitingDiv("30000");
				
				refreshForm(url);
			}
			
			//检测可否删除
			function checkDel(){
				jQuery.ajaxSetup({async:false});
				var num = jQuery('input[name=primarykey_cbv]').length;
				var pk = new Array();
				var n = 0;
				for(var i=0;i<num;i++){
					var obj = jQuery('input[name=primarykey_cbv]')[i];
					if(obj.checked){
						pk[n]=escape(obj.value);
						n++;
					}
				}
					
				//其他数据
			 	var parameter = {
					"pk":pk.join("!!@@!!")
				};
				
				var url="pjpyJgcx.do?method=checkDel";
				
				showWaitingDiv("10000");
				jQuery.post(url,parameter,function(result){
					if(result != ""){
						alertInfo(result);
					}else{
						batchData('primarykey_cbv','pjpyJgcx.do?method=pjpyQuery&doType=del','del');
					}
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				});
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>

	<body onload="getPjlc($('xmdm'));xyDisabled('xy');hiddenTr($('moreTerm'));">
		<html:form action="/pjpyJgcx" method="post">
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" name="userType" value="${userType }" />
			<input type="hidden" name="userDep" value="${userDep }" />
		
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="lcmcValue" value="${lcmcValue }" />
			<input type="hidden" id="realTable" name="realTable" value="xg_pjpy_pjxmsqb" />
			<input type="hidden" id="tableName" name="tableName" value="xg_view_pjpy_jgcx" />

			<button type="button" id="forward" onclick="goXmsb()" style="display: none">跳转</button>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 我的评奖 - 结果查询</a>
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
			
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.点击<font color="blue">更过条件</font>，可以展示其他隐藏的条件。
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
				    	<a href="#" onclick="goOtherGnmk('pjpy_mypj.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function79.png" />
							<p>我的评奖</p>
						</a>   	
					</div>
					
					<!-- 学生用户 -->
					<logic:equal name="userType" value="stu">
					    <div class="liucheng_font floatleft">
					    	<a href="#" onclick="goOtherGnmk('pjpy_pjlc_xssq.do');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>项目申请</p>
							</a>   	
						</div>
					</logic:equal>
					<!-- 学生用户 end-->
					
					<!-- 非学生用户 -->
					<logic:notEqual name="userType" value="stu">
					    <div class="liucheng_font floatleft">
					    	<a href="#" onclick="goOtherGnmk('pjpy_pjlc_xssq.do');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>学生申请</p>
							</a>   	
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="showTopWin('/xgxt/pjpyXmsb.do?method=sbfwChoose',600,550);return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
								<p>老师上报</p>
							</a>
						</div>
					</logic:notEqual>
					<!-- 非学生用户 end-->
			
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->
			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<logic:notEqual name="userType" value="stu">
								<li>
									<a href="#" class="btn_sc"
										onclick="checkDel();return false;"
										id="btn_qx">删除</a>
								</li>
								<%--<li>
									<a href="#" class="btn_dr"
										onclick="impAndChkData()"
										id="btn_dr">导入</a>
								</li>
								--%><li>
									<a href="#" class="btn_dc"
										onclick="expData('pjpyJgcx.do?method=pjpyQuery&doType=exp')"
										id="btn_dc">导出</a>
								</li>
							</logic:notEqual>
						    <li>
								<a href="#" class="btn_dy"
									onclick="printPj('pjpyCommPrint.do?method=printJsp')"
									id="btn_dy">打印</a>
							</li>
							<%--<li>
								<a href="#"
									id="btn_dc"
									onclick="configureExportData();return false;"
									class="btn_dc"> 导出 </a>
							</li>
							<li>
								<a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">导出字段确认</a>
							</li>
						--%></ul>
					</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>更多条件</strong>
										</label>
									</div>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('pjpyJgcx.do?method=pjpyQuery&doType=query')">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="width:160px" onmouseover=""
										styleId="nj" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select>
								</td>
								<th>学号</th>
								<td>
									<logic:equal value="stu" name="userType">
										<html:text property="xh" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										<html:text property="xh"></html:text>
									</logic:notEqual>
								</td>
								<th>姓名</th>
								<td>
									<html:text property="xm"></html:text>
								</td>
								
							</tr>
							
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:160px" onmouseover=""
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>专业</th>
								<td>
									<html:select property="zydm" onchange="initBjList()" onmouseover=""
												 styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>班级</th>
								<td>
									<html:select property="bjdm" styleId="bj" onmouseover="" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									项目名称
								</th>
								<td>
									<html:select property="xmdm" style="width:160px" styleId="xmdm" onchange="getPjlc(this);">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
									</html:select>
								</td>
								<th>申请时间</th>
								<td>
									<html:text property="sqkssj" styleId="sqkssj" style="width:100px"
											onclick="return showCalendar(this.id,'y-MM-dd');"
											readonly="true"></html:text>
									-
									<html:text property="sqjssj" styleId="sqjssj" style="width:100px"
											onclick="return showCalendar(this.id,'y-MM-dd');"
											readonly="true"></html:text>
								</td>
								<th>审核状态</th>
								<td>
									<html:select property="shzt" style="width:160px" styleId="shzt">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
							
							<%--<tr style="display:none">
								<th>
									评奖学年
								</th>
								<td>
									<html:select property="pjxn" style="width:160px" styleId="pjxn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									评奖学期
								</th>
								<td>
									<html:select property="pjxq" style="width:160px" styleId="pjxq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									评奖年度
								</th>
								<td>
									<html:select property="pjnd" style="width:160px" styleId="pjnd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							--%><%--<tr style="display:none">
								<th>
									项目类型
								</th>
								<td>
									<html:select property="xmlx" style="width:160px" styleId="xmlx">
										<html:option value=""></html:option>
										<html:options collection="xmlxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									项目性质
								</th>
								<td>
									<html:select property="xmxz" style="width:160px" styleId="xmxz">
										<html:option value=""></html:option>
										<html:options collection="xmxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									项目范围
								</th>
								<td>
									<html:select property="xmfw" style="width:160px" styleId="xmfw">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
						--%></tbody>
						<tbody id="tbody">
						
						</tbody>
					</table>
				</div>
			</div> 

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)">
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
											<logic:iterate id="v" name="s" offset="4" length="1">
												<input type="hidden" value="${v }" />											
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="${v }" />											
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<logic:equal name="v" value="未审核">
													<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="v" value="通过">
													<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="v" value="不通过">
													<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="v" value="退回">
													<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="v" value="需重审">
													<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
												</logic:equal>
		
												<logic:notEqual name="v" value="退回">
												<logic:notEqual name="v" value="需重审">
												<logic:notEqual name="v" value="不通过">
												<logic:notEqual name="v" value="通过">
												<logic:notEqual name="v" value="未审核">
													${v }
												</logic:notEqual>
												</logic:notEqual>
												</logic:notEqual>
												</logic:notEqual>
												</logic:notEqual>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=pjpyJgcxForm"></jsp:include>
			</div>
			<logic:present name="message">
				<script>
					alert("${message}");
				</script>
			</logic:present>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
