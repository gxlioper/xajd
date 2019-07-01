<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<link rel="stylesheet"
			href="${jQuery}/zTree/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script language="javascript"
			src="${jQuery}/zTree/jquery-ztree-2.5.min.js"></script>
		<script language="javascript" src="${js}/hashMap.js"></script>
		<script type="text/javascript" src="js/comm/ymPrompt.js" ></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<style type="text/css">
			 .ztree button {
				 border: red 0px solid !important;
				 outline: red 0px solid !important;
			 }

			.bodyPerson td {
				border: red 0px solid !important;
				vertical-align: top;
				height: 18px;
				font-weight: normal;
			}
	</style>
	<script language="javascript">
	function initYhsz(){
		var spgw = jQuery("#spgw").val();
		var lcid = jQuery("#lcid").val();
		refreshForm("/xgxt/splcNew.do?method=splcYhsz&spgw="+spgw+"&lcid="+lcid);
	}
		//一切为了南京技师...
		setTimeout("createYhzDiv()",500);

		var userCodeMsg = '请输入用户名/姓名'; 
		var userCodeYxMsg = '请输入用户名/姓名'; 
		//初始化提示信息
		function initData(id, msg){	
			jQuery("#" + id).focus( function() {
				var v = jQuery.trim(jQuery(this).val());
				if (v == msg) {
					jQuery(this).val("");
					jQuery(this).css("color", "");
				}
			});
			jQuery("#" + id).blur( function() {
				var v = jQuery.trim(jQuery(this).val());
				if (v == "") {
					jQuery(this).val(msg);
					jQuery(this).css("color", "#999");
				}
			});
			jQuery("#" + id).blur();
		}
		jQuery(document).ready(function(){ 
			initData('userCode', userCodeMsg);
			initData('userCodeYx', userCodeYxMsg);
		});
		
		//创建用户组Div
		function createYhzDiv(){
		
			jQuery.ajaxSetup({async:false});
			
			//路径
			var url = "general_xtwh_flow.do?method=createYhzDiv&yhszlx=${yhszlx}";	
			//參數
		 	var parameter = {};
			
			jQuery("#CNLTreeMenu1").load(
				url,
				parameter,
				function(){
					createKxyhDiv("");
					createYxyhDiv();
				}
			);
			
			jQuery.ajaxSetup({async:true});
		}
		
		//创建可选用户Div
		function createKxyhDiv(zdm){
			var zdmKey=$('zdm').value;
			if(zdmKey==""){
				zdmKey = "all";
			}
			$(zdmKey).className="";
			$('zdm').value=zdm;
			$(zdm).className="Current";
			
			//路径
			var url = "general_xtwh_flow.do?method=createKxyhDiv&yhszlx=${yhszlx}";	
			var current = "1";
			if($("kxyhCurrent")){
				current = jQuery("#kxyhCurrent").val();
			}
			// 搜索前清空默认值
			var userCode = jQuery("#userCode").val();
			if(userCode == userCodeMsg){
				userCode = "";
			}
			//參數
		 	var parameter = {
		 		"str_zdm":zdm,
		 		"str_yhm":userCode,
		 		"str_gwdm":jQuery("#spgw").val(),
		 		"str_current":current,
		 		"str_init":jQuery("#initKxyh").val(),
		 		"str_sffdy":jQuery("#sffdy").val(),
		 		"str_sfbzr":jQuery("#sfbzr").val()
		 	};


		 	jQuery.ajaxSetup({
				 contentType:"application/x-www-form-urlencoded;charset=utf-8"
			});
			
			jQuery("#div_kxyh").load(
				url,
				parameter,
				function(){
					
				}
			);
		}
		
		//创建已选用户Div
		function createYxyhDiv(){
			
			//路径
			var url = "general_xtwh_flow.do?method=createYxyhDiv&yhszlx=${yhszlx}";	
			var current = "1";
			if($("yxyhCurrent")){
				current = jQuery("#yxyhCurrent").val();
			}
			// 搜索前清空默认值
			var userCodeYx = jQuery("#userCodeYx").val();
			if(userCodeYx == userCodeYxMsg){
				userCodeYx = "";
			}
			//參數
		 	var parameter = {
		 		"str_yhm":userCodeYx,
		 		"str_gwdm":jQuery("#spgw").val(),
		 		"str_current":current
		 	};
			
			jQuery("#div_yxyh").load(
				url,
				parameter,
				function(){
					
				}
			);
		}
		
		//下一页(可选用户)
		function nextKxyhPages(){
		
			jQuery.ajaxSetup({async:false});
			
			var kxyhCurrent = jQuery("#kxyhCurrent").val();
			var kxyhMax = jQuery("#kxyhMax").val();
			jQuery("#initKxyh").val("no");
			
			if(kxyhCurrent == kxyhMax){
				alert("已达到最大页数");
			}else{
				kxyhCurrent = parseInt(kxyhCurrent) + 1;
				jQuery("#kxyhCurrent").val(kxyhCurrent);
				
				var zdm = "";
				jQuery("li").each(function(){
					var id = jQuery(this).attr("id");
					if($(id).className=="Current"){
						zdm = id;
					}
				});	;
				createKxyhDiv(zdm);
			}
			
			jQuery.ajaxSetup({async:true});
		}
		
		//前一页(可选用户)
		function previousKxyhPages(){
			
			jQuery.ajaxSetup({async:false});
			
			var kxyhCurrent = jQuery("#kxyhCurrent").val();
			jQuery("#initKxyh").val("no");
			
			if(kxyhCurrent == "1"){
				alert("已经是第一页了");
			}else{
				kxyhCurrent = parseInt(kxyhCurrent) - 1;
				jQuery("#kxyhCurrent").val(kxyhCurrent);
				
				var zdm = "";
				jQuery("li").each(function(){
					var id = jQuery(this).attr("id");
					if($(id).className=="Current"){
						zdm = id;
					}
				});	;
				createKxyhDiv(zdm);
			}
			
			jQuery.ajaxSetup({async:true});
		}
		
		//初始化(可选用户)
		function initKyyh(){
			jQuery("#kxyhCurrent").val("1");
			jQuery("#kxyhMax").val("1");
			jQuery("#initKxyh").val("yes");
		}
		
		//下一页(可选用户)
		function nextYxyhPages(){
		
			jQuery.ajaxSetup({async:false});
			
			var yxyhCurrent = jQuery("#yxyhCurrent").val();
			var yxyhMax = jQuery("#yxyhMax").val();
			jQuery("#initYxyh").val("no");
			
			if(yxyhCurrent == yxyhMax){
				alert("已达到最大页数");
			}else{
				yxyhCurrent = parseInt(yxyhCurrent) + 1;
				jQuery("#yxyhCurrent").val(yxyhCurrent);
				
				var zdm = "";
				jQuery("li").each(function(){
					var id = jQuery(this).attr("id");
					if($(id).className=="Current"){
						zdm = id;
					}
				});	;
				createYxyhDiv();
			}
			
			jQuery.ajaxSetup({async:true});
		}
		
		//前一页(可选用户)
		function previousYxyhPages(){
			
			jQuery.ajaxSetup({async:false});
			
			var yxyhCurrent = jQuery("#yxyhCurrent").val();
			jQuery("#initYxyh").val("no");
			
			if(yxyhCurrent == "1"){
				alert("已经是第一页了");
			}else{
				yxyhCurrent = parseInt(yxyhCurrent) - 1;
				jQuery("#yxyhCurrent").val(yxyhCurrent);
				
				var zdm = "";
				jQuery("li").each(function(){
					var id = jQuery(this).attr("id");
					if($(id).className=="Current"){
						zdm = id;
					}
				});
				createYxyhDiv();
			}
			
			jQuery.ajaxSetup({async:true});
		}
		
		//保存 已选
		function saveYxyh(){
		
			jQuery.ajaxSetup({async:false});
						
			var url = "general_xtwh_flow.do?method=saveYxyh&yhszlx=${yhszlx}";
			var spyh =new Array();
			jQuery("[name=kxyhArr]:checked").each(function(i){				
				spyh[i]=jQuery(this).val();	
			});
			
			var parameter={}
			parameter["array_spyh"]=escape(spyh.join("!!array!!"));
			parameter["str_gwdm"]=	jQuery("#spgw").val();
			
			var zdm = "";
			jQuery("li").each(function(){
				var id = jQuery(this).attr("id");
				if($(id).className=="Current"){
					zdm = id;
				}
			});
				
			jQuery.post(url,
				parameter,
				function(result){
					createKxyhDiv(zdm);
					createYxyhDiv();
				}
			);
			
			jQuery.ajaxSetup({async:true});
		}
		
		//删除已选
		function deleteYxyh(){
		
			jQuery.ajaxSetup({async:false});
						
			var url = "general_xtwh_flow.do?method=deleteYxyh&yhszlx=${yhszlx}";
			var spyh =new Array();
			jQuery("[name=yxyhArr]:checked").each(function(i){				
				spyh[i]=jQuery(this).val();	
			});
			
			var parameter={}
			parameter["array_spyh"]=escape(spyh.join("!!array!!"));
			parameter["str_gwdm"]=	jQuery("#spgw").val();
			
			var zdm = "";
			jQuery("li").each(function(){
				var id = jQuery(this).attr("id");
				if($(id).className=="Current"){
					zdm = id;
				}
			});
				
			jQuery.post(url,
				parameter,
				function(result){
					createKxyhDiv(zdm);
					createYxyhDiv();
				}
			);
			
			jQuery.ajaxSetup({async:true});
		}
		
		function searchKxyh(){
			var zdm = "";
			jQuery("li").each(function(){
				var id = jQuery(this).attr("id");
				if($(id).className=="Current"){
					zdm = id;
				}
			});
			
			createKxyhDiv(zdm);
		}
		function searchYxyh(){
			/*
			var zdm = "";
			jQuery("li").each(function(){
				var id = jQuery(this).attr("id");
				if($(id).className=="Current"){
					zdm = id;
				}
			});
			*/
			createYxyhDiv();
		}
		function saveShowAlert(){
				if (parent.window){
					refershParent();
				}
		}
	</script>
	</head>
	<body>
		<html:form action="/splcNew" method="post">
			<input type="hidden" name="model" id="model"
				value="<%=request.getParameter("model")%>" />
			<input type="hidden" id="objId"
				value="<%=request.getParameter("objId")%>" />
			<input type="hidden" id="objUrl"
				value="<%=request.getParameter("objUrl")%>" />
			<input type="hidden" name="zdm" id="zdm" value="" />
			<input type="hidden" name="lcid" id="lcid" value="${lcid}" />
			<%--<input type="hidden" name="sfbzr" id="sfbzr" value="${sfbzr}" />
			<input type="hidden" name="sffdy" id="sffdy" value="${sffdy}" />
			--%><input type="hidden" name="gwlx" id="gwlx" value="${gwlx}" />
			<div class="open_win" id="divbase" style="width:100%;height:350px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" class="formlist">
					<thead>
						<tr>
							<th width="600px" height="100%" colspan="4">
								<span>用户选择&nbsp;&nbsp;</span>
								<logic:notEmpty name="gwxx">
									<font>流程名称：
									<logic:iterate id="v" name="gwxx" offset="0" length="1">
										<font color='red'><bean:write name="v" /></font>
									</logic:iterate>
									</font>
									<font>岗位名称：
									<html:select property="spgw" styleId="spgw" onchange="initYhsz()">
									<html:options collection="gwxxList" property="spgw"
											labelProperty="mc" />
									</html:select>
									</font>
									<logic:iterate id="v" name="gwxx" offset="2" length="1">
										<font color='red'><bean:write name="v" /></font>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="gwxx">
									<input type="hidden" name="spgw" id="spgw" value="${spgw}" />
								</logic:empty>
							</th>
						</tr>
					</thead>
					
					<logic:notEmpty name="gwxx">
						<div id="xxPrompt" class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							可选择岗位名称对当前岗位进行用户授权
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none';"></a>
						</div>
					</logic:notEmpty>
					
