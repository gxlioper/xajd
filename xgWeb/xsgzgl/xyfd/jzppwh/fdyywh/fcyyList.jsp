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
                url : "xyfd_fqyy.do?method=fcyyList&type=query",
                colList : [
                    { label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
                    { label : 'ԤԼ��', name : 'yyh', index : 'yyh',width:'8%',formatter:yyhLink},
                    { label : 'ԤԼѧ��', name : 'xh', index : 'xh',hidden:true},
                    { label : 'ԤԼѧ��', name : 'xm', index : 'xm',width:'10%'},
                    { label : 'ԤԼ��', name : 'yyr', index : 'yyr',width:'5%'},
                    { label : '����ʱ��', name : 'fdsj', index : 'fdsj',width:'10%'},
                    { label : '������ʦ', name : 'fdjs', index : 'fdjs', width : '10%',hidden:true },
                    { label : '������ʦ', name : 'yhm', index : 'yhm', width : '10%',hidden:true },
                    { label : '������ʦ', name : 'fdjsxm', index : 'fdjsxm', width : '10%',hidden:true },
                    { label : '�γ�����', name : 'kcmc', index : 'kcmc', width : '15%',hidden:true },
                    { label : '������', name : 'fdsmc', index : 'fdsmc', width : '15%',hidden:true },
                    { label : '�����ҵص�', name : 'fdsdd', index : 'fdsdd', width : '15%',hidden:true },
                    { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '5%' },
                    { label : '���״̬', name : 'zt', index : 'zt', hidden : true},
                    { label : 'ԤԼ����', name : 'shztmc', index : 'shztmc', width : '40%',formatter:function (cellValue, rowObject) {
						return "��" + rowObject['fdsj'] + "���ڣ�" + rowObject['fdsdd'] + "���ɣ�" + rowObject['fdjsxm'] + "�����еģ�" + rowObject['kcmc'] + "��";
                    } }
                ],
                sortname : "yyh",
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
		<html:form action="/xyfd_fqyy">
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<%--<li>--%>
							<%--<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>--%>
						<%--</li>--%>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύԤԼ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">ȡ��ԤԼ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="pjkc();return false;" class="btn_sr">���ۿγ�</a>
						</li>
						</logic:equal>
							<%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>--%>
							<%--<li><a href="javascript:void(0);" onclick="printzzdsqb('xgygl_zdsq.do?method=printzzdsqb');return false;" class="btn_down">���صǼǱ�</a></li>	--%>
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
