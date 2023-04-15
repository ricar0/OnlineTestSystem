
export const getDateDiff = (startTime, endTime, diffType)=> {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式 
    startTime = startTime.replace(/\-/g, "/");
    endTime = endTime.replace(/\-/g, "/");
    //将计算间隔类性字符转换为小写
    diffType = diffType.toLowerCase();
    var sTime =new Date(startTime); //开始时间
    var eTime =new Date(endTime); //结束时间
    //作为除数的数字
    var timeType =1;
    switch (diffType) {
        case"second":
            timeType =1000;
        break;
        case"minute":
            timeType =1000*60;
        break;
        case"hour":
            timeType =1000*3600;
        break;
        case"day":
            timeType =1000*3600*24;
        break;
        default:
        break;
    }
    return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(timeType));
}

Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}

export const timediff = (begin_time, end_time)=> {
    //年月日时分秒转换为时间戳
    let beginTime = (new Date(begin_time).getTime()) / 1000;
    let endTime = (new Date(end_time).getTime()) / 1000;
    var starttime = ''
    var endtime = ''
    if (beginTime < endTime) {
      starttime = beginTime;
      endtime = endTime;
    } else {
      starttime = endTime;
      endtime = beginTime;
    }
    //计算天数
    var timediff = endtime - starttime;
    var days = parseInt(timediff / 86400);
    //计算小时数
    var remain = timediff % 86400;
    var hours = parseInt(remain / 3600);
    //计算分钟数
    var remain = remain % 3600;
    var mins = parseInt(remain / 60);
    var secs = remain % 60;
    var res = days + '天' + hours + '小时' + mins + '分' + secs + '秒';
    return res;
}

export const timediff2 = (begin_time, end_time)=> {
    //年月日时分秒转换为时间戳
    let beginTime = (new Date(begin_time).getTime()) / 1000;
    let endTime = (new Date(end_time).getTime()) / 1000;
    var starttime = ''
    var endtime = ''
    if (beginTime < endTime) {
      starttime = beginTime;
      endtime = endTime;
    } else {
      starttime = endTime;
      endtime = beginTime;
    }
    var timediff = endtime - starttime;
    //计算分钟数
    var mins = parseInt(timediff / 60);
    var secs = timediff % 60;
    var res = mins + '分' + secs + '秒';
    return res;
}