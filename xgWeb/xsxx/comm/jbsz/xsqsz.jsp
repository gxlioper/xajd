<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript" defer="defer">
		//查询结果
		function searchRs(){
			allNotEmpThenGo('/xgxt/xsxxJbsz.do?method=xsqsz');
		}
		
		//保存显示区设置
		function saveXsqsz(){
			var hadRs = $("hadRs").value;
			if(hadRs == "yes"){
				if (confirm("保存页面显示的显示区设置，请确认！")) {
					saveUpdate('/xgxt/xsxxJbsz.do?method=xsqsz&doType=save','');
				}
			}else{
				alert("请先查询出需要设置的显示区，再执行该操作！");
			}
		}
		
		//弹出批量设置层
		function showPlszDiv(){
		
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					flag = true;
					break;
				}
			}
			
			if(flag){
				viewTempDiv("显示区设置","xsqszDiv",350,200);
			}else{
				alert("请勾选需要设置的显示区！");
			}
		}
		
				//设置批量设置
		function setPlsz(){
		
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			
			//是否展开
			var sfzk = $("sz_sfzk_value").value;
			//是否启用
			var sfqy = $("sz_sfqy_value").value;
			//是否置顶
			var sfzd = $("sz_sfzd_value").value;
	
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					//是否展开
					if(sfzk != "不处理"){
						for(var j=0;j<document.getElementsByName("sfzk"+i).length;j++){
						
							if(document.getElementsByName("sfzk"+i)[j].value == sfzk){
								document.getElementsByName("sfzk"+i)[j].checked = true;
								break;
							}
						}
						$("hid_sfzk_"+i).value=sfzk;
					}
					//是否启用
					if(sfqy != "不处理"){
						for(var j=0;j<document.getElementsByName("sfqy"+i).length;j++){
							if(document.getElementsByName("sfqy"+i)[j].value == sfqy){
								document.getElementsByName("sfqy"+i)[j].checked = true;
								break;
							}
						}
						$("hid_sfqy_"+i).value=sfqy;
					}
					//是否置顶
					if(sfzd != "不处理"){
						for(var j=0;j<document.getElementsByName("sfzd"+i).length;j++){
							if(document.getElementsByName("sfzd"+i)[j].value == sfzd){
								document.getElementsByName("sfzd"+i)[j].checked = true;
								break;
							}
						}
						$("hid_sfzd_"+i).value=sfzd;
					}
				}
			}
			
			hiddenMessage(true,true);
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
									onclick="saveXsqsz();return false;"
									class="btn_ccg">保存</a>
							</li>
							<li>
								<a href="#"
									onclick="showPlszDiv();return false;"
									class="btn_sz">设置</a>
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
											onclick="czSearchCond('sfzk-sfzd-sfqy-xsqmc');">
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
									是否默认展开
								</th>
								<td>
									<html:select property="xsqsfzk" style="width: 150px" styleId="sfzk">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									是否置顶
								</th>
								<td>
									<html:select property="xsqsfzd" style="width: 150px" styleId="sfzd">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									是否启用
								</th>
								<td>
									<html:select property="xsqsfqy" style="width: 150px" styleId="sfqy">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									显示区名称
								</th>
								<td>
									<html:text property="search_xsqmc" 
										style="" maxlength="20" styleId="xsqmc"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>
								<th>
									
								</th>
								<td>
									
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
							&nbsp;&nbsp;<font color="blue">显示顺序限输入2位数字，如果为空，系统将认为其排列在末尾</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1">
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
								<!-- 显示区代码 -->
								<logic:iterate id="v" name="s" offset="0" length="1">
									<td align="left" style="display: none">
										${v }
										<input type="hidden" name="xsqdm" id="xsqdm${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- 显示区名称 -->
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left" nowrap="nowrap">
										${v }
									</td>
								</logic:iterate>
								<!-- 显示顺序 -->
								<logic:iterate id="v" name="s" offset="2" length="1">
									<td align="left" nowrap="nowrap">
										<input type="text" name="xssx" id="xssx${index }" 
											value="${v }" style="width: 70px"
											onkeydown="return onlyNum(this,2)"
											onmousedown="return onlyNum(this,2)"
											maxlength="2"
											style="width : 50%;ime-mode:disabled"/>
									</td>
								</logic:iterate>
								<!-- 是否展开 -->
								<logic:iterate id="v" name="s" offset="3" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="是">
											<input type="radio" name="sfzk${index }" value="是" onclick="clickRadioValue(${index },'sfzk',this.value)" checked="checked"/>是
											<input type="radio" name="sfzk${index }" value="否" onclick="clickRadioValue(${index },'sfzk',this.value)"/>否
										</logic:equal>
										<logic:equal name="v" value="否">
											<input type="radio" name="sfzk${index }" value="是" onclick="clickRadioValue(${index },'sfzk',this.value)"/>是
											<input type="radio" name="sfzk${index }" value="否" onclick="clickRadioValue(${index },'sfzk',this.value)" checked="checked"/>否
										</logic:equal>
										<input type="hidden" name="sfzk" id="hid_sfzk_${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- 是否启用 -->
								<logic:iterate id="v" name="s" offset="4" length="1">
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
								<!-- 是否置顶 -->
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="是">
											<input type="radio" name="sfzd${index }" value="是" onclick="clickRadioValue(${index },'sfzd',this.value)" checked="checked"/>是
											<input type="radio" name="sfzd${index }" value="否" onclick="clickRadioValue(${index },'sfzd',this.value)"/>否
										</logic:equal>
										<logic:equal name="v" value="否">
											<input type="radio" name="sfzd${index }" value="是" onclick="clickRadioValue(${index },'sfzd',this.value)"/>是
											<input type="radio" name="sfzd${index }" value="否" onclick="clickRadioValue(${index },'sfzd',this.value)" checked="checked"/>否
										</logic:equal>
										<input type="hidden" name="sfzd" id="hid_sfzd_${index }" value="${v }"/>
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
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxJbszForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
			<div id="xsqszDiv" style="display: none">
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
								<th width="30%">是否展开</th>
								<td>
									<input type="radio" name="sz_sfzk" value="不处理" onclick="setPlszHiddenValue('sfzk',this.value)" checked="checked"/>不处理
									<input type="radio" name="sz_sfzk" value="是" onclick="setPlszHiddenValue('sfzk',this.value)"/>是
									<input type="radio" name="sz_sfzk" value="否" onclick="setPlszHiddenValue('sfzk',this.value)"/>否
									<input type="hidden" id="sz_sfzk_value" value="不处理"/>
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
							<tr>
								<th>是否置顶</th>
								<td>
									<input type="radio" name="sz_sfzd" value="不处理" onclick="setPlszHiddenValue('sfzd',this.value)" checked="checked"/>不处理
									<input type="radio" name="sz_sfzd" value="是" onclick="setPlszHiddenValue('sfzd',this.value)"/>是
									<input type="radio" name="sz_sfzd" value="否" onclick="setPlszHiddenValue('sfzd',this.value)"/>否
									<input type="hidden" id="sz_sfzd_value" value="不处理"/>
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>