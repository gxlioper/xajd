<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
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
					alert("���Ʒ�Χ����ѡ��");
					return false;
				}
				if($('save_rssx').value.trim()==""){
					//alert("�������ޱ������ã�");
					//return false;
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
					alert("������̱���ѡ��");
					return false;
				}
			}
			return true;;
		}
		
		
		
		//�漰���
		function changeSjje(yesNo){
			$("xmjeTr").style.display=yesNo;
			
			if(yesNo == "none"){
				if($("xmje")){
					$("xmje").value= "";
				}
			}
		}
		
		function changeRssz(yesNo){
			//$("kzjbTr").style.display=yesNo;
			$("kzfwTr").style.display=yesNo;
			$("rssxTr").style.display=yesNo;
		}
		
		function changeSfsh(yesNo){
		
			$("shlcTr").style.display=yesNo;
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
			if($("xmjeTr")){
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


		function xmxxEdit(id){
			$(id).style.display = "";
			$('xmdm_td_text').style.display = "";
			$('xmdm_td_bean').style.display = "none";
			$('xmlx_td').style.display = "";
			$('xmlx_td_span').style.display = "none";
			getValue;
		}
		
		function getValue(){
			
			var xmlx=document.getElementsByName("xmlx");
			for(var i=0;i<3;i++){
				if($("xmlx") && $("xmlx").options[i].value==$("xmlx").value){
					if($("xmlx_span")){
						$("xmlx_span").innerHTML =$("xmlx").options[i].text;
					}
					break;
				}
			}
			
		}
		</script>
	</head>
	<body onload="init();getValue()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- ���� end-->

		<html:form action="/pjpyXmsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<logic:notEmpty name="rs">
			<div class="tab">
				<!-- ����ʱ������ -->
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr onclick="showHiddenNr('tb_pjxmsz');" style="cursor: hand">
							<th colspan="4">
								<span>������Ŀ����</span>
							</th>
						</tr>
					</thead>
					<tbody id = "tb_pjxmsz">
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
					<!-- �������䷽ʽ -->
					<thead>
						<tr onclick="">
							<th colspan="4">
								<span><a href="#" onclick="showHiddenNr('tb_xmxx')">��Ŀ��Ϣ</a></span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    <!--  <a href="#" onclick="xmxxEdit('tb_xmxx')">�༭</a>-->
							</th>
						</tr>
					</thead>
					<tbody id="tb_xmxx">
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td id="xmdm_td_text" style="display:none">
								<logic:empty name="rs">
									<html:text property="save_xmdm" styleId="xmdm" maxlength="10" value="" />
								</logic:empty>
								<logic:equal name="doType" value="update">
									<input type="text" disabled="disabled" value="${rs.save_xmdm}"/>
									<html:hidden name="rs" property="save_xmdm" styleId="xmdm"/>
								</logic:equal>
							</td>
							<td id="xmdm_td_bean">
							     <logic:equal name="doType" value="update">
							       <bean:write name="rs" property="xmdm"/>
							     </logic:equal>
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
									<html:text property="save_ywmc" styleId="ywmc" maxlength="25" />
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
							<td id = "xmlx_td"  style="display:none">
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
							<td id="xmlx_td_span"><span  id="xmlx_span"></span></td>
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
							<td colspan="3">
								<logic:empty name="rs">
									<html:textarea rows="6" style="width:98%" property="save_xmsm"></html:textarea>
								</logic:empty>
								<logic:notEmpty	name="rs">
									<html:textarea rows="6" style="width:98%" name="rs" property="save_xmsm"></html:textarea>
								</logic:notEmpty>
							</td>
						</tr>
					</tbody>
					<!-- ���������� -->
					<thead>
						<tr onclick="showHiddenNr('tb_xgsz');">
							<th colspan="4">
								<span>�������</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!--  <a href="#">�༭</a>-->
							</th>
						</tr>
					</thead>
					<tbody id="tb_xgsz">
						<tr style="">
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
						<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('zqts')" onmouseout="hiddTsDiv('zqts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��&nbsp;&nbsp;&nbsp;&nbsp;</font>	
								<font color="blue" id="zqts" style="display : none">��ѧ����������ڣ����۲�ּ����������޹ء�</font>
							</span>
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
									<input type="radio" name="save_sfqy" id="save_sfqy" value="��"/>
									��
									<input type="radio" name="save_sfqy" id="save_sfqy" value="��" checked="checked"/>
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
						<tr>	
							<td align="left" colspan="4">
							<span onmousemove="showTsDiv('qyts')" onmouseout="hiddTsDiv('qyts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��&nbsp;&nbsp;&nbsp;&nbsp;</font>	
								<font color="blue" id="qyts" style="display : none">ֻ�н�ѧ������ʱ�����ܶԸý�ѧ�������һ�����ü�ѧ�����жԽ�ѧ������롣</font>
							</span>
						</td>
						</tr>
						<tr >
							<th>
								�Ƿ��漰���
							</th>
							<td colspan="3">
								<logic:empty name="rs" property="save_sjje">
									<input type="radio" property="save_sjje" name="sjje" id="sjje_yes"  onclick="changeSjje('')" value="��"/>
									��
									<input type="radio" property="save_sjje" name="sjje" id="sjje_no" checked="checked"  onclick="changeSjje('none')" value="��"/> 
									��
								</logic:empty>
								<logic:notEmpty name="rs" property="save_sjje">
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
							<td >
								<logic:empty name="rs">
									<html:text property="save_xmje" styleId="xmje" value="" maxlength="10" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_xmje" styleId="xmje" maxlength="10"/>
								</logic:notEmpty>
							</td>
							<td align="left" colspan="2">
							<span onmousemove="showTsDiv('jets')" onmouseout="hiddTsDiv('jets')">
								<font color="blue">��ʾ��</font>
								<font color="blue" id="jets" style="display : none">
								ѧ����øý�ѧ��ʱ�õ��������
								</font>
							</span>		
						</td>
						</tr>
					</tbody>
					<!-- ���������� -->
					<thead>
						<tr onclick="showHiddenNr('tb_rssz');">
							<th colspan="4">
								<span>��������</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!--<a href="#">�༭</a>-->
							</th>
						</tr>
					</thead>
					<tbody id="tb_rssz">
						<tr id="rsszTr">
							<th>
								��������
							</th>
							<td >
								<input type="radio" name="save_rssz" id="save_rssz_yes" onclick="changeRssz('')" value="��"/>
								��
								<input type="radio" name="save_rssz" id="save_rssz_no"   onclick="changeRssz('none')" value="��" checked="checked"/>
								��
							</td>
							<td align="left" colspan="2">
							<span onmousemove="showTsDiv('rskgts')" onmouseout="hiddTsDiv('rskgts')">
								<font color="blue">��ʾ��</font>
								<font color="blue" id="rskgts" style="display : none">
								�ý�ѧ���Ƿ�Ҫ�����������������������
								</font>
							</span>	
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
						<tr id="kzfwTr" style="display:none">
							<th>
								���Ʒ�Χ
							</th>
							<td >
								<html:radio property="save_kzfw" styleId="kzfw" value="xy"></html:radio>
								<bean:message key="lable.xb" />
								<html:radio property="save_kzfw" styleId="kzfw" value="zy"></html:radio>
								רҵ
								<html:radio property="save_kzfw" styleId="kzfw" value="bj"></html:radio>
								�༶
								<html:radio property="save_kzfw" styleId="kzfw" value="nj"></html:radio>
								�꼶
							</td>
							<td align="left" colspan="2">
							<span onmousemove="showTsDiv('rsfwts')" onmouseout="hiddTsDiv('rsfwts')">
								<font color="blue">��ʾ��</font>
								<font color="blue" id="rsfwts" style="display : none">
								��ѧ�����ʱ,��һ������������������ã���: �����ó�<bean:message key="lable.xb" />���������ʱ��ֻ����<bean:message key="lable.xb" />���������ޣ�
								</font>
							</span>	
							</td>	
						</tr>
						<tr id="rssxTr" style="display:none">
							<th>
								��������
							</th>
							<td >
								<logic:empty name="rs">
									<html:text property="save_rssx" onkeydown="return onlyNum(this,10)"
													onmousedown="return onlyNum(this,10)" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_rssx" onkeydown="return onlyNum(this,10)"
													onmousedown="return onlyNum(this,10)" />
								</logic:notEmpty>
							</td>
							<td align="left" colspan="2">
							<span onmousemove="showTsDiv('rssxts')" onmouseout="hiddTsDiv('rssxts')">
								<font color="blue">��ʾ��</font>
								<font color="blue" id="rssxts" style="display : none">
								   ȫУ�ܹ��ܻ�øý�ѧ������������������й�����Ӱ�����÷�ʽ��
								</font>
							</span>	
							</td>	
						</tr>
					</tbody>					<!-- ���������� -->
					<thead>
						<tr onclick="showHiddenNr('tb_shsz');">
							<th colspan="4">
								<span>�������</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!--<a href="#">�༭</a>-->
							</th>
						</tr>
					</thead>
					<tbody id="tb_shsz">
						<tr>
							<th>
								��Ҫ���	
							</th>
							<td colspan="3">
								<input type="hidden" name="save_lcid" id="save_lcid" value='${rs.lcid }'/>
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
						<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('shlcts')" onmouseout="hiddTsDiv('shlcts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��&nbsp;&nbsp;&nbsp;&nbsp;</font>	
								<font color="blue" id="shlcts" style="display : none">������������ڣ���ϵͳά��-��������ά����ģ����ά��</font>
							</span>
						</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" onclick="saveXmsz()" id="buttonSave">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<button type="button" onclick="updateXmsz()" id="buttonSave">
											�� ��
										</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</logic:notEmpty>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>	
	</body>
</html>
