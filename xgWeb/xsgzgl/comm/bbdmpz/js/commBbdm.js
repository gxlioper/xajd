/**
 * 
 * @param data
 * @return
 */
function commBbdm(data){
			var mkdm = data['mkdm'];   		//模块代码
			var ywid = data['ywid'];		//业务id
			var thlj = data['thlj'];		//退回路径
			var h_title = data['h_title'];	//页面title参数
			var h_xn = data['h_xn'];      	//页面学年参数
			var h_xqmc = data['h_xqmc'];	//页面学期参数
			var h_zd1 = data['h_zd1'];		//参数字段1
			var h_zd2 = data['h_zd2'];		//参数字段2
			var h_zd3 = data['h_zd3'];		//参数字段3
			var h_zd4 = data['h_zd4'];		//参数字段4
			var h_zd5 = data['h_zd5'];		//参数字段5
			var h_zd6 = data['h_zd6'];		//参数字段6
			var h_zd7 = data['h_zd7'];		//参数字段7
			var h_zd8 = data['h_zd8'];		//参数字段8
			var h_zd9 = data['h_zd9'];		//参数字段9
			var h_zd10 = data['h_zd10'];	//参数字段10

			if(!mkdm){
				showAlertDivLayer("请配置模块代码(注：该信息不能出现在生产环境中,请务必配置正确的模块代码参数！)");
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
