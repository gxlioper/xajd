<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
        <html:form action="/xtwhSpjl" method="post">
        	<html:hidden property="id" styleId="id" />
            <html:hidden property="djh" styleId="djh"  />
            <html:hidden property="djlx" styleId="djlx" />
            <html:hidden property="spbz" styleId="spbz"/>
            <html:hidden property="nspgw" styleId="nspgw"/>
            <html:hidden property="actionName" styleId="actionName"/>
			<div class="tab">
				<h3>
					<em> ������� </em>
				</h3>
				<!-- 
				<div id="xxk">
					<ul>
						<li class="current" id="libasic"
							onclick="setObjStatus(['basic','statistic'])">
							<a href="#" onfocus="this.blur()">������Ϣ</a>
						</li>
						<li id="listatistic" onclick="setObjStatus(['statistic','basic'])">
							<a href="#" onfocus="this.blur()">������¼</a>
						</li>
					</ul>
				</div>
				 -->
				<div id="divbasic">
					<span class="opencon">
						<table width="100%" border="0" class="formlist">
							<tbody id="rsTable">
								<tr>
                                    <td id=meeting align="right" width="20%" >
                                    	[���뵥��]
                                        <%--
                                        <bean:write name="rs" property="djlx" styleId="djlx" />
                                        <bean:write name="rs" property="name" styleId="name" />
                                        --%>
									</td>
									<td align="left">
									
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										�Ƿ�ͨ��
									</td>
									<td align="left">
									<%--
										<html:radio name="rs" property="sftg" styleId="sftg" onclick="getRadioValue(this.value)">
										</html:radio>
									 --%>
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										�˻�״̬
										<span class="notnull">*</span>
									</td>
									<td align="left">
                                        <html:select  property="thgw"  styleId="thgw">
                                            <html:option value="">��ѡ���˻ظ�λ</html:option>
                                            <html:options collection="thbzList" property="spgw" labelProperty="spgwmc" />
                                        </html:select>
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										����״̬
									</td>
									<td>
										��ʼ
                                        <logic:iterate name="spbzList" id="s" indexId="index">
                                            --> 
                                            <%--
                                            <script type="text/javascript">
                                            var spgw = "<%=request.getAttribute("spgw")%>";
											if(spgw == "<html:property value="spgw"/>"){
												document.write("<strong><html:property value="spgwmc"/></strong>");
											}else{
												document.write("<html:property value="spgwmc"/>");
											}
											</script>
											--%>
										</logic:iterate>
										-->&nbsp;����
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										�� ��(��510��)
									</td>
									<td>
									<%--
                                        <html:text name="rs" property="mc" styleId="mc" rows="9" cssStyle="width:95%"/>
                                    --%>
									</td>
								</tr>
							</tbody>
						</table> </span>
				</div>
				<div id="divstatistic" style="display: none; height: 384px;">
                <div class="formbox">
                  <h3 class="datetitle_01">
                        <span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
                                <font color="red">δ�ҵ��κμ�¼��</font>
                            </logic:empty>  
                            <logic:notEmpty name="rs">
                            <font color="blue"></font>	
                            </logic:notEmpty>
                        </span>
                    </h3>
    
                  <logic:notEmpty name="rs">
                    <div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
                    	<thead>
						<tr>
							<th>
								�������
							</th>
							<th>
								�����
							</th>
							<th>
								�Ƿ�ͨ��
							</th>
							<th>
								�˻ظ�λ
							</th>
							<th>
								������
							</th>
						</tr>
                        </thead>
						<tbody>
                        	<logic:iterate name="spjlList" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<input type="checkbox" name="checkVal" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>
										/>
									</td>
									<logic:iterate id="v" name="s" >
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
                        </tbody>
                    </table>
                    </div>
                  </logic:notEmpty>
                  </div>
                </div>
                <span class="btn" style="float:right">
                <!-- ����������ʽ������ͨ���벻ͨ���Խ�����һ������λ���˻����˻ص�ǰһ��λ������ָ����λ���������������е����ã� -->
                    <button class="" type="submit">
                        �� ��
                    </button>
                    <button type="button" class="btn_cz" onclick="Close();return false;">
                        �� ��
                    </button>
                </span>
			</div>
            <logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("�����ɹ���");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form>
		<script language="javascript">
			<%--var sftg = "<html:property value="sftg" />";--%>
			var sftg = "0";
			if(sftg == "0"){
				$("thgw").disabled = true;
			}else{
				$("thgw").disabled = false;
			}
		    function getRadioValue(radioValue){
		    	if(radioValue == "0"){
		    		$("thgw").disabled = true;
				}else{
		    		$("thgw").disabled = false;
				}
				sftg = radioValue;
		    }
		</script>
	</body>
</html>