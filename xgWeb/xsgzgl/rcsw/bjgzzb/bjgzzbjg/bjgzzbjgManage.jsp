<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption:"�ܱ��б�",
						pager:"pager",
						url:"rcsw_xsgzzb_xsgzzbjggl.do?method=xsgzzbjgManage&type=query&gzzblx=bj",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'��д���û���',name:'lrr', index: 'lrr',hidden:true},
						   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'7%'},
						   {label:'�ܴ�',name:'zcmc', index: 'zcmc',width:'7%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'13%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'13%'},
						   {label:'�༶',name:'bjmc', index: 'bjmc',width:'13%',formatter:bjmcLink},
						   {label:'Ӧ��/ʵ��/���/δ��<br/>���ˣ�',name:'rstj', index: 'rstj',width:'9%'},
						   {label:'��дʱ��',name:'lrsj', index: 'lrsj',width:'9%'},
						   {label:'��д��',name:'lrrxm', index: 'lrrxm',width:'9%'}
						],
						sortname: "lrsj",
					 	sortorder: "desc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
	
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
	
			function xsgzzbjgView(jgid) {
				showDialog("�ܱ��鿴", 720, 340, "rcsw_xsgzzb_xsgzzbjggl.do?method=viewXsgzzbjg&gzzblx=bj&jgid=" + jgid);
			}
	
			function bjmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='xsgzzbjgView(\""
						+ rowObject["jgid"] + "\");'>" + cellValue
						+ "</a>";
			}
	
			var DCCLBH = "rcsw_bjgzzb_bjgzzbjg.do";//dcclbh,�������ܱ��
			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, xsgzzbjgExportData);
			}
			// ��������
			function xsgzzbjgExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "rcsw_xsgzzb_xsgzzbjggl.do?method=exportData&gzzblx=bj&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			// ��ӡ
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="rcsw_xsgzzb_xsgzzbjggl.do?method=getDjbZip&gzzblx=bj&value="+ids;
					window.open(url);
				 } else {
					var url="rcsw_xsgzzb_xsgzzbjggl.do?method=getDjbWord&gzzblx=bj&jgid="+rows[0]["jgid"];
					window.open(url);
			     }
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_xsgzzb_xsgzzbjggl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>			
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
							<!-- ����ʦ����ѧ begin -->
							<logic:equal name="xxdm" value="10718">	
								<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">�����ܱ���</a></li>
							</logic:equal>
							<!-- ����ʦ����ѧ end -->
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ܱ��б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
