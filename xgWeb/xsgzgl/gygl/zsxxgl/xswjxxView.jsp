<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			//查询结果集
			var gridSetting ;
			jQuery(function(){
				var url = "gyglnew_zsxxgl.do?method=wjxx&type=query";
				url += "&xh=" + jQuery("#xh").val();
				gridSetting = {
						caption:"",
						pager:"pager",
						url:url,			
						colList:[
							{label:'学年',name:'xn', index: 'xn',width:'15%'},
							{label:'学期',name:'xqmc', index: 'xqmc',width:'5%'},
							{label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
							{label:'姓名',name:'xm', index: 'xm',width:'10%'},
							{label:'班级',name:'bjmc', index: 'bjmc',width:'15%'},
							{label:'处分类别',name:'cflbmc', index: 'cflbmc',width:'12%'},
							{label:'处分原因',name:'cfyymc', index: 'cfyymc',width:'10%'},
							{label:'发文时间',name:'fwsj', index: 'fwsj',width:'10%'},
							{label:'发文结果',name:'fwjg', index: 'fwjg',width:'10%'},
							{label:'处分文号',name:'cfwh', index: 'cfwh',width:'10%'}	
						],
							multiselect:false,
							sortname: "fwsj",
						 	sortorder: "desc"
				};
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}
			function zxsxxView(xh) {
				showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
						+ "&xs");
			}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_zsxxgl">
			<html:hidden property="xh" styleId="xh"/>
			<div class="tab">
				<table class="formlist" width="95%">
					<thead>
						<tr style="height: 22px">
							<th colspan="4">
								<span>学生违纪详细信息</span>
							</th>
						</tr>
					</thead>
				</table>			
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</html:form>
	</body>
</html>
