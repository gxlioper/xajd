<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>	
			<script type="text/javascript">
	       function yqhdManageAdd(){
	            var url = "/xgxt/zgdzdx_Gygl.do?method=yqhdManageAdd";
	            showTopWin(url,"600","450");
	       }
	       function yqhdManageModi(){
	           if (curr_row == null) {
		          alert("��ѡҪ�޸ĵļ�¼��\n����һ�м�¼����");
		          return false;
	           } else {
	            var url = "/xgxt/zgdzdx_Gygl.do?";
	            url +="method=yqhdManageModi";
	            url +="&pkValue=";	
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;  	                      
	            showTopWin(url,"600","450");
	           }
	       }
	       function yqhdManageView(){	         
	            var url = "/xgxt/zgdzdx_Gygl.do?";
	            url +="method=yqhdManageModi";
	            url +="&pkValue=";	
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;  
	            url +="&view=true";           
	            showTopWin(url,"600","450");	          
	       }
	       function yqhdManageDel(){
	           if (curr_row == null) {
		          alert("��ѡҪɾ���ļ�¼��\n����һ�м�¼����");
		          return false;
	           } else {
	              if(confirm("ȷ��Ҫɾ���ü�¼��")){
	                 var url = "/xgxt/zgdzdx_Gygl.do?method=yqhdManageDel";
	                 url +="&pkValue=";
	                 url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;	                
	                 refreshForm(url);
	              }else{	 
	                 return false;           
	            
	              }
	          }
	       }
	       
	</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ԰������ - ԰������ </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zgdzdx_Gygl" method="post">
			
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
						<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" 
									onclick="yqhdManageAdd()"
									class="btn_zj">����</a>
							</li>	
							<li>
								<a href="#"
									onclick="yqhdManageModi()"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="yqhdManageDel()"
									class="btn_sc">ɾ��</a>
							</li>		
						</logic:equal>
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
											onclick="allNotEmpThenGo('/xgxt/zgdzdx_Gygl.do?method=yqhdManage')">
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
									ѧ��
								</th>
								<td>
									<html:select  property="xn"  style="width:80px" styleId="xn" >
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>											
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq" style="width:60px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									԰��
								</th>
								<td>
									<html:select property="yqdm" style="width:120px" styleId="yqdm" 
										onchange="">
										<html:options collection="yqList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									��֯��λ
								</th>
								<td>
									<html:text  property="zzdw" style="width: 80px" styleId="zzdw"/>	
								</td>
								<th>
									�ʱ��(��ʼ)
								</th>
								<td>
									<html:text  property="rzrq" styleId="rzrq"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 80px"
										onclick="return showCalendar('rzrq','y-mm-dd');"  />
								</td>
								<th>
									�ʱ��(����)
								</th>
								<td>
									<html:text  property="lzrq" styleId="lzrq"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 80px"
										onclick="return showCalendar('lzrq','y-mm-dd');"  />		
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									�������
								</th>
								<td>
									<html:text  property="xm" style="width: 80px" styleId="xm"/>
								</td>
								<th>
									�����
								</th>
								<td>
									<html:text  property="hdmc" style="width: 80px" styleId="hdmc"/>
								</td>
								<th>
									�����
								</th>
								<td>
									<html:text  property="hdnr" style="width: 200px" styleId="hdnr"/>		
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
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
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
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="yqhdManageView()">
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
							<!--���� end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
		</html:form>

	</body>
</html>
