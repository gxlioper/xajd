<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/nbzy/nbzyJs.js"></script>
<script type="text/javascript">
function rychAdd(){
   var url = "/xgxt/zjlgPjpy.do?method=rychAdd";
   showTopWin(url,"820","530");
}
function rychModi(act){
    if (curr_row==null || curr_row=='') {
		alert("��ѡ��Ҫ�޸ĵļ�¼��\n��������Ӧ���У�");
		return false;
    }
    var url = "/xgxt/zjlgPjpy.do?method=rychModi&act="+act+"&pkValue=";
    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += pk;
    showTopWin(url,"820","530");
}
 function rychDel(){
          var url = "/xgxt/zjlgPjpy.do?method=rychDel";
		   var RowsStr="!!";		  
		   for (i=0; i<document.getElementsByName("pk").length; i++){
	    	  if(document.getElementsByName("pk")[i].checked){
	    		 RowsStr+=document.getElementsByName("pk")[i].value+"!!";
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
	       refreshForm(url);          
       }
</script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/zjlgPjpy" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - �����ƺ����� - ��������ѯ</a>
				</p>
		</div>
		<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
				<logic:equal value="xx" name="userType">
					<li><a href="#" class="btn_zj" id="btn_add" onclick="rychAdd()">����</a></li>
				</logic:equal>
					<li><a href="#" class="btn_xg" id="btn_modi" onclick="rychModi('modi');">�޸�</a></li>
				<logic:equal value="xx" name="userType">
					<li><a href="#" class="btn_sc" id="btn_del" onclick="rychDel();">ɾ��</a></li>
					<li><a href="#" class="btn_dr" onclick="impAndChkData();">����</a></li>
				</logic:equal>
					<li><a href="#" class="btn_dc" onclick="dataExport()">����</a></li>								
				</ul>
			</div>
			</div>
		</logic:equal>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="tableName" name="tableName"
			value="<bean:write name="tableName" scope="request"/>" />
		<input type="hidden" id="realTable" name="realTable"
			value="<bean:write name="realTable" scope="request"/>" />
		<input type="hidden" id="userType" name="userType" value="${userType}" />
		<input type="hidden" id="delPk" name="delPk" value="" />
			<div class="searchtab">
			<table width="100%" class="">
				<tbody>
					<tr>
						<th>�꼶</th>
						<td><html:select property="nj" styleId="nj"
								onchange="initZyList();initBjList()" styleClass="select"
								style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select></td>
						<th>ѧ��</th>
						<td><html:select property="xn"  styleClass="select"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select></td>
						<th>�����ƺ�</th>
						<td><html:select property="rychdm" styleId="rychdm">
								<option value=""></option>
								<html:options collection="rychList" property="dm"
									labelProperty="mc" />
							</html:select></td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td><html:text property="xh" styleId="xh" 
								styleClass="inputtext"></html:text></td>							
							<th>����</th>
							<td><html:text property="xm" styleId="xm" 
							style="width:100px" styleClass="inputtext"></html:text>							
							</td>
							<th></th>
							<td></td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td><html:select property="xydm" onchange="initZyList();initBjList()"
								styleId="xy" style="width: 150px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select></td>
						<th>רҵ</th>
						<td><html:select property="zydm" onchange="initBjList()"
								 styleId="zy" style="width: 150px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select></td>
						<th>�༶</th>
						<td><html:select property="bjdm" styleId="bj" style="width: 150px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<input type="hidden" name="go" value="go" />
						<button type="button" id="search_go" onclick="refreshForm('zjlgPjpy.do?method=rychDefault');document.getElementById('search_go').disabled=true;">
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
								��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
							</span>
						</h3>
						<table width="99%" id="rsTable" class="datelist">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="rychModi('view')">
									<td align=center>
										<input type="checkbox" id="pk" name="pk"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
				</logic:notEmpty>
				
				<div id="tmpdiv"></div>
			</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
	 			alert(''+ document.getElementById('failInfo').value);
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
	</logic:present>
</body>
</html>
