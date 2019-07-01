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
		//����У����Ϣ
		function loadXqxx(){
			var xqdmArr=document.getElementsByName('xqdmArr');
			var xqmcArr=document.getElementsByName('xqmcArr');
			var len=xqdmArr.length;
			var html="<div style='width:98%;height=70px;overflow:auto;overflow-x:hidden'><table width='100%'><tr>";
			//����У����¥��ѡ��ʱ ���õ���checkBox����radio
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
			//����У����¥��ѡ��ʱ ���õ���checkBox����radio
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
			if($("czyq").value=="��"){
				var userStatus = $("userStatus").value;
				var userName = $("userName").value;
				var userDep = $("userDep").value;
				dwr.engine.setAsync(false);
				var xqdm=getXqdmArr();
				//����У����¥��ѡ��ʱ ���õ���checkBox����radio
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
						html+="��ѡУ��������԰��";
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
			if($("czyq").value=="��"){
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
					html+="��ѡУ��(԰��)������¥��";
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
				//html+="ȫѡ";
				html+="</td>";
				html+="<td style='width:50px'><bean:message key="lable.cs" />/<br/>����������</td>";
				html+="<td align='center'>����<bean:message key="lable.qs" />��</td>";
				html+="<td align='center'>��λ��</td>";
				html+="<td align='center'>�շѱ�׼</td>";
				html+="<td align='center' style='width:150px'>������<br>(�������Ҳ��ɷ��䣬ס��)</td>";
				html+="<td align='center'>�Ա�<br>(ֻ����ס���Ա�ѧ��)</td>";
				html+="<td align='center'>�ɷ��ס<br>(�ɷ���ס������ѧ��)</td>";
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
					html+="<td>"+i+"��<input type='hidden' name='csArr' id='cs_"+i+"' value='"+i+"'>/"+qss+"��</td>";
					html+="<td><input type='text' style='width:25px' name='qsslArr' id='qssl_"+i+"' onblur='checkQsxx("+i+")' maxlength='2' onkeyup='checkNumBer(this)'></td>";
					html+="<td><input type='text' name='cwsArr' disabled='true' style='width:25px' id='cws_"+i+"' maxlength='2'  onkeyup='checkNumBer(this)'></td>";
					html+="<td><input type='text' name='sfbzArr' disabled='true' style='width:50px' id='sfbz_"+i+"' maxlength='10' onkeyup='checkNumBer(this)' ></td>";
					html+="<td><input type='hidden' name='fpbjArr' id='fpbjs_"+i+"' value='һ��'>";
					html+="<input type='radio' name='fpbj_"+i+"' id='fpbjy_"+i+"' disabled='true' checked='true' onclick=\"changeFpbj('fpbjy_"+i+"',"+i+")\" value='һ��'>�ɷ���<input type='radio' name='fpbj_"+i+"' id='fpbjb_"+i+"' disabled='true' onclick=\"changeFpbj('fpbjb_"+i+"',"+i+")\" value='����'>���ɷ���</td>";
					if("���"==xb){
						html+="<td><input type='hidden' name='xbArr'  id='xbs_"+i+"' value='��'>";
						html+="<input type='radio' name='xb_"+i+"' id='xbnan_"+i+"' checked='true' disabled='true' onclick=\"changeXb('xbnan_"+i+"',"+i+")\" value='��'>��<input type='radio' name='xb_"+i+"' id='xbnv_"+i+"'  disabled='true' onclick=\"changeXb('xbnv_"+i+"',"+i+")\" value='Ů'>Ů</td>";
					}else if("��"==xb){
						if(i==1){
							$("xbsz_nan").checked="true";
							$("xbsz_nv").disabled="true";
							$("xbszHid").value="��"
							
						}
						html+="<td><input type='hidden' name='xbArr'  id='xbs_"+i+"' value='��'>";
						html+="��</td>";
					}else if("Ů"==xb){
						if(i==1){
						
							$("xbsz_nv").checked="true";
							$("xbsz_nan").disabled="true";
							$("xbszHid").value="Ů"
						}
						html+="<td><input type='hidden' name='xbArr'  id='xbs_"+i+"' value='Ů'>";
						html+="Ů</td>";
					}
					

					html+="<td><input type='hidden' name='kfhzArr' id='kfhzs_"+i+"' value='����'>";
					html+="<input type='radio' name='kfhz_"+i+"' id='kfhzky_"+i+"' checked='true' disabled='true' onclick=\"changeKfhz('kfhzky_"+i+"',"+i+")\" value='����'>����<input type='radio' name='kfhz_"+i+"' id='kfhzbk_"+i+"' disabled='true' onclick=\"changeKfhz('kfhzbk_"+i+"',"+i+")\" value='����'>����</td>";
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
				//�ж��Ƿ�ѡ��
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
					
					if($("fpbjHid").value=="һ��"){
						$("fpbjy_"+(i+1)).click();
					}else if($("fpbjHid").value=="����"){
						$("fpbjb_"+(i+1)).click();
					}
					
					if($("xbszHid").value=="��"){
						if($("xbnan_"+(i+1))){
							$("xbnan_"+(i+1)).click();
						}
					}else if($("xbszHid").value=="Ů"){
						if($("xbnv_"+(i+1))){
							$("xbnv_"+(i+1)).click();
						}
					}
					
					if($("kfhzHid").value=="����"){
						$("kfhzky_"+(i+1)).click();
					}else if($("kfhzHid").value=="����"){
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
				alert("����ѡ��¥��!");
				return false;
			}
			blog=false;
			var qsslArr=document.getElementsByName('qsslArr');
			var cwsArr=document.getElementsByName('cwsArr');
			for(i=0;i<qsslArr.length;i++){
				
				if(qsslArr[i].value!=""){
					if(cwsArr[i].value=="" || cwsArr[i].disabled){
						alert("��"+(i+1)+"�㴲λ��û����д!");
						return false;
					}
					blog=true;
				}
			}
			if(!blog){
				alert("û����Ҫ����������Զ�������Ϣ!");
				return false;
			}
			
			var input=document.getElementsByTagName("input");
			
			
			var url="/xgxt/gyglGywh.do?method=zdscManage&doType=zdsc";
			if(confirm("ȷ��Ҫ���������Զ����ɲ�����")){
				showLoadPage();
				for(i=0;i<input.length;i++){
					input[i].disabled=false; 			
				}
				refreshForm(url);
			}
		}
		
		function loadXqYqLd(){
			if($("czxq").value=="��"){
				loadXqxx();
			}
			
			if($("czyq").value=="��"){
				loadYqxx();
			}
			
			loadLdxx();
		}
		
		//��һ������
		function nextCz(){
			var next = $("next_cz").value;
			if(next == "gb"){//�ر�
				hiddenMessage(true,true);
				$("dgncz").style.display="";
			}else if(next == "sd"){//�ֶ�����
				refreshForm("gygl_gywh_qswh.do");
			}
		}
		
		//��ʾ��������Div
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
				viewTempDiv('��������','plszDiv',360,300);
			}else{
				alert("�빴ѡ��Ҫ�������õ�¥�㣡");
			}
		}
		
		function showWsxz(){
			if($("blxs") && $("blxs").checked){
				$("xsxs").innerHTML="������<font color='blue'>(λ)</font>";
				$("blxsxz").style.display="";
				$("gdwsxz").style.display="none";
			}else if($("gdws") && $("gdws").checked){
				$("xsxs").innerHTML="��ʾλ��<font color='blue'>(λ)</font>";
				$("blxsxz").style.display="none";
				$("gdwsxz").style.display="";
			}
		}
		
		//��ʾ����ҳ��
		function showLoadPage(){
			//�๦�ܲ���
			$("dgncz").style.display="none";
			//��ѯ���
			$("cxjg").style.display="none";
			//��ʾ
			$("page_loading").style.display="";
			//������ʾ��Ϣ
			$("prompt").innerHTML="���ڽ��������Զ�����,���Ժ�!";
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
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="ldxbxd" id="ldxbxd" value=""/>
			<input type="hidden" name="czxq" id="czxq" value="${czxq }"/>
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}"/>
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}"/>
			<input type="hidden" name="lddmHid" id="lddmHid" value="${rs.lddm}"/>
			<input type="hidden" name="bcxs" id="bcxs" value="checkbox"/>
			<input type="hidden" id="czxq" name="czxq" value="${czxq}"/><!-- ����У�� -->
			<input type="hidden" id="czyq" name="czyq" value="${czyq}"/><!-- ����԰�� -->
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
										�� ��
									</button>
									<button class="" onclick="zdscQs()" id="btn_bc">
										�� ��
									</button>
									<logic:present name="tzPath">
									<button class="" onclick="fhPath()" id="btn_fh">
										�� ��
									</button>
									</logic:present>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѡ����Ҫά�����ҵ�<bean:message key="lable.ld" />(����ѡ��һ��<bean:message key="lable.ld" />)</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal name="czxq" value="��">
						<tr>
							<th style="width:16%">
								����<bean:message key="lable.xiaoqu" />
							</th>
							<td colspan="3" id="p_xq" style="width:84%">
								
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="czyq" value="��">
						<tr>
							<th style="width:16%">
								����<bean:message key="lable.yuanqu" />
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
								<span><bean:message key="lable.qs" />��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th  style="width:16%">
								��Ź���
							</th>
							<td  style="width:34%">
								<!-- ���������ļ�ȡ �Զ��������ҹ��� -->
								<logic:iterate name="bhgzxzArr" id="bhgzxz">
									<logic:equal name="bhgzxz" value="blxs">
										<!-- ������ʽ���� -->
										<html:radio property="bhgz" styleId="blxs" value="blxs" onclick="showWsxz()"/><bean:message key="lable.cs" />+������+����<br/>
									</logic:equal>
									<logic:equal name="bhgzxz" value="gdws">
										<!-- �̶�λ�� -->
										<html:radio property="bhgz" styleId="gdws" value="gdws" onclick="showWsxz()"/><bean:message key="lable.cs" />+����<br/>
									</logic:equal>
								</logic:iterate>
							</td>
							<th  style="width:16%">
								<p id="xsxs">������<font color="blue">(λ)</font></p>
							</th>
							<!-- ������ʽѡ�� -->
							<td  style="width:34%" id="blxsxz">
								<html:select property="bls" style="width:50px">
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
								</html:select><br/><font color="blue">(����һ�㣬������Ϊ1λ�����ֱ��Ϊ10�����ɵ����Һ�Ϊ1010;
								ʮ�㣬������Ϊ1λ�����ֱ��Ϊ10�����ɵ����Һ�Ϊ10010)</font>
							</td>
							<!-- �̶�λ����ʽѡ�� -->
							<td  style="width:34%" id="gdwsxz" style="display:none">
								<!-- ��ʾλ�� -->
								<html:select property="wsxz" style="width:50px">
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
								</html:select><br/><font color="blue">(����һ�㣬��ʾλ��Ϊ3λ�����ֱ��Ϊ10�����ɵ����Һ�Ϊ110;
								ʮ�㣬��ʾλ��Ϊ3λ�����ֱ��Ϊ10�����ɵ����Һ�Ϊ1010)</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									ά����Ҫ���ɵ��������(ע��������ѡ��¥�������⣬��Ҫ¼��ÿ��¥ϣ�����ɵ������������ſ���ά��������Ϣ)
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
											<td align="center" valign="middle"><span><font color="red">����ѡ��<bean:message key="lable.ld" />��</font></span></td>
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
								<span>����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								������������
							</th>
							<td>
								<input type="radio" name="scqsssz" id="scqsssz_no" checked value="���޸�" onclick="$('qsslsz').disabled=true"/>���޸�
								<input type="radio" name="scqsssz" id="scqsssz_yes" value="�޸�"  onclick="$('qsslsz').disabled=false"/>�޸�
								<input type="text" name="qsslsz" id="qsslsz" maxlength="2" size="10" disabled="true" onkeyup="value=value.replace(/[^\d]/g,'')" />
							</td>
						</tr>
						<tr>
							<th>
								�������Ҵ�λ��
							</th>
							<td>	
								<input type="radio" name="qscwssz" id="cwssz_no" checked value="���޸�" onclick="$('cwssz').disabled=true"/>���޸�
								<input type="radio" name="qscwssz" id="cwssz_yes" value="�޸�"  onclick="$('cwssz').disabled=false"/>�޸�
								<input type="text" name="cwssz" id="cwssz"  maxlength="2" size="10" disabled="true"  onkeyup="value=value.replace(/[^\d]/g,'')" />
							</td>
						</tr>
						<tr>
							<th>
								�շѱ�׼
							</th>
							<td>
								<input type="radio" name="qssfbzsz" id="sfbzsz_no" checked value="���޸�" onclick="$('sfbzsz').disabled=true"/>���޸�
								<input type="radio" name="qssfbzsz" id="sfbzsz_yes" value="�޸�" onclick="$('sfbzsz').disabled=false"/>�޸�
								<input type="text" name="sfbzsz" id="sfbzsz"  maxlength="10"  size="10"  disabled="true"   onkeyup="value=value.replace(/[^\d]/g,'')" />
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								<input type="radio" name="fpbjsz" onclick="$('fpbjHid').value=this.value" checked="true" value='���޸�'/>���޸�
								<input type="radio" name="fpbjsz" onclick="$('fpbjHid').value=this.value" value='һ��'/>�ɷ���
								<input type="radio" name="fpbjsz" onclick="$('fpbjHid').value=this.value" value='����' />���ɷ���
								<input type="hidden" name="fpbjHid" id="fpbjHid" value="���޸�"/>
							</td>
						</tr>
						<tr>	
							<th>
								�����Ա�
							</th>
							<td>
								<input type="radio" name="xbsz" id="xbsz_bxg" onclick="$('xbszHid').value=this.value" checked="true" value='���޸�'/>���޸�
								<input type="radio" name="xbsz" id="xbsz_nan" onclick="$('xbszHid').value=this.value" value='��'/>��
								<input type="radio" name="xbsz" id="xbsz_nv"  onclick="$('xbszHid').value=this.value" value='Ů'/>Ů
								<input type="hidden" name="xbszHid" id="xbszHid" value="���޸�"/>
							</td>
						</tr>
						<tr>	
							<th>
								���Ż�ס
							</th>
							<td>
								<input type="radio" name="kfhz" onclick="$('kfhzHid').value=this.value" checked="true" value='���޸�'/>���޸�
								<input type="radio" name="kfhz" onclick="$('kfhzHid').value=this.value" value='����'/>����
								<input type="radio" name="kfhz" onclick="$('kfhzHid').value=this.value" value='����'/>����
								<input type="hidden" name="kfhzHid" id="kfhzHid" value="���޸�"/>
							</td>
						</tr>
						<tfoot>
						<tr>	
							<td colspan="2" align="right">
								<button class="" onclick="xzcsSz();hiddenMessage(true,true);" id="btn_bc">
									ȷ ��
								</button>
								<button class="" onclick="hiddenMessage(true,true);" id="btn_gb">
									�� ��
								</button>
							</td>
						</tr>
						</tfoot>
					</tbody>
				</table>
			</div>

			<!-- ��ʾ��Ϣ -->
			<logic:notEmpty name="result">
			<logic:equal value="false" name="result">
					<script language="javascript">
					alert("����ʧ�ܣ�");
					</script>
			</logic:equal>
			<logic:equal value="true" name="result">
			<logic:present name="message">
				<!-- ���������ʾ�� -->
				<div id="tsxxDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											�Ѿ��ɹ�������${message }�����ң���ȷ����һ������
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="gb"/>
										<input type="radio" name="next" id="next_sd" value="sd" onclick="$('next_cz').value = this.value"/>
										ǰ��"������Ϣά��"ģ��
										</br>
										<input type="radio" name="next" id="next_gb" value="gb" checked="true" onclick="$('next_cz').value = this.value"/>
										����Ϊ����¥�������������Ҳ���
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
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
					
					viewTempDiv("��ѡ����һ������","tsxxDiv",350,200);
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
			<!-- ��ȴ� -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- ��ȴ� end-->
		</html:form>
	</body>
</html>
