<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//显示设置向导页面
		function showSzGuide(){
		
			var url = "/xgxt/sjyJcsjsz.do?method=jcsjszGuide";
				url+= "&step=step1";
				
			showTopWin(url,800,650);
		}
		
		//显示设置修改页面
		function showSzUpdate(){
		
			if(curr_row != null){
			
				var zd = curr_row.getElementsByTagName('input')[0].value;
				var url = "/xgxt/sjyJcsjsz.do?method=jcsjszUpdate";
					url+= "&zd="+zd;
					
				showTopWin(url,480,450);
			}else{
				alert("请选择需要设置的字段^_^");
				return false;
			}
		}
		
		//创建存储过程
		function createProcedure(){
			if (confirm("将要根据设置创建存储过程，请确认设置。\n注：影响存储过程的设置主要为“学工为准”")) {
				saveUpdate('/xgxt/sjyJcsjsz.do?method=jcsjszManage&doType=create','');
			}
		}
		
		//查询
		function searchRs(){
			allNotEmpThenGo('/xgxt/sjyJcsjsz.do?method=jcsjszManage');
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				如果你是第一次进行该功能模块的设置，请点击“设置向导”功能，方便你迅速了解该功能。<br/>	
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/sjyJcsjsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="hadRs" id="hadRs" value="${hadRs }"/>

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showSzGuide();return false;"
									class="btn_yl">设置向导</a>
							</li>
							<li>
								<a href="#"
									onclick="showSzUpdate();return false;"
									class="btn_sz">字段设置</a>
							</li>
							<li>
								<a href="#"
									onclick="createProcedure();return false;"
									class="btn_sx">创建存储过程</a>
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
										
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('sjy-xgwz-lrxz-wkxz-lrxs-sfqy-zdm-ymxs-xsmc');">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
<%--								<th>--%>
<%--									数据源--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:select property="search_sjy" style="width: 150px" styleId="sjy">--%>
<%--										<html:option value=""></html:option>--%>
<%--										<html:options collection="jbszSjyList" property="en" labelProperty="cn" />--%>
<%--									</html:select>--%>
<%--								</td>--%>
								<th>
									显示名称
								</th>
								<td>
									<html:text property="search_ymxs" style="" maxlength="20" styleId="ymxs"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>
								<th>
									以学工为准
								</th>
								<td>
									<html:select property="search_xgwz" style="width: 150px" styleId="xgwz">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									录入限制
								</th>
								<td>
									<html:select property="search_lrxz" style="width: 150px" styleId="lrxz">
										<html:option value=""></html:option>
										<html:options collection="jbszLrxzList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									可否为空
								</th>
								<td>
									<html:select property="search_wkxz" style="width: 150px" styleId="wkxz">
										<html:option value=""></html:option>
										<html:options collection="jbszWkxzList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									录入形式
								</th>
								<td>
									<html:select property="search_lrxs" style="width: 150px" styleId="lrxs">
										<html:option value=""></html:option>
										<html:options collection="jbszLrxsList" property="en" labelProperty="cn" />
									</html:select>		
								</td>
								<th>
									是否启用
								</th>
								<td>
									<html:select property="search_sfqy" style="width: 150px" styleId="sfqy">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
<%--							<!-- 第三行 -->--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									字段名--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:text property="search_zdm" style="" maxlength="20" styleId="zdm"--%>
<%--										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>					--%>
<%--								</td>--%>
<%--								<th>--%>
<%--									--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									--%>
<%--								</td>--%>
<%--								<th>--%>
<%--									--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									--%>
<%--								</td>--%>
<%--							</tr>--%>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end -->
			
			<!-- 查询结果-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue">页面显示如果为空，将以字段名为准</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr" offset="1" length="9">
								<td id="<bean:write name="tit" property="en"/>"
<%--									onclick="tableSort(this)" --%>
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left">
										${v }
										<input type="hidden" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--内容 end-->
					<!-- 补空行 -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- 补空行 end-->
				</table>
				</div>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=sjyJcsjszForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>