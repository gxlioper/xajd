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
				
				dataBatch('zjxyRcsw.do?method=swffFfpjManage&doType=savePj');
			}
			
			function tj(){
					url="/xgxt/zjxyRcsw.do?method=swffPjTjManage&doType=tj";
					document.forms[0].target = "_blank";
			        document.forms[0].action=url;
			        document.forms[0].submit();
			        document.forms[0].target = "_self";
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
			<input type="hidden" name="tableName" id="tableName" value="view_rcsw_swffrqwh"/> 
			<input type="hidden" name="realTable" id="realTable" value="view_rcsw_swffrqwh"/> 
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
							<li> <a href="#" onclick="tj();return false;" class="btn_tj"> ͳ�� </a> </li>
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
												onclick="BatAlert.showTips('��ѯ�����У����Ժ�');allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffPjTjManage&doType=query');">
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
									<th >
										����ʼʱ��
									</th>
									<td colspan="2">
										 <html:text property="kssj" styleId="kssj" style="width:90px"
										onclick="return showCalendar('kssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="jssj" styleId="jssj"  style="width:90px"
										onclick="return showCalendar('jssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
									<td colspan="3"></td>
								</tr>
								<tr>	
									<th >
										���������ʼ
									</th>
									<td colspan="2">
										 <html:text property="ffjskssj" styleId="ffjskssj" style="width:90px"
										onclick="return showCalendar('ffjskssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="ffjsjssj" styleId="ffjsjssj"  style="width:90px"
										onclick="return showCalendar('ffjsjssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
									<td colspan="3"></td>
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
						 		 	<font color="blue">��ʾ��������ͷ��������</font>
						 		 </logic:notEmpty>
						</h3>

						<logic:notEmpty name="rs">
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										
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
											>
											
											<logic:iterate id="v" name="s" offset="0">
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
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
							<!--��ҳ��ʾ-->
						</logic:notEmpty>
					</div>
				</div>
			</logic:empty>
			
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
