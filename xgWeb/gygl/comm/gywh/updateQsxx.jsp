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
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglGywh.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/searchUtil.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getOtherData.js"></script>
		<script type="text/javascript">
		function modi(){
			if($("qsh").value==""){
				alert("���ҺŲ���Ϊ��!");
				return false;
			}
			if($("cws").value==""){
				$("cws").value="0";
			}
			var url="/xgxt/gyglGywh.do?method=qsxxwh&doType=modi";
			refreshForm(url);
		}
		
		function showXszsxx(xh){
			var doType=$("doType").value;
			var pkValue=$("pkValue").value;
			var url="/xgxt/gyglGywh.do?method=zsxxInfo&doType="+doType+"&xh="+xh+"&pkValue="+pkValue;
			refreshForm(url,800,600);
		}
		
		function loadCwxx(){
				var doType=$("doType").value;
				if(doType!="modi"){
					$("cwthead").style.display="";
					$("cwtbody").style.display="";
					var cws=eval($("cws").value);
					var cwhArr=document.getElementsByName("cwhsArr");
					var xmArr=document.getElementsByName("xmArr");
					var xhArr=document.getElementsByName("xhArr");
					var cwbjArr=document.getElementsByName("cwbjsArr");
					var html="<table width='100%'><tr>";
					for(i=1;i<=cws;i++){
						html+="<td width='20%'>";
						html+=i+"�Ŵ�: ";
						var blog=false;
						for(j=0;j<cwhArr.length;j++){
							
							if(i==eval(cwhArr[j].value)){
								if(xmArr[j]){
									if(xmArr[j].value==null || xmArr[j].value=="" ){
										
										
										if("view"==doType){
										
											html+=cwbjArr[j].value;
										}else{
											html+="<select name='cwbjArr' id='cwlx_"+i+"' value='"+cwbjArr[j].value+"'>";
											html+="<option value='δ��ס'>δ��ס</option></select>";
											html+="<input type='hidden' name='cwhArr' id='cwh_"+i+"' value='"+i+"' />";
											
											//<option value='���λ'>���λ</option>
										}
									}else {
										if("view"==doType){
										
											html+="<a href='#' onclick=\"showXszsxx('"+xhArr[j].value+"')\"><font color='blue'>"+xmArr[j].value+"</font></a>";
										}else{
											html+="<a href='#' onclick=\"showXszsxx('"+xhArr[j].value+"')\"><font color='blue'>"+xmArr[j].value+"</font></a>";
										}
									}
									blog=true;
									break;
								}
							}
						}
						if(!blog){
							html+="<select name='cwbjArr' id='cwlx_"+i+"'>";
							html+="<option value='δ��ס'>δ��ס</option><option value='���λ'>���λ</option></select>";
							html+="<input type='hidden' name='cwhArr' id='cwh_"+i+"' value='"+i+"' />";
						}
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
				
				for(i=1;i<=cws;i++){
					for(j=0;j<cwhArr.length;j++){
						if(i==eval(cwhArr[j].value)){
							if($("cwlx_"+i)){
								$("cwlx_"+i).value=cwbjArr[j].value;
								break;
							}
						}
					}
				}
			}
		}
		
		function loadCws(){
			var doType=$("doType").value;
			if(doType!="modi" && doType!="view"){
				var fpbj=$("fpbjhid").value;
				if($("fpbjhid").value!="һ��"){
					$("div_help").style.display="none";
				}
			
				if($("fpbjhid").value!="һ��"){
					$("cwsSelect").style.display="none";
					$("cwsSpan").innerHTML="0";
					$("cwtbody").style.display="none";
					$("cwthead").style.display="none";
					$("cwshid").value="0";
					$("cws").value="0";
					
				}
				
				if($("fpbjhid").value=="һ��"){
					
					var selectArr=document.getElementsByTagName("select");
					for(i=0;i<selectArr.length;i++){
						if(selectArr[i].id=="cwsSelect"){
							$("cwsSelect")[0].selected="true";
							$("cwsSelect").style.display="";
							$("cwsSpan").innerHTML="";
						}
					}
					
					setCws();
					loadCwxx();
				}
			}else if(doType=="view" && $("cws").value=="0"){
				$("cwtbody").style.display="none";
				$("cwthead").style.display="none";
			}
		}
		
		function cwxxIni(){
			var doType=$("doType").value;
			if(doType!="modi" && doType!="view"){
				var fpbj=$("fpbjhid").value;
				if($("fpbjhid").value!="һ��"){
					$("div_help").style.display="none";
				}
			
				if($("fpbjhid").value!="һ��"){
					$("cwsSelect").style.display="none";
					$("cwsSpan").innerHTML="0";
					$("cwtbody").style.display="none";
					$("cwthead").style.display="none";
					$("cwshid").value="0";
					$("cws").value="0";
					
				}
				
				
			}else if(doType=="view" && $("cws").value=="0"){
				$("cwtbody").style.display="none";
				$("cwthead").style.display="none";
			}
		}
		
		
		function setCws(){
			if($("cwsSelect")){
				$('cws').value=$("cwsSelect").value
			}
		}
		</script>
	</head>
	<body onload="loadCwxx();cwxxIni()">
		<html:form action="/gyglGywh" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="czxq" id="czxq" value="${czxq }" />
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}" />
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}" />
			<input type="hidden" name="lddmHid" id="lddmHid" value="${rs.lddm}" />
			<input type="hidden" name="bcxs" id="bcxs" value="��ѡ" />
			<input type="hidden" name="yyrrz" id="yyrrz" value="${yyrrz}" />
			<input type="hidden" id="cwshid" name="cwshid" value="${rs.cws}" />
			<input type="hidden" id="cws" name="cws" value="${rs.cws}" />
			<logic:iterate name="cwxxList" id="cwxx" indexId="index">
				<input type="hidden" name="cwhsArr" value="${cwxx.cwh}" />
				<input type="hidden" name="xmArr" value="${cwxx.xm}" />
				<input type="hidden" name="xhArr" value="${cwxx.xh}" />
				<input type="hidden" name="cwbjsArr" value="${cwxx.cwbj}" />
			</logic:iterate>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>			
			<!-- ���� end-->
			<!-- ��ʾ��Ϣ START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					�����λ���ϵ�ѧ�����������Բ鿴��סѧ������ϸ��ס�����
					<br />
				</p>
				<a class="close" title="����"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="update">
										<button class="button2" onclick="modi()">
											�� ��
										</button>
										<button class="button2" onclick="Close();return false;">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="view">
										<button class="button2" onclick="Close();return false;">
											�� ��
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
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
								����
								<bean:message key="lable.ld" />
							</th>
							<td>
								<bean:write name="rs" property="ldmc" />
									<html:hidden name="rs" property="lddm" value="${rs.lddm }" />
								<logic:equal name="czxq" value="��">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xiaoqu" />:<bean:write name="rs" property="xqmc" />
								</logic:equal>
								<logic:equal name="czyq" value="��">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.yuanqu" />:<bean:write name="rs" property="yqmc" />
								</logic:equal>
							</td>
							<th>
								<bean:message key="lable.ld" />
								�Ա�����
							</th>
							<td>
								<bean:write name="rs" property="xbxd" />
							</td>
						</tr>
						<tr>
							<th>
								���Һ�
							</th>
							<td>
								<bean:write name="rs" property="qsh" />
								<html:hidden name="rs" property="qsh" value="${rs.qsh }" />
							</td>
							<th>
								����
								<bean:message key="lable.cs" />
							</th>
							<td>
								<bean:write name="rs" property="cs" />
								<html:hidden name="rs" property="cs" value="${rs.cs }" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��λ��
							</th>
							<td>

								<!-- �޸Ĳ��� -->
								<logic:equal name="doType" value="update">
									<!-- û������ס -->
									<logic:notEqual name="yyrrz" value="true">
										<html:select property="cwsSelect" styleId="cwsSelect"
											onchange="setCws();loadCwxx();" value="${rs.cws}">
											<html:options collection="cwsList" property="dm"
												labelProperty="mc" />
										</html:select>

										<span id="cwsSpan" />

									</logic:notEqual>
									<!-- ��������ס -->
									<logic:equal name="yyrrz" value="true">
										<span id="cwsSpan">${rs.cws}</span>
										<html:hidden property="cwsSelect" styleId="cwsSelect"
											value="${rs.cws}" />
									</logic:equal>


								</logic:equal>
								<!-- �޸Ĳ��� -->

								<logic:equal name="doType" value="view">
									<span id="cwsSpan">${rs.cws}</span>
									<html:hidden property="cws" styleId="cws" value="${rs.cws}" />
								</logic:equal>
							</td>
							<th>
								�ɷ��ס
							</th>
							<td>
								<!-- �޸Ĳ��� -->
								<logic:equal name="doType" value="update">
									<html:radio name="rs" property="kfhz" styleId="kfhz" value="����" />
								����
								<html:radio name="rs" property="kfhz" styleId="kfhz" value="����" />
								����
								</logic:equal>

								<logic:equal name="doType" value="view">
									${rs.kfhz}
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								���ұ��
							</th>
							<td>
								<!-- �޸Ĳ��� -->
								<input type="hidden" name="fpbjhid" id="fpbjhid"
											value="${rs.fpbj}" />
								<logic:equal name="doType" value="update">
									<logic:notEqual name="yyrrz" value="true">
										
										<html:radio name="rs" property="fpbj" styleId="fpbj"
											onclick="$('fpbjhid').value=this.value;loadCws()" value="һ��" />
										һ��
										<html:radio name="rs" property="fpbj" styleId="fpbj"
												onclick="$('fpbjhid').value=this.value;loadCws()" value="����" />
										����
									</logic:notEqual>
								<logic:equal name="yyrrz" value="true">
									${rs.fpbj}
									<html:hidden name="rs" property="fpbj" styleId="fpbj"/>
								</logic:equal>
								
								</logic:equal>

								<logic:equal name="doType" value="view">
									<input type="hidden" name="fpbjhid" id="fpbjhid"
										value="${rs.fpbj}" />
										${rs.fpbj}
									</logic:equal>

							</td>
							<th>
								�����Ա�
							</th>
							<td>
								<logic:equal name="rs" property="xbxd" value="���">
									<html:radio name="rs" property="xb" styleId="xb_man" value="��" />��
									<html:radio name="rs" property="xb" styleId="xb_woman" value="Ů" />Ů
								</logic:equal>
								<logic:notEqual name="rs" property="xbxd" value="���">
								${rs.xb}
								<html:hidden name="rs" property="xb" styleId="xb"
									value="${rs.xb }" />
								</logic:notEqual>
							</td>

						</tr>
						<tr>
							<th>
								���ҵ绰
							</th>
							<td>
								<logic:equal name="doType" value="update">
									<html:text name="rs" property="qsdh" styleId="qsdh"
										onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="15" />
								</logic:equal>
								<logic:equal name="doType" value="view">
									${rs.qsdh }
								</logic:equal>
							</td>
							<th>
								�շѱ�׼
							</th>
							<td>
								<logic:equal name="doType" value="update">
									<html:text name="rs" property="sfbz" styleId="sfbz"
										onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="10" />
								</logic:equal>
								<logic:equal name="doType" value="view">
									${rs.sfbz }
								</logic:equal>
							</td>

						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="width:84%">
								<logic:equal name="doType" value="update">
									<html:textarea name="rs" rows="5" styleId="bz"
										style="width:98%" property="bz" styleId="bz"
										style="word-break:break-all;width:99%"
										onblur="chLeng(this,150);"></html:textarea>
								</logic:equal>
								<logic:equal name="doType" value="view">
									<html:textarea name="rs" rows="5" styleId="bz"
										style="width:98%" disabled="true" property="bz" styleId="bz"
										style="word-break:break-all;width:99%"
										onblur="chLeng(this,150);"></html:textarea>
								</logic:equal>
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
