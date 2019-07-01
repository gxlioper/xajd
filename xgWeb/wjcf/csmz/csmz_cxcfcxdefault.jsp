<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getWjcfInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="wjcf/csmz/csmzJs/csmzJs.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="javascript"
			src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="js/Ajaxfunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript">
		 	function bbprint() {
			var xxdm = document.getElementById('xxdm').value;
			if ('13022'==xxdm) {
				var url = 'wjcf_nblg_cxprint.do?pk=';
				if (curr_row!=null) {
					url += curr_row.cells[0].getElementsByTagName("input")[0].value; 	
				}
				window.open(url);
			} else {
				expAppTab('rsTable','学生解除处分申请表','')
			}
		}
		function chkPriseOne2(url, w, h) {
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
		var sh = curr_row.cells[10].innerText.trim();	
		var userType = document.getElementById('userType').value;
		if (sh == '通过' && (userType =='xy' || userType =='fdy')) {
			alert("相关部门审核且通过，不能再修改！");
			return false;
		}	
		showTopWin(url, w, h);
}
}

		function xscfcx() {
				
				var pkValue=document.getElementsByName("cbv");
				var pk="";
				var n=0;
				for(var i=0;i<pkValue.length;i++){
					if(pkValue[i].checked){
						pk=pkValue[i].value;
						n++;
					}
				}
				
				if(n!=1){
					alert("请选择一条记录！");
					return false;
				}
				
				var url = 'wjcf_nblg_cdtyWjcx.do?pkValue=';
				url += pk;
				window.open(url);
			
		}
		 </script>
	</head>
	<body onload="xyDisabled('xy')">
		<html:form action="/wjcfcsmzwh" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="isFdy" name="isFdy"
				value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr"
				value="${bzrQx }" />
			<input type="hidden" name="tableName" id="tableName"
				value="view_wjcf_cxcf" />
			<input type="hidden" name="realTable" id="realTable"
				value="wjcf_cxcfb" />
			<input type="hidden" id="xyV" name="xyV" value="" />
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
			<div class="tab_cur" id="title_m">
				<p class="location">
					<em>您的当前位置：</em><a> <logic:equal value="10827" name="xxdm">
							<bean:message key="wjcf_csmz_cxcfsq" bundle="wjcfcsmz" />
						</logic:equal> <logic:notEqual value="10827" name="xxdm">
							<logic:equal value="13022" name="xxdm">
							违纪处分 - 解除察看期申请 - 查询
						</logic:equal>
							<logic:notEqual value="13022" name="xxdm">
							违纪处分 - <logic:equal value="10827" name="xxdm">解除</logic:equal>
								<logic:notEqual value="10827" name="xxdm">解除处分管理 - 解除</logic:notEqual>结果
						</logic:notEqual>
						</logic:notEqual> </a>
				</p>
			</div>
			<div class="toolbox">
				<div class="buttonbox">
					<logic:equal value="yes" name="writeAble" scope="request">
						<ul>
						<li>
								<a href="#"
									onclick="refreshForm('csmz_cxcfsqdefault.do');"
									id="btn_modi" class="btn_xg"> 增加 </a>
							</li>
							<li>
								<a href="#"
									onclick="chkPriseOne2('cxcfdetails.do?act=modi&pkValue=',700,550)"
									id="btn_modi" class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#" onclick="chkPriseOne6('delcxcfinfo.do')"
									id="btn_del" class="btn_sc"> 删除 </a>
							</li>
<%--							<li>--%>
<%--								<a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a>--%>
<%--							</li>--%>
							<li>
								<a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a>
							</li>
							<li>
								<a href="#" onclick="bbprint()" id="btn_print" class="btn_dy">
									打印/预览 </a>
							</li>
							
							<logic:equal name="xxdm" value="10653">
							<li>
								<a href="#" onclick="xscfcx()" id="btn_print" class="btn_tj">
									撤销处分登记表 </a>
							</li>
							</logic:equal>
						</ul>
					</logic:equal>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<input type="hidden" name="go" value="go" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';allNotEmpThenGo('csmzcxcfcx.do')">
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

						<thead>
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="width:110px" styleId="xn"
										styleClass="select">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" style="width:90px" styleId="xq"
										styleClass="select">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" style="width:90px;"
										styleClass="inputtext" maxlength="20"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:90px;"
										styleClass="inputtext" maxlength="10"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList()" styleClass="select">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:150px"
										onchange="initBjList()" styleClass="select">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px"
										styleClass="select">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
							审核
							</th>
							<td colspan="">
							<html:select property="xxsh" style="width:100px"
									styleId="sh">
									<html:option value=""></html:option>
									<html:options collection="shList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
			 		 		记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
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
									style="cursor:hand;background-color:"
									ondblclick="chkPriseOne_shgc('cxcfdetails.do?pkValue=',700,550)">
									<td align=center>
										<input type="hidden"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
										<input type="checkbox" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
											<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:present name="result">
			<logic:equal value="view" name="result">
				<script>
				alert("操作成功");
				window.document.getElementById('search_go').click();
			</script>
				<logic:notEqual value="view" name="result">
					<script>
				alert("操作失败");
				window.document.getElementById('search_go').click();
			</script>
				</logic:notEqual>
			</logic:equal>
		</logic:present>
	</body>
</html>
