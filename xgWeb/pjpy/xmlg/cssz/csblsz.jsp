<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
<script type="text/javascript">
function chgPage(objTr) {
	document.forms[0].action = "pjpy_xmlg_csblsz.do?act=" + objTr.id;
	document.forms[0].submit();
}
function disBmxx() {
	var bm = document.getElementById('bmlb').value;
	if (bm=='xy') {
		document.getElementById("zy").selectedIndex = 0;	
		document.getElementById("zy").disabled = true;
		document.getElementById("bj").selectedIndex = 0;	
		document.getElementById("bj").disabled = true;
	} else if (bm=='zy') {
		document.getElementById("bj").selectedIndex = 0;	
		document.getElementById("bj").disabled = true;
		document.getElementById("zy").disabled = false;
	} else if (bm=='bj') {
		document.getElementById("bj").disabled = false;
		document.getElementById("zy").disabled = false;
	}
}
function initData() {
	if (confirm('��ʼ�����ݽ�����յ�ǰ����ѧ���������õĸ���ѧ���������<bean:message key="lable.xsgzyxpzxy" />����������,����������!')) {
		if (confirm('�ò���������ת,ȷ��Ҫ������')) {
			refreshForm("pjpy_xmlg_initData.do");
			if ($("pt")) {
				BatAlert.showTips('���ڳ�ʼ�����ݣ���ȴ�...');
			}
		} 
	}
	return false;
}
function modiData() {
	var bl = curr_row.cells[7].innerText;
	bl = bl.replace(/(^\s*)|(\s*$)/g, "");
	if (bl == null || bl == '') {
		alert("��δ���ý�ѧ�����,���ܶԱ������е���!�����Ƚ��иý���ı������ò���.");
		return false;
	}

	modiAndDel('pjpy_xmlg_modiBlszxx.do?pkValue=','modi',600,450);
}
</script>
<body onload="disBmxx();pageCardOn('')">
	<html:form action="/xmlgpjpy.do?method=csblsz" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="operType" value="query"/>
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="tname" id="tname" value="jxj"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="nd" id="nd" value="${nd }"/>
		<input type="hidden" name="xq" id="xq" value="${xq }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				����λ��:�������� - �������� - ��ѧ���������
			</div>
		</div>
		<div class="xxk">
				<logic:notEmpty name="pageCard">
					<logic:iterate id="card" name="pageCard">
						<ul>
							<li id="<bean:write name="card" property="en"/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name="card" property="en"/>"
								onclick="chgPage(this)" class="xxk_off_m">
								&nbsp;
								<bean:write name="card" property="cn" />
								&nbsp;
							</li>
							<li id="<bean:write name="card" property="en"/>r"
								class="xxk_off_r"></li>
						</ul>
					</logic:iterate>
				</logic:notEmpty>
			</div>
		<fieldset>
			<legend>
				����ѡ��
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="" nowrap="nowrap">
							ѧ�꣺
							<html:select property="xn" styleId="xn" style="width:90px"
								styleClass="select" disabled="true" value="${xn }">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;
							��ѧ��:<html:select property="dm" styleId="dm">
								<html:option value=""></html:option>
								<html:options collection="jxjList" property="dm" labelProperty="mc"/>
							</html:select>
							&nbsp;&nbsp;
							��ѯ��Χ:
							<html:select property="bmlb" styleId="bmlb" onchange="disBmxx()">
								<html:options collection="fwList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('pjpy_xmlg_csblsz.do')">
								��ѯ
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							�꼶��
							<html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp;
							<bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; רҵ��
							<html:select property="zydm" style="width:170px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp; �༶��
							<html:select property="bjdm" style="170px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</thead>
			</table>
		</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						${rsNum}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ���Խ�������˫��һ�п��Խ��б���������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								align="center" ondblclick="modiData()">
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=pjpyXmlgActionForm"></jsp:include>
												</TD>
											</TR>
											<TR>
												<TD height=3></TD>
											</TR>
										</TABLE>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2"
								onclick="showTopWin('chg_prise_xn.do',300,200)"
								style="width:100px">
								����ѧ��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_add"
								onclick="initData()"
								style="width:80px">
								��ʼ������
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_xg"
								onclick="showTopWin('pjpy_xmlg_jxjBlsz.do',860,600)"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_xg"
								onclick="showTopWin('pjpy_xmlg_tzrsFssz.do',450,300)">
								�����������Ʒ�ʽ����
							</button>
							&nbsp;&nbsp;
							 
							<button type="button" class="button2" id="btn_dc"
								onclick="window.open('pjpy_xmlg_jxjjehzOutPut.do')"
								style="width:80px">
								���鿴
							</button> 
						</div>
					</center>
				</logic:equal>
			<div id="tmpdiv"></div>
	</html:form>
		<logic:equal value="yes" name="writeAble" scope="request">
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</logic:equal>
		<logic:present name="inserted">
			<logic:equal value="true" name="inserted">
				<script>
					alert('�����ɹ�!');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ��!');
				</script>
			</logic:equal>
		</logic:present>
</body>
