<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		
		<script type='text/javascript' src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/xgservice.js"></script>
	<script type='text/javascript' src="js/moveDiv.js"></script>
	<script type='text/javascript' src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<script type='text/javascript' src="js/messageFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
	<script type="text/javascript" src="js/comm/ymPrompt.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/gygl/cwrzgl/xjhb.js"></script>
		<script language="javascript" defer="defer">
		//床位手动分配查询
		function allNotEmpThenGo(){
			var lddm=$("lddm").value;
			if(lddm==""){
				alertInfo("请选择楼栋!");
				return false;
			}
			var url="gyglnew_cwrzgl.do?method=cwrzglSdfp";
			refreshForm(url);
			BatAlert.showTips('正在操作，请稍等...');
		}

		//床位手动分配
		var doType_temp;
		function saveQscwfpxx(doType){
			doType_temp=doType;			
			var select_qs_count=0;//选中的寝室个数
			var select_cw_count=0;//选中的床位个数
			var select_xs_count=0;//选中的学生个数
			var checkbox=document.getElementsByName("checkbox_qsh");
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					select_qs_count++;
				}
			}
			checkbox=document.getElementsByName("checkbox_cwh");
			for(var i=0;i<checkbox.length;i++){
				if(doType=="qxfp"){
					if(checkbox[i].checked&&checkbox[i].parentNode.getElementsByTagName("div").length>0
							&&checkbox[i].parentNode.getElementsByTagName("div").length==1){ 
						select_cw_count++;
					}
				}else{
					if(checkbox[i].checked&&checkbox[i].parentNode.getElementsByTagName("input").length>1){ 
						select_cw_count++;
					}else{
						checkbox[i].checked=false;
					}
				}
			}
			checkbox=document.getElementsByName("checkbox_xh");
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					select_xs_count++;
				}
			}
			
			if(doType=="qxfp"){
				if(select_xs_count==0&&select_cw_count==0){
					alertInfo("请选择需要取消入住的学生或床位！");
					return false;
				}
				$("th_span_lable").innerHTML="取消入住信息";
				$("th_rzsj").innerHTML="<span class='red'>*</span>退宿时间";
				$("tr_tsyy").style.display="";
				$("tr_rzyy").style.display="none";
				$("tr_bz").style.display="";
				$("submit_bz").innerHTML="本次共选中了学生："+select_xs_count+"个,床位数："+select_cw_count+"个。确定取消入住吗？";
				//tipsWindown("保存提示","id:tempDiv","350","200","true","","true","id");
				tipsWindownNew("保存提示","id:tempDiv",450,260);
				//if(!confirm("本次共选中了,学生："+select_xs_count+"个,床位数："+select_cw_count+"个\n确定取消入住吗？")){
				//	return false;
				//}
			}else{
				if(select_cw_count==0){
					alertInfo("请安排需要入住的学生到对应的床位！");
					return false;
				}
				$("th_span_lable").innerHTML="保存入住信息";
				$("th_rzsj").innerHTML="<span class='red'>*</span>入住时间";
				$("tr_tsyy").style.display="none";
				$("tr_rzyy").style.display="";
				$("tr_bz").style.display="none";
				$("submit_bz").innerHTML="本次共入住"+select_cw_count+"个学生。确定入住吗？"+
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				//tipsWindown("保存提示","id:tempDiv","350","130","true","","true","id");
				tipsWindownNew("保存提示","id:tempDiv",450,260);
				//if(!confirm("本次共入住"+select_cw_count+"个学生。\n确定入住吗？")){
				//	return false;
				//}
			}
		}	
		
		//保存取消床位分配信息——提交
		function saveQscwfpxx_submit(){
			var doType=doType_temp;
			var rzsj=$("rzsj").value;			
			if("qxfp"==doType){
				if(rzsj==""){
					alertInfo("请选择退宿时间！");
					return false;
				}
				var tsyy=$("tsyy").value;
				if(tsyy==""){
					alertInfo("请选择退宿原因！");
					return false;
				}

				var xn=$("xn").value;
				if(xn==""){
					alertInfo("请选择学年！");
					return false;
				}
				var xq=$("xq").value;
				if(xq==""){
					alertInfo("请选择学期！");
					return false;
				}
			}else{
				if(rzsj==""){
					alertInfo("请选择入住时间！");
					return false;
				}
			}
			jQuery("#nj").value=jQuery("#hidden_nj").value;
			jQuery("#xydm").value=jQuery("#hidden_xydm").value;
			jQuery("#xb").value=jQuery("#hidden_xb").value;
			jQuery("#lddm").value=jQuery("#hidden_lddm").value;
			var url="gyglnew_cwrzgl.do?method=cwrzglSdfp&doType="+doType;
			refreshForm(url);
			//BatAlert.showTips('正在保存，请稍等...');
		}
		
		//选中所有
		function selectAll(obj,curr_type){
			var xb=$("xb").value;
			var xxdm = jQuery("#xxdm").val();
			var ches;
			if(curr_type=="cw"){//床位
				if(jQuery(obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode).children().eq(0).children().eq(0).val()!=xb){//性别不对应时不进行选择
					obj.checked=false;
					return false;
				}
				selectCw(obj,curr_type,1);
				return false;
			}else if(curr_type=="qs"){//寝室
				ches=obj.parentNode.parentNode.getElementsByTagName("input");
			}else if(curr_type=="lc"){//楼层
				ches=jQuery(obj.parentNode.parentNode).next()[0].getElementsByTagName("input");
			}else if(curr_type=="ld"){//楼栋
				ches=obj.parentNode.parentNode.parentNode.parentNode.getElementsByTagName("input");
			}else{
				return false;
			}
			var new_cw_count=0;//本次新选择的床位个数
			for(var i=0;i<ches.length;i++){
				if(ches[i].type=="checkbox"&&ches[i].disabled==false){
					//alertInfo(ches[i].parentNode.parentNode.childNodes[0].childNodes[0].value);
					if(ches[i].name=="checkbox_cwh"){
							if(jQuery(ches[i].parentNode.parentNode.parentNode.parentNode.parentNode.parentNode).children().eq(0).children().eq(0).val()!=xb){//性别不对应时不进行选择
								continue;
							}
					}else if(ches[i].name=="checkbox_qsh"){
							if(jQuery(ches[i].parentNode.parentNode).children().eq(0).children().eq(0).val()!=xb){//性别不对应时不进行选择
								if(curr_type=="qs"){//对于床位和寝室不进行选择
									obj.checked=false;
								}
								continue;
							}
					}
					if(obj.checked&&!ches[i].checked&&ches[i].name=="checkbox_cwh"){//++本次新选择的床位数
						new_cw_count++;
					}
					ches[i].checked=obj.checked?true:false;
				}
			}
			//alertInfo("寝室数："+select_qs_count+"   床位数："+select_cw_count);
			if("13560" == xxdm){
				selectCwForHbxy(obj,curr_type,new_cw_count);
			}else{
				selectCw(obj,curr_type,new_cw_count);//进行预入住
			}
			qxNotAllCheckbox();
		}

		//选中床位进行学生入住的操作
		function selectCw(obj,type,new_cw_count){
			var xss=document.getElementsByName("checkbox_xh");
			var cws=document.getElementsByName("checkbox_cwh");
			var xxdm = jQuery('#xxdm').val();
			var xsnamediv=document.getElementById("table_ld").getElementsByTagName("div");
			if(obj.checked){
				var xs_index=0;
				var cw_index=0;
				var tr_xs;
				var new_xs_count=0;//当前选择还未预入住的学生
				for(var i=0;i<cws.length;i++){
					if(cws[i].checked&&cws[i].parentNode.getElementsByTagName("div").length==0){
						for(;xs_index<xss.length;xs_index++){
							if(xss[xs_index].checked&&xss[xs_index].parentNode.parentNode.style.display==""
								&&xss[xs_index].parentNode.getElementsByTagName("input")[1].value=="未入住"){
								new_xs_count++;//++当前选择还未预入住的学生
								break;
							}
						}
						if(!xss[xs_index]){
							if(new_xs_count==0){
								alertInfo("请选择需要入住的学生！");
							}else{
								alertInfo("选中的可入住床位数为<font color='red'>"+new_cw_count+"</font>个，<br/>"+
										"入住所选的<font color='red'>"+new_xs_count+"</font>个学生后，<br/>"+
										"仍有<font color='red'>"+(new_cw_count-new_xs_count)+"</font>个床位可入住。");
							}
							break
						};
						tr_xs=xss[xs_index].parentNode.parentNode;
						var div=document.createElement("div");
						div.name="xsxsnamediv";
						div.id="div_xs_"+xss[xs_index].value;
						div.onmouseover=function(){BatAlert.showInfo(tr_xs.cells[2].getElementsByTagName("input")[0].value);};
						div.onmouseout=BatAlert.showInfo;
						if("10279" == xxdm) {
							div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xss[xs_index].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText+"</br>"+tr_xs.cells[4].innerText;
						} else if ("13560" == xxdm){//新疆厚博学院个性化
							if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//如果有语种类别
								jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").append("<input type='hidden' name='yzlbdms' value='"+tr_xs.cells[3].getElementsByTagName("input")[0].value+"'></input>");
								div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xss[xs_index].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText+"<input type='hidden' name='xsyzlbdm' value='"+jQuery(xss[xs_index]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val()+"'>";						
							}else{
								div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xss[xs_index].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText;
							}
						}
						else {
							div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xss[xs_index].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+tr_xs.cells[1].innerHTML;
						}
						cws[i].parentNode.appendChild(div);
						document.getElementById("tr_xs_"+xss[xs_index].value).style.display="none";
						xs_index++;
					}
					cw_index++;
				}
				//取消由于学生不足对应的床位
				for(var i=cw_index;i<cws.length;i++){
					if(cws[i].parentNode.getElementsByTagName("div").length==0){
						cws[i].checked=false;
					}
				}
			}else{//床位单个取消
				var xsnamediv;
				var xh;
				for(var i=0;i<cws.length;i++){
					xsnamediv=cws[i].parentNode.getElementsByTagName("div");
					if(!cws[i].checked&&xsnamediv.length==1){
					//alertInfo(xsnamediv[0].getElementsByTagName("input"));
						if(xsnamediv[0].getElementsByTagName("input").length>0){//已入住保存的床位的div没有隐藏域
							xh=xsnamediv[0].getElementsByTagName("input")[0].value;
							document.getElementById("tr_xs_"+xh).style.display="";
							xsnamediv[0].parentNode.removeChild(xsnamediv[0]);
						}
					}
				}
			}
		}
		//取消非全选checkbox
		function qxNotAllCheckbox(){
			var cws;
			var chs;
			var b=false;
			var qss=document.getElementsByName("checkbox_qsh");
			for(var i=0;i<qss.length;i++){
				b=false;
				cws=qss[i].parentNode.parentNode.getElementsByTagName("input");
				for(var j=0;j<cws.length;j++){
					if(cws[j].type=="checkbox"&&!cws[j].checked){
						b=true;
						break;
					}
				}
				if(b){
					qss[i].checked=false;//取消寝室选中
					var xxdm = jQuery("#xxdm").val();
					if("13560" == xxdm){
						jQuery(qss[i]).parent().find("input[name='yzlbdms']").remove();
					}
				}
			}
		}
		//楼层床位信息的显示与隐藏
		function show_hidden_lc(obj,type){
			if(type=="all"){//操作所有楼层
				//var tbody_tr=obj.parentNode.parentNode.parentNode.nextSibling.childNodes;
				if(jQuery(obj).text() =="展开"){
					jQuery("#anniu table.dateline>tbody>tr").show();
					jQuery("#anniu table.dateline>tbody>tr button").text("隐藏");
					
				}else{
					jQuery("#anniu table.dateline>tbody>tr:odd").hide();
					jQuery("#anniu table.dateline>tbody>tr button").text("展开");
				}
				/*for(var i=0;i<tbody_tr.length;i++){
					//alertInfo(tbody_tr[i].tagName);
					if(i%2==0&&tbody_tr[i].getElementsByTagName("button")[0]){
						tbody_tr[i].getElementsByTagName("button")[0].innerHTML=obj.innerHTML=="隐藏"?"展开":"隐藏";
					}else{
						tbody_tr[i].style.display=obj.innerHTML=="隐藏"?"none":"";
					}
				}*/
			}else{
				if(jQuery(obj).text() =="展开"){
					jQuery(obj).parents("tr").next().show();
					
				}else{
					jQuery(obj).parents("tr").next().hide();
				}
				
				/*var temp=obj.parentNode.parentNode.nextSibling;
				temp.style.display=temp.style.display==""?"none":"";
				*/
			}
			obj.innerHTML=obj.innerHTML=="隐藏"?"展开":"隐藏";
		}
		//选中所有
		function checkAll(obj,checkbox_name){
			var chs=document.getElementsByName(checkbox_name);
			for(var i=0;i<chs.length;i++){
				chs[i].checked=obj.checked;
			}
		}

		//加载楼栋列表
		function loadLdlist(){
			var nj=document.getElementById("nj").value;
			var xydm=document.getElementById("xy").value;
			var xb=document.getElementById("xb").value;
			var url="gyglnew_cwfpgl.do?method=loadLdlist";
			jQuery.get(url, { xb:(xb=="男"?"1":"2"),nj:nj,xydm:xydm},
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
				<em>您的当前位置：</em><a>公寓管理-住宿管理-床位入住</a>
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
			<p>
				寝室床位颜色说明：
				<font color="green"><B>已入住床位</B></font>，
				<font color="black"><B>未入住床位</B></font>。
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/gyglnew_cwrzgl">			
			<!-- 隐藏域 -->
			<!-- 分配对象 -->
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx }"/>
			<input type="hidden" name="hidden_nj" id="hidden_nj" value="<bean:write name="rs" property="nj"/>"/>
			<input type="hidden" name="hidden_xydm" id="hidden_xydm" value="<bean:write name="rs" property="xydm"/>"/>
			<input type="hidden" name="hidden_xb" id="hidden_xb" value="<bean:write name="rs" property="xb"/>"/>
			<input type="hidden" name="hidden_lddm" id="hidden_lddm" value="<bean:write name="rs" property="lddm"/>"/>
			<input type="hidden" name="act" value="${act}"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 入住 -->
						<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('save');return false;" class="btn_shtg"> 保存入住 </a></li>
						<!-- 取消入住 -->
						<logic:equal value="true" name="isKqxrz">
							<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('qxfp');return false;" class="btn_shbtg"> 取消入住 </a>	</li>
						</logic:equal>
						<logic:notEqual value="true" name="isKqxrz">
							<li><a href="#" id="btn_xg" title="当前年级<bean:message key="lable.xsgzyxpzxy" />的取消入住未开启!" class="btn_shbtg"><font color="gray">取消入住</font> </a>	</li>
						</logic:notEqual>
						<li><a href="#" id="btn_ldtj" onclick="show_hidden_ldtj()" class="btn_cx">隐藏楼栋统计</a></li>	
						<li><a href="gyglnew_cwrzgl_cwrz.do" id="btn_fh" class="btn_fh">返回 </a></li>	
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
							<td align="left">楼栋</td>
							<td align="left">总床位数</td>
							<td align="left">已分配床位数</td>
							<td align="left">未分配床位数</td>
							<td align="left">当前<bean:message key="lable.xsgzyxpzxy" />床位数</td>
							<td align="left">当前<bean:message key="lable.xsgzyxpzxy" />已入住床位数</td>
							<td align="left">当前<bean:message key="lable.xsgzyxpzxy" />未入住床位数</td>
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
								<td style="color: red"><bean:write name="s" property="xyyrzcws"/></td>
								<td style="color: red"><bean:write name="s" property="xywrzcws"/></td>
							<%}else{ %>
								<td><bean:write name="s" property="ldmc"/></td>
								<td><bean:write name="s" property="cws"/></td>
								<td><bean:write name="s" property="yfpcws"/></td>
								<td><bean:write name="s" property="wfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="xycws"/></td>
								<td style="color: red"><bean:write name="s" property="xyyrzcws"/></td>
								<td style="color: red"><bean:write name="s" property="xywrzcws"/></td>
							<%} %>
						</tr>
						</logic:iterate>
					</table>
					</logic:notEmpty>
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<td>当前操作<bean:message key="lable.xsgzyxpzxy" />对象：</td>
								<th width="6%"><bean:message key="lable.xsgzyxpzxy" /></th>
								<td width="20%">
									<logic:equal value="xy" name="userType">
									     <html:select property="xydm" styleId="xy" onchange="initZyList();initBjList();loadLdlist();" disabled="true" style="width:150px;">
											<html:optionsCollection name="xyList" label="xymc" value="xydm"/>
										</html:select>
										<input type="hidden" id="xydm" name="xydm" value="<bean:write name="rs" property="xydm"/>" />
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
									     <html:select property="xydm" styleId="xy" onchange="initZyList();initBjList();loadLdlist();" style="width:150px;">
											<html:optionsCollection name="xyList" label="xymc" value="xydm"/>
										</html:select>
									</logic:notEqual>
								</td>
								<th width="6%">年级</th>
								<td width="10%">
									<html:select property="nj" styleId="nj" onchange="initZyList();initBjList();loadLdlist();" style="width:70px;">
										<html:optionsCollection name="njList" label="nj" value="nj"/>
									</html:select>
								</td>
								<th width="6%">性别</th>
								<td width="10%">
									<html:select property="xb" styleId="xb" style="width:70px;" onchange="loadLdlist();">
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
									</html:select>
								</td>
								<td width="30%">
									总人数:<font color="red">${xynj_tjxx.num }</font>,
									已入住:<font color="red">${xynj_tjxx.yycws}</font>,
									未入住:<font color="red">${xynj_tjxx.wrzrs}</font>;<br/>
									已分配床位数:<font color="red">${xynj_tjxx.cws }</font>,
									剩余床位数:<font color="red">${xynj_tjxx.sycws }</font>
								</td>
							</tr>
							<tr>
								<td>当前操作楼栋对象：</td>	
								<th>楼栋</th>
								<td>
									<html:select name="rs" property="lddm" styleId="lddm" style="width:150px;">
										<html:option value="">--请选择--</html:option>
										<html:optionsCollection name="ldlist" label="ldmc" value="lddm"/>
									</html:select>
								</td>
								<th>床位状态</th>
								<td>
									<html:select property="cwzt" styleId="cwzt" style="width:70px;">
										<html:option value=""></html:option>
										<html:option value="未入住">未入住</html:option>
										<html:option value="已入住">已入住</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8">
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
						<logic:equal value="10264" name="xxdm">
						&nbsp;&nbsp;<font color="#ff9600">温馨提醒：</font><font color="#666666">当前学生已按省份分组</font>
						</logic:equal>
						<logic:empty name="ldjbxx">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="ldjbxx">
							&nbsp;&nbsp;
							<font color="blue">
							
							</font> 
						</logic:notEmpty>
					</span>
					
				</h3>
				
				<div class="con_overlfow">
				<!-- 布局用table start -->
				<table style="width:100%">
					<tr>
						<td width="25%" style="vertical-align: top;" >
							<table class="dateline" width="100%">
								<tr>
									<th>专业</th>
									<td>
										<html:select property="zydm" styleId="zy" onchange="initBjList()" style="width:120px;">
											<html:option value=""></html:option>
											<logic:present name="zyList">
												<html:optionsCollection name="zyList" label="zymc" value="zydm"/>
											</logic:present>
										</html:select>
									</td>
								</tr>
								<tr>
									<th>班级</th>
									<td>
										<html:select property="bjdm" styleId="bj" style="width:120px;">
											<html:option value=""></html:option>
											<logic:present name="bjList">
												<html:optionsCollection name="bjList" label="bjmc" value="bjdm"/>
											</logic:present>
										</html:select>
									</td>
								</tr>
								<logic:equal name="xxdm" value="13560">
									<tr>
										<th>语种</th>
										<td>
											<html:select property="yzlbdm" styleId="yzlbdm" style="width:120px;">
												<html:option value=""></html:option>
												<logic:present name="yzlbList">
													<html:optionsCollection name="yzlbList" label="yzlbmc" value="yzlbdm"/>
												</logic:present>
											</html:select>
										</td>
									</tr>
								</logic:equal>
								<tr>
									<th>入住情况</th>
									<td>
										<html:select property="rzqk" styleId="rzqk" style="width:120px;">
											<html:option value=""></html:option>
											<html:option value="未入住">未入住</html:option>
											<html:option value="已入住">已入住</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<th><nobr>学号/姓名:</nobr></th>
									<td><html:textarea property="xhxm" rows="5" style="width:115px;"></html:textarea></td>
								</tr>
								<tr>
									<td colspan="2" align="right">
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go" 
												onclick="allNotEmpThenGo('showNews.do')">
												查 询
											</button>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<logic:equal name="xxdm" value="13560">											
											<div style="height: 500px;overflow-x:auto;overflow-y:auto;">
										</logic:equal>
										<logic:notEqual name="xxdm" value="13560">
											<div style="height: 500px;overflow-x:hidden;overflow-y:auto;">
										</logic:notEqual>
										<table id="table_xsxx" width="100%"> 
											<thead>
												<tr>
													<th><input type="checkbox" onclick="checkAll(this,'checkbox_xh')"/></th>
													<th onclick="tableSort(this)">学号</th>
													<th onclick="tableSort(this)">姓名</th>
