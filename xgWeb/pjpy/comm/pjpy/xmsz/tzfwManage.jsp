<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
			<script>
		
		
		//显示帮助层信息
			function showHelpDiv(){

				if($("div_help")){
					if($("div_help").style.display == "none"){
						$("div_help").style.display = "";
					}else{
						$("div_help").style.display = "none";
					}
				}
				
			}
		</script>
	</head>

	<body>
		<html:form action="/pjpy_ty_tzfw" method="post">
			<input type="hidden" name="message" id="message" value="${message}"/>
			<input type="hidden" name="doType" id="doType" value="${doType}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优-评奖综合设置-调整设置</a>
				</p>
				
				<!-- 在线帮助 -->
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
				<!-- 在线帮助 end -->
				
				<!-- 相关功能 -->
				<p class="other" style="position:relative;">
					<a href="#" onclick="return false;" 
						onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
						style="display:block;height:30px;">相关功能</a>
				</p>
				<!-- 相关功能 end-->
			
			</div>
			
			<!-- 提示信息-->
			<div class="prompt">
				<h3>
					<span>评奖周期：</span>
				</h3>
				<p>
					评奖学年(${pjxtszModel.pjxn })&nbsp;&nbsp;
					评奖学期(${pjxtszModel.pjxqmc })&nbsp;&nbsp;
					评奖年度(${pjxtszModel.pjnd })&nbsp;&nbsp;
				</p>
			</div>
			<!-- 提示信息 end-->	
			
				<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.本模块用于设置代替的评奖项目，例如：某学生符合获得评奖"项目A",但是该项目由于审核通过人数达到了设置人数,这时候就可以把该学生名额调整到其它项目。注:该设置在最后一级审核时起作用。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_rssz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function25.png" />
							<p>评奖人数设置</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_tjsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function56.png" />
							<p>评奖条件设置</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_jdsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function71.png" />
							<p>项目兼得设置</p>
						</a>   	
					</div>
					
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_sjsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function60.png" />
							<p>时间设置</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->
			
			
			<div class="toolbox">
				<!-- 按钮 -->
			
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_sz"
									onclick="showTopWin('pjpy_ty_tzfw.do?method=xzxm',500,400)"
									id="btn_dr">设置</a>
							</li>
							<li>
								<a href="#" class="btn_xg"
									onclick="showUpdateWindow('primarykey_cbv','pjpy_ty_tzfw.do?method=tzfwsz&doType=update',500,400);"
									id="btn_qx">修改</a>
							</li>
							<li>
								<a href="#" class="btn_sc"
									onclick="batchData('primarykey_cbv','pjpy_ty_tzfw.do?method=tzfwManage&doType=del','del')"
									id="btn_qx">删除</a>
							</li>
						</ul>
					</div>
			
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('pjpy_ty_tzfw.do?method=tzfwManage&doType=query')">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									项目类型
								</th>
								<td>
									<html:select property="xmlx" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xmlxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									项目性质
								</th>
								<td>
									<html:select property="xmxz" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xmxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									项目范围
								</th>
								<td>
									<html:select property="xmfw" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									项目名称
								</th>
								<td>
									<html:text property="xmmc" style="width:155px"></html:text>
								</td>
								<th></th>
								<td></td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="${s.xmdm }" />
										</td>
										<td>
											${s.xmmc }
										</td>
										<td>
											${s.tzxmmc }
										</td>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=pjpyTzfwszForm"></jsp:include>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			
		</html:form>
	</body>
</html>
