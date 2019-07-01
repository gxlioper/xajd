<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�۲�������",
				pager:"pager",
				url:"xpj_tjcx.do?method=viewTjcxList&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'��������',name:'pjzq', index: 'xn',width:'8%'},				
					{label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
					{label:'����',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />'+'����',name:'cpxymc', index: 'cpxymc',width:'10%'},
					{label:'��Ŀ����',name:'xmlxmc', index: 'xmlxmc',width:'5%'},
					{label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'13%'},
					{label:'���ʱ��',name:'hdsj', index: 'hdsj',width:'13%'}
				]
			};

			var gridSetting1 = {
					caption:"�۲�������",
					pager:"pager",
					url:"xpj_tjcx.do?method=viewTjcxList&type=query",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'��������',name:'pjzq', index: 'xn',width:'8%'},				
						{label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
						{label:'����',name:'xm', index: 'xm',width:'8%'},
						{label:'<bean:message key="lable.xb" />'+'����',name:'cpxymc', index: 'cpxymc',width:'10%'},
						{label:'��Ŀ����',name:'xmlxmc', index: 'xmlxmc',width:'5%'},
						{label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'13%'},
						{label:'���',name:'jgyz', index: 'jgyz',width:'8%'},
						{label:'���ʱ��',name:'hdsj', index: 'hdsj',width:'13%'}
					]
				};
			
			jQuery(function(){
				if('${xxdm}' == '10277' ){
					gridSetting1["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting1);
				}else{
					gridSetting["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting);
				}
				
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
				var xn_xq =  jQuery("a[name=a_name_xq]").length;
				var xmlx_num = jQuery("a[name=a_name_xxmlx]").length;
				var pjzq = jQuery("#pjzq").val();
				
				if(xn_num != "1"){
					alertError("��ѡ��һ��ѧ�꣡");
					flag = false;
				}else if(xmlx_num < 1){
					alertError("������ѡ��һ����Ŀ���ͣ�");
					flag = false;
				}else if(xn_xq != "1" && pjzq == "1"){
						alertError("��ѡ��һ��ѧ�ڣ�");
						flag = false;
					}

				return flag;
			}
			

			/*����������*/
			
			function hjmddc(dcfs){
				var pjzq = jQuery("#pjzq").val();
				if(checkSearch()){
					var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
					//var xmlx = jQuery("a[name=a_name_xxmlx]").eq(0).attr("id").replace("a_id_","");
					if(pjzq == "1") {
						var xq = jQuery("a[name=a_name_xq]").eq(0).attr("id").replace("a_id_","");
					}
					var url = "xpj_tjcx.do?method=expHjmdtj&dcfs="+dcfs;
						url+= "&str_xn="+xn;
						if(pjzq == "1") {
							url+= "&str_xq="+xq;
						}
						//url+= "&str_xmlx="+xmlx;

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
					
					var q=0;
					
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

			//�Ϻ����ѧԺ ����ɷ��ѯ
			function checkSearch_11458(){
			
				var flag = true;

				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xmxz_num = jQuery("a[name=a_name_xxmxz]").length;
				
				if(xn_num != "1"){
					alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
					flag = false;
				}else if(xmxz_num != "1"){
					alertError("��Ŀ������������Ϊ�գ���ֻ��ѡ��һ����");
					flag = false;
				}

				return flag;
			}

            function hjmddc_11458(){
                if(checkSearch_11458()){
                    var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
                    var url = "xpj_tjcx.do?method=expHjmdtj_11458";
                    url+= "&str_xn="+xn;

                    var xmxz = new Array();//��Ŀ����

                    var m = 0;
                    jQuery("a[name=a_name_xxmxz]").each(function(){
                        var id = jQuery(this).attr("id");
                        var xmxzmc = id.replace("a_id_","");
                        if(xmxzmc !=null && xmxzmc!=""){
                            xmxz[m] = xmxzmc;
                            m++;
                        }
                    });

                    if(xmxz != null && xmxz.length>0){
                        url+= "&array_xmxz="+xmxz.join("!!array!!");
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

                    document.forms[0].action = url;
                    document.forms[0].target = "_blank";
                    document.forms[0].submit();
                    document.forms[0].target = "_self";

                }
            }

            /*��̶��ѧ ����������*/
            function hjmddc_10530(){

                var xn_num =  jQuery("a[name=a_name_xn]").length;
                var xy_num = jQuery("a[name=a_name_xy]").length;

                if(xn_num != "1"){
                    alertError("��ѡ��һ��ѧ�꣡");
					return false;
                }else if(xy_num < 1){
                    alertError("������ѡ��һ��ѧԺ��");
                    return false;
                }

				var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
				var url = "xpj_tjcx.do?method=getPymdhzb";
				url+= "&xn="+xn;

				var xyArr = [];//ѧԺ����

				jQuery("a[name=a_name_xy]").each(function(){
					var id = jQuery(this).attr("id");
					var xy = id.replace("a_id_","");
					if(xy !=null && xy!=""){
						xyArr.push(xy);
					}
				});

				if(xyArr != null && xyArr.length>0){
					url+= "&xyArr="+xyArr;
				}

				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
            }

			var DCCLBH = "pj_tjcx.do";//dcclbh,�������ܱ��

			//�Զ��嵼�� ����
			function jgdc_11458(){
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, ExportData);
			}

			//��������
			function ExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "xpj_tjcx.do?method=exportDataShty&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
            function getJxjExcel(type){
                var url="xpj_tjcx.do?method=getJxjExcel&type="+type;
                var xnArr = jQuery("a[name=a_name_xn]");
                var xqArr = jQuery("a[name=a_name_xq]");
                if(xnArr.length != 1){
                    showAlertDivLayer("��ѡ��һ��ѧ��������");
                    return false;
                }
                if(xqArr.length != 1){
                    showAlertDivLayer("��ѡ��һ��ѧ��������");
                    return false;
                }
                setSearchTj();
                url = addSuperSearchParams(url);
                document.forms[0].action = url;
                document.forms[0].submit();
            }
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_pjjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" name="pjzq" id="pjzq" value="${pjzq}"/>
			<div class="toolbox">
				<!-- ��ť -->	
				<div class="buttonbox">
					<ul>
						<!-- ͨ�õ��� begin-->
						<logic:notEqual name="xxdm" value="11458">
						<logic:notEqual name="xxdm" value="10277">
						<li><a href="#" class="btn_dc" onclick="hjmddc('dc');return false;">����</a></li>
						</logic:notEqual>
						</logic:notEqual>
						<!-- ͨ�õ��� end-->
						<!-- �Ϻ�����ѧԺ���Ƶ��� begin-->
						<logic:equal name="xxdm" value="10277">
						<li><a href="#" class="btn_dc" onclick="hjmddc('dc');return false;">�񵥴�ӡ</a></li>
						<li><a href="#" class="btn_dc" onclick="jgdc_11458();return false;">�������</a></li>
						</logic:equal>
						<!-- �Ϻ�����ѧԺ���Ƶ��� end-->
						<!-- �Ϻ����ѧԺ���Ƶ��� begin-->
						<logic:equal name="xxdm" value="11458">
						<li><a href="#" class="btn_dc" onclick="hjmddc_11458();return false;">����</a></li>						
						</logic:equal>
						<!-- �Ϻ����ѧԺ���Ƶ��� end-->
						<!-- �㽭��ѧ���Ƶ��� begin-->
						<logic:equal name="xxdm" value="10335">
						<li><a href="#" class="btn_dc" onclick="hjmddc('dcwd');return false;">����word</a></li>
						<li><a href="#" class="btn_dc" onclick="hjmddc('njdc');return false;">�������</a></li>
					    <li><a href="#" class="btn_dc" onclick="hjmddc('njdcwd');return false;">�������word</a></li>		 				
						</logic:equal>
						<!-- �㽭��ѧ���Ƶ��� end-->
						<%--��̶��ѧ���Ƶ�����begin--%>
						<logic:equal name="xxdm" value="10530">
						<li><a href="#" class="btn_dc" onclick="hjmddc_10530();return false;">Ժϵ���Ż�������</a></li>
						</logic:equal>
						<%--��̶��ѧ���Ƶ�����end--%>
						<%--�Ͼ��ߵ�ְҵ����ѧУ--%>
						<logic:equal name="xxdm" value="10874">
							<li><a href="javascript:void(0);" onclick="getJxjExcel('tj');return false;" class="btn_down">��ѧ��ͳ�Ʊ���</a></li>
							<li><a href="javascript:void(0);" onclick="getJxjExcel('lq');return false;" class="btn_down">��ѧ����ȡ����</a></li>
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
				<span>������ͳ��</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>