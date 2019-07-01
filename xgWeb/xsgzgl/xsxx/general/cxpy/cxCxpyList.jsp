<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/general/cxpy/js/cxpy.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生操行信息列表",
				pager:"pager",
				url:"xsxx_gygl_cxcxpy.do?method=cxCxpyList&type=query",
				colList:[
				   {label:'ID',name:'pk',index:'pk',key:true ,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'15%' ,formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'学年',name:'xn', index: 'xn',width:'10%'},
				   <logic:notEqual name="xxdm" value="13943">
				   {label:'学期',name:'xqmc', index: 'xqmc',width:'10%'},
				   </logic:notEqual>
				   {label:'专业',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'20%'},
				   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
				   {label:'等级',name:'cxdjmc', index: 'cxdjmc',width:'10%'}
				]
				
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='cxpyView(\""+rowObject["pk"]+"\");'>"+cellValue+"</a>";
			}

			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}
			function delCxpy(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("流程数据不能删除！");
							return false;
						}
					}
					
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
					jQuery.post("xsxx_gygl_cxcxpy.do?method=delCxpy",{values:ids.toString()},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
				}
			}


			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="addCxpy()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="delCxpy()" class="btn_sc">删除</a></li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="viewCxpy()" class="btn_ck">查看</a></li>
						
						<logic:equal name="writeAble" value="yes">
							<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a><li>
						</logic:equal>	
						
						<li><a href="#" class="btn_dc" onclick="exportData();return false;">导出</a></li>
						
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
				<span> 学生操行信息列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
