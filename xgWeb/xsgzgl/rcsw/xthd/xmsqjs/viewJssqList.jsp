<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsqjs/js/xmsqjs.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"�����б� ",
				pager:"pager",
				url:"rcsw_txhd_xmsq.do?method=viewJssqList&type=query",
				colList:[
				   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
					   return "<a href=\"javascript:ckSqb('"+rowObject["sqid"]+"');\" class='name'>"+cell+"</a>";
				   }},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
				   {label:'������Ŀ',name:'xmmc', index: 'xmdm',width:'13%',formatter:show},
				   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'13%'},
				   {label:'��Ŀ����',name:'xmdm', index: 'xmdm',width:'13%',hidden:true},
				   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
				   {label:'splc',name:'splc', index: 'splc',hidden:true},
				   {label:'���״̬',name:'shztmc', index: 'shzt',width:'5%'},
				   {label:'�������NEW',name:'shlc', index: 'shlc',width:'5%',hidden:true}
				   
				],
				sortname:"sqsj",
				sortorder:"desc",
				radioselect:true
			};
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/rcsw_txhd_xmsq" method="post" styleId="TxhdXmsqJsForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
								<li>
									<a href="javascript:void(0);" class="btn_zj" onclick="editSqb();return false;" 
									   title="����ð�ť�����������дҳ�档"
									>
									����
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="updateXmsq();return false;" class="btn_xg"
									   title="ѡ��һ����¼������ð�ť���޸������"
									>�޸�</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xmsqDelete();return false;" class="btn_sc"
									   title="ֻ��ɾ��δ��˻��˻صļ�¼���Ѿ�����˵Ĳ���ɾ����"
									>ɾ��</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
										title="��������д�����δ�ύ�ļ�¼�����ύ���������"
									>�ύ</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr"
										 title="�������ύδ��˵ļ�¼���г���������"
									>����</a>
								</li>
							<li>
								<a href="javascript:void(0);" onclick="xmsqLcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
							</li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
				<span>�����б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
