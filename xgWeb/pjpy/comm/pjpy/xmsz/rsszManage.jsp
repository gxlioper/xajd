<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function initRssz(){
				var xmmc = jQuery('#xmdm option:selected').text();
				confirmInfo('您确定要初始化项目"'+xmmc+'"的人数设置吗?',function(type){
					if ('ok' == type){
						var xmdm = jQuery('#xmdm').val();
						var url = "pjpy_comm_rssz.do?method=initData";
						
						jQuery.post(url,{xmdm:xmdm},function(data){
							if ('true' == data){
								ymPrompt.alert({message:'人数设置初始化成功!',handler:function(d){
									if ('ok' == d){
										jQuery('#search_go').click();
									}
								}})
							} else {
								alertInfo('人数设置初始化失败!');
							}
							
						})
					}
				});
			}

			function plsz(){
				if (isChecked('primarykey_cbv')){
					tipsWindown("系统提示","id:rsszDiv","350","160","true","","true","id");
				}
			}
			
			function rsszSave(){
				var bl = jQuery('#bl').val();
				var fpfs = jQuery('input[type=radio][name=fpfs]:checked').val()
			
				confirmInfo('您确定要保存吗?',function(type){
					if ('ok' == type){
						refreshForm('pjpy_comm_rssz.do?method=saveRssz&fpfs='+fpfs+'&bl='+bl);
					}
				})
			}
			
			
			function szfwSearch(){
				var url = "/xgxt/pjpy_comm_rssz.do";
				var szfw = $('szfw').value;

				if('xy' == szfw){
					url += "?method=rsszForSzfw&szfw=xy" ;
				}else if('zy' == szfw){
					url += "?method=rsszForSzfw&szfw=zy";
				}else if('bj' == szfw){
					url += "?method=rsszForSzfw&szfw=bj";
				}else{
					url += "?method=rsszForSzfw&szfw=nj";
				}
				
				
				allNotEmpThenGo(url);
			}
			
			function saveQdrs(){
				var qdrs = document.getElementsByName('qdrs')
				if( qdrs != null && qdrs.length>0){
					confirmInfo('您确定要保存吗?',function(type){
						if ('ok' == type){
							refreshForm('/xgxt/pjpy_comm_rssz.do?method=saveQdrs');	
						}
					})
				}else {
					alertInfo('请点击查询，填写确定人数后保存！');
				}
			}
			
			function setMrrs(){
				var checkbox = document.getElementsByName('primaryKey_cbv');
				var mrrs = document.getElementsByName('mrrs');
				var qdrs = document.getElementsByName('qdrs');
				var count = $('count').value;
				var syrs = $('syrs').value;
					
				var count_now = 0;
				var temp = 0;
				for(var i=0;i<checkbox.length;i++){
					if(checkbox[i].checked == true){
						temp = mrrs[i].value == "" ? 0 : eval(mrrs[i].value);
					}else{
						temp = qdrs[i].value == "" ? 0 : eval(qdrs[i].value);
					}
					count_now += temp;										
				}
				
				var cz = count_now - count;
				
				if(cz<=syrs || syrs == -1){				
					batchData('primarykey_cbv','/xgxt/pjpy_comm_rssz.do?method=saveQdrsFromMrrs','tb')
				}else{
					alertInfo('超过该项目人数上限！');
				}
			}
			
			function skipNext(obj){
			
				var currIndex = jQuery(obj).parents("tr").index();
				
				if(event.keyCode==13 || event.keyCode==40){
					currIndex++;
				} else if (event.keyCode==38){
					currIndex--;
				}
				var tr = jQuery('#dataTab tr:eq('+currIndex+')');
				tr.click();
				jQuery('input[name=qdrs]',tr).focus();
			}
			
			function calculateQdrs(obj){
				checkInputData(obj);
				
				var syrs = eval($('syrs').value);
				var count = eval($('count').value);
				var dqrs = obj.value;
				
				var qdrs = document.getElementsByName('qdrs');
				
				var count_now = 0;
				if(qdrs !=null){
					for(var i=0;i<qdrs.length;i++){
						var temp = qdrs[i].value == "" ? 0 : eval(qdrs[i].value);
						
						count_now += temp;
					}
				}
				
				var cz = count_now - count;
				
				if(cz<=syrs){				
					$('count').value = count_now;
					$('syrs').value = syrs-cz;
						if($('span_syrs')!=null)
					$('span_syrs').innerHTML = $('syrs').value;
				}else if(syrs != -1){
					alertInfo('超过该项目人数上限！');
					obj.value = "";
				}
			}
			
			function showTj(){
				var szfw = $('szfw').value;
				if("xy" == szfw){
					if($('xy')!=null||$('zy')!=null||$('bj')!=null){
					$('xy').disabled = false;
					$('zy').value = ""
					$('zy').disabled = true;
					$('bj').value = ""
					$('bj').disabled = true;
					}
				}else if("zy" == szfw){
					if($('xy')!=null||$('zy')!=null||$('bj')!=null){
					$('xy').disabled = false;
					$('zy').disabled = false;
					$('bj').value = ""
					$('bj').disabled = true;
					}
				}else if("bj" == szfw){
					if($('xy')!=null||$('zy')!=null||$('bj')!=null){
					$('xy').disabled = false;
					$('zy').disabled = false;
					$('bj').disabled = false;
					}
				}else if("nj" == szfw){
					if($('xy')!=null||$('zy')!=null||$('bj')!=null){
					$('xy').value = ""
					$('xy').disabled = true;
					$('zy').value = ""
					$('zy').disabled = true;
					$('bj').value = ""
					$('bj').disabled = true;
					}
				}
				
			}
			
			function choiceKzfw(){
				var xmdm = $('xmdm').value;
				var kzfw = $(xmdm+'_kzfw').value;
				
				$('szfw').value = kzfw;
				$('sel_szfw').value = kzfw;
				
				showTj();
				
				var rssz = jQuery('#rssz').val();
				if(rssz!='true'){
					jQuery('#search_go').click();
				}
				
			}

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
	<body onload="choiceKzfw();">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优-评奖综合设置-人数设置</a>
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
			 1.初 始 化：清除当前所选项目相关的人数设置。<br/>
		     2.计算规则：设置计算后人数从小数点后第几位四舍五入计算出默认人数。<br/>
		                  如：计算后人数为12.45，从小数点后第一位四舍五入计算出默认人数为12，从小数点后第二位计算出默认人数为13。<br/>
		     3.比例设置：选择记录设置比例后系统自动计算出默认人数。<br/>
		     4.启用默认人数：选择记录设置确定人数为系统自动计算出的默认人数。<br/>
		     5. 保存：手工输入完确定人数后保存。<br/>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					
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
		
		<html:form action="/pjpy_comm_rssz" method="post">
			<input type="hidden" id="message" value="${message}" />
			<input type="hidden" name="lastXmdm" value="${lastXmdm }" />
			<input type="hidden" name="lastSzfw" value="${lastSzfw }" />
			<input type="hidden" id="syrs" value="${syrs }" />
			<input type="hidden" id="count" value="${count }" />

			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />

			<input type="hidden" id="szfw" name="szfw" value="" />
			<input type="hidden" id="rssz" name="rssz" value="${rssz }" />

			<input type="hidden" id="doType" name="doType" value="${doType }" />
			
			<logic:iterate id="pjxmMap" name="pjxmList">
				<input type="hidden" id="${pjxmMap.xmdm }_kzfw"
					value="${pjxmMap.kzfw }" />
			</logic:iterate>


				<logic:equal value="true" name="kfsz">
					<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" class="btn_csh" onclick="initRssz();return false;">初始化</a>
								</li>
								<li>
									<a href="#" class="btn_sz"
										onclick="showTopWin('/xgxt/pjpy_comm_rssz.do?method=rsszCssz',500,300);return false;">计算规则</a>
								</li>
								<li>
									<a href="#" class="btn_sz" onclick="plsz();return false;">比例设置</a>
								</li>
								<li>
									<a href="#" class="btn_sx" onclick="setMrrs();return false;">启用默认人数</a>
								</li>
								<li>
									<a href="#" class="btn_ccg" onclick="saveQdrs();return false;">保存设置</a>
								</li>
							</ul>
						</div>
					</div>
				</logic:equal>

			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>
								项目名称
							</th>
							<td>
								<html:select property="xmdm" styleId="xmdm" style="width:180px"
									onchange="choiceKzfw();szfwSearch();">
									<html:options collection="pjxmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
							<th>
								设置范围
							</th>
							<td>
								<select id="sel_szfw" disabled="disabled" style="width: 180px">
									<option value="xy">
										<bean:message key="lable.xb" />
									</option>
									<option value="zy">
										专业
									</option>
									<option value="bj">
										班级
									</option>
									<option value="nj">
										年级
									</option>
								</select>
							</td>
							<th>
								年级 
							</th>
							<td> 
								<html:select property="nj" styleId="nj" 
											 style="width: 180px"
											 onchange="initZyList();initBjList()">
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
								<html:select property="xydm" styleId="xy" style="width: 180px"
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm" styleId="zy" style="width: 180px"
									onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm" styleId="bj" style="width: 180px"
									onchange="">
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
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="szfwSearch()">
										查 询
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="czSearchCond('nj-xy-zy-bj');return false;">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<div class="formbox">
				<logic:empty name="rs">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <font color="red">未找到任何记录！</font> </span>
					</h3>
				</logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp;
							<font color="blue">单击表头可以排序;
								<logic:equal value="-1" name="syrs">该项目无人数限制;</logic:equal>
							</font>
							<logic:greaterEqual value="0" name="syrs">
								<font color="blue">该项目未分配人数:</font><font id="span_syrs" color="red">${syrs }</font>
							</logic:greaterEqual>
						</span>
					</h3>
					<div class="con_overlfow">
						<table width="99%" id="rsTable" class="dateline tablenowrap">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:equal value="true" name="kfsz">
										<td>
											<input type="checkbox" disabled="true" />
										</td>
									</logic:equal>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody id="dataTab">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<logic:equal value="true" name="kfsz">
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="primarykey_cbv" id="pkV"
													${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
											</logic:iterate>
										</td>
									</logic:equal>
									<logic:iterate id="v" name="s" offset="1" length="${size-2}">
										<td>
											${v }
										</td>
									</logic:iterate>
									<td>
										<logic:iterate id="v" name="s" offset="${size-1}" length="1">
											<logic:equal value="true" name="kfsz">
												<html:text property="qdrs" value="${v }"
													onblur="calculateQdrs(this);" 
													onkeydown="return onlyNum(this,5);skipNext(this);"
													onmousedown="return onlyNum(this,5)"
													maxlength="5" style="width:50px"></html:text>
											</logic:equal>
											<logic:notEqual value="true" name="kfsz">
										 	${v }
										 </logic:notEqual>
										</logic:iterate>

										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" name="pks" value="${v }" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="${size-2}" length="1">
											<input type="hidden" name="mrrs" value="${v }" />
										</logic:iterate>
									</td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=pjpyRsszForm"></jsp:include>
					<!--分页显示-->
				</logic:notEmpty>
			</div>
			
			
			
			
			
			
			<div id="rsszDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>批量人数设置</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									分配方式
								</th>
								<td>
									<html:radio property="fpfs" value="bl" />按比例分配
								</td>
							</tr>
							<tr>
								<th>比例</th>
								<td>
									<html:text property="bl" styleId="bl" onblur="checkInputNum(this);chMax(this,100);" maxlength="3"></html:text>%
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="rsszSave()">
											保 存
										</button>
										<button type="button" name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			</div>
		</html:form>
	</body>
</html>
