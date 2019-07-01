<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxglCx.js"></script>
		<script language="javascript" src="js/LodopFuncs.js"></script>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript">
			// 设置模板
			function szXszCommon(csdm) {
			 	jQuery.post("xsxx_xsxxgl.do?method=cxXsz",{csdm:csdm},function(data){
				 	 var csz = data["csz"];
					 if(csz == null){
						 showAlertDivLayer("模板未初始化！");
						 return false;
					 }else{
						// 初始化组件
						var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
						LODOP.PRINT_INIT("打印");
						// 布局模板元素
						eval(csz); 
						// 设置返回值
						LODOP.SET_PRINT_MODE("PRINT_SETUP_PROGRAM",true);
						
						//var csz = LODOP.PRINT_SETUP(); // 普通用户模式（只能调整模板内容的位置）
						var csz = LODOP.PRINT_DESIGN(); // 开发人员模式（随意修改模板内容）
						
						confirmInfo("是否保存模板？",function(data){
							if("ok"==data){
								jQuery.post("xsxx_xsxxgl.do?method=bcXsz",{csdm:csdm, csz:csz},function(data){
									showAlertDivLayer(data["message"]);
								},'json');
							}
						});
					 }
				},'json');
			}
			// 打印模板
			function dyXszCommon(csdm) {
				 var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要打印的记录！");
				 } else {
					 var ids = jQuery("#dataTable").getSeletIds();
					 jQuery.post("xsxx_xsxxgl.do?method=dyXsz",{csdm:csdm, value:ids+""},function(data){
							 var csz = data["csz"];
							 if(csz == null){
								 showAlertDivLayer("学生信息未填写完整，请填写后再打印！");
								 return false;
							 }else{
								// 初始化组件
								var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
								LODOP.PRINT_INIT("打印");
								// 布局模板元素
								eval(csz);
								// 打印预览
								LODOP.PREVIEW();
							 }
						},'json');
			     }
			}

			function dyByzsCommon(csdm) {
				 var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要打印的记录！");
				 } else {
					 var ids = jQuery("#dataTable").getSeletIds();
					 jQuery.post("xsxx_xsxxgl.do?method=dyByzs",{csdm:csdm, value:ids+""},function(data){
							 var csz = data["csz"];
							 if(csz == null){
								 showAlertDivLayer("毕业学生信息未填写完整，请填写后再打印！");
								 return false;
							 }else{
								// 初始化组件
								var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
								LODOP.PRINT_INIT("打印");
								// 布局模板元素
								eval(csz);
								// 打印预览
								LODOP.PREVIEW();
							 }
						},'json');
			     }
			}

			// 西安培华学生证打印
			function dyXszXaph(csdm) {
				 var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要打印的记录！");
				 } else {
					 var ids = jQuery("#dataTable").getSeletIds();
					 jQuery.post("xsxx_xsxxgl.do?method=dyXszXaph",{csdm:csdm, value:ids+""},function(data){
							 var csz = data["csz"];
							 if(csz == null){
								 showAlertDivLayer("学生信息未填写完整，请填写后再打印！");
								 return false;
							 }else{
								// 初始化组件
								var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
								LODOP.PRINT_INIT("打印");
								// 布局模板元素
								eval(csz);
								// 打印预览
								LODOP.PREVIEW();
							 }
						},'json');
			     }
			}

			
	/*学生信息导出*/
	function xsxxdc(){
	 var rows = jQuery("#dataTable").getSeletRow();
	 var len = rows.length;
	 if(len == 0){
		 showAlertDivLayer("请先勾选数据，再进行导出操作！");
		 return false;
	 }
	 var xhs = "";
	 jQuery(rows).each(function(i,row){
		 var xh = row["xh"];
		 /*为了防止有空学号，有可能有测试数据，或者错误数据*/
		 if(jQuery.trim(xh).length !=0){
			 xhs +=xh; 
		 }
		 if(i != len -1){
			 xhs +=","; 
		 }
	 })
	 var url = "xsxx_hzdc.do?method=xsxxDcPrepare&xhs="+xhs;
	 showDialog("学生信息导出", 690, 500, url);
	}
	//江苏吴中中专
	function sxcl(){
		var ids = jQuery("#dataTable").getSeletIds();
		var cnt = ids.length;
		if(cnt == 0){
			return showAlertDivLayer("请先勾选数据，再进行实习处理操作！");
		}
		var xh = cnt ==1 ? ids : "";
		var url = "xsxx_xsxxgl.do?method=sxztXg&cnt="+cnt+"&xh="+xh;
		showDialog("实习处理", 300, 210, url);
	}
			
		</script>
	</head>
	<body>
		<input type="hidden" id="pksPlHidden" value=""/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/xsxx_xsxxgl" method="post">
			<input type="hidden" name="method" id="method" value="${method}"/>
	
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xhstr" id="xhstr" />
			<input type="hidden" name="xh" id="xh" />
			<input type="hidden" name="value" id="value" />
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							<!-- 非四川机电职业技术学院  江西科技师范大学  浙江传媒学院-->
							<logic:notEqual name="xxdm" value="11647">
							  <logic:notEqual name="xxdm" value="12751">
								<logic:notEqual name="xxdm" value="11318">
									<li>
										<a href="#" onclick="showzxsxxAdd();return false;" id="btn_zj"
											class="btn_zj"> 增加 </a>
									</li>
									<li>
										<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg"
											class="btn_xg"> 修改 </a>
									</li>
									<!-- 武昌首义学院、苏州旅游与财经高等职业技术学校屏蔽删除按钮 -->
									<logic:notEqual value="12309" name="xxdm">
										<logic:notEqual value="2297" name="xxdm" >
											<li>
												<a href="#" onclick="deletezxsxx();return false;" id="btn_sc"
													class="btn_sc"> 删除 </a>
											</li>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							  </logic:notEqual>
							</logic:notEqual>
							<!-- end 非四川机电职业技术学院  江西科技师范大学  浙江传媒学院-->
							
							<!-- 四川机电职业技术学院 -->
							<logic:equal name="xxdm" value="12751">
								<logic:notEqual value="xx" name="userStatus">
									<li>
										<a href="#" onclick="showzxsxxEdit();return false;"
											id="btn_xg" class="btn_xg"> 修改 </a>
									</li>
								</logic:notEqual>
							</logic:equal>
							<!-- end 四川机电职业技术学院 -->
							
							<!-- 江西科技师范大学-->
							<logic:equal name="xxdm" value="11318">
								<logic:equal value="xx" name="userStatus">
								<li>
									<a href="#" onclick="showzxsxxAdd();return false;" id="btn_zj"
										class="btn_zj"> 增加 </a>
								</li>
								</logic:equal>
								<li>
									<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg"
										class="btn_xg"> 修改 </a>
								</li>
								<logic:equal value="xx" name="userStatus">
								<li>
									<a href="#" onclick="deletezxsxx();return false;" id="btn_sc"
										class="btn_sc"> 删除 </a>
								</li>
								</logic:equal>
							</logic:equal>
							<!-- end 江西科技师范大学 -->
							
							<!-- 浙江传媒学院-->
							<logic:equal name="xxdm" value="11647">
								<logic:equal value="xx" name="userStatus">
								<li>
									<a href="#" onclick="showzxsxxAdd();return false;" id="btn_zj"
										class="btn_zj"> 增加 </a>
								</li>
								</logic:equal>
								<li>
									<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg"
										class="btn_xg"> 修改 </a>
								</li>
								<logic:equal value="xx" name="userStatus">
								<li>
									<a href="#" onclick="deletezxsxx();return false;" id="btn_sc"
										class="btn_sc"> 删除 </a>
								</li>
								</logic:equal>
							</logic:equal>
							<!-- end 浙江传媒学院 -->
							<%--江苏吴中中等专科学校 实习处理--%>	
							<logic:equal value="1773" name="xxdm">
							<li>
								<a href="#" onclick="sxcl();return false;"
									class="btn_csh">实习处理</a>
							</li>
							</logic:equal>						
						</logic:equal>
						<li>
							<a href="#" onclick="showzxsxxView();return false;" id="btn_ck"
								class="btn_ck"> 查看 </a>
						</li>
						
						<li>
							<a href="#" class="btn_dc" id="btn_dc" onclick="zxsxxExportConfig();return false;">导出</a>
						</li>
						
						<!-- 东北石油大学个性化 start-->
						<!-- 学生证打印 start -->
						<logic:equal name="xszFlag" value="true">							
