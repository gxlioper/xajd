<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="tips" scope="request" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type='text/javascript'>
		function showSm(){
			var tjfs = $("tjfs").value;
			var xxdm = $("xxdm").value;
			if(xxdm =="10491"){//�й��ش�
				if(tjfs == "kxcwinfo"){
					$("sm").style.display = "";
				}else{
					$("sm").style.display = "none";
				}
			}
		}
		</script>
		<html:form action="/emptydormManager" method="post">

				<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="userName" name="userName"
						value="<bean:write name="userName" scope="session"/>" />
			    <input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="request"/>" />
				<input type="hidden" name="lcV" id="lcV" value=""/>
			    <input type="hidden" name="qshV" id="qshV" />	
			    <input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />	
			     
			<!-- ������ end-->
			<div class="toolbox">
								<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()" 
								class="btn_dc">����</a>
						</li>	
						</logic:equal>	
					</ul>
				</div>
				<!-- ��ť end-->
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="showTips('���������У���ȴ�......');allNotEmpThenGo('/xgxt/emptydormManager.do?go=go')">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<td colspan="2">
									<html:radio property="kxqk" styleId="kxqk" value="kxssinfo" onclick="cxInfo('kxssinfo')">����������Ϣ</html:radio>
								</td>
								<td colspan="2">
									<html:radio property="kxqk" styleId="kxqk" value="kxcwinfo" onclick="cxInfo('kxcwinfo')">���д�λ��Ϣ</html:radio>
									<html:hidden property="kxqk" styleId="tjfs"/>
								</td>
							</tr>	
							<!-- �ڶ��� -->
							<tr>
								<th>
									У��
								</th>
								<td>
								<html:select property="xqdm" style="" styleId="xqdm" onchange="setLdList()">
											<html:options collection="xqdmList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th>
									¥��
								</th>
								<td>
								<html:select property="lddm" style="" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
							</tr>	
							<!-- ������ -->
							<tr>
								<th>
									����
								</th>
								<td>
								<html:select property="cs" style="" styleId="cs" onchange="setQsList();">
											<html:options collection="csList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th>
									���Һ�
								</th>
								<td>
								<html:select property="qsh" style="" styleId="qsh" onchange="">
											<html:options collection="qsList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
							</tr>	
						</tbody>
					</table>
				</div>
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
							<logic:notEmpty name="rs">		
								<font color="blue">��������ϼƣ�	${kxqsNum }</font>
								&nbsp;&nbsp;&nbsp;
								<logic:notEmpty name="zcwshj">
									�ܴ�λ���ϼƣ�${zcwshj}
								</logic:notEmpty>
								<logic:notEmpty name="kxcwhj">
									<font color="blue">���д�λ���ϼƣ�${kxcwhj}</font>
								</logic:notEmpty>
							
							</logic:notEmpty>	
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand">
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td >
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xsgyglForm"></jsp:include>
						     <script type="text/javascript">
						      $('choose').className="hide";
						     </script>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
		</html:form>
	</body>
</html>
