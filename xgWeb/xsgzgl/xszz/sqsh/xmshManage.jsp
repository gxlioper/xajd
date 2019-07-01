<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
		<script type="text/javascript">
			function getDclGird(){
				var colList = [
							   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
							   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
							   {label:'����',name:'xm', index: 'xm',width:'8%'},
							   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
								{label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
								{label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
								{label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
							   {label:'��������',name:'sqzq', index: 'sqzq',width:'12%'},
							   {label:'����ѧ��',name:'xn', index: 'xn',hidden:true},
							   {label:'����ѧ��',name:'xqmc', index: 'xq',hidden:true},
							   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
							   {label:'��Ŀ����',name:'xmmc', index: 'xmdm',width:'11%'},
							   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'gwid',name:'xtgwid', index: 'xtgwid',hidden:true},
							   {label:'������',name:'ylzd1', index: 'ylzd1',width:'11%'}
							];
	
				var zcxm = jQuery("[name=zcxm]");
				jQuery.each(zcxm,function(i,n){
					var checked = jQuery(n).prop("checked");
					var v = jQuery(n).val();
					var xmfsJson = {
			 				label:jQuery(n).attr("xmmc"),
			 				name:v,
			 				index:v,
			 				hidden:(!checked)
			 		};
					colList.push(xmfsJson);
				});
	
				colList.push({label:'���״̬',name:'shztmc', index: 'shzt',width:'11%'});
				if(jQuery("#xxdm").val() == "13627"){
					colList.push({label:'����Ա',name:'fdyxm', index: 'fdyxm',width:'8%'});
				}
				return {
					caption:"��������б�",
					pager:"pager",
					url:"xszz_sqsh.do?method=xmshManage&type=query",
					colList:colList,
					params:{shzt:"dsh"},//Ĭ�ϴ����
					sortname: "sqsj",
				 	sortorder: "desc"
				};
			}
			function getYclGrid(){
				var colList = [
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'9%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'6%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
					   {label:'��������',name:'sqzq', index: 'sqzq',width:'12%'},
					   {label:'ѧ��',name:'xn', index: 'xn',hidden:true},
					   {label:'ѧ��',name:'xqmc', index: 'xq',hidden:true},
					   {label:'��Ŀ����',name:'xmmc', index: 'xmdm',width:'11%'},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'shid',name:'shid', index: 'shid',hidden:true},
					   {label:'gwid',name:'xtgwid', index: 'xtgwid',hidden:true},
					   {label:'������',name:'ylzd1', index: 'ylzd1',width:'11%'}
					];
	
				var zcxm = jQuery("[name=zcxm]");
				jQuery.each(zcxm,function(i,n){
					var checked = jQuery(n).prop("checked");
					var v = jQuery(n).val();
					var xmfsJson = {
			 				label:jQuery(n).attr("xmmc"),
			 				name:v,
			 				index:v,
			 				hidden:(!checked)
			 		};
					colList.push(xmfsJson);
				});
	
				colList.push({label:'���ʱ��',name:'shsj', index: 'shsj',width:'13%'});
				colList.push({label:'���״̬',name:'shztmc', index: 'shzt',width:'9%'});
				if(jQuery("#xxdm").val() == "13627"){
					colList.push({label:'����Ա',name:'fdyxm', index: 'fdyxm',width:'8%'});
				}
				return {
					caption:"��������б�",
					pager:"pager",
					url:"xszz_sqsh.do?method=xmshManage&type=query",
					colList:colList,
					params:{shzt:"ysh"},//�����
					sortname: "shsj",
				 	sortorder: "desc"
				};
			}

			//װ��cookie
			function loadCookie(){
				var indexStr = jQuery.cookie("xszzSqshGrid");
				if(indexStr != null && indexStr != undefined){
					var indexArray = indexStr.split("-");
					
					jQuery.each(indexArray,function(i,n){
						
						if (n != ""){
							jQuery("[name=zcxm][index="+n+"]").attr("checked",true);
						}
					});
				}
				
			}
			
			//����cookie
			function setJxshCookie(){
				var chekedZcxm = jQuery("[name=zcxm]:checked:not(:disabled)");
				var indexStr = "";
				
				jQuery.each(chekedZcxm,function(i,n){
					var index = jQuery(n).attr("index");
					indexStr += index+"-";
				});
				jQuery.cookie("xszzSqshGrid",indexStr, { expires: 7});
			}
			
			jQuery(function(){
				jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
					var index = jQuery(this).attr("index");
					var liName = jQuery("#tabUl li.ha").attr("clzt");
					
					if ("dcl" == liName){
						var gridSetting = getDclGird();
						jQuery("#dataTable").initGrid(gridSetting);
					} else {
						var gridSetting = getYclGrid();
						jQuery("#dataTable").initGrid(gridSetting);
					}
				});
				
				loadCookie();
				
				var gridSetting = getDclGird(); 
				var map = getSuperSearch();
				map["shzt"]="dsh";
				gridSetting["params"] = map;
				
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>

	<body onunload="setJxshCookie();">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >ʹ�ð���</a>
			</p>
		</div>
		<html:form action="/xszz_jtqkdc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						������ʹ�ø߼���ѯ����������Ŀ���ƣ����һ�˶�ڣ���������ѡ��ͬһ��λ�ļ�¼����������ˡ�
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			<div class="toolbox">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="zzxmSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelXmsh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>
						</logic:equal>						
						<li><a href="javascript:void(0);" onclick="xmsqLcgz();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>			
						<li><a href="javascript:void(0);" onclick="zzxmShqk();return false;" 
							   title="����鿴����������ͳ�ơ�"
							   class="btn_tj">���ͳ��</a></li>
							   
						<li>
							<a href="#" class="btn_dc" onclick="exportConfigSh();return false;">����</a>
						</li>
						<%--���������Ի� --%>
						<logic:equal value="13431" name="xxdm">
							<li><a href="javascript:void(0);" class="btn_down" onclick="getWord();return false;">��������������</a></li>	
						</logic:equal>
						<logic:notEqual value="13431" name="xxdm">
							<li><a href="javascript:void(0);" class="btn_down" onclick="getWord();return false;">���صǼǱ�</a></li>			
						</logic:notEqual>
								
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%" id="tabUl">
			        <li class="ha" clzt="dcl"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li clzt="ycl"><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ŀ�����б�&nbsp;&nbsp; </span>
			</h3>
			<logic:notEqual name="xxdm" value="10530">	
			<input type="checkbox" xmmc="��������Ŀ" value="tzxmmc" name="zcxm" index="0" />��������Ŀ
			<input type="checkbox" xmmc="��������" value="tzxmje" name="zcxm" index="1" />��������
			</logic:notEqual>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
