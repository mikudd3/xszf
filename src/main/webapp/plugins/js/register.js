Vue.createApp({
    data() {
        return {
            username: "",
            password: "",
            password2: "",
            phone: "",
            sex: "",
            realname: "",
            sfz: "",
            email: ""
        }
    },
    methods: {
        doRegister() {
            if (this.username == "" || this.username == null) {
                alert("用户名不能为空")
            } else if (this.password == "" || this.password == null) {
                alert("密码不能为空")
            } else if (this.password2 == "" || this.password2 == null) {
                alert("请再次输入密码")
            } else if (this.password != this.password2) {
                alert("两次输入不一样")
            } else if (!/^1\d{10}$/.test(this.phone)) {
                alert("手机号输入有误")
            } else if (!/^(女|男)$/.test(this.sex)) {
                alert("请输入男或者女")
            } else if (this.realname == "" || this.realname == null) {
                alert("姓名不能为空")
            } else if (!/^\d{18}$/.test(this.sfz)) {
                alert("请输入正确的身份证号")
            } else if (!/^\w+@\w+\.[a-zA-z]{2,}/.test(this.email)) {
                alert("请输入正确的邮箱")
            } else {
                axios({
                    methods: "get",
                    url: "user/add",
                    params: {
                        username: this.username,
                        password: this.password,
                        phone: this.phone,
                        sex: this.sex,
                        realname: this.realname,
                        sfz: this.sfz,
                        email: this.email,
                    }
                }).then(res => {
                    let result = res.data;
                    if (result.code == 1) {
                        location.href = "login.html"
                    } else {
                        console.log(result.msg)
                        this.$message.error(result.msg)
                    }
                })
            }
        }
    }
}).mount("#form2");