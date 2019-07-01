<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/gyglGywh.js"></script>	
		<script type='text/javascript' src='dwr/interface/getCommGygl.js'></script>
		<script type="text/javascript">
		function modi(){

			//��֤����������
			b=false;
			var re_num = /^[\d]+$/;
			var scqss=document.getElementsByName("scqss");
			var ldmc=jQuery("[name='ldmc']").val();
			if(ldmc==""){
				alertInfo("¥�����Ʋ���Ϊ��!");
				return false;
			}
			for(var i=0;i<scqss.length;i++){
				if(scqss[i].value.trim()==""){
					b=true;
					break;
				}else if(!re_num.test(scqss[i].value)){
					alertInfo("¥������������Ϊ����!");
					return false;
				}
			}
			if(b){
				alertInfo("¥��������������Ϊ��!");
				return false;
			}
			//��֤����������
			b=false;
			var cws=document.getElementsByName("cws");
			for(var i=0;i<cws.length;i++){
				if(cws[i].value.trim()==""){
					b=true;
					break;
				}else if(!re_num.test(scqss[i].value)){
					alertInfo("��λ������Ϊ����!");
					return false;
				}
			}
			if(b){
				alertInfo("��λ��������Ϊ��!");
				return false;
			}
			//��֤�շѱ�׼
			b=false;
			var sfbz=document.getElementsByName("sfbz");
			for(var i=0;i<sfbz.length;i++){
				if(sfbz[i].value.trim()==""){
					b=true;
					break;
				}else if(!re_num.test(scqss[i].value)){
					alertInfo("�շѱ�׼����Ϊ����!");
					return false;
				}
			}
			if(b){
				alertInfo("�շѱ�׼������Ϊ��!");
				return false;
			}
			
			var url="gyglnew_ldgl.do?method=ldxxwh&doType=modi";
			refreshForm(url);
		}

		//����¥��
		function createLd(){
			if($("newldmark").value=="yes"){
                if($("sfkzjlc")&&$("sfkzjlc").value=="1"){
                    var originalLdcs = $("originalLdcs").value;
                    var ldcs = $("ldcs").value;

                    if(parseInt(originalLdcs)>=parseInt(ldcs)){
                        alertInfo("ֻ��������¥�㣡");
                        $("ldcs").value=originalLdcs;
                    }
					createLd_new_add();
                    return true;
                }
				createLd_new();
				return true;
			}

			var ldxb="��";//¥���Ա�
			var ldxb_radio_temp=document.getElementsByName("ldxb");
			for(var i=0;i<ldxb_radio_temp.length;i++){
				if(ldxb_radio_temp[i].checked){
					ldxb=ldxb_radio_temp[i].value;
					break;
				}
			}

			var ld_table=$("ld_table");//¥��table
			var ld_trs=ld_table.getElementsByTagName("TR");
			var td;
			for(var i=2;i<ld_trs.length;i++){//�������õĲ�������¥��
				td=ld_trs[i].cells[3];
				//alertInfo(ldxb+"    "+("��ס"!=ldxb)+"  "+td.getElementsByTagName("span").length);
				if("��ס"==ldxb){ 
					if(td.getElementsByTagName("span")[1]){
						td.getElementsByTagName("span")[0].style.display="none";
						td.getElementsByTagName("span")[1].style.display="";
					}
				}else{
					if(td.getElementsByTagName("span")[1]){
						td.getElementsByTagName("span")[0].style.display="";
						td.getElementsByTagName("span")[0].innerHTML=ldxb;
						td.getElementsByTagName("span")[1].style.display="none";
					}
				}
				//break;
			}
		}

	  function init(){
		var qsch=$("qsch").value;
		if(qsch>=0){
			$("sfhlc_lable").style.display="none";
			$("sfhlc_obj").style.display="none";
		}else{
			$("sfhlc_lable").style.display="";
			$("sfhlc_obj").style.display="";
		}
	  }

	//����¥��
		function createLd_new(){
			var ldxb="��";//¥���Ա�
			var ldxb_radio_temp=document.getElementsByName("ldxb");
			for(var i=0;i<ldxb_radio_temp.length;i++){
				if(ldxb_radio_temp[i].checked){
					ldxb=ldxb_radio_temp[i].value;
					break;
				}
			}
			var ldcs=$("ldcs").value;//¥������
			var qsch=parseInt($("qsch").value);//��ʼ���
			var sfhlc=$("sfhlc").value;//�Ƿ����
			if(ldxb==""||ldcs==""){
				return false;
			}
			
			var ld_table=$("ld_table_new");//¥��table
			var ld_trs=ld_table.getElementsByTagName("TR");
			for(var i=ld_trs.length-1;i>1;i--){//�Ƴ�ԭ�ȴ�������Ϣ
				ld_table.deleteRow(i);
			}
			var b_mark;//����B���
			var ch_value;//���ֵ
			var ch_name;//�������
			var sfhlc_mark=(qsch>0||sfhlc=="��"?0:1);//�Ƿ������//������������㣬���������Ҫ����
			var top_mark=true;//������
			for(var i=parseInt(ldcs)-1,xbnum=0;i>-1;i--,xbnum++){//�������õĲ�������¥��var i=0;i<parseInt(ldcs);i++
				var r=ld_table.insertRow(parseInt(ldcs)-1-i+2);//ld_table.insertRow(i+2);
				if(i+qsch<0){
					b_mark="B";
					ch_value=i+qsch;
					ch_name=-(i+qsch);
				}else{
					b_mark="";
					ch_value=i+qsch+sfhlc_mark;
					ch_name=i+qsch+sfhlc_mark;
				}
				
				r.insertCell(0).innerHTML='<input type="hidden" name="ch" value="'+ch_value+'"/>'+b_mark+ch_name+"��/0";
				
				r.insertCell(1).innerHTML='<input type="text" style="width:100px" name="scqss" maxlength="2"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
				r.insertCell(2).innerHTML='<input type="text" style="width:100px" name="cws" maxlength="2"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
				r.insertCell(3).innerHTML='<input type="text" style="width:100px" name="sfbz" maxlength="4"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
				if(ldxb=="��"){
					r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" checked="checked" value="��"/>��<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" value="Ů"/>Ů';
				}else if(ldxb=="Ů"){
					r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" display="none;" name="ldxb'+xbnum+'" value="��"/>��<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" checked="checked" value="Ů"/>Ů';
				}else{
					r.insertCell(4).innerHTML='<input type="radio" name="qsxb'+xbnum+'" checked="checked" value="��"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>��<input type="radio" name="qsxb'+xbnum+'" value="Ů"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>Ů';
				}
				top_mark=false;
			}
		}

		// ����¥��For����¥��
        function createLd_new_add(){
            var ldxb="��";//¥���Ա�
            var ldxb_radio_temp=document.getElementsByName("ldxb");
            for(var i=0;i<ldxb_radio_temp.length;i++){
                if(ldxb_radio_temp[i].checked){
                    ldxb=ldxb_radio_temp[i].value;
                    break;
                }
            }
            var ldcs=$("ldcs").value;//¥������
			var originalLdcs = $("originalLdcs").value;//ԭʼ¥������
            var qsch=parseInt($("qsch").value);//��ʼ���
            var sfhlc=$("sfhlc").value;//�Ƿ����
            if(ldxb==""||ldcs==""){
                return false;
            }

            var ld_table=$("ld_table_new");//¥��table
            var ld_trs=ld_table.getElementsByTagName("TR");
            for(var i=ld_trs.length-1;i>1;i--){//�Ƴ�ԭ�ȴ�������Ϣ
                ld_table.deleteRow(i);
            }
            var b_mark;//����B���
            var ch_value;//���ֵ
            var ch_name;//�������
            var sfhlc_mark=(qsch>0||sfhlc=="��"?0:1);//�Ƿ������//������������㣬���������Ҫ����
            var top_mark=true;//������
            for(var i=parseInt(ldcs)-1,xbnum=0;i>parseInt(originalLdcs)-1;i--,xbnum++){//�������õĲ�������¥��var i=0;i<parseInt(ldcs);i++
                var r=ld_table.insertRow(parseInt(ldcs)-1-i+2);//ld_table.insertRow(i+2);
                if(i+qsch<0){
                    b_mark="B";
                    ch_value=i+qsch;
                    ch_name=-(i+qsch);
                }else{
                    b_mark="";
                    ch_value=i+qsch+sfhlc_mark;
                    ch_name=i+qsch+sfhlc_mark;
                }

                r.insertCell(0).innerHTML='<input type="hidden" name="ch" value="'+ch_value+'"/>'+b_mark+ch_name+"��/0";

                r.insertCell(1).innerHTML='<input type="text" style="width:100px" name="scqss" maxlength="2"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
                r.insertCell(2).innerHTML='<input type="text" style="width:100px" name="cws" maxlength="2"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
                r.insertCell(3).innerHTML='<input type="text" style="width:100px" name="sfbz" maxlength="4"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
                if(ldxb=="��"){
                    r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" checked="checked" value="��"/>��<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" value="Ů"/>Ů';
                }else if(ldxb=="Ů"){
                    r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" display="none;" name="ldxb'+xbnum+'" value="��"/>��<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" checked="checked" value="Ů"/>Ů';
                }else{
                    r.insertCell(4).innerHTML='<input type="radio" name="qsxb'+xbnum+'" checked="checked" value="��"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>��<input type="radio" name="qsxb'+xbnum+'" value="Ů"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>Ů';
                }
                top_mark=false;
            }
        }
		//��������
		function plsr(obj){
			if($("plsr_checkbox").checked){
				var objs=document.getElementsByName(obj.name);
				for(var i=1;i<objs.length;i++){
					objs[i].value=obj.value;
				}
			}
		}
		//������������Ա�
		function click_qsxb(obj){
			if($("plsr_checkbox").checked){
				var ldcs=$("ldcs").value;//¥������
				for(var i=1;i<ldcs;i++){
					if(obj.value=="��"){
						document.getElementsByName("qsxb"+i)[0].checked="checked";
					}else{
						document.getElementsByName("qsxb"+i)[1].checked="checked";
					}
				}
			}
		}

		//�����Ƿ�������ʾ����
		function show_sfhlc(qsch){
			if(qsch>=0){
				if(qsch==0){
					$("sfhlc").value="��";
				}else{
					$("sfhlc").value="��";
				}
				$("sfhlc_lable").style.display="none";
				$("sfhlc_obj").style.display="none";
			}else{
				$("sfhlc_lable").style.display="";
				$("sfhlc_obj").style.display="";
			}
		}

		var ld_table=document.createElement("table");
		//newSelectObj = selectObj.cloneNode(true);
		var ld_table_new=document.createElement("table");
		//���´���¥��
		function cxCreateLd(but){
			if(but.innerHTML=="��������"){
				but.innerHTML="ȡ������"
				$("newldmark").value="yes";
				ld_table=$("ld_table").cloneNode(true);
				$("div_ld_table").removeChild($("ld_table"));
				if($("ld_table_new")){
					$("ld_table_new").style.display="";
				}else{
					$("div_ld_table").appendChild(ld_table_new);
				}
				$("qsh_gz").style.display="";
				$("ldcs").disabled=false;
				$("qsch").disabled=false;
				$("sfhlc").disabled=false;
				createLd_new();
				
			}else if((but.innerHTML=="ȡ������")){
				but.innerHTML="��������"
				$("newldmark").value="no";
				ld_table_new=$("ld_table_new").cloneNode(true);
				$("div_ld_table").removeChild($("ld_table_new"));
				$("div_ld_table").appendChild(ld_table);

				var ldxb_radio_temp=document.getElementsByName("ldxb");
				for(var i=0;i<ldxb_radio_temp.length;i++){
					if(ldxb_radio_temp[i].value==$("hid_ldxb").value){
						ldxb_radio_temp[i].checked=true;
						break;
					}
				}
				$("qsh_gz").style.display="none";
				$("ldcs").value=$("hid_ldcs").value;
				$("qsch").value=$("hid_qsch").value;
				$("sfhlc").value=$("hid_sfhlc").value;
				$("ldcs").disabled=true;
				$("qsch").disabled=true;
				$("sfhlc").disabled=true;
				show_sfhlc($("qsch").value);
				createLd();
			}else if(but.innerHTML=="����¥��"){
                but.innerHTML="ȡ������";
                $("newldmark").value="yes";
                ld_table=$("ld_table").cloneNode(true);
                //$("div_ld_table").removeChild($("ld_table"));
                if($("ld_table_new")){
                    $("ld_table_new").style.display="";
                }else{
                    $("div_ld_table").prepend(ld_table_new);
                }

                $("qsh_gz").style.display="";
                $("ldcs").disabled=false;
                $("qsch").disabled=false;
                $("sfhlc").disabled=false;
//                createLd_new();
			}else{
                but.innerHTML="����¥��";
                $("newldmark").value="no";
                ld_table_new=$("ld_table_new").cloneNode(true);
                $("div_ld_table").removeChild($("ld_table_new"));

                var ldxb_radio_temp=document.getElementsByName("ldxb");
                for(var i=0;i<ldxb_radio_temp.length;i++){
                    if(ldxb_radio_temp[i].value==$("hid_ldxb").value){
                        ldxb_radio_temp[i].checked=true;
                        break;
                    }
                }
                $("qsh_gz").style.display="none";
                $("ldcs").value=$("hid_ldcs").value;
                $("ldcs").disabled=true;
                show_sfhlc($("qsch").value);
			}
		}
		function showSzm(){
			if(jQuery("[name='qsh_sfhszm']").prop("checked")){
				jQuery("#qsh_szm").css("display","");
			}else{
				jQuery("#qsh_szm").css("display","none");
				//$("qsh_szm").style.display = "none";
			}
		}
		</script>
	</head>
	<body onload="init();">
		<html:form action="/gyglnew_ldgl" method="post">
			<logic:present name="rs">
			<input type="hidden" name="czxq" id="czxq" value="${czxq }"/>
			<input type="hidden" name="czyq" id="czyq" value="${czyq }"/>
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}"/>
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}"/>
			<input type="hidden" id="sfcjxld" value="${rs.mark}"/>
			<input type="hidden" name="newldmark" id="newldmark" value=""/>
			
			<input type="hidden" id="hid_ldxb" value="${rs.ldxb}"/>
			<input type="hidden" id="hid_ldcs" value="${rs.ldcs}"/>
			<input type="hidden" id="hid_qsch" value="${rs.qsch}"/>
			<input type="hidden" id="hid_sfhlc" value="${rs.sfhlc}"/>
			<%--<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--%><!-- ��ʾ��Ϣ �����޸�ʱ��ʾ end-->
			<logic:equal name="doType" value="update">
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��������ס��¥�������޸�¥���Ա����ơ�<br/>	
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			</logic:equal>
			<!-- ��ʾ��Ϣ end-->		
			<div class="tab" style="tab;height:460px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.ld" />��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<logic:notEqual value="0" name="xqbj">
							<th style="width:10%">
								<font color="red">*</font>
								У��
							</th>
							<td  style="width:40%">
								<html:select property="xqdm" name="rs" styleId="xqdm" disabled="true">
									<html:option value=""></html:option>
									<html:optionsCollection name="xqlist" label="xqmc" value="dm"/>
								</html:select>
							</td>
							</logic:notEqual>
							<logic:notEqual value="0" name="yqbj">
							<th>
								<font color="red">*</font>
								<bean:message key="lable.yuanqu" />
							</th>
							<td>
								<html:select property="yqdm" name="rs" styleId="yqdm" disabled="true">
									<html:option value=""></html:option>
									<html:optionsCollection name="yqlist" label="yqmc" value="yqdm"/>
								</html:select>
							</td>
							</logic:notEqual>
						</tr>
						<tr>
							<th style="width:14%">
								<font color="red">*</font>
								¥������
							</th>
							<td  style="width:36%">
								<html:hidden property="lddm" name="rs"/>
								<html:text property="lddm" name="rs" disabled="true"></html:text>
							</td>
							<th>
								<font color="red">*</font>
								¥������
							</th>
							<td>
								<html:text property="ldmc" name="rs" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width:14%">
								<font color="red">*</font>
								<bean:message key="lable.ld" />�Ա�
							</th>
							<td  style="width:36%">
								<logic:equal value="��" name="rs" property="ldxb">
									<logic:equal value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="��" onclick="createLd();" checked="checked"/>��
										<input type="radio" name="ldxb" value="��ס" onclick="createLd();" />��ס
									</logic:equal>
									<logic:notEqual value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="��" onclick="createLd();" checked="checked"/>��
										<input type="radio" name="ldxb" value="Ů" onclick="createLd();" />Ů
										<input type="radio" name="ldxb" value="��ס" onclick="createLd();" />��ס
									</logic:notEqual>
								</logic:equal>
								<logic:equal value="Ů" name="rs" property="ldxb">
									<logic:equal value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="Ů" onclick="createLd();" checked="checked"/>Ů
										<input type="radio" name="ldxb" value="��ס" onclick="createLd();" />��ס
									</logic:equal>
									<logic:notEqual value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="��" onclick="createLd();" />��
										<input type="radio" name="ldxb" value="Ů" onclick="createLd();" checked="checked"/>Ů
										<input type="radio" name="ldxb" value="��ס"  onclick="createLd();"/>��ס
									</logic:notEqual>
								</logic:equal>
								<logic:equal value="��ס" name="rs" property="ldxb">
									<logic:equal value="1" name="rs" property="mark">
										<logic:notEqual value="1" name="rs" property="w_mark">
											<input type="radio" name="ldxb" value="��"  onclick="createLd();"/>��
										</logic:notEqual>
										<logic:notEqual value="1" name="rs" property="m_mark">
											<input type="radio" name="ldxb" value="Ů" onclick="createLd();"/>Ů
										</logic:notEqual>
										<input type="radio" name="ldxb" value="��ס" onclick="createLd();" checked="checked"/>��ס
									</logic:equal>
									<logic:notEqual value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="��"  onclick="createLd();"/>��
										<input type="radio" name="ldxb" value="Ů"  onclick="createLd();"/>Ů
										<input type="radio" name="ldxb" value="��ס"  onclick="createLd();" checked="checked"/>��ס
									</logic:notEqual>
								</logic:equal>
