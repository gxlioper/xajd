<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
		
	var gridSetting = {
		pager : "pager",
		url : "xljk_xlwjyjgl_ylxlxsglwh.do?method=query",
		colList : [
				{ label : 'key', name : 'xh', index : 'xh',key : true, hidden : true },
				{ label : 'sfmtdm', name : 'sfmtdm', index : 'sfmtdm', hidden : true },
				{ label : 'yjkdm', name : 'yjkdm', index : 'yjkdm', hidden : true },
				{ label : 'ѧ��', name : 'xh', index : 'xh', width : '12%',formatter : link},
				{ label : '����', name : 'xm', index : 'xm', width : '10%'},
				{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
				{ label : '�꼶', name : 'nj', index : 'nj', width : '7%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
				{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%' },
				{ label : '�Ƿ�����̸', name : 'sfmtmc', index : 'sfmtmc', width : '10%' },
				{ label : '������������', name : 'lxmc', index : 'lxmc', width : '10%' },
				{ label : '�жϵȼ�', name : 'gzdj', index : 'gzdj', width : '6%' },
				{ label : '�Ƿ��ύԤ����', name : 'yjkcx', index : 'yjkcx',  width : '8%'}],
		sortname : "sfmtmc", sortorder : "desc" };
	
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
	 * ����
	 * @return
	 */
	function add(){
		showDialog('�ϱ�',780,550,'xljk_xlwjyjgl_ylxlxsglwh.do?method=xz');
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
				var sfmtdm = rows[i]['sfmtdm'];
				if(sfmtdm == 'y'){
					showAlertDivLayer("����ɾ������̸�����ݣ���ȷ�� ��");
					return false;
				}
			}
			
			showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
				jQuery.post("xljk_xlwjyjgl_ylxlxsglwh.do?method=delAction",{xhs:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}

	/**
	�ύ
	*/
	function tj(){
		var rows = jQuery("#dataTable").getSeletRow();
		var ids = jQuery("#dataTable").getSeletIds();
		if (rows.length == 0){
			showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
			return false;
		} else{
			for(i = 0 ; i < rows.length ; i++){
				var yjkdm = rows[i]['yjkdm'];
				if(yjkdm == 'y'){
					showAlertDivLayer("��ѡ�д������ύԤ��������ݣ���ȷ�� ��");
					return false;
				}
			}
			showDialog('�ύԤ����',680,330,'xljk_xlwjyjgl_ylxlxsglwh.do?method=tj&xhs=' + ids.toString() );
		}
		
	}
	
	/**
	 * ��̸¼��
	 * @return
	 */
	function mtlr(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����Ҫ��̸¼��ļ�¼��");
			return false;
		} else{
			if(rows[0]['yjkdm'] == 'y'){
				showAlertDivLayer("��¼���ύԤ���⣬�����޸ģ�");
				return false;
			}
			showDialog('��̸¼��',780,540,'xljk_xlwjyjgl_ylxlxsglwh.do?method=mtlr&xh=' + rows[0]['xh'] );
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
		var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 780,530 , 'xljk_xlwjyjgl_ylxlxsglwh.do?method=mtlrck&xh=" + rowObject['xh'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}


	var DCCLBH = "xljk_xlwjyjgl_ylxlxsgl.do";//dcclbh,�������ܱ��


	//�Զ��嵼�� ����
	function exportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport(DCCLBH, exportData);
	}

	// ��������
	function exportData() {
		setSearchTj();//���ø߼���ѯ����
		var url = "xljk_xlwjyjgl_ylxlxsglwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}

	/**
	����
	*/
	function importConfig(){
		toImportData("IMPORT_XLZX_YLXLXSGL");
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
		<html:form action="/xljk_xlwjyjgl_ylxlxsglwh">
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
								onclick="mtlr();return false;" class="btn_xg" style="font-weight: bold"
							>��̸¼��</a>
						</li>
						<li>
								<a href="javascript:void(0);"
									onclick="del();return false;" class="btn_sc"
									>ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="tj();return false;" class="btn_shuc"  style="font-weight: bold">�ύԤ����</a>
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
