<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript"></script>
	<body onload="xyDisabled('xy');chgDispconf('dispFlag')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_whlgdx.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">

		</script>
		<html:form action="/prise_conf_rs" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>当前所在位置：</em><a>${title }</a>
					</p>
				</div>
		
			<input type="hidden" id="isFdy" value="" />
			<input type="hidden" id="zyV" name="zyV" />
			<input type="hidden" id="bjV" name="bjV" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
			<!-- 该隐藏域用于子窗口判断 -->
			<logic:equal value="on" name="xqmod">
			<input type="hidden" id="xqmod" name="xqmod" value="${xqmod }" />
			</logic:equal>
			<logic:notEqual value="on" name="xqmod">
			<input type="hidden" id="xq" name="xq" value="${xq }" />
			</logic:notEqual>
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${tableName }" />
			<input type="hidden" id="act" name="act"
				value="${act }" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" id="pk" name="pk"
				value="${pk }" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			
			
			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
					<ul>
						<logic:equal name="userType" value="xx" scope="request">
						<li>
						<a href="#" class="btn_ck"
							onclick="showTopWin('viewTotStuNum.do',700,500)">
							查看参评人数
						</a>
						</li>
					    <li>
					    <a href="#" class="btn_sz" onclick="chkPriseBat();" >
							批量设置
						</a>
						</li>
    						<logic:notEqual value="北京林业大学" name="xxmc" scope="session">
							<%--武汉理工大学--%>
							<logic:equal value="10497" name="xxdm">
								<a href="#" class="btn_ck" onclick="viewFpb()" >
									名额分配表
								</a>
							</logic:equal>
							<%--武汉理工大学end--%>
							<%--<logic:notEqual value="10497" name="xxdm">
							<button type="button" class="button2"
								onclick="showTopWin('viewFpb.do',750,500)">
								名额分配表
							</button>
							</logic:notEqual>
    &nbsp;&nbsp;&nbsp;&nbsp;
    --%>
						</logic:notEqual>
						<li>
						<a href="#" class="btn_csh" onclick="priseDataInit()">
							初始化数据
						</a>
						</li>
    					<logic:notEqual value="北京林业大学" name="xxmc" scope="session">
<%--							<button type="button" class="button2"--%>
<%--								onclick="showTopWin('chg_prise_xn.do',300,200)"--%>
<%--								style="width:100px">--%>
<%--								调整学年--%>
<%--							</button>--%>
						</logic:notEqual>
					</logic:equal>
					<li>
					<a href="#" id="exp_datas" class="btn_dc" 
						onclick="openWins()">
						按<bean:message key="lable.xsgzyxpzxy" />导出数据
					</a>
					</li>
