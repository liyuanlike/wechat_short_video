//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    
  },
  doRegist: function(e){
    var formObject=e.detail.value;
    var username = formObject.username;
    var password=formObject.password;
    //判断用户名密码是否为空
    if(username.length==0 || password.length==0){
      wx.showToast({
        title: '用户名或密码不能为空',
        icon: 'none',
        duration: 2000 //2秒后消失
      })
    }else{
       var serverUrl=app.serverUrl;
       wx.request({
         url: serverUrl+"/regist",
         method: "POST",
         data: {
           username: username,
           password: password
         },
         header: {
           "content-type": "application/json"
         },
         //访问成功
         success: function(res){
          //  console.log(res);
           var status=res.data.status;
           //注册成功
           if(status==200){
             wx.showToast({
               title: '恭喜你，注册成功',
               icon: 'none',
               duration: 2000
             }),
               app.useInfo=res.data.data
           }else{
             wx.showToast({
               title: res.data.msg,
               icon: 'none',
               duration: 2000
             })
           }
         }
       })
    }
  },

  //写数据时隐藏Toast
  writing: function(){
    wx.hideToast();
  }
})
