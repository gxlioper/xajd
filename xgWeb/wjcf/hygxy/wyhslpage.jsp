<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript">
<!--
	//修改和删除
function modiAndDelete(url,type,w,h) {
	if (curr_row==null || curr_row=='') {
		alert('请选择要操作的数据行！');
		return;
	} else {
		if (type=='modi') {
			var realTable;
			if ($('realTable')) {
				realTable = document.getElementById('realTable').value;
			}
			url += curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += '&realTable=';
			url += realTable;
			url += '&xh=';
			url += curr_row.cells[2].innerText;
			url += '&xn=';
			url += curr_row.cells[4].innerText;
			showOpenWindow(url,w,h);
		} else {
			if (confirm('确认要删除所选择的数据吗？')) {
				refreshForm(url);
			} else {
				return;
			}
		}
	}
}
function plsl() {
	var flag = false;
				var str = '';
				var array = document.getElementsByName('cbv');
				for (var i=0;i<array.length;i++) {
					if (array[i].checked) {
						flag = true;
						str+=array[i].value+'!';
					}
				}
				if (flag) {
					showTopWin('wjcf_hygxy_plsl.do?str='+str,450,350);
				} else {
					alert("没有选择相应记录，请选择之后再确定！");
				}
}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/wjcfhygxywh.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="tableName" id="tableName" value="${tableName }"/>
		<input type="hidden" name="realTable" id="realTable" value="${realTable }"/>
		<input type="hidden" name="failInfo" id="failInfo" value="${failinfo }"/>
		<input type="hidden" name="pt" id="pt" value=""/>
		<div class="title">
			<div class="title_img" id="title_m">
				${tips }
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
								styleClass="select">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; 年级：
							<html:select property="nj" styleId="nj" style="width:90px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp; 学号：
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px">
							</html:text>
							
							&nbsp;&nbsp;
							处分类别:
							<html:select property="cflb" styleId="cflb">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="cflbdm" labelProperty="cflbmc"/>
							</html:select>
							&nbsp;&nbsp;
							处分原因:
							<html:select property="cfyy" styleId="cfyy">
								<html:option value=""></html:option>
								<html:options collection="cfyyList" property="cfyydm" labelProperty="cfyymc"/>
							</html:select>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<input type="hidden" name="act" value="query" />
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('wjcf_hygxy_wyhsl.do')">
								查询
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
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
							<html:select property="bjdm" style="170px" styleId="bj"
								styleClass="select">
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
								align="center" ondblclick="modiAndDelete('hygxy_wyhslone.do?pkValue=','modi','700','600')">
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" id="btn_add"
								onclick="plsl()"
								style="width:80px">
								批量受理
							</button>
						</div>
					</center>
				</logic:equal>
			<div id="tmpdiv"></div>
	</html:form>
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
					alert('操作成功!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
					alert(''+document.getElementById('failInfo').value);
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
</body>
