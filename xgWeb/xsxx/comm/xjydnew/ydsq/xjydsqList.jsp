<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydsq.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
 	   var gridSetting = {
			caption:"ѧ���춯�����б�",
			pager:"pager",
			url:"xjydsq.do?method=xjydsqList&type=query",
			colList:[
			   {label:'xjydsqid',name:'xjydsqid', index: 'xjydsqid',key:true,hidden:true},
			   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
			   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
			   {label:'ydlbdm',name:'ydlbdm', index: 'ydlbdm',hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'10%'},
			   {label:'ԭ�꼶',name:'ydqnj', index: 'ydqnj'},
			   {label:'ԭ<bean:message key="lable.xb" />',name:'ydqxymc', index: 'ydqxymc'},
			   {label:'ԭ�༶',name:'ydqbjmc', index: 'ydqbjmc'},
			   {label:'�춯���',name:'ydlbmc', index: 'ydlbmc'},
			   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
			   {label:'���״̬',name:'shztmc', index: 'shztmc'}
			],
			sortname: "sqsj",
		 	sortorder: "desc"
		}

 	  function getWord(){
			var rows = jQuery("#dataTable").getSeletRow();

			var ids = jQuery("#dataTable").getSeletIds();
			
			 if (rows.length == 0){
				showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				return;
			 }
			var url="xjydsq.do?method=doPrint&value="+ids ;
			window.open(url);
		}

		jQuery(function(){
			gridSetting.params = getSuperSearch();
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
		<html:form action="/xjydsq.do?method=xjydsqList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="pltj();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="plqxtj();return false;" class="btn_sr">����</a>
						</li>
					</logic:equal>
					<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
						   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
						   class="btn_cs">���̸���</a></li>
					<logic:equal name="writeAble" value="yes">		   
					<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</logic:equal>
					<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div class="formbox">
		<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ���춯�����б� </span>
			</h3>	
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>	
	</body>
</html>
