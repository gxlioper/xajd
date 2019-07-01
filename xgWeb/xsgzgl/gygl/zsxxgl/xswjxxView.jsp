<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			//��ѯ�����
			var gridSetting ;
			jQuery(function(){
				var url = "gyglnew_zsxxgl.do?method=wjxx&type=query";
				url += "&xh=" + jQuery("#xh").val();
				gridSetting = {
						caption:"",
						pager:"pager",
						url:url,			
						colList:[
							{label:'ѧ��',name:'xn', index: 'xn',width:'15%'},
							{label:'ѧ��',name:'xqmc', index: 'xqmc',width:'5%'},
							{label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
							{label:'����',name:'xm', index: 'xm',width:'10%'},
							{label:'�༶',name:'bjmc', index: 'bjmc',width:'15%'},
							{label:'�������',name:'cflbmc', index: 'cflbmc',width:'12%'},
							{label:'����ԭ��',name:'cfyymc', index: 'cfyymc',width:'10%'},
							{label:'����ʱ��',name:'fwsj', index: 'fwsj',width:'10%'},
							{label:'���Ľ��',name:'fwjg', index: 'fwjg',width:'10%'},
							{label:'�����ĺ�',name:'cfwh', index: 'cfwh',width:'10%'}	
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
				showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
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
								<span>ѧ��Υ����ϸ��Ϣ</span>
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
