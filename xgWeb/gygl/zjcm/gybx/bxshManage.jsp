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
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/zjcmGygl.do?method=gybxSh','sh','800','600')"
									class="btn_sh">审核</a>
							</li>
						</logic:equal>
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
											onclick="if(checkSearchTj('querygreaterequal_bxsj','querylessequal_bxsj')){allNotEmpThenGo('/xgxt/zjcmGygl.do?method=bxshManage')}">
											查询
										</button>
										<button class="button2" style="" id="cz"
											onclick="czSearchCond('nj-xn-xq-nd-xy-zy-bj-xh-xm-xqdm-lddm-cs-qsh-querygreaterequal_bxsj-querylessequal_bxsj-sfsf-sfwg');">
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
									<html:select property="queryequals_nj" style="" styleId="nj" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>												
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="querylike_xh" styleId="xh" style="" maxlength="20"/>
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
								<td colspan="3">
									<html:text property="querygreaterequal_bxsj" styleId="querygreaterequal_bxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
										onclick="return showCalendar('querygreaterequal_bxsj','y-mm-dd');"/>	
									—
									<html:text property="querylessequal_bxsj" styleId="querylessequal_bxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
										onclick="return showCalendar('querylessequal_bxsj','y-mm-dd');"/>	
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align="" width="100%">
							<!-- 表头 -->
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
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="showInfo('/xgxt/zjcmGygl.do?method=gybxSh','view','800','600')">
									<logic:iterate id="v" name="s" offset="0" length="1">
									<td>
										<input type="checkbox" id="checkVal" name="checkVal" 
											value="${v }" style="display : none"/>
										<logic:iterate id="v" name="s" offset="1" length="1">
											${v }
										</logic:iterate>
									</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2">
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