<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/JyxyDataSearch.do?act=go&doType=query";
		 	document.forms[0].submit();
    }
    
    function jygljyxyDataExport() {
	       document.forms[0].action = "/xgxt/jygljyxyDataExport.do?tableName=jygl_jyxy";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		
		function viewMoreinfo1(doType){
		var url ="/xgxt/JyglJyxyViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showOpenWindow(url, 800, 600);
		 }
		}
		
		
		function jyxyupdate(doType){
		var url ="/xgxt/turnjyxyupdate.do?whichArea=shenhe&doType=first&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 900, 600);
		         //showOpenWindow(url, 900, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function JyglJyxyDel(doType2){
		var url = "/xgxt/JyglJyxyDel.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType2 == "del") {
		   if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		    } else {
			if (confirm("ȷ��Ҫɾ������������")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		function jyxyfdysh(doType){
			var url ="/xgxt/turnjyxyfdysh.do?doType=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
					return false;
		    	} else {
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 800, 600);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}
		
		function jyxyxxsh(doType){
			var url ="/xgxt/turnjyxyxxsh.do?doType=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showOpenWindow(url, 800, 600);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}
		
		
		</script>
	</head>

	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>��ҵ���� - ��ҵЭ�鷽�� - Э����˲�ѯ</a>
				</p>
			</div>
			<div class="toolbox">
				<div class="buttonbox">
				    <ul>
					<logic:notEqual value="12061" name="xxdm" scope="session">
						<logic:notEqual value="11122" name="xxdm" scope="session">
							<logic:equal name="who" value="fudaoyuan">
								<li>
									<a href="#" onclick="jyxyfdysh('shenhe')" class="btn_sh">
										����Ա��� </a>
								</li>
							</logic:equal>

							<logic:equal name="who" value="teacher">
								<li>
									<a href="#" onclick="jyxyxxsh('shenhe')" class="btn_shtg">
										ѧУ��� </a>
								</li>
							</logic:equal>
						</logic:notEqual>
						<logic:equal value="11122" name="xxdm" scope="session">
							<logic:equal name="whos" value="xy">
								<li>
									<a href="#" onclick="jyxyfdysh('shenhe')" class="btn_sh"> <bean:message
											key="lable.xsgzyxpzxy" />��� </a>
								</li>
							</logic:equal>
							<logic:equal name="whos" value="xx">
								<li>
									<a href="#" onclick="jyxyxxsh('shenhe')" class="btn_shtg">
										ѧУ��� </a>
								</li>
							</logic:equal>
						</logic:equal>
					</logic:notEqual>
					<logic:equal value="12061" name="xxdm" scope="session">
						<logic:equal name="whos" value="xy">
							<li>
								<a href="#" onclick="jyxyfdysh('shenhe')" class="btn_sh"> <bean:message
										key="lable.xsgzyxpzxy" />��� </a>
							</li>
						</logic:equal>
						<logic:equal name="whos" value="xx">
							<li>
								<a href="#" onclick="jyxyxxsh('shenhe')" class="btn_shtg">
									ѧУ��� </a>
							</li>
						</logic:equal>
					</logic:equal>
					<li>
						<a href="#" onclick="jyxyupdate('update')" class="btn_xg"> �޸�
						</a>
					</li>
					<li>
						<a href="#" onclick="JyglJyxyDel('del')" class="btn_sc"> ɾ�� </a>
					</li>
					<logic:equal name="who" value="teacher">
						<li>
							<a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a>
						</li>
						<li>
							<a href="#" onclick="jygljyxyDataExport()" class="btn_dc"> ����
							</a>
						</li>
					</logic:equal>
					</ul></div>
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
							<tr>
								<td colspan="8">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button class="btn_cx" onclick="querygo('/xgxt/JyxyDataSearch.do')" id="query_go">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
							<tbody>
								<tr style="cursor:hand">
										<%--								<logic:equal value="12061" name="xxdm" scope="session">--%>
										<logic:equal name="whos" value="xx">
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
											<html:select name="rs1" property="xymc" style="width:150px">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xymc"
													labelProperty="xymc" />
											</html:select>
										</td>
										</logic:equal>
										<logic:equal name="whos" value="xy">
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
						       				 <html:text name="rs1" property="xymc" style="width:150px"
												readonly="true" />
										</td>
											<%--								</logic:equal>--%>
										</logic:equal>
										<%--								<logic:notEqual value="12061" name="xxdm" scope="session">--%>
										<%--								<logic:equal name="who" value="teacher">--%>
										<%--								<bean:message key="lable.xsgzyxpzxy" />��--%>
										<%--								<html:select name="rs1" property="xymc" style="width:150px">--%>
										<%--										<html:option value=""></html:option>--%>
										<%--										<html:options collection="xyList" property="xymc"--%>
										<%--											labelProperty="xymc" />--%>
										<%--									</html:select>--%>
										<%--								</logic:equal>--%>
										<%--								<logic:equal name="who" value="fudaoyuan">--%>
										<%--							    <bean:message key="lable.xsgzyxpzxy" />��--%>
										<%--						        <html:text name="rs1" property="xymc" style="width:150px"--%>
										<%--										readonly="true" />--%>
										<%--								</logic:equal>--%>
										<%--								</logic:notEqual>--%>
										<th>
											��ѧ���
										</th>
										<td>
										<html:select name="rs1" property="nd" style="width:60px">
											<html:option value=""></html:option>
											<html:options collection="ndList" property="nj"
												labelProperty="nj" />
										</html:select>
										</td>
										<th>
											��ҵ���
										</th>
										<td>
										<html:select name="rs1" property="bynd" style="width:60px">
											<html:option value=""></html:option>
											<html:options collection="ndList" property="nj"
												labelProperty="nj" />
										</html:select>

										<logic:equal value="11122" name="xxdm" scope="session">
										<th>
											<bean:message key="lable.xsgzyxpzxy" />���
										</th>
										<td>
											<html:select name="rs1" property="fdysh" style="width:65px">
													<html:option value=""></html:option>
													<html:option value="δ���">δ���</html:option>
													<html:option value="��ͨ����">��ͨ����</html:option>
													<html:option value="δͨ��X">δͨ��X</html:option>
												</html:select>
										</td>
										</logic:equal>
										<%--<logic:notEqual value="11122" name="xxdm" scope="session">
												����Ա��ˣ�
												</logic:notEqual>
												<html:select name="rs1" property="fdysh" style="width:65px">
													<html:option value=""></html:option>
													<html:option value="δ���">δ���</html:option>
													<html:option value="��ͨ����">��ͨ����</html:option>
													<html:option value="δͨ��X">δͨ��X</html:option>
												</html:select>
												
												--%>
										<logic:equal value="xx" name="userlx">
										<th>
											ѧУ���
										</th>
										<td>
											<html:select name="rs1" property="xxsh" style="width:65px">
												<html:option value=""></html:option>
												<html:option value="δ���">δ���</html:option>
												<html:option value="��ͨ����">��ͨ����</html:option>
												<html:option value="δͨ��X">δͨ��X</html:option>
											</html:select>
										</logic:equal>
									</td>
								</tr>
								<tr>
									<th>
										ѧ��
									</th>
									<td>
										<input type="text" name="xsxh"
											value="<bean:write name="rs1" property="xsxh"/>" />
									</td>
									<th>
										����
									</th>
									<td>
										<input type="text" name="name"
											value="<bean:write name="rs1" property="name"/>"
											style="width:95px" />
									</td>
									<th>
										�Ա�
									</th>
									<td>
										<html:select name="rs1" property="xbdm" style="width:60px">
											<html:option value=""></html:option>
											<html:option value="1">
												��
											</html:option>
													<html:option value="2">
												Ů
											</html:option>
										</html:select>
									</td>
									<th>
										ѧ�����
									</th>
									<td>
										<html:select name="rs1" property="xslb" style="width:65px">
											<html:option value=""></html:option>
											<html:option value="ר����">
												ר����
											</html:option>
													<html:option value="������">
												������
											</html:option>
													<html:option value="�о���">
												�о���
											</html:option>
										</html:select>
									</td>
									
								</tr>
								<tr>
									<th>
										��ҵ��־
									</th>
									<td>
										<html:select name="rs1" property="wjybz" style="width:65px">
											<html:option value=""></html:option>
											<html:option value="0">�Ѿ�ҵ</html:option>
											<html:option value="1">δ��ҵ</html:option>
										</html:select>
									</td>
									
									<logic:equal value="11122" name="xxdm" scope="session">
									<th>
										Э������
									</th>
									<td>
									<html:text property="xysbh"></html:text>
										
									</td>
									<td colspan="4"></td>
									</logic:equal>
									<logic:notEqual value="11122" name="xxdm" scope="session">
										<td colspan="6"></td>
									</logic:notEqual>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp;
								<logic:empty name="rs">
									<font color="red">δ�ҵ��κμ�¼��</font>
								</logic:empty> 
								<logic:notEmpty name="rs">
									<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
								</logic:notEmpty> </span>
						</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" id="rsTable" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="2" length="9">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<logic:equal value="12061" name="xxdm" scope="session">
											<logic:iterate id="tit" name="topTr" offset="10" length="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:message key="lable.xsgzyxpzxy" />
													���
												</td>
											</logic:iterate>
											<logic:equal value="xx" name="userlx">
												<logic:iterate id="tit" name="topTr" offset="11" length="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</logic:equal>
										</logic:equal>

										<logic:notEqual value="12061" name="xxdm" scope="session">
											<logic:equal value="11122" name="xxdm" scope="session">
												<logic:iterate id="tit" name="topTr" offset="10" length="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)" nowrap>
														<bean:message key="lable.xsgzyxpzxy" />
														���
													</td>
												</logic:iterate>
												<logic:equal value="xx" name="userlx">
													<logic:iterate id="tit" name="topTr" offset="11" length="1">
														<td id="<bean:write name="tit" property="en"/>"
															onclick="tableSort(this)" nowrap>
															<bean:write name="tit" property="cn" />
														</td>
													</logic:iterate>
												</logic:equal>
											</logic:equal>
											<logic:notEqual value="11122" name="xxdm" scope="session">
												<logic:iterate id="tit" name="topTr" offset="11" length="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</logic:notEqual>
										</logic:notEqual>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="viewMoreinfo1('view')">
										<td style="display:none">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2" length="9">
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:equal value="12061" name="xxdm" scope="session">
											<logic:iterate id="v" name="s" offset="10" length="1">
												<td align="center">
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<logic:equal value="xx" name="userlx">
												<logic:iterate id="v" name="s" offset="11" length="1">
													<td align="center">
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</logic:equal>
										</logic:equal>

										<logic:notEqual value="12061" name="xxdm" scope="session">
											<logic:equal value="11122" name="xxdm" scope="session">
												<logic:iterate id="v" name="s" offset="10" length="1">
													<td align="center">
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<logic:equal value="xx" name="userlx">
													<logic:iterate id="v" name="s" offset="11" length="1">
														<td align="center">
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</logic:equal>
											</logic:equal>
											<logic:notEqual value="11122" name="xxdm" scope="session">
												<logic:iterate id="v" name="s" offset="11" length="1">
													<td align="center">
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</logic:notEqual>
										</logic:notEqual>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							<script type="text/javascript">
									$('choose').className="hide";
							</script>
					</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("ɾ���ɹ�!");
                </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("ɾ��ʧ��");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

