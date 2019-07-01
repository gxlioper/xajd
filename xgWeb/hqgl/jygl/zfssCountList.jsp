<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"走访宿舍统计列表",
				pager:"pager",
				url:"jygl_zfss_zfssCt.do?type=query",
				colList:[
				   {label:'姓名',name:'xm', index: 'xm',width:'60%'},
				   {label:'次数',name:'cs', index: 'cs',width:'40%'}
				],
				sortname: "xm",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["zgh"] = jQuery("#zgh").val();
				map["bfwss"] = jQuery("#bfwss").val();
				map["jrsjks"] = jQuery("#jrsjks").val();
				map["jrsjjz"] = jQuery("#jrsjjz").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function exportZfxxCountList() {
				customExport("jygl_zfss_zfssCt.do", exportZfxxCountData,750,500);
			}
			
			// 导出方法
			function exportZfxxCountData() {
				//setSearchTj();//设置高级查询条件
				var url = "zfss_zfss.do?method=exportZfxxCountData&dcclbh=" + "jygl_zfss_zfssCt.do";//dcclbh,导出功能编号
				//url = addSuperSearchParams(url);//设置高级查询参数
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
	<html:form action="/zfss_zfss">
		<div class="toolbox">
		<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="#" onclick="exportZfxxCountList();return false;" class="btn_dc">导出</a>	</li>			
				</ul>
			</div>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										查 询
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset()">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">姓名</th>
							<td width="84%">
								<html:text property="zgh" styleId ="zgh"/>
							</td>
						</tr>
						<tr>
							<th>进入时间从</th>
							<td>
								<html:text property="jrsjks" styleId ="jrsjks" onclick="return showCalendar(this.id,'yyyy-MM-dd',true,'jrsjjz');"/>至
								<html:text property="jrsjjz" styleId ="jrsjjz" onclick="return showCalendar(this.id,'yyyy-MM-dd',false,'jrsjks');"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>走访宿舍统计列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
