<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function pubNews(){
				if(frames('eWebEditor1').getHTML()){
					$('zpnr').value = frames('eWebEditor1').getHTML();
				}
			}
		</script>
		<logic:present name="message">
			<script type="text/javascript">
				alert("${message}");
	 			if(window.dialogArguments){
	 				window.close();
	 				dialogArgumentsQueryChick();
	 			}	
			</script>
		</logic:present>
	</head>
	<body id="a">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>У����Ƹά��</a>
			</p>
		</div>
	
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="save_id" value="${id }" />
			<input type="hidden" name="tempDwmc"  id="tempDwmc" value="${tempDwmc }"/>
			<input type="hidden" name="tempGwValue" id="tempGwValue" value="${tempGwValue }"/>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>У����Ƹά��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button name="�ύ"
											onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=ztzpAdd&doType=save','save_zpzt-save_zpsj-save_zpdd-tempGwValue-zpnr');">
										����
									</button>
									<button  onclick="refreshForm('jyweb_ztzpManage.do');">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="14%">
								<span class="red">*</span>��Ƹ����
							</th>
							<td width="86%" colspan="3">
								<html:text property="save_zpzt" maxlength="50" style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							<th width="14%">
								<span class="red">*</span>��Ƹʱ��
							</th>
							<td width="36%">
								<html:text property="save_zpsj" styleId="zpsj"
										   onblur='dateFormatChg(this)'
										   onclick="showCalendar(this.id,'y-mm-dd')"
								></html:text>
							</td>
							<th width="14%">
								<span class="red">*</span>��Ƹ�ص�
							</th>
							<td width="36%">
								<html:text property="save_zpdd" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>������</th>
							<td>${jyweb_realName }
								<input type="hidden" value="${jyweb_realName }" name="save_fbr"/>
							</td>
							<th>����ʱ��</th>
							<td>${now }</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��Ƹ��λ</th>
							<td colspan="2">
								<div id="gwxx">
									<logic:present name="ztzpGwList">
										<table style="width:100%">
											<thead>
												<tr>
													<td>��λ����</td>
													<td>רҵ/��λ����</td>
												</tr>
											</thead>
											<tbody>
												<logic:iterate id="v" name="ztzpGwList">
													<tr>
														<td>${v.gsmc }</td>
														<td>${v.zpzw }</td>
													</tr>
												</logic:iterate>
											</tbody>
										</table>
									</logic:present>
								</div>
							</td>
							<td>
								<button onclick="showTopWin('/xgxt/jyweb.do?method=gwxxk',650,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
								</button>
							</td>
						</tr>
						<%--<tr>
							<th>��ע<span class="red"><br/><��500��></span></th>
							<td colspan="3">
								<html:textarea property="save_bz" style="width:95%" rows="5" onblur="checkLen(this,500)"/>
							</td>
						</tr>
						--%><tr>
							<th>
								<span class="red">*</span>��Ƹ����
							</th>
							
							<td colspan="3">
								<input type="hidden" name="save_zpnr" id="zpnr" value="<logic:present name="rs"><bean:write name="rs" property="zpnr" filter="true"/></logic:present>"/>
								<input type="hidden" name="content1" id="content1" value="<logic:present name="rs"><bean:write name="rs" property="zpnr" filter="true"/></logic:present>"/>
								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
									scrolling="no" width="96%" height="350"></iframe>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
