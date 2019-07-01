<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
		//统计报表
		function tjBb(){
			
			var xmdm = $("xmdm").value;
			var xxdm = $("xxdm").value;
			var xydm = $('xy').value;
			var xn = $('xn').value;
			var xq = $('xq').value;
			var url = "/xgxt/commXszz.do?method=tjbb&xmdm="+xmdm;
			
			if(xmdm == ""){
				alert("请选择需要统计的项目");
				return false;
			} else if ("1004" ==xmdm) {//绿色通道
				if (""==xydm){
					alert("请选择院系！");
					return false;
				}
				url += "&xydm="+xydm;
			} else if ("1001" ==xmdm){//国家励志奖学金
				if (""==xn){
					alert("请选择学年！");
					return false;
				}
				url += "&xn="+xn;
			} else if ("1003"==xmdm){//学生缓交学费
				
			} else if ("1002"==xmdm){//国家助学金
				if (""==xn){
					alert("请选择学年！");
					return false;
				}
				if (""==xq){
					alert("请选择学期！");
					return false;
				}
				url += "&xn="+xn+"&xq="+xq;
			} else if(xmdm == "5002"){//困难生
				if (""==xydm){
					alert("请选择院系！");
					return false;
				}
				url += "&xydm="+xydm;
			} else {
				alert("不好意思，这个统计表没有做！\n已做报表（绿色通道、国家励志奖学金、学生缓交学费、国家助学金、困难生）");
				return false;
			}
			
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			
		}
		
function showExpDiv(divId,isbackDiv,divColor){
	if($('##floatDiv##')==null){//显示操作区  
		var floatDiv = document.createElement('div');
		floatDiv.id = "##floatDiv##";
		floatDiv.style.position = "absolute";
		floatDiv.style.width = "600px";
		floatDiv.style.height = "400px";
		floatDiv.style.backgroundColor = "#FFFFFF";
		
		var x_pixel = 100;
		var y_pixel = 25;
		floatDiv.style.left = "100px";
		floatDiv.style.top = "100px";
		floatDiv.style.zIndex  = 1001;
		
		
		floatDiv.innerHTML = document.getElementById(divId).innerHTML;
		document.getElementById(divId).innerHTML = "";
		document.body.appendChild(floatDiv);
	}else{
		$('##floatDiv##').style.display = "block";
	}
	
	if($('##backDiv##')==null && isbackDiv ==true){//显示背景  
		var backDiv = document.createElement('div');
		backDiv.id = "##backDiv##";
		backDiv.style.backgroundColor = "Black";
		backDiv.style.filter = "alpha(opacity=70)";
		backDiv.style.MozOpacity = "0.70";
		backDiv.style.position = "absolute";
		backDiv.style.left = "0px";
		backDiv.style.top = "0px";
		backDiv.style.width = Math.max(document.body.scrollWidth, document.body.clientWidth) +"px";
		backDiv.style.height = Math.max(document.body.scrollHeight, document.body.clientHeight)+"px";
		document.body.appendChild(backDiv);
		$('##backDiv##').style.xIndex = 1000;
	}else if(isbackDiv ==true){
		$('##backDiv##').style.display = "block";
	}  
  	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		var obj = document.getElementsByTagName("select")[j];
		var id = obj.id;
		var arr = id.split("_");
		var splitName = arr[0];
		
		if(splitName != "select"){
			obj.style.display = "none";
		}
	}
	
}
 
