<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglGywh.js"></script>	
		<script type="text/javascript" src="/xgxt/dwr/interface/searchUtil.js"></script>	
		<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
		<script type="text/javascript">
		var ldcheck="";
		//加载校区信息
		function loadXqxx(){
			var xqdmArr=document.getElementsByName('xqdmArr');
			var xqmcArr=document.getElementsByName('xqmcArr');
			var len=xqdmArr.length;
			var html="<div style='width:98%;height=70px;overflow:auto;overflow-x:hidden'><table width='100%'><tr>";
			//配置校区、楼栋选择时 采用的是checkBox还是radio
			var bcxs=$("bcxs").value;
			for(i=1;i<=len;i++){
				html+="<td style='width:33%'>";
				html+="<input type='"+bcxs+"' name='xqdm' id='xqxx_"+i+"'  value='"+xqdmArr[i-1].value+"'  onclick='changeYqxx();changeLdxx()'>"+xqmcArr[i-1].value;
				html+="</td>";
				if(i%3==0){
					html+="</tr><tr>";
				}
			}
			
			if(len%3!=0){
				for(i=1;i<=3-(len%3);i++){
					html+="<td></td>"
				}
			}
			html+="</tr></table></div>";
			$("p_xq").innerHTML=html;
		}
		
		function loadYqxx(){
			var yqdmArr=document.getElementsByName('yqdmArr');
			var yqmcArr=document.getElementsByName('yqmcArr');
			var len=yqdmArr.length;
			//var html="<div style='width:98%;height:70px;overflow:auto;overflow-x:hidden'>";
			var html="<table width='100%'><tr>";
			//配置校区、楼栋选择时 采用的是checkBox还是radio
			var bcxs=$("bcxs").value;
			for(i=1;i<=len;i++){
				html+="<td style='width:33%'>";
				html+="<input type='"+bcxs+"' name='yqdm' id='yqxx_"+i+"' value='"+yqdmArr[i-1].value+"' onclick='changeLdxx()'>"+yqmcArr[i-1].value;
				
				html+="</td>";
				if(i%3==0){
					html+="</tr><tr>";
				}
			}
			
			if(len%3!=0){
				for(i=1;i<=3-(len%3);i++){
					html+="<td></td>"
				}
			}
			html+="</tr></table>";
			//html+="</div>";
			$("p_yq").innerHTML=html;
		}
		
		function loadLdxx(){
			var lddmArr=document.getElementsByName('lddmArr');
			var ldmcArr=document.getElementsByName('ldmcArr');
			var len=lddmArr.length;
			//var html="<div style='width:98%;height=150px;overflow-y:auto;overflow-x:hidden'>";
			var html="<table width='100%'><tr>";
			for(i=1;i<=len;i++){
				html+="<td style='width:33%'>";
				html+="<input type='radio' name='lddm' id='ldxx_"+i+"' onclick='getLdxx()' value='"+lddmArr[i-1].value+"'><span id='span_ldxx_"+i+"' ><font color='black'>"+ldmcArr[i-1].value+"</font></span>";
				html+="</td>";
				if(i%3==0){
					html+="</tr><tr>";
				}
			}
			
			if(len%3!=0){
				for(i=1;i<=3-(len%3);i++){
					html+="<td></td>"
				}
			}
			html+="</tr></table>";
			//html+="</div>";
			$("p_ld").innerHTML=html;
		}
		
		function changeYqxx(){
			if($("czyq").value=="是"){
				var userStatus = $("userStatus").value;
				var userName = $("userName").value;
				var userDep = $("userDep").value;
				dwr.engine.setAsync(false);
				var xqdm=getXqdmArr();
				//配置校区、楼栋选择时 采用的是checkBox还是radio
				var bcxs=$("bcxs").value;
				searchUtil.getGyglInfo("yq",xqdm,null,null,null,userStatus,userName,userDep,function(data){
					//var html="<div style='width:98%;height:70px;overflow:auto;overflow-x:hidden'>";
					var html="<table width='100%'><tr>";
					var len=data.length;
					if(len !=0 ){
						for(i=1;i<=len;i++){
							dm=""+data[i-1].yqdm;
							mc=""+data[i-1].yqmc;
							html+="<td style='width:33%'>";
							html+="<input type='"+bcxs+"' name='yqdm' id='yqxx_"+i+"' onclick='changeLdxx()' value='"+dm+"' >"+mc;
							html+="</td>";
							if(i%3==0){
								html+="</tr><tr>";
							}
						}
						
						if(len%3!=0){
							for(i=1;i<=3-(len%3);i++){
								html+="<td></td>"
							}
						}
					}else{
						html+="<td>";
						html+="所选校区下属无园区";
						html+="</td>";
					}
					html+="</tr></table>";
					//html+="</div>";
					$("p_yq").innerHTML=html;
				});
				 dwr.engine.setAsync(true);
			}
		}
		
		function getXqdmArr(){
			var xqdm=document.getElementsByName('xqdm');
			var xqdmArr=new Array();
			var len=xqdm.length;
			var m=0;
			for(i=0;i<len;i++){
				if(xqdm[i].checked){
					xqdmArr[m]=xqdm[i].value;
					m++;
				}
			}
			return xqdmArr;
		}
		
		function getYqdmArr(){
			if($("czyq").value=="是"){
				var yqdm=document.getElementsByName('yqdm');
				var yqdmArr=new Array();
				var len=yqdm.length;
				var m=0;
				for(i=0;i<len;i++){
					if(yqdm[i].checked){
						yqdmArr[m]=yqdm[i].value;
						m++;
					}
				}
				return yqdmArr;
			}else {
				return null;
			}
		}
		
		function getLddm(){
			var lddm=document.getElementsByName('lddm');
			var lddmValue="";
			var len=lddm.length;
			
			
			for(i=0;i<len;i++){
				if($(ldcheck)){
					$(ldcheck).innerHTML=$(ldcheck).innerHTML.replace("blue","black");
				}
				if(lddm[i].checked){
					lddmValue=lddm[i].value;
					ldcheck="span_"+lddm[i].id;
					if($(ldcheck)){
						$(ldcheck).innerHTML=$(ldcheck).innerHTML.replace("black","blue");
					}
					break;
				}
				
				
			}
			return lddmValue;
		}
		
		function changeLdxx(){
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
			dwr.engine.setAsync(false);
			
			var xqdm=getXqdmArr();
			var yqdm=getYqdmArr();
			searchUtil.getGyglInfo("ld",xqdm,yqdm,null,null,userStatus,userName,userDep,function(data){
				//var html="<div style='width:98%;height:200px;overflow-y:auto;;overflow-x:hidden'><table width='100%'><tr>";
				var html="<table width='100%'><tr>";
				var len=data.length;
				if(len !=0){
					for(i=1;i<=len;i++){
						dm=""+data[i-1].lddm;
						mc=""+data[i-1].ldmc;
						html+="<td style='width:33%'>";
						html+="<input type='radio' name='lddm' id='ldxx_"+i+"' onclick='getLdxx()' value='"+dm+"' ><span id='span_ldxx_"+i+"'><font color='black'>"+mc+"</font></span>";
						html+="</td>";
						if(i%3==0){
							html+="</tr><tr>";
						}
					}
					
					if(len%3!=0){
						for(i=1;i<=3-(len%3);i++){
							html+="<td></td>"
						}
					}
				}else{
					html+="<td>";
					html+="所选校区(园区)下属无楼栋";
					html+="</td>";
				}
				html+="</tr></table>";
				//html+="</div>";
				$("p_ld").innerHTML=html;
			});
			dwr.engine.setAsync(true);
		}
		
		function getLdxx(){
			var tableName="xg_view_gygl_sslddm";
			var colList=["lddm","ldmc","xqmc","yqmc","cs","xbxd","bz"];
			var pk="lddm";
			var pkValue=getLddm();
			gyglGywh.getLdlcList(pkValue,function(data){
				if(data != null){
				
				var html="<div style='width:98%;height:200px;overflow-y:auto;overflow-x:hidden'>";
				html+="<table width='100%' border='0' class='formlist'>";
				html+="<thead><td align='center' style='width:10px'><input type='checkbox' id='selall' name='selall' onclick='selAll()' />";
				//html+="全选";
				html+="</td>";
				html+="<td style='width:50px'><bean:message key="lable.cs" />/<br/>已有寝室数</td>";
				html+="<td align='center'>生成<bean:message key="lable.qs" />数</td>";
				html+="<td align='center'>床位数</td>";
				html+="<td align='center'>收费标准</td>";
				html+="<td align='center' style='width:150px'>分配标记<br>(保留寝室不可分配，住人)</td>";
				html+="<td align='center'>性别<br>(只可入住该性别学生)</td>";
				html+="<td align='center'>可否混住<br>(可否入住他部门学生)</td>";
				html+="</thead>";
				html+="<tbody>";
				for(i=1;i<=data.length;i++){
					var lddm=data[i-1].lddm;
					var ldmc=data[i-1].ldmc;
					var xqmc=data[i-1].xqmc;
					var yqmc=data[i-1].yqmc;
					var cs=data[i-1].cs;
					var xb=data[i-1].xb;
					var qss=data[i-1].qss;
					html+="<tr><td><input type='checkbox' name='cbv' id='lcs_"+i+"' value='"+i+"'></td>"
					html+="<td>"+i+"层<input type='hidden' name='csArr' id='cs_"+i+"' value='"+i+"'>/"+qss+"个</td>";
					html+="<td><input type='text' style='width:25px' name='qsslArr' id='qssl_"+i+"' onblur='checkQsxx("+i+")' maxlength='2' onkeyup='checkNumBer(this)'></td>";
					html+="<td><input type='text' name='cwsArr' disabled='true' style='width:25px' id='cws_"+i+"' maxlength='2'  onkeyup='checkNumBer(this)'></td>";
					html+="<td><input type='text' name='sfbzArr' disabled='true' style='width:50px' id='sfbz_"+i+"' maxlength='10' onkeyup='checkNumBer(this)' ></td>";
					html+="<td><input type='hidden' name='fpbjArr' id='fpbjs_"+i+"' value='一般'>";
					html+="<input type='radio' name='fpbj_"+i+"' id='fpbjy_"+i+"' disabled='true' checked='true' onclick=\"changeFpbj('fpbjy_"+i+"',"+i+")\" value='一般'>可分配<input type='radio' name='fpbj_"+i+"' id='fpbjb_"+i+"' disabled='true' onclick=\"changeFpbj('fpbjb_"+i+"',"+i+")\" value='保留'>不可分配</td>";
					if("混合"==xb){
						html+="<td><input type='hidden' name='xbArr'  id='xbs_"+i+"' value='男'>";
						html+="<input type='radio' name='xb_"+i+"' id='xbnan_"+i+"' checked='true' disabled='true' onclick=\"changeXb('xbnan_"+i+"',"+i+")\" value='男'>男<input type='radio' name='xb_"+i+"' id='xbnv_"+i+"'  disabled='true' onclick=\"changeXb('xbnv_"+i+"',"+i+")\" value='女'>女</td>";
					}else if("男"==xb){
						if(i==1){
							$("xbsz_nan").checked="true";
							$("xbsz_nv").disabled="true";
							$("xbszHid").value="男"
							
						}
						html+="<td><input type='hidden' name='xbArr'  id='xbs_"+i+"' value='男'>";
						html+="男</td>";
					}else if("女"==xb){
						if(i==1){
						
							$("xbsz_nv").checked="true";
							$("xbsz_nan").disabled="true";
							$("xbszHid").value="女"
						}
						html+="<td><input type='hidden' name='xbArr'  id='xbs_"+i+"' value='女'>";
						html+="女</td>";
					}
					

					html+="<td><input type='hidden' name='kfhzArr' id='kfhzs_"+i+"' value='可以'>";
					html+="<input type='radio' name='kfhz_"+i+"' id='kfhzky_"+i+"' checked='true' disabled='true' onclick=\"changeKfhz('kfhzky_"+i+"',"+i+")\" value='可以'>可以<input type='radio' name='kfhz_"+i+"' id='kfhzbk_"+i+"' disabled='true' onclick=\"changeKfhz('kfhzbk_"+i+"',"+i+")\" value='不可'>不可</td>";
					html+="</tr>";
					
					
				}
				html+="</tbody>";
				html+="</table></div>";
				$("rsTable").innerHTML=html;
				}
			});
			$("btn_sz").style.display="";
		}
		
		function xzcsSz(){
			var chV=document.getElementsByName('cbv');
			for(i=0;i<chV.length;i++){
				//判断是否选中
				if(chV[i].checked){
					if($("scqsssz_yes").checked){
						$("qssl_"+(i+1)).value=$("qsslsz").value;
					}
					checkQsxx(i+1);
					if($("cwssz_yes").checked){
						$("cws_"+(i+1)).value=$("cwssz").value;
					}
					
					if($("sfbzsz_yes").checked){
						$("sfbz_"+(i+1)).value=$("sfbzsz").value;
					}
					
					if($("fpbjHid").value=="一般"){
						$("fpbjy_"+(i+1)).click();
					}else if($("fpbjHid").value=="保留"){
						$("fpbjb_"+(i+1)).click();
					}
					
					if($("xbszHid").value=="男"){
						if($("xbnan_"+(i+1))){
							$("xbnan_"+(i+1)).click();
						}
					}else if($("xbszHid").value=="女"){
						if($("xbnv_"+(i+1))){
							$("xbnv_"+(i+1)).click();
						}
					}
					
					if($("kfhzHid").value=="可以"){
						$("kfhzky_"+(i+1)).click();
					}else if($("kfhzHid").value=="不可"){
						$("kfhzbk_"+(i+1)).click();
					}
					
				}
			}
		}
		
		function checkNumBer(obj){
			obj.value=obj.value.replace(/[^\d]/g,'');
		}
		
		function checkQsxx(num){
			if($("qssl_"+num).value!=""){
					$("cws_"+num).disabled=false;
					$("sfbz_"+num).disabled=false;
					$("fpbjy_"+num).disabled=false;
					$("fpbjb_"+num).disabled=false;
					
					if($("xbnan_"+num)){
						$("xbnan_"+num).disabled=false;
						$("xbnv_"+num).disabled=false;
					}
					
					$("kfhzky_"+num).disabled=false;
					$("kfhzbk_"+num).disabled=false;
		
			}else{
				$("cws_"+num).disabled=true;
					$("sfbz_"+num).disabled=true;
					$("fpbjy_"+num).disabled=true;
					$("fpbjb_"+num).disabled=true;
					
					if($("xbnan_"+num)){
						$("xbnan_"+num).disabled=true;
						$("xbnv_"+num).disabled=true;
					}
					
					$("kfhzky_"+num).disabled=true;
					$("kfhzbk_"+num).disabled=true;
					
		
			}
		}

		function changeFpbj(id,num){
			$("fpbjs_"+num).value=$(id).value;
		}
		
		function changeXb(id,num){
			$("xbs_"+num).value=$(id).value;
		}
		
		function changeKfhz(id,num){
			$("kfhzs_"+num).value=$(id).value;
			if(id.indexOf("kfhzky")>-1){
				$("kfhzky_"+num).checked="checked";
			}else if(id.indexOf("kfhzbk")>-1){
				$("kfhzbk_"+num).checked="checked";
			}
		}
		
		function zdscQs(){
			var lddm=document.getElementsByName('lddm');
			var blog=false;
			for(i=0;i<lddm.length;i++){
				if(lddm[i].checked){
					blog=true;
					break;
				}
			}
			if(!blog){
				alert("请先选择楼栋!");
				return false;
			}
			blog=false;
			var qsslArr=document.getElementsByName('qsslArr');
			var cwsArr=document.getElementsByName('cwsArr');
			for(i=0;i<qsslArr.length;i++){
				
				if(qsslArr[i].value!=""){
					if(cwsArr[i].value=="" || cwsArr[i].disabled){
						alert("第"+(i+1)+"层床位号没有填写!");
						return false;
					}
					blog=true;
				}
			}
			if(!blog){
				alert("没有需要保存的寝室自动生成信息!");
				return false;
			}
			
			var input=document.getElementsByTagName("input");
			
			
			var url="/xgxt/gyglGywh.do?method=zdscManage&doType=zdsc";
			if(confirm("确定要进行寝室自动生成操作吗？")){
				showLoadPage();
				for(i=0;i<input.length;i++){
					input[i].disabled=false; 			
				}
				refreshForm(url);
			}
		}
		
		function loadXqYqLd(){
			if($("czxq").value=="是"){
				loadXqxx();
			}
			
			if($("czyq").value=="是"){
				loadYqxx();
			}
			
			loadLdxx();
		}
		
		//下一步操作
		function nextCz(){
			var next = $("next_cz").value;
			if(next == "gb"){//关闭
				hiddenMessage(true,true);
				$("dgncz").style.display="";
			}else if(next == "sd"){//手动分配
				refreshForm("gygl_gywh_qswh.do");
			}
		}
		
		//显示批量设置Div
		function showPlszDiv(){
		
			var num = document.getElementsByName("cbv").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("cbv")[i];
				if(obj.checked){
					flag = true;
				}
			}
			
			if(flag){
				viewTempDiv('寝室设置','plszDiv',360,300);
			}else{
				alert("请勾选需要批量设置的楼层！");
			}
		}
		
		function showWsxz(){
			if($("blxs") && $("blxs").checked){
				$("xsxs").innerHTML="补零数<font color='blue'>(位)</font>";
				$("blxsxz").style.display="";
				$("gdwsxz").style.display="none";
			}else if($("gdws") && $("gdws").checked){
				$("xsxs").innerHTML="显示位数<font color='blue'>(位)</font>";
				$("blxsxz").style.display="none";
				$("gdwsxz").style.display="";
			}
		}
		
		//显示加载页面
		function showLoadPage(){
			//多功能操作
			$("dgncz").style.display="none";
			//查询结果
			$("cxjg").style.display="none";
			//显示
			$("page_loading").style.display="";
			//设置提示信息
			$("prompt").innerHTML="正在进行寝室自动生成,请稍候!";
		}
		
		function setLdValue(){
			if($("lddmHid") && $("lddmHid").value!=""){
				var lddm=document.getElementsByName("lddm");
				for(i=0;i<lddm.length;i++){
					if(lddm[i].value==$("lddmHid").value){
						lddm[i].checked=true;
						lddm[i].onclick();
					}
				}
			}
		}
		
		function fhPath(){
			var url=$("tzPath").value;
			refreshForm(url);
		}
		</script>
