//index.js
//获取应用实例
const app = getApp()

Page({
  data: {

  },
  doLogin: function(e){
    var formObject=e.detail.value;
    var username=formObject.username;
    var password=formObject.password;
    //判断用户名和密码是否为空
    if(username.length==0||password.length==0){
      wx.showToast({
        title: '用户名和密码不能为空',
        icon: 'none',
        duration: 2000
      })
    }else{
      wx.request({
        url: app.serverUrl +"/login",
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
          var status=res.data.status;
          if(status==200){
            wx.showToast({
              title: '验证通过',
              icon: 'success',
              duration: 2000
            })
            app.useInfo = res.data.data
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
  writing: function () {
    wx.hideToast();
  }

})
