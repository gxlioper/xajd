<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"
			src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/xmlg/pjpyxmlg.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript">
	
		function openAuditOne(url, w, h) {
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {		
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
		var tab='';
		if ($('realTable')) {
				tab = document.getElementById('realTable').value;
			}
		if((xxdm=='11551' && tab=='xsjxjb') || ((xxdm=='11551') && tab=='xsrychb')){
			url += "&xh=";
			url += curr_row.cells[5].innerText;
		}
		
		if ((xxdm=='13022' && tab=='xsjxjb') || ((xxdm=='13022') && tab=='xsrychb')) {
					
			url += '&jqfpm=';
			url += curr_row.cells[0].getElementsByTagName("input")[1].value;
			url += '&zhszcpzfpm=';
			url += curr_row.cells[0].getElementsByTagName("input")[2].value;
		}
		showTopWin(url, w, h);
   		}
	}
	function xftzs(url) {
		var checkBoxArr = document.getElementsByName("cbv");
		var pk = "";
		var flag = false;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
				pk += checkBoxArr[i].value+"!@";
			}
		}
		if (flag){
			if (confirm("确定要对所选择的数据进行下发拟处分通知吗？")) {
				refreshForm(url);
			}
		}else{
			alert("没有选择相应记录，请选择之后再进行操作！！");
		}
	}
	function tzsPrint(url) {
		var pk = "";
		if (curr_row != null) {
			pk = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		}
		url += pk;
		window.open(url);
	}
	
	//批量审核
		function plshcfdata(url) {
			var checkBoxArr = document.getElementsByName("cbv");
			var pk = "";
			var flag = false;
			var leng = checkBoxArr!=null ? checkBoxArr.length:0;
			var array = [leng];
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
					pk += checkBoxArr[i].value+"!@";
					array[i] = checkBoxArr[i].parentNode.parentNode.cells[4].innerText;
					array[i] = array[i].trim();
				}
			}
			if (flag){
				if (checkArrEleIsCf(array)) {
					alert("不能对同一学生的多条处分记录进行同一文号和时间的发文操作.");
					return false;
				}
				showTopWin(url + "?keys=" + pk,450,350);
			}else{
				alert("没有选择相应记录，请选择之后再进行操作！！");
			}
		}
		
		//判断数组元素是否重复值
		function checkArrEleIsCf(ary) {
			var s = ary.join(",")+","; 
			for(var i=0;i<ary.length;i++) { 
				if(s.replace(ary[i]+",","").indexOf(ary[i]+",")>-1) { 
					return true;
				} 
			}
			return false;
		}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a><bean:write name="tips" scope="request" /> </a>
			</p>
		</div>


		<html:form action="/prise_conf_rs" method="post">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />
			<input type="hidden" id="printWj" name="printWj" value="${printWj}"/>	
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox" id="btn">
						<ul>
							<!--非广州大学-->
							<logic:notEqual value="11078" name="xxdm">
<%--								<logic:notEqual value="xy" name="userType">--%>
									<li>
										<a href="#" onclick="plshcfdata('wjcf_xmlg_plsh.do')"
											class="btn_sh">审核</a>
									</li>
<%--								</logic:notEqual>--%>
							</logic:notEqual>
							<!--end非广州大学-->
							<!--广州大学-->
							<logic:equal name="xxdm" value="11078">
								<li>
									<a href="#" onclick="plshcfdata('wjcf_xmlg_plsh.do')"
										class="btn_sh">审核</a>
								</li>
							</logic:equal>
							<!--end广州大学-->

							<logic:notEqual value="11078" name="xxdm">
								<logic:notEqual value="12645" name="xxdm">

