<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
	<script language="javascript">	     
     function rychSqSave(){
        var xh = "";
        var rychdm = "";
        var bz  = "";
        var xn  = "";
        var pkValue= "";
        if($("xh")){
           pkValue = $("pkValue").value;
        }
        if($("xh")){
           xh = $("xh").value;
        }
        if($("rychdm")){
           rychdm = $("rychdm").value;
        }
        if($("bz")){
          bz = $("bz").value;
        }
        if($("xn")){
          xn= $("xn").value;
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
        if(xn==""){
          alert("ѧ�겻��Ϊ�գ�");
          return false;
        }
        var url= "/xgxt/zjlgPjpy.do?method=rychModi&doType=save";
        var tem = xh+xn+rychdm; 
        getPjpyDao.getInfoEx("xsrychb","xh||xn||rychdm",pkValue," xysh='ͨ��'",function(tag){
		     if(tag){
		        alert("����Ϣ�Ѿ�ͨ����ˣ������ٽ��в�����");
		     }else{
		        if(confirm("ȷ��Ҫ�������ݣ�")){
		           refreshForm(url);
		           if($("buttonSave")){
                     $("buttonSave").disabled=true;
                   }
		        }              
		     }
    	});	                  
     }
     function viewLoad(){
        var act = "";
        if($("act")){
          act = $("act").value;
        }
        if(act=="view"){
          $("buttonSave").style.display="none";
        }
     }
</script>
</head>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body onload="viewLoad()">
		<html:form action="/zjlgPjpy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - �����ƺ����� - ��������ѯ - ѧ�������ƺ���Ϣ����</a>
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
				value="/zjlgPjpy.do?method=rychAdd" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act"/>" />
			<%--				<div align="left">--%>
			<%--				<br>--%>
			<%--				<font color="red">--%>
			<%--				��ʾ���˹���ֻ���ѷ���Ȩ�޵�ѧУ�û������Ա�ɲ������������Ϣ����Ҫ��˼���ͨ����--%>
			<%--				</font>--%>
			<%--				</div>		--%>
			<div class="tab">
			<table width="99%" id="rsT" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>��д��Ϣ��</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" style="width: 10%">
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true" />
					</td>
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
						<html:select name='rsOther' property="rychdm" styleId="rychdm">
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
					</td>
				</tr>
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
						<bean:message key="lable.xsgzyxpzxy" />��
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
						<html:textarea rows="8" style="width:98%" name='rsOther'
							property="bz" />
					</td>
				</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave" onclick="rychSqSave();">�� ��</button>
			            <button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
			</table>
			</div>
			
			<logic:equal name="done" value="true">
				<script>
			          alert("�޸ĳɹ���");
                      if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	                    window.dialogArguments.document.all("search_go").click();
    	                    Close();
                      }			          
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("�޸�ʧ�ܣ�");
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
