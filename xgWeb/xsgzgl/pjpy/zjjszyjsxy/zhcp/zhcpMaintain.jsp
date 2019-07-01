<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			var id = "left_a_0";
			if($(id)){
				$(id).click();
			}
			
			
		}
		
		//按钮控制
		function controlBtn(){
		
			var xmdm = $("shxm").value;
			
			if(xmdm == "zd1"){
				if($("li_js")){//计算
					$("li_js").style.display="";
				}
				if($("li_sz")){//设置
					$("li_sz").style.display="";
				}
				if($("li_tj")){//统计
					$("li_tj").style.display="";
				}
				
				if($("li_bc")){//保存
					$("li_bc").style.display="none";
				}
				if($("li_tij")){//提交
					$("li_tij").style.display="none";
				}
				if($("li_dc")){//导出
					$("li_dc").style.display="none";
				}
				if($("li_dr")){//导入
					$("li_dr").style.display="none";
				}
			}else{
				if($("li_js")){//计算
					$("li_js").style.display="none";
				}
				if($("li_sz")){//设置
					$("li_sz").style.display="none";
				}
				if($("li_tj")){//统计
					$("li_tj").style.display="none";
				}
				
				if($("li_bc")){//保存
					$("li_bc").style.display="";
				}
				if($("li_tij")){//提交
					$("li_tij").style.display="";
				}
				if($("li_dc")){//导出
					$("li_dc").style.display="";
				}
				if($("li_dr")){//导入
					$("li_dr").style.display="";
				}
			}
		}
		
		//查询结果集
		function searchRs(){
			//按钮控制
			controlBtn();
			
			var url = "general_zhcp_ajax.do?method=getZhcpInfoList";
			var czxm = $("czxm").value;
			var ie = "ie";
			
			var otherValue = [czxm,ie];

			//showWaitingDiv("1000");
			
			if(checkSearch()){
				searchRsByAjax(url,otherValue);
				$("is_default").value = "no";
			}
		}
		
		//检验可否查询
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;
				
				if(xn_num != "1"){
					alertError("学年条件不可为空，且只能选择一个！");
					flag = false;
				}else if( xq_num != "1"){
					alertError("学期条件不可为空，且只能选择一个！");
					flag = false;
				}
			}
			return flag;
		}
		
		//保存综合测评分
		function saveZhcpf(tag){
			
			if(tag == "ok"){
				var czxm = $("czxm").value;
				
				var url="zjjs_zhcp_ajax.do?method=";
					
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//学年
				var i = 0;
				var xn = new Array();   
				jQuery("a[name=a_name_xn]").each(function(){
					var id = jQuery(this).attr("id");
					xn[i] = id.replace("a_id_","");
					i++;
				});
				
				//学期
				i = 0;
				var xq = new Array();   
				jQuery("a[name=a_name_xq]").each(function(){
					var id = jQuery(this).attr("id");
					xq[i] = id.replace("a_id_","");
					i++;
				});
				
				//学号
				i = 0;
				var xh = new Array();   				  
				jQuery("input[name=input_xh]").each(function(){xh[i] = jQuery(this).val();i++;});
				
				//申请分
				i = 0;
				var dyf = new Array();   				  
				jQuery("input[name=input_dyf]").each(function(){
					if(jQuery(this).val()!=""){
						dyf[i] = jQuery(this).val();
					}else{
						dyf[i] = " ";
					}
					i++;
				});
				
				//加分
				i = 0;
				var addf = new Array();   				  
				jQuery("input[name=input_addf]").each(function(){
					if(jQuery(this).val()!=""){
						addf[i] = jQuery(this).val();
					}else{
						addf[i] = " ";
					}
					i++;
				});
				
				//减分
				i = 0;
				var decf = new Array();   				  
				jQuery("input[name=input_decf]").each(function(){
					if(jQuery(this).val()!=""){
						decf[i] = jQuery(this).val();
					}else{
						decf[i] = " ";
					}
					i++;
				});
				
				//原因
				i = 0;
				var yy = new Array();   				  
				jQuery("input[name=input_yy]").each(function(){
					if(jQuery(this).val()!=""){
						yy[i] = escape(jQuery(this).val());
					}else{
						yy[i] = " ";
					}
					i++;
				});
				
				//晨跑分
				i = 0;
				var cpf = new Array();   				  
				jQuery("input[name=input_cpf]").each(function(){
					if(jQuery(this).val()!=""){
						cpf[i] = jQuery(this).val();
					}else{
						cpf[i] = " ";
					}
					i++;
				});
				//ISEDIT
				i = 0;
				var isEditArr = new Array();   				  
				jQuery("input[name=isEditArr]").each(function(){
					if(jQuery(this).val()!=""){
						isEditArr[i] = jQuery(this).val();
					}else{
						isEditArr[i] = " ";
					}
					i++;
				});
				
				//参数
			 	var parameter;
				
				if(czxm == "zd2"){//德育分
				
					url+="saveDyf";
					
					//参数
				 	var parameter = {
						"xn":xn.join("!!@@!!"),
						"xq":xq.join("!!@@!!"),
						"xh":xh.join("!!@@!!"),
						"dyf":dyf.join("!!@@!!")
					};
				
				}else if(czxm == "zd3"){//智育分
					url+="saveZyf";
					
					//参数
				 	var parameter = {
						"xn":xn.join("!!@@!!"),
						"xq":xq.join("!!@@!!"),
						"xh":xh.join("!!@@!!"),
						"addf":addf.join("!!@@!!"),
						"decf":decf.join("!!@@!!"),
						"yy":yy.join("!!@@!!"),
						"isEditArr":isEditArr.join("!!@@!!")
					};
					
				}else if(czxm == "zd4"){//体育分
				
					url+="saveTyf";
					
					//参数
				 	var parameter = {
						"xn":xn.join("!!@@!!"),
						"xq":xq.join("!!@@!!"),
						"xh":xh.join("!!@@!!"),
						"cpf":cpf.join("!!@@!!")
					};
				}
				
				if(checkSearch()){
					jQuery.post(url,parameter,function(result){
						$("had_edit").value = "no";
						alertInfo(result);
						searchRs();
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
					});
				}
			}else{
				$("had_edit").value = "no";
			}
		}
		
		//保存综合测评分
		function submitZhcpf(tag){
			
			if(tag == "ok"){
			
				setSearchTj();
				
				var czxm = $("czxm").value;
				
				var url="zjjs_zhcp_ajax.do?method=submitZhcpf";
				
				//模糊查询
				var input_mhcx = "";
				if($("input_mhcx")){
					input_mhcx = $("input_mhcx").value;
				}
				
				var mhcx_lx = "";
				if($("mhcx_lx")){
					mhcx_lx = $("mhcx_lx").value;
				}
			
				//点击查询
				var searchLx = new Array();
				var searchTj = new Array();
				var searchTjz = new Array();
				
				var n=0;
				var m=3;
				
				searchLx[0]="xy";
				searchLx[1]="zy";
				searchLx[2]="bj";
				
				for(var i=0;i<jytj.length;i++){
					searchLx[m]=jytj[i];
					m++;
				}
			
				var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
					
				for(var j=0;j<tj_num;j++){
					var obj = $("searchTjDiv").getElementsByTagName('input')[j];
					searchTj[n]=obj.name;
					searchTjz[n]=escape(obj.value);
					n++;
				}
					
				//其他数据
			 	var parameter = {
			 		"czxm":czxm,
					"searchTj":searchTj.join("!!@@!!"),
					"searchTjz":searchTjz.join("!!@@!!"),
					"searchLx":searchLx.join("!!@@!!")
				};			
					
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
							
				jQuery.post(url,parameter,function(result){
					alertInfo(result);
					searchRs();
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				});
			}
		}
		
		//检查是否修改
		function saveMethod(){
			confirmInfo('您已经修改了相关分数，请问是否需要保存？',saveZhcpf);
		}
		
		//显示分数修改Div
		function showFsxgDiv(xh,xgf,lx){	
			
			if(xgf!="" && xgf!=null){
				$("input_xgf").value = xgf;
			}else{
				$("input_xgf").value = "";
			}
			
			if(xh!="" && xh!=null){
				$("input_xgf_xh").value = xh;
			}else{
				$("input_xgf_xh").value = "";
			}
	
			if(lx!="" && lx!=null){
				$("input_xgf_lx").value = lx;
			}else{
				$("input_xgf_lx").value = "";
			}
			
			tipsWindown("系统提示","id:div_xgf","350","150","true","","true","id");
		}
		
		//保存修改分
		function saveXgf(tag){
		
			var xgf = $("input_xgf").value;
			var xgf_xh = $("input_xgf_xh").value;
			var lx = $("input_xgf_lx").value;

			if(xgf == ""){
				alertError("修改分不能为空");
				return false;
			}
			
			if(tag == "ok"){
				
				if(checkSearch()){
					var url;
					var parameter;
					
					//学年
					var i = 0;
					var xn = new Array();   
					jQuery("a[name=a_name_xn]").each(function(){
						var id = jQuery(this).attr("id");
						xn[i] = id.replace("a_id_","");
						i++;
					});
					
					//学期
					i = 0;
					var xq = new Array();   
					jQuery("a[name=a_name_xq]").each(function(){
						var id = jQuery(this).attr("id");
						xq[i] = id.replace("a_id_","");
						i++;
					});
					
					url="zjjs_zhcp_ajax.do?method=saveXgf";
				
					//参数
				 	parameter = {
				 		"xn":xn[0],
				 		"xq":xq[0],
						"xh":xgf_xh,
						"xgf":xgf,
						"czxm":lx
					};
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,function(result){
						alertInfo(result);
						searchRs();
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						closeWindown();
					});	
				}
			}
		}
		
		//显示原因Div
		function showYyDiv(xh,yy,sftj){	
			
			if(xh!="" && xh!=null){
				$("input_yy_xh").value = xh;
			}else{
				$("input_yy_xh").value = "";
			}

			if(yy!="" && yy!=null){
				jQuery("#textarea_yy").text(yy);
			}else{
				$("textarea_yy").value = "";
			}
			
			var id = "input_yy_"+xh;
			if($(id).value != ""){
				$("textarea_yy").value = $(id).value;
			}

			if(sftj == "yes"){
				$("btn_bc_yy").style.display="none";
			}else{
				$("btn_bc_yy").style.display="";
			}
			
			tipsWindown("系统提示","id:div_yy","350","210","true","","true","id");
		}
		
		//保存原因
		function saveYy(){
		
			setHadEdit();
			
			var xh = $("input_yy_xh").value;
			var yy = $("textarea_yy").value;
			
			var id = "input_yy_"+xh;
			
			if(yy != ""){
				$(id).value = yy;
			}else{
				$(id).value = "";
			}
			
			closeWindown();
		}

		function mbsc(){
			setSearchTj();
				
			var czxm = jQuery("#shxm").val();
			var url="zjjs_zhcp_ajax.do?method=submitZhcpf";
				
			//点击查询
			var searchLx = ['xy','zy','bj'];
			var searchTj = new Array();
			var searchTjz = new Array();
				
			var n=0;
			var m=3;
				
			for(var i=0;i<jytj.length;i++){
				searchLx[m]=jytj[i];
				m++;
			}
			
			var tj_num = jQuery('input',jQuery('#searchTjDiv')).length;
					
			for(var j=0;j<tj_num;j++){
				var obj = jQuery('input',jQuery('#searchTjDiv'))[j];
				searchTj[n]=jQuery(obj).attr('name');
				searchTjz[n]=escape(jQuery(obj).val());
				n++;
			}
					
			//其他数据
			var parameter = {
			 	"czxm":czxm,
				"searchTj":searchTj.join("!!@@!!"),
				"searchTjz":searchTjz.join("!!@@!!"),
				"searchLx":searchLx.join("!!@@!!")
			};		
			
			expData('zjjs_zhcp_ajax.do?method=mbsc&czxm='+parameter.czxm+'&searchTj='+parameter.searchTj+'&searchTjz='+parameter.searchTjz+'&searchLx='+parameter.searchLx);
		}

		
		// ----------------------made by qlj begin----------------
		
		//-----------------------综测信息统计----------------------
		function zcxxtj(){
		
			var bjlen=jQuery("a[name=a_name_bj]").length;
			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var xq_num =  jQuery("a[name=a_name_xq]").length;
				
			
			if(bjlen!=1){
				alertInfo("请选择一个班级进行统计!",function(t){
					if(t=="ok"){
						return false;
					}
				});
			}else if(xn_num != "1"){
				
				alertInfo("学年条件不可为空，且只能选择一个！");
				flag = false;
			
			}else if( xq_num != "1"){
				
				alertInfo("学期条件不可为空，且只能选择一个！");
				flag = false;
			
			}else{

				setSearchTj();
				
				//点击查询
				var searchLx = new Array();
				var searchTj = new Array();
				var searchTjz = new Array();
				
				var n=0;
				var m=3;
				
				searchLx[0]="xy";
				searchLx[1]="zy";
				searchLx[2]="bj";
				
				for(var i=0;i<jytj.length;i++){
					searchLx[m]=jytj[i];
					m++;
				}
			
				var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
					
				for(var j=0;j<tj_num;j++){
					var obj = $("searchTjDiv").getElementsByTagName('input')[j];
					searchTj[n]=obj.name;
					searchTjz[n]=escape(obj.value);
					n++;
				}
					
				//其他数据
			 	var parameter = {
					"searchTj":searchTj.join("!!@@!!"),
					"searchTjz":searchTjz.join("!!@@!!"),
					"searchLx":searchLx.join("!!@@!!")
				};			
				var url='/xgxt/zjjs_zhcp_ajax.do?method=printZhtj';
				url+='&searchTj='+parameter.searchTj;
				url+='&searchTjz='+parameter.searchTjz;
				url+='&searchLx='+parameter.searchLx;
				
				expData(url);
			}
		}
		//-----------------------综测信息统计 ----------------------
		
		// -------------------显示综测比例信息列表 begin---------------------
		function showZcblDiv(){
			
			jQuery.ajaxSetup({async:false});
			var url="/xgxt/zjjs_zhcp_ajax.do?method=showZcblInfo";
			var html="";
			$("div_blsz_blxx").innerHTML="";
			
			jQuery.post(url,function(result){
				var data=eval(result);
				html+="<table align='center' class='formlist'>";
				html+="<thead><tr><th colspan='2'><span>综测比例设置</span></th></tr></thead>";
				html+="<tbody>";
				for(var i=0;i<data.length;i++){
					var trHtml="<tr>";
					trHtml+="<th>";
					trHtml+="<input type='hidden' name='xmdmArr' id='xmdm_"+i+"' value='"+data[i].xmdm+"' />"
					trHtml+="<input type='hidden' name='bldmArr' id='bldm_"+i+"' value='"+data[i].bldm+"' />";
					trHtml+="<input type='hidden' name='xmmcArr' id='xmmc_"+i+"' value='"+data[i].xmmc+"' />";
					trHtml+=data[i].xmmc+data[i].blmc;	
					trHtml+="</th>";
					trHtml+="<td>";
					trHtml+="<input type='text' name='blArr' id='bl_"+i+"' value='"+data[i].bl+"' maxlength='10' onkeyup='checkInputNum(this)' style='ime-mode:disabled'/>%";	
					trHtml+="</td>";
					trHtml+="</tr>"
					html+=trHtml;	
				}
				html+="</tbody>";
				html+="<tfoot>";
				html+="<tr>";
				html+="<td colspan='2'>";
				html+="<div class='bz'>";
				html+="</div>";
				html+="<div class='btn'>";
				html+="<button type='button' id='btn_bc' onclick='saveZcbl()'>保 存</button>";
				html+="<button type='button' id='btn_gb' onclick='closeWindown()'>关 闭</button>";
				html+="</div></td></tr></tfoot></table>";
			});
			
			$("div_blsz_blxx").innerHTML=html;
			tipsWindown("系统提示","id:div_blsz","350","200","true","","true","id");
			jQuery.ajaxSetup({async:true});
			
		}
		
		function saveZcbl(){
			
			var url="/xgxt/zjjs_zhcp_ajax.do?method=saveZcbl";
			
			var xmdmArr=document.getElementsByName("xmdmArr");	
			var xmmcArr=document.getElementsByName("xmmcArr");					
			var blArr=document.getElementsByName("blArr");	
			var bldmArr=document.getElementsByName("bldmArr");	
			
			var xmArr=new Array();
			var blzArr=new Array();		
			var bldmzArr=new Array();
			
			var flag=true;
			
			var xmmc="";
			for(var i=0;i<xmdmArr.length;i++){
				xmArr[i]=xmdmArr[i].value;
				blzArr[i]=blArr[i].value;
				bldmzArr[i]=bldmArr[i].value;
				if(blzArr[i]==""){
					flag=false;
					xmmc=xmmcArr[i].value;
					break;
				}
			}
			
			//其他数据
		 	var parameter = {
				"xmdm":xmArr.join("!!@@!!"),
				"bldm":bldmzArr.join("!!@@!!"),
				"bl":blzArr.join("!!@@!!")
			};	
			
			if(flag){
				confirmInfo("确定要保存综测比例吗?",function(t){
					if(t=="ok"){
						jQuery.post(url,parameter,function(result){
							alertInfo(result);
							closeWindown();
						});	
					}
				});
			}else{
				alertInfo(xmmc+"比例能为空!",function(t){
					if(t=="ok"){
						
						return false;
					}
				});
			}
			
		}
	
		//检验计算时的学年学期
		function checkXnXq(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;

				if(xn_num != 1 && flag){
					alertInfo("综测分计算时，学年条件不可为空，且只能为当前评奖学年！");
					flag = false;
				}
				
				if( xq_num != 1 && flag){
					alertInfo("综测分计算时，学期条件不可为空，且只能为当前评奖学期！");
					flag = false;
				}
				
				if(flag){
					var xnid = jQuery("a[name=a_name_xn]")[0].id;
					var xqid = jQuery("a[name=a_name_xq]")[0].id;
					
					var xn_value =xnid.replace("a_id_","");
					var xq_value =xqid.replace("a_id_","");
					var dqxn=jQuery("#pjxn").val();
					var dqxq=jQuery("#pjxq").val();
					
					
					if(xn_value!=dqxn){
						alertInfo("综测分计算时，仅可选择当前评奖学年！");
						flag = false;
					}
					
					if(xq_value!=dqxq && flag){
						alertInfo("综测分计算时，仅可选择当前评奖学期！");
						flag = false;
					}
				}
				
			}
			return flag;
		}
		
		function zczfAccount(){
			
			var url="/xgxt/zjjs_zhcp_ajax.do?method=zczfAccount";
			
			if(checkXnXq()){
				
				confirmInfo("确定要计算综测分吗?",function(t){
				
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					if(t=="ok"){
						setSearchTj();
					
					//点击查询
					var searchLx = new Array();
					var searchTj = new Array();
					var searchTjz = new Array();
					var jslx = new Array();
					var pmjs = new Array();
					
					var n=0;
					var m=3;
					
					searchLx[0]="xy";
					searchLx[1]="zy";
					searchLx[2]="bj";
					
					for(var i=0;i<jytj.length;i++){
						searchLx[m]=jytj[i];
						m++;
					}
				
					var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
						
					for(var j=0;j<tj_num;j++){
						var obj = $("searchTjDiv").getElementsByTagName('input')[j];
						searchTj[n]=obj.name;
						searchTjz[n]=escape(obj.value);
						n++;
					}
					
					var jslxArr =document.getElementsByName("jslx");
					for(var i=0;i<jslxArr.length;i++){
						jslx[i]=jslxArr[i].value;
					}	
					
					var pmjsArr =document.getElementsByName("pmjs");
					for(var i=0;i<pmjsArr.length;i++){
						pmjs[i]=pmjsArr[i].value;
					}		
						
					//其他数据
				 	var parameter = {
						"searchTj":searchTj.join("!!@@!!"),
						"searchTjz":searchTjz.join("!!@@!!"),
						"searchLx":searchLx.join("!!@@!!"),
						"jslx":jslx.join("!!@@!!"),
						"pmjs":pmjs.join("!!@@!!")
					};			
						
						jQuery.post(url,parameter,function(result){
							alertInfo(result);
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							closeWindown();
							searchRs();
						});	
					}
				});
			}else{
				return false
			}
		}
		// -------------------显示综测比例信息列表 end---------------------
		function showPm(){
			//选中排名
			
			if($("jslx_pm").checked){
				$("pmjs").style.display="";
			}else{
				$("pmjs").style.display="none";
			}
		}
		
		function showDiv(){
			tipsWindown("计算方式选择","id:zcfjsDiv","350","170","true","","true","id");
		}
		// ----------------------made by qlj begin---------------------
		
		function setIsEdit(id){
			
			$(id).value="yes"
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
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
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.本功能默认展示的是本评奖学年学期的数据。</br>
				2.由辅导员用户录入分数，并执行保存操作，提交后，<font color="blue">不可进行修改</font>。</br>
				3.学校用户可以查看辅导员提交后的分数，并可针对分数进行修改，如果不修改，将以辅导员<font color="blue">提交为准</font>。</br>
				4.数据导入仅提供学校用户使用，导入时，请使用本功能模块提供的<font color="blue">模板</font>。</br>
				5.综测分及排名计算时，如有选择查询条件。会根据已选查询条件计算查询范围内的数据的综测分和排名信息。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 是否初始化  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<!-- 是否修改 -->
			<input type="hidden" id="had_edit" value="no"/>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn }"/>
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq }"/>
			
			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 综合分 -->
						<logic:equal name="userType" value="admin">
							<li id="li_js">
								<a href="#" onclick="showDiv();return false;" class="btn_yl">
									综合分计算
								</a>
							</li>
							<li id="li_sz">
								<a href="#" onclick="showZcblDiv();return false;" class="btn_sz">
									比例设置
								</a>
							</li>
						</logic:equal>
						<li id="li_tj">
							<a href="#" onclick="zcxxtj();return false;" class="btn_tj">
								导出统计报表
							</a>
						</li>	
						<!-- 综合分 end-->	
						
						<!-- 其他 -->
						<logic:equal name="writeAble" value="yes">
							<logic:notEqual name="userType" value="admin">
								<li id="li_bc" style="display: none">
									<a href="#" onclick="confirmInfo('将要保存您所录入的分数，请确认',saveZhcpf);return false;" class="btn_ccg">
										保存
									</a>
								</li>
								<li id="li_tij" style="display: none">
									<a href="#" onclick="confirmInfo('提交后不可再进行分数修改，请确认是否提交？(以过滤条件中选择的部门为准)',submitZhcpf);return false;" class="btn_fs">
										提交
									</a>
								</li>
							</logic:notEqual>
						</logic:equal>
						
						<logic:equal name="userType" value="admin">
							<li id="li_dc" style="display: none">
								<a href="#" onclick="mbsc();return false;" class="btn_dc">
									模板生成
								</a>
							</li>
							<li id="li_dr" style="display: none">
								<a href="#" onclick="showTopWin('zjjs_zhcp.do?method=inZcf&czxm='+jQuery('#shxm').val(),'450','280');return false;" class="btn_dr">
									数据导入
								</a>
							</li>
						</logic:equal>
						<!-- 其他 end-->	
					</ul>
				</div>
				<!-- 按钮 end-->
		
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end-->
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="15%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">	
												<li id="li_${index}" class="Child">
													<a href="#" name="left_a" id="left_a_${index}" 
														onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
														<span class="ico"></span>${xmnr.xmmc}
													</a>
													<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:360px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 学生申诉弹出层 -->
			<div id="div_xgf" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>修改信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									修改分
								</th>
								<td>
									<input type="hidden" id="input_xgf_xh" value=""/>	
									<input type="hidden" id="input_xgf_lx" value=""/>	
									<input type="text" id="input_xgf" value="" 
										onkeyup="checkInputNum(this);"
										onblur="checkInputNum(this)" 
										maxlength="5"
										style="width : 50px;ime-mode:disabled;" 
									/>	
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="confirmInfo('分数将以修改后为准，请确认是否保存修改？',saveXgf)">
											保 存
										</button>
										
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>



			<!-- 学生申诉弹出层 -->
			<div id="div_yy" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>加减分原因</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									原因<br/>
									<font color="red"><限250字></font>
								</th>
								<td>
									<input type="hidden" id="input_yy_xh" value=""/>	
									<textarea id="textarea_yy" rows="5" cols="" 
										style="word-break:break-all;width:100%" onblur="chLeng(this,250)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc_yy" onclick="saveYy()">
											确 定
										</button>
										
										<button type="button" id="btn_gb_yy" onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!--  made by (be sweet on master)  begin -->
			<div id="div_blsz" style="display:none">
				<div class="open_win01">
					<div id="div_blsz_blxx">
					
					</div>
				</div>
			</div>
			
			<!-- 总分计算选择DIV -->
			<div id="zcfjsDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span> 综合素质测评分计算 </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									计算类型
								</th>
								<td>
									<input type="checkbox" name='jslx' id='jslx_zf' value='zfjs'
										checked />
									综测分计算
									<input type="checkbox" name='jslx' id='jslx_pm' value='pmjs'
										onclick="showPm()" />
									综测排名计算
								</td>
							</tr>
							<tr id="pmjs" style="display:none">
								<th>
									综测分排名计算
								</th>
								<td>
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />班级排名
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy' />年级专业排名
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="zczfAccount()">
											确 定
										</button>
										<button type="button" onclick="closeWindown();">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- made by (be sweet on master)  end -->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>