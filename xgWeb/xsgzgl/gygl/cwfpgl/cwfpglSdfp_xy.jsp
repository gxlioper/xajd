<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script language="javascript" defer="defer">
		//床位手动分配查询
		function allNotEmpThenGo(){
			var lddm=$("lddm").value;
			if(lddm==""){
				alertInfo("请选择楼栋!");
				return false;
			}
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp";
			refreshForm(url);
			BatAlert.showTips('正在操作，请稍等...');
		}

		//床位手动分配
		function saveQscwfpxx(doType){
			var select_qs_count=0;//选中的寝室个数
			var select_cw_count=0;//选中的床位个数
			var checkbox=document.getElementsByName("checkbox_qsh");
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					select_qs_count++;
				}
			}
			checkbox=document.getElementsByName("checkbox_cwh");
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					select_cw_count++;
				}
			}
			
			if(doType=="qxfp"){
				if(select_qs_count==0&&select_cw_count==0){
					alertInfo("请选择需要取消分配的床位！");
					return false;
				}
				confirmInfo("本次共选中了床位数："+select_cw_count+"个,\n确定取消分配吗？",function(ok){
					if(ok=="ok"){
						saveQscwfpxx_submit(doType);
					}
				});
				//if(!confirm("本次共选中了床位数："+select_cw_count+"个\n确定取消分配吗？")){
				//	return false;
				//}
			}else{
				if(select_qs_count==0&&select_cw_count==0){
					alertInfo("请选择需要分配的床位！");
					return false;
				}
				confirmInfo("本次共选中了床位数："+select_cw_count+"个,\n确定分配吗？",function(ok){
					if(ok=="ok"){
						saveQscwfpxx_submit(doType);
					}
				});
				//if(!confirm("本次共选中了床位数："+select_cw_count+"个\n确定分配吗？")){
				//	return false;
				//}
			}
		}	

		function saveQscwfpxx_submit(doType){
			$("nj").value=$("hidden_nj").value;
			$("bjdm").value=$("hidden_bjdm").value;
			$("xb").value=$("hidden_xb").value;
			$("lddm").value=$("hidden_lddm").value;
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&doType="+doType;
			refreshForm(url);
			BatAlert.showTips('正在保存，请稍等...');
		}
		
		//页面切换
		function pageqh(act){
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&pageqh=pageqh&act="+act;
			refreshForm(url);
			BatAlert.showTips('正在跳转，请稍等...');
		}
		
		//设置查询提示层位置
		function setSearchMsgWz(left,top){
			if($("input_mhcx_msg")){
				$("input_mhcx_msg").style.left=left;
				$("input_mhcx_msg").style.top=top;
			}
		}	

		//动态显示选择的寝室床位个数
		function dyn_show_qs_cw_count(){
			$("dyn_show_qs_cw_count").innerHTML="当前已选择寝室数：<font color='red'>"+dyn_qs_count+"</font>个，"+
			                                              "床位数：<font color='red'>"+dyn_cw_count+"</font>个";	
		}

		var dyn_qs_count=0;//动态选择的寝室个数
		var dyn_cw_count=0;//动态选择的床位个数
		//选中所有
		function selectAll(obj,curr_type){
			var xb=$("xb").value;
			var ches;
			if(curr_type=="cw"){//床位
				if(obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[0].childNodes[0].value!=xb){//性别不对应时不进行选择
					obj.checked=false;
				}
				obj.checked?dyn_cw_count++:dyn_cw_count--;//改变动态选择的床位个数
				ches=new Array();
				//return false;
			}else if(curr_type=="qs"){//寝室
				ches=obj.parentNode.parentNode.getElementsByTagName("input");
			}else if(curr_type=="lc"){//楼层
				ches=obj.parentNode.parentNode.nextSibling.getElementsByTagName("input");
			}else if(curr_type=="ld"){//楼栋
				ches=obj.parentNode.parentNode.parentNode.parentNode.getElementsByTagName("input");
			}else{
				return false;
			}

			for(var i=0;i<ches.length;i++){
				if(ches[i].type=="checkbox"&&ches[i].disabled==false){
					if(ches[i].name=="checkbox_cwh"){
							if(ches[i].parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[0].childNodes[0].value!=xb){//性别不对应时不进行选择
								continue;
							}
							obj.checked?(ches[i].checked?"":dyn_cw_count++):(ches[i].checked?dyn_cw_count--:"");//改变动态选择的床位个数
					}else if(ches[i].name=="checkbox_qsh"){
							if(ches[i].parentNode.parentNode.childNodes[0].childNodes[0].value!=xb){//性别不对应时不进行选择
								if(curr_type=="qs"){//对于床位和寝室不进行选择
									obj.checked=false;
								}
								continue;
							}
							if(curr_type=="qs"){
								obj.checked?dyn_qs_count++:dyn_qs_count--;
							}else{
								obj.checked?(ches[i].checked?"":dyn_qs_count++):(ches[i].checked?dyn_qs_count--:"");//改变动态选择的寝室个数
							}
					}
					ches[i].checked=obj.checked?true:false;
				}
			}
			dyn_show_qs_cw_count();
		}

		function show_hidden_lc(obj,type){
			if(type=="all"){//操作所有楼层

				if(jQuery(obj).text() =="展开"){
					jQuery(".con_overlfow table.dateline>tbody>tr").show();
					jQuery(".con_overlfow table.dateline>tbody>tr button").text("隐藏");
					
				}else{
					jQuery(".con_overlfow table.dateline>tbody>tr:odd").hide();
					jQuery(".con_overlfow table.dateline>tbody>tr button").text("展开");
				}
			
					//hide();
				
				/*
				var tbody_tr=obj.parentNode.parentNode.parentNode.nextSibling.childNodes;
				
				//alertInfo(tbody_tr.length);
				for(var i=0;i<tbody_tr.length;i++){
					//alertInfo(tbody_tr[i].tagName);
					alert(tbody_tr[i].jQuery("#button"));
					if(i%2==0&&tbody_tr[i].getElementsByTagName("button")[0]){
						tbody_tr[i].getElementsByTagName("button")[0].innerHTML=obj.innerHTML=="隐藏"?"展开":"隐藏";
					}else{
						tbody_tr[i].style.display=obj.innerHTML=="隐藏"?"none":"";
					}
				}
				*/
			}else{
				//alert(jQuery(obj).text());
				if(jQuery(obj).text() =="展开"){
					jQuery(obj).parents("tr").next().show();
					
				}else{
					jQuery(obj).parents("tr").next().hide();
				}
				
				
				/*
				var temp=obj.parentNode.parentNode.nextSibling;
				temp.style.display=temp.style.display==""?"none":"";

				var tbody_tr=$("button_all").parentNode.parentNode.parentNode.nextSibling.childNodes;
				//alertInfo(tbody_tr.length);
				if(obj.innerHTML=="展开"){
					for(var i=0;i<tbody_tr.length;i++){
						if(temp!=tbody_tr[i]){
							if(i%2==0&&tbody_tr[i].getElementsByTagName("button")[0]){
								tbody_tr[i].getElementsByTagName("button")[0].innerHTML="展开";
							}else{
								tbody_tr[i].style.display="none";
							}
						}
					}
				}
				*/
				
			}
			obj.innerHTML=obj.innerHTML=="隐藏"?"展开":"隐藏";
		}

		function loadLdlist(){
			var xb=document.getElementById("xb").value;
			var xydm=document.getElementById("xydm").value;
			var nj=document.getElementById("nj").value;
			var url="gyglnew_cwfpgl.do?method=loadLdlist";
			jQuery.get(url, {xydm:xydm,nj:nj, xb:(xb=="男"?"1":"2")},
					  function(data){
				  		var ldobj=document.getElementById("lddm");
					    var ldlist=data.split(";");
					    var t;
					    ldobj.options.length=0;
					    for(var i=0;i<ldlist.length;i++){
						    t=ldlist[i].split(",");
					    	ldobj.options[ldobj.options.length] = new Option(t[1], t[0]);
						}
					  }); 
		}
		function show_hidden_ldtj(){
			var ldtj = document.getElementById("ldtj");
			if(!ldtj){
				return false;
			}
			var btn_ldtj = document.getElementById("btn_ldtj");
			if(ldtj.style.display=="none"){
				ldtj.style.display="";
			}else{
				ldtj.style.display="none";
			}
			btn_ldtj.innerHTML=btn_ldtj.innerHTML=="隐藏楼栋统计"?"查看楼栋统计":"隐藏楼栋统计";
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理-住宿管理-<logic:equal value="to_qxfp" name="act">取消</logic:equal>床位分配</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>寝室床位颜色说明:
				<font color="green"><B>当前班级的寝室床位</B></font>;
				<font color="red"><B>非当班级的寝室床位</B></font>;
				<font color="black"><B>未分配的寝室床位</B></font>。<br>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
					此处填写操作说明
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/gyglnew_cwfpgl">			
			<!-- 隐藏域 -->
			<!-- 分配对象 -->
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx }"/>
			<input type="hidden" name="hidden_nj" id="hidden_nj" value="<bean:write name="rs" property="nj"/>"/>
			<input type="hidden" name="xydm" id="xydm" value="<bean:write name="rs" property="xydm"/>"/>
			<input type="hidden" name="hidden_bjdm" id="hidden_bjdm" value="<bean:write name="rs" property="bjdm"/>"/>
			<input type="hidden" name="hidden_xb" id="hidden_xb" value="<bean:write name="rs" property="xb"/>"/>
			<input type="hidden" name="hidden_lddm" id="hidden_lddm" value="<bean:write name="rs" property="lddm"/>"/>
			<input type="hidden" name="act" value="${act}"/>
			
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="to_fp" name="act">
							<!-- 保存分配 -->
							<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('save');return false;" class="btn_shtg"> 保存分配 </a></li>
							<li><a href="#" id="btn_xg" onclick="pageqh('to_qxfp');return false;" class="btn_sx"> 转至取消分配界面 </a></li>
						</logic:equal>
						<logic:equal value="to_qxfp" name="act">
							<!-- 取消分配 -->
							<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('qxfp');return false;" class="btn_shbtg">取消分配</a></li>			
							<li><a href="#" id="btn_xg" onclick="pageqh('to_fp');return false;" class="btn_sx"> 转至保存分配界面 </a></li>
						</logic:equal>
						<li><a href="#" id="btn_ldtj" onclick="show_hidden_ldtj()" class="btn_cx">隐藏楼栋统计</a></li>	
						<li><a href="gyglnew_cwfpgl_cwfp.do" id="btn_fh" class="btn_fh">返回 </a></li>	
					</ul>
				</div>
				<!-- 按钮 end-->					
				<!-- 过滤条件 -->
