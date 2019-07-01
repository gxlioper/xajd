<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
				<input type="hidden" id="userDep" name="userDep"
				value="${userDep }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />

			<logic:equal value="xy" name="userType" scope="session">
				<html:hidden property="queryequals_bmdm" value="${userDep }" />
			</logic:equal>



			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="showTopWin('/xgxt/jygl_jygzhysb.do','700','500')"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#"
									onclick="update('/xgxt/jygl.do?method=jygzhysb','700','500')"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#"
									onclick="deletedata('/xgxt/jygl.do?method=jygzhycx&doType=del');"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a>
							</li>
							<li>
								<a href="#"
									onclick="expData('/xgxt/jygl.do?method=jygzhycx&doType=expData')"
									class="btn_dc"> 导出 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jygzhycx&doType=query')">
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
								<th>部门名称</th>
								<td>
									<html:select property="queryequals_bmdm" style="width:160px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
								</td>
								<th>时间</th>
								<td colspan="3">
									<html:text property="querygreaterequal_sj"
											   styleId="querygreaterequal_sj"
											   onblur="dateFormatChg(this)"
										       onclick="return showCalendar(this.id,'y-mm-dd');"
									></html:text>
									-
									<html:text property="querylessequal_sj"
											   styleId="querylessequal_sj"
											   onblur="dateFormatChg(this)"
										       onclick="return showCalendar(this.id,'y-mm-dd');"
									></html:text>
								</td>
							</tr>
							<tr>
								<th>会议类型</th>
								<td>
									<html:select property="queryequals_hylxdm" style="width:160px">
										<html:options collection="hylxList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
								<th>会议主题</th>
								<td><html:text property="querylike_hyzt" style="width:155px"></html:text></td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>主持人</th>
								<td><html:text property="querylike_zcr" style="width:155px"></html:text></td>
								<th>审核状态</th>
								<td>
									<html:select property="queryequals_shzt" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<%--<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px"/>--%>
									</td>
									<logic:iterate id="tit" name="topTr" offset="2" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showInfo('/xgxt/jygl.do?method=jygzhysb','view','800','600')"
										class="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
										<td align=center>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
												/>
											<input type="hidden" value="<bean:write name="v" />" />
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
					</div>
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<!--分页显示-->
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="message">
			<script language="javascript">
	         	alert("${message}");
	         </script>
	     </logic:present>
	</body>
</html>
