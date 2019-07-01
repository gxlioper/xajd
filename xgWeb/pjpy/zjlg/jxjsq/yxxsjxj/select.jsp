<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript">
//查询提示
function dataQryChk(url) {
	if (qryChk()) {
		if (confirm("您没有选择任何条件，此次操作将返回全部数据，可能会耗费相当长的时间。确定要继续吗？")) {
			refreshForm(url);
			if ($('search_go')) {
				document.getElementById('search_go').disabled=true;
			}
			return;
		} else {
			return;
		}
	} else {
		refreshForm(url);
		if ($('search_go')) {
				document.getElementById('search_go').disabled=true;
			}
		return;
	}
}

//数据查询时的提示
function qryChk() {
	var sel = document.getElementsByTagName("select");
	for (i = 0; i < sel.length; i++) {
		if (sel[i].value != null && sel[i].value != '') {
			return false;
		}
	}
	if (document.getElementById('xh').value != '' && document.getElementById('xh').value != null) {
		return false;
	}
	return true;
}

//修改和删除
function modiAndDel(url,type,w,h) {
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
			showTopWin(url,w,h);
		} else {
			if (confirm('确认要删除所选择的数据吗？')) {
				refreshForm(url);
			} else {
				return;
			}
		}
	}
}

//批量删除
function deldata(url) {
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
			if ($("pt")) {
				BatAlert.showTips('正在操作，请等待...');
			}
		}
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyhxxywh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前所在位置：评奖评优 - 奖学金申请 - 申请结果查询
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="pt" name="pt" />
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!-- 批量删除信息提示 -->
    	<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td align="left">
							年级：
							<html:select property="nj" styleId="nj" styleClass="select" style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
							&nbsp;&nbsp;
								学年：
								<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;
								奖学金：
								<html:select property="jxjdm" styleClass="select"
									styleId="jxjdm" ><html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
								&nbsp;&nbsp;
								
								学号:
								<html:text property="xh" styleId="xh" styleClass="inputtext"
								 style="width:100px"></html:text>
								 &nbsp;&nbsp;
								姓名:
								<html:text property="xm" styleId="xm" styleClass="inputtext" style="width:100px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="act" value="qry" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="dataQryChk('pjpy_whlghxxy_jxjsqqry.do');">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								专业：
								<html:select property="zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								班级：
								<html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								&nbsp;
								状态：
								<html:select property="zt" 
								styleClass="select" styleId="zt">
									<html:option value=""></html:option>
									<html:option value="通过"></html:option>
									<html:option value="不通过"></html:option>
									<html:option value="未审核"></html:option>
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
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：双击一行可以查看详细信息;单击表头可以进行排序;</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;" ondblclick="modiAndDel('pjpy_hxxy_jxjmodi.do?act=view&pkValue=','modi','700','550');">
										<td align=center><input type="checkbox" id="cbv" name="cbv"
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=pjpyHxxyActionForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="refreshForm('pjpy_whlghxxy_jxjsqdefault.do');">
								增加
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_modi" style="width:80px"
								onclick="modiAndDel('pjpy_hxxy_jxjmodi.do?pkValue=','modi','700','550');">
								修改
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_del" style="width:80px"
								onclick="deldata('pjpy_whlghxxy_jxjsqqry.do?act=del')">
								删除
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
										导入数据
									</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
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