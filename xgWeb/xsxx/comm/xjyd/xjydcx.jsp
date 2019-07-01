<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/xsxx/comm/xjyd/xjyd.js"></script>
	</head>
	<body>

		<html:form action="/xsxxXjyd" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" id="isFdy"     name="isFdy"  	 value="${isFdy }" />
			<input type="hidden" id="userName"  name="userName"  value="${userName }" />
			<input type="hidden" id="userType"  name="userType"  value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="view_bks_xjydxx" />
			<input type="hidden" id="realTable" name="realTable" value="bks_xjydxx" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			
			<input type="hidden" name="ydhxyV" value="" />
			<input type="hidden" name="ydhzyV" value="" />
			<input type="hidden" name="ydhbjV" value="" />
			
			<!-- 大师的权限用的 -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview" value="${ purview }" /> 
				<input type="hidden" id=" operateBound " name="operateBound" value="${ operateBound }" />
			</logic:present>
			

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									id = "btn_zj"
									onclick="showTopWin('xsxxXjyd.do?method=xjydAdd','750','550')"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#"
									id = "btn_xg"
									onclick="showUpdateWindow('primarykey_cbv','xsxxXjyd.do?method=xjydUpdate','750','550')"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#"
									id="btn_sc"
									onclick="doDel('primarykey_cbv','xsxxXjyd.do?method=delXjyd')"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#"
									id="btn_cs"
									onclick="showViewWindow('primarykey_cbv','xsxxXjyd.do?method=lcgzXjyd','550','350',false,'请勾选一条记录!')"
									class="btn_cs">流程跟踪 </a>
							</li>
<%--							<li>--%>
<%--								<a href="#"--%>
<%--									id="btn_dr"--%>
<%--									onclick="impAndChkData()"--%>
<%--									class="btn_dr"> 导入 </a>--%>
<%--							</li>--%>
							<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>	
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						
						<tbody>
							<tr>
								<th>
									学号
								</th>
								<td>
									<logic:equal value="stu" name="userType">
										<html:text property="xh" style="width:120px" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										<html:text property="xh" style="width:120px"></html:text>
									</logic:notEqual>
								</td>
								<th>姓名</th>
								<td>
									<html:text property="xm" style="width:120px"></html:text>
								</td>
								<th>
									异动类别
								</th>
								<td>
									<html:hidden property="save_shlcid" styleId="shlcid"/>
									<html:select property="ydlbm" styleId="ydlbm" style="width:180px" >
										<html:option value=""></html:option>
										<html:options collection="ydlbAllList" property="ydlbm" labelProperty="ydlbmc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>申请时间</th>
								<td>
									<html:text property="sqkssj" style="width:80px"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('sqkssj','y-mm-dd');"></html:text>
									-
									<html:text property="sqjssj" style="width:80px"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('sqjssj','y-mm-dd');"></html:text>
								</td>
								<th>异动前年级</th>
								<td>
									<html:select property="ydqnj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>异动后年级</th>
								<td>
									<html:select property="ydhnj" styleId="ydhnj"
										onchange="initYdhZyList();initYdhBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>异动前<bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="ydqxydm"
										onchange="initZyList();initBjList();" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydqXyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>异动前专业</th>
								<td>
									<html:select property="ydqzydm"
										onchange="initBjList();" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydqZyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>异动前班级</th>
								<td>
									<html:select property="ydqbdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<logic:present name="ydqBjList">
											<html:options collection="ydqBjList" property="bjdm"
												labelProperty="bjmc" />
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>异动后<bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="ydhxydm"
										onchange="initYdhZyList();initYdhBjList()" styleId="ydhxy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydhXyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>异动后专业</th>
								<td>
									<html:select property="ydhzydm"
										onchange="initYdhBjList();" styleId="ydhzy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydhZyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>异动后班级</th>
								<td>
									<html:select property="ydhbdm" styleId="ydhbj"
										style="width:180px">
										<html:option value=""></html:option>
										<logic:present name="ydhBjList">
											<html:options collection="ydhBjList" property="bjdm"
												labelProperty="bjmc" />
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>审核结果</th>
								<td>
									<html:select property="shzt" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="shjgList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th>
								学年
							</th>
							<td>
								<html:select property="xn" style="width:180px" 
									styleId="xn" styleClass="select" >
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>
								学期
							</th>
							<td>
								<html:select property="xq" style="width:180px" 
									styleId="xq" styleClass="select" >
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							</tr>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xsxxXjyd.do?method=xjydcxquery&doType=query')">
											查 询
										</button>
										<button type="button" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> 
					</span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												alt="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"/>
										</td>
										
											<td>
												<a
												href="javascript:showTopWin('xsxxXjyd.do?method=xjydUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>','750','600');"
												class="pointer" style="color:#0A63BF"> 
													<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>
												</a>
											</td>
										
										<logic:iterate id="v" name="s" offset="3">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxXjydForm"></jsp:include>
				<!--分页显示-->
			</div>
			
		</html:form>
		<logic:present name="message">
			<script language="javascript">
         		alert("${message}");
         	</script>
		</logic:present>
	</body>
</html>
