<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="tips" scope="request" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/data_search" method="post">
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

			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
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
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">
								<logic:present name="showCsmzzyjsxy">
								报表
								</logic:present>	
								<logic:notPresent name="showCsmzzyjsxy">
								导出数据
								</logic:notPresent>
							</a>
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
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/daily_note_search.do?act=dailyNote')">
											查询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<td>
								&nbsp;&nbsp; 校区名：
								<html:select property="xqdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="xqmc" />
								</html:select>
								<logic:notPresent name="visible">
								&nbsp;&nbsp; 楼栋名:
								<html:select property="lddm" style="width:100px">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
								</logic:notPresent>
								<logic:notPresent name="showZjjj">
								&nbsp;&nbsp;值班人员：
								<html:select property="zbrydm" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="zbryList" property="zbrydm"
										labelProperty="zbrymc" />
								</html:select>
								
								<logic:present name="showCsmzzyjsxy">
								&nbsp;&nbsp;值班人类型：
								<html:select property="zbrlx" style="width:70px">
									<html:option value=""></html:option>
									<html:options collection="zbrLxList" property="en"
										labelProperty="cn" />
								</html:select>
								</logic:present>
								</logic:notPresent>
								<logic:present name="showZjjj">
								&nbsp;&nbsp;值班人员：
								<html:text property="zbrydm"></html:text>
								</logic:present>	
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
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
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
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="viewMore('view')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						 <script type="text/javascript">
					      $('choose').className="hide";
					     </script>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
		</html:form>
	</body>
</html>