<%--									<li>--%>
<%--										<a href="#" onclick="showTopWin('wjcflxcktj.do',750,700)"--%>
<%--											class="btn_ck"> 留校察看提醒 </a>--%>
<%--									</li>--%>
								</logic:notEqual>
							</logic:notEqual>
							<logic:equal value="13022" name="xxdm">
								<li>
									<a href="#" onclick="print('1')" class="btn_dy">打 印(格式1) </a>
								</li>
								<li>
									<a href="#" onclick="print('2')" class="btn_dy">打 印(格式2) </a>
								</li>
							</logic:equal>
							<logic:notEqual value="13022" name="xxdm">
								<logic:notEqual value="12645" name="xxdm">
									<logic:notEqual value="10657" name="xxdm">
										<li>
											<a href="#" onclick="print()" class="btn_dy">打 印/预 览 </a>
										</li>
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>

							<!-- 贵州大学 -->
							<logic:equal value="10657" name="xxdm">
								<li>
									<a href="#" onclick="print()" class="btn_dy">打 印/预 览 </a>
								</li>
							</logic:equal>

							<logic:equal value="12645" name="xxdm">
								<logic:notEqual value="xy" name="userType">
									<li>
										<a href="#"
											onclick="tzsPrint('wjcf_nbcs_cbbprint.do?pkValue=')"
											class="btn_dy">处分呈报表打印 </a>
									</li>
									<li>
										<a href="#" onclick="xftzs('wjcf_nbcs_xfcf.do')"
											class="btn_dy">下发拟处分通知 </a>
									</li>
								</logic:notEqual>

								<li>
									<a href="#"
										onclick="tzsPrint('wjcf_nbcs_tzsPrint.do?pkValue=')"
										class="btn_dy">拟处分通知书打印</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/stuPunishAudit.do')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="width:100px" styleId="xn"
										>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" style="width:80px" styleId="nd"
										>
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" onchange="initZyList();initBjList()"
										style="width:90px;padding-left:80px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleId="xy" style="width:170px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()"
										styleId="zy" style="width:170px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:170px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" style="width:90px" styleId="xh"></html:text>
									<input type="text" name="ycy" id="ycy" value="" style="display: none;"/>
								</td>

								<th>
									处分类别
								</th>
								<td>
									<html:select property="cflb" style="width:90px" styleId="cflb">
										<html:option value=""></html:option>
										<html:options collection="cflbList" property="cflbdm"
											labelProperty="cflbmc" />
									</html:select>
								</td>

								<logic:notEqual name="xxdm" value="11078">
									<th>
										审核结果
									</th>
									<td>
										<html:select property="sh" style="" styleId="sh">
											<html:option value=""></html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:notEqual>
								<logic:equal name="xxdm" value="11078">
									<th>

									</th>
									<td>

									</td>
								</logic:equal>
							</tr>
							<logic:equal name="xxdm" value="11078">
								<tr>
									<th>
										学校审核
									</th>
									<td>
										<html:select property="xxsh" style="" styleId="xxsh">
											<html:option value=""></html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										<bean:message key="lable.xb" />审核
									</th>
									<td>
										<html:select property="xysh" style="" styleId="xysh">
											<html:option value=""></html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>

									</th>
									<td>

									</td>
								</tr>
							</logic:equal>
							<logic:equal value="12645" name="xxdm">
								<tr>
									<th>
										处分期限
									</th>
									<td>
										<html:text property="cfnx" styleId="cfnx" maxlength="20"></html:text>
									</td>
									<th>
										是否已下发拟处分通知
									</th>
									<td>
										<html:select property="xftz" styleId="xftz">
											<html:option value=""></html:option>
											<html:option value="yes">是</html:option>
											<html:option value="no">否</html:option>
										</html:select>
									</td>
									<th>

									</th>
									<td>

									</td>
								</tr>
							</logic:equal>

							<logic:notEqual name="xxdm" value="11078">
								<logic:notEqual value="12645" name="xxdm">

<%--								<tr>--%>
<%--									<th>--%>
<%--										是否签收--%>
<%--									</th>--%>
<%--									<td>--%>
<%--										<html:select property="sfqs" styleId="sfqs">--%>
<%--											<html:option value=""></html:option>--%>
<%--											<html:option value="是">是</html:option>--%>
<%--											<html:option value="否">否</html:option>--%>
<%--										</html:select>--%>
<%--									</td>--%>
<%--									<th>--%>
<%--									</th>--%>
<%--									<td>--%>
<%--									</td>--%>
<%--									<th>--%>
<%--									</th>--%>
<%--									<td>--%>
<%--									</td>--%>
<%--								</tr>--%>
								</logic:notEqual>
							</logic:notEqual>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
							<logic:notEmpty name="rs">
								记录数：${rsNum }
								<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
							</logic:notEmpty>
									
					 </span>
					</h3>

					<logic:notEmpty name="rs">
						<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" align="" width="100%">
							<thead>
								<tr style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="3">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;"
										ondblclick="openAuditOne('/xgxt/stuPunishAuditOne.do?act=view&pkVal=',700,600)">
										<td>
											<input type="checkbox" name="cbv" id="cbv"
												value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v" /></logic:iterate>" <logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v" /></logic:iterate>/>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="3" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="4">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						</div>
					</logic:notEmpty>
				</div>
				<div id="tmpdiv"></div>
			</div>
			<!-- 宁波城市下发处分通知提示 -->
			<logic:notEmpty name="res">
				<logic:equal value="true" name="res">
					<script>
					alert("操作成功!");
				</script>
				</logic:equal>
				<logic:equal value="false" name="res">
					<script>
					alert("操作失败!");
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script language="javascript">
					
				function print(typ) {
					var url = 'wjcf_nblg_cfprint.do?typ='+typ+'&pkValue=';
					var xxdm = document.getElementById('xxdm').value;
					
					if($("printWj") && $("printWj").value=="true"){
						if(!curr_row){
							alert("没有选择任何记录,单击一行即可!");
							return false;
						}else{
							window.open('wjcf_cdty_cfbprint.do?sfshmk=yes&cfpk=' + curr_row.cells[0].getElementsByTagName("input")[0].value);
							return false;
						}
						
					}
					
					
					if (curr_row==null||curr_row=='') {
						if (confirm('没有选择任何记录,单击一行即可,要继续吗?')) {
							if (xxdm=='11641') {
								url = 'wjcf_hhgxy_jdprint.do?pkValue=';
								window.open(url);
								return;
							}else if (xxdm=='11078') {
							url = 'wjcf_gzdx_cfbprint.do?pkValue=';
							window.open(url);
							return;
						} else if (xxdm == '10657' ) {
							url = 'wjcfnblgwh.do?method=gzdxCfPrint&pkValue='
							window.open(url);
							return;
						}
							window.open(url);
						}
					} else {
						url += curr_row.cells[0].getElementsByTagName("input")[0].value;
						if (xxdm=='11641') {
							var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
							url = 'wjcf_hhgxy_jdprint.do?pkValue=';
							window.open(url+pk);
							return;
						} else if (xxdm=='11078') {
							var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
							url = 'wjcf_gzdx_cfbprint.do?pkValue=';
							window.open(url+pk);
							return;
						} else if (xxdm == '10657' ) {
							var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
							url = 'wjcfnblgwh.do?method=gzdxCfPrint&pkValue='+pk;
							window.open(url);
							return;
						}
						window.open(url);
					}
				}
		</script>

	</body>
</html>
