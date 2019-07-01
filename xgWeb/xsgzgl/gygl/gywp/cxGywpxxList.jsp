<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gywp/js/gywp.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��Ʒ��Ϣ�б�",
				pager:"pager",
				url:"gygl_qywpxx.do?method=cxGywpxxList&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'��Ʒ����',name:'wpmc', index: 'wpmc',width:'20%',formatter:wpLink},
				   {label:'¥��',name:'ldmc', index: 'ldmc',width:'15%'},
				   {label:'���Һ�',name:'qsh', index: 'qsh',width:'10%'},
				   {label:'��������',name:'wpdlmc', index: 'wpdlmc',width:'15%'},
				   {label:'��Ʒ���',name:'wplbmc', index: 'wplbmc',width:'15%'},
				   {label:'��Ʒ����',name:'sl', index: 'sl',width:'5%'},
				   {label:'�Ƿ����',name:'sfwh', index: 'sfwh',width:'10%'}
				]
				
			}
			
			function wpLink(cellValue,rowObject){
				return "<a href='javascript:void(0);'onclick='gywpxxView(\""+rowObject["id"]+"\")' class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}


			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					������������Һ�ֵΪ��#����Ϊ¥����Ʒ��
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<%--<li><a href="javascript:void(0);" onclick="back()" class="btn_fh">����</a></li>--%>
						<li><a href="javascript:void(0);" onclick="updateGywpxx();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="delGywpxx()" class="btn_sc">ɾ��</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="viewGywpxx()" class="btn_ck">�鿴</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="exportData();return false;">��������</a></li>--%>						
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
				<span> ��ѯ���	 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
