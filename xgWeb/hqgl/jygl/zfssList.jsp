<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="hqgl/jygl/js/sfss.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"走访宿舍列表",
				pager:"pager",
				url:"jygl_zfss_zfssMg.do?type=query",
				colList:[
				   {label:'key',name:'djid', index: 'djid',key:true ,hidden:true},
				   {label:'职工号',name:'zgh', index: 'zgh',width:'10%'},
				   {label:'姓名',name:'xm', index: 'xm',width:'12%'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'部门',name:'bmmc', index: 'bmmc',width:'15%'},
				   {label:'进入时间',name:'jrsj', index: 'jrsj',width:'15%'},
				   {label:'离开时间',name:'lksj', index: 'lksj',width:'15%'},
				   {label:'走访情况',name:'fwly', index: 'fwly',width:'43%'}
				],
				sortname: "jrsj",
			 	sortorder: "desc"
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["zgh"] = jQuery("#zgh").val();
				map["jrsjks"] = jQuery("#jrsjks").val();
				map["jrsjjz"] = jQuery("#jrsjjz").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function updateZfss(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要修改的记录！");
				} else {
					var url = 'zfss_zfss.do?method=updateZfss&djid='+rows[0]["djid"];
					showWindow('修改走访登记',720,370,url);
				}
			}

			function delZfss(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					jQuery.post("zfss_zfss.do?method=delZfss",{values:ids.toString()},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			}

			function addZfss(){
				showDialog("新增走访登记",720,370,'zfss_zfss.do?method=addZfss');
			}

			function exportZfxxList() {
				customExport("jygl_zfss_zfssMg.do", exportZfxxData,750,500);
			}
			
			// 导出方法
			function exportZfxxData() {
				//setSearchTj();//设置高级查询条件
				var url = "zfss_zfss.do?method=exportZfxxData&dcclbh=" + "jygl_zfss_zfssMg.do";//dcclbh,导出功能编号
				//url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			// 导入
			function drZfxxList(){
				toImportData("IMPORT_N381601");
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
		<html:form action="/zfss_zfss">
		<div class="toolbox">
			<logic:equal value="yes" name="writeAble">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="addZfss();return false;" class="btn_zj">来访登记</a></li>
						<li><a href="javascript:void(0);" onclick="updateZfss();" class="btn_xg">维护离开</a></li>
						<li><a href="javascript:void(0);" onclick="delZfss();" class="btn_sc">删除</a></li>	
						<li><a href="#" onclick="drZfxxList();return false;" class="btn_dr">导入</a>	</li>	
						<li><a href="#" onclick="exportZfxxList();return false;" class="btn_dc">导出</a>	</li>	
					</ul>
				</div>
			</logic:equal>		
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
							<th width="16%">职工号</th>
							<td width="84%">
								<html:text property="zgh" styleId ="zgh"/>
							</td>
						</tr>
						<tr>
							<th>进入时间从</th>
							<td>
							<html:text property="jrsjks" styleId ="jrsjks"  onclick="return showCalendar(this.id,'yyyy-MM-dd',true,'jrsjjz');"/>至
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
				<span>走访登记列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