<%--					<tr class="searchbox">--%>
<%--						<td colspan="4">--%>
<%--							<span> <label>--%>
<%--									请输入用户姓名/登录名--%>
<%--								</label><input type="text" name="" id="yhm" style="display:none" />--%>
<%--								<input--%>
<%--									type="text" class="text_nor" name="userCode" id="userCode"--%>
<%--									onkeydown="if(event.keyCode==13){searchKxyh()}" /> --%>
<%--								是否班主任&nbsp;--%>
<%--								<select name="sfbzr" id="sfbzr" style="width:50px">--%>
<%--										<option value=""></option>--%>
<%--										<option value="1">是</option>--%>
<%--										<option value="2">否</option>--%>
<%--								</select>--%>
<%--								是否辅导员&nbsp;--%>
<%--								<select name="sffdy" id="sffdy" style="width:50px">--%>
<%--										<option value=""></option>--%>
<%--										<option value="1">是</option>--%>
<%--										<option value="2">否</option>--%>
<%--								</select>--%>
<%--									<button type="button" id="findUserCode" onclick="searchKxyh();">查询</button>--%>
<%--							</span>--%>
<%--						</td>--%>
<%--					</tr>--%>
					<tbody id="tbodyList">
						<tr class="searchbox">
							<td width="120px" align="center">
								<span>用户组列表信息</span>
							</td>
							<td width="220px" align="left">
								<span>可选用户&nbsp;<input type="text" class="text_nor" name="userCode" id="userCode" onkeydown="if(event.keyCode==13){searchKxyh()}" onblur="searchKxyh()" style="width:120px;"/></span>
							</td>
							<td width="80px" align="center">
								<span>操作</span>
							</td>
							<td width="220px" align="center">
								<span>已选用户&nbsp;<input type="text" class="text_nor" name="userCodeYx" id="userCodeYx" onkeydown="if(event.keyCode==13){searchYxyh()}" onblur="searchYxyh()" style="width:120px;"/></span>
							</td>
						</tr>
						<tr>
							<!-- 树结构 begin -->
							<td width="120px" valign="top">
								<div class="CNLTreeMenu1" id="CNLTreeMenu1"
									style="height:350px; overflow-x:hidden;overflow-y:auto;">
									<ul id="tree" class="ztree"
										style="overflow-y: auto; overflow-x: auto; height: 350px; margin-top: 0px;"></ul>
								</div>
							</td>
							<!-- 树结构 end -->

							<!-- 可选用户begin -->
							<td width="220px" valign="top" style="vertical-align: top;overflow-y;hidden">

								<jsp:include flush="true" page="/xtwh/comm/splc/kxyhInfo.jsp"></jsp:include>
							</td>
							<!-- 可选用户end -->

							<!-- 操作按钮begin -->
							<td width="80px" align="center">
								<button type="button" id="save" onclick="saveYxyh();">添加</button>
								<br></br>
								<button type="button" id="remove" onclick="deleteYxyh();">删除</button>
								<br></br>

							</td>
							<!-- 操作按钮end -->

							<!-- 已选用户begin -->
							<td width="220px" valign="top"
								style="vertical-align: top; overflow-y;hidden">

								<jsp:include flush="true" page="/xtwh/comm/splc/yxyhInfo.jsp"></jsp:include>

							</td>
							<!-- 已选用户end -->
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveShowAlert();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>

		<script type="text/javascript"> 		 
		
			
		    
			
			
			//加载树结构（组信息加载）
			function refreshTree(result) {
				jQuery.ajaxSetup({async:false});	
				var treeHtml="";
				var blog=false;
				
				
				for(i=0;i<result.length;i++){
					
					treeHtml+="<li id='"+result[i].zdm+"'";
					
					if($("zdm") && $("zdm").value==result[i].zdm){
						treeHtml+="class='Current' ";
						blog=true;
					}
					treeHtml+=">";
					treeHtml+="<a href=\"#\" class='Child' title='"+result[i].zmc+"'";
					treeHtml+="onclick=\"loadYhxxInfo('"+result[i].zdm+"');return false\"";
					treeHtml+="style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>"+result[i].zmc+"</a>";
					treeHtml+="</li>";
					
				}
				
				
				$("tree").innerHTML=treeHtml;
				
				//默认进入时选择first
				if(!blog){
					
					$(result[0].zdm).className="Current";
					$("zdm").value=result[0].zdm;
					$("kxyhPages").value="1";
				
					var url="splcAjax.do?method=loadKxyhInfo&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&kxyhPage=1&yhszlx=${yhszlx}";
					
					jQuery.post(url,function(kxyh){
							
								var jSon=eval(kxyh);
								reloadKxyhInfo(jSon);
							
							}
						);
						
					
					
					yxyhurl="splcAjax.do?method=loadYxyhInfo&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&yxyhPage=1&yhszlx=${yhszlx}";	
					
					//初始页设置
					$("yxyhPages").value="1";
					jQuery.post(yxyhurl,function(yxyh){
								
								var jSon=eval(yxyh)
								if(eval(jSon[0].count)<=10){
									for(i=1;i<jSon.length;i++){
										hidYxyhArr[yxLen]=jSon[i].yhm
										yxLen++;
									}
									
								}else{
									loadAllYxyhInfo();
								}
								reloadYxyhInfo(jSon);
								
							}
					
					)
				}
				jQuery.ajaxSetup({async:true});
			}
			
			function loadAllYxyhInfo(){
				url="splcAjax.do?method=loadAllYxyhInfo&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&yhszlx=${yhszlx}";	
				jQuery.ajax({
					type:'post',
					url:url,
					dataType:'json',
					async: false,
					success:function(yxyh){
						for(i=0;i<yxyh.length;i++){
							hidYxyhArr[yxLen]=yxyh[i].yhm
							yxLen++;
						}
					}
				});
				
			}
			
			
			function reloadKxyhInfo(kxyh){
				var kxyhHtml="";
				
				for(i=1;i<kxyh.length;i++){
					kxyhHtml+="<tr id='"+kxyh[i].yhm+"' ></tr>";
					kxyhHtml+="<td>";
					kxyhHtml+="<input name='kxyhArr' id='kxyh_"+i+"' type='checkbox' value='"+kxyh[i].yhm+"'/><span>"+kxyh[i].xm+"("+kxyh[i].yhm+")</span>";
					kxyhHtml+="<input name='kxyhxmArr' id='kxyhxm_"+i+"' type='hidden' value='"+kxyh[i].xm+"'/>";
					kxyhHtml+="</td>";
					kxyhHtml+="</tr>";
				}
				
				if(kxyh.length<11){
					for(i=0;i<11-kxyh.length;i++){
						kxyhHtml+="<tr >";
						kxyhHtml+="<td>&nbsp;";
						kxyhHtml+="</td>";
						kxyhHtml+="</tr>";
					}
				}
				$("kxyhNoteLen").value=kxyh[0].count;
				$("p_kxyhPages").innerHTML=$("kxyhPages").value;
				
				
				var p_kxyhMaxPages=1;
				if(eval(kxyh[0].count)%10==0){
					p_kxyhMaxPages=eval(kxyh[0].count)/10;
				}else {
					var count=eval(kxyh[0].count);
					p_kxyhMaxPages=(count-eval(count)%10)/10+1;
				}
				
			
				$("p_kxyhMaxPages").innerHTML=p_kxyhMaxPages;
				$("p_kxyhNoteLen").innerHTML=kxyh[0].count;
				
				
				if(eval(p_kxyhMaxPages)<=1){
					$("kxyhTurnPage").style.display="none";
					$("showkxyhPages").style.display="none"
				}else{
					$("showkxyhPages").style.display=""
					$("kxyhTurnPage").style.display="";
				}
				
				if($("kxyhPages").value=="1"){
					$("kxyhFirst").className ='first_disable';
					$("kxyhAscend").className ='left_disable';
					$("kxyhFirst").disabled =true;
					$("kxyhAscend").disabled =true;
					$("kxyhNext").className="right";
					$("kxyhLast").className="last";
					$("kxyhNext").disabled =false;
					$("kxyhLast").disabled =false;
					
				}else if(eval($("kxyhPages").value)==eval(p_kxyhMaxPages)){
					$("kxyhNext").className="right_disable";
					$("kxyhLast").className="last_disable";
					$("kxyhNext").disabled =true;
					$("kxyhLast").disabled =true;
					$("kxyhFirst").className="first";
					$("kxyhAscend").className="left";
					$("kxyhFirst").disabled =false;
					$("kxyhAscend").disabled =false;
				}else{
					$("kxyhFirst").className="first";
					$("kxyhAscend").className="left";
					$("kxyhNext").className="right";
					$("kxyhLast").className="last";
					$("kxyhFirst").disabled =false;
					$("kxyhAscend").disabled =false;
					$("kxyhNext").disabled =false;
					$("kxyhLast").disabled =false;
				}
				jQuery("#ul_eligiblyUser").html("");
				jQuery("#ul_eligiblyUser").append(kxyhHtml);
				
								   
			}
			
			function reloadYxyhInfo(yxyh){
				var yxyhHtml="";
				
				
				for(i=1;i<yxyh.length;i++){
					yxyhHtml+="<tr id='"+yxyh[i].yhm+"'></tr>";
					yxyhHtml+="<td>";
					yxyhHtml+="<input name='yxyhArr' id='yxyh_"+(i-1)+"' type='checkbox' "+yxyh[i].dis+" value='"+yxyh[i].yhm+"'/><span>"+yxyh[i].xm+"("+yxyh[i].yhm+")</span>";
					yxyhHtml+="<input name='yxyhmArr' id='yxyhm_"+(i-1)+"' type='hidden' value='"+yxyh[i].yhm+"'/>";
					yxyhHtml+="<input name='yxyhxmArr' id='yxyhxm_"+(i-1)+"' type='hidden' value='"+yxyh[i].xm+"'/>";
					yxyhHtml+="</td>";
					yxyhHtml+="</tr>";
				}
				
				if(yxyh.length<11){
					for(i=0;i<11-yxyh.length;i++){
						yxyhHtml+="<tr ></tr>";
						yxyhHtml+="<td>&nbsp;";
						yxyhHtml+="</td>";
						yxyhHtml+="</tr>";
					}
				}
				
				$("yxyhNoteLen").value=yxyh[0].count;
				$("p_yxyhPages").innerHTML=$("yxyhPages").value;
				
				var p_yxyhMaxPages=1;
				if(eval(yxLen)%10==0){
					p_yxyhMaxPages=eval(yxLen)/10;
				}else {
					var count=eval(yxLen);
					p_yxyhMaxPages=(count-eval(count)%10)/10+1;
				}
				$("p_yxyhMaxPages").innerHTML=p_yxyhMaxPages;
				$("p_yxyhNoteLen").innerHTML=yxLen;
				
				
				if(yxLen<=10){
					$("yxyhTurnPage").style.display="none";
					$("showyxyhPages").style.display="none";
				}else{
					$("yxyhTurnPage").style.display="";
					$("showyxyhPages").style.display="";
				}
				
				if($("yxyhPages").value=="1"){
					$("yxyhFirst").className ='first_disable';
					$("yxyhAscend").className ='left_disable';
					$("yxyhFirst").disabled =true;
					$("yxyhAscend").disabled =true;
					$("yxyhNext").className="right";
					$("yxyhLast").className="last";
					$("yxyhNext").disabled =false;
					$("yxyhLast").disabled =false;
					
				}else if(eval($("yxyhPages").value)==eval(p_yxyhMaxPages)){
					$("yxyhNext").className="right_disable";
					$("yxyhLast").className="last_disable";
					$("yxyhNext").disabled =true;
					$("yxyhLast").disabled =true;
					$("yxyhFirst").className="first";
					$("yxyhAscend").className="left";
					$("yxyhFirst").disabled =false;
					$("yxyhAscend").disabled =false;
				}else{
					$("yxyhFirst").className="first";
					$("yxyhAscend").className="left";
					$("yxyhNext").className="right";
					$("yxyhLast").className="last";
					$("yxyhFirst").disabled =false;
					$("yxyhAscend").disabled =false;
					$("yxyhNext").disabled =false;
					$("yxyhLast").disabled =false;
				}
				
				jQuery("#table_selectedUser").html("");
				jQuery("#table_selectedUser").append(yxyhHtml);
							   
			}
			
			
			
			function loadYhxxInfo(zdm){
				var zdmKey=$('zdm').value;
				$(zdmKey).className="";
				$('zdm').value=zdm;
				$(zdm).className="Current";
				
				var kxyhArr=document.getElementsByName("kxyhArr");
				var kxyhxmArr=document.getElementsByName("kxyhxmArr");
				
				var yxyhArr=document.getElementsByName("yxyhArr");
				var yxyhxmArr=document.getElementsByName("yxyhxmArr");
				
				var n=0;
				
				var strArr="";
			
				for(i=0;i<hidYxyhArr.length;i++){
					strArr+=hidYxyhArr[i]+"!!@@!!";
				}
				
				var parameter = {
					"strArr":strArr
				};
				
				var searchUserName=$("userCode").value;
				jQuery.ajaxSetup({async:false});
				var url="splcAjax.do?method=loadKxyhByDel&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&kxyhPage=1&searchUserName="+searchUserName+"&yhszlx=${yhszlx}";
					jQuery.ajax({
							type:'post',
							url:url,
							data:parameter,
							dataType:'json',
							async: false,
							success:function(kxyh){
								reloadKxyhInfo(kxyh);
							}
						});
						
					var url="splcAjax.do?method=loadYxyhByDel&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&yxyhPage=1&yhszlx=${yhszlx}";	
					
					jQuery.ajax({
							type:'post',
							url:url,
							data:parameter,
							dataType:'json',
							async: false,
							success:function(yxyh){
								reloadYxyhInfo(yxyh);
							}
						});
					
				jQuery.ajaxSetup({async:true});		   
			}
			
			function reloadYhxxInfo(strArr){
			
			 	var parameter = {
					"strArr":strArr
				};
				var url="splcAjax.do?method=loadKxyhInfo&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&kxyhPage=1&yhszlx=${yhszlx}";
				jQuery.ajaxSetup({async:false});
					jQuery.ajax({
							type:'post',
							url:url,
							data:parameter,
							dataType:'json',
							async: false,
							success:function(kxyh){
								reloadKxyhInfo(kxyh);
							}
						});
						
					var url="splcAjax.do?method=loadYxyhByDel&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&yxyhPage=1&yhszlx=${yhszlx}";	
					
					jQuery.ajax({
							type:'post',
							url:url,
							data:parameter,
							dataType:'json',
							async: false,
							success:function(yxyh){
								reloadYxyhInfo(yxyh);
							}
						});
				jQuery.ajaxSetup({async:true});		   
			}
			
			function reloadYhxxByDel(strArr){
			
			 	var parameter = {
					"strArr":strArr
				};
				var url="splcAjax.do?method=loadKxyhByDel&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&kxyhPage=1&yhszlx=${yhszlx}";
				jQuery.ajaxSetup({async:false});
					jQuery.ajax({
							type:'post',
							url:url,
							data:parameter,
							dataType:'json',
							async: false,
							success:function(kxyh){
								reloadKxyhInfo(kxyh);
							}
						});
						
					var url="splcAjax.do?method=loadYxyhByDel&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&yxyhPage=1&yhszlx=${yhszlx}";	
					
					jQuery.ajax({
							type:'post',
							url:url,
							data:parameter,
							dataType:'json',
							async: false,
							success:function(yxyh){
								reloadYxyhInfo(yxyh);
							}
						});
				jQuery.ajaxSetup({async:true});
							   
			}
			
			
			function zTreeOnClick(event,treeId,treeNode){
			   if(treeNode.id != ""){
			       eligiblyParams["roleId"] = treeNode.id;
				   eligiblyListing();
			   }
			}
			
			function search(){
					loadYhxxInfo($('zdm').value);
				
			}
			
			// ======================选择用户========================
			function addUser(){
				
				var kxyhArr=document.getElementsByName("kxyhArr");
				var kxyhxmArr=document.getElementsByName("kxyhxmArr");
				
				var yxyhArr=document.getElementsByName("yxyhArr");
				var yxyhxmArr=document.getElementsByName("yxyhxmArr");
				
				var n=0;
				
				var strArr="";
				var flag=false;
				for(i=0;i<kxyhArr.length;i++){
					if(kxyhArr[i].checked){
						
						hidYxyhArr[yxLen]=kxyhArr[i].value;
						yxLen++;
						flag=true;
					}
				}
				
				if(flag){
					for(i=0;i<hidYxyhArr.length;i++){
						strArr+=escape(hidYxyhArr[i])+"!!@@!!";
					}
					reloadYhxxInfo(strArr)
				}else{
					alertError("请选择需要分配的用户!");	
				}
				
				$("checkAkxyh").checked=false;
				$("checkAyxyh").checked=false;
			}
			// ======================选择用户 end========================
			
			// ======================全选 用户========================
