<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script type="text/javascript" >
<!--
	function distype() {
		var xxdm = document.getElementById('xxdm').value;
		if (xxdm=='10656') {
			if (document.getElementById('bname').checked) {
			document.getElementById('lx').selectedIndex=0;
			document.getElementById('lx').disabled=true;
			document.getElementById('btn_count').disabled=true;
		}
		}
		return;
		
	}
//-->
</script>
<body onload="xyDisabled('xy');distype();">
		<html:form action="/pjpyxnmzwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - ��Ϣά�� - ѧ���ɼ�ά��
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>"/>
			<input type="hidden" id="failinfo" name="failinfo" value="${failinfo }"/>
			<input type="hidden" id="tableName" name="tableName" value="${tableName }"/>
			<input type="hidden" id="realTable" name="realTable" value="${realTable }"/>
			<input type="hidden" id="xxdm" value="${xxdm }"/>
				<input type="hidden" name="zyV" id="zyV"/>
				<input type="hidden" name="bjV" id="bjV"/>
				<input type="hidden" name="xyV" id="xyV"/>
			<fieldset>
				<legend>
					��ѯ��:
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
									<html:radio property="bname" styleId="bname" value="0" style="cursor: hand" onclick="refreshForm('pjpy_xnmz_xscjwh.do');distype();">ѧ���ɼ���:</html:radio>
									<html:radio property="bname" styleId="bname" value="1" style="cursor: hand" onclick="refreshForm('pjpy_xnmz_xscjwh.do');distype();">ѧ�������:</html:radio>
							</td>
						</tr>
					</thead>
					</table>
			</fieldset>	
				
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap="nowrap">
								�꼶��
								<html:select property="nj" styleId="nj" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								&nbsp;&nbsp;
								</html:select>
								ѧ�꣺
								<html:select property="xn" style="width:90px" 
									styleId="xn" styleClass="select">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="xq" style="width:70px" 
									styleId="xq" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;
								ѧ�ţ�<html:text property="xh" styleId="xh" style="width:95px" styleClass="inputtext"></html:text>
								&nbsp;&nbsp;
								������<html:text property="xm" styleId="xm" style="width:95px" styleClass="inputtext"></html:text>
								<logic:equal value="10656" name="xxdm">
									&nbsp;&nbsp;
								����:
								<html:select property="lx" styleId="lx"  style="width:120px">
									<html:option value=""></html:option>
									<html:option value="0">����ѧ��</html:option>
									<html:option value="1">��ѧ��</html:option>
									<html:option value="2">���Ƽ���</html:option>
									<html:option value="3">ƽ������</html:option>
									
								</html:select>
								</logic:equal>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<logic:equal value="10656" name="xxdm">
									<button class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_xnmz_cjjdqry.do?act=xnmz');">
									��ѯ
									</button>
								</logic:equal>
								<logic:notEqual value="10656" name="xxdm">
									<button class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_xnmz_cjjdqry.do?act=shcb');">
									��ѯ
									</button>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:180px" styleId="xy"
								 onchange="initZyList();initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="zydm" style="width:180px" styleId="zy"
								 onchange="initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" style="width:180px" styleId="bj"
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
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="center"
								style="cursor:hand;"
   					 			ondblclick="">							
								<logic:iterate id="v" name="s" offset="0">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty><%--
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				--%>
				<logic:equal value="10656" name="xxdm">
					<div id="btn" class="buttontool" align="center" 
					style="position: absolute;left:1%;top:100px" width="100%">
					<button id="btn_count" class="button2" onclick="tb()" style="">
						�ɼ���������ͬ��
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport()" style="width:80px">
						���ݵ���
					</button>
				</div>
				</logic:equal>
			<div id="tmpdiv"></div>
			<logic:equal value="10656" name="xxdm">
			<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
				function tb() {
					if (confirm('�ôβ�������ͬ������ѧ���ɼ�������Ϣ,���ܺķѽϳ�ʱ��,ȷ��Ҫ������?')) {
						refreshForm('pjpy_xnmz_xjcjjttb.do');
						BatAlert.showTips('���ڲ�������ȴ�...');
					}
					return;
				}
				</script>
				</logic:equal>
				<logic:present name="inserted">
					<script>	
						alert('�������!');
					</script>
				</logic:present>
				<logic:present name="failinfo">
					<script>	
						alert(document.getElementById('failinfo').value;);
					</script>
				</logic:present>
		</html:form>
		
</body>
