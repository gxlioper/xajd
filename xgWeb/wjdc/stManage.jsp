<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getWjdcDAO.js'></script>
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
								<a href="#" class="btn_zj" onclick="addSt()" id="btn_zj">增加</a>
							</li>
							<li>
								<a href="#" class="btn_xg" onclick="showSt('update')" id="btn_xg">修改</a>
							</li>
							<li>
								<a href="#" class="btn_sc" onclick="delSt()" id="btn_sc">删除</a>
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
											onclick="searchSt();">
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
								<th>试题类型</th>
								<td>
									<html:select property="queryequals_stlx" style="width:120px" styleId="stlx">
											<html:options collection="stlxList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th>试题所属</th>
								<td>
									<html:select property="queryequals_stss" style="width:120px" styleId="stss">
											<html:options collection="stssList" property="dm" labelProperty="mc" />
											<html:option value="其他">其他</html:option>
										</html:select>
								</td>
								<th>标准答案</th>
								<td>
									<html:select property="queryequals_haveda" style="width:120px" styleId="haveda">
											<html:options collection="ywlxList" property="en" labelProperty="cn" />
										</html:select>
								</td>
							</tr>
							<tr>
								<th>模块类型</th>
								<td>
									<html:hidden property="queryequals_mklx"/>
									<html:select property="queryequals_mklx"style="width:120px" styleId="mklx" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="mklxList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>试题编号</th>
								<td><html:text property="querylike_stbh" styleId="stbh" style="width:115px" maxlength="4"/></td>
								<th>建立时间</th>
								<td>
									<html:text property="querygreaterequal_jlsj" styleId="querygreaterequal_jlsj"
											onblur="dateFormatChg(this)" 
											onclick="return showCalendar('querygreaterequal_jlsj','y-mm-dd');"/>	
										—
										<html:text property="querylessequal_jlsj" styleId="querylessequal_jlsj"
											onblur="dateFormatChg(this)" 
											onclick="return showCalendar('querylessequal_jlsj','y-mm-dd');"/>	
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
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="showSt('view')">								
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align="center">
												<input type="checkbox" id="checkVal" 
												   name="primarykey_checkVal"  
												   value="<bean:write name="v"/>"/>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
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
