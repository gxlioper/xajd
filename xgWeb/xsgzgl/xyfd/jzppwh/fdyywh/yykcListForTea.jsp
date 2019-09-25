<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/jzppwh/fdyywh/js/wfcyy.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "�ҵ�ԤԼ�б�",
				pager : "pager",
                url : "xyfd_fdyy.do?method=fdyyListTea&type=query",
                colList : [
                    { label : 'jgid', name : 'jgid', index : 'jgid',key : true,hidden : true },
                    { label : '�ǼǺ�', name : 'fdjs', index : 'fdjs', width : '8%',hidden:true  },
                    { label : '������ʦ�û���', name : 'yhm', index : 'yhm', width : '10%',hidden:true},
                    { label : '�γ�����', name : 'kcmc', index : 'kcmc', width : '15%',formatter:kcLink },
                    { label : '������ʦ', name : 'xm', index : 'xm', width : '10%' ,formatter:jsLink},
                    { label : '������ʦ����', name : 'fdjslx', index : 'fdjslx', width : '15%' },
                    { label : '������', name : 'fdsmc', index : 'fdsmc', width : '15%' },
                    { label : '�����ҵص�', name : 'fdsdd', index : 'fdsdd', width : '15%' },
                    { label : '������Դ', name : 'sjly', index : 'sjly', hidden : true},
                    { label : 'lrsj', name : 'lrsj', index : 'lrsj', hidden : true},
                    { label : 'ԤԼ״̬', name : 'lrsj', index : 'lrsj', hidden : true}
                ]
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
		<html:form action="/xyfd_fqyy">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="isJsOrPb" value="true">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add('tea');return false;"  >ԤԼ</a>
						</li>
						</logic:equal>
					</ul>
				</div>

				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
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
