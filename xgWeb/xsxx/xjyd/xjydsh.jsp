<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.*" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type='text/javascript' src='/xgxt/dwr/interface/xjydgl.js'></script>
<script type="text/javascript">
	function plsh(){
		viewTempDiv("ѧ���춯���","shdiv",380,200);
	}
	
	function qxsh(){
		if(isChecked('primarykey_cbv')){
			var xtgwid = radioValue('xtgwid');
			var ydlbm = val('ydlbdm');
			var userName = val('userName');
			
			xjydgl.checkSfkqxsh(array,userName,xtgwid,ydlbm,function(data){
				if(data != null && data != ""){
					alert(data);
				}else{
					if(confirm('��ȷ��Ҫȡ���������ѡ�ļ�¼��')){
						refreshForm('xjyd.do?method=xjydsh&doType=qxsh');
					}
				}
			});			
		}
	}
</script>
</head>
	<body onload="xyDisabled('fdy');">		
		<html:form action="/xjyd.do?method=xjydsh" method="post">
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="xyV" name="xyV" value=""/>
			<input type="hidden" id="zyV" name="zyV" value=""/>
			<input type="hidden" id="bjV" name="bjV" value=""/>		
			
			
			<html:hidden property="ydhxydm" styleId="ydhxydm"/>

			<!--�û���Ϣ-->
			<%@ include file="/xsxx/yhxx.jsp"%>
		
			<logic:notEqual value="true" name="isFdy" scope="session">
				<logic:equal value="xy" name="userType" scope="session">
					<input type="hidden" name="queryequals_yhqxydm" value="${userDep}"/>
				</logic:equal>
			</logic:notEqual>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<logic:empty name="yhgwList">
				<div class="prompt">
		          <h3><span>��ʾ��</span></h3>
		          <p>
					��������������ڣ�
				  </p>
		          <a class="close" title="����" onclick="this.parentNode.style.display='none'"></a>
		      	</div>
			</logic:empty>
			<div class="toolbox">
				  <!--��дȨ-->
				  <logic:equal value="yes" name="writeAble">
					  <logic:empty name="yhInfo">
							<logic:notEmpty name="yhgwList">
							  <!-- ��ť -->				  
							  <div class="buttonbox">
							    <ul>
								<li> <a href="#" onclick="showAuditingWindow('primarykey_cbv','xjyd.do?method=xjydShOne&xtgwid='+radioValue('xtgwid'),800,600);return false;" class="btn_sh">���</a> </li>
								<li> <a href="#" onclick="qxsh();return false;" class="btn_qxsh">ȡ�����</a> </li>
								</ul>					
							  </div>
							</logic:notEmpty>				    
					  </logic:empty>
				  </logic:equal>	
				  <div class="searchtab">
					<table width="100%" border="0" >
				      <tfoot>
				        <tr>	
						 <td colspan="4">
							<logic:notEmpty name="yhgwList">
								��ǰ�û���λ��
								<logic:iterate id="v" name="yhgwList">
									<html:radio property="xtgwid" value="${v.xtgwid}" onclick="refreshForm('xjyd.do?method=xjydsh')">${v.xtgwmc}</html:radio>
								</logic:iterate>
							</logic:notEmpty>
							
							<logic:equal value="xy" name="userType">
							<logic:notEqual value="true" name="isFdy">
								&nbsp;&nbsp;
									<bean:message key="lable.xsgzyxpzxy" />��Χ:
									<html:radio property="xycxfw" value="ydqbxy" styleId="ydqbxy" onclick="setXy();">�춯ǰ��<bean:message key="lable.xb" /></html:radio>
									<html:radio property="xycxfw" value="ydhbxy" styleId="ydhbxy" onclick="setXy();">�춯��<bean:message key="lable.xb" /></html:radio>
								</logic:notEqual>
							</logic:equal>
							
						  </td>			  
				          <td colspan="2">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/xjyd.do?method=xjydsh&doType=query')">
										��ѯ
									</button>
									<button type="button"
										onclick="searchReset();return false;">
										����
									</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>					  
					  <tr>
						<th width="15%">�춯ǰ�꼶</th>
						<td>
							<html:select property="ydqnj" onchange="initZyList();initBjList()" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>	
							<html:text property="xh" maxlength="20" style="width:80px"></html:text>	
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" maxlength="20" style="width:80px"></html:text>	
						</td>
					  </tr>
					  <tr>
						<th>�춯ǰ<bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="ydqxydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							
							<html:hidden property="xydm" styleId="xydm"/>
						</td>
						<th>�춯ǰרҵ</th>
						<td>
							<html:select property="ydqzydm" onchange="initBjList()"  styleId="zy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>		
						</td>
						<th>�춯ǰ�༶</th>
						<td>
							<html:select property="ydqbjdm"  styleId="bj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>

					  <tr>
						<th>�춯���</th>
						<td>
                            <html:select property="ydlbdm" styleId="ydlbdm" style="width:160px" onchange="refreshForm('xjyd.do?method=xjydsh')">
								<html:options collection="ydlbList" property="dm"
									labelProperty="mc" />
							</html:select>
                        </td>
						<th>�춯����</th>
						<td>
                            <html:text property="querygreaterequal_ydrq"  styleId="ydrqks"
							           style="width:60px" 
							           onclick="return showCalendar('ydrqks','y-mm-dd');" 
							           onblur="dateFormatChg(this)"
							           ></html:text>
								-
							<html:text property="querylessequal_ydrq"  styleId="ydrqjs"
							           style="width:60px" 
							           onclick="return showCalendar('ydrqjs','y-mm-dd');" 
							           onblur="dateFormatChg(this)"
							           ></html:text>
                        </td>
						<th>�춯��ֹ����</th>
						<td>
                            <html:text property="querygreaterequal_ydjzrq" styleId="ydjzrqks"
							           style="width:60px"
							           onclick="return showCalendar('ydjzrqks','y-mm-dd');" 
							           onblur="dateFormatChg(this)"
							           ></html:text>
								-
							<html:text property="querylessequal_ydjzrq" styleId="ydjzrqjs"
							           style="width:60px"
							           onclick="return showCalendar('ydjzrqjs','y-mm-dd');" 
							           onblur="dateFormatChg(this)"
							           ></html:text>
                        </td>
						</tr>
						<!--�������ҳ��-->
						<%@ include file="/xsxx/xsxx_shtjym.jsp"%>

					  </tbody>
					</table>
				</div>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			       <td>
						<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px" />
					</td>
					<logic:iterate id="tit" name="topTr" offset="3" scope="request">
						<td id="${tit.en}"
							onclick="tableSort(this)">
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" ondblclick="showDetailWindow('xjyd.do?method=xjydShOne&doType=view&xtgwid='+radioValue('xtgwid'),800,600)"
							style="cursor:hand;background-color:
							    <logic:iterate id="v" name="s" offset="1" length="1">
							    ${v}
							    </logic:iterate>
							     ">

							<td>
								<logic:iterate id="v" name="s" offset="2" length="1">								
									<input type="hidden" name="check"  value="${v}"/>
									<input type="checkbox" name="primarykey_cbv" value="${v}" alt="<logic:iterate id="v" name="s" offset="0" length="1"><logic:equal value="��" name="v">disabled</logic:equal></logic:iterate>" id="cbv" />
								</logic:iterate>
						    </td>
							<td>
								<logic:iterate id="v" name="s" offset="3" length="1">							
									<a href="#" onclick="showTopWin('stu_info_details.do?xh=${v}',800,600)">${v}</a>
								</logic:iterate>
						    </td>
							<logic:iterate id="v" name="s" offset="4">
								<td>
									${v}
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				
				</logic:notEmpty>
				</div>

				<div class="open_win01" style="display:none;" id="shdiv">
				<table width='80%' class='formlist'>
					<tbody>
						<tr>
							<th>
								������
								<br />
								<font color="red"><��500��>
								</font>
							</th>
							<td colspan="3">
								<html:textarea property="shyj" onblur="chLeng(this,500)"
									style="width:90%" rows="5"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								�����
							</th>
							<td>
								${userNameReal}
							</td>
							<th>
								���ʱ��
							</th>
							<td>
								${now}
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='4'>
								<div class='btn'>
									<button type="button"
										onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('xjyd.do?method=xjydsh&shjg=ͨ��&doType=sh')}">
										ͨ��
									</button>
									<button type="button"
										onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('xjyd.do?method=xjydsh&shjg=��ͨ��&doType=sh')}">
										��ͨ��
									</button>
									<button type="button"
										onclick="if (confirm('��ȷ��Ҫ�˻�����ѡ�ļ�¼��')){refreshForm('xjyd.do?method=xjydsh&shjg=�˻�&doType=sh')}">
										�˻�
									</button>
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		 
		 <logic:present name="result">
			<script>
				alert(''+$('message').value);
				//document.getElementById('search_go').click();
			</script>		
		</logic:present>
		
		<script type="text/javascript" defer>
			function setXy(){
				if ($('ydqbxy') && $('ydqbxy').checked){
					$('xy').value = $('userDep').value;
					$('xydm').value = $('userDep').value;
					$('ydhxydm').value = "";
					$('xy').disabled = true;
				}
				
				if ($('ydhbxy') && $('ydhbxy').checked){
					$('xy').disabled = false;
					$('xy').value = "";
					$('ydhxydm').value = $('userDep').value;
					$('xydm').value = "";
				}	
			}
			setXy();
		</script>
		
		
	</body>
</html>
