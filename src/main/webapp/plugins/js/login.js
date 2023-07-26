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
                url: "/user/login",
                method: "post",
                params: {
                    username: this.username,
                    password: this.password
                }
            }).then(res => {
                let result = res.data;
                if (result.code == 1) {
                    let user = result.data;
                    if (user.yhsf == 0 || user.yhsf == 2) {
                        //住客 跳转用户界面
                        location.href = "/pages_user/main.html"
                    } else {
                        //跳转房东界面
                        location.href = "/pages_yz/pages/main.html"
                    }
                } else {
                    this.$message.error(result.msg)
                }
            })
        },
    },
})