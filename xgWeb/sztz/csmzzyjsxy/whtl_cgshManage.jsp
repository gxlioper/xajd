<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="bjDisabled('nj-xy-zy-bj');myxyDisabled('xy');removeXnXq();checkType()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/whtlSztz.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript">
		function showJx(){
			
			var len=jQuery("[name=pkV]:checked").length;
			
			if(len>0){ 
				tipsWindown("ϵͳ��ʾ","id:div_jx","350","150","true","","true","id");
			}else{
				alert("��ѡ����Ҫ��˵ļ�¼��");
				return false;
			}
		}
		
		function xmsh(flag){
			
			var jxlb=jQuery("#jxlb").val();
			
			if(jxlb==""){
				alert("����������ѡ��");
				return false;
			}else{
				batchCheck(flag);
			}
		
		}
		
		function changeXmList(){
			var xn=$("xn").value;
			var xq=$("xq").value;
			dwr.engine.setAsync(false);
			whtlSztz.getXmList(xn,xq,function(data){
				DWRUtil.removeAllOptions("xmid");			
				DWRUtil.addOptions("xmid",data,"xmid","xmmc");
			});
			dwr.engine.setAsync(true);
			document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value);
		}
		</script>
    <html:form action="/csmz_sztz.do?method=data_search" method="post">
		<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
	        <input type="hidden" name="userType" id="userType" 
		        value="<bean:write name="userType" scope="request"/>">	
		     <input type="hidden" name="userName" id="userName" 
		        value="<bean:write name="userName" scope="session"/>">
			<input type="hidden" id="pkVStr" name="pkVStr"
				value="" />	
<%--			<input type="hidden" id="kcxf"	name="kcxf" value="">--%>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">
				 <!-- ��ť -->
				 <div class="buttonbox">
				    <ul>
					  <li> <a href="#"  onclick="showJx();return false;" id="checkpass" class="btn_shtg"> ��� </a> </li>
				     
				    </ul>
				 </div>
				<div id="showDiv" style="display:none" align="center">
					<fieldset style="width:90%;height:90%">
						<legend>
							������ʾ��Ϣ
						</legend>

						<table class='buttontool' hight='100px' >
							<thead>
								<tr>
									<td colspan='2'>
										���÷ֿ۳�
									</td>
								</tr>
							</thead>
							<tr>
								<td align='right' width='40%'>
									<font color=red>*</font>�� �� ֵ��
								</td>
								<td align='left'>
									<input type='text' name='kcfv'>
								</td>
							</tr>
							<tfoot>
								<tr>
									<td colspan='2'>
										<button class='button2' id="kfbtnSave" onclick='savePoint()'>
											�ύ
										</button>
										&nbsp;&nbsp;
										<button id="kfbtnClose" onclick='closeClearPoint()' class='button2'>
											�ر�
										</button>
									</td>
								</tr>
							</tfoot>
						</table>
					</fieldset>
				</div>	
				<div class="searchtab">
				<table width="100%" border="0">
				      <tfoot>
				        <tr>
				          <td colspan="6">
				            <div class="btn">
				               <input type="hidden" name="go" value="a" />
				              <button class="btn_cx" id="search_go" 
				              	onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
				              	�� ѯ
				              </button>
				              &nbsp;&nbsp;&nbsp;&nbsp;
				              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
				              	�� ��
				              </button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					<tbody>
								<tr>
									<th align="left">
										ѧ��
									</th>
									<td>
										<html:select property="xn" style="width:100px" styleId="xn"
											onchange="changeXmList()">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xq" style="width:50px" styleId="xq"
											onchange="changeXmList()">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>										
										�꼶
									</th>
									<td>
										<html:select property="nj" style="width:50px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>								
								</tr>
								<tr>
									<th>
										ѧ��
									</th>
									<td>
										<html:text property="xh" style="width:90px"></html:text>
									</td>
									<th>
										����
									</th>
									<td>
										<html:text property="xm" style="width:90px"></html:text>
									</td>
									<th>
										���״̬
									</th>
									<td>
										<html:select property="yesNo" style="width:70px">
										    <html:option value=""></html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />											
										</html:select>	
									</td>	
								</tr>
								<tr>
									<th align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />(����)
									</th>
									<td>
										<html:select property="xydm" style="width:150px" styleId="xy"
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
										<html:select property="zydm" style="width:180px" styleId="zy"
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
										<html:select property="bjdm" style="width:180px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>		
								<tr>
									<th align="left" nowrap>
										��Ŀ����
									</th>
									<td>
										<html:select property="xmid" style="width:150px" styleId="xmid"
											onchange="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
											<html:options collection="xmList" property="xmid"
												labelProperty="xmmc" />
										</html:select>
									</td>
									<th>
										
									</th>
									<td>
										
									</td>
									<th>
										
									</th>
									<td>
										
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
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>

				<logic:notEmpty name="rs">
				  <table summary="" class="dateline" align="" width="100%" id="rsTable">
						<thead>
							<tr style="cursor:hand">
								<td title="ȫѡ">
									<input type="checkbox" name="fyxx" value="all" onclick="chec()">
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td align="center" id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);"
								style="cursor:hand;background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        "
								ondblclick="CheckDo()">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
				 </logic:notEmpty>	
				 </div>	
             
