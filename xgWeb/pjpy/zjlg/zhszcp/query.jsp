<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
	<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
	<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script language="javascript">	
		function printBb(){
			var xn = $("xn").value;
			if(xn == ""){
				alert("请选择要打印的学年");
				return false;
			}
			if (confirm("将要打印"+xn+"学年的综合素质测评情况，确认吗？\n点击\"确定\"，打印；\n点击\"取消\"，将放弃打印！")) {
				var url = "/xgxt/zjlgPjpy.do?method=zhszcpPrint";
				wjcfDataExport(url);
			}
		}
		function jsfs(){
			var xn = $("xn").value;
			if (xn == ""){
				alert("计算综合素质分必须选定学年！！");
				return false;
			}
			if (confirm("将要计算此条件的综合素质分，确认吗？\n点击\"确定\"，计算数据；\n点击\"取消\"，将放弃计算！")) {
				showTips('处理数据中，请等待......');
				var url = "pjpy_zjlg_zhszcp.do?act=save";
				refreshForm(url);
			}
		}
		function setCpzList(){
			var xydm = $("xy").value;
			var xn   = $("xn").value;
			getCpzfp.getCpzList(xn,xydm,function initTjList(data){
				if (data != null && typeof data == 'object') {
				var objId = "cpz";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);
					if($('tableName')){
						$(objId).options[0] = new Option('','');   
					}
					DWRUtil.addOptions(objId,data,"cpzdm","xscpz");
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}
			}
			});
		}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjlgPjpydcj" method="post">
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="userName" name="userName" value="${userName }"/>
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="message" name="message" value="${message }" />
			<input type="hidden" id="pt" name="pt" value="${message }" />
			<input type="hidden" id="cpzdm" value="${cpzdm }"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 信息维护 - 综合素质测评</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<logic:notEqual value="xy" name="userType">
							<li><a href="#" class="btn_sz" onclick="showTopWin('/xgxt/zjlgPjpy.do?method=zhszcpSz&type=edit',400,200);">参数设置</a></li>
						</logic:notEqual>
						<li><a href="#" class="btn_xg" onclick="jsfs()">自动计算</a></li>
						<li><a href="#" class="btn_dy" onclick="printBb();">打印报表</a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入数据</a></li>
						<li><a href="#" class="btn_dc" onclick="wjcfDataExport('pjpy_zjlg_zhszcp.do?act=export')">导出数据</a></li>								
					</ul>
				</div>
				</div>
			</logic:equal>
			<div class="rightcontent">
				<div class="searchtab">
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th>年级</th>
								<td><html:select property="queryequals_nj" style="width:80px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select></td>
								<th>学年</th>
								<td><html:select property="queryequals_xn" style="width:100px"  styleId="xn" onchange="refreshForm('pjpy_zjlg_zhszcp.do');">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select></td>
								<th>参评组</th>
								<td><html:select property="queryequals_cpzdm" style="width:150px"  styleId="cpz">
									<html:option value=""></html:option>
									<html:options collection="cpzList" property="cpzdm"
										labelProperty="cpzmc" />
									</html:select></td>
								</tr>
								<tr>
								<th>学号</th>
								<td><html:text property="querylike_xh" style="width:85px" maxlength="20"></html:text></td>
								<th>姓名</th>
								<td><html:text property="querylike_xm" style="width:85px" maxlength="20"></html:text>								
								</td>
								<th></th>
								<td></td>
							</tr>
								
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><logic:equal value="xy" name="userType">
										<input type="hidden" name="queryequals_xydm" value="${xydm }"/>
										<html:select property="xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:equal>
							
									<logic:notEqual value="xy" name="userType">
										<html:select property="queryequals_xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList();refreshForm('pjpy_zjlg_zhszcp.do');">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									</td>
									<th>专业</th>
									<td><html:select property="queryequals_zydm" styleId="zy" style="width:150px"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select></td>
									<th>班级</th>
									<td><html:select property="queryequals_bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go" onclick="allNotEmpThenGo('pjpy_zjlg_zhszcp.do?act=qry')">
										查询
								</button>
								 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									重 置
								 </button>
								</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				
				<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;记录数：<bean:write name="rsNum" />
							<font color="blue">提示：单击表头可以排序;
							</font>
							<logic:notEmpty name="zfbl">
								<font color="red">
									智育成绩所占比例：${zfbl.zybl }%;
									德育成绩所占比例：${zfbl.dybl }%
								</font>
							</logic:notEmpty>
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
							<logic:iterate name="rs" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand;">
							<td align="center">
								<input type="checkbox" id="cbv" name="cbv" style="height:17.5px"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
							</td>
							<logic:iterate id="v" name="s" offset="1">
								<td align="center">
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
						</logic:iterate>
					</table>
					<!--分页显示-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dycjActionForm"></jsp:include>
					<!--分页显示-->
				</logic:notEmpty>
				</div>
				
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:notEmpty name="result">
			<script>
				alert("" + $('message').value);
				document.getElementById('search_go').onclick();
			</script>
		</logic:notEmpty>
	</body>
	</html>
