<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
		<script type="text/javascript">
	var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
		return count+ "<input type='hidden' style='width:100%'  name='_xuh' value='"+count+"'>";
	},
	function(data){	
	    var htmltext = "<select style='width:100%'  name='fzxm' id='fzxm" + count + "'>";
	  		htmltext+= "<option value='grszf'>�������ʷ�</option>";
	  		htmltext+= "<option value='jsssf'>����������5S��</option>";
	  		htmltext+= "<option value='ktf'>����5S��</option>";
	  		htmltext+= "<option value='cxf'>���ŷ�</option>";
	  		htmltext+= "<option value='qtf'>������</option></select> ";
		return htmltext;
    },	
	function(data){	    
		var htmltext = "<select style='width:100%'  name='jjf' id='jjf" + count + "'>";
	  		htmltext+= "<option value='�ӷ�'>�ӷ�</option>";
	  		htmltext+= "<option value='����'>����</option></select>";
		return htmltext;
    },
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='fz' id='fz" + count + "' maxlength='5'";
    		htmltext += "onblur='onlyNumInput(this)'/>";
    	return htmltext;
    },
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='lrrq' id='lrrq" + count + "'";
    		htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
			htmltext+="onclick='time(this.id)'/>";
 
    	return htmltext;
    },
    function(data){
    	//var htmltext = "<textarea type='text' style='width:100%' name='yy' id='yy"+count+"'";
			//htmltext+= "rows='2' onblur='chLeng(this,250);repYh(this);'/>";	  
		var htmltext = "<select style='width:100%'  name='yy' id='yy" + count + "'>";
	  		htmltext+= $('jjfyy').innerHTML;
    	return htmltext;
    },
    function(data){
    	var htmltext = "<input type='text' style='width:100%' name='xxsh' id='xxsh"+count+"' readonly='true' value='δ���'/>";  
    	return htmltext;
    }
	];
	
	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $("numAdd").value = "";
    }        
}
function time(id){
	return showCalendar(id,'y-mm-dd');
}
function repYh(obj){
	
}
function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("ȷ��Ҫɾ����"+(length)+"�У�")){
       if($("xxsh"+length).value!="δ���"){
         alert("�ü�¼�Ѿ�����ز�����ˣ����ܽ���ɾ��������");   
         return false;
       }else{
         tabobj.deleteRow(tabobj.rows.length-1);    
       }       
    }
}
function trDelAll(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $("numDel").value; 
    if(length==0){
       $("numDel").value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){ 
         for(i=1;i<=num;i++){           
            if($("xxsh"+length).value!="δ���"){
              alert("���Ѿ�����ز�����˵ļ�¼�����ܽ���ɾ��������");   
              return false;
            }
            length--;
         }
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDel").value = "";
}	
function fillValue(obj){
		var xm= "xm" + obj.id.replace("xh","");
		var bj= "bj" + obj.id.replace("xh","");
  		if(obj.value==""){
  		  return false;
  		}	
		getSztzData.getXsInfo(obj.value,function(data){
		if(data!=null){
			$(xm).value=data[0];
			$(bj).value=data[1];
		}else{
			alert("��ѧ�Ų����ڣ���������ȷѧ�ţ�");
			obj.value="";
			obj.focus();
			}
		});

}
function ztbhSave(the_tab){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	var xh = $("xh").value;
	var xn = $("xnV").value;
	var xq = $("xqV").value;
	
	if(xh == ""){
		alert("��ȷ���������ѧ��");
		return false;
	}
	if(rowLen == 0){
		alert("�������Ϊ��");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
		if($("lrrq"+i)){
			if($("lrrq"+i).value == ""){
				alert("��"+i+"������Ϊ�գ���ȷ��");
				return false;
			}
		}
		if($("fz"+i)){
			if($("fz"+i).value == ""){
				alert("��"+i+"�з�ֵΪ�գ���ȷ��");
				return false;
			}
		}
		if($("yy"+i)){
			if($("yy"+i).value == ""){
				//alert("��"+i+"�мӷ�ԭ��Ϊ�գ���ȷ��");
				//return false;
			}
		}
	}

   showTips('���������У���ȴ�......');
   refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_5sAdd&doType=save&xn='+xn+'&xq='+xq);
   $("buttonSave").disabled=true;
}

function onShow(){
	var pk = jQuery("#pk").val();
	if(pk == ""){
		if(jQuery("#xnV")&& jQuery("#xqV") && jQuery("#xh")){
			if(jQuery("#xnV").val() != "" &&
			   jQuery("#xqV").val() != "" &&
			   jQuery("#xh").val() != ""){
			  
			   pk=jQuery("#xh").val()+jQuery("#xnV").val()+jQuery("#xqV").val();
			}
		}
	}
	if(pk != ""){
		dwr.engine.setAsync(false);
		getSzPjpyDAO.dao_get5s(pk,function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');
				for(var i=1;i<=data.length;i++){
					if($("fzxm"+i)){
						$("fzxm"+i).value = data[i-1].fzxm;
					}
					if($("fz"+i)){
						var fz = data[i-1].fz;
						if(fz == null){
							fz = "";
						}
						$("fz"+i).value = fz;
					}
					if($("jjf"+i)){
						$("jjf"+i).value = data[i-1].jjf;
					}
					if($("lrrq"+i)){
						$("lrrq"+i).value = data[i-1].lrrq;
					}
					if($("yy"+i)){
						var yy = data[i-1].yy;
						if(yy == null){
							yy = "";
						}
						$("yy"+i).value = yy;
					}
					if($("xxsh"+i)){
						$("xxsh"+i).value = data[i-1].xxsh;
						if($("xxsh"+i).value == "ͨ��"){
							$("fz"+i).readOnly = "true";
							var fzxmindex = $("fzxm"+i).options.length;	
							var jjfindex = $("jjf"+i).options.length;	
							for(var j=0;j<fzxmindex;j++){		
								if(j==(fzxmindex-1)){
									var xmmc;
									if(data[i-1].fzxm=="grszf"){
										xmmc="�������ʷ�";
									}else if(data[i-1].fzxm=="jsssf"){
										xmmc="����������5S��";
									}else if(data[i-1].fzxm=="ktf"){
										xmmc="����5S��";
									}else if(data[i-1].fzxm=="cxf"){
										xmmc="���ŷ�";
									}else if(data[i-1].fzxm=="qtf"){
										xmmc="������";
									}
									$("fzxm"+i).options[0].value = data[i-1].fzxm;
									$("fzxm"+i).options[0].text = xmmc;
								}else{
									$("fzxm"+i).options[0] = null;
								}
							}				
							for(var j=0;j<jjfindex;j++){		
								if(j==(jjfindex-1)){
									$("jjf"+i).options[0].value = data[i-1].jjf;
									$("jjf"+i).options[0].text = data[i-1].jjf;
								}else{
									$("jjf"+i).options[0] = null;
								}
							}
						}
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
	}
}

jQuery(function(){
	onShow();
})
	</script>
	</head>
	<body>
		<html:form action="/pjpyszyqwh">
			<input type="hidden" id="url" name="url"
				value="/pjpyszyqwh.do?method=szyc_5sAdd" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xh-xm-xb-xymc-zymc-bjmc" />
			<input type="hidden" id="pk" name="pk" value="${pk}" />
			<input type="hidden" id="xnV" name="xnV" value="${xnV}" />
			<input type="hidden" id="xqV" name="xqV" value="${xqV}" />
			<input type="hidden" id="msg" name="msg" value="${msg}" />
			<input type="hidden" id="showSelect" name="showSelect" value="no" />
			<input type="hidden" id="doType" name="doType" value="${doType}" />
			<html:select property="jjfyy" style="display:none"
				styleClass="select" styleId="jjfyy">
				<html:options collection="yyList" property="dm" labelProperty="mc" />
			</html:select>



			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>5s��ά��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button type="button" name="����" onclick="ztbhSave('flag');" id="buttonSave">
											����
										</button>
									</logic:notEqual>
									<button type="button" name="�ر�" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<logic:equal name="doType" value="add">
									<html:text name='rs' property="xh" styleId="xh" readonly="true"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								</logic:notEqual>
							</td>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:text name='rs' property="xn" styleId="xn" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<html:text name='rs' property="xb" styleId="xb" readonly="true" />
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:text name='rs' property="xq" styleId="xq" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<html:text name='rs' property="xm" styleId="xm" readonly="true" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:text name='rs' property="xymc" styleId="xymc"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								<html:text name='rs' property="zymc" styleId="zymc"
									readonly="true" />
							</td>
							<th>
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bjmc"
									readonly="true" />
							</td>
						</tr>

						<tr>
							<td colspan="4">
								<!-- ��ѯ�õ�����������ʾ���� -->
								<input  value="+" onclick="trAdd('flag','add')"
									style="width: 20px" />
								<input  value="-" onclick="trDel('flag')"
									style="width: 20px" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
								<input type="text" name="numAdd" id="numAdd" style="width: 20px"
									onblur="trAdd('flag','madd')" />
								&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
								<input type="text" name="numDel" id="numDel" style="width: 20px"
									onblur="trDelAll('flag')" />
								&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="red">ע�����ͨ������Ŀ(��ԭ����)�����޸��Լ�ɾ��</font>
							</td>
						</tr>
						</tbody>

						<tr>
							<td colspan="4">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 23px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:5%'>
												���
											</td>
											<td align="center" nowrap="nowrap" style='width:25%'>
												��Ŀ
											</td>
											<td align="center" nowrap="nowrap" style='width:12%'>
												�Ӽ���
											</td>
											<td align="center" nowrap="nowrap" style='width:10%'>
												��ֵ
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												����
											</td>
											<td align="center" nowrap="nowrap" style='width:33%'>
												�ӷ�ԭ��
											</td>
											<td align="center" nowrap="nowrap" style='width:33%'>
												���״̬
											</td>
										</tr>
									</thead>
									<tbody class="formlist" id="flag">
									</tbody>
								</table>
							</td>
						</tr>
				</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
