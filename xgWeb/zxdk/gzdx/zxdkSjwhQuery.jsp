<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/zxdk/gzdx/zxdkjs/zxdkjs.js"></script>
		<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<script type="text/javascript">
</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.双击一行可以查看详细信息；</br>
				2.单击表头可以排序。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/zxdk_gzdx.do" method="post">
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="operType" value="query" />
			<input type="hidden" name="tableName" value="${tableName }" />
			<input type="hidden" name="realTable" value="${realTable }" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="failInfo" id="failInfo"
				value="${failinfo }" />


			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('zxdk_gzdx_addZxdkSjwh.do',640,510)"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#"
									onclick="modiAndDel('zxdk_gzdx_updateZxdkSjwh.do?pkValue=','modi',640,510)"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#" onclick="deldata('zxdk_gzdx_delZxdkSjwh.do')"
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
										<button class="btn_cx" id="search_go"
											onclick="refreshForm('zxdk_gzdx_sjwh.do')">
											查询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
									<html:select property="nj" styleId="nj" style="width:150px"
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
									<html:select property="xn" styleId="xn" style="width:150px"
										styleClass="select">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
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
							</tr>
							<tr>
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
								<th>
									助学贷款名称
								</th>
								<td>
									<html:select property="zxdkmc" styleId="zxdkmc" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="身源地贷款">身源地贷款</html:option>
										<html:option value="国家助学贷款">国家助学贷款</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" styleClass="inputtext"
										style="width:130px" maxlength="15">
									</html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext"
										style="width:130px" maxlength="15">
									</html:text>
								</td>
								
								<th>
									贷款金额
								</th>
								<td>
									<html:text property="dkje_ks" 
										style='width:50px' maxlength="5"  styleId="dkje_ks" onblur="checkInputNum(this);"  /> (元)
										-
									<html:text property="dkje_js"
										style='width:50px' maxlength="5" styleId="dkje_js" onblur="checkInputNum(this);" /> (元)
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox"><!-- formbox -->
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
				<div class="con_overlfow">
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" width="100%">
							<thead>
								<tr>
									<td>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody >
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand;"
										ondblclick="modiAndDel('zxdk_gzdx_updateZxdkSjwh.do?operType=view&pkValue=','modi',640,510)" >
										<td nowrap="nowrap">
											<input type="checkbox" id="cbv" name="cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
												<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
												
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap="nowrap">
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
							page="/sjcz/turnpage.jsp?form=zxdkgzdxForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
					alertInfo('操作成功!');
					//document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
					alertInfo('操作失败!');
					//document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>