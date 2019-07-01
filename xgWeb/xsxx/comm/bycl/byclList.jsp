<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="xsxx/comm/bycl/js/bycl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption:"ѧ����Ϣ�б�",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"bycl.do?method=byclList&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',key:true,formatter:setXh},
				   {label:'����',name:'xm', index: 'xm',width:'13%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'15%'},
				   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'18%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'18%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
				   {label:'ѧ��',name:'xz', index: 'xz',width:'5%'},
				   {label:'ѧ�����',name:'xjlb', index: 'xjlb',width:'18%'},
				   {label:'�Ƿ���У',name:'sfzx', index: 'sfzx',width:'10%'}
				],
				sortname: "xymc,bjmc,xh",
			 	sortorder: "desc"
			}
			var gridSetting2 = {
					caption:"ѧ����Ϣ�б�",
					pager:"pager",
					multiselect:true,
					rowNum:10,
					url:"bycl.do?method=byclList&type=query",
					colList:[
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',key:true,formatter:setXh},
					   {label:'����',name:'xm', index: 'xm',width:'13%'},
					   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
                        {label:'��Ժ',name:'symc', index: 'symc',width:'15%'},
                        {label:'�����༶',name:'bjmc', index: 'bjdm',width:'18%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'18%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
					   {label:'ѧ��',name:'xz', index: 'xz',width:'5%'},
					   {label:'��ҵ����',name:'byny', index: 'byny',width:'10%'}
					],
					sortname: "xymc,bjmc,xh",
				 	sortorder: "desc"
				}
			jQuery(function(){
				jQuery("#li_qxby").hide();
				jQuery("#dataTable").initGrid(gridSetting1);
			});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				�����󣬲�ѡ���κ�ѧ������ȫѡ
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ END-->
		<html:form action="/xsxx_xsgl">
			<input type="hidden" id="sfyby" value=""/>
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
					<logic:equal value="yes" name="writeAble">
						<li id="li_by">
							<a href="javascript:void(0);" onclick="bycl();return false;" class="btn_zj" id="byButton">��ҵ����</a>
						</li>
						<li id="li_qxby">
							<a href="javascript:void(0);" onclick="qxbycl();return false;" class="btn_sc" id="qxbyButton">ȡ����ҵ</a>
						</li>
						
						<li id="li_bydr">
							<a href="javascript:void(0);" class="btn_dr" onclick="byxsImport();return false;">����</a>
						</li>
					</logic:equal>
					    <li>
						    <a href="javascript:void(0);" onclick="exportConfig();return false;"  class="btn_dc">����</a>
					    </li>
					<logic:equal value="11318" name="xxdm">
							<li id="li_jcdy"><a href="#" class="btn_dy" id="jcxxButton" onclick="getJcXxWord();return false;">ѧ��������Ϣ��ӡ</a></li>
						</logic:equal>
				  </ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<!-- ��ť -->
				<div class="comp_title" id="comp_title">
			      <ul style="width:70%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>δ��ҵѧ��</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>�ѱ�ҵѧ��</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
					<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ����Ϣ�б�
				 </span>
			</h3>
		</div>
			<table id="dataTable" ></table>
			<div id="pager"></div>
	</body>
</html>
