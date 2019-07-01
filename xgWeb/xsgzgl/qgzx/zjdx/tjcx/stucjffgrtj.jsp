<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "tjcx_zjdx.do?method=getStucjffgrtj&type=query",
				colList : [ {label: '学号',name :'xh',index:'xh',width:'15%'},
							{label: '姓名',name :'xm',index:'xm',width:'5%'},
							{label: '用人单位',name:'bmmc',index:'bmmc',width:'15%'},
							{label: '发发金额',name:'bcje',index:'bcje',width:'5%'},
							{label: '备注',name:'bz',index:'bz',width:'15%'}],
				multiselect:false,
				usedefined : true,
				uselastrow:true,
				uselastid:"dataTable1",
				usercols:16,
				useurl:"tjcx_zjdx.do?method=getStucjffgrtjSum"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		
		//高级查询条件
		function searchRs(){
			//默认年度查询
			if(jQuery("[name='tj_nd']").parent().find(".selectedValue").length != 1){
				showAlertDivLayer("年度高级查询条件有且只能选择一个！");
				return false;
			}
			//默认月份查询
			if(jQuery("[name='tj_yf']").parent().find(".selectedValue").length != 1){
				showAlertDivLayer("月份高级查询条件有且只能选择一个！");
				return false;
			}
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		
		//导出
		var DCCLBH='cjff_tjcx_zjdx_stucjffgrtj.do';
		function exportConfig(){ 
			customExport(DCCLBH, exportData);
		}
		function exportData(){
			setSearchTj();//设置高级查询条件
			var url = "tjcx_zjdx.do?method=exportDataStucjffgrtj&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
			
				<logic:equal value="true" name="sfqggly">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</ul>
				</div>
				</logic:equal>
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生报酬发放明细汇总&nbsp;&nbsp;<font color="blue">单位(元)</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" >
					<thead>
							<tr class="nowrap">
								<th width="6%" style="text-align:center" sortname="xh">学号</th>
								<th width="10%" style="text-align:center" sortname="xm">姓名</th>
								<th width="15%" style="text-align:center" sortname="bmmc">用人单位</th>
								<th width="6%" style="text-align:center" >发放金额</th>
								<th width="15%" style="text-align:center">备注</th>
							</tr>
					</thead>
				</table>
			<div style="display:none">
				<table  class="dateline" >
					<tbody id="dataTable1">
						<tr >
							<td width="35%" style="text-align:center" colspan="3">
								<font color="red" style="font-weight:bold">总计</font>
							</td>
							<td width="5%" style="text-align:center" name="zje" colspan="2">
								${zje}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="pager"></div>
		</div>
	</body>
</html>