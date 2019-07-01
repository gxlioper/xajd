<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"心理危机个案列表",
				pager:"pager",
				url:"xlzx_xlwjga_xlwjgafkgl.do?method=xlwjgafkManage&type=query",
				colList:[
				   {label:'主键',name:'id', index: 'id',key:true,hidden : true},
				   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				  {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				  {label:'性别',name:'xb', index: 'xb',width:'7%'},
				  {label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
				  {label:'联系电话',name:'lxdh', index: 'lxdh',width:'10%'},
				   {label:'上报人',name:'sbrxm', index: 'sbrxm',width:'10%'},
				   {label:'上报人联系电话',name:'sbrlxfs', index: 'sbrlxfs',width:'10%'},
				   {label : '危机程度',name : 'wjcdmc',index : 'wjcdmc',width : '8%'},
				   {label : '中心反馈',name : 'zzfkmc',index : 'zzfkmc',width : '8%'},
				   {label : '危机个案状态',name : 'wjgabzmc',index : 'wjgabzmc',width : '9%'},
				   {label : '危机程度代码',name : 'wjcd',index : 'wjcd',hidden : true},
				   {label : '中心反馈代码',name : 'zzfk',index : 'zzfk',hidden : true},
				   {label : '危机个案状态代码',name : 'wjgabz',index : 'wjgabz',hidden : true},
				   {label : '上报时间',name : 'sbsj',index : 'sbsj',hidden : true}
				],
				sortname: "sbsj",
			 	sortorder: "desc"
			}
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='xlwjgafkView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}
			function xlwjgafkView(xh) {
				showDialog("查看心理危机个案", 750,500, "xlzx_xlwjga_xlwjgasbgl.do?method=viewXlwjgasb&xh=" + xh);
			}
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要反馈的记录！");
				} else {
					if(rows[0]["wjgabz"] != '1'){
						showAlertDivLayer("请选择危机个案的记录！");
						return false;
					}
					var url = 'xlzx_xlwjga_xlwjgafkgl.do?method=updateXlwjgafk&xh='+rows[0]["xh"];
					var title = "心理危机个案反馈";
					showDialog(title,520,250,url);
				}
			}
			var DCCLBH = "xlzx_xlwjga_xlwjgafk.do";//dcclbh,导出功能编号
			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, kycxxmjgExportData);
			}
			// 导出方法
			function kycxxmjgExportData() {
				setSearchTj();//设置高级查询条件
				var url = "xlzx_xlwjga_xlwjgafkgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
	<html:form action="/xlzx_xlwjga_xlwjgafkgl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">反馈</a></li>
					</logic:equal>
					<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
		</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>心理危机个案列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