<%--								<html:radio property="ldxb" value="��" name="rs" onclick="createLd();">��</html:radio>--%>
<%--								<html:radio property="ldxb" value="Ů" name="rs" onclick="createLd();">Ů</html:radio>--%>
<%--								<html:radio property="ldxb" value="��ס" name="rs" onclick="createLd();">��ס</html:radio>--%>
							</td>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />����
							</th>
							<td>
								<html:select property="ldcs" name="rs" styleId="ldcs" onchange="createLd();" disabled="true">
									<html:optionsCollection name="ldcsList" label="mc" value="dm" />
								</html:select>
								<input type="hidden" id="originalLdcs" name="originalLdcs" value="${rs.ldcs}"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />��ʼ��
							</th>
							<td>
								<html:select property="qsch" name="rs" styleId="qsch" onclick="createLd();show_sfhlc(this.value);"  disabled="true">
									<html:option value="5">5</html:option>
									<html:option value="4">4</html:option>
									<html:option value="3">3</html:option>
									<html:option value="2">2</html:option>
									<html:option value="1">1</html:option>
									<html:option value="0">0</html:option>
									<html:option value="-1">-1</html:option>
									<html:option value="-2">-2</html:option>
									<html:option value="-3">-3</html:option>
									<html:option value="-4">-4</html:option>
								</html:select>
							</td>
							<th>
								<span id="sfhlc_lable" style="display: none;">�Ƿ�0��</span>
							</th>
							<td>
								<span id="sfhlc_obj" style="display: none;">
								<html:select property="sfhlc" name="rs" styleId="sfhlc" onclick="createLd();" disabled="true">
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
								</span>
							</td>
						</tr>
						<tr>
							<th >
								<bean:message key="lable.ld" />��ע<br/><font color="red">(��1000��)</font>
							</th>
							<td  colspan="3">
								<html:textarea property="bz" name="rs" rows="3" style="word-break:break-all;width:95%" onblur="chLeng(this,1000);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>ά����Ҫ���ɵ��������</span>
								<logic:notEqual value="1" name="rs" property="mark">
									<button type="button"  onclick="cxCreateLd(this);">��������</button>
								</logic:notEqual>
								<logic:equal value="1" name="rs" property="mark">
									<logic:equal value="10530" name="xxdm">
										<input type="hidden" id="sfkzjlc" name="sfkzjlc" value="1"/>
										<button type="button" onclick="cxCreateLd(this);"   title="��¥����δ��ס��δ���������¿�����¥�㣡">����¥��</button>
									</logic:equal>
									<logic:notEqual value="10530" name="xxdm">
										<button type="button"  disabled="disabled" title="��¥����δ��ס��δ���������²ſ��������ɣ�">��������</button>
									</logic:notEqual>
								</logic:equal>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr id="qsh_gz" style="display: none">
							<td colspan="5">
								���Һ����ɹ���
								<input type="checkbox" name="qsh_sfhszm" value="��" onclick="showSzm();"/>����ĸ
								<input type="text" name="qsh_szm" id="qsh_szm" maxlength="4" style="width: 50px;display: none"/>
								&nbsp;&nbsp;&nbsp;&nbsp;<strong>+</strong>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="qsh_ws" value="3" checked="checked"/>��λ
								<input type="radio" name="qsh_ws" value="4"/>��λ
							</td>
						</tr>
					</tbody>					
				</table>
				<div id="div_ld_table" style="overflow-x:hidden;overflow-y:auto;width: 100%">
						<%--	�´���¥��new  start			--%>
					<table width="100%" border="0" class="datelist tablenowrap" id="ld_table_new" style="display:none;">
						<thead>
						<tr>
							<td colspan="5">
								<input type="checkbox" id="plsr_checkbox" checked="checked" />Ĭ�ϰ���ʼ��������������������λ�����շѱ�׼
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								���/����������
							</th>
							<th style="width:20%">
								����������
							</th>
							<th style="width:20%">
								�������Ҵ�λ��
							</th>
							<th style="width:20%">
								�շѱ�׼��Ԫ��
							</th>
							<th style="width:20%">
								�����Ա�
							</th>
						</tr>
						</thead>
					</table>
						<%--	�´���¥��new  end			--%>

				<table width="98%" border="0" class="datelist tablenowrap" id="ld_table">					
					<thead>
						<%--<tr>
							<td colspan="5">
								<input type="checkbox" id="plsr_checkbox" checked="checked" />Ĭ�ϰ��ײ�������������������λ�����շѱ�׼
							</td>
						</tr>--%>
						<tr>
							<th style="width:20%">
								���
							</th>
							<th style="width:20%">
								������
							</th>
							<th style="width:20%">
								�ܴ�λ��
							</th>
							<th style="width:20%">
								�����Ա�
							</th>
						</tr>
					<thead>
					<tbody>
						<logic:iterate id="rt" name="rstj">
							<tr>
								<td>
									<% String ch=((HashMap<String,String>)rt).get("ch");
										if(ch!=null&&Integer.parseInt(ch)<0){
											%>B<%=Math.abs(Integer.parseInt(ch)) %><%
										}else{
											%><%=ch%><%
										}
									%>
									��
									<input type="hidden" name="ch" value="${rt.ch }" />
								</td>
								<td>${rt.qss }</td>
								<td>${rt.cwzs }</td>
								<td>
									<logic:notEqual value="��ס" name="rs" property="ldxb">
										<logic:equal value="0" name="rt" property="mark">
											<logic:equal value="��" name="rt" property="qsxb">
												<span>��</span>
												<span style="display: none;">
													<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="��" checked="checked"/>��
													<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="Ů" />Ů
												</span>
											</logic:equal>
											<logic:equal value="Ů" name="rt" property="qsxb">
												<span>Ů</span>
												<span style="display: none;">
													<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="��" />��
													<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="Ů" checked="checked"/>Ů
												</span>
											</logic:equal>
										</logic:equal>
										<logic:notEqual value="0" name="rt" property="mark">
											<span>${rt.qsxb}</span>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="��ס" name="rs" property="ldxb">
										<logic:notEqual value="0" name="rt" property="mark">
											<span>${rt.qsxb}</span>
										</logic:notEqual>
										<logic:equal value="0" name="rt" property="mark">
											<span></span>
											<span>
											<logic:equal value="��" name="rt" property="qsxb">
												<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="��" checked="checked"/>��
												<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="Ů"/>Ů
											</logic:equal>
											<logic:equal value="Ů" name="rt" property="qsxb">
												<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="��"/>��
												<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="Ů" checked="checked"/>Ů
											</logic:equal>
											</span>
										</logic:equal>
									</logic:equal>
<%--								${rt.qsxb}<input type="hidden" name="qsxb_old" value="${rt.qsxb}"/>--%>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				</div>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<font color="blue">
										ע����"<font color="red">*</font>"����Ϣ����¼�룬�Ա𲻿��޸ĵ�¥����˵����¥������һ�λ�ѷ����λ����ס
									</font>
								</div>
								<div class="btn">
									<button type="button" class="button2" id="btn_bc"  onclick="modi()">
										�� ��
									</button>
									<button type="button" class="button2"  onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			</logic:present>
			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
