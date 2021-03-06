<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
<script type="text/javascript">
<!--
	function account() {
		var xn = document.getElementById('xn').value;
		var xydm = document.getElementById('xydm').value;
		if (xn==null||xn==''||xydm==null||xydm=='') {
			alert("自动计算以学年,<bean:message key="lable.xsgzyxpzxy" />为单位,请选择计算条件!");
			return false;
		}
		if (confirm("自动计算将学年,<bean:message key="lable.xsgzyxpzxy" />为单位进行计算,\n首先会清空当前已计算出来的学生的综合素质测评各项成绩,\n然后再根据学生申报综合表现分的得分进行计算!")) {
			if (confirm("如果学生的综合素质测评各项成绩是以导入数据的方式维护的,建议不要自动计算!")) {
				if ($("pt")) {
					BatAlert.showTips('正在操作，请等待...');
				}
				refreshForm('pjpy_gzdx_zhszcpwh.do?act=account');	
			}
		}
	}
	function exppmdata() {
		if (checkNotNull('xn-xydm')) {
			wjcfDataExport('pjpy_gzdx_expZhszcp.do');
		} else {
			alert("请选择要输出的学年和<bean:message key="lable.xsgzyxpzxy" />！");
			return false;
		}
	}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/gzdxPjpy.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="operType" value="query"/>
		<input type="hidden" name="tableName" value="${tableName }"/>
		<input type="hidden" name="realTable" value="${realTable }"/>
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置: 评奖评优 - 综测信息维护 - 综合素质测评
			</div>
		</div>
		<fieldset>
			<legend>
				条件选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="" nowrap="nowrap">
							学年：
							<html:select property="xn" styleId="xn" style="width:90px"
								styleClass="select" >
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;
							学号：
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px">
							</html:text>
							&nbsp;&nbsp;姓名：
							<html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px">
							</html:text>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<button class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('pjpy_gzdx_zhszcpwh.do')">
								查询
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							年级：
							<html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp;
							<bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; 专业：
							<html:select property="zydm" style="width:170px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp; 班级：
							<logic:notEmpty name="bjList">
							<html:select property="bjdm" style="width:180px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</logic:notEmpty>
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
						${rsNum}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以进行排序；双击一行可以查看详细信息；</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
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
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								align="center" ondblclick="modiAndDel('pjpy_gzdx_modiZhszcpxx.do?operType=view&pkValue=','modi',600,450)">
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=pjpyGzdxActionForm"></jsp:include>
												</TD>
											</TR>
											<TR>
												<TD height=3></TD>
											</TR>
										</TABLE>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2" id="btn_add"
								onclick="account()"
								style="">
								自动计算
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_add"
								onclick="showTopWin('pjpy_gzdx_addZhszcpxx.do',600,450)"
								style="width:80px">
								增加
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_xg"
								onclick="modiAndDel('pjpy_gzdx_modiZhszcpxx.do?pkValue=','modi',600,450)"
								style="width:80px">
								修改
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_sc"
								onclick="deldata('pjpy_gzdx_zhszcpwhDelete.do')"
								style="width:80px">
								删除
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_dr"
								onclick="impAndChkData()"
								style="width:80px">
								导入
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_dc"
								onclick="dataExport()"
								style="width:80px">
								导出
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_dc"
								onclick="exppmdata()"
								style="">
								综测班级排名输出
							</button>
						</div>
					</center>
				</logic:equal>
			<div id="tmpdiv"></div>
	</html:form>
		<logic:equal value="yes" name="writeAble" scope="request">
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</logic:equal>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert('操作成功!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert('操作失败!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
</body>
