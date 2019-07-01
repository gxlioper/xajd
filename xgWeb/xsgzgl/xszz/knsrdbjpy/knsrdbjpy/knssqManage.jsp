<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrdbjpy/knsrdbjpy/js/knsrdbjpy.js"></script>
		<script type="text/javascript">
	function initGridSetting(){
		var gridSetting = {};
		
		if("xq"==jQuery("#sqzq").val()){
			gridSetting = {
					caption:"�������϶������б�",
					pager:"pager",
					url:"xszz_knsrdbjpy.do?method=knssqManage&type=query",
					colList:[
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
					   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'6%'},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
					   {label:'���뵵��',name:'sqdcmc', index: 'sqdcmc',width:'8%'},
					   {label:'�༶����<br/>�Ƽ�����',name:'pddcmc', index: 'pddcmc',width:'8%'},
					   {label:'�϶�����',name:'dcmc', index: 'dcdm',width:'8%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'xq',name:'xq', index: 'xq',hidden:true},
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'6%'}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
				}
		}
		else{
			gridSetting = {
					caption:"�������϶������б�",
					pager:"pager",
					url:"xszz_knsrdbjpy.do?method=knssqManage&type=query",
					colList:[
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
					   {label:'���뵵��',name:'sqdcmc', index: 'sqdcmc',width:'8%'},
					   {label:'�༶����<br/>�Ƽ�����',name:'pddcmc', index: 'pddcmc',width:'8%'},
					   {label:'�϶�����',name:'dcmc', index: 'dcdm',width:'8%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'6%'}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
				}
		}
		gridSetting["params"]=getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
		}
	
		
			jQuery(function(){
				initGridSetting();

				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					jQuery("#prompt_isopen").show();
					jQuery("#prompt_null_isopen").show();
					return false;
				}
				if ("false" == isopen){
					jQuery("#prompt_isopen").show();
					jQuery("#prompt_false_isopen").show();
					return false;
				}
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}			

		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				<bean:message key="lable.jcszwcsh_prompt" />
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/xszz_jtqkdc">
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_knssqb"/>
			<input type="hidden" name="sfysq" id="sfysq" value="${sfysq }" />
			<html:hidden property="sqzq" styleId="sqzq" value="${sqzq}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="knssq();return false;"  title="����ð�ť�����������дҳ�档">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="knssqUpdate();return false;" class="btn_xg" title="ѡ��һ����¼������ð�ť���޸������">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="knssqDelete();return false;" class="btn_sc" title="ֻ��ȡ��δ��˵ļ�¼���Ѿ�����˵Ĳ���ȡ����" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="knssqLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a>
						</li>					
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
							<%--<li><a href="#" class="btn_dc" onclick="exportData();return false;">��������</a></li>--%>
						<li><a href="javascript:void(0);" onclick="printKnssq('xszz_knsrdbjpy.do?method=printJsp');return false;" class="btn_down">���صǼǱ�</a></li>						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�������϶������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
