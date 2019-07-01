<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			function initHxnl(obj){
				if ('' != jQuery(obj).val()){
					var params = {kmdm:jQuery(obj).val()};
					jQuery.ajaxSetup({async:false});
					jQuery.post('sztzAjax.do?method=initHxnl',params,function(data){
						jQuery('#hxnl').empty();
						
						jQuery('<option value=""></option>').appendTo('#hxnl');
						for (var i = 1 ; i < data.length; i++){
							var option="<option value='"+data[i].dm+"'>"+data[i].mc+"</option>";
								
							jQuery(option).appendTo('#hxnl');
						}
					},'json');
					
					jQuery('#hxnl').val('${sztzActionForm.queryequals_hxnl}');
					jQuery.ajaxSetup({async:true});
					
				}
			}
			
			function xmdy(){
				var len=jQuery("[name=primarykey_cbv]:checked").length;
				if(len==1){	
					var pkValue=jQuery("[name=primarykey_cbv]:checked").val();	
					var url = "/xgxt/sztz.do?method=xmDy";
					url +="&id="+pkValue;
					document.forms[0].action = url;
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";	
				}else{
					alertInfo("�빴ѡһ����Ҫ��ӡ����Ŀ��");
					return false;
				}
			}

			function delxm(){
				var pkValues = document.getElementsByName('primarykey_cbv');
				var pkValue ="";
				for (var i = 0 ; i < pkValues.length ; i++){
					if (pkValues[i].checked){
						pkValue +=pkValues[i].value+"!@!";
					}
				}
				if(pkValue ==""){
					alert("������ѡ��һ����¼��");
					return false;
					}else{
						jQuery.post('sztz.do?method=checkDelXm',
								{pkValue:pkValue},
								function(data){
								if(data==false){
									alertError("��ʹ�õ���Ŀ����ɾ����");
									return false;
									}else{
										batchData('primarykey_cbv','sztz.do?method=xmDel','del')
										}
							},'json')
						}
	     			
			
				//batchData('primarykey_cbv','sztz.do?method=kmslDel','del')
			}

			function xgxm(){
				var pkValues = document.getElementsByName('primarykey_cbv');
				var pkValue ="";
				var n=0;
				for (var i = 0 ; i < pkValues.length ; i++){
					if (pkValues[i].checked){
						pkValue +=pkValues[i].value;
						n++;
					}
				}
				if(n!=1){
					alertInfo("��ѡ��һ����¼��");
					return false;
					}else{
						jQuery.post('sztz.do?method=checkXgXm',
								{pkValue:pkValue},
								function(data){
								if(data==false){
									alertError("�������޸�У����Ŀ��");
									return false;
									}else{
										showUpdateWindow('primarykey_cbv','sztz.do?method=xmsbView&doType=update',750,600);
										}
							},'json')
						}
				
				}
			
		</script>
	</head>

	<body  onload="initHxnl(jQuery('#kmdm'))">
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sztz" method="post">
			<input type="hidden" id="realTable" value="xg_sztz_xmglb"/>
			<input type="hidden" id="tableName" name="tableName" value="xg_view_sztz_xmglb"/>
			<input type="hidden" name="isComm" value="true"/>
		
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href='#'
								   class="btn_zj"
								   onclick="showTopWin('sztz.do?method=xmsb',750,600)"
								   id="btn_zj">�걨</a>
							</li>
							<li>
								<a href="#" 
								   class="btn_xg"
								   onclick="xgxm()"
								   id="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" 
								   class="btn_sc"
								   onclick="delxm()"
								   id="btn_sc">ɾ��</a>
							</li>
							<%--<li>
								<a href="#"
									id="btn_dr"
									onclick="impAndChkData()"
									class="btn_dr"> ���� </a>
							</li>
							--%><li>
								<a href="#"
									id="btn_dc"
									onclick="configureExportData();return false;"
									class="btn_dc"> ���� </a>
							</li>
							<li>
								<a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">�����ֶ�ȷ��</a>
							</li>
							<li>
								<a href="#"
									id="btn_cs"
									onclick="showViewWindow('primarykey_cbv','sztz.do?method=sztzXmshLcgz','550','350',false,'�빴ѡһ����¼!')"
									class="btn_cs">���̸��� </a>
							</li>
							<li>
								<a href="#" class="btn_dc" onclick="xmdy();return false;" id="btn_dc">�����ɼ���</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('sztz.do?method=xmglManage&doType=query')">
											�� ѯ
										</button>
										<button class="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>ѧ��</th>
								<td>
									<html:select property="queryequals_xn" style="width:120px">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>ѧ��</th>
								<td>
									<html:select property="queryequals_xq" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
									</html:select>
								</td>
								<th width="16%">
									�꼶
								</th>
								<td width="34%">
									<logic:empty name="nj">
										<html:select property="queryequals_nj" styleId="nj" style="width:120px">
											<html:option value="">ȫ��</html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
									</logic:empty>
									<logic:notEmpty name="nj">
										<html:select property="queryequals_nj" styleId="nj" value="${nj }" disabled="true" style="width:120px">
											<html:option value="">ȫ��</html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
									</logic:notEmpty>
				            	</td>
							</tr>
							<tr>
								<th width="16%">
									Ժϵ
								</th>
								<td width="34%">
									<logic:empty name="xydm">
										<html:select property="queryequals_xydm" style="width:120px" styleId="xydm">
											<html:option value="">ȫ��</html:option>
											<html:option value="xj">У��</html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
									</logic:empty>
									<logic:notEmpty name="xydm">
										<html:select property="queryequals_xydm" style="width:120px" styleId="xydm" value="${xydm }" disabled="true">
											<html:option value="">ȫ��</html:option>
											<html:option value="xj">У��</html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
									</logic:notEmpty>
				            	</td>
								<th>��Ŀ����</th>
								<td>
									<html:text property="querylike_xmmc" style="width:117px"></html:text>
								</td>
								<th>������Ŀ</th>
								<td>
									<html:select property="queryequals_kmdm" onchange="initHxnl(this)" styleId="kmdm" style="width:120px">
										<html:options collection="kmdmList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>��������</th>
								<td>
									<html:select property="queryequals_hxnl" styleId="hxnl" style="width:120px">
										
									</html:select>
								</td>
								<th>��Ŀ����</th>
								<td>
									<html:select property="queryequals_xmlx" style="width:120px">
										<html:options collection="xmlxList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
							<th>
								�ٰ�ʱ��
							</th>
							<td>
								<html:text property="querygreaterequal_jbkssj" 
										   readonly="true"
										   styleId="querygreaterequal_jbkssj"
										   style="width:60px"
										   onblur="dateFormatChg(this);"
										   onclick="return showCalendar('querygreaterequal_jbkssj','y-mm-dd');"
								></html:text>
								��
								<html:text property="querylessequal_jbjssj"
										   readonly="true"
										   styleId="querylessequal_jbjssj"
										   style="width:60px"
										   onblur="dateFormatChg(this);"
										   onclick="return showCalendar('querylessequal_jbjssj','y-mm-dd');"
								></html:text>
							</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty>
					</span>
				</h3>
				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												alt="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										</td>
										<td>
											<a
												href="javascript:showTopWin('sztz.do?method=xmsbView&doType=view&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>','750','550');"
												class="pointer" style="color:#0A63BF"> 
													<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>
											</a>
										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=sztzActionForm"></jsp:include>
			</div>
			<logic:present name="message">
				<script defer>
					alertInfo('${message}',function(){
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					});
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
