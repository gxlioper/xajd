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
				var url = "gyglnew_zsxxgl.do?method=pllxview&type=query";
				url += "&nj=" + jQuery("#nj").val();
				url += "&xydm=" + jQuery("#xydm").val();
				url += "&zydm=" + jQuery("#zydm").val();
				gridSetting = {
						caption:"",
						pager:"pager",
						url:url,			
						colList:[
							{label:'ѧ��',name:'xh', index: 'xh',width:'12%'},
							{label:'����',name:'xm', index: 'xm',width:'12%'},
							{label:'key',name:'id', index: 'id',key:true ,hidden:true},
							{label:'¥������',name:'ldmc', index: 'lddm',width:'10%'},
							{label:'���Һ�',name:'qsh', index: 'qsh',width:'8%'},
							{label:'��λ��',name:'cwh', index: 'cwh',width:'8%'},
							{label:'��λ�Ա�',name:'qsxb', index: 'qsxb',width:'4%'},
							{label:'�༶����',name:'bjmc', index: 'bjdm',width:'12%'}	
						],
						multiselect:false
						};
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_zsxxgl">
			<html:hidden property="nj" styleId="nj"/>
			<html:hidden property="xydm" styleId="xydm"/>
			<html:hidden property="zydm" styleId="zydm"/>
			<div class="tab">
				<table class="formlist" width="95%">
					<thead>
						<tr style="height: 22px">
							<th colspan="4">
								<span><font color="blue">${nj}&nbsp;${xymc}&nbsp;${zymc}&nbsp;&nbsp;</font>������ѧ����Ϣ</span>
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