<%--													上海海洋大学个性化--%>
													<logic:equal name="xxdm" value="10264">
														<th onclick="tableSort(this)">生源地</th>
													</logic:equal>
													<%--上海戏剧个性化--%>
													<logic:equal name="xxdm" value="10279">
														<th onclick="tableSort(this)">生源地</th>
														<th onclick="tableSort(this)">民族</th>
													</logic:equal>
													<%--新疆医科大学厚博学院个性化--%>
													<logic:equal name="xxdm" value="13560">
														<th>语种</th>
													</logic:equal>
												</tr>
											</thead>
											<tbody>
											<% HashMap<String,Character> bjmark=new HashMap<String,Character>();
											   char curr_bjmark='@';
											   String curr_bjmc="";
											   String[] xs;%>
												<logic:iterate id="s" name="xsxxlist">
													<tr id="tr_xs_${s[0] }">
														<td><input type="checkbox" name="checkbox_xh" onclick="" value="${s[0]}"/><input type="hidden" value="${s[2]}"/></td>
														<td><nobr>${s[0] }</nobr></td>
														<td onmouseover="BatAlert.showInfo('${s[6]},人数：${s[7] }');" onmouseout="BatAlert.showInfo();">
															<nobr>
															<input type="hidden" value="${s[6]}"/>															
															<%xs=(String[])s;
															if("已入住".equals(xs[2])){//已入住学生字体标记为绿色
															%>
															<font color="green">
															<%}
															if(!curr_bjmc.equals(xs[6])){
																curr_bjmc=xs[6];
																curr_bjmark++;
																bjmark.put(xs[6],curr_bjmark);
																}
															%>
															<logic:notEqual name="xxdm" value="12673">
															[<%=curr_bjmark%>]
															</logic:notEqual>
															${s[1] }
															<%
															if("已入住".equals(xs[2])){
															%>
															</font>
															<%}%>
															&nbsp;&nbsp;</nobr>
														</td>
