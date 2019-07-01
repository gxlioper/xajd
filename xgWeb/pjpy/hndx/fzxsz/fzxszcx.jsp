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
				
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value
				+'&xn='+$('xn').value,700,500);
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
	<body onload="choiceDisabled('xy','xy');choiceDisabled('stu','xh-xm-xy-zy-bj');">
		<html:form action="/hndx_fzxsz.do" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="userType" id="userType"
				value="${userType}" />
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" name="tableName" value="${tableName }" />
			<input type="hidden" name="realTable" value="${realTable }" />

			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
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
								学年：
								<html:select property="xn" styleId="xn" value="${xn }">
									<logic:iterate id="map" name="xnList" offset="1">
										<html:option value="${map.xn }">${map.xn }</html:option>
									</logic:iterate>
								</html:select>
								&nbsp;&nbsp;学号：
								<html:text property="querylike_xh" styleId="xh"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="querylike_xm" styleId="xm"></html:text>

							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go" style="height:20px"
									onclick="allNotEmpThenGo('/xgxt/hndx_fzxsz.do?method=fzxszfcx&go=go')">
									查 询
								</button>
								<br/>
								<button type="button" class="button2" style="height:20px" id="cz" onclick="czSearchCond('xh-xm-xy-zy-bj');">
									重 置
								</button>
							</td>
						</tr>

						<tr>
							<td>
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="queryequals_xydm" value="${userDep}">
								</logic:equal>
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
								<logic:equal value="fdy" name="userType">
									<input type="hidden" name="isFdy" value="true" />
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
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:iterate id="xmOne" name="xmList">
									<td>
										${xmOne.xmmc }
										<br />
										<font color="red">(该项目上限分为${xmOne.sxf })</font>
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('hndx_fzxsz.do?method=fzxszViewAndModi&doType=view')"
								align="center">
								<td>
									<input type="checkbox" name="primarykey_cbv" id="pkV"
									value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>">
								</td>
								<logic:iterate id="v" name="s" offset="0" length="2">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="map" name="xmList" indexId="index">
									<td>
										<input type="hidden" name="xh"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="xmdm" value="${map.xmdm }" />
										<input type="hidden" name="xmmc" value="${map.xmmc }" />
										<logic:iterate id="v" name="s" offset="${index+2 }" length="1">${v }</logic:iterate>
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
								page="/sjcz/turnpage.jsp?form=fzxszForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<br />
			<br />
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble" scope="request">
					<button type="button" class="button2"
						onclick="modi('hndx_fzxsz.do?method=fzxszViewAndModi&doType=modi')"
						style="width:80px">
						修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="batchData('pkV','hndx_fzxsz.do?method=fzxszfcx&doType=del&go=go','del')"
						style="width:80px">
						删 除
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
					onclick="wjcfDataExport('hndx_fzxsz.do?method=fzxszfExp&queryequals_xn='+$('xn').value,600,400)"
					style="width:80px">
						导出数据
					</button>
				</logic:equal>
			</div>

			<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
			</script>
		</html:form>
		<logic:present name="msg">
			<input type="hidden" id="message" value="${msg }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>
