<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
			<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>

	<body >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="tips" scope="request" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/wmqspb" method="post">
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
		    <input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="request"/>" />
				<input type="hidden" name="lcV" id="lcV" value=""/>
			<input type="hidden" name="qshV" id="qshV" />
								<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
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
							<li>
								<a href="#" 
									onclick="impAndChkData();"
									class="btn_dr">导入</a>
							</li>		
						</logic:equal>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">导出</a>
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
											onclick="refreshForm('/xgxt/wmqspb.do?go=go')">
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
									学年
								</th>
								<td>
									<html:select  property="xn"  styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>										
								</td>
								<th>
									学期
								</th>
								<td  colspan="5">
									<html:select  property="xq"  styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									楼栋名
								</th>
								<td>
									<html:select property="lddm"  styleId="lddm" onchange="getLcList()">
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
								</td>
								<th>
									楼层
								</th>
								<td>
									<html:select property="lc" styleId="lc" onchange="getQshList2()">
										<html:options collection="lcList" property="dm" labelProperty="mc" />	
									</html:select>	
								</td>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh"  styleId="qsh">
										
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>										
								</td>
								<th>
									<logic:equal name="xxdm" value="10491">
									寝室类别
									</logic:equal>
								</th>
								<td>
									<logic:equal name="xxdm" value="10491">
									<html:select property="lbmc" style="width:110px">	
										<html:option value=""></html:option>								
										<html:options collection="qslbList" property="lbmc" labelProperty="lbmc" />
									</html:select>
									</logic:equal>
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									年级	
								</th>
								<td>
									<html:select property="nj" 
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>								
								</td>
								<th>
									院系
								</th>
								<td>
									<html:select property="xydm" styleId="xy" style="width: 100px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width: 100px"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>	
								</td>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width: 100px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
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