<%--				<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
				<!-- 过滤条件 end-->				
				<div class="searchtab">	
					<logic:notEmpty name="xynjxbld_tjxx">
					<table id="ldtj" class="dateline" width="98%" style="display: ">
						<thead>
						<tr>
							<td>楼栋</td>
							<td>总床位数</td>
							<td>已分配床位数</td>
							<td>未分配床位数</td>
							<td>分配当前班级床位数</td>
						</tr>
						</thead>
						<% String lddm=((HashMap<String,String>)request.getAttribute("rs")).get("lddm");//当前选中的楼栋代码 %>
						<logic:iterate id="s" name="xynjxbld_tjxx">
						<tr>
							<%if(((HashMap<String,String>)s).get("lddm").equals(lddm)){ %>
								<td style="color: red"><bean:write name="s" property="ldmc"/></td>
								<td style="color: red"><bean:write name="s" property="cws"/></td>
								<td style="color: red"><bean:write name="s" property="yfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="wfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="xycws"/></td>
							<%}else{ %>
								<td><bean:write name="s" property="ldmc"/></td>
								<td><bean:write name="s" property="cws"/></td>
								<td><bean:write name="s" property="yfpcws"/></td>
								<td><bean:write name="s" property="wfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="xycws"/></td>
							<%} %>
						</tr>
						</logic:iterate>
					</table>
					</logic:notEmpty>				
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<td>当前操作班级对象：</td>
								<th width="6%">班级</th>
								<td width="20%">
									<html:select name="rs" property="bjdm" styleId="bj" style="width:120px;">
										<html:optionsCollection name="bjList" label="bjmc" value="bjdm"/>
									</html:select>
								</td>
								<th width="6%">专业</th>
								<td width="20%">
									<html:select name="rs" property="zydm" styleId="zy" style="width:120px;" onchange="initBjallList();">
										<html:option value="">--请选择--</html:option>
										<html:optionsCollection name="zyList" label="zymc" value="zydm"/>
									</html:select>
								</td>
								<th width="6%">年级</th>
								<td width="10%">
									<html:select name="rs" property="nj" styleId="nj" style="width:60px;" onchange="initBjallList();loadLdlist();">
										<html:optionsCollection name="njList" label="nj" value="nj"/>
									</html:select>
								</td>
								<th width="6%">性别</th>
								<td width="10%">
									<html:select name="rs" property="xb" styleId="xb" style="width:60px;" onchange="loadLdlist();">
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
									</html:select>
								</td>	
								<td width="30%">
									总人数:<font color="red">${xynj_tjxx.num }</font>;
									已分配床位数为:<font color="red">${xynj_tjxx.cws }</font>
								</td>							
							</tr>
							<tr>
								<td>当前操作楼栋对象：</td>								
								<th>楼栋</th>
								<td>
									<html:select name="rs" property="lddm" styleId="lddm" style="width:120px;">
										<html:option value="">--请选择--</html:option>
										<html:optionsCollection name="ldlist" label="ldmc" value="lddm"/>
									</html:select>
								</td>
								<th>床位状态</th>
								<td>
									<html:select property="cwzt" styleId="cwzt" style="width:120px;">
										<html:option value=""></html:option>
										<html:option value="未分配">未分配</html:option>
										<html:option value="已分配">已分配</html:option>
									</html:select>
								</td><%--
								<td colspan="2">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('showNews.do')">
											查 询
										</button>
									</div>
								</td>
							--%></tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('showNews.do')">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>					
				</div>
				
			</div>
			<!-- 多功能操作区 end -->
			
			<!-- 查询结果-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
						<logic:empty name="ldjbxx">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="ldjbxx">
							&nbsp;&nbsp;
							<font color="blue">
							
							</font> 
						</logic:notEmpty>
					</span>
					<span id="dyn_show_qs_cw_count"></span>
				</h3>
				
				<div class="con_overlfow" style="min-height: 500px;">
				<table summary="" class="dateline" align="" border="1px;" width="100%">
					<!-- 表头 -->
					<thead>
						<logic:notEmpty name="ldjbxx">
						<tr align="center" style="cursor:hand">
							<td>
							<input type="checkbox" name="checkbox_lddm" onclick="selectAll(this,'ld');" 
							value="<bean:write name="ldjbxx" property="lddm"/>"/>
							楼栋名称:<bean:write name="ldjbxx" property="ldmc"/><%--
							[
								层数：<bean:write name="ldjbxx" property="ldcs"/>
								寝室数：<bean:write name="ldjbxx" property="qss"/>
								床位数：<bean:write name="ldjbxx" property="cws"/>
								楼栋性别：<bean:write name="ldjbxx" property="ldxb"/>
								<font color="blue">
								未分配寝室数/床位数：
								<bean:write name="ldjbxx" property="wfp_qss"/>/<bean:write name="ldjbxx" property="wfp_cws"/>
								</font>
							]
							--%><button type="button"  id="button_all" onclick="show_hidden_lc(this,'all');">隐藏</button>
							</td>
						</tr>
						</logic:notEmpty>
					</thead><tbody>
					<!-- 表头 end-->
					<!--内容 -->
					<% 
					    String act=(String)request.getAttribute("act");//当前选择的学院
					    String xydm=((HashMap<String,String>)request.getAttribute("rs")).get("xydm");//当前选择的学院
						String nj=((HashMap<String,String>)request.getAttribute("rs")).get("nj");//当前选中的年级
					    String bjdm=((HashMap<String,String>)request.getAttribute("rs")).get("bjdm");//当前选择的班级
						List<HashMap<String,String>> ldlcqscwxxb=(List<HashMap<String,String>>)request.getAttribute("ldlcqscwxxb");
						
						int index=0; //记录循环床位的索引
						String curr_ch="";//当前层号
						String curr_qsh="";//当前寝室号
						String curr_qsxy="";//当前寝室所属学院
						String curr_qsnj="";//当前寝室所属年级
						HashMap<String,String> cwxx=null;//床位信息
						boolean qs_top_mark=true;//寝室开始标记，用于显示寝室头部
						boolean qs_cw_end_mark=true;//寝室床位末尾标记
						boolean ld_cw_end_mark=false;//楼栋床位是否循环完成
					%>
					<logic:iterate name="ldlcxx" id="s">
						<tr onclick="rowOnClick(this);" style="cursor:hand">
						<td>
							<%curr_ch=((HashMap<String,String>)s).get("ch");//对当前循环的层进行临时标记赋值
							  qs_top_mark=true;%>
							  <input type="checkbox" name="checkbox_ch" onclick="selectAll(this,'lc');" 
							  value="<bean:write name="s" property="ch"/>" />
							<bean:write name="s" property="chmc"/> 层
							[
								寝室数/床位数：<bean:write name="s" property="qss"/>/<bean:write name="s" property="cws"/>
								未分配床位数：<bean:write name="s" property="wfp_cws"/>								
								分配当前班级床位数：<font color="red"><bean:write name="s" property="bxy_cws"/></font>
							]
							<button type="button"  onclick="show_hidden_lc(this);">隐藏</button>
						</td>
						</tr>
						<tr>
							<td>
								<table width="98%" style="border: 0px;">
									<tr>
									<%for(int i=0;i<10000&&index<ldlcqscwxxb.size();i++){//首先循环寝室，根据床位的层号进行判断该床位是否是该层的床位（数据在查询时已排序）
										cwxx=ldlcqscwxxb.get(index);
										if(curr_ch.equals(cwxx.get("ch"))){//床位层号和当前层号对应时才可循环
											if(curr_qsh.equals(cwxx.get("qsh"))){//寝室号对应时，进行床位的循环
												if(qs_top_mark){//如果是寝室开始循环，需要先添加寝室头部
													%>
														<td width="20%">
															<input type="hidden" value="<%=cwxx.get("qsxb") %>"/>
															<input type="checkbox" name="checkbox_qsh" onclick="selectAll(this,'qs');" 
															    value="<%=cwxx.get("qsh")%>"/>
															<%
																if(xydm.equals(cwxx.get("qsxydm"))&&nj.equals(cwxx.get("qsnj"))){//相对应
																	%><font color="green"><%
																}else if(cwxx.get("qsxydm")==null){//未分配
																	%><font color=""><%
																}else{//不对应
																	%><font color="red"><%
																}
															%>
															<%=cwxx.get("qsh") %>
															[<%=cwxx.get("qsxb") %>]
															<%=cwxx.get("qsnj")==null?"":"("+cwxx.get("qsnj")+")" %><%--年级 --%>
															<div align="center"><%=cwxx.get("qsxymc")==null?"":cwxx.get("qsxymc") %></div><%--学院 --%>
															</font>
														</td><td><table><tr>
													<%
													  qs_cw_end_mark=true;
												}
												%>
														<td width="90px">
															<input type="checkbox" name="checkbox_cwh"  onclick="selectAll(this,'cw');" 
																<%=(("to_fp".equals(act)&&cwxx.get("bjdm")!=null)||cwxx.get("xh")!=null||("to_qxfp".equals(act)&&cwxx.get("bjdm")==null))?"disabled='disabled'":""%> 
															value="<%=cwxx.get("qsh")%>_<%=cwxx.get("cwh")%>"/>
															<%
																//curr_qsxy!=null&&curr_qsxy.equals(cwxx.get("xydm"))
																   		//&&curr_qsnj!=null&&curr_qsnj.equals(cwxx.get("nj"))
																if(bjdm.equals(cwxx.get("bjdm"))){//床位学院与寝室学院对应
																	%><font color="green" ><%=cwxx.get("cwh") %><%
																}else if(cwxx.get("bjdm")==null){//未分配
																	%><font color="" ><b><%=cwxx.get("cwh") %></b><%
																}else{//其他学院
																	%><font color="red" >
																	<%=cwxx.get("cwh") %>
																	<%
																}
																
															%>
															<%=cwxx.get("cwbjmc")==null?"":cwxx.get("cwbjmc")%>
															</font><br><%=cwxx.get("xm")==null?"":cwxx.get("xm") %>
														</td>
													<%
												qs_top_mark=false;
												index++;//床位输出时，床位号信息索引++
											}else{//寝室号不对应是就进行赋值，并另起一行进入下一个寝室
												if(!"".equals(curr_qsh)&& qs_cw_end_mark){
													%></tr></table></td><%
												}
												curr_qsh=cwxx.get("qsh");
												curr_qsxy=cwxx.get("qsxydm");
												curr_qsnj=cwxx.get("qsnj");
												qs_top_mark=true;
												%></tr><tr><%
											}
										}else{//层号不对应跳出，进入下一个楼层
											if(!"".equals(curr_qsh)&& qs_cw_end_mark){
												%></tr></table></td><%
											}
											qs_cw_end_mark=false;
											break;
										}
									} %>
									
									<%if(ldlcqscwxxb.size()>0&&index==ldlcqscwxxb.size()&&!ld_cw_end_mark){//用于床位已循环完成，但楼层还有的情况
										%></tr></table></td><%
										ld_cw_end_mark=true;
									} %>
									</tr>
								</table>
							</td>
						</tr>
					</logic:iterate>
					</tbody>
					<!--内容 end-->
					<!-- 补空行 -->
					<!-- 补空行 end-->
				</table>
				</div>
			</div>
			<!-- 查询结果 end-->
			
		</html:form>
		<logic:present name="back">
			<script>
			<logic:equal value="true" name="back">
				alertInfo("操作成功！");
			</logic:equal>
			<logic:equal value="false" name="back">
				alertInfo("操作失败！");
			</logic:equal>
			</script>
		</logic:present>
	</body>
</html>