<%--						<li><a href="javascript:void(0);" onclick="szXszCommon('xsz_${xxdm }');return false;" class="btn_dy">设置学生证（仅开发用）</a></li>--%>
							<li><a href="javascript:void(0);" onclick="dyXszCommon('xsz_${xxdm }');return false;" class="btn_dy">打印学生证</a></li>
						</logic:equal>
						<!-- 学生证打印 end -->
						<!-- 第一学位证书 start -->
						<logic:equal name="dyxwzsFlag" value="true">							
<%--						<li><a href="javascript:void(0);" onclick="szXszCommon('dyxwzs_${xxdm }');return false;" class="btn_dy">设置第一学位证书（仅开发用）</a></li>--%>
							<li><a href="javascript:void(0);" onclick="dyByzsCommon('dyxwzs_${xxdm }');return false;" class="btn_dy">第一学位证书</a></li>
						</logic:equal>
						<!-- 第一学位证书 end -->
						<!-- 第二学位证书 start -->
						<logic:equal name="dexwzsFlag" value="true">							
<%--						<li><a href="javascript:void(0);" onclick="szXszCommon('dexwzs_${xxdm }');return false;" class="btn_dy">设置第二学位证书（仅开发用）</a></li>--%>
							<li><a href="javascript:void(0);" onclick="dyByzsCommon('dexwzs_${xxdm }');return false;" class="btn_dy">第二学位证书</a></li>
						</logic:equal>
						<!-- 第二学位证书 end -->
						<!-- 专升本证书 start -->
						<logic:equal name="zsbzsFlag" value="true">							
