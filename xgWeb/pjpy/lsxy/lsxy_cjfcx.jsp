<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="js/jsFunction.js"></script>
<script language="javascript" src="js/xgutil.js"></script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyLsxy" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰ����λ��: ${title }
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!-- ����ɾ����Ϣ��ʾ -->
    	<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td align="left">
							�꼶��
							<html:select property="queryequals_nj" styleId="nj" styleClass="select" style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
							&nbsp;&nbsp;
							ѧ�꣺
							<html:select property="queryequals_xn" style="width:90px" styleClass="select"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>								
							&nbsp;&nbsp;
							ѧ�ڣ�
							<html:select property="queryequals_xq" style="width:90px" styleClass="select"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>								
							&nbsp;&nbsp;
							ѧ��:
							<html:text property="querylike_xh" styleId="xh" styleClass="inputtext"
							 style="width:100px"></html:text>
							&nbsp;&nbsp;
							����:
							<html:text property="querylike_xm" styleId="xm" styleClass="inputtext"
							 style="width:100px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:25px" id="search_go"
									onclick="refreshForm('pjpyLsxy.do?method=cjfcx&act=qry');">
									��ѯ
								</button>
								<br>
								<button class="button2" style="height:25px" id="cz"
									onclick="czSearchCond('xn-xq-xn-nj-xh-xm-xy-zy-bj');return false;">
									����
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="queryequals_xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="queryequals_zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="queryequals_bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
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
								<font color="blue">��ʾ��������ͷ���Խ�������;</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="all" name="all" onclick="chec()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap="nowrap">
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;" ondblclick="modi('pjpyLsxy.do?method=cjcxOne&act=view','700','550');">
										<td align=center><input type="checkbox" name="primarykey_cbv" id="pkV" 
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										    <input type="hidden" value="<logic:iterate id="v" name="s" offset="9" length="1"><bean:write name="v"/></logic:iterate>"/>
										    <input type="hidden" value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v"/></logic:iterate>"/>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						<TABLE width="99%" id="rsTable1" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=pjpyLsxyForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
					</logic:notEmpty>
                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
						<button class="button2" onclick="wjcfDataExport('pjpyLsxy.do?method=expCjf')" style="width:80px">
							��������
						</button>								
					</div>
					<div id="tmpdiv"></div>
				</div>
			</div>
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <logic:present name="result">
	 	<logic:equal value="true" name="result">
	 		<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="false" name="result">
			<input type="hidden" id="message" value="${message}"/>
	 		<script>
	 			alert(document.getElementById('message').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 </logic:present> 
</body>