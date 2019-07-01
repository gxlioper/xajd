<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/dateUtils.js"></script>
		<script type="text/javascript">
		var gridSetting = {
			caption:"ԤԼ��ѯ�б�",
			pager:"pager",
			url:"xlzx_yysqnew.do?method=yysqManage&doType=query",
			colList:[
				{label:'ԤԼ���',name:'id', index: 'id',key:true,hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
			   {label:'����',name:'xsxm', index: 'xsxm'},
			   {label:'�Ա�',name:'xsxb', index: 'xsxb',width:'4%'},
			   <logic:equal value="10504" name="xxdm">
			   {label:'Σ��<br/>����',name:'sfxlwjgamc', index: 'sfxlwjgamc',width:'6%'},
			   </logic:equal>
			   <logic:equal value="10026" name="xxdm">
			   {label:'��ѯʦ',name:'zxsxm', index: 'zxsxm',width:'8%'},
			   </logic:equal>
			   {label:'ԤԼ��ѯ����',name:'yyzxrq', index: 'yyzxrq'},
			   {label:'ԤԼ״̬code',name:'status', index: 'status',hidden:true},
			   {label:'ԤԼ״̬',name:'statusmc', index: 'status',formatter:getYyColor},
			   {label:'��ѯ��������',name:'zxrq', index: 'zxrq'},
			   <logic:equal value="10026" name="xxdm">
			   {label:'��ѯʱ���',name:'sjdmczx', index: 'sjdmczx',width:'7%'},
			   </logic:equal>
			    {label:'��ѯ״̬',name:'zxzt', index: 'zxzt',hidden:true},
			   {label:'��ѯ״̬',name:'zxztmc', index: 'zxztmc',formatter:getZxColor},
			    {label:'��ѯ����',name:'xspjzt', index: 'xspjzt',hidden:true},
			   {label:'��ѯ����',name:'pjztmc', index: 'pjztmc',width:'5%',formatter:getPjColor},
			   {label:'ѧ��<br/>����',name:'yyzxzt', index: 'yyzxzt',width:'3%',align:'center',formatter:getSfxssq},
				{label:'ǩ��״̬',name:'qdztmc', index: 'qdztmc'},
                {label:'ǩ��״̬',name:'qdzt', index: 'qdzt',hidden:true}
			],
			sortname: "zxrq,yyzxrq",
		 	sortorder: "desc"
		};
		
		function getSfxssq(cellValue,rowObject){
			if(!cellValue){
				return "��";
			}else{
				return "��";
			}
		}
		
		function xhLink(cellValue,rowObject){
			return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"+rowObject["id"]+"');\">"+cellValue+"</a>";
		}
		
		function showDetail(id){
			showDialog("ԤԼ��ѯ����",750,440,"xlzx_yysqnew.do?method=zxfkView&id="+id);
		}
		
		
		function getYyColor(cellValue,rowObject){
				if(rowObject["status"]=="1"){
					return "<font color='blue'>"+cellValue+"</font>";
				}else if(rowObject["status"]=="2"){
					return "<font color='red'>"+cellValue+"</font>";
				}else{
					return cellValue;
				}
		}
		
		function getZxColor(cellValue,rowObject){
			if(rowObject["zxzt"]=="1"){
				return "<font color='blue'>"+cellValue+"</font>";
			}else if(rowObject["zxzt"]=="2" || rowObject["zxzt"]=="3"){
				return "<font color='red'>"+cellValue+"</font>";
			}else{
				return cellValue;
			}
		}
		
		function getPjColor(cellValue,rowObject){
			if(rowObject["xspjzt"]=="1"){
				return "<font color='blue'>"+cellValue+"</font>";
			}else if(rowObject["xspjzt"]=="2"){
				return "<font color='red'>"+cellValue+"</font>";
			}
		}
		
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function searchRs(){
			var map = getSuperSearch();

			jQuery("#dataTable").reloadGrid(map);
		}

		function addYyzxInfo(){
			showDialog("������ѯ",750,520,"xlzx_yysqnew.do?method=addYyzxInfo");
		}
		
		// ɾ��ԤԼ��ѯ
		function delYyzxInfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				
				for(var i=0;i<ids.length;i++){
					if(rows[i]['yyzxzt'] || rows[i]['zxzt']=='2'){
						showAlertDivLayer("����ѯ����ѧ������ļ�¼����ɾ����");
						return false;
					}
				}
				
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {"okFun" : function() {
						jQuery.post("xlzx_yysqnew.do?method=delYyzxInfo", {
							values : ids.toString()
						}, function(data) {
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								searchRs();
							}});
						}, 'json');
						
				}});
			}
		}
		function ckYyzxInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
				return false;
			}else{
				id = rowsValue[0]["id"];
			}
			showDialog("ԤԼ��ѯ����",750,560,"xlzx_yysqnew.do?method=yyzxDetail&doType=view&id="+id);
		}	
		
		function updateYyzxInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				return false;
			}else{
				if(rowsValue[0]["status"]=="3" ||rowsValue[0]["status"]=="4" || (rowsValue[0]["status"]=="2"&& rowsValue[0]["zxzt"]!="1" )){
					showAlertDivLayer("������¼�����޸ģ�");
					return false;
				}
				id = rowsValue[0]["id"];
				showDialog("�޸���ѯ",750,520,"xlzx_yysqnew.do?method=updateYyzxInfo&id="+id+"&xh="+rowsValue[0]["xh"]);
			}
		}
		
		function zxfkInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
				return false;
			}else{
				if(rowsValue[0]["status"]!="2"){
					showAlertDivLayer("��ѡ��ԤԼ�ɹ��ļ�¼��");
					return false;
				}
				if(rowsValue[0]["xspjzt"]=="2"){
					return showAlertDivLayer("��ѡ������۵ļ�¼��");
				}
                 if(rowsValue[0]["qdzt"] !="yqd" && rowsValue[0]["qdzt"] !="cd" ){
                     return showAlertDivLayer("��ѡ��ǩ��״̬Ϊ����ǩ�����򡰳ٵ����ļ�¼��");
                 }
				id = rowsValue[0]["id"];
			}
			showDialog("��ѯ����",750,440,"xlzx_yysqnew.do?method=zxfkDetail&id="+id);
		}
		

		function exportConfig() {
			var dcclbh = (jQuery("#pbfs").val() == "2" )? "xlzx_zxyyclnew_zxyyclnewforsjd.do" :"xlzx_zxyyclnew_zxyyclnew.do" ;
			customExport(dcclbh, exportData,750,500);
		}
		// ��������
		function exportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xlzx_yysqnew.do?method=exportData&dcclbh=xlzx_zxyyclnew_zxyyclnew.do";//dcclbh,�������ܱ��
			if(jQuery("#pbfs").val() == "2"){
				url = "xlzx_yysqnew.do?method=exportData&dcclbh=xlzx_zxyyclnew_zxyyclnewforsjd.do";
			}
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function qdztwh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫά���ļ�¼��");
                return false;
            }
            showDialog("ǩ��״̬ά��",350,300,"xlzx_yysqnew.do?method=qdztwh&id="+rows[0]["id"]);
		}
		
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_yysqnew" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="pbfs" id="pbfs" value="${pbfs}"/>
			<!-- ������ -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			
				<div class="toolbox">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
						<logic:equal  name="writeAble" value="yes">
							<li>
								<a href="#" onclick="addYyzxInfo();return false;" class="btn_zj">������ѯ</a>
							</li>
							<li>
								<a href="#" onclick="updateYyzxInfo();return false;" class="btn_xg">�޸���ѯ</a>
							</li>
							<li>
								<a href="#" onclick="delYyzxInfo();return false;" class="btn_sc">ɾ����ѯ</a>
							</li>
							<li>
								<a href="#" onclick="zxfkInfo();return false;" class="btn_xg">��ѯ����</a>
							</li>
							<li>
								<a href="#" onclick="qdztwh();return false;" class="btn_xg">ǩ��״̬ά��</a>
							</li>
						</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</ul>
					</div> 
				</div>
		  		<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ԤԼ��ѯ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
