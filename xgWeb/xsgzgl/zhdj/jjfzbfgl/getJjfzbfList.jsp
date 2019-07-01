<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zhdj/jjfzbfgl/js/jjfzbfgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "sgygc_jjfzbfgl.do?method=getJjfzbfList&type=query",
				colList : [
					{label : 'key', name : 'bfid', index : 'bfid', hidden : true},
                    {label : '�뵳��������', name : 'rdjjfzxm', index : 'rdjjfzxm', width : '15%'},
                    {label : '�������', name : 'bfdxxm', index : 'bfdxxm', width : '15%'},
                    {label : '��������ƻ�ʱ��', name : 'jlsj', index : 'jlsj', width : '20%'},
                    {label : '������ʵʩ��', name : 'ssqk', index : 'ssqk', width : '15%'},
                    {label : '����޸�ʱ��', name : 'zhxgsj', index : 'zhxgsj', width : '20%'}

			],
				sortname : "jlsj",
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
		<html:form action="/dzdy_cygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="js" styleId="js" value="${userType}"/>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addBfjh();return false;">��������ƻ�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="ckBfjh();return false;" class="btn_ck" >����ƻ��鿴</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateBfjh();return false;" class="btn_xg" >����ƻ��޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_sc" onclick="delBfjh();return false;">����ƻ�ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="whSsqk();return false;" class="btn_sz" >ά��ʵʩ�����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="ckSsqk();return false;" class="btn_ck" >�鿴ʵʩ�����</a>
						</li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>





					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">

			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
