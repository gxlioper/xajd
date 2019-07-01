<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/mdqrDWR.js"></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript">
			function showStu() {
				if (null == curr_row) {
					alert('请选择一行');
				} else {
					var xh = curr_row.getElementsByTagName('input')[0].value;
					var url = '/xgxt/stu_info_details.do?xh='+xh;
					showTopWin(url,'820','600');
				}
			}
			
			function modi(url){
				if(curr_row != null){
					showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('请选择要修改的数据行！');
					return false;
				}
			}
			
			function getXm(){
				var xmdm=$("queryequals_xmdm").value;
				if($("xmlbdm")){
					var xmlbdm=$("xmlbdm").value;
					var yhqx=$("yhqx").value;
					var dm="";
					var mc="";
					var text="";
					mdqrDWR.getXmShList(xmlbdm,yhqx,function(data){
						for(i=0;i<data.length;i++){
							dm=""+data[i].dm;
							mc=""+data[i].mc;
							if(xmdm==dm){
								text+="<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  style='color:#0A63BF' onclick='changeSzzq();allNotEmpThenGo(\"/xgxt/mdqr.do?method=mdqrQr&gnmk=${gnmk}&doType=query&xmdm="+dm+"\")'><span class='ico'></span>"+mc+"</a></li>";
							}else{
								text+="<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  onclick='changeSzzq();allNotEmpThenGo(\"/xgxt/mdqr.do?method=mdqrQr&gnmk=${gnmk}&doType=query&xmdm="+dm+"\")'><span class='ico'></span>"+mc+"</a></li>";
							}
						}
						document.getElementById('xmxslb').innerHTML = text;
					});
				}
			}
		
			function sfxzxm(){
				if($("xmdm").value==""){
					alert("请先选择项目!");
					return false;
				}
				
				if($("xmlbdm").value==""){
					alert("请先选择项目类别!");
					return false;
				}
				return true;
			}
			
				function changeSzzq(){
				var xmdm=$("queryequals_xmdm").value;
				mdqrDWR.getSzzq(xmdm,function(data){
					if(data=="xn"){
						if ($("showXn")) $("showXn").style.display="";
						if ($("showXq")) $("showXq").style.display="none";
						if ($("showNd")) $("showNd").style.display="none";
					}else if(data=="xq"){
						if ($("showXn")) $("showXn").style.display="none";
						if ($("showXq")) $("showXq").style.display="";
						if ($("showNd")) $("showNd").style.display="none";
					}else if(data=="nd"){
						if ($("showXn")) $("showXn").style.display="none";
						if ($("showXq")) $("showXq").style.display="none";
						if ($("showNd")) $("showNd").style.display="";
					}else if(data=="wzq"){
						if ($("showXn")) $("showXn").style.display="none";
						if ($("showXq")) $("showXq").style.display="none";
						if ($("showNd")) $("showNd").style.display="none";
					}else if(data=="jyc"){
						if ($("showXn")) $("showXn").style.display="none";
						if ($("showXq")) $("showXq").style.display="none";
						if ($("showNd")) $("showNd").style.display="none";
					}
				})
			}
			
			function mdqrBcqx(mdqr){
				//是否选择项目
					var xmdm="";
					var url=""
					if($("xmlbdm").value==""){
						alert("请选择项目类别!");
						return false;
					}
					
					if($("queryequals_xmdm").value==""){
						
						alert("请选择项目");
						return false;
					}
					var len= document.getElementsByName("pkV").length;
					if(!len>0){
						alert("没有可确认的学生名单!");
						return false;
					}
					url="/xgxt/mdqr.do?method=mdqrQr&gnmk=${gnmk}&doType=mdqr&xmdm"+xmdm+"&mdqr="+mdqr;
					dataBatch(url);
			}
			
			function getXmxx(){
				allNotEmpThenGo('/xgxt/mdqr.do?method=mdqrQr&gnmk=${gnmk }&doType=query');
			}
			
			function checkXm(){
				var xmdm = $("queryequals_xmdm").value;
				if(xmdm!="" && xmdm!=null){
					allNotEmpThenGo('/xgxt/mdqr.do?method=mdqrQr&gnmk=${gnmk }&doType=query');
				}else{
					alert(" 请在左侧菜单选择项目进行查询");
				}
			}
		</script>
	</head>
	<body onload="getXm();changeSzzq()">

		<html:form action="/mdqr" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType}" />
			<input type="hidden" name="realTable" id="realTable"
				value="mdqr_xmnrb" />
			<input type="hidden" name="tabName" id="tabName" value="mdqr_xmnrb" />
			<input type="hidden" name="viewName" id="viewName"
				value="view_mdqr_xmnrb" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="yhqx" id="yhqx" value="${yhqx}" />
			<input type="hidden" name="qrqx" id="qrqx" />
			<input type="hidden" name="queryequals_xmdm" id="queryequals_xmdm"
				value="${xmdm }" />
			<input type="hidden" name="rsNum" id="rsNum" value="${rsNum }" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
					<div class="buttonbox">
						<ul>

							<li>
								<a href="#" onclick="mdqrBcqx('checked')" class="btn_shtg">
									确认 </a>
							</li>
							<li>
								<a href="#" onclick="mdqrBcqx('')" class="btn_shbtg"> 取消确认 </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a>
							</li>

						</ul>
					</div>
				</logic:equal>

				<div class="main_box">
					<div class="mid_box">
						<div style="float:left;">
							<div class="listbox">
								<div class="menulist"
									style="width:158px;height:26px;padding-top: 5px"">
									项目类别:
									<html:select property="queryequals_xmlbdm" styleId="xmlbdm"
										style="width:80px" onchange="getXm();">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="dm"
											labelProperty="mc" />
									</html:select>
									<div class="CNLTreeMenu1" id="CNLTreeMenu1"
										style="height: 440px;">
										<ul id="xmxslb" class="CNLTreeMenu1" id="CNLTreeMenu1"
											style="height: 440px;">
										</ul>
									</div>
								</div>
								<script type="text/javascript">
									<!--
									var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");
									//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif","menutitle");
			 						-->
								</script>
							</div>
						</div>
						<div style="float:right;width:630px;">
							<div class="searchtab">
								<table width="100%" border="0">
									<tfoot>
										<tr>
											<td colspan="6">
												<div class="btn">
													<button class="btn_cx" id="search_go" onclick="checkXm()">
														查 询
													</button>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
														重 置
													</button>
												</div>
											</td>
										</tr>
									</tfoot>

									<tbody>
										<tr>
											<th>
												学号
											</th>
											<td>
												<html:text property="querylike_xh" style="width:100px"
													styleId="xh" />
											</td>
											<th>
												姓名
											</th>
											<td>
												<html:text property="querylike_xm" style="width:100px"
													styleId="xm" />
											</td>
											<th>
												年级
											</th>
											<td>
												<html:select property="queryequals_nj" styleId="nj"
													style="width:100px">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th>
												<bean:message key="lable.xb" />
											</th>
											<td>
												<logic:equal name="userType" value="xy">
													<html:select property="xydm" styleId="xy"
														style="width:100px" onchange="initZyList();initBjList();"
														disabled="true" value="${userDep }">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
													<html:hidden property="queryequals_xydm" value="${userDep}" />
												</logic:equal>
												<logic:notEqual name="userType" value="xy">
													<html:select property="queryequals_xydm" styleId="xy"
														style="width:100px" onchange="initZyList();initBjList();">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
												</logic:notEqual>
											</td>
											<th>
												专业
											</th>
											<td>
												<html:select property="queryequals_zydm" styleId="zy"
													onchange="initBjList();" style="width:100px">
													<html:option value=""></html:option>
													<html:options collection="zyList" property="zydm"
														labelProperty="zymc" />
												</html:select>
											</td>
											<th>
												班级
											</th>
											<td>
												<html:select property="queryequals_bjdm" styleId="bj"
													style="width:100px">
													<html:option value=""></html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
											</td>
										</tr>
										<tr>

											<th>
												是否确认名单
											</th>
											<td>
												<html:select property="yesNo" styleId="yesNo"
													style="width:100px" onchange="getXm()">
													<html:option value=""></html:option>
													<html:option value="是">是</html:option>
													<html:option value="否">否</html:option>
												</html:select>
											</td>
											<th>
												设置时间
											</th>
											<td colspan="2">
												<html:text property="kssj" styleId="kssj" style="width:90px"
													onclick="return showCalendar('kssj','y-mm-dd');"
													onblur="dateFormatChg(this)" />
												--
												<html:text property="jssj" styleId="jssj"
													onclick="return showCalendar('jssj','y-mm-dd');"
													style="width:90px" onblur="dateFormatChg(this)" />
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="formbox">
								<h3 class="datetitle_01">
									<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
											<font id="showXn" color="blue" style="display:none">
												提示：单击表头可以排序 确认学年:${xn } </font>
											<font id="showXq" color="blue" style="display:none">
												提示：单击表头可以排序 确认学年:${xn } 确认学期:${xq } </font>
											<font id="showNd" color="blue" style="display:none">
												提示：单击表头可以排序 确认年度:${nd } </font>
										</logic:notEmpty> </span>
								</h3>
								<div style="overflow-x:auto;width:630px;">
									<table summary="" id="rsTable" class="dateline" width="100%">
										<thead>
											<tr align="center" style="cursor:hand">
												<td>
													<!-- 假的CHECKBOX 防止全选被隐藏-->
													<input type="checkbox" name="jiade" value="all" onclick="" />

													<input type="checkbox" name="all" value="all"
														onclick="chec()" />

												</td>
												<logic:iterate id="tit" name="topTr" offset="1"
													indexId="index">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</tr>
										</thead>
										<tbody>
											<logic:iterate name="rs" id="s">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" name="showXh" id="showXh"
																value="<logic:iterate id="v" name="s" offset="4" length="1">${v}</logic:iterate>" />
															<input type="hidden" name="save_xh" id="save_xh"
																value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
															<input type="hidden" name="xysh" id="xysh"
																value="<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>" />
															<input type="hidden" name="xxsh" id="xxsh"
																value="<logic:iterate id="v" name="s" offset="3" length="1">${v}</logic:iterate>" />
															<input type="hidden" name="sbsjArr" id="sbsjArr"
																value="<logic:iterate id="v" name="s" offset="7" length="1">${v}</logic:iterate>" />
															<input type="checkbox" name="primarykey_cbv" id="pkV"
																value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="4" length="1">
														<td nowrap>
															<a href="javascript:showStu();" class="pointer"
																style="color:#0A63BF">${v }</a>
														</td>
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="5">
														<td nowrap>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
											<logic:lessEqual value="11" name="rsNum">
												<%
															ArrayList list = ((ArrayList) request.getAttribute("rs"));
															int rsNum = 0;
															if (list != null) {
																rsNum = list.size();
															}
															int pageSize = (Integer) request.getAttribute("pageSize");
															for (int i = 0; i < (pageSize - rsNum); i++) {
												%>
												<tr>
													<logic:iterate id="tit" name="topTr" offset="0" >
														<td>
															&nbsp;
														</td>
													</logic:iterate>
												</tr>
												<%
												}
												%>
											</logic:lessEqual>
										</tbody>
									</table>
								</div>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=mdqrForm"></jsp:include>
								<script type="text/javascript">
									$('choose').className="hide";
									</script>
							</div>
							
						</div>
						<logic:present name="result">
							<logic:equal value="true" name="result">
								<script>
						alert('操作成功！');
					</script>
							</logic:equal>
							<logic:notEqual value="true" name="result">
								<script>
						alert('操作失败！');
					</script>
							</logic:notEqual>
						</logic:present>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