<%--					<li>--%>
<%--					<a href="#" id="btn_" class="btn_sz" onclick="showTopWin('pjpy_tyb_pjsjsz.do',450,330)">评奖周期设置</a>--%>
<%--					</li>--%>
						</ul>
					</div>
						
					<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<logic:equal value="10497" name="xxdm">
											<input type="hidden" name="go" value="a" />
											<button type="button" class="btn_cx"  id="search_go"
												onclick="listPriseConfJxj('/xgxt/prise_conf_rs.do')">
												查询
											</button>
										</logic:equal>
										<logic:notEqual value="10497" name="xxdm">
												<input type="hidden" name="go" value="a" />
												<button type="button" class="btn_cx"  id="search_go"
													onclick="listPriseConfJxj('/xgxt/prise_conf_rs.do')">
													查询
												</button>
										</logic:notEqual>
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
									<html:select property="xn" style="width:120px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								</td>
								
								<logic:equal value="on" name="xqmod">
									<th>
									学期
									</th>
									<td>
									<html:select property="xq" style="width:90px" disabled="true"
										styleId="xq">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								
									<html:select property="nd" style="width:90px;display:none" styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
										</td>
								</logic:equal>
								<logic:notEqual value="13108" name="xxdm">
									<th>
									年度
									</th>
									<td>
									<html:select property="nd" style="width:90px" disabled="true"
										styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
									</td>
								</logic:notEqual>

								<%--非武汉理工大学--%>
								<logic:notEqual value="10497" name="xxdm">
									<th>奖学金</th>
									<td>
									<html:select property="xmdm" style="width:150px"
										styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
									</html:select>
									</td>
								</logic:notEqual>
								<%--非武汉理工大学end--%>

								<th>显示方式</th>
								<td>
								<html:select property="dispFlag" style="width:70px"
									styleId="dispFalg" onchange="chgDispconf('dispFlag');">
									<html:option value="xydm"><bean:message key="lable.xsgzyxpzxy" /></html:option>
									<html:option value="zydm">专业</html:option>
									<html:option value="bjdm">班级</html:option>
								</html:select>
								</td>
							</tr>
							
							<logic:equal value="10497" name="xxdm">
								<tr>
									<th>
										奖学金分类
										</th>
										<td>
										<html:select property="jxjfl" style="width:150px"
											styleId="jxjfl" onchange="initJxjList()">
											<html:option value=""></html:option>
											<html:options collection="jxjflList" property="jxjfldm"
												labelProperty="jxjflmc" />
										</html:select>
										</td>
										<th>奖学金名称</th>
										<td>
										<html:select property="xmdm" style="width:150px"
											styleId="jxjdm">
											<html:option value=""></html:option>
											<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
										</html:select>
									</td>
								</tr>
							</logic:equal>
							<tr>
							<th>
								年级
								</th>
								<td>
								<html:select property="nj" styleId="nj"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								</td>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
								<html:select property="xydm" style="width:160px" styleId="xy"
									onchange="refreshForm('/xgxt/prise_conf_rs.do')">
									<html:option value="">    全部    </html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</td>
								<span id="dispZy"><th>专业</th><td><html:select
										property="zydm" style="width:160px;" styleId="zy"
										onchange="refreshForm('/xgxt/prise_conf_rs.do')">
										<html:option value="">    全部    </html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select> </td></span>
								<span id="dispBj"><th>班级</th><td><html:select
										property="bjdm" style="width:140px" styleId="bj">
										<html:option value="">    全部    </html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select> </td></span>
						</tr>
						
						</tbody>
					</table>
				</div>
			</div>
			
				<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> 
						<logic:notEmpty name="rs">
						记录数：
						<bean:write name="rsNum" />
						<logic:equal value="12764" name="xxdm">提示：双击一行可以调整金额；单击表头可以排序</logic:equal>
							<logic:notEqual value="12764" name="xxdm">提示：双击一行可以调整人数；单击表头可以排序</logic:notEqual>
						</logic:notEmpty>
						</span>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
							<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="left" style="cursor:hand"
								ondblclick="if(curr_row.cells[4].innerText==''){alert('尚未生成 建议人数，不能调整人数！');return false;}showTopWin('/xgxt/prise_conf_one.do',450,400);">

								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
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
					<!--分页显示-->
					
					<!--分页显示-->
				</logic:notEmpty>
			</div>
			<%--			<logic:notEqual name="commanForm" property="xydm" value="all"--%>
			<%--				scope="request">--%>
			<%--				<div id="jxjtmp" style="position:absolute;width:98%" align="center">--%>
			<%--					<fieldset>--%>
			<%--						<legend>--%>
			<%--							<bean:write name="userDepName" />--%>
			<%--							奖学金参评人数--%>
			<%--						</legend>--%>
			<%--						<table width="100%" class="tbstyle">--%>
			<%--							<thead>--%>
			<%--								<tr>--%>
			<%--									<td>--%>
			<%--										项 目--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										学业一等--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										学业二等--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										学业三等--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										社会工作--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										社会实践--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										文体活动--%>
			<%--									</td>--%>
			<%--									<td>--%>
			<%--										奖金总额--%>
			<%--									</td>--%>
			<%--								</tr>--%>
			<%--							</thead>--%>
			<%--							<tr onclick="rowOnClick(this)" style="cursor:hand">--%>
			<%--								<td>--%>
			<%--									建 议--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs1" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs2" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs3" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs4" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs5" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs6" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs7" />--%>
			<%--									元--%>
			<%--								</td>--%>
			<%--							</tr>--%>
			<%--							<tr onclick="rowOnClick(this)" style="cursor:hand"--%>
			<%--								style="color:<bean:write name="bgColor" />">--%>
			<%--								<td>--%>
			<%--									调整后--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs11" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs21" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs31" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs41" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs51" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs61" />--%>
			<%--									人--%>
			<%--								</td>--%>
			<%--								<td>--%>
			<%--									<bean:write name="rs71" />--%>
			<%--									元--%>
			<%--								</td>--%>
			<%--							</tr>--%>
			<%--							<thead>--%>
			<%--								<tr>--%>
			<%--									<td colspan="5"--%>
			<%--										style="height:18px;filter:alpha(opacity=0);cursor:hand;"--%>
			<%--										align="right">--%>
			<%--									</td>--%>
			<%--									<td onclick="hidFlag=!hidFlag;setInterval('tm(125,85)',10);"--%>
			<%--										colspan="3"--%>
			<%--										style="height:18px;filter:alpha(opacity=50);cursor:hand;"--%>
			<%--										align="right">--%>
			<%--										<bean:write name="userDepName" />--%>
			<%--										奖学金参评人数--%>
			<%--									</td>--%>
			<%--								</tr>--%>
			<%--							</thead>--%>
			<%--						</table>--%>
			<%--					</fieldset>--%>
			<%--				</div>--%>
			<%--			</logic:notEqual>--%>
			<logic:notEmpty name="initOK" scope="request">
				<logic:equal value="ok" name="initOK">
					<script language="javascript">
    alert("数据初始化完成！");
    </script>
				</logic:equal>
				<logic:equal value="no" name="initOK">
					<script language="javascript">
    alert("数据初始化失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:equal value="yes" name="writeAble" scope="request">
				
					<%--					<logic:equal name="userType" value="xy" scope="request">--%>
					<%--						<button type="button" class="button2"--%>
					<%--							onclick="showTopWin('xySetStuNum.do',450,405);">--%>
					<%--							上报参评人数--%>
					<%--						</button>--%>
					<%--					</logic:equal>--%>
					
					
		
			</logic:equal>
			<div id="tmpdiv"></div>
			<logic:present name="add">
				<logic:equal name="add" value="yes">
					<script language="javascript">
					alert("增加条件成功");
				</script>
				</logic:equal>
				<logic:equal name="add" value="no">
					<script language="javascript">
					alert("增加条件失败，请确认没有该类型条件后再增加");
				</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript">
if(document.getElementById("jxjtmp") != null){
	document.getElementById("jxjtmp").style.top = -85;
}

function openWins(){
	if (document.getElementById('jxjdm').value=='') {
		alert('请选择奖学金类别!');
		return;
	} else {
		url = 'jxjrsdataexp.do?jxjdm=';
		url += document.getElementById('jxjdm').value;
		url += '&xydm=';
		url += document.getElementById('xy').value;
		url += '&zydm=';
		url += document.getElementById('zy').value;
		url += '&bjdm=';
		url += document.getElementById('bj').value;
		url += '&bmlb=';
		url += document.getElementById('dispFalg').value;
		url += '&nj=';
		url += document.getElementById('nj').value;
		if($('xq')){
			url += '&xq=';
			url += document.getElementById('xq').value;
		}
		window.open(url);
	} 
}
</script>
	</body>
</html>
