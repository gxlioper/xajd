<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>

	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		function choiceDisabled(userType,ele) {
   			if($("userType")){
				if ($("userType").value == userType) {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						if(document.getElementById(tmp[i])){
			  		 		document.getElementById(tmp[i]).disabled = true;
						}
					}
  		 		}  
			}
		}
		
		function isSh(){
			if(curr_row != null){
					var xysh = curr_row.getElementsByTagName('input')[2].value;
				var xxsh = curr_row.getElementsByTagName('input')[3].value;
				var userType = $('userType').value;
				
				if(userType == "njbzr" && xxsh == "ͨ��"){
					alert("��ѧ���ѱ��ϼ����ͨ��������Ȩ���");
				}else{
					modi('ghxy_bjbz.do?method=bjbzfshone');
				}
			}else{
				alert('��ѡ��Ҫ��˵������У�');
			}
		}
	</script>
	<body>
		<html:form action="/ghxy_ryjf.do?method=ryjfsh" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ${title }
				</div>
			</div>
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
							<logic:notEqual value="njbzr" name="userType">
									�꼶��
									<html:select property="queryequals_nj" styleId="nj1">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" 
											labelProperty="nj" />
									</html:select>
								</logic:notEqual>
								
								<logic:equal value="njbzr" name="userType">
									�꼶��
									<html:select property="queryequals_nj" styleId="nj"  onchange="initZyList();initBjList();">
											<html:options collection="njListForBzr" property="nj"
											labelProperty="nj"/>
									</html:select>
								</logic:equal>
								<input type="hidden" name="queryequals_xn" value="${xn }" />
								&nbsp;&nbsp;ѧ�꣺
								<select disabled="disabled">
									<option>
										${xn }
									</option>
								</select>
								<input type="hidden" property="queryequals_xq" value="${xq }" />
								&nbsp;&nbsp;ѧ�ڣ�
								<select disabled="disabled">
									<option>
										${xq }
									</option>
								</select>
								&nbsp;&nbsp;�¶ȣ�
								<html:select property="queryequals_yd" styleId="yd">
									<option>
									</option>
									<html:option value="01">��1�¶�</html:option>
									<html:option value="02">��2�¶�</html:option>
									<html:option value="03">��3�¶�</html:option>
									<html:option value="04">��4�¶�</html:option>
								</html:select>
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go" style="height:20px"
									onclick="allNotEmpThenGo('/xgxt/ghxy_bjbz.do?method=bjbzfsh&go=go')">
									�� ѯ
								</button>
								
								<br/>
								<button type="button" class="button2" style="height:20px" id="cz" onclick="czSearchCond('xn-xh-xq-nj1-yd-xm-xy-zy-bj');">
									�� ��
								</button>
							</td>
						</tr>

						<tr>
							<td>
								ѧ�ţ�
								<html:text property="querylike_xh" styleId="xh"></html:text>
								&nbsp;&nbsp;������
								<html:text property="querylike_xm" styleId="xm"></html:text>
								<logic:equal value="njbzr" name="userType">
									<input type="hidden" name="shzd" value="njbzrsh" />
<%--									<input type="hidden" name="shsj" value="njbzrshsj" />--%>
								</logic:equal>
								<logic:equal value="xx" name="userType">
									<input type="hidden" name="queryequals_njbzrsh" value="ͨ��" />
									<input type="hidden" name="shzd" value="xxsh" />
<%--									<input type="hidden" name="shsj" value="xxshsj" />--%>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xb" />��
								<html:select property="queryequals_xydm" style="width:180px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="queryequals_zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="queryequals_bjdm" style="width:180px"
									styleId="bj">
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
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()">
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
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('ghxy_bjbz.do?method=bjbzfViewAndModi&doType=view')"
								align="center" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>">
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="${v }" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										${v }
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="5">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="8" indexId="index">
									<td>					
										<input type="hidden" value="${v }" />
										${v }
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
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=ryjfForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble" scope="request">
					<button type="button" class="button2"
						onclick="batchData('primarykey_cbv','ghxy_bjbz.do?method=bjbzfsh&shjg=tg&doType=sh&go=go','sh')"
						style="width:80px">
						ͨ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="batchData('primarykey_cbv','ghxy_bjbz.do?method=bjbzfsh&shjg=btg&doType=sh&go=go','sh')"
						style="width:80px">
						��ͨ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="isSh();"
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
		</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>
