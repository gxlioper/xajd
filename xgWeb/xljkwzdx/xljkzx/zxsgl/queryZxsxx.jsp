<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
	<script type="text/javascript" src="xljkwzdx/xljkzx/js/zxsgl.js"></script>
	<script type="text/javascript">
		var gridSetting={
			caption:"��ѯʦ��Ϣ�б�",
			pager:"pager",
			multiselect:true,
			rowNum:10,
			url:"xljk_zxsgl.do?method=queryZxsxxAction",
			colList:[
			   {label:'ְ����',name:'zgh', index: 'zgh', key:true, formatter:zghLink},
			   {label:'����',name:'xm', index: 'xm'},
			   {label:'�Ա�',name:'xb', index: 'xb'},
			   {label:'����',name:'age', index: 'age'},
			   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
			   {label:'����',name:'bmmc', index: 'bmmc'},
			   {label:'�ڸ�״̬',name:'status', index: 'status', formatter:zgztChange},
			   {label:'��ְ����',name:'zxszg', index: 'zxszg'}
			],
			sortname: "zgh",
		 	sortorder: "desc"
		}
		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function reloadZxsxxDataTable(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
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
		 * �ڸ�״̬����ת������
		 * 
		 * @param cellValue
		 * @param rowObject
		 * @return
		 */
		function zgztChange(cellValue, rowObject) {
			var returnText;
			switch (cellValue) {
				case "0":
					returnText="���ڸ�";
					break;
				case "1":
					returnText="�ڸ�";
					break;
				default:
					returnText="";
					break;
			}
			return returnText;
		}
	</script>
  </head>
  
  <body>
    <div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
		</p>
	</div>
	<html:form action="/xljk_zxsgl">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
			  <ul>
				<logic:equal value="yes" name="writeAble">
					<li>
						<a href="javascript:void(0);" onclick="addZxsxx();return false;" class="btn_zj" id="zjButton">����</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="updateZxsxx();return false;" class="btn_xg" id="xgButton">�޸�</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="deleteZxsxx();return false;" class="btn_sc" id="scButton">ɾ��</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="setZxsxxStatus();return false;" class="btn_sz" id="szButton">�����ڸ�״̬</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="setZxyysm();return false;" class="btn_sz" id="szButton">��ѯԤԼ˵��</a>
					</li>
				</logic:equal>
			  </ul>
			</div>
		</div>
		<!-- �������� -->	
		<%@ include file="/comm/search/superSearchArea.jsp"%>
		<!-- �������� end-->
	</html:form>
	<div>
		<h3 class="datetitle_01">
			<span> 
				��ѯʦ��Ϣ�б�
			</span>
		</h3>
	</div>
	<div class="formbox" style="width:100%;height:320px;overflow-x:hidden;overflow-y:auto;">
		<table id="dataTable" ></table>
	</div>
	<div id="pager"></div>
  </body>
</html>
