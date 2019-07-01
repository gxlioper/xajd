<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"
			src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/pjpyLsxy" method="post">
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
			<!-- 批量删除信息提示 -->



			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" id="btn_zj"
									onclick="showTopWin('pjpyLsxy.do?method=rychSq','700','550')"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#" id="btn_xg"
									onclick="modi('pjpyLsxy.do?method=rychModi','700','550');"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#" id="btn_sc"
									onclick="dataBatch('pjpyLsxy.do?method=rychResult&act=del')"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();" class="btn_dr"
									id="btn_dr"> 导入 </a>
							</li>
							<li>
								<a href="#" id="btn_dc"
									onclick="wjcfDataExport('pjpyLsxy.do?method=expRych');return false;"
									class="btn_dc"> 导出 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="refreshForm('pjpyLsxy.do?method=rychResult&act=qry');">
											查 询
										</button>
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
									<html:select property="queryequals_nj" styleId="nj"
										styleClass="select" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学年
								</th>
								<td>
									<html:select property="queryequals_xn" style="width:90px"
										styleClass="select" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="querylike_xh" styleId="xh"
										styleClass="inputtext" style="width:100px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" styleId="xm"
										styleClass="inputtext" style="width:100px"></html:text>
								</td>
								<th>
									荣誉称号
								</th>
								<td>
									<html:select property="queryequals_rychdm" styleClass="select"
										styleId="rychdm">
										<html:option value=""></html:option>
										<html:options collection="rychList" property="rychdm"
											labelProperty="rychmc" />
									</html:select>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleClass="select"
										style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" style="width:180px"
										styleClass="select" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" style="width:180px"
										styleClass="select" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：双击一行可以查看详细信息;单击表头可以进行排序;</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="all" name="all" onclick="chec()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap="nowrap">
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand;"
										ondblclick="modi('pjpyLsxy.do?method=rychModi&act=view','700','550');">
										<td align=center>
											<input type="checkbox" name="primarykey_cbv" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<input type="hidden"
												value="<logic:iterate id="v" name="s" offset="9" length="1"><bean:write name="v"/></logic:iterate>" />
											<input type="hidden"
												value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v"/></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--分页显示-->
			</div>
			<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyLsxyForm"></jsp:include>
			<!--分页显示-->
			</logic:notEmpty>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
	 			alert('操作成功！');
	 			document.getElementById('search_go').onclick();
	 		</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<input type="hidden" id="message" value="${message}" />
				<script>
	 			alert(document.getElementById('message').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
			</logic:equal>
		</logic:present>
	</body>
</html>