<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		function checkTjxnxq(){
			var dd_html = "";
			dd_html += "<table width='240' class='formlist'>";
			dd_html += "<tbody>";
			dd_html += "<tr >";
			dd_html += "<th align='right' width='30%'>";
			dd_html += "<font color='red'>*</font>ѧ��:";
			dd_html += "</th>";
			dd_html += "<td align='left'>";
			dd_html += "<select name='tjxn' id ='tjxn'>";
			dd_html += $('stjxn').innerHTML;
			dd_html += "</select>";
			dd_html += "</td>";
			dd_html += "</tr>";
			
			dd_html += "<tr>";
			dd_html += "<th align='right' width='30%'>";
			dd_html += "<font color='red'>*</font>ѧ��:";
			dd_html += "</th>";
			dd_html += "<td align='left'>";
			dd_html += "<select name='tjxq' id ='tjxq'>";
			dd_html += $('stjxq').innerHTML;
			dd_html += "</select>";
			dd_html += "</td>";
			dd_html += "</tr>";
		
			dd_html += "<tr bgcolor='EEF4F9'>";
			dd_html += "<td colspan='2' align='right'>";
			dd_html += "<button  onclick='wsjctj();'>ȷ��</button>";
			dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
			dd_html += "<button  onclick='closeStuDiv()'>�ر�</button>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tbody>";
			dd_html += "</table>";
			showTempDivForJs("��ѡ��ͳ�Ʒ�Χ",dd_html,280,140);	
		}
		
		function closeStuDiv(){
			$('tempDiv').style.display='none';
			i = document.getElementsByTagName("select").length;
			
			for (j = 0; j < i; j++) {
				var obj=document.getElementsByTagName("select")[j];
				if(obj.id!="stjxn" && obj.id!="stjxq"){
					obj.style.visibility = "";
					obj.style.display = "";
				}
			}
		}
		function wsjctj(){
			var xn=$('tjxn').value;
			var xq=$('tjxq').value;
			if(xn=="" || xq==""){
				alert("��ѡ��Ҫͳ�Ƶ�ѧ���ѧ�ڣ�");
				return false;
			}
			var url="/xgxt/zjcmGygl.do?method=wstjManage&xn="+xn+"&xq="+xq;
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
			closeStuDiv();
		}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zjcmGygl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="csh" name="csh" value="${csh }" />
			<html:select property="stjxn" style="display:none" styleId="stjxn">
				<html:options collection="xnList" property="xn" labelProperty="xn" />
			</html:select>												
			<html:select property="stjxq" style="display:none" styleId="stjxq" onchange="">
				<html:option value=""></html:option>
				<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
			</html:select>
			<!-- ������ end-->
			<!-- ҳǩ -->
			<div class="compTab" id="card">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li class="ha">
							<a href="#"
								onclick="$('go').value='';refreshForm('zjcmGygl.do?method=wsjcManage')"
							 	id="${card.whdmb }">
								<span>�鿴</span>
							</a>
						</li>
						<li>
							<a href="#" 
								onclick="$('go').value='';refreshForm('zjcmGygl.do?method=wsjcUpdate')"
							 	id="${card.whdmb }">
								<span>¼��</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- ҳǩ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/zjcmGygl.do?method=wsjcView','update','600','480')"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/zjcmGygl.do?method=wsjcManage','del')"
									class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">����</a>
							</li>		
						</logic:equal>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">����</a>
						</li>
						</logic:equal>
						<logic:equal name="xxdm" value="13104">
							<a href="#" 
									onclick="checkTjxnxq()"
									class="btn_tj">ͳ��</a>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="if(checkSearchTj('kssj','jssj')){allNotEmpThenGo('/xgxt/zjcmGygl.do?method=wsjcManage')}">
											��ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									У��
								</th>
								<td>
									<html:select property="xqdm" style="width: 100px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>													
								</td>
								<th>
									¥��
								</th>
								<td>
									<html:select property="lddm" style="width: 100px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="cs" style="width: 100px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="qsh" style="width: 100px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="" styleId="xn" onchange="">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>												
								</td>
								<th>
									ѧ��		
								</th>
								<td>
									<html:select property="xq" style="" styleId="xq" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									���ʱ��(��ʼ)
								</th>
								<td>
									<html:text  property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 60%"
										onclick="return showCalendar('kssj','y-mm-dd');"/>	
								</td>
								<th>
									���ʱ��(����)
								</th>
								<td>
									<html:text  property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 60%"
										onclick="return showCalendar('jssj','y-mm-dd');"/>		
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									
									<logic:equal value="xy" name="userType" scope="session">
										<html:hidden value="${userDep }" property="xydm"/>
									</logic:equal>
									
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm"
										onchange="initBjList()" styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td>
										�������ȼ�
									</td>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="showInfo('/xgxt/zjcmGygl.do?method=wsjcView','view','600','480')">
									<td align="center">
										<input type="checkbox" id="checkVal" name="checkVal" 
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align="left">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
	</body>
</html>
