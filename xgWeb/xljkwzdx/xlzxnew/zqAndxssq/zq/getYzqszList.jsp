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
		url : "xlzxnew_zqrcgl.do?method=searchYrcCx",
		colList : [
		        { label :'ybid',name : 'ybid',index : 'ybid',hidden:true,key : true},
				{ label : 'ѧ��', name : 'xn', index : 'xn', width : '50%'},
				{ label : '�·�', name : 'yf', index : 'yf', width : '50%',formatter:yzqLink },
				{ label : 'sbcnt', name : 'sbcnt', index : 'sbcnt',  hidden : true }

				]}

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
		showDialog('����',580,420,'xlzxnew_zqrcgl.do?method=addYzqsz');
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
				var SQCOUNT = rows[i]['sbcnt'];
				if(SQCOUNT != '0'){
					showAlertDivLayer("���ܴ��ѱ�ʹ�ã�����ɾ������ȷ�� ��");
					return false;
				}
			}
			
			showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
				jQuery.post("xlzxnew_zqrcgl.do?method=delYzqSz",{values:ids.toString()},function(data){
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

			var SQCOUNT = rows[0]['sbcnt'];
			if(SQCOUNT != '0'){
				showAlertDivLayer("���ܴ��ѱ�ʹ�ã������޸ģ���ȷ�� ��");
				return false;
			}
			showDialog('�޸�',580,420,'xlzxnew_zqrcgl.do?method=editYzqsz&ybid=' + rows[0]['ybid']);
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
					</ul>
				</div>
				</logic:equal>
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li ><a href="javascript:void(0);" onclick="selectTab(this,'zb');"><span>�༶�ܱ��ճ�</span></a></li>
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'yb');"><span>ѧԺ�±��ճ�</span></a></li>
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
