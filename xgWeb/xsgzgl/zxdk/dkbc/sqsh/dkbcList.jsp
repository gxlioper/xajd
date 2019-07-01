<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/dkbc/sqsh/js/bcsq.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"dkbc_sqsh.do?method=getDkbcList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
						{label:'��Ժ',name:'symc', index: 'sydm'},
                        {label:'�����༶',name:'bjmc', index: 'bjdm'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybj'},
					   {label:'������',name:'dcje', index: 'dcje'},
//					   {label:'�����',name:'dkbj', index: 'dkbj'},
					   {label:'����ʱ��',name:'sqsj',index:'sqsj'},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'shzt',name:'shzt',index:'shzt',hidden:true},
					   {label:'���״̬',name:'shztmc',index:'shztmc'}
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
				showDialog("�鿴�����",800,480,"dkbc_sqsh.do?method=cksq&id="+id);
			}
			function printSqb(){
				var url = "dkbc_sqsh.do?method=printSqb";
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
	
		<html:form action="/dkbc_sqsh" method="post" styleId="zxdkSyddkForm">
			<input type="hidden" id="ksqcs" name="ksqcs" value=${cssz.ksqcs } >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="1" name="cssz" property="sqkg">
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="bcsq();return false;" 
									>
									����
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xgsq();return false;" class="btn_xg"
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
								<li><a href="#" class="btn_cs" onclick="viewLcgz();return false;">���̸���</a></li>
								<li>
									<a href="javascript:void(0);" onclick="qxsq();return false;" class="btn_sc"
									>ɾ��</a>
								</li>
								<li><a href="javascript:void(0);" onclick="printSqb();" class="btn_dy">��ӡ�����</a></li>
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
				<span>����������б�  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
