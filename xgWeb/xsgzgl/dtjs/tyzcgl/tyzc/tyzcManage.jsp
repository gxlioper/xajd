<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��Աע���б�",
				pager:"pager",
				url:"dtjs_tyzc.do?method=tyzcManage&type=query",
				colList:[
					{label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'12%' , formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'8%'},
					{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
					{label:'�꼶',name:'nj', index: 'nj',width:'7%'},
					{label:'�༶',name:'bjmc', index: 'bjdm',width:'18%'},
					{label:'ѧ��',name:'xn', index: 'xn',width:'12%'},
					{label:'ע��״̬',name:'zcztmc', index: 'zcztmc',width:'8%'},
					{label:'ע��ʱ��', name:'zcsj', index: 'zcsj',width:'15%'},
					{label:'ע��״̬����',name:'zczt', index: 'zczt', hidden:true}
				],
				sortname: "xn,xh",
			 	sortorder: "asc"
			};
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick=\"showDialog('��Աע����Ϣ' , 700,305 , 'dtjs_tyzc.do?method=tyzcView&pk=" + rowObject['pk'] + "')\" >" + cellValue + "</a>";
			}
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			//��Աע��
			function tyzc(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 1) {
					if(rows[0]['zczt']=='1'){
						showAlertDivLayer("��ѡ��δע���ѧ����");
						return false;
					}
					var url = "dtjs_tyzc.do?method=tyzcDgzc&pk=" + rows[0]["pk"];
					var title = "��Աע��";
					showDialog(title,700,305,url);
				}else {
					if(rows.length == 0){
						// ����
						jQuery("#pksPlHidden").val("");
						// ���ݲ�ѯ���������������
						var xn_num = jQuery("a[name=a_name_xn]").length;
						if(xn_num != 1){
							alertError("��ѡ��һ��ѧ�꣡");
							return false;
						}
						var a_name_zczt = jQuery("a[name=a_name_zczt]");
						var zczt_num = a_name_zczt.length;
						if(zczt_num == 1 && a_name_zczt.eq(0).attr("id").replace("a_id_","") == '-1'){
							var rowConut = jQuery("#rowConut").html();
							var url = "dtjs_tyzc.do?method=tyzcPlzc&len="+rowConut;
							var title = "����ע��";
							showDialog(title,350,255,url);
						}else{
							showAlertDivLayer("������ע��״̬������ѡ��δע�᡿��������ע�ᣡ");
							return false;
						}
					}else{
						var pks = new Array();
						// ���ݹ�ѡ��¼������������
						for(var i=0;i<rows.length;i++){
							if(rows[i]['zczt']=='1'){
								showAlertDivLayer("��ѡ��δע���ѧ����");
								return false;
							}
							pks.push(rows[i]['pk']);
						}
						jQuery("#pksPlHidden").val(pks.join(','));
						var url = "dtjs_tyzc.do?method=tyzcPlzc&len="+rows.length;
						var title = "����ע��";
						showDialog(title,350,255,url);
					}
				}
			}
			//����ע��
			function tyzcCancel(){
				var rows = jQuery("#dataTable").getSeletRow();
				if(rows.length == 0){
					// ����
					jQuery("#pksPlHidden").val("");
					// ���ݲ�ѯ���������������
					var xn_num = jQuery("a[name=a_name_xn]").length;
					if(xn_num != 1){
						alertError("��ѡ��һ��ѧ�꣡");
						return false;
					}
					var a_name_zczt = jQuery("a[name=a_name_zczt]");
					var zczt_num = a_name_zczt.length;
					if(zczt_num == 1 && a_name_zczt.eq(0).attr("id").replace("a_id_","") == '1'){
						var rowConut = jQuery("#rowConut").html();
						var url = "dtjs_tyzc.do?method=tyzcCancel&len="+rowConut;
						var title = "��������ע��";
						showDialog(title,360,170,url);
					}else{
						showAlertDivLayer("������ע��״̬������ѡ����ע�᡿������������ע�ᣡ");
						return false;
					}
				}else{
					var pks = new Array();
					// ���ݹ�ѡ��¼������������
					for(var i=0;i<rows.length;i++){
						if(rows[i]['zczt']=='-1'){
							showAlertDivLayer("��ѡ����ע���ѧ����");
							return false;
						}
						pks.push(rows[i]['pk']);
					}
					jQuery("#pksPlHidden").val(pks.join(','));
					var url = "dtjs_tyzc.do?method=tyzcCancel&len="+rows.length;
					var title = "��������ע��";
					if(rows.length == 1){
						title = "����ע��";
					}
					showDialog(title,360,170,url);
				}
			}
			//����
			function exportConfig(){
				var DCCLBH='dtjs_tyzcgl.do';
				customExport(DCCLBH, exportData);
			}
			function exportData(){
				var DCCLBH='dtjs_tyzcgl.do';
				setSearchTj();//���ø߼���ѯ����
				var url = "dtjs_tyzc.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
		<input type="hidden" id="pksPlHidden" value=""/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					������ѡѧ��ʱ���Բ�ѯ������������ѧ������ע��/����ע�᣻<br/>
					����ѡѧ��ʱ���Թ�ѡ��ѧ������ע��/����ע�ᡣ
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/dtjs_tyzc" method="post" styleId="tyzcForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="tyzc();" class="btn_plqy">ע��</a></li>
							<li><a href="javascript:void(0);" onclick="tyzcCancel();" class="btn_sc">����ע��</a></li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</ul>
				</div>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Աע���б�</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
