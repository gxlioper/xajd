<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');removeXnXq();bzrLoad();">
		<script language="javascript">
		function queryOne() {
	if((curr_row == null) || (curr_row == "")){
		return false;
	}
	var xh = curr_row.getElementsByTagName("input")[2].value;
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showTopWin(url, 800, 600);
}
		var Rows=new Array();	//����ѡ�е��ж���
		var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
		var cur_bgc = "#ffdead";//ѡ���б������ַ�����
		
function rowOver(objTr) {//
	curr_row = objTr;
}

function rowOut(objTr) {//
	curr_row = null;
}
			function xz_viewMore(curr_row)
			{
				var xxdm=document.all['xxdm2'].value;
				var xg_xxdm = document.getElementById("xxdm").value;
				if(xg_xxdm=="10402"){//����ʦ��
					viewMore('modi');
				} else if("no"==xxdm)
				{	
					viewMore('view');
				} else if("10110"==xxdm)
				{
					if("zhszcp"==document.all['realTable'].value)
					{
					var xn=curr_row.cells[1].innerText;
					var nd=curr_row.cells[0].innerText;
					var xh=curr_row.cells[3].innerText;
				    var url='/xgxt/pjpy_zbdx_weihu_one.do?doType=view';
				    url=url+"&xn="+xn+"&nd="+nd+"&xh="+xh;
				    showTopWin(url,'550','500');
				} else {
				    viewMore('view');
				}
			}
			}
			function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
			function chgrychlists(xydms) {
		var xydm = document.getElementById(xydms).value;
		chgRychlist.xyRychList(xydm,function(data) {
					DWRUtil.removeAllOptions('rychdm');			
					var o = [{id:'',labelText:''}];
					DWRUtil.addOptions('rychdm',o,'id','labelText');
					for(var i=0;i<data.length;i++){
						o = [{id:data[i].rychdm,labelText:data[i].rychmc}];
					DWRUtil.addOptions('rychdm',o,'id','labelText');
					}
		});
	}  		
	function chgJxjlists(xydms) {
		var xydm = document.getElementById(xydms).value;
		chgJxjlist.xyJxjList(xydm,function(data) {
					DWRUtil.removeAllOptions('jxjdm');			
					var o = [{id:'',labelText:''}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					for(var i=0;i<data.length;i++){
						o = [{id:data[i].jxjdm,labelText:data[i].jxjmc}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					}
		});
	}

		</script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/chgRychlist.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>

		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ��:
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<logic:notEqual value="stu" name="userType">
				<div class="rightcontent">

					<fieldset>
						<legend>
							�� ѯ
						</legend>
						<input type="hidden" name="zyV" id="zyV" />
						<input type="hidden" name="bjV" id="bjV" />
						<input type="hidden" id="userType" name="userType"
							value="<bean:write name="userType" scope="request"/>" />
						<input type="hidden" id="tableName" name="tableName"
							value="<bean:write name="tableName" scope="request"/>" />
						<input type="hidden" id="act" name="act"
							value="<bean:write name="act" scope="request"/>" />
						<input type="hidden" id="realTable" name="realTable"
							value="<bean:write name="realTable" scope="request"/>" />
						<input type="hidden" id="pk" name="pk"
							value="<bean:write name="pk" scope="request"/>" />
						<input type="hidden" id="xxdm" name="xxdm"
							value="<bean:write name="xxdm" scope="session"/>" />
						<input type="hidden" id="dxq" name="dxq"
							value="<bean:write name="writeAble" scope="request"/>" />
						<input type="hidden" id="isBzr" name="isBzr"
							value="<bean:write name="isBzr" scope="request"/>" />
						<input type="hidden" id="stab" name="stab" value="stab" />
						<input type="hidden" id="userName" name="userName"
							value="<bean:write name="userName" scope="session"/>" />
						<table width="100%" class="tbstyle">
							<logic:present name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2"
									value="<bean:write name="xxdm" scope="request"/>" />
							</logic:present>
							<logic:notPresent name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2" value="no" />
							</logic:notPresent>
							<logic:present name="sfxfzrx">
								<input type="hidden" id="sfxfzrx" name="sfxfzrx"
									value="<bean:write name="sfxfzrx" scope="request"/>" />
							</logic:present>
							<thead>
								<tr>
									<td align="left">
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />���ƣ�
										<html:select property="xymc" style="width:180px"
														styleId="xy">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
										&nbsp;&nbsp;���ڣ�
										<html:text property="dtsj" style="width:85px" onclick="return showCalendar('dtsj','y-mm-dd');"></html:text>
									
									
									
									ѧ�꣺ 
									<html:select property="xn" style="width:90px" styleId="xn"
													onchange="">
													<html:options collection="xnList" property="xn"
														labelProperty="xn" />
												</html:select>
								   ѧ�ڣ� 
								<html:select property="xq" style="width:60px" styleId="xq">
														<html:option value=""></html:option>
														<html:options collection="xqList" property="xqdm"
															labelProperty="xqmc" />
													</html:select>
								�ܴΣ� 
												<html:select name="zcmap" property="zc" style="width:120px"
														styleId="zc">
															<html:option value=""></html:option>
														<html:option value="1~2��"></html:option>
														<html:option value="3~4��"></html:option>
														<html:option value="5~6��"></html:option>
														<html:option value="7~8��"></html:option>
														<html:option value="9~10��"></html:option>
														<html:option value="11~12��"></html:option>
														<html:option value="13~14��"></html:option>
														<html:option value="15~16��"></html:option>
														<html:option value="17~18��"></html:option>
														<html:option value="19~20��"></html:option>
													</html:select>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<button type="button" class="button2" style="height: 40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/data_search.do');">
											��ѯ
										</button>
									</td>

								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��ʾ��¼����
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"><logic:present name="qssj">(<bean:write
											name="qssj" />--</logic:present> <logic:present name="zzsj">
										<bean:write name="zzsj" /> Υ������)</logic:present>
									��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;��סCtrl���Զ�ѡ</font>
							</legend>

							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor: hand">
										<logic:notPresent name="xsjxjb">
											<logic:iterate id="tit" name="topTr" offset="1" length="5">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:notPresent>
										<td>ѧ��</td>
										<td>ѧ��</td>
										<td>�ܴ�</td>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="xz_viewMore(this)">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" id="jzid" name="jzid" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2" length="4">
														<td nowrap>
															<bean:write name="v" />
														</td>
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="7" length="1">
														<td nowrap>
															<bean:write name="v" />
														</td>
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="8" length="1">
														<td nowrap>
														<logic:equal value="01" name="v" >
																��
														</logic:equal>
														<logic:equal value="02" name="v">
																��
														</logic:equal>
														</td>
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="9" length="1">
														<td nowrap>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
									<!-- ��ѧ�� -->
								</logic:iterate>
							</table>
							<logic:notEqual value="12872" name="xxdm">
								<TABLE width="99%" id="rsTable1" class="tbstyle">
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
								<br />
								<br />
							</logic:notEqual>

						</fieldset>
					</logic:notEmpty>
					<br />
					<br />
					<div id="toolTipLayer"
						style="position: absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute; left: 1%; top: 100px" width="100%">
							<logic:equal value="yes" name="writeAble" scope="request">
								&nbsp;
								<logic:notPresent name="showzdjs">
									<logic:equal name="act" value="party">
										<button type="button" class="button2"
											onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											���µ�ѧ����
										</button>
										&nbsp;
									</logic:equal>
									<logic:equal name="act" value="prepare">
										<button type="button" class="button2"
											onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											���µ�ѧ����
										</button>
										&nbsp;
									</logic:equal>
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<logic:notEqual value="12872" name="xxdm">
											<button type="button" class="button2" onclick="viewMore('add')"
												style="width: 80px">
												�� ��
											</button>
										</logic:notEqual>
									</logic:notEqual>
											<button type="button" class="button2"
												onclick="
												<logic:equal value="10827" name="xxdm">
												<logic:equal value="view_xsrychb" name="tableName">updaterychxx('modi')</logic:equal>
												<logic:notEqual value="view_xsrychb" name="tableName">viewMore('modi')</logic:notEqual>
												</logic:equal>
												<logic:notEqual value="10827" name="xxdm">viewMore('modi')</logic:notEqual>"
												style="width: 80px">
												�� ��
											</button>
									&nbsp;
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											ɾ ��
										</button>
									</logic:notEqual>
									&nbsp;						
							</logic:notPresent>
								<logic:notEqual value="no" name="xydel">
									<button type="button" class="button2" onclick="Alldel()" style="width: 80px">
										ȫ��ɾ��
									</button>
							</logic:notEqual>
								<logic:equal value="yes" name="writeAble" scope="request">
											<button type="button" class="button2" onclick="impAndChkData()"
												style="width: 80px">
												��������
											</button>
									<button type="button" class="button2" onclick="dataExport()"
										style="width: 80px">
										��������
									</button>
								</logic:equal>
							</logic:equal>
						</div>
					</center>
				</div>
			</logic:notEqual>

			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal name="result" value="ok">
			<script language="javascript">
      				alert("�����ɹ���");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  				alert("����ʧ��! ");
	  		</script>
		</logic:equal>
		<!-- 	<script type="text/javascript" src="js/bottomButton.js"></script>  -->
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
					
					function hzyrychprint(){
						if (curr_row==null || curr_row=='') {
							alert('��ѡ��Ҫ��ӡ�������У�');
							return;
						} else 
							window.open('hzyrychprint.do?pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value);
					}
					function hzyprint() {
				     	if (curr_row==null || curr_row=='') 
				     	{
				     		alert('��ѡ��Ҫ��ӡ�������ݣ�����һ�м���!');
				     		return;
				     	}
				     	 else {
				     	 	var url = 'dxjxjsp.do?method=dxjxjsp&pk=';
				     	 	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
				     	 	window.open(url);
				     	 }
				     }
				     function hzyjxjmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('��ѡ��Ҫ������������.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyjxjmodi.do?pkValue='+pkValue,'670','550');
				     	}
				     } 
				     function hzzyrychmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('��ѡ��Ҫ������������.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyrychmodi.do?pkValue='+pkValue,'610','510');
				     	}
				     }
				     function wjsjzy(url) {
				     	var RowsStr="!!SplitOneSplit!!";   
				     	if (Rows.length==0) {
				     		alert('��ѡ��Ҫ������������,��סCtrl�����Զ�ѡ!');
				     		return;
				     	}
				     	if (confirm('ȷ��Ҫ��ѡ�������ת����ʷ��Ϣ����?')) {
				     		for (i=0; i<Rows.length; i++){ 										//�����ַ���
    							RowsStr+=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
							}
							showTips('���������У���ȴ�......');
							refreshForm(url+"?pkValue="+RowsStr);
				     	}
				     	return;
				     }
		</script>
	</body>
</html>
