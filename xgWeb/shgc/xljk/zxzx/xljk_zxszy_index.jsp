<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
 <script type="text/javascript">   

 var   Rows=new   Array(); //����ѡ�е��ж���   
 var   ShiftStartRow=""; //Shift��ѡʱ�洢��ʼ�ж���   
    
  //ѡ��������   
  function   onfocusit(){   
  	iRow=window.event.srcElement;   
  do{   
  	iRow=iRow.parentElement; 
  }	while(iRow.tagName!='TR')   
    
  //Ctrl��ѡ   
  if(event.ctrlKey){   
  		var   j=-1;   
  		for(i=0;i<Rows.length;i++){   
  			if(iRow==Rows[i]){
  			//	alert("a");
 				//alert(Rows[i].getElementsByTagName("input")[0].value);   
  				j=i;   
  				break;   
  			}   
  		}   
  		if(j!=-1){    //j==0
  			for(i=j;i<Rows.length-1;i++){   
  			Rows[i]=Rows[i+1];
  			//alert("b");
  			//alert(Rows[i].getElementsByTagName("input")[0].value);
 			}   
  			Rows.length=Rows.length-1;   
 	 	}else{   
  			Rows[Rows.length]=iRow;   
  		}   
 	 		ShiftStartRow=iRow;   
  	}else{	
		if (Rows.length!=0){
			for (i=0; i<Rows.length; i++){	
				if (Rows[i].tagName.toLowerCase() == "tr") {
					Rows[i].style.backgroundColor = "#FFFFFF";
						// alert("aa");  
						// var kk=document.forms[0].xn_id.value;
						//document.
  	 					//alert(Rows[i].getElementsByTagName("input")[0].value);
	    		}
			}
		}
		Rows.length=1;
		Rows[0]=iRow;
//		ShiftStartRow=iRow;
	}
	 changeColor(iRow);
}
	
	
  function changeColor(E){   
	  for(var i=1;i<E.parentElement.rows.length;i++){   
	  	 E.parentElement.rows(i).style.backgroundColor="#FFFFFF"; 
	  }   
	  for(i=0;i<Rows.length;i++){   
	  	Rows[i].style.backgroundColor="#EEEEEE";   
	  }   
  }
  
  function pipei() {
	   var len=Rows.length;
	   var str="";
	   if(Rows.length==0){
	   		alert("������ѡ��һ�У�");
	   		return false;
	   }
	   for(i=0;i<Rows.length;i++){
	  		str=str+Rows[i].getElementsByTagName("input")[0].value+(i==Rows.length-1?"":",");
	  	}
	  		showTopWin('/xgxt/xljk_zxsxx_view.do?doType=View_Zxszy&xn_id='+str,600,520);
  }
  
  function view() {
  	 	for(i=0;i<Rows.length;i++){
  			var xn_id=Rows[i].getElementsByTagName("input")[0].value;
  			showTopWin('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl&doType=View&xn_id='+xn_id,700,450);
  		}
  }
  
  function del(){
  		var len=Rows.length;
   		var str="";
   		if(Rows.length==0){
   			alert("������ѡ��һ�У�");
   			return false;
   		}else {
			if (confirm("ȷ��Ҫɾ������������")) {
			underDealWith();
			for(i=0;i<Rows.length;i++){
  				//alert(Rows[i].getElementsByTagName("input")[0].value);
  				str=str+Rows[i].getElementsByTagName("input")[0].value+(i==Rows.length-1?"":",");
  			}
  			refreshForm('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl&doType=Del&xn_id='+str);
			return true;
			} 
			else {
				return false;
			}
		}
  }
  
	function ppzxs() {
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		var arrId = "";
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				arrId += checkBoxArr[i].value + "!!@@!!";
				flag = true;
			}
		}
		
		if(!flag){
			alert("�빴ѡҪ���������");
			return false;
		}
		
		$("arrId").value = arrId;
		
		showTopWin('/xgxt/xljk_zxsxx_view.do?doType=View_Zxszy',600,520);
	}
  
	//�����ύ
	function sumitInfo(url,doType){
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		
		if(flag){
			url+="&doType="+doType;
			if (confirm("ȷ��Ҫɾ������ѡ������?")) {
				showTips('���������У���ȴ�......');
				refreshForm(url);
			}
		}else{
			alert("�빴ѡҪ���������");
			return false;
		}
	}
  </script>   
 	</head> 
	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������ - ������ѯ - ԤԼ��Դ����</a>
			</p>
		</div>
	
		<html:form action="/xljk_zxszyAtion" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="arrId" name="arrId" value="" />
			
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="showTopWin('/xgxt/xljk_zxszy_add.do?act=xljk_zxszygl','500','410');"
									class="btn_zj">�� �� �� Դ</a>
							</li>
							<%-- �ǹ��ݴ�ѧ --%>
							<logic:notEqual name="xxdm" value="11078">
								<li>
									<a href="#"
										onclick="del();"
										class="btn_sc"> ɾ �� </a>
								</li>
							</logic:notEqual>
							<%-- ���ݴ�ѧ --%>
							<logic:equal name="xxdm" value="11078">
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl','del');"
										class="btn_sc"> ɾ �� </a>
								</li>
							</logic:equal>
							<%-- �ǹ��ݴ�ѧ --%>
							<logic:notEqual name="xxdm" value="11078">
								<li>
									<a href="#"
										onclick="pipei();"
										class="btn_xg">ƥ����ѯʦ</a>
								</li>
							</logic:notEqual>
							<%-- ���ݴ�ѧ --%>
							<logic:equal name="xxdm" value="11078">
								<li>
									<a href="#"
										onclick="ppzxs();"
										class="btn_xg">ƥ����ѯʦ</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="refreshForm('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									�ص�
								</th>
								<td>
									<html:select property="dd_dm" style="width:100px"
										styleId="dd_dm">
										<html:option value=""></html:option>
										<html:options collection="ddList" property="DMH"
											labelProperty="DMMC" />
									</html:select>
								</td>
								<th>
									ʱ���
								</th>
								<td>
									<html:text styleId="dateF" property="rq"
										onclick="return showCalendar('dateF','y-mm-dd');"
										readonly="readonly" />
								</td>
								<th>
									��ѯʦ
								</th>
								<td>
									<html:select property="zxxbh" style="width:100px"
										styleId="sjd_dm" onchange="">
										<html:option value=""></html:option>
										<html:options collection="zxxList" property="zxxbh"
											labelProperty="zxxxm" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> <logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������.</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr>
									<%-- ���ݴ�ѧ --%>
									<logic:equal name="xxdm" value="11078">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" />
										</td>
									</logic:equal>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this);" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" offset="0">
									<tr style="cursor:hand" onclick="onfocusit()"
										ondblclick="view()">
										<%-- ���ݴ�ѧ --%>
										<logic:equal name="xxdm" value="11078">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal"
													value="<bean:write name="s" property="XN_ID"/>" />
											</td>
										</logic:equal>
										<td>
											<input type="hidden" id="xn_id" name="xn_id"
												value="<bean:write name="s" property="XN_ID"/>" />
											<bean:write name="s" property="RQ" />
										</td>
										<td>
											<bean:write name="s" property="SJD" />
										</td>
										<td>
											<bean:write name="s" property="DD" />
										</td>
										<td>
											<bean:write name="s" property="ZXXXM" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
		<logic:notEmpty name="message">
			<logic:equal value="del_success" name="message">
				<script type="text/javascript">
						alert("ɾ���ɹ�!");
				</script>
			</logic:equal>
			<logic:equal value="del_fail" name="message">
				<script type="text/javascript">
						alert("ɾ��ʧ��!");
				</script>
			</logic:equal>
			<logic:equal value="insert_success" name="message">
				<script type="text/javascript">
						alert("����ɹ�!");
				</script>
			</logic:equal>
			<logic:equal value="insert_fail" name="message">
				<script type="text/javascript">
						alert("����ʧ��!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

