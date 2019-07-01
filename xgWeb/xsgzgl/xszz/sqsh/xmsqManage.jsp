<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"������Ŀ�����б�",
				pager:"pager",
				url:"xszz_sqsh.do?method=xmsqManage&type=query",
				colList:[
				   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
				   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
                   {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
				   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
				   {label:'����ѧ��',name:'xn', index: 'xn',width:'10%'},
				   {label:'����ѧ��',name:'xqmc', index: 'xq',width:'6%'},
				   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
				   {label:'��Ŀ����',name:'xmmc', index: 'xmdm',width:'11%'},
				   {label:'��������Ŀ',name:'tzhxmmc', index: 'tzhxmmc',width:'11%'},
				   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
				   {label:'���״̬',name:'shztmc', index: 'shzt',width:'6%'},
				   {label:'isopen',name:'isopen',index:'isopen',hidden:true}
				],
				sortname: "sqsj",
			 	sortorder: "desc"
			}
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
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
		</div>
		<html:form action="/xszz_sqsh">
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_zzxmsqb"/>
			<input type="hidden" id="SFBJPY_Y" value="${SFBJPY_Y }"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="xmsq();return false;" 
							   title="����ð�ť�����������дҳ�档"
							>����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateXmsq();return false;" class="btn_xg"
							   title="ѡ��һ����¼������ð�ť���޸������"
							>�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xmsqDelete();return false;" class="btn_sc"
							   title="ֻ��ȡ��δ��˵ļ�¼���Ѿ�����˵Ĳ���ȡ����"
							>ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="xmsqLcgz();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a>
						</li>							
			            <li>
			              <a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
			            </li>
				        
							<%--<li><a href="#" class="btn_dc" onclick="exportData();return false;">��������</a></li>--%>					
						<!--  
						<li><a href="javascript:void(0);" onclick="printXmsq('xszz_sqsh.do?method=printJsp');return false;" class="btn_dy">��ӡ�����</a></li>						
						-->
							<%--���������Ի� --%>
						<logic:equal value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">��������������</a></li>
						</logic:equal>
						<logic:notEqual value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10530">	
							<li><a href="javascript:void(0);" onclick="printSqlct();return false;" class="btn_down">��������ͼ</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="exceptionDataResolve();return false;" class="btn_xg">�ύ�쳣���ݴ���</a></li>
					</ul>
					
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ŀ�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
