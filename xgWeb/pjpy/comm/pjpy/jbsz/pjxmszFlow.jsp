<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//�����������
		function saveXmsz(){
<%--			changePjzq();--%>
			if($("xmdm").value==""){
				alert("��Ŀ���벻��Ϊ��!");
				return false;
			}
			if($("xmmc").value==""){
				alert("��Ŀ���Ʋ���Ϊ��!");
				return false;
			}
			if($("xmlx").value==""){
				alert("��Ŀ���Ͳ���Ϊ��!");
				return false;
			}
			if($("xmxz").value==""){
				alert("��Ŀ���ʲ���Ϊ��!");
				return false;
			}
			if($("xmfw").value==""){
				alert("��Ŀ��Χ����Ϊ��!");
				return false;
			}
			
			
			var b=saveUpdateCheck();
			if(!b){return false;}
			refreshForm("/xgxt/pjpyXmsz.do?method=pjxmszOne&doType=save");
		}

		//�����������
		function saveXmszFlow(){
<%--			changePjzq();--%>
			if($("xmdm").value==""){
				alert("��Ŀ���벻��Ϊ��!");
				return false;
			}
			if($("xmmc").value==""){
				alert("��Ŀ���Ʋ���Ϊ��!");
				return false;
			}
			if($("xmlx").value==""){
				alert("��Ŀ���Ͳ���Ϊ��!");
				return false;
			}
			if($("xmxz").value==""){
				alert("��Ŀ���ʲ���Ϊ��!");
				return false;
			}
			if($("xmfw").value==""){
				alert("��Ŀ��Χ����Ϊ��!");
				return false;
			}
			
			
			var b=saveUpdateCheck();
			if(!b){return false;}
			var sfqy = $("save_sfqy").value;
			if(sfqy=="��"){
				refreshForm("/xgxt/pjpyXmsz.do?method=pjxmszOne&doType=save");
				window.close();
				window.dialogArguments.window.alertInfo("����ɹ�");
			}else{
			   confirmInfo("������Ŀ���������,�������ò��ɷ���,�����Ҫ�޸��뵽������Ŀ������ֱ���޸�,�Ƿ�Ҫ������һ��������?",mbDownLoad);
			}
		}
		function mbDownLoad(tag){
			if(tag=='ok'){
				refreshForm("/xgxt/pjpyXmsz.do?method=pjxmszFlow&doType=save");
			}
		}
		
		//�޸Ļ�������
		function updateXmsz(){
<%--			changePjzq();--%>
			if($("xmdm").value==""){
				alert("��Ŀ���벻��Ϊ��!");
				return false;
			}
			if($("xmmc").value==""){
				alert("��Ŀ���Ʋ���Ϊ��!");
				return false;
			}
			if($("xmlx").value==""){
				alert("��Ŀ���Ͳ���Ϊ��!");
				return false;
			}
			if($("xmxz").value==""){
				alert("��Ŀ���ʲ���Ϊ��!");
				return false;
			}
			if($("xmfw").value==""){
				alert("��Ŀ��Χ����Ϊ��!");
				return false;
			}
			
			
			if($("save_sfsh_no").checked){
				$("save_lcid").value="";
			}
			
			var b=saveUpdateCheck();
			if(!b){return false;}
			refreshForm("/xgxt/pjpyXmsz.do?method=pjxmszUpdate&doType=save");
		}
		
		//�����޸���֤--�÷���ֻ��֤ �������ú��������
		function saveUpdateCheck(){
			var b=false;
			if($('save_rssz_yes').checked){//��������
				var kzfw=document.getElementsByName('save_kzfw');
				for(var i=0;i<kzfw.length;i++){
					if(kzfw[i].checked){
						b=true;
						break;
					}
				}
				if(!b){
					alertInfo("���Ʒ�Χ����ѡ��");
					return false;
				}
				
			}
			b=false;
			if($('save_sfsh_yes').checked){//�Ƿ����
				var lcid=document.getElementsByName('lcid');
				for(var i=0;i<lcid.length;i++){
					if(lcid[i].checked){
						b=true;
						break;
					}
				}
				if(!b){
					alertInfo("������̱���ѡ��");
					return false;
				}
			}
			return true;;
		}
		
		
		
		//�漰���
		function changeSjje(yesNo){
			$("xmjeTr").style.display=yesNo;
			var rownum = $("t_pjtjk_t").rows.length;
			if(yesNo==""){
				$("t_pjtjk_t").deleteRow(rownum-2);
			}
			if(yesNo=="none"){
				$("t_pjtjk_t").insertRow(rownum-2).setAttribute("height",23);
			}

			
		}
		
		function changeRssz(yesNo){
			//$("kzjbTr").style.display=yesNo;
			$("kzfwTr").style.display=yesNo;
			$("rssxTr").style.display=yesNo;
			var rownum = $("t_pjtjk_f").rows.length;
			if(""==yesNo){
				$("t_pjtjk_f").deleteRow(rownum-2);
				$("t_pjtjk_f").deleteRow(rownum-3);
			}
			if("none"==yesNo){
				$("t_pjtjk_f").insertRow(rownum-1).setAttribute("height",23);
				$("t_pjtjk_f").insertRow(rownum-2).setAttribute("height",23);
			}
		}
		
		function changeSfsh(yesNo){
			$("shlcTr").style.display=yesNo;
			if('none' == yesNo){
				var lcs = document.getElementsByName('save_lcid');
				lcs[0].checked = "checked";

				var rownum = $("t_pjtjk_s").rows.length;
				$("t_pjtjk_s").insertRow(rownum-1).setAttribute("height",23);
				$("t_pjtjk_s").insertRow(rownum-2).setAttribute("height",23);
				$("t_pjtjk_s").insertRow(rownum-3).setAttribute("height",23);
			}
			if(yesNo==""){
				var rownum = $("t_pjtjk_s").rows.length;
				$("t_pjtjk_s").deleteRow(rownum-2);
				$("t_pjtjk_s").deleteRow(rownum-3);
				$("t_pjtjk_s").deleteRow(rownum-4);
			}
			
			if('none' == yesNo){
				var lcs = document.getElementsByName('save_lcid');
				lcs[0].checked = "checked";
				$("save_lcid").value="";
			}else{
				if($("lcid_0")){
					$("save_lcid").value=$("lcid_0").value;
				}
			}
			//$("rsszTr").style.display=yesNo;
		}

		function init(){
			var sjje='${rs.save_sjje}';
			if(sjje=="��"){
				$("xmjeTr").style.display="";
			}
		
			var kzfw='${rs.save_kzfw}';
			var kzfw_rd=document.getElementsByName("save_kzfw");
			if(kzfw_rd){
				for(var i=0;i<kzfw_rd.length;i++){
					if(kzfw_rd[i].value==kzfw){
						kzfw_rd[i].checked="checked";
						break;
					}
				} 
			}
			var lcid='${rs.save_lcid}';
			var lcid_rd=document.getElementsByName("lcid");
			if(lcid_rd){
				for(var i=0;i<lcid_rd.length;i++){
					if(lcid_rd[i].value==lcid){
						lcid_rd[i].checked="checked";
						break;
					}
				} 
			}
			
			
			var rssz='${rs.save_rssz}';
			var sfsh='${rs.save_sfsh}';
			if(rssz=="��"){
				$("save_rssz_yes").checked="checked";
				$("kzfwTr").style.display="";
				$("rssxTr").style.display="";
			}
			if(sfsh=="��"){
				$("save_sfsh_yes").checked="checked";
				$("shlcTr").style.display="";
			}
		}
		
		function showTsDiv(id){
			if($(id)){
				$(id).style.display = "";
			}
		}
		
		function hiddTsDiv(id){
			if($(id)){
				$(id).style.display = "none";
			}
		}
		
