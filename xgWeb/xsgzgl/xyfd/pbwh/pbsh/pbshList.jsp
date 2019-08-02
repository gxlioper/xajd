<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/pbwh/pbsh/js/pbSh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption : "��־Ը�ߴ�����б�",
				pager : "pager",
				url : "xyfd_pbsh.do?method=pbshList&type=query",
				colList : [
					{ label : 'sqid', name : 'sqid', index : 'sqid',key : true,hidden : true },
					{ label : '��������', name : 'splc', index : 'splc',hidden : true },
					{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
					{ label : '����', name : 'xm', index : 'xm', width : '10%' },
					{ label : '������Ŀ', name : 'fdkm', index : 'fdkm', width : '15%' },
					{ label : '������', name : 'fdsmc', index : 'fdsmc', width : '15%' },
					{ label : '�����ҵص�', name : 'fdsdd', index : 'fdsdd', width : '15%' },
					{ label : '��ϵ�绰', name : 'lxdh', index : 'lxdh', width : '15%' },
                    { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
                    { label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
                    { label : '���Id',name:'shid', index: 'shid',hidden:true},
                    { label : 'lrsj', name : 'lrsj', index : 'lrsj', hidden : true},
                    { label : '��λId',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"dsh"},
				radioselect:false
		}

		var gridSetting2 = {
				caption : "��־Ը��������б�",
				pager : "pager",
				url : "xyfd_pbsh.do?method=pbshList&type=query",
				colList : [
					{ label : 'sqid', name : 'sqid', index : 'sqid',key : true,hidden : true },
					{ label : '��������', name : 'splc', index : 'splc',hidden : true },
					{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
					{ label : '����', name : 'xm', index : 'xm', width : '10%' },
					{ label : '������Ŀ', name : 'fdkm', index : 'fdkm', width : '15%' },
					{ label : '������', name : 'fdsmc', index : 'fdsmc', width : '15%' },
					{ label : '�����ҵص�', name : 'fdsdd', index : 'fdsdd', width : '15%' },
					{ label : '��ϵ�绰', name : 'lxdh', index : 'lxdh', width : '15%' },
                    { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
                    { label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
                    { label : '���Id',name:'shid', index: 'shid',hidden:true},
                    { label : 'lrsj', name : 'lrsj', index : 'lrsj', hidden : true},
                    { label : '��λId',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"ysh"},
			 	radioselect:true
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xyfd_pbsh">
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sh();return false;"
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���¼����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>