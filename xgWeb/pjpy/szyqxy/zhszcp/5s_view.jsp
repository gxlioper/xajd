<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">
	function selectAllss(){
	var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
				checkBoxArr[i].checked = true;
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
	}
	function yd() {
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 350;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "------------------------ѡ��༶------------------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "�꼶:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='printnj' id ='printnj' onchange='initPrintZyList();initPrintBjList()'>" 
		dd_html += $('nj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right'>";
		dd_html += "<bean:message key="lable.xsgzyxpzxy" />:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='printxy' id ='printxy' onchange='initPrintZyList();initPrintBjList()'>" 
		dd_html += $('xy').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right'>";
		dd_html += "רҵ:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='printzy' id ='printzy'onchange='initPrintBjList()'>" 
		dd_html += $('zy').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right'>";
		dd_html += "�༶:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='printbj' id ='printbj'>" 
		dd_html += $('bj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right'>";
		dd_html += "�����¶�:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='yf' id ='yf'>" 
		dd_html += $('yf').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='printPy()';>ȷ��</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>�ر�</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
		//document.forms[0].action = "/xgxt/pjpyszyqwh.do?method=szyc_5sPrint";
		//document.forms[0].target = "_blank";
		//document.forms[0].submit();
		//document.forms[0].target = "_self";
	}
	
	function bbhz(){
		var url = "pjpyszyqwh.do?method=szyc_5sbb";
		showOpenWindow(url, '600','450');
	}
</script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyszyqwh" method="post">
    	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ۺ�����-ѧ���ۺ��������ɿ�-5S�����</a>
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
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc }</font>ѧ�� չ���ġ�</br>
				2.���<font color="blue">���ͨ��</font>��<font color="blue">��˲�ͨ��</font>����ѧ��������ˡ�</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType}"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="tmp" name="tmp" value="${tmp }"/>
    	<input type="hidden" name="pkstring" value="" />
    	<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
    	<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />
    	<!-- ����ɾ����Ϣ��ʾ -->
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo}"/>
    	
    	<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_shtg" onclick="sh5s('pjpyszyqwh.do?method=szyc_5sView&doType=save','tg');return false;">���ͨ��</a></li>
					<li><a href="#" class="btn_xg" onclick="sh5s('pjpyszyqwh.do?method=szyc_5sView&doType=save','btg');return false;">��˲�ͨ��</a></li>
					<li><a href="#" class="btn_sc" onclick="del5s('pjpyszyqwh.do?method=szyc_5sDel&doType=del');return false;">ɾ��</a></li>
					<li><a href="#" class="btn_tj" onclick="bbhz();return false;">�������</a></li>
				</ul>
			</div>
			</div>
		</logic:equal>
    	
		<div class="searchtab">
				<table width="100%">
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="refreshForm('pjpyszyqwh.do?method=szyc_5sView&go=go');">
								��ѯ
							</button>
							 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								����
							 </button>
							</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th>
								�꼶
							</th>
							<td>
							<html:select property="nj" styleId="nj" style=""
							onchange="initZyList();initBjList()" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="xn" style="" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="xq" style="" styleClass="select"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td>
								<logic:equal name = "userType" value="stu" scope="session">
									<input type="text" name = "xh" value="<bean:write name="userName" scope="session"/>" readonly="readonly" style="width:80px" class="inputtext"/>
								</logic:equal>
								<logic:notEqual name = "userType" value="stu" scope="session">
									<html:text property="xh" styleId="xh" styleClass="inputtext"
								 	style="width:80px"></html:text>
								 </logic:notEqual>
							</td>
							<th>							
								����
							</th>
							<td>
								<html:text property="xm" styleId="xm" styleClass="inputtext"
								 style="width:80px"></html:text>
							</td>
							<th>���״̬</th>
							<td><html:select property="shzt" styleClass="select" styleId="shzt">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
									<html:option value="����ͨ��">����ͨ��</html:option>
									<html:option value="����δ���">����δ���</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()"
									style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								רҵ
							</th>
							<td><html:select property="zydm" onchange="initBjList()" style="" 
								style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>�༶</th>
							<td><html:select property="bjdm" style="" 
								style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</div>
			
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
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
							    	<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td id="${tit.en}"
										onclick="tableSort(this)">
										${tit.cn}
									</td>
								</logic:iterate>
								<td>
									���״̬
								</td>
							</tr>
						</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor: hand;"
							ondblclick="modiTab('pjpyszyqwh.do?method=szyc_5sSh&pkValue=', '650', '500')">
							<td align=center>
								<input type = "hidden" id="pk" name="pk" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="checkbox" id="checkVal" name="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>
							<logic:iterate id="v" name="s" offset="1">
								<td align=center>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzyqxyZhszcpActionForm"></jsp:include>
			</logic:notEmpty>
		</div>
		<div id="tmpdiv1"></div>
		<div id="tmpdiv"></div>			
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <!-- ������ʾ -->
	 <jsp:include flush="true" page="/syscommon/deleteprompt.jsp"></jsp:include>
</body>
</html>