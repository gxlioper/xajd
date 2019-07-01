<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="js/String.js"></script>
<script language="javascript" src="js/jsFunction.js"></script>
<script language="javascript" src="js/qtsjFunction.js"></script>
<script>
	function modi(url){
		if(curr_row != null){
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
			return true;
		}else{
			alert('请选择要操作的数据行！');
			return false;
		}
	}
</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/gzdxQgzx.do" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="realTable" id="realTable" value="${realTable}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 勤工助学评奖评优 - 积极分子结果查询					
				</div>
			</div>			
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">								
								年级：
								<html:select property="queryequals_nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;学年：
								<html:select property="queryequals_xn">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;年度：
								<html:select property="queryequals_nd">
									<html:option value=""></html:option>
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="queryequals_xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;学号：
								<html:text property="querylike_xh" style="width:80px"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="querylike_xm" style="width:80px"></html:text>								
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go" style="height:40px"
									onclick="allNotEmpThenGo('/xgxt/gzdxQgzx.do?method=jjfzjgcx')">
									查 询
								</button>
							</td>
						</tr>
						<tr>
						<td>
							<bean:message key="lable.xsgzyxpzxy" />：
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
							&nbsp;&nbsp;专业：
							<html:select property="queryequals_zydm" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp;班级：
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
							辅导员审核：
							<html:select property="queryequals_fdysh">
								<html:option value=""></html:option>
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
							&nbsp;&nbsp;
							<bean:message key="lable.xsgzyxpzxy" />审核：
							<html:select property="queryequals_xysh">
								<html:option value=""></html:option>
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
							&nbsp;&nbsp;
							学校审核：
							<html:select property="queryequals_xxsh">
								<html:option value=""></html:option>
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
							&nbsp;&nbsp;申报时间：
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
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('gzdxQgzx.do?method=jjfzModi&type=view')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" value="<bean:write name="v"/>">
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
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
					<button type="button" class="button2" onclick="dataBatch('gzdxQgzx.do?method=jjfzjgcx&type=del')"
						style="width:80px">
						删 除
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="wjcfDataExport('gzdxQgzx.do?method=jjfzExp')" style="width:80px">
						导出数据
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
