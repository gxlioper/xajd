<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/tqhk/js/hkjg.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"zxdkHkjg.do?method=getHkjgList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckHkjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'�����˺�',name:'hkzh', index: 'hkzh',width:'13%'},
					   {label:'������',name:'hkje', index: 'hkje',width:'13%'},
					   {label:'������',name:'hkbj',index:'hkbj',width:'13%'},
					   {label:'����ʱ��',name:'sqsj',index:'sqsj',width:'13%'}
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function importTqhk(){
				toImportData("ZXDK_TQHK");
				return false;
			}
			
			function exportData(){
				var DCCLBH='zxdk_tqhk.do';
				setSearchTj();//���ø߼���ѯ����
				
				var url = "zxdkHkjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			//����
			function exportConfig(){
				var DCCLBH='zxdk_tqhk.do';
				customExport(DCCLBH, exportData);
			}
			
			function ckHkjg(id){
				showDialog("�鿴�����",800,520,"zxdkHkjg.do?method=viewTqhk&id="+id);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/zxdkSyddk" method="post" styleId="zxdkSyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" class="btn_zj"
								   onclick="addTqhk();return false;" 
								>
								����
								</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="editTqhk();return false;" class="btn_xg"
								>�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="delTqhk();return false;" class="btn_sc"
								>ɾ��</a>
							</li>
							<li><a href="#" class="btn_dr" onclick="importTqhk();return false;">����</a></li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��ǰ�����б�  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
