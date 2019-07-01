<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/kyxmgl/kyxm/js/kyxm.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "������Ŀ�б�",
				pager : "pager",
				url : "qgzx_kycxkyxmgl.do?method=getKyxmglList&type=query",
				colList : [
							{ label : 'key', name : 'xmid', index : 'xmid',key:true,hidden : true },
							{ label : 'splcid', name : 'splcid', index : 'splcid',hidden : true },
							{ label : 'shzt', name : 'shzt', index : 'shzt',hidden : true },
							{ label : '��Ŀ���', name : 'xmbh', index : 'xmbh', width : '8%' },
							{ label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmmcLink},
							{ label : '��Ŀ����', name : 'xmsxmc', index : 'xmsxmc', width : '10%' },
							{ label : '��Ŀ������', name : 'xm', index : 'xm', width : '10%' },
							{ label : '��Ŀ������λ', name : 'ssdwmc', index : 'ssdwmc', width : '12%' },
							{ label : '��Ŀ��ʼʱ��', name : 'kssj', index : 'kssj', width : '10%' },
							{ label : '��Ŀ����ʱ��', name : 'jssj', index : 'jssj', width : '10%' },
							{ label : '��Ŀ����״̬', name : 'yxztmc', index : 'yxztmc', width : '15%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '15%' }
							]
					 }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_kycxkyxmgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="kyxmFywh();return false;" class="btn_sz">��Ŀ��������</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="ztsz();return false;" class="btn_sz">����״̬����</a>
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
				<span>������Ŀ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
