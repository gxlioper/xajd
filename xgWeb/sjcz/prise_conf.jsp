<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript"></script>
	<body onload="xyDisabled('xy');chgDispconf('dispFlag')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_whlgdx.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">

		</script>
		<html:form action="/prise_conf_rs" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>��ǰ����λ�ã�</em><a>${title }</a>
					</p>
				</div>
		
			<input type="hidden" id="isFdy" value="" />
			<input type="hidden" id="zyV" name="zyV" />
			<input type="hidden" id="bjV" name="bjV" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
			<!-- �������������Ӵ����ж� -->
			<logic:equal value="on" name="xqmod">
			<input type="hidden" id="xqmod" name="xqmod" value="${xqmod }" />
			</logic:equal>
			<logic:notEqual value="on" name="xqmod">
			<input type="hidden" id="xq" name="xq" value="${xq }" />
			</logic:notEqual>
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${tableName }" />
			<input type="hidden" id="act" name="act"
				value="${act }" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" id="pk" name="pk"
				value="${pk }" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			
			
			<div class="toolbox">
				<!-- ��ť -->
					<div class="buttonbox">
					<ul>
						<logic:equal name="userType" value="xx" scope="request">
						<li>
						<a href="#" class="btn_ck"
							onclick="showTopWin('viewTotStuNum.do',700,500)">
							�鿴��������
						</a>
						</li>
					    <li>
					    <a href="#" class="btn_sz" onclick="chkPriseBat();" >
							��������
						</a>
						</li>
    						<logic:notEqual value="������ҵ��ѧ" name="xxmc" scope="session">
							<%--�人����ѧ--%>
							<logic:equal value="10497" name="xxdm">
								<a href="#" class="btn_ck" onclick="viewFpb()" >
									��������
								</a>
							</logic:equal>
							<%--�人����ѧend--%>
							<%--<logic:notEqual value="10497" name="xxdm">
							<button type="button" class="button2"
								onclick="showTopWin('viewFpb.do',750,500)">
								��������
							</button>
							</logic:notEqual>
    &nbsp;&nbsp;&nbsp;&nbsp;
    --%>
						</logic:notEqual>
						<li>
						<a href="#" class="btn_csh" onclick="priseDataInit()">
							��ʼ������
						</a>
						</li>
    					<logic:notEqual value="������ҵ��ѧ" name="xxmc" scope="session">
<%--							<button type="button" class="button2"--%>
<%--								onclick="showTopWin('chg_prise_xn.do',300,200)"--%>
<%--								style="width:100px">--%>
<%--								����ѧ��--%>
<%--							</button>--%>
						</logic:notEqual>
					</logic:equal>
					<li>
					<a href="#" id="exp_datas" class="btn_dc" 
						onclick="openWins()">
						��<bean:message key="lable.xsgzyxpzxy" />��������
					</a>
					</li>
