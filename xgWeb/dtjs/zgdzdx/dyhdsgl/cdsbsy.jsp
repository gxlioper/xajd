<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
		<script language="javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript">
		function dyhdsgl_sh(){
			var url ="/xgxt/zgdzdxXxwh.do?method=dyhdsgl_sh&xxk=cdsbsy&pkValue=";
			var pkValue ="";
		   		if (curr_row == null) {
					alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 700,500);
		    		return true;
		    	}
		}
		
	/*
	�������ͨ��
	*/
	 function pass(url){
	   var RowsStr="!!#!!";
	
	   for (i=0; i<document.getElementsByName("pk").length; i++){
		 if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	 }
	   }
	   document.forms[0].pkstring.value = RowsStr;
	
	   if (RowsStr=="!!#!!"){
		  alert("��ѡ��Ҫ������˵ļ�¼��");
		  return false;
	   }
	
	   if (!confirm("ȷ��Ҫͨ����ѡ��¼��")){
		  return false;
	   }
	   document.forms[0].action=url;
       document.forms[0].submit();
    }
    
    /*
	������˷��
	*/
    function notpass(url){
	var RowsStr="!!#!!";
	
	  for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	  }
	  }
	  document.forms[0].pkstring.value = RowsStr;
	
	  if (RowsStr=="!!#!!"){
		alert("��ѡ��Ҫ������˵ļ�¼��");
		return false;
	  }
	
	  if (!confirm("ȷ��Ҫ�����ѡ��¼��")){
		return false;
	  }
	document.forms[0].action=url;
    document.forms[0].submit();
   }
    
    
	function querygo(){
		 	document.forms[0].action = "/xgxt/zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=cdsbsy&go=go";
		 	document.forms[0].submit();
    }
    /*
	ȫ��ѡ��
	*/    
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }		
      /*
	����ɾ��
	*/
      function del(){
        var url = "/xgxt/zgdzdxXxwh.do?method=dyhdsgl_del&xxk=cdsbsy&czlx=del";
	    var RowsStr="!!#!!";
	
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		   return false;
    	}
	
	    if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
		   return false;
	    }
	    document.forms[0].action=url;
        document.forms[0].submit();
     }
      
		function bysjbxxbdel(doType){
		var url = "/xgxt/yxjzyjs.do?methodact=del&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		    } else {
			if (confirm("ȷ��Ҫɾ������������")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		
		function viewMoreinfo(doType){
		var url = "/xgxt/zgdzdxXxwh.do?method=dyhdsgl_save&xxk=cdsbsy&czlx=view&pkValue=";
		var pkValue ="";
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 //showOpenWindow(url, 750, 500);
		 showTopWin(url, 700, 500);
		 }
		}

		
		function dyhdsupdate(doType){
		var url ="/xgxt/zgdzdxXxwh.do?method=dyhdsgl_save&czlx=update&xxk=cdsbsy&pkValue=";
		var pkValue ="";
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         //alert(url);
		         showTopWin(url, 700, 500);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		
		
		
		
		function jyglxsjbxxbDataExport() {
	       document.forms[0].action = "/xgxt/jyglxsjbxxbDataExport.do?tableName=jygl_xsjbxxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		function yxjzyjsadd(){
			 var url = "/xgxt/yxjzyjs.do?act=add";
			 showTopWin(url, 700, 500);
		}
		</script>
	</head>
	<body>
		<html:form action="/zgdzdxXxwh.do" method="post">

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>˼����� - ����ά�� - ��Ա��ҹ���</a>
				</p>
			</div>

			<input type="hidden" id="isFdy" value="" />
			<input type="hidden" id="zyV" name="zyV" />
			<input type="hidden" id="bjV" name="bjV" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="method" name="method" value="cdsbsy" />
			<input type="hidden" name="pkstring" value="" />

			<div class="compTab">
				<div class="comp_title">
					<ul>
						<li id="001m"
							class="ha"
							onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=cdsbsy')">
							<a><span>�����豸ʹ��</span>
							</a>
						</li>
						<li id="002m"
							onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=zcgl')"
							class="xxk_off_m">
							<a><span>�ʲ�����</span>
							</a>
						</li>
						<li
							onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=xxzl')"
							class="xxk_off_m">
							<a><span>ѧϰ����</span>
							</a>
						</li>
						<li id="003m"
							onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=zljy')">
							<a><span>���Ͻ���</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<logic:equal value="xx" name="usertype">
							<li>
								<a href="#" class="btn_sh" onclick="dyhdsgl_sh();">���</a>
							</li>
						</logic:equal>

						<li>
							<a href="#" class="btn_zj"
								onclick="showTopWin('zgdzdxXxwh.do?method=dyhdsgl_save&xxk=cdsbsy&czlx=save',700,500)">����</a>
						</li>
						<li>
							<a href="#" class="btn_xg" onclick="dyhdsupdate();">�޸�</a>
						</li>
						<logic:notEqual name="userType" value="xy" scope="session">
							<li>
								<a href="#" class="btn_sc" onclick="del()">ɾ��</a>
							</li>
						</logic:notEqual>
					</ul>
				</div>
			</div>
			<div class="searchtab">
				<table width="100%" class="">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="btn_cx" id="search_go" onclick="querygo();">
										�� ѯ
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>

					<tbody>
						<tr>
							<th>
								ʹ�õ�λ
							</th>
							<td>
								<html:text property="sydw"></html:text>
							</td>
							<th>
								����������
							</th>
							<td>
								<html:text property="sqrxm"></html:text>
							</td>
							<th>
								ʹ��ʱ��
							</th>
							<td>
								<html:text property="sysj"
									onclick="return showCalendar('sysj','y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								ѧУ���
							</th>
							<td>
								<html:select property="xxsh">
									<html:option value=""></html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
									<html:option value="δ���">δ���</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="formbox">
				<logic:empty name="rs">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <font color="red">δ�ҵ��κμ�¼��</font> </span>
					</h3>
				</logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr>
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')" disabled="true"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" length="4">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:iterate id="tit" name="topTr" offset="6" length="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:iterate id="tit" name="topTr" offset="10" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="viewMoreinfo('view')">
									
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2" length="4">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="7" length="3">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="11" length="1">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=zgdzdxDtjsForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>

			<div id="tmpdiv1"></div>
			<div id="tmpdiv"></div>
			<logic:notEmpty name="delall">
				<logic:equal value="ok" name="delall">
					<script language="javascript">
						alert("ɾ���ɹ�");
						document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="no" name="delall">
					<script language="javascript">
						alert("ɾ��ʧ��");
						document.getElementById('search_go').click();
					</script>
				</logic:equal>
			</logic:notEmpty>
			<logic:present name="message">
				<input type="hidden" name="message" id="message"
					value="<bean:write name="message"/>" />
				<script language="javascript">
					alert($('message').value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript">

function openWins(){
	if (document.getElementById('jxjdm').value=='') {
		alert('��ѡ��ѧ�����!');
		return;
	} else {
		url = 'jxjrsdataexp.do?jxjdm=';
		url += document.getElementById('jxjdm').value;
		url += '&xydm=';
		url += document.getElementById('xy').value;
		url += '&zydm=';
		url += document.getElementById('zy').value;
		url += '&bjdm=';
		url += document.getElementById('bj').value;
		url += '&bmlb=';
		url += document.getElementById('dispFalg').value;
		url += '&nj=';
		url += document.getElementById('nj').value;
		window.open(url);
	} 
}
</script>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
