<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/stuinfoFunction.js"></script>	
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script>
			function checkExist(){
				var xh = document.getElementById("xh").value;
				getStuDetails.checkGradArchiveApply(xh,function(data){
					if(data){
						if(confirm("�Ѿ�������ת������ļ�¼���Ƿ�Ҫ�������룿")){
							commSave('/xgxt/business.do?method=gradArchivesApply&doType=save',
							'xh-sqly-hkssqx-hkssjd-hkxxdz-rxsj-bysj');
						}else{
							return false;
						}
					}else{
						commSave('/xgxt/business.do?method=gradArchivesApply&doType=save',
							'xh-sqly-hkssqx-hkssjd-hkxxdz-rxsj-bysj');
					}						
				});
			}
			
			function print(url){
			var pk = "!!";
			var xh = document.getElementById("xh").value;
			//var xm = document.getElementById("xm").value;
			//var xydm = document.getElementById("xydm").value;
			//var zydm = document.getElementById("zydm").value;
			//var bjdm = document.getElementById("bjdm").value;
			var rxsj = document.getElementById("rxsj").value;
			var bysj = document.getElementById("bysj").value;
			var hkssqx = document.getElementById("hkssqx").value;
			var hkssjd = document.getElementById("hkssjd").value;
			var hkxxdz = document.getElementById("hkxxdz").value;
			var lxfs = document.getElementById("lxfs").value;
			var sqly = document.getElementById("sqly").value;
			//var nj = document.getElementById("nj").value;
			url += "&xh=" + xh;
			//url += "&xm=" + xm;
			//url += "&xydm=" + xydm;
			//url += "&zydm=" + zydm;
			//url += "&bjdm=" + bjdm;
			url += "&rxsj=" + rxsj;
			url += "&bysj=" + bysj;
			url += "&hkssqx=" + hkssqx;
			url += "&hkssjd=" + hkssjd;
			url += "&hkxxdz=" + hkxxdz;
			url += "&lxfs=" + lxfs;
			url += "&sqly=" + sqly;
			//url += "&nj=" + nj;
			
			window.open(url);
			}
		</script>
	</head>
	<body>
		<html:form action="/business.do" method="post">
		<input type="hidden" name="url" id="url" value="/shgc/stu_info/shgc/gradstu_archives_apply.jsp"/>
		<input type="hidden" value="xh-xm-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��ҵѧ��ת�� - ��дת�������</a>
				</p>
			</div>
				<logic:present name="isBys">
					<logic:equal value="no" name="isBys">
						<br/>
						<br/>
						<center>
							ֻ�б�ҵ�����ڵ�ѧ���������룬�㲻�Ǳ�ҵ�����ڵ�ѧ����
						</center>
					</logic:equal>
				</logic:present>	
				<logic:notPresent name="isBys">
				<table width="98%"  border="0" class="formlist">
					 <thead>
	    				<tr>
	        				<th colspan="4"><span>��ҵѧ��ת������</span></th>
	        			</tr>
	   				 </thead>
	   				 <tbody>
						<tr style="height:22px">
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th align="right">
									<font color="red">*</font>ѧ��
								</th>
								<td align="left">
								<html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button align="left" class="button2"
									onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									 id="buttonFindStu">
									ѡ��
								</button>								
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th align="right">
									<font color="red">*</font>ѧ��
								</th>
								<td align="left">
									<input type="text" id="xh" name="xh" value="<bean:write name="userName"/>" readonly="readonly" />
								</td>
							</logic:equal>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">	
								<bean:write name="rs" property="xymc"/>							
<%--								<html:select name="rs" property="xydm"  style="width:150px" styleId="xydm"--%>
<%--								onchange="refreshForm('business.do?method=gradArchivesApply')">--%>
<%--								<html:option value=""></html:option>--%>
<%--								<html:options collection="xyList" labelProperty="xymc" property="xydm"/>							--%>
<%--								</html:select>--%>
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right" >
								����
							</th>
							<td align="left">
							<bean:write name="rs" property="xm"/>							
<%--								<html:text name="rs"  property="xm" styleId="xh" />--%>
							</td>							
							<th align="right">
								רҵ
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc"/>							
<%--								<html:select name="rs"  property="zydm"  style="width:150px"--%>
<%--								onchange="refreshForm('business.do?method=gradArchivesApply')" styleId="zydm">--%>
<%--								<html:option value=""></html:option>--%>
<%--								<html:options collection="zyList" labelProperty="zymc" property="zydm"/>							--%>
<%--								</html:select>--%>
							</td>
						</tr>
						<tr style="height:22px">	
							<th align="right">
								�꼶
							</th>
							<td align="left">
								<bean:write name="rs" property="nj"/>	
<%--								<html:select name="rs"  property="nj"  style="width:150px"--%>
<%--								onchange="" styleId="nj">--%>
<%--								<html:option value=""></html:option>--%>
<%--								<html:options collection="njList" labelProperty="nj" property="nj"/>							--%>
<%--								</html:select>--%>
							</td>
							<th align="right">
								�༶
							</th>
							<td align="left">
							<bean:write name="rs" property="zymc"/>	
<%--								<html:select name="rs"  property="bjdm"  style="width:150px"--%>
<%--								onchange="" styleId="bjdm">--%>
<%--								<html:option value=""></html:option>--%>
<%--								<html:options collection="bjList" labelProperty="bjmc" property="bjdm"/>							--%>
<%--								</html:select>--%>
							</td>
						</tr>
						<tr style="height:22px">	
							<th align="right">
								<font color="red">*</font>��Уʱ��
							</th>
							<td align="left">
								<html:text name="rs" property="rxsj" styleId="rxsj" onclick="return showCalendar('rxsj','y-mm-dd');"/>
							</td>	
							<th align="right">
								<font color="red">*</font>��ҵʱ��
							</th>
							<td align="left">
								<html:text name="rs"   property="bysj" styleId="bysj" onclick="return showCalendar('bysj','y-mm-dd');"/>
							</td>				
						</tr>								
						<tr style="height:22px">
							<th align="right">
								<font color="red">*</font>������������
							</th>
							<td align="left">
								<html:text name="rs"  property="hkssqx" styleId="hkssqx"/>
							</td>
							<th align="right">
								<font color="red">*</font>���������ֵ�
							</th>
							<td align="left">
								<html:text name="rs"  property="hkssjd" styleId="hkssjd"/>
							</td>
						</tr>	
						<tr align="left">
							<th align="right">
								<font color="red">*</font>������ϸ��ַ
							</th>
							<td align="left" >
								<html:text name="rs"   property="hkxxdz" styleId="hkxxdz"/>
							</td>
							<th align="right">
								�绰��������ϵ��ʽ
							</th>
							<td align="left" >
								<html:text name="rs"  property="lxfs" styleId="lxfs"/>
							</td>
						</tr>						
						<tr align="left">
							<th align="right" width="150px">
								<font color="red">*</font>��������
							</th>
							<td align="left" colspan="3">
								<html:textarea name="rs"  property="sqly" style="height:100px;width:700px" styleId="sqly"/>
							</td>
						</tr>	
						</tbody>
						<tfoot>
						 <tr>
							<td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
						    <div class="btn">
							<button  
								onclick="checkExist()">
								�� �� �� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button   onclick="print('business.do?method=printBysArchivesApp')">
								�� ӡ �� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							</td>
						</tr>
					</tfoot>					
					</table>
				</logic:notPresent>	
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
				<script>
					alert("����ɹ���");
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
					alert("����ʧ�ܣ�");
				</script>
				</logic:equal>
				</logic:notEmpty>
		</html:form>
	</body>
</html>
