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
                    address: "桂林",
                    zhm: "ly",
                    status: 0,
                }
            ],//当前页要展示的列表数据
            address: "",
            status: "",
            user_id: 0
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
                    this.user_id = resp.data.data.id;
                    console.log(this.user_id)
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
                url: "/rent/page",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    address: this.address,
                    status: this.status,
                    user_id: this.user_id
                }
            }).then((res) => {
                if (res.data.code == 1) {
                    let r = res.data;
                    console.log(r);
                    this.dataList = r.data.records;
                    this.totalCount = r.data.total;
                    console.log(this.totalCount)
                } else {
                    this.$message.error(res.data.msg);
                }
            })

        },
        cz() {
            this.address = "";
            this.status = "";
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