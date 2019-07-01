<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getLdxx.js'></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript">
		
		function getldxx(){			
			var xqdm = $("xqdm").value;
			var yhm = $('userName').value;
			dwr.engine.setAsync(false);
			getLdxx.getLdlb(xqdm,yhm,function(data){
				DWRUtil.removeAllOptions($("lddm"));		
				DWRUtil.addOptions($("lddm"),data,"dm","mc");
			});
			dwr.engine.setAsync(true);
		}
		
		function getlcxx(){
		
			var lddm = $("lddm").value;
			dwr.engine.setAsync(false);
			getLdxx.getCslb(lddm,function(data){
				DWRUtil.removeAllOptions($("cs"));		
				DWRUtil.addOptions($("cs"),data,"dm","mc");
			});
			dwr.engine.setAsync(true);
		}
		
		function getqshxx(){
			var lddm = $("lddm").value;
			var cs = $("cs").value;
			dwr.engine.setAsync(false);
			getLdxx.getQslb(lddm,cs,function(data){
				DWRUtil.removeAllOptions($("qsh"));		
				DWRUtil.addOptions($("qsh"),data,"dm","mc");
			});
			dwr.engine.setAsync(true);
		}
		
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value
				+'&dqqx='+$('qx').value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}		
		
		function search(){
			if($('xqdm') && $('lddm')){
				var xq = $('xqdm').value;
				var lddm = $('lddm').value;
				
				if(xq == ""){
					alert('请选择校区');
				}else if(lddm == ""){
					alert('请选择楼栋');
				}else{
					allNotEmpThenGo('/xgxt/ghxy_rykf.do?method=ryjflr&go=go');
				}
			}else{
				allNotEmpThenGo('/xgxt/ghxy_rykf.do?method=ryjflr&go=go');
			}
		}
		
		function setld(){
		
			var ld = $("ld").value;
			if(ld != ""){			
				$("lddm").value = ld;
			}
			
		}
		
		function setlc(){
		
			var lc = $("lc").value;
			if(lc != ""){			
				$("cs").value = lc;
			}
			
		}
		
		function setqs(){
		
			var qs = $("qs").value;
			if(qs != ""){			
				$("qsh").value = qs;
			}
			
		}
	</script>
