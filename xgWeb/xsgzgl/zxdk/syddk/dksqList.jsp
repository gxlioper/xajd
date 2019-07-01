<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/syddk/js/dksq.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"��ѧ���������б� ",
					pager:"pager",
					url:"zxdk_sydk.do?method=getDksqList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckSqb('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
                        {label:'��Ժ',name:'symc', index: 'sydm',width:'13%'},
                        {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybj',width:'13%'},
					   {label:'����ѧ��',name:'xn', index: 'xn',width:'15%'},
					   {label:'�����ܽ��',name:'dkje', index: 'dkje',width:'13%'},
					   <logic:notEqual name="xxdm" value="10335">
					   {label:'��������(��)',name:'dkqx', index: 'dkqx',width:'13%'},
					   </logic:notEqual>
					   {label:'����ʱ��',name:'sqsj',index:'sqsj',width:'13%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'5%'}
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			
			function printSqb(){
				var url = "zxdkXyddk.do?method=printSqb";
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
	
		<html:form action="/zxdk_sydk" method="post" styleId="zxdkSydksqshForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="1" name="cssz" property="sydKg">
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
									<a href="javascript:void(0);" onclick="cancelSubmit();return false;" class="btn_sr"
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
				<span>��Դ���������б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
