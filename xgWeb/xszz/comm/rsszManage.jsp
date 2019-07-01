<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript">
		
		//显示保存按钮
		function showSaveBtn(){
			var rsNum = $("rsNum").value;
			
			if(rsNum != "" && rsNum != "0"){
				$("saveBtn").style.display = "";
			}else{
				$("saveBtn").style.display = "none";
			}
		}
		
		//修改人数设置
		function saveRssz(cz){
			
			var xmdm = $("queryequals_xmdm").value;
		
			if(xmdm == ""){
				alert("请指定需要设置的项目!");
				return false;	
			}
			if (confirm('确定该分配吗？')){
				saveUpdate('/xgxt/commXszz.do?method=rsszManage&doType=save&cz='+cz,'');
			}
		}
		//设置控制级别
		function setKzjb(){	
			
			var xmdm = $("queryequals_xmdm").value;
			var tableName="xszz_zzxmb";
			var colList =["kzjb"];
			var pk = "xmdm";
			var pkValue = xmdm;
			dwr.engine.setAsync(false);
			getXszzInfo.getXszzInfo(tableName, pk, pkValue,colList,function(data){ 
				if(data!=null){
					var kzjb = data.kzjb;
					$("kzjb").value = kzjb;	
					$("kzjbSelect").value = kzjb;			
				}
			});
			
			dwr.engine.setAsync(true);
		}
		
		//显示设置页面
		function showSzym(){
			var iskns = $('iskns').value;
			var xmdm = $("queryequals_xmdm").value;
			
			if(xmdm != ""){
			
				var url = "/xgxt/commXszz.do?method=rsszUpdate&iskns="+iskns;
					url+="&xmdm="+xmdm;
					
				showTopWin(url,'800','600');
			}else{
			
				alert("请指定需要设置的项目!");
				
			}
		}
		
		//查询人数设置
		function searchXmrs(){
			var xmdm = $("queryequals_xmdm").value;
			if(xmdm != ""){
				allNotEmpThenGo('/xgxt/commXszz.do?method=rsszManage&xmdm='+xmdm);
			}else{
				alert("请指定需要设置的项目!");		
			}
		}
		
		//判断是否有修改过的信息 没有保存
		function checkXxbc(){
			var len=0;
			if($("number")){
				 var obj = document.getElementsByName("number");
				 len=obj.length;
			}
			for(i=0;i<len;i++){
				var hid_szrs=$("hid_szrs"+i).value;
				var szrs=$("szrs"+i).value;
				if(hid_szrs !=szrs){
					if (confirm("人数设置中有未保存的数据,要保存吗?")) {
						checkRssz();
						return false;
						
					}else{
						searchXmrs();
						return false;
					}
				}
			}
			
			searchXmrs();
		}
		
		function checkRssz(cz){
			var len=0;
			if($("number")){
				 var obj = document.getElementsByName("number");
				 len=obj.length;
			}
			for(i=0;i<len;i++){
				var szrs=$("szrs"+i).value;
				if(szrs.length>1 && szrs.substring(0,1)=="0"){
					alert("人数必须设置为整数!");
					$("szrs"+i).focus();
					return false;
				}
				
				if(szrs==null || szrs==""){
					alert("人数设置不能为空!");
					$("szrs"+i).focus();
					return false;
				}
			}
			saveRssz(cz);
		}
		
		
		
		function getXm(){
			var text="<span>";
			var xmdm=$("queryequals_xmdm").value;
			var num=document.getElementsByName("rsxmdm").length;
			if($("xmlb")){
				for(i=0;i<num;i++){	
					var dm=document.getElementsByName("rsxmdm")[i].value;
					var mc=document.getElementsByName("rsxmmc")[i].value;
					var lx=document.getElementsByName("rsxmlb")[i].value;
					if($("xmlb").value==lx || $("xmlb").value==""){
						if(xmdm==dm){
							text+="<li id='"+lx+"' style='background:#dce8f8'>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  style='color:#0A63BF'  onclick='setXmdm(\""+dm+"\");checkXxbc()'><span class='ico'>"+mc+"</span></a></li>";
						}else{
							text+="<li id='"+lx+"'>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  onclick='setXmdm(\""+dm+"\");checkXxbc()'><span class='ico'></span>"+mc+"</a></li>";
						}
					}
				}
				text+="</span>"
				document.getElementById('xmxslb').innerHTML = text;
				
			}
		}
		
		function setXmdm(dm){
			$("queryequals_xmdm").value=dm;
			setKzjb();
		}
		
		function checkCsh(){
			if($("number")){
				 var obj = document.getElementsByName("number");
				 len=obj.length;
				for(i=0;i<len;i++){
					var hid_szrs=$("hid_szrs"+i).value;
					var szrs=$("szrs"+i).value;
					if(hid_szrs !=szrs){
						if (confirm("人数设置中有未保存的数据,要保存吗?")) {
							checkRssz('csh');
							return false;
							
						}else{
							showSzym();
							return false;
						}
					}
				}
			}
			showSzym();
		}
		
		function isNotCsh(){
			var cz=$("cz").value;
			if("csh"==cz){
				$("cz").value="bc";
				showSzym();
			}
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
		</script>
	</head>
	<body onload="showSaveBtn();isNotCsh()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXszz">
			<%
					String widthType = request.getAttribute("widthType").toString();
					String width = "";
					if ("dbsx".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					} else if ("kjfs".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					}
			%>
			<!-- 隐藏域 -->
			<html:hidden property="iskns" styleId="iskns" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="cz" name="cz" value="${cz}" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }" />
			<input type="hidden" id="queryequals_xmdm" name="queryequals_xmdm"
				value="${xmdm}" />
			<input type="hidden" id="widthType" name="widthType"
				value="${widthType}" />
			<!-- 隐藏域 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="checkCsh();return false;" class="btn_csh">初始化</a>
							</li>
							<li>
								<p id="saveBtn" style="display : none">
									<a href="#" onclick="checkRssz();return false;" class="btn_ccg">保存</a>
								</p>
							</li>
						</ul>
					</div>
				</logic:equal>
			</div>

			<!-- 多功能操作区 -->
			<div class="leftframe04">
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
								<html:select property="queryequals_xmlb" style="width:80px"
									styleId="xmlb" onchange="loadXmList(this)">
									<html:option value=""></html:option>
									<html:options collection="xmlbList" property="en"
										labelProperty="cn" />
								</html:select>
							</p>
							<ul id="xmxslb">
								<logic:iterate id="rssz" name="zzxmList">
									<li name="${rssz.lb}"
										class='<logic:equal value="${xmdm}" name="rssz" property="dm">Current</logic:equal>'>
										<a href="#" class='Child' title="${rssz.mc}"
											onclick="setXmdm('${rssz.dm}');checkXxbc();return false;"
											style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>${rssz.mc}</a>
									</li>
								</logic:iterate>
							</ul>
						</div>

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
								<td colspan="10">
									<div class="bz">
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;当前学年：${xn}&nbsp;&nbsp;&nbsp;&nbsp;
											当前学期：${xq}&nbsp;&nbsp;&nbsp;&nbsp;当前年度：${nd }</font>
									</div>
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go" onclick="checkXxbc();return false;">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz"
											onclick="czSearchCond('nj-kzjbSelect-xy-zy-bj');return false;">
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
									控制级别
								</th>
								<td>
									<html:hidden property="kzjb" styleId="kzjb" />
									<html:select property="kzjb" style="width:100px"
										styleId="kzjbSelect" onchange="" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="kzjbList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
										style="width:100px" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<!--  非学院用户 -->
									<logic:notEqual name="userType" value="xy">
										<html:select property="queryequals_xydm" style="width:100px"
											styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									<!--  非学院用户 -->
									<logic:equal name="userType" value="xy">
										<html:hidden property="queryequals_xydm" />
										<html:select property="queryequals_xydm" style="width:100px"
											styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>

								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm" style="width:100px"
										styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" style="width:100px"
										styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<!-- 判断该项目是否存在人数上限 -->
							<logic:notEmpty name="xmInfo" property="rssx">
								<font color="blue"> 注：该项目人数上限为${xmInfo.rssx }人,目前已分配 <logic:empty
										name="xmInfo" property="szrs">0</logic:empty> <logic:notEmpty
										name="xmInfo" property="szrs">${xmInfo.szrs }</logic:notEmpty>
									人 </font>
							</logic:notEmpty>
							<logic:empty name="xmInfo" property="rssx">
								<font color="blue">注：该项目不存在人数上限。</font>
							</logic:empty>
						</logic:notEmpty> </span>
				</h3>
				<div class="con_overlfow">
					<table width="100%" class="dateline tablenowrap">
						<!-- 表头 -->
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- 表头 end-->


						<tbody>
							<logic:notEmpty name="rs">
								<!--内容 -->
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="">
										<!-- 部门名称 -->
										<logic:iterate id="v" name="s" offset="4">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
										<!-- 设置人数 -->
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td align="left">
												<input type="hidden" name="number" id="number" />
												<!-- 主键 -->
												<logic:iterate id="p" name="s" offset="0" length="1">
													<input type="hidden" name="primarykey_checkVal"
														id="pk${index }" value="${p }" />
												</logic:iterate>
												<!-- 是否存在 -->
												<logic:iterate id="cz" name="s" offset="1" length="1">
													<input type="hidden" name="iscz" id="iscz${index }"
														value="${cz }" />
												</logic:iterate>
												<!-- 设置年级 -->
												<logic:iterate id="sznj" name="s" offset="3" length="1">
													<input type="hidden" name="sznj" id="sznj${index }"
														value="${sznj }" />
												</logic:iterate>

												<!-- 设置时间的隐藏域 用来判断设置的时间是否改变 -->
												<input type="hidden" name="hid_szrs" id="hid_szrs${index }"
													value="${v }" />
												<input type="type" name="szrs" onblur="" id="szrs${index }"
													value="${v }" onkeydown="return onlyNum(this,5)"
													onmousedown="return onlyNum(this,5)"
													style="width:50%;ime-mode:disabled" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!--内容 end -->
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
								<logic:iterate id="tit" name="topTr" offset="0" length="8">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
								<logic:equal name="sffj" value="分级">
									<td>
										&nbsp;
									</td>
								</logic:equal>
								<logic:iterate id="tit" name="topTr" offset="7" length="2">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<%
							}
							%>
						</tbody>

					</table>
				</div>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
				<!--分页end-->
				<script type="text/javascript">
									$('choose').className="hide";
									</script>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- 提示信息 end-->
	</body>
</html>
