<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"���ύ�۲��ѧԺ�б�",
				pager:"pager",
				url:"xpjpy_zcwh.do?method=getQxtjzcfList&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'19%'},
				   {label:'������',name:'xyrs', index: 'xyrs',width:'8%'},
				   {label:'�ύ��',name:'tjrxm', index: 'tjr',width:'15%'},
				   {label:'�ύʱ��',name:'tjsj', index: 'tjsj',width:'15%'}
				],
				sortname: "xydm",
			 	sortorder: "desc",
			 	radioselect:false
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//ȡ���ύ�۲��״̬
			function cancelTj(){
				var id = jQuery("#dataTable").getSeletIds();
				if (id.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫȡ����ѧԺ��");
					return false;
				} 
				var title = "ȡ���ύ";
				var url = "xpjpy_zcwh.do?method=cancelTj&id="+id;
				showDialog(title,400,250,url);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${cssz.xn}" id="xn"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >ʹ�ð���</a>
			</p>
		</div>
		<html:form action="/xpjpy_zcwh" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						�������ύ�۲����ݵİ༶������Ա���ڴ˴�����<font color="red">ȡ���ύ</font>������ȡ���ύ�İ༶�۲����ݿ�<font color="red">���µ����޸�</font>
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="cancelTj();" class="btn_xg">ȡ���ύ</a></li>
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
				<span><font color="blue">${cssz.xn}&nbsp;</font>���ύѧԺ </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>