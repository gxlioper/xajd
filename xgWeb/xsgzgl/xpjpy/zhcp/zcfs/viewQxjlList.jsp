<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"���ύ�۲�ְ༶�б�",
				pager:"pager",
				url:"xpj_zcfs.do?method=viewQxjlList&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
				   <%--{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},--%>
//				   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
                    {label:'��Ժ',name:'symc', index: 'sydm',width:'23%'},
				   {label:'�����༶',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'23%'},
				   {label:'�ύ��',name:'qxr', index: 'qxr',width:'10',hidden:true},
				   {label:'������',name:'qxrxm', index: 'qxrxm',width:'12%'},
				   {label:'����ʱ��',name:'qxsj', index: 'qxsj',width:'18%'},
				   <logic:equal value="1" name="szyf">
				   {label:'�۲�����',name:'yf', index: 'yf',width:'10%'},
				   </logic:equal>
				   {label:'ȡ��ԭ��',name:'qxyy',index:'qxyy',width:'15%',hidden:true}
				],
				sortname: "qxsj",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//ǰ��ȡ��������¼
			function goYtjbj(){
				var url = "pj_zcfqx.do";
				refreshForm(url);
			}

			//�鿴ȡ����¼
			
			function qxjlView(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
					return false;
				} 
				
				var id = rows[0]["id"];
				showDialog("�鿴�۲��",510,295,"xpj_zcfs.do?method=qxjlView&id="+id);
			}
			
			
			//�۲��ȡ���ύ��¼����
			function exportConfig(){
				var DCCLBH="pj_qxjl.do";
				customExport(DCCLBH, exportData);
			}
			function exportData(){
				var DCCLBH="pj_qxjl.do";
				setSearchTj();//���ø߼���ѯ����
				var url = "xpj_zcfs.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
	
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_zcfs" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->	
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li><a href="javascript:void(0);" onclick="qxjlView();" class="btn_ck">�鿴</a></li>
						</logic:equal>	
						
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>�ύ״̬ȡ����¼ </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
