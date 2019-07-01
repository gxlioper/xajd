<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/ybgzz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"ybgzzSqsh.do?method=getList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'�༶',name:'bjmc', index: 'bjdm'},
					   {label:'�����������',name:'sqjrsj',index:'sqjrsj'},
					   {label:'����ʱ��',name:'sqsj',index:'sqsj'},
					   {label:'shzt',name:'shzt',index:'shzt',hidden:true},
					   {label:'splcid',name:'splcid',index:'splcid',hidden:true},
					   {label:'���״̬',name:'shztmc',index:'shztmc'},
					   {label:'�Ƿ��˳�',name:'sftc',index:'sftc',formatter:function(c,r){
						   return c=="��" ? "<a href=\"javascript:cktc('"+r["id"]+"');\" class='name'>��</a>" : "";
					   }}
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog("�鿴�����",600,400,"ybgzzSqsh.do?method=view&id="+id);
			}
			
			function cktc(id){
				showDialog('�鿴',400,150,'ybgzzCywh.do?method=viewExit&id='+id);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/zxdkSyddk" method="post" styleId="zxdkSyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="1" name="cssz" property="sqkg">
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="add();return false;" 
									>
									����
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xgsq();return false;" class="btn_xg"
									>�޸�</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="qxsq();return false;" class="btn_sc"
									>ɾ��</a>
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
									<a href="javascript:void(0);" onclick="viewLcgz();return false;" 
									   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
									   class="btn_cs">���̸���</a>
								</li>
							</logic:equal>
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
				<span>�װ๤��վ�����б�  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