<%--			function addAllUser(){--%>
<%--				--%>
<%--				var kxyhArr=document.getElementsByName("kxyhArr");--%>
<%--				for(i=0;i<kxyhArr.length;i++){--%>
<%--					kxyhArr[i].checked=true;--%>
<%--				}--%>
<%--				--%>
<%--			}--%>
			// ======================全选 用户 end========================
			
			// ======================删除用户========================
			function delUser(){
				
				var kxyhArr=document.getElementsByName("kxyhArr");
				var kxyhxmArr=document.getElementsByName("kxyhxmArr");
				
				var yxyhArr=document.getElementsByName("yxyhArr");
				var yxyhxmArr=document.getElementsByName("yxyhxmArr");
				
				var n=0;
				
				var strArr="";
				var anewArr=new Array();
				
				var flag=false;
				for(i=0;i<yxyhArr.length;i++){
					if(yxyhArr[i].checked){
						flag=true;
						for(j=0;j<hidYxyhArr.length;j++){
							if(hidYxyhArr[j]==yxyhArr[i].value){
								hidYxyhArr[j]="removeThisMember";
								yxLen--;
							}
						}

					}
				}
				
				if(!flag){
					alertError("请选择需要删除的用户!");
				}
				
				var m=0;
				for(i=0;i<hidYxyhArr.length;i++){
					if(hidYxyhArr[i]!="removeThisMember"){
						anewArr[m]=hidYxyhArr[i];
						m++;
					}
				}
				hidYxyhArr=anewArr;
				
				for(i=0;i<hidYxyhArr.length;i++){
					strArr+=hidYxyhArr[i]+"!!@@!!";
				}
				
				reloadYhxxByDel(strArr)
				
				$("checkAkxyh").checked=false;
				$("checkAyxyh").checked=false;
			}
			
			// ======================删除用户========================
