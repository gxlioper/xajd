<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrd/js/knsrd.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		var gridSetting = {};
		var gridSetting2 = {};
		var sfyc=true;
		var sqxz=true;
		
		function initGridSetting(){
			if("10335"==jQuery("#xxdm").val()){
				sfyc=false;
				sqxz=false;
				}
			if("xn"==jQuery("#sqzq").val()){
			gridSetting = {
				caption:"�������϶�����б�",
				pager:"pager",
				url:"xszz_knsrd.do?method=knsshManage&type=query",
				colList:[
				   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'bjdm',width:'13%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
				   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
				   {label:'ǰ���Ƽ�����',name:'sjdcmc', index: 'sjdc',width:'11%'},
                    {label:'ǰ���Ƽ�����',name:'sjdc', index: 'sjdc',width:'11%',hidden:true},
				   //{label:'�޳��������',name:'wczzje', index: 'wczzje',width:'11%',hidden:sfyc},
				   {label:'��������',name:'sqxz', index: 'sqxz',width:'8%',hidden:sqxz,formatter:sqxzLink},
				   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true},
				   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
				   <logic:equal name = 'xxdm' value= '11998'>
				   {label:'����������',name:'zf', index: 'zf',width:'8%'},
				   </logic:equal>
				   //�����ʵ��ѧ���Ի�
				   <logic:equal name = 'xxdm' value= '13627'>
				   {label:'����Ա',name:'fdyxm', index: 'fdyxm',width:'8%'},
				   </logic:equal>
				   {label:'���״̬',name:'shztmc', index: 'shzt',width:'13%'},
				   {label:'����id',name:'ylzd2', index: 'ylzd2',hidden:true}
				],
				params:{shzt:"dsh"},//Ĭ�ϴ����
				sortname: "sqsj",
			 	sortorder: "desc"
			}


			gridSetting2 = {
				caption:"�������϶�����б�",
				pager:"pager",
				url:"xszz_knsrd.do?method=knsshManage&type=query",
				colList:[
				   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'9%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'bjdm',width:'13%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
				   {label:'���ʱ��',name:'shsj', index: 'shsj',width:'15%'},
				   {label:'�Ƽ�����',name:'dcmc', index: 'rddc',width:'8%'},
				//   {label:'�޳��������',name:'dczzje', index: 'dczzje',width:'11%',hidden:sfyc},
				   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true},
				   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
				   <logic:equal name = 'xxdm' value= '11998'>
				   {label:'����������',name:'zf', index: 'zf',width:'8%'},
				   </logic:equal>
				   //�����ʵ��ѧ���Ի�
				   <logic:equal name = 'xxdm' value= '13627'>
				   {label:'����Ա',name:'fdyxm', index: 'fdyxm',width:'8%'},
				   </logic:equal>
				   {label:'���״̬',name:'shztmc', index: 'shzt',width:'10%'},
				   {label:'����id',name:'ylzd2', index: 'ylzd2',hidden:true}
				],
				params:{shzt:"ysh"},//�����
				sortname: "shsj",
			 	sortorder: "desc"
			}
		}else{
			gridSetting = {
					caption:"�������϶�����б�",
					pager:"pager",
					url:"xszz_knsrd.do?method=knsshManage&type=query",
					colList:[
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                       {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
                       {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                       {label:'רҵ�༶',name:'zybjmc', index: 'bjdm',width:'13%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
					   {label:'ѧ��',name:'xqmc', index: 'xq',width:'6%'},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
					   {label:'ǰ���Ƽ�����',name:'sjdcmc', index: 'sjdc',width:'11%'},
                        {label:'ǰ���Ƽ�����',name:'sjdc', index: 'sjdc',width:'11%',hidden:true},
					//   {label:'�޳��������',name:'wczzje', index: 'wczzje',width:'11%',hidden:sfyc},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'shid',name:'shid', index: 'shid',hidden:true},
					   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
					   <logic:equal name = 'xxdm' value= '11998'>
					   {label:'����������',name:'zf', index: 'zf',width:'8%'},
					   </logic:equal>
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'13%'},
					   {label:'����id',name:'ylzd2', index: 'ylzd2',hidden:true}
					],
					params:{shzt:"dsh"},//Ĭ�ϴ����
					sortname: "sqsj",
				 	sortorder: "desc"
				}


				gridSetting2 = {
					caption:"�������϶�����б�",
					pager:"pager",
					url:"xszz_knsrd.do?method=knsshManage&type=query",
					colList:[
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'9%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                       {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
                       {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                       {label:'רҵ�༶',name:'zybjmc', index: 'bjdm',width:'13%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
					   {label:'ѧ��',name:'xqmc', index: 'xq',width:'6%'},
					   {label:'���ʱ��',name:'shsj', index: 'shsj',width:'15%'},
					   {label:'�Ƽ�����',name:'dcmc', index: 'rddc',width:'8%'},
				//	   {label:'�޳��������',name:'dczzje', index: 'dczzje',width:'11%',hidden:sfyc},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'shid',name:'shid', index: 'shid',hidden:true},
					   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
					   <logic:equal name = 'xxdm' value= '11998'>
					   {label:'����������',name:'zf', index: 'zf',width:'8%'},
					   </logic:equal>
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'10%'},
					   {label:'����id',name:'ylzd2', index: 'ylzd2',hidden:true}
					],
					params:{shzt:"ysh"},//�����
					sortname: "shsj",
				 	sortorder: "desc"
				}
		}
			
			}
			
			jQuery(function(){
				initGridSetting();
				var map = getSuperSearch();
				map["shzt"]="dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
				if("14008" == jQuery("#xxdm").val()) {
					var isopensh = jQuery("#isopensh").val();
					if ("false" == isopensh) {
						jQuery('#li_sh').css('display','none');
					}
				}
				
			});

			function searchRs(){
				var map = getSuperSearch();
				var shzt = jQuery("#shzt").val();
				
				if (shzt != ""){
					map["shzt"] = shzt;
				}
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_knsrd">
			<html:hidden property="sqzq" styleId="sqzq" value="${sqzq}"/>
			<logic:equal name="xxdm" value="14008">
				<input type="hidden" name="isopensh" id="isopensh" value="${jcszModel.isopensh }"/>
			</logic:equal>
			<input type="hidden" id="xxdm" value="${xxdm}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="knsrdSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelKnssh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_sr">����</a>
						</li>		
						</logic:equal>				
						<li><a href="javascript:void(0);" onclick="knssqLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>
						<logic:equal value="10335" name="xxdm">
							<li><a href="javascript:void(0);" onclick="viewYxKnsfp();return false;"
								   title="����鿴��Ժϵ���ͨ����������������������"
								   class="btn_tj">��Ժϵ������</a>
							</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_dc" onclick="exportConfigSh();return false;">����</a>
						</li>
						<%-- ������ͨ��ѧ��ѧԺ ���Ի�����--%>
						<logic:equal value="13431 " name="xxdm">
							<li><a href="javascript:void(0);" onclick="printKnssq('xszz_knsrd.do?method=printJsp');return false;" class="btn_down">�����������϶������</a></li>
						</logic:equal>
						<%--�ǻ�����ͨ��--%>
						<logic:notEqual value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="printKnssq('xszz_knsrd.do?method=printJsp');return false;" class="btn_down">���صǼǱ�</a></li>
						</logic:notEqual>
						<%--<li><a href="javascript:void(0);" onclick="knsrdShqk();return false;" 
							   title="����鿴����������ͳ�ơ�"
							   class="btn_tj">���ͳ��</a></li>--%>						
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
				<span>�������϶������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
