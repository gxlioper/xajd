<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsjg/js/knsjglist.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">

		jQuery(function() {
			var gridSetting=initGridSetting();
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		function drxx(){
			if("xn"==jQuery("#sqzq").val()){
				toImportData("IMPORT_N100103_XN");
			} else {
				toImportData("IMPORT_N100103");
			}
			return false;
		}
		
		function export_12688(){
			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var xq_num =  jQuery("a[name=a_name_xq]").length;
			if(xn_num != "1"){
				alertError("学年条件不可为空，且只能选择一个！");
				return false;
			}
			if ( xq_num != "1"){
				alertError("学期条件不可为空，且只能选择一个！ ");
				return false;
			}
			var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
			var xq = jQuery("a[name=a_name_xq]").eq(0).attr("id").replace("a_id_","");
			var url = "xszz_knsjg.do?method=export_12688&xn="+xn+"&xq="+xq;
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}

		var DCCLBHS = "kns_jtjjkn.do";//dcclbh,导出功能编号
		//导出徐州医药个性化
		function exportConfigXzyy(dcclbh){
			DCCLBHS = dcclbh;
			customExport(dcclbh, jgExportData);
		}

		//导出方法
		function jgExportData() {
			setSearchTj();//设置高级查询条件
			var url = "xszz_knsjg.do?method=exportConfigXzyy&dcclbh=" + DCCLBHS;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function exportConfigZjzyy(dcclbh){
			customExport(dcclbh, jgExportDataZjzyy);
		}

		//导出方法
		function jgExportDataZjzyy() {
			setSearchTj();//设置高级查询条件
			var url = "xszz_knsjg.do?method=exportConfigZjzyy&dcclbh=kns_jtjjkn_jtxx_zjzyy.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//下载汇总表
		function downloadHzb() {
			var selObj = jQuery("#xy_ul").find(".selectedValue");
			if (selObj.length == 1) {
				var idArray = jQuery(selObj).attr("id").split("_");
				var indexLast = idArray.length-1;
				var xydm = idArray[indexLast];
				var url = "xszz_knsjg.do?method=printHzb";
				url += "&xydm=" + xydm;
				window.open(url);
			} else if (selObj.length == 0) {
				showAlertDivLayer("请选择一个学院！");
                return false;
			} else {
				showAlertDivLayer("只能选择一个学院！");
				return false;
			}
		}

		//困难生评定导出
		function knspddc() {
			setSearchTj();//设置高级查询条件
			var url = "xszz_knsjg.do?method=knspddc&dcclbh=xg_knsrd_knsrddc.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//困难生家庭情况导出
		function knsjtqkdc() {
			setSearchTj();//设置高级查询条件
			var url = "xszz_knsjg.do?method=knspddc&dcclbh=kns_jtjjkn_jtxx_ahjzdx.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//困难生建档立卡导出
		function knsjdlkdc() {
			setSearchTj();//设置高级查询条件
			var url = "xszz_knsjg.do?method=knspddc&dcclbh=kns_knsjg_knsjdlkdc.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					学生或老师提交的困难生认定，经过审核最终通过的申请结果会进入此菜单<br/>
					用户也可在此处直接维护困难生名单					
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/xszz_knsjg" >
			<input type="hidden" id="iscanCopy" value="${iscanCopy}"/> 
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_knsjgb"/>
			<input type="hidden" id="xbmc" value="<bean:message key='lable.xb' />"/>
			<html:hidden property="sqzq" styleId="sqzq" value="${sqzq}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>
							<logic:notEqual value="10335" name="xxdm">
							<li><a href="javascript:void(0);" onclick="copy();" class="btn_tj">复制</a></li>	
							</logic:notEqual>
						</logic:equal>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="#" onclick="drxx();return false;" class="btn_dr">导入</a></li>
						</logic:equal>
			                  <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>

						<logic:equal value="10657" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="knsrdTjExport();return false;">困难学生统计导出</a></li>
						</logic:equal>
						<logic:equal value="10335" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="knsExport();return false;">困难学生统计汇总导出</a></li>
						<li><a href="#" class="btn_dc" onclick="knstksPercent();return false;" title="点击查看各院系困难与特困生人数">困难与特困生比例</a></li>
						</logic:equal>
						<%-- 华东交通大学理工学院 个性化需求--%>
						<logic:equal value="13431 " name="xxdm">
							<li><a href="javascript:void(0);" onclick="printKnssq('xszz_knsjg.do?method=printJsp');return false;" class="btn_down">下载困难生认定申请表</a></li>	
						</logic:equal>
						<%--非华东交通理工--%>
						<logic:notEqual value="13431" name="xxdm">
						<li><a href="javascript:void(0);" onclick="printKnssq('xszz_knsjg.do?method=printJsp');return false;" class="btn_down">下载登记表</a></li>	
						</logic:notEqual>
						<logic:equal value="12898" name="xxdm">
						<li><a href="javascript:void(0);" onclick="exportKnsjg('dashk');return false;" class="btn_down">导出档案数据库</a></li>
						<li><a href="javascript:void(0);" onclick="exportKnsjg('rdpxb');return false;" class="btn_down">导出认定排序表</a></li>
						</logic:equal>
						<logic:equal value="12930" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="rdhzbExport();return false;">下载汇总表</a></li>
						</logic:equal>
						<logic:equal value="12688" name="xxdm">
						<li><a href="#" onclick="export_12688();return false;" class="btn_dc">导出统计表</a></li>
						</logic:equal>
						<logic:equal value="11998" name="xxdm">
			                  <li><a href="#" class="btn_dc" onclick="exportConfigXzyy('kns_jtjjkn.do');return false;">家庭经济困难学生导出</a></li>
			                  <li><a href="#" class="btn_dc" onclick="exportConfigXzyy('kns_jtjjkn_jtxx.do');return false;">家庭经济信息录入导出</a></li>
						</logic:equal>
						<logic:equal value="10344" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="exportConfigZjzyy('kns_jtjjkn_jtxx_zjzyy.do');return false;">家庭经济信息录入导出</a></li>
						</logic:equal>
						<logic:equal value="12309" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rdhzbExport();return false;">困难学生认定汇总表</a></li>
							<li><a href="#" class="btn_dc" onclick="exportConfigZjzyy('kns_jtjjkn_jtxx_zjzyy.do');return false;">家庭经济信息录入导出</a></li>
						</logic:equal>
						<logic:equal value="10098" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rdhzbExport();return false;">困难学生认定汇总表</a></li>
							<li><a href="#" class="btn_dc" onclick="exportConfigZjzyy('kns_jtjjkn_jtxx_zjzyy.do');return false;">家庭经济信息录入导出</a></li>
						</logic:equal>
						<logic:equal value="11842" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="exportConfigZjzyy('kns_jtjjkn_jtxx_zjzyy.do');return false;">家庭经济信息录入导出</a></li>
						</logic:equal>
						<%--杭师大个性化需求--%>
						<logic:equal value="10346" name="xxdm">
							<li><a href="javascript:void(0);" onclick="downloadHzb();return false;" class="btn_down">下载信息汇总表</a></li>
						</logic:equal>
						
						<!-- 安徽建筑大学 -->
						<logic:equal value="10878" name="xxdm">
							<li><a href="javascript:void(0);" onclick="knspddc();return false;" class="btn_dc">困难生认定名单导出</a></li>
							<li><a href="javascript:void(0);" onclick="knsjtqkdc();return false;" class="btn_dc">困难生家庭信息导出</a></li>
							<li><a href="javascript:void(0);" onclick="knsjdlkdc();return false;" class="btn_dc">困难生建档立卡导出</a></li>
						</logic:equal>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 困难生结果列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
