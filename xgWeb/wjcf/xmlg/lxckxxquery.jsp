<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript">
	function lxcksq() {
		var pkValue = curr_row.getElementsByTagName("input")[0].value;
		window.close();
		window.dialogArguments.document.forms[0].action="wjcf_xmlg_lxckStusq.do?pkValue=" + pkValue;
		window.dialogArguments.document.forms[0].submit();
	}
</script>
<body onload="">
	<html:form action="/wjcfxmlgwh.do" method="post">
		<input type="hidden" name="operType" value="query"/>
		<input type="hidden" name="failInfo" id="failInfo" value="${failinfo }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ��:Υ�ʹ��� - �����У�쿴 - ��У�쿴���ݲ�ѯ
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
							����ʱ�䣺
							<html:text property="cfsj" styleId="cfsj"></html:text>
							<font color="red">(��ʽ2009��11��18���磺20091118)</font>
						</td>
						<td width="10" rowspan="" align="center" valign="middle">
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('wjcf_xmlg_lxckxxquery.do')">
								��ѯ
							</button>
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
						<font color="blue"></font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onmousemove="rowOnClick(this)" style="cursor:hand;"
								align="center" ondblclick="lxcksq()">
									<input type="hidden" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								<logic:iterate id="v" name="s" offset="2" >
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
														page="/sjcz/turnpage.jsp?form=wjcfXmlgActionForm"></jsp:include>
												</TD>
											</TR>
											<TR>
												<TD height=3></TD>
											</TR>
										</TABLE>
				</fieldset>
			</logic:notEmpty>
	</html:form>
</body>
