<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
		<script type="text/javascript" src="comm/editor/zh_CN.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		//���� 
        var gsjjeditor;

		function yrdwDivSave(){
			var val = jQuery("input[name='dwlb']:checked").val();
			var checkIds;
			if(val == "01"){
                checkIds = "xydm-zgh-lxdh-bgdd-jj";
			} else {
                checkIds = "yrdwmc-hy-xm-lxdh-bgdd-jj-dzyx-sfzh";
			}

            if(!checkNotNull(checkIds)){
                showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
                return false;
            }
			if(!isTelephone("lxdh")){
                showAlert("��������ȷ����ϵ�绰!");
                return false;
			}
			/*if(val == "02"){
			    if(!checkPsw(jQuery("#mm").val())){
			        return
				}
			}*/
			if(jQuery("#bgdh").val()!="" && !isTelephone("bgdh")){
                showAlert("��������ȷ�İ칫�绰!");
                return false;
			}
            if(jQuery("#dzyx").val() != "" && !isEmail(jQuery("#dzyx").val())){
                showAlert("��������ȷ�ĵ�������!");
                return false;
			}
			if(jQuery("#xssh").val()!="" && !isTelephone("xssh")){
                showAlert("��������ȷ��ѧ���ֻ���!");
                return false;
			}
            var url="qgzx_jcdmwh_ajax.do?method=yrdwBc";
            ajaxSubFormWithFun("qgzxJcdmwhForm",url,function(data){
                if (data["message"] == "����ɹ���") {
                    showAlert(data["message"], {}, {
                        "clkFun" : function() {
                            if (parent.window) {
                                refershParent();
                            }
                        }
                    });
                } else {
                    showAlert(data["message"]);
                }
            });

		}
		//��λ���ı�
		function dwlbChange(target) {
			var val = jQuery(target).val();
//            jQuery("#dwxx").empty();
		    if(val == "01"){
				jQuery(".xnxx").attr("style","");
                jQuery(".xwxx").attr("style","display:none");
			} else {
                jQuery(".xnxx").attr("style","display:none");
                jQuery(".xwxx").attr("style","");
			}
        }
		jQuery(function(){
            //����ͼ
            /*var simpleOption = {
                name:'simple',
                resizeType : 1,
                themeType : 'simple',//��ʽ���
                items : [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons','link'
                ],
                afterBlur:function(){this.sync();}
            };
            gsjjeditor = KindEditor.create('textarea[name="jj"]',simpleOption);*/
		})
		</script>
	</head>
	<body >

		<html:form styleId="qgzxJcdmwhForm" action="/qgzx_jcdmwh_ajax" method="post" onsubmit="return false;">
			<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="4" style="width: 25%">
								<span>���˵�λ����ά��</span>
							</th>
						</tr>
					</thead>
				<tbody>
				<tr>
					<th>
						<span class="red">*</span>��λ���
					</th>
					<td colspan="3">
						<input type="radio" name="dwlb" value="01" onchange="dwlbChange(this);" checked="checked">У�ڵ�λ</input>
						<input type="radio" name="dwlb" value="02" onchange="dwlbChange(this);">У����ҵ</input>
					</td>
				</tr>
				</tbody>
					<tbody>
					<tr class="xnxx" >
						<th>
							<span class="red">*</span>��λ����
						</th>
						<td>
							<html:select property="xydm" styleId="xydm">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</td>
						<th><span class="red">*</span>����</th>
						<td>
							<input name="zgh" id="zgh" readonly>
							<button class="btn_01" type="button"
									onclick="showDialog('��ѡ��һ����ʦ',800,500,'qgzx_jcdmwh_ajax.do?method=selectTeacher');">ѡ��</button>
						</td>
					</tr>
					<tr class="xwxx" style="display: none">
						<th>
							<span class="red">*</span>��λ����
						</th>
						<td>
							<input name="yrdwmc" id="yrdwmc" maxlength="50"></input>
						</td>
						<th>
							<span class="red">*</span>��ҵ
						</th>
						<td>
							<input name="hy" id="hy" maxlength="50"></input>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>����������
						</th>
						<td id="xmtr">
							<input name="xm" id="xm" class="xwxx" style="display: none" maxlength="25">
						</td>
						<th>
							<span class="red">*</span>��ϵ�绰
						</th>
						<td>
							<input type="text" name="lxdh" id="lxdh"></input>
						</td>
					</tr>
						<%--<tr class="xwxx" style="display: none">
							<th>
								<span class="red">*</span>�û���
							</th>
							<td>
								<input name="yhm" id="yhm" maxlength="20">
							</td>
							<th>
								<span class="red">*</span>����
							</th>
							<td>
								<input type="text" name="mm" id="mm" maxlength="70"></input>
							</td>
						</tr>--%>
					<tr>
						<th>
							<span class="red">*</span>�칫�ص�
						</th>
						<td>
							<input name="bgdd" id="bgdd" maxlength="50"></input>
						</td>
						<th>
							�칫�绰
						</th>
						<td>
							<input  name="bgdh" id="bgdh"></input>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>��������
						</th>
						<td>
							<input name="dzyx" id="dzyx"></input>
						</td>
						<th>
							QQ
						</th>
						<td>
							<input  name="qq" id="qq" maxlength="20" onblur=""></input>
						</td>
					</tr>
					<tr>
						<th class="xwxx" style="display: none">
							<span class="red">*</span>���֤��
						</th>
						<td class="xwxx" style="display: none">
							<input name="sfzh" id="sfzh" onblur="chkSfzh(this);"></input>
						</td>
						<%--<th>
							<span class="red">*</span>��������
						</th>
						<td>
							<input name="gzsx" id="gzsx" maxlength="5" onblur="checkMoney(this)"></input>
						</td>--%>
					</tr>
					<tr>
						<th>
							��ϵѧ��
						</th>
						<td>
							<input name="lxxs" id="lxxs"></input>
						</td>
						<th>
							ѧ���ֻ�
						</th>
						<td>
							<input name="xssh" id="xssh"></input>
						</td>
					</tr>
					<tr>
						<th class="xnxx">
							<span class="red">*</span>ѧԺ�����ţ����</br><span class="red">(������2000����)</span>
						</th>
						<th class="xwxx" style="display: none">
							<span class="red">*</span>��ҵ���</br><span class="red">(������2000����)</span>
						</th>
						<td colspan="3">
							<textarea name="jj" id="jj" style="width:100%;height:270px" maxlength="2000"></textarea>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="����" onclick="yrdwDivSave();return false;">
										�� ��
									</button>
									<button type="button" name="�ر�" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>
