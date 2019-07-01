<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"日志列表",
				pager:"pager",
				url:"log.do?method=getLogList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'用户名',name:'username', index: 'username'},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'用户组',name:'zmc', index: 'zmc'},
				   {label:'IP地址',name:'ip', index: 'ip'},
				   {label:'操作时间',name:'dotime', index: 'dotime'},
				   {label:'描述',name:'description', index: 'description'},
				   {label:'操作系统',name:'osname', index: 'osName'},
				   {label:'浏览器',name:'browsername', index: 'browserName'},
				   {label:'浏览器版本',name:'browserversion', index: 'browserVersion'}
				],
				sortname: "dotime",
			 	sortorder: "desc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["username"] = jQuery("#username").val();
				map["description"] = jQuery("#description").val();
				map["czkssj"] = jQuery("#czkssj").val();
				map["czjssj"] = jQuery("#czjssj").val();
				map["zdm"] = jQuery("#zdm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			
			function exportConfig() {
				customExport("log.do", ExportDatas);
			}
			
			function ExportDatas() {
				var url = "log.do?method=exportData";//dcclbh,导出功能编号
				//url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				//console.log(jQuery("form").eq(0));
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
	<html:form action="/log" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
	<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc" >导出</a>
						</li>
					
					</ul>
				</div>
			</div>
		<div class="toolbox">
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="8">
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
							<th>用户名</th>
							<td >
								<html:text property="username"  styleId="username"  style="width:100px"></html:text>
							</td>
							<th>操作模块(描述)</th>
							<td>
							<html:text property="description"  styleId="description"  style="width:100px"></html:text>
							</td>
							<th>操作日期</th>
							<td>
								<html:text property="czkssj" styleId="czkssj" onfocus="return showCalendar('czkssj','yyyy-MM-dd',true,'czjssj');" maxlength="30" style="width:70px"></html:text>
								至  
								<html:text property="czjssj" styleId="czjssj" onfocus="return showCalendar('czjssj','yyyy-MM-dd',false,'czkssj');" maxlength="30" style="width:70px"></html:text>
							</td>
						
							<th>
									用户组
							</th>
								<td>
									<html:select property="zdm" style="width:100px" styleId="zdm">
										<html:option value="">--请选择--</html:option>
										<html:options collection="yhzList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 操作日志列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
