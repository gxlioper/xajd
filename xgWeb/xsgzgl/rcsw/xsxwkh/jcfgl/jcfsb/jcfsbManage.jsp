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
			caption:"���ͷ��걨�б�",
			pager:"pager",
			url:"rcsw_rcxwwhnew_rcxwxxwhgl.do?method=rcxwxxwhManage&type=query",
			colList:[
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
			   {label:'splc',name:'splc', index: 'splc',hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
			   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'13%'},
			   {label:'��Ŀ����',name:'xmlx', index: 'xmlx',width:'8%'},
			   {label:'��ֵ',name:'fz', index: 'fz',width:'12%'},
			   {label:'�걨ʱ��',name:'sbsj', index: 'sbsj',width:'15%'},
			   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
			],
			sortname: "sbsj",
		 	sortorder: "desc"
		}

		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
			var xxdm = jQuery("#xxdm").val();
			if(xxdm == "10504"){
				var userStatus = jQuery("#userStatus").val();
				if(userStatus == "stu"){
					jQuery("#btn_zj").hide();
					jQuery("#btn_xg").hide();
					jQuery("#btn_sc").hide();
				}else{
					jQuery("#btn_shuc").hide();
					jQuery("#btn_sr").hide();
				}
			}
		});
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		function add(){
			var url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=addXwxx";
			var title = "���ӽ��ͷ�";
			showDialog(title,950,450,url);
		}
		function update() {
			var rows = jQuery("#dataTable").getSeletRow();

			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				var shzt = rows[0]["shzt"];
				if(shzt=='0'||shzt=='3'){
					var url = 'rcsw_rcxwwhnew_rcxwxxwhgl.do?method=updateXwxx&id=' + rows[0]["id"]
		      		+ '&xh=' + rows[0]["xh"] +'&rcxwlbdldm=' + rows[0]["rcxwlbdldm"] 
		      		+ '&rcxwlbdlmc=' + rows[0]["rcxwlbdlmc"] +'&rcxwlbdlmc=' + rows[0]["rcxwlbmc"];
		      		var title = "�޸Ľ��ͷ�";
		      		showDialog(title, 850, 450, url);
				}else{
					showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
					return false;
				}
			}
		}

		function del(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			} else {
				var shzt = rows[0]["shzt"];
				
				if(shzt=='0'){
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("rcsw_rcxwwhnew_rcxwxxwhgl.do?method=delXwxx",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}else{
					showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
					return false;
				}
			}
		}


		function xwxxView(id, xh) {
			showDialog("�鿴���ͷ�", 720, 520, "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=viewXwxx&id=" + id
					+ "&xh=" + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwxxView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "rcsw_rcxwwhnew_rcxwxxwhgl.do";//dcclbh,�������ܱ��

		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport(DCCLBH, rcxwxxwhExportData);
		}

		// ��������
		function rcxwxxwhExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function submitBusi(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				var shzt = rows[0]["shzt"];
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
						showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
						return false;
					}
					if(rows[i]['shzt']!='3'){
						url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=submitXwxx&returnflag=back";
					}else{
						url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=submitXwxx";
					}
					
				}
				
				showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
								
					jQuery.post(url,
						{values:ids.toString(),
						 xh : rows[0]['xh'],  
						 rcxwlbdldm : rows[0]['rcxwlbdldm']
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
		}

		function cancel(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
			} else if (ids.length >1 ) {
				showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='5'){
						showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
						return false;
					}
				}
				showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("rcsw_rcxwwhnew_rcxwxxwhgl.do?method=cancelRcxwxx",
						{
						 values:ids.toString(),
						 splcid : rows[0]['splc'] 
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
			
		}

		function rcxwxxwhLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			var shzt = rows[0]["shzt"];
			if (ids.length != 1){
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
			} else {	
				if ("0" == shzt){
					showAlertDivLayer("�����������Ϣ��");
					return false;
				}
				showDialog("�������̸���",600,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
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
		<html:form action="/rcsw_rcxwwhnew_rcxwxxwhgl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_rcxwxxwh"/>
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="btn_zj">
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" 
							>����</a>
						</li>
						<li id="btn_xg">
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li id="btn_sc">
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>ɾ��</a>
						</li>
						<li id="btn_shuc">
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li id="btn_sr">
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="rcxwxxwhLcinfo();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>	
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���ͷ��걨�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
