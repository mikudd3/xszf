new Vue({
    el: '#app',
    data: {
        dataList: [
            {
                id: "2",
                index: "1",
                address_detail: "学府雁园1栋0101室",
                basemoney: "1000",
                yajin: "1000",
                fangdong: "xx"
            }
        ],//当前页要展示的列表数据
        id: 0,
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getUser();
    },
    methods: {
        //同意出租请求
        tycz(row) {
            //下载合同的逻辑
            axios({
                url: "/rent/tycz?id=" + row.id,
                method: "post",
            }).then(resp => {
                if (resp.data.code == 1) {
                    this.$message.success("修改成功");
                    this.getAll();
                } else {
                    this.$message.error(resp.data.msg);
                }
            })
        },
        //拒绝出租请求
        jjcz(row) {
            this.$confirm("此操作将拒绝用户的租房请求，是否继续？", "提示", {
                type: "info"
            }).then(() => {
                //做删除业务
                //根据id查询数据
                axios({
                    url: "/rent/jjcz?id=" + row.id,
                    method: "post",
                }).then(resp => {
                    if (resp.data.code == 1) {
                        this.$message.success("修改成功");
                        this.getAll();
                    } else {
                        this.$message.error(resp.data.msg);
                    }
                })

            }).catch(() => {
                this.$message.info("取消操作")
            })

        },
        getUser() {
            axios({
                url: "/user/getUser",
                method: "get",
            }).then(resp => {
                if (resp.data.code == 1) {
                    this.id = resp.data.data.id;
                    console.log(this.id)
                    this.getAll();
                } else {
                    this.$message.error(resp.data.msg);
                }
            })
        },
        //列表
        getAll() {
            axios({
                method: "get",
                url: "/rent/shfz?id=" + this.id,
            }).then((res) => {
                if (res.data.code == 1) {
                    this.dataList = res.data.data;
                } else {
                    this.$message.error(res.data.msg);
                }
            })

        },
    }
})