<%--		<style>--%>
<%--		.include_tab{border-collapse:collapse;border:0px;}--%>
<%--		.include_tab td{border-top:0;bordedr-right:1px solid red!importalt;border-bottom:0;border-left:0;}--%>
<%--		.include_tab_r{}--%>
<%--		</style>--%>
	</head>
	<body onload="loadXqYqLd();showWsxz();setLdValue()">
		<html:form action="/gyglGywh" method="post">
			<input type="hidden" name="cxjg" id="cxjg"/>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="ldxbxd" id="ldxbxd" value=""/>
			<input type="hidden" name="czxq" id="czxq" value="${czxq }"/>
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}"/>
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}"/>
			<input type="hidden" name="lddmHid" id="lddmHid" value="${rs.lddm}"/>
			<input type="hidden" name="bcxs" id="bcxs" value="checkbox"/>
			<input type="hidden" id="czxq" name="czxq" value="${czxq}"/><!-- 存在校区 -->
			<input type="hidden" id="czyq" name="czyq" value="${czyq}"/><!-- 存在园区 -->
			<input type="hidden" name="ldHid" id="ldHid" value="${ldHid}"/> 
			<logic:iterate name="xqList" id="xqxx" indexId="index">
				<input type="hidden" name="xqdmArr" value="${xqxx.dm}"/>
				<input type="hidden" name="xqmcArr" value="${xqxx.mc}"/>
			</logic:iterate>
			
			<logic:iterate name="yqList" id="yqxx" indexId="index">
				<input type="hidden" name="yqdmArr" value="${yqxx.dm}"/>
				<input type="hidden" name="yqmcArr" value="${yqxx.mc}"/>
			</logic:iterate>
			
			<logic:iterate name="ldList" id="ldxx" >
				<input type="hidden" name="lddmArr" value="${ldxx.lddm}"/>
				<input type="hidden" name="ldmcArr" value="${ldxx.ldmc}"/>
			</logic:iterate>
			<input type="hidden" name="tzPath" id="tzPath" value="${tzPath}"/>
			<div class="tab" id="dgncz">
				<table width="100%" border="0" class="formlist">
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button style="display:none" onclick="showPlszDiv();" id="btn_sz">
										设 置
									</button>
									<button class="" onclick="zdscQs()" id="btn_bc">
										保 存
									</button>
									<logic:present name="tzPath">
									<button class="" onclick="fhPath()" id="btn_fh">
										返 回
									</button>
									</logic:present>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>请选择需要维护寝室的<bean:message key="lable.ld" />(必须选择一个<bean:message key="lable.ld" />)</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal name="czxq" value="是">
						<tr>
							<th style="width:16%">
								所属<bean:message key="lable.xiaoqu" />
							</th>
							<td colspan="3" id="p_xq" style="width:84%">
								
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="czyq" value="是">
						<tr>
							<th style="width:16%">
								所属<bean:message key="lable.yuanqu" />
							</th>
							<td colspan="3" id="p_yq" style="width:84%">
								
							</td>
						</tr>
						</logic:equal>
						
						<tr>
							<th style="width:16%">
								<font color="red">*</font><bean:message key="lable.ld" />
							</th>
							<td colspan="3" id="p_ld" style="width:84%">
								
							</td>
						</tr>
	
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.qs" />命名规则</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th  style="width:16%">
								编号规则
							</th>
							<td  style="width:34%">
								<!-- 根据配置文件取 自动生成寝室规则 -->
								<logic:iterate name="bhgzxzArr" id="bhgzxz">
									<logic:equal name="bhgzxz" value="blxs">
										<!-- 补零形式控制 -->
										<html:radio property="bhgz" styleId="blxs" value="blxs" onclick="showWsxz()"/><bean:message key="lable.cs" />+补零数+数字<br/>
									</logic:equal>
									<logic:equal name="bhgzxz" value="gdws">
										<!-- 固定位数 -->
										<html:radio property="bhgz" styleId="gdws" value="gdws" onclick="showWsxz()"/><bean:message key="lable.cs" />+数字<br/>
									</logic:equal>
								</logic:iterate>
							</td>
							<th  style="width:16%">
								<p id="xsxs">补零数<font color="blue">(位)</font></p>
							</th>
							<!-- 补零形式选择 -->
							<td  style="width:34%" id="blxsxz">
								<html:select property="bls" style="width:50px">
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
								</html:select><br/><font color="blue">(例：一层，补零数为1位，数字编号为10，生成的寝室号为1010;
								十层，补零数为1位，数字编号为10，生成的寝室号为10010)</font>
							</td>
							<!-- 固定位数形式选择 -->
							<td  style="width:34%" id="gdwsxz" style="display:none">
								<!-- 显示位数 -->
								<html:select property="wsxz" style="width:50px">
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
								</html:select><br/><font color="blue">(例：一层，显示位数为3位，数字编号为10，生成的寝室号为110;
								十层，显示位数为3位，数字编号为10，生成的寝室号为1010)</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									维护需要生成的寝室情况(注：必须先选择楼栋，另外，需要录入每层楼希望生成的寝室数量，才可以维护其他信息)
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div id="rsTable" style="width:100%;height:200px;">
									<table  style="width:100%;height:200px" border="0">
										<tr>
											<td align="center" valign="middle"><span><font color="red">请先选择<bean:message key="lable.ld" />！</font></span></td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="plszDiv" style="display:none">
				<table width='100%' border='0' class='formlist'>
					<thead>
						<tr>
							<th colspan="2">
								<span>设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								生成寝室数量
							</th>
							<td>
								<input type="radio" name="scqsssz" id="scqsssz_no" checked value="不修改" onclick="$('qsslsz').disabled=true"/>不修改
								<input type="radio" name="scqsssz" id="scqsssz_yes" value="修改"  onclick="$('qsslsz').disabled=false"/>修改
								<input type="text" name="qsslsz" id="qsslsz" maxlength="2" size="10" disabled="true" onkeyup="value=value.replace(/[^\d]/g,'')" />
							</td>
						</tr>
						<tr>
							<th>
								单个寝室床位数
							</th>
							<td>	
								<input type="radio" name="qscwssz" id="cwssz_no" checked value="不修改" onclick="$('cwssz').disabled=true"/>不修改
								<input type="radio" name="qscwssz" id="cwssz_yes" value="修改"  onclick="$('cwssz').disabled=false"/>修改
								<input type="text" name="cwssz" id="cwssz"  maxlength="2" size="10" disabled="true"  onkeyup="value=value.replace(/[^\d]/g,'')" />
							</td>
						</tr>
						<tr>
							<th>
								收费标准
							</th>
							<td>
								<input type="radio" name="qssfbzsz" id="sfbzsz_no" checked value="不修改" onclick="$('sfbzsz').disabled=true"/>不修改
								<input type="radio" name="qssfbzsz" id="sfbzsz_yes" value="修改" onclick="$('sfbzsz').disabled=false"/>修改
								<input type="text" name="sfbzsz" id="sfbzsz"  maxlength="10"  size="10"  disabled="true"   onkeyup="value=value.replace(/[^\d]/g,'')" />
							</td>
						</tr>
						<tr>
							<th>
								分配标记
							</th>
							<td>
								<input type="radio" name="fpbjsz" onclick="$('fpbjHid').value=this.value" checked="true" value='不修改'/>不修改
								<input type="radio" name="fpbjsz" onclick="$('fpbjHid').value=this.value" value='一般'/>可分配
								<input type="radio" name="fpbjsz" onclick="$('fpbjHid').value=this.value" value='保留' />不可分配
								<input type="hidden" name="fpbjHid" id="fpbjHid" value="不修改"/>
							</td>
						</tr>
						<tr>	
							<th>
								寝室性别
							</th>
							<td>
								<input type="radio" name="xbsz" id="xbsz_bxg" onclick="$('xbszHid').value=this.value" checked="true" value='不修改'/>不修改
								<input type="radio" name="xbsz" id="xbsz_nan" onclick="$('xbszHid').value=this.value" value='男'/>男
								<input type="radio" name="xbsz" id="xbsz_nv"  onclick="$('xbszHid').value=this.value" value='女'/>女
								<input type="hidden" name="xbszHid" id="xbszHid" value="不修改"/>
							</td>
						</tr>
						<tr>	
							<th>
								部门混住
							</th>
							<td>
								<input type="radio" name="kfhz" onclick="$('kfhzHid').value=this.value" checked="true" value='不修改'/>不修改
								<input type="radio" name="kfhz" onclick="$('kfhzHid').value=this.value" value='不可'/>不可
								<input type="radio" name="kfhz" onclick="$('kfhzHid').value=this.value" value='可以'/>可以
								<input type="hidden" name="kfhzHid" id="kfhzHid" value="不修改"/>
							</td>
						</tr>
						<tfoot>
						<tr>	
							<td colspan="2" align="right">
								<button class="" onclick="xzcsSz();hiddenMessage(true,true);" id="btn_bc">
									确 定
								</button>
								<button class="" onclick="hiddenMessage(true,true);" id="btn_gb">
									关 闭
								</button>
							</td>
						</tr>
						</tfoot>
					</tbody>
				</table>
			</div>

			<!-- 提示信息 -->
			<logic:notEmpty name="result">
			<logic:equal value="false" name="result">
					<script language="javascript">
					alert("操作失败！");
					</script>
			</logic:equal>
			<logic:equal value="true" name="result">
			<logic:present name="message">
				<!-- 分配完成提示层 -->
				<div id="tsxxDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											已经成功生成了${message }个寝室，请确定下一步操作
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="gb"/>
										<input type="radio" name="next" id="next_sd" value="sd" onclick="$('next_cz').value = this.value"/>
										前往"寝室信息维护"模块
										</br>
										<input type="radio" name="next" id="next_gb" value="gb" checked="true" onclick="$('next_cz').value = this.value"/>
										继续为其他楼栋进行生成寝室操作
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- 确定 -->
										<button onclick="nextCz()">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<script defer="defer">
					
					viewTempDiv("请选择下一步操作","tsxxDiv",350,200);
					$("windown-close").style.display="none";
					if($("message") && $("message").value != ""){
						$("message").value = "";
						$("doType").value = "";	
					}
					$("dgncz").style.display="none";
				</script>
			</logic:present>
			</logic:equal>
			</logic:notEmpty>
			<!-- 请等待 -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- 请等待 end-->
		</html:form>
	</body>
</html>
