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
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglGywh.js"></script>	
		<script type="text/javascript" src="/xgxt/dwr/interface/searchUtil.js"></script>	
		<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCommGygl.js'></script>
		<script type="text/javascript">
		function save(){
			if($("lddm").value==""){
				alert("����ѡ��¥��!");
				return false;
			}
			if($("qsh").value==""){
				alert("���ҺŲ���Ϊ��!");
				return false;
			}
			if($("cws").value==""){
				alert("��λ������Ϊ��!");
				return false;
			}
			showTips('���ݴ����У����Ժ�......');
			var url="/xgxt/gyglGywh.do?method=qsxxwh&doType=save";
			refreshForm(url);
		}
		
		function modi(){
			var url="/xgxt/gyglGywh.do?method=qsxxwh&doType=modi";
			refreshForm(url);
		}
		
		function loadXqxx(){
			var xqdmArr=document.getElementsByName('xqdmArr');
			var xqmcArr=document.getElementsByName('xqmcArr');
			var xqdmHid=$("xqdmHid").value;
			var len=xqdmArr.length;
			var html="<table width='100%'><tr>";
			for(i=1;i<=len;i++){
				html+="<td width='33%'>";
				if(xqdmHid==xqdmArr[i-1].value){
					html+="<input type='radio' name='xqdm' id='xqxx_"+i+"' checked='true' value='"+xqdmArr[i-1].value+"'  onclick='changeYqxx();changeLdxx()'>"+xqmcArr[i-1].value;
				}else{
					html+="<input type='radio' name='xqdm' id='xqxx_"+i+"'  value='"+xqdmArr[i-1].value+"'  onclick='changeYqxx();changeLdxx()'>"+xqmcArr[i-1].value;
				}
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
			$("p_xq").innerHTML=html;
		}
		
		function loadYqxx(){
			var yqdmArr=document.getElementsByName('yqdmArr');
			var yqmcArr=document.getElementsByName('yqmcArr');
			var yqdmHid=$("yqdmHid").value;
			var len=yqdmArr.length;
			var html="<div style='width:98%;height=70px;overflow:auto;overflow-x:hidden'><table width='100%'><tr>";
			for(i=1;i<=len;i++){
				html+="<td width='33%'>";
				if(yqdmHid==yqdmArr[i-1].value){
					html+="<input type='radio' name='yqdm' checked='true' id='yqxx_"+i+"' value='"+yqdmArr[i-1].value+"' onclick='changeLdxx()'>"+yqmcArr[i-1].value;
				}else{
					html+="<input type='radio' name='yqdm' id='yqxx_"+i+"' value='"+yqdmArr[i-1].value+"' onclick='changeLdxx()'>"+yqmcArr[i-1].value;
				}
				
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
			$("p_yq").innerHTML=html;
		}
		
		function loadLdxx(){
			var lddmArr=document.getElementsByName('lddmArr');
			var ldmcArr=document.getElementsByName('ldmcArr');
			var lddmHid=$("lddmHid").value;
			var len=lddmArr.length;
			var html="<div style='width:98%;height=150px;overflow-y:auto;overflow-x:hidden'><table width='100%'><tr>";
			for(i=1;i<=len;i++){
				html+="<td width='33%'>";
				if(lddmHid==lddmArr[i-1].value){
					html+="<input type='radio' name='lddm' checked='true' id='ldxx_"+i+"' onclick='getLdxx()' value='"+lddmArr[i-1].value+"'>"+ldmcArr[i-1].value;
				}else{
					html+="<input type='radio' name='lddm' id='ldxx_"+i+"' onclick='getLdxx()' value='"+lddmArr[i-1].value+"'>"+ldmcArr[i-1].value;
				}
				
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
			$("p_ld").innerHTML=html;
		}
		
		function changeYqxx(){
			if($("czyq").value=="��"){
				var userStatus = $("userStatus").value;
				var userName = $("userName").value;
				var userDep = $("userDep").value;
				dwr.engine.setAsync(false);
				var xqdm=getXqdmArr();
				searchUtil.getGyglInfo("yq",xqdm,null,null,null,userStatus,userName,userDep,function(data){
					var html="<table width='100%'><tr>";
					var len=data.length;
					for(i=1;i<=len;i++){
						dm=""+data[i-1].yqdm;
						mc=""+data[i-1].yqmc;
						html+="<td width='33%'>";
						html+="<input type='radio' name='yqdm' id='yqxx_"+i+"' value='"+dm+"' >"+mc;
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
		}
		
		function getLddm(){
			var lddm=document.getElementsByName('lddm');
			var lddmValue="";
			var len=lddm.length;
			for(i=0;i<len;i++){
				if(lddm[i].checked){
					lddmValue=lddm[i].value;
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
				var html="<table width='100%'><tr>";
				var len=data.length;
				for(i=1;i<=len;i++){
					dm=""+data[i-1].lddm;
					mc=""+data[i-1].ldmc;
					html+="<td >";
					html+="<input type='radio' name='lddm' id='ldxx_"+i+"' onclick='getLdxx()' value='"+dm+"' >"+mc;
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
				$("p_ld").innerHTML=html;
			});
			dwr.engine.setAsync(true);
		}
		
		function getLdxx(){
			var tableName="xg_view_gygl_sslddm";
			var colList=["lddm","ldmc","xqmc","yqmc","cs","xbxd","bz"];
			var pk="lddm";
			var pkValue=getLddm();
			var czxq=$("czxq").value;
			var czyq=$("czyq").value;
			getOtherData.getTableInfo(tableName,colList,pk,pkValue,"",function(data){
				if(data != null){
					//¥������
					if(data[0] != "" && data[0] != null){
						$("lddmDiv").innerHTML=data[0];
						$("lddm").value=data[0];
					}else{
						$("lddmDiv").innerHTML="";
					}
					//¥������
					if(data[1]!="" && data[1]!=null){
						$("ldmcDiv").innerHTML=data[1];
					}else{
						$("ldmcDiv").innerHTML="";
					}
					
					//У������
					if(czxq=="��" && data[2]!="" && data[2]!=null){
						$("xqmcDiv").innerHTML=data[2];
					}else{
						if($("xqmcDiv")){
							$("xqmcDiv").innerHTML="";
						}
					}

					//԰������
					if(czyq=="��" && data[3]!="" && data[3]!=null){
						$("yqmcDiv").innerHTML=data[3];
					}else{
						if($("yqmcDiv")){
							$("yqmcDiv").innerHTML="";
						}
					}
					
					if(data[4]!="" && data[4]!=null){
						$("csDiv").innerHTML=data[4];
						//$("cs").value=data[4];
						
						var divHtml = "";
						for(var j=1;j<=data[4];j++){
							divHtml +="<input type='radio' name='rad_cs' ";
							divHtml +="id='cs_"+j+"' ";
							divHtml +="value='"+j+"'";
							if(j==1){
								divHtml +="checked='true'";
								$('cs').value = j;
							}
							divHtml +="onclick=\"$('cs').value=this.value\"";
							divHtml +="/>"+j;
							divHtml +="<bean:message key="lable.cs" />"
							if(j%7==0 && j!=1){
								divHtml +="<br/>";
							}
						}
						$("div_cs").innerHTML=divHtml;
					}else{
						$("csDiv").innerHTML="";
					}
					//�Ա�
					if(data[5]!="" && data[5]!=null){
						$("xbDiv").innerHTML=data[5];
						if(data[5] == "��"){
							$("xb_man").disabled = false;
							$("xb_man").checked=true;
							$("xb_woman").disabled = true;
						}else if(data[5] == "Ů"){
							$("xb_woman").disabled = false;
							$("xb_woman").checked=true;
							$("xb_man").disabled = true;
						}else{
							$("xb_woman").disabled = false;
							$("xb_man").disabled = false;
							$("xb_man").checked=true;
						}

					}else{
						$("xbDiv").innerHTML="";
					}
					//��ע
					if(data[6]!="" && data[6]!=null){
						$("bz").value=data[6];
					}else{
						$("bz").value="";
					}
				}
			});
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
		
		function loadCwxx(){
			//��λ��
			$("cwthead").style.display="";
			$("cwtbody").style.display="";
			var cws=eval($("cws").value);
			
			var html="<table width='100%'><tr>";
			for(i=1;i<=cws;i++){
				html+="<td width='20%'>";
				html+=i+"�Ŵ�: <select name='cwbjArr' id='cwlx_"+i+"'><option value='δ��ס'>δ��ס</option></select>";
				//<option value='���λ'>���λ</option>
				html+="<input type='hidden' name='cwhArr' id='cwh_"+i+"' value='"+i+"' />";
				html+="</td>";
				if(i%5==0){
					html+="</tr><tr>";
				}
			}
			if(cws%5!=0){
				for(i=1;i<=5-(cws%5);i++){
					html+="<td></td>"
				}
			}
			html+="</tr></table>";
			
			$("cwnr").innerHTML=html;
			
		}
		
		function jd(){
			if($("jd")){
				$("jd").focus();
			}
		}
		
		function checkPk(tableName,zdm,zdz,dis){
			
			dwr.engine.setAsync(false);
			zdz=$("lddm").value+"!!@@!!"+$("cs").value+"!!@@!!"+zdz;
			zdm="lddm||'!!@@!!'||cs||'!!@@!!'||qsh";
			getCommGygl.checkPkValue(tableName,zdm,zdz,function(data){
				
				if(data==false){
					if($("qshPrompcon")){
						$("qshPrompcon").style.display="";
						if(dis=="yes"){
							$("btn_bc").disabled="true";
						}
					}
				}else{
					if($("qshPrompcon")){
						$("qshPrompcon").style.display="none";
						if(dis=="yes"){
							$("btn_bc").disabled="";
						}
					}
				}
			});
			dwr.engine.setAsync(true);
		}
		
		</script>
<%--		<style>--%>
<%--		.include_tab{border-collapse:collapse;border:0px;}--%>
<%--		.include_tab td{border-top:0;bordedr-right:1px solid red!importalt;border-bottom:0;border-left:0;}--%>
<%--		.include_tab_r{}--%>
<%--		</style>--%>
	</head>
	<body onload="loadXqYqLd();jd()">
		<html:form action="/gyglGywh" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="czxq" id="czxq" value="${czxq }"/>
			<input type="hidden" name="czyq" id="czyq" value="${czyq }"/>
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}"/>
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}"/>
			<input type="hidden" name="lddmHid" id="lddmHid" value="${rs.lddm}"/>
			<input type="hidden" name="bcxs" id="bcxs" value="��ѡ"/>
			<input type="hidden" name="lddm" id="lddm" />
			
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
			<div class="tab_cur" id="jd" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a >${title }</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button class="button2" id="btn_bc" onclick="save();return false;">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<button class="button2" id="btn_bc" onclick="modi()">
											�� ��
										</button>
									</logic:equal>
									<button class="button2" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѡ����Ҫά�����ҵ�¥��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal name="czxq" value="��">
						<tr>
							<th style="width:16%">
								����<bean:message key="lable.xiaoqu" />
							</th>
							<td colspan="3"  style="width:84%">
								<div id="p_xq" style='width:98%;height=70px;overflow:auto;overflow-x:hidden'>
								</div>
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="czyq" value="��">
						<tr>
							<th style="width:16%">
								����<bean:message key="lable.yuanqu" />
							</th>
							<td colspan="3"  style="width:84%">
								<div id="p_yq" style='width:98%;height:70px;overflow:auto;overflow-x:hidden'>
								</div>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.ld" />
							</th>
							<td colspan="3" id="" style="width:84%">
								<div id="p_ld" style='width:98%;height:70px;overflow:;overflow-x:hidden'>
								</div>
							</td>
						</tr>
						<thead>
							<tr>
							<th colspan="4">
								<span><bean:message key="lable.ld" />��Ϣ(<font color="blue">ע:����ѡ��¥��</font>)</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th  style="width:16%">
								<bean:message key="lable.ld" />����
							</th>
							<td  style="width:34%" >
								<div id="lddmDiv">
									
								</div>
							</td>
							<th  style="width:16%">
								<bean:message key="lable.ld" />����
							</th>
							<td  style="width:34%">
								<div id="ldmcDiv">
									
								</div>
								<logic:equal name="czxq" value="��">
								����<bean:message key="lable.xiaoqu" />
								<span id="xqmcDiv">
									
								</span>
								</logic:equal>
								
								<logic:equal name="czyq" value="��">
								����<bean:message key="lable.yuanqu" />
								<span id="yqmcDiv">
									
								</span>
								</logic:equal>
						</tr>
						</tr>
						<tr>
							<th  style="width:16%">
								<bean:message key="lable.cs" />
							</th>
							<td  style="width:34%">
								<div id="csDiv">
									
								</div>
							</td>
							<th  style="width:16%">
								�Ա�����
							</th>
							<td  style="width:34%">
								<div id="xbDiv">
									
								</div>
							</td>
						</tr>
						<tr>
							<th  style="width:16%">
								��ע
							</th>
							<td colspan="3"  style="width:84%">
								<html:textarea name="rs" rows="5" styleId="bz" style="width:98%"
									property="bz" styleId="bz" readonly="true"></html:textarea>
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>���Һ�
							</th>
							<td colspan="3">
								<html:text name="rs" property="qsh" styleId="qsh" maxlength="30"  
								onkeyup="checkPk('ssxxb','qsh',this.value,'yes');value=value.replace(/[^\d]/g,'')"/>
								
								<span class="msg_prompt2" id="qshPrompcon" style="display:none">
					              	<em class="prompcon">
					                	���Һ��Ѿ�����,��������д
					                </em>
					            </span>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��λ��
							</th>
							<td colspan="3">
								<html:select name="rs" property="cws"  styleId="cws" onchange="loadCwxx()">
									<html:option value=""></html:option>
									<html:options collection="cwsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����<bean:message key="lable.cs" />
							</th>
							<td colspan="3">
								<div id="div_cs">��ѡ��¥��</div>
								<html:hidden name="rs" property="cs" styleId="cs"/>
							</td>
						</tr>
						<tr>
							<th>
								�շѱ�׼
							</th>
							<td>
								<html:text name="rs" property="sfbz"  styleId="sfbz" onkeyup="value=value.replace(/[^\d]/g,'')"
								maxlength="10" />
							</td>
							<th>
								���ҵ绰
							</th>
							<td>
								<html:text name="rs" property="qsdh" styleId="qsdh" onkeyup="value=value.replace(/[^\d]/g,'')"
								maxlength="15" />
							</td>
						</tr>
						<tr>
							<th>
								���ұ��
							</th>
							<td>
								<html:radio name="rs" property="fpbj"  styleId="fpbj_yb" value="һ��"/>һ��
								<html:radio name="rs" property="fpbj"  styleId="fpbj_bl" value="����"/>����
							</td>
							<th>
								�����Ա�����
							</th>
							<td>
								<html:radio name="rs" property="xb"  styleId="xb_man" value="��"/>��
								<html:radio name="rs" property="xb"  styleId="xb_woman" value="Ů"/>Ů

							</td>
						</tr>
						<tr>
							<th>
								���Ż�ס
							</th>
							<td>
								<html:radio name="rs" property="kfhz" styleId="kfhz_bk" value="����"/>����
								<html:radio name="rs" property="kfhz" styleId="kfhz_ky" value="����"/>����
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								��ע
								</br>
								(����¼��150��)
							</th>
							<td colspan="3"  style="width:84%">
								<html:textarea name="rs" rows="5" styleId="qsbz" style="word-break:break-all;width:99%" 
									property="qsbz" styleId="qsbz" onblur="chLeng(this,150);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<%@ include file="/gygl/comm/gywh/cwxxwh.jsp"%>
				</table>
			</div>


			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("�����ɹ���");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
