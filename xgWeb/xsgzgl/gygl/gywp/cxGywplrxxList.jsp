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
				caption:"��Ԣ��Ʒά����Ϣ",
				pager:"pager",
				url:"gygl_qywpxx.do?method=cxGywplrxxList&type=query",
				colList:[
				   {label:'pk',name:'pk', index: 'pk',hidden:true,key:true},
				   {label:'¥��',name:'ldmc', index: 'ldmc',width:'15%'},
				   {label:'���',name:'ch', index: 'ch',width:'15%'},
				   {label:'���Һ�',name:'qsh', index: 'qsh',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'20%'},
				   {label:'��Ʒ������',name:'rs', index: 'rs',width:'20%',formatter:rsLink}
				]
				
			}
			
			function rsLink(cellValue,rowObject){
				return "<a href='javascript:void(0);'onclick='cxGywpxxList(\""+rowObject["pk"]+"\")' class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
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
					�����������š����Һ�ֵΪ��#����Ϊ¥����Ʒ��
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="addGywpxx()" class="btn_zj">��Ʒ�Ǽ�</a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ѯ��� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
