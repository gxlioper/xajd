<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<!-- ͷ�ļ� -->
<html>
	<body onload="xyDisabled('xy');">
		<html:form action="/jygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�${title }
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"	value="${realTable }" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<fieldset>
				<legend>
					��ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap>
								�꼶��
								<html:select property="nj"
									onchange="initZyList();initBjList()" styleId="rxnf">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp; ѧ�ţ�
								<html:text property="xh" maxlength="20"
									style="width:80px"></html:text>
								&nbsp;&nbsp; ������
								<html:text property="xm" maxlength="20"
									style="width:80px"></html:text>
							</td>

							<td width="10" rowspan="3" align="center" valign="middle">
								<button class="button2" style="height:25px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/jygl.do?method=ecfpManage&doType=query')">
									��ѯ
								</button>
								&nbsp;&nbsp;
								<button class="button2" style="height:25px"
									onclick="searchReset();return false;">
									����
								</button>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm"
									onchange="initZyList();initBjList()" styleId="xy"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="zydm" onchange="initBjList()"
									styleId="zy" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" styleId="bj"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								ԭ��λ��
								<html:select property="ydw">
									<html:options collection="yrdwList" property="dm" labelProperty="mc"/>
								</html:select>
								&nbsp;&nbsp;���η��䵥λ��
								<html:select property="ecfpdw">
									<html:options collection="yrdwList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
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
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px">
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showInfo('/xgxt/jygl.do?method=ecfpUpdate','view','650','400');"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="cbv" name="primarykey_cbv"  value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
	                		<button class="button2" onclick="showTopWin('/xgxt/jygl.do?method=ecfpUpdate','650','400')"
								style="width:80px">
								����
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="showInfo('/xgxt/jygl.do?method=ecfpUpdate','update','650','400')"
								style="width:80px">
								�޸�
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="deletedata('/xgxt/jygl.do?method=ecfpManage&doType=del');"
								style="width:80px">
								ɾ��
							</button>
	                		&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"onclick="impAndChkDataForZdy('ty_bdsz')"style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="ZdyDataExport('ty_bdsz_bcnr')" style="width:80px">
								��������
							</button>
						</div>
					</logic:equal>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
