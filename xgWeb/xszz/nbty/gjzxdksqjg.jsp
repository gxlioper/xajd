<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript">
		function print(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}

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
	<body onload="choiceDisabled('xy','xy');choiceDisabled('stu','xy-zy-bj-xm-nj-xh');">
		<html:form action="/nbty_xszz.do?method=gjzxdksqjg" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="userType" value="${userType}" } />
			<input type="hidden" id="tableName"value="${tableName }" />
			<input type="hidden" id="realTable" value="${realTable }" />
			
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：助学贷款 - 申请 - 国家助学贷款申请结果查询
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
								<html:select styleId="nj" property="queryequals_nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;学年：
								<html:select styleId="xn" property="queryequals_xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学号：
								<html:text styleId="xh" property="querylike_xh"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text styleId="xm" property="querylike_xm"></html:text>
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go" style="height:40px"
									onclick="allNotEmpThenGo('/xgxt/nbty_xszz.do?method=gjzxdksqjg&go=go')">
									查 询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />：
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="queryequals_xydm" value="${userDep }"/>
								</logic:equal>
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
					</thead>
					<logic:equal value="fdy" name="userType">
						<input type="hidden" name="isFdy" value="true"/>
					</logic:equal>
					<input type="hidden" name="userName" value="${userName }"/>
					<logic:equal value="stu" name="userType">
						<input type="hidden" name="queryequals_nj" value="${queryInfo.nj}"/>
						<input type="hidden" name="querylike_xh" value="${queryInfo.xh}"/>
						<input type="hidden" name="querylike_xm" value="${queryInfo.xm}"/>
<%--						<input type="hidden" name="queryequals_xydm" value="${queryInfo.xydm}"/>--%>
<%--						<input type="hidden" name="queryequals_zydm" value="${queryInfo.zydm}"/>--%>
<%--						<input type="hidden" name="queryequals_bjdm" value="${queryInfo.bjdm}"/>--%>
					</logic:equal>
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
								ondblclick="modi('nbty_xszz.do?method=gjzxdkView&doType=view')"
								align="center" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>">
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
								page="/sjcz/turnpage.jsp?form=gjzxdkForm"></jsp:include>
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
						onclick="location='nbty_xszz.do?method=gjzxdksq';"
						style="width:80px">
						增加
					</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
						onclick="modi('nbty_xszz.do?method=gjzxdkView&doType=modi')"
						style="width:80px">
						修改
					</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
						onclick="dataBatch('nbty_xszz.do?method=gjzxdksh&doType=del&go=go')"
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
				<button type="button" class="button2"
					onclick="wjcfDataExport('nbty_xszz.do?method=gjzxdkExp',600,400)"
					style="width:80px">
					导出数据
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="print('nbty_xszz.do?method=gjzxdkView&doType=print')"
					style="width:80px">
					打 印
				</button>
			</div>

			<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
			</script>
		</html:form>
	</body>
</html>
