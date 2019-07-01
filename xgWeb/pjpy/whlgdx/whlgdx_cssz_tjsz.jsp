<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<body onload="">
	<script language="javascript" src="js/pjpyFunction.js"></script>	
	<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_whlgdx.js"></script>
	<script type="text/javascript">
		function addTj(){
			var xmdm = document.getElementById("xmdm").value;
			var jxjdm = document.getElementById("jxjdm").value;
			var jxjfl = document.getElementById("jxjfl").value;
			var xn = document.getElementById("xn").value;
			var url = "pjpy_whlgdx.do?method=showTjszAdd";
			url += "&xmdm=";
			url += xmdm;
			url += "&jxjfl=";
			url += jxjfl;
			url += "&jxjdm=";
			url += jxjdm;		
			url += "&xn=";
			url += xn;
			
			showTopWin(url);
		}
	</script>
	<html:form action="/pjpy_whlgdx.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 参数设置 - 条件设置
			</div>
		</div>
		<fieldset>
			<legend>
				条件设置
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align=left id="vXn">
							学年：
							<html:select property="xn" styleId="xn" style="width:90px">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;项目：
							<html:select property="xmdm" style="width:150px" styleId="xmdm"
								onchange="initXmflList();refreshForm('pjpy_whlgdx.do?method=tjssInit')">
								<html:option value="jxjdmb">奖学金</html:option>
								<html:option value="rychdmb">荣誉称号</html:option>
							</html:select>
							&nbsp;&nbsp; 分类：
							<html:select property="jxjfl" style="width:150px" styleId="jxjfl"
								onchange="initXmList();">
								<html:option value=""></html:option>
								<html:options collection="jxjflList" property="jxjfldm"
									labelProperty="jxjflmc" />
							</html:select>
							&nbsp;&nbsp;名称：
							<html:select property="jxjdm" style="width:150px"
								onchange="refreshForm('pjpy_whlgdx.do?method=tjssInit')" styleId="jxjdm">
								<html:option value=""></html:option>
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
							<input type="hidden" name="go" value="a" />
							<button class="button2" id="search_go" onclick="allNotEmpThenGo('pjpy_whlgdx.do?method=tjssInit');">
								查询
							</button>
						</td>
					</tr>
				</thead>
			</table>
		</fieldset>
		<br />
		<logic:notEmpty name="rs">
			<fieldset>
				<legend>
					现有条件
				</legend>
				<table width="100%" id="rsTable" class="tbstyle">
					<thead>
						<tr align="center">
							<logic:iterate id="tit" name="topTr" offset="2">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)" nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:iterate id="s" name="rs" scope="request">
						<tr align="center" onclick="rowOnClick(this)" style="cursor:hand">
							<td>
								<input type="hidden"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								<input type="hidden"
									value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
								<logic:iterate id="v" name="s" offset="2" length="1">
									<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="3">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
			</fieldset>
		</logic:notEmpty>
		<div class="buttontool" id="btn"
			style="position: absolute;left:1%;top:100px" width="100%">
			<button class="button2" onclick="addTj()">
				修改条件
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2"
				onclick="modijxjtjsz('pjpy_whlgdx.do?method=tjszDel&pkValue=')">
				删除条件
			</button>
		</div>
	</html:form>
	<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);

function modijxjtjsz(url,type){
	if(curr_row==null){
		alert('请选中要删除的记录！');
		return false;
	}else{
		url += curr_row.cells[0].getElementsByTagName("input")[0].value;
		url += "&tableName=";
		url += document.getElementById("xmdm").value;
		if (confirm('确定要删除所选择的数据吗？')) {
			refreshForm(url);
			return;
		}
		return;
	}
}

</script>
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="result" name="result">
			<script>
				alert("操作失败！");
			</script>
		</logic:equal>
	</logic:present>
</body>
