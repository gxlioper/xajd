<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"
			src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<script type="text/javascript">
		<!--
			function openWin(url) {
				if (curr_row != null && curr_row != '') {
					url += curr_row.cells[0].getElementsByTagName("input")[0].value;;
				} else {
					alert('没有选择相应记录，请选择之后再进行操作！！');
					return false;
				}
				showOpenWindow(url,800,600)
			}
			function checkRowBox() {
				if (curr_row != null && curr_row != '') {
						curr_row.cells[0].getElementsByTagName("input")[0].checked = true;			
				}
			}
		//-->
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>



		<html:form action="/wjcfxmlgwh" method="post">
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="operType" value="query" />
			<input type="hidden" name="tableName" value="${tableName }" />
			<input type="hidden" name="realTable" value="${realTable }" />
			<input type="hidden" name="pt" id="pt" />
			<input type="hidden" name="actType" id="actType" value="cfxxwh" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox" id="btn">
						<ul>
							<li>
								<a href="#" onclick="refreshForm('stuPunishApp.do')"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#"
									onclick="openWin('wjcf_xmlg_modiCfsbxx.do?pkValue=')"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#" onclick="deldata('wjcf_xmlg_delCfsbxx.do')"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a>
							</li>
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
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('wjcf_xmlg_cfsbxxModi.do')">
											查询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
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
									<html:select property="nj" styleId="nj" style="width:80px"
										styleClass="select" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:90px"
										styleClass="select">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" styleId="nd" style="width:90px">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:150px"
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
										style="width:150px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
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
									<html:text property="xh" styleId="xh" styleClass="inputtext"
										style="width:100px">
									</html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext"
										style="width:100px">
									</html:text>
								</td>
								<th>
									处分类别
								</th>
								<td>
									<html:select property="cflb" styleId="cflb" styleClass="select">
										<html:option value="">--请选择--</html:option>
										<html:options collection="cflbList" property="cflbdm"
											labelProperty="cflbmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									处分原因
								</th>
								<td>
									<html:select property="cfyy" styleId="cfyy" styleClass="select" style="width:150px">
										<html:option value="">--请选择--</html:option>
										<html:options collection="cfyyList" property="cfyydm"
											labelProperty="cfyymc" />
									</html:select>
								</td>
								<th>
									审核结果
								</th>
								<td>
									<html:select property="xxsh" styleId="xxsh" styleClass="select">
										<html:option value="">--请选择--</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="解除处分">解除处分</html:option>
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
								<font color="blue"> 提示：双击一行可以查看详细信息；</font>
							</logic:notEmpty>
							</span>
					</h3>

					<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" width="100%">
							<thead>
								<tr>
									<td>
										<input type="checkbox" id="cb" name="cb" disabled="true" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand;"
										align="left"
										ondblclick="openWin('wjcf_xmlg_modiCfsbxx.do?operType=view&pkValue=')"
										style="cursor:hand;background-color:
										    <logic:iterate id="v" name="s" offset="2" length="1">
										    <bean:write name="v"/>
										    </logic:iterate>
										     "
										>
										<td>
											<input type="checkbox" id="cbv" name="cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
												<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
										</td>
										<logic:iterate id="v" name="s" offset="3">
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
							page="/sjcz/turnpage.jsp?form=wjcfXmlgActionForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="yes" name="writeAble" scope="request">
		</logic:equal>
		<logic:present name="deleted">
			<logic:equal value="true" name="deleted">
				<script>
					alert('操作成功!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="false" name="deleted">
				<script>
					alert('操作失败!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
