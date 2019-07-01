<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/uicomm.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/commUtil.js"></script>
		<script language="javascript" src="xsgzgl/szdw/fdypx/js/fdypxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<!-- 导入功能需要 -->
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			var path = jQuery("#path").val();
			var url = "szdw_dwwh.do?method=searchDwwh&path="+path;
			var ie = "ie";
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		//江西科技师范大学思政队伍辅导员配备表导出个性化
		function pbbExportData() {
				var url = "szdw_dwwh.do?method=pbbExportData";
				var map = getSuperSearch();
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		//江西科技师范大学思政队伍辅导员档案信息导出个性化
		function dabExportData() {
			var url = "szdw_dwwh.do?method=fdyxxwhExportJxsf";
			var map = getSuperSearch();
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		//江西科技师范大学思政队伍辅导员带班情况表导出个性化
		function dbqkbData() {
				var url = "szdw_dwwh.do?method=dbqkbData";
				var map = getSuperSearch();
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		
		function configureExportData() {
			customExport("szdw_general_dwwh.do", fdyxxwhExportData);
		}
		
		// 导出方法
		function fdyxxwhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "szdw_dwwh.do?method=fdyxxwhExportData&dcclbh=" + "szdw_general_dwwh.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function viewFdyxx(){
			var url='szdw_fdyxx.do?method=fdyxxView&zgh='
			var check = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var num = check.length;
			if(num == 1){
				var zgh = jQuery(check[0]).val();
				//showTopWin("/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh="+zgh,830,600); .
				showDialog('', 830, 500, url+zgh);
				//window.open ('/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh='+zgh);
			}else{
				showAlertDivLayer("请勾选<font color='blue'>一条记录</font>进行查看");
				return false;
			}
		}
		function viewjgz(zgh){
			var url='szdw_fdyxx.do?method=fdyxxView&zgh='+zgh;
			showDialog('', 830, 500, url);
		}
		// 思政队伍设置
		function szdwSz_10352(){
			var array = new Array();
			jQuery("[name=primarykey_checkVal]:checked").each(function(i){
				array[i] = escape(jQuery(this).val());
			});
			if(array.length == 0){
				showAlertDivLayer("请勾选<font color='blue'>至少一条记录</font>");
				return false;
			}
			var ids = array.join('!!array!!');
			showDialog('思政队伍设置',450,150,'szdw_dwwh.do?method=szdwSz_10352&ids='+ids);
		}
		
		jQuery(function(){
			onShow();
		})
		
		//山西财经大学教师登记表导出
		function getSxCjDxJsdjbExport(){
			var check = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			if(check.length == 0){
				showAlertDivLayer("请勾选<font color='blue'>至少一条记录</font>");
				return false;
			}
			
			var url = "";
			if(check.length == 1){
				var zgh = jQuery(check[0]).val();
				url = "szdw_fdyxx.do?method=getJsdj";
				url += "&zgh=" + zgh;
			}else{
				var zghs = "";
				for(var i=0;i<check.length;i++){
					zghs +=jQuery(check[i]).val()+",";
				}
				url = "szdw_fdyxx.do?method=getJsdjTy";
				url += "&value=" + zghs;
			}
			
			window.open(url);
		}
		</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span> 在此处维护教师信息，需要登录系统的老师需要在“用户维护”菜单中进行分组操作 </span>
			</p>
			<a class="close" title="隐藏"
				onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/general_szdw" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="path" value="${path}" />

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 begin-->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" class="btn_zj"
									onclick="createDwwhDiv('insert');return false;"> 增加 </a>
							</li>
							<li>
								<a href="#" class="btn_xg"
									onclick="checkDwwhUpdate();return false;"> 修改 </a>
							</li>
							<li>
								<a href="#" class="btn_sc" onclick="deleteDwwh();return false;">
									删除 </a>
							</li>
							<li>
								<a href="#" class="btn_ck" onclick="viewFdyxx();return false;">
									查看 </a>
							</li>
							<li>
							<logic:equal value="10698" name="xxdm">
								<a href="#" id="btn_dr"
									onclick="toImportDataNew('IMPORT_N100108_10698'); return false;"
									class="btn_dr">导入</a>
							</logic:equal>
							<logic:equal value="10080" name="xxdm">
								<a href="#" id="btn_dr"
									onclick="toImportDataNew('IMPORT_N100108_10698'); return false;"
									class="btn_dr">导入</a>
								</logic:equal>
							<logic:notEqual value="10698" name="xxdm">
							<logic:notEqual value="13431" name="xxdm">
								<logic:notEqual value="10080" name="xxdm">
								<a href="#" id="btn_dr"
										onclick="toImportData('IMPORT_N100108'); return false;"
										class="btn_dr">导入</a>
								</logic:notEqual>
							</logic:notEqual>
							</logic:notEqual>
							</li>
							<logic:equal value="11318" name="xxdm">
								<li>
									<a href="#" class="btn_dc"
										onclick="pbbExportData();return false;">配备表导出</a>
								</li>
							</logic:equal>
							<logic:equal value="11318" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="dbqkbData();return false;">带班情况表导出</a>
								</li>
							</logic:equal>
							<logic:equal value="11318" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="dabExportData();return false;">档案表导出</a>
								</li>
							</logic:equal>
							
							<logic:equal value="10335" name="xxdm">
								<logic:equal value="zf01" name="userName">
									<li>
										<a href="#" class="btn_dc"
											onclick="configureExportData();return false;">导出</a>
									</li>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="10335" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="configureExportData();return false;">导出</a>
								</li>
							</logic:notEqual>
							
							<logic:equal value="10125" name="xxdm">
								<li>
									<a href="#" class="btn_dy"
										onclick="getSxCjDxJsdjbExport();return false;">教师登记表</a>
								</li>
							</logic:equal>
							<logic:equal value="14073" name="xxdm">
								<li>
									<a href="#" class="btn_dy"
										onclick="getSxCjDxJsdjbExport();return false;">教师登记表</a>
								</li>
							</logic:equal>
							<%--<logic:equal value="13431" name="xxdm">--%>
								<li>
									<a href="#" class="btn_dy"
										onclick="getSxCjDxJsdjbExport();return false;">教师登记表</a>
								</li>
							<%--</logic:equal>--%>
							<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>--%>
							<logic:equal value="10352" name="xxdm">
								<li>
									<a href="#" class="btn_sz" onclick="szdwSz_10352();return false;">思政队伍设置</a>
								</li>
							</logic:equal>
							<!-- 北京中医药大学 教师身份维护 -->
							<logic:equal value="10026" name="xxdm">
								<li>
									<a href="#" class="btn_csh" onclick="jssfPlwh();return false;">教师身份维护</a>
								</li>
							</logic:equal>
						</logic:equal>
						<!-- 读写权 end-->
					</ul>
				</div>
				<!-- 按钮 end-->

				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
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
				<div id="div_rs" style="" class="con_overlfow">
				</div>

				<!--分页显示-->
				<div style="clear: both;">
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=generalSzdwGeneralForm"></jsp:include>
					<script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->

			<!-- 队伍维护Div begin -->
			<div id="div_dwwh" style="display: none">

			</div>
			<!-- 队伍维护Div end -->

			<!-- 用户库Div begin -->
			<div id="div_yhk" style="display: none">

			</div>
			<!-- 用户库Div end -->

			<!-- 院校兼任Div begin -->
			<div id="div_yxjr" style="display: none">

			</div>
			<!-- 院校兼任Div end -->

			<!-- 班级信息Div begin -->
			<div id="div_bjxx" style="display: none">

			</div>
			<!-- 班级信息Div end -->

			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>