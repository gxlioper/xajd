<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/zdsz.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript" defer="defer">
		
		//页面初始化
		function onShow(){
			
			//学工为准
			showXgwz();
			//设置特殊字段
			setTszd();
			//来源表
			showLybList();
		}
		
		//显示视图操作DIV
		function showViwDiv(){
			viewTempDiv("视图操作","viewDiv",300,100);
		}
		
//设置特字段
function setTszd(){
	var num = document.getElementsByName("zd").length;
	var notNull = ["xh","nj","xydm","zydm","bjdm"];
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("zd")[i];
		var rownum = obj.id.replace("zd","");
		 
		var zd = obj.value;	
		
		for(var j=0;j<notNull.length;j++){
			if(zd == notNull[j]){
				setWkxz(rownum);
			}
		}
		
		setQt(zd,rownum);
	}
}

//设置为空限制
function setWkxz(num){
	var id = "wkxz"+num;
	if($(id)){
		for(var i = 0 ; i <$(id).options.length; i++){
			if($(id).options[i].value == "可以为空"){
				$(id).options[i] = null;
				break;
			}
		}
	}
}

//设置其他字段特殊格式
function setQt(zd,num){

	var bm_arr = ["nj","xydm","zydm","bjdm"];
	var qx_arr = ["syd","jg","hkszd"];
	
	for(var j=0;j<bm_arr.length;j++){
		if(zd == bm_arr[j]){
			var lrxs_id = "lrxs"+num;
			DWRUtil.removeAllOptions(lrxs_id);
			var options = [{dm:"特殊格式",mc:"特殊格式"}];
			DWRUtil.addOptions(lrxs_id,options,"dm","mc");
		}
	}
	
	var num_id = "hid_lybnum_"+num;
	
	if($(num_id).value == 0){
	
		var lrxs_id = "lrxs"+num;
		for(var i = $(lrxs_id).options.length-1 ; i >=0; i--){
			if($(lrxs_id).options[i].value == "下拉列表" || $(lrxs_id).options[i].value == "单选框"){
				$(lrxs_id).options[i] = null;
			}
		}
	}else{
		var lrxs_id = "lrxs"+num;
		for(var i = $(lrxs_id).options.length-1 ; i >=0; i--){
			if($(lrxs_id).options[i].value == "输入框" || $(lrxs_id).options[i].value == "时间格式" || $(lrxs_id).options[i].value == "文本域"){
				$(lrxs_id).options[i] = null;
			}
		}
	}
	
	for(var j=0;j<qx_arr.length;j++){
		if(zd == qx_arr[j]){
			var lrxs_id = "lrxs"+num;
			DWRUtil.removeAllOptions(lrxs_id);
			var options = [{dm:"输入框",mc:"输入框"},{dm:"特殊格式",mc:"特殊格式"}];
			DWRUtil.addOptions(lrxs_id,options,"dm","mc");
		}
	}
	
	var lrxs_select = $("hid_lrxs_"+num).value;
	$(lrxs_id).value = lrxs_select;
}
		</script>
	</head>
	<body onload="onShow()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/xsxxJbsz">
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
									onclick="saveZdsz();return false;"
									class="btn_ccg">保存</a>
							</li>
							<li>
								<a href="#"
									onclick="showPlszDiv();return false;"
									class="btn_sz">设置</a>
							</li>
							<li>
								<a href="#"
									onclick="creatNewView();return false;"
									class="btn_sx">创建视图</a>
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
								<th>
									数据源
								</th>
								<td>
									<html:select property="search_sjy" style="width: 150px" styleId="sjy">
										<html:option value=""></html:option>
										<html:options collection="jbszSjyList" property="en" labelProperty="cn" />
									</html:select>
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
							<!-- 第三行 -->
							<tr>
								<th>
									字段名
								</th>
								<td>
									<html:text property="search_zdm" style="" maxlength="20" styleId="zdm"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>					
								</td>
								<th>
									显示名称
								</th>
								<td>
									<html:text property="search_ymxs" style="" maxlength="20" styleId="ymxs"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
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
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
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
								<td align="center" width="5px">
									<input type="checkbox" id="checkVal" 
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- 字段 -->
								<logic:iterate id="v" name="s" offset="0" length="1">
									<td align="left" style="display: none">
										${v }
										<input type="hidden" name="zd" id="zd${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- 字段名 -->
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left" nowrap="nowrap">
										${v }
										<input type="hidden" name="zdm" id="zdm${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- 页面显示 -->
								<logic:iterate id="v" name="s" offset="2" length="1">
									<td align="left" nowrap="nowrap">
										<input type="text" name="xsmc" id="xsmc${index }" value="${v }" style="width: 70px" maxlength="25"/>
									</td>
								</logic:iterate>
								<!-- 数据源 -->
								<logic:iterate id="v" name="s" offset="3" length="1">
									<td align="left" width="60px" nowrap="nowrap">
										<select name="sjy" id="sjy${index }" style="width: 60px" onchange="selectSjy('${index }')">
											<logic:iterate name="jbszSjyList" id="sjyOption">
												<logic:equal name="sjyOption" property="en" value="${v }">
													<option value="${sjyOption.en }" selected="selected">${sjyOption.cn }</option>
												</logic:equal>
												<logic:notEqual name="sjyOption" property="en" value="${v }">
													<option value="${sjyOption.en }">${sjyOption.cn }</option>
												</logic:notEqual>
											</logic:iterate>	
										</select>
									</td>
								</logic:iterate>
								<!-- 是否学工为准 -->
								<logic:iterate id="v" name="s" offset="4" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="是">
											<input type="radio" name="xgwz${index }" value="是" onclick="clickRadioValue(${index },'xgwz',this.value)" checked="checked"/>是
											<input type="radio" name="xgwz${index }" value="否" onclick="clickRadioValue(${index },'xgwz',this.value)"/>否
										</logic:equal>
										<logic:equal name="v" value="否">
											<input type="radio" name="xgwz${index }" value="是" onclick="clickRadioValue(${index },'xgwz',this.value)"/>是
											<input type="radio" name="xgwz${index }" value="否" onclick="clickRadioValue(${index },'xgwz',this.value)" checked="checked"/>否
										</logic:equal>
										<input type="hidden" name="xgwz" id="hid_xgwz_${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- 录入限制 -->
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td align="left" width="150px" nowrap="nowrap">
										<select name="lrxz" id="lrxz${index }" style="width: 150px">
											<logic:iterate name="jbszLrxzList" id="lrxzOption">
												<logic:equal name="lrxzOption" property="en" value="${v }">
													<option value="${lrxzOption.en }" selected="selected">${lrxzOption.cn }</option>
												</logic:equal>
												<logic:notEqual name="lrxzOption" property="en" value="${v }">
													<option value="${lrxzOption.en }">${lrxzOption.cn }</option>
												</logic:notEqual>
											</logic:iterate>	
										</select>
									</td>
								</logic:iterate>
								<!-- 为空限制 -->
								<logic:iterate id="v" name="s" offset="6" length="1">
									<td align="left" width="80px" nowrap="nowrap">
										<select name="wkxz" id="wkxz${index }" style="width: 80px">
											<logic:iterate name="jbszWkxzList" id="wkxzOption">
												<logic:equal name="wkxzOption" property="en" value="${v }">
													<option value="${wkxzOption.en }" selected="selected">${wkxzOption.cn }</option>
												</logic:equal>
												<logic:notEqual name="wkxzOption" property="en" value="${v }">
													<option value="${wkxzOption.en }">${wkxzOption.cn }</option>
												</logic:notEqual>
											</logic:iterate>	
										</select>
									</td>
								</logic:iterate>
								<!-- 录入形式 -->
								<logic:iterate id="v" name="s" offset="7" length="1">
									<td align="left" width="width: 100px" nowrap="nowrap">
										<select name="lrxs" id="lrxs${index }" style="width: 80px" onchange="setLybList('${index }')">
											<logic:iterate name="jbszLrxsList" id="lrxsOption">
												<logic:equal name="lrxsOption" property="en" value="${v }">
													<option value="${lrxsOption.en }" selected="selected">${lrxsOption.cn }</option>
												</logic:equal>
												<logic:notEqual name="lrxsOption" property="en" value="${v }">
													<option value="${lrxsOption.en }">${lrxsOption.cn }</option>
												</logic:notEqual>
											</logic:iterate>
										</select>
										<input type="hidden" id="hid_lrxs_${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- 数据来源表 -->
								<logic:iterate id="v" name="s" offset="8" length="1">
									<td align="left" nowrap="nowrap">
										<select name="lyb" id="lyb${index }" style="width: 130px">
											<option value="">请先选择录入形式</option>
										</select>
									</td>
								</logic:iterate>
								<!-- 是否启用 -->
								<logic:iterate id="v" name="s" offset="9" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="是">
											<input type="radio" name="sfqy${index }" value="是" onclick="clickRadioValue(${index },'sfqy',this.value)" checked="checked"/>是
											<input type="radio" name="sfqy${index }" value="否" onclick="clickRadioValue(${index },'sfqy',this.value)"/>否
										</logic:equal>
										<logic:equal name="v" value="否">
											<input type="radio" name="sfqy${index }" value="是" onclick="clickRadioValue(${index },'sfqy',this.value)"/>是
											<input type="radio" name="sfqy${index }" value="否" onclick="clickRadioValue(${index },'sfqy',this.value)" checked="checked"/>否
										</logic:equal>
										<input type="hidden" name="sfqy" id="hid_sfqy_${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- 是否有来源表 -->
								<logic:iterate id="v" name="s" offset="10" length="1">
									<td align="left" nowrap="nowrap" style="display: none">
										<input type="text" name="lybnum" id="hid_lybnum_${index }" value="${v }"/>
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxJbszForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
			<div id="tmpdiv1" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>设置内容（注：需要执行保存操作才起作用）</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">页面显示</th>
								<td>
									<input type="radio" name="sz_ymxs" value="不处理" onclick="setPlszHiddenValue('ymxs',this.value)" checked="checked"/>不处理
									<input type="radio" name="sz_ymxs" value="同字段名" onclick="setPlszHiddenValue('ymxs',this.value)"/>同字段名
									<input type="hidden" id="sz_ymxs_value" value="不处理"/>
								</td>
							</tr>
							<tr>
								<th>数据源</th>
								<td>
									<input type="radio" name="sz_sjy" value="不处理" onclick="setPlszHiddenValue('sjy',this.value)" checked="checked"/>不处理
									<input type="radio" name="sz_sjy" value="学工" onclick="setPlszHiddenValue('sjy',this.value)"/>学工
									<input type="radio" name="sz_sjy" value="接口" onclick="setPlszHiddenValue('sjy',this.value)"/>接口
									<input type="hidden" id="sz_sjy_value" value="不处理"/>
								</td>
							</tr>
							<tr>
								<th>以学工为准</th>
								<td>
									<input type="radio" name="sz_xgwz" value="不处理" onclick="setPlszHiddenValue('xgwz',this.value)" checked="checked"/>不处理
									<input type="radio" name="sz_xgwz" value="是" onclick="setPlszHiddenValue('xgwz',this.value)"/>是
									<input type="radio" name="sz_xgwz" value="否" onclick="setPlszHiddenValue('xgwz',this.value)"/>否
									<input type="hidden" id="sz_xgwz_value" value="不处理"/>
								</td>
							</tr>
							<tr>
								<th>录入限制</th>
								<td>
									<select name="sz_lrxz" id="select_sz_lrxz" onchange="setPlszHiddenValue('lrxz',this.value)">
										<option value="不处理">不处理</option>
										<logic:iterate name="jbszLrxzList" id="lrxzOption">
											<option value="${lrxzOption.en }">${lrxzOption.cn }</option>
										</logic:iterate>	
									</select>
									<input type="hidden" id="sz_lrxz_value" value="不处理"/>
								</td>
							</tr>
							<tr>
								<th>为空限制</th>
								<td>
									<input type="radio" name="sz_wkxz" value="不处理" onclick="setPlszHiddenValue('wkxz',this.value)" checked="checked"/>不处理
									<input type="radio" name="sz_wkxz" value="可以为空" onclick="setPlszHiddenValue('wkxz',this.value)"/>可以为空
									<input type="radio" name="sz_wkxz" value="不可为空" onclick="setPlszHiddenValue('wkxz',this.value)"/>不可为空
									<input type="hidden" id="sz_wkxz_value" value="不处理"/>
								</td>
							</tr>

							<tr>
								<th>录入形式</th>
								<td>
									<input type="radio" name="sz_lrxs" value="不处理" onclick="setPlszHiddenValue('lrxs',this.value)" checked="checked"/>不处理
									<input type="radio" name="sz_lrxs" value="输入框" onclick="setPlszHiddenValue('lrxs',this.value)"/>输入框
									<input type="hidden" id="sz_lrxs_value" value="不处理"/>
								</td>
							</tr>
							<tr>
								<th>是否启用</th>
								<td>
									<input type="radio" name="sz_sfqy" value="不处理" onclick="setPlszHiddenValue('sfqy',this.value)" checked="checked"/>不处理
									<input type="radio" name="sz_sfqy" value="是" onclick="setPlszHiddenValue('sfqy',this.value)"/>是
									<input type="radio" name="sz_sfqy" value="否" onclick="setPlszHiddenValue('sfqy',this.value)"/>否
									<input type="hidden" id="sz_sfqy_value" value="不处理"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">

									<button type="button" onclick="setPlsz()" id="buttonSave">
										确 定
									</button>
									
									<button type="button" onclick="hiddenMessage(true,true);return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 提示信息 -->
			<logic:present name="message">
				<logic:equal name="message" value="需要">
					<script defer="defer">
						$("message").value = "";
						$("doType").value = "";
						
						if (confirm("保存成功，是否要进入下一步设置学生信息页面的字段设置？")) {
							refreshForm("xsxx_jbsz_ymjbsz.do");
						}
					</script>
				</logic:equal>
				<logic:notEqual name="message" value="需要">
					<%@ include file="/comm/other/tsxx.jsp"%>
				</logic:notEqual>
			</logic:present>	
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>