function hiddenExpDiv(){

	if($('##backDiv##')!=null){ 
		$('##backDiv##').style.display = "none";
    }         
    
    if($("##floatDiv##")){ 
		$("##floatDiv##").style.display = "none";
    }
    
	i = document.getElementsByTagName("select").length;
	
	for (j = 0; j < i; j++) {
		var obj = document.getElementsByTagName("select")[j];
		var id = obj.id;
		if($(id)){
			obj.style.display = "";
		}
	} 
}

	function getXm(){
			var text="";
			var xmdm=$("xmdm").value;
			if($("xmlb")){
				var tableName = "xszz_zzxmb"; 
				var dm = "xmdm"; 
				var mc = "xmmc";
				var msg="";
				var pk = "xmlb";
				var pkValue = $("xmlb").value;
				var id = "xmdm";
					
				if(pkValue == ""){
					pk = "";
				}
				getXszzInfo.getXszzxmList(tableName, dm, mc, msg, pk, pkValue,function(data){
					for(i=0;i<data.length;i++){
							dm=""+data[i].dm;
							mc=""+data[i].mc;
							if(xmdm==dm){
								text+="<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  style='color:#0A63BF'  onclick=\"setXmdm('"+dm+"');checkTimes('"+dm+"');changeWin();\"><span class='ico'></span>"+mc+"</a></li>";
							}else{
								text+="<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  onclick=\"setXmdm('"+dm+"');checkTimes('"+dm+"');changeWin();\"><span class='ico'></span>"+mc+"</a></li>";
							}
							
							
					}
					document.getElementById('xmxslb').innerHTML = text;
				});
			}
		}
		
		
		function checkTimes(dm){
			allNotEmpThenGo('/xgxt/commXszz.do?method=jgcxManage&xmdm='+dm);
		}
		
		function setXmdm(dm){
			$("xmdm").value=""+dm;
		}
		
		function loadXmList(obj){
			var xmlb = jQuery(obj).val();
			if (xmlb != ''){
				jQuery('li[name='+xmlb+']',jQuery('#xmxslb')).attr('style','display:');
				jQuery('li[name!='+xmlb+']',jQuery('#xmxslb')).attr('style','display:none');
			} else {
				jQuery('li',jQuery('#xmxslb')).attr('style','display:');
			}
		}
		
	
		jQuery(function(){
			
			
			changeWin();
		});
		
		function changeWin() {
			var left=parent.document.getElementById("left");
			var right=parent.document.getElementById("right");
			
			if (left.className == "hide") {
<%--				left.className = "hide";--%>
<%--				right.style.width = "100%";--%>
<%--				if (jQuery('div.rightframe04')){--%>
<%--					jQuery('div.rightframe04').attr('class','rightframe04_hidden');--%>
<%--				}--%>
<%--			} --%>
<%--			else {--%>
				
				right.style.width = "100%";
				
				if (jQuery(document).find('div.rightframe04')){
					jQuery(document).find('div.rightframe04').attr('class','rightframe04_hidden');
				}
			}
		}
		
		
		</script>
		<style type="css">
