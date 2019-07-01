<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/bzjl/wdbzjl/bzjlsqsh/js/jxsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				var gridSetting = {
					caption:"����ѧ���б� ",
					pager:"pager",
					url:"bzjl_sqsh.do?method=viewJxsqList&type=query",
					colList:[
					   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckSqb('"+rowObject["sqid"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'רҵ�༶',name:'bjmc', index: 'bjdm',width:'13%'},
					    <logic:equal name="xxdm" value="10466">
					   		{label:'�����',name:'qsh',index:'qsh',width:'10%'},
					   	</logic:equal>
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'15%'},
					   {label:'���뽱��',name:'xmmc', index: 'xmdm',width:'13%'},
					   {label:'��Ŀ����',name:'xmdm', index: 'xmdm',width:'13%',hidden:true},
					   {label:'���ջ�ý���',name:'tzhxmmc',index:'tzhxmdm',width:'13%'},
					    <logic:equal name="xxdm" value="10264">
					   		{label:'����Ǹ���',name:'sfwtgg',index:'sfwtgg',width:'13%'},
					   	</logic:equal>
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splc',name:'splc', index: 'splc',hidden:true},
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'5%'},
					   {label:'isopen',name:'isopen', index: 'isopen',hidden:true}
					],
					sortname:"sqsj",
					sortorder:"desc"
					//radioselect:true
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);

				var btndr=jQuery("#btn_dr");
				//����
				if(btndr!=null){
					btndr.click(function () {
						toImportData("IMPORT_N750602_PJJXSQ");
						return false;
					});
				}
			});

			/**
			*	���صǼǱ�
			*/
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjxmsq.do?method=getDjbZip&value="+ids + "&actionFrom=sqsh";
					window.open(url);
				 } else {
					var url="xpj_pjxmsq.do?method=getDjbWord&sqid="+rows[0]["sqid"] + "&actionFrom=sqsh";
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
		<input type="hidden" id="SFBJPY_Y" value="${SFBJPY_Y }"/>
		<html:form action="/bzjl_sqsh" method="post" styleId="zcxmForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden styleId="xzdm" property="xzdm" value="${xzdm}"/>
			<div class="toolbox">	
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal value="true" name="cssz" property="kgzt">
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
									<a href="javascript:void(0);" onclick="qxsq();return false;" class="btn_sc"
									   title="ֻ��ɾ��δ��˻��˻صļ�¼���Ѿ�����˵Ĳ���ɾ����"
									>ɾ��</a>
								</li>
							</logic:equal>
							<li>
								<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
								   title="��������д�����δ�ύ�ļ�¼�����ύ���������"
								>�ύ</a>
							</li>
							<logic:equal value="true" name="cssz" property="kgzt">
								<li>
									<a href="javascript:void(0);" onclick="cancleRst();return false;" class="btn_sr"
									   title="�������ύδ��˵ļ�¼���г���������"
									>����</a>
								</li>
							</logic:equal>
						<!-- �㽭��ѧ-->
						<logic:equal name="xxdm" value="10335">
							<li><a href="#" class="btn_dr" id="btn_dr">����</a></li>
						</logic:equal>
						</logic:equal>
							<li>
								<a href="javascript:void(0);" onclick="viewLcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a>
							</li>
						<!-- �㽭��ѧ begin -->
						<logic:equal name="xxdm" value="10335">
							<logic:equal value="true" name="cssz" property="kgzt">
								<logic:equal value="xy" name="userType">
									<li><a href="jxmcmb_10335.xls" onclick="" class="btn_tj">��������ģ��</a></li>
								</logic:equal>
							</logic:equal>
							<logic:equal value="zf01" name="userName">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
								</li>
							</logic:equal>
						</logic:equal>
						<!-- �㽭��ѧ end -->
						
						<logic:notEqual value="10335" name="xxdm">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
							</li>
						</logic:notEqual>

						<li><a href="javascript:void(0);" class="btn_xg" onclick="exceptionDataResolve();return false;">�ύ�쳣���ݴ���</a></li>
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
				<span>���������б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
