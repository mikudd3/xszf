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
                    realname: "言",
                    sex: "男",
                    sfz: "4521333151051",
                    phone: "12345678",
                    email: "1234@qq.com",
                }
            ],//当前页要展示的列表数据
            formData: {},//表单数据
            dialogFormVisible4Edit: false,//编辑表单是否可见
            username: "",
            imageUrl: "",
        }
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getAll();
    },
    methods: {
        //列表
        getAll() {
            axios({
                method: "post",
                url: "/user/userShPage",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    username: this.username,
                }
            }).then((res) => {
                if(res.data.code ==1){
                    let r = res.data;
                    this.dataList = r.data.records;
                    this.totalCount = r.data.total;
                    console.log(this.totalCount)
                }else{
                    this.$message.error(resp.data.msg)
                }

            })

        },
        //弹出审核窗口
        handleUpdate(row) {
            //请求数据
            axios({
                method: "get",
                url: "/user/getUserShVo?id=" + row.id,
            }).then((res) => {
                if(res.data.code == 1){
                    this.formData = res.data.data;
                    this.imageUrl = `/common/download?name=${this.formData.zmcl[0]}`
                    console.log(this.formData.zmcl[0])
                    this.dialogFormVisible4Edit = true;
                }else {
                    this.$message.error(res.data.msg)
                }
            })

        },
        //通过审核
        handleEdit() {
            this.dialogFormVisible4Edit = false;
        },
        handlePass(row) {
            this.$confirm("此操作将通过请求，请确定审核材料无误", "提示", {
                type: "info"
            }).then(() => {
                axios({
                    method: "post",
                    url: "/user/UserPass?id=" + row.id,
                }).then((res) => {
                    if(res.data.code !=1){
                        this.$message.error(res.data.msg)
                    }
                }).finally(() => {
                    this.getAll();
                });
            }).catch(() => {
                this.$message.info("退出审核")
            })
        },
        // 审核不通过
        handleDelete(row) {
            //弹出提示框
            this.$confirm("此操作将拒绝请求，是否继续？", "提示", {
                type: "info"
            }).then(() => {
                this.formData.status = "4"
                axios({
                    method: "post",
                    url: "/user/UserRefuse?id=" + row.id,
                }).then((res) => {
                    if(res.data.code !=1){
                        this.$message.error(res.data.msg)
                    }
                }).finally(() => {
                    this.getAll();
                });

            }).catch(() => {
                this.$message.info("退出审核")
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