.text-overflow {
	display: block; /*内联对象需加*/
	width: 16em;
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
}
		
		</style>
	</head>

	<body onload="changeWin();getTjsz();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXszz">
			<!-- 隐藏域 -->
			<html:hidden property="iskns" styleId="iskns" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xmjb" value="${xmjb }" />
			<input type="hidden" id="xmdm" name="xmdm" value="${xmdm}" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }" />
			<input type="hidden" id="widthType" name="widthType"
				value="${widthType}" />
			<%
					String widthType = request.getAttribute("widthType").toString();
					String width = "";
					if ("dbsx".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					} else if ("kjfs".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					}
			%>
			<!-- 隐藏域 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" onclick="showAddJgJsp();return false;" class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="showOpenInfo('/xgxt/commXszz.do?method=jgcxUpdate&iskns=${xszzTyForm.iskns }','update','','800','600');return false;"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/commXszz.do?method=jgcxManage','del');return false;"
									class="btn_sc">删除</a>
							</li>
							<!-- 闽江学院 -->
							<logic:equal value="10395" name="xxdm">
								<a href="#" onclick="tjBb();return false;" class="btn_tj">统计报表</a>
							</logic:equal>
							<li>
								<a href="#" onclick="impInfo();return false;" class="btn_dr">导入</a>
							</li>
							<li>
								<a href="#" title="如果有导入数据的话，请点击此按钮进行同步审核状态以保证数据在本系统中的统一"
									onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/commXszz.do?method=jgcxManage&doType=tb');return false;"
									class="btn_ccg">同步审核状态</a>
							</li>

							<logic:equal value="yes" property="iskns" name="xszzTyForm">
								<logic:equal value="true" name="knsdl">
									<logic:equal value="true" name="jtqkdc">
										<li>
											<a href="#" title="为保证数据统一，请在导入数据后点击此按钮"
												onclick="showTips('处理数据中，请等待......');refreshForm('commXszz.do?method=jgcxManage&doType=jtqkdcTb');return false;"
												class="btn_ccg">同步家庭情况调查</a>
										</li>
									</logic:equal>
								</logic:equal>
							</logic:equal>

						</logic:equal>
						<logic:equal name="writeAble" value="yes" >
						<li>
							<a href="#" onclick="expInfo();return false;" class="btn_dc">导出</a>
						</li>
						</logic:equal>
						<%--						<li>--%>
						<%--							<a href="#" --%>
						<%--								onclick="if($('xmdm').value == ''){alert('请选择需要导出的项目')}else{showExpDiv('expEiv',true,'#C7DEFC');getTableZd();}" --%>
						<%--								class="btn_dc">阿弥陀佛</a>--%>
						<%--						</li>		--%>
					</ul>
				</div>
			</div>

			<div class="leftframe04" id="a11">
				<div class="menulist">
					<!--层start-->
					<div class="menutitle">
						<h3>
							<span class="title"> <logic:empty name="mklx">
					      	资助项目
					  </logic:empty> <logic:notEmpty name="mklx">
									<logic:equal name="mklx" value="zz">
					      	资助项目
					      </logic:equal>
									<logic:equal name="mklx" value="pj">
					      	评奖项目
					      </logic:equal>
								</logic:notEmpty> </span>
						</h3>
						<!--CNLTreeMenu Start:-->
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height:420px; overflow-x:hidden;overflow-y:auto;">
							<p class="choose_select">
								<label>
									项目类别
								</label>
								<html:select property="xmlb" style="width:80px" styleId="xmlb" onchange="loadXmList(this)">
									<html:option value=""></html:option>
									<html:options collection="xmlbList" property="en"
										labelProperty="cn" />
								</html:select>
							</p>
							<ul id="xmxslb">
								<logic:iterate id="cxxm" name="xmList">
									<li name="${cxxm.xmlb}"
										class='<logic:equal value="${xmdm}" name="cxxm" property="xmdm">Current</logic:equal>'>
										<a  href="#" class='Child'
											title = "${cxxm.xmmc}"
											onclick="setXmdm('${cxxm.xmdm}');checkTimes('${cxxm.xmdm}');return false"
											style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>${cxxm.xmmc}</a>
									</li>
								</logic:iterate>
							</ul>
						</div>
					</div>
					<!--层end-->
				</div>
			</div>

			<div class="rightframe04" style="<%=width%>">
				<!--查询条件-->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commXszz.do?method=jgcxManage');return false;">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz"
											onclick="czSearchCond('nj-xn-xq-nd-kssj-jssj-xy-zy-bj-xh-xm-shzt1-shzt2-shzt3');return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" style="width:100px" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" style="width:100px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="width:100px" styleId="nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<!-- 学生用户 -->
									<logic:equal name="userType" value="stu">
										<html:text property="xh" styleId="xh" style="width:100px"
											readonly="true" />
									</logic:equal>
									<!-- 学生用户 -->
									<logic:notEqual name="userType" value="stu">
										<html:text property="xh" styleId="xh" style="width:100px"
											maxlength="20" />
									</logic:notEqual>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:100px"
										maxlength="20" />
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<!-- 是学院用户 -->
									<logic:equal name="isxy" value="true">
										<html:hidden property="xydm" />
										<html:select property="xydm" style="width:100px" styleId="xy"
											disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<!-- 是学院用户 end -->
									<!-- 非学院用户 -->
									<logic:equal name="isxy" value="false">
										<html:select property="xydm" style="width:100px" styleId="xy"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<!-- 非学院用户 end-->
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:100px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:100px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第三行 -->

							<!-- 第四行 -->
							<tr>
								<th>
									申请时间查询范围
								</th>
								<td colspan="3">
									<html:text property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="width:100px;cursor:hand;"
										onclick="return showCalendar('kssj','y-mm-dd');" />至
								
									<html:text property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="width:100px;cursor:hand;"
										onclick="return showCalendar('jssj','y-mm-dd');" />
								</td>
								<th>
									<span id="jbId" style="display : none"> 项目级别 </span>
								</th>
								<td>
									<span id="jbVId" style="display : none"> <html:select
											property="xmzzjb" style="width:100px" styleId="xmzzjb">
											<html:option value="">----请选择----</html:option>
										</html:select> </span>
								</td>
								
							</tr>
							<!-- 第五行 -->
							<tr>
								<th>
									<span id="zt1Id" style="display : none"> 一级审核 </span>
								</th>
								<td>
									<span id="zt1VId" style="display : none"> <html:select
											property="shzt1" style="width:100px" styleId="shzt1">
											<html:option value="">----请选择----</html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select> </span>
								</td>
								<th>
									<span id="zt2Id" style="display : none"> 两级审核 </span>
								</th>
								<td>
									<span id="zt2VId" style="display : none"> <html:select
											property="shzt2" style="width:100px" styleId="shzt2">
											<html:option value="">----请选择----</html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select> </span>
								</td>
								<th>
									<span id="zt3Id" style="display : none"> 三级审核 </span>
								</th>
								<td>
									<span id="zt3VId" style="display : none"> <html:select
											property="shzt3" style="width:100px" styleId="shzt3">
											<html:option value="">----请选择----</html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select> </span>
								</td>
							</tr>
						</tbody>
					</table>

				</div>



				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" width="100%"
						id="rsTable">
						<!-- 表头 -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<!-- 假的CHECKBOX 防止全选被隐藏-->
									<input type="checkbox" name="jiade" value="all" onclick="" />
									<input type="checkbox" id="selall" name="selall"
										onclick="selAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- 表头 end-->
						<!--内容 -->
						<tbody>
							<logic:notEmpty name="rsList">
								<logic:iterate name="rsList" id="rs" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="showOpenInfo('/xgxt/commXszz.do?method=jgcxUpdate&iskns=${xszzTyForm.iskns }','view','','800','600');">
										<td align="center">
											<logic:equal name="rs" property="delete" value="yes">
												<input type="checkbox" id="checkVal"
													name="primarykey_checkVal" value="${rs.pk }" />
											</logic:equal>
											<logic:equal name="rs" property="delete" value="no">
												<input type="checkbox" id="checkVal" disabled="disabled"
													name="primarykey_checkVal" value="${rs.pk }" />
											</logic:equal>
										</td>
										<td align="center">
											${rs.xh }
										</td>
										<td align="center">
											${rs.xm }
										</td>
										<td align="center">
											${rs.xb }
										</td>
										<td align="center">
											${rs.nj }
										</td>
										<td align="center">
											${rs.xymc }
										</td>
										<td align="center">
											${rs.zymc }
										</td>
										<td align="center">
											${rs.bjmc }
										</td>
										<td align="center">
											${rs.xmmc }
										</td>
										<logic:notEmpty name="rs" property="xmzzjb">
											<logic:equal name="rs" property="sqzq" value="学年">
												<td align="center">
													${rs.xn }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="sqzq" value="学期">
												<td align="center">
													${rs.xn }
												</td>
												<td align="center">
													${rs.xq }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="sqzq" value="年度">
												<td align="center">
													${rs.nd }
												</td>
											</logic:equal>
										</logic:notEmpty>
										<td align="center">
											${rs.sqsj }
										</td>
										
										<!-- 信阳师范 -->
											<logic:equal name="xxdm" value="88888">
												<!-- 绿色通道 -->
												<logic:equal name="rs" property="xmdm" value="5002">
													<td align="center">
														${rs.sfdb }
													</td>
													<td align="center">
														${rs.kzzd3 }
													</td>
													<td align="center">
														${rs.jtrjsr }
													</td>
												</logic:equal>
												
											</logic:equal>
										
										<logic:empty name="rs" property="xmzzjb">
											<td align="center">
												<logic:equal name="rs" property="shjb" value="无需审核">
															无需审核
														</logic:equal>
												<logic:equal name="rs" property="shjb" value="一级审核">
															${rs.shzt1 }
														</logic:equal>
												<logic:equal name="rs" property="shjb" value="两级审核">
															一级审核（${rs.shzt1 }）<br>
														二级审核（${rs.shzt2 }） 
												</logic:equal>
												<logic:equal name="rs" property="shjb" value="三级审核">
															一级审核（${rs.shzt1 }）<br>
														二级审核（${rs.shzt2 }） 
													<br>
														三级审核（${rs.shzt3 }） 
												</logic:equal>
											</td>
										</logic:empty>
										<logic:notEmpty name="rs" property="xmzzjb">
											<logic:equal name="rs" property="sffj" value="分级">
												<td align="center">
													${rs.xmzzjb }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="sfje" value="需要">
												<td align="center">
													${rs.xmzzje }
												</td>
											</logic:equal>
											<!-- 信阳师范 -->
											<logic:equal name="xxdm" value="10477">
												<!-- 绿色通道 -->
												<logic:equal name="rs" property="xmdm" value="1004">
													<td align="center">
														${rs.yjje }
													</td>
													<td align="center">
														${rs.sjje }
													</td>
												</logic:equal>
												<!-- 国家助学贷款 -->
												<logic:equal name="rs" property="xmdm" value="5003">
													<td align="center">
														${rs.dknd }
													</td>
													<td align="center">
														${rs.dkje }
													</td>
												</logic:equal>
											</logic:equal>
											<!-- 信阳师范 end-->
											<logic:equal name="rs" property="shjb" value="无需审核">
												<td align="center">
													无需审核
												</td>
											</logic:equal>
											<logic:equal name="rs" property="shjb" value="一级审核">
												<td align="center">
													${rs.shzt1 }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="shjb" value="两级审核">
												<td align="center">
													${rs.shzt1 }
												</td>
												<td align="center">
													${rs.shzt2 }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="shjb" value="三级审核">
												<td align="center">
													${rs.shzt1 }
												</td>
												<td align="center">
													${rs.shzt2 }
												</td>
												<td align="center">
													${rs.shzt3 }
												</td>
											</logic:equal>
										</logic:notEmpty>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<%
									ArrayList list = ((ArrayList) request.getAttribute("rsList"));
									int rsNum = 0;
									if (list != null) {
										rsNum = list.size();
									}
									int pageSize = (Integer) request.getAttribute("pageSize");
									for (int i = 0; i < (pageSize - rsNum); i++) {
							%>
							<tr>
								<td>
									<input type="checkbox" value="" disabled="true" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<%
							}
							%>
						</tbody>
						<!--内容 end -->
					</table>
				</div>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
				<!--分页end-->
				<script type="text/javascript">
									$('choose').className="hide";
							</script>
			</div>
			<!-- 查询结果 end-->
		</html:form>
		<div id="tmpdiv1"></div>
		<!-- 提示信息 -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- 提示信息 end-->

		<!-- 导出控制的层 -->
		<%@ include file="/comm/exp/commExp.jsp"%>
		<!-- 导出控制的层 end-->
	</body>
</html>
