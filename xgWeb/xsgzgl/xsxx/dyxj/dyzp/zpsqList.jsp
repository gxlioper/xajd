<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/dyxj/dyzp/js/dyzp.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"xsxxDyxjDyzp.do?method=getZpsqList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'�༶',name:'bjmc', index: 'bjdm'},
					   {label:'ѧ��',name:'xn', index: 'xn'},
					   {label:'ѧ��',name:'xqmc', index: 'xq'},
					   {label:'����ʱ��',name:'pysj',index:'pysj'},
					   {label:'shzt',name:'shzt',index:'shzt',hidden:true},
					   {label:'splcid',name:'splcid',index:'splcid',hidden:true},
					   {label:'���״̬',name:'shztmc',index:'shztmc'}
					],
					sortname:"pysj",
					sortorder:"desc"
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog("�鿴�����",800,480,"xsxxDyxjDyzp.do?method=cksq&id="+id);
			}

			function exporDyxj(){
				var ids = jQuery("#dataTable").getSeletIds();
			    var rows = jQuery("#dataTable").getSeletRow();
				var len = ids.length;
				if (len == 1) {
				//	if(rows[0]["shzt"] != '1'){
				//		showAlertDivLayer("��ѡ��ͨ���ļ�¼��");
				//		return false;
				//	}
					var url = "xsxxDyxjZpjg.do?method=getZpjgxjb";
					url += "&id=" + ids+"&flag=sq";
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
					return false;
				} else {
					/*
					var flag = true;
					for(var i=0;i<rows.length;i++){
						if(rows[i]["shzt"] != 1){
							flag = false;
							break;
						}
					}
				    if(!flag){
				    	showAlertDivLayer("��ѡ��ͨ���ļ�¼��");
				    	return false;
				    }*/
					var url = "xsxxDyxjZpjg.do?method=getZpjgTy";
					url += "&value=" + ids+"&flag=sq";
					window.open(url);
				}
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xsxxDyxjDyzp" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="1" name="cssz" property="sqkg">
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="zpsq();return false;" 
									>
									����
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xgsq();return false;" class="btn_xg"
									>�޸�</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="qxsq();return false;" class="btn_sc"
									>ɾ��</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
										title="��������д�����δ�ύ�ļ�¼�����ύ���������"
									>�ύ</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="cancelSubmit();return false;" class="btn_sr"
										 title="�������ύδ��˵ļ�¼���г���������"
									>����</a>
								</li>
							</logic:equal>
							<li>
							     <a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						     </li>
							<li><a href="javascript:void(0);" onclick="exporDyxj();" class="btn_dy">����С��</a></li>	
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>�������������б�  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
