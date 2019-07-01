<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/commit.js"></script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/rcsw_xszgl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="${realTable }" />
			
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" id="btn_zj"
									onclick="showTopWin('rcsw_xszgl.do?method=xszdjAdd',600,400)"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#" id="btn_xg"
									onclick="showUpdateWindow('primarykey_cbv','rcsw_xszgl.do?method=xszdjModi',600,400);"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#" id="btn_sc"
									onclick="batchData('primarykey_cbv','rcsw_xszgl.do?method=xszdj&type=del','del')"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#" id="btn_sc" onclick="impAndChkData();"
									class="btn_dr"> 导入 </a>
							</li>
							<li>
								<a href="#" id="btn_sc"
									onclick="expData('rcsw_xszgl.do?method=xszdjExp')"
									class="btn_dc"> 导出 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<%--<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>更多条件</strong>
										</label>
									--%>
									</div>
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/rcsw_xszgl.do?method=xszdj')">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:select property="queryequals_nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								学号
							</th>
							<td>
								<logic:equal value="student" name="userOnLine">
									<html:text property="querylike_xh" readonly="true"></html:text>
								</logic:equal>
								<logic:notEqual value="student" name="userOnLine">
									<html:text property="querylike_xh"></html:text>
								</logic:notEqual>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="querylike_xm"></html:text>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								<html:select property="queryequals_xydm" style="width:180px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>专业</th>
							<td>
								<html:select property="queryequals_zydm" styleId="zy"
									onchange="initBjList()" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>班级</th>
							<td>
								<html:select property="queryequals_bjdm" styleId="bj" style="width:180px">
									<logic:notEqual value="yes" name="isBzr">
										<html:option value=""></html:option>
									</logic:notEqual>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								登记时间
							</th>
							<td colspan="3">
								<html:text property="querygreaterequal_djsj" 
							           style="width:80px" 
							           onblur="dateFormatChg(this)"
							           onclick="return showCalendar('querygreaterequal_djsj','y-mm-dd');"></html:text>
								-
								<html:text property="querylessequal_djsj" 
							           style="width:80px"
							           onblur="dateFormatChg(this)"
							           onclick="return showCalendar('querylessequal_djsj','y-mm-dd');"></html:text>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
			
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align=""
						width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" >
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('rcsw_xszgl.do?method=xszdjModi&type=view')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" value="<bean:write name="v"/>">
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2" length="6">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="8" length="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="9">
									<td nowrap="nowrap">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</div>
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
			</logic:notEmpty>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						document.getElementById('search_go').onclick();
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
						document.getElementById('search_go').onclick();
					</script>
				</logic:notEqual>
			</logic:present>
		</html:form>
	</body>
</html>
