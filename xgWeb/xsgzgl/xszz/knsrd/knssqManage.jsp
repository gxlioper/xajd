<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrd/js/knsrd.js"></script>
		<script type="text/javascript">
	function initGridSetting(){
		var gridSetting = {};

		if("10335" == jQuery("#xxdm").val()){
			gridSetting = {
					caption:"�������϶������б�",
					pager:"pager",
					url:"xszz_knsrd.do?method=knssqManage&type=query",
					colList:[
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
					   {label:'ѧ��',name:'xq', index: 'xq',width:'10%',hidden:true},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
					   {label:'���뵵��',name:'sqdcmc', index: 'sqdcmc',width:'8%'},
					   {label:'�϶�����',name:'dcmc', index: 'dcdm',width:'8%'},
					   {label:'��������',name:'ylzd9', index: 'ylzd9',width:'8%',formatter:sqxzLink},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'6%'}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
				}
			
		}else if ("xq"==jQuery("#sqzq").val()){
			gridSetting = {
					caption:"�������϶������б�",
					pager:"pager",
					url:"xszz_knsrd.do?method=knssqManage&type=query",
					colList:[
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
                        {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
                        {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
					   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'6%'},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
					   {label:'���뵵��',name:'sqdcmc', index: 'sqdcmc',width:'8%'},
					   {label:'�϶�����',name:'dcmc', index: 'dcdm',width:'8%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'xq',name:'xq', index: 'xq',hidden:true},
					   <logic:equal name = 'xxdm' value= '11998'>
					   {label:'����������',name:'zf', index: 'zf',hidden:true},
					   </logic:equal>
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'6%'}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
				}
		} else{
			gridSetting = {
					caption:"�������϶������б�",
					pager:"pager",
					url:"xszz_knsrd.do?method=knssqManage&type=query",
					colList:[
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
                       {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
					   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                       {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
					   {label:'���뵵��',name:'sqdcmc', index: 'sqdcmc',width:'8%'},
					   {label:'�϶�����',name:'dcmc', index: 'dcdm',width:'8%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   <logic:equal name = 'xxdm' value= '11998'>
					   {label:'����������',name:'zf', index: 'zf',hidden:true},
					   </logic:equal>
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
					jQuery("#div_help").show();
					jQuery("#prompt_null_isopen").show();
					return false;
				}
				if ("false" == isopen){
					jQuery("#div_help").show();
					jQuery("#prompt_false_isopen").show();
					return false;
				}
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}			

			function downloadDjb(lx) {
				var ids = jQuery("#dataTable").getSeletIds();
				var len = ids.length;
				if (len == 1) {
					var url = "xszz_knsrd.do?method=printDjb";
					url += "&guid=" + ids+"&lx="+lx;
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
					return false;
				} else {
					var url = "xszz_knsrd.do?method=printDjbTy";
					url += "&value=" + ids+"&lx="+lx;
					window.open(url);
				}
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
		<div class="prompt" id="div_help" style="display:none;">
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
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
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
						
						<li>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
						</li>
							<%--<li><a href="#" class="btn_dc" onclick="exportData();return false;">��������</a></li>--%>
						<%--�㽭ͬ�ø��Ի�����--%>
						<logic:equal value="12647 " name="xxdm">
							<li><a href="javascript:void(0);" 
									onclick="viewPrint();return false;" class="btn_down">���صǼǱ�</a></li>
						</logic:equal>	
						<%-- ������ͨ��ѧ��ѧԺ ���Ի�����--%>
						<logic:equal value="13431 " name="xxdm">
							<li><a href="javascript:void(0);" 
									onclick="printKnssq('xszz_knsrd.do?method=printJsp');return false;" class="btn_down">�����������϶������</a></li>
						</logic:equal>	
						<%--���㽭ͬ�á�������ͨ��--%>
						<logic:notEqual value="12647" name="xxdm">
							<logic:notEqual value="13431" name="xxdm">
							<li><a href="javascript:void(0);" 
									onclick="printKnssq('xszz_knsrd.do?method=printJsp');return false;" class="btn_down">���صǼǱ�</a></li>
							</logic:notEqual>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10530">	
							<li><a href="javascript:void(0);" onclick="printSqlct('xszz_knsrd.do?method=printLct');return false;" class="btn_down">��������ͼ</a></li>
						</logic:equal>		
						<%--��ʦ����Ի�����--%>
						<logic:equal value="10346" name="xxdm">
							<li><a href="javascript:void(0);" onclick="downloadDjb('bgs');return false;" class="btn_down">���ؽ��ⱨ����</a></li>
							<li><a href="javascript:void(0);" onclick="downloadDjb('lxs');return false;" class="btn_down">���������걨��</a></li>
						</logic:equal>	
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
