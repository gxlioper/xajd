<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>	
		 <SCRIPT LANGUAGE="JavaScript"> 
<!-- Hide 
function killErrors() { 
return true; 
} 
window.onerror = killErrors; 
// --> 
</SCRIPT>
		<script type="text/javascript">
			 
			function getFdyInfo(act){
				var xxdm = document.getElementById("xxdm").value;
				var url = "";
				if(xxdm == "10497"){ //�人����ѧ
					url = '/xgxt/fdyxxOne_whlg.do?act=' + act + '&pk=' + curr_row.cells[0].getElementsByTagName('input')[0].value;
				}else if("view" == act){
					url = '/xgxt/fdyxxOne.do?act=' + act + '&operation=view' + '&pk=' + curr_row.cells[0].getElementsByTagName('input')[0].value;
				}else {
					url = '/xgxt/fdyxxOne.do?act=' + act + '&pk=' + curr_row.cells[0].getElementsByTagName('input')[0].value;
				}
				showTopWin(url,800,600); 
			}
			
			function modiFdyInfo(){
				if(curr_row == null){
					alert('��ѡ��Ҫ�޸ĵ���!');
					return false;
				}
				getFdyInfo('modi');
			}
			
			function addFdyInfo(){
				var xxdm = document.getElementById("xxdm").value;
				var url = "";
				if(xxdm == "10497"){ //�人����ѧ
					url = '/xgxt/fdyxxOne_whlg.do?act=add';
				}else{
					url = '/xgxt/fdyxxOne.do?act=add';
				}
				showTopWin(url,800,600);
			}
			
			function checkXxdm(){ //�人����ѧ
				var xxdm = document.getElementById("xxdm").value;
				if(xxdm == "10497"){
					document.getElementById("tableName").value = "view_whlgdx_fdyxx";
				}
			}
			
			function saveTgZyhxxk() {
					if($('select_zdmList').value==''){
						alert("��ѡ�������û���");
						return false;
					}
					if($('select_dwList').value==''){
						alert("��ѡ���û����ڵ�λ");
						return false;
					}
					document.forms[0].go.value='go';
					refreshForm('/xgxt/fdyxx.do?act=tgyhxx');
			}
			
			function bbtj(){
				document.forms[0].action = "/xgxt/wxsz_sjtj.do";
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
			
			function tgZyhxxk(){
				var checkBoxArr = document.getElementsByName("checkVal");
				var yzchk=true;
				for(var i=0;i<checkBoxArr.length;i++){
						if(checkBoxArr[i].checked){
							yzchk =false;
							break;
						}
				}
				if(yzchk==true){
					alert("�빴ѡ������ת���û���Ľ�ʦ");
					return;
				}
				viewTempDiv("ת���û���ѡ��","viewYhz",450, 190);
		}
		</script>
	</head>
	<body onload="xyDisabled('xy');checkXxdm();">
		<div class="tab_cur">
				<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
				</p>
			</div>
		<html:form action="/data_search" method="post">
		 <!-- ��ť -->
		 <!-- �ж�дȨ -->
		 <div class="toolbox">
		 <logic:equal value="yes" name="writeAble" scope="request">	
		 <div class="buttonbox">
		    <ul>
			<li> <a href="#" onclick="addFdyInfo();return false;" class="btn_zj"> ���� </a> </li>
		      <li> <a href="#" onclick="modiFdyInfo();return false;" class="btn_xg"> �޸� </a> </li>
			<li> <a href="#" onclick="sumitInfo('/xgxt/fdyxx.do?act=del','del');return false;" class="btn_sc"> ɾ�� </a> </li>
			<li> <a href="#" onclick="impAndChkData();return false;" class="btn_dr"> ���� </a> </li>
			<li> <a href="#" onclick="dataExport();return false;" class="btn_dc"> ���� </a> </li>
		   	<logic:equal value="yes" name="qx" scope="request">
			<li> <a href="#" onclick="tgZyhxxk();return false;" class="btn_sx"> ������û���Ϣ�� </a> </li>
			</logic:equal>
			<!-- ����ְҵ -->
			<logic:equal name="xxdm" value="12702">
				<li> <a href="#" onclick="bbtj();return false;" class="btn_tj"> ����ͳ�� </a> </li>
			</logic:equal>
			<!-- ����ְҵend -->
			<!-- ������ְҵ -->
			<logic:notEqual name="xxdm" value="12702">
				<%--<li> <a href="#" onclick="expTab('rsTable','˼������ - ������Ϣ�б�','');return false;" class="btn_dy"> ��ӡ�б� </a> </li>
			--%></logic:notEqual>
			<!-- ������ְҵend -->
		    </ul>
		 </div>
		 </logic:equal>
		 <!-- û�ж�дȨ -->
		<logic:equal value="no" name="writeAble" scope="request">	
			 <div class="buttonbox">
			<ul>
				<li> <a href="#" onclick="dataExport()" class="btn_dc"> ���� </a> </li>
				<%--<li> <a href="#" onclick="expTab('rsTable','˼������ - ������Ϣ�б�','');return false;" class="btn_dy"> ��ӡ�б� </a> </li>
			--%></ul>
			</div>
		</logic:equal>
			<input type="hidden" id="tableName" name="tableName"
				value="view_fdyxxbAllinfo" />
			<input type="hidden" id="realTable" name="realTable"
				value="fdyxxb" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="session"/>" />	
			
			<div class="searchtab">
			<table width="100%" border="0">
				 <tfoot>
				 	<tr>
				 		<td colspan="6" >
							<input type="hidden" name="go" value="a" />
							<div class="btn">
		             			 <button type="button" class="btn_cx" id="search_go" 
									onclick="document.forms[0].go.value='go';refreshForm('/xgxt/fdyxx.do?act=persInfo')">
									�� ѯ
								 </button>
		                         &nbsp;&nbsp;&nbsp;&nbsp;
		                         <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              	           	 �� ��
		                         </button>
		                    </div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<th align="left">
							����
						</th>
						<td>
							<html:select property="xydm" styleId="xy" style="width:150px"
								onmouseover="">
								<html:option value=""></html:option>
								<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc" />
							</html:select>
						</td>
						<th>
							ְ��
						</th>
						<td>
							<html:select property="zxm" styleId="zxm" style="width:150px"
								onmouseover="">
								<html:option value=""></html:option>
								<html:options collection="zwList" property="zwdm"
									labelProperty="zwmc" />
							</html:select>
							
						</td>
						<th>
							�Ƿ�ϵͳ�û�
						</th>
						<td>
							<html:select property="sfyh" styleId="sfyh"  style="width:150px"
								onmouseover="">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
							
						</td>
					</tr>
					<tr>
					<th>
						����
					</th>
					<td>
						 <logic:notPresent name = "xm">
					   		<input type="text" name="xm" style="width:150px"/>
					  	 </logic:notPresent>
				    	 <logic:present name = "xm">
				   			<input type="text" name="xm" value ="<bean:write name = "xm"/>" style="width:150px"/>
				   		 </logic:present>
					 </td>
					<logic:equal name="xxdm" value="10290" scope="session">
						<th>
						�ֹ��꼶
						</th>
						<td>
					    <html:select property="nj" style="width:150px" styleId="nj"
					    		onmouseover=""> 
	          					<html:option value=""></html:option>
	          					<html:option value="no">δ����</html:option>   
	          					<html:options collection="njList" property="nj" labelProperty="nj" /> 
	        			</html:select>
	        			</td>
	        			</logic:equal>
	        		   <th>
					   ѧ�������о�����
					   </th>
					   <logic:notPresent name = "xsgzyjfx">
					   <td colspan="3">
					   <input type="text" name="xsgzyjfx" />
					   </td>
					   </logic:notPresent>
					    <logic:present name = "xsgzyjfx">
					    <td>
					   <input type="text" name="xsgzyjfx" value ="<bean:write name = "xsgzyjfx"/>" style="width:150px"/>
					   </td>
					   </logic:present>
					   <logic:equal name="xxdm" value="11062">
					   <th>
					   �ش��ʧʱ��
					   </th>
					   <td>
					   	<input type="text" name="kssj" onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
							onclick="return showCalendar('kssj','y-mm-dd');"/> - 
						<input type="text" name="jssj" onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
							onclick="return showCalendar('jssj','y-mm-dd');"/>
					   </td>
					   </logic:equal>
					   <logic:equal name="xxdm" value="12702">
					   <th>
					    �������
					    </th>
					    <td>
					   		<html:select property="fdyz" styleId="fdyz" style="width:150px"
					   			onmouseover="">
					   			<html:option value=""></html:option>
								<html:options collection="fdyzList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					   </logic:equal>
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
			 		 <logic:notEmpty name="rs" >
								<font color="blue"> 
									��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">
			<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
			    	<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td  id="<bean:write name="tit" property="en"/>" 
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="left"
								ondblclick="getFdyInfo('view')">
								<td align="center">
										<input type="checkbox" id="checkVal" name="checkVal" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</td>
								<td>
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
						</tbody>
					</table>
					</div>
					<!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			  <!--��ҳ��ʾ-->
			</logic:notEmpty>
			</div>
			<logic:present name="del">
				<logic:equal value="ok" name="del">
					<script>
         				 alert("ɾ���ɹ���ɾ����ְ����Ϣ���������������û���Ϣɾ����������Ҫ������ϵ����Ա��ɾ������û�");
       				 </script>
				</logic:equal>
				<logic:equal value="no" name="del">
					<script>
         				 alert("ɾ��ʧ�ܣ�");
        			</script>
				</logic:equal>
			</logic:present>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ�.\n��ӵ��û�Ĭ������Ϊ:888888");
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
         				 alert("����ʧ�ܣ�");
        			</script>
				</logic:equal>
			</logic:present>
			<!-- ��ѯ�û����� -->
			<div style="display:none;" id="viewYhz">

				<table width='80%' class='formlist'>
				<thead>
					<tr>
						<th width='25%' colspan="2">
							------------------------------��ѡ��Ҫת����û���-----------------------------
						</th>
					</tr>
				</thead>
				<tbody>
					
					<tr>
						<th>
							�û���
						</th>
						<td>
							<html:select property="zdm" styleId="select_zdmList" style="width:120px;" >
								<html:option value=""></html:option>
								<html:options collection="yhzList" property="zdm"
									labelProperty="zmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							������λ
						</th>
						<td>
							<html:select property="dwdm" styleId="select_dwList" style="width:120px;" >
								<html:option value=""></html:option>
								<html:options collection="dwList" property="dwdm"
									labelProperty="dwmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
					
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class='btn'>
									<font color="red">ע�����û����Ѵ��ڸ��û�������²��Ḳ��ԭ���û�</font>
									<button type="button" onclick="saveTgZyhxxk()">
											ȷ��
									</button>
									<button type="button" onclick="hiddenMessage(true,true);return false;">
											�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		
	</body>
</html>
