<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/xsgbxx.do?method=pdyjAdd&act=add";
		 var yzdz = "";
		 var yzcd = "";
		 var userlx = document.getElementById("userlx").value;
		 if(userlx == "khry"){
			 yzdz = "sjbmpdyjV";
			 yzcd = "800";
		 }else if(userlx == "xx"){
			 yzdz = "zongpV-bzV";
			 yzcd = "800-800";
		 }
		 var mustFill = "xh";
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
	 function checkdataSave(url,yzdz,yzcd,mustFill){
	     
		 var eles = mustFill.split("-");
	    	for (var i = 0; i < eles.length; i++) {
	    		if (document.getElementById(eles[i]) && document.getElementById(eles[i]).value == "") {
	    			alert("�뽫��\"*\"�ŵ���Ŀ����������");
	    			return false;
	    		}
	    	}
	    	 var cheangelx = document.getElementById("zwpdlx").value;
			 if(cheangelx == ""){
					alert("��ѡ����������!!");
					return false;
				}
				if(cheangelx == "ѧ��"){
					var xn = document.getElementById("xn").value;
					if(xn == ""){
						alert("ѧ�겻��Ϊ�գ���");
						return false;
					}
				}else if(cheangelx == "ѧ��"){
					var xn = document.getElementById("xn").value;
					var xq = document.getElementById("xq").value;
					if(xn == ""){
						alert("ѧ�겻��Ϊ�գ���");
						return false;
					}
					if(xq == ""){
						alert("ѧ�ڲ���Ϊ�գ���");
						return false;
					}
				}else if(cheangelx == "��"){
					var xn = document.getElementById("xn").value;
					var xq = document.getElementById("xq").value;
					var yf = document.getElementById("yf").value;
					if(xn == ""){
						alert("ѧ�겻��Ϊ�գ���");
						return false;
					}
					if(xq == ""){
						alert("ѧ�ڲ���Ϊ�գ���");
						return false;
					}
					if(yf == ""){
						alert("�·ݲ���Ϊ�գ���");
						return false;
					}
				}
	    	var splitDz = "";
	    	var splitCd = "";
	    	if(yzdz != ""){
	    		splitDz = yzdz.split("-");
	    	}
	    	if(yzcd != ""){
	    		splitCd = yzcd.split("-");
	    	}
	    	for (i = 0; i < splitDz.length; i++) {
	    		var dzsjcd = document.getElementById(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
	    		var dzsjmc = document.getElementById(splitDz[i]).cells[0].innerHTML;
	    		if (dzsjcd.length>splitCd[i]) {
	    			alert(dzsjmc.replace("<BR>", "")+"���ܳ���"+splitCd[i]+"���֣�");
	    			return false;
	    		}
	    	}
	    	document.forms[0].action = url;
	    	document.forms[0].submit();
	    	document.getElementById("buttonSave").disabled=true;
	    	document.getElementById("buttonQx").disabled=true;
	 }
	 
	 function printwmgylgl(){
			document.forms[0].action = "/xgxt/jhzy_gygl.do?method=wmgylprint";
			document.forms[0].target = "_blank";
		    document.forms[0].submit();
		    document.forms[0].target = "_self";
	 }
	 function userlxchange(){
		 var userlx = document.getElementById("userlx").value;
		 if(userlx == "khry"){
			 document.getElementById("zongp").disabled = true;
			 document.getElementById("bz").disabled = true;
		 }else if(userlx == "xx"){
			 document.getElementById("sjbmpdyj").disabled = true;
		 }else{
			 document.getElementById("buttonSave").style.display = none;
			 document.getElementById("buttonQx").style.display = none;
		 }
	 }
	</script>
	</head>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN
		onload="cheangelx();userlxchange();">
		<html:form action="/xsgbxx" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ṥ�� - ѧ���ɲ� - �������¼��</a>
				</p>
			</div>
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<input type="hidden" id="userlx" name="userlx"
					value="<bean:write name="userlx"/>" />
				<input type="hidden" id="tableName" name="tableName"
					value="zjlg_xsgbxxb" />
				<input type="hidden" id="url" name="url"
					value="/xsgbxx.do?method=getxsInfo" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh" />
				<div class="tab">
					<table width="80%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>�ֶ�ά��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>ѧ��
								</th>
								<td>
									<html:hidden property="id" name="rs" />
									<html:text name="rs" property="xh" 
										readonly="true"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:equal value="add" name="doType">
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:equal>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text name="rs" property="xm"
										readonly="true" />
								</td>
								<th rowspan="4" align="center" width="150">
									<img border="0" style="height:133px;width:100px;"
									src="/xgxt<bean:write name="rs" property="zhaop" />"/>
								</th>
							</tr>
							<tr>
								<th>
									�Ա�
								</th>
								<td>
									<html:text name="rs" property="xb"
										maxlength="10" readonly="true" />
								</td>
								<th>
									������ְʱ��
								</th>
								<td>
									<html:text name="rs" property="drxzsj"
										maxlength="30" readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:text name="rs" property="xydm"
										style="display: none" readonly="true" />
									<input value="<bean:write name="rs" property="xymc"/>"
										readonly="true" disabled="disabled"
										style="display: " />
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:text name="rs" property="bjdm" style="display: none"
										readonly="true" />
									<input value="<bean:write name="rs" property="bjmc"/>"
										disabled="disabled" style="display: " />
								</td>
							</tr>
							<tr>
								<th>
									������֯
								</th>
								<td>
									<html:text name="rs" property="sszz" 
										maxlength="40" readonly="true" />
								</td>
								<th>
									����ְ��
								</th>
								<td>
									<html:text name="rs" property="srzw" 
										maxlength="40" readonly="true" />
								</td>
							</tr>
							<tr id="grjlV">
								<th align="center">
									���˼���
								</th>
								<td colspan="6">
									<html:textarea name="rs" property="grjl" rows="4"
										style="word-break:break-all;width:99%"  readonly="true" />
								</td>
							</tr>
							<tr id="jcqkV">
								<th align="center">
									�������
								</th>
								<td colspan="6">
									<html:textarea name="rs" property="jcqk" rows="4"
										style="word-break:break-all;width:99%"  readonly="true" />
								</td>
							</tr>
							<%--				<tr>--%>
							<%--					<td align="center">--%>
							<%--						�����ܽ�--%>
							<%--					</td>--%>
							<%--					<td colspan="6">--%>
							<%--						<html:textarea name="rs" property="yf" rows="4"--%>
							<%--							style="width=100%" />--%>
							<%--					</td>--%>
							<%--				</tr>--%>
							<%--				<tr>--%>
							<%--					<td align="center">--%>
							<%--						��߻�--%>
							<%--					</td>--%>
							<%--					<td colspan="6">--%>
							<%--						<html:textarea name="rs" property="yf" rows="4"--%>
							<%--							style="width=100%" />--%>
							<%--					</td>--%>
							<%--				</tr>--%>
							<tr>
								<th rowspan="2">
									��������
								</th>
								<td colspan="6">
									<html:select name="rs" property="zwpdlx"
										onchange="cheangelx();" disabled="true">
										<html:option value=""></html:option>
										<html:option value="ѧ��">ѧ��</html:option>
										<html:option value="ѧ��">ѧ��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
									<span id="xnV">ѧ�꣺ <html:select name="rs" property="xn"
											style="width:" styleId="xn" disabled="true">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select> </span>
									<span id="xqV">ѧ�ڣ� <html:select name="rs" property="xq"
											style="width:" styleId="xn" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqmc"
												labelProperty="xqmc" />
										</html:select> </span>
									<span id="yfV">�·ݣ� <html:select name="rs" property="yf"
											style="width:" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="yfList" property="yf"
												labelProperty="yf" />
										</html:select> </span>
								</td>
							</tr>
							<tr id="zwpdV">
								<td colspan="5">
									<html:textarea name="rs" property="zwpd" rows="4"
										style="word-break:break-all;width:99%"  readonly="true" />
								</td>
							</tr>
							<tr id="sjbmpdyjV">
								<th >
									�ϼ������������
								</th>
								<td colspan="6">
									<html:textarea name="rs" property="sjbmpdyj" rows="4"
										style="word-break:break-all;width:99%" />
								</td>
							</tr>
							<tr id="zongpV">
								<th>
									����
								</th>
								<td colspan="6">
									<html:textarea name="rs" property="zongp" rows="4"
										style="word-break:break-all;width:99%"  />
								</td>
							</tr>
							<tr id="bzV">
								<th>
									��ע
								</th>
								<td colspan="7">
									<html:textarea name="rs" property="bz" rows="6" 
										style="word-break:break-all;width:99%" />
								</td>
							</tr>
						</tbody>
						<tfoot
							<logic:equal value="view" name="view">
					style="display: none"
					</logic:equal>>
							<tr>
								<td colspan="6">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button onclick="dataSave();" id="buttonSave">
											����
										</button>
										<button class="button2" onclick="Close();return false;" id="buttonQx">
											ȡ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
			alert("�����ɹ���");
			Close();
			window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<logic:equal value="yes" name="isexists">
			<script language="javascript">
			alert("��¥���Ѿ��ڱ�ѧ�������ˣ������ظ����룡");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
				alert("����ʧ�ܣ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
		</script>
		</logic:equal>
		<logic:equal value="yes" name="existx">
			<script language="javascript">
			alert("�û��Ѵ��ڣ�");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
		function cheangelx(){
			var cheangelx = document.getElementById("zwpdlx").value;
			if(cheangelx == "ѧ��"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="none";
				document.getElementById("yfV").style.display="none";
			}else if(cheangelx == "ѧ��"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="inline";
				document.getElementById("yfV").style.display="none";
			}else if(cheangelx == "��"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="inline";
				document.getElementById("yfV").style.display="inline";
			}else{
				document.getElementById("xnV").style.display="none";
				document.getElementById("xqV").style.display="none";
				document.getElementById("yfV").style.display="none";
			}
	 	}
	</script>
</html>
