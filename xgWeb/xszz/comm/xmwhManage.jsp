<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script language="javascript">
		function setKgzt(value,num){
			var id = "kgzt"+num;
			$(id).value = value;
		}
		
		function saveKgzt(){
			if (confirm('确定开关状态吗？')){
				saveUpdate('/xgxt/commXszz.do?method=xmwhManage&doType=save','');
			}
		}
		
		//前往人数设置
		function goRssz(){
			var url = "xszz_xgsz_rssz.do";	
				url+="?xmdm="+$("savedXmdm").value;	
				url+="&kzjb="+$("savedKzjb").value;
											
			showWaitingDiv("30000");											
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXszz">
			<!-- 隐藏域 -->
			<html:hidden property="iskns"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="savedXmdm" id="savedXmdm" value=""/>
			<input type="hidden" name="savedKzjb" id="savedKzjb" value=""/>
			<button type="button"   id="forward" onclick="goRssz()" style="display: none">跳转</button>
			<!-- 隐藏域 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<!-- 困难生认定单独抽出模块屏蔽增加、删除、导出功能按钮 -->
							<logic:notEqual value="true" name="knsdl">
								<li>
									<a href="#" 
										onclick="showTopWin('/xgxt/commXszz.do?method=xmwhUpdate','800','600');"
										class="btn_zj">增加</a>
								</li>
							</logic:notEqual>
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/commXszz.do?method=xmwhUpdate','update','800','600')"
									class="btn_xg">修改</a>
							</li>
							<logic:notEqual value="true" name="knsdl">
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/commXszz.do?method=xmwhManage','xmDel')"
										class="btn_sc">删除</a>
								</li>
							</logic:notEqual>
							<li>
								<a href="#" 
									onclick="saveKgzt()" 
									class="btn_ccg">保存开关</a>
							</li>
							<logic:notEqual value="true" name="knsdl">
								<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
								<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
							</logic:notEqual>
						</ul>
					</div>
				</logic:equal>
				<!-- 按钮 end-->
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="bz">
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;当前学年：${xn}&nbsp;&nbsp;&nbsp;&nbsp;
											当前学期：${xq}&nbsp;&nbsp;&nbsp;&nbsp;当前年度：${nd }</font>
									</div>
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button"  class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commXszz.do?method=xmwhManage');">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"   class="btn_cz" id="btn_cz" 
											onclick="searchReset();return false;">
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
									涉及金额
								</th>
								<td>
									<html:select property="queryequals_sfje" style="width:100px" styleId="sfje" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sfjeList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									是否分级
								</th>
								<td>
									<html:select property="queryequals_sffj" style="width:100px" styleId="sffj" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sffjList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									金额类型
								</th>
								<td>
									<html:select property="queryequals_jelx" style="width:100px" styleId="jelx" onchange="">
										<html:option value=""></html:option>
										<html:options collection="jelxList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									审核级别
								</th>
								<td>
									<html:select property="queryequals_shjb" style="width:100px" styleId="shjb" onchange="">
										<html:option value=""></html:option>
										<html:options collection="shjbList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							
							<!-- 第二行 -->
							<!-- 第三行 -->
							<tr>
								<th>
									人数控制
								</th>
								<td>
									<html:select property="queryequals_rskz" style="width:100px" styleId="rskz" onchange="">
										<html:option value=""></html:option>
										<html:options collection="rsjbList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									申请周期
								</th>
								<td>
									<html:select property="queryequals_sqzq" style="width:100px" styleId="sqzq" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sqzqList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									评定时间
								</th>
								<td>
									<html:select property="queryequals_pdsj" style="width:100px" styleId="pdsj" onchange="">
										<html:option value=""></html:option>
										<html:options collection="pdsjList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<td colspan="2"></td>
							</tr>
							<!-- 第四行 -->
							<tr>
								<th>
									项目类别
								</th>
								<td>
									<html:select property="queryequals_xmlb" style="width:100px" styleId="xmlb" onchange="chXmlb()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									项目
								</th>
								<td>
									<html:select property="queryequals_xmdm" style="width: 100px" styleId="xmdm">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm" labelProperty="xmmc" />
									</html:select>
								</td>
								<th>
									项目名称
								</th>
								<td>
									<html:text property="querylike_xmmc" onkeypress="if(pressEnter(event)){return false;}" styleId="xmmc" style="width: 100px" maxlength="20"/>
									<html:text property="xh" styleId="xh" style="display: none"/>
								</td>
								<th>
									默认项目
								</th>
								<td>
									<html:select property="queryequals_mrxm" style="width:100px" styleId="mrxm" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
							<logic:notEmpty name="rs">
								<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息.</font>
							</logic:notEmpty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<div class="con_overlfow">
						 <table summary="" id="rsTable" class="dateline tablenowrap" width="100%">
							<!-- 表头 -->
							<thead>
								<tr>
									<td width="5%" align="center">
										<input type="checkbox" id="selall" name="selall" onclick="selAll()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" 
										style="cursor:hand" 
										ondblclick="showInfo('/xgxt/commXszz.do?method=xmwhUpdate','view','800','600')">
										<!-- checkbox -->								
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align="left">
												<input type="hidden" name="zzxmdm" value="${v }"/>
												<logic:iterate id="mrxm" name="s" offset="1" length="1">
													<!-- 默认项目 -->
													<logic:equal name="mrxm" value="是">
														<input type="checkbox" id="checkVal"  name="primarykey_checkVal" disabled="disabled"/>
													</logic:equal>
													<!-- 默认项目 end-->
													<!-- 非默认项目 -->
													<logic:notEqual name="mrxm" value="是">
														<input type="checkbox" id="checkVal"  name="primarykey_checkVal"  value="<bean:write name="v"/>"/>
													</logic:notEqual>
													<!-- 非默认项目 end-->
												</logic:iterate>		
											</td>
										</logic:iterate>
										<!-- 项目信息 -->		
										<logic:iterate id="v" name="s" offset="2" length="10">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<!-- 开关 -->		
										<logic:iterate id="v" name="s" offset="12" length="1">
											<td align="left" nowrap>
												<logic:iterate name="kgztList" id="kg">
													${kg.en }
													<logic:equal name="kg" property="en" value="${v }">
														<input type="radio" value="${kg.en }" name="kg${index }" checked="checked" onclick="setKgzt(this.value,${index })"/>
														<br/>
													</logic:equal>
													<logic:notEqual name="kg" property="en" value="${v }">
														<input type="radio" value="${kg.en }" name="kg${index }" onclick="setKgzt(this.value,${index })"/>
														<br/>
													</logic:notEqual>
												</logic:iterate>
												<input type="hidden" name="zzxmkg" id="kgzt${index }" value="${v }"/>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>		
							<!--内容 end -->
						</table>
						</div>
					<!--分页-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
					<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		<!-- 提示信息 end-->
	</body>
</html>