<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
<script type="text/javascript" src="pjpy/xmlg/pjpyxmlg.js"></script>
<script type="text/javascript">
<!--
	function refForm() {
		var userType = document.getElementById('userType').value;
		if (userType=='xy') {
			refreshForm('pjpy_xmlg_jxjsh.do');
		}
	}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/xmlgpjpy.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="pt" id="pt" />
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<!-- 调整人数 -->	
		<input type="hidden" name="szrs" id="szrs" value="${szrs }"/>	
		<!-- 调整人数方式  -->
		<input type="hidden" name="fpfs" id="fpfs" value="${fpfs }"/>	
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置 - 评奖评优 - 审核 - 奖学金审核
			</div>
		</div>
		<fieldset>
			<legend>
				条件选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="" nowrap="nowrap">
							学年：
							<html:select property="xn" styleId="xn" style="width:90px"
								styleClass="select" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; 奖学金类别：
							<html:select property="lbdm" styleId="lbdm"
								onchange="refreshForm('pjpy_xmlg_jxjsh.do')">
								<html:option value=""></html:option>
								<html:options collection="jxjlbList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;&nbsp; 奖学金：
							<html:select property="jxjdm" styleId="jxjdm"
								onchange="refForm()">
								<html:option value=""></html:option>
								<html:options collection="jxjList" property="dm"
									labelProperty="mc" />
							</html:select>

							&nbsp;&nbsp; 审核状态：
							<html:select property="sh" styleId="sh">
								<html:option value=""></html:option>
								<html:options collection="shList" property="en"
									labelProperty="cn" />
							</html:select>
							<br />
							学号：
							<html:text property="xh" styleId="xh" styleClass="inputtext">
							</html:text>
							&nbsp;&nbsp;姓名：
							<html:text property="xm" styleId="xm" styleClass="inputtext">
							</html:text>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('pjpy_xmlg_jxjsh.do?operType=query');">
								查询
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							年级：
							<html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList();refForm()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; 专业：
							<html:select property="zydm" style="width:170px" styleId="zy"
								styleClass="select" onchange="initBjList();refForm()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp; 班级：
							<logic:notEmpty name="bjList">
							<html:select property="bjdm" style="170px" styleId="bj"
								onchange="refForm()" styleClass="select">
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
				未找到任何记录！
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<fieldset>
				<legend>
					记录数： ${rsNum} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">提示：双击一行可以查看详细信息；<logic:equal value="xy"
							name="userType"><font color="red">${fpfsxx }&nbsp;${szrsxx }</font></logic:equal> </font>
				</legend>
				<table width="100%" id="rsTable" class="tbstyle">
					<thead>
						<tr align="center" style="cursor:hand">
							<td nowrap>
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
						<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center"
							ondblclick="modiAndDel('pjpy_xmlg_jxjDgsh.do?pkValue=','modi',800,650)">
							<td>
								<input type="checkbox" id="cbv" name="cbv"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
									<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
							</td>
							<logic:iterate id="v" name="s" offset="2">
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
					<button type="button" class="button2" id="btn_add"
						onclick="checkShdata('pjpy_xmlg_jxjplsh.do?act=tg','jxj')" style="width:80px">
						审核通过
					</button>
					&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_xg"
						onclick="shdata('pjpy_xmlg_jxjplsh.do?act=btg')"
						style="width:80px">
						审核不通过
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
		<logic:equal value="yes" name="inserted">
			<script>
					alert('操作成功!');
					document.getElementById('search_go').onclick();
				</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script>
					alert('操作失败!');
					document.getElementById('search_go').onclick();
				</script>
		</logic:equal>
	</logic:present>
</body>
