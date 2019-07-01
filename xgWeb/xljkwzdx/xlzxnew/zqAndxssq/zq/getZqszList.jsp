<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zqAndxssq/zq/js/zqsz.js"></script>
		<script type="text/javascript">
		
	var gridSetting_1 = {
		pager : "pager",
		url : "xlzxnew_zqrcgl.do?method=searchZrcCx",
		colList : [
				{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
				{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
				{ label : 'zbid', name : 'zbid', index : 'zbid',key : true, hidden : true },
				{ label : 'zblx', name : 'zblx', index : 'zblx',hidden : true },
				{ label : '�ܴ�', name : 'zbzc', index : 'zbzc', width : '25%',formatter:zqLink},
				{ label : '��ʼ����', name : 'zbksrq', index : 'zbksrq', width : '15%' },
				{ label : '��������', name : 'zbjsrq', index : 'zbjsrq', width : '15%' },
				{ label : 'ʱ��', name : 'czsj', index : 'czsj', width : '20%' },
				{ label : 'sqcount', name : 'sqcount', index : 'sqcount',  hidden : true }],
		sortname : "czsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting_1["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting_1);
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
		showDialog('����',580,420,'xlzxnew_zqrcgl.do?method=addZq');
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
				var SQCOUNT = rows[i]['sqcount'];
				if(SQCOUNT != '0'){
					showAlertDivLayer("���ܴ��ѱ�ʹ�ã�����ɾ������ȷ�� ��");
					return false;
				}
			}
			
			showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
				jQuery.post("xlzxnew_zqrcgl.do?method=delzqsz",{zbids:ids.toString()},function(data){
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

			var SQCOUNT = rows[0]['sqcount'];
			if(SQCOUNT != '0'){
				showAlertDivLayer("���ܴ��ѱ�ʹ�ã������޸ģ���ȷ�� ��");
				return false;
			}
			showDialog('�޸�',580,420,'xlzxnew_zqrcgl.do?method=editZq&zbid=' + rows[0]['zbid']);
		}
	}
	// δ/���ϱ��༶
	function cxSbbj(sbbjlx){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ���ϱ��ܴΣ�");
			return false;
		} else{
			var title = "���ϱ��༶";
			if(sbbjlx == 'wsb'){
				title = "δ�ϱ��༶";
			}
			showDialog(title,800,500,'xlzxnew_zqrcgl.do?method=ckZqDetailxx&zbid=' + rows[0]['zbid'] + "&sbbjlx=" + sbbjlx);
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
		<html:form action="/xljk_xlwygl_zbrcglwh">
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
						<li id="ysbbj_li" >
							<a href="javascript:void(0);"
								onclick="cxSbbj('ysb');return false;" class="btn_cx" 
								>���ϱ��༶</a>
						</li>
						<li id="wsbbj_li" >
							<a href="javascript:void(0);"
								onclick="cxSbbj('wsb');return false;" class="btn_cx" 
								>δ�ϱ��༶</a>
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
