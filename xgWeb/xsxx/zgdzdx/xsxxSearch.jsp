<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script>
		//�޸ġ���ʾ��ϸ��Ϣ
		function ljsdaUpdate(url,w,h){	
			var pk="";	
			if(curr_row == null ){
				alert("��ѡ��һ�м�¼��\n����һ�м���!");
				return false;
			} 	
					
			pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
			url+=pk;
			
			if(w==""||w==null){
				w = 800;
			}
			if(h==""||h==null){
				h = 600;
			}
			showTopWin(url,w,h);		
		}
		
		//��ȡ����
		function chec(){
	      for(i=0;i<document.getElementsByName("pk").length;i++){
	      	document.getElementsByName("pk")[i].checked=document.getElementsByName("xsxx")[0].checked;
	      }
	    }
	   
	       //��������
		function batch(url,oper){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	}
			}
			document.forms[0].delPk.value = RowsStr;
			
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ���������ļ�¼��");
				return false;
			}
			
			if (!confirm("ȷ��Ҫ������ѡ��¼��")){
				return false;
			}
			if(oper=="del"){
				refreshForm(url);
				showTips('���������У���ȴ�......');
			}else{
				url += "&pk=";
				url += RowsStr;
				showTopWin(url,400,300);
			}
		}
		
		//��������
		function batchOper(url){
			var RowsStr="!!";		
				for (i=0; i<document.getElementsByName("pk").length; i++){
			    	if(document.getElementsByName("pk")[i].checked){
			    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
			    	}
				}
				document.forms[0].delPk.value = RowsStr;
				
				if (RowsStr=="!!"){
					alert("��ѡ��Ҫ���������ļ�¼��");
					return false;
				}
				
				if (!confirm("ȷ��Ҫ������ѡ��¼��")){
					return false;
				}
				
				url += "&pk=";
				url += RowsStr;
				refreshForm(url);	
		}
		
		//�ж��û�����
		function check_user(){
			var user=document.all['userType'].value;
			if("xy"==user){
				document.getElementById('xydm').disabled=true;
			}else if("xx"==user){
				document.getElementById('xydm').disabled=false;
			}
		}
		
		//��ʾ��������
		function showTr(){
			var xxdm = document.getElementById("xxdm").value;
			var length = 12;
			for(var i=0; i<length; i++){
				if(ele('temDiv'+(i+1))){
					ele('temDiv'+(i+1)).style.display=(ele('temDiv'+(i+1)).style.display =='none')?'':'none';
				}
			}
		}
		
		//��ȡ������������
		function writeCondition(){
			var xxdm = document.getElementById("xxdm").value;
			ele = 'xh-xm-sfzh-ssbh-jg-byny-nj-xy-zy-bj-mz-zzmm-xb-xjztm-xz-jtcyxm-ksh-xslb-xslx-nfby-ydlbm-xsqrxxbz';
			
			var strs = ele.split('-');			
			var tmp = "";
			for(var i=0; i<strs.length; i++){
				if(document.getElementById(strs[i])){
					if(document.getElementById(strs[i]).value != ''){
						
						if(strs[i] == 'xy'){
							tmp += "@xydm!!" + document.getElementById(strs[i]).value;
						}else if(strs[i] == 'zy'){
							tmp += "@zydm!!" + document.getElementById(strs[i]).value;
						}else if(strs[i] == 'bj'){
							tmp += "@bjdm!!" + document.getElementById(strs[i]).value;
						}else{
							tmp += "@" + strs[i]+ "!!" + document.getElementById(strs[i]).value;
						}
					}
				}
			}
			document.getElementById('condition').value = tmp;
		}
	
		//ע��
	   	function allCtrl(lx){
	           var clinStr = "";
	           var pk= "";
	           if(lx == "ztzc"){
	           	clinStr = "�˹��ܽ�ʵ�֣���ĳ�꼶��<bean:message key="lable.xsgzyxpzxy" />��רҵ��༶Ϊ��λ��\n\n���õ�λ��ѧ������ע�ᡣ\n\nע����ѧ�����������ȫ�����á�";
	           }else if(lx == "ztby"){
	         		clinStr = "�˹��ܽ�ʵ�֣���ĳ�꼶��<bean:message key="lable.xsgzyxpzxy" />��רҵ��༶Ϊ��λ��\n\n���õ�λ��ѧ���������͵���ҵ����ʷ�⡣\n\nע��ת�Ƶ�ͬʱ����ոõ�λ�ڵĵ�ǰѧ����Ϣ��";
	           }
	           var nj = document.forms[0].nj.value;
	           var xy = document.forms[0].xy.value;
	           var zy = document.forms[0].zy.value;
	           var bj = document.forms[0].bj.value;
	           var xymc = "";
	           var zymc ="";
	           var bjmc = "";
	
	           xymc = document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;
	           zymc = document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
	           bjmc = document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
	           var confirmTxt = "";
	           
	           if (nj==""&&xy==""
	                     &&zy==""
	                     &&bj==""){
	            alert(clinStr+"\n\n��ѡ��Ҫ������Ϣ���꼶��<bean:message key="lable.xsgzyxpzxy" />��רҵ��༶��");
	            return false;
	           }else{
	               confirmTxt = "���� ";
	               if (nj!=""&&nj!="null"){
	               confirmTxt += " \'"+nj+"\'��";	        
		        }
		        if (xy!=""&&xy!="null"){
			       confirmTxt += "  \'"+xymc+"\'";	        
		        }
			    if (zy!=""&&zy!="null") {
				   confirmTxt += "  \'"+zymc+"\' רҵ";        
			    } 
			    if(bj!=""&&bj!="null"){
			       confirmTxt += " \'"+bjmc+"\' �༶";        
			    }
			    if(lx == "ztzc"){
			    	confirmTxt += " Ϊ��λ��ע��õ�λ��ȫ��ѧ����"; 
			    }else if(lx == "ztby"){
	         			confirmTxt += " Ϊ��λ�����õ�λ��ȫ��ѧ��ת�Ƶ�ѧ����ʷ�⣡"; 
				}					        		        
		     }
		  	if(confirm(confirmTxt)){
				if(lx == "ztzc"){
	           		var url = 'xsxxZgdzdx.do?method=stuAllRegister&nj='+nj+'&xy='+xy+'&zy='+zy+'&bj='+bj;
	           		window.open(url);
	           	}else if(lx == "ztby"){
	         			var url = 'xsxxZgdzdx.do?method=stuAllComplete&nj='+nj+'&xy='+xy+'&zy='+zy+'&bj='+bj;
	         			window.open(url);
				}
		    }else{
		       return false;
		    }	   
	    }	       	
	
		//������ѧ�ǼǱ�
	    function xsrxdjb(){
	    	if(curr_row == null ){
				alert("��ѡ��һ�м�¼��\n����һ�м���!");
				return false;
			} 	
			var pkvalue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			var url = "/xgxt/stu_info_add.do?method=dzdxxsrxdjb&pkvalue="+pkvalue;
			 document.forms[0].action = url;
			 document.forms[0].target = "_blank";
			 document.forms[0].submit();
	    }
	    
	    function initXsqrxx(){
	    	if(confirm("��ʼ����ѧ���Ƿ�ȷ����ϢΪ'��'����ȷ�ϸò�����")){
	    		refreshForm('xsxxZgdzdx.do?method=initXsqrxxbz');
	    	}
	    }
	</script>
