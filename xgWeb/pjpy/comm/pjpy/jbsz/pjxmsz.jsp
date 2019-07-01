<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pjpy/comm/pjpy/jbsz/pjryqd.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/ryqdDWR.js"></script>
		<script language="javascript">
			function xmszWh(doType){
				showTopWin("pjpyXmsz.do?method=pjxmszOne&doType=add","800","600");
			}
			function xmszWhFlow(doType){
				showTopWin("pjpyXmsz.do?method=pjxmszFlow&doType=add","1000","650");
			}
			function saveSfqy(){
				var blog=false;
				if(confirm("确定要进行保存操作吗？")){
					refreshForm('pjpyXmsz.do?method=pjxmsz&doType=save');
				}
			}
			
			function changeSfqy(value,num){
				var id = "sfqy"+num;
				$(id).value = value;
			}
			
			
			function modi(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);
					return true;
				}else{
					alertInfo('请选择要修改的数据行！');
					return false;
				}
			}
			
			function showXmfz(){
				if($("xmfzdiv")){
					viewTempDiv('项目复制','xmfzdiv',220,230);
				}else{
					alertInfo("请选择要操作的记录！");
					return false;
				}
			}
			
			function fzlnxm(){
				if($("select_xn") && $("select_xn").value==""){
					alertInfo("需复制项目学年不能为空!");
					return false;
				}
				if($("select_xq") && $("select_xq").value==""){
					alertInfo("需复制项目学期不能为空!");
					return false;
				}
				if($("select_nd") && $("select_nd").value==""){
					alertInfo("需复制项目年度不能为空!");
					return false;
				}
				refreshForm('pjpyXmsz.do?method=pjxmsz&doType=fzxm');
			}
			
			function removeOption(){
				$("pjxn").options[0]=null;
				$("pjxq").options[0]=null;
				$("pjnd").options[0]=null;
			}
		</script>
	</head>
	<body onload="removeOption()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优-评奖综合设置-评奖项目设置</a>
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
		
		
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。<br/>
				<span id="div_help" style="display: none">
				1.本模块包括对评奖项目的增加、修改、删除和复制的功能。<br/>
				2.修改和删除的功能仅在选择的周期为当前评奖周期时才开放。
				</span>
			</p>
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
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_tzfwsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function24.png" />
							<p>项目调整范围设置</p>
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
		
		<!-- 标题 end-->
		<html:form action="/pjpyXmsz">
			<!-- 隐藏域 -->
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						     <li>
								<a href="#" onclick="xmszWhFlow('add')" class="btn_zj">增加</a>
							</li>
							<!--  <li>
								<a href="#" onclick="xmszWh('add')" class="btn_zj">增加</a>
							</li>-->
						<logic:present name="showDqpj">
							<li>
								<a href="#" onclick="modi('/xgxt/pjpyXmsz.do?method=pjxmszUpdate&doType=update')" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="dataBatch('/xgxt/pjpyXmsz.do?method=pjxmsz&doType=delete');"
									class="btn_sc">删除</a>
							</li>
						
<%--							<li>--%>
<%--								<a href="#" onclick="saveSfqy()" class="btn_ccg">保存</a>--%>
<%--							</li>--%>
						</logic:present>
							<li>
								<a href="#" onclick="showXmfz()" class="btn_fz">复制项目</a>
							</li>
						
					</ul>
				</div>

				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/pjpyXmsz.do?method=pjxmsz&doType=query')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
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
									评奖学年
								</th>
								<td>
									<html:select property="pjxn" styleId="pjxn" style="width: 150px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									评奖学期
								</th>
								<td>
									<html:select property="pjxq" styleId="pjxq" style="width: 150px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									评奖年度
								</th>
								<td >
									<html:select property="pjnd" styleId="pjnd" style="width: 150px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									项目类型
								</th>
								<td >
									<html:select property="xmlx" styleId="xmlx" style="width: 150px">
										<html:option value=""></html:option>
										<html:options collection="xmlxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									项目性质
								</th>
								<td>
									<html:select property="xmxz" styleId="xmxz"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xmxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									项目范围
								</th>
								<td>
									<html:select property="xmfw" styleId="xmfw"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									项目代码
								</th>
								<td>
									<html:text property="xmdm" styleId="xmdm" style="width: 150px"/>
								</td>
								<th>
									项目名称
								</th>
								<td align="left">
									<html:text property="xmmc" styleId="xmmc" style="width: 150px"/>
								</td>
								<th align="right">
									英文名称
								</th>
								<td align="left">
									<html:text property="ywmc" styleId="ywmc" style="width: 150px"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" style="display:none" />
									</td>
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
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<td nowrap="nowrap">
											<input type="checkbox" name="primarykey_cbv" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
											<input type="hidden" name="xmdms" id="xmdms"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="9">
											<td align="left"  nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										
										<!-- 是否启用 -->
										<logic:iterate id="v" name="s" offset="10" length="1">
											<td align="left"  nowrap="nowrap">
												<logic:iterate name="sfqyList" id="sfqy">
													${sfqy.en }
													<logic:equal name="sfqy" property="en" value="${v }">
														<input type="radio" value="${sfqy.en }" name="qy${index }"
															checked="checked" onclick="changeSfqy('${sfqy.en }','${index }')"/>
													</logic:equal>
													<logic:notEqual name="sfqy" property="en" value="${v }">
														<input type="radio" value="${sfqy.en }" name="qy${index }" 
															onclick="changeSfqy('${sfqy.en }','${index }')"/>
													</logic:notEqual>

												</logic:iterate>
												<input type="hidden" name="sfqy" id="sfqy${index }" value="${v }" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
							<!--内容 end-->
						</table>
						</div>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=pjpyXmszForm"></jsp:include>

						<!--分页end-->
				</logic:notEmpty>
			</div>
			<!-- 查询结果 end-->
			
			
			<!-- 隐藏域 -->
			<div id="xmfzdiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>需复制项目所<br/>在评奖学年：
								</th>
								<td>
									<html:select property="select_xn" styleId="select_xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>需复制项目所<br/>在评奖学期：
								</th>
								<td>
									<html:select property="select_xq" styleId="select_xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>需复制项目所<br/>在评奖年度：
								</th>
								<td>
									<html:select property="select_nd" styleId="select_nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan='2' align='right'>
									<div class="bz">"<font color="red">*</font>"为必填项</div>
									<button type="button" onclick='fzlnxm()'>
										保存
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<%@ include file="/comm/delMessage.jsp"%>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alertInfo('操作成功！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
