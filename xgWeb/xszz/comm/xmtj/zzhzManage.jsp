<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript"  src="js/xszz/xszzFunction.js"></script>
		<script language="javascript"  src="js/xszz/xszzComm.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
		function showXxInfo(){
		
			if(curr_row == null){
				alert('请选择要查看的数据！');
				return false;
			}
			
			var xh = curr_row.getElementsByTagName('input')[0].value;
			var kssj = $("kssj").value;
			var jssj = $("jssj").value;
			
			var url = "/xgxt/commXszz.do?method=zzhzUpdate";
				url+= "&xh="+xh;
				url+= "&kssj="+kssj;
				url+= "&jssj="+jssj;
				
			showTopWin(url,800,600);
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
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
<%--						<li>--%>
<%--							<a href="#" --%>
<%--								onclick="showXxInfo();" --%>
<%--								class="btn_ck">查看</a>--%>
<%--						</li>--%>
						<li>
							<a href="#" 
								onclick="wjcfDataExport('commXszz.do?method=zzhzManage&doType=exp');" 
								class="btn_dc">导出</a>
						</li>
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
										<button type="button"  class="btn_cx" id="search_go"
											onclick="if(checkSearchTj('kssj','jssj')){allNotEmpThenGo('/xgxt/commXszz.do?method=zzhzManage')};">
											统 计
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('nj-kssj-jssj-xy-zy-bj-xh-xm-xmdm-xmlb-xmmc');">
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
									年级	
								</th>
								<td>
									<html:select property="nj" style="" onchange="initZyList();initBjList()">
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
										<html:text property="xh" styleId="xh" style="" readonly="true"/>
									</logic:equal>
									<!-- 学生用户 -->
									<logic:notEqual name="userType" value="stu">
										<html:text property="xh" styleId="xh" style="" maxlength="20"/>
									</logic:notEqual>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="" maxlength="20"/>			
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									院系
								</th>
								<td>
									<!-- 是学院用户 -->
									<logic:equal name="isxy" value="true">
										<html:hidden property="xydm"/>
										<html:select property="xydm" style="width: 150px" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>	
									</logic:equal>
									<!-- 是学院用户 end -->
									<!-- 非学院用户 -->
									<logic:equal name="isxy" value="false">
										<html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
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
									<html:select property="zydm" style="width: 150px" styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width: 150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									项目类别	
								</th>
								<td>
									<html:select property="xmlb" style="" styleId="xmlb" onchange="chXmlb()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									项目
								</th>
								<td>
									<html:select property="xmdm" style="width: 150px" styleId="xmdm" onchange="">
										<html:options collection="xmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									项目名称
								</th>
								<td>
									<html:text property="xmmc" styleId="xmmc" style="" maxlength="20" />		
								</td>
							</tr>
							<!-- 第四行 -->
							<tr>
								<th>
									统计开始时间
								</th>
								<td>
									<html:text property="kssj" styleId="kssj" readonly="true"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('kssj','y-mm-dd');"/>	
								</td>
								<th>
									统计结束时间
								</th>
								<td>
									<html:text property="jssj" styleId="jssj" readonly="true"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('jssj','y-mm-dd');"/>	
								</td>
								<td colspan="2">
									
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
							<logic:notEmpty name="rsArrList">
								<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息.</font>
							</logic:notEmpty>
							<logic:empty name="rsArrList">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rsArrList">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" length="8">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<tbody>
								<logic:iterate name="rsArrList" id="rs" >
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
											ondblclick="showXxInfo()">
										<logic:iterate name="rs" id="v" offset="0" length="1">
											<td align="center">
												${v }
												<input type="hidden" id="checkVal" 
													name="primarykey_checkVal"  
													value="${v }">
											</td>
										</logic:iterate>
										<logic:iterate name="rs" id="v" offset="1" length="7">
											<td align="center">
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
							<!--内容 end-->
						</table>
					<!--分页-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
					<script type="text/javascript">
				      $('choose').className="hide";
				     </script>
					<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
		</html:form>
		<div id="tmpdiv1"></div>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		<!-- 提示信息 end-->
	</body>
</html>