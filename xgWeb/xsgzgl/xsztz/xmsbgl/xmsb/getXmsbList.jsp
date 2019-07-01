<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/xmsb/js/xmsb.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��Ŀ�걨�б�",
				pager : "pager",
				url : "xmsbXmsb.do?method=getXmsbList&type=query",
				colList : [
							{ label : 'key', name : 'xmdm', index : 'xmdm',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'sfkxg', name : 'sfkxg', index : 'sfkxg',hidden : true },
							<%--
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%' },
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '5%' },--%>
							{ label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmmcLink  },
							{ label : '��Ŀ����', name : 'csmsmc', index : 'csms', width : '10%'},
							{ label : '��Ŀ����', name : 'xmjbmc', index : 'xmjbmc', width : '10%' },
							{ label : '��Ŀ��ʼʱ��', name : 'xmkssj', index : 'xmkssj', width : '12%' },
							{ label : '�ɲ�����/����', name : 'kcyrs', index : 'kcyrs', width : '5%',},
							{ label : '����ѧ��', name : 'jcxf', index : 'jcxf', width : '5%' },
							{ label : '�걨ʱ��', name : 'xmsbsj', index : 'xmsbsj', width : '12%' },
							{ label : '�걨����', name : 'sbbmmc', index : 'sbbmmc', width : '15%' },
							{ label : '�걨��', name : 'sbrxm', index : 'sbrxm', width : '8%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true}],
					sortname : "xmsbsj",
				    sortorder : "desc" }
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
		<html:form action="/xmsbXmsb">
		<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >�걨</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>	
										
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ŀ�걨�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
