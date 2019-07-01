<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		//��ʼ����ѯ
		var gridSetting = {
				caption:"ѧ���춯����б�",
				pager:"pager",
				url:"xjydsh.do?method=xjydshList&type=query",
				colList:[
						   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm'},
						   {label:'ԭ<bean:message key="lable.xb" />',name:'ydqxymc', index: 'ydqxymc'},
						   {label:'ԭ�༶',name:'ydqbjmc', index: 'ydqbjmc'},
						   {label:'�춯����',name:'ydlbmc', index: 'ydlbmc'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
						   {label:'���״̬',name:'shztmc', index: 'shztmc'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
						   {label:'ydlbdm',name:'ydlbdm', index: 'ydlbdm',hidden:true},
						   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'splc',name:'splc', index: 'splc',hidden:true},
						   {label:'xjydsqid',name:'xjydsqid', index: 'xjydsqid',key:true,hidden:true}
				],
				params:{shlx:"dsh"},
				sortname: "sqsj",
			 	sortorder: "desc"
		}
		var gridSetting2 = {
				caption:"ѧ���춯����б�",
				pager:"pager",
				url:"xjydsh.do?method=xjydshList&type=query",
				colList:[
						   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm'},
						   {label:'ԭ<bean:message key="lable.xb" />',name:'ydqxymc', index: 'ydqxymc'},
						   {label:'ԭ�༶',name:'ydqbjmc', index: 'ydqbjmc'},
						   {label:'�춯����',name:'ydlbmc', index: 'ydlbmc'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
						   {label:'���״̬',name:'shztmc', index: 'shztmc'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
						   {label:'ydlbdm',name:'ydlbdm', index: 'ydlbdm',hidden:true},
						   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'splc',name:'splc', index: 'splc',hidden:true},
						   {label:'xjydsqid',name:'xjydsqid', index: 'xjydsqid',key:true,hidden:true}
				],
				params:{shlx:"ysh"},
				sortname: "shsj",
			 	sortorder: "desc"
		}
			/*
			 * �������̳���
			 * shid ���id
			 * splc ��������id 
			 */
			function splc_cx(splcid,shid){
				confirmInfo("��ȷ��Ҫ����������?",function(ty){
					if(ty=="ok"){
						jQuery.post("xjydsh.do?method=cxsh",{splcid:splcid,shid:shid},function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						},'json');
					}
				});
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_sh").click(go_sh);
				jQuery("#btn_cs").click(lcgz);
				jQuery("#btn_qxsh").click(cxshnew);
				jQuery("#btn_qxsh").hide();
			});
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="cancelPath" id="cancelPath" value="${cancelPath}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li><a href="javascript:void(0);" id="btn_sh" class="btn_sh">���</a>
							<a href="javascript:void(0);" id="btn_qxsh" class="btn_qxsh">����</a>
							</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">���̸���</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="query(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="query(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>ѧ���춯����б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
