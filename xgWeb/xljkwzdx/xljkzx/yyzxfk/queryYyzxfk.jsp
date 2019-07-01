<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
	<script type="text/javascript" src="xljkwzdx/xljkzx/js/yyzxfk.js"></script>
	<script type="text/javascript">
		var gridSetting={
			caption:"������ѯ�б�",
			pager:"pager",
			multiselect:true,
			rowNum:10,
			url:"xljk_yyzxfk.do?method=queryYyzxfkAction",
			colList:[
			   {label:'sqid',name:'sqid',index:'sqid',hidden:true, key:true  },
			   {label:'zxid',name:'zxid',index:'zxid',hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh', formatter:xhLinkForYyfk},
			   {label:'����',name:'xm', index: 'xm'},
			   {label:'�Ա�',name:'xb', index: 'xb'},
			   {label:'ԤԼ״̬',name:'yyzt', index: 'yyzt',formatter:yyztChange},
			   {label:'ԤԼ����',name:'yyfkzt', index: 'yyfkzt',formatter:yyfkztChange},
			   {label:'��ѯ��������',name:'zzaprq', index: 'zzaprq'},
			   {label:'������ѯʦ',name:'zxsmc', index: 'zxsmc',formatter:zxsxmLink},
			   {label:'��ѯ״̬',name:'zxzt', index: 'zxzt',formatter:zxztChange},
			   {label:'zxs',name:'zxs',index:'zxs',hidden:true}
			],
			sortname: "yyzt",
		 	sortorder: "asc"
		}
		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function reloadYyzxfkDataTable(){
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
	</script>
  </head>
  
  <body>
     <div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
		</p>
	 </div>
	 <html:form action="/xljk_xlzxcl">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
			  <ul>
				<logic:equal value="yes" name="writeAble">
					<li>
						<a href="javascript:void(0);" onclick="xlzxyyfk();return false;" class="btn_xg" id="xgButton">ԤԼ����</a>
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
				������ѯ�б�   
			</span>
		</h3>
	</div>
	<div class="formbox" style="width:100%;height:320px;overflow-x:hidden;overflow-y:auto;">
		<table id="dataTable" ></table>
	</div>
	<div id="pager"></div>
  </body>
</html>
