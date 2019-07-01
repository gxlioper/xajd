<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getWjdcDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
		<script language="javascript"  src="js/wjdc/wjdc.js"></script>
		<script language="javascript"  src="js/wjdc/wjdcMk.js"></script>
	</head>
	<body onload="">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/wjdc" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<!-- 按钮 -->
					<logic:equal value="yes" name="writeAble">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" class="btn_gx" onclick="hdwj()" id="btn_gx">答卷</a>
								</li>
							</ul>
						</div>
					</logic:equal>
					<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="searchHd()">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>学年</th>
								<td>
									<html:select property="queryequals_xn" style="width:120px" styleId="xn" onchange="getWjList()">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>年度</th>
								<td>
									<html:select property="queryequals_nd" style="" styleId="nd" onchange="getWjList()">
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select>
								</td>
								<th>学期</th>
								<td>
									<html:select property="queryequals_xq" style="" styleId="xq" onchange="getWjList()">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>模块类型</th>
								<td>
									<html:hidden property="queryequals_mklx"/>
									<html:select property="queryequals_mklx" style="" styleId="mklx" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="mklxList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>问卷</th>
								<td>
									<html:select property="queryequals_id" style="" styleId="id">
										<html:options collection="wjList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>组卷信息</th>
								<td>
									<html:select property="queryequals_zjxx" style="" styleId="zjxx">
										<html:option value="">----请选择----</html:option>
										<html:options collection="zzList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> 
					</span>
				</h3>
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td id="hdqk" onclick="tableSort(this)" nowrap>
									本人回答情况
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="showHd('view')">								
									<logic:iterate id="v" name="s" offset="1" length="1">
										<td align="center">
											<bean:write name="v"/>
											<input type="checkbox" id="checkVal" style="display : none" 
											   name="primarykey_checkVal"  
											   value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=wjdcForm"></jsp:include>
				<!--分页显示-->
				</logic:notEmpty>
			</div>
			</logic:empty>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- 提示信息 end-->
	</body>
</html>
