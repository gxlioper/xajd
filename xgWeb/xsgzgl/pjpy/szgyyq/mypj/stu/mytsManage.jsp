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
		
		//显示学生投诉Div
		function showXstsDiv(tsnr,tssj,clr,clyj,clsj){
			
			$("tsnr").value = tsnr;
			$("clyj").value = clyj;
			
			$("p_tssj").innerHTML = tssj;
			$("p_clr").innerHTML = clr;
			$("p_clsj").innerHTML = clsj;
			
			tipsWindown("系统提示","id:div_xsts","350","380","true","","true","id");
		}
		
		//查询结果集
		function searchRs(){
			var url = "szgyyq_mypj_stu.do?method=getMytsInfoList";

			//查询类型
			var mhcx_lx = $("mhcx_lx").value;
			//模糊查询
			var input_mhcx = $("input_mhcx").value;
			if(input_mhcx == ""){
				input_mhcx = " ";
			}else{
				input_mhcx=escape(input_mhcx);
			}

			//项目
			var xmdm = new Array();
			var xmdm_num = jQuery("a[name=a_name_xmdm]").length;
		
			var num = 0;
			
			if(xmdm_num != 0){
				for(var i=0;i<xmdm_num;i++){
					var id = jQuery("a[name=a_name_xmdm]")[i].id;
					xmdm[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xmdm = [" "];
			}
			
			//学年
			var xn = new Array();
			var xn_num = jQuery("a[name=a_name_xn]").length;
		
			num = 0;
			
			if(xn_num != 0){
				for(var i=0;i<xn_num;i++){
					var id = jQuery("a[name=a_name_xn]")[i].id;
					xn[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xn = [" "];
			}
			
			//学期
			var xq = new Array();
			var xq_num = jQuery("a[name=a_name_xq]").length;
		
			num = 0;
			
			if(xq_num != 0){
				for(var i=0;i<xq_num;i++){
					var id = jQuery("a[name=a_name_xq]")[i].id;
					xq[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xq = [" "];
			}
			
			//类型
			var lx = new Array();
			var lx_num = jQuery("a[name=a_name_lx]").length;
		
			num = 0;
			
			if(lx_num != 0){
				for(var i=0;i<lx_num;i++){
					var id = jQuery("a[name=a_name_lx]")[i].id;
					lx[num] = escape(id.replace("a_id_",""));
					num++;
				}
			}else{
				lx = [" "];
			}
			
			var otherValue = [
				xmdm.join("!!##!!"),
				xn.join("!!##!!"),
				xq.join("!!##!!"),
				lx.join("!!##!!"),
				mhcx_lx,
				input_mhcx
			];

			if(checkSearch()){
				searchRsByAjax(url,otherValue);
			}
		}
		
		//检验可否查询
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;
				
				if(xn_num == "0"){
					alertError("请至少选择一个学年！");
					flag = false;
				}else if( xq_num == "0"){
					alertError("请至少选择一个学期！");
					flag = false;
				}
			}
			return flag;
		}
		</script>
	</head>
	<body onload="searchRs()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>综合素质养成课 - 我的工作 - 我的投诉</a>
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
				1.以下所有操作都是基于<font color="blue">${xn}</font>学年，<font color="blue">${xqmc }</font>学期 展开的。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/szgyyq_mypj_stu" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="is_default" id="is_default" value=""/>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<li>
								<a href="#" onclick="goMypj();return false;" class="btn_fh">
									返回
								</a>
							</li>		
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				<!-- 过滤条件 -->
				
				<!-- 模糊查询 -->
				<div class="search_advanced">
					<div class="adv_filter">
						<table border="0" width="100%">
							<tbody>
								<tr>
									<td style="padding-left:68px;">
										查询条件：
								        <input type="text" name="searchModel.input_mhcx"
											id="input_mhcx" value="${searchTj.input_mhcx }" size="50"
											onkeypress="if(pressEnter(event)){searchRs();return false;}"
											onfocus="setTsMsg();displayMsgDiv('input_mhcx_msg');"
											onblur="hideMsgDiv('input_mhcx_msg')" />
			
										<div id="input_mhcx_msg" class="hide"
											style="left: 140px;top: 122px;">
											<div class="prompcon" style="width: 250px">
												<p id="tsxx_span"></p>
											</div>
										</div>
										
										<button type="button" class="btn_cx" id="search_go"
											onclick="searchRs();return false;">
											查 询
										</button>
			
										<button type="button" class="btn_cz" onclick="czSuperSearch()">
											重 置
										</button>
										<br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:radio property="searchModel.mhcx_lx" value="all" onclick="$('mhcx_lx').value = this.value"/>
										全部
										<!-- 模糊循环 -->
										<html:radio property="searchModel.mhcx_lx" value="xh" onclick="$('mhcx_lx').value = this.value"/>被投诉人学号
										<html:radio property="searchModel.mhcx_lx" value="xm" onclick="$('mhcx_lx').value = this.value"/>被投诉人姓名
										<input type="hidden" id="mhcx_lx" value="all"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<!-- 已选条件(个性化) -->
					<div class="selected-attr" id="yxtj_gxh_div" style="display: none">
						<h3>
							已选条件：
						</h3>
						<dl id="yxtj_gxh_dl">
				
						</dl>
					</div>
					<!-- 已选条件 end-->
					
					<!-- 高级查询(个性化)-->
					<div class="prop-item" id="gjcx_gxh_div" style="display: none">
						<!-- 学年 -->
						<dl>
							<dt>学年：</dt>
							<dd>
								<ul>
									<logic:iterate name="xnTjList" id="xnMap" indexId="list_num">
										<logic:notEqual name="list_num" value="0">
											<li>
												<a href="#" class=""
													onclick="clickTj('xn','${xnMap.xn }');creatGxhClickedTj('xn','学年','${xnMap.xn }','${xnMap.xn }',this);return false;" 
													id="tj_xn_${xnMap.xn }" name="tj_xn">
													${xnMap.xn }
												</a>
											</li>
										</logic:notEqual>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- 学年 end-->
						
						<!-- 学期 -->
						<dl>
							<dt>学期：</dt>
							<dd>
								<ul>
									<logic:iterate name="xqTjList" id="xqMap" indexId="list_num">
										<li>
											<a href="#" class=""
												onclick="clickTj('xq','${xqMap.xqdm }');creatGxhClickedTj('xq','学期','${xqMap.xqdm }','${xqMap.xqmc }',this);return false;" 
												id="tj_xq_${xqMap.xqdm }" name="tj_xq">
												${xqMap.xqmc }
											</a>
										</li>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- 学期 end-->
						
												
						<!-- 投诉状态 -->
						<dl>
							<dt>投诉状态：</dt>
							<dd>
								<ul>
									<logic:iterate name="lxTjList" id="lxMap">
										<li>
											<a href="#" class=""
												onclick="clickTj('lx','${lxMap.en }');creatGxhClickedTj('lx','投诉状态','${lxMap.en }','${lxMap.cn }',this);return false;" 
												id="tj_lx_${lxMap.en }" name="tj_lx">
												${lxMap.cn }
											</a>
										</li>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- 投诉状态 end-->
						
						<!-- 投诉项目 -->
						<dl>
							<dt>投诉项目：</dt>
							<dd>
								<ul>
									<logic:iterate name="xmdmTjList" id="xmdmMap">
										<li>
											<a href="#" class=""
												onclick="clickTj('xmdm','${xmdmMap.en }');creatGxhClickedTj('xmdm','投诉项目','${xmdmMap.en }','${xmdmMap.cn }',this);return false;" 
												id="tj_xmdm_${xmdmMap.en }" name="tj_xmdm">
												${xmdmMap.cn }
											</a>
										</li>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- 投诉项目 end-->
						
					</div>
					<!-- 高级查询 end-->
				</div>

				<!-- 伸缩按钮 -->
				<div class="more--item_bottom">
					<p>
						<a href="#" class="down"
							onclick="showGxhTbody(this,'up','down','收 起','更 多');return false">
							更 多
						</a>
					</p>
				</div>
				<!-- 伸缩按钮 end-->
				
				<!-- 构建条件 -->
				<div id="searchGxhTjDiv" style="display:none">
					<logic:present name="searchTj">
						<!-- 点击条件 -->
						<logic:notEmpty name="searchTj" property="search_tj_xn">
							<!-- 学年 -->
							<logic:iterate name="searchTj" property="search_tj_xn" id="queryValue">
								<input type="hidden" title="xn" name="search_tj_xn" value="${queryValue }" />
							</logic:iterate>
							<!-- 学期 -->
							<logic:iterate name="searchTj" property="search_tj_xq" id="queryValue">
								<input type="hidden" title="xq" name="searchModel.search_tj_xq" value="${queryValue }" />
							</logic:iterate>
						</logic:notEmpty>	

						<!-- 构建已选条件 -->
						<script language="javascript" defer="defer">
							setTimeout("creatClickedTjByGxhSearch()",500);
						</script>
					</logic:present>
				</div>
				<!-- 构建条件end-->
				
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- 过滤条件 end-->
			</div>
			
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
				<div id="div_rs" style="height:360px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzgyyqStuForm"></jsp:include>
					 <script type="text/javascript">
				      $('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->	
			
			<!-- 学生投诉弹出层 -->
			<div id="div_xsts" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>投诉信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									投诉内容
								</th>
								<td>
									<textarea id="tsnr" rows="5" cols="" 
										style="word-break:break-all;width:100%"></textarea>
								</td>
							</tr>
							<tr>
								<th width="30%">
									投诉时间
								</th>
								<td>
									<p id="p_tssj"></p>
								</td>
							</tr>
							<tr>
								<th width="30%">
									处理人
								</th>
								<td>
									<p id="p_clr"></p>
								</td>
							</tr>
							<tr>
								<th width="30%">
									处理意见
								</th>
								<td>
									<textarea id="clyj" rows="5" cols="" 
										style="word-break:break-all;width:100%"></textarea>
								</td>
							</tr>
							<tr>
								<th width="30%">
									处理时间
								</th>
								<td>
									<p id="p_clsj"></p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
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