<%--					<li>--%>
<%--					<a href="#" id="btn_" class="btn_sz" onclick="showTopWin('pjpy_tyb_pjsjsz.do',450,330)">������������</a>--%>
<%--					</li>--%>
						</ul>
					</div>
						
					<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<logic:equal value="10497" name="xxdm">
											<input type="hidden" name="go" value="a" />
											<button type="button" class="btn_cx"  id="search_go"
												onclick="listPriseConfJxj('/xgxt/prise_conf_rs.do')">
												��ѯ
											</button>
										</logic:equal>
										<logic:notEqual value="10497" name="xxdm">
												<input type="hidden" name="go" value="a" />
												<button type="button" class="btn_cx"  id="search_go"
													onclick="listPriseConfJxj('/xgxt/prise_conf_rs.do')">
													��ѯ
												</button>
										</logic:notEqual>
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
									<html:select property="xn" style="width:120px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								</td>
								
								<logic:equal value="on" name="xqmod">
									<th>
									ѧ��
									</th>
									<td>
									<html:select property="xq" style="width:90px" disabled="true"
										styleId="xq">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								
									<html:select property="nd" style="width:90px;display:none" styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
										</td>
								</logic:equal>
								<logic:notEqual value="13108" name="xxdm">
									<th>
									���
									</th>
									<td>
									<html:select property="nd" style="width:90px" disabled="true"
										styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
									</td>
								</logic:notEqual>

								<%--���人����ѧ--%>
								<logic:notEqual value="10497" name="xxdm">
									<th>��ѧ��</th>
									<td>
									<html:select property="xmdm" style="width:150px"
										styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
									</html:select>
									</td>
								</logic:notEqual>
								<%--���人����ѧend--%>

								<th>��ʾ��ʽ</th>
								<td>
								<html:select property="dispFlag" style="width:70px"
									styleId="dispFalg" onchange="chgDispconf('dispFlag');">
									<html:option value="xydm"><bean:message key="lable.xsgzyxpzxy" /></html:option>
									<html:option value="zydm">רҵ</html:option>
									<html:option value="bjdm">�༶</html:option>
								</html:select>
								</td>
							</tr>
							
							<logic:equal value="10497" name="xxdm">
								<tr>
									<th>
										��ѧ�����
										</th>
										<td>
										<html:select property="jxjfl" style="width:150px"
											styleId="jxjfl" onchange="initJxjList()">
											<html:option value=""></html:option>
											<html:options collection="jxjflList" property="jxjfldm"
												labelProperty="jxjflmc" />
										</html:select>
										</td>
										<th>��ѧ������</th>
										<td>
										<html:select property="xmdm" style="width:150px"
											styleId="jxjdm">
											<html:option value=""></html:option>
											<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
										</html:select>
									</td>
								</tr>
							</logic:equal>
							<tr>
							<th>
								�꼶
								</th>
								<td>
								<html:select property="nj" styleId="nj"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								</td>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
								<html:select property="xydm" style="width:160px" styleId="xy"
									onchange="refreshForm('/xgxt/prise_conf_rs.do')">
									<html:option value="">    ȫ��    </html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</td>
								<span id="dispZy"><th>רҵ</th><td><html:select
										property="zydm" style="width:160px;" styleId="zy"
										onchange="refreshForm('/xgxt/prise_conf_rs.do')">
										<html:option value="">    ȫ��    </html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select> </td></span>
								<span id="dispBj"><th>�༶</th><td><html:select
										property="bjdm" style="width:140px" styleId="bj">
										<html:option value="">    ȫ��    </html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select> </td></span>
						</tr>
						
						</tbody>
					</table>
				</div>
			</div>
			
				<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> 
						<logic:notEmpty name="rs">
						��¼����
						<bean:write name="rsNum" />
						<logic:equal value="12764" name="xxdm">��ʾ��˫��һ�п��Ե�����������ͷ��������</logic:equal>
							<logic:notEqual value="12764" name="xxdm">��ʾ��˫��һ�п��Ե���������������ͷ��������</logic:notEqual>
						</logic:notEmpty>
						</span>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
							<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="left" style="cursor:hand"
								ondblclick="if(curr_row.cells[4].innerText==''){alert('��δ���� �������������ܵ���������');return false;}showTopWin('/xgxt/prise_conf_one.do',450,400);">

								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<!--��ҳ��ʾ-->
					
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
			<%--			<logic:notEqual name="commanForm" property="xydm" value="all"--%>
			<%--				scope="request">--%>
			<%--				<div id="jxjtmp" style="position:absolute;width:98%" align="center">--%>
			<%--					<fieldset>--%>
			<%--						<legend>--%>
			<%--							<bean:write name="userDepName" />--%>
			<%--							��ѧ���������--%>
			<%--						</legend>--%>
			<%--						<table width="100%" class="tbstyle">--%>
			<%--							<thead>--%>
			<%--								<tr>--%>
			<%--									<td>--%>
			<%--										�� Ŀ--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										ѧҵһ��--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										ѧҵ����--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										ѧҵ����--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										��Ṥ��--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										���ʵ��--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										����--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										�����ܶ�--%>
			<%--									</td>--%>
			<%--								</tr>--%>
			<%--							</thead>--%>
			<%--							<tr onclick="rowOnClick(this)" style="cursor:hand">--%>
			<%--								<td>--%>
			<%--									�� ��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs1" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs2" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs3" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs4" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs5" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs6" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs7" />--%>
			<%--									Ԫ--%>
			<%--								</td>--%>
			<%--							</tr>--%>
			<%--							<tr onclick="rowOnClick(this)" style="cursor:hand"--%>
			<%--								style="color:<bean:write name="bgColor" />">--%>
			<%--								<td>--%>
			<%--									������--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs11" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs21" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs31" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs41" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs51" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs61" />--%>
			<%--									��--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs71" />--%>
			<%--									Ԫ--%>
			<%--								</td>--%>
			<%--							</tr>--%>
			<%--							<thead>--%>
			<%--								<tr>--%>
			<%--									<td colspan="5"--%>
			<%--										style="height:18px;filter:alpha(opacity=0);cursor:hand;"--%>
			<%--										align="right">--%>
			<%--									</td>--%>
			<%--									<td onclick="hidFlag=!hidFlag;setInterval('tm(125,85)',10);"--%>
			<%--										colspan="3"--%>
			<%--										style="height:18px;filter:alpha(opacity=50);cursor:hand;"--%>
			<%--										align="right">--%>
			<%--										<bean:write name="userDepName" />--%>
			<%--										��ѧ���������--%>
			<%--									</td>--%>
			<%--								</tr>--%>
			<%--							</thead>--%>
			<%--						</table>--%>
			<%--					</fieldset>--%>
			<%--				</div>--%>
			<%--			</logic:notEqual>--%>
			<logic:notEmpty name="initOK" scope="request">
				<logic:equal value="ok" name="initOK">
					<script language="javascript">
    alert("���ݳ�ʼ����ɣ�");
    </script>
				</logic:equal>
				<logic:equal value="no" name="initOK">
					<script language="javascript">
    alert("���ݳ�ʼ��ʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:equal value="yes" name="writeAble" scope="request">
				
					<%--					<logic:equal name="userType" value="xy" scope="request">--%>
					<%--						<button type="button" class="button2"--%>
					<%--							onclick="showTopWin('xySetStuNum.do',450,405);">--%>
					<%--							�ϱ���������--%>
					<%--						</button>--%>
					<%--					</logic:equal>--%>
					
					
		
			</logic:equal>
			<div id="tmpdiv"></div>
			<logic:present name="add">
				<logic:equal name="add" value="yes">
					<script language="javascript">
					alert("���������ɹ�");
				</script>
				</logic:equal>
				<logic:equal name="add" value="no">
					<script language="javascript">
					alert("��������ʧ�ܣ���ȷ��û�и�����������������");
				</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript">
if(document.getElementById("jxjtmp") != null){
	document.getElementById("jxjtmp").style.top = -85;
}

function openWins(){
	if (document.getElementById('jxjdm').value=='') {
		alert('��ѡ��ѧ�����!');
		return;
	} else {
		url = 'jxjrsdataexp.do?jxjdm=';
		url += document.getElementById('jxjdm').value;
		url += '&xydm=';
		url += document.getElementById('xy').value;
		url += '&zydm=';
		url += document.getElementById('zy').value;
		url += '&bjdm=';
		url += document.getElementById('bj').value;
		url += '&bmlb=';
		url += document.getElementById('dispFalg').value;
		url += '&nj=';
		url += document.getElementById('nj').value;
		if($('xq')){
			url += '&xq=';
			url += document.getElementById('xq').value;
		}
		window.open(url);
	} 
}
</script>
	</body>
</html>
