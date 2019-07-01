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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
	<script language="javascript">	
	function chLx(value){
		if(value != "全日制" && value !=""){
			$("xydm").disabled = true;
		}else{
			$("xydm").disabled =false ;
		}
	}
	
	function printBb(){
		
		var url = "/xgxt/bjlh_fyk.do?method=tbtjManage&doType=print";
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	
	function xx(lddm,cs,qsh){
		var pk = lddm+cs+qsh;
		var url = "/xgxt/bjlh_fyk.do?method=fykUpdate";
		url+="&doType=view";
		url+="&pk="+pk;
		showTopWin(url,'800','600');
	}
	
	function dispXylist() {
    	var lx = document.getElementById('lx').value;
    	if (lx != null && lx != '') {
    		//document.getElementById('fbbj').disabled = true;
    	}
    	if (lx!='全日制' && lx != '') {
    		document.getElementById('xydm').disabled = true;
    	} else {
    		document.getElementById('xydm').disabled = false;
    	}
    }
	</script>
	<body onload="dispXylist()">
		<html:form action="/bjlh_fyk" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" id="xslx" name="xslx" value="" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理-房源分配结果-图表统计
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
					<fieldset>
						<legend>
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td colspan="">
										<input type="radio" name="cjlx" value="0" id="cjlx" 
											onclick="refreshForm('/xgxt/bjlh_fyk.do?method=fykManage');" >
											结果查询
										&nbsp; 
										<input type="radio" name="cjlx" value="1" id="cjlx" checked>
										&nbsp; 
											图表统计
										<input type="radio" name="cjlx" value="2" id="cjlx" 
											onclick="refreshForm('/xgxt/bjlh_fyk.do?method=jjtjManage');" >
											精简统计		
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="showTips('数据统计中，请等待......');allNotEmpThenGo('/xgxt/bjlh_fyk.do?method=tbtjManage');">
											统计
										</button>
									</td>
								</tr>
								<tr>
									<td align="left">
										校区：
										<html:select property="xqdm" style="" styleId="xqdm" onchange="setLdList()">
											<html:options collection="xqList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;楼栋：
										<html:select property="lddm" style="" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;所属层数：
										<html:select property="cs" style="" styleId="cs" onchange="setQsList();">
											<html:options collection="csList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;寝室号：
										<html:select property="qsh" style="" styleId="qsh" onchange="">
											<html:options collection="qsList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>	
										<bean:message key="lable.xsgzyxpzxy" />：						
										<logic:equal name="isXy" value="yes">
										<html:select property="xydm" style="" styleId="xydm" disabled="true">
											<html:options collection="xyList" property="dm" labelProperty="mc" />
										</html:select>
										</logic:equal>
										<logic:equal name="isXy" value="no">
										<html:select property="xydm" style="" styleId="xydm" onchange="setZyList($('xslx').value);setBjList($('xslx').value)">
											<html:options collection="xyList" property="dm" labelProperty="mc" />
										</html:select>
										</logic:equal>
										&nbsp;&nbsp;学生类型：			
										<logic:equal name="isAdmin" value="yes">
										<html:select property="lx" style="" styleId="lx" onchange="chLx(this.value)">
											<html:options collection="xsList" property="en" labelProperty="cn" />
										</html:select>
										</logic:equal>
										<logic:equal name="isAdmin" value="no">
										<html:select property="lx" style="" styleId="lx" disabled="true">
											<html:options collection="xsList" property="en" labelProperty="cn" />
										</html:select>
										</logic:equal>
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
								注:颜色状态：
								<font color="green">满的；</font>
								<font color="red">空的；</font>
								<font color="orange">闲的.</font>
							</legend>
							<logic:iterate name="rs" id="xy">
								<table width="100%" class="tbstyle" style="">
									<tr>
										<td align="center" colspan="2">
											<bean:write name="xy" property="xymc" /><br>
											房间数:<bean:write name="xy" property="fjs" />
											床位数:<bean:write name="xy" property="zcws" />
											空床位数:<bean:write name="xy" property="kcws" />
											行李床位数:<bean:write name="xy" property="xlcws" />
										</td>
									</tr>
									<logic:iterate id="xq" name="xy" property="xqRs">
										<tr>
											<td align="center" colspan="2">
												${xq.xqMap.xqmc}<br>
												房间数:${xq.xqMap.fjs}
												床位数:${xq.xqMap.zcws}
												空床位数:${xq.xqMap.kcws}
												行李床位数:${xq.xqMap.xlcws}
											</td>
										</tr>
										<logic:iterate id="ld" name="xq" property="ldRs">
											<tr>
												<td align="center" colspan="2">
													${ld.ldMap.ldmc}<br>
													房间数:${ld.ldMap.fjs}
													床位数:${ld.ldMap.zcws}
													空床位数:${ld.ldMap.kcws}
													行李床位数:${ld.ldMap.xlcws}
												</td>
											</tr>	
											<logic:iterate id="cs" name="ld" property="csRs">
												<tr>				
													<td width="10%" valign="top">
														<font>第${cs.csMap.cs}层</font><br>
														房间数:${cs.csMap.fjs}<br>
														床位数:${cs.csMap.zcws}<br>
														空床位数:${cs.csMap.kcws}<br>
														行李床位数:${cs.csMap.xlcws}
													</td>
													<td>
													<table>
														<tr>
															<logic:iterate id="qs" name="cs" property="qsRs">
																<logic:iterate id="q" name="qs" indexId="index">		
																	<td bgcolor="${q.col}">
																		<a href="javascript:xx('${q.lddm}','${q.cs}','${q.qsh}')">${q.qsh}</a><br>
																		总床位数:${q.cws}<br>
																		行李床位:${q.xlcws}<br>
																		已住床位:${q.bxyyzrs}<br>
																		空闲床位:${q.kcws}<br>
																	</td>		
																	<%if((index.intValue()+1)%10==0){%>   
																		<% out.print("</tr>"); %>
																	<%}%> 
																</logic:iterate>   
															</logic:iterate>
														</tr>
													</table>
													</td>
												</tr>
											</logic:iterate>				
										</logic:iterate>
									</logic:iterate>
								</table>
							</logic:iterate>
						</fieldset>
					</logic:notEmpty>
				</div>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" id="btn_add" onclick="printBb()" style="width:80px">
						导出数据
					</button>
				</div>
			</center>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>
