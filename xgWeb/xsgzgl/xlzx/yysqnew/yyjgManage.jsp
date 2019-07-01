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
			url:"xlzx_yysqnew.do?method=yyjgManage&doType=query",
			colList:[
				{label:'ԤԼ���',name:'id', index: 'id',key:true,hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
			   {label:'����',name:'xsxm', index: 'xsxm'},
			   {label:'�Ա�',name:'xsxb', index: 'xsxb',width:'4%'},
			   <logic:equal value="10504" name="xxdm">
			   {label:'Σ��<br/>����',name:'sfxlwjgamc', index: 'sfxlwjgamc',width:'6%'},
			   </logic:equal>
			   {label:'ԤԼ��ѯ����',name:'yyzxrq', index: 'yyzxrq'},
			   {label:'ԤԼ״̬code',name:'status', index: 'status',hidden:true},
			   //{label:'ԤԼ״̬',name:'statusmc', index: 'status',formatter:getYyColor},
			   {label:'��ѯ��������',name:'zxrq', index: 'zxrq'},
			   {label:'������ѯʦ',name:'apzxsxm', index: 'apzxsxm',width:'9%',formatter:apzxsxmLink},
			   {label:'������ѯʦְ����',name:'apzxs', index: 'apzxs',hidden:true},
			   <logic:equal  name="xxdm" value="10026">
			   {label:'��ѯ����ʱ���',name:'sjdmczx', index: 'sjdmczx'},
			   </logic:equal>
			    {label:'��ѯ״̬',name:'zxzt', index: 'zxzt',hidden:true},
			   {label:'��ѯ״̬',name:'zxztmc', index: 'zxztmc',formatter:getZxColor},
			    {label:'��ѯ����',name:'xspjzt', index: 'xspjzt',hidden:true},
			   {label:'��ѯ����',name:'pjztmc', index: 'pjztmc',width:'6%',formatter:getPjColor},
			   {label:'���ķ������',name:'zxfknr', index: 'zxfknr',width:'10%',formatter:function(cellValue,rowObject){
				   if(!cellValue){
					   cellValue = "";
				   }
				   var cellValueTemp = cellValue;
				   if(cellValue.length > 6){
					   cellValueTemp = cellValue.substring(0,6)+"...";
				   }
				   return jQuery("<span title='"+cellValue+"'>"+cellValueTemp+"</span>");
				 }
				}
			   //{label:'ѧ������',name:'yyzxzt', index: 'yyzxzt',align:'center',formatter:getSfxssq}
			],
			sortname: "zxrq,yyzxrq",
		 	sortorder: "desc"
		};
		// ��ѯʦ��������(����)
		function apzxsxmLink(cellValue, rowObject) {
			return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('�鿴��ѯʦ��Ϣ' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=" + rowObject['apzxs'] + "')\" >" + (cellValue==null?" ":cellValue) + "</a>";
		}
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
			showDialog("ԤԼ��ѯ����",750,440,"xlzx_yysqnew.do?method=yyjgView&id="+id);
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

		function ckYyzxInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
				return false;
			}else{
				id = rowsValue[0]["id"];
			}
			showDialog("ԤԼ��ѯ����",750,560,"xlzx_yysqnew.do?method=yyjgView&doType=view&id="+id);
		}	
		
		function updateYyzxInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
				return false;
			}else{
				/*if(rowsValue[0]["status"]=="3" ||rowsValue[0]["status"]=="4" || (rowsValue[0]["status"]=="2"&& rowsValue[0]["zxzt"]!="1" )){
					showAlertDivLayer("������¼���ܷ�����");
					return false;
				}*/
				id = rowsValue[0]["id"];
				showDialog("���ķ������",520,190,"xlzx_yysqnew.do?method=updateYyjg&id="+id+"&xh="+rowsValue[0]["xh"]);
			}
		}
		
		function exportConfig() {
			var dcclbh = "xlzx_zxyyclnew_zxyyclnewjg.do";
			if(jQuery("#pbfs").val() == "2"){
				dcclbh = "xlzx_zxyyclnew_zxyyclnewjgforsjd.do";
			}
			customExport(dcclbh, exportData,750,500);
		}
		// ��������
		function exportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xlzx_yysqnew.do?method=exportDataJg&dcclbh=xlzx_zxyyclnew_zxyyclnewjg.do";//dcclbh,�������ܱ��
			if(jQuery("#pbfs").val() == "2"){
				 url = "xlzx_yysqnew.do?method=exportDataJg&dcclbh=xlzx_zxyyclnew_zxyyclnewjgforsjd.do";//dcclbh,�������ܱ��
			}
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
		<html:form action="/xlzx_yysqnew" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="pbfs" name="pbfs" value="${pbfs}"/>
			<!-- ������ -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			
				<div class="toolbox">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
						<logic:equal  name="writeAble" value="yes">
							<li>
								<a href="#" onclick="updateYyzxInfo();return false;" class="btn_xg">���ķ���</a>
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
