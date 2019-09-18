<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/xyyj/zjyj/js/zjyj.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "���������б�",
				pager : "pager",
                url : "xyfd_zjyj.do?method=getZjyjList&type=query",
                colList : [
                    { label : 'zjid', name : 'zjid', index : 'zjid',key : true,hidden : true },
                    { label : 'ѧ������', name : 'xm', index : 'xm',width:'10%'},
                    { label : 'ѧ��', name : 'xh', index : 'xh',width:'10%'},
                    { label : 'ת��ԭ��', name : 'zjyy', index : 'zjyy',width:'10%'},
                    { label : 'ת����', name : 'zjr', index : 'zjr', width : '10%',hidden:true },
                    { label : 'ת����', name : 'zjrxm', index : 'zjrxm', width : '10%' },
                    { label : '��ϸ��Ϣ', name : 'xxxx', index : 'xxxx', width : '25%'},
                    { label : 'ȷ����Ϣ', name : 'qrsj', index : 'qrsj', width : '15%',formatter : function (cell,rowObject) {
						if(rowObject["qrsj"]!=null&&rowObject["qrsj"]!=""){
						    return "��"+rowObject["qrsj"]+"����";
						}else {
						    return "��δȷ��";
						}
                    } }
                ],
                sortname : "jdsj",
                sortorder : "desc"
            }
			var map = getSuperSearch();
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
		<html:form action="/xyfd_zjyj">
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="jsyj();return false;" class="btn_sr" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="fhyj();return false;" class="btn_shuc">����</a>
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
