<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//前往我的评奖
		function goMypj(){
			var url = "pjpy_szgyyq_mypj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		function searchRs(doType){
			if("jsfspm"==doType){
				showWaitingDiv("30000");
				allNotEmpThenGo('/xgxt/pjpy_szgyyq_zhcp.do?doType='+doType);
			}else if(checkSearch()){
				allNotEmpThenGo('/xgxt/pjpy_szgyyq_zhcp.do');
			}
		}

		function jszhf(){
			tipsWindown("条件选择","id:tempDiv","375","225","true","","true","id");
		}
		
		//检验可否查询
		function checkSearch(){
		
			var flag = true;
			//var is_default = $("is_default").value;
			
			//if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;
				
				if(xn_num != "1"){
					alertError("学年条件不可为空，且只能查询一个！");
					flag = false;
				}else if( xq_num != "1"){
					alertError("学期条件不可为空，且只能查询一个！");
					flag = false;
				}
			//}
			return flag;
		}

		function exportZhcpBjhzb(){

			setSearchTj(); 
			var bj_num =  jQuery("a[name=a_name_bj]").length;
			
			if(bj_num != "1"){
				alertError("班级不可为空，且只能导出一个！");
				return false;
			}
			var url = "/xgxt/pjpy_szgyyq_zhcp.do?doType=export";
		 	document.forms[0].action = url;
		 	document.forms[0].target = "_blank";
		 	document.forms[0].submit();
		 	document.forms[0].target = "_self";
		}
		
		//前往我的评奖
		function goMypj(){
			var url = "pjpy_szgyyq_mypj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		</script>
	</head>
	<body>
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>综合素质养成课 - 我的工作 - 综合测评</a>
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
				2.可通过点击<font color="blue">计算综合分</font>来计算学生的综合分，系统会在每一个月的一号凌晨自动计算。</br>
				3.可通过点击<font color="blue">导出班级汇总表</font>来导出选中班级分数的汇总情况。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
	
	
	
		<html:form action="/szgyyq_mypj_tea" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
<%--			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>--%>
			<!-- 隐藏域 -->
			<!-- 提示信息 end-->	
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 返回begin -->
						<li>
							<a href="#" onclick="goMypj();return false;" class="btn_fh">
								返回
							</a>
						</li>
						<!-- 返回end -->
						<logic:equal value="admin" name="userType">
							<li><a href="#" onclick="jszhf();return false;" class="btn_zj"> 计算综合分 </a></li>
						</logic:equal>
						<logic:equal value="xx" name="userType">
							<li><a href="#" onclick="jszhf();return false;" class="btn_zj"> 计算综合分 </a></li>
						</logic:equal>
						
						<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">导出数据</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出字段确认</a></li>
						<li><a href="#" class="btn_dc" onclick="exportZhcpBjhzb();return false;">导出班级汇总表</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序</font>
						</span>
					</h3>
					<div class="con_overlfow" style="min-height: 230px;overflow-x:auto;overflow-y:hidden;">
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="pk" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" length='11'>
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<logic:iterate id="v" name="s">
										<td><nobr>${v }</nobr></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
					</logic:notEmpty>
				</table>
				</div>
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzgyyqTeaForm"></jsp:include>
		   	 	<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">请选择综合分计算范围</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj" styleClass="select"
										onchange="initZyList();initBjList()" >
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<html:select property="xydm" onchange="initZyList();initBjList()"
										style="width:180px"  styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()" 
										style="width:180px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
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
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="计算"  onclick="searchRs('jsfspm')">
											计 算 
										</button>
										<button type="button" name="取消"  onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
		</html:form>
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>