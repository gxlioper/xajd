<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="js/String.js"></script>
<script language="javascript" src="js/jsFunction.js"></script>
<script language="javascript" src="js/qtsjFunction.js"></script>
<script>
	function modi(url){
		if(curr_row != null){
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
			return true;
		}else{
			alert('��ѡ��Ҫ�����������У�');
			return false;
		}
	}
</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/gzdxQgzx.do" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="realTable" id="realTable" value="${realTable}" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - �ڹ���ѧ�������� - �����������					
				</div>
			</div>			
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">								
								�꼶��
								<html:select property="queryequals_nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;ѧ�꣺
								<html:select property="queryequals_xn">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;��ȣ�
								<html:select property="queryequals_nd">
									<html:option value=""></html:option>
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="queryequals_xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;ѧ�ţ�
								<html:text property="querylike_xh" style="width:80px"></html:text>
								&nbsp;&nbsp;������
								<html:text property="querylike_xm" style="width:80px"></html:text>								
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go" style="height:40px"
									onclick="allNotEmpThenGo('/xgxt/gzdxQgzx.do?method=jjfzSh')">
									�� ѯ
								</button>
							</td>
						</tr>
						<tr>
						<td>
							<bean:message key="lable.xsgzyxpzxy" />��
							<logic:notEqual value="true" name="fdyQx">
							<logic:equal value="xy" name="userType">							
								<html:hidden property="queryequals_xydm" value="${userDep}"/>
							</logic:equal>
							</logic:notEqual>
							<html:select property="queryequals_xydm"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp;רҵ��
							<html:select property="queryequals_zydm" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp;�༶��
							<html:select property="queryequals_bjdm" styleId="bj">
								<logic:notEqual value="yes" name="isBzr">
									<html:option value=""></html:option>
								</logic:notEqual>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
						</tr>	
						<tr>
							<td>								
							<logic:equal value="true" name="fdyQx">
								����Ա��ˣ�
								<html:select property="queryequals_fdysh">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								&nbsp;&nbsp;
							</logic:equal>							
							<logic:equal value="xy" name="userType">						
							<bean:message key="lable.xsgzyxpzxy" />��ˣ�
							<html:select property="queryequals_xysh">
								<html:option value=""></html:option>
								<html:option value="δ���">δ���</html:option>
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
							</html:select>
							&nbsp;&nbsp;
							</logic:equal>
							ѧУ��ˣ�
							<html:select property="queryequals_xxsh">
								<html:option value=""></html:option>
								<html:option value="δ���">δ���</html:option>
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
							</html:select>
							&nbsp;&nbsp;�걨ʱ�䣺
							<html:text property="querygreaterequal_sbsj" 
							           styleId="sjks" 
							           style="width:80px;inputtext"
								       styleClass="inputtext"
								       onblur="dateFormatChg(this)" 
									   onclick="return showCalendar('sjks','y-mm-dd');"></html:text>
							-
							<html:text property="querylessequal_sbsj" 
							           styleId="sjjs" 
							           style="width:80px;inputtext"
								       styleClass="inputtext"
								       onblur="dateFormatChg(this)" 
									   onclick="return showCalendar('sjjs','y-mm-dd');"></html:text>
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
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()">
								</td>
								<logic:iterate id="tit" name="topTr" offset="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('gzdxQgzx.do?method=jjfzShOne&type=view')" 
								style="cursor:hand;background-color:
										    <logic:iterate id="v" name="s" offset="1" length="1">
										    <bean:write name="v"/>
										    </logic:iterate>
										     ">
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" value="<bean:write name="v"/>" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> >
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="4">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=qgzxTyForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble" scope="request">		
					<button type="button" class="button2" onclick="dataBatch('gzdxQgzx.do?method=jjfzSh&shjg=ͨ��&type=save')"
						style="width:80px">
						ͨ ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataBatch('gzdxQgzx.do?method=jjfzSh&shjg=��ͨ��&type=save')"
						style="width:80px">
						��ͨ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="modi('gzdxQgzx.do?method=jjfzShOne')"
						style="width:80px">
						�������
					</button>
				</logic:equal>
				</div>

				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
					<input type="hidden" id="message" value="${message}">
					<script>
						alert(document.getElementById('message').value);
						document.getElementById('search_go').onclick();
					</script>
				</logic:present>
		</html:form>
	</body>
</html>
