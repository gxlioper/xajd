<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
				<script type="text/javascript" src="xsgzgl/sztz/zyszpj/pjwh/js/pjwh.js"></script>
				<script type="text/javascript">
					jQuery(function(){
						var query=jQuery("#query").val();
					     var gridSetting = {
									caption:"ְҵ���������б�",
									pager:"pager",
									url:"zyszpjwh.do?method=list&type=query&query="+query,
									colList:[
									   {label:'ְҵ����id',name:'zyszid', index: 'zyszid',key:true,hidden:true},
									   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
									   {label:'����',name:'xm', index: 'xm',width:'10%'},
									   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'10%'},
									   {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
									   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
									   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'6%'},
									   {label:'��дʱ��',name:'txsj', index: 'txsj',width:'14%'},
									   {label:'����״̬',name:'hpzt', index: 'hpzt',width:'10%'},
									   {label:'ʦ��״̬',name:'spzt', index: 'spzt',width:'10%'}
									   
									],
									sortname: "txsj",
								 	sortorder: "asc"
								}
					jQuery("#dataTable").initGrid(gridSetting);
					});
					
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/zyszpjwh.do?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
				<logic:equal value="yes" name="writeAble">
					<logic:equal value="brcx" name="query">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">��д�����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
					</logic:equal>
					<logic:equal value="txcx" name="query">
						<li>
							<a href="javascript:void(0);" onclick="txhp();return false;" class="btn_sz">����</a>
						</li>
					</logic:equal>
					<logic:equal value="lscx" name="query">
						<li>
							<a href="javascript:void(0);" onclick="sp();return false;" class="btn_sz">ʦ��</a>
						</li>
					</logic:equal>
					<logic:notEqual value="txcx" name="query">
					<li>
						<a href="javascript:void(0);" onclick="dy();return false;" class="btn_dy">��ӡ</a>
					</li>
					</logic:notEqual>
				</logic:equal>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ְҵ�������������б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
