<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
		function jxjSqSavett(){
			var xmdm = document.getElementById('jxjdm').value;
			var xh = document.getElementById('xh').value;
			var xxjl = document.getElementById('xxjl').value;
			var fdyyj = document.getElementById('fdyyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var yhkh = document.getElementById('yhkh').value;

			if(!isNumber(yhkh)){
				alert("���п���ֻ��������!");
				return false;
			}
			if(xmdm == null || xmdm == ""){
				alert("��ѡ��Ҫ����Ľ���ѧ��!");
				return false;
			}
			if(xh == null || xh == ""){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(fdyyj != null){
	         	if(fdyyj.length > 200){	         
	          		 alert("�༶�Ƽ�������ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			if(xxjl != null){
	         	if(xxjl.length > 800){	         
	          		 alert("�������벻�ܴ���400���ַ���");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.length > 200){	         
	          		 alert(jQuery("#xbmc").val()+"������ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			if(xxshyj != null){
	         	if(xxshyj.length > 200){	         
	          		 alert("ѧУ������ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&doType=save&jxjtype=yxxsjxj";
			document.forms[0].submit();
		}
		
		function chang(){
			
			alert('tt');
			return false;
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq";
			document.forms[0].submit();
		}
		
		function toPrintOut(){
			var xmdm = document.getElementById('xmdm').value;
			if(xmdm == null || xmdm == ""){
				alert("��ѡ��Ҫ���صĽ���ѧ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmmbxz&xmdm="+xmdm;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		function initjxjList(){
			var jxjdm = document.getElementById("jxjdm").value;
			GetListData.getJxjdm(jxjdm,function initTjList(data){
					if (data != null) {
						if(document.getElementById("jxjlb").value!=data || document.getElementById("jxjlb").value=="ר�ѧ��"){
							document.getElementById("jxjlb").value=data;
							if(data=="ͻ�����׽�ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=tcgxjxj";
								document.forms[0].submit();
							}else if(data=="����ѧ����ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxxsjxj";
								document.forms[0].submit();
							}else if(data=="��Ṥ����ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=shgzjxj";
								document.forms[0].submit();
							}else if(data=="���д��½�ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=kycxjxj";
								document.forms[0].submit();
							}else if(data=="���ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=dxjxj";
								document.forms[0].submit();
							}else if(data=="�����ҵ����ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxbysjxj";
								document.forms[0].submit();
							}else if(data=="ר�ѧ��"){
								var jxjlb;
								if(jxjdm=="00071"){
									jxjlb="fzzgjxj";
								}else if(jxjdm=="00072"){
									jxjlb="gjjxj";
								}else if(jxjdm=="00073"){
									jxjlb="hdjxj";
								}else if(jxjdm=="00074"){
									jxjlb="smjxj";
								}else{
										alert("ѡ�����������ѡ��");
								}
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype="+jxjlb;
								document.forms[0].submit();
							}
						}else{
						}
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}
				});
		}

		function heckistype(){
			var userlx = document.getElementById("userlx").value;
			if(userlx=="stu"){
				document.getElementById("xylxyj1").style.display = "none";
				document.getElementById("xylxyj2").style.display = "none";
				document.getElementById("xxlxyj1").style.display = "none";
				document.getElementById("xxlxyj2").style.display = "none";
			}else if(userlx=="xy"){
				document.getElementById("xxlxyj1").style.display = "none";
				document.getElementById("xxlxyj2").style.display = "none";
			}else if(userlx=="xx"){
				document.getElementById("xylxyj1").style.display = "none";
				document.getElementById("xylxyj2").style.display = "none";
			}
		}
		function pjpyjxjprint(){
			var jxjdm = document.getElementById("jxjdm").value;

			if(jxjdm == ""){
				alert("��ѡ��ѧ������");
				return false;
			}
			var xh = document.getElementById("xh").value;
			var xn = $('xn').value;
			window.open('/xgxt/zjlgPjpy.do?method=jxjReport&pkValue='+xh+xn+jxjdm+"&jxjcxzj="+jxjdm);
			//"height=867,width=1024,status=yes,toolbar=no,menubar=no,location=no");
			//showOpenWindow('/xgxt/zjlgPjpy.do?method=jxjReport&pkValue='+xh+'&jxjcxzj='+jxjdm,'900','700');
			//showOpenWindow('/xgxt/zjlgPjpy.do?method=jxjReport&xh='+xh,'900','700');
			//document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjReport&pkValue='+xh+'&jxjcxzj='+jxjdm;
			//document.forms[0].target = "_blank";
			//document.forms[0].submit();
			//document.forms[0].target = "_self";
			}
	</script>
</head>

<body onload="heckistype();">
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>�������� - ��ѧ������</a>
		</p>
	</div>
	
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڻ򲻷�����������������
			</p>
		</center>
	</logic:equal>
	
		<html:form action="/zjlgPjpy" method="post">
			
<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="url" name="url"
				value="/zjlgPjpy.do?method=yxxsjxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" />"/>
			<input type="hidden" id="xq" name="xq"
				value="<bean:write name="rs" property="xq" />"/>
			<input type="hidden" id="jxjmc" name="jxjmc"
				value="yxxsjxj"/>
				
			<input id="userlx" type="hidden" value="<bean:write name="userType" scope="session" />"/>	
			
			
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("��ͨ����ˣ��������룡");
	         		</script>
				</logic:match>
			</logic:present>

			<div class="div">
			<table class="formlist" width="90%">
			<thead>
				<tr><th colspan='4'><span>��д�����</span></th></tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<font color="red">*</font>��ѧ������
					</th>
					<td width="25%">
						<html:select property="jxjdm" style="width:160px"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
					</td>
					<th>
						<div>
							��ѧ�����
						</div>
					</th>
					<td>
						<html:text property="jxjlb" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td>
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td>
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly" />
						</td>
					</logic:equal>
					<th>
						<div>
							����
						</div>
					</th>
					<td>
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<font color="red">*</font>���п���
						</div>
					</th>
					<td>
						<html:text property="yhkh" maxlength="50"></html:text>
					</td>
					<th>
						<div>
							��������
						</div>
					</th>
					<td>
						<!--<html:text property="yhlx" maxlength="100" ></html:text>-->
						<html:select property="yhlx" name="rs" styleId="yhlx"
									>
									<html:option value=""></html:option>
									<html:options collection="yhklxList" property="yhklxmc" labelProperty="yhklxmc"/>
								</html:select>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ѧ��
						</div>
					</th>
					<td>
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<th>
						<div>
							ѧ��
						</div>
					</th>
					<td>
						<html:text name="rs" property="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							�Ա�
						</div>
					</th>
					<td>
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
					<th>
						<div>
							��������
						</div>
					</th>
					<td>
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							������ò
						</div>
					</th>
					<td>
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<th>
						<div>
							����ְ��
						</div>
					</th>
					<td>
						<html:text name="rs" property="zw" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</th>
					<td>
						<html:text name="rs" property="xymc" readonly="true"></html:text>
					</td>
					<th>
						<div>
							�༶
						</div>
					</th>
					<td>
						<html:text name="rs" property="bjmc" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
								����������
						</div>
					</th>
					<td>
						<html:text name="rs" property="zhszcpcjpm" readonly="true"></html:text>
					</td>
					<th>
						<div>
							����
						</div>
					</th>
					<td>
						<html:text name="rs" style="width:60px" property="dycj" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							����
						</div>
					</th>
					<td>
						<html:text name="rs" style="width:60px" property="zycj" readonly="true"></html:text>
					</td>
					<th>
						<div>
							����
						</div>
					</th>
					<td>
						<html:text name="rs" style="width:60px" property="tycj" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th rowspan="2" align="center">
						<div>
							��������
						</div>
					</th>
					<td onpropertychange="this.style.posHeight=this.scrollHeight" colspan="3">
						�����˵���������ı��֣�����������л�����ķ������ʵ���ȷ��棩
					</td>
				</tr>
				<tr>
					<td class="word-break" colspan="3">
						<html:textarea property="xxjl" rows='6' style="width:100%"/>
					</td>
				</tr>
				<tr id="xylxyj1">
					<th align="center">
						<div>
							�༶�Ƽ����
						</div>
					</th>
					<td class="word-break" colspan="3">
						<html:textarea property="fdyyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xylxyj2">
					<th rowspan="1">
						<div>
							<bean:message key="lable.xb" />���
						</div>
					</th>
					<td class="word-break" colspan="3">
						<html:textarea property="xyshyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xxlxyj1">
					<th rowspan="1">
						<div>
							ѧУ���
						</div>
					</th>
					<td class="word-break" colspan="3">
						<html:textarea property="xxshyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xxlxyj2">
					<th>
						<div>
							��ע
						</div>
					</th>
					<td colspan="3">
					<html:textarea name="rs" property="bz" rows='6' style="width:100%;display: none" />
						1. ����һʽ���ݣ�������д����Ҫ����Դ�ӡ��<br/>
						2. <bean:message key="lable.xb" />���༶��д�����
					</td>
				</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
				        <button type="button" id="buttonSave" onclick="jxjSqSavett();">
							�ύ����
						</button>
						<button type="button" onclick="pjpyjxjprint()">
							��ӡ����
						</button>
						<button type="button" id="btn_cjprint" onclick="showOpenWindow('pjpy_shcbys_cjprint.do?xh='+document.getElementById('xh').value,700,600)">
							ѧ���ɼ���
						</button>
						<button type="button" id="btn_cfqk" onclick="showOpenWindow('zjlgPjpy.do?method=jxjcfqk&xh='+document.getElementById('xh').value+'&xn='+document.getElementById('xn').value+'&xq='+document.getElementById('xq').value,700,600)">
							�������
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
			</table>
			</div>
			
			<logic:equal name="done" value="true">
				<script>
			          alert("����ɹ���");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("����ʧ�ܣ�");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("�ý�ѧ��������,����ͨ����ز������\n�����������,�����ٴ����룡");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf1">
			<script>
			        alert("�����ɼ���������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf2">
			<script>
			        alert("�����ֲ���������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf3">
			<script>
			        alert("�����ֲ���������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf4">
			<script>
			        alert("��������������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf5">
			<script>
			        alert("�����ɼ���������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf6">
			<script>
			        alert("����ɼ�����������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf7">
			<script>
			        alert("�ۺ����ʷ���������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf8">
			<script>
			        alert("�ۺ����ʷֲ���������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf9">
			<script>
			        alert("�㲻�Ǳ�ҵ������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf9">
			<script>
			        alert("��ѧ���������ۺ����ʲ����֣�");			    
			        </script>
			</logic:equal>
			<logic:equal name="jxjcftj" value="yes">
			<script>
			        alert("�ý�ѧ��������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="jxjjd1">
			<script>
			        alert("��־���ѧ�������ѧ����ѧ���ܼ��");			    
			        </script>
			</logic:equal>
			<logic:equal name="isczcpf" value="isczcpf">
			<script>
			        alert("�Ҳ�����ѧ�����ۺ����ʲ����ɼ�����������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="jxjjd2">
			<script>
			        alert("����ѧ����ѧ���ܼ��");			    
			        </script>
			</logic:equal>
			<logic:equal name="cpzisnull" value="cpzisnull">
			<script>
			        alert("��ѧ�����ڲ�����Ϊ�գ���������");			    
			        </script>
			</logic:equal>
		</html:form>
</body>
</html>
