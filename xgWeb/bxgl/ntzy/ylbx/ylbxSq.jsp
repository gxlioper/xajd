<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
	<script language="javascript">	     
     function SqSave(){
     	var xh="";
     	
     	xh=$("xh").value;
     	bxsj=$("bxsj").value;
     	if(xh==""){
     		alert("������ѧ��!");
     		return false;
     	}
     	
     	if(bxsj==""){
     		alert("����д����ʱ��!");
     		return false;
     	}
     	$("save_tzlsh").value=xh+bxsj;
        refreshForm("/xgxt/ntzyYlbx.do?method=ylbxSq&doType=save");
        showTips("������,���Ժ�...");
        if($("buttonSave")){
        $("buttonSave").disabled=true;
        }
     }
     
      function updateYlbx(){
        refreshForm("/xgxt/ntzyYlbx.do?method=ylbxOne&doType=modi");
        showTips("������,���Ժ�...");
        if($("buttonSave")){
        $("buttonSave").disabled=true;
        }
     }
</script>
</head>
	<body>
		
		   <html:form action="/ntzyYlbx" method="post">
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-jg-mzmc" />
			<input type="hidden" id="url" name="url" value="/ntzyYlbx.do?method=ylbxSq" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="save_sbsj" id="save_sbsj" value="${nowTime}"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<logic:equal name="act" value="save">
				<input type="hidden" name="save_tzlsh" id="save_tzlsh"/>
			</logic:equal>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:present name="result">
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
			</logic:present>
			
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>ҽ�Ʊ��ռ�¼</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <logic:equal name="writeAble" value="yes">
						<logic:equal name="act" value="save">
						<button id="buttonSave" onclick="SqSave();" >
							��  �� 
						</button>
						</logic:equal>
						</logic:equal>
			
						<logic:equal name="writeAble" value="yes">
						<logic:equal name="act" value="modi">
						<button  id="buttonSave" onclick="updateYlbx();" >
							��  �� 
						</button>
						<button  id="buttonSave" onclick="Close();return false;" >
							��  ��
						</button>	
						</logic:equal>
						</logic:equal>
			
						<logic:equal name="writeAble" value="yes">
							<logic:equal name="act" value="view">
								<button  id="buttonSave" onclick="Close();return false;">
								��  ��
								</button>	
							</logic:equal>
						</logic:equal>
			          </div>
			         </td>
			      </tr>
			    </tfoot>
			    
			 
			    
				<tbody>
				<tr style="height:22px">
					<th align="right" style="width: 10%">
						<font color="red">*</font>ѧ��
					</th>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="left">
						<logic:notEmpty name="rs" scope="request">
							<html:text  property="xh" styleId="xh" readonly="true"
								onblur="dctStuXh()" name="rs"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<html:hidden name='rs' property="save_xh" value="${rs.xh}" />
						</logic:notEmpty>
						<logic:empty name="rs" scope="request">
							<html:text  property="xh" styleId="xh" 
								onblur="dctStuXh()" 
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
						</logic:empty >
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								 id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="left">
						<logic:notEmpty name="rs"  scope="request">
							<html:text name='rs' property="xh" readonly="true"/>
							<html:hidden name='rs' property="save_xh" value="${rs.xh}" />
						</logic:notEmpty>
						</td>
					</logic:equal>

					<th align="right" style="width: 10%">
						����
					</th>
					<td align="left" style="width: 40%">
						${rs.xm}
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						${rs.xb}
					</td>
					<th align="right">
						���֤��
					</th>
					<td align="left">
						${rs.sfzh}
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�꼶
					</th>
					<td align="left">
						${rs.nj}
					</td>
					<th align="right">
						<font color="red"></font><bean:message key="lable.xb" />
					</th>
					<td align="left">
						${rs.xymc}
					</td>		
				</tr>
				<tr style="height:22px">
					<th align="right">
						רҵ
					</th>
					<td align="left">
						${rs.zymc }
					</td>
					<th align="right">
						�༶
					</th>
					<td align="left">
						${rs.bjmc }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��������
					</th>
					<td align="left">
						${rs.csrq }
					</td>
					<th align="right">
						������
					</th>
					<td align="left">
						${rs.cym }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						<font color="red">*</font>����ʱ��
					</th>
					<td align="left">
						<logic:equal name="act" value="save">
							 <html:text property="save_bxsj" styleId="bxsj" 
							onclick="return showCalendar('bxsj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />
						</logic:equal>
						<logic:notEqual name="act" value="save">
							${rs.bxsj}
							<input type="hidden" name="save_bxsj" id="save_bxsj" value="${rs.bxsj }"/>
						</logic:notEqual>
					</td>
					<th>
						&nbsp;
					</th>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						��ע
						<br />
						<span style="color:red"><��400��></span>
					</th>
					<td colspan="3" align="left">
						<logic:equal name="act" value="save">
							<html:textarea rows="8" style="width:98%" property="save_bz" onblur="chLeng(this,400)"/>
						</logic:equal>
						<logic:notEqual name="act" value="save">
							<html:textarea rows="8" style="width:98%" name="rs" property="save_bz" onblur="chLeng(this,400)"/>
						</logic:notEqual>
					</td>
				</tr>
				</tbody>
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

