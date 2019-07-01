<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/pjpyCommService.js'></script>
		<script type="text/javascript" src="js/pjpy/comm/pjpy/pjxmList.js"></script>
		<script type="text/javascript">
			function getPjlc(obj){
				var xmdm = obj.value;
				DWRUtil.removeAllRows('tbody');
				if ('' != xmdm){
					///�Ӻ�̨��������lcmcValue�ַ��������������ʽ
					var lcmcValue = $('lcmcValue').value.replace('[','').replace(']','').split(',');
					$('shzt').disabled=true;
					pjpyCommService.getLcmcByXmdm(xmdm,lcmcValue,function(data){
						
						var i=0,n=0,cells=[];
						while (i < data.length){
							cells[n++]="&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].key;
							cells[n++]="<select name='"+data[i].key+"' style='width:160px' value='"+data[i].value+"'>"+$('shzt').innerHTML+"</select>";
							i++;
						}
						DWRUtil.addRows('tbody',[''],cells,{escapeHtml:false});
						
						for (var i = 0 ; i < data.length ; i++){
							jQuery("select[name="+data[i].key+"]").attr("value",data[i].value);
						}
					});
				} else {
					$('shzt').disabled=false;
				}
			}
			//��ӡ����
			function printPj(url){
				var num = document.getElementsByName("primarykey_cbv").length;
				var flag = false;
				var n = 0;
				var jxjName = "";
				var xh = "";
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("primarykey_cbv")[i];
					if(obj.checked == true){
						flag = true;
						var children = obj.parentNode.childNodes;
						jxjName = children[2].value;
						xh = children[4].value;
						n++;
					}
				}
				
				if(flag){
					if(n==1){
						 document.forms[0].action = url+"&jxjName="+jxjName+"&xh="+xh;
						 document.forms[0].target = "_blank";
						 document.forms[0].submit();
						 document.forms[0].target = "_self";
					}else{
						alert("�빴ѡһ����¼��");
					}
				}else{
					alert("�빴ѡһ����Ҫ��ӡ��¼��");
				}	
		    }
		    
		    //ǰ����Ŀ�ϱ�
			function goXmsb(){
				var url = "pjpyXmsb.do?method=xmsbManage";
					url+= "&xmdm="+$("xmdm").value;
					url+= "&bjdm="+$("bjdm").value;
				
				showWaitingDiv("30000");
				
				refreshForm(url);
			}
			
			//���ɷ�ɾ��
			function checkDel(){
				jQuery.ajaxSetup({async:false});
				var num = jQuery('input[name=primarykey_cbv]').length;
				var pk = new Array();
				var n = 0;
				for(var i=0;i<num;i++){
					var obj = jQuery('input[name=primarykey_cbv]')[i];
					if(obj.checked){
						pk[n]=escape(obj.value);
						n++;
					}
				}
					
				//��������
			 	var parameter = {
					"pk":pk.join("!!@@!!")
				};
				
				var url="pjpyJgcx.do?method=checkDel";
				
				showWaitingDiv("10000");
				jQuery.post(url,parameter,function(result){
					if(result != ""){
						alertInfo(result);
					}else{
						batchData('primarykey_cbv','pjpyJgcx.do?method=pjpyQuery&doType=del','del');
					}
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				});
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>

	<body onload="getPjlc($('xmdm'));xyDisabled('xy');hiddenTr($('moreTerm'));">
		<html:form action="/pjpyJgcx" method="post">
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" name="userType" value="${userType }" />
			<input type="hidden" name="userDep" value="${userDep }" />
		
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="lcmcValue" value="${lcmcValue }" />
			<input type="hidden" id="realTable" name="realTable" value="xg_pjpy_pjxmsqb" />
			<input type="hidden" id="tableName" name="tableName" value="xg_view_pjpy_jgcx" />

			<button type="button" id="forward" onclick="goXmsb()" style="display: none">��ת</button>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - �ҵ����� - �����ѯ</a>
				</p>
				
				<!-- ���߰��� -->
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
				<!-- ���߰��� end -->
				
				<!-- ��ع��� -->
				<p class="other" style="position:relative;">
					<a href="#" onclick="return false;" 
						onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
						style="display:block;height:30px;">��ع���</a>
				</p>
				<!-- ��ع��� end-->
			
			</div>
			
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.���<font color="blue">��������</font>������չʾ�������ص�������
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
		
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_mypj.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function79.png" />
							<p>�ҵ�����</p>
						</a>   	
					</div>
					
					<!-- ѧ���û� -->
					<logic:equal name="userType" value="stu">
					    <div class="liucheng_font floatleft">
					    	<a href="#" onclick="goOtherGnmk('pjpy_pjlc_xssq.do');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>��Ŀ����</p>
							</a>   	
						</div>
					</logic:equal>
					<!-- ѧ���û� end-->
					
					<!-- ��ѧ���û� -->
					<logic:notEqual name="userType" value="stu">
					    <div class="liucheng_font floatleft">
					    	<a href="#" onclick="goOtherGnmk('pjpy_pjlc_xssq.do');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>ѧ������</p>
							</a>   	
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="showTopWin('/xgxt/pjpyXmsb.do?method=sbfwChoose',600,550);return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
								<p>��ʦ�ϱ�</p>
							</a>
						</div>
					</logic:notEqual>
					<!-- ��ѧ���û� end-->
			
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->
			<div class="toolbox">
				<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<logic:notEqual name="userType" value="stu">
								<li>
									<a href="#" class="btn_sc"
										onclick="checkDel();return false;"
										id="btn_qx">ɾ��</a>
								</li>
								<%--<li>
									<a href="#" class="btn_dr"
										onclick="impAndChkData()"
										id="btn_dr">����</a>
								</li>
								--%><li>
									<a href="#" class="btn_dc"
										onclick="expData('pjpyJgcx.do?method=pjpyQuery&doType=exp')"
										id="btn_dc">����</a>
								</li>
							</logic:notEqual>
						    <li>
								<a href="#" class="btn_dy"
									onclick="printPj('pjpyCommPrint.do?method=printJsp')"
									id="btn_dy">��ӡ</a>
							</li>
							<%--<li>
								<a href="#"
									id="btn_dc"
									onclick="configureExportData();return false;"
									class="btn_dc"> ���� </a>
							</li>
							<li>
								<a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">�����ֶ�ȷ��</a>
							</li>
						--%></ul>
					</div>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>��������</strong>
										</label>
									</div>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('pjpyJgcx.do?method=pjpyQuery&doType=query')">
											�� ѯ
										</button>
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
									�꼶
								</th>
								<td>
									<html:select property="nj" style="width:160px" onmouseover=""
										styleId="nj" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select>
								</td>
								<th>ѧ��</th>
								<td>
									<logic:equal value="stu" name="userType">
										<html:text property="xh" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										<html:text property="xh"></html:text>
									</logic:notEqual>
								</td>
								<th>����</th>
								<td>
									<html:text property="xm"></html:text>
								</td>
								
							</tr>
							
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:160px" onmouseover=""
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>רҵ</th>
								<td>
									<html:select property="zydm" onchange="initBjList()" onmouseover=""
												 styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>�༶</th>
								<td>
									<html:select property="bjdm" styleId="bj" onmouseover="" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									��Ŀ����
								</th>
								<td>
									<html:select property="xmdm" style="width:160px" styleId="xmdm" onchange="getPjlc(this);">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
									</html:select>
								</td>
								<th>����ʱ��</th>
								<td>
									<html:text property="sqkssj" styleId="sqkssj" style="width:100px"
											onclick="return showCalendar(this.id,'y-MM-dd');"
											readonly="true"></html:text>
									-
									<html:text property="sqjssj" styleId="sqjssj" style="width:100px"
											onclick="return showCalendar(this.id,'y-MM-dd');"
											readonly="true"></html:text>
								</td>
								<th>���״̬</th>
								<td>
									<html:select property="shzt" style="width:160px" styleId="shzt">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
							
							<%--<tr style="display:none">
								<th>
									����ѧ��
								</th>
								<td>
									<html:select property="pjxn" style="width:160px" styleId="pjxn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									����ѧ��
								</th>
								<td>
									<html:select property="pjxq" style="width:160px" styleId="pjxq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									�������
								</th>
								<td>
									<html:select property="pjnd" style="width:160px" styleId="pjnd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							--%><%--<tr style="display:none">
								<th>
									��Ŀ����
								</th>
								<td>
									<html:select property="xmlx" style="width:160px" styleId="xmlx">
										<html:option value=""></html:option>
										<html:options collection="xmlxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<html:select property="xmxz" style="width:160px" styleId="xmxz">
										<html:option value=""></html:option>
										<html:options collection="xmxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��Ŀ��Χ
								</th>
								<td>
									<html:select property="xmfw" style="width:160px" styleId="xmfw">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
						--%></tbody>
						<tbody id="tbody">
						
						</tbody>
					</table>
				</div>
			</div> 

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)">
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
											<logic:iterate id="v" name="s" offset="4" length="1">
												<input type="hidden" value="${v }" />											
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="${v }" />											
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<logic:equal name="v" value="δ���">
													<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="v" value="ͨ��">
													<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="v" value="��ͨ��">
													<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="v" value="�˻�">
													<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="v" value="������">
													<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
												</logic:equal>
		
												<logic:notEqual name="v" value="�˻�">
												<logic:notEqual name="v" value="������">
												<logic:notEqual name="v" value="��ͨ��">
												<logic:notEqual name="v" value="ͨ��">
												<logic:notEqual name="v" value="δ���">
													${v }
												</logic:notEqual>
												</logic:notEqual>
												</logic:notEqual>
												</logic:notEqual>
												</logic:notEqual>
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
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
					page="/sjcz/turnpage.jsp?form=pjpyJgcxForm"></jsp:include>
			</div>
			<logic:present name="message">
				<script>
					alert("${message}");
				</script>
			</logic:present>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
