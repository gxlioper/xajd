<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "��ʷ�ɼ��б�",
					pager : "pager",
					radioselect:false,
					url : "khgljgcx.do?method=lscpjgList&type=query",
					colList : [
						{label : 'ѧ��',name : 'xn',index : 'xn',width : '15%'}, 
					   {label : 'ѧ��',name : 'xh',index : 'xh',width : '15%',key:true}, 
					   {label : 'xmid',name : 'xmid',index : 'xmid',width : '15%',hidden:true}, 
					   {label : '����',name : 'xm',index : 'xm',width : '10%'}, 
					   {label : '�Ա�',name : 'xb',index : 'xb',width : '5%'}, 
					   {label : '�꼶',name : 'nj',index : 'nj',width : '8%'}, 
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label : '�༶',name : 'bjmc',index : 'bjmc',width : '15%'},
					   {label : '������',name : 'zpzf',index : 'zpzf',width : '8%'},
					   {label : '��������',name : 'bzzf',index : 'bzzf',width : '8%'},
					   {label : '����������',name : 'bzrzf',index : 'bzrzf',width : '8%'},
					   {label : '�ܷ�',name : 'zf',index : 'zf',width : '8%'},
					   {label : '�༶����',name : 'pm',index : 'pm',width : '10%'}
					 
					   ]
				}

			jQuery(function() {
					gridSetting["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting);
			});
			
			//�߼���ѯ
			function searchRs(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			}
						
		</script>
	</head>
	<body>
	<html:form action="/khgljgcx" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li id="li_xz"><a href="javascript:void(0);" onclick="getCpcjWord();" class="btn_xg">�����ɼ�����</a></li>
						<li id="li_dc"><a href="javascript:void(0);" onclick="bjzccjzbxz();" class="btn_dc">�༶�۲�ɼ��ܱ�����</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						�����ɼ�
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