</head>
	<body onload="check_user()" style="height:800px">	
		<html:form action="/xsxxZgdzdx.do?method=xsxxSearch" method="post">	
				<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
				<input type="hidden" id="userType" name="userType" value="${userSpeType}" />
				<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
				<input type="hidden" id="userName" name="userName" value="${userName}"/>
				<input type="hidden" name="jgz" value="" />
				<input type="hidden" name="mes" value="${mes}" />
				<input type="hidden" name="condition" value="" id='condition' />
				<input type="hidden" id="delPk" name="delPk" value="pk" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />
				<logic:notEmpty name="sfyby">
					<input type="hidden" id="nfby" value="${sfyby}"/>
					<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName"/>" />
					<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable"/>" />
				</logic:notEmpty>
				<logic:equal value="yes" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="xsxxb"/>
				</logic:equal>
				<logic:equal value="no" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="bks_xsjbxx"/>
				</logic:equal>
				
				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${title}</a>
					</p>
				</div>
				<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
					  <logic:notEmpty name="userOper">
					  <%--��ѧԺ�û�--%>
						<logic:notPresent name="isXY">
							<li> <a href="#" onclick="showTopWin('stu_info_add.do?method=showStuInfo&oper=add',800,600,false);" class="btn_zj">�� ��</a> </li>
							<li> <a href="#" onclick="ljsdaUpdate('stu_info_add.do?method=showStuInfo&oper=update&xh=');" class="btn_xg">�� ��</a> </li>
							<li> <a href="#" onclick="batch('stu_info_add.do?method=stuInfoDelete','del');" class="btn_sc">ɾ ��</a> </li>
							<li> <a href="#" onclick="viewTempDiv('��ҵ��Ϣ����','bycfDiv',450,500);" class="btn_csh">��ҵ����</a> </li>
							<li> <a href="#" onclick="viewTempDiv('��ҵʱ������','bysjDiv',400,300);" class="btn_csh">��ҵʱ���ʼ��</a> </li>
							<li> <a href="#" onclick="initXsqrxx();" class="btn_csh">ѧ��ȷ�ϳ�ʼ��</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
						</logic:notPresent>
					  </logic:notEmpty>
					  </logic:equal>
						<li> <a href="#" onclick="writeCondition();refreshForm('xsxxgl.do?method=showExportPage');" class="btn_dc">��������</a> </li>
						<li> <a href="#" onclick="ljsdaUpdate('stu_info_add.do?method=showStuInfo&type=details&oper=update&xh=',800,600);" class="btn_ck">��ϸ��Ϣ</a> </li>
					</ul>
				  </div>
				  <!-- ��ť -->				  
				  <div class="buttonbox">
					<ul>
						<li> <a href="#" onclick="xsrxdjb();" class="btn_dy">������ѧ�ǼǱ�</a> </li>
					</ul>
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>
						  <th nowrap="nowrap">
							<input type="checkbox" name="type" value="type" checked="checked" onclick="showTr()"></input>
							��������
						  </th>
				          <td colspan="5">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<logic:equal value="yes" name="sfyby">
									<button type="button" class="button2" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo&sfyby=yes');">
										��ѯ
									</button>
								</logic:equal>
								<logic:notEqual value="yes" name="sfyby">
									<button type="button" class="button2" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');">
										��ѯ
									</button>
								</logic:notEqual>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr id="gdDiv1">
						<th>�꼶</th>
						<td>
							<html:select property="nj"  styleId="nj" style="width:180px"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:text style="width:180px" property="xh"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId='xh' />									
						</td>
						<th>����</th>
						<td>
							<html:text style="width:180px" property="xm"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId='xm' />
						</td>
					  </tr>
					  <tr id="gdDiv2">
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm"  styleId="xy" style="width:180px"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="zydm"  styleId="zy" style="width:180px"
								onchange="initBjList();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>�༶</th>
						<td>
							<html:select property="bjdm" styleId="bj" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
					  <tr id="temDiv1">
						<th>�Ա�</th>
						<td>
							<html:select property="xb" style="width:180px" styleId="xb">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="Ů">Ů</html:option>
							</html:select>									
						</td>
						<th>����</th>
						<td>
							<html:select property="mz"  styleId="mz" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm"
									labelProperty="mzmc" />
							</html:select>
						</td>
						<th>������ò</th>
						<td>
							<html:select property="zzmm" styleId="zzmm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm"
									labelProperty="zzmmmc" />
							</html:select>
						</td>
					  </tr>
					  <tr id="temDiv2">
						<th>����</th>
						<td>
							<html:text property="jg" style="width:180px"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId='jg' />
						</td>
						<th>���֤��</th>
						<td>
							<html:text property="sfzh" style="width:180px"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId="sfzh" />
						</td>
						<th>������</th>
						<td>
							<html:text property="ksh" styleId="ksh" style="width:180px"/>
						</td>
					  </tr>
					  <tr id="temDiv3">
						<th>ѧ��</th>
						<td>
							<html:text property="xz" style="width:180px"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId="xz" />
						</td>
						<th>ѧ��״̬</th>
						<td>
							<html:select property="xjzt" styleId="xjztm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
							</html:select>
						</td>
						<th>������</th>
						<td>
							<html:text property="ssbh" style="width:180px"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId='ssbh' />
						</td>
					  </tr>
					  <tr id="temDiv4">
						<th>��ͥ��Ա����</th>
						<td>
							<html:text style="width:180px" property="jtcyxm" 
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');" />
						</td>
						<th>��ҵʱ��</th>
						<td>
							<html:text property="byny" styleId="byny" style="width:180px"/>
						</td>
						<th>ѧ���Ƿ�ȷ��</th>
						<td>
							<html:select property="xsqrxxbz" styleId="xsqrxxbz" style="width:180px">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
					  </tr>
					  <tr id="temDiv5">
						<th>ѧ�����</th>
						<td>	
							<html:select property="xslb" style="width:180px" styleId="xslb" >
								<html:option value=""></html:option>
								<html:options collection="xsLbList" property="dm"
									labelProperty="mc" />
							</html:select>  
						</td>
						<th>ѧ������</th>
						<td>
							<html:select property="xslx" style="width:180px" styleId="xslx">
								<html:option value=""></html:option>
								<html:options collection="xsLxList" property="dm"
									labelProperty="mc" />
							</html:select> 
						</td>
						<th>�춯���</th>
						<td>
							<html:select property="ydlbm" styleId="ydlbm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="ydlbList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					  </tr>
					  </tbody>
					</table>
				</div>
				</div>

				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		</logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ��</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <logic:equal value="yes" name="userOper">
						<td>
							<input type="checkbox" name="xsxx" value="all"
								onclick="chec()"/>
						</td>
					</logic:equal>

					<logic:iterate id="tit" name="topTr" offset="0" length="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap="nowrap">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>

					<logic:iterate id="tit" name="topTr" offset="2">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap="nowrap">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
					<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
						ondblclick="stuInfoWin(this)">

						<logic:equal value="yes" name="userOper">
							<td align="center">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" name="pk"
										value="<bean:write name="v"/>"/>
								</logic:iterate>
							</td>
						</logic:equal>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<bean:write name="v" />
							</logic:iterate>
							<input type="hidden" value="<bean:write name="v" />" />
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="2" length="1">
								<bean:write name="v" />
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="3">
							<td align="left">
								<bean:write name="v" />
							</td>
						</logic:iterate>						
					</tr>
				</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxZgdzdxForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				</logic:notEmpty>
				</div>
				<div id="tmpdiv"></div>
				<logic:notEmpty name="result">
					<logic:equal value="true" name="result">
						<logic:notEmpty name="mes">
							<script>
								alert(document.getElementById('mes').value);
							</script>
						</logic:notEmpty>
						<logic:empty name="mes">
							<script>
								alert("�����ɹ���");
							</script>
						</logic:empty>
						<script>
							Close();
							document.getElementById('search_go').click();						
						</script>
					</logic:equal>

					<logic:equal name="result" value="false">
						<logic:present name="mes">
							<logic:notEmpty name="mes">
								<script>
									alert(document.getElementById('mes').value);
								</script>
							</logic:notEmpty>
							<logic:empty name="mes">
								<script>
									alert("����ʧ��!");
								</script>
							</logic:empty>
						</logic:present>
						<logic:notPresent name="mes">
							<script>
								alert("����ʧ��!");
							</script>
						</logic:notPresent>
						<script>
								document.getElementById('search_go').click();
							</script>
					</logic:equal>
				</logic:notEmpty>

	<div id='bycfDiv' style="display:none">
	<table class='formlist'>
	<tbody>
	<tr>
		<th>
			��ѡ���޸ķ�ʽ��
		</th>
		<td>
			<input type='radio' name='configtype' value='2' checked="checked"/>&nbsp;�������޸�(���ڲ�ѯ����ѡ���ѧ������)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
			</br>
			<input type='radio' name='configtype' value='1'/>&nbsp;�޸�ѡ�е�����(���ڲ�ѯ����б��й�ѡ������)
		</td>
	</tr>
	</tbody>
	
	<tbody>		
	<thead>
		<tr>
			<th colspan='2'>
				<span>ѧ����ҵ�����Ϣ</span>
			</th>
		</tr>
	</thead>	
	<tr>
	<th>
		ѧ��״̬��
	</th>
	<td>
		<html:select property="xjzt" styleId="select_newxjztm" style="width:180px">
			<html:option value=""></html:option>
			<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
		</html:select>
	</td>
	</tr>
	<!--�й����ʴ�ѧ-->
	<logic:equal value="10491" name="xxdm">
		<tr>
			<th>
				�Ƿ���У��
			</th>
			<td>
				<select id='select_newsfzx' name='sfzx'><option></option><option value='��У'>��У</option><option value='����У'>����У</option></select>
			</td>
		</tr>
		<tr>
			<th>
				�ܷ��ҵ��
			</th>
			<td>
				<select id='select_newnfby' name='nfby'><option></option><option value='��'>��</option><option value='��'>��</option></select>
			</td>
		</tr>
		<tr>
			<th>
				�Ƿ��ѱ�ҵ��
			</th>
			<td>
				<select id='select_newsfyby' name='sfyby'><option></option><option value='��'>��</option><option value='��'>��</option></select>
			</td>
		</tr>	
	</logic:equal>
	<tr>
		<th>
			�Ƿ��ҵ����
		</th>
		<td>
			<select id='select_newsfbys' name='sfbys'><option></option><option value='��'>��</option><option value='��'>��</option></select>
		</td>
	</tr>
	<tr>
		<th>
			��ҵʱ�䣺
		</th>
		<td>
			<input type='text' id='newbyny' name='byny' onclick="return showCalendar('newbyny','y-mm-dd');"/>
		</td>
	</tr>
	</tbody>
	<tfoot>
	<tr>
		<td colspan='2'> 
			<div class='btn'>
				<span class='red'>���������ѧ����ҵ��ص���Ϣ,�����ز�����</span>
				<button type="button" onclick='byxxConfig()'>ȷ��</button>
				<button type="button" onclick="hiddenMessage(true,true);">�ر�</button>
			</div>
		</td>
	</tr>
	</tfoot>
	</table>
	</div>

	<div id="bysjDiv" style="display:none">
	<table width='350' class='formlist'>
	<thead>
	<tr>
	 <th align='center'>
	  	��ѡ���ʼ����ʽ
	 </th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>
			<input type='radio' name='configtype2' value='0'checked/>&nbsp;��ʼ��ȫ��&nbsp;&nbsp;&nbsp;&nbsp;
			<input type='radio' name='configtype2' value='2'/>&nbsp;��������ʼ��&nbsp;&nbsp;&nbsp;&nbsp;		
			<input type='radio' name='configtype2' value='1'/>&nbsp;��ʼ��ѡ�е�����
		</td>
	</tr>
	</tbody>

	<thead>
	<tr>
	 <th>
	 	 �����ҵ�·ݺ�����
	 </th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>
			<input type='text' id='yf' name='yf' value='' style='width:80px' maxlength='2' onkeyup="this.value=value.replace(/[^\d]/g,'');if(value>12){ value=12}"/>��<input type='text' id='rq' name='rq' value='' maxlength='2' style='width:80px' onkeyup="this.value=value.replace(/[^\d]/g,'');if(value>31){value=31}"/>��
		</td>
	</tr>
	</tbody>

	</tfoot>
	<tr>
		<td>
			<div class='btn'>
				<button type="button" class='button2' onclick='dataConfig()'>ȷ��</button>&nbsp;&nbsp;
				<button type="button" class='button2' onclick="hiddenMessage(true,true);">�ر�</button>
			</div>
		</td>
	</tr>
	</tfoot>
	</table>
	</div>	
</html:form>
	<div id="tmpdiv"></div>
	</body>
</html>

