<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script type="text/javascript" >
<!--
	function distype() {
		var xxdm = document.getElementById('xxdm').value;
		if (xxdm=='10656') {
			if (document.getElementById('bname').checked) {
			document.getElementById('lx').selectedIndex=0;
			document.getElementById('lx').disabled=true;
			document.getElementById('btn_count').disabled=true;
		}
		}
		return;
		
	}
//-->
</script>
<body onload="xyDisabled('xy');distype();">
		<html:form action="/pjpyxnmzwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 信息维护 - 学生成绩维护
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>"/>
			<input type="hidden" id="failinfo" name="failinfo" value="${failinfo }"/>
			<input type="hidden" id="tableName" name="tableName" value="${tableName }"/>
			<input type="hidden" id="realTable" name="realTable" value="${realTable }"/>
			<input type="hidden" id="xxdm" value="${xxdm }"/>
				<input type="hidden" name="zyV" id="zyV"/>
				<input type="hidden" name="bjV" id="bjV"/>
				<input type="hidden" name="xyV" id="xyV"/>
			<fieldset>
				<legend>
					查询表:
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
									<html:radio property="bname" styleId="bname" value="0" style="cursor: hand" onclick="refreshForm('pjpy_xnmz_xscjwh.do');distype();">学生成绩表:</html:radio>
									<html:radio property="bname" styleId="bname" value="1" style="cursor: hand" onclick="refreshForm('pjpy_xnmz_xscjwh.do');distype();">学生绩点表:</html:radio>
							</td>
						</tr>
					</thead>
					</table>
			</fieldset>	
				
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap="nowrap">
								年级：
								<html:select property="nj" styleId="nj" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								&nbsp;&nbsp;
								</html:select>
								学年：
								<html:select property="xn" style="width:90px" 
									styleId="xn" styleClass="select">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" style="width:70px" 
									styleId="xq" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;
								学号：<html:text property="xh" styleId="xh" style="width:95px" styleClass="inputtext"></html:text>
								&nbsp;&nbsp;
								姓名：<html:text property="xm" styleId="xm" style="width:95px" styleClass="inputtext"></html:text>
								<logic:equal value="10656" name="xxdm">
									&nbsp;&nbsp;
								类型:
								<html:select property="lx" styleId="lx"  style="width:120px">
									<html:option value=""></html:option>
									<html:option value="0">单科学分</html:option>
									<html:option value="1">总学分</html:option>
									<html:option value="2">单科绩点</html:option>
									<html:option value="3">平均绩点</html:option>
									
								</html:select>
								</logic:equal>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<logic:equal value="10656" name="xxdm">
									<button class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_xnmz_cjjdqry.do?act=xnmz');">
									查询
									</button>
								</logic:equal>
								<logic:notEqual value="10656" name="xxdm">
									<button class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_xnmz_cjjdqry.do?act=shcb');">
									查询
									</button>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy"
								 onchange="initZyList();initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
								 onchange="initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:180px" styleId="bj"
								styleClass="select">
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
					<table width="100%" id="rsTable" class="tbstyle">
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
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="center"
								style="cursor:hand;"
   					 			ondblclick="">							
								<logic:iterate id="v" name="s" offset="0">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty><%--
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				--%>
				<logic:equal value="10656" name="xxdm">
					<div id="btn" class="buttontool" align="center" 
					style="position: absolute;left:1%;top:100px" width="100%">
					<button id="btn_count" class="button2" onclick="tb()" style="">
						成绩绩点数据同步
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport()" style="width:80px">
						数据导出
					</button>
				</div>
				</logic:equal>
			<div id="tmpdiv"></div>
			<logic:equal value="10656" name="xxdm">
			<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
				function tb() {
					if (confirm('该次操作将会同步所有学生成绩绩点信息,可能耗费较长时间,确认要继续吗?')) {
						refreshForm('pjpy_xnmz_xjcjjttb.do');
						BatAlert.showTips('正在操作，请等待...');
					}
					return;
				}
				</script>
				</logic:equal>
				<logic:present name="inserted">
					<script>	
						alert('操作完成!');
					</script>
				</logic:present>
				<logic:present name="failinfo">
					<script>	
						alert(document.getElementById('failinfo').value;);
					</script>
				</logic:present>
		</html:form>
		
</body>
