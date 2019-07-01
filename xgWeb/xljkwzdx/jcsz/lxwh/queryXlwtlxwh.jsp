<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="xljkwzdx/jcsz/js/xlwtlxwh.js"></script>
	<script type="text/javascript">
		var gridSetting={
			caption:"�������������б�",
			pager:"pager",
			multiselect:true,
			rowNum:10,
			url:"xljk_xlwtlxwh.do?method=queryXlwtlxAction",
			colList:[
			   {label:'���ʹ���',name:'lxdm', index: 'lxdm', key:true},
			   {label:'��������',name:'lxmc', index: 'lxmc'}
			],
			sortname: "lxdm",
		 	sortorder: "desc"
		}
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
		//ҳǩ�л�
		function selectTab(obj,xzflg){
			if("0" ==xzflg){
				refreshForm("xljk_xlwtlxwh.do?method=queryXlwtlx");
			}else if("1" ==xzflg){
				refreshForm("xljk_fdlxwh.do?method=queryFdlx");
			}
		}
		function reloadXlwtlxDataTable(){
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
	<html:form action="/xljk_xlwtlxwh">
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
			  <ul>
				<logic:equal value="yes" name="writeAble">
					<li>
						<a href="javascript:void(0);" onclick="addXlwtlx();return false;" class="btn_zj" id="zjButton">����</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="updateXlwtlx();return false;" class="btn_xg" id="xgButton">�޸�</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="deleteXlwtlx();return false;" class="btn_sc" id="scButton">ɾ��</a>
					</li>
				</logic:equal>
			  </ul>
			</div>
			<div class="comp_title" id="comp_title">
		      <ul style="width:70%">
		        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>������������</span></a></li>
		        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>��������</span></a></li>
		      </ul>
		    </div>
			
		</div>
		<div>
			<h3 class="datetitle_01">
				<span> 
					��ѯ���
					<font color="red">(����:&nbsp;�Դ������ͳһ���룬ͬʱ��������ɾ�����¼�¼��)</font>
				</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:320px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</html:form>
  </body>
</html>
