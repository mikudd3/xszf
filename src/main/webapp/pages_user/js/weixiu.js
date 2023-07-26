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
                    zfid: 100,
                    type: "aa",
                    status: "bb",
                    money: "100",
                    date: 2023 - 12 - 11,
                    set_date: 300,
                }
            ],//当前页要展示的列表数据
            formData: {},//表单数据
            dialogFormVisible: false,//控制表单是否可见
            addresslist: {},
            weixiu: {
                zfid: "",
                status: ""
            },
            id: 0,
            yhsf:0,
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
                url: "/service/page",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    //用户id
                    id: this.id,
                    yhsf:this.yhsf,
                    zfid: this.weixiu.zfid,
                    status: this.weixiu.status
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
        resetForm() {
            this.formData = {}
        },
        //弹出添加窗口
        handleCreate() {
            this.resetForm();
            axios({
                method: "post",
                url: "/service/gethousedata",
                data: {
                    //取用户id确定其户下的租房
                    id: this.id,
                }
            }).then((res) => {
                if (res.data.code == 1) {
                    this.addresslist = res.data.data.records;
                    console.log(this.addresslist)
                } else {
                    this.$message.error(res.data.msg);
                }
            })
            this.dialogFormVisible = true;

        },
        handleAvatarSuccess(response) {
            this.formData.photo = response.data;
            console.log(this.imageUrl)
            console.log(response.data)
        },
        add() {
            axios({
                method: "post",
                url: "/service/add",
                data: this.formData,
            }).then((res) => {
                if (res.data.code == 1) {
                    this.dialogFormVisible = false;
                    this.$message({
                        showClose: true,
                        message: '上报成功！',
                        type: 'success'
                    });
                } else {
                    this.$message.error(res.data.msg);
                }
            })
        }
    }
})