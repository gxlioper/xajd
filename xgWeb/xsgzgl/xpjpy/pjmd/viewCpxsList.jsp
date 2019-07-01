<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/pjmd/js/pjmd.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��������ȷ��",
				pager:"pager",
				url:"xpj_cpmd.do?method=viewCpxsList&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'13%'},
				   {label:'����',name:'xm', index: 'xm',width:'9%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�����꼶',name:'nj', index: 'nj',width:'5%'},
				   {label:'����<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'18%'},
				   {label:'����רҵ',name:'zymc', index: 'zydm',width:'21%'},
				   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'25%',formatter:function(cell,rowObject){

							if (rowObject["sfdh"] == "��"){
								return "<a class='name' href='javascript:void(0);' style='text-decoration:none;' title='ԭ�༶��"+rowObject["ybj"]+"'>"+cell+"</a>";
							} else {
								return cell;
							}
					   
					   }},
				   {label:'�Ƿ����',name:'sfdh', index: 'sfdh',width:'4',hidden:true},
				   {label:'������',name:'tzr', index: 'tzr',width:'4',hidden:true},
				   {label:'����������',name:'tzrxm',index:'tzrxm',whedth:'5',hidden:true},
				   {label:'ԭ�༶', name:'ybj', index:'ybj',width:'25',hidden:true},
				   {label:'�ύ״̬',name:'tjzt', index: 'tjzt',width:'4',hidden:true}
				],
				sortname: "nj,xydm,zydm,bjdm",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
		</script>
		
	</head>

	<body style="min-height: 800px;">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_cpmd">
		<input type="hidden" id="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li id="li_zj">
							<a href="javascript:void(0);" onclick="cpxsZj();return false;" 
							   title="����һ�������Լ����ݷ�Χ�ڵ�������Ա��"
							   class="btn_zj">���Ӳ���ѧ��</a>
						</li>
						<li id="li_tz">
							<a href="javascript:void(0);" onclick="cpxsTz();return false;" 
							   title="��ѡһ����¼����������Ա����Ϊ��������������Լ���ǰ�����ݷ�Χ��"
							   class="btn_xg">��������ѧ��</a>
						</li>
						<li >
							<a href="javascript:void(0);" onclick="cpxsDelete();return false;" 
							   title="ѡ��һ���������¼��ȡ����ѡѧ���Ĳ���״̬��"
							   class="btn_sc">ȡ������ѧ��</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a ><span>������Ա</span></a></li>
			        <li ><a href="javascript:void(0);" onclick="goTzjl();return false;"><span>������¼</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color="blue">${zqmc }</font>&nbsp;&nbsp;������Ա&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
