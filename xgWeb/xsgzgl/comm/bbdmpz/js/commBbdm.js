/**
 * 
 * @param data
 * @return
 */
function commBbdm(data){
			var mkdm = data['mkdm'];   		//ģ�����
			var ywid = data['ywid'];		//ҵ��id
			var thlj = data['thlj'];		//�˻�·��
			var h_title = data['h_title'];	//ҳ��title����
			var h_xn = data['h_xn'];      	//ҳ��ѧ�����
			var h_xqmc = data['h_xqmc'];	//ҳ��ѧ�ڲ���
			var h_zd1 = data['h_zd1'];		//�����ֶ�1
			var h_zd2 = data['h_zd2'];		//�����ֶ�2
			var h_zd3 = data['h_zd3'];		//�����ֶ�3
			var h_zd4 = data['h_zd4'];		//�����ֶ�4
			var h_zd5 = data['h_zd5'];		//�����ֶ�5
			var h_zd6 = data['h_zd6'];		//�����ֶ�6
			var h_zd7 = data['h_zd7'];		//�����ֶ�7
			var h_zd8 = data['h_zd8'];		//�����ֶ�8
			var h_zd9 = data['h_zd9'];		//�����ֶ�9
			var h_zd10 = data['h_zd10'];	//�����ֶ�10

			if(!mkdm){
				showAlertDivLayer("������ģ�����(ע������Ϣ���ܳ���������������,�����������ȷ��ģ����������)");
				return;
				}

			if(mkdm)
				jQuery("input[name='mkdm']").val(mkdm);
			if(ywid)
				jQuery("input[name='ywid']").val(ywid);
			if(thlj)
				jQuery("input[name='thlj']").val(thlj);
			if(h_title)
				jQuery("input[name='h_title']").val(h_title);
			if(h_xn)
				jQuery("input[name='h_xn']").val(h_xn);
			if(h_xqmc)
				jQuery("input[name='h_xqmc']").val(h_xqmc);
			if(h_zd1)
				jQuery("input[name='h_zd1']").val(h_zd1);
			if(h_zd2)
				jQuery("input[name='h_zd2']").val(h_zd2);
			if(h_zd3)
				jQuery("input[name='h_zd3']").val(h_zd3);
			if(h_zd4)
				jQuery("input[name='h_zd4']").val(h_zd4);
			if(h_zd5)
				jQuery("input[name='h_zd5']").val(h_zd5);
			if(h_zd6)
				jQuery("input[name='h_zd6']").val(h_zd6);
			if(h_zd7)
				jQuery("input[name='h_zd7']").val(h_zd7);
			if(h_zd8)
				jQuery("input[name='h_zd8']").val(h_zd8);
			if(h_zd9)
				jQuery("input[name='h_zd9']").val(h_zd9);
			if(h_zd10)
				jQuery("input[name='h_zd10']").val(h_zd10);
			
			jQuery('#commBbdmpzForm').submit();
}
