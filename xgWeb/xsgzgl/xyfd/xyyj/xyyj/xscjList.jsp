<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/xyyj/xyyj/js/xyyj.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "�ɼ��б�",
				pager : "pager",
                url : "xyfd_xyyj.do?method=xscjList&type=query",
                colList : [
                    { label : 'ѧ��', name : 'xn', index : 'xq',width:'10%'},
                    { label : 'ѧ��', name : 'xq', index : 'xq',width:'10%',hidden:true},
                    { label : 'ѧ��', name : 'xqmc', index : 'xqmc',width:'10%'},
                    { label : '�����༶', name : 'bjdm', index : 'bjdm',width:'10%',hidden:true},
                    { label : '�����༶', name : 'bjmc', index : 'bjmc',width:'10%'},
                    { label : '�������Ŀ', name : 'kcmc', index : 'kcmc', width : '10%' },
                    { label : '�ɼ�', name : 'cj', index : 'cj', width : '5%' },
                    { label : 'ѧ��', name : 'xf', index : 'xf', width : '5%' },
                    { label : '�γ�����', name : 'kcxz', index : 'kcxz', width : '10%' },
                    { label : '�γ����', name : 'kclx', index : 'kclx', width : '10%'}
                ],
                sortname : "xq,xn",
                sortorder : "desc"
            }
			var map = getSuperSearch();
			map["xh"] = jQuery("#xh").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})

        function searchRs() {
            var map = getSuperSearch();
            map["xh"] = jQuery("#xh").val();
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
		<html:form action="/xyfd_zjyj">
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���¼�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<input type="hidden" id="xh" name="xh" value="${model.xh}"/>
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
