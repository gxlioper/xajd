<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript">	  
	function dydf(act) {
		if (curr_row == null) {
			alert("��ѡ��Ҫ���ֵ�ѧ��������ѡ��һ�м���!");
			return false;
		} else {
			showTopWin('pjpy_czxx_addDyfjfxx.do?pkValue=' + curr_row.cells[0].getElementsByTagName("input")[0].value + '&modi='+act, 700,550);
		}
	}
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/czxxPjpyTycj" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName"
			value="view_czxx_dyfjfb" />
		<input type="hidden" name="realTable" id="realTable"
			value="czxx_dyfjfb" />
		<input type="hidden" name="userName" id="userName"
			value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - �۲���Ϣά�� - �������ӷ�ά��
			</div>
		</div>
		<div class="rightcontent">
			<fieldset>
				<legend>
					�� ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;ѧ�ڣ�
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;�꼶��
								<html:select property="nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;ѧ�ţ�
								<html:text property="xh" style="width:100px" styleId="xh"></html:text>
								&nbsp;������
								<html:text property="xm" styleId="xm" style="width:80px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_czxx_dyfjfb.do?act=qry');">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="zydm" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" styleId="bj">
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
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						${rsNum }
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="cb" onclick="selectAll()">
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
							<tr onclick="rowOnClick(this);" style="cursor:hand;"
								ondblclick="dydf('view')">
								<td align="center">
									<input type="checkbox" id="cbv" name="cbv" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								</td>
								<logic:iterate id="v" name="s" offset="1" >
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="rsTable" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyCzxxActionForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
		</div>
	</html:form>
	<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
	<center>
		<logic:equal value="yes" name="writeAble">
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button type="button" class="button2" onclick="dydf('add')" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dr" onclick="impAndChkData()"
					style="width:80px">
					����
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="dataExport()"
					style="width:80px">
					����
				</button>
			</div>
			<script language="javascript">
                  document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
                  document.getElementById("btn").style.width = "96%";
                  window.setInterval("initBTNTool('btn')",1);
                </script>
		</logic:equal>
	</center>
</body>
<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("�����ɹ�!");
					Close();
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("����ʧ��,ԭ����������ݿ����Ѵ�����ͬ��¼!");
					Close();
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
