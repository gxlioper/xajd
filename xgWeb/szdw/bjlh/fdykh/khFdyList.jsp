<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"����Ա��Ϣ�б�",
				pager:"pager",
				url:"bjlh_fdykh.do?method=fdykhXskhcp&type=query",
				colList:[
				   {label:'ְ����',name:'zgh', index: 'zgh',width:'20%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'20%'},
				   {label:'���',name:'lx', index: 'lx',width:'20%'},
				   {label:'�Ƿ��ѿ���',name:'sfykh', index: 'sfykh',width:'20%'}
				],
				sortname: "zgh",
			 	sortorder: "asc"
			}


			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function fdykh(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ���˵ļ�¼��");
				} else {
					var url = 'bjlh_fdykh.do?method=fdykhXskhpf&khzgh='+rows[0]["zgh"];
					url+='&flag=fh';
					var title = "��ʦ��Ϣ����";
					//showDialog(title,800,520,url);
					refreshForm(url);
				}
				
			}


			
		</script>
	</head>

	<body>
		<html:form action="/bjlh_fdykh">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="fdykh();" class="btn_xg">����</a></li>
				</ul>
			</div>
			</logic:equal>
		</div>
		
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ����Ա������Ϣ�б� </span>
			</h3>

			<table id="dataTable"></table>

		</div>
		</html:form>
	</body>
</html>