<%--													上海海洋大学个性化--%>
														<logic:equal name="xxdm" value="10264">
															<td><nobr>${s[8] }</nobr></td>
														</logic:equal>
														<%--上海戏剧个性化--%>
														<logic:equal name="xxdm" value="10279">
															<td><nobr>${s[8] }</nobr></td>
															<td><nobr>${s[9] }</nobr></td>
														</logic:equal>
														<%--新疆医科大学厚博学院个性化--%>
														<logic:equal name="xxdm" value="13560">
															<td>
																<nobr>${s[10] }</nobr>
																<input type="hidden" name="xs_yzlbdm" value="${s[11]}"/> 
															</td>
														</logic:equal>
													</tr>
												</logic:iterate>
											</tbody>
										</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
						<td width="75%" style="vertical-align: top;">
				<!-- 布局用table end1 -->	
				<div id="anniu" style="height: 718px;overflow-x:auto;overflow-y:auto;">
				<table class="dateline" id="table_ld" border="1px;" width="100%">
					<!-- 表头 -->
					<thead>
						<logic:notEmpty name="ldjbxx">
						<tr align="center" style="cursor:hand">
							<td>
							<input type="checkbox" id="checkbox_lddm" name="checkbox_lddm" onclick="selectAll(this,'ld');" 
							value="<bean:write name="ldjbxx" property="lddm"/>"/>
							楼栋名称:<bean:write name="ldjbxx" property="ldmc"/>
							<button type="button"  onclick="show_hidden_lc(this,'all');">隐藏</button>
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
								分配床位数：<bean:write name="s" property="cws"/>；
								未入住床位数：<bean:write name="s" property="wfp_cws"/>；						
								已入住床位数：<font color="red"><bean:write name="s" property="yfp_cws"/></font>
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
														<td width="15%">
															<input type="hidden" value="<%=cwxx.get("qsxb") %>"/>
															<input type="checkbox" name="checkbox_qsh" onclick="selectAll(this,'qs');" 
																<%=("to_fp".equals(act)&&cwxx.get("qsxydm")!=null)?"disabled='disabled'":"" %> 
															    value="<%=cwxx.get("qsh")%>"/><%=cwxx.get("qsh") %>
															</font>
														</td><td><table><tr>
													<%
													  qs_cw_end_mark=true;
												}
												%>
														<td width="70px" <%if(cwxx.get("xh")!=null){%>onmouseover="BatAlert.showInfo('<%=cwxx.get("bjmc")==null?"":cwxx.get("bjmc")%>');" onmouseout="BatAlert.showInfo();"<%}%></td>
															<input type="checkbox" name="checkbox_cwh"  onclick="selectAll(this,'cw');" 
																
															value="<%=cwxx.get("qsh")%>_<%=cwxx.get("cwh")%>"/><%=cwxx.get("cwh") %><%
																if((curr_qsxy!=null&&!curr_qsxy.equals(cwxx.get("xydm")))
																   		||(curr_qsnj!=null&&!curr_qsnj.equals(cwxx.get("nj")))){
																	%>
																	<%=cwxx.get("nj")==null?"":"("+cwxx.get("nj")+")"%><br>
																	<%=cwxx.get("xymc")==null?"":cwxx.get("xymc")%>
																	<%
																}
															%>
															</font>
															<%  if(cwxx.get("xh")!=null){
																%>
																<div style="color: green;">
																<%if(bjmark.get(cwxx.get("bjmc"))==null){
																	curr_bjmc=cwxx.get("bjmc");
																	curr_bjmark++;
																	bjmark.put(cwxx.get("bjmc"),curr_bjmark);
																}%>
																[<%=bjmark.get(cwxx.get("bjmc"))%>]																															
																<%=cwxx.get("xm")==null?"":cwxx.get("xm") %><br/>
																<%=cwxx.get("xh")==null?"":cwxx.get("xh") %>
																
																<%  
																String xxdm=(String)request.getAttribute("xxdm");
																if("10279".equals(xxdm)) {
																%>
																	<br/>
																	<%=cwxx.get("sydqmc")==null?"":cwxx.get("sydqmc") %><br/>
																	<%=cwxx.get("mz")==null?"":cwxx.get("mz") %>
																
																<%}%>
																</div><%
															} %>
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
				<!-- 布局用table start1 -->
				</td>
					</tr>
				</table>
				<!-- 布局用table end1 -->
				</div>
			</div>
			<!-- 查询结果 end-->
			
			
			
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">请选择入住时间</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th  width="25%" id="th_rzsj">
									<span class="red">*</span>入住时间
								</th>
								<td>
									<html:text property="rzsj" styleId="rzsj" onkeypress="onlyBackSpace(this,event);" style="width:100px"
										onclick="return showCalendar(this.id,'yyyy-MM-dd','','',200,10)"></html:text>
								</td>
								
							</tr>
							<tr id="tr_rzyy">
								<th >
									<span class="red">*</span>入住原因
								</th>
								<td>
									<html:select property="rzyy" styleId="rzyy">
										<html:optionsCollection name="rzyylist" label="rzyymc" value="rzyydm"/>
									</html:select>
								</td>
							</tr>
							<tr id="tr_tsyy">
								<th>
									<span class="red">*</span>退宿原因
								</th>
								<td>
