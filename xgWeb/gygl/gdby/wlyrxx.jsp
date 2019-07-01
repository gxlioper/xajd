<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ������Ա���õǼ�</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/XsGyGlLogic.do?method=xsGySdCbGl" method="post">
			<!-- ҳǩ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="myViewMore('add')"
									class="btn_zj">����</a>
							</li>
							<li>
								<a href="#"
									onclick="myViewMore('modi')"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="myViewMore('del')"
									class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">����</a>
							</li>		
						</logic:equal>
						<li>
							<a href="#" 
								onclick="BbTj('/xgxt/XsGyGlLogic.do?method=xsGyWlRy&done=bbTj')"
								class="btn_dc">�ǼǱ���</a>
						</li>
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
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsGyGlLogic.do?method=xsGyWlRy')">
											��ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									���
								</th>
								<td>
									 <html:select  property="nd" style="width:100px" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>												
								</td>
								<th>
									�·�
								</th>
								<td>
									<html:select  property="yf" style="width:100px" styleId="yf">
									    <html:option value=""></html:option>
										<html:options collection="yfList" property="yf"
											labelProperty="yf" />
									</html:select>
								</td>
								<th>
									¥��
								</th>
								<td>
									<html:select property="lddm" style="width:90px" styleId="lddm">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:text property="rq" styleId="rq" readonly="true" onblur="dateFormatChg(this)"
						  			  onclick="return showCalendar('rq','y-mm-dd');" style="width:100px;cursor:hand " ></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s">
							<tr  onclick="rowOnClick(this)" style="cursor:hand;background-color:
	                          <logic:iterate id="v" name="s" offset="0" length="1">
	                           <bean:write name="v"/>
	                            </logic:iterate>
	                             " style="cursor:hand" ondblclick="myViewMore('view')">
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td >
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>						
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>		
		</html:form>

<script type="text/javascript">
function myViewMore(doType){
   var pkValue = "";
   var url = "/xgxt/XsGyGlLogic.do?method=xsGyWlRy_Modi&doType=";
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
      if(doType=="view"){
        showTopWin(url,'600','400');
      }
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
function BbTj(url){
 if(document.getElementById('lddm').value==""){
		alert("¥�����Ʋ���Ϊ�գ�");
		return false;
    }
    url+="&lddm="+document.getElementById('lddm').value;
    url+="&nd="+document.getElementById('nd').value;
    url+="&yf="+document.getElementById('yf').value;
    url+="&rq="+document.getElementById('rq').value;
     showTopWin(url,'800','700');
}
</script>

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

		
