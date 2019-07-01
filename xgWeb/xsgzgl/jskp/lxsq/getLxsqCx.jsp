<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/lxsq/js/lxsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "jskp_lxsq.do?method=seachForLxsq&type=query",
				colList : [ 
							{label : 'key',name : 'sqid',index : 'sqid',key : true,hidden : true}, 
							{label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '10%',formatter : xmmcLink}, 
							{label : '��Ŀ���',name : 'xmlbmc',index : 'xmlbmc',width : '10%'},
							{label : 'ָ������',name : 'bmmc',index : 'bmmc',width : '10%'},
							{label : '������',name : 'fzrxm',index : 'fzrxm',width : '10%'},
							{label : '����ʱ��',name : 'lxsj',index : 'lxsj',hidden : true},
							{label : '���״̬',name : 'shztmc',index : 'shztmc',width : '10%'},
							/*δ���������롢������˵����̲���ʾ��Ա����*/
							<logic:equal value="1" name="sfsh">
								{label : '��Ա����',name : 'rysz',index : 'rysz',width : '10%',formatter : ryszLink},
								{label : 'shzt',name : 'shzt',index : 'shzt',hidden : true},
							</logic:equal>
							<logic:equal value="0" name="sfsh">
								{label : '���״̬',name : 'shzt1',index : 'shzt1',hidden : true},
								{label : 'ѧ��',name : 'lxxn',index : 'lxxn',width : '8%'},
							</logic:equal>
							{name : 'splcid',index : 'splcid',hidden : true}
						]
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
			if('${splc}'==null||'${splc}'==''){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_null_isopen").show();
				return false;
			}
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				�������δ���ã�����ϵ����Ա��
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/jskp_lxsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" value="${sfsh}" name="sfsh" id="sfsh"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:notEmpty name="splc">
								<li>
									<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
								</li>
							</logic:notEmpty>
						
								<li>
									<a href="javascript:void(0);" onclick="submitBusi();return false" class="btn_shuc" >�ύ</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="cancel();return false" class="btn_sr" >����</a>
								</li>
							<logic:equal value="0" name="sfsh">
								<logic:notEqual value="stu" name="userType">
									<li>
										<a href="javascript:void(0);" onclick="batchSubmission();return false" class="btn_shuc" >�����ύ</a>
									</li>
									<li>
										<a href="javascript:void(0);" onclick="dataImport();return false" class="btn_dr" >����</a>
									</li>
								</logic:notEqual>
							</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ŀ�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
