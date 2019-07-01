<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�������������б�",
				pager:"pager",
				url:"xpjpy_fwmddc.do?method=getFwmddcList&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'��������',name:'pjzq', index: 'xn',width:'8%'},				
					{label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
					{label:'����',name:'xm', index: 'xm',width:'8%'},
					{label:'ѧԺ����',name:'xymc', index: 'xymc',width:'10%'},
					{label:'��Ŀ����',name:'xmlxmc', index: 'xmlxmc',width:'5%'},
					{label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'13%'},
					{label:'���ʱ��',name:'hdsj', index: 'hdsj',width:'13%'}
				]
			};
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//�߼���ѯ
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}


			//����ɷ��ѯ
			function checkSearch(){
				var flag = true;
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xmlx_num = jQuery("a[name=a_name_xxmlx]").length;
				var pjzq = jQuery("#pjzq").val();
				
				if(xn_num != "1"){
					alertError("��ѡ��һ��ѧ�꣡");
					flag = false;
				}else if(xmlx_num < 1){
					alertError("������ѡ��һ����Ŀ���ͣ�");
					flag = false;
				}
				return flag;
			}

			/*����������*/
			function hjmddc(dcfs){
				var pjzq = jQuery("#pjzq").val();
				if(checkSearch()){
					var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
					var url = "xpjpy_fwmddc.do?method=expHjmdtj&dcfs="+dcfs;
					url+= "&str_xn="+xn;
					var xmlx = new Array();//��Ŀ����
					var m = 0;
					jQuery("a[name=a_name_xxmlx]").each(function(){
						var id = jQuery(this).attr("id");
						var xmlxmc = id.replace("a_id_","");
						if(xmlxmc !=null && xmlxmc!=""){
							xmlx[m] = xmlxmc;
							m++;
						}
					});

					if(xmlx != null && xmlx.length>0){
						url+= "&array_xmlx="+xmlx.join("!!array!!");
					}
					var xydm = new Array();//ѧԺ����
					
					var n=0;
					
					jQuery("a[name=a_name_xy]").each(function(){
						var id = jQuery(this).attr("id");
						var xy = id.replace("a_id_","");
						if(xy !=null && xy!=""){
							xydm[n] = xy;
							n++;
						}
					});
					
					if(xydm != null && xydm.length>0){
						url+= "&array_xydm="+xydm.join("!!array!!");
					}

					var xmxz = new Array();//��Ŀ����
					var o=0;
					jQuery("a[name=a_name_xxmxz]").each(function(){
						var id = jQuery(this).attr("id");
						var xmxzmc = id.replace("a_id_","");
						if(xmxzmc !=null && xmxzmc!=""){
							xmxz[o] = xmxzmc;
							o++;
						}
					});
					
					if(xmxz != null && xmxz.length>0){
						url+= "&array_xmxz="+xmxz.join("!!array!!");
					}

					var xmmc = new Array();//��Ŀ����
					var q = 0;
					jQuery("a[name=a_name_xmmc]").each(function(){
						var id = jQuery(this).attr("id");
						var xm = id.replace("a_id_","");
						if(xm !=null && xm!=""){
							xmmc[n] = xm;
							q++;
						}
					});
					
					if(xmmc != null && xmmc.length>0){
						url+= "&array_xmmc="+xmmc.join("!!array!!");
					}
					document.forms[0].action = encodeURI(encodeURI(url)); ;
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";	
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
		<html:form action="/xpjpy_fwmddc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" name="pjzq" id="pjzq" value="${pjzq}"/>
			<div class="toolbox">
				<!-- ��ť -->	
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_dc" onclick="hjmddc('dc');return false;">����</a>
						</li>
						<li>
							<a href="#" class="btn_dc" onclick="hjmddc('dcwd');return false;">����word</a>
						</li>
						<li>
							<a href="#" class="btn_dc" onclick="hjmddc('njdc');return false;">�������</a>
						</li>
					    <li>
					    	<a href="#" class="btn_dc" onclick="hjmddc('njdcwd');return false;">�������word</a>
					    </li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������ͳ��</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>