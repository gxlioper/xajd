<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript" src="js/check.js"></script>

	<body onload="xyDisabled('xy');">
		
		<html:form action="/bxxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			
			<logic:equal value="xy" name="userType" scope="session">
				<input type="hidden" name="queryequals_xydm" value="${userDep }" />
			</logic:equal>
			
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�${title }
				</div>
			</div>
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
									ѧ�ţ�
									<logic:equal value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" styleId="xh" maxlength="20" style="width:80px" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" styleId="xh" maxlength="20" style="width:80px"></html:text>
									</logic:notEqual>
									
									&nbsp;&nbsp;
									������
									<html:text property="querylike_xm" maxlength="20" style="width:80px"></html:text>
									
								</td>
								<td  rowspan="3"align="center">
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/bxxx.do?method=lpjg&doType=query')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<Td>
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
							</Td>
						</tr>
						<tR>
							<td>
								������Ŀ:
								<html:select property="queryequals_lpxm" >
									<html:options collection="lpxmList" property="dm" labelProperty="mc"/>
								</html:select>
								&nbsp;&nbsp;
								��˽����
								<html:select property="queryequals_shjg">
									<html:option value=""></html:option>
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
						</tR>
					</thead>
				</table>
			</fieldset>
			<div id="tempDiv"></div>
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
									<tr onclick="rowOnClick(this)" style="cursor:hand;"
											ondblclick="showInfo('/xgxt/bxxx.do?method=lpInfo','view','600','450')"
										>
										<td align=center>
											<input type="checkbox" id="cbv" name="primarykey_cbv" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									    	<input type="hidden" value="${v }"/>
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
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<div>
						<br/><br/>
					</div>
				<logic:equal value="yes" name="writeAble">	
				<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
					<button class="button2" onclick="showInfo('/xgxt/bxxx.do?method=lpInfo','update','600','450')"
							style="width:80px">
							�޸�
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="deletedata('/xgxt/bxxx.do?method=lpjg&doType=del');"
							style="width:80px">
							ɾ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"onclick="impAndChkData();"style="width:80px">
						��������
					</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expData('/xgxt/bxxx.do?method=lpjg&doType=expData')" style="width:80px">
						��������
					</button>
				</div>
				</logic:equal>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:equal value="true" name="result">
			<script>
			alert('�����ɹ�');
			document.getElementById('search_go').click();
			</script>
		</logic:equal>
			
	</body>
