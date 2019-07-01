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
				caption:"�����б�",
				pager:"pager",
				url:"xpjpy_zsdysj.do?method=getZsdysjList&type=query",
				colList:[
				   	{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'����',name:'xm', index: 'xm',width:'10%'},
					{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
					{label:'����',name:'xmmc', index: 'xmmc',width:'25%'},
					{label:'����Ӣ��',name:'xmpy', index: 'xmpy',width:'20%'},
					{label:'����Ӣ��',name:'xmywmc', index: 'xmywmc',width:'30%'}
				],
				sortname: "xmmc",
			 	sortorder: "asc"
			}
			
			/**�߼���ѯ*/
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			/**ѧ������*/
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='xhView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
			}

			/**��������鿴*/
			function xhView(id,xh){
				showDialog("���������ѯ",800,473,"xpjpy_pjjg.do?method=viewPjjg&id="+id+"&xh="+xh);
			}
			
			//֤���ӡ�������
			function exportZs(){
		 		setSearchTj();//���ø߼���ѯ����
		 		var url = "xpjpy_zsdysj.do?method=zsdysjExport";
		 		url = addSuperSearchParams(url);//���ø߼���ѯ����
		 		jQuery("form").eq(0).attr("action", url);
		 		jQuery("form").eq(0).submit(); 
			}

		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpjpy_zsdysj">		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="exportZs();return false;" class="btn_dc">֤���ӡ����</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>