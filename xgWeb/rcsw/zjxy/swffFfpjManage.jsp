<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/rcsw/rcswFunction.js"></script>
		<script  language="javascript">
			function showPlpj(){
				var RowsStr="!!";
				for (i=0; i<document.getElementsByName("pkV").length; i++){
			    	if(document.getElementsByName("pkV")[i].checked){
			    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
			    	}
				}
				if (RowsStr=="!!"){
					alert("��ѡ��Ҫ�����ļ�¼��");
					return false;
				}
				viewTempDiv('ʳ�﷢������','plpj',280,160);
			}
			
			function savePj(){
				if($("pjyj").value==""){
					alert("�����������Ϊ��!");
					return false;
				}
				
				dataBatch('zjxyRcsw.do?method=swffFfpjManage&doType=savePj');
			}
			
			function modi(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('��ѡ��Ҫ�޸ĵ������У�');
					return false;
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/zjxyRcsw" method="post">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					 <!-- ��ť -->
					 <logic:equal name="writeAble" value="yes">
					 <div class="buttonbox">
					    <ul>
					    	<li> <a href="#" onclick="modi('/xgxt/zjxyRcsw.do?method=swffOnePjManage&doType=modi')" class="btn_sh"> ���� </a> </li>
							<li> <a href="#" onclick="showPlpj()" class="btn_shtg"> �������� </a> </li>
					    </ul>
					 </div>
					 </logic:equal>
					<div class="searchtab">
						<table width="100%" border="0">
							
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go"
												onclick="BatAlert.showTips('��ѯ�����У����Ժ�');allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfpjManage&doType=query');">
												�� ѯ
											</button>
											<input type="hidden" name="go" value="a" />
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
										ѧ��
									</th>
									<td>
										<html:select property="xn" style="" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xq" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										���
									</th>
									<td>
										<html:select property="nd" style="" onchange="">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th >
										ѧ��
									</th>
									<td>
										<html:text  property="zgh"  value="${userName}" disabled="true" style="width:85px" maxlength="20" />
										<html:hidden property="zgh" value="${userName }"/>
									</td>
									<th>
										��Ŀ����
									</th>
									<td>
										<html:select property="xmlx" style=""
											styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										��Ŀ����
									</th>
									<td>
										<html:text property="xmmc" styleId="xmmc"/> 
									</td>
								</tr>
								<tr>	
									<th >
										����ʼʱ��
									</th>
									<td >
										 <html:text  property="kssj"   styleId="kssj" style="width:70px"
										onclick="return showCalendar('kssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="jssj" styleId="jssj"  style="width:70px"
										onclick="return showCalendar('jssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
									<th >
										���������ʼ
									</th>
									<td>
										 <html:text property="ffjskssj" styleId="ffjskssj" style="width:70px"
										onclick="return showCalendar('ffjskssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="ffjsjssj" styleId="ffjsjssj"  style="width:70px"
										onclick="return showCalendar('ffjsjssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
									<td colspan="2"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
									<font color="red">δ�ҵ��κμ�¼��</font>
								</logic:empty> </span>
								<logic:notEmpty name="rs">
						 		 	<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						 		 </logic:notEmpty>
						</h3>

						<logic:notEmpty name="rs">
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox"  name="all" value="all" onclick="chec()"/>
											
										</td>
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="modi('/xgxt/zjxyRcsw.do?method=swffOnePjManage&doType=view')">
											<td>
												<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
											   />
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
							<!--��ҳ��ʾ-->
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
							
							<!--��ҳ��ʾ-->
						</logic:notEmpty>
					</div>
				</div>
			</logic:empty>
			<div id="plpj" style="display: none">
				<div class="tab">
					<table class="formlist">
						<tr>
							<th><font color="red">*</font>����̶�</th>
							<td>
								<input type="radio" name="mycd" value="fcmy" checked/>�ǳ�����
								<input type="radio" name="mycd" value="my"/>����<br/>
								<input type="radio" name="mycd" value="bjmy"/>�Ƚ�����
								<input type="radio" name="mycd" value="bmy"/>������
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�������</th>
							<td>
								<textarea  name="pjyj" id="pjyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' ></textarea>
							</td>
						</tr>
						<tr>
							<td colspan='2' align='right'>
							<button type="button" onclick='savePj()'>ȷ��</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
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
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
