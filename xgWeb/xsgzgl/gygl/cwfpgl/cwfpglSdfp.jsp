<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<style type="text/css">
			.fontTk{color:green; font-weight:bold; font-size:12px;padding-left:10px;}
			.demo_hq_list1{width:750px; margin:30px 20px; overflow:hidden;}
			.demo_hq_list1 ul li .con4{ width:98%; padding:1%; float:left; background:#f2f2f2;display:none;height:400px;overflow-y:auto;overflow-x:hidden;}
			.demo_hq_list1 ul li .con40{ width:98%; padding:1%; float:left; background:#f2f2f2;display:none;}
		</style>
		<script language="javascript" defer="defer">
		
		//��λ�ֶ������ѯ
		function allNotEmpThenGo(){
			var lddm=$("lddm").value;
			if(lddm==""){
				alertInfo("��ѡ��¥��!");
				return false;
			}
			// ��ʾ�������
			var resultSet = jQuery("#resultSet").val();
			var xb = jQuery(".imitation_xl div").attr("name");
			if("boy" == xb){
				xb = '1';
			}else if ("gril" == xb){
				xb = '2';
			}
			if("boy" == xb){
				xb = '1';
			}else if ("gril" == xb){
				xb = '2';
			}		
			if(jQuery("[name=bjdm]").val()!=""){
				resultSet = "bjli";
			}else if(jQuery("[name=zydm]").val()!=""){
				resultSet = "zyli";
			}else if(jQuery("[name=xydm]").val()!=""){
				resultSet = "xyli";
			}
			// userType
			var userType = jQuery("#userType").val();
			var xyyh = "";
			if("xy"==userType && "xyli" == resultSet){

				alertInfo('��ѡ��רҵ/�༶��');
				return false;
			}else if("xy"==userType){
				
				var nj=$("hidden_nj").value;
				var xydm=$("hidden_xydm").value;
				xyyh = "&nj="+nj+"&xydm="+xydm;
			}	
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&resultSet="+resultSet+"&xb="+xb;
			
			refreshForm(url+xyyh);
			BatAlert.showTips('���ڲ��������Ե�...');
		}
		
		//��λ�ֶ�����
		function saveQscwfpxx(doType){

			// ��ʾ�������
			var resultSet = jQuery("#resultSet").val();
			// userType
			var userType = jQuery("#userType").val();

			if("xy"==userType && "xyli" == resultSet){

				alertInfo(jQuery("#xbmc").val()+"�û����ܶ�"+jQuery("#xbmc").val()+"���з��䣬���л�������ٽ��в�����");
				return false;
			}

			var select_qs_count = jQuery("[name='checkbox_qsh']:checkbox:checked").length;
			var select_cw_count = jQuery("[name='checkbox_cwh']:checkbox:checked").length;

			if(doType=="qxfp"){
				if(select_qs_count==0&&select_cw_count==0){
					alertInfo("��ѡ����Ҫȡ����������һ�λ��");
					return false;
				}
				confirmInfo("���ι�ѡ������������"+select_qs_count+"��,��λ����"+select_cw_count+"��,\nȷ��ȡ��������",function(ok){
					if(ok=="ok"){
						saveQscwfpxx_submit(doType);
					}
				});
				
			}else{
				if(select_qs_count==0&&select_cw_count==0){
					alertInfo("��ѡ����Ҫ��������һ�λ��");
					return false;
				}
				confirmInfo("���ι�ѡ������������"+select_qs_count+"��,��λ����"+select_cw_count+"��,\nȷ��������",function(ok){
					if(ok=="ok"){
						saveQscwfpxx_submit(doType);
					}
				});
			}
		}	

		function saveQscwfpxx_submit(doType){			
			$("nj").value=$("hidden_nj").value;
			jQuery("[name=xydm]").val($("hidden_xydm").value);
			jQuery("[name=zydm]").val($("hidden_zydm").value);
			jQuery("[name=bjdm]").val($("hidden_bjdm").value);

			var xydm = $("hidden_xydm").value;
			var nj = $("hidden_nj").value;
			
			var xb = jQuery(".imitation_xl div").attr("name");
			if("boy" == xb){
				xb = '1';
			}else if ("gril" == xb){
				xb = '2';
			}		
			$("lddm").value=$("hidden_lddm").value;
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&doType="+doType+"&xb="+xb+"&xydm="+xydm+"&nj="+nj;
			refreshForm(url);
			BatAlert.showTips('���ڱ��棬���Ե�...');
		}
		//ҳ���л�
		function pageqh(act){
			var msg="";
			// userType
			var userType = jQuery("#userType").val();
			var xyyh = "";
			if("xy"==userType){				
				var nj=$("hidden_nj").value;
				var xydm=$("hidden_xydm").value;
				xyyh = "&nj="+nj+"&xydm="+xydm;
			}	
			
			if(act=="to_fp"){
				msg="ȷ���뿪��ǰ����ҳ�棬ת���������ҳ����";
			}else{
				msg="ȷ���뿪��ǰ����ҳ�棬ת��ȡ���������ҳ����";
			}
			var xb = jQuery(".imitation_xl div").attr("name");
			if("boy" == xb){
				xb = '1';
			}else if ("gril" == xb){
				xb = '2';
			}
			confirmInfo(msg, function(tag){
				if(tag == 'ok'){
					var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&pageqh=pageqh&act="+act+"&xb="+xb+xyyh;
					refreshForm(url);
					BatAlert.showTips('������ת�����Ե�...');
				}
			});
		}
		
		//���ò�ѯ��ʾ��λ��
		function setSearchMsgWz(left,top){
			if($("input_mhcx_msg")){
				$("input_mhcx_msg").style.left=left;
				$("input_mhcx_msg").style.top=top;
			}
		}	
		
		//��̬��ʾѡ������Ҵ�λ����
		function dyn_show_qs_cw_count(){
			var dyn_qs_count = jQuery("[name='checkbox_qsh']:checkbox:checked").length;
			var dyn_cw_count = jQuery("[name='checkbox_cwh']:checkbox:checked").length;
			var inhtml = "��ǰ��ѡ����������<font color='red'>"+dyn_qs_count+"</font>����"+
			"��λ����<font color='red'>"+dyn_cw_count+"</font>��";

			//������ʾ��Ϣ
			jQuery("#dyn_show_qs_cw_count").html(inhtml);			
		}

		//ѡ������
		function selectAll(obj,curr_type){
			var xb = jQuery(".imitation_xl div").html();
			var ches;
			if(curr_type=="cw"){//��λ
				if(jQuery(obj).parents("td[name=cwxx]").prevAll("td[name=qsxx]").find("input[name=qsh_xb]").val()!=xb){//�Ա𲻶�Ӧʱ������ѡ��
					jQuery(obj).attr("checked","false");
				}
				ches=new Array();
			}else if(curr_type=="qs"){//����

				ches = jQuery(obj).parent().next().find(":checkbox");
			}else if(curr_type=="lc"){//¥��
				ches = jQuery(obj).parent().nextAll().find(".dateline").find(":checkbox");
				
			}else if(curr_type=="ld"){//¥��
				ches = jQuery(obj).parents(".demo_hq_list1").find(":checkbox");
			}else{
				return false;
			}
			//checkboxѡ��״̬
			var checkZt = jQuery(obj).prop("checked");
			for(var i=0;i<ches.length;i++){
				if(jQuery(ches[i]).attr("disabled")!=true){
					if(jQuery(ches[i]).attr("name")=="checkbox_cwh"){
						if(jQuery(ches[i]).parents("td[name=cwxx]").prevAll("td[name=qsxx]").find("input[name=qsh_xb]").val()!=xb){//�Ա𲻶�Ӧʱ������ѡ��
							continue;
						}
					}else if(jQuery(ches[i]).attr("name")=="checkbox_qsh"){
						if(jQuery(ches[i]).prev("input[name=qsh_xb]").val()!=xb){//�Ա𲻶�Ӧʱ������ѡ��
							if(curr_type=="qs"){//���ڴ�λ�����Ҳ�����ѡ��
								obj.checked=false;
							}
							continue;
						}
					}
					jQuery(ches[i]).attr("checked",checkZt);
				}
			}
			dyn_show_qs_cw_count();
		}

		//��ʾ¥��������Ϣ
		function modi(obj){
			var lddm = jQuery(obj).find("input:checkbox").val();
			var url = "gyglnew_xxtj.do?method=xxtjDetail"
			var h=1024
			var w=768
			if(lddm != null){
				showOpenWindow(url + '&pkValue='+lddm,h,w);
				return false;
			}
			return false;
		}

		//�л��Ա�����¼���
		function onchangeSex(xb){
			
			// ��ʾ�������
			var resultSet = jQuery("#resultSet").val();
			
			if(jQuery("[name=bjdm]").val()!=""){
				resultSet = "bjli";
			}else if(jQuery("[name=zydm]").val()!=""){
				resultSet = "zyli";
			}else if(jQuery("[name=xydm]").val()!=""){
				resultSet = "xyli";
			}
			// userType
			var userType = jQuery("#userType").val();
			var xyyh = "";
			if("xy"==userType){				
				var nj=$("hidden_nj").value;
				var xydm=$("hidden_xydm").value;
				xyyh = "&nj="+nj+"&xydm="+xydm;
			}	
			var cwzt = jQuery("#cwzt").val();
			var xydm = jQuery("#hidden_xydm").val(); 
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&resultSet="+resultSet+"&xbdm="+xb+xyyh+"&cwzt="+cwzt+"&xydm="+xydm;
			refreshForm(url);
			BatAlert.showTips('���ڲ��������Ե�...');
		}
		
		jQuery(function() {

			// ��λ�������
			var cwfpdx = jQuery("#cwfpdx").val();

			// ѧԺ����
			if("xydm"==cwfpdx){
				//����רҵ/�༶�б�
				jQuery("#zytr").hide();
				jQuery("#bjtr").hide();
			} else if("zydm"==cwfpdx){
				//���ذ༶�б�
				jQuery("#bjtr").hide();
			}
			jQuery("#fptj").css("display","none");
			jQuery(".regcon .regcon_nav").click(function(){
				if(jQuery("#fptj").css("display")	==	"none"){
					jQuery(this).children(".floatright1").removeClass("floatright1").addClass("floatright");
					jQuery("#fptj").css("display","block");	
				}
				else
				{
					jQuery(this).children(".floatright").removeClass("floatright").addClass("floatright1");
					jQuery("#fptj").css("display","none");	
				}
				
			})
			
			//�Ա�����
			var xb = jQuery("#hidden_xb").val();
			if(xb == '��'){
				jQuery(".imitation_xl div").attr("name","boy");
			}else if(xb == 'Ů'){
				jQuery(".imitation_xl div").attr("name","gril");
			}
			jQuery(".imitation_xl div").html(xb);
			
			jQuery(".imitation_xl div").click(function(event){
				event.stopPropagation();
				if(jQuery(this).prev("ul").is(":hidden")){
					jQuery(this).prev("ul").show();
				}else{
					jQuery(this).prev("ul").hide();
				}
			});
			jQuery(".imitation_xl ul li a").click(function(){
				var liid = jQuery(this).attr("name");
				if("boy" == liid){
					onchangeSex('1');
				}else if ("gril" == liid){
					onchangeSex('2');
				}
				
			});
			
			jQuery("body").click(function(){jQuery(".imitation_xl ul").hide()});

			// userTypeѧԺ�û��������л��꼶ѧԺ
			var userType = jQuery("#userType").val();
			if("xy" ==userType){
				jQuery("#nj").attr("disabled", true);
				jQuery("#xy").attr("disabled", true);
			}

			//¥�����ͳ�ƽ���չʾ
			jQuery(".demo_hq_list1 ul li .num").each(function(){
				var num		=	jQuery(this).html(); //�ܷ�����
				var num1	=	jQuery(this).parent().next().children(".con2_2").children(".floatleft").children(".num1").html(); //��ס
				var num2	=	num1/num*100;
				var num3	=	num2+"%";
				jQuery(this).parent().next().children(".length").children(".length1").animate({width:num3},1500);
			});
			//�鿴/����������Ϣ
			jQuery(".demo_hq_list1 ul li .con3").click(function(){
				if(jQuery(this).next().find(".dateline").is(":hidden")){
					jQuery(this).attr("title","����ر�");
					jQuery(this).next().find(".dateline").show();
					var height = jQuery(this).next().find(".dateline").height();
					if(height > 400){
						jQuery(this).next().attr("class","con4");
					}else{
						jQuery(this).next().attr("class","con40");
					}
					jQuery(this).next().show();
					jQuery(this).parent().addClass("li");
				}else{
					jQuery(this).attr("title","���չ��");
					jQuery(this).next().find(".dateline").hide();
					jQuery(this).next().hide();
					jQuery(this).parent().removeClass("li");
				}
			});
			initLdList();				
		});
		//ѡ��רҵ������״̬���л��Ա�ʱ¥���б�����
		function initLdList(){
			var xydm =jQuery("#xy").val();
			var zydm = jQuery("#zy").val();
			var cwzt = jQuery("#cwzt").val();
			var xbdm = null;
			var liid = jQuery(".imitation_xl div").attr("name");
			if("boy" == liid){
				xbdm = "1";
			}else if ("gril" == liid){
				xbdm = "2";
			}
			var url = "gyglnew_cwfpgl.do?method=cwfpglInitLdList";
			
				jQuery.ajax({
					type : "post",
					async : false,
					url : url,
					contentType:"application/x-www-form-urlencoded; charset=UTF-8",
					data : {
						xydm:xydm,
						zydm:zydm,
						cwzt:cwzt,
						xbdm:xbdm
					},
					
					success : function(data) {
						var datas = eval('('+data+')');
						var html='<option value="">--��ѡ��--</option>';
						if(datas!=null&&datas!=""&&datas.length!=0){
	 						for ( var int = 0; int < datas.length; int++) {
 								html+='<option value="'+datas[int]["lddm"]+'">'+datas[int]["ldmc"]+'</option>';
							}
				}
						jQuery("#lddm").html(html);
						jQuery("#lddm").val(jQuery("#cxlddm").val());
					}
					});
			}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ����-ס�޹���-<logic:equal value="to_qxfp" name="act">ȡ��</logic:equal>��λ����</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>���Ҵ�λ��ɫ˵��:
				<font color="green"><B>��ǰ������Χ�����Ҵ�λ</B></font>;
				<font color="red"><B>�ǵ�ǰ������Χ�����Ҵ�λ</B></font>;
				<font color="black"><B>δ��������Ҵ�λ</B></font>��<br>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/gyglnew_cwfpgl">			
			<!-- ������ -->
			<!-- ������� -->
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx }"/>
			<input type="hidden" name="hidden_nj" id="hidden_nj" value="<bean:write name="rs" property="nj"/>"/>
			<input type="hidden" name="hidden_xydm" id="hidden_xydm" value="<bean:write name="rs" property="xydm"/>"/>
			<input type="hidden" name="hidden_xb" id="hidden_xb" value="<bean:write name="rs" property="xb"/>"/>
			<input type="hidden" name="hidden_lddm" id="hidden_lddm" value="<bean:write name="rs" property="lddm"/>"/>
			<input type="hidden" name="act" value="${act}"/>
			<!-- ���չʾ -->
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="resultSet" name="resultSet" value="${resultSet }" />
			<input type="hidden" id="cwfpdx" name="cwfpdx" value="${cwfpdx }" />
			<input type="hidden" name="hidden_bjdm" id="hidden_bjdm" value="<bean:write name="rs" property="bjdm"/>"/>
			<input type="hidden" name="hidden_zydm" id="hidden_zydm" value="<bean:write name="rs" property="zydm"/>"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="cxlddm" value="${rs.lddm }"/>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="to_fp" name="act">
							<!-- ������� -->
							<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('save');return false;" class="btn_shtg"> ������� </a></li>
							<li><a href="#" id="btn_xg" onclick="pageqh('to_qxfp');return false;" class="btn_sx"> ת��ȡ��������� </a></li>
						</logic:equal>
						<logic:equal value="to_qxfp" name="act">
							<!-- ȡ������ -->
							<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('qxfp');return false;" class="btn_shbtg">ȡ������</a></li>			
							<li><a href="#" id="btn_xg" onclick="pageqh('to_fp');return false;" class="btn_sx"> ת������������� </a></li>
						</logic:equal>
						<li><a href="gyglnew_cwfpgl_cwfp.do" id="btn_fh" class="btn_fh">���� </a></li>	
					</ul>
				</div>
				<!-- ��ť end-->					
				<!-- �������� -->
<%--				<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
				<!-- �������� end-->				
				<div class="searchtab">
						
					<!-- ��ǰ������Χ -->
					<div id="dqczfw_all">
						<h3 class="datetitle_01">
							<span> ��ǰ������Χ��</span>
						</h3>
						<table width="100%" id="searchTab" style="border:0;">
							<tr>
								<th style="width:80px;">�꼶</th>
								<td>
									<html:select name="rs" property="nj" styleId="nj" style="width:200px;" onchange="initZyList();initBjList();">
										<html:optionsCollection name="njList" label="nj" value="nj"/>
									</html:select>
								</td>						
								<th>¥������</th>
								<td>
									<html:select name="rs" property="lddm" styleId="lddm" style="width:150px;">
										<html:option value="">--��ѡ��--</html:option>
										<html:optionsCollection name="ldlist" label="ldmc" value="lddm"/>
									</html:select>
								</td>	
							</tr>
							<tr>
								<th style="width:80px;"><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select name="rs" property="xydm" style="width:200px;" styleId="xy" onchange="initZyList();initLdList();">
										<html:optionsCollection name="xyList" label="xymc" value="xydm"/>
									</html:select>
								</td>
								<th>����״̬</th>
								<td>
									<html:select property="cwzt" styleId="cwzt" style="width:150px;" onchange="initLdList();">
										<html:option value=""></html:option>
										<html:option value="δ����">δ����</html:option>
										<html:option value="�ѷ���">�ѷ���</html:option>
									</html:select>
								</td>
							</tr>
							<tr >
								<td colspan="2" style="padding: 0px 3px 0px 5px;">
									<table style="border:0;">											
										<tr id="zytr">
											<th style="border:0;width:80px;padding: 0px 5px 0px 0px;">רҵ</th>
											<td style="border:0;">
												<html:select name="rs" property="zydm" styleId="zy" style="width:200px;" onchange="initBjList();initLdList();">
													<% String userType=(String)request.getAttribute("userType"); %>
													<% if(!"xy".equalsIgnoreCase(userType)){ %>
														<html:option value="">--��ѡ��--</html:option>
													<% } %>
													<html:optionsCollection name="zyList" label="zymc" value="zydm"/>
												</html:select>
											</td>		
										</tr>
									</table>
								</td>				
								<th>�Ա�</th>
								<td >									
							        <div class="xl" style="float:left">
							            <div class="imitation_xl" style="padding-top:0px;">
							                <ul style="width:65px;">
							                    <li><a href="javascript:;" name="boy">��</a></li>
							                    <li><a href="javascript:;" name="gril">Ů</a></li>
							                </ul>
							                <div style="width:65px;" name="boy">��</div>
							            </div>
							        </div>
							    </td>
							</tr>
							<tr>
								<td colspan="2" style="padding: 0px 3px 0px 5px;">
									<table style="border:0;">
										<tr id="bjtr">
											<th style="border:0;width:80px;padding: 0px 5px 0px 0px;">�༶</th>
											<td style="border:0;">
												<html:select name="rs" property="bjdm" style="width:200px;" styleId="bj" >
													<html:option value="">--��ѡ��--</html:option>
													<html:optionsCollection  name="bjList" label="bjmc" value="bjdm"/>
												</html:select>
											</td>		
										</tr> 
									</table>
								</td>
								<td></td>													
								<td >				
									<div>
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('showNews.do')">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</table>
					</div>					
				</div>
			</div>
			<!-- ����ͳ�� -->
			<div class="regform" style="padding-top:0px;">
			<div class="regcon">
				<h3 class="regcon_nav" title="��� �鿴 /����">
					<div style="float:left">
						<span style="color:black; font-weight:bold; font-size:12px;">�ѷ���ͳ��</span>		
						<span style="color:green;padding-left:20px;" class="fontTk"> ������:<font color="red">${xynj_tjxx.num }</font>��</span>;
				        <span style="color:green;padding-left:20px;" class="fontTk">�ѷ��䴲λ��:<font color="red">${xynj_tjxx.cws }</font>��</span>;
				        <span style="color:green" class="fontTk">������:<font color="red">${xynj_tjxx.sumqsh }</font>��</span>;
				        <span style="color:green" class="fontTk">¥����:<font color="red">${xynj_tjxx.sumlddm }</font>��</span>;
					</div>
					<div class="floatright"></div>
				</h3>
			</div>
				<div id="fptj" style="padding-bottom:10px;">
					<logic:notEmpty name="xynjxbld_tjxx">
					<table id="ldtj" class="dateline" width="98%" >
						<thead>
						<tr>
							<td>¥��</td>
							<td>�ɷ��䴲λ����</td>
							<td>�ѷ��䴲λ����</td>
							<td>�մ�λ��</td>
							<td>����ǰ������Χ����λ��</td>
						</tr>
						</thead>
						<% String lddm=((HashMap<String,String>)request.getAttribute("rs")).get("lddm");//��ǰѡ�е�¥������ %>
						<logic:iterate id="s" name="xynjxbld_tjxx">
						<tr>
							<%if(((HashMap<String,String>)s).get("lddm").equals(lddm)){ %>
								<td style="color: red"><bean:write name="s" property="ldmc"/></td>
								<td style="color: red"><bean:write name="s" property="cws"/></td>
								<td style="color: red"><bean:write name="s" property="yfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="wfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="xycws"/></td>
							<%}else{ %>
								<td><bean:write name="s" property="ldmc"/></td>
								<td><bean:write name="s" property="cws"/></td>
								<td><bean:write name="s" property="yfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="wfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="xycws"/></td>
							<%} %>
						</tr>
						</logic:iterate>
					</table>
					</logic:notEmpty>
					<logic:empty name="xynjxbld_tjxx">
						<span class="fontTk"> û���ѷ�����Ϣ��</span>			
					</logic:empty>
				</div>
			</div>			
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="ldjbxx">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
					</span>
					<div class="xl" style="float:right"><li id="dyn_show_qs_cw_count"></li></div>
				</h3>
				<div class="con_overlfow" style="min-height: 500px;overflow-x:auto;overflow-y:auto;">
								
					<!--���� -->
					<% 
					    String act=(String)request.getAttribute("act");//�������/ȡ������[to_fp,to_qxfp]
					    String resultSet=(String)request.getAttribute("resultSet");//�����[xyli,zyli,bjli]
					    String cwfpdx=(String)request.getAttribute("cwfpdx");//��λ�������[xydm,zydm,bjdm]
						String nj=((HashMap<String,String>)request.getAttribute("rs")).get("nj");//��ǰѡ�е��꼶
					    String xydm=((HashMap<String,String>)request.getAttribute("rs")).get("xydm");//��ǰѡ���ѧԺ
						String zydm=((HashMap<String,String>)request.getAttribute("rs")).get("zydm");//��ǰѡ���רҵ
						String bjdm=((HashMap<String,String>)request.getAttribute("rs")).get("bjdm");//��ǰѡ��İ༶
						List<HashMap<String,String>> ldlcqscwxxb=(List<HashMap<String,String>>)request.getAttribute("ldlcqscwxxb");
						
						int index=0; //��¼ѭ����λ������
						String curr_ch="";//��ǰ���
						String curr_qsh="";//��ǰ���Һ�
						String curr_qsxy="";//��ǰ��������ѧԺ
						String curr_qsnj="";//��ǰ���������꼶
						HashMap<String,String> cwxx=null;//��λ��Ϣ
						boolean qs_top_mark=true;//���ҿ�ʼ��ǣ�������ʾ����ͷ��
						boolean qs_cw_end_mark=true;//���Ҵ�λĩβ���
						boolean ld_cw_end_mark=false;//¥����λ�Ƿ�ѭ�����
					%>
				<!-- ¥����ͷ -->
				<div class="demo_hq_list1">
					<logic:notEmpty name="ldjbxx">
						<div class="demo_hq_list1_nav">
							<a class="cur" href="#" style="min-width:110px;" ondblclick="modi(this);">
							<input type="checkbox" name="checkbox_lddm" onclick="selectAll(this,'ld');" 
									value="<bean:write name="ldjbxx" property="lddm"/>"/>
									<bean:write name="ldjbxx" property="ldmc" /></a>
						</div>
						<logic:empty name="ldlcxx">
							<div style="padding-top:10px;">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
							</div>
						</logic:empty>
						<ul>
				        	<logic:iterate name="ldlcxx" id="s">
			        		<li>
			        			<%curr_ch=((HashMap<String,String>)s).get("ch");//�Ե�ǰѭ���Ĳ������ʱ��Ǹ�ֵ
  									qs_top_mark=true;%>
			            		<div class="con0">
			            			<input type="checkbox" name="checkbox_ch" id="checkbox_ch" onclick="selectAll(this,'lc');" 
	  									value="<bean:write name="s" property="ch"/>" /></div>
				        		
			            		<div class="con1"><span class="hl"><bean:write name="s" property="chmc"/>��</span>
			            			<br />������/��λ����<bean:write name="s" property="qss"/>/<span class="num"><bean:write name="s" property="cws"/></span></div>
			            		<div class="con2">
				                	<div class="length">
				                    	<div class="length1"></div>
				                    </div>
				                    <div class="con2_2"><p class="floatleft"><span class="zfx1"></span>�ѷ��䵱ǰ������Χ��λ��<span class="num1">
										<% if("xyli".equalsIgnoreCase(resultSet)) {%>
											<bean:write name="s" property="bxy_cws"/>
										
										<% }else if("zyli".equalsIgnoreCase(resultSet)) {%>
											<bean:write name="s" property="bzy_cws"/>
										
										<% }else if("bjli".equalsIgnoreCase(resultSet)) {%>
											<bean:write name="s" property="bbj_cws"/>
										<% }%>
									</span></p>
									<p class="floatright"><span class="zfx2"></span>�մ�λ��&nbsp;<span class="num2"><bean:write name="s" property="wfp_cws"/></span></p></div>
				                	</div>
				                <div class="con3" title="���չ��"></div>
				                <div>
				               		<table class="dateline" style="display:none;" border="1px;" width="100%">
				               			<!--  <thead>
				               				<tr>
				               					<th>���Ҵ�λ��Ϣ��</th>
				               				</tr>
				               			</thead>-->
				               			<tbody>
											<tr>
											 	<td>
													<table width="98%" style="border: 0px;">
														<tr>
														<%for(int i=0;i<10000&&index<ldlcqscwxxb.size();i++){//����ѭ�����ң����ݴ�λ�Ĳ�Ž����жϸô�λ�Ƿ��Ǹò�Ĵ�λ�������ڲ�ѯʱ������
															cwxx=ldlcqscwxxb.get(index);
															if(curr_ch.equals(cwxx.get("ch"))){//��λ��ź͵�ǰ��Ŷ�Ӧʱ�ſ�ѭ��
																if(curr_qsh.equals(cwxx.get("qsh"))){//���ҺŶ�Ӧʱ�����д�λ��ѭ��
																	if(qs_top_mark){//��������ҿ�ʼѭ������Ҫ���������ͷ��
																		%>
																			<td name="qsxx" width="20%">
																				<input type="hidden" name="qsh_xb" value="<%=cwxx.get("qsxb") %>"/>
																				<input type="checkbox" name="checkbox_qsh" id="checkbox_qsh" onclick="selectAll(this,'qs');" 
																					<%if ("xyli".equalsIgnoreCase(resultSet)) {%>
																						<%=(("to_fp".equals(act)&&cwxx.get("qsxydm")!=null)||("to_qxfp".equals(act)&&cwxx.get("qsxydm")==null))?"disabled='disabled'":"" %>
																					<% }%>
																				    value="<%=cwxx.get("qsh")%>"/>
																				<%
																					if(xydm.equals(cwxx.get("qsxydm"))&&nj.equals(cwxx.get("qsnj"))){//���Ӧ
																						%><font color="green"><%
																					}else if(cwxx.get("qsxydm")==null){//δ����
																						%><font color=""><%
																					}else{//����Ӧ
																						%><font color="red"><%
																					}
																				%>
																				<%=cwxx.get("qsh") %>
																				[<%=cwxx.get("qsxb") %>]
																				<%=cwxx.get("qsnj")==null?"":"("+cwxx.get("qsnj")+")" %><%--�꼶 --%>
																				<div align="center"><%=cwxx.get("qsxymc")==null?"":cwxx.get("qsxymc") %></div><%--ѧԺ --%>
																				</font>
																			</td><td name="cwxx"><table><tr>
																		<%
																		  qs_cw_end_mark=true;
																	}
																	%>
																			<td width="90px">
																					<!-- �жϲ�ͬ�������Ӧ��checkbox����ʾ -->
																				<input type="checkbox" name="checkbox_cwh" id="checkbox_cwh" onclick="selectAll(this,'cw');" 
																					<% if("xyli".equalsIgnoreCase(resultSet)) { %>
																						<%=(("to_fp".equals(act)&&cwxx.get("xydm")!=null)||cwxx.get("xh")!=null||("to_qxfp".equals(act)&&cwxx.get("xydm")==null))?"disabled='disabled'":""%> 
																					<% }else if("zyli".equalsIgnoreCase(resultSet)) {%>
																						<%=(("to_fp".equals(act)&&cwxx.get("zydm")!=null)||cwxx.get("xh")!=null||("to_qxfp".equals(act)&&cwxx.get("zydm")==null))?"disabled='disabled'":""%> 
																					<% }else if("bjli".equalsIgnoreCase(resultSet)) {%>
																						<%=(("to_fp".equals(act)&&cwxx.get("bjdm")!=null)||cwxx.get("xh")!=null||("to_qxfp".equals(act)&&cwxx.get("bjdm")==null))?"disabled='disabled'":""%> 
																					<% } %>
																																			
																				value="<%=cwxx.get("qsh")%>_<%=cwxx.get("cwh")%>"/>
																				<%
																					//curr_qsxy!=null&&curr_qsxy.equals(cwxx.get("xydm"))
																					//&&curr_qsnj!=null&&curr_qsnj.equals(cwxx.get("nj"))
																					//====�ж��Ƿ�ѧԺSTART=====
																					if("xyli".equalsIgnoreCase(resultSet)) {
																						if(xydm.equals(cwxx.get("xydm"))&&nj.equals(cwxx.get("nj"))){//��λѧԺ������ѧԺ��Ӧ
																							%><font color="green" ><%=cwxx.get("cwh") %><%
																						}else if(cwxx.get("xydm")==null){//δ����
																							%><font color="" ><%=cwxx.get("cwh") %><%
																						}else{//����ѧԺ
																							%><font color="red" >
																							<%=cwxx.get("cwh") %>
																							<%
																						}
																					
																						if((curr_qsxy!=null&&!curr_qsxy.equals(cwxx.get("xydm")))
																						   		||(curr_qsnj!=null&&!curr_qsnj.equals(cwxx.get("nj")))){
																							%>
																							<%=cwxx.get("nj")==null?"":"("+cwxx.get("nj")+")"%><br>
																							<%=cwxx.get("xymc")==null?"":cwxx.get("xymc")%>
																							<% } %>
																					<% //====�ж��Ƿ�ѧԺEND===== 
																						//====�ж��Ƿ�רҵSTART===== 
																					}else if("zyli".equalsIgnoreCase(resultSet)) { 
																					
																						if(zydm.equals(cwxx.get("zydm"))){//��λרҵ��Ӧ
																							%><font color="green" ><%=cwxx.get("cwh") %><%
																						}else if(cwxx.get("zydm")==null){//δ����
																							%><font color="" ><b><%=cwxx.get("cwh") %></b><%
																						}else{//����ѧԺ
																							%><font color="red" >
																							<%=cwxx.get("cwh") %>
																					<% }%>																
																					
																					<% //====�ж��Ƿ�רҵEND===== 
																						//====�ж��Ƿ�༶START===== 
																					}else if("bjli".equalsIgnoreCase(resultSet)) { 
																						
																						if(bjdm.equals(cwxx.get("bjdm"))){//��λѧԺ������ѧԺ��Ӧ
																								%><font color="green" ><%=cwxx.get("cwh") %><%
																							}else if(cwxx.get("bjdm")==null){//δ����
																								%><font color="" ><b><%=cwxx.get("cwh") %></b><%
																							}else{//����ѧԺ
																								%><font color="red" >
																								<%=cwxx.get("cwh") %>
																						<% }%>
																						<%=cwxx.get("cwbjmc")==null?"":cwxx.get("cwbjmc")%>
																					
																					<% //====�ж��Ƿ�༶END===== 
																						} %>
																				
																				</font><br><%=cwxx.get("xm")==null?"":cwxx.get("xm") %>
																			</td>
																		<%
																	qs_top_mark=false;
																	index++;//��λ���ʱ����λ����Ϣ����++
																}else{//���ҺŲ���Ӧ�Ǿͽ��и�ֵ��������һ�н�����һ������
																	if(!"".equals(curr_qsh)&& qs_cw_end_mark){
																		%></tr></table></td><%
																	}
																	curr_qsh=cwxx.get("qsh");
																	curr_qsxy=cwxx.get("qsxydm");
																	curr_qsnj=cwxx.get("qsnj");
																	qs_top_mark=true;
																	%></tr><tr><%
																}
															}else{//��Ų���Ӧ������������һ��¥��
																if(!"".equals(curr_qsh)&& qs_cw_end_mark){
																	%></tr></table></td><%
																}
																qs_cw_end_mark=false;
																break;
															}
														} %>
														
														<%if(ldlcqscwxxb.size()>0&&index==ldlcqscwxxb.size()&&!ld_cw_end_mark){//���ڴ�λ��ѭ����ɣ���¥�㻹�е����
															%></tr></table></td><%
															ld_cw_end_mark=true;
														} %>
														</tr>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
				                </div>
					            </li>
				        	</logic:iterate>
						</ul>
					</logic:notEmpty>
				</div>
				
				</div>
			</div>
			<!-- ��ѯ��� end-->
			
		</html:form>
		<logic:present name="back">
			<script>
			<logic:equal value="true" name="back">
				alertInfo("�����ɹ���");
			</logic:equal>
			<logic:equal value="false" name="back">
				alertInfo("����ʧ�ܣ�");
			</logic:equal>
			</script>
		</logic:present>
	</body>
</html>