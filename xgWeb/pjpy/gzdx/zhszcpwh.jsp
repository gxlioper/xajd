<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
<script type="text/javascript">
<!--
	function account() {
		var xn = document.getElementById('xn').value;
		var xydm = document.getElementById('xydm').value;
		if (xn==null||xn==''||xydm==null||xydm=='') {
			alert("�Զ�������ѧ��,<bean:message key="lable.xsgzyxpzxy" />Ϊ��λ,��ѡ���������!");
			return false;
		}
		if (confirm("�Զ����㽫ѧ��,<bean:message key="lable.xsgzyxpzxy" />Ϊ��λ���м���,\n���Ȼ���յ�ǰ�Ѽ��������ѧ�����ۺ����ʲ�������ɼ�,\nȻ���ٸ���ѧ���걨�ۺϱ��ֵַĵ÷ֽ��м���!")) {
			if (confirm("���ѧ�����ۺ����ʲ�������ɼ����Ե������ݵķ�ʽά����,���鲻Ҫ�Զ�����!")) {
				if ($("pt")) {
					BatAlert.showTips('���ڲ�������ȴ�...');
				}
				refreshForm('pjpy_gzdx_zhszcpwh.do?act=account');	
			}
		}
	}
	function exppmdata() {
		if (checkNotNull('xn-xydm')) {
			wjcfDataExport('pjpy_gzdx_expZhszcp.do');
		} else {
			alert("��ѡ��Ҫ�����ѧ���<bean:message key="lable.xsgzyxpzxy" />��");
			return false;
		}
	}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/gzdxPjpy.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="operType" value="query"/>
		<input type="hidden" name="tableName" value="${tableName }"/>
		<input type="hidden" name="realTable" value="${realTable }"/>
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ��: �������� - �۲���Ϣά�� - �ۺ����ʲ���
			</div>
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
								styleClass="select" >
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;
							ѧ�ţ�
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px">
							</html:text>
							&nbsp;&nbsp;������
							<html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px">
							</html:text>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<button class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('pjpy_gzdx_zhszcpwh.do')">
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
							<logic:notEmpty name="bjList">
							<html:select property="bjdm" style="width:180px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</logic:notEmpty>
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
						<font color="blue">��ʾ��������ͷ���Խ�������˫��һ�п��Բ鿴��ϸ��Ϣ��</font>
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
								align="center" ondblclick="modiAndDel('pjpy_gzdx_modiZhszcpxx.do?operType=view&pkValue=','modi',600,450)">
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
														page="/sjcz/turnpage.jsp?form=pjpyGzdxActionForm"></jsp:include>
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
							<button class="button2" id="btn_add"
								onclick="account()"
								style="">
								�Զ�����
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_add"
								onclick="showTopWin('pjpy_gzdx_addZhszcpxx.do',600,450)"
								style="width:80px">
								����
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_xg"
								onclick="modiAndDel('pjpy_gzdx_modiZhszcpxx.do?pkValue=','modi',600,450)"
								style="width:80px">
								�޸�
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_sc"
								onclick="deldata('pjpy_gzdx_zhszcpwhDelete.do')"
								style="width:80px">
								ɾ��
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_dr"
								onclick="impAndChkData()"
								style="width:80px">
								����
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_dc"
								onclick="dataExport()"
								style="width:80px">
								����
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_dc"
								onclick="exppmdata()"
								style="">
								�۲�༶�������
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
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert('�����ɹ�!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert('����ʧ��!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
</body>
