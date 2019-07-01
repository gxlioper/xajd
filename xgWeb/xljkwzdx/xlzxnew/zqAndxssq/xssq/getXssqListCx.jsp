<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		pager : "pager",
		url : "xlzxnew_xssq.do?method=searchForXssQ",
		colList : [
				{ label : 'pk', name : 'pk', index : 'pk',key : true, hidden : true },
				{ label : 'sbcount', name : 'sbcount', index : 'sbcount',hidden : true },
				{ label : 'lx', name : 'lx', index : 'lx',hidden : true },
				{ label : 'ѧ��', name : 'xh', index : 'xh', width : '15%',formatter : xhLink },
				{ label : '����', name : 'xm', index : 'xm', width : '15%'},
				{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
				{ label : '�꼶', name : 'nj', index : 'nj', width : '10%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '20%' },
				{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '20%' },
				{ label : '��ְ��ʼ����', name : 'rzksrq', index : 'rzksrq', width : '10%' },
				{ label : '��ְ��������', name : 'rzjsrq', index : 'rzjsrq', width : '10%' },
				{ label : '�ϱ���¼', name : 'ywsbmc', index : 'ywsbmc', width : '5%' }]
		 }

	jQuery(function() {
		jQuery("#dataTable").initGrid(gridSetting);

	});

	
	/**
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function xhLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('����' , 680 , 430 , 'xlzxnew_xssq.do?method=ck"
				 + "&xh=" + rowObject['xh'] +"')" + "\"";

		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
				+ "</a>";

		return el;
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
	 * ����
	 * @return
	 */
	function add(){
		showDialog('����',680,430,'xlzxnew_xssq.do?method=addStu');
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
				var sbcount = rows[i]['sbcount'];
				if(sbcount != '0'){
					showAlertDivLayer("����ɾ�����ϱ���¼�����ݣ���ȷ�� ��");
					return false;
				}
			}
			
			showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
				jQuery.post("xlzxnew_xssq.do?method=delAction",{pks:ids.toString()},function(data){
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
			showDialog('�޸�',680,430,'xlzxnew_xssq.do?method=editStu&xh=' + rows[0]['xh']);
		}
	}


	var DCCLBH = "xg_xlzxnew_xssq.do";//dcclbh,�������ܱ��


	//�Զ��嵼�� ����
	function exportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport(DCCLBH, exportData);
	}

	// ��������
	function exportData() {
		setSearchTj();//���ø߼���ѯ����
		var url = "xlzxnew_xssq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}

	/**
	����
	*/
	function importConfig(){
		var drdm = "IMPORT_XSSQ_NEW";
		toImportDataNew(drdm);
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
		<html:form action="/xljk_xlwygl_xssqglwh">
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			<logic:equal name="writeAble" value="yes">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="add();return false;">����</a>
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
						<li>
							<a href="javascript:void(0);"
								onclick="importConfig();return false;" class="btn_dr">����</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
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
