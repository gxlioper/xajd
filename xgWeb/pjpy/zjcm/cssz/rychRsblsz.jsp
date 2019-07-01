<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"
			src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js"></script>
	</head>
	<body onload="xyDisabled('xy');dispconf('fs')">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 参数设置 - 参数设置</a>
			</p>
		</div>
		<!-- 标题 end-->

		<html:form action="/pjpyZjcmCssz.do?method=rychRsblsz" method="post">
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />


			<div class="comp_title">
				<ul>
					<li>
						<a href="javascript:refreshForm('pjpyZjcmCssz.do?method=rsblsz');"><span>奖学金比例设置</span>
						</a>
					</li>
					<li class="ha">
						<a
							href="javascript:refreshForm('pjpyZjcmCssz.do?method=rychRsblsz');"><span>荣誉称号比例设置</span>
						</a>
					</li>
				</ul>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showTopWin('viewTotStuNum.do',700,500);"
								class="btn_ck">查看参评人数</a>
						</li>
						<li>
							<a href="#" onclick="baseDataInit('rych');" class="btn_csh">初始化数据</a>
						</li>
						<li>
							<a href="#"
								onclick="modiAndDel('pjpy_zjcm_rychrstz.do?pkValue=','modi','620','520');"
								class="btn_sz">人数调整</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('pjpy_zjcm_rychRsblsz.do?act=qry');">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="width:90px"
										styleClass="select" styleId="xn" disabled="true">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" styleId="xq" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" styleId="nd" disabled="true">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									荣誉称号
								</th>
								<td>
									<html:select property="jxjdm" styleId="jxjdm">
										<html:options collection="dmList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									显示范围
								</th>
								<td>
									<html:select property="fs" styleId="fs"
										onchange="dispconf('fs')">
										<html:options collection="cpfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()" styleClass="select"
										style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
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
									<html:select property="zydm" onchange="initBjList()"
										style="width:180px" styleClass="select" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:180px"
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
				<!-- 过滤条件 -->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查看详细信息;单击表头可以进行排序</font>
							</logic:notEmpty> </span>
					</h3>
					<logic:notEmpty name="rs">
						<div class="con_overlfow">
							<table summary="" class="dateline tablenowrap" align=""
								width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<td nowrap>
											<input type="checkbox" id="cb" name="cb"
												onclick="selectAll()" style="height: 17.5px" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1"
											scope="request">
											<td id="${tit.en}" onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" style="cursor:hand;"
											ondblclick="modiAndDel('pjpy_zjcm_rychrstz.do?act=view&pkValue=','modi','620','520');">
											<td align=center>
												<input type="checkbox" id="cbv" name="cbv"
													style="height: 17.5px"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align=center>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
					</logic:notEmpty>
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=pjpyZjcmActionForm"></jsp:include>
					<!--分页显示-->
				</div>
		</html:form>
		<div id="tmpdiv"></div>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
	 			alert('操作成功！');
	 			document.getElementById('search_go').onclick();
	 		</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
	 			alert('操作失败！');
	 			document.getElementById('search_go').onclick();
	 		</script>
			</logic:equal>
		</logic:present>
	</body>