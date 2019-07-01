<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjlgPjpyylxfb" method="post">
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

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 荣誉称号申请 - 优良学风班级申请结果查询</a>
				</p>
			</div>
			<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_zj" onclick="showTopWin('pjpy_zjlg_ylxfbsq.do',800, 600)">增加</a></li>
							<li><a href="#" class="btn_xg" onclick="modiAndDel('pjpy_zjlg_ylxfbModi.do?pkValue=','modi',800,600)">修改</a></li>
							<li><a href="#" class="btn_sc" onclick="deletedata('pjpy_zjlg_ylxfbwh.do?act=del')">删除</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
							<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>								
						</ul>
					</div>
					</div>
				</logic:equal>
			<div class="searchtab">		
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th>学年</th>
							<td><html:select property="queryequals_xn" style="width:100px">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select></td>
							<th>年级</th>
							<td><html:select property="queryequals_nj" style="width:80px"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select></td>
							<th></th><td></td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><logic:equal value="xy" name="userType">
									<input type="hidden" name="queryequals_xydm" value="${userDep }"/>
									<html:select property="xydm" styleId="xy" style="width:150px"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									<html:select property="queryequals_xydm" styleId="xy" style="width:150px"
									onchange="initZyList();initBjList()"> 
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:notEqual></td>
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
								<button type="button" id="search_go" onclick="allNotEmpThenGo('pjpy_zjlg_ylxfbwh.do?act=qry')">
									查 询
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px"/>
									</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
							<logic:iterate name="rs" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpy_zjlg_ylxfbModi.do?act=view&pkValue=','modi',760,600)">
							<td align="center">
								<input type="checkbox" id="cbv" name="primarykey_cbv" style="height:17.5px"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
									<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
							</td>
							<logic:iterate id="v" name="s" offset="2">
								<td align="center">
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
						</logic:iterate>
					</table>
					<!--分页显示-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=ylxfbActionForm"></jsp:include>
					<!--分页显示-->	
				</logic:notEmpty>
				
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
