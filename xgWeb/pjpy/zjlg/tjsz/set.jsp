<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
	<script language="javascript">	
	function allFilleds(val) {
		var arrayList = val.split('-');
		if(document.getElementById(arrayList[0]).value==''){
			alert("ѧ�겻��Ϊ��!");
			return false;
		}
		if($("szlx").value !="xjbj" && $("szlx").value !="ylxfb"){
			if(document.getElementById(arrayList[5]).value==''){
				alert("�������󲻵�Ϊ��!");
				return false;
			}
		}
		if(document.getElementById(arrayList[1]).value==''){
			alert("��������Ϊ��!");
			return false;
		}
		if(document.getElementById(arrayList[2]).value==''){
			alert("��������Ϊ��!");
			return false;
		}
		if(document.getElementById(arrayList[1]).value=="sfyxbj"){
			if(document.getElementById(arrayList[4]).value==""){
				alert("��������Ϊ��!");
				return false;
			}
		}else{
			if(document.getElementById(arrayList[3]).value==""){
				alert("��������Ϊ��!");
				return false;
			}
		}
		
	 var val =  $("val");
	 if(document.getElementById(arrayList[3]).value!=""){
		 var valV =val.value.replace(/[^(\d|\.)]/g,'');
		 if(!valV.match(/\d+/g)){		
			alert("����ֵ��Ϊ�������͵����ݣ�");
			$("val").focus()
			return false;
		 }
	 }
	 if($("zdm").value=="wmqsgs"||$("zdm").value=="ajqsgs"){
	     if(val.value.indexOf(".")!=-1){
	       alert("���������ֵ���󣬲���ΪС����");
	       return false;
	     }
	 }
	 if($("zdm").value=="bjgl"||$("zdm").value=="dz"||$("zdm").value=="dypm"||$("zdm").value=="zypm"||$("zdm").value=="pm"){
	 	if(val.value > 100){
		 	alert("���������Ϊ�ٷֱȣ����ܴ���100��");
		 	return false;
	 	}
	 }
	 var zdmc = document.forms[0].zdm.options[document.forms[0].zdm.selectedIndex].text;
     refreshForm("/xgxt/zjlgPjpy.do?method=tjsz&type=save");
     if($("buttonSave")){
        $("buttonSave").disabled=true;
     }
          if($("search_go")){
        $("search_go").disabled=true;
     }
          if($("buttonDel")){
        $("buttonDel").disabled=true;
     }
	}
	
	function searchTj(){
		var xn = $("xn").value;
		var szlx = $("szlx").value;
		if(xn == ""){
			alert("ѧ�겻��Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if(szlx == "jxj"){
			if($("jxjdm").value == ""){
				alert("��ѧ�����Ʋ���Ϊ�գ���ȷ�ϣ���");
				return false;
			}
		}
		if(szlx == "rych"){
			if($("jxjdm").value == ""){
				alert("�����ƺ����Ʋ���Ϊ�գ���ȷ�ϣ���");
				return false;
			}
		}
	  allNotEmpThenGo('/xgxt/zjlgPjpy.do?method=tjsz');
	  if($("buttonSave")){
        $("buttonSave").disabled=true;
      }
          if($("search_go")){
        $("search_go").disabled=true;
      }
          if($("buttonDel")){
        $("buttonDel").disabled=true;
      }		
	}
	
	function delTj(){
		if (curr_row == null) {
			alert("��ѡ��Ҫɾ��������,����һ�м��ɣ�");
			return false;
		}
	  if(confirm("ȷ��Ҫɾ����ѡ��¼��")){
	     var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
	     refreshForm("/xgxt/zjlgPjpy.do?method=tjsz&type=del&pk="+pk);
	     if($("buttonSave")){
            $("buttonSave").disabled=true;
         }
         if($("search_go")){
            $("search_go").disabled=true;
         }
         if($("buttonDel")){
            $("buttonDel").disabled=true;
         }			     
	  }
	}
	
	function chZdm(value){
		if(value == "sfyxbj"){
			$("tj").value="=";
			$("tj").disabled=true;
			$("val").style.display="none";
			$("sfyxbj").style.display = "";
		}else{
			$("tj").value="";
			$("tj").disabled=false;
			$("val").style.display="";
			$("sfyxbj").value = "";
			$("sfyxbj").style.display = "none";
		}
	}
	function tjShow(){
		var value = $("zdm").value;
		if(value == "sfyxbj"){
			$("tj").value="=";
			$("tj").disabled=true;
			$("val").style.display="none";
			$("sfyxbj").style.display = "";
		}else{
			$("tj").value="";
			$("tj").disabled=false;
			$("val").style.display="";
			$("sfyxbj").style.display = "none";
			$("sfyxbj").value = "";
		}
	}
	</script>
	</head>
	<body onload="tjShow()">
		<html:form action="/zjlgPjpy" method="post">
			<input type="hidden" name="szlx" id="szlx" value="<bean:write name="szlx"/>"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_sc" id="buttonDel" onclick="delTj()">ɾ������</a></li>
					</ul>
				</div>
				</div>
			</logic:equal>
			<div class="rightcontent">
				<div class="searchtab">		
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<td colspan="6">
									<logic:equal name="szlx" value="rych">
										<input type="radio" name="cjlx" value="0" id="cjlx" 
											onclick="refreshForm('/xgxt/zjlgPjpy.do?method=tjsz&szlx=rych');" checked />
											�����ƺ�
									</logic:equal>
									<logic:notEqual name="szlx" value="rych">
										<input type="radio" name="cjlx" value="0" id="cjlx" 
											onclick="refreshForm('/xgxt/zjlgPjpy.do?method=tjsz&szlx=rych');"/>
											�����ƺ�
									</logic:notEqual>
									<logic:equal name="szlx" value="xjbj">
										<input type="radio" name="cjlx" value="1" id="cjlx" 
											onclick="refreshForm('/xgxt/zjlgPjpy.do?method=tjsz&szlx=xjbj');" checked/>
											�Ƚ��༶
									</logic:equal>
									<logic:notEqual name="szlx" value="xjbj">
										<input type="radio" name="cjlx" value="1" id="cjlx" 
											onclick="refreshForm('/xgxt/zjlgPjpy.do?method=tjsz&szlx=xjbj');"/>
											�Ƚ��༶
									</logic:notEqual>
									<logic:equal name="szlx" value="jxj">
										<input type="radio" name="cjlx" value="2" id="cjlx" 
											onclick="refreshForm('/xgxt/zjlgPjpy.do?method=tjsz&szlx=jxj');" checked/>
											��ѧ��
									</logic:equal>
									<logic:notEqual name="szlx" value="jxj">
										<input type="radio" name="cjlx" value="2" id="cjlx" 
											onclick="refreshForm('/xgxt/zjlgPjpy.do?method=tjsz&szlx=jxj');" />
											��ѧ��
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>ѧ��</th>
								<td><html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select></td>	
									<logic:equal name="szlx" value="rych">
									<th>�����ƺ�</th>
									<td><html:select property="jxjdm" styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="dm"
												labelProperty="mc" />
									</html:select></td>
									<th></th><td></td>
									</logic:equal>
									<logic:equal name="szlx" value="jxj">
									<th>��ѧ�����</th>
									<td><html:select property="jxjlb" styleId="jxjlb" onchange="getJxjList(this.value);">
										<html:option value=""></html:option>
										<html:options collection="jxjlbList" property="jxjlbdm"
												labelProperty="jxjlbmc" />
									</html:select></td>
									<th>��ѧ��</th>
									<td><html:select property="jxjdm" styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
									</html:select></td>
									</logic:equal>					
							</tr>
							
							<tr>
								<th>����</th>
								<td colspan="5"><html:select property="zdm" styleId="zdm" onchange="chZdm(this.value)">
									<html:option value=""></html:option>
									<html:options collection="zdList" property="zdmc"
											labelProperty="zdsm" />
									</html:select>
									
									<logic:equal name="szlx" value="ylxfb">
									<html:select property="tj" styleId="tj" style="width:120px">
											<html:option value=""></html:option>
											<html:option value="&gt;=">
												���ڻ����
											</html:option>
									</html:select>
									</logic:equal>
									<logic:notEqual value="ylxfb" name="szlx">
										<html:select property="tj" styleId="tj" style="width:120px">
											<html:option value=""></html:option>
											<html:option value="&gt;=">
												���ڻ����
											</html:option>
											<html:option value="=">
												����
											</html:option>
											<html:option value="&gt;">
												����
											</html:option>
											<html:option value="&lt;">
												С��
											</html:option>
											<html:option value="&lt;=">
												С�ڻ����
											</html:option>
									</html:select>
									</logic:notEqual>
									<html:text property="val" style="width:120px" onkeypress='return sztzNumInputValue(this,6,event)' />
									<html:select property="sfyxbj" styleId="sfyxbj" style="width:120px;display:none">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<logic:equal name="writeAble" value="yes">			
									<button type="button"  id="buttonSave" onclick="allFilleds('xn-zdm-tj-val-sfyxbj-jxjdm')">
										�������
									</button>
								</logic:equal>
								<button type="button" id="search_go" onclick="searchTj();">
									�� ѯ
								</button>
								 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									�� ��
								 </button>
								</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				
				<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue"></font> 
						</span>
					</h3>
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">					
									<td onclick="tableSort(this)">
										����
									</td>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td align="left">
										<input type="hidden" name="pk" value="<bean:write name="s" property="pk"/>" />
										<bean:write name="s" property="tjmc" />
										<bean:write name="s" property="tjlx" />
										<bean:write name="s" property="tjz" />
									</td>
								</tr>
							</logic:iterate>
						</table>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>

				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
