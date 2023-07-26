new Vue({
    el: "#app",
    data() {
        return {
            username: "",
            password: "",
        }

    },
    methods: {
        dosubmit() {
            axios({
                url: "/admin/login",
                method: "post",
                data: {
                    username: this.username,
                    password: this.password
                }
            }).then(resp => {
                if(resp.data.code == 1){
                    //登录成功
                    location.href="main.html";
                    localStorage.setItem('emp', resp.data.data);
                }else {
                    console.log(resp)
                    this.$message.error(resp.data.msg)
                }
            })
        },

    },
})