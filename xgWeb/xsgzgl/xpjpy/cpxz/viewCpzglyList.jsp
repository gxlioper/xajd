<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/cpxz/js/cpxz.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�������б�",
				pager:"pager",
				url:"xpj_cpxz.do?method=viewCpzglyList&type=query",
				colList:[
                    {label:'���������',name:'cpzdm', index: 'cpzdm',key:true,hidden:true},
				   {label:'������',name:'cpzmc', index: 'cpzmc',width:'15%'},
                    {label:'����Ա',name:'yhm', index: 'zgh',width:'15%'}
				],
                sortname: "cpzdm",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//�������Ա
			function cpzFpGly(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("��ѡ������һ����¼��");
				}else{
					showDialog('�������Ա',760, 525,'xpj_cpxz.do?method=fpgly&cpzdms='+ids);
				}
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<%--<p class="help">
				<a href="#" onclick="return false;"	onmousedown="showHelpDiv()">ʹ�ð���</a>
			</p>--%>
		</div>
		<html:form action="/xpj_cpxz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- ��ʾ��Ϣ end-->
			<%--<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span >
						ϵͳĬ��һ���༶Ϊһ�������飬��ѧУ��Ҫ������༶����Ϊһ�������飬���ڴ˴�����
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>--%>
			<!-- ��ʾ��Ϣ end-->
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<logic:equal value="true" name="cssz" property="kgzt">
						<li><a href="javascript:void(0);" onclick="cpzFpGly();return false;" class="btn_ccg" >������������Ա</a></li>
						</logic:equal>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3>
				<span><font color="blue">${cssz.zqmc}&nbsp;&nbsp;</font>������</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
