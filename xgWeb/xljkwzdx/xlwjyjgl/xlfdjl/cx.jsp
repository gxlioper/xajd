<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
			<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		pager : "pager",
		url : "xljk_xlwjyjgl_xlfdjlglwh.do?method=query",
		colList : [
				{ label : 'key', name : 'fdid', index : 'fdid',key : true, hidden : true },
				{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter : link},
				{ label : '����', name : 'xm', index : 'xm', width : '10%'},
				{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
				{ label : '�꼶', name : 'nj', index : 'nj', width : '5%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '13%' },
				{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '13%' },
				{ label : '������������', name : 'lxmc', index : 'lxmc', width : '10%' },
				{ label : '��ע�ȼ�', name : 'gzdj', index : 'gzdj', width : '5%' },
				{ label : '����', name : 'xlfdlxmc', index : 'xlfdlxmc',  width : '6%'},
				{ label : 'ʱ��', name : 'sj', index : 'sj', width : '10%' },
				{ label : '��ʦ', name : 'fdyxm', index : 'fdyxm', width : '10%' }],
		sortname : "sj", sortorder : "desc" };
	
	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
	});

	/**
	 * �߼���ѯ
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
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
			showDialog('�޸�',680,500,'xljk_xlwjyjgl_xlfdjlglwh.do?method=xg&xh=' + rows[0]['xh'] + '&fdid=' + rows[0]['fdid'] );
		}
	}

	/**
	 * ����
	 * @return
	 */
	function add(){
		showDialog('����',680,500,'xljk_xlwjyjgl_xlfdjlglwh.do?method=xz');
	}
	
	/**
	 * ����
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function link(cellValue,rowObject){
		//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
		var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 780,530 , 'xljk_xlwjyjgl_xlfdjlglwh.do?method=ck&xh=" + rowObject['xh'] + "&fdid=" + rowObject['fdid'] +"')" + "\"";
		
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
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
			showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
				jQuery.post("xljk_xlwjyjgl_xlfdjlglwh.do?method=delAction",{fdids:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}


	var DCCLBH = "xljk_xlwjyjgl_xlfdjlglwh.do";//dcclbh,�������ܱ��


	//�Զ��嵼�� ����
	function exportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport(DCCLBH, exportData);
	}

	// ��������
	function exportData() {
		setSearchTj();//���ø߼���ѯ����
		var url = "xljk_xlwjyjgl_xlfdjlglwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}

	/**
	����
	*/
	function importConfig(){
		toImportData("IMPORT_XLZX_XLFDGL");
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
		<html:form action="/xljk_xlwjyjgl_xlfdjlglwh">
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
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
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