<%--			function delAllUser(){--%>
<%--				--%>
<%--				var yxyhArr=document.getElementsByName("yxyhArr");--%>
<%--				for(i=0;i<yxyhArr.length;i++){--%>
<%--					if(yxyhArr[i].disabled!=true){--%>
<%--						yxyhArr[i].checked=true;--%>
<%--					}--%>
<%--				}--%>
<%--				--%>
<%--				--%>
<%--			}--%>
			// ======================删除用户 end========================
			
			
			// ====================保存操作结果====================
			// 保存按钮已删除 改方法已无用
			function saveShgw(){
				var url="/xgxt/splcNew.do?method=splcYhsz&doType=save&yhszlx=${yhszlx}";
				confirmInfo('确定要保存已分配的岗位用户信息吗？',function(t){
					if (t=='ok'){
						var yxyhArr=document.getElementsByName("yxyhArr");
						
						jQuery('input[name=yxyhArr]').remove();
						
						for(i=0;i<hidYxyhArr.length;i++){
							var obj=document.createElement("input");
							obj.name="yxyhArr";
							obj.type="hidden";
							obj.value=hidYxyhArr[i];
							document.forms[0].appendChild(obj);
						}
						refreshForm(url);
					}
				})		
				
				
			}
			
			function updateLcsz(){
				var url="splcAjax.do?method=updateLcsz&yhszlx=${yhszlx}";	
				jQuery.ajaxSetup({async:false});	
				jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false
					});
				jQuery.ajaxSetup({async:true});
			}
			
			function checkAllKxyh(){
				var kxyhArr=document.getElementsByName("kxyhArr");
				for(i=0;i<kxyhArr.length;i++){
					if(kxyhArr[i].disabled==false){
						kxyhArr[i].checked=$("checkAkxyh").checked;
					}
				}
			}
			
			function checkAllYxyh(){
				var yxyhArr=document.getElementsByName("yxyhArr");
				for(i=0;i<yxyhArr.length;i++){
					if(yxyhArr[i].disabled==false){
						yxyhArr[i].checked=$("checkAyxyh").checked;
					}
				}
			}
	  </script>
		<logic:present name="message">
			<script>
				alertInfo("${message}!");
			</script>
		</logic:present>
	</body>
</html>
