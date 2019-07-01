<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
	</head>
	<body onload="xyDisabled('xy');">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a><logic:equal value="11049" name="xxdm">
						违纪处理 - 跟踪教育 - 跟踪教育 
					</logic:equal> <logic:notEqual value="11049" name="xxdm">
						<bean:write name="tips" scope="request" />
					</logic:notEqual> </a>
			</p>
		</div>

		<html:form action="/edu_search" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="realTable" name="realTable" value="wjcfb" />
			<input type="hidden" id="tableName" name="tableName" value="view_wjcf" />
		<input type="hidden" id="userType" name="userType"
						value="${userType }" />
		
		
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="chkPriseOne('track_teach.do?pkValue=',600,520)"
									class="btn_zj"> 添加个人表现 </a>
							</li>

							<logic:equal value="yes" name="isSHGC">
								<li>
									<a href="#" onclick="gzjyprint('jygzbprint.do?pkVal=')"
										class="btn_dy"> 打印/预览 </a>
								</li>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<li>
									<a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a>
								</li>
							</logic:notEqual>
							<li>
								<a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a>
							</li>
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
											onclick="allNotEmpThenGo('/xgxt/edu_search.do')">
											查 询
										</button>
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
									年级
								</th>
								<td>
									<input type="hidden" name="njV" value="" />
									<html:select property="nj" style="width:90px;padding-left:80px" styleId="nj"
										onchange="initZyList();initBjList()"
									>
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<input type="hidden" name="ndV" value="" />
									<html:select property="nd" style="width:90px" styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>

								<th>
									学年
								</th>
								<td>
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>

							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:200px"
										onchange="initZyList();initBjList()" styleId="xy">
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
										style="width:200px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:200px">
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
									<html:text property="xh" style="width:100px"></html:text>
								</td>
								<th>
									处分类别
								</th>
								<td>
									<html:select property="cflb" styleId="cflb">
										<html:option value=""></html:option>
										<html:options collection="cflbList" property="cflbdm"
											labelProperty="cflbmc" />
									</html:select>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> 
							<logic:notEmpty name="rs">
											<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
										</logic:notEmpty>
							</span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:equal value="yes" name="isSHGC">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											ondblclick="chkPriseOne('track_teach.do?pkValue=',500,400)"
											style="cursor:hand">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="2" length="8">
												<td nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<td>
												<logic:iterate id="v" name="s" offset="10" length="1">
													<img src="images/<bean:write name="v" />.gif" id="ishg"
														alt="<bean:write name="v" />"></img>
												</logic:iterate>
											</td>
										</tr>
									</logic:iterate>
								</logic:equal>
								<logic:notPresent name="isSHGC">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);" 
											ondblclick="chkPriseOne('track_teach.do?pkValue=',600,520)"
											style="cursor:hand">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="2">
												<td nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:notPresent>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<script language="javascript">
					function gzjyprint(url)
					{
						if (curr_row == null){
							window.open(url);
						}else{
							var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
							url += val;
							window.open(url);
						}
					}
		</script>
	</body>
</html>

