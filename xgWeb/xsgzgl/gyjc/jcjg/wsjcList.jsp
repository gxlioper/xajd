<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcjg/js/ccrc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_wsjc.do?method=wsjcList&type=query",
				colList : [   {
					label : '¥����',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
				}, {
					label : '���Һ�',
					name : 'qsh',
					index : 'qsh',
					width : '20%',
				}, {
					label : '����ѧԺ',
					name : 'bmmc',
					index : 'bmmc',
					width : '20%',
				}, {
					label : '���ʱ��',
					name : 'jcsj',
					index : 'jcsj',
					width : '30%',
				},
				{
					label : '���۵ȼ�',
					name : 'pjdj',
					index : 'pjdj',
					width : '10%',
				},
                    {
                        label : 'key',
                        name : 'jcrq',
                        index : 'jcrq',
                        hidden : true
                    }
                    ,
                    {
                        label : 'key2',
                        name : 'lddm',
                        index : 'lddm',
                        hidden : true
                    }
			],
				sortname : "jcsj",
				sortorder : "desc",
				radioselect:true
			}
			var map = getSuperSearch();
			map["xydm"]=jQuery("#xydm").val();
			map["js"]=jQuery("#js").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gyjc_ccrcsz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="js" styleId="js" value="${userType}"/>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						
						<li>
							<a href="javascript:void(0);" class="btn_sz" onclick="jcjgck();return false;"  >�鿴</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updatejc();return false;" class="btn_sz" >�޸�</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>

						
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��������б�&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
