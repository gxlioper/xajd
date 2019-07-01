<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='js/moveDiv.js'></script>
		<script type='text/javascript' src='js/comm/searchTj.js'></script>
		<script language="javascript">
		//一日规范分统计
		function yrgffTj(){
			document.forms[0].action = "/xgxt/rcswLnny.do?method=yrgfTj";
		    document.forms[0].target = "_blank";
		    document.forms[0].submit();
		    document.forms[0].target = "_self";
		}
		//规范分维护
		function gffwh(){
			allNotEmpThenGo('/xgxt/rcswLnny.do?method=yrgfManage&doType=query');
			
		}
		
		function showXx(){
			var pk = curr_row.getElementsByTagName('input')[0].value;
			var xn=$("xn").value;
			var xq=$("xq").value;
			var nd=$("nd").value;
			var kssj=$("kssj").value;
			var jssj=$("jssj").value;
			showTopWin("/xgxt/rcswLnny.do?method=xsyrgff&xh="+pk+"&xn="+xn+"&xq="+xq+"&nd="+nd+"&kssj="+kssj+"&jssj="+jssj,600,500);
		}
		</script>
	</head>

	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/rcswLnny">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xmdm" styleId="xmdm" />
			<input type="hidden" id="sffj" name="sffj" value="${sffj }" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum}" />
			<html:hidden property="xmlxdm" styleId="xmlxdm" />
			<html:hidden property="xmlxmc" styleId="xmlxmc" />
			
			<!-- 隐藏域 end-->

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="yrgffTj()" class="btn_tj">统计</a>
							</li>
							<li>
								<a href="#" onclick="gffwh()" class="btn_sh">规范分维护</a>
							</li>
						</ul>
					</div>
				</logic:equal>
			</div>
			
			<!-- 搜索 -->
			<div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/rcswLnny.do?method=yrgfTjcx&doType=query')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz"
											onclick="czSearchCond('xn-xq-nd-nj-xy-zy-bj-xh-xm-kssj-jssj');">
											重 置
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
									<html:select property="xn" style="" styleId="xn" >
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" style="" styleId="xq"
										>
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" style="" styleId="nd"
										>
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>

							</tr>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="" styleId="nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" style="" maxlength="20" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="" maxlength="20" />
								</td>

							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									院系
								</th>
								<td>
								<logic:equal name="userType" value="xy">
									<html:select property="queryequals_xydm" style="width:150px" styleId="queryequals_xydm" value="${userDep}"
										disabled="true"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" styleId="xy" value="${userDep}"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
									<html:select property="xydm" style="width:150px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:150px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>

							</tr>
							<tr>
								<th>
									打分时间
								</th>
								<td>
									<html:text property="kssj" styleId="kssj" style="width:90px"
										onclick="return showCalendar('kssj','y-mm-dd');"
										onblur="dateFormatChg(this)" readonly="true" />
									--
									<html:text property="jssj" styleId="jssj"   style="width:90px"
										onclick="return showCalendar('jssj','y-mm-dd');"
										onblur="dateFormatChg(this)" readonly="true" />
								</td>
								<td colspan="4">

								</td>
							</tr>
						</tbody>
					</table>
					<!-- 过滤条件 end-->
					<!-- 查询结果-->
				</div>
			
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp;
						 <logic:empty name="rs">
						 	<font color="red">未找到任何记录！</font>
						 </logic:empty>
						 <logic:notEmpty name="rs">
							<font color="blue">双击一条记录可以查看详细信息</font>
						 </logic:notEmpty>
						 </span>
					</h3>
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" 
										ondblclick="showXx()"
										style="cursor:hand">
										<td	style="display:none" nowrap="nowrap">
											<input type="hidden" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="0">
											<td  nowrap="nowrap">
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
					page="/sjcz/turnpage.jsp?form=rcswLnnyForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				</div>

				<!--分页显示-->
				</logic:notEmpty>
				
		</html:form>

	</body>
</html>
