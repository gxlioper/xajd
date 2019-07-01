<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ����Ϣ�б�",
				pager:"pager",
				url:"xsxx_tygl_xszpdr.do?type=query",
				colList:[
						   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink,key:true },
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'18%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'19%'},
						   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
						   {label:'������Ƭ�Ƿ���',name:'xssfdr', index: 'xssfdr',width:'5%'},
						   {label:'ѧ����Ƭ�Ƿ���',name:'sfdr', index: 'sfdr',width:'5%'}
						],
						multiselect:false
				
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='xsxxView(\""+cellValue+"\");'>"+cellValue+"</a>";
			}

			//�鿴
			function xsxxView(xh){
				showDialog("�鿴ѧ��",850,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh);
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
				
		// ����
		function xszpdc() {
			showDialog("", 400, 250, 'xsxx_xszpgl.do?method=xszpdc');
		}

		
		function exportZpxx(mmfs,zpType){
			setSearchTj();//���ø߼���ѯ����
			var url = "xsxx_xszpgl.do?method=xszpdc&type=exp&photoNameType="+mmfs+"&zpType="+zpType;
				url = addSuperSearchParams(url);
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
		}
		

		//��Ƭ����
		function xszpdr(){
			showDialog('������Ƭ����',800,400,'xsxx_xszpgl.do?method=xszpdrManage&zpType=xszp');
			}
		function zpdr(){
			showDialog('ѧ����Ƭ����',800,400,'xsxx_xszpgl.do?method=xszpdrManage');
			}
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_jtqkdc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="xszpdr();return false;" class="btn_dr">������Ƭ����</a></li>
						<li><a href="javascript:void(0);" onclick="zpdr();return false;" class="btn_dr">ѧ����Ƭ����</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="xszpdc();return false;" class="btn_dc">��Ƭ����</a></li>
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
				<span>ѧ����Ϣ�б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
