<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/zzgxzc/xxjg/js/xxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��Ϣ����б�",
				pager:"pager",
				url:"dtjs_xxjg.do?method=xxjgList&type=query",
				colList:[
				   {label:'��Ϣ���id',name:'jgid', index: 'jgid',key:true,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'12%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'14%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'14%'},
				   {label:'������ò',name:'zzmmmc', index: 'zzmmmc',width:'8%'},
				   {label:'���ڵ�֧��',name:'szdzbmc', index: 'szdzbmc',width:'11%'},
				   {label:'���ձ�����֯��ϵ�ĵ���֯',name:'jsdzz', index: 'jsdzz',width:'11%'},
				   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'6%'},
				   {label:'sjly',name:'sjly',index:'sjly',hidden:true}
				],
				sortname: "sqsj",
			 	sortorder: "desc"
			}

			function xhLink(cellValue,rowObject){
				var id = rowObject["jgid"];
				return "<a href='javascript:void(0);' onclick=\"xxjgShow('"+id+"')\" class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function dzzgxdc(){
				var ids = jQuery("#dataTable").getSeletIds();
				var len = ids.length;
				if (len == 1) {
					var url = "dtjs_xxjg.do?method=zzgxdjbDc";
					url += "&id=" + ids;
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
					return false;
				} else {
					var url = "dtjs_xxjg.do?method=zzgxdjbDcTy";
					url += "&value=" + ids;
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
		<html:form action="/xszz_zzkff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="xxjgAdd()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="xxjgUpdate();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="xxjgDelete()" class="btn_sc">ɾ��</a></li>
						</logic:equal>	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<logic:equal value="12309" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="dzzgxdc();return false;">��Ա��֯��ϵ������</a></li>
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
				<span> ��Ϣ����б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
