<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		pager : "pager",
		url : "xljk_xlwjyjgl_wgwtwh.do?method=query",
		colList : [
				{ label : 'key', name : 'xh', index : 'xh',key : true, hidden : true },
				{ name : 'yjkdm', index : 'yjkdm', hidden : true },
				{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter : link},
				{ label : '����', name : 'xm', index : 'xm', width : '10%'},
				{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
				{ label : '�꼶', name : 'nj', index : 'nj', width : '7%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
				{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%' },
				{ label : '����޸�ʱ��', name : 'xgsj', index : 'rksj', width : '10%' },
				{ label : '�ܷ�', name : 'zf_num', index : 'zf_num',  width : '5%'},
				{ label : '�Ƿ��ύԤ����', name : 'yjkcx', index : 'yjkcx',  width : '10%'}],
		sortname : "yjkcx", sortorder : "asc" };
	
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
		showDialog('�ϱ�',780,530,'xljk_xlwjyjgl_wgwtwh.do?method=xz');
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
				var yjkdm = rows[i]['yjkdm'];
				if(yjkdm == 'y'){
					showAlertDivLayer("����ɾ�����ύԤ��������ݣ���ȷ�� ��");
					return false;
				}
			}
			
			showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
				jQuery.post("xljk_xlwjyjgl_wgwtwh.do?method=delAction",{xhs:ids.toString()},function(data){
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

			
			showDialog('�ύԤ����',680,330,'xljk_xlwjyjgl_wgwtwh.do?method=tj&xhs=' + ids.toString() );
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
			showDialog('�޸�',780,530,'xljk_xlwjyjgl_wgwtwh.do?method=xg&xh=' + rows[0]['xh'] );
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
		var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 780,530 , 'xljk_xlwjyjgl_wgwtwh.do?method=ck&xh=" + rowObject['xh'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}
	
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xljk_xlwjyjgl_xlwjyjkglwh">
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
									onclick="tj();return false;" class="btn_shuc"  style="font-weight: bold">�ύԤ����</a>
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
