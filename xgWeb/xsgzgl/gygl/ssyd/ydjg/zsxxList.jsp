<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function xhLink(cellValue,row){
				return "<a href='javascript:void(0);' class='name' onClick='seeXsydInfo(\""+row["xh"]+"\")'>"+cellValue+"</a>";
			}
			//�鿴ѧ���춯��Ϣ
			function seeXsydInfo(xh){
				showDialog("�鿴ѧ��ס����Ϣ",650,480,"ydjg.do?method=ckXsydInfoLsxx&xh="+xh,null);
			}
			
			function ckYdjg(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�鿴�ļ�¼��");
				}  else{
					showDialog("ס����ʷ��Ϣ",760,390,'ydjg.do?method=zsxxck&ssydid='+rows[0]["ssydid"]);
				}
			}

			function exportConfig() {
				customExport("lsxxgl_lsxxgl_zslsxxgl.do", exportData,690,500);
			}
			// ��������
			function exportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "ydjg.do?method=exportData&dcclbh=ydjgbase.do";//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			//�鿴���޴�λ��Ϣ
			function tscwLink(cellValue,row){
				if( cellValue!=null && cellValue!=""){
					return "<a href='javascript:void(0);' class='name' onClick='seeCwInfo(\""+row["ydqlddm"]+"\",\""+row["ydqqsh"]+"\",\""+row["ydqcwh"]+"\")'>"+cellValue+"</a>";
				}else{
					return "";
				}
			}

			//�鿴��ס��λ��Ϣ
			function rzcwLink(cellValue,row){
				if( cellValue!=null && cellValue!=""){
					return "<a href='javascript:void(0);' class='name' onClick='seeCwInfo(\""+row["ydhlddm"]+"\",\""+row["ydhqsh"]+"\",\""+row["ydhcwh"]+"\")'>"+cellValue+"</a>";
				}else{
					return "";
				}
			}


			//��סʱ��
			function rzsjLink(cellValue,row){
				if( cellValue!=null && cellValue!=""){
					return cellValue.substr(0,10);
				}else{
					return "";
				}
			}
						
			//��ע������ȡ
			function bzSubstring(cellValue,row){
				if(cellValue==null){
					cellValue="";
				}
				var strValue = cellValue;
				if(strValue.length > 10){
					strValue = strValue.substring(0, 10)+"...";
				}
				return "<span title='"+cellValue+"'>"+strValue+"</span>";;
			}

			//�鿴��λ��Ϣ
			function seeCwInfo(lddm,qsh,cwh){
				showDialog("�鿴��λ��Ϣ",850,350,"ydjg.do?method=ckQsydInfo&ydqlddm="+lddm+"&ydqqsh="+qsh+"&ydqcwh="+cwh,null);
			}

			//��ѯ
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			jQuery(function(){
				//��ʼ����ѯ
				var gridSetting = {
						caption:"ס����ʷ��Ϣ�б�",
						pager:"pager",
						url:"ydjg.do?method=zslslist&type=query",
						colList:[
								   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
								   {label:'����',name:'xm', index: 'xm'},
								   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
								   {label:'�Ա�',name:'xb', index: 'xb'},
								   {label:'������',name:'ssydlxmc', index: 'ssydlxmc'},
								   {label:'ԭ����',name:'tsqs', index: 'tsqs',formatter:tscwLink},
								   {label:'������',name:'rzqs', index: 'rzqs',formatter:rzcwLink},
								   {label:'���ʱ��',name:'tstzsj', index: 'tstzsj',formatter:rzsjLink},
								   {label:'��¼ʱ��',name:'czsj', index: 'czsj',formatter:rzsjLink,hidden:true},
								   {label:'��ע',name:'bz', index: 'bz',title:'bz',formatter:bzSubstring},
								   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},	
								   {label:'ydqlddm',name:'ydqlddm', index: 'ydqlddm',hidden:true},	
								   {label:'ydqqsh',name:'ydqqsh', index: 'ydqqsh',hidden:true},	
								   {label:'ydqcwh',name:'ydqcwh', index: 'ydqcwh',hidden:true},	
								   {label:'ydhlddm',name:'ydhlddm', index: 'ydhlddm',hidden:true},	
								   {label:'ydhqsh',name:'ydhqsh', index: 'ydhqsh',hidden:true},	
								   {label:'ydhcwh',name:'ydhcwh', index: 'ydhcwh',hidden:true},	
								   {label:'ssydid',name:'ssydid', index: 'ssydid',key:true,hidden:true}
						],
						sortname: "czsj",
					 	sortorder: "desc"
				}
								
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="ckYdjg();return false;" class="btn_ck">�鿴</a>
						</li>
						<logic:equal value="yes" name="writeAble">
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
				<span>ס����ʷ��Ϣ�б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
