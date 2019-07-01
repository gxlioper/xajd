<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/cxpd/sqsh/js/cxpd.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"cxpdSqsh.do?method=getPdshList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'ѧ��',name:'xn', index: 'xn'},
					   <logic:notEqual name="xxdm" value="13943">
					   {label:'ѧ��',name:'xqmc',index:'xqmc'},
					   </logic:notEqual>
					   {label:'��������',name:'cxdjmc',index:'cxdjmc'},
					   {label:'����ʱ��',name:'sqsj',index:'sqsj'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'5%'}
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

				var map = getSuperSearch();
				map["shzt"] = "dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cancelAuding(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
				} else {
					jQuery.post("cxpdSqsh.do?method=cancelAudit",{id:ids.toString(),gwid:rows[0]["xtgwid"]},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/zxdkXyddk" method="post" styleId="zxdkXyddkForm">
			<html:hidden property="shzt" styleId="shzt"/>
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sh"><a href="#" class="btn_sh" onclick="showAuding();return false;">���</a></li>
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelAuding();return false;" 
							   class="btn_qxsh">����</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_cs" onclick="viewLcgz();return false;">���̸���</a></li>
					</ul>
				</div>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%">
		        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
		        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
		      </ul>
			</div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>���еȼ�����б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
