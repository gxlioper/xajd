<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function choiceDisabled(userType,ele) {
   			if($("userType")){
				if ($("userType").value == userType) {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						if(document.getElementById(tmp[i])){
			  		 		document.getElementById(tmp[i]).disabled = true;
						}
					}
  		 		}  
			}
		}
		
		function isSh(){
			if(curr_row != null){
				var xxsh = curr_row.getElementsByTagName('input')[3].value;
				var userType = $('userType').value;
				
				if(userType == "njbzr" && xxsh == "通过"){
					alert("该学生已被上级审核通过，您无权审核");
				}else{
					modi('ghxy_rykf.do?method=ryjfshone');
				}
			}else{
				alert('请选择要审核的数据行！');
			}
		}
	</script>
	<body onload="choiceDisabled('xy','xy');">
		<html:form action="/ghxy_rykf.do?method=ryjfsh" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： ${title }
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
								<logic:notPresent name="njbzr">
									年级：
									<html:select property="queryequals_nj" styleId="nj1">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" 
											labelProperty="nj" />
									</html:select>
								</logic:notPresent>
								
								<logic:equal value="yes" name="njbzr">
									年级：
									<html:select property="queryequals_nj" styleId="nj"  onchange="initZyList();initBjList();">
											<html:options collection="njListForBzr" property="nj"
											labelProperty="nj"/>
									</html:select>
								</logic:equal>
								<input type="hidden" name="queryequals_xn" value="${xn }" />
								&nbsp;&nbsp;学年：
								<select disabled="disabled">
									<option>
										${xn }
									</option>
								</select>
								<input type="hidden" name="queryequals_xq" value="${xqdm }" />
								&nbsp;&nbsp;学期：
								<select disabled="disabled">
									<option>
										${xq }
									</option>
								</select>
								&nbsp;&nbsp;学号：
								<html:text property="querylike_xh" styleId="xh" style="width:120px"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="querylike_xm" styleId="xm" style="width:120px"></html:text>
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go" style="height:20px"
									onclick="allNotEmpThenGo('/xgxt/ghxy_rykf.do?method=ryjfsh&go=go')">
									查 询
								</button>
								
								<br/>
								<button type="button" class="button2" style="height:20px" id="cz" onclick="czSearchCond('nj1-xn-xh-xq-xm-xy-zy-bj');">
									重 置
								</button>
							</td>
						</tr>

						<tr>
							<td>
								<bean:message key="lable.xb" />：
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
								<logic:equal value="njbzr" name="userType">
									<input type="hidden" name="shzd" value="njbzrsh" />
								</logic:equal>
								<logic:equal value="xx" name="userType">
									<input type="hidden" name="queryequals_njbzrsh" value="通过" />
									<input type="hidden" name="shzd" value="xxsh" />
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
								ondblclick="modi('ghxy_rykf.do?method=ryjfViewAndModi&doType=view')"
								align="center" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>">
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="${v }" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										${v }
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="9">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="12">
									<td>					
										<input type="hidden" value="${v }" />
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
				style="position: absolute;left:1%;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble" scope="request">
					<button type="button" class="button2"
						onclick="batchData('primarykey_cbv','ghxy_rykf.do?method=ryjfsh&shjg=tg&doType=sh&go=go','sh')"
						style="width:80px">
						通过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="batchData('primarykey_cbv','ghxy_rykf.do?method=ryjfsh&shjg=btg&doType=sh&go=go','sh')"
						style="width:80px">
						不通过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="isSh();"
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
