<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/xsqshf/js/xsqshf.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/comm/js/comm.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"���һ���",
				pager:"pager",
				url:"xszyXsqshf.do?method=getQshfxxList&type=query",
				colList:[
					{label:'lddm',name:'lddm', index: 'lddm',hidden:true},
					{label:'dldm',name:'dldm', index: 'dldm',hidden:true},
					{label:'nj',name:'nj', index: 'nj',hidden:true},
					{label:'qsgxid',name:'qsgxid', index: 'qsgxid',hidden:true},
					{label:'���Һ�',name:'qsh', index: 'qsh',width:'8%',formatter:qshLink},
					{label:'¥������',name:'ldmc', index: 'ldmc',width:'12%'},
					{label:'ѧ԰',name:'xymc', index: 'xymc',width:'14%'},
					{label:'�Ա�',name:'qsxb', index: 'qsxb',width:'6%'},
					{label:'����',name:'dl', index: 'dl',width:'17%'},
					{label:'�༶',name:'bjmc', index: 'bjmc',width:'17%'},
					{label:'��ס����',name:'rzrs', index: 'rzrs',width:'5%'},
					{label:'�Ƿ��</br>������',name:'sfhhqs', index: 'sfhhqs',width:'5%'},
					{label:'����',name:'', index: '',width:'16%',noSort:true,formatter:sdfpLink}
					],
				params:{fpzt:"dfp"},
				
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"���һ���",
				pager:"pager",
				url:"xszyXsqshf.do?method=getQshfxxList&type=query",
				colList:[
					{label:'lddm',name:'lddm', index: 'lddm',hidden:true},
					{label:'dldm',name:'dldm', index: 'dldm',hidden:true},
					{label:'qsgxid',name:'qsgxid', index: 'qsgxid',hidden:true},
					{label:'nj',name:'nj', index: 'nj',hidden:true},
					{label:'id',name:'id', index: 'id',hidden:true,key:true},
					{label:'���Һ�',name:'qsh', index: 'qsh',width:'8%',formatter:qshLink},
					{label:'¥������',name:'ldmc', index: 'ldmc',width:'12%'},
					{label:'ѧ԰',name:'xymc', index: 'xymc',width:'12%'},
					{label:'�Ա�',name:'qsxb', index: 'qsxb',width:'5%'},
					{label:'����',name:'dl', index: 'dl',width:'18%'},
					{label:'��ס����',name:'rzrs', index: 'rzrs',width:'5%'},
					{label:'�Ƿ�������',name:'sfhhqs', index: 'sfhhqs',width:'5%'},
					{label:'����Ժϵ',name:'ssyxdm', index: 'ssyxdm',hidden:true},
					{label:'����Ժϵ',name:'ssyxmc', index: 'ssyxmc',width:'16%'},
					{label:'����',name:'', index: '',width:'9%',noSort:true,formatter:tzfpLink}
					],
				params:{fpzt:"yfp"},
				
			 	radioselect:false
		}
		var gridSetting3 = {
				caption:"���һ���",
				pager:"pager",
				url:"xszyXsqshf.do?method=getQshfxxList&type=query",
				colList:[
					{label:'lddm',name:'lddm', index: 'lddm',hidden:true},
					{label:'dldm',name:'dldm', index: 'dldm',hidden:true},
					{label:'qsgxid',name:'qsgxid', index: 'qsgxid',hidden:true},
					{label:'nj',name:'nj', index: 'nj',hidden:true},
					{label:'���Һ�',name:'qsh', index: 'qsh',width:'5%',formatter:qshLink},
					{label:'¥������',name:'ldmc', index: 'ldmc',width:'12%'},
					{label:'ѧ԰',name:'xymc', index: 'xymc',width:'10%'},
					{label:'�Ա�',name:'qsxb', index: 'qsxb',width:'5%'},
					{label:'����',name:'dl', index: 'dl',width:'17%'},
					{label:'�༶',name:'bjmc', index: 'bjmc',width:'17%'},
					{label:'��ס����',name:'rzrs', index: 'rzrs',width:'5%'},
					{label:'�Ƿ��</br>������',name:'sfhhqs', index: 'sfhhqs',width:'5%'},
					{label:'�˻���',name:'thr', index: 'thr',width:'8%'},
					{label:'����',name:'', index: '',width:'16%',noSort:true,formatter:sdfpLink}
					],
				params:{fpzt:"yth"},
				
			 	radioselect:false
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["fpzt"]="dfp";
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
		<html:form action="/xszyXsqshf">
			<input type="hidden" id="fpzt" value="dfp"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_plfp">
							<a href="javascript:void(0);" onclick="plfp();return false;" 
							   title="����������Ժϵ"
							   class="btn_zj">����������Ժϵ</a>
						</li>						
						<li id="li_qxfp" style="display: none;">
							<a href="javascript:void(0);" onclick="qxFp();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ��ķ��������"
							   class="btn_sr">ȡ������</a>
						</li>
						<li id="li_fpxq" style="display: none;">
							<a href="xszyXsqshf.do?method=fpxqCk"
							   title="ѡ��һ����¼������ð�ť�����Բ鿴�������顣"
							   class="btn_ck">��������</a>
						</li>
						<li id="li_fptj" style="display: none;">
							<a href="xszyXsqshf.do?method=fptjCk"
							   class="btn_ck">ѧ԰����ͳ��</a>
						</li>			
						</logic:equal>					
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dfp');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yth');">
			        <logic:equal value="xx" name="userStatus">
			        <span>ѧ԰�˻�</span>
			        </logic:equal>
			        <logic:notEqual value="xx" name="userStatus">
			        <span>���˻�</span>
			        </logic:notEqual>
			        </a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yfp');"><span>�ѷ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���һ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
