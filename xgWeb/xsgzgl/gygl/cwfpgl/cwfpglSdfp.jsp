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
		<style type="text/css">
			.fontTk{color:green; font-weight:bold; font-size:12px;padding-left:10px;}
			.demo_hq_list1{width:750px; margin:30px 20px; overflow:hidden;}
			.demo_hq_list1 ul li .con4{ width:98%; padding:1%; float:left; background:#f2f2f2;display:none;height:400px;overflow-y:auto;overflow-x:hidden;}
			.demo_hq_list1 ul li .con40{ width:98%; padding:1%; float:left; background:#f2f2f2;display:none;}
		</style>
		<script language="javascript" defer="defer">
		
		//床位手动分配查询
		function allNotEmpThenGo(){
			var lddm=$("lddm").value;
			if(lddm==""){
				alertInfo("请选择楼栋!");
				return false;
			}
			// 显示结果对象
			var resultSet = jQuery("#resultSet").val();
			var xb = jQuery(".imitation_xl div").attr("name");
			if("boy" == xb){
				xb = '1';
			}else if ("gril" == xb){
				xb = '2';
			}
			if("boy" == xb){
				xb = '1';
			}else if ("gril" == xb){
				xb = '2';
			}		
			if(jQuery("[name=bjdm]").val()!=""){
				resultSet = "bjli";
			}else if(jQuery("[name=zydm]").val()!=""){
				resultSet = "zyli";
			}else if(jQuery("[name=xydm]").val()!=""){
				resultSet = "xyli";
			}
			// userType
			var userType = jQuery("#userType").val();
			var xyyh = "";
			if("xy"==userType && "xyli" == resultSet){

				alertInfo('请选择专业/班级！');
				return false;
			}else if("xy"==userType){
				
				var nj=$("hidden_nj").value;
				var xydm=$("hidden_xydm").value;
				xyyh = "&nj="+nj+"&xydm="+xydm;
			}	
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&resultSet="+resultSet+"&xb="+xb;
			
			refreshForm(url+xyyh);
			BatAlert.showTips('正在操作，请稍等...');
		}
		
		//床位手动分配
		function saveQscwfpxx(doType){

			// 显示结果对象
			var resultSet = jQuery("#resultSet").val();
			// userType
			var userType = jQuery("#userType").val();

			if("xy"==userType && "xyli" == resultSet){

				alertInfo(jQuery("#xbmc").val()+"用户不能对"+jQuery("#xbmc").val()+"进行分配，请切换结果集再进行操作！");
				return false;
			}

			var select_qs_count = jQuery("[name='checkbox_qsh']:checkbox:checked").length;
			var select_cw_count = jQuery("[name='checkbox_cwh']:checkbox:checked").length;

			if(doType=="qxfp"){
				if(select_qs_count==0&&select_cw_count==0){
					alertInfo("请选择需要取消分配的寝室或床位！");
					return false;
				}
				confirmInfo("本次共选中了寝室数："+select_qs_count+"个,床位数："+select_cw_count+"个,\n确定取消分配吗？",function(ok){
					if(ok=="ok"){
						saveQscwfpxx_submit(doType);
					}
				});
				
			}else{
				if(select_qs_count==0&&select_cw_count==0){
					alertInfo("请选择需要分配的寝室或床位！");
					return false;
				}
				confirmInfo("本次共选中了寝室数："+select_qs_count+"个,床位数："+select_cw_count+"个,\n确定分配吗？",function(ok){
					if(ok=="ok"){
						saveQscwfpxx_submit(doType);
					}
				});
			}
		}	

		function saveQscwfpxx_submit(doType){			
			$("nj").value=$("hidden_nj").value;
			jQuery("[name=xydm]").val($("hidden_xydm").value);
			jQuery("[name=zydm]").val($("hidden_zydm").value);
			jQuery("[name=bjdm]").val($("hidden_bjdm").value);

			var xydm = $("hidden_xydm").value;
			var nj = $("hidden_nj").value;
			
			var xb = jQuery(".imitation_xl div").attr("name");
			if("boy" == xb){
				xb = '1';
			}else if ("gril" == xb){
				xb = '2';
			}		
			$("lddm").value=$("hidden_lddm").value;
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&doType="+doType+"&xb="+xb+"&xydm="+xydm+"&nj="+nj;
			refreshForm(url);
			BatAlert.showTips('正在保存，请稍等...');
		}
		//页面切换
		function pageqh(act){
			var msg="";
			// userType
			var userType = jQuery("#userType").val();
			var xyyh = "";
			if("xy"==userType){				
				var nj=$("hidden_nj").value;
				var xydm=$("hidden_xydm").value;
				xyyh = "&nj="+nj+"&xydm="+xydm;
			}	
			
			if(act=="to_fp"){
				msg="确认离开当前操作页面，转至分配操作页面吗？";
			}else{
				msg="确认离开当前操作页面，转至取消分配操作页面吗？";
			}
			var xb = jQuery(".imitation_xl div").attr("name");
			if("boy" == xb){
				xb = '1';
			}else if ("gril" == xb){
				xb = '2';
			}
			confirmInfo(msg, function(tag){
				if(tag == 'ok'){
					var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&pageqh=pageqh&act="+act+"&xb="+xb+xyyh;
					refreshForm(url);
					BatAlert.showTips('正在跳转，请稍等...');
				}
			});
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
			var dyn_qs_count = jQuery("[name='checkbox_qsh']:checkbox:checked").length;
			var dyn_cw_count = jQuery("[name='checkbox_cwh']:checkbox:checked").length;
			var inhtml = "当前已选择寝室数：<font color='red'>"+dyn_qs_count+"</font>个，"+
			"床位数：<font color='red'>"+dyn_cw_count+"</font>个";

			//设置提示信息
			jQuery("#dyn_show_qs_cw_count").html(inhtml);			
		}

		//选中所有
		function selectAll(obj,curr_type){
			var xb = jQuery(".imitation_xl div").html();
			var ches;
			if(curr_type=="cw"){//床位
				if(jQuery(obj).parents("td[name=cwxx]").prevAll("td[name=qsxx]").find("input[name=qsh_xb]").val()!=xb){//性别不对应时不进行选择
					jQuery(obj).attr("checked","false");
				}
				ches=new Array();
			}else if(curr_type=="qs"){//寝室

				ches = jQuery(obj).parent().next().find(":checkbox");
			}else if(curr_type=="lc"){//楼层
				ches = jQuery(obj).parent().nextAll().find(".dateline").find(":checkbox");
				
			}else if(curr_type=="ld"){//楼栋
				ches = jQuery(obj).parents(".demo_hq_list1").find(":checkbox");
			}else{
				return false;
			}
			//checkbox选择状态
			var checkZt = jQuery(obj).prop("checked");
			for(var i=0;i<ches.length;i++){
				if(jQuery(ches[i]).attr("disabled")!=true){
					if(jQuery(ches[i]).attr("name")=="checkbox_cwh"){
						if(jQuery(ches[i]).parents("td[name=cwxx]").prevAll("td[name=qsxx]").find("input[name=qsh_xb]").val()!=xb){//性别不对应时不进行选择
							continue;
						}
					}else if(jQuery(ches[i]).attr("name")=="checkbox_qsh"){
						if(jQuery(ches[i]).prev("input[name=qsh_xb]").val()!=xb){//性别不对应时不进行选择
							if(curr_type=="qs"){//对于床位和寝室不进行选择
								obj.checked=false;
							}
							continue;
						}
					}
					jQuery(ches[i]).attr("checked",checkZt);
				}
			}
			dyn_show_qs_cw_count();
		}

		//显示楼栋具体信息
		function modi(obj){
			var lddm = jQuery(obj).find("input:checkbox").val();
			var url = "gyglnew_xxtj.do?method=xxtjDetail"
			var h=1024
			var w=768
			if(lddm != null){
				showOpenWindow(url + '&pkValue='+lddm,h,w);
				return false;
			}
			return false;
		}

		//切换性别后重新检索
		function onchangeSex(xb){
			
			// 显示结果对象
			var resultSet = jQuery("#resultSet").val();
			
			if(jQuery("[name=bjdm]").val()!=""){
				resultSet = "bjli";
			}else if(jQuery("[name=zydm]").val()!=""){
				resultSet = "zyli";
			}else if(jQuery("[name=xydm]").val()!=""){
				resultSet = "xyli";
			}
			// userType
			var userType = jQuery("#userType").val();
			var xyyh = "";
			if("xy"==userType){				
				var nj=$("hidden_nj").value;
				var xydm=$("hidden_xydm").value;
				xyyh = "&nj="+nj+"&xydm="+xydm;
			}	
			var cwzt = jQuery("#cwzt").val();
			var xydm = jQuery("#hidden_xydm").val(); 
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&resultSet="+resultSet+"&xbdm="+xb+xyyh+"&cwzt="+cwzt+"&xydm="+xydm;
			refreshForm(url);
			BatAlert.showTips('正在操作，请稍等...');
		}
		
		jQuery(function() {

			// 床位分配对象
			var cwfpdx = jQuery("#cwfpdx").val();

			// 学院代码
			if("xydm"==cwfpdx){
				//隐藏专业/班级列表
				jQuery("#zytr").hide();
				jQuery("#bjtr").hide();
			} else if("zydm"==cwfpdx){
				//隐藏班级列表
				jQuery("#bjtr").hide();
			}
			jQuery("#fptj").css("display","none");
			jQuery(".regcon .regcon_nav").click(function(){
				if(jQuery("#fptj").css("display")	==	"none"){
					jQuery(this).children(".floatright1").removeClass("floatright1").addClass("floatright");
					jQuery("#fptj").css("display","block");	
				}
				else
				{
					jQuery(this).children(".floatright").removeClass("floatright").addClass("floatright1");
					jQuery("#fptj").css("display","none");	
				}
				
			})
			
			//性别下拉
			var xb = jQuery("#hidden_xb").val();
			if(xb == '男'){
				jQuery(".imitation_xl div").attr("name","boy");
			}else if(xb == '女'){
				jQuery(".imitation_xl div").attr("name","gril");
			}
			jQuery(".imitation_xl div").html(xb);
			
			jQuery(".imitation_xl div").click(function(event){
				event.stopPropagation();
				if(jQuery(this).prev("ul").is(":hidden")){
					jQuery(this).prev("ul").show();
				}else{
					jQuery(this).prev("ul").hide();
				}
			});
			jQuery(".imitation_xl ul li a").click(function(){
				var liid = jQuery(this).attr("name");
				if("boy" == liid){
					onchangeSex('1');
				}else if ("gril" == liid){
					onchangeSex('2');
				}
				
			});
			
			jQuery("body").click(function(){jQuery(".imitation_xl ul").hide()});

			// userType学院用户，不能切换年级学院
			var userType = jQuery("#userType").val();
			if("xy" ==userType){
				jQuery("#nj").attr("disabled", true);
				jQuery("#xy").attr("disabled", true);
			}

			//楼层分配统计进度展示
			jQuery(".demo_hq_list1 ul li .num").each(function(){
				var num		=	jQuery(this).html(); //总房间数
				var num1	=	jQuery(this).parent().next().children(".con2_2").children(".floatleft").children(".num1").html(); //已住
				var num2	=	num1/num*100;
				var num3	=	num2+"%";
				jQuery(this).parent().next().children(".length").children(".length1").animate({width:num3},1500);
			});
			//查看/隐藏寝室信息
			jQuery(".demo_hq_list1 ul li .con3").click(function(){
				if(jQuery(this).next().find(".dateline").is(":hidden")){
					jQuery(this).attr("title","点击关闭");
					jQuery(this).next().find(".dateline").show();
					var height = jQuery(this).next().find(".dateline").height();
					if(height > 400){
						jQuery(this).next().attr("class","con4");
					}else{
						jQuery(this).next().attr("class","con40");
					}
					jQuery(this).next().show();
					jQuery(this).parent().addClass("li");
				}else{
					jQuery(this).attr("title","点击展开");
					jQuery(this).next().find(".dateline").hide();
					jQuery(this).next().hide();
					jQuery(this).parent().removeClass("li");
				}
			});
			initLdList();				
		});
		//选择专业、分配状态、切换性别时楼栋列表联动
		function initLdList(){
			var xydm =jQuery("#xy").val();
			var zydm = jQuery("#zy").val();
			var cwzt = jQuery("#cwzt").val();
			var xbdm = null;
			var liid = jQuery(".imitation_xl div").attr("name");
			if("boy" == liid){
				xbdm = "1";
			}else if ("gril" == liid){
				xbdm = "2";
			}
			var url = "gyglnew_cwfpgl.do?method=cwfpglInitLdList";
			
				jQuery.ajax({
					type : "post",
					async : false,
					url : url,
					contentType:"application/x-www-form-urlencoded; charset=UTF-8",
					data : {
						xydm:xydm,
						zydm:zydm,
						cwzt:cwzt,
						xbdm:xbdm
					},
					
					success : function(data) {
						var datas = eval('('+data+')');
						var html='<option value="">--请选择--</option>';
						if(datas!=null&&datas!=""&&datas.length!=0){
	 						for ( var int = 0; int < datas.length; int++) {
 								html+='<option value="'+datas[int]["lddm"]+'">'+datas[int]["ldmc"]+'</option>';
							}
				}
						jQuery("#lddm").html(html);
						jQuery("#lddm").val(jQuery("#cxlddm").val());
					}
					});
			}
		</script>
	</head>
	<body>
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
				<font color="green"><B>当前操作范围的寝室床位</B></font>;
				<font color="red"><B>非当前操作范围的寝室床位</B></font>;
				<font color="black"><B>未分配的寝室床位</B></font>。<br>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/gyglnew_cwfpgl">			
			<!-- 隐藏域 -->
			<!-- 分配对象 -->
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx }"/>
			<input type="hidden" name="hidden_nj" id="hidden_nj" value="<bean:write name="rs" property="nj"/>"/>
			<input type="hidden" name="hidden_xydm" id="hidden_xydm" value="<bean:write name="rs" property="xydm"/>"/>
			<input type="hidden" name="hidden_xb" id="hidden_xb" value="<bean:write name="rs" property="xb"/>"/>
			<input type="hidden" name="hidden_lddm" id="hidden_lddm" value="<bean:write name="rs" property="lddm"/>"/>
			<input type="hidden" name="act" value="${act}"/>
			<!-- 结果展示 -->
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="resultSet" name="resultSet" value="${resultSet }" />
			<input type="hidden" id="cwfpdx" name="cwfpdx" value="${cwfpdx }" />
			<input type="hidden" name="hidden_bjdm" id="hidden_bjdm" value="<bean:write name="rs" property="bjdm"/>"/>
			<input type="hidden" name="hidden_zydm" id="hidden_zydm" value="<bean:write name="rs" property="zydm"/>"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="cxlddm" value="${rs.lddm }"/>
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
							<li><a href="#" id="btn_xg" onclick="pageqh('to_fp');return false;" class="btn_sx"> 转至分配操作界面 </a></li>
						</logic:equal>
						<li><a href="gyglnew_cwfpgl_cwfp.do" id="btn_fh" class="btn_fh">返回 </a></li>	
					</ul>
				</div>
				<!-- 按钮 end-->					
				<!-- 过滤条件 -->
<%--				<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
				<!-- 过滤条件 end-->				
				<div class="searchtab">
						
					<!-- 当前操作范围 -->
					<div id="dqczfw_all">
						<h3 class="datetitle_01">
							<span> 当前操作范围：</span>
						</h3>
						<table width="100%" id="searchTab" style="border:0;">
							<tr>
								<th style="width:80px;">年级</th>
								<td>
									<html:select name="rs" property="nj" styleId="nj" style="width:200px;" onchange="initZyList();initBjList();">
										<html:optionsCollection name="njList" label="nj" value="nj"/>
									</html:select>
								</td>						
								<th>楼栋名称</th>
								<td>
									<html:select name="rs" property="lddm" styleId="lddm" style="width:150px;">
										<html:option value="">--请选择--</html:option>
										<html:optionsCollection name="ldlist" label="ldmc" value="lddm"/>
									</html:select>
								</td>	
							</tr>
							<tr>
								<th style="width:80px;"><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select name="rs" property="xydm" style="width:200px;" styleId="xy" onchange="initZyList();initLdList();">
										<html:optionsCollection name="xyList" label="xymc" value="xydm"/>
									</html:select>
								</td>
								<th>分配状态</th>
								<td>
									<html:select property="cwzt" styleId="cwzt" style="width:150px;" onchange="initLdList();">
										<html:option value=""></html:option>
										<html:option value="未分配">未分配</html:option>
										<html:option value="已分配">已分配</html:option>
									</html:select>
								</td>
							</tr>
							<tr >
								<td colspan="2" style="padding: 0px 3px 0px 5px;">
									<table style="border:0;">											
										<tr id="zytr">
											<th style="border:0;width:80px;padding: 0px 5px 0px 0px;">专业</th>
											<td style="border:0;">
												<html:select name="rs" property="zydm" styleId="zy" style="width:200px;" onchange="initBjList();initLdList();">
													<% String userType=(String)request.getAttribute("userType"); %>
													<% if(!"xy".equalsIgnoreCase(userType)){ %>
														<html:option value="">--请选择--</html:option>
													<% } %>
													<html:optionsCollection name="zyList" label="zymc" value="zydm"/>
												</html:select>
											</td>		
										</tr>
									</table>
								</td>				
								<th>性别</th>
								<td >									
							        <div class="xl" style="float:left">
							            <div class="imitation_xl" style="padding-top:0px;">
							                <ul style="width:65px;">
							                    <li><a href="javascript:;" name="boy">男</a></li>
							                    <li><a href="javascript:;" name="gril">女</a></li>
							                </ul>
							                <div style="width:65px;" name="boy">男</div>
							            </div>
							        </div>
							    </td>
							</tr>
							<tr>
								<td colspan="2" style="padding: 0px 3px 0px 5px;">
									<table style="border:0;">
										<tr id="bjtr">
											<th style="border:0;width:80px;padding: 0px 5px 0px 0px;">班级</th>
											<td style="border:0;">
												<html:select name="rs" property="bjdm" style="width:200px;" styleId="bj" >
													<html:option value="">--请选择--</html:option>
													<html:optionsCollection  name="bjList" label="bjmc" value="bjdm"/>
												</html:select>
											</td>		
										</tr> 
									</table>
								</td>
								<td></td>													
								<td >				
									<div>
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('showNews.do')">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</table>
					</div>					
				</div>
			</div>
			<!-- 分配统计 -->
			<div class="regform" style="padding-top:0px;">
			<div class="regcon">
				<h3 class="regcon_nav" title="点击 查看 /隐藏">
					<div style="float:left">
						<span style="color:black; font-weight:bold; font-size:12px;">已分配统计</span>		
						<span style="color:green;padding-left:20px;" class="fontTk"> 总人数:<font color="red">${xynj_tjxx.num }</font>人</span>;
				        <span style="color:green;padding-left:20px;" class="fontTk">已分配床位数:<font color="red">${xynj_tjxx.cws }</font>个</span>;
				        <span style="color:green" class="fontTk">寝室数:<font color="red">${xynj_tjxx.sumqsh }</font>个</span>;
				        <span style="color:green" class="fontTk">楼栋数:<font color="red">${xynj_tjxx.sumlddm }</font>个</span>;
					</div>
					<div class="floatright"></div>
				</h3>
			</div>
				<div id="fptj" style="padding-bottom:10px;">
					<logic:notEmpty name="xynjxbld_tjxx">
					<table id="ldtj" class="dateline" width="98%" >
						<thead>
						<tr>
							<td>楼栋</td>
							<td>可分配床位总数</td>
							<td>已分配床位总数</td>
							<td>空床位数</td>
							<td>【当前操作范围】床位数</td>
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
								<td style="color: red"><bean:write name="s" property="wfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="xycws"/></td>
							<%} %>
						</tr>
						</logic:iterate>
					</table>
					</logic:notEmpty>
					<logic:empty name="xynjxbld_tjxx">
						<span class="fontTk"> 没有已分配信息。</span>			
					</logic:empty>
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
					</span>
					<div class="xl" style="float:right"><li id="dyn_show_qs_cw_count"></li></div>
				</h3>
				<div class="con_overlfow" style="min-height: 500px;overflow-x:auto;overflow-y:auto;">
								
					<!--内容 -->
					<% 
					    String act=(String)request.getAttribute("act");//保存分配/取消分配[to_fp,to_qxfp]
					    String resultSet=(String)request.getAttribute("resultSet");//结果集[xyli,zyli,bjli]
					    String cwfpdx=(String)request.getAttribute("cwfpdx");//床位分配对象[xydm,zydm,bjdm]
						String nj=((HashMap<String,String>)request.getAttribute("rs")).get("nj");//当前选中的年级
					    String xydm=((HashMap<String,String>)request.getAttribute("rs")).get("xydm");//当前选择的学院
						String zydm=((HashMap<String,String>)request.getAttribute("rs")).get("zydm");//当前选择的专业
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
				<!-- 楼栋表头 -->
				<div class="demo_hq_list1">
					<logic:notEmpty name="ldjbxx">
						<div class="demo_hq_list1_nav">
							<a class="cur" href="#" style="min-width:110px;" ondblclick="modi(this);">
							<input type="checkbox" name="checkbox_lddm" onclick="selectAll(this,'ld');" 
									value="<bean:write name="ldjbxx" property="lddm"/>"/>
									<bean:write name="ldjbxx" property="ldmc" /></a>
						</div>
						<logic:empty name="ldlcxx">
							<div style="padding-top:10px;">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
							</div>
						</logic:empty>
						<ul>
				        	<logic:iterate name="ldlcxx" id="s">
			        		<li>
			        			<%curr_ch=((HashMap<String,String>)s).get("ch");//对当前循环的层进行临时标记赋值
  									qs_top_mark=true;%>
			            		<div class="con0">
			            			<input type="checkbox" name="checkbox_ch" id="checkbox_ch" onclick="selectAll(this,'lc');" 
	  									value="<bean:write name="s" property="ch"/>" /></div>
				        		
			            		<div class="con1"><span class="hl"><bean:write name="s" property="chmc"/>层</span>
			            			<br />寝室数/床位数：<bean:write name="s" property="qss"/>/<span class="num"><bean:write name="s" property="cws"/></span></div>
			            		<div class="con2">
				                	<div class="length">
				                    	<div class="length1"></div>
				                    </div>
				                    <div class="con2_2"><p class="floatleft"><span class="zfx1"></span>已分配当前操作范围床位数<span class="num1">
										<% if("xyli".equalsIgnoreCase(resultSet)) {%>
											<bean:write name="s" property="bxy_cws"/>
										
										<% }else if("zyli".equalsIgnoreCase(resultSet)) {%>
											<bean:write name="s" property="bzy_cws"/>
										
										<% }else if("bjli".equalsIgnoreCase(resultSet)) {%>
											<bean:write name="s" property="bbj_cws"/>
										<% }%>
									</span></p>
									<p class="floatright"><span class="zfx2"></span>空床位数&nbsp;<span class="num2"><bean:write name="s" property="wfp_cws"/></span></p></div>
				                	</div>
				                <div class="con3" title="点击展开"></div>
				                <div>
				               		<table class="dateline" style="display:none;" border="1px;" width="100%">
				               			<!--  <thead>
				               				<tr>
				               					<th>寝室床位信息：</th>
				               				</tr>
				               			</thead>-->
				               			<tbody>
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
																			<td name="qsxx" width="20%">
																				<input type="hidden" name="qsh_xb" value="<%=cwxx.get("qsxb") %>"/>
																				<input type="checkbox" name="checkbox_qsh" id="checkbox_qsh" onclick="selectAll(this,'qs');" 
																					<%if ("xyli".equalsIgnoreCase(resultSet)) {%>
																						<%=(("to_fp".equals(act)&&cwxx.get("qsxydm")!=null)||("to_qxfp".equals(act)&&cwxx.get("qsxydm")==null))?"disabled='disabled'":"" %>
																					<% }%>
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
																			</td><td name="cwxx"><table><tr>
																		<%
																		  qs_cw_end_mark=true;
																	}
																	%>
																			<td width="90px">
																					<!-- 判断不同结果集对应的checkbox的显示 -->
																				<input type="checkbox" name="checkbox_cwh" id="checkbox_cwh" onclick="selectAll(this,'cw');" 
																					<% if("xyli".equalsIgnoreCase(resultSet)) { %>
																						<%=(("to_fp".equals(act)&&cwxx.get("xydm")!=null)||cwxx.get("xh")!=null||("to_qxfp".equals(act)&&cwxx.get("xydm")==null))?"disabled='disabled'":""%> 
																					<% }else if("zyli".equalsIgnoreCase(resultSet)) {%>
																						<%=(("to_fp".equals(act)&&cwxx.get("zydm")!=null)||cwxx.get("xh")!=null||("to_qxfp".equals(act)&&cwxx.get("zydm")==null))?"disabled='disabled'":""%> 
																					<% }else if("bjli".equalsIgnoreCase(resultSet)) {%>
																						<%=(("to_fp".equals(act)&&cwxx.get("bjdm")!=null)||cwxx.get("xh")!=null||("to_qxfp".equals(act)&&cwxx.get("bjdm")==null))?"disabled='disabled'":""%> 
																					<% } %>
																																			
																				value="<%=cwxx.get("qsh")%>_<%=cwxx.get("cwh")%>"/>
																				<%
																					//curr_qsxy!=null&&curr_qsxy.equals(cwxx.get("xydm"))
																					//&&curr_qsnj!=null&&curr_qsnj.equals(cwxx.get("nj"))
																					//====判断是否学院START=====
																					if("xyli".equalsIgnoreCase(resultSet)) {
																						if(xydm.equals(cwxx.get("xydm"))&&nj.equals(cwxx.get("nj"))){//床位学院与寝室学院对应
																							%><font color="green" ><%=cwxx.get("cwh") %><%
																						}else if(cwxx.get("xydm")==null){//未分配
																							%><font color="" ><%=cwxx.get("cwh") %><%
																						}else{//其他学院
																							%><font color="red" >
																							<%=cwxx.get("cwh") %>
																							<%
																						}
																					
																						if((curr_qsxy!=null&&!curr_qsxy.equals(cwxx.get("xydm")))
																						   		||(curr_qsnj!=null&&!curr_qsnj.equals(cwxx.get("nj")))){
																							%>
																							<%=cwxx.get("nj")==null?"":"("+cwxx.get("nj")+")"%><br>
																							<%=cwxx.get("xymc")==null?"":cwxx.get("xymc")%>
																							<% } %>
																					<% //====判断是否学院END===== 
																						//====判断是否专业START===== 
																					}else if("zyli".equalsIgnoreCase(resultSet)) { 
																					
																						if(zydm.equals(cwxx.get("zydm"))){//床位专业对应
																							%><font color="green" ><%=cwxx.get("cwh") %><%
																						}else if(cwxx.get("zydm")==null){//未分配
																							%><font color="" ><b><%=cwxx.get("cwh") %></b><%
																						}else{//其他学院
																							%><font color="red" >
																							<%=cwxx.get("cwh") %>
																					<% }%>																
																					
																					<% //====判断是否专业END===== 
																						//====判断是否班级START===== 
																					}else if("bjli".equalsIgnoreCase(resultSet)) { 
																						
																						if(bjdm.equals(cwxx.get("bjdm"))){//床位学院与寝室学院对应
																								%><font color="green" ><%=cwxx.get("cwh") %><%
																							}else if(cwxx.get("bjdm")==null){//未分配
																								%><font color="" ><b><%=cwxx.get("cwh") %></b><%
																							}else{//其他学院
																								%><font color="red" >
																								<%=cwxx.get("cwh") %>
																						<% }%>
																						<%=cwxx.get("cwbjmc")==null?"":cwxx.get("cwbjmc")%>
																					
																					<% //====判断是否班级END===== 
																						} %>
																				
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
										</tbody>
									</table>
				                </div>
					            </li>
				        	</logic:iterate>
						</ul>
					</logic:notEmpty>
				</div>
				
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