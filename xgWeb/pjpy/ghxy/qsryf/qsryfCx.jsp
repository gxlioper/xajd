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
		<title><bean:message key="lable.title" />
		</title>
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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/pjpy/ghxy/qsryfCx.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/initQsxx.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		function checkAjax(){
		   if($("userType").value!="admin" && $("userType").value!="xx"
		   		&& $("userType").value!="stu"){
				if(!checkBox()){
					alert("请选择要查询的权限范围！");
					return false;
				}
			}
			if($("lc").value=="null"){
				$("lc").value="";
			}
			if($("qsh").value=="null"){
				$("qsh").value="";
			}
			refreshForm('/xgxt/ghxyQsryf.do?method=qsryfCx&doType=qry');
		}
	</script>
	<body >
		<html:form action="ghxyQsryf" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userDep" id="userDep" value="${userDep}" />
			<input type="hidden" name="realTable" id="realTable" value="ghxy_qsryfb" />
			<div class="title">
				<div class="title_img" id="title_m">
					所在位置：${title }			
				</div>
			</div>		
			<div class="xxk">
							<ul>
								<li id="001l"
									class="xxk_off_l"></li>
								<li id="001m"
									onclick="refreshForm('ghxyQsryf.do?method=qsryfWh&lx=wh')" class="xxk_off_m">
									&nbsp;
									寝室荣誉分维护
									&nbsp;
								</li>
								<li id="001r"
									class="xxk_off_r"></li>
							</ul>
							<ul>
								<li id="002l"
									class="xxk_on_l"></li>
								<li id="002m"
									onclick="refreshForm('ghxyQsryf.do?method=qsryfWh&lx=cx')" class="xxk_on_m">
									&nbsp;
									寝室荣誉分查询
								</li> 
								<li id="002r"
									class="xxk_on_r"></li>
							</ul>
			  			</div>	
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								&nbsp;&nbsp;学年：
								<html:select property="xn" styleId="xn">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;年级：
								<html:select property="nj" styleId="nj">
									<logic:notEqual name="qyfdy" value="true">
										<html:option value=""></html:option>
									</logic:notEqual>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;公寓园区：
								<html:select property="yqqdm" styleId="yqqdm">
									<html:option value=""></html:option>
									<html:options collection="yqqdmList" property="yqqdm"
										labelProperty="yqqmc" />
								</html:select>	
								&nbsp;&nbsp;批次：
								<html:select property="pc" styleId="pc">
									<html:option value=""></html:option>
									<html:options collection="ryfPcList" property="pcdm"
										labelProperty="pcmc" />
								</html:select>	
								
								
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="checkAjax()" style="height: 25px">
									查 询
								</button>
								<br>
								<button type="button" class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('xn-xq-yqdm-lddm-lc-qsh-pc-nj');">
											重 置
								</button>
							</td>
						</tr>
						<tr>
						<td>
							<logic:equal name="userType" value="admin">
								&nbsp;楼栋名：
								<html:select property="lddm"  styleId="lddm"
								onchange="getLcList()">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
								</html:select>
								&nbsp;楼层：
					    		<html:select property="cs" styleId="lc"
						   			onchange="getQshList2()">
						   			<html:option value=""></html:option>
									<html:options collection="lcList" property="dm"
									labelProperty="mc" />
								</html:select>
								&nbsp;寝室号：
								<html:select property="qsh"  styleId="qsh" >
									<html:option value=""></html:option>
									<html:options collection="qshList" property="dm" 
									 labelProperty="mc" />
								</html:select>
							</logic:equal>	
							<logic:equal name="userType" value="xx">
								&nbsp;楼栋名：
								<html:select property="lddm"  styleId="lddm"
								onchange="getLcList()">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
								</html:select>
								&nbsp;楼层：
					    		<html:select property="cs" styleId="lc"
						   			onchange="getQshList2()">
						   			<html:option value=""></html:option>
									<html:options collection="lcList" property="dm"
									labelProperty="mc" />
								</html:select>
								&nbsp;寝室号：
								<html:select property="qsh"  styleId="qsh" >
									<html:option value=""></html:option>
									<html:options collection="qshList" property="dm" 
									 labelProperty="mc" />
								</html:select>
							</logic:equal>
							<logic:notEqual name="userType" value="xx">
								<logic:notEqual name="userType" value="admin">
								
								<logic:equal name="userType" value="stu" > 
								&nbsp;楼栋名：
								<html:select property="queryequals_lddm"  styleId="lddm" 
								disabled="true">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
								</html:select>
								<html:hidden property="lddm" value="${lddm}"/>
								&nbsp;楼层：
					    		<html:select property="queryequals_cs" styleId="lc"  disabled="true"
					    			>
						   			<html:option value=""></html:option>
									<html:options collection="lcList" property="cs"
									labelProperty="cs" />
								</html:select>
								<html:hidden property="cs" value="${cs}"/>
								&nbsp;寝室号：
								<html:select property="queryequals_qsh"  styleId="qsh"  disabled="true"
									>
									<html:option value=""></html:option>
									<html:options collection="qshList" property="qsh" 
									 labelProperty="qsh" />
								</html:select>
								<html:hidden property="qsh" value="${qsh}"/>
								</logic:equal>
								
								<logic:notEqual name="userType" value="stu">
								&nbsp;楼栋名：
								<html:select property="lddm"  styleId="lddm" 
								  onchange="initCsByQx();">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
								</html:select>
								&nbsp;楼层：
					    		<html:select property="cs" styleId="lc" onchange="initQshByQx()"
						   			>
						   			<html:option value=""></html:option>
									<html:options collection="lcList" property="cs"
									labelProperty="cs" />
								</html:select>
								&nbsp;寝室号：
								<html:select property="qsh"  styleId="qsh" >
									<html:option value=""></html:option>
									<html:options collection="qshList" property="qsh" 
									 labelProperty="qsh" />
								</html:select>
								</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
							
							<logic:equal name="userType" value="xy">
								<html:checkbox property="yhqxfp" styleId="yhqxfp" value="xy"   onclick="changeList()"/><bean:message key="lable.xb" />
							</logic:equal>		
							<logic:equal name="isFdy" value="true">
								<html:checkbox property="yhqxfp"  styleId="yhqxfp" value="fdy"  onclick="changeList()"/>辅导员
							</logic:equal>		
							<logic:equal name="isGyfdy" value="true">
								<html:checkbox property="yhqxfp" styleId="yhqxfp" value="gyfdy"  onclick="changeList()"/>公寓辅导员
							</logic:equal>		
							<logic:equal name="isQyfdy" value="true">
								<html:checkbox property="yhqxfp"  styleId="yhqxfp"  value="qyfdy"  onclick="changeList()"/>区部辅导员
							</logic:equal>						
							</td>
						</tr>
						
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rsNum">
				<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
				</logic:empty>
			</logic:empty>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()">
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<logic:notEqual name="index" value="1">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
									</logic:notEqual>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								 style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
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
								page="/sjcz/turnpage.jsp?form=ghxyQsryfForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
				</logic:notEmpty>
			</logic:notEmpty>
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">	
				<logic:equal name="writeAble" value="yes">
					<button type="button" class="button2"
						onclick="showTopWin('ghxyQsryf.do?method=qsryfTj&doType=view',700,500)"
						style="width:80px">
						增 加
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="modi('ghxyQsryf.do?method=qsryfXx&doType=save')" style="width:80px">
						修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataBatch('ghxyQsryf.do?method=qsryfCx&doType=del')"
						style="width:80px">
						删 除
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
					<button type="button" class="button2" onclick="wjcfDataExport('ghxyQsryf.do?method=expDate')" style="width:80px">
						导出数据
					</button>
					</div>
				
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
