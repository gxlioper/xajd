<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			if($("xn").value==""){
				alertInfo("请选择学年！");
				return false;
			}
			if($("xq").value==""){
				alertInfo("请选择学期！");
				return false;
			}
			if($("bjdm").value==""){
				alertInfo("请选择班级！");
				return false;
			}
			refreshForm('/xgxt/szgyyq_pjhz.do?method=cjhzManage&go=go');
		}

		 function export_list(){
			 if($("xn").value==""){
					alertInfo("请选择学年！");
					return false;
				}
				if($("xq").value==""){
					alertInfo("请选择学期！");
					return false;
				}
				if($("bjdm").value==""){
					alertInfo("请选择班级！");
					return false;
				}
				var url = "/xgxt/szgyyq_pjhz.do?method=cjhzManage&go=export";
			 	document.forms[0].action = url;
			 	document.forms[0].target = "_blank";
			 	document.forms[0].submit();
			 	document.forms[0].target = "_self";
				
		 }
		</script>
	</head>
	<body>
		<html:form action="/szgyyq_pjhz" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />
			<!-- 隐藏域 -->
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>学生综合素质养成课-统计分析-成绩汇总</a>
				</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc }</font>学期 展开的。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
			
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="export_list();return false;">导出数据</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
			<table width="100%">
				<tbody>
					<tr>
						<th>年级</th>
						<td>
							<html:select property="nj" styleId="nj" styleClass="select"
								onchange="initZyList();initBjList()" >
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>学年</th>
						<td>
							<html:select property="xn"  styleClass="select"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" styleClass="select"
								style="padding-left:80px" styleId="xq">								
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" onchange="initZyList();initBjList()"
								style="width:180px"  styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm" onchange="initBjList()" 
								style="width:180px" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>班级</th>
						<td>
							<html:select property="bjdm" 
								style="width:180px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
<%--						<input type="hidden" name="go" value="a" />--%>
						<button type="button" id="search_go" onclick="searchRs();return false;">
							查 询
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							重 置
						 </button>
						</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
				
				<!-- 过滤条件 end-->
			</div>
			
			
			
				<div class="formbox" style="height: 768px">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<logic:empty name="rs"><font color="red">没有相关统计信息！</font></logic:empty>
						</span>
					</h3>
					<div class="con_overlfow" style="minHeight: 400px;overflow-x:auto;">
					<table width="99%" id="rsTable" class="dateline">
						<thead align="center">
							<tr  align="center">
								<logic:present name="topTr">
									<logic:iterate id="s" name="topTr">
										<td>${s}</td>
									</logic:iterate>
								</logic:present>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
								<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="strs">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<logic:iterate name="strs" id="str">
										<td>${str }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
					</logic:notEmpty>
				</table>
				</div>
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
