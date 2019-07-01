<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/khdxgl/js/khdxwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"khglKhdxgl.do?method=showKhdx&type=query&khlx=1&khdxid="+'${khdxid}',
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
				   {label:'�Ƿ��ɲ�',name:'sfbgb', index: 'sfbgb',hidden:true,width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'�Ƿ��ɲ�',name:'sfbgbmc', index: 'sfbgbmc',width:'10%'}
						],
				params:{fpzt:"kfp"},
				sortname: "xh",
			 	sortorder: "asc",
			}
			var gridSetting2 = {
					caption:"��ѯ���",
					pager:"pager",
					rowNum:10,
					url:"khglKhdxgl.do?method=showKhdx&type=query&khlx=1&khdxid="+'${khdxid}',
					colList:[
					   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
					   {label:'�Ƿ��ɲ�',name:'sfbgb', index: 'sfbgb',hidden:true,width:'10%'},
					   {label:'sfybpf',name:'sfybpf', index: 'sfybpf',hidden:true,width:'10%'},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
					   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
					   {label:'�Ƿ��ɲ�',name:'sfbgbmc', index: 'sfbgbmc',width:'10%'}
					],
					params:{fpzt:"yfp"},
					sortname: "xh",
				 	sortorder: "asc",
				}
		jQuery(function(){
			var map = getSuperSearch();
			map["fpzt"]="kfp";
			gridSetting1["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting1);
			
			jQuery("#btn_fh").bind("click",function(){
				if (parent.window){
					refershParent();
				}
			});
		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/khglKhdxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="khdxid" value="${khdxid}"/>
			<input type="hidden" id="fpzt" value="kfp"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>
						<li id="li_fp">
							<a href="javascript:void(0);" onclick="khdxFp('1');return false;" class="btn_zj">����</a>
						</li>
						<li id="li_dr" style="display: none;">
							<a href="javascript:void(0);" onclick="khdxDr('1');return false;" class="btn_dr">����</a>
						</li>
						<li id="li_qxfp" style="display: none;">
							<a href="javascript:void(0);" onclick="khdxQxFp('1');return false;" class="btn_sr">ȡ������</a>
						</li>
				</ul>
				</div>
				<h3 class=datetitle_01>
				<span>���˶������ƣ�${qsppForm.ldmc} </span>
			    </h3>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'kfp');"><span>�ɷ���</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yfp');"><span>�ѷ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="toolbox">
					<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ѯ���
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
