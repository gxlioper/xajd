<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//保存报修申请
		function saveBxSq(){
			
			var mklx = $("mklx").value;
			var url = "/xgxt/zjcmGygl.do?method=gybxSq&doType=save";
			url += "&mklx="+mklx;
			if (checkSjTj("bxsj","报修时间","xwsj","希望时间")) {	
				saveUpdate(url,'xh');	
			}
		}
		
		//显示评价信息
		function showPjInfo(){
		
			var mklx = $("mklx").value;
			var userType = $("userType").value;
		
			//if(userType != "stu"){
				showInfo('/xgxt/zjcmGygl.do?method=gybxPj','view','800','600');
			//}else{
			//	showInfo('/xgxt/zjcmGygl.do?method=gybxPj','view','800','600');
			//}
		}
		</script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjcmGygl" >
			<!-- 隐藏域 -->
			<%@ include file="/gygl/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 学生用户 -->
						<logic:equal name="userType" value="stu">
							<li>
								<a href="#" 
									onclick="showInfo('/xgxt/zjcmGygl.do?method=gybxPj','pj','800','600')"
									class="btn_xg">评价</a>
							</li>
						</logic:equal>
						<!-- 非学生用户 -->
						<logic:notEqual name="userType" value="stu">
							<logic:equal value="yes" name="writeAble">
								<li>
									<a href="#" 
										onclick="showTopWin('/xgxt/zjcmGygl.do?method=gybxSq&isjg=yes',800,600);"
										class="btn_zj">增加</a>
								</li>
								<li>
									<a href="#"
										onclick="showInfo('/xgxt/zjcmGygl.do?method=gybxSh','update','800','600')"
										class="btn_xg">修改</a>
								</li>
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/zjcmGygl.do?method=bxjgManage','del')"
										class="btn_sc">删除</a>
								</li>
								<li>
									<a href="#" 
										onclick="impAndChkData()" 
										class="btn_dr">导入</a>
								</li>
							</logic:equal>
							<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" 
									onclick="wjcfDataExport('/xgxt/zjcmGygl.do?method=bxjgManage&doType=exp')" 
									class="btn_dc">导出</a>
							</li>
							</logic:equal>
						</logic:notEqual>
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="if(checkSearchTj('querygreaterequal_bxsj','querylessequal_bxsj')){allNotEmpThenGo('/xgxt/zjcmGygl.do?method=bxjgManage')}">
											查询
										</button>
										<button class="button2" style="" id="cz"
											onclick="czSearchCond('nj-xn-xq-nd-xy-zy-bj-xh-xm-xqdm-lddm-cs-qsh-querygreaterequal_bxsj-querylessequal_bxsj-sfsf-sfwg-shzt');">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj" style="" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>												
								</td>
								<th>
									学号
								</th>
								<td>
									<!-- 学生用户 -->
									<logic:equal name="userType" value="stu">
										<html:text property="querylike_xh" styleId="xh" style="" maxlength="20" readonly="true"/>
									</logic:equal>
									<!-- 非学生用户 -->
									<logic:notEqual name="userType" value="stu">
										<html:text property="querylike_xh" styleId="xh" style="" maxlength="20"/>
									</logic:notEqual>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" styleId="xm" style="" maxlength="20"/>	
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<!-- 是学院用户 -->
									<logic:equal name="isxy" value="true">
										<html:hidden property="queryequals_xydm"/>
										<html:select property="xydm" style="" styleId="xy" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>	
									</logic:equal>
									<!-- 是学院用户 end -->
									<!-- 非学院用户 -->
									<logic:equal name="isxy" value="false">
										<html:select property="queryequals_xydm" style="width: 150px" styleId="xy" 
											onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>	
									</logic:equal>
									<!-- 非学院用户 end-->											
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm" style="width: 150px" styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" style="width: 150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									校区
								</th>
								<td>
									<html:select property="queryequals_xqdm" style="width: 150px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>								
								</td>
								<th>
									楼栋
								</th>
								<td>
									<html:select property="queryequals_lddm" style="width: 150px" styleId="lddm" 
										onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									所属层数
								</th>
								<td>
									<html:select property="queryequals_cs" style="width: 150px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- 第四行 -->
							<tr>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="queryequals_qsh" style="width: 150px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>				
								</td>
								<th>
									报修时间
								</th>
								<td>
									<html:text property="querygreaterequal_bxsj" styleId="querygreaterequal_bxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:60px"
										onclick="return showCalendar('querygreaterequal_bxsj','y-mm-dd');"/>	
									—
									<html:text property="querylessequal_bxsj" styleId="querylessequal_bxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:60px"
										onclick="return showCalendar('querylessequal_bxsj','y-mm-dd');"/>	
								</td>
								<th>
									维修费用
								</th>
								<td>
									<html:text property="querygreaterequal_wxfy" 
										styleId="querygreaterequal_wxfy"
										onkeydown="return onlyNum(this,10)"
									    onmousedown="return onlyNum(this,10)"
									    maxlength="10" style="width:60px;ime-mode:disabled"
									/>	
									—
									<html:text property="querylessequal_wxfy" styleId="querylessequal_wxfy"
									styleId="querygreaterequal_wxfy"
										onkeydown="return onlyNum(this,10)"
									    onmousedown="return onlyNum(this,10)"
									    maxlength="10" style="width:60px;ime-mode:disabled"
									/>	
								</td>
							</tr>
							<!-- 第五行 -->
							<tr>
								<th>
									审核状态
								</th>
								<td>
									<html:select property="queryequals_shzt" style="" styleId="shzt">
										<html:option value="">----请选择----</html:option>
										<html:options collection="shList" property="en" labelProperty="cn" />
									</html:select>						
								</td>
								<th>
									是否收费
								</th>
								<td>
									<html:select property="queryequals_sfsf" style="" styleId="sfsf">
										<html:option value="">----请选择----</html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									是否完工
								</th>
								<td>
									<html:select property="queryequals_sfwg" style="" styleId="sfwg">
										<html:option value="">----请选择----</html:option>
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
					<logic:empty name="rs">
					    <h3 class="datetitle_01">
					    <span>
					    	查询结果&nbsp;&nbsp;
								<font color="red">未找到任何记录！</font> 
					    </span>
					    </h3>
					</logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序；已经审核过的信息不可以再被删除。</font> 
						</span>
					</h3>
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
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
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="showPjInfo()">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center">
											<!-- 判断该此维修是否已经审核过 -->
											<logic:iterate id="zt" name="s" offset="14" length="1">
												<logic:equal name="zt" value="未审核">
													<input type="checkbox" id="checkVal" name="primarykey_checkVal" 
														value="${v }" style=""/>
												</logic:equal>
												<logic:notEqual name="zt" value="未审核">
													<input type="checkbox" id="checkVal" name="primarykey_checkVal" 
														value="${v }" style="" disabled="disabled"/>
												</logic:notEqual>
											</logic:iterate>	
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1">
										<td align="left">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
					</div>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>