<%--						<li><a href="javascript:void(0);" onclick="szXszCommon('zsbzs_${xxdm }');return false;" class="btn_dy">设置专升本证书（仅开发用）</a></li>--%>
							<li><a href="javascript:void(0);" onclick="dyByzsCommon('zsbzs_${xxdm }');return false;" class="btn_dy">专升本证书</a></li>
						</logic:equal>
						<!-- 专升本证书 end -->
						<!-- 东北石油大学个性化 end-->
						<logic:equal name="xxdm" value="10220">
						   </ul>
						  </div>
						  <div class="buttonbox" id="buttonbox2">
						   <ul>
						</logic:equal>
						<!--  -->
						<logic:notEqual name="xxdm" value="14008">
							<logic:notEqual name="xxdm" value="90211">
								<logic:notEqual name="xxdm" value="11600">
									<logic:notEqual name="xxdm" value="10834">
										<logic:notEqual name="xxdm" value="9800003">
											<logic:notEqual name="xxdm" value="110501">
												<logic:notEqual name="xxdm" value="13033">
													<logic:notEqual name="xxdm" value="12684">
														<logic:notEqual name="xxdm" value="70002">
															<%--<li>
																<a href="javascript:void(0);"
																   onclick="printTyXjk();return false;" class="btn_dy" id="dyxsxx">
																	<logic:equal name="xxdm" value="12036">
																		在校生证明打印
																	</logic:equal>
																	<logic:notEqual name="xxdm" value="12036">
																		<logic:notEqual name="xxdm" value="13431">
																			学生登记表打印
																		</logic:notEqual>
																	</logic:notEqual>
																	<logic:equal name="xxdm" value="13431">
																			学籍表导出
																	</logic:equal>
																</a>
															</li>--%>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>

						<!-- 永康职业技术学校个性化 -->
						<logic:equal name="xxdm" value="90211">							
							<li>
								<a href="javascript:void(0);" onclick="printRxdjb();return false;"
									class="btn_dy">学生登记表打印</a>
							</li>
						</logic:equal>
						
						<!-- 江西科技师范大学个性化 -->
						<logic:equal name="xxdm" value="11318">							
							<li>
								<a href="javascript:void(0);" onclick="printRxdjb();return false;"
									class="btn_dy">入学登记表打印</a>
							</li>
						</logic:equal>
						<!-- 浙江传媒大学个性化 -->
						<logic:equal name="xxdm" value="11647">							
							<li>
								<a href="javascript:void(0);" onclick="printRxdjb();return false;"
									class="btn_dy">户籍登记表打印</a>
							</li>
						</logic:equal>
								<%--<!--北京林业大学 -->
						<logic:equal name="xxdm" value="10022">
							<li>
								<a href="javascript:void(0);" onclick="printJbxxdjb();return false;"
									class="btn_dy">本科生基本信息登记表</a>
							</li>
						</logic:equal>--%>
						<!-- 湖南机电职业技术学院个性化 -->
						<logic:equal name="xxdm" value="13033">
							<li>
								<a href="javascript:void(0);"
									onclick="printTyXjk();return false;" class="btn_dy">登记表打印</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="printDjb();return false;" class="btn_dy">学籍卡打印</a>
							</li>
						</logic:equal>
						<!-- 广州铁路职业技术学院个性化 -->
						<logic:equal name="xxdm" value="13943">
							<li>
								<a href="javascript:void(0);"
									onclick="printDjb();return false;" class="btn_dy">学籍卡打印</a>
							</li>
						</logic:equal>
						
						<!-- 重庆三峡医药高等专科学校个性化 -->	
						<logic:equal name="xxdm" value="14008">
							<li>
								<a href="javascript:void(0);"
									onclick="printRxdjb();return false;" class="btn_dy">学生登记表打印</a>
							</li>
						</logic:equal>
						
						<!-- 湖北经济学院个性化 -->
						<logic:equal name="xxdm" value="11600">
							<li>
								<a href="javascript:void(0);"
									onclick="printZxsxx();return false;" class="btn_dy">学生登记表打印</a>
							</li>
						</logic:equal>
						<!-- 武汉职业技术学院个性化 -->
						<logic:equal name="xxdm" value="10834">
							<li>
								<a href="javascript:void(0);"
									onclick="printZxsxx();return false;" class="btn_dy">学生登记表打印</a>
							</li>
						</logic:equal>
										
						<!-- 广东轻工 -->
						<logic:equal name="xxdm" value="9800003">
							<li>
								<a href="javascript:void(0);" onclick="printXjk();return false;"
									class="btn_dy">学生学籍卡打印</a>
							</li>
						</logic:equal>
						
						<!-- 上海海洋大学 -->
						<logic:equal name="xxdm" value="10264">
							<li>
								<a href="javascript:void(0);" onclick="printXsdjb();return false;"
									class="btn_dy">新生登记表打印</a>
							</li>
						</logic:equal>
						
						<logic:equal name="xxdm" value="	12866">
							<li>
								<a href="javascript:void(0);" onclick="printXsdjb();return false;"
									class="btn_dy">新生登记表打印</a>
							</li>
						</logic:equal>
						
						<!--杭州汽车 -->
						<logic:equal name="xxdm" value="80152">
							<li>
								<a href="javascript:void(0);" onclick="printCjd();return false;"
									class="btn_dy">成绩单打印</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="printHzqcxjk();return false;"
									class="btn_dy">学籍卡打印</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="70002">
							<li>
								<a href="javascript:void(0);" onclick="printXzyyXjk();return false;"
									class="btn_dy">学生学籍卡打印</a>
							</li>
						</logic:equal>
						<!-- 南通科技职业学院 -->
						<logic:equal name="xxdm" value="12684">
							<li>
								<a href="javascript:void(0);" onclick="printHzqcxjk();return false;"
									class="btn_dy" id="btn_ztzyjsxyXjk">学籍卡打印</a>
							</li>
							<!--
							<li>
								<a href="javascript:void(0);" onclick="printXscjb();return false;"
									class="btn_dy">三年制成绩表</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="printXscjbwu();return false;"
									class="btn_dy">五年制成绩表</a>
							</li>
							 -->
						</logic:equal>
						<!-- 浙江大学 -->
						<logic:equal name="xxdm" value="10335">
							<li>
								<a href="javascript:void(0);" onclick="printXsrxdjb();return false;"
									class="btn_dy">新生入学登记表</a>
							</li>
						</logic:equal>
						<!-- 南通高等师范学校 -->
						<logic:equal name="xxdm" value="110501">
							<!-- update 张昌路[982] -->
							<li>
								<a href="javascript:void(0);" onclick="getWord();return false;"
									class="btn_dy">下载学生学籍卡</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();return false;"
									class="btn_dr">导入基本信息</a>
							</li>
						</logic:equal>
						<!-- 华中师范大学 -->
						<logic:equal name="xxdm" value="10511">
							<li>
								<a href="javascript:void(0);" onclick="getXskpWord();return false;"
									class="btn_dy" id="btn_dyCard">下载学生卡片</a>
							</li>
						</logic:equal>
	
						<logic:equal name="writeAble" value="yes">	
