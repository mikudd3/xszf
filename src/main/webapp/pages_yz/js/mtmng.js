new Vue({
    el: '#app',
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            totalCount: 0,
            pagination: {},
            dialogFormVisible: false,
            dataList: [
                {
                    //查询时返回维修id
                    id: 1,
                    address: 101,
                    type: "卧室窗户",
                    realname: "l",
                    phone: 651,
                    set_date: "111111111",
                    status: 0
                }
            ],
            formData: {},
            id: 0,
            yhsf: 0,
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
                    this.yhsf = resp.data.data.yhsf;
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
                url: "/service/page1",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    //用户id
                    id: this.id,
                }
            }).then((res) => {
                if (res.data.code == 1) {
                    this.dataList = res.data.data.records;
                    this.totalCount = res.data.data.total;
                    console.log(this.totalCount)
                } else {
                    this.$message.error(res.data.msg);
                }
            })

        },
        //弹出添加窗口
        handleUpdate(row) {
            //维修id
            this.formData = row;
            console.log(row)
            this.dialogFormVisible = true;
        },
        //更新维修信息
        updata() {
            axios({
                method: "post",
                url: "/service/updata",
                data: this.formData,
            }).then((res) => {
                console.log(res)
                if (res.data.code == 1) {
                    this.$message({
                        showClose: true,
                        message: '更新成功！',
                        type: 'success'
                    });
                } else {
                    this.$message({
                        showClose: true,
                        message: '更新失败！',
                        type: 'error'
                    });
                }
                this.getAll();
            })
            this.dialogFormVisible = false;
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