<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value
				+'&xysh='+curr_row.getElementsByTagName('input')[2].value
				+'&xxsh='+curr_row.getElementsByTagName('input')[3].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function choiceDisabled(usertype,ele) {
   			if($("userType")){
				if ($("userType").value == usertype) {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						if(document.getElementById(tmp[i])){
			  		 		document.getElementById(tmp[i]).disabled = true;
						}
					}
  		 		}  
			}
		}
	</script>
	<body onload="choiceDisabled('xy','xy');">
		<html:form action="/nbty_qhtsjxj.do?method=qhtsjxjsh" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 审核 - 清寒天使奖学金审核
				</div>
			</div>
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								年度：
								<select disabled="disabled">
									<option>${nd }</option>
								</select>
								<input type="hidden" property="queryequals_xn" value="${xn }"/>
								&nbsp;&nbsp;学年：
								<select disabled="disabled">
									<option>${xn }</option>
								</select>
								&nbsp;&nbsp;学号：
								<html:text property="querylike_xh"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="querylike_xm"></html:text>

							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go" style="height:40px"
									onclick="allNotEmpThenGo('/xgxt/nbty_qhtsjxj.do?method=qhtsjxjsh&go=go')">
									查 询
								</button>
							</td>
						</tr>
				
						<tr>
							<td>
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="queryequals_xydm" value="${userDep}">
								</logic:equal>
								
								<bean:message key="lable.xsgzyxpzxy" />：
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
								<logic:equal value="fdy" name="userType">
									<input type="hidden" name="isFdy" value="true" />
									<input type="hidden" name="shzd" value="bjsh"/>
									<input type="hidden" name="shsj" value="bjshsj"/>
								</logic:equal>
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="queryequals_bjsh" value="通过" />
									<input type="hidden" name="shzd" value="xysh"/>
									<input type="hidden" name="shsj" value="xyshsj"/>
								</logic:equal>
								<logic:equal value="xx" name="userType">
									<input type="hidden" name="queryequals_xysh" value="通过" />
									<input type="hidden" name="shzd" value="xxsh"/>
									<input type="hidden" name="shsj" value="xxshsj"/>
								</logic:equal>
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
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('nbty_qhtsjxj.do?method=qhtsjxjone&doType=view')"
								align="center"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" >
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="${v }" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										${v }
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="4">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="7" indexId="index">
									<logic:equal name="index" value="7">
										<td>
											${v }
										</td>
									</logic:equal>
									<logic:equal name="index" value="8">
										<td>
											<input type="hidden" value="${v }" />
											${v }
										</td>
									</logic:equal>
									<logic:equal name="index" value="9">
										<td>
											<input type="hidden" value="${v }" />
											${v }
										</td>
									</logic:equal>
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
								page="/sjcz/turnpage.jsp?form=qhtsjxjForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
					</TABLE>
			</logic:notEmpty>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble" scope="request">
					<button type="button" class="button2"
						onclick="batchData('primarykey_cbv','nbty_qhtsjxj.do?method=qhtsjxjsh&shjg=tg&doType=sh&go=go','sh')"
						style="width:80px">
						通过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="batchData('primarykey_cbv','nbty_qhtsjxj.do?method=qhtsjxjsh&shjg=btg&doType=sh&go=go','sh')"
						style="width:80px">
						不通过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="modi('nbty_qhtsjxj.do?method=qhtsjxjone&doType=shone')"
						style="width:80px">
						单个审核
					</button>
				</logic:equal>
			</div>

			<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
			</script>
		</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>
