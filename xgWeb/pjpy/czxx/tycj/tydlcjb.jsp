<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript">	  
	function saveinfo(url) {
		var checkBoxArr = document.getElementsByName("cbv");
		var flag = false;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if (flag) {
			if (confirm('确认要保存当前页面的所有数据吗?')) {
				refreshForm(url);
			}
			return false;
		} else {
			alert("没有选择相应记录，请选择之后再进行操作！！");
			return false;
		}
	}
	
	function checkBox(obj) {
		if (obj.value != null && obj.value != null) {
			obj.parentNode.parentNode.getElementsByTagName("td")[0].getElementsByTagName('input')[0].checked = true;
		} 
	}
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/czxxPjpyTycj" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName"
			value="view_czxx_tydlcjb" />
		<input type="hidden" name="realTable" id="realTable"
			value="czxx_tydlcjb" />
		<input type="hidden" name="userName" id="userName"
			value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 综测信息维护 - 课外体育锻炼分维护
			</div>
		</div>
		<div class="rightcontent">
			<fieldset>
				<legend>
					查 询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;学期：
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;年级：
								<html:select property="nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;学号：
								<html:text property="xh" style="width:100px" styleId="xh"></html:text>
								&nbsp;姓名：
								<html:text property="xm" styleId="xm" style="width:80px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_czxx_tydlcjb.do?act=qry');">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" styleId="bj">
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
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						${rsNum }
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="cb" onclick="selectAll()">
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
							<tr onclick="rowOnClick(this);" style="cursor:hand;"
								ondblclick="tydf('view')" >
								<td align="center" >
									<input type="checkbox" id="cbv" name="cbv" style="height: 17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									<input type="hidden" name="hiddenVal" id="hiddenVal" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>	
								</td>
								<logic:iterate id="v" name="s" offset="1" length="6">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<td align="center" >
									<logic:iterate id="v" name="s" offset="7" >
									<input type="text" name="fs" id="fs" style="width:50px;height: 17.5px" maxlength="4" onkeyup="chkinpdata(this);checkBox(this);" value="${v }" />
									</logic:iterate>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="rsTable1" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyCzxxActionForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
		</div>
		<center>
		<logic:equal value="yes" name="writeAble">
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button type="button" class="button2" onclick="saveinfo('pjpy_czxx_addTydlfxx.do')" style="width:80px">
					保存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="deldata('pjpy_czxx_tydlcjb.do?act=del')" style="width:80px">
					删除
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dr" onclick="impAndChkData()"
					style="width:80px">
					导入
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="dataExport()"
					style="width:80px">
					导出
				</button>
			</div>
			<script language="javascript">
                  document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
                  document.getElementById("btn").style.width = "96%";
                  window.setInterval("initBTNTool('btn')",1);
                </script>
		</logic:equal>
	</center>
	</html:form>
	
</body>
<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("操作成功!");
					Close();
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("操作失败,原因可能是数据库中已存在相同记录!");
					Close();
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
