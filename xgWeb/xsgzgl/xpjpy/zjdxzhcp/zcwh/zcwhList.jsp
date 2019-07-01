<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/zcwh/js/zcwh.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�۲�༶�б�",
				pager:"pager",
				url:"xpjpy_zcwh.do?method=getZcwhList&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧԺ����',name:'xydm', index: 'xydm',hidden:true},
				   {label:'ѧԺ',name:'xymc', index: 'xydm',width:'12%'},
				   {label:'������',name:'xyrs', index: 'xyrs',width:'6%',formatter:cprLink},
				   {label:'�ύ״̬',name:'tjzt', index: 'tjzt',hidden:true},
				   {label:'�ύ״̬',name:'tjztmc', index: 'tjztmc',width:'8%'},
				   {label:'��Ŀ�������',name:'xmdm', index: 'xmdm',width:'8%',hidden:true},
				   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'8%'},
				   {label:'����ά��',name:'', index: '',width:'8%',formatter:zcczLink}
				],
				sortname: "tjzt,xymc",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			/*��ѯ*/
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>
	<body>
		<input type="hidden" value="${cssz.xn}" id="xn"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpjpy_zcwh" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ��ʾ��Ϣ end-->
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="1" name="cssz" property="pjkg">
								<li>
									<a href="javascript:void(0);" class="btn_xg" onclick="zcwhEdit();">��������ά��</a>
								</li>
								<li>
									<a href="javascript:void(0);" class="btn_up" onclick="zcwhSubmit();">�ύ</a>
								</li>
								<!-- ֻ�й���Ա�ſ���ȡ���ύ�۲�� -->
								<logic:equal value="xx" name="userType">
									<li>
										<a href="javascript:void(0);" class="btn_up" onclick="zcwhCancelSubmit();">ȡ���ύ</a>
									</li>
								</logic:equal>
							</logic:equal>
								<li>
									<a href="javascript:void(0);" class="btn_ck" onclick="zcwhView();">�鿴</a>
								</li>
							<!-- �˹����������β��ã�����Ҫ��ʱ�������¹�˼
								�����������һ��ͬ�����֮��ѧ����ط�����
								<li>
									<a href="javascript:void(0);" onclick="dataSynchronization();" class="btn_sx">ͬ���۲���Ŀ��</a>
								</li>
							-->
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
				<span><font color="blue">${cssz.xn}&nbsp;</font>�۲�ѧԺ�б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
