<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "���˶����б�",
					pager : "pager",
					radioselect:false,
					url : "khglKhpf.do?method=bzrpf&type=query",
					colList : [
					   {label : 'xmid',name : 'xmid',index : 'xmid' ,hidden:true}, 
					   {label : 'khbid',name : 'khbid',index : 'khbid' ,hidden:true},
					   {label : 'khdxr',name : 'khdxr',index : 'khdxr' ,hidden:true}, 
					   {label : 'xmszid',name : 'xmszid',index : 'xmszid' ,hidden:true},
					   {label : 'xn',name : 'xn',index : 'xn' ,hidden:true},
					   {label : 'ѧ��',name : 'xh',index : 'xh',width : '12%',key:true},
					   {label : '����',name : 'xm',index : 'xm',width : '10%'}, 
					   {label : '�༶',name : 'bjmc',index : 'bjmc',width : '18%'},
					   {label : '������',name : 'zpzf',index : 'zpzf',width : '7%'},
                       {label : '״̬',name : 'zpyp',index : 'zpyp',width : '6%'},
					   {label : '��������',name : 'bzzf',index : 'bzzf',width : '7%'},
                        {label : '״̬',name : 'bzyp',index : 'bzyp',width : '6%'},
					   {label : '����������',name : 'bzrzf',index : 'bzrzf',width : '7%'},
					   {label : '�ܷ�',name : 'zf',index : 'zf',width : '5%'},
					   {label : '�༶����',name : 'pm',index : 'pm',width : '7%'},
					   {label : '���״̬',name : 'bzrshzt',index : 'bzrshzt',width : '10%',hidden:true},
					   {label : '���״̬',name : 'bzrshztmc',index : 'bzrshztmc',width : '8%'},
					   {label : '����',name : 'cz',index : 'cz',width : '12%',formatter:czFormatter}
					   ]
				};

			jQuery(function() {
				var sftj = jQuery("#sftj").val();
				var map = {xmid:jQuery("#xmid").val() ,khbid:jQuery("#khbid").val()};
				gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
			});
			function czFormatter(cellValue, rowObject){
			
			   return "<a href='javascript:void(0);' class='name' onclick='xgpf(\""+rowObject.bzrzf+"\",\""+rowObject.xmid+"\",\""+rowObject.khbid+"\",\""+rowObject.xh+"\",\""+rowObject.xmszid+"\",\""+rowObject.bzrshzt+"\");'>�޸�</a>";
			
			}
			//�޸�����
			function xgpf(bzrpf,xmid,khbid,khdxr,xmszid,shzt){
				if(shzt=="1"){
				 showAlertDivLayer("��ѧ�������ͨ�����������޸ģ�");
					return false;
				}
					var url = "khglKhpf.do?method=xgpf&xmid=";
						url+=xmid+"&khbid="+khbid+"&khdxr="+khdxr+"&xmszid="+xmszid+"&fs="+bzrpf+"&pflx="+jQuery("#pflx").val();
						showDialog("�޸�����",800,520,url,{close:function(){
							if (jQuery("#search_go")){
								jQuery("#search_go").click();
							}
						}});
			}
			//�߼���ѯ
			function searchRs(){
				var map = getSuperSearch();
				map["xmid"] = jQuery("#xmid").val();
				map["khbid"] = jQuery("#khbid").val();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			}
						
		</script>
	</head>
	<body>
	<html:form action="/khglKhpf" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value=${xmInfo.xmid} />
	<input type="hidden" name="khbid" id="khbid" value=${xmInfo.khbid} />
	<input type="hidden" name="xmszid" id="xmszid" value=${xmInfo.xmszid} />
	<input type="hidden" name="pflx" id="pflx" value=${xmInfo.pflx} />
	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li id="li_ck"><a href="javascript:void(0);" onclick="viewPf();" class="btn_ck">�鿴</a></li>
						<li id="li_xz"><a href="javascript:void(0);" onclick="getCpcjWord();" class="btn_xg">�����ɼ�����</a></li>
						<li id="li_sh"><a href="javascript:void(0);" onclick="bzsh();" class="btn_sh">���ͨ��</a></li>
						<li id="li_qx"><a href="javascript:void(0);" onclick="cxsh();" class="btn_qxsh">�������</a></li>
						<li id="li_qx"><a href="javascript:void(0);" onclick="cxxszp();" class="btn_qxsh">����ѧ������</a></li>
						<li id="li_csh"><a href="javascript:void(0);" onclick="scbzpfmm();" class="btn_csh">���ɰ�����������</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						�����������б�
						<font style="color: blue"> ${xmInfo.xmmc}</font>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
