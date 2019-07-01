<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/zzdgl/jg/js/zzdqr.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"ѧ��ת�߶���ϸ�������Ա��ȷ���б�",
				pager:"pager",
				url:"xgygl_zdjg.do?method=getPageListForQr&type=query",
				colList : [
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'zzdid', name : 'zzdid', index : 'zzdid', hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '15%',formatter:xhLink },
							{ label : '����', name : 'xm', index : 'xm', width : '15%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '15%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : 'ѧ��ѧ��', name : 'xnxq', index : 'xnxq', width : '10%' },
							{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '15%' },
							{ label : '����Աȷ��', name : 'qrztmc', index : 'qrztmc', width : '10%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true}],
				params:{qrzt:"dsh"},
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"ѧ��ת�߶���ϸ�������Ա��ȷ���б�",
				pager:"pager",
				url:"xgygl_zdjg.do?method=getPageListForQr&type=query",
				colList : [
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'zzdid', name : 'zzdid', index : 'zzdid', hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '15%',formatter:xhLink },
							{ label : '����', name : 'xm', index : 'xm', width : '15%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '15%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : 'ѧ��ѧ��', name : 'xnxq', index : 'xnxq', width : '10%' },
							{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '15%' },
							{ label : '����Աȷ��', name : 'qrztmc', index : 'qrztmc', width : '10%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true}],
				params:{qrzt:"ysh"},
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:true
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["qrzt"]="dsh";
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
		<html:form action="/xgygl_zdqr">
			<input type="hidden" id="qrzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="qr();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">ȷ��</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancel();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">ȷ��ȡ��</a>
						</li>	
						</logic:equal>					
						<%--<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					--%></ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>δ����</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ��ת�߶��������Աȷ���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
