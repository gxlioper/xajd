<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzxmjg/js/zzxmjglist.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
		
		function drxx(){			
			toImportData("IMPORT_N100104");
			return false;
		}

		//导入
		function dr() {
			// 调用通用的导入function，参数是导入功能模块代码。
			toImportDataNew("IMPORT_ZZXMJG");
			return false;

		}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_knsjg" >
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_zzxmjgb"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<input type="hidden" id="iscanCopy" value="${iscanCopy}"/> 
			
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>	
							<logic:equal value="10335" name="xxdm">
							<li><a href="javascript:void(0);" onclick="copy();" class="btn_sz">考核复制</a></li>						
							</logic:equal>
							<logic:equal value="13011" name="xxdm">
<%--								<li><a href="javascript:void(0);" onclick="qdjddr();return false;" class="btn_dr" >导入</a></li>--%>
								<li><a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr" >导入</a></li>							
							</logic:equal>
							<logic:notEqual value="13011" name="xxdm">
								<logic:notEqual value="10335" name="xxdm">
									<li><a href="#" onclick="drxx();return false;" class="btn_dr">导入</a></li>
								</logic:notEqual>					
								<logic:equal value="10335" name="xxdm">
									<li><a href="javascript:void(0);" onclick="zzjgdr();return false;" class="btn_dr" >导入</a></li>
								</logic:equal>								
							</logic:notEqual>
							
						</logic:equal>
						
						<li>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
						</li>
						
						<%--<li><a href="#" class="btn_dc" onclick="exportData();return false;">导出数据</a></li>--%>
						<!--  
						<li><a href="javascript:void(0);" onclick="printXmsq('xszz_zzxmjg.do?method=printJsp');return false;" class="btn_dy">打印申请表</a></li>		
						-->
						<%--华东理工个性化 --%>
						<logic:equal value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载申请审批表</a></li>	
						</logic:equal>
						<logic:notEqual value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>
						</logic:notEqual>
							
						<logic:equal value="12861" name="xxdm">
						      <li><a href="javascript:void(0);" onclick="exportzzHzData();return false;" class="btn_dc">汇总导出</a></li>	
						</logic:equal>
						<%--山东畜牧兽医个性化打印start --%>			
						<logic:equal value="12947" name="xxdm">
						      <li><a href="javascript:void(0);" onclick="getshzxjHzexcel();return false;" class="btn_dc">社会助学金汇总导出</a></li>	
						      <%-- 
						      <li><a href="javascript:void(0);" onclick="getgjlzjHzexcel();return false;" class="btn_dc">国家励志奖学金汇总导出</a></li>	--%>
						      <%-- 
						      <li><a href="javascript:void(0);" onclick="getSdxm_gjzxjhzexcel();return false;" class="btn_dc">国家助学金汇总导出</a></li>	
						      --%>
						      <%-- 
						      <li><a href="javascript:void(0);" onclick="getSdxm_gjlzjhzmbexcel();return false;" class="btn_dc">国家励志奖学金申请表汇总模板导出</a></li>	
						      --%>
						</logic:equal>
						<%--山东畜牧兽医个性化打印end --%>
						<!-- 安徽建筑大学 -->
						<logic:equal value="10878" name="xxdm">
							   <li><a href="javascript:void(0);" onclick="gjjxjdc();return false;" class="btn_dc">国家奖学金名单导出</a></li>
							   <li><a href="javascript:void(0);" onclick="gjzxjdc();return false;" class="btn_dc">国家助学金名单导出</a></li>
							   <li><a href="javascript:void(0);" onclick="gjlzjdc();return false;" class="btn_dc">国家励志奖学金名单导出</a></li>
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
				<span> 资助结果列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
