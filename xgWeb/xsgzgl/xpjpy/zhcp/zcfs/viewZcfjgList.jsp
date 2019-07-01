<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery(".ha a").first().click();
			});
			
			function getGridSettiong(){
				var pmfs = jQuery("#pmfs").val();
				var colList="";
				var xxdm = jQuery("#xxdm").val();
				var gridSetting = {
					caption:"�۲���",
					pager:"pager",
					url:"xpj_zcfs.do?method=viewZcfjgList&doType=query",
					params:getSuperSearch(),
					multiselect:false,
					sortname: "fs0",
				 	sortorder: "desc"
				};

				if(xxdm == "13108" && pmfs == "bjpm"){
					gridSetting["multiselect"] = true;
				}
				
				if(pmfs=="cpzpm"||pmfs==""){//������	
					if(xxdm == "10335"){//�㽭��ѧ
						colList=[
						   {label:'�۲�����',name:'zczq', index: 'zczq',width:'15%',key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'10%'}
						];	
					}else if(xxdm == "10264"){//�Ϻ������ѧ
						colList=[
						   {label:'�۲�����',name:'zczq', index: 'zczq',width:'15%',key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'����Ǹ�',name:'sfwtgg', index: 'sfwtgg',width:'5%'},
						   {label:'����������',name:'cpzmc', index: 'cpzdm',width:'15%'}
						];	
					}else if(xxdm == "12684"){//��ͨ�Ƽ�ְҵ����ѧԺ
						colList=[
						   {label:'�۲�����',name:'zczq', index: 'zczq',width:'15%',key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'����������',name:'cpzmc', index: 'cpzdm',width:'15%'},
						   {label:'�༶����',name:'bjmc', index: 'bjdm',width:'15%'}
						];	
					}else{
						colList=[
						   {label:'�۲�����',name:'zczq', index: 'zczq',width:'15%',key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'�����༶',name:'bjmc',index:'bjmc'},
                           {label:'רҵ�༶',name:'zybjmc',index:'zybjmc'},
                           {label:'����������',name:'cpzmc', index: 'cpzdm',width:'15%'}
						];	
					}
				}else if (pmfs=="bjpm"){
					if(xxdm == "10264"){
						colList=[
						   {label:'�۲�����',name:'zczq', index: 'zczq',width:'15%',key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'����Ǹ�',name:'sfwtgg', index: 'sfwtgg',width:'5%'},
						   {label:'�༶����',name:'bjmc', index: 'bjdm',width:'15%'}
						];
					}else{
						colList=[
						   {label:'�۲�����',name:'zczq', index: 'zczq',width:'15%',key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
                           {label:'�����༶',name:'bjmc',index:'bjmc'},
                           {label:'רҵ�༶',name:'zybjmc',index:'zybjmc'}
						];
					}
				}else if (pmfs=="njzypm"){
					if(xxdm == "10264"){
						colList=[
	                       {label:'�۲�����',name:'zczq', index: 'zczq',width:'15%',key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'����Ǹ�',name:'sfwtgg', index: 'sfwtgg',width:'5%'},
						   {label:'רҵ����',name:'zymc', index: 'zydm',width:'15%'}
						];
					}else{
						colList=[
	                       {label:'�۲�����',name:'zczq', index: 'zczq',width:'15%',key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'רҵ����',name:'zymc', index: 'zydm',width:'15%'},
                           {label:'�����༶',name:'bjmc',index:'bjmc'},
                           {label:'רҵ�༶',name:'zybjmc',index:'zybjmc'}
						];
					}
				}else if(pmfs=="zybjpm"){
                    colList=[
                        {label:'�۲�����',name:'zczq', index: 'zczq',width:'15%',key:true},
                        {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
                        {label:'����',name:'xm', index: 'xm',width:'10%'},
                        {label:'רҵ����',name:'zymc', index: 'zydm',width:'15%'},
                        {label:'�����༶',name:'bjmc',index:'bjmc'},
                        {label:'רҵ�༶',name:'zybjmc',index:'zybjmc'}
                    ];
                }
					
				var zcxm = jQuery("font[name=zcxm]");
				jQuery.each(zcxm,function(i,n){
					var xmfsJson = {
							label:jQuery(n).attr("xmmc"),
							name:"fs"+i,
							index:"fs"+i
					};
					if(xxdm != "10335"){ // ������㽭��ѧ����ô����
						var xmpmJson = {
								label:"����",
								name:"pm"+i,
								index:"fs"+i
						};
						colList.push(xmpmJson);
					}
					colList.push(xmfsJson);
				});

				var xnColJson = {
						label:"ѧ��",
						name:"xn",
						index:"xn",
						hidden:true
				};
				colList.push(xnColJson);
				var xqColJson = {
						label:"ѧ��",
						name:"xq",
						index:"xq",
						hidden:true
				};
				colList.push(xqColJson);
				
				gridSetting["colList"] = colList;
				
				return gridSetting;				
			}

	
			function searchRs(){
				var xn_num = document.getElementsByName("a_name_xn").length;
				var notFirst = jQuery("#notFirst").val();
				if("yes"==notFirst&&0==xn_num){
					showAlertDivLayer("����ѡ��һ��ѧ�꣡");
					return false;
				}
				jQuery.ajaxSetup({async:false});
				initZcxm();
				var gridSetting = getGridSettiong();
				var pmfs = jQuery("#pmfs").val();
				var zczq = jQuery("#zczq").val();
				var map = getSuperSearch();
				map["pmfs"] = pmfs;
				map["zczq"] = zczq;
				gridSetting.params = map;
				//jQuery("#dataTable").reloadGrid(map);
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery.ajaxSetup({async:true});
			}
		
			//jQuery(function(){
			//	jQuery("#dataTable").initGrid(gridSetting);
			//});


			//�۲�ֵ���
			function exportZcf(){
				setSearchTj();//���ø߼���ѯ����
				var zczq = jQuery("#zczq").val();
				var pmfs = jQuery("#pmfs").val();
				var url = "xpj_zcfs.do?method=exportViewZcfsjg&pmfs="+pmfs +"&zczq="+zczq;
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			//�㽭����ְҵ����ѧԺ �����۲����
			function exportZcf_12861(){
				var xn_num = document.getElementsByName("a_name_xn").length;
				var xq_num = document.getElementsByName("a_name_xq").length;
				if(xn_num != 1){
					showAlertDivLayer("��ѡ��һ��ѧ�꣡");
					return false;
				}
				var zczq = "${zczq}";
				if(zczq == '1' && xq_num != 1){
					showAlertDivLayer("��ѡ��һ��ѧ�ڣ�");
					return false;
				}
				setSearchTj();//���ø߼���ѯ����
				var zczq = jQuery("#zczq").val();
				var pmfs = jQuery("#pmfs").val();
				var url = "xpj_zcfs.do?method=exportViewZcfsjg_12861&pmfs="+pmfs +"&zczq="+zczq;
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

            //��������ҽҩ�ߵ�ְҵѧУ
            function exportCjhzb_70002(){
                var xn_num = document.getElementsByName("a_name_xn").length;
                var xq_num = document.getElementsByName("a_name_xq").length;
                var bj_num = document.getElementsByName("a_name_bj").length;
                if(xn_num != 1){
                    showAlertDivLayer("��ѡ��һ��ѧ�꣡");
                    return false;
                }
                if(xq_num != 1){
                    showAlertDivLayer("��ѡ��һ��ѧ�ڣ�");
                    return false;
                }
                if(bj_num != 1){
                    showAlertDivLayer("��ѡ��һ���༶��");
                    return false;
                }
                setSearchTj();//���ø߼���ѯ����
                var url = "xpj_zcfs.do?method=exportCjhzb";
                url = addSuperSearchParams(url);
                jQuery("form").eq(0).attr("action", url);
                jQuery("form").eq(0).submit();
            }

			//ҳǩ�л�
			function selectTab(obj,pmfs,zczq){
				jQuery("#pmfs").val(pmfs);
				jQuery("#zczq").val(zczq);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				searchRs();
				var xxdm = jQuery("#xxdm").val();
				if(xxdm == "12861" && pmfs == "bjpm"){
					jQuery("#li_exportzcf_12861").show();
				}else{
					jQuery("#li_exportzcf_12861").hide();
				}
				if(xxdm == "13108" && pmfs == "bjpm"){
					jQuery("#li_xszhszcphzb").show();
					jQuery("#li_xszhszcpjghzb").show();
				}else{
					jQuery("#li_xszhszcphzb").hide();
					jQuery("#li_xszhszcpjghzb").hide();
				}
			}
			function initZcxm(){
				var url="xpj_zcfs.do?method=initZcxm";
				var zczq = jQuery("#zczq").val();
				var xn_num = document.getElementsByName("tj_xn").length;
				var xn_arr = new Array();
				var xn_count = 0;
				for(var i=0;i<xn_num;i++){
					var obj = document.getElementsByName("tj_xn")[i];
					if(obj.className == "selectedValue"){
						var xn_v = obj.id.split("_")[2];
						xn_arr[xn_count] = xn_v;
						xn_count++;
					}
				}
				var xq_num = document.getElementsByName("tj_xq").length;
				var xq_arr = new Array();
				var xq_count = 0;
				for(var i=0;i<xq_num;i++){
					var obj = document.getElementsByName("tj_xq")[i];
					if(obj.className == "selectedValue"){
						var xq_v = obj.id.split("_")[2];
						xq_arr[xq_count] = xq_v;
						xq_count++;
					}
				}

				var notFirst = jQuery("#notFirst").val();
				if(notFirst != "yes"){ // ����˵�����һ�μ���ʱ���޷���ȡ�߼���ѯ��ѧ�ꡢѧ�ڣ�ֻ���ֶ����
					xn_arr.push("${cssz.xn}");
					var zczqTemp = "${zczq}";
					if(zczqTemp == '1'){
						xq_arr.push("${cssz.xq}");
					}
				}
				
				jQuery.post(url,{"zczq":zczq,"xn":xn_arr.join("!!@@!!"),"xq":xq_arr.join("!!@@!!")},function(data){
					dataObj=data;
					createZcxmDiv();
					},'json');

				
				}
			function createZcxmDiv(){
				var zcDiv=jQuery("#zcxmDiv");
				jQuery("font",zcDiv).remove();
				var zcxmHtml = "";
				for ( var i = 0; i < dataObj.length; i++) {
					var o = dataObj[i];
					zcxmHtml+="<font style='display:none;' xmdm="+o.xmdm+" xmmc="+o.xmmc+" name='zcxm'></font>";
				}
				zcDiv.html(zcxmHtml);
				jQuery("#notFirst").val("yes")
				}

			// ���ز������ܱ�
			function getWord_xszhszcphzb(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					 var xns = new Array();
					 var xqs = new Array();
					 var xhs = new Array();
					for(var i = 0; i < rows.length; i++){
						var row = rows[i];
						xns.push(row["xn"]);
						xqs.push(row["xq"]);
						xhs.push(row["xh"]);
					}
					var url="xpj_zcfs.do?method=getDjbZipStu&xns="+xns.join(",")+"&xqs="+xqs.join(",")+"&xhs="+xhs.join(",");
					window.open(url);
				 } else {
					 var row = rows[0];
					var url="xpj_zcfs.do?method=getDjbWordStu&xn="+row["xn"]+"&xq="+row["xq"]+"&xh="+row["xh"];
					window.open(url);
			     }
			}
			// ���ز���������ܱ�
			function getWord_xszhszcpjghzb(){
				var xn_num = document.getElementsByName("a_name_xn").length;
				if(xn_num != 1){
					showAlertDivLayer("��ѡ��һ��ѧ�꣡");
					return false;
				}
				var zczqTemp = "${zczq}";
				if(zczqTemp == '1'){
					var xq_num = document.getElementsByName("a_name_xq").length;
					if(xq_num != 1){
						showAlertDivLayer("��ѡ��һ��ѧ�ڣ�");
						return false;
					}
				}
				var bj_num = document.getElementsByName("a_name_bj").length;
				if(bj_num != 1){
					showAlertDivLayer("��ѡ��һ���༶��");
					return false;
				}

				var xn = document.getElementsByName("a_name_xn")[0].id.replace("a_id_","");
				var xq = "on";
				if(zczqTemp == '1'){
					xq = document.getElementsByName("a_name_xq")[0].id.replace("a_id_","");
				}
				var bjdm = document.getElementsByName("a_name_bj")[0].id.replace("a_id_","");	
				var url="xpj_zcfs.do?method=getDjbWordBjdm&xn="+xn+"&xq="+xq+"&bjdm="+bjdm;
				window.open(url);
			}
		</script>
	</head>

	<body style="min-height: 800px;">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		
		<logic:present name="zcxmList">
		<div id="zcxmDiv">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }" xmmc="${z.xmmc }" name="zcxm"></font>
			</logic:iterate>
		</logic:present>
		</div>
		<html:form action="/xpj_zcfs">
			<input type="hidden" id="pmfs" value=""/>
			<input type="hidden" id="zczq" value=""/>
			<input type="hidden" id="notFirst" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="10335" name="xxdm">
					<logic:equal value="zf01" name="userName">
						<div class="buttonbox">
							<ul>
								<li id="li_sh">
									<li>
										<a href="#" class="btn_dc" onclick="exportZcf(); return false;">����</a>
									</li>
								</li>
							</ul>
						</div>
					</logic:equal>
				</logic:equal>
				<logic:notEqual value="10335" name="xxdm">
					<div class="buttonbox">
						<ul>
								<li>
									<a href="#" class="btn_dc" onclick="exportZcf(); return false;">����</a>
								</li>
								<logic:equal name="xxdm" value="12861">
									<li id="li_exportzcf_12861" style="display: none;"><a href="#" class="btn_dc" onclick="exportZcf_12861(); return false;">�����۲����</a></li>
								</logic:equal>
								<logic:equal name="xxdm" value="13108">
									<li id="li_xszhszcphzb" style="display: none;"><a href="#" class="btn_down" onclick="getWord_xszhszcphzb(); return false;">���ز������ܱ�</a></li>
									<li id="li_xszhszcpjghzb" style="display: none;"><a href="#" class="btn_down" onclick="getWord_xszhszcpjghzb(); return false;">���ز���������ܱ�</a></li>
								</logic:equal>
								<logic:equal name="xxdm" value="70002">
									<li><a href="#" class="btn_dc" onclick="exportCjhzb_70002(); return false;">���سɼ����ܱ�</a></li>
								</logic:equal>
						</ul>
					</div>
				</logic:notEqual>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->			
				<logic:notEqual name="zczq" value="2">
					<div class="comp_title" id="comp_title">
				      <ul style="width:90%">
				      <logic:notEqual name="xxdm" value="12867">
				      	  <logic:notEqual name="xxdm" value="10335">
					        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'cpzpm','xq');"><span>����������(ѧ��)</span></a></li>
					        <li><a href="javascript:void(0);" onclick="selectTab(this,'bjpm','xq');"><span>�༶����(ѧ��)</span></a></li>
					        <li><a href="javascript:void(0);" onclick="selectTab(this,'njzypm','xq');"><span>�꼶רҵ����(ѧ��)</span></a></li>
					      </logic:notEqual>
				      </logic:notEqual>
				      <logic:equal name="xxdm" value="12867">
				        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'bjpm','xq');"><span>�༶����(ѧ��)</span></a></li>
				      </logic:equal>
				      </ul>
				    </div>
				</logic:notEqual>
			    <logic:notEqual name="zczq" value="1">
			   	 <div class="comp_title" id="comp_title">
			   	 	<ul style="width:90%">
			   	 	 <% if(!"12867".equals(xxdm) && !"10681".equals(xxdm)){ %>
				        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'cpzpm','xn');"><span>����������(ѧ��)</span></a></li>
				        <logic:notEqual name="xxdm" value="10335">
					        <li><a href="javascript:void(0);" onclick="selectTab(this,'bjpm','xn');"><span>�����༶����(ѧ��)</span></a></li>
							<li><a href="javascript:void(0);" onclick="selectTab(this,'zybjpm','xn');"><span>רҵ�༶����(ѧ��)</span></a></li>
					        <li><a href="javascript:void(0);" onclick="selectTab(this,'njzypm','xn');"><span>�꼶רҵ����(ѧ��)</span></a></li>
				    	</logic:notEqual>    
				    <% }else{ %>
				    	<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'bjpm','xn');"><span>�༶����(ѧ��)</span></a></li>
				     <% } %>
				    </ul>
			     </div>
			   </logic:notEqual>
				
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�۲��� </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
