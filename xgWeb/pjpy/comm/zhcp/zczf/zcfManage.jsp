<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		function searchRs(){
		
			allNotEmpThenGo('/xgxt/zhcpZczf.do?method=zcfManage');
		}
		
		function showPdbxQd(){
			var pkValue=curr_row.getElementsByTagName('input')[0].value;
			showTopWin("/xgxt/zhcpPdbx.do?method=pdbxQd&pkValue="+pkValue,800,600);
		}
		
		
		
		function lxgn(){
			showTopWin("/xgxt/zhcpZczf.do?method=kindChoose",800,500);
		}
		
		function zhszcpjs(){
			var jslxArr=document.getElementsByName("jslx");
			var pmjsArr=document.getElementsByName("pmjs");
			var blog=false;
			var flag=false;
			var bool=false;
			for(i=0;i<jslxArr.length;i++){
			
				
				if(jslxArr[i].checked){
					blog=true;
					if(jslxArr[i].value=="pmjs"){
						bool=true; 
						for(j=0;j<pmjsArr.length;j++){
							if(pmjsArr[j].checked){
								flag=true;
								break;
							}
						}
					}
				}
			}
			
			if(!blog){
				alertInfo("请选择需要计算的类型！");
				return false;
			}
			
			if(!flag && bool){
				alertInfo("请选择需要计算的排名类型！");
				return false;
			}
		
			confirmInfo("将对指定的综测信息进行计算，是否继续？",function(t){

				if(t=="ok"){
					$("prompt").innerHTML="正在进行综测信息计算，请稍候!";
					showLoadPage();
					refreshForm("/xgxt/zhcpZczf.do?method=zcfManage&doType=zcjs");
					hiddenMessage(true,true);
				}
				
			});
		}
		
		
		//显示加载页面
		function showLoadPage(){
			//多功能操作
			$("dgncz").style.display="none";
			//查询结果
			$("cxjg").style.display="none";
			//显示
			$("page_loading").style.display="";
			//设置提示信息
			
		}
		
		function showDiv(){
			viewTempDiv("计算方式选择","zcfjsDiv",350,200);
		}
		
		function showPm(){
			//选中排名
			if($("zcpm").value!="1" && $("zcpm").value!="2" && $("zcpm").value!="3"){
			if($("jslx_pm").checked){
				$("pmjs").style.display="";
			}else{
				$("pmjs").style.display="none";
			}
			}
		}
		
		function showZypm(){
			//选中排名
			if($("zypm").value!="1" && $("zypm").value!="2" && $("zypm").value!="3"){
				if($("jslx_zypm").checked){
					$("zypmjs").style.display="";
				}else{
					$("zypmjs").style.display="none";
				}
			}
		}
		
		jQuery(function(){
		
			if($("zd30")){
				var zd30=$("zd30").name;
			
				jQuery("."+zd30).each(function(){
					var text=jQuery(this).text();
						
					var title=jQuery(this).attr("title");
					
					var title=text;
					if(text.length>10){
						title=text;
						text=text.substr(0,10)+"...";
					}
					jQuery(this).attr("title",title);
					jQuery(this).text(text)
				});
			}
		});
		
		function expZccj(){
			jQuery('#select_xy').attr('id','xy');
			jQuery('#select_zy').attr('id','zy');
			jQuery('#select_bj').attr('id','bj');
			viewTempDiv("选择打印班级","expZccj",350,200);
		}
		</script>
	</head>
	<body >
		

		

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
			<!-- 相关功能 -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;"
					onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">相关功能</a>
			</p>
			<!-- 相关功能 end-->
		</div>
		<!-- 标题 end-->

		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.如果查询结果显示不了任何内容的话，可能执行了
				<font color="blue">列选</font>操作后就好了。
				</br>
				2.您可以根据
				<font color="blue">列选</font>功能来定制您希望看到的结果集。
				</br>
				3.综合素质测评分以及相关排名需要执行
				<font color="blue">总分计算</font>获取。
				</br>
				4.如果各项分数发生变化的话，综合素质测评分需要
				<font color="blue">重新计算</font>。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar"
				style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">

					<div class="liucheng_font floatleft">
						<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
							<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>
								评奖基本设置
							</p> </a>
					</div>

					<div class="liucheng_font floatleft">
						<a href="#"
							onclick="goOtherGnmk('zhcp_sjcl_sjdr.do');return false;"> <img
								src="<%=stylePath%>/images/blue/48-1/Function35.png" />
							<p>
								综测分维护
							</p> </a>
					</div>

				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->

		<html:form action="/zhcpZczf" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="zypm" id="zypm" value="${zypm }" />
			<input type="hidden" name="zcpm" id="zcpm" value="${zcpm }" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
				
		<div id="expZccj" style="display: none">
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span> 打印班级选择 </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="select_xy">
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
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="select_zy">
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
								<html:select property="bjdm" style="width:180px" styleId="select_bj">
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
								<div class="btn">
									<button type="button" onclick="expData('zhcpZczf.do?method=getZhcpPrintData&bjdm='+jQuery('#bj').val())">
										确 定
									</button>
									<button type="button" onclick="hiddenMessage(true,true);">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</tfoot>
				</table>
			</div>
		</div>
		

			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showDiv();return false;" class="btn_xg">总分计算</a>
						</li>
						<li>
							<a href="#" onclick="lxgn();return false;" class="btn_zt">列选
							</a>
						</li>
						<logic:equal value="13108" name="xxdm">
							<li>
								<a href="#" onclick='expZccj();' class="btn_dy">打印
								</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="all" value="all" onclick="chec()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
											name="td_${index}" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" ondblclick=""
										style="cursor:hand">
										<td>
											<input type="checkbox" name="primary_key" id="pkV"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"
												<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate> />
										</td>
										<logic:iterate id="v" name="s" offset="1" indexId="cols">
											<td nowrap class="td_${cols}">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=zhcpZczfForm"></jsp:include>
				</logic:notEmpty>
			</div>
		</html:form>
		
		<!-- 总分计算选择DIV -->
		<div id="zcfjsDiv" style="display: none">
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span> 综合素质测评分计算 </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								计算类型
							</th>
							<td>
								<input type="checkbox" name='jslx' id='jslx_zf' value='zfjs'
									checked />
								综测分计算
								<input type="checkbox" name='jslx' id='jslx_pm' value='pmjs'
									onclick="showPm()" />
								综测排名计算
								<logic:notEqual name="zypm" value="0">
									<br />
									<input type="checkbox" name='jslx' id='jslx_zypm'
										value='zypmjs' onclick="showZypm()" />智育排名计算
								</logic:notEqual>
							</td>
						</tr>
						<tr id="pmjs" style="display:none">
							<th>
								综测分排名计算
							</th>
							<td>
								<logic:equal name="zcpm" value="1">
									<input type="checkbox" name='pmjs' id='pmjs_xy' value='njxy'
										checked />
								</logic:equal>
								<logic:equal name="zcpm" value="2">
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy'
										checked />
								</logic:equal>
								<logic:equal name="zcpm" value="3">
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />
								</logic:equal>
								<logic:equal name="zcpm" value="4">
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />班级排名
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy' />年级专业排名
								</logic:equal>
								<logic:equal name="zcpm" value="5">
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />班级排名
									<input type="checkbox" name='pmjs' id='pmjs_xy' value='njxy' />年级<bean:message key="lable.xb" />排名
								</logic:equal>

								<!-- 专业、学院排名begin  -->
								<logic:equal name="zcpm" value="6">
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy'
										checked />年级专业排名
									<input type="checkbox" name='pmjs' id='pmjs_xy' value='njxy' />年级<bean:message key="lable.xb" />排名
								</logic:equal>
								<!-- 专业、学院排名end -->

								<!-- 班级、专业、学院排名begin  -->
								<logic:equal name="zcpm" value="7">
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />班级排名
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy' />年级专业排名<br />
									<input type="checkbox" name='pmjs' id='pmjs_xy' value='njxy' />年级<bean:message key="lable.xb" />排名
								</logic:equal>
								<!-- 班级、专业、学院排名end  -->
							</td>
						</tr>
						<tr id='zypmjs' style="display:none">
							<th>
								智育排名计算
							</th>
							<td>
								<logic:equal name="zypm" value="1">
									<input type="checkbox" name='zypmjs' id='zypmjs_xy'
										value='njxy' checked />
								</logic:equal>
								<logic:equal name="zypm" value="2">
									<input type="checkbox" name='zypmjs' id='zypmjs_zy'
										value='njzy' checked />
								</logic:equal>
								<logic:equal name="zypm" value="3">
									<input type="checkbox" name='zypmjs' id='zypmjs_bj' value='bj'
										checked />
								</logic:equal>

								<logic:equal name="zypm" value="4">
									<input type="checkbox" name='zypmjs' id='zypmjs_bj' value='bj'
										checked />班级排名
									<input type="checkbox" name='zypmjs' id='zypmjs_zy'
										value='njzy' />年级专业排名
								</logic:equal>
								<logic:equal name="zypm" value="5">
									<input type="checkbox" name='zypmjs' id='zypmjs_bj' value='bj'
										checked />班级排名
									<input type="checkbox" name='zypmjs' id='zypmjs_xy'
										value='njxy' />年级<bean:message key="lable.xb" />排名
								</logic:equal>

								<!-- 专业、学院排名begin  -->
								<logic:equal name="zypm" value="6">
									<input type="checkbox" name='zypmjs' id='zypmjs_zy'
										value='njzy' checked />年级专业排名
									<input type="checkbox" name='zypmjs' id='zypmjs_xy'
										value='njxy' />年级<bean:message key="lable.xb" />排名
								</logic:equal>
								<!-- 专业、学院排名end -->

								<!-- 班级、专业、学院排名begin  -->
								<logic:equal name="zypm" value="7">
									<input type="checkbox" name='zypmjs' id='zypmjs_bj' value='bj'
										checked />班级排名
									<input type="checkbox" name='zypmjs' id='zypmjs_zy'
										value='njzy' />年级专业排名<br />
									<input type="checkbox" name='zypmjs' id='zypmjs_xy'
										value='njxy' />年级<bean:message key="lable.xb" />排名
								</logic:equal>
								<!-- 班级、专业、学院排名end  -->
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" onclick="zhszcpjs()">
										确 定
									</button>
									<button type="button" onclick="hiddenMessage(true,true);">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		
		
		
		
	</body>
	<%@ include file="/comm/loading.jsp"%>
	<%@ include file="/comm/other/tsxx.jsp"%>
</html>
