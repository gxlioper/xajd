<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="xyDisabled('xy');bjLimit('bj')">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="tips" scope="request" />
				</a>
			</p>
		</div>
		<!-- ���� end-->
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/data_search" method="post">
			<div id="clinDiv" style="display:none" align="center">
				<!-- ��Ŀ������� -->
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>����ʱ������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align='right' width='40%'>
								<font color=red>*</font>����ʱ��
							</td>
							<td align='left'>
								<input type='text' name='tssjv' id="tssjv"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tssjv','y-mm-dd','aa');"
									readonly="true"/>
							</td>
						</tr>
						
						<tr>
							<td align='right' width='40%'>
								(������Ϊ��)<br/>�Ƿ��ͷŷ�Դ
							</td>
							<td align='left'>
								<select name="sfsf" id="select_sfsf">
									<option value="no">��</option>
									<option value="yes">��</option>
								</select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<button type="button" id="clinBtnSave" onclick='closeToSave()'>
										�ύ
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick='hiddenMessage(true,true)'>
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- �й����ʴ�ѧ -->
			<logic:equal value="10491" name="xxdm">
				<!-- ҳǩ -->
				<div class="compTab" id="card">
					<div class="comp_title" id="div1">
						<ul id="ul1">
							<li class="ha">
								<a href="#"
									onclick="$('go').value='';refreshForm('dorm_Using_Search.do?act=usingInfo')">
									<span>��Уѧ��</span> </a>
							</li>
							<li>
								<a href="#"
									onclick="$('go').value='';refreshForm('wxs_dormUser_Manage.do')">
									<span>����ѧ��</span> </a>
							</li>
						</ul>
					</div>
				</div>
				<!-- ҳǩ end-->
			</logic:equal>
			<!-- �й����ʴ�ѧ end-->
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="dxq" name="dxq"
				value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value="" />
			<input type="hidden" name="qshV" id="qshV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="delPk" name="delPk" value="" />
			<input type="hidden" name="zgdzdxSssZs" id="zgdzdxSssZs"
				value="<bean:write name="zgdzdxSssZs" scope="request"/>" />
			<input type="hidden" name="isFdy " id="isFdy"
				value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" name="tsFlag" id="tsFlag" value="" />
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" onclick="viewMore('add')" class="btn_zj">����</a>
							</li>
							<li>
								<a href="#" onclick="viewMore('modi')" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" onclick="batchDel()" class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#" onclick="batchTs()" class="btn_shbtg">����</a>
							</li>
							<li>
								<a href="#" onclick="tohis()" class="btn_down">��������</a>
							</li>
							<!-- ���ֶ���web.xml������ -->
							<logic:equal name="gysxKz" value="true">
								<li>
									<a href="#" onclick="xssjTb()" class="btn_qd">����������Ϣ��ȡ</a>
								</li>
							</logic:equal>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr">����</a>
							</li>
						</logic:equal>
						<li>
							<a href="#" onclick="dataExport()" class="btn_dc">����</a>
						</li>
						<li>
							<a href="#" onclick="showTopWin('xszsxx_check.do', 800, 600);" class="btn_down">����У��</a>
						</li>
						<logic:present name="showgdnz">
							<li>
								<a href="#" onclick="gdnz_dormDataExp('/xgxt/gdnz_dormSlt.do')"
									class="btn_dc">�������Ա�</a>
							</li>
							<li>
								<a href="#" onclick="window.open('/xgxt/gdnz_dormFpb.do')"
									class="btn_dc">������������</a>
							</li>
							<li>
								<a href="#" onclick="gdnz_dormDataExp('/xgxt/gdnz_xqxsrstj.do')"
									class="btn_dc">ѧ��ѧ������ͳ��</a>
							</li>
							<li>
								<a href="#" onclick="gdnz_dormDataExp('/xgxt/gdnz_ndzxxstj.do')"
									class="btn_dc">�����Уѧ��ͳ��</a>
							</li>
						</logic:present>
						<logic:present name="showgdbyxy">
							<li>
								<a href="#"
									onclick="gdnz_dormDataExp('/xgxt/XsGyGlLogic.do?method=gdby_dormCwtj')"
									class="btn_dc">���ᴲλ�����</a>
							</li>
						</logic:present>
						<logic:present name="showhngydx">
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/hngydx_gygl.do?method=getHmcTj',410,200)"
									class="btn_dc">������</a>
							</li>
						</logic:present>
					</ul>
				</div>
				<!-- ��ť end-->
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/dorm_Using_Search.do?act=usingInfo');this.disabled=true">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<logic:equal value="false" name="zgdzdxSssZs">
								<!-- ��һ�� -->
								<tr>
									<th>
										�꼶
									</th>
									<td>
										<html:select property="nj" styleId="nj"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:text property="xh" style="width:80px" styleId="xh"></html:text>
									</td>
									<th>
										����
									</th>
									<td>
										<html:text property="xm" styleId="xm" style="width:80px"></html:text>
									</td>
								</tr>
								<!-- �ڶ��� -->
								<tr>
									<th>
										Ժϵ
									</th>
									<td>
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										רҵ
									</th>
									<td>
										<html:select property="zydm" styleId="zy" style="width:150px"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										�༶
									</th>
									<td>
										<html:select property="bjdm" styleId="bj" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<!-- ������ -->
								<tr>
									<th>
										У����
									</th>
									<td>
										<html:select property="xqdm" styleId="xqdm"
											onchange="refreshForm('/xgxt/dorm_Using_Search.do?act=usingInfo')">
											<html:option value=""></html:option>
											<html:options collection="xiaoqquList" property="dm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										¥����
									</th>
									<td>
										<html:select property="lddm" styleId="lddm"
											onchange="getLcList()">
											<html:options collection="ldList" property="lddm"
												labelProperty="ldmc" />
										</html:select>
									</td>
									<th>
										¥��
									</th>
									<td>
										<html:select property="lc" styleId="lc"
											onchange="getQshList2()">
											<html:options collection="lcList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<!-- ������ -->
								<tr>
									<th>
										���Һ�
									</th>
									<td>
										<html:select property="qsh" styleId="qsh">
											<html:options collection="qshList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										�Ա�
									</th>
									<td>
										<html:select property="xb1" styleId="xb1">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="Ů">Ů</html:option>
										</html:select>
									</td>
									<th>
										<logic:equal name="xxdm" value="10690">
									���ҳ�
									</logic:equal>
									</th>
									<td>
										<logic:equal name="xxdm" value="10690">
											<html:select property="sfqsz" styleId="sfqsz">
												<html:option value=""></html:option>
												<html:option value="��">��</html:option>
												<html:option value="��">��</html:option>
											</html:select>
										</logic:equal>
									</td>
								</tr>
								<!-- ������ -->
								<tr>
									<td colspan="6">
										<logic:present name="showzjcm">
                                          &nbsp;&nbsp;�������
										    <html:select property="pycc" styleId="pycc"
												style="width: 100px">
												<html:option value=""></html:option>
												<html:options collection="pyccList" property="dm"
													labelProperty="mc" />
											</html:select>
										</logic:present>
										<logic:present name="showzjcmxy">
											&nbsp;&nbsp;������ò				
							    			<html:select property="zzmm" style="width:100px"
												styleId="zzmm">
												<html:option value=""></html:option>
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</logic:present>
										<logic:present name="showxbemy">
											<!-- ��������Ժ -->
											<br />							
							            ����			
							    	<html:select property="mz" style="width:100px" styleId="mz">
												<html:option value=""></html:option>
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>						    	
							    &nbsp;&nbsp;����				
							    	<html:text property="jg" styleId="jg" style="width:130px"></html:text>
							    	&nbsp;&nbsp;���֤��
							    	<html:text property="sfzh" styleId="sfzh"
												style="width:140px"></html:text>
											<br />
							    	��Դ��				
							    	<html:text property="sydmc" styleId="sydmc"
												style="width:100px"></html:text>	
										&nbsp;&nbsp;��ͥ��ַ
							    	<html:text property="jtdz" styleId="jtdz"
												style="width:200px"></html:text>
											<br />
							    	��ͥ�绰
							    	<html:text property="jtdh" styleId="jtdh"
												style="width:110px"></html:text>
							    	&nbsp;&nbsp;���˵绰
							    	<html:text property="lxdh" styleId="lxdh"
												style="width:110px"></html:text>	
							    	&nbsp;&nbsp;������ò				
							    	<html:select property="zzmm" style="width:180px"
												styleId="zzmm">
												<html:option value=""></html:option>
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</logic:present>
										<logic:present name="showhhgxy">							   
								&nbsp;&nbsp;���ҵ绰
								    <html:text property="qsdh" styleId="qsdh" style="width:80px"></html:text>
								&nbsp;&nbsp;������ϵ��ʽ
								    <html:text property="lxdh" styleId="lxdh" style="width:80px"></html:text>
										</logic:present>
										<logic:equal value="true" name="zgdzdxSssZs">

										</logic:equal>
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="true" name="zgdzdxSssZs">
								<!-- �о��� ��ѯ���� -->
								<!-- ��һ�� -->
								<tr>
									<th>
										�꼶
									</th>
									<td>
										<html:select property="nj" onchange="SssBjLb();">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										Ժϵ
									</th>
									<td>
										<html:select property="xydm" styleId="xy"
											onchange="SssBjLb();">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="ssxyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										�༶
									</th>
									<td>
										<html:select property="bjdm" styleId="bj">
											<html:options collection="ssbjList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<!-- �ڶ��� -->
								<tr>
									<th>
										У����
									</th>
									<td>
										<html:select property="xqdm" styleId="xqdm"
											onchange="getLdLb()">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="xiaoqquList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										¥����
									</th>
									<td>
										<html:select property="lddm" onchange="getqshLb()"
											styleId="lddm">
											<html:options collection="ldList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										���Һ�
									</th>
									<td>
										<html:select property="qsh" styleId="qsh">

											<html:options collection="ssList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<!-- ������ -->
								<tr>
									<th>
										ѧ��
									</th>
									<td>
										<html:text property="xh" styleId="xh" style="width:100px"></html:text>
									</td>
									<th>
										����
									</th>
									<td>
										<html:text property="xm" styleId="xm" style="width:80px"></html:text>
									</td>
									<th>

									</th>

									</td>
								</tr>
							</logic:equal>
						</tbody>
					</table>
				</div>
				<!-- ��ѯ���-->
				<div class="formbox">
					<logic:empty name="rs">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; <font color="red">δ�ҵ��κμ�¼��</font>
							</span>
						</h3>
					</logic:empty>
					<logic:notEmpty name="rs">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</span>
						</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="fyxx" value="all"
											onclick="chec()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="viewMore('view')">
										<td align="center">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="checkbox" name="pkV"
													value="<bean:write name="v"/>">
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								<!--���� -->
							</tbody>
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="msg">
			<input type="hidden" id="msg" value="${msg }" />

			<script type="text/javascript">
				alert($('msg').value);
			</script>

		</logic:present>
		<%--		<logic:present name="showgdnz">--%>
		<%--			<script language="javascript">--%>
		<%--				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 55;--%>
		<%--				document.getElementById("btn").style.width = "96%";--%>
		<%--				window.setInterval("initBTNTool('btn')",1);--%>
		<%--			</script>--%>
		<%--		</logic:present>--%>
		<%--		<logic:notPresent name="showgdnz">--%>
		<%--			<script language="javascript">--%>
		<%--				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;--%>
		<%--				document.getElementById("btn").style.width = "96%";--%>
		<%--				window.setInterval("initBTNTool('btn')",1);--%>
		<%--			</script>--%>
		<%--		</logic:notPresent>--%>
		<%--		<logic:present name="showgdbyxy">--%>
		<%--			<script language="javascript">--%>
		<%--				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 55;--%>
		<%--				document.getElementById("btn").style.width = "96%";--%>
		<%--				window.setInterval("initBTNTool('btn')",1);--%>
		<%--			</script>--%>
		<%--		</logic:present>--%>
		<script type="text/javascript">
		 function chec(){
             for(i=0;i<document.getElementsByName("pkV").length;i++){
      	         document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
             }
         }
        function batchDel(){
           var url = "/xgxt/XsgyglDispatch.do?method=xsZsXxPlDelete"; 
		   var RowsStr="!!";		  
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;
		   
		   if (RowsStr=="!!"){
			   alert("��ѡ��Ҫɾ���ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		
		   if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
			  return false;
		   }
<%--		   else{		     --%>
<%--			  url += "&toHistory=no";			 --%>
<%--		   }	   --%>
	       refreshForm(url);          
       }
  function batchTs(){
<%--           var url = "/xgxt/XsgyglDispatch.do?method=xsZsXxPlDelete"; --%>
		   var RowsStr="!!";		  
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;		   
		   if (RowsStr=="!!"){
			   alert("��ѡ��Ҫ���޵�ѧ����¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		   if (!confirm("ȷ��Ҫ����ѡ��ѧ���������޲�����\n\n���\'ȷ��\'����ס����Ϣ����ת����ʷ���У�\n\nͬʱѧ��ס����Ϣ���и�����¼����ɾ����\n\n���\'ȡ��\'����ֹ���޲�����")){
			  return false;
		   }
		   document.getElementById("tsFlag").value="plts";//��������������
<%--		   else{--%>
<%--		      url += "&toHistory=yes";			 --%>
<%--		   }--%>
           if($("tssjv")){
              $("tssjv").value=getCurrDate();
           }
		   viewTempDiv('ѧ������','clinDiv',300,180);	   
	       //refreshForm(url);          
       }
       
       function xssjTb(){
       		if (confirm("ȷ��Ҫ�ӽӿڻ�ȡ��������������ݣ�\n ��ȷ���Ѿ����ú�ӭ��ϵͳ�Ľӿ�\n���\'ȷ��\'������")){
			 	refreshForm("/xgxt/XsgyglDispatch.do?method=xssjTb");	
			 	showTips('����ͬ���У����Ժ�');    	  
		   }  
      	}
                  
    function tohis(){
            var clinStr = "�˹��ܽ�ʵ�֣���ĳ�꼶��<bean:message key="lable.xsgzyxpzxy" />��רҵ��༶Ϊ��λ��\n\n���õ�λ��ѧ��ס����Ϣ����ת�Ƶ�ס����ʷ��Ϣ���У�\n\nע��ת�Ƶ�ͬʱ����ոõ�λ�ڵĵ�ǰס����Ϣ��";
            var nj = document.forms[0].nj.value;
            var xy = document.forms[0].xy.value;
            var zy = document.forms[0].zy.value;
            var bj = document.forms[0].bj.value;
            var pycc = "";
            if($("pycc")){
            	pycc = $("pycc").value;
            }
            var xymc = "";
            var zymc ="";
            var bjmc = "";
            xymc = document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;
            if(document.forms[0].zgdzdxSssZs.value!='true'){
            zymc = document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
            }
            bjmc = document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
            var confirmTxt = "";
            
            if (nj==""&&xy==""
                      &&zy==""
                      &&bj==""
                      &&pycc==""){
             alert(clinStr+"\n\n��ѡ��Ҫת��ס����Ϣ���꼶��<bean:message key="lable.xsgzyxpzxy" />��רҵ��༶��");
             return false;
            }else{
                if(nj==""){
                   alert("��ѡ���꼶!");
                   return false;
                }
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
			    if(pycc!=""&&pycc!="null"){
			       confirmTxt += "( \'"+pycc+"\' )";
			    }
			    confirmTxt += " Ϊ��λ�����õ�λ��ѧ��ס����Ϣ\n����ת�Ƶ�ס����ʷ��Ϣ���У�"; 					        		        
		     }
		    if(confirm(confirmTxt)){
             if($("tssjv")){
               $("tssjv").value=getCurrDate();
             }		     
		     viewTempDiv('��������','clinDiv',300,180);
		     document.getElementById("tsFlag").value="ztts";//��������	   		      
		    }else{
		       return false;
		    }		   
	    }	    
function gdnz_dormDataExp(url){
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}	
function closeToSave() {
    var tssj = "";
    var sfsf = $('select_sfsf').value;
    if($("tssjv")) {
        tssj = $("tssjv").value;
    } 
    if($("tsFlag")){
        var tsFlagV = $("tsFlag").value;
        if(tsFlagV=="plts"){//��������������   
             $("clinBtnSave").disabled=true;     
	         refreshForm("/xgxt/XsgyglDispatch.do?method=xsZsXxPlDelete&toHistory=yes&tssjv="+tssj+"&sfsf="+sfsf);        
        }else if(tsFlagV=="ztts"){//��������			 
		     var dd_html = "";
		     dd_html += "<div><td height='60' align='center'><font color='red'>�������ڴ����У����Ժ�......<br><br></div>";		     
			 hiddenMessage(false,true);//����ʱ������
	         showDiv(dd_html, 300, 120);
             $("clinBtnSave").disabled=true;
		     refreshForm("/xgxt/XsgyglDispatch.do?method=xsZsXxToHis&tssjv="+tssj+"&sfsf="+sfsf);			         
        }
    }   
}	
var b = false;;
if(document.getElementById('rsTable')){
	for (i=0; i<document.getElementById("rsTable").rows[0].cells.length; i++){
  		if(document.getElementById("rsTable").rows[0].cells[i].id == "xh"){
  			b = true;
  			break;
  		}
  	}
  	if (b) {
  		for (j=1; j<document.getElementById("rsTable").rows.length; j++){
  		    var xhTd = document.getElementById("rsTable").rows[j].cells[i];
  		    var xhStr = xhTd.innerText.replace(/^\s+/g,"").replace(/\s+$/g,"").replace(/\n/g,"");
  		    var html_content = "<a href=\"javascript:queryOne('"+xhStr+"');\">"+xhStr+"</a>";
  			xhTd.innerHTML = html_content;
  		}
  	}
}	 
function queryOne(xh) {
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showTopWin(url, 800, 600);
}
		</script>
	</body>
</html>
