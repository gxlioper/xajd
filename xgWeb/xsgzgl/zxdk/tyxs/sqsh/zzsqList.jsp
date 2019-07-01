<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					<logic:notEqual value="12688" name="xxdm" >
					caption:"ѧ�����������б� ",
					</logic:notEqual>
					<logic:equal value="12688" name="xxdm" >
					caption:"�����������������б� ",
					</logic:equal>
					pager:"pager",
					url:"tyxs_zzsq.do?method=getZzsqList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckSqbs('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";}},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
                        {label:'��Ժ',name:'symc', index: 'sydm',width:'13%'},
					   {label:'�����༶',name:'bjmc', index: 'bjmc',width:'13%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
					   {label:'����ѧ��',name:'xn',index:'xn',width:'13%'},	
					    {label:'�����ܽ��',name:'sqxfzj',index:'sqxfzj',width:'13%'},					   				
					   {label:'����ʱ��',name:'sqsj',index:'sqsj',width:'13%'},					   
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'5%'},
					   {label:'���Id',name:'shid', index: 'shid',hidden:true},
					{label:'��λId',name:'xtgwid', index: 'xtgwid',hidden:true}
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:false
				};
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
				
			});
			/**
			 * �鿴�����
			 * @param id
			 */
			function ckSqbs(id){
				showDialog("�鿴�����",800,590,"tyxs_zzsq.do?method=ckZzsq&id="+id);
			}
			
			function printSqbs(){
				var url = "tyxs_zzsq.do?method=printSqb";
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length <=0) {
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					var guid = jQuery("#dataTable").getSeletIds();
					var url = url + "&ids=" +guid;
					window.open(url);
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
	
		<html:form action="/tyxs_zzsq" method="post" styleId="tyxsZzsqForm">
			 <%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="splc" styleId="splc" value=""/>
			<html:hidden property="xxdm" styleId="xxdm" value="${xxdm}"/>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
						
							<logic:equal value="1" name="cssz" property="xfzzsqkg">
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="editSqb();return false;" 
									   title="����ð�ť�����������дҳ�档"
									>
									����
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xgSqb();return false;" class="btn_xg"
									   title="ѡ��һ����¼������ð�ť���޸������"
									>�޸�</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
										title="��������д�����δ�ύ�ļ�¼�����ύ���������"
									>�ύ</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="cancelsq();return false;" class="btn_sr"
										 title="�������ύδ��˵ļ�¼���г���������"
									>����</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="qxsq();return false;" class="btn_sc"
									   title="ֻ��ɾ��δ��˻��˻صļ�¼���Ѿ�����˵Ĳ���ɾ����"
									>ɾ��</a>
								</li>
							</logic:equal>
							<li>
								<a href="javascript:void(0);" onclick="viewLcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
							</li>
							<logic:equal value="10335" name="xxdm">
								<logic:equal value="zf01" name="userName">
									<li>
										<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
									</li>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="10335" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
								</li>
							</logic:notEqual>
						
							<li><a href="javascript:void(0);" onclick="printSqbs();" class="btn_dy">���صǼǱ�</a></li>						
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
				<logic:notEqual value="12688" name="xxdm" >
					<span>���������б� </span>
				</logic:notEqual>
				<logic:equal value="12688" name="xxdm" >
					<span>���������б� </span>
				</logic:equal>
				
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
	
	
	<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzsq.js"></script>
</html>
