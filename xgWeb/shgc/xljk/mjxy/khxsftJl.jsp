<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/Function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		
		<script language="javascript">	     
	     function sqSave(){
	        refreshForm("/xgxt/xljkMjxyKhxsft.do?method=khxsftJl&doType=save");
	        if($("buttonSave")){
	          $("buttonSave").disabled=true;
	        }
	     }
	     function khxsftjlb(){
	        window.open('xljkMjxyKhxsft.do?method=khxsftjlb&pkValue=${pkValue}');
	       }	
	       
	       function gzjlb(){
	        window.open('xljkMjxyKhxsft.do?method=fdygzftb&pkValue=${pkValue}');
	       }	
		</script>
		</head>
		<body>
		   <html:form action="/xljkMjxyKhxsft" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<logic:equal name="doType" value="add">
				<input type="hidden" name="save_txsj" id="save_txsj" value="${nowTime}"/>
			</logic:equal>
			<logic:equal name="doType" value="modi">
				<input type="hidden" name="save_txsj" id="save_txsj" value="${rs.txsj}"/>
			</logic:equal>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<logic:present name="result">
			<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
			</logic:present>
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>��д�����</b>
						</td>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" style="width: 10%">
						 <logic:equal name="doType" value="add">
						 <font color="red"/>*</font>
						 </logic:equal>
						���
					</th>
					<td>
					<logic:equal name="doType" value="add">
						<html:text property="save_bh" value="${rs.bh }"/>
					</logic:equal>
					<logic:notEqual name="doType" value="add">
						<html:text property="save_bh" value="${rs.bh }" readonly="true"/>
					</logic:notEqual>
					</td>
					<th>
						��̸ʱ��
					</th>
					<td>
					<logic:equal name="doType" value="add">
						<html:text property="save_ftsj" value="${rs.ftsj }" />
					</logic:equal>
					<logic:notEqual name="doType" value="add">
						<html:text property="save_ftsj" value="${rs.ftsj }"  readonly="true"/>
					</logic:notEqual>
					</td>
				</tr>
				
				<tr >
					<th>
						��̸��ʦ
					</th>
					<td>
					<logic:equal name="doType" value="add">
					   <html:text property="save_ftls" value="${rs.ftls }"/>
					</logic:equal>
					<logic:notEqual name="doType" value="add">
						<html:text property="save_ftls" value="${rs.ftls }"  readonly="true"/>
					</logic:notEqual>
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>
						ϵ(Ժ)վ���������
						<br/>
						<font  color="red">(��500��)&nbsp;</font>
					</th>
					<td colspan="3">
						<html:textarea property="save_gzqk"  style="word-break:break-all;"
						 rows="8" cols="86" value="${rs.gzqk }" onblur="chLeng(this,500)"></html:textarea>
						
					</td>
				</tr>
				<tr >
					<th >
						��̸��Ҫ����
						<br />
						<font  color="red">(��500��)&nbsp;</font>
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="8" cols="86"  style="word-break:break-all;" property="save_zynr" value="${rs.zynr}"  onblur="chLeng(this,500)"/>
					</td>
				</tr>
				<tr >
					<th align="right">
						��̸���
						<br />
						<font  color="red">(��500��)&nbsp;</font>
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="8" cols="86"  style="word-break:break-all;"
						 property="save_ftjg" value="${rs.ftjg}"  onblur="chLeng(this,500)"/>
					</td>
				</tr>
				<tr >
					<th align="right">
						�ճ�ѧϰ���������������
						<br />
						<font  color="red">(��500��)&nbsp;</font>
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="8" cols="86"  style="word-break:break-all;"
							 property="save_jbqk" value="${rs.jbqk}"  onblur="chLeng(this,500)"/>
					</td>
				</tr>
				</tbody>
				 <tfoot>
			      <tr>
			        <td colspan="6">
			          <logic:equal name="doType" value="add">
			          <div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          </logic:equal>
			          <div class="btn">
			          	<logic:equal name="writeAble" value="yes">
				          	<logic:equal name="doType" value="add">
								<button  id="buttonSave" onclick="sqSave();" style="width:80px">
									��  �� 
								</button>
							</logic:equal>
						<logic:equal name="doType" value="modi">
							<button  id="buttonSave" onclick="refreshForm('/xgxt/xljkMjxyKhxsft.do?method=khxsftOne&doType=update')" style="width:80px">
									��  �� 
							</button>
						</logic:equal>
						</logic:equal>
						<button class="button2" onclick="khxsftjlb()" style="width:80px">
							��̸��¼
						</button>   
						<button class="button2" onclick="gzjlb()" style="width:80px">
							���ټ�¼
						</button>   
			          </div>
			          </td>
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

