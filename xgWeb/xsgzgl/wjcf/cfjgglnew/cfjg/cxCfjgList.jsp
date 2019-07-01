<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/uicomm.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsgzgl/wjcf/cfjgglnew/cfjg/js/cfjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<!-- ���빦����Ҫ-->
		<script language="javascript" defer="defer">	
		jQuery(function(){
			
			 var gridSetting = {
		    		 caption:"���ֽ����Ϣ�б�",
						pager:"pager",
						url:"wjcf_cfjg.do?method=cxCfjgList&type=query",
						params:getSuperSearch(),
						colList:[
						   {label:'����id',name:'cfid', index: 'cfid',width:'10%',key:true, hidden:true},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'5%'},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'�����༶',name:'bjmc', index: 'bjmc',width:'15%'},
                           {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'15%'},
						   {label:'�������',name:'cflbmc', index: 'cflbmc',width:'12%'},
						   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc',width:'10%'},
						   {label:'����ʱ��',name:'fwsj', index: 'fwsj',width:'10%'},
						   {label:'���Ľ��',name:'fwjg', index: 'fwjg',width:'10%'},
						   {label:'�����ĺ�',name:'cfwh', index: 'cfwh',width:'10%'},
						   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						   {label:'jcwh',name:'jcwh', index: 'jcwh',hidden:true}
						],
						sortname: "fwsj",
					 	sortorder: "desc"
					}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
		})
		
		function searchRs() {
	           var map = getSuperSearch();
	          jQuery("#dataTable").reloadGrid(map);
	          }
		
		function sjkwhExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("wjcf_cfjg.do", sjkwhExportData);
		}
			
			
		// ��������
		function sjkwhExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "wjcf_cfjg.do?method=sjkwhExportData&dcclbh=" + "wjcf_cfjg.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}


		//���ش��־�����(����ʦ��ѧԺ)
		function printCfjdsWord() {
			var rows = jQuery("#dataTable").getSeletRow();			
			if (rows.length != 1) {
				showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ�����صļ�¼��");
				return false;
			}
			var cfid = rows[0]["cfid"];
			var url ="wjcfCfshwh_cfsjwh.do?method=getCfjdsb&&cfid="+cfid;
			window.open(url);
		}

		//�����⴦�ָ�֪��(����ʦ��ѧԺ)
		function printNcfgzsWord() {
			var rows = jQuery("#dataTable").getSeletRow();	
			if (rows.length != 1) {
				showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ�����صļ�¼��");
				return false;
			}
			var cfid = rows[0]["cfid"];
			var url ="wjcfCfshwh_cfsjwh.do?method=getNcfgzsb&&cfid="+cfid;
			window.open(url);
		}

		//���ؽ�������ļ�(����ʦ��ѧԺ)
		function printJccfwjb() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			var len = ids.length;
			if (len == 1) {
				var url = "wjcf_cfjcsq.do?method=getJccfwjb";
				url += "&xh=" + ids+"&cfid="+rows[0]["cfid"];
				window.open(url);
			} else {
				showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ�����صļ�¼��");
				return false;
			}
		}
		//���ش�������飨�ӱ���ҵ��ѧ��
		function printCftzswjb() {
			var rows = jQuery("#dataTable").getSeletRow();	
			if (rows.length != 1) {
				showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ�����صļ�¼��");
				return false;
			}
			var cfid = rows[0]["cfid"];
			var url ="wjcfCfshwh_cfsjwh.do?method=getCftzswjb&&cfid="+cfid;
			window.open(url);
		}
		// ͨ�ô�ӡ��������̨xxdm�ж�
		function getWordWjcfComman(type){
			 var rows = jQuery("#dataTable").getSeletRow();
	
			 if (rows.length == 0){
				showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
			 } else if (rows.length > 1){
				if(type == 'cfjctzs'){ 
					var flag = false;
					for(var i = 0; i < rows.length; i++){
						if((rows[i]["jcwh"] == '' || rows[i]["jcwh"] == null)){
							 flag = true;
						 }
					}
					if(flag){
						showAlertDivLayer("��ѡ�������ֵļ�¼��");
						return false;
					}
				}
				if("cfjgdy"==type){
					var flag = false;
					 for(var i = 0; i < rows.length; i++){
							if(rows[i]["fwjg"] != '�������' && rows[i]["fwjg"] != '���ֳ���'){
								 flag = true;
								 break;
							 }
						}
						if(flag){
							showAlertDivLayer("��ѡ�������ֻ򴦷ֳ����ļ�¼��");
							return false;
						}
					 }
				var ids = jQuery("#dataTable").getSeletIds();
				var fwjgArray = new Array();
				for(var i = 0; i < rows.length; i++){
					fwjgArray.push(rows[i]["fwjg"]);
				}
				var url="wjcf_cfsbgl.do?method=getDjbZip&value="+ids+"&type="+type+"&fwjgArray="+fwjgArray;
				window.open(url);
			 }else{
				 if(type == 'cfjctzs' && (rows[0]["jcwh"] == '' || rows[0]["jcwh"] == null)){
					 showAlertDivLayer("��ѡ�������ֵļ�¼��");
					 return false;
				 }
				 if("cfjgdy"==type&&rows[0]["fwjg"] != '�������' && rows[0]["fwjg"] != '���ֳ���'){
					 showAlertDivLayer("��ѡ�������ֻ򴦷ֳ����ļ�¼��");
					 return false;
				 }
				var url="wjcf_cfsbgl.do?method=getDjbWord&cfid="+rows[0]["cfid"]+"&type="+type+"&fwjg="+rows[0]["fwjg"];
				window.open(url);
		 	}
		}
			/*ʯ��ׯ��·ְҵѧԺ-ѧ�����־�����*/
			function printCfjds() {
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = jQuery("#dataTable").getSeletIds();
				var len = ids.length;
				if (len == 1) {
					var url = "wjcf_cfjg.do?method=getCfjdsDjb";
					url += "&xh=" + rows[0]["xh"] + "&cfid=" + rows[0]["cfid"];
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
					return false;
				} else {
					var url = "wjcf_cfjg.do?method=getCfjdsDjbZip";
					url += "&value=" + ids;
					window.open(url);
				}
			}

			/*�ൺ�Ƶ����ְҵ����ѧԺ13011*/
			function getCfjdsForQdjd(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				var len = rows.length;
				if(0 == len){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
					return false;
				}else{
					/*ֻ�����ش��ֳ����ļ�¼*/
					for(var i = 0; i < rows.length; i++){
						if(rows[i]["fwjg"] != '���ֳ���'){
							showAlertDivLayer("��ѡ�񴦷ֳ����ļ�¼��");
							return false;
						 }
					}
					var url="wjcf_cfsbgl.do?method=getCfjdsForQdjd&ids="+ids;
					window.open(url);
				}
			}
			//���صǼǱ�(�Ϻ�Ϸ��ѧԺ)
			function printCfWord(type) {
				var rows = jQuery("#dataTable").getSeletRow();			
				var ids = jQuery("#dataTable").getSeletIds();
				var len = rows.length;
				if (len == 1) {
					var url = "wjcf_cfjg.do?method=getCfForShxj&type="+type;
					url += "&xh=" + rows[0]["xh"] + "&cfid=" + rows[0]["cfid"];
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
					return false;
				} else {
					var url = "wjcf_cfjg.do?method=getCfForShxjZip&type="+type;
					url += "&value=" + ids;
					window.open(url);
				}
			}

        function getWord(){
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length == 0) {
                showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
            } else if (rows.length > 1) {
                var ids = "";
                var xhs = "";
                for (var i = 0; i < rows.length; i++) {
                    ids += rows[i]["cfid"];
                    xhs += rows[i]["xh"];
                    if (i < rows.length - 1) {
                        ids += ",";
                        xhs += ",";
                    }
                }
                var url = "wjcf_cfsssq.do?method=getWjcfjdsZip&value=" +ids+"&xhs="+ xhs;
                window.open(url);
            }else {
                var url="wjcf_cfsssq.do?method=getWjcfjdsWord";
                var url= url+"&cfid="+rows[0]["cfid"]+"&xh="+rows[0]["xh"];
                window.open(url);
            }
        }

        function getCfjcWord() {
            var rows = jQuery("#dataTable").getSeletRow();

            if (rows.length == 0) {
                showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
            } else if (rows.length > 1) {
                var ids = "";
                var xhs = "";
                for (var i = 0; i < rows.length; i++) {
                    var fwzt = rows[i]["fwjg"];
                    if(fwzt!="�������")
                    {
                        showAlertDivLayer("��ѡ���ѽ�����ֵļ�¼��");
                        return false;
                    }
                    ids += rows[i]["cfid"];
                    xhs += rows[i]["xh"];
                    if (i < rows.length - 1) {
                        ids += ",";
                        xhs += ",";
                    }

                }
                var url = "wjcf_cfsbgl.do?method=getCfjcZip&value="+ids+"&xhs="+xhs;
                window.open(url);
            } else {
                var fwzt = rows[0]["fwjg"];
                if(fwzt!="�������")
                {
                    showAlertDivLayer("��ѡ���ѽ�����ֵļ�¼��");
                    return false;
                }
                var url = "wjcf_cfsbgl.do?method=getCfjcWord&cfid=" + rows[0]["cfid"] + "&xh=" + rows[0]["xh"];
                window.open(url);
            }
        }
		</script>
	</head>
	<body>

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã� </em><a>${title }</a>
			</p>
		</div>
		<html:form action="/wjcf_cfjg" method="post">
			<input type="hidden" id="text"
				value="<bean:message key="wjcf.text" />" />

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->

				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showDialog('���Ӵ��ֽ��', 800, 500,'wjcf_cfjg.do?method=cfjgZj');return false;"class="btn_zj">����</a>
							</li>
							<li>
								<a href="#" onclick="cfjgXg();return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" onclick="cfjgSc();return false;" class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#" onclick="cfsscl();return false;" class="btn_xg">��������</a>
							</li>
							<li>
								<a href="#" onclick="cfjccl();return false;" class="btn_xg">����<bean:message key="wjcf.text" /> </a>
							</li>
							<li>
								<a href="#" onclick="cfzzcl();return false;" class="btn_xg">������ֹ</a>
							</li>
							<li>
								<a href="#" onclick="toImportData( 'IMPORT_N100109');return false;" class="btn_dr">��������</a>
							</li>
						</logic:equal>
						<logic:equal value="10606" name="xxdm">
							<li><a href="#" class="btn_down" onclick="printCfjdsWord();return false;">�����ļ�����</a></li>
							<li><a href="#" class="btn_down" onclick="printNcfgzsWord();return false;">�⴦�ָ�֪������</a></li>
						</logic:equal>
						<logic:equal value="10080" name="xxdm">
							<li><a href="#" class="btn_down" onclick="printCftzswjb();return false;">�����������</a></li>
						</logic:equal>
						<%--�Ϻ�Ϸ��ѧԺ --%>
						<logic:equal value="10279" name="xxdm">
							<li><a href="#" class="btn_down" onclick="printCfWord('cfjds');return false;">���־���������</a></li>
							<li><a href="#" class="btn_down" onclick="printCfWord('cfsds');return false;">�����ʹ���ǩ�յ�����</a></li>
						</logic:equal>
						<logic:notEqual value="10606" name="xxdm">
							<li>
								<a href="#" onclick="showView(); return false;" class="btn_ck">�鿴</a>
							</li>
							<li>
								<a href="#" class="btn_dc" onclick="sjkwhExportConfig();return false;">����</a>
							</li>
							<%--
							
							<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>--%>
							<!-- ���ݴ�ѧ -->
							<logic:equal value="10351" name="xxdm">
								<li>
									<a href="#" class="btn_down" onclick="getWord();return false;">����<bean:message key="wjcf.text" />��������</a>
								</li>
							</logic:equal>
							<!-- �Ϻ�����ְҵ����ѧԺ -->
							<logic:equal value="12915" name="xxdm">
								<li>
									<a href="#" class="btn_dy" onclick="getWjCfWord();return false;">Υ�ʹ���֪ͨ���ӡ</a>
								</li>
							</logic:equal>
							<!-- ���ݹ�ҵ -->
							<logic:equal value="12686" name="xxdm">
								<li>
									<a href="#" class="btn_down" onclick="getWordWjcfComman('cfjgdy');return false;">���ֽ������ӡ</a>
								</li>
							</logic:equal>	
							<logic:equal value="12865" name="xxdm">
								<li>
									<a href="#" class="btn_down" onclick="getCfjdWord();return false;">���־���������</a>
								</li>
							</logic:equal>	
						</logic:notEqual>	
						<!-- ��ͨ��ó��ʦѧԺ begin -->
						<logic:equal name="xxdm" value="5002">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcftzs');return false;" class="btn_down">���ش���֪ͨ��</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjctzs');return false;" class="btn_down">���س�������֪ͨ��</a></li>
						</logic:equal>
						<!-- ��ͨ��ó��ʦѧԺ end -->	
						<!-- ����ְҵ����ѧԺ begin -->
						<logic:equal name="xxdm"  value="11773">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">����Υ�͵��鴦���걨��</a></li>
						</logic:equal>
						<!-- ����ְҵ����ѧԺend -->
						<!-- ������ũ��ְҵѧԺ begin -->
						<logic:equal name="xxdm" value="12727">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">���ؽ������������</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">����Υ�ʹ���������</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcftzs');return false;" class="btn_down">���ش��־�����</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfqsd');return false;" class="btn_down">���ش��־�����ǩ�յ�</a></li>
						</logic:equal>
						<!-- ������ũ��ְҵѧԺ end -->	
						<!-- ����ҽҩ�ߵ�ְҵѧУ begin -->
						<logic:equal name="xxdm"  value="70002">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">����Υ�ʹ���������</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjgdy');return false;" class="btn_down">���ش��ֳ��������</a></li>
						</logic:equal>
						<!-- ����ҽҩ�ߵ�ְҵѧУ end -->
						<!-- ������Ͽҽҩ�ߵ�ר��ѧУ begin -->
						<logic:equal name="xxdm" value="14008">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfsssq');return false;" class="btn_down">���ش������������</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">���ش��ֳ��������</a></li>
						</logic:equal>
						<!-- ������Ͽҽҩ�ߵ�ר��ѧУ end -->
						<!-- �Ĵ�ְҵ����ѧԺbegin -->
						<logic:equal name="xxdm" value="12970">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">���ɴ���ǼǱ�</a></li>
						</logic:equal>	
						<!-- �Ĵ�ְҵ����ѧԺend -->
						<!-- �������ù���ְҵѧԺbegin -->
						<logic:equal name="xxdm" value="14073">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfjds');return false;" class="btn_down">Υ�ʹ��־�����</a></li>
						</logic:equal>	
						<logic:equal name="xxdm" value="13871">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfjds');return false;" class="btn_down">Υ�ʹ��־�����</a></li>
						</logic:equal>	
						<!-- �������ù���ְҵѧԺend -->
							
						<!-- �ൺ�Ƶ����ְҵ����ѧԺ -->
						<logic:equal value="13011" name="xxdm">
							<li><a href="javascript:void(0);" class="btn_down" onclick="getCfjdsForQdjd();return false;">���־���������</a></li>
						</logic:equal>
						
						<!-- �ں�ְҵ����ѧԺ -->
						<logic:equal value="13915" name="xxdm">
							<li><a href="javascript:void(0);" class="btn_down" onclick="cfwjDownload('wjcf_cfjg.do?method=cfjdsDownload');return false;">���־���������</a></li>
							<li><a href="javascript:void(0);" class="btn_down" onclick="cfwjDownload('wjcf_cfjg.do?method=cfspbDownload');return false;">��������������</a></li>
						</logic:equal>		
						
						<!-- ʯ��ׯ��·ְҵ����ѧԺ-ѧ�����־����� -->
						<logic:equal value="12424" name="xxdm">
							<li><a href="javascript:void(0);" onclick="printCfjds();return false;" class="btn_down">���־�����</a></li>
						</logic:equal>
						
						<!-- �㽭����ְҵѧԺ�����Ի���ӡѧ������������ -->
						<logic:equal name="xxdm" value="12869">
							<li><a href="javascript:void(0);" onclick="printXscfspb();return false;" class="btn_down">ѧ������������</a></li>
						</logic:equal>
						<!-- �ӱ�����ʦ��ѧԺ begin -->
						<logic:equal name="xxdm" value="10098">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">Υ�ʹ��־���������</a></li>
						</logic:equal>
						<!-- �ӱ�����ʦ��ѧԺ end -->
                        <!-- �ӱ�����ʦ��ѧԺ begin -->
                        <logic:equal name="xxdm" value="10098">
                            <li><a href="javascript:void(0);" onclick="getCfjcWord();return false;" class="btn_down">�����������������</a></li>
                        </logic:equal>
                        <!-- �ӱ�����ʦ��ѧԺ end -->
					</ul>
				</div>
				<logic:equal value="10606" name="xxdm">
				<div class="buttonbox">
					<ul>							
						<li>
							<a href="javascript:void(0);" onclick="printJccfwjb();return false;"
								class="btn_down"><bean:message key="wjcf.text" />�����ļ�����</a>
						</li>
						<li>
							<a href="#" onclick="showView(); return false;" class="btn_ck">�鿴</a>
						</li>
						<li>
							<a href="#" class="btn_dc" onclick="sjkwhExportConfig();return false;">����</a>
						</li>
					</ul>
				</div>
				</logic:equal>

				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ѯ���</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
