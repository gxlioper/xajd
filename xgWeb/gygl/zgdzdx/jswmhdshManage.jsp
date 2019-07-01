<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>	
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��� - ������������ </a>
			</p>
		</div>
		<!-- ���� end-->	
		<html:form action="/zgdzdx_Gygl" method="post">
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="swmhdSh('yes')"
									class="btn_shtg">ͨ ��</a>
							</li>
							<li>
								<a href="#"
									onclick="swmhdSh('no')"
									class="btn_shbtg">��ͨ��</a>
							</li>							
						</logic:equal>
						<logic:present name="notDone">
		                   <script language="javascript">
		                       alert("�û�Ѿ��ϼ���˹������ܽ��в�����");
		                   </script>
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
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zgdzdx_Gygl.do?method=hdShManage')">
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
									�����
								</th>
								<td>
									<html:text  property="hdmc" style="width: 150px" styleId="hdmc"/>												
								</td>
								<th>
									���쵥λ
								</th>
								<td>
									<html:text  property="zbdw" style="width: 150px" styleId="zbdw"/>
								</td>
								<th>
									�����
								</th>
								<td>
									<html:text  property="hdnr" style="width: 150px" styleId="hdnr"/>				
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									���״̬							
								</th>
								<td>
									<html:select property="yesNo">
		                                 <html:options  collection="chkList" property="en"  labelName="cn"/>
		                            </html:select>	
								</td>
								<th>
									�ʱ��(��ʼ)
								</th>
								<td>
									<html:text property="hdksrq" styleId="hdksrq"
										onblur="dateFormatChg(this)" style="cursor:hand;width:90px"
										onclick="return showCalendar('hdksrq','y-mm-dd');"/>
								</td>
								<th>
									�ʱ��(����)
								</th>
								<td>
									<html:text property="hdjsrq" styleId="hdjsrq"
										onblur="dateFormatChg(this)" style="cursor:hand;width:90px"
										onclick="return showCalendar('hdjsrq','y-mm-dd');"/>	
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
								<tr align="center" style="cursor:hand" >
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
									ondblclick="jswmhdView()">
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
	<script type="text/javascript">
	       function jswmhdView(){	      
	            url="/xgxt/zgdzdx_Gygl.do?method=jswmhdhShView&rid=";	           	
	            url+=(curr_row.cells[0].getElementsByTagName("input"))[0].value ;                
	            showTopWin(url,"600","300");	          
	       }
	       function swmhdSh(shType){
	           if (curr_row == null) {
		          alert("��ѡҪ��˵ļ�¼��\n����һ�м�¼����");
		          return false;
	           } else {	           
	                url="/xgxt/zgdzdx_Gygl.do?method=jswmhdhSh&rid=";	           	
	                url+=(curr_row.cells[0].getElementsByTagName("input"))[0].value ;
	                url+="&shType="+shType;
	                var clinText = "";
	                if(shType=="yes"){
	                   clinText="ͨ��";
	                }else{
	                   clinText="��ͨ��";
	                }
	                if(confirm("ȷ�� "+clinText+" �û��" )){
	                   refreshForm(url);
	                }  
	          }              	            
	       }
	
	</script>
</html>
