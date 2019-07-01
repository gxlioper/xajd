<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		var DCCLBH="rcsw_cwsj.do";
		var gridSetting = {
				caption:"������Ϣ",
				pager:"pager",
				url:"rcsw_cwsj.do?method=getCwsjList&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'7%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
				   {label:'ѧ��',name:'xqmc', index: 'xq',width:'3%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'15%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
				   {label:'Ӧ��',name:'zd2', index: 'zd2',width:'5%'},
				   {label:'ʵ��',name:'zd3', index: 'zd3',width:'5%'},
				   {label:'Ƿ��',name:'zd1', index: 'zd1',width:'8%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
		function xhLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
		}
		function zxsxxView(xh){
			
			var pkValue=xh;
			var url="xsxx_tygl.do?method=ckZxsxx";
			url+="&xh="+pkValue;
			var width=850;
			showDialog('',850,640,url);
	}

			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}
			function exportConfig(){
				customExport('rcsw_cwsj.do', exportData);
			}
			function exportData(){
				setSearchTj();//���ø߼���ѯ����
				var url = "rcsw_cwsj.do?method=exportData&dcclbh="+DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_cwsj">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
				<span> ѧ�����������б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