<%--						<li><a href="javascript:void(0);" onclick="drxx();return false;" id="btn_dr" class="btn_dr">导入</a></li>--%>
						<li><a href="javascript:void(0);" onclick="drxxNew();return false;" id="btn_dr" class="btn_dr">导入</a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
						--%>
						<logic:equal name="xxdm" value="10052">
							<li>
								<a href="#" onclick="mmcsh('show');return false;"
									class="btn_csh">密码初始化</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10335">
							<li>
								<a href="#" onclick="mmcsh('show');return false;"
									class="btn_csh">密码初始化</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10026">
							<li>
								<a href="#" onclick="mmcsh('show');return false;"
									class="btn_csh">密码初始化</a>
							</li>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10052">
							<logic:notEqual name="xxdm" value="10335">
								<logic:notEqual name="xxdm" value="10026">
									<logic:equal name="writeAble" value="yes">
										<li>
											<a href="#" onclick="mmcsh('show');return false;"
												class="btn_csh">密码初始化</a>
										</li>
									</logic:equal>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
						<!--江西航空学院-->
						<logic:equal name="xxdm" value="13871">
							<logic:notEqual name="writeAble" value="yes">
								<logic:equal value="true" name="bzrfdy">
									<li>
										<a href="#" onclick="mmcsh('show');return false;"
											class="btn_csh">密码初始化</a>
									</li>
								</logic:equal>
							</logic:notEqual>
						</logic:equal>
						<%--西安培华学院 --%>
						<logic:equal name="xxdm" value="11400">
						<%--  
							<li><a href="javascript:void(0);" onclick="szXszCommon('xsz_${xxdm }');return false;" class="btn_dy">设置学生证（仅开发用）</a></li>--%>
					        <li><a href="javascript:void(0);" onclick="dyXszXaph('xsz_${xxdm }');return false;" class="btn_dy">打印学生证</a></li>
							
						</logic:equal>
						<li>
							<a href="#" onclick="xsxxdc();return false;" id="btn_xsxxdy"
								class="btn_dy">学生信息打印</a>
                        </li>
					</ul>
				</div>
				
				<!-- 华中科技大学武昌分校 -->
				<logic:equal name="xxdm" value="12309">
				<div class="buttonbox" id="buttonbox2">
					<ul>
						<li>
							<a href="javascript:void(0);"
								onclick="printZzjsx();return false;" class="btn_dy">团组织关系介绍信打印</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="tyqntj();return false;" class="btn_dy">学生团员基本情况统计</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				
				<!-- 浙江警察学院 -->
				 <logic:equal name="xxdm" value="11483">
					<div class="buttonbox" id="buttonbox2">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="zjjcXshzExportData();return false;"
									class="btn_dc" >毕业生在校表现导出</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="zjjcZcfExportData();return false;"
									class="btn_dc" >学生综合素质导出</a>
							</li>
														<li>
								<a href="javascript:void(0);" onclick="zjjcZhqkExportData();return false;"
									class="btn_dc" >学生表现综合情况导出</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				
				<!-- 咸宁职业技术学院 -->
				<logic:equal name="xxdm" value="13265">
					<div class="buttonbox" id="buttonbox2">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="xnzyjsxyhmcExportData();return false;"
									class="btn_dc" >班级花名册导出</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- 按钮 end-->
				<logic:equal name="xxdm" value="12303">
					<div class="buttonbox" id="buttonbox2">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="getPrintTGB();return false;"
									class="btn_dc" >团干部信息打印</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="getPrintTY();return false;"
									class="btn_dc" >团员信息打印</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- 重庆工程职业技术学院 -->
				<logic:equal name="xxdm" value="12759">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="printSI();return false;"
									class="btn_dy" >学生身份证明打印</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- 重庆工程职业技术学院: end-->
				<!-- 浙江旅游职业技术学院 -->
				<logic:equal name="xxdm" value="12867">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="ycsjTs();return false;"
									class="btn_dy" >异常学生同步</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- 扎兰屯职业学院 -->
				<logic:equal name="xxdm" value="14539">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="ycsjTs();return false;"
									class="btn_dy" >异常学生同步</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>

		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>学生信息列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
