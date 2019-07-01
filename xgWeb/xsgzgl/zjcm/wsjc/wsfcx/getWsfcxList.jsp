<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/wsfcx/js/wsfcx.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "�������б�",
					pager : "pager",
					url : "cjWsfcx.do?method=getWsfcxList&type=query",
					colList : [					   	 
					   {label : 'wsfid',name : 'wsfid',index : 'wsfid',key:true ,hidden:true},
					   {label : '�������',name : 'ccny',index : 'ccny',width : '9%'},
					   {label : '¥������',name : 'ldmc',index : 'ldmc',width : '12%'},
					   {label : '���Һ�',name : 'qsh',index : 'qsh',width : '5%'},
					   {label : '�����꼶',name : 'nj',index : 'nj',width : '5%'},
					   {label : '����ѧԺ',name : 'bmmc',index : 'bmmc',width : '20%'},
					   {label : '��λ��',name : 'cws',index : 'cws',width : '8%'}, 
					   {label : '��ס����',name : 'rzrs',index : 'rzrs',width : '8%'},					   
					   {label : '��ֵ',name : 'fz',index : 'fz',width : '10%'},
					   {label : '�ȼ�',name : 'dj',index : 'dj',width : '7%'},
					   {label:'������',name:'pfzmc', index: 'pfzmc',width:'15%'}
					   ],
					sortname : "ccny",
					sortorder : "desc"
				}

			jQuery(function() {
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//�߼���ѯ
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
						
		</script>
		
	</head>
	<body>
	<html:form action="/cjWsfcx" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				
			<div class="buttonbox">
				<ul>
					<!-- ��ť -->
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" 
								   title="ѡ��һ����¼������ð�ť�����Գ���ʧ����ύ������"
								   class="btn_qxsh">����</a>
						</li>
					</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="ck();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴��Ϣ��"
								   class="btn_ck">�鿴</a>
						</li>									
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>						
				</ul>					
			</div>							
			<!-- �������� -->
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
			<div class="formbox">
			<!--����start-->
				<h3 class="datetitle_01">
					<span> �����ֲ�ѯ�б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
