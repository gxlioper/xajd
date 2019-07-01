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
				url:"xpj_tjcx.do?method=viewZsdy&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'����',name:'xm', index: 'xm',width:'10%'},
					{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
					//{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
					//{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
					//{label:'�꼶',name:'nj', index: 'nj',width:'7%'},
					//{label:'�༶',name:'bjmc', index: 'bjdm',width:'28%'},
					//{label:'��������',name:'pjzq', index: 'pjzq',width:'13%'},
					{label:'����',name:'xmmc', index: 'xmmc',width:'25%'},
					{label:'����Ӣ��',name:'xmpy', index: 'xmpy',width:'20%'},
					{label:'����Ӣ��',name:'xmywmc', index: 'xmywmc',width:'30%'}
					//{label:'���',name:'xmje', index: 'xmje',width:'11%'}
				],
				sortname: "XSXH asc,xm",
			 	sortorder: "asc"
			}
			//�߼���ѯ
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			//��������鿴
			function xhView(id,xh){
				showDialog("���������ѯ",800,473,"xpj_pjjg.do?method=pjxmjgView&id="+id+"&xh="+xh);
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='xhView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			//֤���ӡ�������
			function exportZs(){
				/*var url="xpj_tjcx.do?method=exportZs";
		 		window.open(url);*/

		 		setSearchTj();//���ø߼���ѯ����
		 		var url = "xpj_tjcx.do?method=exportZsNew";
		 		url = addSuperSearchParams(url);//���ø߼���ѯ����
		 		jQuery("form").eq(0).attr("action", url);
		 		jQuery("form").eq(0).submit(); 
			}

			//У�轱ѧ���ӡ��������
			function exportXsjxj(){
				var lxmc='002';
				exportData(lxmc);
			}

			//���轱ѧ���ӡ��������
			function exportWsjxj(){
				var lxmc='001';
				exportData(lxmc);
			}

			//�����ƺŴ�ӡ��������
			function exportRych(){
				var lxmc='004';
				exportData(lxmc);
			}

			function exportData(lxmc){
				var DCCLBH = "pj_zsdy.do";
				var url = "xpj_tjcx.do?method=exportData&dcclbh=" + DCCLBH+"&lxmc="+lxmc;//dcclbh,�������ܱ��
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
		<html:form action="/xpj_tjcx">		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="exportZs();return false;" class="btn_dc">֤���ӡ����</a>
						</li>
<%--						<li>--%>
<%--							<a href="javascript:void(0);" onclick="exportXsjxj();return false;" class="btn_dc">У�轱ѧ���ӡ��������</a>--%>
<%--						</li>--%>
<%--						<li>--%>
<%--							<a href="javascript:void(0);" onclick="exportWsjxj();return false;" class="btn_dc">���轱ѧ���ӡ��������</a>--%>
<%--						</li>--%>
<%--						<li>--%>
<%--							<a href="javascript:void(0);" onclick="exportRych();return false;" class="btn_dc">�����ƺŴ�ӡ��������</a>--%>
<%--						</li>--%>
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
