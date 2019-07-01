<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="tips" scope="request" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/ssyd" method="post">
			<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="dxq" name="dxq"
						value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="request"/>" />			
			<input type="hidden" name="lcV" id="lcV" value=""/>
			<input type="hidden" name="qshV" id="qshV" />
			<input type="hidden" name="zyV" id="ztV" />
			<input type="hidden" name="bjV" id="bjV" />	
			<input type="hidden" name="tjxm" value="宿舍异动信息" id="tjxm"/>	
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="canWrite">
							<li>
								<a href="#"
									onclick="viewMore('add')"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="viewMore('modi')"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="viewMore('del')"
									class="btn_sc">删除</a>
							</li>		
						</logic:equal>
						<logic:equal name="xxdm" value="11052">
							<li>
								<a href="#" 
									onclick="expTab('rsTable',document.forms[0].tjxm.value)"
									class="btn_dc">生成报表</a>
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
										<button type="button" class="btn_cx" id="search_go"
											onclick="if(checkSearchTj('gzkssj','gzjssj')){refreshForm('/xgxt/ssyd.do?go=go')}">
											查询
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
									<html:select property="nj" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>											
								</td>
								<th>
									学年
								</th>
								<td>
									<html:select  property="xn" style="width:90px" styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td colspan="3">
									<html:select  property="xq" style="width:90px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>	
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									院系								
								</th>
								<td>
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>										
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td colspan="3">
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									学号								
								</th>
								<td>
									<html:text  property="xh" style="width:80px" styleId="xh" maxlength="20"/>									
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text  property="xm" style="width:80px" styleId="xm" maxlength="20"/>
								</td>
								<th>
									异动日期(开始)
								</th>
								<td colspan="3">
									<html:text property="gzkssj" styleId="gzkssj"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('gzkssj','y-mm-dd');"
										style="cursor:hand ;width:80px" />
								—
									<html:text property="gzjssj" styleId="gzjssj"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('gzjssj','y-mm-dd');"
										style="cursor:hand ;width:80px" />
								</td>
							</tr>
							<logic:present name="showhzy">
							<!-- 第四行 -->
							<tr>
								<th>
									校区名						
								</th>
								<td>
									<html:select property="xqdm" style="width:120px" onchange="refreshForm('/xgxt/ssyd.do')">
										<html:option value=""></html:option>
										<html:options collection="xiaoqquList" property="dm"
											labelProperty="xqmc" />
									</html:select>							
								</td>
								<th>
									异动后楼栋名
								</th>
								<td>
									<html:select property="lddm" style="width:120px" styleId="lddm" onchange="getLcList()">										
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>	
								</td>
								<th>
									异动后楼层
								</th>
								<td>
									<html:select property="lc" style="width:80px" styleId="lc" onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
										labelProperty="mc" />
									</html:select>
								</td>
								<th>
									异动后寝室号
								</th>
								<td>
									<html:select property="qsh" style="width:80px" styleId="qsh">
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							</logic:present>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:notEmpty name="rs">
								<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息.</font>
							</logic:notEmpty>
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>								
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%" id="rsTable">
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
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);"
									style="cursor:hand" ondblclick="viewMore('view')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	

		</html:form>		
  </body>
</html>
