<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		
	var gridSetting_1 = {
		pager : "pager",
		url : "xljk_xlwygl_xxsbjgglwh.do?method=query&sblx=0",
		colList : [
				{ label : 'sbjgid', name : 'sbjgid', index : 'sbjgid',key : true, hidden : true },
				{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',hidden : true },
				{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
				{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
				{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
				{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
				{ label : 'ѧ��', name : 'xh', index : 'xh', width : '13%',formatter : link},
				{ label : '����', name : 'xm', index : 'xm', width : '8%'},
				{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
				{ label : '�꼶', name : 'nj', index : 'nj', width : '5%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
				{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%' },
				{ label : '�ܴ�', name : 'zbzc', index : 'zbzc', width : '7%' },
				{ label : '��¼ʱ��', name : 'sbsj', index : 'sbsj', width : '10%' },
				{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
		sortname : "sbsj", sortorder : "desc" }

	var gridSetting_2 = {
			pager : "pager",
			url : "xljk_xlwygl_xxsbjgglwh.do?method=query&sblx=1",
			colList : [
						{ label : 'sbjgid', name : 'sbjgid', index : 'sbjgid',key : true, hidden : true },
						{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',hidden : true },
						{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
						{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
						{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
						{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
						{ label : 'ѧ��', name : 'xh', index : 'xh', width : '13%',formatter : link},
						{ label : '����', name : 'xm', index : 'xm', width : '8%'},
						{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
						{ label : '�꼶', name : 'nj', index : 'nj', width : '5%' },
						{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
						{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%' },
						{ label : '�ܴ�', name : 'zbzc', index : 'zbzc', width : '7%' },
						{ label : '��¼ʱ��', name : 'sbsj', index : 'sbsj', width : '10%' },
						{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
			sortname : "sbsj", sortorder : "desc" }

	var gridSetting_3 = {
			pager : "pager",
			url : "xljk_xlwygl_xxsbjgglwh.do?method=query&sblx=2",
			colList : [
						{ label : 'sbjgid', name : 'sbjgid', index : 'sbjgid',key : true, hidden : true },
						{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',hidden : true },
						{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
						{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
						{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
						{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
						{ label : 'ѧ��', name : 'xh', index : 'xh', width : '13%',formatter : link},
						{ label : '����', name : 'xm', index : 'xm', width : '10%'},
						{ label : '�Ա�', name : 'xb', index : 'xb', width : '10%' },
						{ label : '�꼶', name : 'nj', index : 'nj', width : '10%' },
						{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '19%' },
						{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '19%' },
						{ label : '��¼ʱ��', name : 'sbsj', index : 'sbsj', width : '15%' },
						{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
			sortname : "sbsj", sortorder : "desc" }
	
	jQuery(function() {
		jQuery('#tab_title li:first').css('ha');
		jQuery('#tab_title li:first').children().click();
	});

	/**
	 * �������Ѵ�����ǩ�л�
	 * @param obj
	 * @param shzt
	 * @return
	 */
	function selectTab(obj,query_type){
		jQuery("#query_type").val(query_type);

		if (query_type == "0"){
			jQuery("#dataTable").initGrid(gridSetting_1);
		} else if(query_type == "1"){
			jQuery("#dataTable").initGrid(gridSetting_2);
		}else if(query_type == "2"){
			jQuery("#dataTable").initGrid(gridSetting_3);
		}
		jQuery(".ha").removeClass("ha");
		jQuery(obj).parent().addClass("ha");
		
		searchRs();
	}
	
	/**
	 * �߼���ѯ
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * �ϱ�
	 * @return
	 */
	function sb(){
		var sblx = jQuery("#query_type").val();
		var rows = jQuery("#dataTable").getSeletRow();
		showDialog('�ϱ�',680,430,'xljk_xlwygl_xxsbjgglwh.do?method=sb&sblx=' + sblx);
	}

	/**
	 * ɾ��
	 * @return
	 */
	function del(){
		var rows = jQuery("#dataTable").getSeletRow();
		var ids = jQuery("#dataTable").getSeletIds();
		if (rows.length == 0){
			showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
			return false;
		} else{

			for(i = 0 ; i < rows.length; i++){
				var sjly = rows[i]['sjly'];
				if(sjly == '1'){
					showAlertDivLayer("����ɾ���������ݣ���ȷ�� ��");
					return false;
				}
			}
			
			showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
				jQuery.post("xljk_xlwygl_xxsbjgglwh.do?method=delAction",{pks:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	/**
	 * �޸�
	 * @return
	 */
	function update(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		} else{
			var sjly = rows[0]['sjly'];
			if(sjly == '1'){
				showAlertDivLayer("�����޸��������ݣ���ȷ�� ��");
				return false;
			}
			showDialog('�޸�',680,430,'xljk_xlwygl_xxsbjgglwh.do?method=xg&sbjgid=' + rows[0]['sbjgid']);
		}
	}

	/**
	 * ����
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function link(cellValue,rowObject){
		//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
		var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,430 , 'xljk_xlwygl_xxsbjgglwh.do?method=ck&sbjgid=" + rowObject['sbjgid'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}

	var DCCLBH1 = "xljk_xlwygl_xxsbjggl1.do";//dcclbh,�������ܱ��

	var DCCLBH2 = "xljk_xlwygl_xxsbjggl2.do";//dcclbh,�������ܱ��


	//�Զ��嵼�� ����
	function exportConfig() {
		var dcdm = "";
		if(jQuery("#query_type").val() == '0'){
			dcdm = DCCLBH1;
		}else if(jQuery("#query_type").val() == '1'){
			dcdm = DCCLBH1;
		}else{
			dcdm = DCCLBH2;
		}
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport(dcdm, exportData);
	}

	// ��������
	function exportData() {
		var dcdm = "";
		if(jQuery("#query_type").val() == '0'){
			dcdm = DCCLBH1;
		}else if(jQuery("#query_type").val() == '1'){
			dcdm = DCCLBH1;
		}else{
			dcdm = DCCLBH2;
		}
		setSearchTj();//���ø߼���ѯ����
		var url = "xljk_xlwygl_xxsbjgglwh.do?method=exportData&dcclbh=" + dcdm + "&sblx=" + jQuery("#query_type").val();//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}


	/**
	����
	*/
	function importConfig(){
		var drdm = "";
		if(jQuery("#query_type").val() == '0'){
			drdm = "IMPORT_XLZX_XXSBJGGL_1";
		}else if(jQuery("#query_type").val() == '1'){
			drdm = "IMPORT_XLZX_XXSBJGGL_1";
		}else{
			drdm = "IMPORT_XLZX_XXSBJGGL_2";
		}
		toImportData(drdm);
		return false;
	}
	
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xljk_xlwygl_xxsbjgglwh">
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="sb();return false;">�ϱ�</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li>
								<a href="javascript:void(0);"
									onclick="del();return false;" class="btn_sc"
									>ɾ��</a>
							</li>
							<%--<li>
								<a href="javascript:void(0);"
									onclick="importConfig();return false;" class="btn_dr">����</a>
							</li>
							--%><li>
								<a href="javascript:void(0);"
									onclick="exportConfig();return false;" class="btn_dc">����</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%" id="tab_title">
			       		<li><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>�༶�����ܱ�</span></a></li>
				       	<li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>��Ԣ�����ܱ�</span></a></li>
				        <li><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>ƽʱ����ϱ�</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
