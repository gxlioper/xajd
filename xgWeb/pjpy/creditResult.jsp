<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<html>
	<body onload="xyDisabled('xy');">
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<html:form action="/typj" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�${title }
				</div>
			</div>
			
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
			<input type="hidden" name="realTable" id="realTable" value="xsrychb"/>
			<input type="hidden" name="tableName" id="tableName" value="view_xsrychb"/>
			<input type="hidden" name="pk" id="pk" value="xh||xn||rychdm||xq"/>
			<input type="hidden" name="act" id="act" value="modi"/>
			
			<input type="hidden" name="syme" id="syme" value="${syme }">
			<input type="hidden" id="isFdy"name="isFdy" value="${isFdy }">
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			<logic:equal value="xy" name="userType" scope="session">
				<input type="hidden" name="queryequals_xydm" value="${userDep }"/>
			</logic:equal>
			
			<fieldset>
				<legend>
					��ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap>
								�꼶��
								<html:select property="queryequals_nj" onchange="initZyList();initBjList()" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
								&nbsp;&nbsp;
								ѧ�꣺
								<html:hidden property="queryequals_xn" value="${xn }"/>
								<html:select property="queryequals_xn" value="${xn }" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
								&nbsp;&nbsp;
								��ȣ�
								<html:hidden property="queryequals_nd" value="${nd }"/>
								<html:select property="queryequals_nd" value="${nd }" disabled="true">
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>
								&nbsp;&nbsp;
								
								ѧ�ţ�
								<logic:equal value="stu" name="userType" scope="session">
									<html:text property="querylike_xh" maxlength="20" style="width:80px" readonly="true"></html:text>
								</logic:equal>
								<logic:notEqual value="stu" name="userType" scope="session">
									<html:text property="querylike_xh" maxlength="20" style="width:80px"></html:text>
								</logic:notEqual>
								
								&nbsp;&nbsp;
								������
								<html:text property="querylike_xm" maxlength="20" style="width:80px"></html:text>
								
							</td>
								
								<td width="10" rowspan="3" align="center" valign="middle">
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/typj.do?method=creditResult&doType=query')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
								<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="queryequals_xydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="queryequals_zydm" onchange="initBjList()"  styleId="zy" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="queryequals_bjdm"  styleId="bj" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								</td>
						</tr>
						<tr>
							<td>
							�����ƺţ�
							<html:select property="queryequals_rychdm" style="width:145px" styleId="jxjdm">
								<html:options collection="rychList" property="dm"
									labelProperty="mc" />
							</html:select>
									&nbsp;&nbsp;����Ա���
									<html:select property="queryequals_fdysh">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />���
									<html:select property="queryequals_xysh">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
									&nbsp;&nbsp;ѧУ���
									<html:select property="queryequals_xxsh">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en" labelProperty="cn"/>
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
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px" >
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
										ondblclick="viewMore('view')"
										style="cursor:hand;
										     ">
										<td align=center name="a">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="checkbox" name="primarykey_cbv" id="cbv" value="<bean:write name="v"/>" >
												<input type="hidden" name="check" value="<bean:write name="v"/>">
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
											page="/sjcz/turnpage.jsp?form=guizhdxForm"></jsp:include>
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
		                		<button class="button2" onclick="viewMore('add')"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="viewMore('modi')"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="deletedata('/xgxt/typj.do?method=creditResult&doType=del');"
								style="width:80px">
								ɾ ��
							</button>
						 	&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="expData('/xgxt/typj.do?method=creditResult&doType=expData')" style="width:80px">
								��������
							</button>  
							</div>
					</logic:equal>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:present name="result">
			<script>
				alert(''+$('message').value);
				document.getElementById('search_go').click();
			</script>
	</logic:present>
	</body>
</html>
