<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/pjpy/typj.js"></script>
	<script type="text/javascript">
		function dy() {
		var pkValue = '';
		
		if (null != curr_row) {
			pkValue = curr_row.getElementsByTagName('input')[0].value;
		}
		
		var url = '/xgxt/guizhdx.do?method=xjbjDy&pkValue='+pkValue;
		window.open(url);
	}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/guizhdx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>评奖评优－先进班级－先进班级查询</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"	value="${realTable }" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }">
			<input type="hidden" id="syme" value="${syme }" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<logic:equal value="xy" name="userType" scope="session">
				<input type="hidden"  name="queryequals_xydm" value="${userDep }" />
			</logic:equal>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/guizhdx.do?method=xjbjUpdate','800','600');"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#"
									onclick="checkUpdate('/xgxt/guizhdx.do?method=xjbjOne','modi','800','600');"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#"
									onclick="deletedata('/xgxt/guizhdx.do?method=xjbjManage&doType=del');"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#"
									onclick="impAndChkData();"
									class="btn_dr"> 导入数据 </a>
							</li>
							<li>
								<a href="#"
									onclick="expData('/xgxt/guizhdx.do?method=xjbjManage&doType=expData')"
									class="btn_dc"> 导出数据 </a>
							</li>
							<li>
								<a href="#"
									onclick="dy();"
									class="btn_dy"> 打印 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/guizhdx.do?method=xjbjManage&doType=query')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:select property="queryequals_nj" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="queryequals_xydm" onchange="initZyList();initBjList()" style="width:150px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="queryequals_zydm" onchange="initBjList()" style="width:150px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>	
								班级
							</th>
							<td>
								<html:select property="queryequals_bjdm" style="width:150px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学年
							</th>
							<td>
								<html:select property="queryequals_xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th>	
								辅导员审核
							</th>
							<td>
								<html:select property="queryequals_fdysh">
									<html:option value=""></html:option>
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />审核
							</th>
							<td>
								<html:select property="queryequals_xysh">
									<html:option value=""></html:option>
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								学校审核
							</th>
							<td>
								<html:select property="queryequals_xxsh">
									<html:option value=""></html:option>
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>	
						</tr>
					</tbody>
				</table>
			</div>
			</div>
			<div class="formbox" id="result">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs" >
							<font color="blue"> 
									提示：双击一行可以查看详细信息；单击表头可以排序</font>
				 		 </logic:notEmpty>
				    </span>
				    </h3>
					<logic:notEmpty name="rs">
							<div class="con_overlfow">
								 <table summary="" id="rsTable" class="dateline" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px">
										</td>
										<logic:iterate id="tit" name="topTr" offset="3" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										name="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
										ondblclick="showInfo('/xgxt/guizhdx.do?method=xjbjOne','view','800','600');"
										style="cursor:hand;background-color:
										    <logic:iterate id="v" name="s" offset="1" length="1">
										    <bean:write name="v"/>
										    </logic:iterate>
										     ">
										<td align=center>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													value="<bean:write name="v"/>"
													<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> />
												<input type="hidden" value="<bean:write name="v" />">
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=guizhdxForm"></jsp:include>
							</div>
					</logic:notEmpty>
<%--					<logic:equal value="yes" name="writeAble">--%>
<%--	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">--%>
<%--	                		<button class="button2" onclick="showTopWin('/xgxt/guizhdx.do?method=xjbjUpdate','800','600');"--%>
<%--								style="width:80px">--%>
<%--								增加--%>
<%--							</button>--%>
<%--	                		&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<button class="button2" onclick="checkUpdate('/xgxt/guizhdx.do?method=xjbjOne','modi','800','600');"--%>
<%--								style="width:80px">--%>
<%--								修 改--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<button class="button2" onclick="deletedata('/xgxt/guizhdx.do?method=xjbjManage&doType=del');"--%>
<%--								style="width:80px">--%>
<%--								删 除--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<button class="button2"onclick="impAndChkData();"style="width:80px">--%>
<%--								导入数据--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<button class="button2" onclick="expData('/xgxt/guizhdx.do?method=xjbjManage&doType=expData')" style="width:80px">--%>
<%--								导出数据--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<button class="button2" onclick="dy();" style="width:80px">--%>
<%--								打&nbsp;&nbsp;印--%>
<%--							</button>--%>
<%--						</div>--%>
<%--					</logic:equal>--%>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
