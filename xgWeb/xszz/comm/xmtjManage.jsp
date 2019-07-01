<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
		
		//审核项目
		function shxm(){
			if(curr_row == null){
				alert('请选择要审核的项目！');
				return false;
			}
			
			var iskns = $('iskns').value;
			var xmdm = curr_row.getElementsByTagName('input')[0].value;
			var url = "/xgxt/commXszz.do?method=xmshManage&iskns="+iskns;
			url += "&xmdm="+xmdm;
			
			refreshForm(url);
		}
		
		//修改项目类别
		function chXmlb(){
			var url = "/xgxt/commXszz.do?method=xmtjManage";
			refreshForm(url);
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
		<html:form action="/commXszz">
			<!-- 隐藏域 -->
			<html:hidden property="iskns" styleId="iskns"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }"/>
			<input type="hidden" name="go" value="a" />
			<button type="button" class="button2" style="height:25px;display : none" id="search_go"
				onclick="allNotEmpThenGo('/xgxt/commXszz.do?method=xmtjManage')">
				隐藏按钮
			</button>
			<!-- 隐藏域 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" 
									onclick="shxm();"
									class="btn_sh">审核</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 按钮 end-->		
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									当前学年								
								</th>
								<td>
									<html:select property="xn" style="width:120px" styleId="xn" disabled="true">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>		
								</td>
								<th>
									当前年度
								</th>
								<td>
									<html:select property="nd" style="" styleId="nd" disabled="true">
										<html:options collection="ndList" property="nd" labelProperty="nd" />
									</html:select>
								</td>
								<th>
									当前学期
								</th>
								<td>
									<html:select property="xq" style="" styleId="xq" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									当前日期
								</th>
								<td>
									<html:text property="sqsjCn" disabled="true"/>
								</td>
								<!-- 困难生独立模块不要这个过滤条件 了 2011.7.12 qph -->
								<logic:notEqual value="yes" property="iskns" name="xszzTyForm">
									<th>
										项目类别
									</th>
									<td>
										<html:select property="xmlb" style="" styleId="xmlb" onchange="chXmlb()">
											<html:option value=""></html:option>
											<html:options collection="xmlbList" property="en" labelProperty="cn" />
										</html:select>
									</td>
								</logic:notEqual>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 可审核项目&nbsp;&nbsp; 
							<font color="red">（注：上级部门已经审核通过的学生将不在审核列表中出现，请在结果查询处进行查看）</font>
							<logic:empty name="rsList">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rsList">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>	
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							</tbody>
								<logic:iterate name="rsList" id="rs" indexId="index">
									<tr onclick="rowOnClick(this);" 
										style="cursor:hand" 
										ondblclick="">
										<td>
											${rs.xmmc }		
											<input type="hidden" name="xmdm" value="${rs.xmdm }"/>			
										</td>
										<td>
											${rs.xmlb }													
										</td>	
										<td>
											${rs.sqrs }													
										</td>	
										<td>
											${rs.xshrs }													
										</td>					
										<td>
											${rs.shrs }	
										</td>
									</tr>
								</logic:iterate>
							<!--内容 end-->
							</tbody>
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
							 <script type="text/javascript">
						      $('choose').className="hide";
						     </script>
						<!--分页end-->
					</logic:notEmpty>
					<!-- 查询结果 end-->
				</div>
			</div>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- 提示信息 end-->
	</body>
</html>