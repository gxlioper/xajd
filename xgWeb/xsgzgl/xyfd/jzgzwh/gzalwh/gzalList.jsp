<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/jzgzwh/gzalwh/js/gzal.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "���������б�",
				pager : "pager",
                url : "xyfd_gzaljl.do?method=gzalList&type=query",
                colList : [
                    { label : 'jdh', name : 'jdh', index : 'jdh',key : true,hidden : true },
                    { label : 'ѧ������', name : 'xm', index : 'xm',width:'10%'},
                    { label : 'ѧ��', name : 'xh', index : 'xh',width:'10%'},
                    { label : '��Ժ', name : 'symc', index : 'symc',width:'10%'},
                    { label : 'ѧԺ', name : 'xymc', index : 'xymc',width:'10%'},
                    { label : '��������', name : 'aljb', index : 'aljb',width:'10%'},
                    { label : '״̬', name : 'alzt', index : 'alzt', width : '5%',hidden : true },
                    { label : '״̬', name : 'alztmc', index : 'alztmc', width : '5%' },
                    { label : '������', name : 'jdr', index : 'jdr', width : '5%',hidden : true },
                    { label : '����ʱ��', name : 'jdsj', index : 'jdsj', width : '5%',hidden : true },
                    { label : '�Ƿ�ת��', name : 'sfzj', index : 'sfzj', width : '5%',hidden : true }
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
		<html:form action="/xyfd_gzaljl">
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="printGzal('xyfd_gzaljl.do?method=printJsp');return false;"  >����ģ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_dr" onclick="importConfig();return false;"  >������Ϣ</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="ckal();return false;"  >�鿴��������</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addGzal();return false;"  >�½��������</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addGzjl();return false;"  >��ӹ�����¼</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cd();return false;" class="btn_shuc" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="zj();return false;" class="btn_xg" >ת��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delAl();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xgjb();return false;" class="btn_shuc">�޸ļ���</a>
						</li>
						</logic:equal>
							<%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>--%>
							<%--<li><a href="javascript:void(0);" onclick="printzzdsqb('xgygl_zdsq.do?method=printzzdsqb');return false;" class="btn_down">���صǼǱ�</a></li>	--%>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
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
