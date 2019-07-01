<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/dfgz/js/dfgz.js"></script>	
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "�������б�",
					radioselect:true,
					pager : "pager",
					url : "cjWsfDfgz.do?method=getDfgzList&type=query",
					colList : [ 
					   {label : 'dfszid',name : 'dfszid',index : 'dfszid',key:true ,hidden:true},
					   {label : 'ѧ��',name : 'xn',index : 'xn',width : '20%'},
					   {label : 'ѧ��',name : 'xqdm',index : 'xqdm',width : '20%',hidden:true}, 
					   {label : 'ѧ��',name : 'xqmc',index : 'xqmc',width : '10%'},
					   {label : '�������',name : 'ccny',index : 'ccny',width : '20%',formatter:gzszLink},
					   {label : '����ά��ʱ��',name : 'kfwhsj',index : 'kfwhsj',width : '30%'},
					   {label : '���ύ������',name : 'ytjqs',index : 'ytjqs',width : '20%',
						formatter:function(cellValue,rowObject){
									var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
									html.bind("click",function(){
										showDialog("�鿴���ύ����",800,500,"cjWsfDfgz.do?method=getCcqsList&tjzt=1&dfszid="+rowObject["dfszid"]);
									});
									return html;
								 }
					   },
						   
					   {label : 'δ�ύ������',name : 'wtjqs',index : 'wtjqs',width : '20%',
						   formatter:function(cellValue,rowObject){
							var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
							html.bind("click",function(){
								showDialog("�鿴δ�ύ����",800,500,"cjWsfDfgz.do?method=getCcqsList&tjzt=0&dfszid="+rowObject["dfszid"]);
							});
							return html;
						 }
	  					}
					   ],
					sortname : "ccny",
					sortorder : "desc"
				}

			function yhsLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='yhck(\""+rowObject["pfzid"]+"\");'>"+cellValue+"</a>";
			}

			//����������鿴
			function yhck(pfzid) {
				showDialog("�鿴�û�", 800, 550, "cjWsfPfz.do?method=viewPfzList&pfzid=" + pfzid);
			}

			jQuery(function() {
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function add() {
				var url = "cjWsfDfgz.do?method=addDfgz";
				var title = "����";
				showDialog(title, 600, 300, url);
			}
						
		</script>
		
	</head>
	<body>
	<html:form action="/cjWsfDfgz" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a></li>		
					<li><a href="javascript:void(0);" onclick="gzsd();return false;" class="btn_sz" >�����趨</a></li>					
				</ul>
			</div>
			</logic:equal>
				
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
			<div class="formbox">
			<!--����start-->
				<h3 class="datetitle_01">
					<span> ��ֹ����б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
