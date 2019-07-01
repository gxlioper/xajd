<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js">
</script>
<script type="text/javascript">
<!--
function printFun(){
	var requestPath = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint&';
	requestPath += "bjdm="+'';
	requestPath += "&titName="+'';
	var pk = '';
	if (curr_row=='' || curr_row==null) {
		pk='';
	} else {
		pk = curr_row.getElementsByTagName("input")[0].value;
	}
	requestPath+='&pks='+pk;
	window.open(requestPath);
}
//-->
function delxjtzzdata() {
    var url="pjpy_hzy_xjch.do?method=xjchDel&go=go";
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('确定要删除所选择的数据吗？')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("btn_del")) {
				$("btn_del").disabled=true;
			}			
		}
	}else{
		alert("请选择要删除的记录！\n(单击每条记录前复选框)");
	}
}
function xjtzbModi(act){
    if (curr_row==null || curr_row=='') {
		alert("请选择要修改的记录！\n（单击相应的行）");
		return false;
    }
    var url = "/xgxt/pjpy_xjch_modi.do?method=xjchModi&act="+act+"&pkValue=";
    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
    var xjType = $("xjType").value;
	url += pk;
	url += "&xjType="+xjType;
    showTopWin(url,"820","530");
}
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpy_hzy_xjch" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 － 荣誉称号申请 － 其他荣誉称号申请结果查询
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType"
			value="${userType}" />
		<input type="hidden" id="tableName" name="tableName"
			value="${tableName}" />
		<input type="hidden" id="realTable" name="realTable"
			value="${realTable}" />
		<fieldset>
			<legend>
				条件选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							其他称号类型：
							<html:select property="xjType" styleId="xjType"
								onchange="refreshForm('pjpy_hzy_xjch.do?method=xjbjsqQry')"
								styleClass="select" style="width:90px">
								<html:option value="xjbj">先进班级</html:option>
								<html:option value="wmbj">文明班级</html:option>
								<html:option value="xjtzb">先进团支部</html:option>
								<html:option value="xjtzz">先进团总支</html:option>
							</html:select>
							&nbsp;&nbsp; 学年：
							<html:select property="xn" style="width:90px" styleClass="select"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; 学期：
							<html:select property="xq" style="width:50px" styleClass="select"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>	
							&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
							<html:select property="xydm" style="background-color:#FFFFFF;">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>						
							&nbsp;&nbsp;团总支：
							<html:text property="tzzmc" styleId="tzbmc" style="width:150px"></html:text>
							&nbsp;&nbsp;书记：
							<html:text property="tzzsj" styleId="tzbsj" style="width:80px"></html:text>
							&nbsp;&nbsp;申请时间：
							<html:text property="sqsj" styleId="sqsj" style="width:80px"></html:text>
						</td>
						<td width="10" align="center" valign="middle">
							<input type="hidden" name="go" value="a" />
							<button class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('pjpy_hzy_xjch.do?method=xjbjsqQry&go=go');this.disabled=true;">
								查询
							</button>
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
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="xjtzbModi('view')">
									<td align=center>
										<input type="checkbox" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttontool" align="center" id="btn"
						style="position:absolute;width:95%;top:100px">
						<button class="button2" id="btn_modi" style="width:80px"
							onclick="xjtzbModi('modi')">
							修改
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" id="btn_del" style="width:80px"
							onclick="delxjtzzdata()">
							删除
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="impAndChkData();"
							style="width:80px">
							导入数据
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataExport()" style="width:80px">
							导出数据
						</button>
					</div>
				</logic:equal>
				<div id="tmpdiv"></div>
			</div>
		</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
	 			alert('操作成功！');
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
	 			alert('' + document.getElementById('failInfo').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
	</logic:present>
</body>
