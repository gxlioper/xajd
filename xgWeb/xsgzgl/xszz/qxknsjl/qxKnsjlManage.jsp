<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">	
		
		jQuery(function(){
			var gridSetting = {
					caption:"������ȡ����¼��ѯ",
					pager:"pager",
					url:"xszz_qxknsjlgl.do?method=qxKnsjlManage&type=query",
					colList:[
						{label:'key',name:'jgguid', index: 'jgguid',key:true ,hidden:true},												 						 
						{label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
						{label:'���� ',name:'xm', index: 'xm',width:'7%'},						
						{label:'ѧԺ',name:'xymc', index: 'xymc',width:'21%'},
						{label:'�༶',name:'bjmc', index: 'bjmc',width:'21%'},
						{label:'ȡ������ ',name:'dcmc', index: 'rddc',width:'9%'},
						{label:'������',name:'czrxm', index: 'czrxm',width:'21%'},
						{label:'����ʱ��',name:'czsj', index: 'czsj',width:'13%'}
					],
					sortname: "czsj",
				 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function ckqx(){
							
			var rows = jQuery("#dataTable").getSeletRow();				
			if(rows.length == 0){}																																							  
			//var url = 'xszz_qxknszggl.do?method=qxknsrd&xh='+rows[0]["xh"];	
			var url = 'xszz_qxknszggl.do?method=qxknsrd';			    				 				
			var title = "ȡ���϶�";
			showDialog(title, 459,258, url);															
			
		}

		function viewQxKnszgjl() {
			
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
				
			} else {
				showDialog("�鿴������ȡ���϶���Ϣ", 720, 520, "xszz_qxknsjlgl.do?method=viewQxKnszgjl&jgguid=" + rows[0]['jgguid']
						+ "&xh=" + rows[0]['xh']);
			}
			
		}

		//����
		function exportConfig(){
			var DCCLBH='xszz_qxknsjl_cx.do';
			customExport(DCCLBH, exportData);
		}
		function exportData(){
			var DCCLBH='xszz_qxknsjl_cx.do';
			setSearchTj();//���ø߼���ѯ����
			var url = "xszz_qxknsjlgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					ѧ������ʦ�ύ���������϶��������������ͨ���������������˲˵�<br/>
					�û�Ҳ���ڴ˴�ֱ��ά������������					
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/xszz_qxknsjlgl" >			
			<input type="hidden" name="tableName" id="tableName" value="xg_xszz_new_knsqxjl"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="viewQxKnszgjl();return false;" class="btn_ck">�鿴</a>
						</li>				
						
		                <logic:equal value="zf01" name="userName">
               				<li>
               					<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
               				</li>
              			</logic:equal>
              			
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ȡ����������¼��ѯ </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
