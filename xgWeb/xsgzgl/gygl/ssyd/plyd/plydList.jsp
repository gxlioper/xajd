<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/plyd.js"></script>
		<script type="text/javascript">
		var yrzGrid = {
				pager:"pager",
				url:"gy_plyd.do?method=getYrzList",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',key:true,formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'¥��',name:'ldmc', index: 'ldmc'},
				   {label:'����',name:'qsh', index: 'qsh'},
				   {label:'��λ',name:'cwh', index: 'cwh'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			};

			var dtzGrid = {
				pager:"pager",
				url:"gy_plyd.do?method=getDtzList",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',key:true,formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'ԭ¥��',name:'ldmc', index: 'ldmc'},
				   {label:'ԭ����',name:'yss', index: 'yss'},
				   {label:'ԭ��λ',name:'ycw', index: 'ycw'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			};

			var qrtzGrid = {
				pager:"pager",
				url:"gy_plyd.do?method=getQrtzList",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',key:true,formatter:function(c,r){
					   
					   var html = kftj = r["kftj"]=="1" ? "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\"" + c  + "\");'>"+c +"</a>" : "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\"" + c  + "\");'>"+c +"</a><input type='hidden' name='bktj'/>";
					   
					   return html;
				   }},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'ԭ¥��',name:'ldmc', index: 'ldmc'},
				   {label:'ԭ����',name:'yss', index: 'yss'},
				   {label:'ԭ��λ',name:'ycw', index: 'ycw'},
				   {label:'��¥��',name:'xldmc', index: 'xldmc'},
				   {label:'������',name:'xss', index: 'xss'},
				   {label:'�´�λ',name:'xcw', index: 'xcw'},
				   {label:'�ɷ��ύ',name:'kftj', index: 'kftj',hidden:true}
				],
				sortname: "kftj",
			 	sortorder: "asc"
			};
			jQuery(function(){
				jQuery("#dataTable").initGrid(yrzGrid);
			});
	
 			function searchRs(){
 				var map = getSuperSearch();
 				jQuery("#dataTable").reloadGrid(map);
 			}

 			//�鿴ѧ������
 			function xhLink(cellValue, rowObject) {
 				return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\"" + cellValue + "\");'>" + cellValue
 						+ "</a>";
 			}
			
 			//����ѧ�Ų鿴
 			function zxsxxView(xh){
 				
 					var pkValue=xh;
 					var url="xsxx_tygl.do?method=ckZxsxx";
 					url+="&xh="+pkValue;
 					var width=850;
 					//showTopWin(url,850,640);
 					showDialog('�鿴ѧ����ϸ��Ϣ',850,640,url);
 			}
 				
		</script>
	</head>

	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
	
		<html:form action="/xsxx_xsgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
							<li id="btn_tzrz">
								<a href="javascript:void(0);" onclick="tzrz();return false;"  class="btn_zj">������ס</a>
							</li>
							<li id="btn_dtz">
								<a href="javascript:void(0);" onclick="szDtz();return false;"  class="btn_xg">������</a>
							</li>
							<li id="btn_sc" style="display:none;">
								<a href="javascript:void(0);" onclick="qxDtz();return false;"  class="btn_sc">ɾ��</a>
							</li>
							
							<li id="btn_qxtz" style="display:none;">
								<a href="javascript:void(0);" onclick="qxtz();return false;" class="btn_sc">ȡ������</a>
							</li>
							<li id="btn_qrtz" style="display:none;">
								<a href="javascript:void(0);" onclick="qrtz();return false;" class="btn_shtg">ȷ�ϵ���</a>
							</li>
					</ul>
				</div>
				</logic:equal>
			</div>
		</html:form>
		<!-- �������� -->	
 				<%@ include file="/comm/search/superSearchArea.jsp"%> 
		<!-- �������� end-->
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%;" id="tabUl">
			<li class="ha"><a href="javascript:void(0);" onclick="changeTab(0);"><span>����ס</span></a></li>
			<li><a href="javascript:void(0);" onclick="changeTab(1);"><span>������</span></a></li>
			<li><a href="javascript:void(0);" onclick="changeTab(2);"><span>ȷ�ϵ���</span></a></li>
	      </ul>
	    </div>
		
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ����Ϣ�б� </span> 
			</h3>
			
			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
