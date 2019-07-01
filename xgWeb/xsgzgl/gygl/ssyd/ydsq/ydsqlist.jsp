<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			     var gridSetting = {
							caption:"�����춯�����б�",
							pager:"pager",
							url:"ydsq.do?method=list&type=query",
							colList:[
							   {label:'�����춯id',name:'ssydsqid', index: 'ssydsqid',key:true,hidden:true},
							   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
							   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink},
							   {label:'����',name:'xm', index: 'xm'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
							   {label:'�༶',name:'bjmc', index: 'bjmc'},
							   {label:'�Ա�',name:'xb', index: 'xb'},
							   {label:'����',name:'qsmc', index: 'qsmc'},
							   <logic:equal name="xxdm" value="13627">
							   {label:'����Ա',name:'fdyxm', index: 'fdyxm'},	
							   </logic:equal>
							   {label:'�춯����',name:'ydlxmc', index: 'ydlxmc'},
							   {label:'ssydlx',name:'ssydlx', index: 'ssydlx',hidden:true},
							   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
							   {label:'���״̬',name:'shztmc', index: 'shztmc'},
							   {label:'���',name:'shzt', index: 'shzt',hidden:true}
							],
							sortname: "sqsj",
						 	sortorder: "desc"
							 	
						}			
			    gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			//��������ת
			function dcmcLink(cellValue, rowObject) {
				var ssydsqid = rowObject["ssydsqid"];
				return "<a href='javascript:void(0);' onclick=\"ckxx('" + ssydsqid
						+ "')\" class='name'>" + cellValue + "</a>";
			}
			
		 	//���״̬Ϊ1(�Ѿ��ύ)�� ���ܲ���
		 	//jQuery("#dataTable").unSelect("qjzt","1");
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/ydsq?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		
        <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
					</logic:equal>
					<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
						   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
						   class="btn_cs">���̸���</a></li>
					<logic:equal value="yes" name="writeAble">	   
					<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</logic:equal>
					<logic:equal name="xxdm" value="12865">
							<li>
								<a href="javascript:void(0);" onclick="printTstzd();return false;" class="btn_down">����֪ͨ��</a>
							</li>
					</logic:equal>
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
				<span>�����춯�����б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
