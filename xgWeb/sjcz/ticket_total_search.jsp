<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/commanFunction.js"></script>
	</head>
	<body>


		<html:form action="/prise_conf_rs" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="view_cptj" />
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('/xgxt/ticket_total_search.do?go=go')">
											查 询
										</button>
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
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" style="width:90px" disabled="true"
										styleId="xq">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									乘车日期
								</th>
								<td>
									<html:text property="ccrq" styleId="ccrq"
										onclick="return showCalendar('ccrq','y-mm-dd');"></html:text>
								</td>
								<th>
									车次
								</th>
								<td>
									<html:select property="cc" style="width:100px" styleId="cc">
										<html:option value=""></html:option>
										<html:options collection="ccList" property="cc"
											labelProperty="cc" />
									</html:select>
								</td>
								<th>
									到站
								</th>
								<td>
									<html:select property="dz" style="width:100px" styleId="dz">
										<html:option value=""></html:option>
										<html:options collection="dzList" property="czdm"
											labelProperty="czmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									&nbsp;&nbsp;
									<html:checkbox property="bxcc" value="1" styleId="bxcc"></html:checkbox>
									不限车次&nbsp;&nbsp;
									<html:checkbox property="ksy" value="1" styleId="ksy"></html:checkbox>
									可顺延&nbsp;&nbsp;
									<html:checkbox property="klc" value="1" styleId="klc"></html:checkbox>
									可联程&nbsp;&nbsp;
									<html:checkbox property="kwz" value="1" styleId="kwz"></html:checkbox>
									可无座&nbsp;&nbsp;
									<html:checkbox property="qt" value="1" styleId="qt"></html:checkbox>
									其他
								</td>
								
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
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
									<tr>

										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
				</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>

		</html:form>

	</body>
</html>
