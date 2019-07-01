<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/sjy/jcsjsz.js"></script>
		<script language="javascript" defer="defer">
		jQuery(function(){
			var bmdm = jQuery('#bmdm');

			bmdm.combobox({
				valueField:'dm',
 				textField:'mc',
				url:'sjyJcsjcsh.do?method=setBmOption',
				data:{url:'sjyJcsjcsh.do?method=setBmOption'}
			});
			
			bmdm.combobox('setValue',$('search_bm').value);
		});
		
		//查询
		function searchRs(){
			allNotEmpThenGo('/xgxt/sjyJcsjcsh.do?method=jcsjcshManage');
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
				数据通过“数据导入”或者“接口同步”进入本系统临时表，然后操作“规则制定”，
				处理不符合规则的数据，完成后，操作“勾选提交”或者“整体提交”，将临时表的数据
				推向正式表，使其应用于整个学工系统	。
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/sjyJcsjcsh">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="hadRs" id="hadRs" value="${hadRs }"/>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<input type="hidden" id="search_bm" value="${bm }"/>
			<input type="hidden" name="importResult" id="importResult" value=""/>

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="impAndChkData();return false;"
									class="btn_dr">数据导入</a>
							</li>
							<li>
								<a href="#"
									onclick="tbData();return false;"
									class="btn_gx">接口同步</a>
							</li>
							<li>
								<a href="#"
									onclick="showRule();return false;"
									class="btn_sq">规则制定</a>
							</li>
							<li>
								<a href="#"
									onclick="submitBmInfo('choose');return false;"
									class="btn_sh">勾选提交</a>
							</li>
							<li>
								<a href="#"
									onclick="submitBmInfo('all');return false;"
									class="btn_shtg">整体提交</a>
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
											onclick="czSearchCond('zydm-zymc-bmdm');">
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
									专业代码
								</th>
								<td>
									<html:text property="zydm" styleId="zydm" style="width: 150px"
										onkeypress="if(pressEnter(event)){searchRs();return false;}" maxlength="20"/>
								</td>
								<th>
									专业名称
								</th>
								<td>
									<html:text property="zymc" styleId="zymc" style="width: 150px"
										onkeypress="if(pressEnter(event)){searchRs();return false;}" maxlength="20"/>
								</td>
								<th>
									上级院系
								</th>
								<td>
									<html:select property="bmdm" styleId="bmdm" style="width: 150px" title="可录入">
										
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end -->
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 477px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');changeCzxm('${xmnr.xmdm}');return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');changeCzxm('${xmnr.xmdm}');return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:notEqual>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
						<td width="80%" valign="top" style="position: relative;">
							<div id="dy_area" style="width:650px;overflow-x:auto;overflow-y:hidden;">
								<span class="formbox">
									<table class="dateline" width="100%">
										<!-- 标题 -->
								    	<thead>
											<tr>
												<td width="5px">
													<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
												</td>
												<logic:iterate id="tit" name="topTr" offset="0">
													<td>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
								      		</tr>
										</thead>
										<!-- 标题 end-->
										
										<!-- 内容 -->
										<logic:equal name="hadRs" value="yes">
								    	<tbody>
									    	<logic:iterate name="rsArrList" id="rs" indexId="index">
									    		<tr onclick="rowOnClick(this);">
									    			<td align="center">
														<input type="checkbox" id="checkVal" 
															   name="primarykey_checkVal"  
															   value="<logic:iterate id="v" name="rs" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
													</td>
									    			<logic:iterate id="v" name="rs">
														<td align="left" nowrap="nowrap">
															${v }
														</td>
													</logic:iterate>
												</tr>
									    	</logic:iterate>
										</tbody>
										</logic:equal>
										<!-- 补空行 -->
										<%@ include file="/comm/noRows.jsp"%>
										<!-- 补空行 end-->
									</table>
								</span>
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=sjyJcsjcshForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>