<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzxmjg/js/zzxmhzList.js"></script>
		<script type="text/javascript">
		//����ɷ��ѯ
		function checkSearch(){
			var flag = true;
			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var xq_num =  jQuery("a[name=a_name_xq]").length;
			var xmlb_num = jQuery("a[name=a_name_zzxmlb]").length;
			
			if(xn_num != "1"){
				alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
				flag = false;
			}else if(xmlb_num < 1){
				alertError("������ѡ��һ����Ŀ���");
				flag = false;
			}else if(xq_num > 0 && xq_num != "1"){
				alertError("ѧ������ֻ��ѡ��һ����");
				flag = false;
			}
			return flag;
		}
		/*����������*/
		function hjmddc(){
			if(checkSearch()){
				var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
				var xq_num = jQuery("a[name=a_name_xq]").length;
				var xq = "";
				if(xq_num > 0){
					xq = jQuery("a[name=a_name_xq]").eq(0).attr("id").replace("a_id_","");
				}
				var url = "xszz_zzxmjg.do?method=expHjmdtj";
					url+= "&str_xn="+xn;
					url+= "&str_xq="+xq;

				var xmlb = new Array();//��Ŀ���

				var m = 0;
				jQuery("a[name=a_name_zzxmlb]").each(function(){
					var id = jQuery(this).attr("id");
					var xmlbmc = id.replace("a_id_","");
					if(xmlbmc !=null && xmlbmc!=""){
						xmlb[m] = xmlbmc;
						m++;
					}
				});

				if(xmlb != null && xmlb.length>0){
					url+= "&array_xmlb="+xmlb.join("!!array!!");
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


        // ��Ŀ�����������
        function getXmzzqkhz_10098() {
		    var sxxm="";
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length ==0){
                showAlertDivLayer("������ѡ��һ����¼��");
                return false;
            }else{
                for(var i=0 ; i< rows.length;i++){
                    if(i==0)
					{
                        sxxm=sxxm+rows[i]['xmmc'];
					}
					else{
                        sxxm=sxxm+','+rows[i]['xmmc'];
                    }
				}
			}
			jQuery("#sxxm").val(sxxm);
            setSearchTj();//���ø߼���ѯ����
            var url = "xszz_zzxmjg.do?method=gjjxdc&dcclbh=xg_xszz_xmzzqkhz.do";//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >ʹ�ð���</a>
			</p>
		</div>
		<html:form action="/xszz_zzxmjg" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						����ѡ�� ͬѧ�ꡢͬ��Ŀ���Ƶ�������Ŀ��
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div id="shtyhzdcdiv" style="display:none">
				
			</div>
			<input type="hidden" id="sxxm" name="sxxm" value=""/>
			<!-- ��ʾ��Ϣ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>-->
						<li><a href="javascript:void(0);" onclick="getExcel();return false;" class="btn_down">������Ϣ�ϱ�</a></li>
						<logic:equal name="xxdm" value="12036">
							<logic:equal value="xx" name="userStatus">
								<li><a href="javascript:void(0);" onclick="getExcelByXy();return false;" class="btn_down">ѧԺ�ϱ�����</a></li>
							</logic:equal>
						</logic:equal>
						<logic:equal name="xxdm" value="11318">
						<li><a href="#" class="btn_dc" onclick="hjmddc();return false;">����</a></li>
						</logic:equal>
						<logic:equal name="xxdm" value="12947">
					    <li><a href="javascript:void(0);" onclick="getshzxjHzexcel();return false;" class="btn_down">�����ѧ�����</a></li>	
						</logic:equal>
						<logic:equal name="xxdm" value="10277">
						 <li><a href="javascript:void(0);" onclick="getZzxmHz_10277();return false;" class="btn_down">������Ŀ���ܱ�</a></li>	
						</logic:equal>
						<logic:equal name="xxdm" value="10098">
							<li><a href="javascript:void(0);" onclick="getXmzzqkhz_10098();return false;" class="btn_down">��Ŀ����������ܱ�</a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ������Ŀ�����б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