<%--					<button class="button2" onclick="allBjSh('/xgxt/csmz_sztz.do?method=all_check&bjdm='+document.forms[0].bjdm.value)"--%>
<%--								style="width:80px">--%>
<%--								ȫ��ͨ��--%>
<%--					</button>										--%>					  	
				 			<!-- ����ѡ��DIV -->
			<div id="div_jx" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>�������
								</th>
								<td>
									<html:select property="jxlb" styleId="jxlb" style="width:150px"
										>
										<html:option value=""></html:option>
										<html:options collection="jxList" property="jxid"
											labelProperty="jxm" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button name="ͨ��" onclick="xmsh('yes')">
											ͨ  ��
										</button>
										<button name="��ͨ��" onclick="xmsh('no')">
											��ͨ��
										</button>
										<button name="ȡ ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div> 											
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>		
    </body>
	<logic:equal name="autoChk" value="ok" scope="request">
		<script language="javascript">
                   alert("�Զ������ɣ�");
                   document.getElementById('search_go').click();	      
	    </script>
	</logic:equal>
	<logic:equal name="autoChk" value="no" scope="request">
		<script language="javascript">
                 alert("�Զ����ʧ�ܣ�");
                 document.getElementById('search_go').click();	      
 	    </script>
	</logic:equal>
<script type="text/javascript">
function checkType(){
   var userType = $("userType").value;
   if(userType=="xx"||userType=="admin"){
      if($("checkpass")){ $("checkpass").value="ѧУ���ͨ��";}
      if($("checknopass")){ $("checknopass").value="ѧУ��˲�ͨ��";}
   }else if(userType=="xy"){
      if($("checkpass")){ $("checkpass").value="<bean:message key="lable.xsgzyxpzxy" />���ͨ��";}
      if($("checknopass")){ $("checknopass").value="<bean:message key="lable.xsgzyxpzxy" />��˲�ͨ��";}
   }else{
      if($("checkpass")){ $("checkpass").value="�༶���ͨ��";}
      if($("checknopass")){ $("checknopass").value="�༶��˲�ͨ��";}
   }
}

function chec(){
  for(i=0;i<document.getElementsByName("pkV").length;i++){
    document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
  }
}
function CheckDo(){
    var	w = 600;
	var	h = 400;
	var realTable = document.getElementById("realTable").value;
	url = "/xgxt/csmz_sztz.do?method=tzcg_shView";	
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
}

function allBjSh(url){
    confirmTxt = "ȫ��ͨ����˽���ĳѧ�ꡢѧ����Ŀ\n���༶Ϊ��λ��������ͨ�����! \n";
    if(document.forms[0].xn.value==""){
		alert(confirmTxt+"��ѡ��ѧ�꣡");
		return false;
    }
    if(document.forms[0].xq.value==""){
        alert(confirmTxt+"��ѡ��ѧ�ڣ�");
        return false;
    }
    if (document.forms[0].bjdm.value == "") {
		alert("��ѡ��༶��");
		return false;
	}else{
	   confirmTxt = "ȫ��ͨ����˽��ķѽϳ���ʱ�䣬ȷ��Ҫ��ʼ�Զ������";
		if (confirm(confirmTxt)) {		
		    alert("���\"ȷ��\"��ʼ��ˣ�");		    
		    sztzShowTips('���ڽ���������ˣ����Ժ�......');
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	}	
}
function myxyDisabled(ele) {
    var xxdm = $("xxdm").value;
    if(xxdm!='11417'){
       var userT = document.getElementById("userType").value;
	   if (userT == "xy"||userT =="stu") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).disabled = true;
		}
	   }
	}
}

 function batchCheck(str){
           var userType = $("userType").value;
           var xxdm  = $("xxdm").value;
           var url = "/xgxt/csmz_sztz.do?method=plCheck&check="+str; 
		   var RowsStr="!!";		  
		   var userType = $("userType").value;		   
		   xyshDone = (str=="yes")?"ͨ��":"��ͨ��";
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }	    	  
		   }		   
		   if (RowsStr=="!!"){
			   alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   
		   getSztzData.getInfoEx2("csmz_tzcgb"," (xh||xmid||cyjs||jxlb) in ("+pkVArray+") and (xysh<>'ͨ��' or ( '13022'='"+xxdm+"' and fdysh<>'ͨ��' ) ) ",function(boolean){		       
		          if(boolean){
		               if(userType=="xx"||userType=="admin"){
		                   if(confirm("��ѡ�м�¼�У����¼��������\"��ͨ��\"��\"δ���\"�ļ�¼��\n\nȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��")){
		                       toSave(url,str)
		                       return false;  
		                   }
		               }else{
		                   var clin ="ȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��";
		                   if(userType=="xy"){
		                       if(xxdm=='13022'){//�㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" />
		                          clin = "��ѡ�м�¼�У��а༶���\"��ͨ��\"��\"δ���\"�ļ�¼��\n\nȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��";		                      
		                       }
		                   }
		                   if (confirm(clin)){
		                       toSave(url,str)
		                   }		                
		              }
		          }else{
		               if (confirm("ȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��")){
			              toSave(url,str)
		               }
		          }		      
		   });	             
  }
function toSave(url,str){
    var xxdm = $("xxdm").value;
    if(xxdm=='11417'){
       if(str=="yes"){
          refreshForm(url); 
       }else{
          showClearPoint(); 
       }
    }else{
       refreshForm(url); 
    }    
}
</script>
 <logic:present name="done">
	 	<logic:equal value="true" name="done">
	 		<script>
	 			alertInfo('�����ɹ���');
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="false" name="done">
	 		<script>
	 			alertInfo('����ʧ�ܣ�');
	 		</script>
	 	</logic:equal>
	 	</logic:present>
</html>	
