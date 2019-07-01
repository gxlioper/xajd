<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<html>
	<body onload="xyDisabled('xy');">
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<html:form action="/xszzPortallet" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" name="queryequals_xxsh" value="通过"/>
			<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap>
								年级：
								<html:select property="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
								&nbsp;&nbsp;
								学号：
								<html:text property="xh" maxlength="20" style="width:80px"></html:text>
								&nbsp;&nbsp;
								姓名：
								<html:text property="xm" maxlength="20" style="width:80px"></html:text>
							</td>
								
								<td width="10" rowspan="2"  align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/xszzPortallet.do?method=szzqk&doType=query')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" onchange="initBjList()"  styleId="zy" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm"  styleId="bj" style="width:200px">
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
								${num}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px">
										</td>
										<logic:iterate id="tit" name="topTr" offset="0" scope="request">
											<td id="tit"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit"/>
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showInfo('/xgxt/xszzPortallet.do?method=szzqkShow','view','800','600')"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="cbv" name="primarykey_cbv" 	value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									   		<input type="hidden" value="<bean:write name="v" />">
									    </td>
										<logic:iterate id="v" name="s" offset="0">
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
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
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
				<script>
	       	 	 alert("" + $('message').value);
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
