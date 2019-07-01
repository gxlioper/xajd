<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/sh/js/jcftzsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"У����չ�������б�",
				pager:"pager",
				url:"jcftz_sh.do?method=getJcftzShList&type=query",
				colList : [
							{ label : 'rdsqid', name : 'rdsqid', index : 'rdsqid',key : true, hidden : true },
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'rdsplc', name : 'rdsplc', index : 'rdsplc',hidden : true },
							{ label : 'xmdm', name : 'xmdm', index : 'xmdm',hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%'},
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc' ,width : '7%' },
							{ label : 'ѧ��', name : 'xq', index : 'xq',hidden : true },
							{ label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmmcLink },
							{ label : '��Ŀ����',name : 'csmsmc',index : 'csmsmc',width : '5%'},
							{ label : '��Ŀ����', name : 'xmjbmc', index : 'xmjbmc', width : '5%' },
							{ label : '������Ŀ', name : 'sskmmc', index : 'sskmmc', width : '15%' },
							{ label : '��Ŀ��ʼʱ��', name : 'xmkssj', index : 'xmkssj', width : '15%' },
							{ label : '��/����', name : 'ybjrs', index : 'ybjrs', width : '10%',formatter : rsLink },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '18%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
							{label : '��Ŀ����',name : 'csms',index : 'csms',hidden:true},
							{label:'���Id',name:'shid', index: 'shid',hidden:true},
							{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
							],
				params:{shzt:"dsh"},
				sortname: "xmkssj",
			 	sortorder: "desc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"У����չ�������б�",
				pager:"pager",
				url:"jcftz_sh.do?method=getJcftzShList&type=query",
				colList : [
							{ label : 'rdsqid', name : 'rdsqid', index : 'rdsqid',key : true, hidden : true },
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'rdsplc', name : 'rdsplc', index : 'rdsplc',hidden : true },
							{ label : 'xmdm', name : 'xmdm', index : 'xmdm',hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%'},
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc' ,width : '7%' },
							{ label : 'ѧ��', name : 'xq', index : 'xq',hidden : true },
							{ label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmmcLink },
							{ label : '��Ŀ����',name : 'csmsmc',index : 'csmsmc',width : '5%'},
							{label : '��Ŀ����',name : 'csms',index : 'csms',hidden:true},
							{ label : '��Ŀ����', name : 'xmjbmc', index : 'xmjbmc', width : '5%' },
							{ label : '������Ŀ', name : 'sskmmc', index : 'sskmmc', width : '15%' },
							{ label : '��Ŀ��ʼʱ��', name : 'xmkssj', index : 'xmkssj', width : '15%' },
							{ label : '��/����', name : 'ybjrs', index : 'ybjrs', width : '10%',formatter : rsLink },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '18%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
							{label:'���Id',name:'shid', index: 'shid',hidden:true},
							{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
							],
				params:{shzt:"ysh"},
				sortname: "xmkssj",
			 	sortorder: "desc",
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
		<html:form action="/jcftz_sh">
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sbsh();return false;" 
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
<%--						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>--%>
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
				<span>У����չ�������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
