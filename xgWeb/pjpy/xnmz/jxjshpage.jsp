<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript" src="pjpy/xnmz/xnmz.js">
<!--

//-->
</script>
<script type="text/javascript" src="js/BatAlert.js"></script>

<body onload="xyDisabled('xy');">
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
				<input type="hidden" name="zyV" id="zyV"/>
				<input type="hidden" name="bjV" id="bjV"/>
				<input type="hidden" name="xyV" id="xyV"/>
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap="nowrap">
								ѧ�꣺
								<html:select property="xn" style="width:90px" 
									styleId="xn" styleClass="select">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;
								�꼶:
								<html:select property="nj" styleId="nj" onchange="initZyList();initBjList()" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
								&nbsp;&nbsp;ѧ�ţ�
								<html:text property="xh" styleId="xh" style="width:95px" styleClass="inputtext"></html:text>
								&nbsp;&nbsp;
								������<html:text property="xm" styleId="xm" style="width:95px" styleClass="inputtext"></html:text>
								&nbsp;&nbsp;
								��ѧ��:
								<html:select property="jxjdm" styleId="jxjdm" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="shqry('pjpy_xnmz_jxjshqry.do');">
									��ѯ
								</button>
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
							<td>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="center"
								style="cursor:hand;background-color:<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v" /></logic:iterate>"
   					 			ondblclick="">
   					 			<td>	
   					 			<input type="checkbox" name="cbv" id="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>"/>						
   					 			</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty><div id="btn" class="buttontool" align="center" 
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" onclick="shandSubmit('pjpy_xnmz_jxjshres.do','tg');" style="width:80px">
						���ͨ��
					</button>
					&nbsp;&nbsp;
					<button class="button2" onclick="shandSubmit('pjpy_xnmz_jxjshres.do','btg');" style="width:80px">
						��˲�ͨ��
					</button>
					&nbsp;&nbsp;
					<button class="button2" onclick="dataExport()" style="width:80px">
						���ݵ���
					</button>
				</div>
			<div id="tmpdiv"></div>
			<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="inserted">
					<logic:equal value="yes" name="inserted">
						<script>
							alert('�������!');
							document.getElementById('search_go').click();
						</script>
					</logic:equal>
					<logic:equal value="no" name="inserted">
						<script>
							alert('' + document.getElementById('failinfo').value);
							document.getElementById('search_go').click();
						</script>
					</logic:equal>
				</logic:present>
		</html:form>
</body>
