<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<head>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
			function chec(){
		      for(i=0;i<document.getElementsByName("primary_cbv").length;i++){
		    	if(document.getElementsByName("primary_cbv")[i].disabled == false){
		    		document.getElementsByName("primary_cbv")[i].checked=document.getElementsByName("primary_cbv")[0].checked;
		    	}
		      }
		    }
		</script>
		
	</head>
	<body>
		<html:form action="/msxldc" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			
			<input type="hidden" id="userType" value="${user.userType }" />
			<input type="hidden" name="isFdy" value="${isFdy }"/>
			<input type="hidden" name="isBzr" value="${isBzr }" />
			<input type="hidden" name="userName" value="${user.userName }"/>
			
			<input type="hidden" id="userType" value="${user.userType }" />
			
			<logic:equal value="xy" name="user" property="userType">
				<input type="hidden" id="userDep" name="xydm" value="${userDep }" />
			</logic:equal>
			
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�${title }
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
								�꼶��
								<html:select property="nj" styleId="nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;ѧ�ţ�
								<html:text property="xh" styleId="xh" />
								&nbsp;&nbsp;������
								<html:text property="xm" styleId="xm" />
							</td>
						
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button class="button2" id="search_go" style="height:20px"
									onclick="allNotEmpThenGo('msxldc.do?method=jgfxManage');">
									�� ѯ
								</button>
									<br/>
								<button class="button2" style="height:20px" id="cz" onclick="searchReset();return false;">
									�� ��
								</button>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" styleId="xy" style="width:150px" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							&nbsp;&nbsp;רҵ��
								<html:select property="zydm" styleId="zy" style="width:150px" onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							&nbsp;&nbsp;�༶��
								<html:select property="bjdm" styleId="bj" style="width:150px">
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
				<fieldset>
					<legend>
						��¼����
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td><input type="checkbox" name="all" value="all" onclick="chec()"></td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr align="center">
								<td>
									<input type="checkbox" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" align="center" ondblclick="modi('msxldc.do?method=wjhdView',800,600);">
									<td> 
									
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="primary_cbv" value="${v }"/>
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="0">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)request.getAttribute("pageSize");
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr align="center">
								<td><input type="checkbox" disabled="disabled"/></td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=msdcForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
					</TABLE>
			</fieldset>
			
				<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px">
				<logic:equal value="yes" name="writeAble" scope="request">
					<button class="button2"
						onclick="modi('msxldc.do?method=pywhUpdate',800,600);"
						style="width:80px">
						�� ��
					</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
						onclick="batchData('primary_cbv','msxldc.do?method=jgfxManage&doType=del','del');"
						style="width:80px">
						ɾ ��
					</button>
				</logic:equal>
			</div>
		</html:form>
		
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alert($('message').value);
			</script>
		</logic:present>
	</body>
</html>
