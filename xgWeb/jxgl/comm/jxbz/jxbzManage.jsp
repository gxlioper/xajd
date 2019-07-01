<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/plugins/ringhtMenu/rightMenu.css" /> 
	       
	    <script type="text/javascript" src="js/jquery/plugins/ringhtMenu/contextMenu.js"></script>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//查询结果集
		function searchRs(){

			jQuery.ajaxSetup({async: false});
		
			//最大级别编制
			creatMaxbzHtml();
			showDefauleMsg();
			defauleNextJxbz();
			
			//创建Js
			creatJs();	
			jQuery.ajaxSetup({async: true});
		}	
		
		//显示初始化语句
		function showDefauleMsg(){
		
			var lis = jQuery('li',jQuery('#div_maxBz')).length;
			var firstFw = $("firstFw").value;
			
			if(lis == 0 && firstFw==""){
				$("firstFw").value="no";
				$("div_nextBz").innerHTML = "";
				var xn = $("xn").value;
				var msg = xn+"学年军训编制尚未开始，请先维护一最高级别编制"
				confirmInfo(msg,defauleJxbz);
			}
		}
		
		function defauleNextJxbz(){
			var lis = jQuery('li',jQuery('#div_maxBz')).length;
			if(lis > 0){
				creatNextbzHtml();	
			}		
		}
		
		//初始化军训编制
		function defauleJxbz(tag){
		
			if(tag == "ok"){
				var url = "/xgxt/jxglJxbz.do?method=jxbzUpdate";
					url+= "&menuId=1_1";
					url+= "&czlx=same";
				showTopWin(url,500,350);
			}
		}
		
		//最大级别编制
		function creatMaxbzHtml(){
			
			var url = "jxglJxbz.do?method=getMaxbzHtml";
			var checkedBzdm = "";
			if($("checkedBzdm")){
				checkedBzdm = $("checkedBzdm").value;
			}
			
			//参数
		 	var parameter = {
				"checkedBzdm":checkedBzdm
			};
			
			jQuery("#div_maxBz").load(url,parameter,function(){});
		}	
		
		//次级别编制
		function creatNextbzHtml(){
			
			var url = "jxglJxbz.do?method=getNextbzHtml";
			var checkedBzdm = $("checkedBzdm").value;

			//参数
		 	var parameter = {
				"checkedBzdm":checkedBzdm
			};
			
			jQuery("#div_nextBz").load(url,parameter,function(){});
		}	
		
		//创建Js
		function creatJs(){
			
			var url = "jxglJxbz.do?method=getJxbzJs";
			//参数
		 	var parameter = {

			};
			
			jQuery("#div_js").load(url,parameter,function(){});
		}	
		
		//展示军训编制页面
		function showJxbzUpdate(id,czlx){
			
			var minBz = $("minBz").value;
			var bzdj = id.split("_")[0];
			var url = "";
			
			if(czlx == "next" && bzdj == (minBz-1)){
				url = "/xgxt/jxglJxbz.do?method=jxbzBjfp";
				url+= "&menuId="+id;
				url+= "&czlx="+czlx;
				showTopWin(url,800,600);
			}else{
				url = "/xgxt/jxglJxbz.do?method=jxbzUpdate";
				url+= "&menuId="+id;
				url+= "&czlx="+czlx;
				showTopWin(url,500,350);
			}
		}
		
		//删除军训编制
		function delJxbz(tag){
			if(tag == "ok"){
				var url = "/xgxt/jxglJxbz.do?method=delJxbz";
				
				var menuId = $("menuId").value;
				//参数
			 	var parameter = {
			 		"menuId":menuId
				};
				
				jQuery.post(url,parameter,function(result){doSuccess(result);searchRs();});
			}
			searchRs();
		}	
		
		//点击编制
		function clickBz(obj){
			var id=obj.id;
			var bzdm = id.split("_")[1];
			var liId = "li_"+bzdm;
			
			$("checkedBzdm").value = bzdm;
			
			var as = getElementsByClass('current',document,'li');
			for(var i=0;i<as.length;i++){
				as[i].className = "";
			}
			
			obj.parentNode.className = "current";
			
			//次级别编制
			setTimeout("creatNextbzHtml()",500);
			//创建Js
			setTimeout("creatJs()",1000);
		}

		function init(){
			jQuery(function(){
				
				jQuery(this).not(jQuery("a[name=jxbz]")).mouseup(function(){
					
					jQuery("#smartMenu_b").attr("style","display:none");
					
				});
				
			})
		}
		setTimeout("init()",3000);
		</script>
	</head>
	<body onload="searchRs()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
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
				1.鼠标移动到想操作编制上，单击<font color="blue">右键</font>，可以执行相关操作。</br>
				<span id="div_help" style="display: none">
				2.执行<font color="blue">增加同级编制</font>，可以增加与点击编制同级的编制。</br>
				3.执行<font color="blue">增加子级编制</font>，可以增加该级别的下属编制。</br>
				4.执行<font color="blue">删除本编制</font>，将删除该编制，包括<font color="blue">下属编制</font>已经<font color="blue">参训学生</font>。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/jxglJxbz">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="menuId" value="" />	
			<input type="hidden" id="minBz" value="${minBz }" />
			<input type="hidden" id="xn" value="${xn }" />
			<input type="hidden" id="firstFw" value="${firstFw }" />
			<!-- 隐藏域 end-->
			
			<!-- 过滤条件 -->
			<div style="display:none">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 过滤条件 end-->
			
			<!-- 编制DIV -->
			<div class="main_function">
				<!-- 最大级别编制 -->
				<div class="function_list01" id="div_maxBz">

  				</div>
  				<!-- 最大级别编制 end -->
  				
  				<!--  -->
  				<div class="function_list02" id="div_nextBz" style="overflow: scroll;overflow-x:hidden; height: 500px">
				  	
				</div>
  				<!--  -->
  				 <div class="function_list03"></div>
			</div>
			<!-- 编制DIV end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		
		<!-- Js Div -->
		<div id="div_js">

		</div>
		<!-- Js Div end -->
	</body>
</html>