<%--		function changePjzq(){--%>
<%--			var save_sqzq_xn=document.getElementById("save_sqzq_xn");--%>
<%--			var save_sqzq_xq=document.getElementById("save_sqzq_xq");--%>
<%--			var save_sqzq_nd=document.getElementById("save_sqzq_nd");--%>
<%--			if(save_sqzq_xn.checked){--%>
<%--				$("save_pjxq").value="��";--%>
<%--				$("save_pjnd").value="��";--%>
<%--			}else if(save_sqzq_xq.checked){--%>
<%--				$("save_pjxn").value=$("pjxn").value;--%>
<%--				$("save_pjxq").value=$("pjxq").value;--%>
<%--				$("save_pjnd").value="��";--%>
<%--			}else if(save_sqzq_nd.checked){--%>
<%--				$("save_pjxn").value=$("pjxn").value;--%>
<%--				$("save_pjnd").value=$("pjnd").value;--%>
<%--				$("save_pjxq").value="��";--%>
<%--			}--%>
<%--		}--%>

         //��һ���Ŀ���
          //��0��
         function pjtjsz0(){
        	 if($("xmdm").value==""){
 				alertInfo("��Ŀ���벻��Ϊ��!");
 				return false;
 			}
 			if($("xmmc").value==""){
 				alertInfo("��Ŀ���Ʋ���Ϊ��!");
 				return false;
 			}
 			if($("xmlx").value==""){
 				alertInfo("��Ŀ���Ͳ���Ϊ��!");
 				return false;
 			}
 			if($("xmxz").value==""){
 				alertInfo("��Ŀ���ʲ���Ϊ��!");
 				return false;
 			}
 			if($("xmfw").value==""){
 				alertInfo("��Ŀ��Χ����Ϊ��!");
 				return false;
 			}
 			 document.getElementById("first_li").className='current';	
 			 document.getElementById("second_li").className = 'current-next';
 			 document.getElementById("third_li").className = 'current-next';	
 			 document.getElementById("forth_li").className = 'last';
        	 document.getElementById("pjtjk_o").style.display="";
        	 document.getElementById("pjtjk_t").style.display="none";
        	 document.getElementById("pjtjk_f").style.display="none";
        	 document.getElementById("pjtjk_s").style.display="none";
         }
         //��һ��
         function pjtjsz1(){
             var flag = false;
        	 var url = "/xgxt/pjpyXmsz.do?method=xmdmExist";
        	 jQuery.ajax({
        		   type: "POST",
        		   async: false,
        		   url: url,
        		   data: "xmdm="+$("xmdm").value,
        		   success: function(msg){
  	        		 if(msg == "true"){
  							alertInfo("��Ŀ�����Ѵ���,��������д��");
  							return false;
  	        		 }else{
                            flag = true;
  	  	             }
        		   }
        	     });
            if(flag){
	        	if($("xmdm").value==""){
	        		alertInfo("��Ŀ���벻��Ϊ��!");
	 				return false;
	 			}
	 			var b=checkIsChinese($("xmdm").value);
	 			if(!b){return false;}

	 			var c = checkIsEn($('ywmc').value);
	 			if(!c){return false;}
	 			
	 			if($("xmmc").value==""){
	 				alertInfo("��Ŀ���Ʋ���Ϊ��!");
	 				return false;
	 			}
	 			if($("xmlx").value==""){
	 				alertInfo("��Ŀ���Ͳ���Ϊ��!");
	 				return false;
	 			}
	 			if($("xmxz").value==""){
	 				alertInfo("��Ŀ���ʲ���Ϊ��!");
	 				return false;
	 			}
	 			if($("xmfw").value==""){
	 				alertInfo("��Ŀ��Χ����Ϊ��!");
	 				return false;
	 			}
	 			 document.getElementById("first_li").className='done current-prev';	
	 			 document.getElementById("second_li").className = 'current';
	 			 document.getElementById("third_li").className = 'current-next';	
	 			 document.getElementById("forth_li").className = 'last';
	        	 document.getElementById("pjtjk_o").style.display="none";
	        	 document.getElementById("pjtjk_t").style.display="";
	        	 document.getElementById("pjtjk_f").style.display="none";
	        	 document.getElementById("pjtjk_s").style.display="none";
            }
         }
         //�ڶ���
         function pjtjsz2(){
        	 document.getElementById("first_li").className='done';	
 			 document.getElementById("second_li").className = 'done current-prev';
 			 document.getElementById("third_li").className = 'current';	
 			 document.getElementById("forth_li").className = 'last';
        	 document.getElementById("pjtjk_o").style.display="none";
        	 document.getElementById("pjtjk_t").style.display="none";
        	 document.getElementById("pjtjk_f").style.display="";
        	 document.getElementById("pjtjk_s").style.display="none";
         }
        //������
         function pjtjsz3(){
        	 var b=saveUpdateCheck();
 			 if(!b){return false;}
 			 document.getElementById("first_li").className='done';	
			 document.getElementById("second_li").className = 'done';
			 document.getElementById("third_li").className = 'done current-prev';
			 document.getElementById("forth_li").className = 'current';
        	 document.getElementById("pjtjk_o").style.display="none";
        	 document.getElementById("pjtjk_t").style.display="none";
        	 document.getElementById("pjtjk_f").style.display="none";
        	 document.getElementById("pjtjk_s").style.display="";
         }

         function   checkIsChinese(str) 
         { 
                 //���ֵΪ�գ�ͨ��У�� 
                 if   (str=="") 
                         return   true; 
                 var pattern = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
                 if   (!pattern.test(str)) 
                         return   true; 
                 else {
                         alertInfo("��Ŀ�����벻Ҫ�������ĺ���!");
                         $('xmdm').focus();
                         return   false; 
                 }
         }//~~~

         function checkIsEn(str)
         {
           var pattern = /[^a-zA-Z]/g;
           if   (!pattern.test(str)) 
               return   true; 
           else {
               alertInfo("Ӣ������������Ӣ��!!");
               return   false; 
       }
         }
		</script>
	</head>
	<body onload="init()" >
	<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��������-��������-������Ŀ���� </a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		
		 <!-- ���̰�ť���� -->
		<div class="flow-steps">
		     <ol class="num3">
			    <li class="current" id="first_li" style ="width:23%"><span class="first">1. ��Ŀ��Ϣ����</span></li>
			    <li class="current-next" id="second_li" style ="width:23%"><span>2. ��Ŀ�������</span></li>
			     <li class="current-next" id="third_li" style ="width:23%"><span>3. ��Ŀ��������</span></li>
			    <li class="last" id="forth_li" style ="width:23%"><span>4. ��Ŀ�������</span></li>
		    </ol>
      </div>
				
		 <!-- ��ʾ��Ϣ start-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
				�������ڣ�����ѧ��(${myForm.pjxn })&nbsp;&nbsp;����ѧ��(${myForm.pjxqmc })&nbsp;&nbsp;�������(${myForm.pjnd })</br>
				1.��Ŀ��������<font color="blue">�����Ľ���</font>��<br />
				2.��ѧ����������ڣ����۲�ּ���������<font color="blue">�޹�</font>��<br />
				3.ֻ�н�ѧ��<font color="blue">����</font>ʱ�����ܶԸý�ѧ�������һ�����ü�ѧ�����жԽ�ѧ������롣<br />
				4.<font color="blue">��Ŀ���</font>��ѧ����øý�ѧ��ʱ�õ�������<br />
				5.<font color="blue">��������</font>:ȫУ�ܹ��ܻ�øý�ѧ������������������й�����Ӱ�����÷�ʽ����<br />
				6.<font color="blue">���Ƽ���</font>:��ѧ�����ʱ,��һ������������������ã���: �����ó�<bean:message key="lable.xb" />���������ʱ��ֻ����<bean:message key="lable.xb" />���������ޣ���<br />
				7.<font color="blue">��������</font>:�ý�ѧ���Ƿ�Ҫ����������������������á�<br />
				8.<font color="blue">�����������</font>�ڣ���ϵͳά��-��������ά����ģ����ά����
				</span>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->				
		<!-- ���� end-->
		<html:form action="/pjpyXmsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab">
			<div style="display:none">
			<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								<font color="red">*</font>����ѧ��
							</th>
							<td align="left" width="70%" colspan="3">
								<html:select property="pjxn" styleId="pjxn"  disabled="true" style="width: 150px">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<html:hidden property="save_pjxn" styleId="save_pjxn"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>����ѧ��
							</th>
							<td align="left" colspan="3">
								<html:select property="pjxq" styleId="pjxq" disabled="true" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<html:hidden property="save_pjxq" styleId="save_pjxq" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>�������
							</th>
							<td align="left" colspan="3">
								<html:select property="pjnd" styleId="pjnd"  disabled="true" style="width: 150px">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
								<html:hidden property="save_pjnd" styleId="save_pjnd"/>
							</td>
						</tr>
					</tbody>
					</table>
			    </div>
				<!-- ����ʱ������ -->
				
				<div id="pjtjk_o" style="height:150px">
				<table class="formlist" border="0" align="center" style="width: 100%">
					<!-- �������䷽ʽ -->
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
								<logic:empty name="rs">
									<html:text property="save_xmdm" styleId="xmdm" maxlength="10" value=""  onchange="checkIsChinese(this.value)" onfocus="displayMsgDiv()" onblur="hideMsgDiv()"  />
								</logic:empty>
								<logic:equal name="doType" value="update">
									<input type="text" disabled="disabled" value="${rs.save_xmdm}"/>
									<html:hidden name="rs" property="save_xmdm" styleId="xmdm"/>
								</logic:equal>
								 <div id="msg_div" class="hide">
						              <div class="prompcon">
						                <p>ͬ������������Ŀ���벻���ظ�</p>
						              </div>
					              </div>
							</td>
							<th>
								��ʾ˳��
							</th>
							<td>
								<logic:empty name="rs">
									<html:text property="save_xssx" styleId="xssx" maxlength="2" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_xssx" styleId="xssx" maxlength="2"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
								<logic:empty name="rs">
									<html:text property="save_xmmc" styleId="xmmc" maxlength="25" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_xmmc" styleId="xmmc" maxlength="25"/>
								</logic:notEmpty>
							</td>
							<th>
								Ӣ������
							</th>
							<td>
								<logic:empty name="rs">
									<html:text property="save_ywmc" styleId="ywmc" maxlength="25" onchange="checkIsEn(this.value)" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_ywmc" styleId="ywmc" maxlength="25"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
								<logic:empty name="rs">
									<html:select property="save_xmlx" styleId="xmlx" style="width: 150px">
											<html:option value=""></html:option>
											<html:options collection="xmlxList" property="en"
												labelProperty="cn" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:select name="rs" property="save_xmlx" styleId="xmlx" style="width: 150px">
											<html:option value=""></html:option>
											<html:options collection="xmlxList" property="en"
												labelProperty="cn" />
									</html:select>
								</logic:notEmpty>
							</td>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
								<logic:empty name="rs">
									<html:select property="save_xmxz" styleId="xmxz"
											style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xmxzList" property="dm"
												labelProperty="mc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:select name="rs" property="save_xmxz" styleId="xmxz"
											style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xmxzList" property="dm"
												labelProperty="mc" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ��Χ
							</th>
							<td colspan="3">
								<logic:empty name="rs">
								<html:select property="save_xmfw" styleId="xmfw"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:select name="rs" property="save_xmfw" styleId="xmfw"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								��Ŀ˵��
							</th>
							<td colspan="3" height="92px">
								<logic:empty name="rs">
									<html:textarea rows="6" style="width:98%" property="save_xmsm" onblur="checkLen(this,500)"></html:textarea>
								</logic:empty>
								<logic:notEmpty	name="rs">
									<html:textarea rows="6" style="width:98%" name="rs" property="save_xmsm" onblur="checkLen(this,500)"></html:textarea>
								</logic:notEmpty>
							</td>
						</tr>
						<tr></tr>
					</tbody>
					<tfoot>
					   <tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
										<button type="button" onclick="pjtjsz1()" id="buttonSave">
											��һ��
										</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</table>
					</div>
					
					
					
					<!-- ���������� -->
					<div id="pjtjk_t" style="display:none;height:350px">
					<table class="formlist" border="0" align="center" style="width: 100%" id="t_pjtjk_t">
					<thead>
						<tr>
							<th colspan="4">
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>���뷽ʽ
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_sqfs" id="save_sqfs" value="xssq" checked="checked"/>
									ѧ������
									<input type="radio" name="save_sqfs" id="save_sqfs" value="lssb"/>
									��ʦ�ϱ�
								</logic:empty>
								<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_sqfs" styleId="save_sqfs" value="xssq"/>
										ѧ������
										<html:radio name="rs"  property="save_sqfs" styleId="save_sqfs" value="lssb"/>
										��ʦ�ϱ�
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_sqzq" id="save_sqzq_xn" value="xn" checked="checked"/>
									ѧ��
									<input type="radio" name="save_sqzq" id="save_sqzq_xq" value="xq"/>
									ѧ��
									<input type="radio" name="save_sqzq" id="save_sqzq_nd" value="nd" />
									���
								</logic:empty>
								<logic:notEmpty	name="rs">
									<html:radio name="rs" property="save_sqzq" styleId="save_sqzq_xn" value="xn"  />
									ѧ��
									<html:radio name="rs" property="save_sqzq" styleId="save_sqzq_xq" value="xq" />
									ѧ��
									<html:radio name="rs" property="save_sqzq" styleId="save_sqzq_nd" value="nd" />
									���
<%--									onclick="changePjzq()"--%>
								</logic:notEmpty>
							</td>
						</tr>
						<tr  style="display: none;">
							<th>
								��Ϊǰ������
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_qztj" id="save_qztj"  value="��"/>
									��
									<input type="radio" name="save_qztj" id="save_qztj"  value="��" checked="checked"/>
									��
								</logic:empty>
								<logic:notEmpty	name="rs">
										<html:radio name="rs" property="save_qztj" styleId="save_qztj" value="��" />
										��
										<html:radio name="rs" property="save_qztj" styleId="save_qztj" value="��"/>
										��
								</logic:notEmpty>
							</td>
						</tr>
						<tr style="display: none;">
							<th>
								ʱ�����
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_sjkz" id="save_sjkz" value="��Ҫ"/>
									��Ҫ
									<input type="radio" name="save_sjkz" id="save_sjkz" value="����Ҫ" checked="checked"/>
									����Ҫ
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:radio name="rs" property="save_sjkz" styleId="save_sjkz" value="��Ҫ"/>
									��Ҫ
									<html:radio name="rs" property="save_sjkz" styleId="save_sjkz" value="����Ҫ"/>
									����Ҫ
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ�����
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_sfqy" id="save_sfqy" value="��" checked="checked"/>
									��
									<input type="radio" name="save_sfqy" id="save_sfqy" value="��" />
									��
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:radio name="rs" property="save_sfqy" styleId="save_sfqy" value="��"/>
									��
									<html:radio name="rs" property="save_sfqy" styleId="save_sfqy" value="��"/>
									��
								</logic:notEmpty>
							</td>
						</tr>
						<tr >
							<th>
								�Ƿ��漰���
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="hidden" name="save_sjje" id="save_sjje" value="��"/>
									<input type="radio" property="save_sjje" name="sjje" id="sjje_yes"  onclick="changeSjje('');$('save_sjje').value=this.value" value="��"/>
									��
									<input type="radio" property="save_sjje" name="sjje" id="sjje_no" checked="checked"  onclick="changeSjje('none');$('save_sjje').value=this.value" value="��"/> 
									��
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:radio name="rs" property="save_sjje" styleId="sjje_yes"  onclick="changeSjje('')" value="��"/>
									��
									<html:radio name="rs" property="save_sjje" styleId="sjje_no"  onclick="changeSjje('none')" value="��"/> 
									��
								</logic:notEmpty>
							</td>
						</tr>
						<tr id="xmjeTr" style="display:none">
							<th>
								��Ŀ���
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<html:text property="save_xmje" styleId="xmje" value="" maxlength="10" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_xmje" styleId="xmje" maxlength="10"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
					</tbody>
					<tfoot>
					   <tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
								        <button type="button" onclick="pjtjsz0()" id="buttonSave">
											��һ��
										</button>
										<button type="button" onclick="pjtjsz2()" id="buttonSave">
											��һ��
										</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</table>
					</div>
					<div id="pjtjk_f" style="display:none;height:350px">
					<table class="formlist" border="0" align="center" id="t_pjtjk_f"
					style="width: 100%">
					<!-- ���������� -->
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr id="rsszTr">
							<th>
								��������
							</th>
							<td >
								<input type="radio" name="save_rssz" id="save_rssz_yes" maxlength="5" onclick="changeRssz('')" value="��" checked="checked"/>
								��
								<input type="radio" name="save_rssz" id="save_rssz_no"  maxlength="5" onclick="changeRssz('none')" value="��" />
								��
							</td>
						</tr>
						<tr id="kzjbTr" style="display:none" >
							<th>
								���Ƽ���
							</th>
							<td colspan="3">
								<html:radio property="save_kzjb" styleId="kzjb" value="xn"></html:radio>
								ѧ��
								<html:radio property="save_kzjb" styleId="kzjb" value="xq"></html:radio>
								ѧ��
								<html:radio property="save_kzjb" styleId="kzjb" value="nd"></html:radio>
								���
							</td>
						</tr>
						<tr id="kzfwTr" style="">
							<th>
								���Ʒ�Χ
							</th>
							<td >
							<!----><html:radio property="save_kzfw" styleId="kzfw" value="xy" ></html:radio>
							
							    <!--<input type="radio" name="save_kzfw" id="save_kzfw_xy" maxlength="5"  value="xy" />-->
								<bean:message key="lable.xb" />
								
								<html:radio property="save_kzfw" styleId="kzfw" value="zy"></html:radio>
								
								<!-- <input type="radio" name="save_kzfw" id="save_kzfw" maxlength="5"  value="zy" />-->
								רҵ
								<html:radio property="save_kzfw" styleId="kzfw" value="bj"></html:radio>
								<!-- <input type="radio" name="save_kzfw" id="save_kzfw" maxlength="5"  value="by" />-->
								�༶
								<html:radio property="save_kzfw" styleId="kzfw" value="nj" ></html:radio>
								<!-- <input type="radio" name="save_kzfw" id="save_kzfw" maxlength="5"  value="ny" />-->
								�꼶
							</td>
						</tr>
						<tr id="rssxTr" style="">
							<th>
								��������
							</th>
							<td >
								<logic:empty name="rs">
									<html:text property="save_rssx" onkeydown="return onlyNum(this,10)"
													onmousedown="return onlyNum(this,10)"/>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_rssx" onkeydown="return onlyNum(this,10)"
													onmousedown="return onlyNum(this,10)"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
					</tbody>	
					<tfoot>
					   <tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
								        <button type="button" onclick="pjtjsz1()" id="buttonSave">
											��һ��
										</button>
										<button type="button" onclick="pjtjsz3()" id="buttonSave">
											��һ��
										</button>
									<button type="button" onclick="winClose();" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</table>
					</div>
					
					
			<!-- ���������� -->		
			<div id="pjtjk_s" style="display:none;height:350px">
				<table class="formlist" border="0" align="center" style="width: 100%" id="t_pjtjk_s">
					<thead>
						<tr>
							<th colspan="4">
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��Ҫ���	
							</th>
							<td colspan="3">
								<input type="hidden" name="save_lcid" id="save_lcid" value=''/>
								<logic:empty name="rs">
									<input type="radio" name="save_sfsh" id="save_sfsh_yes" value="��"
										onclick="changeSfsh('')"/>
									��Ҫ
									<input type="radio" name="save_sfsh" id="save_sfsh_no" value="��"
										onclick="changeSfsh('none')" checked="checked"/>
									����Ҫ
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:radio  name="rs" property="save_sfsh" styleId="save_sfsh_yes" value="��"
										onclick="changeSfsh('')"/>
									��Ҫ
									<html:radio  name="rs" property="save_sfsh" styleId="save_sfsh_no" value="��"
										onclick="changeSfsh('none')"/>
									����Ҫ
								</logic:notEmpty>
							</td>
						</tr>
						<tr id="shlcTr" style="display:none">
							<th>
								�������
							</th>
							<td colspan="3">
								<logic:present name="lcList">
									<logic:iterate id="s" name="lcList" indexId="index">
										<logic:equal name="index" value="0">
											<input type="radio" name="lcid" id="lcid_${index}" checked="checked" onclick='$("save_lcid").value=this.value' value="${s.lcid}"/>${s.lcmc } : ${s.gzgw}<br/>
										</logic:equal>
										<logic:notEqual name="index" value="0">
											<input type="radio" name="lcid" id="lcid_${index}"  onclick='$("save_lcid").value=this.value' value="${s.lcid}"/>${s.lcmc } : ${s.gzgw}<br/>
										</logic:notEqual>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
					    <tr style="height: 23px"></tr>
					 	<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
					</tbody>
					<tfoot>
					   <tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
								        <button type="button" onclick="pjtjsz2()" id="buttonSave">
											��һ��
										</button>
										<button type="button" onclick="saveXmszFlow()" id="buttonSave">
											����
										</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</table>
					</div>
					<div>
					</div>
					
			</div>
		</html:form>
		
	</body>
</html>
