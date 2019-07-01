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
		<meta name="Copyright" content="正方软件 zfsoft" />
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
		var Rows=new Array();	//所有选中的行对象
		var ShiftStartRow="";		//Shift多选时存储开始行对象
		var cur_bgc = "#ffdead";//选中行背景（字符串）
		
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
				if(xg_xxdm=="10402"){//漳州师范
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
					当前所在位置:
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<logic:notEqual value="stu" name="userType">
				<div class="rightcontent">

					<fieldset>
						<legend>
							查 询
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
										年级：
										<html:select property="nj" style="width:70px"
											onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										<!--
										<logic:notEqual value="view_xslxfszsxx" name="tableName">
											<logic:notEqual value="xshsxfb" name="realTable">											
												&nbsp;&nbsp;学年：											
												<html:select property="xn" style="width:90px" styleId="xn"
													onchange="">
													<html:options collection="xnList" property="xn"
														labelProperty="xn" />
												</html:select>
											</logic:notEqual>
										</logic:notEqual>

										&nbsp;&nbsp;&nbsp;年度：
										<html:select property="nd" styleId="nd">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
  										-->
										<%--										<logic:present name="zjujxjrych">--%>
										<logic:notEqual value="bjhj" name="bjhjType">
										&nbsp;&nbsp;&nbsp;&nbsp;
										学号：
										<html:text property="xh" style="width:120px"></html:text>
										&nbsp;&nbsp;姓名：
										<html:text property="xm" style="width:85px"></html:text>
										</logic:notEqual>

									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<button type="button" class="button2" style="height: 40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/data_search.do');">
											查询
										</button>
									</td>

								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<logic:equal value="10827" name="xxdm">
													<html:select property="xydm" style="width:180px"
														styleId="xy" onchange="initZyList();initBjList();">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
										</logic:equal>
											<html:select property="xydm" style="width:180px" styleId="xy"
												onchange="initZyList();initBjList()">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										<logic:notEqual value="xyhj" name="xyhjType">
										&nbsp;&nbsp;专业：
										<html:select property="zydm" style="width:180px" styleId="zy"
												onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="bjdm" style="width:180px" styleId="bj">
												<logic:notEqual value="yes" name="isBzr">
													<html:option value=""></html:option>
												</logic:notEqual>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								显示记录数：
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"><logic:present name="qssj">(<bean:write
											name="qssj" />--</logic:present> <logic:present name="zzsj">
										<bean:write name="zzsj" /> 违纪名单)</logic:present>
									提示：双击一行可以查看详细信息；单击表头可以排序;按住Ctrl可以多选</font>
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
												</tr>
									<!-- 奖学金 -->
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
											onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											更新到学生库
										</button>
										&nbsp;
									</logic:equal>
									<logic:equal name="act" value="prepare">
										<button type="button" class="button2"
											onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											更新到学生库
										</button>
										&nbsp;
									</logic:equal>
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<logic:notEqual value="12872" name="xxdm">
											<button type="button" class="button2" onclick="viewMore('add')"
												style="width: 80px">
												增 加
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
												修 改
											</button>
									&nbsp;
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											删 除
										</button>
									</logic:notEqual>
									&nbsp;						
							</logic:notPresent>
								<logic:notEqual value="no" name="xydel">
									<button type="button" class="button2" onclick="Alldel()" style="width: 80px">
										全部删除
									</button>
							</logic:notEqual>
								<logic:equal value="yes" name="writeAble" scope="request">
											<button type="button" class="button2" onclick="impAndChkData()"
												style="width: 80px">
												导入数据
											</button>
									<button type="button" class="button2" onclick="dataExport()"
										style="width: 80px">
										导出数据
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
      				alert("操作成功！");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  				alert("操作失败! ");
	  		</script>
		</logic:equal>
		<!-- 	<script type="text/javascript" src="js/bottomButton.js"></script>  -->
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
					
					function hzyrychprint(){
						if (curr_row==null || curr_row=='') {
							alert('请选择要打印的数据行！');
							return;
						} else 
							window.open('hzyrychprint.do?pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value);
					}
					function hzyprint() {
				     	if (curr_row==null || curr_row=='') 
				     	{
				     		alert('请选择要打印的行数据，单击一行即可!');
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
				     		alert('请选择要操作的数据行.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyjxjmodi.do?pkValue='+pkValue,'670','550');
				     	}
				     } 
				     function hzzyrychmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('请选择要操作的数据行.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyrychmodi.do?pkValue='+pkValue,'610','510');
				     	}
				     }
				     function wjsjzy(url) {
				     	var RowsStr="!!SplitOneSplit!!";   
				     	if (Rows.length==0) {
				     		alert('请选择要操作的数据行,按住Ctrl键可以多选!');
				     		return;
				     	}
				     	if (confirm('确定要将选择的数据转入历史信息库吗?')) {
				     		for (i=0; i<Rows.length; i++){ 										//连接字符串
    							RowsStr+=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
							}
							showTips('处理数据中，请等待......');
							refreshForm(url+"?pkValue="+RowsStr);
				     	}
				     	return;
				     }
		</script>
	</body>
</html>
