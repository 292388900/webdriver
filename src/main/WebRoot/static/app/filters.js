/**
 * Format disk size to a pretty style
 * 1024B => 1KB
 * @param longSize  the long size to format
 */
app.filter('prettySize', function(){
   return function(longSize) {
       if(longSize == null) return;
       var units = ['B', 'KB', 'MB', 'GB'];
       var i = 0;
       var resultSize = '0';
       var unit = units[0];
       while (longSize > 1024) {
           unit = units[++i];
           resultSize = Math.floor(longSize * 100 / 1024) / 100;
           longSize = longSize / 1024;
       }
       return resultSize + unit;
   };
});

/**
 * Date formatter
 * @param longDate  the long date to format
 * @param pattern   date pattern. (e.g.,yyyy-MM-dd)
 */
app.filter('dateFormatter',function(){
   return function(longDate, pattern) {
       if(longDate == null) return;
       console.log(longDate);
       var date = new Date(longDate);
       var o = {
           "M+" : date.getMonth()+1,
           "d+" : date.getDate(),
           "h+" : date.getHours(),
           "m+" : date.getMinutes(),
           "s+" : date.getSeconds(),
           "q+" : Math.floor((date.getMonth()+3)/3),
           "S"  : date.getMilliseconds()
       };
       var stringDate;
       if(/(y+)/.test(pattern))
           stringDate=pattern.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
       for(var k in o)
           if(new RegExp("("+ k +")").test(pattern))
               stringDate = pattern.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
       return stringDate;
   };
});
