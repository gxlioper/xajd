<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/mdqrDWR.js"></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" defer="defer">
			function showXmxz(){
				showTopWin('/xgxt/zjxyRcsw.do?method=swffXmxz&doType=query','680','480');
			}
			
			function showLqtz(){
				var blog=false;
				for (i=0; i<document.getElementsByName("checkVal").length; i++){
			    	if(document.getElementsByName("checkVal")[i].checked){
			    		blog=true;
			    	}
				}
				
		    }
			
			
			function saveFfry(){
				var pkV=document.getElementsByName("checkVal");
				var blog=false;
				for(i=0;i<pkV.length;i++){
					if(pkV[i].checked && !pkV[i].disabled){
						blog=true;
					}
				}
				if(blog){
					refreshForm('/xgxt/zjxyRcsw.do?method=swffXmffManage&doType=save');
					hiddenMessage(true,true);
					BatAlert.showTips('���ڲ��������Ե�...');
				}else{
					alert("û����Ҫ����ļ�¼!");
					return false;
				}
			}
			
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffXmffManage&doType=${doType }');
			}
			
			function loadMrz(){
				if($("mrtj").value=="ff"){
					$("tj_sf_��").onclick();
				}else if($("mrtj").value=="tj"){
					$("tj_sf_��").onclick();
				}
			}
			
			function dxtz(){
				if( $("tzdxxh") ){
					var tzdxxh=document.getElementsByName("tzdxxh");
					if(tzdxxh.length>0){
						if (confirm("����ɹ�,�Ƿ���Ҫ���Ͷ���֪ͨ?")) {
							$("dxtz").value="fsdxtz";
							fsdxtz();
						}else {
							return false;
						}
					}
				}
			}
			
			function fsdxtz(){
				var url='/xgxt/zjxyRcsw.do?method=swffXmffManage&doType=';
				if($("dxtz").value=="fsdxtz"){
					url+='fsdxtz';
				}else{
					url+='zjfsdxtz';
				}
				refreshForm(url);
			}
			
			function setKgzt(value,num){
				var id = "dxtz"+num;
				$(id).value = value;
			}
		
			
			function showDiV(){
				var sfqd=document.getElementsByName("sfqd");
				var blog=false;
				
				
				for(i=0;i<sfqd.length;i++){
					if($("dxtz"+i) && $("dxtz"+i).value=="��"){
						blog=true;
					}
					
				}
				if(blog){
					viewTempDiv('����֪ͨ','divq',360,200);
				}else{
					alert("���޼�¼��Ҫ���Ͷ��Ų���!");
					return false;
				}
			}
			
			function getxx(){
				var sfqd=document.getElementsByName("sfqd");
				for(i=0;i<sfqd.length;i++){
					var text="";
					if($("sfqd_"+i).value=="��" && ($("sftz_"+i).value=="��֪ͨ")){
						text="��֪ͨ";
					}
					if($("sfqd_"+i).value=="��"){
						text="�޷�֪ͨ";
					}
					if($("sfqd_"+i).value=="��" && ($("sftz_"+i).value=="δ֪ͨ")){
						text="<input type=\"radio\" name=\"sftz"+i+"\" id=\"sftz_yes"+i+"\" value=\"��\"  onclick=\"setKgzt(this.value,"+i+")\"/>��Ҫ<input type=\"radio\" name=\"sftz"+i+"\" id=\"sftz_no"+i+"\" value=\"��\" checked  onclick=\"setKgzt(this.value,"+i+")\"/>����Ҫ"
					}
					$("td_"+i).innerHTML=text;
				}
			}
			
			function showSzdiv(){
				viewTempDiv('��������','plsz',360,200);
			}
			
			function pldxtzsz(){
				var sfqd=document.getElementsByName("sfqd");
				if($("sfxytz_yes").checked){
					for(i=0;i<sfqd.length;i++){
						if($("sftz_yes"+i)){
							$("sftz_yes"+i).checked="true";
							setKgzt("��",i);
						}
					}
				}else if($("sfxytz_no").checked){
					for(i=0;i<sfqd.length;i++){
						if($("sftz_no"+i)){
							
							$("sftz_no"+i).checked="true";
							setKgzt("��",i)
						}
					}
				}
				hiddenMessage(true,true);
			}
		</script>
	</head>
	<body onload="loadMrz();dxtz();getxx()">

		<html:form action="/zjxyRcsw" method="post">
			<input type="hidden" name="mrtj" id="mrtj" value="${mrtj}"/>
			<input type="hidden" name="dxtz" id="dxtz" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="pk" id="pk" value="${xmPkValue}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<input type="hidden" name="viewName" id="viewName" value="view_xg_swff_ffryb"/>
			<input type="hidden" name="tableName" id="tableName" value="view_xg_swff_ffryb"/>
			<input type="hidden" name="xn" id="xn" value="${xmxxMap.xn}"/>
			<input type="hidden"  name="xq" id="xq" value="${xmxxMap.xq}"/>
			<input type="hidden" name="nd" id="nd" value="${xmxxMap.nd}"/>
			<input type="hidden" name="xmlx" id="xmlx" value="${xmxxMap.xmlx}"/>
			<input type="hidden" name="ffsj" id="ffsj" value="${xmxxMap.ffsj}"/>
			<input type="hidden" name="url" id="url" value="/xgxt/zjxyRcsw.do?method=swffFfryManage&doType=view" />
			<!-- �߼���ѯ ���� -->
		    <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
		  	<input type="hidden" id="path" name="searchModel.path" value="swffFfryManage.jsp"/>
			<input type="hidden" name="path" value="zjxyRcsw.do?method=swffXmffManage"/>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:notEmpty name="dxtzdxList">
				<logic:iterate name="dxtzdxList" id="dxtzdx">
					<input type="hidden" name="tzdxxh" id="tzdxxh" value="${dxtzdx}"/>
				</logic:iterate>
			</logic:notEmpty>

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
					<div class="buttonbox">
						<ul>
							<li>
							<logic:equal name="doType" value="view">
								<a href="#" onclick="saveFfry()" class="btn_ccg"> ���� </a>
							</logic:equal>
							</li>
							<li>
								<a href="#" onclick="showDiV()" class="btn_fs"> ���Ͷ��� </a>
							</li>
							<li>
								<a href="#" onclick="showSzdiv()" class="btn_sz"> �������� </a>
							</li>
							<logic:equal name="doType" value="view">
							<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>		
							<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>
							
							<!-- ����Ŀά��ģ����뱾ģ�� -->
							<logic:equal name="goType" value="xmwh">
							<li>
								<a href="#" onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=update')" class="btn_fh"> ���� </a>
							</li>
							</logic:equal>
							
							<!-- ����Ŀά��ģ����� -->
							<logic:notEqual name="goType" value="xmwh">
							<li>
								<a href="#" onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffXmxz&doType=query')" class="btn_fh"> ���� </a>
							</li>
							</logic:notEqual>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<!-- new �汾 -->
			     <logic:equal name="superSearch" value="yes">
			     	<%@ include file="/comm/search/superSearchArea.jsp"%>
			     </logic:equal>
			     
			     <!-- old �汾 -->
			     <logic:equal name="superSearch" value="no">
				
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffXmffManage&doType=${doType }')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="querylike_xhzgh" style="width:150px"
										styleId="xh" />
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="querylike_xm" style="width:150px"
										styleId="xm" />
								</td>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList();" disabled="true"
											value="${userDep }">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="queryequals_xydm" value="${userDep}" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="queryequals_xydm" styleId="xy"
											style="width:150px" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="queryequals_zydm" styleId="zy"
										onchange="initBjList();" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<logic:equal name="doType" value="view">
							<tr>
								<th>
									�Ƿ�ȷ��
								</th>
								<td>
									<html:select property="queryequals_sfqd" styleId="sfqd"
										style="width:150px">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
							</tr>
							</logic:equal>
						</tbody>
					</table>
				</div>
				</logic:equal>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
						<font color="blue">��Ŀ���ƣ�${xmxxMap.xmmc }&nbsp;&nbsp;&nbsp;&nbsp;ѧ�꣺${xmxxMap.xn }&nbsp;&nbsp;&nbsp;&nbsp;ѧ�ڣ�${xmxxMap.xqmc }&nbsp;&nbsp;&nbsp;&nbsp;��ȣ�${xmxxMap.nd }</font>	
						</logic:notEmpty>
					</span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />

								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<input type="hidden" name="xhzgh" id="xhzgh"
											value="<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>" />
										<input type="checkbox" name="checkVal" id="pkV"
										value="<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>" 
										<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>
										/>
									</td>
									<logic:iterate id="v" name="s" offset="2" length='6'>
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="8" length='1'>
										<td nowrap>
											<input type="hidden" name="sfqd" id="sfqd_${index}" value="${v}"/>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="9" length='1'>
										<td style="display:none">
										<input type="hidden" name="sftzArr" id="sftz_${index}" value="${v}" />
										<input type="hidden" name="sftz" id="dxtz${index }" value="null" />
										</td>
										<td nowrap id="td_${index}">
											
											
										</td>
										
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
				</logic:notEmpty>
			</div>
			<div id="divq" style="display: none">
				<div class="tab">
				<table class="formlist">
					<thead>
						<tr><td>
							<span color="blue">
								���Ͷ���֪ͨ
							</span>
						</td></tr>
					</thead>
					<tbody>
					<tr>
						<td>
						���ŷ���ʱ��
						<html:text property="dxtzsj" styleId="dxtzsj" 
							onclick="return showCalendar('dxtzsj','yyyy-MM-dd HH:mm:ss');" 
							readonly="true" />
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td   align='right'>
							<button type="button" onclick='fsdxtz()'>ȷ��</button>
							<button type="button" class='button2' onclick='hiddenMessage(true,true);'>�ر�</button>	 
						</td>
					</tr>
					</tfoot>
				</table>
				</div>
			</div>	
			<div id="plsz" style="display: none">
				<div class="tab">
				<table class="formlist">
					<thead>
						<tr><td colspan="2">
							<span color="blue">
								��������֪ͨ
							</span>
						</td></tr>
					</thead>
					<tbody>
					<tr>
						<th>
							�Ƿ���Ҫ֪ͨ
						</th>
						<td>
							<input type="radio" name="sfxytz" id="sfxytz_yes" checked="true" value="��Ҫ"/>��Ҫ
							<input type="radio" name="sfxytz" id="sfxytz_no" value="����Ҫ"/>����Ҫ
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td  align='right' colspan="2">
							<button type="button" onclick='pldxtzsz()'>ȷ��</button>
							<button type="button" class='button2' onclick='hiddenMessage(true,true);'>�ر�</button>	 
						</td>
					</tr>
					</tfoot>
				</table>
				</div>
			</div>	
		</html:form>
		<logic:present  name="dxResult">
		<logic:equal value="false" name="dxResult">
			<script>
				alert("���Ͷ���ʧ��!");
			</script>
		</logic:equal>
		<logic:equal value="true" name="dxResult">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		</logic:present>
		<logic:present  name="result">
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>
		</logic:present>
	</body>
</html>
