<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
function myViewMore(doType){
   var pkValue = "";
   var url = "/xgxt/XsGyGlLogic.do?method=sdCbXx_Modi&doType=";
   url += doType;
   url += "&pkValue=";
   if(doType=="add"){
      url +=pkValue;
      showTopWin(url,'600','400');
   }else {
      if(curr_row == null){
        alert("��ѡ��Ҫ���������ݣ�\n��������Ӧ���У�");
		return false;
      }
      pkValue = curr_row.getElementsByTagName("input")[0].value;     
      url +=pkValue;
      if(doType=="modi"){                        
         if(confirm("ȷ��Ҫ�޸ĸ����ݣ�")){
             showTopWin(url,'600','400');
         }else{
             return false;
         }
      }
      if(doType=="del"){
         if(confirm("ȷ��Ҫ��ɾ���������ݣ�")){
             refreshForm(url);
         }else{
             return false;
         }
      }
   }
}
function CbTj(url){   
    var confirmTxt = "����ˮ�糬���¶Ȼ��ܣ������ꡢ��Ϊ��λ����ͳ��";
    if(confirm(confirmTxt)){
    if(document.getElementById('nd').value==""){
		alert("��Ȳ���Ϊ�գ�");
		return false;
    }
    if(document.getElementById('yf').value==""){
        alert("�·��·ݲ���Ϊ�գ�");
        return false;
    }
    url+="&nd="+document.getElementById('nd').value;
    url+="&yf="+document.getElementById('yf').value;
    showTopWin(url,'600','700');
    return true;
 	}else{
 	return false;
    }
}	
</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��Ԣ���� - ��Ϣά�� - ��Ԣˮ��ѹ���</a>
			</p>
		</div>


		<html:form action="/XsGyGlLogic.do?method=xsGySdCbGl" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />



			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="myViewMore('add')" class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="myViewMore('modi')" class="btn_xg"> �޸�
								</a>
							</li>
							<li>
								<a href="#" onclick="myViewMore('del')" class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="dataExport()" class="btn_dc"> ���� </a>
							</li>
							<li>
								<a href="#"
									onclick="CbTj('/xgxt/XsGyGlLogic.do?method=sdCbXxTj')"
									class="btn_tj"> �¶Ȼ��ܱ� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<logic:notEqual value="11641" name="xxdm">
											<button class="btn_cx" id="search_go"
												onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsGyGlLogic.do?method=xsGySdCbGl')">
												�� ѯ
											</button>
										</logic:notEqual>
										<logic:equal name="xxdm" value="11641">
											<button class="btn_cx" id="search_go"
												onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsGyGlLogic.do?method=xsGySdCbGl&xh='+document.getElementById('xh').value)">
												�� ѯ
											</button>
										</logic:equal>

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
									���
								</th>
								<td>
									<html:select property="nd" style="width:100px" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									�·�
								</th>
								<td>
									<html:select property="yf" style="width:100px" styleId="yf">
										<html:option value=""></html:option>
										<html:options collection="yfList" property="yf"
											labelProperty="yf" />
									</html:select>
								</td>
								<th>
									¥������
								</th>
								<td>
									<html:select property="lddm" style="width:90px" styleId="lddm"
										onchange="GetQshList()">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									���Һ�
								</th>
								<td>
									<input type="hidden" name="qshV" id="qshV" />
									<html:select property="qsh" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="ssList" property="qsh"
											labelProperty="qsh" />
									</html:select>
								</td>
								<logic:equal value="11641" name="xxdm" scope="session">
									<th>
										ѧ��
									</th>
									<td>
										<input type="text" name="xh" id="xh" maxlength="20" />
									</td>
								</logic:equal>
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
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsgyglForm"></jsp:include>
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
		</html:form>

		<logic:equal value="ok" name="done">
			<script type="text/javascript">
				alert("�����ɹ���");
				document.getElementById('search_go').click();   
			</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script type="text/javascript">
				alert("����ʧ�ܣ�");
				document.getElementById('search_go').click();   
			</script>
		</logic:equal>
	</body>
</html>


