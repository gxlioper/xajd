<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/xyyj/yjgy/js/yjgy.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "ѧҵԤ���б�",
				pager : "pager",
                url : "xyfd_yjgy.do?method=yjgyList&type=query",
                colList : [
                    { label : 'ѧ������', name : 'xm', index : 'xm',width:'20%'},
                    { label : 'ѧ��', name : 'xh', index : 'xh',width:'10%',formatter:function (cell,rowObject) {
                        return "<a href='#' onclick=xscjck('" + rowObject["xh"] + "')>" + rowObject["xh"] + "</a>";
                    }},
                    { label : 'Ԥ��ԭ��', name : 'yjyy', index : 'yjyy',width:'10%'},
                    { label : '��ϸ��Ϣ', name : 'xxxx', index : 'xxxx', width : '60%'}
                ],
                sortname : "xh",
                sortorder : "desc"
            }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})

        function searchRs() {
            var map = getSuperSearch()
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
		<html:form action="/xyfd_xyyj">
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="yjzj();return false;"  >ת��</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="gyjs();return false;"  >��Ԥ����</a>
						</li>
						</logic:equal>
					</ul>
				</div>
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
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
