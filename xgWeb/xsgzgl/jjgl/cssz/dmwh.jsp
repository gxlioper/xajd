<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/jjgl/cssz/script/dmwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			/**
			*���¼�������
			*/
			function reloadWindow(){
				jQuery("#dataTable").reloadGrid();
			}
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_fyff_ffxm">
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%" id="tabUl">
		      	<li class="ha" >
		      		<a href="javascript:void(0);" onclick="selectTab(this,'jjxk');"><span>�ҽ�ѧ��</span></a>
		      	</li>
				<li >
					<a href="javascript:void(0);" onclick="selectTab(this,'jjnj');"><span>�ҽ��꼶</span></a>
				</li>
				</ul>
		    </div>
		</div>
		</html:form>
		
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span> 
						��ѯ���
						<font color="red">(����:&nbsp;�Դ������ͳһ���룬ͬʱ��������ɾ�����¼�¼��)</font>
					</span>
				</h3>
			</div>
			<input type="hidden" id="hiddenQryType" name="hiddenQryType" value="jjxk"/>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
