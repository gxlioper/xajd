<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="xljkwzdx/xljkzx/js/xsyysq.js"></script>
	<script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
	<script type="text/javascript">
		var gridSetting={
			caption:"������ѯ�б�",
			pager:"pager",
			multiselect:true,
			rowNum:10,
			url:"xljk_xsyyzx.do?method=queryXsyysqAction",
			colList:[
               {label:'pkid',name:'pkid',index:'pkid',hidden:true, key:true },
			   {label:'sqid',name:'sqid',index:'sqid',hidden:true},
			   {label:'zxid',name:'zxid',index:'zxid',hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh', formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm'},
			   {label:'�Ա�',name:'xb', index: 'xb'},
			   {label:'ԤԼ״̬',name:'yyzt', index: 'yyzt',formatter:yyztChange},
			   {label:'��ѯ����ʱ��',name:'zzaprq', index: 'zzaprq'},
			   {label:'������ѯʦ',name:'zxsxm', index: 'zxsxm', formatter:zxsxmLink},
			   {label:'��ѯ״̬',name:'zxzt', index: 'zxzt', formatter:zxztChange},
			   {label:'��ѯ����',name:'xspjzt', index: 'xspjzt'},
			   {label:'zxs',name:'zxs',index:'zxs',hidden:true},
			   {label:'����ʱ��',name:'cjsj',index:'cjsj',hidden:true}
			],
			sortname: "cjsj",
		 	sortorder: "desc"
		}
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function reloadXsyysqDataTable(){
			jQuery("#dataTable").reloadGrid();
		}
	</script>
  </head>
  
  <body>
     <div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
		</p>
	 </div>
	 <html:form action="/xljk_xsyyzx">
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
			  <ul>
				<logic:equal value="yes" name="writeAble">
					<li>
						<a href="javascript:void(0);" onclick="addXsyysq();return false;" class="btn_zj" id="zjButton">����ԤԼ</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="updateXsyysq();return false;" class="btn_xg" id="xgButton">�޸�ԤԼ</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="cancelXsyysq();return false;" class="btn_sz" id="szButton">ȡ��ԤԼ</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="setZxpj();return false;" class="btn_sz" id="szButton">��ѯ����</a>
					</li>
				</logic:equal>
			  </ul>
			</div>
		</div>
	</html:form>
	<div>
		<h3 style="font-weight: normal;background-color: #E8F0FB;height: 25px;line-height: 25px;padding-left: 70px;" >
			<span> 
				ѧ�ţ�${username }&nbsp;&nbsp;&nbsp;&nbsp;������${realname }
			</span>
		</h3>
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
