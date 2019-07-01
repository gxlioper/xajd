<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<html>	
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
		function expPageData() {
			document.forms[0].action = "/xgxt/jygl.do?method=tjbcx&doType=expPageData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/jygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								补办原因:
								<html:select property="queryequals_bbyy">
									<html:option value=""></html:option>
									<html:options collection="yyList" property="en" labelProperty="cn"/>
								</html:select>
								&nbsp;&nbsp;所需材料：
								<html:select styleId="sxcl" property="queryequals_sxcl" >
									<html:option value=""></html:option>
									<html:options collection="clList" property="en" labelProperty="cn"/>
								</html:select>
								&nbsp;&nbsp;协议书编号：
								<html:text property="querylike_xysbh" maxlength="20"></html:text>
								&nbsp;&nbsp;学号：
								<html:text property="querylike_xh" maxlength="20"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="querylike_xm" maxlength="20"></html:text>
								
								<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/jygl.do?method=tjbcx&doType=query')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								年级:
								<html:select property="queryequals_nj" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<logic:notEqual value="xy" name="userType">
									<html:select property="queryequals_xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy" >
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
								<logic:equal value="xy" name="userType">
									<html:select property="queryequals_xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy"  value="${userDep }">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:equal>
								&nbsp;&nbsp;专业：
								<html:select property="queryequals_zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="queryequals_bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数：
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal value="xy" name="userType">
									<font color="blue">提示：双击一行可以单个审核；单击表头可以排序</font>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
								</logic:notEqual>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px">
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showInfo('/xgxt/jygl.do?method=showTjb','view','800','600');"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="cbv" name="primarykey_cbv" 	value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									   		<input type="hidden" value="<bean:write name="v" />">
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble">
		                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
		                			<button class="button2" onclick="showTopWin('/xgxt/jygl.do?method=tjbBb','800','600');"
										style="width:80px">
										增加
									</button>
									&nbsp;&nbsp;
									<button class="button2" onclick="deletedata('/xgxt/jygl.do?method=tjbcx&doType=del');"
										style="width:80px">
										删 除
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="button2"onclick="impAndChkData();"style="width:80px">
										导入数据
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="button2" onclick="expPageData()" style="width:80px">
										导出数据
									</button>
							</div>
					</logic:equal>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("操作失败！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
