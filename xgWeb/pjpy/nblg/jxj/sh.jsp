<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script type="text/javascript" src="pjpy/nblg/pjpynblg.js"></script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpynblgwh" method="post">
		<div class="tab_cur">
					<p class="location">
						<em>当前所在位置：</em><a>评奖评优 － 审核 － 奖学金审核</a>
					</p>
		</div>
		<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<input type="hidden" name="ts" id="ts"/>
						<ul>
							<li><a href="#" class="btn_shtg" onclick="shandSubmit('pjpy_nblg_jxjsh.do','tg')">审核通过</a></li>
							<li><a href="#" class="btn_shbtg" onclick="shandSubmit('pjpy_nblg_jxjsh.do','btg')">审核不通过</a></li>
							<li><a href="#" class="btn_dy" onclick="window.open('pjpy_nblg_jxjprint.do?xh=')">打印报表</a></li>
						    <li><a href="#" class="btn_dc" onclick="dataExport()">数据导出</a></li>
						</ul>
					</div>
					</div>
		</logic:equal>	
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" id="tableName" name="tableName"
			value="${tableName}" />
		<input type="hidden" id="realTable" name="realTable"
			value="${realTable}" />
		<input type="hidden" id="failInfo" name="failInfo"
			value="${failinfo }" />
		<input type="hidden" id="pt" value="pt"/>
		<!-- 批量删除信息提示 -->
		
				<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
					<tr>
					<th align="left">年级</th>
						<td><html:select property="nj" styleId="nj" style="width:90px"
								onchange="initZyList();initBjList()" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th align="left">学年</th>
						<td>
							<html:select property="xn" style="width:90px" styleClass="select"
								styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th align="left">奖学金</th>
						<td><html:select property="jxjdm" styleId="jxjdm" style="width:150px" onchange="refreshForm('pjpy_nblg_jxjsh.do')">
								<html:option value=""></html:option>
								<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="left"><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						    </td>	
						    <th align="left">专业</th>
							<td><html:select property="zydm" onchange="initBjList()"
								style="width:180px" styleClass="select" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							</td>
							<th align="left">班级</th>
							<td><html:select property="bjdm" style="width:180px"
								styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						    </td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" 
							onclick="jxjqryres();">
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
							<logic:notEmpty name="xzrs">获奖人为控制在 <font color="red">${xzrs }</font> </logic:notEmpty>
						</span>
					</h3>
						<table width="99%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="2" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="">
									<td align=center>
										<input type="checkbox" id="cbv" name="cbv" <logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length="1">
										<td align=center>
										<a href="#" onclick="queryOne()" title="该生详细信息">
											<bean:write name="v" />
											<input type="hidden" value="<bean:write name="v"/>" />
										</a>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="4">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
				</logic:notEmpty>
				<div id="tmpdiv"></div>
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
	 			alert('' + document.getElementById('failInfo').value);
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
	</logic:present>
</body>
</html>
