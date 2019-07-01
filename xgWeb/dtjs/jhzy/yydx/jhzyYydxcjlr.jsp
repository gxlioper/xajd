<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/nbzy/nbzyJs.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script language="javascript" src="js/jsFunction.js"></script>
<script type="text/javascript">
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/jhzyYydx" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置: 党团建设 - 业余党校 - 成绩录入
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="pkVStr" name="pkVStr" value="" />
		<fieldset>
			<legend>
				条件选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							&nbsp;&nbsp; 学年：
							<html:select property="xn" styleClass="select" disabled="true"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; 学期：
							<html:select property="xq" styleClass="select" disabled="true"
								styleId="xn">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
							&nbsp;&nbsp;年级：
							<html:select property="nj" styleId="nj"
								onchange="initZyList();initBjList()" styleClass="select"
								style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<td width="10" rowspan="3" align="center" valign="middle">
							<input type="hidden" name="go" value="go" />
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick=" refreshForm('jhzyYydx.do?method=jhzyYydxcjlr');document.getElementById('search_go').disabled=true;">
								查询
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							<bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							专业：
							<html:select property="zydm" onchange="initBjList()"
								styleClass="select" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							班级：
							<html:select property="bjdm" styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							&nbsp;&nbsp;学号：
							<html:text property="xh" />
							&nbsp;&nbsp;姓名：
							<html:text property="xm" />
							&nbsp;&nbsp;申请党校期届：
							<html:text property="qj" />期
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
							记录数： ${rsNum} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以查看详细信息;单击表头可以进行排序;</font>
						</legend>
						<table width="99%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;">
									<td align=center>
										<input type="hidden" id="pk" name="pks"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
											</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2" length="7">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="9" length="1">
										<td align=center>
											<input type ="text" name = "cjs" value = "<bean:write name="v" />"  maxlength="6"/>
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<br />
				<br />
				<%--				<logic:equal value="yes" name="writeAble" scope="request">--%>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button type="button" class="button2" onclick="refreshForm('/xgxt/jhzyYydx.do?method=yydxcjbc');" id="checkpass">
						保 存
					</button>
				</div>

				<%--				</logic:equal>--%>
				<div id="tmpdiv"></div>
			</div>
		</div>
		<logic:equal name="update" value="yes">
			<script>
				alert("操作成功");			    
   			</script>
		</logic:equal>
		<logic:equal name="update" value="no">
			<script>
				alert("操作失败");			    
   			</script>
		</logic:equal>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