<%--									<html:text property="tsyy" styleId="tsyy"></html:text>--%>
									<html:select property="tsyy" styleId="tsyy">
										<html:optionsCollection name="tsyylist" label="tsyymc" value="tsyydm"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>学年/学期
								</th>
								<td>
									<html:select property="xn" styleId="xn" disabled="false">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
									</html:select>
									<html:select property="xq" styleId="xq" disabled="false" >
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
									</html:select>
								</td>
						 　　</tr>
							
							<tr id="tr_bz">
								<th width="25%">
									备注
								</th>
								<td>
									<html:textarea property='bz' style="width:95%" styleId="bz" rows='2'/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="确 定 "  onclick="saveQscwfpxx_submit_browser()">
											确 定 
										</button>
										<button type="button" name="取消"  onclick="iFClose();return false;">
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

<script type="text/javascript"> 
	var curTr = null; 
	var tb1 = document.getElementById('table_xsxx');
	var trs = tb1.getElementsByTagName('tr'); 
	tb1.onselectstart = function(){ 
		if(curTr){ 
			document.selection.empty(); return true; 
		} 
	}; 
	for(var i=1; i<trs.length; i++) { 
		trs[i].style.cursor = 'hand'; 
		trs[i].onmousedown = function(){ 
			curTr = this; 
			curTr.style.backgroundColor = '#6495ED'; 
		}; 
		trs[i].onmouseover = function() { 
			if(curTr && curTr != this) { 
				this.swapNode(curTr); 
			} 
		} 
	} 
	document.body.onmouseup = function(){ 
		if(curTr){ 
			curTr.style.backgroundColor = ''; 
			curTr = null; 
		} 
	}; 
</script> 