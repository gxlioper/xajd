<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
	<script language="javascript">	     
     function rychSqSave(){
        var xh = "";
        var rychdm = "";
        var bz  = "";
        if($("xh")){
           xh = $("xh").value;
        }
        if($("rychdm")){
           rychdm = $("rychdm").value;
        }
        if($("bz")){
          bz = $("bz").value;
        }
        if(xh==""){
          alert("ѧ�Ų���Ϊ�գ�");
          return false;
        }
        if(rychdm==""){
          alert("�����ƺŲ���Ϊ�գ�");
          return false;
        }  
        if(bz.length>1000){
          alert("��ע����������1000���ڣ�");
          return false;
        }      
        refreshForm("/xgxt/zjlgPjpy.do?method=rychSq&doType=save");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
     function rychBbPrint(){
         var rychdm ="";
         if($("rychdm")){rychdm = $("rychdm").value;}
         var xh     = "";
         if($("xh")){xh = $("xh").value;}
         var xn  = "";
         if($("xn")){ xn = $("xn").value;}
         if(rychdm==""){
            alert("��ѡ�������ƺţ�");
            return false;
         }
         var url = "/xgxt/zjlgPjpy.do?method=rychDjb";
             url +="&xn="+xn;
         //"/xgxt/zjlgPjpy.do?method=rychDjb&rychdm="+rychdm;
            // url += "&xh="+xh;
             
             //url +="&bz="+bz;
        document.forms[0].action = url;
	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
     }
</script>
</head>
	<body>
		<html:form action="/zjlgPjpy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - �����ƺ����� - ��д�����</a>
				</p>
			</div>

			<logic:equal name="rs" property="stuExists" value="no">
				<script>
			    alert("�������ѧ����Ч!");
			    </script>
			</logic:equal>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/zjlgPjpy.do?method=rychSq" />
			<input type="hidden" id="stab" name="stab" value="stab" />
			<%--					<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />--%>
			
			<div class="tab">
			<table width="99%" id="rsT" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>��д�����</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" style="width: 10%">
						<font color="red">*</font>ѧ��
					</th>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onblur="dctStuXh()"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="left">
							<html:hidden name='rs' property="xh" styleId="xh" />
							<bean:write name='rs' property="xh" />
						</td>
					</logic:equal>

					<th align="right" style="width: 10%">
						ѧ��
					</th>
					<td align="left" style="width: 40%">
						<html:select name="rsOther" property="xn" disabled="true">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						����
					</th>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<th align="right">
						<font color="red">*</font>�����ƺ�
					</th>
					<td align="left">
						<html:select property="rychdm" styleId="rychdm">
							<option value=""></option>
							<html:options collection="rychList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<th align="right">
						��Դ��
					</th>
					<td align="left">
						<bean:write name='rs' property="syd" />
					</td>				</tr>
				<tr style="height:22px">
					<th align="right">
						�꼶
					</th>
					<td align="left">
						<bean:write name='rs' property="nj" />
					</td>
					<th align="right">
						��ϵ�绰
					</th>
					<td align="left">
						<bean:write name='rs' property="lxdh" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
					<th align="right">
						����ְ��
					</th>
					<td align="left">
						<bean:write name='rs' property="zw" />
					</td>

				</tr>
				<tr style="height:22px">
					<th align="right">
						רҵ
					</th>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<th align="right">
						������ò
					</th>
					<td align="left">
						${rs.zzmmmc }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�༶
					</th>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
					<th align="right">
						�س�
					</th>
					<td align="left">
						<bean:write name='rs' property="tc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��ͥ��ַ
					</th>
					<td align="left" colspan="3" align="left">
						<bean:write name='rs' property="jtdz" />
					</td>					
				</tr>
				<tr style="height:22px">
					<th align="right">
						�۲�����
					</th>
					<td align="left">
						<bean:write name='rsOther' property="zcbjpm" />
					</td>
					<th align="right">
						�۲��
					</th>
					<td align="left">
						<bean:write name='rsOther' property="zhcj" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��������
					</th>
					<td align="left">
						<bean:write name='rsOther' property="zypm" />
					</td>
					<th align="right">
						������
					</th>
					<td align="left">
						<bean:write name='rsOther' property="zycj" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��������
					</th>
					<td align="left">
						<bean:write name='rsOther' property="dypm" />
					</td>
					<th align="right">
						������
					</th>
					<td align="left">
						<bean:write name='rsOther' property="dycj" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						������
					</th>
					<td align="left">
						<bean:write name='rsOther' property="tycj" />
					</td>
					<th align="right">
						
					</th>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						���˼��
						<br />
						<span class="style1">(��1000��)&nbsp;</span>
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="bz" />
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave" onclick="rychSqSave();">�� ��</button>
			            <button type="button" id="buttonSave" onclick="rychBbPrint()">
							��  ��
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
			        alert("�������ƺ�������,����ͨ����ز������\n�����������,�����ٴ����룡");			    
			        </script>
			</logic:equal>
			<logic:equal name="pass" value="no">
				<script>
			        alert("������������������ƺ�����������");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>

</html>
