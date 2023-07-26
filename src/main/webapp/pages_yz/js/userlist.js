new Vue({
    el: '#app',
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            totalCount: 0,
            pagination: {},
            dataList: [
                {
                    username: "zhangsan",
                    realname: "zhangsan",
                    sex: "男",
                    sfz: 132,
                    phone: 1234567,
                    email: "1234@qq.com",
                    status: 0,
                    createTime: "2023-7-7"
                }
            ],//当前页要展示的列表数据
            username: "",
            phone: "",
            id: 0
        }
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getUser();
    },
    methods: {
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
                method: "post",
                url: "/user/page1",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    username: this.username,
                    phone: this.phone,
                    id: this.id
                }
            }).then((res) => {
                if (res.data.code == 1) {
                    let r = res.data;
                    this.dataList = r.data.records;
                    this.totalCount = r.data.total;
                    console.log(this.totalCount)
                } else {
                    this.$message.error(res.data.msg);
                }
            })

        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.getAll();
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getAll();
        }
    }
})