</head>
<body>
	<html:form action="/ghxy_rykf.do?method=ryjflr" method="post">
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" id="userName" name="userName" value="${userName }" />
		<input type="hidden" name="tableName" value="${tableName }" />
		<input type="hidden" name="realTable" value="${realTable }" />
		<input type="hidden" name="tableName" value="${tableName }" />
		<input type="hidden" id="qx" name="dqqx" value="${dqqx }" />

		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置： ${title }
			</div>
		</div>
		<logic:present name="fdy">
		<logic:present name="gyfdy">
		<table width="100%" class="tbstyle">
			<tr>
				<td>
					<div class="rightcontent">
							
						<div class="xxk">
							<logic:equal name="dqqx" value="fdy">

								<ul>
									<li id="001l" class="xxk_on_l"></li>
									<li id="001m"
										onclick="$('go').value='';refreshForm('ghxy_rykf.do?method=ryjflr&dqqx=fdy')"
										class="xxk_on_m">
										&nbsp; 辅导员 &nbsp;
									</li>
									<li id="001r" class="xxk_on_r"></li>
								</ul>
								<logic:present name="gyfdy">
									<ul>
										<li id="002l" class="xxk_off_l"></li>
										<li id="002m"
											onclick="$('go').value='';refreshForm('ghxy_rykf.do?method=ryjflr&dqqx=gyfdy')"
											class="xxk_off_m">
											&nbsp; 公寓辅导员
										</li>
										<li id="002r" class="xxk_off_r"></li>
									</ul>
							</logic:present>
							</logic:equal>

							<logic:equal name="dqqx" value="gyfdy">
								<logic:present name="fdy">
									<ul>
										<li id="001l" class="xxk_off_l"></li>
										<li id="001m"
											onclick="$('go').value='';refreshForm('ghxy_rykf.do?method=ryjflr&dqqx=fdy')"
											class="xxk_off_m">
											&nbsp; 辅导员 &nbsp;
										</li>
										<li id="001r" class="xxk_off_r"></li>
									</ul>
								</logic:present>
								<ul>
									<li id="002l" class="xxk_on_l"></li>
									<li id="002m"
										onclick="$('go').value='';refreshForm('ghxy_rykf.do?method=ryjflr&dqqx=gyfdy')"
										class="xxk_on_m">
										&nbsp; 公寓辅导员
									</li>
									<li id="002r" class="xxk_on_r"></li>
								</ul>
							</logic:equal>

						</div>
				</td>
			</tr>

		</table>
		</logic:present>
		</logic:present>

		<fieldset>
			<legend>
				查询条件
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td>
							学年：
							<input type="hidden" name="xn" value="${xn }" />
							<html:select property="queryequals_xn" styleId="xn"
								disabled="true">
								<html:option value="${xn}">${xn}</html:option>
							</html:select>
							&nbsp;&nbsp;学期：
							<input type="hidden" name="xq" value="${xqdm }" />
							<html:select property="queryequals_xq" styleId="xq"
								disabled="true">
								<html:option value="">${xqmc}</html:option>
							</html:select>
							&nbsp;&nbsp;学号：
							<html:text property="querylike_xh" styleId="xh"></html:text>
							&nbsp;&nbsp;姓名：
							<html:text property="querylike_xm" styleId="xm"></html:text>
						</td>
						<td width="10" align="center" valign="middle" rowspan="3">
							<input type="hidden" name="go" value="a" />
							<button type="button" class="button2" id="search_go" style="height: 20px"
								onclick="search();">
								查 询
							</button>
							<br />
							<button type="button" class="button2" style="height: 20px" id="cz"
								onclick="czSearchCond('xh-xm-xy-zy-bj-xqdm-lddm-cs-qsh');">
								重 置
							</button>
						</td>
					</tr>

					<logic:present name="fdy">
						<logic:equal name="dqqx" value="fdy">
						<tr>
							<td>
								院系：
								<html:select property="queryequals_xydm" style="width:180px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="queryequals_zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="queryequals_bjdm" style="width:180px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<input type="hidden" name="isFdy" value="true"/>
						</logic:equal>
					</logic:present>
					<logic:present name="gyfdy">
						<logic:equal name="dqqx" value="gyfdy">
						<tr>
							<td>
								校区：
								<html:select property="queryequals_xqdm" style="" styleId="xqdm"
									onchange="getldxx()">
									<html:option value="">----请选择----</html:option>
									<html:options collection="xqldList" property="xqdm"
									labelProperty="xqmc"></html:options>
								</html:select>
								&nbsp;&nbsp;楼栋：
								<input type="hidden" id="ld" value="${ld }">
								<html:select property="queryequals_lddm" style="width:130px"
									styleId="lddm" onchange="getlcxx()" value="">
									<html:option value="">----请选择----</html:option>
									</html:select>
								<script type="text/javascript">
									getldxx();
									setld();
								</script>
								
								&nbsp;&nbsp;楼层：
								<input type="hidden" id="lc" value="${cs }">
								<html:select property="queryequals_cs" style="width:130px"
									styleId="cs" onchange="getqshxx();" value="">
									<html:option value="">----请选择----</html:option>
								</html:select>
								
								<script type="text/javascript">
									getlcxx();
									setlc();
								</script>
								
								&nbsp;&nbsp;寝室号：
							
								<input type="hidden" id="qs" value="${qsh }"/>
								<html:select property="queryequals_qsh" style="width:110px" styleId="qsh" value="">
										<html:option value="">----请选择----</html:option>
								</html:select>
								<script type="text/javascript">
									getqshxx();
									setqs();
								</script>
							</td>
						</tr>
						</logic:equal>
					</logic:present>
				</thead>
			</table>
		</fieldset>
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				未找到任何记录！
			</p>
		</logic:empty>
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
						<tr align="center" style="cursor: hand">
							<logic:iterate id="tit" name="topTr" offset="0">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)" nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowMoreClick(this,'',event);" align="center"
							style="cursor: hand">
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="${v }" />
										${v }
									</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="1">
								<td>
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
			style="position: absolute; left: 1%; top: 100px" width="100%">
			<logic:equal value="yes" name="writeAble" scope="request">
				<button type="button" class="button2"
					onclick="modi('ghxy_rykf.do?method=ryjflrone');" style="width: 80px">
					录 入
				</button>
			</logic:equal>
		</div>


		<script language="javascript">
		document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
		document.getElementById("btn").style.width = "96%";
		window.setInterval("initBTNTool('btn')", 1);
	</script>
	</html:form>
	<logic:present name="result">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
		</script>
	</logic:present>
</body>
</html:html>
