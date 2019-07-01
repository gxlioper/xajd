<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>

	<body onload="xyDisabled('xy');">
		<html:form action="/zjjtPjpy" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
					<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">	
							<ul>
								<logic:equal name="mklx" value="sh">
									<li><a href="#" class="btn_sh" onclick="showInfo('/xgxt/zjjtPjpy.do?method=cxfUpdate','sh','800','600')">审核</a></li>
								</logic:equal>
								<logic:equal name="mklx" value="jg">
								<li><a href="#" class="btn_zj" onclick="">增加</a></li>
								<li><a href="#" class="btn_sc" onclick="sumitInfo('/xgxt/zjjtPjpy.do?method=cxfJg','del')">删除</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入数据</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">导出数据</a></li>	
								</logic:equal>							
							</ul>
						</div>
						</div>
					</logic:equal>
				<div class="rightcontent">
					<div class="searchtab">
						<table width="100%" class="tbstyle">
							<tbody>
								<tr>
								  <th>年级</th>
								  <td><logic:equal name="userType" value="stu">
											<html:select property="nj" style="" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj" labelProperty="nj" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="stu">
											<html:select property="nj" style="" onchange="initZyList();initBjList()">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj" labelProperty="nj" />
											</html:select>
										</logic:notEqual></td>
									<th>学年</th>
									<td><html:select property="xn" style=""
											onchange="">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select></td>
									<th>年度</th>
									<td><html:select property="nd" style=""
											onchange="">
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select></td>
									
								</tr>
								<tr>
									<th>学期</th>
									<td><html:select property="xq" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select>
									</td>
									<th>学号</th>
									<td><html:text property="xh" style="" maxlength="20"/></td>
									<th>姓名</th>
									<td><html:text property="xm" style="" maxlength="20"/></td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" /></th>
									<td><html:select property="xydm" style="width : 150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select></td>
									<th>专业</th>
									<td><logic:equal name="userType" value="stu">
											<html:select property="zydm" style="width : 150px" styleId="zy" onchange="initBjList()" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="stu">
											<html:select property="zydm" style="width : 150px" styleId="zy" onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:notEqual>
										</td>
										<th>班级</th>
										<td><logic:equal name="userType" value="stu">
											<html:select property="bjdm" style="width : 150px" styleId="bj" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="stu">
											<html:select property="bjdm" style="width : 150px" styleId="bj">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
									</td>
								</tr>
							</tbody>
							
							<tfoot>
								<tr>
									<td colspan="8">
									<div class="btn">
									<input type="hidden" name="go" value="a" />
										<logic:equal name="mklx" value="sh">
											<button id="search_go" onclick="allNotEmpThenGo('/xgxt/zjjtPjpy.do?method=cxfSh');">
												查询
											</button>
										</logic:equal>
										<logic:equal name="mklx" value="jg">
											<button id="search_go" onclick="allNotEmpThenGo('/xgxt/zjjtPjpy.do?method=cxfJg');">
												查询
											</button>
										</logic:equal>
									 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
										<!-- 结果 -->
										<logic:equal name="mklx" value="jg">
										<td>
											<input type="checkbox" id="selall" name="selall" onclick="selAll()">
										</td>
										</logic:equal>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="showInfo('/xgxt/zjjtPjpy.do?method=cxfUpdate','view','800','600')">
										<!-- 审核 -->
										<logic:equal name="mklx" value="sh">
											<logic:iterate id="v" name="s" offset="1" length="1">							
												<td align="center">
													<bean:write name="v" />
													<input type="checkbox" id="checkVal" name="checkVal"  style="display : none"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
												</td>	
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</logic:equal>
										<!-- 结果 -->
										<logic:equal name="mklx" value="jg">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal" 
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</logic:equal>
									</tr>
								</logic:iterate>
							</table>
							
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
					</logic:notEmpty>
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
