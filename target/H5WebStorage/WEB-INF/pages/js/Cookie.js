/**
 * Created by zhj02 on 2015/8/17 0017.
 */
var cookie = {
    set:function(key, val, time) { // 设置Cookie方法
        var date = new Date() ; // 获取当前系统时间
        var expiresDays = time ; // 将date设置为n天以后的时间,time是天数
        date.setTime(date.getTime() + expiresDays*24*3600*1000) ; // 格式化为Cookie识别的时间
        document.cookie = key + '=' + val + ';expires=' + date.toUTCString() ; // 设置Cookie
    },
    get:function(key) { // 获取Cookie方法
        /* 获取Cookie参数 */
        var getCookie = document.cookie.replace(/[ ]/g, "") ; // 获取Cookie，并且将获得的Cookie格式化，去掉空格字符
        var arrCookie = getCookie.split(';') ; // 将获得的Cookie以“;”分号为标识，将cookie保存到arrCookie的数组中
        var tips ; // 声明变量tips
        for ( var i = 0 ; i < arrCookie.length ; i ++ ) { // 使用for讯混查找cookie中的tips变量
            var arr = arrCookie[i].split('=') ; // 将单条cookie以“=”为标识，将Cookie保存到arr数组中
            if( key == arr[0] ) { // 匹配变量名称，其中arr[0]是指的cookie名称，如果该条变量为tips则执行判断语句中的赋值操作
                tips = arr[1] ; // 将cookie的值赋给变量tips
                break ; // 终止for循环遍历
            }
        }
        return tips ;
    }
}
